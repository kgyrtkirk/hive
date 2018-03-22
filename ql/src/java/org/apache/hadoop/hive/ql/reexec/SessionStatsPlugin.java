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

import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.PrivateHookContext;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.SessionStatsSource;
import org.apache.hadoop.hive.ql.plan.mapper.StatsSources;
import org.apache.hadoop.hive.ql.session.SessionState;

public class SessionStatsPlugin implements IReExecutionPlugin {

  private SessionStatsSource statsSource;

  private class A implements ExecuteWithHookContext {

    @Override
    public void run(HookContext hookContext) throws Exception {
      PrivateHookContext h = (PrivateHookContext) hookContext;
      PlanMapper pm = h.getContext().getPlanMapper();

      StatsSources.loadFromPlanMapper(statsSource, pm);
    }

  }

  @Override
  public void initialize(Driver driver) {
    SessionState ss = SessionState.get();
    statsSource = (SessionStatsSource) ss.getSessionStatsSource();
    if (statsSource == null) {
      statsSource = new SessionStatsSource();
      ss.setSessionStatsSource(statsSource);
    }
    driver.setStatsSource(statsSource);
    ExecuteWithHookContext hook = new A();
    driver.getHookRunner().addPostHook(hook);
    driver.getHookRunner().addPostHook(hook);
  }

  @Override
  public void beforeExecute(int executionIndex, boolean explainReOptimization) {
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
