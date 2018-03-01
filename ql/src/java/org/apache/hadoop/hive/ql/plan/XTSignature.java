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

package org.apache.hadoop.hive.ql.plan;

import java.util.ArrayList;
import java.util.Objects;

import org.apache.hadoop.hive.ql.exec.Operator;

public class XTSignature {

  private Operator<?> op;
  private int hashCode;
  private XSignature sig;
  private ArrayList<XTSignature> parentSig;

  XTSignature(Operator<?> op) {
    this.op = op;
    sig = XSignature.of(op);
    parentSig = new ArrayList<>();
    for (Operator<? extends OperatorDesc> parentOp : op.getParentOperators()) {
      parentSig.add(XTSignature.of(parentOp));
    }
    hashCode = Objects.hash(sig, parentSig);
  }

  public static XTSignature of(Operator<?> root) {
    return new XTSignature(root);
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof XTSignature)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    XTSignature o = (XTSignature) obj;
    return logicalEqualsTree(op, o.op);
  }

  // XXX: this is ain't cheap! :)
  private final boolean logicalEqualsTree(Operator<?> o1, Operator<?> o) {
    if (!o1.logicalEquals(o)) {
      return false;
    }
    if (o.getNumParent() != o1.getNumParent()) {
      return false;
    }
    for (int i = 0; i < o1.getNumParent(); i++) {
      Operator<? extends OperatorDesc> copL = o1.getParentOperators().get(i);
      Operator<? extends OperatorDesc> copR = o.getParentOperators().get(i);
      if (!copL.logicalEquals(copR)) {
        return false;
      }
    }
    return true;
  }

}
