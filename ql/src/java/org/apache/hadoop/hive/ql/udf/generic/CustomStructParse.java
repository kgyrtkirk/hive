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
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

@Description(name = "custom_struct_parse")
public class CustomStructParse extends GenericUDF {

  private PrimitiveObjectInspector inputOI;
  private ObjectInspector outputOI;
  private PrimitiveObjectInspector argumentOI;
  private Converter inputConverter;

  static class PayLoadClass {
    public String product_held_identifier;
    public String active_date_time;
    public String party_visible_indicator;
    public String manufacturer_legal_entity_code;
    public String product_held_role_classification_code;
    public String seller_legal_entity_code;
    public String load_date;
    public String external_system_name;
    public String product_held_role_start_timestamp;
    public String external_product_held_identifier_text;
    public String product_held_role_code;
    public String external_system_identifier;
    public String product_held_role_classification_narrative;
    public String party_identifier;
    public String product_held_open_date;
    public String external_product_identifier_text;
    public String product_held_role_narrative;
    public String amendment_effective_date;
    public String delete_flag;
    public String product_held_close_date;
  }

  List<PayLoadClass> payload;

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

    List<Object> rv = new ArrayList<>();
    rv.add(retVal);
    return rv;

  }

  @Override
  public String getDisplayString(String[] children) {
    return getStandardDisplayString("custom_struct_parse", children);
  }

  public ObjectInspector buildOutputOI() {
    ArrayList<String> fname = new ArrayList<String>();
    ArrayList<ObjectInspector> foi = new ArrayList<ObjectInspector>();
    fname.add("label");
    foi.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
    fname.add("cnt");
    foi.add(PrimitiveObjectInspectorFactory.writableLongObjectInspector);

    StandardStructObjectInspector sOI = ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);
    StandardListObjectInspector listOI = ObjectInspectorFactory.getStandardListObjectInspector(sOI);

    return listOI;
  }

}