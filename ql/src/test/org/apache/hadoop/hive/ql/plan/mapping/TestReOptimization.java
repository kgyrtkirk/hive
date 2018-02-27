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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.plan.Statistics;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper.LinkGroup;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.stats.OperatorStats;
import org.apache.hadoop.hive.ql.stats.OperatorStatsReaderHook;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class TestReOptimization {

  @ClassRule
  public static HiveTestEnvSetup env_setup = new HiveTestEnvSetup();

  @Rule
  public TestRule methodRule = env_setup.getMethodRule();

  @BeforeClass
  public static void beforeClass() throws Exception {
    IDriver driver = createDriver();
    dropTables(driver);
    String cmds[] = {
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
    IDriver driver = createDriver();
    dropTables(driver);
  }

  public static void dropTables(IDriver driver) throws Exception {
    String tables[] = { "tu", "tv", "tw" };
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
  public void testStatsAreSetInReopt() throws Exception {
    IDriver driver = createDriver();
    String query = "select assert_true_oom(${hiveconf:zzz} > sum(u*v)) from tu join tv on (tu.id_uv=tv.id_uv) where u<10 and v>1";

    PlanMapper pm = getMapperForQuery(driver, query);
    Iterator<LinkGroup> itG = pm.iterateGroups();
    int checkedOperators = 0;
    while (itG.hasNext()) {
      LinkGroup g = itG.next();
      List<FilterOperator> fos = g.getAll(FilterOperator.class);
      List<OperatorStats> oss = g.getAll(OperatorStats.class);
      // FIXME: oss seems to contain duplicates

      if (fos.size() > 0 && oss.size() > 0) {
        fos.sort(TestCounterMapping.OPERATOR_ID_COMPARATOR.reversed());

        FilterOperator fo = fos.get(0);
        OperatorStats os = oss.get(0);

        Statistics stats = fo.getStatistics();
        assertEquals(os.getOutputRecords(), stats.getNumRows());

        if (!(os.getOutputRecords() == 3 || os.getOutputRecords() == 6)) {
          fail("nonexpected number of records produced");
        }
        checkedOperators++;
      }
    }
    assertEquals(2, checkedOperators);
  }

  @Test
  public void testReExecutedIfMapJoinError() throws Exception {

    IDriver driver = createDriver();
    String query =
        "select assert_true_oom(${hiveconf:zzz}>sum(1)) from tu join tv on (tu.id_uv=tv.id_uv) where u<10 and v>1";
    PlanMapper pm = getMapperForQuery(driver, query);

  }

  @Test(expected = CommandProcessorResponse.class)
  public void testNotReExecutedIfAssertionError() throws Exception {
    HiveConf conf = env_setup.getTestCtx().hiveConf;
    conf.setVar(ConfVars.HIVE_QUERY_REEXECUTION_STRATEGIES, "reoptimize");

    IDriver driver = createDriver();
    String query =
        "select assert_true(${hiveconf:zzz}>sum(1)) from tu join tv on (tu.id_uv=tv.id_uv) where u<10 and v>1";

    PlanMapper pm = getMapperForQuery(driver, query);
    assertEquals(1, driver.getContext().getExecutionIndex());
  }

  @Test
  public void testExplainSupport() throws Exception {

    IDriver driver = createDriver();
    String query = "explain reoptimization select 1 from tu join tv on (tu.id_uv=tv.id_uv) where u<10 and v>1";
    PlanMapper pm = getMapperForQuery(driver, query);
    List<String> res = new ArrayList<>();
    List<String> res1 = new ArrayList<>();
    while (driver.getResults(res1)) {
      res.addAll(res1);
    }

    assertEquals("2TS", 2, res.stream().filter(line -> line.contains("TS_")).count());
    assertEquals("2TS(runtime)", 2,
        res.stream().filter(line -> line.contains("TS") && line.contains("runtime")).count());

  }

  private static IDriver createDriver() {
    HiveConf conf = env_setup.getTestCtx().hiveConf;

    conf.set("hive.query.reexecution.strategy", "reoptimize");
    conf.set("hive.explain.user", "true");
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
