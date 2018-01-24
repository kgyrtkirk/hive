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

import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hbase.shaded.com.google.common.collect.Sets;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.lockmgr.HiveTxnManager;
import org.apache.hadoop.hive.ql.plan.HiveOperation;
import org.apache.hadoop.hive.ql.session.LineageState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class to store query level info such as queryId. Multiple queries can run
 * in the same session, so SessionState is to hold common session related info, and
 * each QueryState is to hold query related info.
 */
public class QueryState {
  /**
   * current configuration.
   */
  private final HiveConf queryConf;
  /**
   * type of the command.
   */
  private HiveOperation commandType;

  /**
   * Per-query Lineage state to track what happens in the query
   */
  private LineageState lineageState = new LineageState();

  /**
   * transaction manager used in the query.
   */
  private HiveTxnManager txnManager;

  /**
   * Private constructor, use QueryState.Builder instead.
   * @param conf The query specific configuration object
   */
  private QueryState(HiveConf conf) {
    this.queryConf = conf;
  }

  public String getQueryId() {
    return (queryConf.getVar(HiveConf.ConfVars.HIVEQUERYID));
  }

  public String getQueryString() {
    return queryConf.getQueryString();
  }

  public String getCommandType() {
    if (commandType == null) {
      return null;
    }
    return commandType.getOperationName();
  }

  public HiveOperation getHiveOperation() {
    return commandType;
  }

  public void setCommandType(HiveOperation commandType) {
    this.commandType = commandType;
  }

  public HiveConf getConf() {
    return queryConf;
  }

  public LineageState getLineageState() {
    return lineageState;
  }

  public void setLineageState(LineageState lineageState) {
    this.lineageState = lineageState;
  }

  public HiveTxnManager getTxnManager() {
    return txnManager;
  }

  public void setTxnManager(HiveTxnManager txnManager) {
    this.txnManager = txnManager;
  }

  /**
   * Builder to instantiate the QueryState object.
   */
  public static class Builder {
    private Map<String, String> confOverlay = null;
    // HIVE-18238: remove before submitting
    @Deprecated
    private boolean runAsync = false;
    private boolean isolated = true;
    private boolean generateNewQueryId = false;
    private HiveConf hiveConf = null;
    private LineageState lineageState = null;

    /**
     * Default constructor - use this builder to create a QueryState object.
     */
    public Builder() {
    }

    /**
     * Set this to true if the configuration should be detached from the original config. If not
     * set the default value is false.
     * @param runAsync If the configuration should be detached
     * @return The builder
     */
    public Builder withRunAsync(boolean runAsync) {
      this.runAsync = runAsync;
      return this;
    }

    /**
     * Set this if there are specific configuration values which should be added to the original
     * config. If at least one value is set, then the configuration will be detached from the
     * original one.
     * @param confOverlay The query specific parameters
     * @return The builder
     */
    public Builder withConfOverlay(Map<String, String> confOverlay) {
      this.confOverlay = confOverlay;
      return this;
    }

    /**
     * Disable configuration isolation.
     *
     * For internal use / testing purposes only.
     */
    public Builder nonIsolated() {
      isolated = false;
      return this;
    }

    /**
     * Set this to true if new queryId should be generated, otherwise the original one will be kept.
     * If not set the default value is false.
     * @param generateNewQueryId If new queryId should be generated
     * @return The builder
     */
    public Builder withGenerateNewQueryId(boolean generateNewQueryId) {
      this.generateNewQueryId = generateNewQueryId;
      return this;
    }

    /**
     * The source HiveConf object used to create the QueryState. If runAsync is false, and the
     * confOverLay is empty then we will reuse the conf object as a backing datastore for the
     * QueryState. We will create a clone of the conf object otherwise.
     * @param hiveConf The source HiveConf
     * @return The builder
     */
    public Builder withHiveConf(HiveConf hiveConf) {
      this.hiveConf = hiveConf;
      return this;
    }

    /**
     * add a LineageState that will be set in the built QueryState
     * @param lineageState the source lineageState
     * @return the builder
     */
    public Builder withLineageState(LineageState lineageState) {
      this.lineageState = lineageState;
      return this;
    }

    private static final Logger LOG = LoggerFactory.getLogger(QueryState.class);

    public class HiveConf1 extends HiveConf {

      //      Set<String> protected1 = Sets.newHashSet("hive.txn.valid.txns");

      Set<String> protected1 = Sets.newHashSet("hive.txn.valid.txns", "fs.permissions.umask-mode", "fs.scheme.class",
          "hive.conf.restricted.list", "hive.exec.dynamic.partition.mode", "hive.execution.engine", "_hive.hdfs.session.path",
          "hive.internal.ss.authz.settings.applied.marker", "_hive.local.session.path", "hive.mapred.mode", "hive.metastore.filter.hook",
          "hive.metastore.rawstore.impl", "hive.security.authenticator.manager", "hive.security.authorization.createtable.owner.grants",
          "hive.session.id", "hive.test.init.phase", "hive.test.shutdown.phase", "_hive.tmp_table_space", "hive.zookeeper.client.port",
          "hive.zookeeper.quorum", "io.file.buffer.size", "mapreduce.job.name", "mapreduce.workflow.name", "test.data.dir",
          "hive.query.id", "hive.query.id",

          //          "hive.doing.acid",

          "mapreduce.workflow.adjacency.Stage-0", "mapreduce.workflow.id", "mapreduce.workflow.node.name"

      );

      private final HiveConf pc;

      public HiveConf1() {
        this(new HiveConf());
      }

      public HiveConf1(HiveConf hiveConf) {
        super(hiveConf);
        pc = hiveConf;
      }

      @Override
      public void set(String arg0, String arg1, String arg2) {
        LOG.info("set3|{}|{}|{}", arg0, arg1, arg2);
        super.set(arg0, arg1, arg2);
        if(!protected1.contains(arg0)) {
          pc.set(arg0, arg1, arg2);
          LOG.info("BYP3|{}|{}|{}", arg0, arg1, arg2);
        }
      }

      @Override
      public void set(String name, String value) {
        LOG.info("set2|{}|{}", name, value);
        super.set(name, value);
        if(!protected1.contains(name)) {
          pc.set(name, value);
          LOG.info("BYP2|{}|{}", name, value);
        }
      }

    }

    /**
     * Creates the QueryState object. The default values are:
     * - runAsync false
     * - confOverlay null
     * - generateNewQueryId false
     * - conf null
     * @return The generated QueryState object
     */
    public QueryState build() {
      HiveConf queryConf;

      if (isolated) {
        // isolate query conf
        if (hiveConf == null) {
          queryConf = new HiveConf();
        } else {
          queryConf = new HiveConf(hiveConf);
        }
      } else {
        queryConf = hiveConf;
      }

      // Set the specific parameters if needed
      if (confOverlay != null && !confOverlay.isEmpty()) {
        try {
          queryConf.verifyAndSetAll(confOverlay);
        } catch (IllegalArgumentException e) {
          throw new RuntimeException("Error applying statement specific settings", e);
        }
      }

      // Generate the new queryId if needed
      if (generateNewQueryId) {
        String queryId = QueryPlan.makeQueryId();
        queryConf.setVar(HiveConf.ConfVars.HIVEQUERYID, queryId);
        // FIXME: druid storage handler relies on query.id to maintain some staging directories
        // expose queryid to session level
        if (hiveConf != null) {
          hiveConf.setVar(HiveConf.ConfVars.HIVEQUERYID, queryId);
        }
      }

      QueryState queryState = new QueryState(queryConf);
      if (lineageState != null) {
        queryState.setLineageState(lineageState);
      }
      return queryState;
    }
  }
}
