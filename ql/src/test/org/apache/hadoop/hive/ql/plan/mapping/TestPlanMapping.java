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

package org.apache.hadoop.hive.ql.plan.mapping;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class TestPlanMapping {

  @ClassRule
  public static HiveTestEnvSetup env_setup = new HiveTestEnvSetup();


  @BeforeClass
  public static void beforeClass() throws Exception {
    IDriver driver = createDriver();
    dropTables(driver);
    String cmds[] = {
        // @formatter:off
        "create table tu(id_uv int,id_uw int,u int)",
        "create table tv(id_uv int,v int)",
        // @formatter:on
    };
    for (String cmd : cmds) {
      int ret = driver.run(cmd).getResponseCode();
      assertEquals("Checking command success", 0, ret);
    }
  }

  @AfterClass
  public static void afterClass() throws Exception {
    IDriver driver = createDriver();
    dropTables(driver);
  }


  public static void dropTables(IDriver driver) throws Exception {
    String tables[] = { "s", "tu", "tv", "tw" };
    for (String t : tables) {
      int ret = driver.run("drop table if exists " + t).getResponseCode();
      assertEquals("Checking command success", 0, ret);
    }
  }

  @Test
  public void testMappingSameQuery() throws ParseException, Exception {
    IDriver driver = createDriver();
    String query = "select sum(id_uv),sum(u) from tu where u>1";

    List res = new ArrayList();
    driver.getFetchTask().fetch(res);
    System.out.println(res);
  }


  private static IDriver createDriver() {
    HiveConf conf = env_setup.getTestCtx().hiveConf;
    conf.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER,
        "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory");
    HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY, false);
    SessionState.start(conf);

    IDriver driver = DriverFactory.newDriver(conf);
    return driver;
  }

}
