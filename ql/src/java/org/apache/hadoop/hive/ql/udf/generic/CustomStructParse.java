
package org.apache.hadoop.hive.ql.udf.generic;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorConverter.TextConverter;

@Description(name = "custom_struct_parse")
public class CustomStructParse extends GenericUDF {

  private PrimitiveObjectInspector inputOI;
  private ObjectInspector outputOI;
  private PrimitiveObjectInspector argumentOI;
  private Converter inputConverter;

  static class PayLoadClass {
    public String product_held_identifier;
    // NOTE: originally was timestamp
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

    // was not in the original schem
    public String expiry_date;

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

    inputConverter = new TextConverter(argumentOI);

    outputOI = buildOutputOI2();
    return outputOI;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Object valObject = arguments[0].get();
    if (valObject == null) {
      return null;
    }
    String stringInput = ((Text) inputConverter.convert(valObject)).toString();

    ObjectMapper mapper = new ObjectMapper();
    try {
      PayLoadClass[] myObjects = mapper.readValue(stringInput, PayLoadClass[].class);
      ArrayList ret = new ArrayList<>();
      for (PayLoadClass payLoadClass : myObjects) {
        ret.add(flattenStruct(payLoadClass));
      }

      return ret;
    } catch (IOException e) {
      throw new RuntimeException("error running custom struct handler",e);
    }

  }


  @Override
  public String getDisplayString(String[] children) {
    return getStandardDisplayString("custom_struct_parse", children);
  }

  public ObjectInspector buildOutputOI2() {

    ArrayList<String> fname = new ArrayList<String>();
    ArrayList<ObjectInspector> foi = new ArrayList<ObjectInspector>();

    Field[] fields = PayLoadClass.class.getFields();
    for (Field field : fields) {
      fname.add(field.getName());
      foi.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
    }
    StandardStructObjectInspector sOI = ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);
    StandardListObjectInspector listOI = ObjectInspectorFactory.getStandardListObjectInspector(sOI);

    return listOI;
  }

  public Object flattenStruct(PayLoadClass pc) {
    Field[] fields = PayLoadClass.class.getFields();
    Object[] ret = new Object[fields.length];

    for (int i = 0; i < fields.length; i++) {
      Object val;
      try {
        val = fields[i].get(pc);
      } catch (IllegalArgumentException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      if (val == null) {
        ret[i]=null;
      } else {
        ret[i] = new Text((String) val);
      }
    }
    return ret;
  }

}