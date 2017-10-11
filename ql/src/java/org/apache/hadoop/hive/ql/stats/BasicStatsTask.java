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


package org.apache.hadoop.hive.ql.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.MetaStoreUtils;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.CompilationOpContext;
import org.apache.hadoop.hive.ql.ErrorMsg;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.TableSpec;
import org.apache.hadoop.hive.ql.plan.BasicStatsWork;
import org.apache.hadoop.hive.ql.plan.DynamicPartitionCtx;
import org.apache.hadoop.hive.ql.plan.LoadTableDesc;
import org.apache.hadoop.hive.ql.plan.api.StageType;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * StatsTask implementation. StatsTask mainly deals with "collectable" stats. These are
 * stats that require data scanning and are collected during query execution (unless the user
 * explicitly requests data scanning just for the purpose of stats computation using the "ANALYZE"
 * command. All other stats are computed directly by the MetaStore. The rationale being that the
 * MetaStore layer covers all Thrift calls and provides better guarantees about the accuracy of
 * those stats.
 **/
public class BasicStatsTask implements Serializable, IStatsProcessor {

  private static final long serialVersionUID = 1L;
  private static transient final Logger LOG = LoggerFactory.getLogger(BasicStatsTask.class);

  private Table table;
  private Collection<Partition> dpPartSpecs;
  public boolean followedColStats;
  private BasicStatsWork work;
  private HiveConf conf;

  protected transient LogHelper console;

  public BasicStatsTask(HiveConf conf, BasicStatsWork work) {
    super();
    dpPartSpecs = null;
    this.conf = conf;
    console = new LogHelper(LOG);
    this.work = work;
  }

  @Override
  public int process(Hive db, Table tbl) throws Exception {

    LOG.info("Executing stats task");
    table = tbl;
    return aggregateStats(db);
  }

  @Override
  public void initialize(CompilationOpContext opContext) {
  }

  public StageType getType() {
    return StageType.STATS;
  }

  public String getName() {
    return "STATS";
  }

  private static class BasicStatsProcessor {

    private Partish partish;
    private FileStatus[] partfileStatus;
    private BasicStatsWork work;
    private boolean atomic;
    private boolean followedColStats1;

    public BasicStatsProcessor(Partish partish, BasicStatsWork work, HiveConf conf, boolean followedColStats2) {
      this.partish = partish;
      this.work = work;
      atomic = HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVE_STATS_ATOMIC);
      followedColStats1 = followedColStats2;
    }

    public Object process(StatsAggregator statsAggregator) throws HiveException, MetaException {
      Partish p = partish;
      Map<String, String> parameters = p.getPartParameters();
      if (p.isAcid()) {
        StatsSetupConst.setBasicStatsState(parameters, StatsSetupConst.FALSE);
      }

      if (work.isTargetRewritten()) {
        StatsSetupConst.setBasicStatsState(parameters, StatsSetupConst.TRUE);
      }

      // work.getTableSpecs() == null means it is not analyze command
      // and then if it is not followed by column stats, we should clean
      // column stats
      // FIXME: move this to ColStat related part
      if (!work.isExplicitAnalyze() && !followedColStats1) {
        StatsSetupConst.clearColumnStatsState(parameters);
      }
      // non-partitioned tables:
      // XXX: I don't aggree with this logic
      // FIXME: deprecate atomic? what's its purpose?
      if (!existStats(parameters) && atomic) {
        return null;
      }
      if(partfileStatus == null){
        LOG.warn("Partition/partfiles is null for: " + partish.getPartition().getSpec());
        return null;
      }

      // The collectable stats for the aggregator needs to be cleared.
      // For eg. if a file is being loaded, the old number of rows are not valid
      // XXX: makes no sense for me... possibly not needed anymore
      if (work.isClearAggregatorStats()) {
        // we choose to keep the invalid stats and only change the setting.
        // FIXME: invalidating numRows would be preferred here...instead keeping anything
        StatsSetupConst.setBasicStatsState(parameters, StatsSetupConst.FALSE);
        removeStatsRelatedParams(parameters, StatsSetupConst.supportedStats);
      }

      updateQuickStats(parameters, partfileStatus);
      if (StatsSetupConst.areBasicStatsUptoDate(parameters)) {
        if (statsAggregator != null) {
          String prefix = getAggregationPrefix(p.getTable(), p.getPartition());
          updateStats(statsAggregator, parameters, prefix, atomic);
        }
      } else {
        removeStatsRelatedParams(parameters, StatsSetupConst.statsRequireCompute);
      }

      return p.getOutput();
    }

    private void removeStatsRelatedParams(Map<String, String> parameters, String[] statsrequirecompute) {
      for (String string : statsrequirecompute) {
        parameters.remove(string);
      }

    }
    public void collectFileStatus(Warehouse wh) throws MetaException {
      Map<String, String> parameters = partish.getPartParameters();
      if (!existStats(parameters) && atomic) {
        return;
      }
      partfileStatus = wh.getFileStatusesForSD(partish.getPartSd());
    }

    @Deprecated
    private boolean existStats(Map<String, String> parameters) {
      return parameters.containsKey(StatsSetupConst.ROW_COUNT)
          || parameters.containsKey(StatsSetupConst.NUM_FILES)
          || parameters.containsKey(StatsSetupConst.TOTAL_SIZE)
          || parameters.containsKey(StatsSetupConst.RAW_DATA_SIZE)
          || parameters.containsKey(StatsSetupConst.NUM_PARTITIONS);
    }

    private void updateQuickStats(Map<String, String> parameters, FileStatus[] partfileStatus) throws MetaException {
      MetaStoreUtils.populateQuickStats(partfileStatus, parameters);
    }

    private String getAggregationPrefix(Table table, Partition partition) throws MetaException {
      String prefix = getAggregationPrefix0(table, partition);
      String aggKey = prefix.endsWith(Path.SEPARATOR) ? prefix : prefix + Path.SEPARATOR;
      return aggKey;
    }

    private String getAggregationPrefix0(Table table, Partition partition) throws MetaException {

      // prefix is of the form dbName.tblName
      String prefix = table.getDbName() + "." + org.apache.hadoop.hive.metastore.utils.MetaStoreUtils.encodeTableName(table.getTableName());
      // FIXME: this is a secret contract; reusein getAggrKey() creates a more closer relation to the StatsGatherer
      // prefix = work.getAggKey();
      prefix = prefix.toLowerCase();
      if (partition != null) {
        return Utilities.join(prefix, Warehouse.makePartPath(partition.getSpec()));
      }
      return prefix;
    }

    private void updateStats(StatsAggregator statsAggregator, Map<String, String> parameters, String aggKey, boolean atomic) throws HiveException {

      for (String statType : StatsSetupConst.statsRequireCompute) {
        String value = statsAggregator.aggregateStats(aggKey, statType);
        if (value != null && !value.isEmpty()) {
          long longValue = Long.parseLong(value);

          if (!work.isTargetRewritten()) {
            String originalValue = parameters.get(statType);
            if (originalValue != null) {
              longValue += Long.parseLong(originalValue); // todo: invalid + valid = invalid
            }
          }
          parameters.put(statType, String.valueOf(longValue));
        } else {
          if (atomic) {
            throw new HiveException(ErrorMsg.STATSAGGREGATOR_MISSED_SOMESTATS, statType);
          }
        }
      }
    }

  }


  private int aggregateStats(Hive db) {

    StatsAggregator statsAggregator = null;
    int ret = 0;
    StatsCollectionContext scc = null;
    EnvironmentContext environmentContext = null;
    environmentContext = new EnvironmentContext();
    environmentContext.putToProperties(StatsSetupConst.DO_NOT_UPDATE_STATS, StatsSetupConst.TRUE);

    try {
      // Stats setup:
      final Warehouse wh = new Warehouse(conf);
      if (!getWork().getNoStatsAggregator() && !getWork().isNoScanAnalyzeCommand()) {
        try {
          scc = getContext();
          statsAggregator = createStatsAggregator(scc, conf);
        } catch (HiveException e) {
          if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVE_STATS_RELIABLE)) {
            throw e;
          }
          console.printError(ErrorMsg.STATS_SKIPPING_BY_ERROR.getErrorCodedMsg(e.toString()));
        }
      }

      List<Partition> partitions = getPartitionsList(db);

      String tableFullName = table.getDbName() + "." + table.getTableName();

      List<Partish> partishes = new ArrayList<>();

      if (partitions == null) {
        Partish p;
        partishes.add(p = new Partish.PTable(table));

        BasicStatsProcessor basicStatsProcessor = new BasicStatsProcessor(p, work, conf, followedColStats);
        basicStatsProcessor.collectFileStatus(wh);
        Object res = basicStatsProcessor.process(statsAggregator);

        if (res == null) {
          return 0;
        }
        db.alterTable(tableFullName, (Table) res, environmentContext);

        if (conf.getBoolVar(ConfVars.TEZ_EXEC_SUMMARY)) {
          console.printInfo("Table " + tableFullName + " stats: [" + toString(p.getPartParameters()) + ']');
        }
        LOG.info("Table " + tableFullName + " stats: [" + toString(p.getPartParameters()) + ']');

      } else {
        // Partitioned table:
        // Need to get the old stats of the partition
        // and update the table stats based on the old and new stats.

        List<Partition> updates = new ArrayList<Partition>();

        final ExecutorService pool = buildBasicStatsExecutor();

        final List<Future<Void>> futures = Lists.newLinkedList();
        List<BasicStatsProcessor> processors = Lists.newLinkedList();

        try {
          for(final Partition partn : partitions) {
            Partish p;
            BasicStatsProcessor bsp = new BasicStatsProcessor(p = new Partish.PPart(table, partn), work, conf, followedColStats);
            processors.add(bsp);

            futures.add(pool.submit(new Callable<Void>() {
              @Override
              public Void call() throws Exception {
                bsp.collectFileStatus(wh);
                return null;
              }
            }));
          }
          pool.shutdown();
          for (Future<Void> future : futures) {
            future.get();
          }
        } catch (InterruptedException e) {
          LOG.debug("Cancelling " + futures.size() + " file stats lookup tasks");
          //cancel other futures
          for (Future future : futures) {
            future.cancel(true);
          }
          // Fail the query if the stats are supposed to be reliable
          if (work.isStatsReliable()) {
            ret = 1;
          }
        } finally {
          if (pool != null) {
            pool.shutdownNow();
          }
          LOG.debug("Finished getting file stats of all partitions!");
        }

        for (BasicStatsProcessor basicStatsProcessor : processors) {
          Object res = basicStatsProcessor.process(statsAggregator);
          if (res == null) {
            LOG.info("Partition " + basicStatsProcessor.partish.getPartition().getSpec() + " stats: [0]");
            continue;
          }
          updates.add((Partition) res);
          if (conf.getBoolVar(ConfVars.TEZ_EXEC_SUMMARY)) {
            console.printInfo("Partition " + basicStatsProcessor.partish.getPartition().getSpec() + " stats: [" + toString(basicStatsProcessor.partish.getPartParameters()) + ']');
          }
          LOG.info("Partition " + basicStatsProcessor.partish.getPartition().getSpec() + " stats: [" + toString(basicStatsProcessor.partish.getPartParameters()) + ']');
        }

        if (!updates.isEmpty()) {
          db.alterPartitions(tableFullName, updates, environmentContext);
        }
        if (work.isStatsReliable() && updates.size() != processors.size()) {
          LOG.info("Stats should be reliadble...however seems like there were some issue.. => ret 1");
          ret = 1;
        }
      }

    } catch (Exception e) {
      console.printInfo("[Warning] could not update stats.",
          "Failed with exception " + e.getMessage() + "\n"
              + StringUtils.stringifyException(e));

      // Fail the query if the stats are supposed to be reliable
      if (work.isStatsReliable()) {
        ret = 1;
      }
    } finally {
      if (statsAggregator != null) {
        statsAggregator.closeConnection(scc);
      }
    }
    // The return value of 0 indicates success,
    // anything else indicates failure
    return ret;
  }

  private BasicStatsWork getWork() {
    return work;
  }

  private ExecutorService buildBasicStatsExecutor() {
    //Get the file status up-front for all partitions. Beneficial in cases of blob storage systems
    int poolSize = conf.getInt(ConfVars.HIVE_MOVE_FILES_THREAD_COUNT.varname, 1);
    // In case thread count is set to 0, use single thread.
    poolSize = Math.max(poolSize, 1);
    final ExecutorService pool = Executors.newFixedThreadPool(poolSize, new ThreadFactoryBuilder().setDaemon(true).setNameFormat("stats-updater-thread-%d").build());
    LOG.debug("Getting file stats of all partitions. threadpool size:" + poolSize);
    return pool;
  }

  private StatsAggregator createStatsAggregator(StatsCollectionContext scc, HiveConf conf) throws HiveException {
    String statsImpl = HiveConf.getVar(conf, HiveConf.ConfVars.HIVESTATSDBCLASS);
    StatsFactory factory = StatsFactory.newFactory(statsImpl, conf);
    if (factory == null) {
      throw new HiveException(ErrorMsg.STATSPUBLISHER_NOT_OBTAINED.getErrorCodedMsg());
    }
    // initialize stats publishing table for noscan which has only stats task
    // the rest of MR task following stats task initializes it in ExecDriver.java
    StatsPublisher statsPublisher = factory.getStatsPublisher();
    if (!statsPublisher.init(scc)) { // creating stats table if not exists
      throw new HiveException(ErrorMsg.STATSPUBLISHER_INITIALIZATION_ERROR.getErrorCodedMsg());
    }

    // manufacture a StatsAggregator
    StatsAggregator statsAggregator = factory.getStatsAggregator();
    if (!statsAggregator.connect(scc)) {
      throw new HiveException(ErrorMsg.STATSAGGREGATOR_CONNECTION_ERROR.getErrorCodedMsg(statsImpl));
    }
    return statsAggregator;
  }

  private StatsCollectionContext getContext() throws HiveException {

    StatsCollectionContext scc = new StatsCollectionContext(conf);
    Task sourceTask = getWork().getSourceTask();
    if (sourceTask == null) {
      throw new HiveException(ErrorMsg.STATSAGGREGATOR_SOURCETASK_NULL.getErrorCodedMsg());
    }
    scc.setTask(sourceTask);
    scc.setStatsTmpDir(this.getWork().getStatsTmpDir());
    return scc;
  }


  private String toString(Map<String, String> parameters) {
    StringBuilder builder = new StringBuilder();
    for (String statType : StatsSetupConst.supportedStats) {
      String value = parameters.get(statType);
      if (value != null) {
        if (builder.length() > 0) {
          builder.append(", ");
        }
        builder.append(statType).append('=').append(value);
      }
    }
    return builder.toString();
  }

  /**
   * Get the list of partitions that need to update statistics.
   * TODO: we should reuse the Partitions generated at compile time
   * since getting the list of partitions is quite expensive.
   *
   * @return a list of partitions that need to update statistics.
   * @throws HiveException
   */
  private List<Partition> getPartitionsList(Hive db) throws HiveException {
    if (work.getLoadFileDesc() != null) {
      return null; //we are in CTAS, so we know there are no partitions
    }

    List<Partition> list = new ArrayList<Partition>();

    if (work.getTableSpecs() != null) {

      // ANALYZE command
      TableSpec tblSpec = work.getTableSpecs();
      table = tblSpec.tableHandle;
      if (!table.isPartitioned()) {
        return null;
      }
      // get all partitions that matches with the partition spec
      List<Partition> partitions = tblSpec.partitions;
      if (partitions != null) {
        for (Partition partn : partitions) {
          list.add(partn);
        }
      }
    } else if (work.getLoadTableDesc() != null) {

      // INSERT OVERWRITE command
      LoadTableDesc tbd = work.getLoadTableDesc();
      table = db.getTable(tbd.getTable().getTableName());
      if (!table.isPartitioned()) {
        return null;
      }
      DynamicPartitionCtx dpCtx = tbd.getDPCtx();
      if (dpCtx != null && dpCtx.getNumDPCols() > 0) { // dynamic partitions
        // If no dynamic partitions are generated, dpPartSpecs may not be initialized
        if (dpPartSpecs != null) {
          // load the list of DP partitions and return the list of partition specs
          list.addAll(dpPartSpecs);
        }
      } else { // static partition
        Partition partn = db.getPartition(table, tbd.getPartitionSpec(), false);
        list.add(partn);
      }
    }
    return list;
  }

  public Collection<Partition> getDpPartSpecs() {
    return dpPartSpecs;
  }

  @Override
  public void setDpPartSpecs(Collection<Partition> dpPartSpecs) {
    this.dpPartSpecs = dpPartSpecs;
  }

}
