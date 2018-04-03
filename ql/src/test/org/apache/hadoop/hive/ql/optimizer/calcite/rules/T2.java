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

package org.apache.hadoop.hive.ql.optimizer.calcite.rules;

import java.util.List;
import java.util.TimeZone;

import org.apache.calcite.DataContext;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.linq4j.QueryProvider;
import org.apache.calcite.plan.RelOptPredicateList;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.rex.RexExecutor;
import org.apache.calcite.rex.RexExecutorImpl;
import org.apache.calcite.rex.RexLiteral;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.rex.RexSimplify;
import org.apache.calcite.rex.RexUtil;
import org.apache.calcite.rex.RexUtil.ExprSimplifier;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.type.SqlTypeName;
import org.junit.Before;
import org.junit.Test;
import org.spark_project.guava.collect.Lists;

import com.google.common.collect.ImmutableMap;

public class T2 {

  @Test
  public void ass() {
    JavaTypeFactoryImpl typeFactory = new JavaTypeFactoryImpl();
    final RelDataType intType = typeFactory.createSqlType(SqlTypeName.VARCHAR);

    RexBuilder rexBuilder = new RexBuilder(typeFactory);

    // @formatter:off
    List<RexNode> newConjuncts = Lists.newArrayList(
        rexBuilder.makeCall(SqlStdOperatorTable.EQUALS, rexBuilder.makeInputRef(intType, 0),rexBuilder.makeLiteral("x")),
        rexBuilder.makeCall(SqlStdOperatorTable.IN, rexBuilder.makeInputRef(intType, 0),rexBuilder.makeLiteral("x")),
        rexBuilder.makeCall(SqlStdOperatorTable.IN, rexBuilder.makeInputRef(intType, 0),rexBuilder.makeLiteral("x"),rexBuilder.makeLiteral("xx"))
        );
    // @formatter:on

    RexNode newPredicate = RexUtil.composeConjunction(rexBuilder, newConjuncts, false);

    ExprSimplifier ss = new RexUtil.ExprSimplifier(simplify, false);
    RexNode p2 = ss.apply(newPredicate);
    System.out.println(newPredicate.toString());
    System.out.println(p2.toString());
    //    assertFalse(p2.isAlwaysTrue());

  }

  //~ Instance fields --------------------------------------------------------
  private JavaTypeFactory typeFactory;
  private RexBuilder rexBuilder;
  private RexLiteral trueLiteral;
  private RexLiteral falseLiteral;
  private RexNode nullLiteral;
  private RexNode unknownLiteral;
  private RexSimplify simplify;


  @Before
  public void setUp() {
    typeFactory = new JavaTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
    rexBuilder = new RexBuilder(typeFactory);
    RexExecutor executor = new RexExecutorImpl(new DummyTestDataContext());
    simplify = new RexSimplify(rexBuilder, RelOptPredicateList.EMPTY, false, executor);
    trueLiteral = rexBuilder.makeLiteral(true);


  }

  /** Dummy data context for test. */
  private static class DummyTestDataContext implements DataContext {
    private final ImmutableMap<String, Object> map;

    DummyTestDataContext() {
      this.map = ImmutableMap.<String, Object> of(Variable.TIME_ZONE.camelName,
          TimeZone.getTimeZone("America/Los_Angeles"), Variable.CURRENT_TIMESTAMP.camelName, 1311120000000L);
    }

    @Override
    public SchemaPlus getRootSchema() {
      return null;
    }

    @Override
    public JavaTypeFactory getTypeFactory() {
      return null;
    }

    @Override
    public QueryProvider getQueryProvider() {
      return null;
    }

    @Override
    public Object get(String name) {
      return map.get(name);
    }
  }

}
