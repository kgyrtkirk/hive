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

package org.apache.hadoop.hive.ql.optimizer.signature;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.externalize.RelWriterImpl;
import org.apache.calcite.sql.SqlExplainLevel;
import org.apache.hadoop.hive.ql.optimizer.calcite.HiveRelOptUtil;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Operator tree signature.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public final class RelTreeSignature {

  @JsonProperty
  private int hashCode;
  @JsonProperty
  private String sig;
  @JsonProperty
  private ArrayList<RelTreeSignature> childSig;

  // need this for Jackson to work
  @SuppressWarnings("unused")
  private RelTreeSignature() {
  }

  public RelTreeSignature(RelNode node) {
    node.copy(node.getTraitSet(), new ArrayList<RelNode>());
    sig = node.getDigest();
    String s2 = HiveRelOptUtil.toString(node);
    childSig = new ArrayList<RelTreeSignature>();
    for (RelNode relNode : node.getInputs()) {
      childSig.add(RelTreeSignature.of(relNode));
    }
  }

  public static RelTreeSignature of(RelNode node) {
    return new RelTreeSignature(node);
  }

  @Override
  public boolean equals(Object obj) {

    if (!(obj instanceof RelTreeSignature)) {
      return false;
    }
    RelTreeSignature other = (RelTreeSignature) obj;
    return sig.equals(other.sig) && childSig.equals(other.childSig);
  }

  @Override
  public int hashCode() {
    return hashCode;
  }
  
  static class NonRecursiveRelWriterImpl extends RelWriterImpl{

    public NonRecursiveRelWriterImpl(PrintWriter pw, SqlExplainLevel detailLevel, boolean withIdPrefix) {
      super(pw, detailLevel, withIdPrefix);
    }
    
//    inputs
    
  }
}
