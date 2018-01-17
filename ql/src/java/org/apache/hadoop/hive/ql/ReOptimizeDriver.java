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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.exec.tez.TezTask;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.plan.BaseWork;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.OperatorStats;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.tez.common.counters.TezCounter;
import org.apache.tez.common.counters.TezCounters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReOptimizeDriver extends AbstractReExecDriver {

  private static final Logger LOG = LoggerFactory.getLogger(ReOptimizeDriver.class);

  private static OperatorStatSource oss = new OperatorStatSource();

  private boolean retryPossible;

  public static class OperatorStatSource {

    private Map<String, OperatorStats> os;

    public OperatorStatSource() {
      os = new HashMap<>();
    }

    public void set(Map<String, OperatorStats> opStats) {
      os = opStats;
    }

    public void clear() {
      os = new HashMap<>();
    }

    @Deprecated
    public OperatorStats lookup(Operator<?> tsop) {
      return os.get(tsop.getOperatorId());
    }

  }
  private Map<String, OperatorStats> opStats = new HashMap<>();

  public ReOptimizeDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    super(queryState, userName, queryInfo);
  }

  @Override
  public void handleExecutionException(Throwable exception) {
    // FIXME: more resiliant failure cause detection :D
    if (exception.getMessage().contains("Vertex failed,")) {
      retryPossible = true;
    }
    System.out.println(exception);
  }

  @Override
  protected void prepareToReExecute() {
    oss.set(opStats);
    HiveConf conf = getConf();
    conf.putAll(conf.subtree("reexec.overlay"));
  }

  @Override
  protected void finish0() {
    oss.clear();
  }

  @Override
  protected boolean shouldReExecute() {
    return retryPossible;
  }

  @Override
  protected void onExecutionSuccess(HookContext hookContext) {
    onExecutionSuccess(hookContext);
  }

  @Override
  protected void onExecutionFailure(HookContext hookContext) {
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
              opStats.put(operatorId, operatorStats);
            } else {
              LOG.warn("Unable to get statistics for vertex: {} opId: {} groupName: {}", vertexName, operatorId, groupName);
            }
          }
        }

        //        // TODO: should it be uncompressed shuffle bytes?
        //        String shuffleCounterName = TaskCounter.SHUFFLE_BYTES.toString();
        //        List<Task<? extends Serializable>> parentTasks = tezTask.getParentTasks();
        //        long shuffleBytesTotal = 0;
        //        for (Task<? extends Serializable> pTask : parentTasks) {
        //          String currTaskName = vertexName.replaceAll("\\s+", "_");
        //          String pTaskName = pTask.getName().replaceAll("\\s+", "_");
        //          String groupName = TaskCounter.class.getSimpleName() + "_" + currTaskName + "_INPUT_" + pTaskName;
        //          TezCounter tezCounter = counters.getGroup(groupName).findCounter(shuffleCounterName, false);
        //          if (tezCounter != null) {
        //            shuffleBytesTotal += tezCounter.getValue();
        //          }
        //        }
      }
    }

    //    QueryPlan qp = hookContext.getQueryPlan();
    //    List<TezTask> f1 = Utilities.getTezTasks(qp.getRootTasks());
    //    for (TezTask tezTask : f1) {
    //      TezWork w = tezTask.getWork();
    //      List<BaseWork> wm = w.getAllWork();
    //      for (BaseWork baseWork : wm) {
    //        Set<Operator<?>> ops = baseWork.getAllOperators();
    //        Operator<?> o = ops.iterator().next();
    //        o.getParentOperators();
    //      }
    //      //      if (wm != null) {
    //      //        int asd = 1;
    //      //      }
    //    }
    handleExecutionException(hookContext.getException());
  }

  public static OperatorStatSource getOperatorStats() {
    return oss;
  }

  @Override
  public CommandProcessorResponse run() throws CommandNeedRetryException {
    return super.run();
  }

}