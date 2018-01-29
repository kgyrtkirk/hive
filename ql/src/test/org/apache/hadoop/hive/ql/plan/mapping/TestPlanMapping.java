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
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.apache.calcite.rel.RelNode;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.refs.HiveFilterRef;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
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

  private PlanMapper getMapperForCompiledQuery(IDriver driver, String query) {
    int ret = driver.compile(query);
    assertEquals("Checking command success", 0, ret);
    PlanMapper pm0 = ((Driver) driver).getContext().getPlanMapper();
    return pm0;
  }

  private PlanMapper getMapperForExecutedQuery(IDriver driver, String query) {
    try {
      int ret = driver.run(query).getResponseCode();
      assertEquals("Checking command success", 0, ret);
      PlanMapper pm0 = ((Driver) driver).getContext().getPlanMapper();
      return pm0;
    } catch (CommandNeedRetryException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testMappingSameQuery() throws ParseException {
    IDriver driver = createDriver();
    String query = "select sum(id_uv),sum(u) from tu where u>1";
    PlanMapper pm0 = getMapperForCompiledQuery(driver, query);
    PlanMapper pm1 = getMapperForCompiledQuery(driver, query);

    HiveFilterRef fm0 = pm0.getAll(HiveFilterRef.class).get(0);
    HiveFilterRef fm1 = pm1.getAll(HiveFilterRef.class).get(0);

    assertEquals(fm0, fm1);
  }

  @Test
  public void testMappingLookup() throws ParseException {
    IDriver driver = createDriver();
    PlanMapper pm0 = getMapperForCompiledQuery(driver, "select sum(id_uv),sum(u) from tu where u>1");
    PlanMapper pm1 = getMapperForCompiledQuery(driver, "select sum(id_uv),sum(u) from tu where u>1");

    HiveFilterRef fm0 = pm0.getAll(HiveFilterRef.class).get(0);
    Object rn = pm1.lookup(RelNode.class, fm0);

    assertNotNull(rn);
  }

  @Test
  @Ignore("this will currently not work ; and not needed")
  public void testMappingDifferentAlias() throws ParseException {
    IDriver driver = createDriver();
    PlanMapper pm0 = getMapperForCompiledQuery(driver, "select sum(id_uv),sum(u) from tu as foo where u>1");
    PlanMapper pm1 = getMapperForCompiledQuery(driver, "select sum(id_uv),sum(u) from tu as bar where u>1");

    HiveFilterRef fm0 = pm0.getAll(HiveFilterRef.class).get(0);
    Object rn = pm1.lookup(RelNode.class, fm0);

    assertNotNull(rn);
  }

  @Test
  @Ignore
  public void testSelectEntityDirect() throws ParseException {
    IDriver driver = createDriver();
    // @formatter:off
    String query="select sum(u*v*w) from tu\n" +
    "        join tv on (tu.id_uv=tv.id_uv)\n" +
    "        join tw on (tu.id_uw=tw.id_uw)\n" +
    "        where w>9 and u>1 and v>3";
    // @formatter:on
    int ret;
    PlanMapper pm0 = getMapperForCompiledQuery(driver, query);
    ret = driver.compile(query);
    assertEquals("Checking command success", 0, ret);
    PlanMapper pm1 = ((Driver) driver).getContext().getPlanMapper();

    List<RelNode> nodes0 = pm0.getAll(RelNode.class);
    List<RelNode> nodes1 = pm1.getAll(RelNode.class);

    int asdf = 1;
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
