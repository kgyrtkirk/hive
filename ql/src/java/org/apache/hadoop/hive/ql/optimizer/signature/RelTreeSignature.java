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

import java.util.ArrayList;

import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Operator tree signature.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public final class RelTreeSignature {

  //  @JsonProperty
  //  private int hashCode;
  @JsonProperty
  private String sig;
  @JsonProperty
  private ArrayList<RelTreeSignature> parentSig;

  // need this for Jackson to work
  @SuppressWarnings("unused")
  private RelTreeSignature() {
  }

  public RelTreeSignature(String string) {
    sig = string;
  }

  public static RelTreeSignature of(RelNode node) {
    return new RelTreeSignature(RelOptUtil.toString(node));
  }

  @Override
  public boolean equals(Object obj) {

    if (!(obj instanceof RelTreeSignature)) {
      return false;
    }
    RelTreeSignature other = (RelTreeSignature) obj;
    return sig.equals(other.sig);
  }
}
