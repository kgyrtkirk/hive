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

import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hive.ql.optimizer.signature.OpTreeSignature;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper.EquivGroup;
import org.apache.hadoop.hive.ql.stats.OperatorStats;

public class StatsSources {

  @Deprecated
  public static StatsSource extracted(StatsSource currentStatsSource, PlanMapper pm) {
    if (currentStatsSource instanceof SessionStatsSource) {

      SessionStatsSource sessionStatsSource = (SessionStatsSource) currentStatsSource;
      loadFromPlanMapper(sessionStatsSource, pm);
      return sessionStatsSource;
    } else {
      return new SimpleRuntimeStatsSource(pm);
    }
  }

  public static void loadFromPlanMapper(SessionStatsSource sessionStatsSource, PlanMapper pm) {
    Iterator<EquivGroup> it = pm.iterateGroups();
    while (it.hasNext()) {
      EquivGroup e = it.next();
      List<OperatorStats> stat = e.getAll(OperatorStats.class);
      List<OpTreeSignature> sig = e.getAll(OpTreeSignature.class);

      if (stat.size() > 1 || sig.size() > 1) {
        throw new RuntimeException("unexpected?!");
      }
      if (stat.size() == 1 && sig.size() == 1) {
        sessionStatsSource.put(sig.get(0), stat.get(0));
      }
    }
  }

}
