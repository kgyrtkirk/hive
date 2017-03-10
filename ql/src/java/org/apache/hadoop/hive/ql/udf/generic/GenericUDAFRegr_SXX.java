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

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFCount.GenericUDAFCountEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFVariance.GenericUDAFVarianceEvaluator;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.LongWritable;

@Description(name = "regr_sxx",
    value = "_FUNC_(y,x) - write this",
    extended = "XXXX MISSING XXXX The function takes as arguments any pair of numeric types and returns a double.\n"
        + "Any pair with a NULL is ignored. If the function is applied to an empty set, NULL\n"
        + "will be returned. Otherwise, it computes the following:\n"
        + "   (SUM(x*y)-SUM(x)*SUM(y)/COUNT(x,y))/COUNT(x,y)\n"
        + "where neither x nor y is null.")
public class GenericUDAFRegr_SXX extends AbstractGenericUDAFResolver {

  @Override
  public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
    if (parameters.length != 2) {
      throw new UDFArgumentTypeException(parameters.length - 1,
          "Exactly two arguments are expected.");
    }

    if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
      throw new UDFArgumentTypeException(0,
          "Only primitive type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
    }

    if (parameters[1].getCategory() != ObjectInspector.Category.PRIMITIVE) {
        throw new UDFArgumentTypeException(1,
            "Only primitive type arguments are accepted but "
            + parameters[1].getTypeName() + " is passed.");
    }
    
    if (!acceptedPrimitiveCategory(((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory())){
      throw new UDFArgumentTypeException(1,
          "Only numeric type arguments are accepted but "
          + parameters[0].getTypeName() + " is passed.");
      
    }
    if (!acceptedPrimitiveCategory(((PrimitiveTypeInfo) parameters[1]).getPrimitiveCategory())){
      throw new UDFArgumentTypeException(1,
          "Only numeric type arguments are accepted but "
          + parameters[1].getTypeName() + " is passed.");
    }
    
    return new GenericUDAFRegrSXXEvaluator();
    
  }

  private boolean acceptedPrimitiveCategory(PrimitiveCategory primitiveCategory) {
    switch (primitiveCategory) {
    case BYTE:
    case SHORT:
    case INT:
    case LONG:
    case FLOAT:
    case DOUBLE:
    case TIMESTAMP:
    case DECIMAL:
      return true;
    default:
      return false;
    }
  }

  public static class GenericUDAFRegrSXXEvaluator extends GenericUDAFEvaluator {

    private GenericUDAFCountEvaluator countEvaluator;
    private GenericUDAFVarianceEvaluator varianceEvaluator;
    private ObjectInspector countOI;
    private ObjectInspector varOI;

    // For FINAL and COMPLETE
    private DoubleWritable result;

    public GenericUDAFRegrSXXEvaluator() {
      countEvaluator = new GenericUDAFCount.GenericUDAFCountEvaluator();
      varianceEvaluator = new GenericUDAFVariance.GenericUDAFVarianceEvaluator();
    }
    
    @Override
    public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
      super.init(m, parameters);
      
      countOI = countEvaluator.init(m, new ObjectInspector[] { parameters[0] });
      varOI = varianceEvaluator.init(m, new ObjectInspector[] { parameters[1] });
      
      if (mode == Mode.PARTIAL1 || mode == Mode.COMPLETE) {
        
      }else{
        throw new RuntimeException("not yet supported");
      }
      
      // init input
//      if (mode == Mode.PARTIAL1 || mode == Mode.COMPLETE) {
//        assert (parameters.length == 2);
//        xInputOI = (PrimitiveObjectInspector) parameters[0];
//        yInputOI = (PrimitiveObjectInspector) parameters[1];
//      } else {
//        assert (parameters.length == 1);
//        soi = (StructObjectInspector) parameters[0];
//
//        countField = soi.getStructFieldRef("count");
//        xavgField = soi.getStructFieldRef("xavg");
//        yavgField = soi.getStructFieldRef("yavg");
//        covarField = soi.getStructFieldRef("covar");
//
//        countFieldOI =
//            (LongObjectInspector) countField.getFieldObjectInspector();
//        xavgFieldOI =
//            (DoubleObjectInspector) xavgField.getFieldObjectInspector();
//        yavgFieldOI =
//            (DoubleObjectInspector) yavgField.getFieldObjectInspector();
//        covarFieldOI =
//            (DoubleObjectInspector) covarField.getFieldObjectInspector();
//      }

      // init output
      if (mode == Mode.PARTIAL1 || mode == Mode.PARTIAL2) {
        throw new RuntimeException("not yet supported");
//        // The output of a partial aggregation is a struct containing
//        // a long count, two double averages, and a double covariance.
//
//        ArrayList<ObjectInspector> foi = new ArrayList<ObjectInspector>();
//
//        foi.add(PrimitiveObjectInspectorFactory.writableLongObjectInspector);
//        foi.add(PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
//        foi.add(PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
//        foi.add(PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
//
//        ArrayList<String> fname = new ArrayList<String>();
//        fname.add("count");
//        fname.add("xavg");
//        fname.add("yavg");
//        fname.add("covar");
//
//        partialResult = new Object[4];
//        partialResult[0] = new LongWritable(0);
//        partialResult[1] = new DoubleWritable(0);
//        partialResult[2] = new DoubleWritable(0);
//        partialResult[3] = new DoubleWritable(0);
//
//        return ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);

      } else {
        setResult(new DoubleWritable(0));
        return PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;
      }
    }

    @AggregationType(estimable = true)
    static class StdAgg extends AbstractAggregationBuffer {
      GenericUDAFCount.GenericUDAFCountEvaluator.CountAgg countBuffer = new GenericUDAFCount.GenericUDAFCountEvaluator.CountAgg();
      GenericUDAFVarianceEvaluator.StdAgg varBuffer=new GenericUDAFVarianceEvaluator.StdAgg ();
      @Override
      public int estimate() { return varBuffer.estimate()+countBuffer.estimate(); }
    };

    @Override
    public AggregationBuffer getNewAggregationBuffer() throws HiveException {
      StdAgg result = new StdAgg();
      reset(result);
      return result;
    }

    @Override
    public void reset(AggregationBuffer agg) throws HiveException {
      StdAgg myagg = (StdAgg) agg;
      countEvaluator.reset(myagg.countBuffer);
      varianceEvaluator.reset(myagg.varBuffer);
    }

    private final boolean warned = false;

    @Override
    public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
      assert (parameters.length == 2);
      Object px = parameters[0];
      Object py = parameters[1];
      if (px != null && py != null) {
        StdAgg myagg = (StdAgg) agg;
        // this new is unfortunate to be here
        varianceEvaluator.iterate(myagg.varBuffer, new Object[]{parameters[1]});
      }
    }

    @Override
    public Object terminatePartial(AggregationBuffer agg) throws HiveException {
//      StdAgg myagg = (StdAgg) agg;
//      ((LongWritable) partialResult[0]).set(myagg.count);
//      ((DoubleWritable) partialResult[1]).set(myagg.xavg);
//      ((DoubleWritable) partialResult[2]).set(myagg.yavg);
//      ((DoubleWritable) partialResult[3]).set(myagg.covar);
//      return partialResult;
      throw new RuntimeException("baj1");
    }

    @Override
    public void merge(AggregationBuffer agg, Object partial) throws HiveException {
      throw new RuntimeException("baj1");
//      if (partial != null) {
//        StdAgg myagg = (StdAgg) agg;
//
//        Object partialCount = soi.getStructFieldData(partial, countField);
//        Object partialXAvg = soi.getStructFieldData(partial, xavgField);
//        Object partialYAvg = soi.getStructFieldData(partial, yavgField);
//        Object partialCovar = soi.getStructFieldData(partial, covarField);
//
//        long nA = myagg.count;
//        long nB = countFieldOI.get(partialCount);
//
//        if (nA == 0) {
//            // Just copy the information since there is nothing so far
//            myagg.count = countFieldOI.get(partialCount);
//            myagg.xavg = xavgFieldOI.get(partialXAvg);
//            myagg.yavg = yavgFieldOI.get(partialYAvg);
//            myagg.covar = covarFieldOI.get(partialCovar);
//        }
//
//        if (nA != 0 && nB != 0) {
//          // Merge the two partials
//          double xavgA = myagg.xavg;
//          double yavgA = myagg.yavg;
//          double xavgB = xavgFieldOI.get(partialXAvg);
//          double yavgB = yavgFieldOI.get(partialYAvg);
//          double covarB = covarFieldOI.get(partialCovar);
//
//          myagg.count += nB;
//          myagg.xavg = (xavgA * nA + xavgB * nB) / myagg.count;
//          myagg.yavg = (yavgA * nA + yavgB * nB) / myagg.count;
//          myagg.covar +=
//              covarB + (xavgA - xavgB) * (yavgA - yavgB) * ((double) (nA * nB) / myagg.count);
//        }
//      }
    }

    @Override
    public Object terminate(AggregationBuffer agg) throws HiveException {
      StdAgg myagg = (StdAgg) agg;

      if (myagg.varBuffer.count == 0) { // SQL standard - return null for zero elements
          return null;
      } else {
          getResult().set(myagg.varBuffer.variance);
          return getResult();
      }
    }

    public void setResult(DoubleWritable result) {
      this.result = result;
    }

    public DoubleWritable getResult() {
      return result;
    }
  }

}
