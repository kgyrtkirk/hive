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

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.hooks.HooksLoader;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;

public class RedDriver implements IDriver {

  private static class MyHooksLoader extends HooksLoader {

    public MyHooksLoader(HiveConf conf) {
      super(conf);
    }


    //    @Override
    //    public <T extends Hook> List<T> getHooks(ConfVars hookConfVar) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    //      List<T> hooks = super.getHooks(hookConfVar);
    //
    //      return hooks;
    //    }


  }

  private Driver coreDriver;

  public RedDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    //    coreDriver = new Driver(queryState, userName, queryInfo);
    coreDriver = new Driver(queryState, userName, new MyHooksLoader(queryState.getConf()), queryInfo, null);
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
    //    CommandProcessorResponse run0 = coreDriver.run(command);

    return coreDriver.run(command);
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
