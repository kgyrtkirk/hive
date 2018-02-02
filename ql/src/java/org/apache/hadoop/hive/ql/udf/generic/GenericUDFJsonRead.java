/*
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
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

@Description(name = "json_read", value = "_FUNC_(json,type) - parses the given json according to the given complex type specification", extended = ""
    + "Parsed as null: if the json is null, it is the empty string or if it contains only whitespaces\n"
    + "Example:\n" + "select _FUNC_('[]','array<struct<a:string>>' ")
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
      if (text.trim().length() == 0) {
        return null;
      }
      JsonParser parser = factory.createParser(text);

      try {
        parser.nextToken();
        Object res = parseDispatcher(parser, outputOI);
        return res;
      } catch (Exception e) {
        String locationStr = parser.getCurrentLocation().getLineNr() + "," + parser.getCurrentLocation().getColumnNr();
        throw new HiveException("at[" + locationStr + "]: " + e.getMessage(), e);
      }
    } catch (Exception e) {
      throw new HiveException("Error parsing json: " + e.getMessage(), e);
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
    case MAP:
      return parseMap(parser, (MapObjectInspector) oi);
    default:
      throw new HiveException("parsing of: " + oi.getCategory() + " is not handled");
    }
  }

  private static Object parseMap(JsonParser parser, MapObjectInspector oi) throws IOException, HiveException {

    if (parser.getCurrentToken() == JsonToken.VALUE_NULL) {
      parser.nextToken();
      return null;
    }

    Map<Object, Object> ret = new LinkedHashMap<>();

    if (parser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw new HiveException("struct expected");
    }

    if(!(oi.getMapKeyObjectInspector() instanceof PrimitiveObjectInspector ) ) {
      throw new HiveException("map key must be a primitive");
    }
    PrimitiveObjectInspector keyOI = (PrimitiveObjectInspector) oi.getMapKeyObjectInspector();
    ObjectInspector valOI = oi.getMapValueObjectInspector();

    JsonToken currentToken = parser.nextToken();
    while (currentToken != null && currentToken != JsonToken.END_OBJECT) {

      if (currentToken != JsonToken.FIELD_NAME) {
        throw new HiveException("unexpected token: " + currentToken);
      }

      Object key = parseMapKey(parser, keyOI);
      Object val = parseDispatcher(parser, valOI);
      ret.put(key, val);

      currentToken = parser.getCurrentToken();
    }
    if (currentToken != null) {
      parser.nextToken();
    }
    return ret;

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
        try {
          StructField field = oi.getStructFieldRef(name);
          if (field == null) {
            throw new HiveException("undeclared field");
          }
          parser.nextToken();
          ret[field.getFieldID()] = parseDispatcher(parser, field.getFieldObjectInspector());
        } catch (Exception e) {
          throw new HiveException("struct field " + name + ": " + e.getMessage(), e);
        }
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
    try {
    while (currentToken != null && currentToken != JsonToken.END_ARRAY) {
      ObjectInspector eOI = oi.getListElementObjectInspector();
      ret.add(parseDispatcher(parser, eOI));
      currentToken = parser.getCurrentToken();
    }
    } catch (Exception e) {
      throw new HiveException("array: " + e.getMessage(), e);
    }
    currentToken = parser.nextToken();

    return ret;
  }

  private static Object parsePrimitive(JsonParser parser, PrimitiveObjectInspector oi)
      throws HiveException, IOException {
    JsonToken currentToken = parser.getCurrentToken();
    if (currentToken == null) {
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

  private static Object parseMapKey(JsonParser parser, PrimitiveObjectInspector oi) throws HiveException, IOException {
    JsonToken currentToken = parser.getCurrentToken();
    if (currentToken == null) {
      return null;
    }
    try {
      switch (parser.getCurrentToken()) {
      case FIELD_NAME:
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
