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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.hadoop.hive.ql.CompilationOpContext;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.FilterDesc;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestOpSigFactory {
  CompilationOpContext cCtx = new CompilationOpContext();

  @Rule
  public MockitoRule a = MockitoJUnit.rule();

  @Spy
  OpTreeSignatureFactory f = OpTreeSignatureFactory.newCache();

  @Test
  public void checkExplicit() {

    ExprNodeDesc pred = new ExprNodeConstantDesc(3);
    FilterDesc fd0 = new FilterDesc(pred, true);
    //    FilterOperator fop = (FilterOperator) OperatorFactory.get(cCtx, fd0);
    FilterOperator fop = new FilterOperator(cCtx);

    FilterDesc fd = Mockito.spy(fd0);
    fop.setConf(fd);

    OpTreeSignature s1 = f.getSignature(fop);
    System.out.println(s1);
    f.getSignature(fop);

    verify(f, times(2)).getSignature(Mockito.any());
    verify(fd, times(1)).getPredicateString();
  }

}
