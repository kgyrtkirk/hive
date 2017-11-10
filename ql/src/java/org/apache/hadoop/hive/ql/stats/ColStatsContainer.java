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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Decimal;
import org.apache.hadoop.hive.ql.plan.ColStatistics;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.hadoop.hive.serde.serdeConstants;

public class ColStatsContainer {

  private List<ColStatistics> stats;

  public ColStatsContainer(List<ColumnStatisticsObj> colStats, String tabName) {
    stats = new ArrayList<>();
    List<ColStatistics> stats = new ArrayList<ColStatistics>(colStats.size());
    for (ColumnStatisticsObj statObj : colStats) {
      ColStatistics cs = getColStatistics(statObj, tabName, statObj.getColName());
      if (cs != null) {
        stats.add(cs);
      }
    }
  }

  // backward compat
  public List<ColStatistics> getValueList() {
    return stats;
  }

  /**
   * Convert ColumnStatisticsObj to ColStatistics
   * @param cso
   *          - ColumnStatisticsObj
   * @param tabName
   *          - table name
   * @param colName
   *          - column name
   * @return ColStatistics
   */
  public static ColStatistics getColStatistics(ColumnStatisticsObj cso, String tabName, String colName) {
    String colTypeLowerCase = cso.getColType().toLowerCase();
    ColStatistics cs = new ColStatistics(colName, colTypeLowerCase);
    ColumnStatisticsData csd = cso.getStatsData();
    if (colTypeLowerCase.equals(serdeConstants.TINYINT_TYPE_NAME) || colTypeLowerCase.equals(serdeConstants.SMALLINT_TYPE_NAME) || colTypeLowerCase.equals(serdeConstants.INT_TYPE_NAME)) {
      cs.setCountDistint(csd.getLongStats().getNumDVs());
      cs.setNumNulls(csd.getLongStats().getNumNulls());
      cs.setAvgColLen(JavaDataModel.get().primitive1());
      cs.setRange(csd.getLongStats().getLowValue(), csd.getLongStats().getHighValue());
    } else if (colTypeLowerCase.equals(serdeConstants.BIGINT_TYPE_NAME)) {
      cs.setCountDistint(csd.getLongStats().getNumDVs());
      cs.setNumNulls(csd.getLongStats().getNumNulls());
      cs.setAvgColLen(JavaDataModel.get().primitive2());
      cs.setRange(csd.getLongStats().getLowValue(), csd.getLongStats().getHighValue());
    } else if (colTypeLowerCase.equals(serdeConstants.FLOAT_TYPE_NAME)) {
      cs.setCountDistint(csd.getDoubleStats().getNumDVs());
      cs.setNumNulls(csd.getDoubleStats().getNumNulls());
      cs.setAvgColLen(JavaDataModel.get().primitive1());
      cs.setRange(csd.getDoubleStats().getLowValue(), csd.getDoubleStats().getHighValue());
    } else if (colTypeLowerCase.equals(serdeConstants.DOUBLE_TYPE_NAME)) {
      cs.setCountDistint(csd.getDoubleStats().getNumDVs());
      cs.setNumNulls(csd.getDoubleStats().getNumNulls());
      cs.setAvgColLen(JavaDataModel.get().primitive2());
      cs.setRange(csd.getDoubleStats().getLowValue(), csd.getDoubleStats().getHighValue());
    } else if (colTypeLowerCase.equals(serdeConstants.STRING_TYPE_NAME) || colTypeLowerCase.startsWith(serdeConstants.CHAR_TYPE_NAME)
        || colTypeLowerCase.startsWith(serdeConstants.VARCHAR_TYPE_NAME)) {
      cs.setCountDistint(csd.getStringStats().getNumDVs());
      cs.setNumNulls(csd.getStringStats().getNumNulls());
      cs.setAvgColLen(csd.getStringStats().getAvgColLen());
    } else if (colTypeLowerCase.equals(serdeConstants.BOOLEAN_TYPE_NAME)) {
      if (csd.getBooleanStats().getNumFalses() > 0 && csd.getBooleanStats().getNumTrues() > 0) {
        cs.setCountDistint(2);
      } else {
        cs.setCountDistint(1);
      }
      cs.setNumTrues(csd.getBooleanStats().getNumTrues());
      cs.setNumFalses(csd.getBooleanStats().getNumFalses());
      cs.setNumNulls(csd.getBooleanStats().getNumNulls());
      cs.setAvgColLen(JavaDataModel.get().primitive1());
    } else if (colTypeLowerCase.equals(serdeConstants.BINARY_TYPE_NAME)) {
      cs.setAvgColLen(csd.getBinaryStats().getAvgColLen());
      cs.setNumNulls(csd.getBinaryStats().getNumNulls());
    } else if (colTypeLowerCase.equals(serdeConstants.TIMESTAMP_TYPE_NAME) || colTypeLowerCase.equals(serdeConstants.TIMESTAMPLOCALTZ_TYPE_NAME)) {
      cs.setAvgColLen(JavaDataModel.get().lengthOfTimestamp());
    } else if (colTypeLowerCase.startsWith(serdeConstants.DECIMAL_TYPE_NAME)) {
      cs.setAvgColLen(JavaDataModel.get().lengthOfDecimal());
      cs.setCountDistint(csd.getDecimalStats().getNumDVs());
      cs.setNumNulls(csd.getDecimalStats().getNumNulls());
      Decimal highValue = csd.getDecimalStats().getHighValue();
      Decimal lowValue = csd.getDecimalStats().getLowValue();
      if (highValue != null && highValue.getUnscaled() != null && lowValue != null && lowValue.getUnscaled() != null) {
        HiveDecimal maxHiveDec = HiveDecimal.create(new BigInteger(highValue.getUnscaled()), highValue.getScale());
        BigDecimal maxVal = maxHiveDec == null ? null : maxHiveDec.bigDecimalValue();
        HiveDecimal minHiveDec = HiveDecimal.create(new BigInteger(lowValue.getUnscaled()), lowValue.getScale());
        BigDecimal minVal = minHiveDec == null ? null : minHiveDec.bigDecimalValue();

        if (minVal != null && maxVal != null) {
          cs.setRange(minVal, maxVal);
        }
      }
    } else if (colTypeLowerCase.equals(serdeConstants.DATE_TYPE_NAME)) {
      cs.setAvgColLen(JavaDataModel.get().lengthOfDate());
      cs.setNumNulls(csd.getDateStats().getNumNulls());
      Long lowVal = (csd.getDateStats().getLowValue() != null) ? csd.getDateStats().getLowValue().getDaysSinceEpoch() : null;
      Long highVal = (csd.getDateStats().getHighValue() != null) ? csd.getDateStats().getHighValue().getDaysSinceEpoch() : null;
      cs.setRange(lowVal, highVal);
    } else {
      // Columns statistics for complex datatypes are not supported yet
      return null;
    }

    return cs;
  }

}
