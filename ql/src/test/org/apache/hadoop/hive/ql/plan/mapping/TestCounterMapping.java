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
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.plan.mapper.HiveFilterRef;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;

public class TestCounterMapping {

  @ClassRule
  public static HiveTestEnvSetup env_setup = new HiveTestEnvSetup();

  @BeforeClass
  public static void beforeClass() throws Exception {
    IDriver driver = createDriver();
    dropTables(driver);
    String cmds[] = {
        // @formatter:off
        "create table s (x int)",
        "insert into s values (1),(2),(3),(4),(5),(6),(7),(8),(9),(10)",
        "create table tu(id_uv int,id_uw int,u int)",
        "create table tv(id_uv int,v int)",
        "create table tw(id_uw int,w int)",

        "from s\n" +
        "insert overwrite table tu\n" +
        "        select x,x,x\n" +
        "        where x<=6 or x=10\n" +
        "insert overwrite table tv\n" +
        "        select x,x\n" +
        "        where x<=3 or x=10\n" +
        "insert overwrite table tw\n" +
        "        select x,x\n" +
        "",
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

  private PlanMapper getMapperForQuery(IDriver driver, String query) {
    int ret;
    try {
      ret = driver.run(query).getResponseCode();
    } catch (CommandNeedRetryException e) {
      throw new RuntimeException("remove this exception");
    }
    assertEquals("Checking command success", 0, ret);
    PlanMapper pm0 = ((Driver) driver).getContext().getPlanMapper();
    return pm0;
  }

  @Test
  public void testFilterNodesHasRuntimeInfo() throws ParseException {
    IDriver driver = createDriver();
    String query="select sum(u) from tu where u>1";
    PlanMapper pm = getMapperForQuery(driver, query);

    List<HiveFilterRef> nodes = pm.getAll(HiveFilterRef.class);

    int asdf = 1;
  }

  @Test
  @Ignore
  public void testFilterNodesHasRuntimeInfoXXX() throws ParseException {
    IDriver driver = createDriver();
    // @formatter:off
    String query="select sum(u*v*w) from tu\n" +
    "        join tv on (tu.id_uv=tv.id_uv)\n" +
    "        join tw on (tu.id_uw=tw.id_uw)\n" +
    "        where w>9 and u>1 and v>3";
    // @formatter:on
    PlanMapper pm = getMapperForQuery(driver, query);

    List<HiveFilterRef> nodes = pm.getAll(HiveFilterRef.class);

    int asdf = 1;
  }

  private static IDriver createDriver() {
    HiveConf conf = new HiveConf(Driver.class);
    //    HadoopShims shims = ShimLoader.getHadoopShims();
    //    MiniMrShim mr1 = shims.getLocalMiniTezCluster(conf, true);
    //    mr1.setupConfiguration(conf);

    conf.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER,
        "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory");
    //    conf.setVar(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK, CheckInputReadEntityDirect.class.getName());
    HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY, false);
    HiveConf.setVar(conf, HiveConf.ConfVars.POSTEXECHOOKS, "org.apache.hadoop.hive.ql.StatsXXXHook");
    SessionState.start(conf);

    IDriver driver = DriverFactory.newDriver(conf);
    return driver;
  }

}
