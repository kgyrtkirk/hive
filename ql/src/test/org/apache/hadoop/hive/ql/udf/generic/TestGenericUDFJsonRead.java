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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredJavaObject;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF.DeferredObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;
import org.junit.Test;

public class TestGenericUDFJsonRead {

  @Test(expected = UDFArgumentException.class)
  public void testArgCnt1() throws HiveException {
    GenericUDFJsonRead udf = new GenericUDFJsonRead();
    ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
    ObjectInspector[] arguments = { valueOI };
    udf.initialize(arguments);
  }

  @Test(expected = UDFArgumentException.class)
  public void testArgCnt3() throws HiveException {
    GenericUDFJsonRead udf = new GenericUDFJsonRead();
    ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
    ObjectInspector[] arguments = { valueOI, valueOI };
    udf.initialize(arguments);
  }

  @Test(expected = UDFArgumentException.class)
  public void testArgInvalidType() throws HiveException {
    GenericUDFJsonRead udf = new GenericUDFJsonRead();
    ObjectInspector[] arguments = buildArguments("asd");
    udf.initialize(arguments);
  }

  @Test
  public void testList() throws HiveException {
    GenericUDFJsonRead udf = new GenericUDFJsonRead();
    ObjectInspector[] arguments = buildArguments("array<string>");
    udf.initialize(arguments);

    Object res = udf.evaluate(evalArgs("[\"a\",\"b\"]"));
    assertTrue(res instanceof List<?>);
    List<?> l = (List<?>) res;
    assertEquals(2, l.size());
    assertEquals(new Text("a"), l.get(0));
    assertEquals(new Text("b"), l.get(1));
  }

  @Test
  public void testStruct() throws HiveException {
    GenericUDFJsonRead udf = new GenericUDFJsonRead();
    ObjectInspector[] arguments = buildArguments("struct<a:string>");
    udf.initialize(arguments);

    Object res = udf.evaluate(evalArgs("{\"a\":\"b\"}"));
    assertTrue(res instanceof Object[]);
    Object o[] = (Object[]) res;
    assertEquals(new Text("b"), o[0]);
  }

  private DeferredObject[] evalArgs(String string) {
    return new DeferredObject[] { new DeferredJavaObject(new Text(string)), null };
  }

  private ObjectInspector[] buildArguments(String typeStr) {
    ObjectInspector valueOI = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
    ObjectInspector[] arguments = { valueOI, PrimitiveObjectInspectorFactory
        .getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.stringTypeInfo, new Text(typeStr)) };
    return arguments;
  }

}
