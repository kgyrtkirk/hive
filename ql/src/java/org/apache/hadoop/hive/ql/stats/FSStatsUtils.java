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

package org.apache.hadoop.hive.ql.stats;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.utils.FileUtils;
import org.apache.hadoop.hive.metastore.utils.MetaStoreUtils;

public class FSStatsUtils {

  /**
   * Collects the file statuses.
   *
   * @return array of FileStatus objects corresponding to the files
   * making up the passed storage description
   */
  @Deprecated
  public static FileStatus[] getFileStatusesForSD(Configuration conf, StorageDescriptor desc) throws MetaException {
    return getFileStatusesForSD1(conf, desc).toArray(new FileStatus[0]);
  }

  public static List<FileStatus> getFileStatusesForSD1(Configuration conf, StorageDescriptor desc)
      throws MetaException {
    try {
      Path path = new Path(desc.getLocation());
      FileSystem fileSys = path.getFileSystem(conf);
      return FileUtils.getFileStatusRecurse(path, -1, fileSys);
    } catch (IOException ioe) {
      MetaStoreUtils.logAndThrowMetaException(ioe);
    }
    return null;
  }

  public static void populateQuickStats(List<FileStatus> fileStatus, Map<String, String> params) {
    populateQuickStats(fileStatus.toArray(new FileStatus[0]), params);

  }

  @Deprecated
  public static void populateQuickStats(FileStatus[] fileStatus, Map<String, String> params) {
    int numFiles = 0;
    long tableSize = 0L;
    for (FileStatus status : fileStatus) {
      // don't take directories into account for quick stats
      if (!status.isDir()) {
        tableSize += status.getLen();
        numFiles += 1;
      }
    }
    params.put(StatsSetupConst.NUM_FILES, Integer.toString(numFiles));
    params.put(StatsSetupConst.TOTAL_SIZE, Long.toString(tableSize));
  }

  public static void clearQuickStats(Map<String, String> params) {
    params.remove(StatsSetupConst.NUM_FILES);
    params.remove(StatsSetupConst.TOTAL_SIZE);
  }

}
