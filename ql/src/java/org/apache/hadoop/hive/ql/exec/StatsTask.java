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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.SetPartitionsStatsRequest;
import org.apache.hadoop.hive.ql.CompilationOpContext;
import org.apache.hadoop.hive.ql.DriverContext;
import org.apache.hadoop.hive.ql.QueryPlan;
import org.apache.hadoop.hive.ql.QueryState;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.parse.ExplainConfiguration.AnalyzeState;
import org.apache.hadoop.hive.ql.plan.StatsWork;
import org.apache.hadoop.hive.ql.plan.api.StageType;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.stats.ColumnStatisticsObjTranslator;
import org.apache.hadoop.hive.serde2.objectinspector.InspectableObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StatsTask implementation.
 **/

public class StatsTask extends Task<StatsWork> implements Serializable {
  private static final long serialVersionUID = 1L;
  private FetchOperator ftOp;
  private static transient final Logger LOG = LoggerFactory.getLogger(StatsTask.class);

  public StatsTask() {
    super();
  }

  @Override
  public void initialize(QueryState queryState, QueryPlan queryPlan, DriverContext ctx,
      CompilationOpContext opContext) {
    super.initialize(queryState, queryPlan, ctx, opContext);
    if (work.getfWork() != null) {
      work.initializeForFetch(opContext);
      try {
        JobConf job = new JobConf(conf);
        ftOp = new FetchOperator(work.getfWork(), job);
      } catch (Exception e) {
        LOG.error(StringUtils.stringifyException(e));
        throw new RuntimeException(e);
      }
    }
  }

  private List<ColumnStatistics> constructColumnStatsFromPackedRows(Hive db) throws HiveException,
      MetaException, IOException {

    String[] names = Utilities.getDbTableName(SessionState.get().getCurrentDatabase(), work.getColStats().getTableName());
    Table tbl = db.getTable(names[0], names[1]);

    String partName = null;
    List<String> colName = work.getColStats().getColName();
    List<String> colType = work.getColStats().getColType();
    boolean isTblLevel = work.getColStats().isTblLevel();

    List<ColumnStatistics> stats = new ArrayList<ColumnStatistics>();
    InspectableObject packedRow;
    while ((packedRow = ftOp.getNextRow()) != null) {
      if (packedRow.oi.getCategory() != ObjectInspector.Category.STRUCT) {
        throw new HiveException("Unexpected object type encountered while unpacking row");
      }

      List<ColumnStatisticsObj> statsObjs = new ArrayList<ColumnStatisticsObj>();
      StructObjectInspector soi = (StructObjectInspector) packedRow.oi;
      List<? extends StructField> fields = soi.getAllStructFieldRefs();
      List<Object> list = soi.getStructFieldsDataAsList(packedRow.o);

      List<FieldSchema> partColSchema = tbl.getPartCols();
      // Partition columns are appended at end, we only care about stats column
      int numOfStatCols = isTblLevel ? fields.size() : fields.size() - partColSchema.size();
      assert list != null;
      for (int i = 0; i < numOfStatCols; i++) {
        StructField structField = fields.get(i);
         String columnName = colName.get(i);
        String columnType = colType.get(i);
        Object values = list.get(i);
        try{
          ColumnStatisticsObj statObj = ColumnStatisticsObjTranslator.readHiveStruct(columnName, columnType, structField, values);
          statsObjs.add(statObj);
        }catch(HiveException e){
          if(work.getBasicStatsWork().isStatsReliable()){
            throw new HiveException("Statistics collection failed while (hive.stats.reliable)",e);
          }else{
            LOG.debug("Because " + columnName + " is infinite or NaN, we skip stats.",e);
          }
        }
      }

      if (!isTblLevel) {
        List<String> partVals = new ArrayList<String>();
        // Iterate over partition columns to figure out partition name
        for (int i = fields.size() - partColSchema.size(); i < fields.size(); i++) {
          Object partVal = ((PrimitiveObjectInspector) fields.get(i).getFieldObjectInspector())
              .getPrimitiveJavaObject(list.get(i));
          partVals.add(partVal == null ? // could be null for default partition
          this.conf.getVar(ConfVars.DEFAULTPARTITIONNAME)
              : partVal.toString());
        }
        partName = Warehouse.makePartName(partColSchema, partVals);
      }
      ColumnStatisticsDesc statsDesc = buildColumnStatsDesc(tbl, partName, isTblLevel);
      ColumnStatistics colStats = new ColumnStatistics();
      colStats.setStatsDesc(statsDesc);
      colStats.setStatsObj(statsObjs);
      if (!colStats.getStatsObj().isEmpty()) {
        stats.add(colStats);
      }
    }
    ftOp.clearFetchContext();
    return stats;
  }

  private ColumnStatisticsDesc buildColumnStatsDesc(Table table, String partName, boolean isTblLevel) {
    return getColumnStatsDesc(table.getDbName(), table.getTableName(), partName, isTblLevel);
  }

  @Deprecated
  private ColumnStatisticsDesc getColumnStatsDesc(String dbName, String tableName, String partName,
      boolean isTblLevel) {
    assert dbName != null;
    ColumnStatisticsDesc statsDesc = new ColumnStatisticsDesc();
    statsDesc.setDbName(dbName);
    statsDesc.setTableName(tableName);
    statsDesc.setIsTblLevel(isTblLevel);

    if (!isTblLevel) {
      statsDesc.setPartName(partName);
    } else {
      statsDesc.setPartName(null);
    }
    return statsDesc;
  }

  private int persistColumnStats(Hive db) throws HiveException, MetaException, IOException {
    // Construct a column statistics object from the result
    List<ColumnStatistics> colStats = constructColumnStatsFromPackedRows(db);
    // Persist the column statistics object to the metastore
    // Note, this function is shared for both table and partition column stats.
    if (colStats.isEmpty()) {
      return 0;
    }
    SetPartitionsStatsRequest request = new SetPartitionsStatsRequest(colStats);
    request.setNeedMerge(work.getColStats().isNeedMerge());
    db.setPartitionColumnStatistics(request);
    return 0;
  }

  @Override
  public int execute(DriverContext driverContext) {
    if (driverContext.getCtx().getExplainAnalyze() == AnalyzeState.RUNNING) {
      return 0;
    }

    // TODO: merge BasicStatsWork and BasicStatsNoJobWork
    if (work.getBasicStatsWork() != null && work.getBasicStatsNoJobWork() != null) {
      LOG.error("Can not have both basic stats work and stats no job work!");
      return 1;
    }
    int ret = 0;
    if (work.getBasicStatsWork() != null) {
      work.getBasicStatsWork().setFollowedByColStats(work.getfWork() != null);
      BasicStatsTask task = (BasicStatsTask) TaskFactory.get(work.getBasicStatsWork(), conf);
      task.initialize(queryState, queryPlan, driverContext, null);
      task.setDpPartSpecs(dpPartSpecs);
      ret = task.execute(driverContext);
    }
    if (work.getBasicStatsNoJobWork() != null) {
      BasicStatsNoJobTask task = (BasicStatsNoJobTask) TaskFactory.get(work.getBasicStatsNoJobWork(), conf);
      task.initialize(queryState, queryPlan, driverContext, null);
      ret = task.execute(driverContext);
    }
    if (ret != 0) {
      return ret;
    }

    if (work.getfWork() != null) {
      try {
        Hive db = getHive();
        return persistColumnStats(db);
      } catch (Exception e) {
        LOG.error("Failed to run column stats task", e);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public StageType getType() {
    return StageType.COLUMNSTATS;
  }

  @Override
  public String getName() {
    return "COLUMNSTATS TASK";
  }

  private Collection<Partition> dpPartSpecs;

  @Override
  protected void receiveFeed(FeedType feedType, Object feedValue) {
    // this method should be called by MoveTask when there are dynamic
    // partitions generated
    if (feedType == FeedType.DYNAMIC_PARTITIONS) {
      dpPartSpecs = (Collection<Partition>) feedValue;
    }
  }
}
