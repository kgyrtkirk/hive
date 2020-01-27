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

package org.apache.hadoop.hive.ql.ddl.table.create.show;

import org.apache.hadoop.hive.ql.ddl.DDLOperationContext;
import org.apache.hadoop.hive.ql.ddl.DDLUtils;
import org.apache.hadoop.hive.ql.ddl.table.create.CreateTableOperation;

import static org.apache.hadoop.hive.metastore.api.hive_metastoreConstants.META_TABLE_STORAGE;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.ql.ddl.DDLOperation;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.util.DirectionUtils;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hive.common.util.HiveStringUtils;
import org.stringtemplate.v4.ST;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Operation process showing the creation of a table.
 */
public class ShowCreateTableOperation extends DDLOperation<ShowCreateTableDesc> {
  private static final String EXTERNAL = "external";
  private static final String TEMPORARY = "temporary";
  private static final String NAME = "name";
  private static final String LIST_COLUMNS = "columns";
  private static final String COMMENT = "comment";
  private static final String PARTITIONS = "partitions";
  private static final String BUCKETS = "buckets";
  private static final String SKEWED = "skewedinfo";
  private static final String ROW_FORMAT = "row_format";
  private static final String LOCATION_BLOCK = "location_block";
  private static final String LOCATION = "location";
  private static final String PROPERTIES = "properties";

  public ShowCreateTableOperation(DDLOperationContext context, ShowCreateTableDesc desc) {
    super(context, desc);
  }

  @Override
  public int execute() throws HiveException {
    // get the create table statement for the table and populate the output
    try (DataOutputStream outStream = DDLUtils.getOutputStream(new Path(desc.getResFile()), context)) {
      Table table = context.getDb().getTable(desc.getTableName(), false);
      String command = table.isView() ?
          getCreateViewCommand(table) :
          getCreateTableCommand(table);
      outStream.write(command.getBytes(StandardCharsets.UTF_8));
      return 0;
    } catch (IOException e) {
      LOG.info("show create table: ", e);
      return 1;
    } catch (Exception e) {
      throw new HiveException(e);
    }
  }

  private static final String CREATE_VIEW_COMMAND = "CREATE VIEW `%s` AS %s";

  private String getCreateViewCommand(Table table) {
    return String.format(CREATE_VIEW_COMMAND, desc.getTableName(), table.getViewExpandedText());
  }

  private static final String CREATE_TABLE_TEMPLATE =
      "CREATE <" + TEMPORARY + "><" + EXTERNAL + ">TABLE `<" + NAME + ">`(\n" +
      "<" + LIST_COLUMNS + ">)\n" +
      "<" + COMMENT + ">\n" +
      "<" + PARTITIONS + ">\n" +
      "<" + BUCKETS + ">\n" +
      "<" + SKEWED + ">\n" +
      "<" + ROW_FORMAT + ">\n" +
      "<" + LOCATION_BLOCK + ">" +
      "TBLPROPERTIES (\n" +
      "<" + PROPERTIES + ">)\n";

  private String getCreateTableCommand(Table table) {
    ST command = new ST(CREATE_TABLE_TEMPLATE);

    command.add(NAME, desc.getTableName());
    command.add(TEMPORARY, getTemporary(table));
    command.add(EXTERNAL, getExternal(table));
    command.add(LIST_COLUMNS, getColumns(table));
    command.add(COMMENT, getComment(table));
    command.add(PARTITIONS, getPartitions(table));
    command.add(BUCKETS, getBuckets(table));
    command.add(SKEWED, getSkewed(table));
    command.add(ROW_FORMAT, getRowFormat(table));
    command.add(LOCATION_BLOCK, getLocationBlock(table));
    command.add(PROPERTIES, getProperties(table));

    return command.render();
  }

  private String getTemporary(Table table) {
    return table.isTemporary() ? "TEMPORARY " : "";
  }

  private String getExternal(Table table) {
    return table.getTableType() == TableType.EXTERNAL_TABLE ? "EXTERNAL " : "";
  }

  private String getColumns(Table table) {
    List<String> columnDescs = new ArrayList<String>();
    for (FieldSchema col : table.getCols()) {
      String columnDesc = "  `" + col.getName() + "` " + col.getType();
      if (col.getComment() != null) {
        columnDesc += " COMMENT '" + HiveStringUtils.escapeHiveCommand(col.getComment()) + "'";
      }
      columnDescs.add(columnDesc);
    }
    return StringUtils.join(columnDescs, ", \n");
  }

  private String getComment(Table table) {
    String comment = table.getProperty("comment");
    return (comment != null) ? "COMMENT '" + HiveStringUtils.escapeHiveCommand(comment) + "'" : "";
  }

  private String getPartitions(Table table) {
    List<FieldSchema> partitionKeys = table.getPartitionKeys();
    if (partitionKeys.isEmpty()) {
      return "";
    }

    List<String> partitionDescs = new ArrayList<String>();
    for (FieldSchema partitionKey : partitionKeys) {
      String partitionDesc = "  `" + partitionKey.getName() + "` " + partitionKey.getType();
      if (partitionKey.getComment() != null) {
        partitionDesc += " COMMENT '" + HiveStringUtils.escapeHiveCommand(partitionKey.getComment()) + "'";
      }
      partitionDescs.add(partitionDesc);
    }
    return "PARTITIONED BY ( \n" + StringUtils.join(partitionDescs, ", \n") + ")";
  }

  private String getBuckets(Table table) {
    List<String> bucketCols = table.getBucketCols();
    if (bucketCols.isEmpty()) {
      return "";
    }

    String buckets = "CLUSTERED BY ( \n  " + StringUtils.join(bucketCols, ", \n  ") + ") \n";

    List<Order> sortColumns = table.getSortCols();
    if (!sortColumns.isEmpty()) {
      List<String> sortKeys = new ArrayList<String>();
      for (Order sortColumn : sortColumns) {
        String sortKeyDesc = "  " + sortColumn.getCol() + " " + DirectionUtils.codeToText(sortColumn.getOrder());
        sortKeys.add(sortKeyDesc);
      }
      buckets += "SORTED BY ( \n" + StringUtils.join(sortKeys, ", \n") + ") \n";
    }

    buckets += "INTO " + table.getNumBuckets() + " BUCKETS";
    return buckets;
  }

  private String getSkewed(Table table) {
    SkewedInfo skewedInfo = table.getSkewedInfo();
    if (skewedInfo == null || skewedInfo.getSkewedColNames().isEmpty()) {
      return "";
    }

    List<String> columnValuesList = new ArrayList<String>();
    for (List<String> columnValues : skewedInfo.getSkewedColValues()) {
      columnValuesList.add("('" + StringUtils.join(columnValues, "','") + "')");
    }

    String skewed =
        "SKEWED BY (" + StringUtils.join(skewedInfo.getSkewedColNames(), ",") + ")\n" +
        "  ON (" + StringUtils.join(columnValuesList, ",") + ")";
    if (table.isStoredAsSubDirectories()) {
      skewed += "\n  STORED AS DIRECTORIES";
    }
    return skewed;
  }

  private String getRowFormat(Table table) {
    StringBuilder rowFormat = new StringBuilder();

    StorageDescriptor sd = table.getTTable().getSd();
    SerDeInfo serdeInfo = sd.getSerdeInfo();

    rowFormat
      .append("ROW FORMAT SERDE \n")
      .append("  '" + HiveStringUtils.escapeHiveCommand(serdeInfo.getSerializationLib()) + "' \n");

    Map<String, String> serdeParams = serdeInfo.getParameters();
    if (table.getStorageHandler() == null) {
      // If serialization.format property has the default value, it will not to be included in SERDE properties
      if (Warehouse.DEFAULT_SERIALIZATION_FORMAT.equals(serdeParams.get(serdeConstants.SERIALIZATION_FORMAT))) {
        serdeParams.remove(serdeConstants.SERIALIZATION_FORMAT);
      }
      if (!serdeParams.isEmpty()) {
        appendSerdeParams(rowFormat, serdeParams);
        rowFormat.append(" \n");
      }
      rowFormat
        .append("STORED AS INPUTFORMAT \n  '" + HiveStringUtils.escapeHiveCommand(sd.getInputFormat()) + "' \n")
        .append("OUTPUTFORMAT \n  '" + HiveStringUtils.escapeHiveCommand(sd.getOutputFormat()) + "'");
    } else {
      String metaTableStorage = table.getParameters().get(META_TABLE_STORAGE);
      rowFormat.append("STORED BY \n  '" + HiveStringUtils.escapeHiveCommand(metaTableStorage) + "' \n");
      if (!serdeParams.isEmpty()) {
        appendSerdeParams(rowFormat, serdeInfo.getParameters());
      }
    }

    return rowFormat.toString();
  }

  public static void appendSerdeParams(StringBuilder builder, Map<String, String> serdeParams) {
    SortedMap<String, String> sortedSerdeParams = new TreeMap<String, String>(serdeParams);
    List<String> serdeCols = new ArrayList<String>();
    for (Entry<String, String> entry : sortedSerdeParams.entrySet()) {
      serdeCols.add("  '" + entry.getKey() + "'='" + HiveStringUtils.escapeHiveCommand(entry.getValue()) + "'");
    }

    builder
      .append("WITH SERDEPROPERTIES ( \n")
      .append(StringUtils.join(serdeCols, ", \n"))
      .append(')');
  }

  private static final String CREATE_TABLE_TEMPLATE_LOCATION =
      "LOCATION\n" +
      "<" + LOCATION + ">\n";

  private String getLocationBlock(Table table) {
    if (!CreateTableOperation.doesTableNeedLocation(table)) {
      return "";
    }

    ST locationBlock = new ST(CREATE_TABLE_TEMPLATE_LOCATION);
    StorageDescriptor sd = table.getTTable().getSd();
    locationBlock.add(LOCATION, "  '" + HiveStringUtils.escapeHiveCommand(sd.getLocation()) + "'");
    return locationBlock.render();
  }

  private static final Set<String> PROPERTIES_TO_IGNORE_AT_TBLPROPERTIES = Sets.union(
      ImmutableSet.<String>of("TEMPORARY", "EXTERNAL", "comment", "SORTBUCKETCOLSPREFIX", META_TABLE_STORAGE),
      new HashSet<String>(StatsSetupConst.TABLE_PARAMS_STATS_KEYS));

  private String getProperties(Table table) {
    return DDLUtils.propertiesToString(table.getParameters(), PROPERTIES_TO_IGNORE_AT_TBLPROPERTIES);
  }
}
