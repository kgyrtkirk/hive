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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hive.ql.exec.Operator;

import com.google.common.annotations.VisibleForTesting;

/**
 * Wraps the operator and provides hashcode on the basis of the
 *
 */
public class XSignature {

  private Map<String, Object> sigMap;
  // FIXME: this is currently retained...
  // but later the signature should be able to serve the same comparision granulaty level as op.logicalEquals right now
  private Operator<? extends OperatorDesc> op;

  private XSignature(Operator<? extends OperatorDesc> op) {
    this.op = op;
    sigMap = new HashMap<>();
    // FIXME: consider to operator info as well..not just conf?
    SignatureUtils1.write(sigMap, op.getConf());
  }

  public static XSignature of(Operator<? extends OperatorDesc> op) {
    return new XSignature(op);
  }

  @Override
  public int hashCode() {
    return sigMap.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof XSignature)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    XSignature o = (XSignature) obj;
    return op.logicalEquals(o.op);
  }

  public boolean signatureCompare(XSignature other) {
    return sigMap.equals(other.sigMap);
  }

  @VisibleForTesting
  public void proveEquals(XSignature other) {
    proveEquals(sigMap,other.sigMap);
  }

  private static void proveEquals(Map<String, Object> m1, Map<String, Object> m2) {
    for (Entry<String, Object> e : m1.entrySet()) {
      String key = e.getKey();
      Object v1 = e.getValue();
      Object v2 = m2.get(key);
      if (v1 == v2) {
        continue;
      }
      if (v1 == null || v2 == null || !v1.equals(v2)) {
        throw new RuntimeException(String.format("equals fails: %s (%s!=%s)", key, v1, v2));
      }
    }
  }

}
