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
import java.io.InputStream;
import java.nio.charset.CharacterCodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class XXXJsonHiveStructReader {

  private static final Logger LOG = LoggerFactory.getLogger(XXXJsonHiveStructReader.class);

  // XXX: RENAME
  private ObjectInspector outputOI;
  private JsonFactory factory;
  // FIX THIS!
  @Deprecated
  private static boolean ignoreUnknownFields;

  public XXXJsonHiveStructReader(TypeInfo t) {
    outputOI = TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(t);
    factory = new JsonFactory();

  }

  // XXX: consider exception types
  public Object parseStruct(String text) throws JsonParseException, IOException, HiveException {
    JsonParser parser = factory.createParser(text);
    return parseInternal(parser);
  }

  // XXX: one method?
  public Object parseStruct(InputStream is) throws JsonParseException, IOException, HiveException {
//    JsonFactory f = new JsonFactory();
//    JsonParser pp = f.createParser(is);
//    if (pp.hasCurrentToken()) {
//      pp.nextToken();
//    }

    //    JsonParser parser = factory.createJsonParser(is);
    JsonParser parser = factory.createParser(is);
    return parseInternal(parser);
  }

  private Object parseInternal(JsonParser parser) throws HiveException {
    try {
      parser.nextToken();
      Object res = parseDispatcher(parser, outputOI);
      return res;
    } catch (Exception e) {
      String locationStr = parser.getCurrentLocation().getLineNr() + "," + parser.getCurrentLocation().getColumnNr();
      throw new HiveException("at[" + locationStr + "]: " + e.getMessage(), e);
    }
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

    if (!(oi.getMapKeyObjectInspector() instanceof PrimitiveObjectInspector)) {
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

    Object[] ret = new Object[oi.getAllStructFieldRefs().size()];

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
          // XXX: linear scan inside the below method...get a map here or something..
          StructField field = null;
          try {
            field = oi.getStructFieldRef(name);
          } catch (RuntimeException e) {
            if (ignoreUnknownFields) {
              Log.warn("ignoring field:" + name);
              parser.nextToken();
              skipValue(parser);
              break;
            }
          }
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

  private static void skipValue(JsonParser parser) throws JsonParseException, IOException {

    int array = 0;
    int object = 0;
    do {
      JsonToken currentToken = parser.getCurrentToken();
      if(currentToken == JsonToken.START_ARRAY) {
        array++;
      }
      if (currentToken == JsonToken.END_ARRAY) {
        array--;
      }
      if (currentToken == JsonToken.START_OBJECT) {
        object++;
      }
      if (currentToken == JsonToken.END_OBJECT) {
        object--;
      }

      parser.nextToken();

    } while (array > 0 || object > 0);

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
        return getObjectOfCorrespondingPrimitiveType(parser.getValueAsString(), oi.getTypeInfo());
      case VALUE_NULL:
        return null;
      default:
        throw new HiveException("unexpected token type: " + currentToken);
      }
    } finally {
      parser.nextToken();

    }
  }

  private static Object getObjectOfCorrespondingPrimitiveType(String s, PrimitiveTypeInfo mapKeyType)
      throws IOException {
    switch (mapKeyType.getPrimitiveCategory()) {
    case INT:
      return Integer.valueOf(s);
    case BYTE:
      return Byte.valueOf(s);
    case SHORT:
      return Short.valueOf(s);
    case LONG:
      return Long.valueOf(s);
    case BOOLEAN:
      return (s.equalsIgnoreCase("true"));
    case FLOAT:
      return Float.valueOf(s);
    case DOUBLE:
      return Double.valueOf(s);
    case STRING:
      return s;
    case BINARY:
      try {
        String t = Text.decode(s.getBytes(), 0, s.getBytes().length);
        return t.getBytes();
      } catch (CharacterCodingException e) {
        LOG.warn("Error generating json binary type from object.", e);
        return null;
      }
    case DATE:
      return Date.valueOf(s);
    case TIMESTAMP:
      return Timestamp.valueOf(s);
    case DECIMAL:
      return HiveDecimal.create(s);
    case VARCHAR:
      return new HiveVarchar(s, ((BaseCharTypeInfo) mapKeyType).getLength());
    case CHAR:
      return new HiveChar(s, ((BaseCharTypeInfo) mapKeyType).getLength());
    }
    throw new IOException("Could not convert from string to map type " + mapKeyType.getTypeName());
  }
  private static Object parseMapKey(JsonParser parser, PrimitiveObjectInspector oi) throws HiveException, IOException {
    JsonToken currentToken = parser.getCurrentToken();
    if (currentToken == null) {
      return null;
    }
    try {
      switch (parser.getCurrentToken()) {
      case FIELD_NAME:
        return getObjectOfCorrespondingPrimitiveType(parser.getValueAsString(), oi.getTypeInfo());
      case VALUE_NULL:
        return null;
      default:
        throw new HiveException("unexpected token type: " + currentToken);
      }
    } finally {
      parser.nextToken();

    }
  }

  public void setIgnoreUnknownFields(boolean b) {
    ignoreUnknownFields = b;
  }

}
