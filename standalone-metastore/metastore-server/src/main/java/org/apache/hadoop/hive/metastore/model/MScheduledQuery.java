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

package org.apache.hadoop.hive.metastore.model;

import org.apache.hadoop.hive.metastore.api.ScheduledQuery;
import org.apache.hadoop.hive.metastore.api.ScheduledQueryKey;

/**
 * Represents a runtime stat query entry.
 *
 * As a query may contain a large number of operatorstat entries; they are stored together in a single row in the metastore.
 * The number of operator stat entries this entity has; is shown in the weight column.
 */
public class MScheduledQuery {

  private String scheduleName;
  private boolean enabled;
  private String clusterNamespace;
  private String schedule;
  private String user;
  private String query;
  private Integer nextExecution;

  public MScheduledQuery(ScheduledQuery s) {
    scheduleName = s.getScheduleKey().getScheduleName();
    enabled = s.isEnabled();
    clusterNamespace = s.getScheduleKey().getClusterNamespace();
    schedule = s.getSchedule();
    user = s.getUser();
    query = s.getQuery();
    nextExecution = s.getNextExecution();
  }

  public static MScheduledQuery fromThrift(ScheduledQuery s) {
    return new MScheduledQuery(s);
  }

  public static ScheduledQuery toThrift(MScheduledQuery s) {
    ScheduledQuery ret = new ScheduledQuery();
    ret.setScheduleKey(new ScheduledQueryKey(s.scheduleName, s.clusterNamespace));
    ret.setEnabled(s.enabled);
    ret.setSchedule(s.schedule);
    ret.setUser(s.user);
    ret.setQuery(s.query);
    ret.setNextExecution(s.nextExecution);
    return ret;
  }

  public ScheduledQuery toThrift() {
    return toThrift(this);
  }

  public void doUpdate(MScheduledQuery schq) {
    // may not change scheduleName
    enabled = schq.enabled;
    clusterNamespace = schq.clusterNamespace;
    schedule = schq.schedule;
    user = schq.user;
    query = schq.query;
    // may not change nextExecution
  }

  public String getSchedule() {
    return schedule;
  }

  public Integer getNextExecution() {
    return nextExecution;
  }

  public void setNextExecution(Integer nextExec) {
    nextExecution = nextExec;
  }

  public String getQuery() {
    return query;
  }

  public String getScheduleName() {
    return scheduleName;
  }

  public ScheduledQueryKey getScheduleKey() {
    return new ScheduledQueryKey(scheduleName, clusterNamespace);
  }

}
