package org.apache.hadoop.hive.ql.optimizer.calcite.rules;

import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptSchema;
import org.apache.calcite.plan.hep.HepPlanner;
import org.apache.calcite.plan.hep.HepProgramBuilder;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.tools.RelBuilder;
import org.apache.hadoop.hive.ql.optimizer.calcite.HiveRelFactories;
import org.apache.hadoop.hive.ql.optimizer.calcite.RelOptHiveTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestXX1 {

  @Mock
  private RelOptSchema schemaMock;
  @Mock
  RelOptHiveTable tableMock;
  private HepPlanner planner;
  private RelBuilder builder;

  private static class MyRecord {
    public int _int;
  }

  @Before
  public void before() {
    HiveReduceExpressionsWithStatsRule i = HiveReduceExpressionsWithStatsRule.INSTANCE;

    HepProgramBuilder programBuilder = new HepProgramBuilder();
    programBuilder.addRuleInstance(i);

    planner = new HepPlanner(programBuilder.build());

    JavaTypeFactoryImpl typeFactory = new JavaTypeFactoryImpl();
    RexBuilder rexBuilder = new RexBuilder(typeFactory);
    final RelOptCluster optCluster = RelOptCluster.create(planner, rexBuilder);
    RelDataType rowTypeMock = typeFactory.createStructType(MyRecord.class);
    Mockito.doReturn(rowTypeMock).when(tableMock).getRowType();
    Mockito.doReturn(tableMock).when(schemaMock).getTableForMember(Matchers.any());

    builder = HiveRelFactories.HIVE_BUILDER.create(optCluster, schemaMock);

  }

  @Test
  public void testXa() {

    // @formatter:off
    final RelNode basePlan = builder
          .scan("t")
          .filter(
              builder.call(SqlStdOperatorTable.GREATER_THAN,
                    builder.field("_int"), builder.literal(1)
                    )
              )
          .build();
    // @formatter:on

    planner.setRoot(basePlan);
    RelNode optimizedRelNode = planner.findBestExp();
    System.out.println(optimizedRelNode);

  }

}
