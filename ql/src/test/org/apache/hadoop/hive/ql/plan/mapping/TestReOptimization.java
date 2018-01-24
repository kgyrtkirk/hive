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
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.apache.calcite.rel.metadata.RelMetadataQuery;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.ReOptimizeDriver;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.optimizer.calcite.reloperators.HiveFilter;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.plan.OperatorStats;
import org.apache.hadoop.hive.ql.plan.Statistics;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper.EquivGroup;
import org.apache.hadoop.hive.ql.session.SessionState;
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
    PlanMapper pm0 = ((ReOptimizeDriver) driver).getContext().getPlanMapper();
    return pm0;
  }

  @Test
  public void testStatsAreSetInReopt() throws ParseException {
    IDriver driver = createDriver();
    String query = "select assert_true_oom(${hiveconf:zzz} > sum(u*v)) from tu join tv on (tu.id_uv=tv.id_uv) where u<10 and v>1";

    PlanMapper pm = getMapperForQuery(driver, query);
    Iterator<EquivGroup> itG = pm.iterateGroups();
    int checkedOperators = 0;
    // FIXME: introduce the Operator trimmer mapper!
    while (itG.hasNext()) {
      EquivGroup g = itG.next();
      List<FilterOperator> fos = g.getAll(FilterOperator.class);
      List<OperatorStats> oss = g.getAll(OperatorStats.class);
      // FIXME: oss seems to contain duplicates
      //      List<HiveFilter> hf = g.getAll(HiveFilter.class);

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
  public void testReOptimizationCanChangeJoinOrder() throws ParseException {
    disablePPD();
    IDriver driver = createDriver();
    // @formatter:off
    String query="select sum(u*v*w) from tu\n" +
    "        join tv on (tu.id_uv=tv.id_uv)\n" +
    "        join tw on (tu.id_uw=tw.id_uw)\n" +
    "        where w>9 and u>1 and v>3";
    // @formatter:on
    PlanMapper pm = getMapperForQuery(driver, query);

    Iterator<EquivGroup> itG = pm.iterateGroups();
    int checkedOperators = 0;
    // FIXME: introduce the Operator trimmer mapper!
    while (itG.hasNext()) {
      EquivGroup g = itG.next();
      List<FilterOperator> fos = g.getAll(FilterOperator.class);
      List<OperatorStats> oss = g.getAll(OperatorStats.class);
      List<HiveFilter> hfs = g.getAll(HiveFilter.class);
      // FIXME: oss seems to contain duplicates
      //      List<HiveFilter> hf = g.getAll(HiveFilter.class);

      if (fos.size() > 0 && oss.size() > 0 && hfs.size() > 0) {
        fos.sort(TestCounterMapping.OPERATOR_ID_COMPARATOR.reversed());

        HiveFilter hf = hfs.get(0);
        FilterOperator fo = fos.get(0);
        OperatorStats os = oss.get(0);

        long cntFilter = RelMetadataQuery.instance().getRowCount(hf).longValue();
        assertEquals(os.getOutputRecords(), fo.getStatistics().getNumRows());
        assertEquals(os.getOutputRecords(), cntFilter);
        //
        //        FilterOperator fo = fos.get(0);
        //        OperatorStats os = oss.get(0);
        //
        //        Statistics stats = fo.getStatistics();
        //        assertEquals(os.getOutputRecords(), stats.getNumRows());
        //
        //        if (!(os.getOutputRecords() == 3 || os.getOutputRecords() == 6)) {
        //          fail("nonexpected number of records produced");
        //        }
        checkedOperators++;
      }
    }
    assertEquals(3, checkedOperators);

  }

  @Deprecated
  private void disablePPD() {
    // these things should be able to work with ppd on
    HiveConf conf = env_setup.getTestCtx().hiveConf;
    conf.set("hive.optimize.ppd", "false");
    //    conf.set("hive.auto.convert.join", "false");
  }

  private static IDriver createDriver() {
    HiveConf conf = env_setup.getTestCtx().hiveConf;

    conf.set("hive.query.reexecution.strategy", "reoptimize");
    conf.set("zzz", "1");
    conf.set("reexec.overlay.zzz", "2000");
    //
    conf.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER,
        "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory");
    HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY, false);
    HiveConf.setVar(conf, HiveConf.ConfVars.POSTEXECHOOKS, "org.apache.hadoop.hive.ql.StatsXXXHook");
    SessionState.start(conf);

    IDriver driver = DriverFactory.newDriver(conf);
    return driver;
  }


}
