package org.apache.hadoop.hive.ql.exec;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.AggregationDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.GroupByDesc;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.BaseStructObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazySimpleStructObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

public class TestGroupByOperator {

  @Test
  public void t0() throws Exception {
    ExprNodeConstantDesc desc = new ExprNodeConstantDesc(137);
    ExprNodeConstantEvaluator eval = new ExprNodeConstantEvaluator(desc);

    Object val = eval.evaluate(new Object[] {});
    System.out.println(val);
  }

  public static class LocalGroupByOpertor extends GroupByOperator {
    @Override
    protected void forward(Object row, ObjectInspector rowInspector) throws HiveException {
      Object[] oo = (Object[]) row;
      System.out.println(oo[0]);
      assertNotNull("should be the constant requested", oo[0]);
    }
  }

  @Test
  public void checkEmptyAggregationConstantPassing() throws Exception {

    ExprNodeConstantDesc desc = new ExprNodeConstantDesc(137);
//    ExprNodeConstantEvaluator eval = new ExprNodeConstantEvaluator(desc);

    LocalGroupByOpertor g0 = new LocalGroupByOpertor();
    GroupByDesc conf = new GroupByDesc();
    AggregationDesc aggregator = new AggregationDesc();
    
    aggregator.setGenericUDAFEvaluator(Mockito.mock(GenericUDAFEvaluator.class));
    aggregator.setParameters(Lists.newArrayList(desc));

    conf.setAggregators(Lists.newArrayList(aggregator));
    conf.setKeys(Lists.newArrayList());
    conf.setOutputColumnNames(Lists.newArrayList());

    List<String> structFieldNames = Lists.newArrayList("asd");
    List<ObjectInspector> structFieldObjectInspectors =
        Lists.newArrayList(desc.getWritableObjectInspector());
    List<String> structFieldComments = Lists.newArrayList("asd");
    byte separator = 'x';
    LazyObjectInspectorParameters lazyParams = null;

    LazySimpleStructObjectInspector sso = new LazySimpleStructObjectInspector(structFieldNames,
        structFieldObjectInspectors, structFieldComments, separator, lazyParams);
    g0.setInputObjInspectors(new ObjectInspector[] { sso });
    g0.setConf(conf);
    g0.initializeOp(new HiveConf());
    g0.closeOp(false);
  }

}
