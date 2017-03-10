/**
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

package org.apache.hadoop.hive.ql.udf.generic;

import static org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory.javaDoubleObjectInspector;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator.AggregationBuffer;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.junit.Test;

public class TestGenericUDAFRegr_SXX {

  private List<Object[]> rowSet;

  public static class GenericUDAFExecutor {

    private GenericUDAFResolver2 evaluatorFactory;
    private GenericUDAFParameterInfo gpi;

    public GenericUDAFExecutor(GenericUDAFResolver2 evaluatorFactory, GenericUDAFParameterInfo gpi) {
      this.evaluatorFactory = evaluatorFactory;
      this.gpi = gpi;
    }

    Object run(List<Object[]> values) throws Exception {
      GenericUDAFEvaluator eval = evaluatorFactory.getEvaluator(gpi);
      eval.init(GenericUDAFEvaluator.Mode.COMPLETE, gpi.getParameterObjectInspectors());
      AggregationBuffer buf = eval.getNewAggregationBuffer();
      for (Object[] parameters : values) {
        eval.iterate(buf, parameters);
      }
      return eval.terminate(buf);
    }
  }

  public static class RowSetGenerator {
    public static interface FieldGenerator {
      public Object apply(int rowIndex);
    }

    public static class DoubleSequence implements FieldGenerator {

      @Override
      public Object apply(int rowIndex) {
        double d = rowIndex;
        return d;
      }
    }

    public static List<Object[]> generate(int numRows, FieldGenerator... generators) {
      ArrayList<Object[]> ret = new ArrayList<>(numRows);
      for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
        ArrayList<Object> row = new ArrayList<>();
        for (FieldGenerator g : generators) {
          row.add(g.apply(rowIdx));
        }
        ret.add(row.toArray());
      }
      return ret;
    }

  }
  
  public TestGenericUDAFRegr_SXX() {
    rowSet = RowSetGenerator.generate(10, new RowSetGenerator.DoubleSequence(), new RowSetGenerator.DoubleSequence());
  }

  @Test
  public void asdAvg() throws Exception {
    ObjectInspector[] params = new ObjectInspector[] { javaDoubleObjectInspector };
    GenericUDAFParameterInfo gpi = new SimpleGenericUDAFParameterInfo(params, false, false, false);
    GenericUDAFExecutor executor = new GenericUDAFExecutor(new GenericUDAFAverage(), gpi);
    DoubleWritable v = (DoubleWritable) executor.run(RowSetGenerator.generate(10, new RowSetGenerator.DoubleSequence()));
    assertEquals(4.5, v.get(), 1e-10);
  }

  @Test
  public void regr_sxx() throws Exception {
    ObjectInspector[] params = new ObjectInspector[] { javaDoubleObjectInspector, javaDoubleObjectInspector };
    GenericUDAFParameterInfo gpi = new SimpleGenericUDAFParameterInfo(params, false, false, false);
    GenericUDAFExecutor executor = new GenericUDAFExecutor(new GenericUDAFRegr_SXX(), gpi);

    Object v = executor.run(rowSet);
    RegrIntermediate expected = RegrIntermediate.computeFor(rowSet);
    assertEquals(expected.sxx(), ((DoubleWritable) v).get(), 1e-10);

  }

  static class RegrIntermediate {
    public double sum_x2, sum_y2;
    public double sum_x, sum_y;
    public double n;

    public void add(Double y, Double x) {
      if (x == null || y == null) {
        return;
      }
      sum_x2 += x * x;
      sum_y2 += y * y;
      sum_x += x;
      sum_y += y;
      n++;
    }

    public Double sxx() {
      if (n == 0)
        return null;
      return sum_x2 - sum_x * sum_x / n;
    }

    public static RegrIntermediate computeFor(List<Object[]> rows) {
      RegrIntermediate ri = new RegrIntermediate();
      for (Object[] objects : rows) {
        ri.add((double) objects[0], (double) objects[1]);
      }
      return ri;
    }

  }

  public void testCorr() throws HiveException {
    GenericUDAFCorrelation corr = new GenericUDAFCorrelation();
    GenericUDAFEvaluator eval1 = corr.getEvaluator(new TypeInfo[] { TypeInfoFactory.doubleTypeInfo, TypeInfoFactory.doubleTypeInfo });
    GenericUDAFEvaluator eval2 = corr.getEvaluator(new TypeInfo[] { TypeInfoFactory.doubleTypeInfo, TypeInfoFactory.doubleTypeInfo });

    ObjectInspector poi1 =
        eval1.init(GenericUDAFEvaluator.Mode.PARTIAL1, new ObjectInspector[] { javaDoubleObjectInspector, javaDoubleObjectInspector });
    ObjectInspector poi2 =
        eval2.init(GenericUDAFEvaluator.Mode.PARTIAL1, new ObjectInspector[] { javaDoubleObjectInspector, javaDoubleObjectInspector });

    GenericUDAFEvaluator.AggregationBuffer buffer1 = eval1.getNewAggregationBuffer();
    eval1.iterate(buffer1, new Object[] { 100d, 200d });
    eval1.iterate(buffer1, new Object[] { 150d, 210d });
    eval1.iterate(buffer1, new Object[] { 200d, 220d });
    Object object1 = eval1.terminatePartial(buffer1);

    GenericUDAFEvaluator.AggregationBuffer buffer2 = eval2.getNewAggregationBuffer();
    eval2.iterate(buffer2, new Object[] { 250d, 230d });
    eval2.iterate(buffer2, new Object[] { 250d, 240d });
    eval2.iterate(buffer2, new Object[] { 300d, 250d });
    eval2.iterate(buffer2, new Object[] { 350d, 260d });
    Object object2 = eval2.terminatePartial(buffer2);

    ObjectInspector coi = eval2.init(GenericUDAFEvaluator.Mode.FINAL, new ObjectInspector[] { poi1 });

    GenericUDAFEvaluator.AggregationBuffer buffer3 = eval2.getNewAggregationBuffer();
    eval2.merge(buffer3, object1);
    eval2.merge(buffer3, object2);

    Object result = eval2.terminate(buffer3);
    assertEquals("0.987829161147262", String.valueOf(result));
  }
}
