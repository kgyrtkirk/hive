/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.ql.plan;

import java.util.Map;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.hadoop.hive.ql.plan.OperatorStats;

/**
 *
 */
// TODO make this generic
public class RuntimeStatisticsCache {
  public static Cache<String, Map<String, OperatorStats>> RUNTIME_STATS_CACHE = CacheBuilder.newBuilder()
    .recordStats().maximumSize(1000).build();

  public String getCacheStatsString() {
    return RUNTIME_STATS_CACHE.stats().toString();
  }

  @Override
  public String toString() {
    return RUNTIME_STATS_CACHE.toString();
  }
}
