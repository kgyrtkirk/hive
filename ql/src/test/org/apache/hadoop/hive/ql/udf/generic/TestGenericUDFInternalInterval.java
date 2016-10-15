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

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredJavaObject;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredObject;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class TestGenericUDFInternalInterval {

  @Test
  public void testDayInterval() throws Exception {
    try (GenericUDFInternalInterval udf = new GenericUDFInternalInterval()) {

      ObjectInspector[] inputOIs =
          { PrimitiveObjectInspectorFactory.writableStringObjectInspector,
              PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(
                  TypeInfoFactory.intTypeInfo,
                  new IntWritable(HiveParser.TOK_INTERVAL_DAY_LITERAL)) };

      DeferredObject[] args = { new DeferredJavaObject(new Text("8")),
          new DeferredJavaObject(new ByteWritable((byte) 4)) };

      PrimitiveObjectInspector oi = (PrimitiveObjectInspector) udf.initialize(inputOIs);
      Assert.assertEquals(TypeInfoFactory.intervalDayTimeTypeInfo, oi.getTypeInfo());
      HiveIntervalDayTimeWritable res = (HiveIntervalDayTimeWritable) udf.evaluate(args);
      Assert.assertEquals(8, res.getHiveIntervalDayTime().getDays());
    }
  }

  @Test(expected = UDFArgumentException.class)
  public void testDoubleArgumentIsNotSupported() throws Exception {
    try (GenericUDFInternalInterval udf = new GenericUDFInternalInterval()) {

      ObjectInspector[] inputOIs =
          { PrimitiveObjectInspectorFactory.writableDoubleObjectInspector,
              PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(
                  TypeInfoFactory.intTypeInfo,
                  new IntWritable(HiveParser.TOK_INTERVAL_DAY_LITERAL)) };

      // should detect double
      udf.initialize(inputOIs);
    }
  }

  @Test(expected = UDFArgumentException.class)
  public void testInvalidString() throws Exception {
    try (GenericUDFInternalInterval udf = new GenericUDFInternalInterval()) {

      ObjectInspector[] inputOIs =
          { PrimitiveObjectInspectorFactory.writableStringObjectInspector,
              PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(
                  TypeInfoFactory.intTypeInfo,
                  new IntWritable(HiveParser.TOK_INTERVAL_DAY_LITERAL)) };

      DeferredObject[] args = { new DeferredJavaObject(new Text("invalid")),
          new DeferredJavaObject(new ByteWritable((byte) 4)) };

      PrimitiveObjectInspector oi = (PrimitiveObjectInspector) udf.initialize(inputOIs);
      Assert.assertEquals(TypeInfoFactory.intervalDayTimeTypeInfo, oi.getTypeInfo());
      HiveIntervalDayTimeWritable res = (HiveIntervalDayTimeWritable) udf.evaluate(args);
      Assert.assertEquals(8, res.getHiveIntervalDayTime().getDays());
    }
  }

  @Test
  public void testNullByPass() throws Exception {
    try (GenericUDFInternalInterval udf = new GenericUDFInternalInterval()) {

      ObjectInspector[] inputOIs =
          { PrimitiveObjectInspectorFactory.writableStringObjectInspector,
              PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(
                  TypeInfoFactory.intTypeInfo,
                  new IntWritable(HiveParser.TOK_INTERVAL_DAY_LITERAL)) };
      DeferredObject[] args =
          { new DeferredJavaObject(null), new DeferredJavaObject(new ByteWritable((byte) 4)) };

      PrimitiveObjectInspector oi = (PrimitiveObjectInspector) udf.initialize(inputOIs);
      Assert.assertEquals(TypeInfoFactory.intervalDayTimeTypeInfo, oi.getTypeInfo());
      HiveIntervalDayTimeWritable res = (HiveIntervalDayTimeWritable) udf.evaluate(args);
      Assert.assertEquals(null, res);
    }
  }

}
