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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

// FIXME: rename to EquivGroupMapper?
// FIXME: consider moving this to a different package
public class PlanMapper {

  Set<EquivGroup> groups = new HashSet<>();

  private Map<Object, EquivGroup> objectMap = new HashMap<>();

  // FIXME: find a better name
  @Deprecated
  public class EquivGroup {
    Set<Object> members = new HashSet<>();

    public void add(Object o) {
      members.add(o);
      objectMap.put(o, this);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> clazz) {
      List<T> ret = new ArrayList<>();
      for (Object m : members) {
        if (clazz.isInstance(m)) {
          ret.add((T) m);
        }
      }
      return ret;
    }
  }

  public void link(Object o1, Object o2) {
    EquivGroup g1 = objectMap.get(o1);
    EquivGroup g2 = objectMap.get(o2);
    if (g1 != null && g2 != null && g1 != g2) {
      throw new RuntimeException("equivalence mapping violation");
    }
    EquivGroup targetGroup = (g1 != null) ? g1 : (g2 != null ? g2 : new EquivGroup());
    groups.add(targetGroup);
    targetGroup.add(o1);
    targetGroup.add(o2);
  }

  public <T> List<T> getAll(Class<T> clazz) {
    List<T> ret = new ArrayList<>();
    for (EquivGroup g : groups) {
      ret.addAll(g.getAll(clazz));
    }
    return ret;
  }

  public void runMapper(GroupTransformer mapper) {
    for (EquivGroup equivGroup : groups) {
      mapper.map(equivGroup);
    }
  }

  public <T> List<T> lookupAll(Class<T> clazz, Object key) {
    EquivGroup group = objectMap.get(key);
    if (group == null) {
      throw new NoSuchElementException(Objects.toString(key));
    }
    return group.getAll(clazz);
  }

  public <T> T lookup(Class<T> clazz, Object key) {
    List<T> all = lookupAll(clazz, key);
    if (all.size() != 1) {
      throw new RuntimeException("Expected match count is 1; but got:" + all);
    }
    return all.get(0);
  }

}
