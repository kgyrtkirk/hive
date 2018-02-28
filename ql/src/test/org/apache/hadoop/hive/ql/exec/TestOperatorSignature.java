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

package org.apache.hadoop.hive.ql.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.hadoop.hive.ql.CompilationOpContext;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.ExprNodeColumnDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeGenericFuncDesc;
import org.apache.hadoop.hive.ql.plan.FilterDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.TableScanDesc;
import org.apache.hadoop.hive.ql.plan.XSignature;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.collect.Lists;

public class TestOperatorSignature {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock
  GenericUDF udfMock;

  CompilationOpContext cCtx = new CompilationOpContext();

  @Test
  public void testFilterOpEquals() {
    Operator<? extends OperatorDesc> op7 = getFilterOp(7);
    Operator<? extends OperatorDesc> op8 = getFilterOp(8);
    Operator<? extends OperatorDesc> op7b = getFilterOp(7);

    checkEquals(op7, op7b);
    assertFalse(op7.logicalEquals(op8));


  }

  private void checkEquals(Operator<?> o1, Operator<?> o2) {
    assertTrue(o1.logicalEquals(o2));
    XSignature s1 = XSignature.of(o1);
    XSignature s2 = XSignature.of(o2);

    assertTrue("sigCmp", s1.signatureCompare(s2));
    assertEquals(s1.hashCode(), s2.hashCode());
    assertEquals(s1, s2);
  }

  @Test
  public void testTableScand() {
    Operator<TableScanDesc> t1 = getTsOp(3);
    Operator<TableScanDesc> t1a = getTsOp(3);
    Operator<TableScanDesc> t2 = getTsOp(4);

    assertTrue(t1.logicalEquals(t1a));
    assertFalse(t1.logicalEquals(t2));
  }

  private Operator<? extends OperatorDesc> getFilterOp(int constVal) {
    ExprNodeDesc pred = new ExprNodeConstantDesc(constVal);
    FilterDesc fd = new FilterDesc(pred, true);
    Operator<? extends OperatorDesc> op7 = OperatorFactory.get(cCtx, fd);
    return op7;
  }

  private Operator<TableScanDesc> getTsOp(int i) {
    Operator<TableScanDesc> t1;
    Table tblMetadata = new Table("db", "table");
      TableScanDesc desc = new TableScanDesc("alias", tblMetadata);
      List<ExprNodeDesc> as =
        Lists.newArrayList(new ExprNodeConstantDesc(TypeInfoFactory.intTypeInfo, Integer.valueOf(i)),
          new ExprNodeColumnDesc(TypeInfoFactory.intTypeInfo, "c1", "aa", false));
      ExprNodeGenericFuncDesc f1 = new ExprNodeGenericFuncDesc(TypeInfoFactory.intTypeInfo, udfMock, as);
      desc.setFilterExpr(f1);
      t1 = OperatorFactory.get(cCtx, desc);
    return t1;
  }

}
