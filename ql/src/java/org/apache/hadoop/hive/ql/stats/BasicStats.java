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

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicStats {

  private static final Logger LOG = LoggerFactory.getLogger(BasicStats.class.getName());

  public static interface IStatsEnhancer {
    void apply(BasicStats stats);
  }

  public static class RowNumEstimator implements IStatsEnhancer {

    private long avgRowSize;

    // FIXME: this is most probably broken ; the call-site is dependent on neededColumns; which indicates that it might mis calculate the value
    public RowNumEstimator(long avgRowSize) {
      this.avgRowSize = avgRowSize;
      if (avgRowSize > 0) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("Estimated average row size: " + avgRowSize);
        }
      }
    }

    @Override
    public void apply(BasicStats stats) {
      if (stats.getNumRows() < 0 && avgRowSize > 0) {
        stats.setNumRows(stats.getDataSize() / avgRowSize);
      }

    }

  }

  public static class DataSizeEstimator implements IStatsEnhancer {

    private HiveConf conf;

    public DataSizeEstimator(HiveConf conf) {
      this.conf = conf;
    }

    @Override
    public void apply(BasicStats stats) {
      long ds = stats.getRawDataSize();
      if (ds <= 0) {
        ds = stats.getTotalSize();

        // if data size is still 0 then get file size
        Path path = stats.partish.getPath();
        if (ds <= 0) {
          try {
            ds = getFileSizeForPath(path);
          } catch (IOException e) {
            ds = 0L;
          }
        }
        float deserFactor = HiveConf.getFloatVar(conf, HiveConf.ConfVars.HIVE_STATS_DESERIALIZATION_FACTOR);
        ds = (long) (ds * deserFactor);

        stats.setDataSize(ds);
      }

    }

    private long getFileSizeForPath(Path path) throws IOException {
      FileSystem fs = path.getFileSystem(conf);
      return fs.getContentSummary(path).getLength();
    }

  }

  private Partish partish;

  private long rowCount;
  private long totalSize;
  private long rawDataSize;

  private long currentNumRows;
  private long currentDataSize;

  public BasicStats(Partish p) {
    partish = p;

    rowCount = parseLong(StatsSetupConst.ROW_COUNT);
    rawDataSize = parseLong(StatsSetupConst.RAW_DATA_SIZE);
    totalSize = parseLong(StatsSetupConst.TOTAL_SIZE);

    currentNumRows = rowCount;
    currentDataSize = rawDataSize;
  }


  public long getNumRows() {
    return currentNumRows;
  }

  public long getDataSize() {
    return currentDataSize;
  }

  public void apply(IStatsEnhancer estimator) {
    estimator.apply(this);
  }

  protected void setNumRows(long l) {
    currentNumRows = l;
  }

  protected void setDataSize(long ds) {
    currentDataSize = ds;
  }

  protected long getTotalSize() {
    return totalSize;
  }

  protected long getRawDataSize() {
    return rawDataSize;
  }

  private long parseLong(String fieldName) {
    Map<String, String> params = partish.getPartParameters();
    long result = -1;

    if (params != null) {
      try {
        result = Long.parseLong(params.get(fieldName));
      } catch (NumberFormatException e) {
        result = -1;
      }
    }
    return result;
  }

}
