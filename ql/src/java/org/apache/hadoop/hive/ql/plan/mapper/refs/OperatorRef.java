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

package org.apache.hadoop.hive.ql.plan.mapper.refs;

import java.util.List;
import java.util.Objects;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.plan.mapper.GroupTransformer;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper.LinkGroup;

public class OperatorRef {

  public static final GroupTransformer MAPPER = new OperatorMapper();

  // this should be enhanced to make it more safe to lookup by using this Ref
  private String myKey;

  OperatorRef(Operator<?> op) {
    myKey = op.getOperatorId();
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
    final OperatorRef other = (OperatorRef) obj;
    return Objects.equals(myKey, other.myKey);
  }

  @Override
  public String toString() {
    return String.format("OPREF\n%s", myKey);
  }

  private static class OperatorMapper implements GroupTransformer {

    @Override
    public void map(LinkGroup group) {
      List<Operator> filters = group.getAll(Operator.class);
      for (Operator op : filters) {
        group.add(new OperatorRef(op));
      }
    }
  }

  public static OperatorRef of(Operator<?> tsop) {
    return new OperatorRef(tsop);
  }

}
