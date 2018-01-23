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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorConverter.TextConverter;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

@Description(name = "json_read", extended = "parses a json into the given complex type")
public class GenericUDFJsonRead extends GenericUDF {

  private ObjectInspector outputOI;
  private transient JsonFactory factory;
  private TextConverter inputConverter;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

    checkArgsSize(arguments, 2, 2);
    checkArgPrimitive(arguments, 0);
    checkArgPrimitive(arguments, 1);
    if (!ObjectInspectorUtils.isConstantObjectInspector(arguments[1])) {
      throw new UDFArgumentTypeException(1, getFuncName() + " argument 2 may only be a constant");
    }

    inputConverter = new TextConverter((PrimitiveObjectInspector) arguments[0]);
    String typeStr = getConstantStringValue(arguments, 1);

    try {
      TypeInfo t = TypeInfoUtils.getTypeInfoFromTypeString(typeStr);
      outputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(t);
    } catch (Exception e) {
      throw new UDFArgumentException(getFuncName() + ": Error parsing typestring: " + e.getMessage());
    }

    factory = new JsonFactory();
    return outputOI;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Object valObject = arguments[0].get();
    if (valObject == null) {
      return null;
    }

    try {
      String text = inputConverter.convert(valObject).toString();
      JsonParser parser = factory.createParser(text);

      parser.nextToken();
      Object res = parseDispatcher(parser, outputOI);

      return res;
    } catch (IOException e) {
      throw new HiveException("error parsing", e);
    }
  }

  @Override
  public String getDisplayString(String[] children) {
    return getStandardDisplayString("json_read", children);
  }

  private static Object parseDispatcher(JsonParser parser, ObjectInspector oi)
      throws JsonParseException, IOException, HiveException {

    switch (oi.getCategory()) {
    case PRIMITIVE:
      return parsePrimitive(parser, (PrimitiveObjectInspector) oi);
    case LIST:
      return parseList(parser, (ListObjectInspector) oi);
    case STRUCT:
      return parseStruct(parser, (StructObjectInspector) oi);
    default:
      throw new HiveException("parsing of: " + oi.getCategory() + " is not handled");
    }
  }

  private static Object parseStruct(JsonParser parser, StructObjectInspector oi)
      throws JsonParseException, IOException, HiveException {

    Object ret[] = new Object[oi.getAllStructFieldRefs().size()];

    if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
      parser.nextToken();
      return null;
    }
    if (parser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw new HiveException("struct expected");
    }
    JsonToken currentToken = parser.nextToken();
    while (currentToken != null && currentToken != JsonToken.END_OBJECT) {

      switch (currentToken) {
      case FIELD_NAME:
        String name = parser.getCurrentName();
        StructField field = oi.getStructFieldRef(name);
        if (field == null) {
          throw new HiveException("field with name: " + name + " is unknown");
        }
        parser.nextToken();
        ret[field.getFieldID()] = parseDispatcher(parser, field.getFieldObjectInspector());
        break;
      default:
        throw new HiveException("unexpected token: " + currentToken);
      }
      currentToken = parser.getCurrentToken();
    }
    if (currentToken != null) {
      parser.nextToken();
    }
    return ret;
  }

  private static Object parseList(JsonParser parser, ListObjectInspector oi)
      throws JsonParseException, IOException, HiveException {
    List<Object> ret = new ArrayList<>();

    if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
      parser.nextToken();
      return null;
    }

    if (parser.getCurrentToken() != JsonToken.START_ARRAY) {
      throw new HiveException("array expected");
    }
    JsonToken currentToken = parser.nextToken();
    while (currentToken != null && currentToken != JsonToken.END_ARRAY) {
      ObjectInspector eOI = oi.getListElementObjectInspector();
      ret.add(parseDispatcher(parser, eOI));
      currentToken = parser.getCurrentToken();
    }
    currentToken = parser.nextToken();

    return ret;
  }

  private static Object parsePrimitive(JsonParser parser, PrimitiveObjectInspector oi)
      throws HiveException, IOException {
    JsonToken currentToken = parser.getCurrentToken();
    if (currentToken == null) {
      // FIXME: does this makes sense?
      return null;
    }
    try {
      switch (parser.getCurrentToken()) {
      case VALUE_FALSE:
      case VALUE_TRUE:
      case VALUE_NUMBER_INT:
      case VALUE_NUMBER_FLOAT:
      case VALUE_STRING:
        Converter c =
            ObjectInspectorConverters.getConverter(PrimitiveObjectInspectorFactory.javaStringObjectInspector, oi);
        return c.convert(parser.getValueAsString());
      case VALUE_NULL:
        return null;
      default:
        throw new HiveException("unexpected token type: " + currentToken);
      }
    } finally {
      parser.nextToken();

    }
  }

}