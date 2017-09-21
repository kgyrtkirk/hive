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

package org.apache.hadoop.hive.ql.parse;

import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.DriverContext;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.io.orc.OrcInputFormat;
import org.apache.hadoop.hive.ql.io.rcfile.stats.PartialScanWork;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.optimizer.GenMapRedUtils;
import org.apache.hadoop.hive.ql.plan.StatsWork;
import org.apache.hadoop.hive.ql.plan.MapWork;
import org.apache.hadoop.hive.ql.plan.BasicStatsNoJobWork;
import org.apache.hadoop.hive.ql.plan.BasicStatsWork;
import org.apache.hadoop.hive.ql.plan.TezWork;
import org.apache.hadoop.mapred.InputFormat;

/**
 * ProcessAnalyzeTable sets up work for the several variants of analyze table
 * (normal, no scan, partial scan.) The plan at this point will be a single
 * table scan operator.
 */
public class ProcessAnalyzeTable implements NodeProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessAnalyzeTable.class.getName());

  // shared plan utils for tez
  private GenTezUtils utils = null;

  /**
   * Injecting the utils in the constructor facilitates testing
   */
  public ProcessAnalyzeTable(GenTezUtils utils) {
    this.utils = utils;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procContext,
      Object... nodeOutputs) throws SemanticException {

    GenTezProcContext context = (GenTezProcContext) procContext;

    TableScanOperator tableScan = (TableScanOperator) nd;

    ParseContext parseContext = context.parseContext;
    Class<? extends InputFormat> inputFormat = tableScan.getConf().getTableMetadata()
        .getInputFormatClass();

    if (parseContext.getQueryProperties().isAnalyzeCommand()) {

      assert tableScan.getChildOperators() == null || tableScan.getChildOperators().size() == 0;

      String alias = null;
      for (String a : parseContext.getTopOps().keySet()) {
        if (tableScan == parseContext.getTopOps().get(a)) {
          alias = a;
        }
      }

      assert alias != null;
      TezWork tezWork = context.currentTask.getWork();
      if (OrcInputFormat.class.isAssignableFrom(inputFormat) ||
          MapredParquetInputFormat.class.isAssignableFrom(inputFormat)) {
        // For ORC & Parquet, all the following statements are the same
        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS
        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS partialscan;
        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS noscan;

        // There will not be any Tez job above this task
        BasicStatsNoJobWork snjWork = new BasicStatsNoJobWork(tableScan.getConf().getTableMetadata()
            .getTableSpec());
        snjWork.setStatsReliable(parseContext.getConf().getBoolVar(
            HiveConf.ConfVars.HIVE_STATS_RELIABLE));
        // If partition is specified, get pruned partition list
        Set<Partition> confirmedParts = GenMapRedUtils.getConfirmedPartitionsForScan(tableScan);
        if (confirmedParts.size() > 0) {
          Table source = tableScan.getConf().getTableMetadata();
          List<String> partCols = GenMapRedUtils.getPartitionColumns(tableScan);
          PrunedPartitionList partList = new PrunedPartitionList(source, confirmedParts, partCols,
              false);
          snjWork.setPrunedPartitionList(partList);
        }
        Task<BasicStatsNoJobWork> snjTask = TaskFactory.get(snjWork, parseContext.getConf());
        snjTask.setParentTasks(null);
        context.rootTasks.remove(context.currentTask);
        context.rootTasks.add(snjTask);
        return true;
      } else {

        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS;
        // The plan consists of a simple TezTask followed by a StatsTask.
        // The Tez task is just a simple TableScanOperator

        BasicStatsWork basicStatsWork = new BasicStatsWork(tableScan.getConf().getTableMetadata().getTableSpec());
        basicStatsWork.setAggKey(tableScan.getConf().getStatsAggPrefix());
        basicStatsWork.setStatsTmpDir(tableScan.getConf().getTmpStatsDir());
        basicStatsWork.setNoScanAnalyzeCommand(parseContext.getQueryProperties().isNoScanAnalyzeCommand());
        basicStatsWork.setPartialScanAnalyzeCommand(parseContext.getQueryProperties().isPartialScanAnalyzeCommand());
        basicStatsWork.setStatsReliable(parseContext.getConf().getBoolVar(
            HiveConf.ConfVars.HIVE_STATS_RELIABLE));
        StatsWork columnStatsWork = new StatsWork(basicStatsWork);
        columnStatsWork.setSourceTask(context.currentTask);
        Task<StatsWork> statsTask = TaskFactory.get(columnStatsWork, parseContext.getConf());
        context.currentTask.addDependentTask(statsTask);

        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS noscan;
        // The plan consists of a StatsTask only.
        if (parseContext.getQueryProperties().isNoScanAnalyzeCommand()) {
          statsTask.setParentTasks(null);
          context.rootTasks.remove(context.currentTask);
          context.rootTasks.add(statsTask);
        }

        // ANALYZE TABLE T [PARTITION (...)] COMPUTE STATISTICS partialscan;
        if (parseContext.getQueryProperties().isPartialScanAnalyzeCommand()) {

          handlePartialScanCommand(tableScan, parseContext, columnStatsWork, context, statsTask);
        }

        // NOTE: here we should use the new partition predicate pushdown API to
        // get a list of pruned list,
        // and pass it to setTaskPlan as the last parameter
        Set<Partition> confirmedPartns = GenMapRedUtils.getConfirmedPartitionsForScan(tableScan);
        PrunedPartitionList partitions = null;
        if (confirmedPartns.size() > 0) {
          Table source = tableScan.getConf().getTableMetadata();
          List<String> partCols = GenMapRedUtils.getPartitionColumns(tableScan);
          partitions = new PrunedPartitionList(source, confirmedPartns, partCols, false);
        }

        MapWork w = utils.createMapWork(context, tableScan, tezWork, partitions);
        w.setGatheringStats(true);

        return true;
      }
    }

    return null;
  }

  /**
   * handle partial scan command.
   *
   * It is composed of PartialScanTask followed by StatsTask.
   */
  private void handlePartialScanCommand(TableScanOperator tableScan, ParseContext parseContext,
      StatsWork columnStatsWork, GenTezProcContext context, Task<StatsWork> statsTask)
      throws SemanticException {

    String aggregationKey = tableScan.getConf().getStatsAggPrefix();
    StringBuilder aggregationKeyBuffer = new StringBuilder(aggregationKey);
    List<Path> inputPaths = GenMapRedUtils.getInputPathsForPartialScan(tableScan,
        aggregationKeyBuffer);
    aggregationKey = aggregationKeyBuffer.toString();

    // scan work
    PartialScanWork scanWork = new PartialScanWork(inputPaths);
    scanWork.setMapperCannotSpanPartns(true);
    scanWork.setAggKey(aggregationKey);
    scanWork.setStatsTmpDir(tableScan.getConf().getTmpStatsDir(), parseContext.getConf());

    // partial scan task
    DriverContext driverCxt = new DriverContext();
    Task<PartialScanWork> partialScanTask = TaskFactory.get(scanWork, parseContext.getConf());
    partialScanTask.initialize(parseContext.getQueryState(), null, driverCxt,
        tableScan.getCompilationOpContext());
    partialScanTask.setWork(scanWork);
    columnStatsWork.setSourceTask(partialScanTask);

    // task dependency
    context.rootTasks.remove(context.currentTask);
    context.rootTasks.add(partialScanTask);
    partialScanTask.addDependentTask(statsTask);
  }
}
