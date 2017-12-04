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

package org.apache.hadoop.hive.ql.exec.vector.expressions;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;

import java.nio.charset.StandardCharsets;

/**
 * Type cast string to boolean
 */
public class CastStringToBoolean extends FuncStringToLong {
//  private final static byte[] TRUE = StandardCharsets.UTF_8.encode("TRUE").array();
  private final static byte[] FALSE = StandardCharsets.UTF_8.encode("FALSE").array();

  public CastStringToBoolean() {
    super();
  }

  public CastStringToBoolean(int inputColumn, int outputColumn) {
    super(inputColumn, outputColumn);
  }

  @Override
  protected void func(LongColumnVector outV, BytesColumnVector inV, int offset) {
    int start = inV.start[offset];
    int length = inV.length[offset];
    byte[] s = inV.vector[offset];
    if (length == FALSE.length) {
      for (int i = 0; i < FALSE.length; i++) {
        byte a = s[i + start];
        byte b = FALSE[i];
        byte c = 'a' - 'A';
        if ((a != b) && (a != (b + c))) {
          outV.vector[offset] = 1; // true
          return;
        }
      }
      outV.vector[offset] = 0; // false
      return;
    }
    outV.vector[offset] = length > 0 ? 1 : 0;
  }
}
