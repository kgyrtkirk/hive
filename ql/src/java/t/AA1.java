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

package t;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.hooks.NoOperatorReuseCheckerHook;
import org.apache.hadoop.hive.ql.parse.ParseContext;

public class AA1 {

  public String chk(ParseContext pctx, String name) {
    return chk(new org.apache.hadoop.hive.ql.optimizer.graph.OperatorGraph(pctx).findOperator(name));
  }

  private String chk(Operator<?> findOperator) {
    try {
      NoOperatorReuseCheckerHook.checkOperator(findOperator);
    } catch (Exception e) {
      return e.getMessage();
    }
    return null;

  }

}
