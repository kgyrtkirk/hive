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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hbase.shaded.com.google.common.collect.Lists;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.Hook;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.HooksLoader;
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

  private class ReExecHooksLoader extends HooksLoader {

    public ReExecHooksLoader(HiveConf conf) {
      super(conf);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Hook> List<T> getHooks(ConfVars hookConfVar, Class<?> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      List<T> ret = Lists.newArrayList();
      ret.addAll(super.getHooks(hookConfVar, clazz));
      if (ExecuteWithHookContext.class.equals(clazz)) {
        ret.add((T) new ExecutionInfoHook());
        ret.add((T) new OperatorStatsReaderHook());
      }
      return ret;
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
    coreDriver = new Driver(queryState, userName, new ReExecHooksLoader(queryState.getConf()), queryInfo, null);
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
  public void setTryCount(int maxValue) {
    coreDriver.setTryCount(maxValue);
  }

  @Override
  public CommandProcessorResponse run() throws CommandNeedRetryException {
    String firstCommand = currentQuery;
    boolean forceRexec = false;
    if (coreDriver.getConf().getBoolean("hive.query.reexecution.explain", false)) {
      Pattern p = Pattern.compile("^[ ]*explain[ ]+(analyze[ ]+|)", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(currentQuery);
      if (m.find()) {
        firstCommand = m.replaceAll("");
        forceRexec = true;
      }
      //      if (currentQuery.trim().toLowerCase().startsWith("explain")) {
      //        firstCommand = currentQuery.trim().substring("explain".length());
      //        forceRexec = true;
      //      }
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
  public CommandProcessorResponse run(String command) throws CommandNeedRetryException {
    CommandProcessorResponse r0 = compileAndRespond(command);
    if (r0.getResponseCode() != 0) {
      return r0;
    }
    return run();
  }

  protected abstract void prepareToReExecute();

  @Override
  public boolean getResults(List res) throws IOException, CommandNeedRetryException {
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
