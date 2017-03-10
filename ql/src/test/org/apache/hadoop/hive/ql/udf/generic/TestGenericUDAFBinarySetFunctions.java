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
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator.AggregationBuffer;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import jersey.repackaged.com.google.common.collect.Lists;

@RunWith(Parameterized.class)
public class TestGenericUDAFBinarySetFunctions {

  /**
   * <pre>
   * 
   * covar_pop
   * covar_samp
   * corr
   * 
   * 
   * regr_slope       ~corr
   * regr_intercept   ~?
   * regr_r2          ~corr
   * regr_sxx         ~var_pop    =>  ok
   * regr_syy         ~var_pop  =>  ok
   * regr_sxy         ~corr?
   * regr_avgx        ~avg
   * regr_avgy        ~avg
   * regr_count       ~count
   * 
   *
   * </pre>
   */

  private List<Object[]> rowSet;

  @Parameters
  public static List<Object[]> getParameters() {
    List<Object[]> ret = new ArrayList<>();
    ret.add(new Object[] { "seq/seq", RowSetGenerator.generate(10,
        new RowSetGenerator.DoubleSequence(0), new RowSetGenerator.DoubleSequence(0)) });
    ret.add(new Object[] { "empty", RowSetGenerator.generate(0,
        new RowSetGenerator.DoubleSequence(0), new RowSetGenerator.DoubleSequence(0)) });
    ret.add(new Object[] { "lonely", RowSetGenerator.generate(1,
        new RowSetGenerator.DoubleSequence(0), new RowSetGenerator.DoubleSequence(0)) });
    ret.add(new Object[] { "seq/seq+10", RowSetGenerator.generate(10,
        new RowSetGenerator.DoubleSequence(0), new RowSetGenerator.DoubleSequence(10)) });
    ret.add(new Object[] { "seq/null", RowSetGenerator.generate(10,
        new RowSetGenerator.DoubleSequence(0), new RowSetGenerator.NullSequence()) });
    ret.add(new Object[] { "null/seq0", RowSetGenerator.generate(10,
        new RowSetGenerator.NullSequence(), new RowSetGenerator.DoubleSequence(0)) });
    return ret;
  }

  public static class GenericUDAFExecutor {

    private GenericUDAFResolver2 evaluatorFactory;
    private GenericUDAFParameterInfo info;
    private ObjectInspector[] partialOIs;

    public GenericUDAFExecutor(GenericUDAFResolver2 evaluatorFactory, GenericUDAFParameterInfo info)
        throws Exception {
      this.evaluatorFactory = evaluatorFactory;
      this.info = info;

      GenericUDAFEvaluator eval0 = evaluatorFactory.getEvaluator(info);
      partialOIs = new ObjectInspector[] {
          eval0.init(GenericUDAFEvaluator.Mode.PARTIAL1, info.getParameterObjectInspectors()) };

    }

    List<Object> run(List<Object[]> values) throws Exception {
      Object r1 = runComplete(values);
      Object r2 = runPartialFinal(values);
      return Lists.newArrayList(r1, r2);
    }

    private Object runComplete(List<Object[]> values) throws SemanticException, HiveException {
      GenericUDAFEvaluator eval = evaluatorFactory.getEvaluator(info);
      eval.init(GenericUDAFEvaluator.Mode.COMPLETE, info.getParameterObjectInspectors());
      AggregationBuffer agg = eval.getNewAggregationBuffer();
      for (Object[] parameters : values) {
        eval.iterate(agg, parameters);
      }
      return eval.terminate(agg);
    }

    private Object runPartialFinal(List<Object[]> values) throws Exception {
      GenericUDAFEvaluator eval = evaluatorFactory.getEvaluator(info);
      eval.init(GenericUDAFEvaluator.Mode.FINAL, partialOIs);
      AggregationBuffer buf = eval.getNewAggregationBuffer();
      for (Object partialResult : runPartial1(values)) {
        eval.merge(buf, partialResult);
      }
      return eval.terminate(buf);
    }

    private List<Object> runPartial1(List<Object[]> values) throws Exception {
      List<Object> ret = new ArrayList<>();
      int batchSize = 1;
      Iterator<Object[]> iter = values.iterator();
      do {
        GenericUDAFEvaluator eval = evaluatorFactory.getEvaluator(info);
        eval.init(GenericUDAFEvaluator.Mode.PARTIAL1, info.getParameterObjectInspectors());
        AggregationBuffer buf = eval.getNewAggregationBuffer();
        for (int i = 0; i < batchSize - 1 && iter.hasNext(); i++) {
          eval.iterate(buf, iter.next());
        }
        batchSize <<= 1;
        ret.add(eval.terminatePartial(buf));

        // back-check to force at least 1 output; and this should have a partial which is empty
      } while (iter.hasNext());
      return ret;
    }
  }

  public static class RowSetGenerator {
    public static interface FieldGenerator {
      public Object apply(int rowIndex);
    }

    public static class NullSequence implements FieldGenerator {
      @Override
      public Object apply(int rowIndex) {
        return null;
      }
    }

    public static class DoubleSequence implements FieldGenerator {

      private int offset;

      public DoubleSequence(int offset) {
        this.offset = offset;
      }

      @Override
      public Object apply(int rowIndex) {
        double d = rowIndex + offset;
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

  public TestGenericUDAFBinarySetFunctions(String label, List<Object[]> rowSet) {
    this.rowSet = rowSet;
  }

  // @Test
  // public void asdAvg() throws Exception {
  // ObjectInspector[] params = new ObjectInspector[] { javaDoubleObjectInspector };
  // GenericUDAFParameterInfo gpi = new SimpleGenericUDAFParameterInfo(params, false, false, false);
  // GenericUDAFExecutor executor = new GenericUDAFExecutor(new GenericUDAFAverage(), gpi);
  // DoubleWritable v = (DoubleWritable) executor
  // .run(RowSetGenerator.generate(10, new RowSetGenerator.DoubleSequence(0)));
  // assertEquals(4.5, v.get(), 1e-10);
  // }

  @Test
  public void regr_sxx() throws Exception {
    RegrIntermediate expected = RegrIntermediate.computeFor(rowSet);
    validateUDAF(expected.sxx(), new GenericUDAFBinarySetFunctions.Regr_SXX());
  }

  @Test
  public void regr_syy() throws Exception {
    RegrIntermediate expected = RegrIntermediate.computeFor(rowSet);
    validateUDAF(expected.syy(), new GenericUDAFBinarySetFunctions.Regr_SYY());
  }

  private void validateUDAF(Double expectedResult, GenericUDAFResolver2 udaf) throws Exception {
    ObjectInspector[] params =
        new ObjectInspector[] { javaDoubleObjectInspector, javaDoubleObjectInspector };
    GenericUDAFParameterInfo gpi = new SimpleGenericUDAFParameterInfo(params, false, false, false);
    GenericUDAFExecutor executor = new GenericUDAFExecutor(udaf, gpi);

    List<Object> values = executor.run(rowSet);

    if (expectedResult == null) {
      for (Object v : values) {
        assertNull(v);
      }
    } else {
      for (Object v : values) {
        assertEquals(expectedResult, ((DoubleWritable) v).get(), 1e-10);
      }
    }
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

    public Double syy() {
      if (n == 0)
        return null;
      return sum_y2 - sum_y * sum_y / n;
    }

    public static RegrIntermediate computeFor(List<Object[]> rows) {
      RegrIntermediate ri = new RegrIntermediate();
      for (Object[] objects : rows) {
        ri.add((Double) objects[0], (Double) objects[1]);
      }
      return ri;
    }

  }
}
