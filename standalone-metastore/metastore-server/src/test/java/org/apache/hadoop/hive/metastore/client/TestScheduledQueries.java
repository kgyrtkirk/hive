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
package org.apache.hadoop.hive.metastore.client;

import java.util.List;

import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.annotation.MetastoreUnitTest;
import org.apache.hadoop.hive.metastore.api.EventRequestType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.RuntimeStat;
import org.apache.hadoop.hive.metastore.api.Schedule;
import org.apache.hadoop.hive.metastore.api.ScheduledQuery;
import org.apache.hadoop.hive.metastore.api.ScheduledQueryMaintenanceRequest;
import org.apache.hadoop.hive.metastore.minihms.AbstractMetaStoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

//FIXME rename/etc?
@RunWith(Parameterized.class)
@Category(MetastoreUnitTest.class)
public class TestScheduledQueries extends MetaStoreClientTest {
  private final AbstractMetaStoreService metaStore;
  private IMetaStoreClient client;

  public TestScheduledQueries(String name, AbstractMetaStoreService metaStore) throws Exception {
    this.metaStore = metaStore;
  }

  @Before
  public void setUp() throws Exception {
    client = metaStore.getClient();

  }

  @After
  public void tearDown() throws Exception {
    try {
      client.close();
    } catch (Exception e) {
      // HIVE-19729: Shallow the exceptions based on the discussion in the Jira
    }
    client = null;
  }

  @Test(expected = NoSuchObjectException.class)
  public void testNonExistent() throws Exception {
    client.getScheduledQuery("nonExistent");
  }

  @Test
  public void testCreate() throws Exception {

    ScheduledQuery schq = new ScheduledQuery();
    schq.setScheduleName("sch1");
    schq.setClusterFuck("c1");
    schq.setEnabled(true);
    Schedule schedule = new Schedule();
    schedule.setCron("* * * * *");
    schq.setSchedule(schedule);
    schq.setUser("user");
    schq.setQuery("select 1");
    ScheduledQueryMaintenanceRequest r = new ScheduledQueryMaintenanceRequest();
    r.setType(EventRequestType.INSERT);
    r.setScheduledQuery(schq);
    client.scheduledQueryMaintenance(r);

    client.getScheduledQuery("sch1");
  }
}
