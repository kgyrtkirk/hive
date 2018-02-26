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

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.SimpleRuntimeStatsSource;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReOptimizeDriver extends AbstractReExecDriver {

  private static final Logger LOG = LoggerFactory.getLogger(ReOptimizeDriver.class);

  private boolean retryPossible;


  public ReOptimizeDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    super(queryState, userName, queryInfo);
  }

  @Override
  public void handleExecutionException(Throwable exception) {
    // FIXME: more resiliant failure cause detection
    if (exception.getMessage().contains("Vertex failed,")) {
      retryPossible = true;
    }
    System.out.println(exception);
  }

  @Override
  protected void prepareToReExecute() {
    PlanMapper pm = coreDriver.getContext().getPlanMapper();
    coreDriver.setRuntimeStatsSource(new SimpleRuntimeStatsSource(pm));
    HiveConf conf = getConf();
    conf.verifyAndSetAll(conf.subtree("reexec.overlay"));
  }

  @Override
  protected boolean shouldReExecute() {
    return retryPossible;
  }

  @Override
  protected void onExecutionSuccess(HookContext hookContext) {
  }

  @Override
  protected void onExecutionFailure(HookContext hookContext) {
    Throwable exception = hookContext.getException();
    if (exception == null) {
      LOG.info("Failure Exception not available");
      return;
    }
    handleExecutionException(exception);
  }


  @Override
  public CommandProcessorResponse run() {
    return super.run();
  }

  // FIXME: this should be different; or at least interface driven?
  @Override
  public Context getContext() {
    return coreDriver.getContext();
  }

}