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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.google.common.collect.Sets;

public class ReExecOverlayDriver implements IDriver {

  private class MyHook implements ExecuteWithHookContext {

    @Override
    public void run(HookContext hookContext) throws Exception {
      switch (hookContext.getHookType()) {
        case ON_FAILURE_HOOK:
          handleExecutionException(hookContext.getException());
          break;
      }
    }

  }

  private class MyHooksLoader extends HooksLoader {

    public MyHooksLoader(HiveConf conf) {
      super(conf);
    }

    @Override
    public <T extends Hook> List<T> getHooks(ConfVars hookConfVar, Class<?>... classes) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      List<T> ret = Lists.newArrayList();
      ret.addAll(super.getHooks(hookConfVar, classes));
      // HIVE-18057 will make this less awkward
      if (Sets.<Class<?>> newHashSet(classes).contains(ExecuteWithHookContext.class)) {
        ret.add((T) new MyHook());
      }

      return ret;
    }

  }

  private Driver coreDriver;
  private boolean possiblyRetry;
  private QueryState queryState;

  public ReExecOverlayDriver(QueryState queryState, String userName, QueryInfo queryInfo) {

    this.queryState = queryState;
    coreDriver = new Driver(queryState, userName, new MyHooksLoader(queryState.getConf()), queryInfo, null);
  }

  public void handleExecutionException(Throwable exception) {
    // FIXME: more resiliant failure cause detection :D
    if (exception.getMessage().contains("Vertex failed,")) {
      //    if (exception instanceof TezException) {
      possiblyRetry = true;
    }
    System.out.println(exception);
  }

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
    if (run0.getResponseCode() == 0 || !possiblyRetry) {
      return run0;
    }

    HiveConf_addOverlay(queryState.getConf(), HiveConf_subtree(queryState.getConf(), "reexec.overlay"));


    return coreDriver.run(command);
  }

  // should be moved to hiveconf
  private void HiveConf_addOverlay(HiveConf conf, Map<String, String> overlay) {
    for (Entry<String, String> entry : overlay.entrySet()) {
      conf.set(entry.getKey(), entry.getValue());
    }
  }

  // should be moved to hiveconf
  private Map<String,String> HiveConf_subtree(HiveConf conf, String string) {
    Map<String, String> ret = new HashMap<>();
    for (Entry<String, String> entry : conf) {
      if (entry.getKey().startsWith(string)) {
        ret.put(entry.getKey().substring(string.length() + 1), entry.getValue());
      }
    }
    return ret;
  }

  @Override
  public boolean getResults(List res) throws IOException, CommandNeedRetryException {
    return coreDriver.getResults(res);
  }

  @Override
  public void setMaxRows(int maxRows) {
    coreDriver.setMaxRows(maxRows);
    ;
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
  public int close() {
    return coreDriver.close();
  }

  @Override
  public void destroy() {
    coreDriver.destroy();
  }

  @Override
  public void resetQueryState() {
    coreDriver.resetQueryState();
  }

}
