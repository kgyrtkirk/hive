///*
// * Licensed to the Apache Software Foundation (ASF) under one
// * or more contributor license agreements.  See the NOTICE file
// * distributed with this work for additional information
// * regarding copyright ownership.  The ASF licenses this file
// * to you under the Apache License, Version 2.0 (the
// * "License"); you may not use this file except in compliance
// * with the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package org.apache.hadoop.hive.ql.hooks;
//
//import java.util.List;
//import java.util.TreeMap;
//
//import org.apache.hadoop.hive.conf.HiveConf;
//import org.apache.hadoop.hive.ql.QueryPlan;
//import org.apache.hadoop.hive.ql.exec.Operator;
//import org.apache.hadoop.hive.ql.exec.Utilities;
//import org.apache.hadoop.hive.ql.exec.tez.TezTask;
//import org.apache.hadoop.hive.ql.plan.BaseWork;
//import org.apache.hadoop.hive.ql.plan.OperatorDesc;
//import org.apache.hadoop.hive.ql.plan.Statistics;
//import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
//import org.apache.hadoop.hive.ql.stats.OperatorStats;
//import org.apache.tez.common.counters.CounterGroup;
//import org.apache.tez.common.counters.TezCounter;
//import org.apache.tez.common.counters.TezCounters;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Checks whether there are mapjoin operators which are overloaded.
// */
//public class OverLoadedMapJoinCheckerHook implements ExecuteWithHookContext {
//
//  private static final Logger LOG = LoggerFactory.getLogger(OverLoadedMapJoinCheckerHook.class);
//
//  @Override
//  public void run(HookContext hookContext) throws Exception {
//
//    HiveConf conf = hookContext.getConf();
//    QueryPlan plan = hookContext.getQueryPlan();
//    List<TezTask> rootTasks = Utilities.getTezTasks(plan.getRootTasks());
//    for (TezTask tezTask : rootTasks) {
//      List<BaseWork> baseWorks = tezTask.getWork().getAllWork();
//      for (BaseWork baseWork : baseWorks) {
//        String vertexName = baseWork.getName();
//        LOG.debug("Reading runtime statistics for tez vertex task: {}", vertexName);
//        TezCounters tezCounters = tezTask.getTezCounters();
//        if (tezCounters != null) {
//          String groupName = HiveConf.getVar(conf, HiveConf.ConfVars.HIVECOUNTERGROUP);
//          CounterGroup group = tezCounters.getGroup(groupName);
//          TreeMap<String, TezCounter> counters1 = new TreeMap<>();
//          for (TezCounter tezCounter : group) {
//            counters1.put(tezCounter.getName(), tezCounter);
//          }
//
//          for (Operator<? extends OperatorDesc> op : baseWork.getAllOperators()) {
//            PlanMapper planMapper = ((PrivateHookContext) hookContext).getContext().getPlanMapper();
//
//            Statistics stats = planMapper.lookup(Statistics.class, op);
//          }
//
//
//            String operatorId = op.getOperatorId();
//            OperatorStats operatorStats = null;
//            String counterName = Operator.Counter.RECORDS_OUT_OPERATOR.toString() + "_" + operatorId;
//            TezCounter tezCounter = group.findCounter(counterName, false);
//            if (tezCounter != null) {
//              if (operatorStats == null) {
//                operatorStats = new OperatorStats(operatorId);
//              }
//              operatorStats.setOutputRecords(tezCounter.getValue());
//            }
//
//            if (operatorStats != null) {
//            } else {
//              LOG.debug("Unable to get statistics for vertex: {} opId: {} groupName: {}", vertexName, operatorId,
//                  groupName);
//            }
//          }
//        }
//      }
//    }
//  }
//}
