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
  CompilationOpContext cCtx = new CompilationOpContext();

  @Test
  public void testFilterOpEquals() {
    Operator<? extends OperatorDesc> op7, op7b;
    {
      ExprNodeDesc pred = new ExprNodeConstantDesc(7);
      FilterDesc fd = new FilterDesc(pred, true);
      op7 = OperatorFactory.get(cCtx, fd);
    }
    {
      ExprNodeDesc pred = new ExprNodeConstantDesc(7);
      FilterDesc fd = new FilterDesc(pred, true);
      op7b = OperatorFactory.get(cCtx, fd);
    }

    XSignature.of(op7);

    assertTrue(op7.logicalEquals(op7b));

  }

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock
  GenericUDF udfMock;

  @Test
  public void testTableScand() {
    Operator<TableScanDesc> t1, t2;
    {
      Table tblMetadata = new Table("db", "t1");
      TableScanDesc desc = new TableScanDesc("alias", tblMetadata);
      List<ExprNodeDesc> as = Lists.newArrayList(new ExprNodeConstantDesc(3),
          new ExprNodeColumnDesc(TypeInfoFactory.intTypeInfo, "c1", "aa", false));
      ExprNodeGenericFuncDesc f1 = new ExprNodeGenericFuncDesc(TypeInfoFactory.intTypeInfo, udfMock, as);
      desc.setFilterExpr(f1);
      t1 = OperatorFactory.get(cCtx, desc);
    }
    {
      Table tblMetadata = new Table("db", "t2");
      TableScanDesc desc = new TableScanDesc("alias", tblMetadata);

      List<ExprNodeDesc> as = Lists.newArrayList(new ExprNodeConstantDesc(3),
          new ExprNodeColumnDesc(TypeInfoFactory.intTypeInfo, "c1", "aa", false));
      ExprNodeGenericFuncDesc f1 = new ExprNodeGenericFuncDesc(TypeInfoFactory.intTypeInfo, udfMock, as);
      desc.setFilterExpr(f1);
      t2 = OperatorFactory.get(cCtx, desc);
    }

    //    XSignature.of(op7);

    assertFalse(t1.logicalEquals(t2));
  }

}
