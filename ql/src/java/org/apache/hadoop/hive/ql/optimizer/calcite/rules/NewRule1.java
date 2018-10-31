package org.apache.hadoop.hive.ql.optimizer.calcite.rules;

import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptRule;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.rel.core.Project;
import org.apache.hadoop.hive.ql.optimizer.calcite.reloperators.HiveProject;

public class NewRule1 extends RelOptRule {

  public static final RelOptRule INSTANCE = new NewRule1();

  public NewRule1() {
    super(operand(HiveProject.class, any()));
  }

  @Override
  public void onMatch(RelOptRuleCall call) {
    final Project project = call.rel(0);
    project.getChildExps();
    RelOptCluster cluster = project.getCluster();

    //    final RexBuilder rexBuilder = filter.getCluster().getRexBuilder();
    //    final RexNode condition = RexUtil.pullFactors(rexBuilder, filter.getCondition());
    //    analyzeCondition(call , rexBuilder, filter, condition);
  }

}
