/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql;

import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.exec.tez.TezTask;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext.HookType;
import org.apache.hadoop.hive.ql.plan.BaseWork;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.OperatorStats;
import org.apache.tez.common.counters.TezCounter;
import org.apache.tez.common.counters.TezCounters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsXXXHook implements ExecuteWithHookContext {

  private static final Logger LOG = LoggerFactory.getLogger(StatsXXXHook.class);

  @Override
  public void run(HookContext hookContext) throws Exception {

    if (hookContext.getHookType() == HookType.PRE_EXEC_HOOK) {
      return;
    }

    HiveConf conf = hookContext.getConf();
    QueryPlan plan = hookContext.getQueryPlan();
    List<TezTask> rootTasks = Utilities.getTezTasks(plan.getRootTasks());
    for (TezTask tezTask : rootTasks) {
      List<BaseWork> baseWorks = tezTask.getWork().getAllWork();
      for (BaseWork baseWork : baseWorks) {
        String vertexName = baseWork.getName();
        LOG.info("Reading runtime statistics for tez vertex task: {}", vertexName);
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
              hookContext.getContext().getPlanMapper().link(op, operatorStats);
              //              opStats.put(operatorId, operatorStats);
            } else {
              LOG.warn("Unable to get statistics for vertex: {} opId: {} groupName: {}", vertexName, operatorId, groupName);
            }
          }
        }
      }
    }
  }

}
