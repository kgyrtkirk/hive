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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.optimizer.signature.OpTreeSignature;
import org.apache.hadoop.hive.ql.optimizer.signature.OpTreeSignatureFactory;
import com.google.common.annotations.VisibleForTesting;

/**
 * Enables to connect related objects to eachother.
 *
 * Most importantly it aids to connect Operators to OperatorStats and probably RelNodes.
 */
public class PlanMapper {

  Set<LinkGroup> groups = new HashSet<>();
  private Map<Object, LinkGroup> objectMap = new HashMap<>();

  public class LinkGroup {
    Set<Object> members = new HashSet<>();

    public void add(Object o) {
      if (members.contains(o)) {
        return;
      }
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

    Set<Object> keySet = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
    keySet.add(o1);
    keySet.add(o2);
    keySet.add(getKeyFor(o1));
    keySet.add(getKeyFor(o2));

    Set<LinkGroup> mGroups = Collections.newSetFromMap(new IdentityHashMap<LinkGroup, Boolean>());

    for (Object object : keySet) {
      LinkGroup group = objectMap.get(object);
      if (group != null) {
        mGroups.add(group);
      }
    }
    if (mGroups.size() > 1) {
      throw new RuntimeException("equivalence mapping violation");
    }
    LinkGroup targetGroup = mGroups.isEmpty() ? new LinkGroup() : mGroups.iterator().next();
    groups.add(targetGroup);
    targetGroup.add(o1);
    targetGroup.add(o2);

  }

  private OpTreeSignatureFactory signatureCache = OpTreeSignatureFactory.newCache();

  private Object getKeyFor(Object o) {
    if (o instanceof Operator) {
      Operator operator = (Operator) o;
      return signatureCache.getSignature(operator);
    }
    return o;
  }

  public <T> List<T> getAll(Class<T> clazz) {
    List<T> ret = new ArrayList<>();
    for (LinkGroup g : groups) {
      ret.addAll(g.getAll(clazz));
    }
    return ret;
  }

  public void runMapper(GroupTransformer mapper) {
    for (LinkGroup equivGroup : groups) {
      mapper.map(equivGroup);
    }
  }

  public <T> List<T> lookupAll(Class<T> clazz, Object key) {
    LinkGroup group = objectMap.get(key);
    if (group == null) {
      throw new NoSuchElementException(Objects.toString(key));
    }
    return group.getAll(clazz);
  }

  public <T> T lookup(Class<T> clazz, Object key) {
    List<T> all = lookupAll(clazz, key);
    if (all.size() != 1) {
      // FIXME: use a different exception type?
      throw new IllegalArgumentException("Expected match count is 1; but got:" + all);
    }
    return all.get(0);
  }

  @VisibleForTesting
  public Iterator<LinkGroup> iterateGroups() {
    return groups.iterator();

  }

  public OpTreeSignature getSignatureOf(Operator<?> op) {
    OpTreeSignature sig = signatureCache.getSignature(op);
    /*    LinkGroup g = objectMap.get(sig);
    if (g == null) {
      g = new LinkGroup();
      g.add(sig);
      groups.add(g);
    }
    g.add(op);*/
    return sig;
  }

}
