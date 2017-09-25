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

package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;

import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.TableSpec;
import org.apache.hadoop.hive.ql.parse.PrunedPartitionList;



/**
 * Client-side stats aggregator task.
 */
public class BasicStatsNoJobWork implements Serializable {
  private static final long serialVersionUID = 1L;

  private TableSpec tableSpecs;
  private boolean statsReliable;
  private PrunedPartitionList prunedPartitionList;

  public BasicStatsNoJobWork() {
  }

  public BasicStatsNoJobWork(TableSpec tableSpecs) {
    this.tableSpecs = tableSpecs;
  }

  // this constructor is never even called?
  @Deprecated
  public BasicStatsNoJobWork(boolean statsReliable) {
    this.statsReliable = statsReliable;
  }

  public TableSpec getTableSpecs() {
    return tableSpecs;
  }

  public boolean isStatsReliable() {
    return statsReliable;
  }

  public void setStatsReliable(boolean statsReliable) {
    this.statsReliable = statsReliable;
  }

  public void setPrunedPartitionList(PrunedPartitionList prunedPartitionList) {
    this.prunedPartitionList = prunedPartitionList;
  }

  public PrunedPartitionList getPrunedPartitionList() {
    return prunedPartitionList;
  }

}
