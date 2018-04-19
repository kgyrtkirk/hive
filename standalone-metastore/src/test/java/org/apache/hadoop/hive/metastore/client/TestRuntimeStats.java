package org.apache.hadoop.hive.metastore.client;

import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.annotation.MetastoreUnitTest;
import org.apache.hadoop.hive.metastore.api.RuntimeStat;
import org.apache.hadoop.hive.metastore.minihms.AbstractMetaStoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RunWith(Parameterized.class)
@Category(MetastoreUnitTest.class)
public class TestRuntimeStats extends MetaStoreClientTest {
  private static final Logger LOG = LoggerFactory.getLogger(TestRuntimeStats.class);
  private final AbstractMetaStoreService metaStore;
  private IMetaStoreClient client;

  public TestRuntimeStats(String name, AbstractMetaStoreService metaStore) throws Exception {
    this.metaStore = metaStore;
    this.metaStore.start();
  }

  @Before
  public void setUp() throws Exception {
    client = metaStore.getClient();

  }

  @After
  public void tearDown() throws Exception {
    client.close();
    client = null;
  }

  @Test
  public void testRuntimeStatHandling() throws Exception {
    int maxRetained = 100;
    List<RuntimeStat> rs0 = client.getRuntimeStats(-1, -1);
    assertNotNull(rs0);
    assertEquals(0, rs0.size());

    RuntimeStat stat = createStat(1);
    client.addRuntimeStat(stat);

    List<RuntimeStat> rs1 = client.getRuntimeStats(-1, -1);
    assertNotNull(rs1);
    assertEquals(1, rs1.size());
    assertEquals(stat, rs1.get(0));

    client.addRuntimeStat(createStat(2));
    client.addRuntimeStat(createStat(3));
    client.addRuntimeStat(createStat(4));

    List<RuntimeStat> rs2 = client.getRuntimeStats(-1, -1);
    assertEquals(4, rs2.size());

    //    // keep 1
    //    metaStore.start();
    //    client.addRuntimeStat(createStat(5));
    //    List<RuntimeStat> rs3 = client.getRuntimeStats(-1, -1);
    //    assertEquals(1, rs3.size());
    //    assertEquals(5, rs3.get(0).getWeight());
    //
    //    // keep 0
    //    client.addRuntimeStat(createStat(5));
    //    List<RuntimeStat> rs4 = client.getRuntimeStats(-1, -1);
    //    assertEquals(0, rs4.size());
    //
    //    // retention ignore
    //    client.addRuntimeStat(createStat(6));
    //    List<RuntimeStat> rs5 = client.getRuntimeStats(-1, -1);
    //    assertEquals(1, rs5.size());
    //
    //    // sleep 1s
    //    Thread.sleep(2000);
    //
    //    // retention ignore
    //    client.addRuntimeStat(createStat(6));
    //    List<RuntimeStat> rs6 = client.getRuntimeStats(-1, -1);
    //    assertEquals(1, rs6.size());

  }

  private RuntimeStat createStat(int w) {

    byte[] payload = new byte[w];
    for (int i = 0; i < payload.length; i++) {
      payload[i] = 'x';
    }

    RuntimeStat stat = new RuntimeStat();
    stat.setWeight(w);
    stat.setPayload(payload);
    return stat;
  }

}
