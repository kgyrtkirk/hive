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

import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

  private static boolean mmm;

  public static IDriver newDriver(HiveConf conf) {
    return newDriver(getNewQueryState(conf), null, null);
  }

  enum ExecutionStrategy {
    none {
      @Override
      IDriver build(QueryState queryState, String userName, QueryInfo queryInfo) {
        return new Driver(queryState, userName, queryInfo);
      }
    };

    abstract IDriver build(QueryState queryState, String userName, QueryInfo queryInfo);
  }

  public static IDriver newDriver(QueryState queryState, String userName, QueryInfo queryInfo) {
    ExecutionStrategy strategy = ExecutionStrategy.none;
    return strategy.build(queryState, userName, queryInfo);
  }

  public static class ProtectedHiveConf extends HiveConf {

    private static transient final Logger LOG = LoggerFactory.getLogger(ProtectedHiveConf.class);

    private boolean protecting = false;
    private HiveConf conf;
    private Set<String> rp;

    public ProtectedHiveConf(HiveConf conf) {
      super(conf);
      this.conf = conf;
      protecting = true;
      rp = new HashSet<>();

      //      rp.add("fs.scheme.class");
      //      rp.add("hive.exec.dynamic.partition.mode");
      //      rp.add("hive.mapred.mode");
      //      rp.add("hive.query.id");
      //      rp.add("hive.query.string");
      //      rp.add("hive.rpc.query.plan");
      //      rp.add("io.file.buffer.size");
      //      rp.add("mapred.input.dir.recursive");
      //      rp.add("mapred.mapper.new-api");
      //      rp.add("mapreduce.job.name");
      //      rp.add("mapreduce.workflow.id");
      //      rp.add("mapreduce.workflow.name");
      //      rp.add("mapreduce.workflow.node.name");

    }

    @Override
    public void set(String arg0, String arg1, String arg2) {
      if (protecting) {
        LOG.error("protected#1; {} but set: {} = {}", reallyProtected(arg0), arg0, arg1);
      }
      if (!reallyProtected(arg0)) {
        conf.set(arg0, arg1, arg2);
      }
      super.set(arg0, arg1, arg2);
    }

    private boolean reallyProtected(String arg0) {
      return rp.contains(arg0);
    }

    @Override
    public void set(String name, String value) {
      if (protecting) {
        LOG.error("protected#2; {} but set: {} = {}", reallyProtected(name), name, value);
      }
      if (!reallyProtected(name)) {
        conf.set(name, value);
      }
      super.set(name, value);
    }

  }

  private static QueryState getNewQueryState(HiveConf conf) {
    // async=true techincally forces to enable HiveConf isolations; but instead for now explicitly:
    HiveConf newConf = new ProtectedHiveConf(conf);
    if (mmm) {
      newConf = conf;
    }
    return new QueryState.Builder().withGenerateNewQueryId(true).withHiveConf(newConf).build();
  }

  // it would be better to use conf at the callsite...but instead for now I clone the original magic from Driver
  @Deprecated
  public static IDriver newDriver() {
    // CLIDriver enter at this point
    HiveConf conf = (SessionState.get() != null) ? SessionState.get().getConf() : new HiveConf();
    return newDriver(conf);
  }

  public static void setFTsetMode(boolean b) {
    mmm = b;
  }

}
