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
package org.apache.hadoop.hive.ql.optimizer.calcite.functions;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.rel.core.AggregateCall;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeField;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.sql.SqlAggFunction;
import org.apache.calcite.sql.SqlFunctionCategory;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlSplittableAggFunction;
import org.apache.calcite.sql.SqlSplittableAggFunction.SumSplitter;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.type.ReturnTypes;
import org.apache.calcite.sql.type.SqlOperandTypeChecker;
import org.apache.calcite.sql.type.SqlOperandTypeInference;
import org.apache.calcite.sql.type.SqlReturnTypeInference;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.util.ImmutableIntList;
import org.apache.hadoop.hive.ql.optimizer.calcite.IRollupableAggregate;

import com.google.common.collect.ImmutableList;

public class HiveSqlX extends SqlAggFunction implements IRollupableAggregate {

  public HiveSqlX(String string, SqlKind kind, SqlReturnTypeInference returnTypeInference,
      SqlOperandTypeInference operandTypeInference,
      SqlOperandTypeChecker operandTypeChecker) {
    super(
        string, kind,
        returnTypeInference,
        operandTypeInference,
        operandTypeChecker,
        SqlFunctionCategory.NUMERIC);
  }


//  @Override
//  public <T> T unwrap(Class<T> clazz) {
//    if (clazz == SqlSplittableAggFunction.class) {
//      return clazz.cast(SelfSplitter.INSTANCE);
//    }
//    return super.unwrap(clazz);
//  }

  @Override
  public <T> T unwrap(Class<T> clazz) {
    if (clazz == SqlSplittableAggFunction.class) {
      return clazz.cast(new HiveSumSplitter());
    }
    return super.unwrap(clazz);
  }

  class HiveSumSplitter extends SumSplitter {

    @Override
    public AggregateCall other(RelDataTypeFactory typeFactory, AggregateCall e) {
      RelDataType countRetType = typeFactory.createTypeWithNullability(typeFactory.createSqlType(SqlTypeName.BIGINT), true);
      return AggregateCall.create(
          new HiveSqlCountAggFunction(false, ReturnTypes.explicit(countRetType), getOperandTypeInference(),
              getOperandTypeChecker()),
        false, ImmutableIntList.of(), -1, countRetType, "count");
    }

    @Override
    public AggregateCall topSplit(RexBuilder rexBuilder,
        Registry<RexNode> extra, int offset, RelDataType inputRowType,
        AggregateCall aggregateCall, int leftSubTotal, int rightSubTotal) {
      final List<RexNode> merges = new ArrayList<>();
      final List<RelDataTypeField> fieldList = inputRowType.getFieldList();
      if (leftSubTotal >= 0) {
        final RelDataType type = fieldList.get(leftSubTotal).getType();
        merges.add(rexBuilder.makeInputRef(type, leftSubTotal));
      }
      if (rightSubTotal >= 0) {
        final RelDataType type = fieldList.get(rightSubTotal).getType();
        merges.add(rexBuilder.makeInputRef(type, rightSubTotal));
      }
      RexNode node;
      switch (merges.size()) {
      case 1:
        node = merges.get(0);
        break;
      case 2:
        node = rexBuilder.makeCall(SqlStdOperatorTable.MULTIPLY, merges);
        node = rexBuilder.makeAbstractCast(aggregateCall.type, node);
        break;
      default:
        throw new AssertionError("unexpected count " + merges);
      }
      int ordinal = extra.register(node);
      return AggregateCall
          .create(
              new HiveSqlSumAggFunction(false, getReturnTypeInference(), getOperandTypeInference(),
                  getOperandTypeChecker()),
          false, ImmutableList.of(ordinal), -1, aggregateCall.type, aggregateCall.name);
    }
  }

  @Override
  public SqlAggFunction getAggregate() {
    return this;
  }
}
