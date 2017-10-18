package org.apache.hadoop.hive.ql.optimizer.calcite.functions;

import java.util.List;

import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.sql.SqlAggFunction;
import org.apache.calcite.sql.SqlFunctionCategory;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlSplittableAggFunction;
import org.apache.calcite.sql.type.SqlOperandTypeChecker;
import org.apache.calcite.sql.type.SqlOperandTypeInference;
import org.apache.calcite.sql.type.SqlReturnTypeInference;
import org.apache.calcite.sql.type.SqlTypeName;

import com.google.common.collect.ImmutableList;

/**
 * <code>Sum0</code> is an aggregator which returns the sum of the values which
 * go into it like <code>Sum</code>. It differs in that when no non null values
 * are applied zero is returned instead of null. Can be used along with <code>
 * Count</code> to implement <code>Sum</code>.
 */
public class HiveSqlSumEmptyIsZeroAggFunction extends SqlAggFunction {
  //~ Constructors -----------------------------------------------------------

  public HiveSqlSumEmptyIsZeroAggFunction(boolean isDistinct, SqlReturnTypeInference returnTypeInference,
          SqlOperandTypeInference operandTypeInference, SqlOperandTypeChecker operandTypeChecker) {
    super("$SUM0",
        null,
        SqlKind.SUM0,
        returnTypeInference,
        operandTypeInference,
        operandTypeChecker,
        SqlFunctionCategory.NUMERIC,
        false,
        false);
  }

  //~ Methods ----------------------------------------------------------------

  public List<RelDataType> getParameterTypes(RelDataTypeFactory typeFactory) {
    return ImmutableList.of(
        typeFactory.createTypeWithNullability(
            typeFactory.createSqlType(SqlTypeName.ANY), true));
  }

  public RelDataType getReturnType(RelDataTypeFactory typeFactory) {
    return typeFactory.createTypeWithNullability(
        typeFactory.createSqlType(SqlTypeName.ANY), true);
  }

  @Override public <T> T unwrap(Class<T> clazz) {
    if (clazz == SqlSplittableAggFunction.class) {
      return clazz.cast(SqlSplittableAggFunction.SumSplitter.INSTANCE);
    }
    return super.unwrap(clazz);
  }
}
