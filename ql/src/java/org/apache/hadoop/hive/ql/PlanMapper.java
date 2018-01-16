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

package org.apache.hadoop.hive.ql;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// FIXME: rename to EquivGroupMapper?
public class PlanMapper {

  private Map<Object, EquivGroup> objectMap = new HashMap<>();

  class EquivGroup {
    Set<Object> members = new HashSet<>();

    public void add(Object o) {
      members.add(o);
      objectMap.put(o, this);
    }
  }

  public void link(Object o1, Object o2) {
    EquivGroup g1 = objectMap.get(o1);
    EquivGroup g2 = objectMap.get(o2);
    if (g1 != null && g2 != null && g1 != g2) {
      throw new RuntimeException("equivalence mapping violation");
    }
    EquivGroup targetGroup = (g1 != null) ? g1 : (g2 != null ? g2 : new EquivGroup());
    targetGroup.add(o1);
    targetGroup.add(o2);
  }

}
