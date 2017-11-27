/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.ql.plan;

/**
 *
 */
public class OperatorStats {
  private String operatorId;
  private long inputRecords;
  private long outputRecords;
  private long inputDataSize;
  private long outputDataSize;

  public OperatorStats(final String opId) {
    this.operatorId = opId;
    this.inputRecords = -1;
    this.inputDataSize = -1;
    this.outputRecords = -1;
    this.outputDataSize = -1;
  }

  public long getInputRecords() {
    return inputRecords;
  }

  public void setInputRecords(final long inputRecords) {
    this.inputRecords = inputRecords;
  }

  public long getOutputRecords() {
    return outputRecords;
  }

  public void setOutputRecords(final long outputRecords) {
    this.outputRecords = outputRecords;
  }

  public long getInputDataSize() {
    return inputDataSize;
  }

  public void setInputDataSize(final long inputDataSize) {
    this.inputDataSize = inputDataSize;
  }

  public long getOutputDataSize() {
    return outputDataSize;
  }

  public void setOutputDataSize(final long outputDataSize) {
    this.outputDataSize = outputDataSize;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(final String operatorId) {
    this.operatorId = operatorId;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append(" operatorId: ").append(operatorId);
    if (inputRecords >= 0) {
      sb.append(" inputRecords: ").append(inputRecords);
    }
    if (inputDataSize >= 0) {
      sb.append(" inputDataSize: ").append(inputDataSize);
    }
    if (outputRecords >= 0) {
      sb.append(" outputRecords: ").append(outputRecords);
    }
    if (outputDataSize >= 0) {
      sb.append(" outputDataSize: ").append(outputDataSize);
    }
    sb.append(" }");
    return sb.toString();
  }
}
