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
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredObject;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

@Description(name = "custom_struct_parse")
public class CustomStructParse extends GenericUDF {

  private transient PrimitiveCategory inputType;
  private final DoubleWritable resultDouble = new DoubleWritable();
  private final LongWritable resultLong = new LongWritable();
  private final IntWritable resultInt = new IntWritable();
  private final HiveDecimalWritable resultDecimal = new HiveDecimalWritable();
  private transient PrimitiveObjectInspector argumentOI;
  private transient Converter inputConverter;

  private PrimitiveObjectInspector inputOI;
  private StandardStructObjectInspector outputOI;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    if (arguments.length != 1) {
      throw new UDFArgumentLengthException("requires 1 argument, got " + arguments.length);
    }

    if (arguments[0].getCategory() != Category.PRIMITIVE) {
      throw new UDFArgumentException("takes primitive types, got " + arguments[0].getTypeName());
    }
    argumentOI = (PrimitiveObjectInspector) arguments[0];

    inputOI = argumentOI;

    inputConverter = ObjectInspectorConverters.getConverter(arguments[0],
        PrimitiveObjectInspectorFactory.writableStringObjectInspector);

    outputOI = buildOutputOI();
    return outputOI;
  }


  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Object valObject = arguments[0].get();
    if (valObject == null) {
      return null;
    }

    Object retVal[] = new Object[2];
    retVal[0] = new Text("asd");
    retVal[1] = new LongWritable(21);

    return retVal;

  }

  @Override
  public String getDisplayString(String[] children) {
    return getStandardDisplayString("custom_struct_parse", children);
  }

  public StandardStructObjectInspector buildOutputOI() {
    ArrayList<String> fname = new ArrayList<String>();
    ArrayList<ObjectInspector> foi = new ArrayList<ObjectInspector>();
    fname.add("label");
    foi.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
    fname.add("cnt");
    foi.add(PrimitiveObjectInspectorFactory.writableLongObjectInspector);
    return ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);
  }

}