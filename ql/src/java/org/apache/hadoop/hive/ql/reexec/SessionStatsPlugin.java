/*
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

package org.apache.hadoop.hive.ql.reexec;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.SessionStatsSource;
import org.apache.hadoop.hive.ql.plan.mapper.StatsSource;
import org.apache.hadoop.hive.ql.plan.mapper.StatsSources;
import org.apache.hadoop.hive.ql.session.SessionState;

public class SessionStatsPlugin implements IReExecutionPlugin {

  // cheang e type to interface
  @Deprecated
  private SessionStatsSource statsSource;
  private boolean alwaysCollectStats;

  @Override
  public void initialize(Driver driver) {
    HiveConf conf = driver.getConf();
    statsSource = (SessionStatsSource) getStatsSource(conf);
    driver.setStatsSource(statsSource);
    alwaysCollectStats = conf.getBoolVar(ConfVars.HIVE_QUERY_REEXECUTION_ALWAYS_COLLECT_OPERATOR_STATS);
  }


  private StatsSource getStatsSource(HiveConf conf) {
    SessionState ss = SessionState.get();
    statsSource = (SessionStatsSource) ss.getSessionStatsSource();
    if (statsSource == null) {
      statsSource = new SessionStatsSource(conf);
      ss.setSessionStatsSource(statsSource);
    }
    return statsSource;

  }

  @Override
  public void beforeExecute(int executionIndex, boolean explainReOptimization) {
    if (executionIndex == 1 && explainReOptimization) {
      alwaysCollectStats = true;
      // FIXME: statsSource should probably be set to a hierarchical one; and prevent modifications
    }
  }

  @Override
  public void afterExecute(PlanMapper planMapper, boolean success) {
    if (!success || alwaysCollectStats) {
      StatsSources.loadFromPlanMapper(statsSource, planMapper);
    }
  }

  @Override
  public boolean shouldReExecute(int executionNum) {
    return false;
  }

  @Override
  public void prepareToReExecute() {
  }

  @Override
  public boolean shouldReExecute(int executionNum, PlanMapper oldPlanMapper, PlanMapper newPlanMapper) {
    return false;
  }

}
