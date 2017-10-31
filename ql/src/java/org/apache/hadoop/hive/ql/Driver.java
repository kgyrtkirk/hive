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
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;

public interface Driver extends CommandProcessor {

  static Driver build0(QueryState queryState, String userName, QueryInfo queryInfo) {
    return new OldDriver(queryState, userName, queryInfo);
  }

  static Driver build0(HiveConf hcatConf) {
    return new OldDriver(hcatConf);
  }

  // FIXME seems unused (from CommandProcessor)
  @Override
  void init();

  QueryDisplay getQueryDisplay();

  // FIXME: make this mandatory; move to a builder?
  void setOperationId(String guid64);

  void setTryCount(int maxValue);

  // FIXME: this is the worst of all methods
  CommandProcessorResponse compileAndRespond(String statement);

  Schema getSchema();

  QueryPlan getPlan();

  CommandProcessorResponse run() throws CommandNeedRetryException;

  // FIXME: remove ret type; add closeable
  int close();
  void destroy();

  void setMaxRows(int maxRows);

  FetchTask getFetchTask();
  void resetFetch() throws IOException;
  boolean getResults(List convey) throws IOException, CommandNeedRetryException;
  boolean isFetchingTable();


}
