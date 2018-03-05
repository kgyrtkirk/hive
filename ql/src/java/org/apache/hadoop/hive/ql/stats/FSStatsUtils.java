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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
  public static FileStatus[] getFileStatusesForSD(Configuration conf, StorageDescriptor desc) throws MetaException {
    try {
      Path path = new Path(desc.getLocation());
      FileSystem fileSys = path.getFileSystem(conf);
      return FileUtils.getFileStatusRecurse(path, -1, fileSys);
    } catch (IOException ioe) {
      MetaStoreUtils.logAndThrowMetaException(ioe);
    }
    return null;
  }

}
