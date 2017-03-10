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

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFCount.GenericUDAFCountEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFVariance.GenericUDAFVarianceEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public class GenericUDAFBinarySetFunctions extends AbstractGenericUDAFResolver {

  @Description(name = "regr_sxx",
      value = "_FUNC_(y,x) - write this",
      extended = "XXXX MISSING XXXX The function takes as arguments any pair of numeric types and returns a double.\n"
          + "Any pair with a NULL is ignored. If the function is applied to an empty set, NULL\n"
          + "will be returned. Otherwise, it computes the following:\n"
          + "   (SUM(x*y)-SUM(x)*SUM(y)/COUNT(x,y))/COUNT(x,y)\n"
          + "where neither x nor y is null.")
  public static class Regr_SXX extends AbstractGenericUDAFResolver {

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
      checkArgumentTypes(parameters);
      return new RegrSXXEvaluator();
    }

    public static class RegrSXXEvaluator extends GenericUDAFVarianceEvaluator {

      @Override
      public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
        switch (m) {
        case COMPLETE:
        case PARTIAL1:
          return super.init(m, new ObjectInspector[] { parameters[1] });
        default:
          return super.init(m, parameters);
        }
      }

      @Override
      public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
        if (parameters[0] == null || parameters[1] == null)
          return;
        super.iterate(agg, new Object[] { parameters[1] });
      }

      @Override
      public Object terminate(AggregationBuffer agg) throws HiveException {
        StdAgg myagg = (StdAgg) agg;
        // SQL standard - return null for zero elements
        if (myagg.count == 0) { 
          return null;
        } else {
          DoubleWritable result = getResult();
          result.set(myagg.variance);
          return result;
        }
      }
    }
  }  
  
  @Description(name = "regr_syy",
      value = "_FUNC_(y,x) - write this",
      extended = "XXXX MISSING XXXX The function takes as arguments any pair of numeric types and returns a double.\n"
          + "Any pair with a NULL is ignored. If the function is applied to an empty set, NULL\n"
          + "will be returned. Otherwise, it computes the following:\n"
          + "   (SUM(x*y)-SUM(x)*SUM(y)/COUNT(x,y))/COUNT(x,y)\n"
          + "where neither x nor y is null.")
  public static class Regr_SYY extends AbstractGenericUDAFResolver {

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
      checkArgumentTypes(parameters);
      return new RegrSXXEvaluator();
    }

    public static class RegrSXXEvaluator extends GenericUDAFVarianceEvaluator {

      @Override
      public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
        switch (m) {
        case COMPLETE:
        case PARTIAL1:
          return super.init(m, new ObjectInspector[] { parameters[0] });
        default:
          return super.init(m, parameters);
        }
      }

      @Override
      public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
        if (parameters[0] == null || parameters[1] == null)
          return;
        super.iterate(agg, new Object[] { parameters[0] });
      }

      @Override
      public Object terminate(AggregationBuffer agg) throws HiveException {
        StdAgg myagg = (StdAgg) agg;
        // SQL standard - return null for zero elements
        if (myagg.count == 0) { 
          return null;
        } else {
          DoubleWritable result = getResult();
          result.set(myagg.variance);
          return result;
        }
      }
    }
  }  

  private static void checkArgumentTypes(TypeInfo[] parameters) throws UDFArgumentTypeException {
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
  }

  private static boolean acceptedPrimitiveCategory(PrimitiveCategory primitiveCategory) {
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

}
