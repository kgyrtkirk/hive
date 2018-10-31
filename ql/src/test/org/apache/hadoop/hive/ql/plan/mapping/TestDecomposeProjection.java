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
package org.apache.hadoop.hive.ql.plan.mapping;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.StatsSources;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.stats.OperatorStatsReaderHook;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class TestDecomposeProjection {

  @ClassRule
  public static HiveTestEnvSetup env_setup = new HiveTestEnvSetup();

  @Rule
  public TestRule methodRule = env_setup.getMethodRule();

  @BeforeClass
  public static void beforeClass() throws Exception {
    IDriver driver = createDriver("");
    dropTables(driver);
    String[] cmds = {
        // @formatter:off
        "create table tu(id_uv int,id_uw int,u int)",
        "create table tv(id_uv int,v int)",
        "create table tw(id_uw int,w int)",

        "insert into tu values (10,10,10),(1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6)",
        "insert into tv values (10,10),(1,1),(2,2),(3,3)",
        "insert into tw values (10,10),(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9)",
        // @formatter:on
    };
    for (String cmd : cmds) {
      int ret = driver.run(cmd).getResponseCode();
      assertEquals("Checking command success", 0, ret);
    }
  }

  @AfterClass
  public static void afterClass() throws Exception {
    IDriver driver = createDriver("");
    dropTables(driver);
  }

  @After
  public void after() {
    StatsSources.clearGlobalStats();
  }

  public static void dropTables(IDriver driver) throws Exception {
    String[] tables = new String[] {"tu", "tv", "tw" };
    for (String t : tables) {
      int ret = driver.run("drop table if exists " + t).getResponseCode();
      assertEquals("Checking command success", 0, ret);
    }
  }

  private PlanMapper getMapperForQuery(IDriver driver, String query) throws CommandProcessorResponse {
    CommandProcessorResponse res = driver.run(query);
    if (res.getResponseCode() != 0) {
      throw res;
    }
    PlanMapper pm0 = driver.getContext().getPlanMapper();
    return pm0;
  }

  @Test
  public void testEquals() throws Exception {
    IDriver driver = createDriver("");
    String query = "select sum(case when v=1 then u else null end) as v_u1,"
        + "sum(case when v=2 then u else null end) as v_u2"
        + " from tu join tv on (tu.id_uv=tv.id_uv)";

    PlanMapper pm = getMapperForQuery(driver, query);
  }

  private static IDriver createDriver(String strategies) {
    HiveConf conf = env_setup.getTestCtx().hiveConf;

    conf.setBoolVar(ConfVars.HIVE_QUERY_REEXECUTION_ENABLED, true);
    conf.setBoolVar(ConfVars.HIVE_VECTORIZATION_ENABLED, false);
    conf.setVar(ConfVars.HIVE_QUERY_REEXECUTION_STRATEGIES, strategies);
    conf.setBoolVar(ConfVars.HIVE_EXPLAIN_USER, true);
    conf.set("zzz", "1");
    conf.set("reexec.overlay.zzz", "2000");
    //
    conf.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER,
        "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory");
    HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY, false);
    HiveConf.setVar(conf, HiveConf.ConfVars.POSTEXECHOOKS, OperatorStatsReaderHook.class.getName());
    SessionState.start(conf);

    IDriver driver = DriverFactory.newDriver(conf);
    return driver;
  }


}
