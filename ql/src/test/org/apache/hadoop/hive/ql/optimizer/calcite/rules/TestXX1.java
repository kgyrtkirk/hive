package org.apache.hadoop.hive.ql.optimizer.calcite.rules;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptSchema;
import org.apache.calcite.plan.hep.HepPlanner;
import org.apache.calcite.plan.hep.HepProgramBuilder;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.metadata.Metadata;
import org.apache.calcite.rel.metadata.MetadataDef;
import org.apache.calcite.rel.metadata.MetadataHandler;
import org.apache.calcite.rel.metadata.RelMetadataProvider;
import org.apache.calcite.rel.metadata.UnboundMetadata;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.tools.RelBuilder;
import org.apache.hadoop.hive.ql.optimizer.calcite.HiveRelFactories;
import org.apache.hadoop.hive.ql.optimizer.calcite.RelOptHiveTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

@RunWith(MockitoJUnitRunner.class)
public class TestXX1 {

  @Mock
  private RelOptSchema schemaMock;
  @Mock
  RelOptHiveTable tableMock;

  static class MyRecord {
    public int asd;
  }

  @Test
  public void testXa() {
    HiveReduceExpressionsWithStatsRule i = HiveReduceExpressionsWithStatsRule.INSTANCE;

    HepProgramBuilder programBuilder = new HepProgramBuilder();
    programBuilder.addRuleInstance(i);

    HepPlanner planner = new HepPlanner(programBuilder.build());

    List<RelMetadataProvider> list = Lists.newArrayList();
    RelMetadataProvider mdProvider = new RelMetadataProvider() {

      @Override
      public <M extends Metadata> Multimap<Method, MetadataHandler<M>> handlers(MetadataDef<M> def) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public <M extends Metadata> UnboundMetadata<M> apply(Class<? extends RelNode> relClass, Class<? extends M> metadataClass) {
        // TODO Auto-generated method stub
        return null;
      }
    };
    list.add(mdProvider);
    planner.registerMetadataProviders(list);
    // RelMetadataProvider chainedProvider = ChainedRelMetadataProvider.of(list);
    // basePlan.getCluster().setMetadataProvider(new CachingRelMetadataProvider(chainedProvider,
    // planner));

    // if (executorProvider != null) {
    // // basePlan.getCluster.getPlanner is the VolcanoPlanner from apply()
    // // both planners need to use the correct executor
    // basePlan.getCluster().getPlanner().setExecutor(executorProvider);
    // planner.setExecutor(executorProvider);
    // }

    // HiveRelFactories.HIVE_BUILDER
    JavaTypeFactoryImpl typeFactory = new JavaTypeFactoryImpl();
    RexBuilder rexBuilder = new RexBuilder(typeFactory);
    final RelOptCluster optCluster = RelOptCluster.create(planner, rexBuilder);
    // new SqlTypeFactoryImpl(new JavaTypeFactoryImpl())
    RelDataType rowTypeMock = typeFactory.createStructType(MyRecord.class);
    // typeFactory.createType(Integer.TYPE);
    Mockito.doReturn(rowTypeMock).when(tableMock).getRowType();
    // Mockito.doReturn(myColStats).when(tableMock).getColStat(Matchers.anyList());
    Mockito.doReturn(tableMock).when(schemaMock).getTableForMember(Matchers.any());

    RelBuilder builder = HiveRelFactories.HIVE_BUILDER.create(optCluster, schemaMock);
    // final RelNode basePlan =
    // builder.scan("t").filter(builder.isNull(rexBuilder.makeNullLiteral(SqlTypeName.BIGINT))).build();
    // @formatter:off
    final RelNode basePlan = builder
          .scan("t")
          .filter(
              builder.call(SqlStdOperatorTable.GREATER_THAN,
                    builder.field("asd"), builder.literal(1)
                    )
              )
          .build();
    // @formatter:on
    // builder.Null(rexBuilder.makeNullLiteral(SqlTypeName.BIGINT))).build();
    // builder.isNull(rexBuilder.makeNullLiteral(SqlTypeName.BIGINT));
    // builder.scan("CUSTOMERS").scan("ORDERS").join(JoinRelType.INNER, "ORDER_ID").build();

    planner.setRoot(basePlan);
    RelNode optimizedRelNode = planner.findBestExp();
    System.out.println(optimizedRelNode);

  }

}
