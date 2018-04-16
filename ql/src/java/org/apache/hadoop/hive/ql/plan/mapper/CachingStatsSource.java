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

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.optimizer.signature.OpSignature;
import org.apache.hadoop.hive.ql.optimizer.signature.OpTreeSignature;
import org.apache.hadoop.hive.ql.stats.OperatorStats;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CachingStatsSource implements StatsSource {


  private final Cache<OpTreeSignature, OperatorStats> cache;

  // FIXME: consider not requesting hiveconf
  public CachingStatsSource(HiveConf conf) {
    int size = conf.getIntVar(ConfVars.HIVE_QUERY_REEXECUTION_STATS_CACHE_SIZE);
    cache = CacheBuilder.newBuilder().maximumSize(size).build();
  }

  public void put(OpTreeSignature sig, OperatorStats opStat) {
    cache.put(sig, opStat);
  }

  @Override
  public Optional<OperatorStats> lookup(OpTreeSignature treeSig) {

    if (cache.size() > 0 && cache.getIfPresent(treeSig) == null) {
      for (OpTreeSignature s : cache.asMap().keySet()) {
        if (treeSig.hashCode() == s.hashCode()) {

          OpTreeSignature oo = s;
          OpTreeSignature t0 = treeSig;
          OpTreeSignature t1 = oo;
          OpSignature s0 = treeSig.getSig();
          OpSignature s1 = oo.getSig();
          boolean eq = s0.equals(s1);
          boolean eq1 = t0.equals(t1);
          boolean eq2 = treeSig.toString().equals(oo.toString());
          for (int i = 0; i < t0.getParentSig().size(); i++) {
            OpTreeSignature p0 = t0.getParentSig().get(i);
            OpTreeSignature p1 = t1.getParentSig().get(i);
            boolean eqX = p0.equals(p1);
            if (!eqX) {
              int asd1 = 1;
            }
          }

          t0.getSig().proveEquals(t1.getSig());
          boolean eq3 = t0.getSig().equals(t1.getSig());
          int asd = 2;
        }
      }
    }

    return Optional.ofNullable(cache.getIfPresent(treeSig));
  }

  @Override
  public boolean canProvideStatsFor(Class<?> clazz) {
    if (Operator.class.isAssignableFrom(clazz)) {
      return true;
    }
    return false;
  }

  @Override
  public void putAll(Map<OpTreeSignature, OperatorStats> map) {
    for (Entry<OpTreeSignature, OperatorStats> entry : map.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

}
