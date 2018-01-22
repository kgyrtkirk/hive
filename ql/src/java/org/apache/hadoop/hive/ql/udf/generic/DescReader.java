
package org.apache.hadoop.hive.ql.udf.generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class DescReader {

  static class Col {

    private String name;
    private String type;

    public Col(String name, String type) {
      this.name = name;
      this.type = type;
    }

  }

  static class DRX {

    private List<Col> cols = new ArrayList<>();

    public void add(Col col) {
      cols.add(col);
    }

  }

  public static void main(String[] args) throws HiveException {
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\":5 , \"s\":[{\"x\":99}] }";

    TypeInfo t = TypeInfoUtils.getTypeInfoFromTypeString("struct<brand:string,doors:int,s:array<struct<x:string>>>");
    StructObjectInspector oi = (StructObjectInspector) TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(t);

    JsonFactory factory = new JsonFactory();
    JsonParser parser;
    try {
      //      ComplexObjectConstructor coc = new ComplexObjectConstructor(oi);
      parser = factory.createParser(carJson);

      while (!parser.isClosed()) {
        JsonToken jsonToken = parser.nextToken();
        //              if (jsonToken != null) {
        //                switch (jsonToken) {
        //                case START_OBJECT:
        //                  coc.startObject();
        //                }
        //              }
        System.out.println("jsonToken = " + jsonToken);
      }
      parser = factory.createParser(carJson);

      parser.nextToken();
      Object res = parseDispatcher(parser, oi);

    } catch (IOException e) {
      throw new HiveException("error parsing", e);
    }
    System.out.println(oi);

  }

  //  static class ComplexObjectConstructor {
  //
  //    private ObjectInspector rootOI;
  //    private ObjectInspector ois;
  //
  //    public ComplexObjectConstructor(ObjectInspector oi) {
  //      this.rootOI = oi;
  //    }
  //
  //    public void startObject() {
  //      if()
  //    }
  //
  //
  //  }
  //
  // probably forget the parser as an argument
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

    if (!parser.isExpectedStartObjectToken()) {
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
    if (!parser.isExpectedStartArrayToken()) {
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
      default:
        throw new HiveException("unexpected token type: " + currentToken);
      }
    } finally {
      parser.nextToken();

    }
  }

  public static void main1(String[] args) throws Exception {
    List<DRX> tables = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("/home/kirk/Downloads/DDL.txt"))) {
      String l;
      DRX drx = null;
      while ((l = br.readLine()) != null) {
        l = l.trim();
        if (l.startsWith("hive>")) {
          if (drx != null) {
            throw new RuntimeException("FX: " + l);
          }
          drx = new DRX();
          continue;
        }

        if (drx != null) {
          if (l.startsWith("# P") || l.startsWith("# D")) {
            tables.add(drx);
            drx = null;
          } else if (l.startsWith("#") || l.startsWith("O") || l.length() == 0) {
          } else {
            String[] parts = l.split("[ ]+");
            if (parts.length != 2) {
              throw new RuntimeException("EE: " + l);
            }
            drx.add(new Col(parts[0], parts[1]));
          }
        }
      }
    }

  }

}
