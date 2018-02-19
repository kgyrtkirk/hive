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

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.stats.OperatorStatsReaderHook;

public abstract class AbstractReExecDriver implements IDriver {

  private class ExecutionInfoHook implements ExecuteWithHookContext {

    @Override
    public void run(HookContext hookContext) throws Exception {
      switch (hookContext.getHookType()) {
      case PRE_EXEC_HOOK:
        break;
      case POST_EXEC_HOOK:
        onExecutionSuccess(hookContext);
        break;
      case ON_FAILURE_HOOK:
        onExecutionFailure(hookContext);
        break;
      }
    }
  }

  private static class HandleReOptimizationExplain implements HiveSemanticAnalyzerHook {

    @Override
    public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast) throws SemanticException {
      return ast;
    }

    @Override
    public void postAnalyze(HiveSemanticAnalyzerHookContext context, List<Task<? extends Serializable>> rootTasks)
        throws SemanticException {
    }
  }

  protected Driver coreDriver;
  private QueryState queryState;
  private String currentQuery;

  @Override
  public HiveConf getConf() {
    return queryState.getConf();
  }

  protected abstract void onExecutionSuccess(HookContext hookContext);

  protected abstract void onExecutionFailure(HookContext hookContext);

  public AbstractReExecDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    this.queryState = queryState;
    coreDriver = new Driver(queryState, userName, queryInfo, null);
    hookup(new ExecutionInfoHook());
    hookup(new OperatorStatsReaderHook());
    coreDriver.getHookRunner().addSemanticAnalyzerHook(new HandleReOptimizationExplain());
  }

  private void hookup(ExecuteWithHookContext operatorStatsReaderHook) {
    coreDriver.getHookRunner().addPreHook(operatorStatsReaderHook);
    coreDriver.getHookRunner().addPostHook(operatorStatsReaderHook);
    coreDriver.getHookRunner().addOnFailureHook(operatorStatsReaderHook);
  }

  abstract protected void handleExecutionException(Throwable exception);

  @Override
  public int compile(String string) {
    return coreDriver.compile(string);
  }

  @Override
  public CommandProcessorResponse compileAndRespond(String statement) {
    currentQuery = statement;
    return coreDriver.compileAndRespond(statement);
  }

  @Override
  public QueryPlan getPlan() {
    return coreDriver.getPlan();
  }

  @Override
  public QueryDisplay getQueryDisplay() {
    return coreDriver.getQueryDisplay();
  }

  @Override
  public void setOperationId(String guid64) {
    coreDriver.setOperationId(guid64);
  }

  @Override
  public CommandProcessorResponse run() {
    String firstCommand = currentQuery;
    boolean forceRexec = false;
    if (coreDriver.getConf().getBoolean("hive.query.reexecution.explain", false)) {
      if (currentQuery.trim().toLowerCase().startsWith("explain")) {
        firstCommand = currentQuery.trim().substring("explain".length());
        forceRexec = true;
      }
    }
    CommandProcessorResponse cpr;
    if (!forceRexec) {
      cpr = coreDriver.run();
      if (cpr.getResponseCode() == 0 || !shouldReExecute()) {
        return cpr;
      }
    } else {
      coreDriver.run(firstCommand);
    }
    prepareToReExecute();
    return coreDriver.run(currentQuery);
  }

  @Override
  public CommandProcessorResponse run(String command) {
    CommandProcessorResponse r0 = compileAndRespond(command);
    if (r0.getResponseCode() != 0) {
      return r0;
    }
    return run();
  }

  protected abstract void prepareToReExecute();

  @Override
  public boolean getResults(List res) throws IOException {
    return coreDriver.getResults(res);
  }

  @Override
  public void setMaxRows(int maxRows) {
    coreDriver.setMaxRows(maxRows);
  }

  @Override
  public FetchTask getFetchTask() {
    return coreDriver.getFetchTask();
  }

  @Override
  public Schema getSchema() {
    return coreDriver.getSchema();
  }

  @Override
  public boolean isFetchingTable() {
    return coreDriver.isFetchingTable();
  }

  @Override
  public void resetFetch() throws IOException {
    coreDriver.resetFetch();
  }

  @Override
  public void close() {
    coreDriver.close();
  }

  @Override
  public void destroy() {
    coreDriver.destroy();
  }

  abstract protected boolean shouldReExecute();

}
