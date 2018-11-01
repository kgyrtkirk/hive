package org.apache.hadoop.hive.ql.optimizer.calcite.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.plan.RelOptRule;
import org.apache.calcite.plan.hep.HepPlanner;
import org.apache.calcite.plan.hep.HepProgram;
import org.apache.calcite.plan.hep.HepProgramBuilder;
import org.apache.calcite.rel.core.Aggregate;
import org.apache.calcite.rel.core.Filter;
import org.apache.calcite.rel.rel2sql.RelToSqlConverterTest;
import org.apache.calcite.rel.rules.FilterMergeRule;
import org.apache.calcite.rel.rules.ProjectJoinTransposeRule;
import org.apache.calcite.test.CalciteAssert;
import org.apache.calcite.tools.RuleSet;
import org.apache.calcite.tools.RuleSets;
import org.apache.hadoop.hive.ql.optimizer.calcite.HiveRelFactories;
import org.junit.Test;

public class X1 extends RelToSqlConverterTest {

  @Test
  public void xx() {
    // TODO Auto-generated method stub

  }

  @Override
  @Test
  public void testBooleanCol() {

    final String sql =
        "SELECT sum(case when emps.empno=120 then emp.deptno else null end ) from emps join emp on (emp.deptno = emps.empno)";
    //    final String sql =
    //        "SELECT sum(case when emps.empno=120 then emp.deptno else null end ) from emps join emp on (emp.deptno = emps.empno) where manager and emps.empno>100";
    final String expected = "SELECT \"DEPT\".\"DEPTNO\"," + " \"EMP\".\"DEPTNO\" AS \"DEPTNO0\"\n"
        + "FROM \"JDBC_SCOTT\".\"DEPT\"\n" + "LEFT JOIN \"JDBC_SCOTT\".\"EMP\""
        + " ON \"DEPT\".\"DEPTNO\" = \"EMP\".\"DEPTNO\"\n" + "WHERE \"EMP\".\"JOB\" LIKE 'PRESIDENT'";



    List<RelOptRule> rules = new ArrayList<>();
    rules.add(HiveFilterProjectTransposeRule.INSTANCE_DETERMINISTIC);
    rules.add(HiveFilterSetOpTransposeRule.INSTANCE);
    rules.add(HiveFilterSortTransposeRule.INSTANCE);
    rules.add(HiveFilterJoinRule.JOIN);
    rules.add(HiveFilterJoinRule.FILTER_ON_JOIN);
    rules.add(new HiveFilterAggregateTransposeRule(Filter.class, HiveRelFactories.HIVE_BUILDER, Aggregate.class));
    rules.add(new FilterMergeRule(HiveRelFactories.HIVE_BUILDER));
    rules.add(HiveProjectFilterPullUpConstantsRule.INSTANCE);
    rules.add(HiveReduceExpressionsRule.PROJECT_INSTANCE);
    rules.add(HiveReduceExpressionsRule.FILTER_INSTANCE);
    rules.add(HiveReduceExpressionsRule.JOIN_INSTANCE);
    rules.add(HiveAggregateReduceFunctionsRule.INSTANCE);
    rules.add(HiveAggregateReduceRule.INSTANCE);
    rules.add(HiveProjectJoinTransposeRule.INSTANCE);
    rules.add(ProjectJoinTransposeRule.INSTANCE);

    HepProgramBuilder hepProgramBuilder = new HepProgramBuilder();
    for (RelOptRule rule1 : rules) {

      RelOptRule rule = rule1;
      hepProgramBuilder.addRuleInstance(rule);
    }
    final HepProgram program = hepProgramBuilder.build();

    final RuleSet ruleSet = RuleSets.ofList(rules);//ProjectFilterTransposeRule.INSTANCE, HiveProjectJoinTransposeRule.INSTANCE);

    //    final RuleSet rules = RuleSets.ofList(UnionMergeRule.INSTANCE);
    sql(sql).schema(CalciteAssert.SchemaSpec.POST).optimize(ruleSet, new HepPlanner(program)).ok(expected);
  }

}
