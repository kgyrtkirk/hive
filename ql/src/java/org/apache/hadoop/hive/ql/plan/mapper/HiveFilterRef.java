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

package org.apache.hadoop.hive.ql.plan.mapper;

import java.util.List;
import java.util.Objects;

import org.apache.calcite.plan.RelOptUtil;
import org.apache.hadoop.hive.ql.optimizer.calcite.reloperators.HiveFilter;
import org.apache.hadoop.hive.ql.optimizer.calcite.reloperators.HiveTableScan;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper.EquivGroup;

public class HiveFilterRef {

  public static final GroupTransformer MAPPER = new HiveFilterMapper();

  // this is just a rough plan/operator indepentent ref
  private String myKey;

  HiveFilterRef(HiveFilter filter) {
    myKey = RelOptUtil.toString(filter);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(myKey);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final HiveFilterRef other = (HiveFilterRef) obj;
    return Objects.equals(myKey, other.myKey);
  }

  @Override
  public String toString() {
    return String.format("REF\n%s", myKey);
  }

  private static class HiveFilterMapper implements GroupTransformer {

    @Override
    public void map(EquivGroup group) {
      List<HiveFilter> filters = group.getAll(HiveFilter.class);
      if (filters.size() != 1) {
        return;
      }
      HiveFilter filter = filters.get(0);
      if (filter.getChildExps().size() == 1 && filter.getInput() instanceof HiveTableScan) {
        group.add(new HiveFilterRef(filter));
      }
    }
  }

  public static HiveFilterRef of(HiveFilter filter) {
    return new HiveFilterRef(filter);
  }
}