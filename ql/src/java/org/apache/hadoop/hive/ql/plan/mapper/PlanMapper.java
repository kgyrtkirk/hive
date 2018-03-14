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
import java.util.HashMap;
import java.util.HashSet;
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
    Object k1 = getKeyFor(o1);
    Object k2 = getKeyFor(o2);

    LinkGroup g1 = objectMap.get(k1);
    LinkGroup g2 = objectMap.get(k2);
    if (g1 != null && g2 != null && g1 != g2) {
      throw new RuntimeException("equivalence mapping violation");
    }
    LinkGroup targetGroup = (g1 != null) ? g1 : (g2 != null ? g2 : new LinkGroup());
    groups.add(targetGroup);
    targetGroup.add(o1);
    targetGroup.add(o2);
    targetGroup.add(k1);
    targetGroup.add(k2);
  }

  private Object getKeyFor(Object o) {
    if (o instanceof Operator) {
      Operator operator = (Operator) o;
      return getSignatureOf1(operator);
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

  private OpTreeSignatureFactory signatureCache = OpTreeSignatureFactory.newCache();

  //  public OpTreeSignature getSignatureOf(Operator<?> op) {
  //    LinkGroup g = objectMap.get(op);
  //    if (g == null) {
  //      g = new LinkGroup();
  //      g.add(op);
  //      groups.add(g);
  //    }
  //    OpTreeSignature sig = signatureCache.getSignature(op);
  //    g.add(sig);
  //    return sig;
  //  }

  public OpTreeSignature getSignatureOf1(Operator<?> op) {
    return signatureCache.getSignature(op);
  }

  public OpTreeSignature getSignatureOf2(Operator<?> op) {

    OpTreeSignature sig = signatureCache.getSignature(op);
    LinkGroup g = objectMap.get(sig);
    if (g == null) {
      g = new LinkGroup();
      g.add(sig);
      groups.add(g);
    }
    g.add(op);
    return sig;
  }

}
