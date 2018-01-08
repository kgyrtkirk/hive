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

public abstract class AbstractReExecDriver implements IDriver {


  private class ReExecutionInfoHook implements ExecuteWithHookContext {

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

    @Override
    public <T extends Hook> List<T> getHooks(ConfVars hookConfVar, Class<?> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      List<T> ret = Lists.newArrayList();
      ret.addAll(super.getHooks(hookConfVar, clazz));
      if (ExecuteWithHookContext.class.equals(clazz)) {
        // FIXME: address this warning
        ret.add((T) new ReExecutionInfoHook());
      }
      return ret;
    }
  }

  protected Driver coreDriver;
  private QueryState queryState;

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
    return coreDriver.run();
  }

  @Override
  public CommandProcessorResponse run(String command) throws CommandNeedRetryException {
    CommandProcessorResponse run0 = coreDriver.run(command);
    if (run0.getResponseCode() == 0 || !shouldReExecute()) {
      return run0;
    }

    prepareToReExecute();
    return coreDriver.run(command);
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

  protected void finish0() {
  }

}
