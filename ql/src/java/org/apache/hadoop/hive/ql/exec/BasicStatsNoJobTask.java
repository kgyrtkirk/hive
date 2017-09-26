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

package org.apache.hadoop.hive.ql.exec;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.HiveStatsUtils;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.ql.CompilationOpContext;
import org.apache.hadoop.hive.ql.DriverContext;
import org.apache.hadoop.hive.ql.QueryPlan;
import org.apache.hadoop.hive.ql.QueryState;
import org.apache.hadoop.hive.ql.io.StatsProvidingRecordReader;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.TableSpec;
import org.apache.hadoop.hive.ql.plan.BasicStatsNoJobWork;
import org.apache.hadoop.hive.ql.plan.api.StageType;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.common.util.ReflectionUtil;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;

/**
 * StatsNoJobTask is used in cases where stats collection is the only task for the given query (no
 * parent MR or Tez job). It is used in the following cases 1) ANALYZE with partialscan/noscan for
 * file formats that implement StatsProvidingRecordReader interface: ORC format (implements
 * StatsProvidingRecordReader) stores column statistics for all columns in the file footer. Its much
 * faster to compute the table/partition statistics by reading the footer than scanning all the
 * rows. This task can be used for computing basic stats like numFiles, numRows, fileSize,
 * rawDataSize from ORC footer.
 **/
public class BasicStatsNoJobTask extends Task<BasicStatsNoJobWork> implements Serializable {

  private static final long serialVersionUID = 1L;
  private static transient final Logger LOG = LoggerFactory.getLogger(BasicStatsNoJobTask.class);

  public BasicStatsNoJobTask() {
    super();
  }

  @Override
  public void initialize(QueryState queryState, QueryPlan queryPlan, DriverContext driverContext, CompilationOpContext opContext) {
    super.initialize(queryState, queryPlan, driverContext, opContext);
  }

  @Override
  public int execute(DriverContext driverContext) {


    LOG.info("Executing stats (no job) task");

    ExecutorService threadPool = StatsTask.newThreadPool(conf);
    Hive db = getHive();

    return aggregateStats(threadPool, db);
  }

  @Override
  public StageType getType() {
    return StageType.STATS;
  }

  @Override
  public String getName() {
    return "STATS-NO-JOB";
  }

  public static final Function<FooterStatCollector, String> SIMPLE_NAME_FUNCTION = new Function<FooterStatCollector, String>() {

    @Override
    public String apply(FooterStatCollector sc) {
      return String.format("%s#%s", sc.partish.getTable().getCompleteName(), sc.partish.getPartishType());
    }
  };
  private static final Function<FooterStatCollector, Partition> EXTRACT_RESULT_FUNCTION = new Function<FooterStatCollector, Partition>() {
    @Override
    public Partition apply(FooterStatCollector input) {
      return (Partition) input.result;
    }
  };

  static class FooterStatCollector implements Runnable {

    private Partish partish;
    private Object result;
    private JobConf jc;
    private Path dir;
    private FileSystem fs;
    private LogHelper console;

    public FooterStatCollector(JobConf jc, Partish partish) {
      this.jc = jc;
      this.partish = partish;
    }

    private boolean isValid() {
      return result != null;

    }

    public void init(HiveConf conf, LogHelper console) throws IOException {
      this.console = console;
      dir = new Path(partish.getPartSd().getLocation());
      fs = dir.getFileSystem(conf);
    }

    @Override
    public void run() {

      Map<String, String> parameters = partish.getPartParameters();
      try {
        long numRows = 0;
        long rawDataSize = 0;
        long fileSize = 0;
        long numFiles = 0;
        FileStatus[] fileList = HiveStatsUtils.getFileStatusRecurse(dir, -1, fs);

        boolean statsAvailable = false;
        for (FileStatus file : fileList) {
          if (!file.isDirectory()) {
            InputFormat<?, ?> inputFormat = ReflectionUtil.newInstance(partish.getInputFormatClass(), jc);
            InputSplit dummySplit = new FileSplit(file.getPath(), 0, 0, new String[] { partish.getLocation() });
            if (file.getLen() == 0) {
              numFiles += 1;
              statsAvailable = true;
            } else {
              org.apache.hadoop.mapred.RecordReader<?, ?> recordReader = inputFormat.getRecordReader(dummySplit, jc, Reporter.NULL);
              if (recordReader instanceof StatsProvidingRecordReader) {
                StatsProvidingRecordReader statsRR;
                statsRR = (StatsProvidingRecordReader) recordReader;
                rawDataSize += statsRR.getStats().getRawDataSize();
                numRows += statsRR.getStats().getRowCount();
                fileSize += file.getLen();
                numFiles += 1;
                statsAvailable = true;
              }
              // XXX: missing?
              //              else{
              //                statsAvailable=false;
              //              }
              recordReader.close();
            }
          }
        }

        if (statsAvailable) {

          StatsSetupConst.setBasicStatsState(parameters, StatsSetupConst.TRUE);

          parameters.put(StatsSetupConst.ROW_COUNT, String.valueOf(numRows));
          parameters.put(StatsSetupConst.RAW_DATA_SIZE, String.valueOf(rawDataSize));
          parameters.put(StatsSetupConst.TOTAL_SIZE, String.valueOf(fileSize));
          parameters.put(StatsSetupConst.NUM_FILES, String.valueOf(numFiles));

          if (partish.getPartition() != null) {
            result = new Partition(partish.getTable(), partish.getPartition().getTPartition());
          } else {
            result = new Table(partish.getTable().getTTable());
          }

          // printout console and debug logs
          String threadName = Thread.currentThread().getName();
          //          partish.getSimpleName();
          String msg = partish.getSimpleName() + " stats: [" + toString(parameters) + ']';
          LOG.debug(threadName + ": " + msg);
          console.printInfo(msg);
        } else {
          String threadName = Thread.currentThread().getName();
          String msg = partish.getSimpleName() + " does not provide stats.";
          LOG.debug(threadName + ": " + msg);
        }
      } catch (Exception e) {
        // XXX: possibly a bad idea?
        console.printInfo("[Warning] could not update stats for " + partish.getSimpleName() + ".", "Failed with exception " + e.getMessage() + "\n" + StringUtils.stringifyException(e));
      }
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

  }

  private int aggregateStats(ExecutorService threadPool, Hive db) {
    int ret = 0;
    try {
      JobConf jc = new JobConf(conf);

      TableSpec tableSpecs = work.getTableSpecs();

      if (tableSpecs == null) {
        throw new RuntimeException("this is unexpected...needs some investigation");
      }
      Table table = tableSpecs.tableHandle;

      Collection<Partition> partitions = null;
      if (work.getPartitions() == null) {
        if (table.isPartitioned()) {
          partitions = tableSpecs.partitions;
        }
      } else {
        partitions = work.getPartitions();
      }

      List<FooterStatCollector> scs = Lists.newArrayList();
      if (partitions == null) {
        scs.add(new FooterStatCollector(jc, Partish.buildFor(table)));
      } else {
        for (Partition part : partitions) {
          scs.add(new FooterStatCollector(jc, Partish.buildFor(table, part)));
        }
      }

      for (FooterStatCollector sc : scs) {
        sc.init(conf, console);
        threadPool.execute(sc);
      }

      LOG.debug("Stats collection waiting for threadpool to shutdown..");
      shutdownAndAwaitTermination(threadPool);
      LOG.debug("Stats collection threadpool shutdown successful.");

      ret = updatePartitions(db, scs, table);

    } catch (Exception e) {
      console.printError("Failed to collect footer statistics.", "Failed with exception " + e.getMessage() + "\n" + StringUtils.stringifyException(e));
      // Fail the query if the stats are supposed to be reliable
      if (work.isStatsReliable()) {
        ret = -1;
      }
    }

    // The return value of 0 indicates success,
    // anything else indicates failure
    return ret;
  }

  private int updatePartitions(Hive db, List<FooterStatCollector> scs, Table table) throws InvalidOperationException, HiveException {

    String tableFullName = table.getDbName() + "." + table.getTableName();

    if (scs.isEmpty()) {
      return 0;
    }
    if (work.isStatsReliable()) {
      for (FooterStatCollector statsCollection : scs) {
        if (statsCollection.result == null) {
          LOG.debug("Stats requested to be reliable. Empty stats found: " + statsCollection.partish.getSimpleName());
          return -1;
        }
      }
    }
    List<FooterStatCollector> validColectors = Lists.newArrayList();
    for (FooterStatCollector statsCollection : scs) {
      if (statsCollection.isValid()) {
        validColectors.add(statsCollection);
      }
    }

    EnvironmentContext environmentContext = new EnvironmentContext();
    environmentContext.putToProperties(StatsSetupConst.DO_NOT_UPDATE_STATS, StatsSetupConst.TRUE);

    ImmutableListMultimap<String, FooterStatCollector> collectorsByTable = Multimaps.index(validColectors, SIMPLE_NAME_FUNCTION);

    LOG.debug("Collectors.size(): {}", collectorsByTable.keySet());

    if (collectorsByTable.keySet().size() < 1) {
      LOG.warn("Collectors are empty! ; {}", tableFullName);
    }

    // for now this should be true...
    assert (collectorsByTable.keySet().size() <= 1);

    LOG.debug("Updating stats for: {}", tableFullName);

    for (String partName : collectorsByTable.keySet()) {
      ImmutableList<FooterStatCollector> values = collectorsByTable.get(partName);

      if (values == null) {
        throw new RuntimeException("very intresting");
      }

      if (values.get(0).result instanceof Table) {
        db.alterTable(tableFullName, (Table) values.get(0).result, environmentContext);
        LOG.debug("Updated stats for {}.", tableFullName);
      } else {
        if (values.get(0).result instanceof Partition) {
          List<Partition> results = Lists.transform(values, EXTRACT_RESULT_FUNCTION);

          db.alterPartitions(tableFullName, results, environmentContext);
          LOG.debug("Bulk updated {} partitions of {}.", results.size(), tableFullName);
        } else {
          throw new RuntimeException("inconsistent");
        }
      }
    }
    LOG.debug("Updated stats for: {}", tableFullName);
    return 0;
  }

  private void shutdownAndAwaitTermination(ExecutorService threadPool) {

    // Disable new tasks from being submitted
    threadPool.shutdown();
    try {

      // Wait a while for existing tasks to terminate
      // XXX this will wait forever... :)
      while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
        LOG.debug("Waiting for all stats tasks to finish...");
      }
      // Cancel currently executing tasks
      threadPool.shutdownNow();

      // Wait a while for tasks to respond to being cancelled
      if (!threadPool.awaitTermination(100, TimeUnit.SECONDS)) {
        LOG.debug("Stats collection thread pool did not terminate");
      }
    } catch (InterruptedException ie) {

      // Cancel again if current thread also interrupted
      threadPool.shutdownNow();

      // Preserve interrupt status
      Thread.currentThread().interrupt();
    }
  }

}
