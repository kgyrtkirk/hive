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
package org.apache.hadoop.hive.ql.plan;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.calcite.rel.RelNode;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.PlanMapper;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class TestUX1 {

  @BeforeClass
  public static void beforeClass() throws Exception {
    Driver driver = createDriver();
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
    Driver driver = createDriver();
    dropTables(driver);
  }

  public static void dropTables(Driver driver) throws Exception {
    String tables[] = { "s", "tu", "tv", "tw" };
    for (String t : tables) {
      driver.run("drop table if exists " + t);
    }
  }

  @Test
  public void testSelectEntityDirect() throws ParseException {
    Driver driver = createDriver();
    // @formatter:off
    String query="select sum(u*v*w) from tu\n" +
    "        join tv on (tu.id_uv=tv.id_uv)\n" +
    "        join tw on (tu.id_uw=tw.id_uw)\n" +
    "        where w>9 and u>1 and v>3";
    // @formatter:on
    int ret;
    ret = driver.compile(query);
    assertEquals("Checking command success", 0, ret);
    PlanMapper pm0 = driver.getContext().getPlanMapper();
    ret = driver.compile(query);
    assertEquals("Checking command success", 0, ret);
    PlanMapper pm1 = driver.getContext().getPlanMapper();

    List<RelNode> nodes0 = pm0.getAll(RelNode.class);
    List<RelNode> nodes1 = pm1.getAll(RelNode.class);

    int asdf = 1;
  }

  private static Driver createDriver() {
    HiveConf conf = new HiveConf(Driver.class);
    conf.setVar(HiveConf.ConfVars.HIVE_AUTHORIZATION_MANAGER,
        "org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactory");
    //    conf.setVar(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK, CheckInputReadEntityDirect.class.getName());
    HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY, false);
    SessionState.start(conf);
    Driver driver = new Driver(conf);
    return driver;
  }

}
