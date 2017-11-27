/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.ql.hooks;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.QueryPlan;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.exec.tez.TezTask;
import org.apache.hadoop.hive.ql.plan.BaseWork;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.RuntimeStatisticsCache;
import org.apache.hadoop.hive.ql.plan.OperatorStats;
import org.apache.tez.common.counters.TaskCounter;
import org.apache.tez.common.counters.TezCounter;
import org.apache.tez.common.counters.TezCounters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Post execution hook to cache runtime statistics from Tez vertices for a given query.
 */
public class TezRuntimeStatisticsHook implements ExecuteWithHookContext {
  private static final Logger LOG = LoggerFactory.getLogger(TezRuntimeStatisticsHook.class.getName());

  @Override
  public void run(HookContext hookContext) throws Exception {
    // don't run this hook for explain queries
    if (hookContext.getQueryPlan().isExplain()) {
      return;
    }
    switch (hookContext.getHookType()) {
      case POST_EXEC_HOOK:
      case ON_FAILURE_HOOK:
        updateRuntimeStats(hookContext);
        break;
      default:
        break;
    }
  }

  private void updateRuntimeStats(final HookContext hookContext) throws NoSuchAlgorithmException {
    HiveConf conf = hookContext.getConf();
    if (!"tez".equals(HiveConf.getVar(conf, HiveConf.ConfVars.HIVE_EXECUTION_ENGINE))) {
      return;
    }

    LOG.info("Executing runtime statistics post execution hook for tez..");
    QueryPlan plan = hookContext.getQueryPlan();
    if (plan == null) {
      return;
    }

    String queryMD5 = QueryPlan.getQueryMD5(plan.getQueryString());
    LOG.info("MD5: {} for query: {}", queryMD5, QueryPlan.normalizeQuery(plan.getQueryString()));
    Map<String, OperatorStats> opStats = RuntimeStatisticsCache.RUNTIME_STATS_CACHE.getIfPresent(queryMD5);
    if (opStats == null) {
      opStats = new HashMap<>();
    }
    List<TezTask> rootTasks = Utilities.getTezTasks(plan.getRootTasks());
    for (TezTask tezTask : rootTasks) {
      List<BaseWork> baseWorks = tezTask.getWork().getAllWork();
      for (BaseWork baseWork : baseWorks) {
        String vertexName = baseWork.getName();
        LOG.info("Updating runtime statistics for tez task: {}", vertexName);
        TezCounters counters = tezTask.getTezCounters();
        if (counters != null) {
          String groupName = HiveConf.getVar(conf, HiveConf.ConfVars.HIVECOUNTERGROUP);
          for (Operator<? extends OperatorDesc> op : baseWork.getAllOperators()) {
            String operatorId = op.getOperatorId();
            OperatorStats operatorStats = null;
            String counterName = Operator.Counter.RECORDS_OUT_OPERATOR.toString() + "_" + operatorId;
            TezCounter tezCounter = counters.getGroup(groupName).findCounter(counterName, false);
            if (tezCounter != null) {
              if (operatorStats == null) {
                operatorStats = new OperatorStats(operatorId);
              }
              operatorStats.setOutputRecords(tezCounter.getValue());
            }

            if (operatorStats != null) {
              opStats.put(operatorId, operatorStats);
            } else {
              LOG.warn("Unable to get statistics for vertex: {} opId: {} groupName: {}", vertexName, operatorId,
                groupName);
            }
          }
        }

        // TODO: should it be uncompressed shuffle bytes?
        String shuffleCounterName = TaskCounter.SHUFFLE_BYTES.toString();
        List<Task<? extends Serializable>> parentTasks = tezTask.getParentTasks();
        long shuffleBytesTotal = 0;
        for (Task<? extends Serializable> pTask : parentTasks) {
          String currTaskName = vertexName.replaceAll("\\s+", "_");
          String pTaskName = pTask.getName().replaceAll("\\s+", "_");
          String groupName = TaskCounter.class.getSimpleName() + "_" + currTaskName + "_INPUT_" + pTaskName;
          TezCounter tezCounter = counters.getGroup(groupName).findCounter(shuffleCounterName, false);
          if (tezCounter != null) {
            shuffleBytesTotal += tezCounter.getValue();
          }
        }
      }
    }

    if (!opStats.isEmpty()) {
      // TODO: look at the DAG status before updating (some killed tasks could end up with wrong stats). Only update
      // when the query SUCCEEDED or FAILED
      LOG.info("Updating runtime stats: {} for queryMD5: {}", opStats, queryMD5);
      RuntimeStatisticsCache.RUNTIME_STATS_CACHE.put(queryMD5, opStats);
    }
  }

}
