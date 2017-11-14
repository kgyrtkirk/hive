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

import java.io.File;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;

public class DriverFactory {

  static File settingFile = new File("/tmp/reexec");

  static boolean enabled = settingFile.exists();

  public static IDriver newDriver(HiveConf conf) {

    return newDriver(getNewQueryState(conf), null, null);
  }

  public static IDriver newDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    if (enabled) {
      return new RedDriver(queryState, userName, queryInfo);
    } else {
      return new Driver(queryState, userName, queryInfo);
    }
  }

  private static QueryState getNewQueryState(HiveConf conf) {
    // async=true techincally forces to enable configuration isolation between forces ; but instead for now explicitly:
    HiveConf newConf = new HiveConf(conf);
    return new QueryState.Builder().withGenerateNewQueryId(true).withHiveConf(newConf).build();
  }

  // it would be better to use conf at the callsite...but instead for now I clone the original magic from Driver
  @Deprecated
  public static IDriver newDriver() {
    // CLIDriver enter at this point
    HiveConf conf = (SessionState.get() != null) ? SessionState.get().getConf() : new HiveConf();
    return newDriver(conf);
  }

}
