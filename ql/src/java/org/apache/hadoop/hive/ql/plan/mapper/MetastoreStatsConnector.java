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

package org.apache.hadoop.hive.ql.plan.mapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.hadoop.hive.metastore.api.RuntimeStat;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.optimizer.signature.OpTreeSignature;
import org.apache.hadoop.hive.ql.optimizer.signature.RuntimeStatsMap;
import org.apache.hadoop.hive.ql.optimizer.signature.RuntimeStatsPersister;
import org.apache.hadoop.hive.ql.stats.OperatorStats;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decorates a StatSource to be loaded and persisted in the metastore as well.
 */
class MetastoreStatsConnector implements StatsSource {

  private static final Logger LOG = LoggerFactory.getLogger(MetastoreStatsConnector.class);

  private final StatsSource ss;
  int lastCreateTime = -1;

  private ExecutorService executor;

  private int batchSize;

  MetastoreStatsConnector(StatsSource ss, int msBatchSize) {
    this.ss = ss;
    this.batchSize = msBatchSize;
    executor = Executors.newSingleThreadExecutor(
        new BasicThreadFactory.Builder()
            .namingPattern("Metastore-RuntimeStats-Thread-%d")
            .daemon(true)
            .build());

    executor.submit(new Updater());
  }

  private class Updater implements Runnable {

    @Override
    public void run() {
      try {
        boolean shouldRun = false;
        do {
          List<RuntimeStat> rs = Hive.get().getMSC().getRuntimeStats(lastCreateTime, batchSize);
          for (RuntimeStat thriftStat : rs) {
            try {
              lastCreateTime = Math.max(thriftStat.getCreateTime() + 1, lastCreateTime);
              ss.putAll(decode(thriftStat));
            } catch (IOException e) {
              logException("Exception while loading runtime stats", e);
            }
          }
          shouldRun = batchSize > 0 && rs != null && rs.size() > 0;
        } while (shouldRun);
      } catch (TException | HiveException e) {
        logException("Exception while reading metastore runtime stats", e);
      }
    }
  }

  @Override
  public boolean canProvideStatsFor(Class<?> clazz) {
    return ss.canProvideStatsFor(clazz);
  }

  @Override
  public Optional<OperatorStats> lookup(OpTreeSignature treeSig) {
    return ss.lookup(treeSig);
  }

  @Override
  public void putAll(Map<OpTreeSignature, OperatorStats> map) {
    ss.putAll(map);
    try {
      RuntimeStat rec = encode(map);
      Hive.get().getMSC().addRuntimeStat(rec);
    } catch (TException | HiveException | IOException e) {
      logException("Exception while persisting runtime stat", e);
    }
  }

  private RuntimeStat encode(Map<OpTreeSignature, OperatorStats> map) throws IOException {
    String payload = RuntimeStatsPersister.INSTANCE.encode(new RuntimeStatsMap(map));
    RuntimeStat rs = new RuntimeStat();
    rs.setWeight(map.size());
    rs.setPayload(ByteBuffer.wrap(payload.getBytes()));
    return rs;
  }

  private Map<OpTreeSignature, OperatorStats> decode(RuntimeStat rs) throws IOException {
    RuntimeStatsMap rsm = RuntimeStatsPersister.INSTANCE.decode(rs.getPayload(), RuntimeStatsMap.class);
    return rsm.toMap();
  }

  public void destroy() {
    throw new RuntimeException();
  }

  static void logException(String msg, Exception e) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(msg, e);
    } else {
      LOG.info(msg + ": " + e.getMessage());
    }
  }

}