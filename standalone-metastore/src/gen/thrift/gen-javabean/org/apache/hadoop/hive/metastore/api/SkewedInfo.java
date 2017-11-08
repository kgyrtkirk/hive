/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class SkewedInfo implements org.apache.thrift.TBase<SkewedInfo, SkewedInfo._Fields>, java.io.Serializable, Cloneable, Comparable<SkewedInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SkewedInfo");

  private static final org.apache.thrift.protocol.TField SKEWED_COL_NAMES_FIELD_DESC = new org.apache.thrift.protocol.TField("skewedColNames", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField SKEWED_COL_VALUES_FIELD_DESC = new org.apache.thrift.protocol.TField("skewedColValues", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField SKEWED_COL_VALUE_LOCATION_MAPS_FIELD_DESC = new org.apache.thrift.protocol.TField("skewedColValueLocationMaps", org.apache.thrift.protocol.TType.MAP, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SkewedInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SkewedInfoTupleSchemeFactory());
  }

  private List<String> skewedColNames; // required
  private List<List<String>> skewedColValues; // required
  private Map<List<String>,String> skewedColValueLocationMaps; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SKEWED_COL_NAMES((short)1, "skewedColNames"),
    SKEWED_COL_VALUES((short)2, "skewedColValues"),
    SKEWED_COL_VALUE_LOCATION_MAPS((short)3, "skewedColValueLocationMaps");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SKEWED_COL_NAMES
          return SKEWED_COL_NAMES;
        case 2: // SKEWED_COL_VALUES
          return SKEWED_COL_VALUES;
        case 3: // SKEWED_COL_VALUE_LOCATION_MAPS
          return SKEWED_COL_VALUE_LOCATION_MAPS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SKEWED_COL_NAMES, new org.apache.thrift.meta_data.FieldMetaData("skewedColNames", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.SKEWED_COL_VALUES, new org.apache.thrift.meta_data.FieldMetaData("skewedColValues", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)))));
    tmpMap.put(_Fields.SKEWED_COL_VALUE_LOCATION_MAPS, new org.apache.thrift.meta_data.FieldMetaData("skewedColValueLocationMaps", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SkewedInfo.class, metaDataMap);
  }

  public SkewedInfo() {
  }

  public SkewedInfo(
    List<String> skewedColNames,
    List<List<String>> skewedColValues,
    Map<List<String>,String> skewedColValueLocationMaps)
  {
    this();
    this.skewedColNames = skewedColNames;
    this.skewedColValues = skewedColValues;
    this.skewedColValueLocationMaps = skewedColValueLocationMaps;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SkewedInfo(SkewedInfo other) {
    if (other.isSetSkewedColNames()) {
      List<String> __this__skewedColNames = new ArrayList<String>(other.skewedColNames);
      this.skewedColNames = __this__skewedColNames;
    }
    if (other.isSetSkewedColValues()) {
      List<List<String>> __this__skewedColValues = new ArrayList<List<String>>(other.skewedColValues.size());
      for (List<String> other_element : other.skewedColValues) {
        List<String> __this__skewedColValues_copy = new ArrayList<String>(other_element);
        __this__skewedColValues.add(__this__skewedColValues_copy);
      }
      this.skewedColValues = __this__skewedColValues;
    }
    if (other.isSetSkewedColValueLocationMaps()) {
      Map<List<String>,String> __this__skewedColValueLocationMaps = new HashMap<List<String>,String>(other.skewedColValueLocationMaps.size());
      for (Map.Entry<List<String>, String> other_element : other.skewedColValueLocationMaps.entrySet()) {

        List<String> other_element_key = other_element.getKey();
        String other_element_value = other_element.getValue();

        List<String> __this__skewedColValueLocationMaps_copy_key = new ArrayList<String>(other_element_key);

        String __this__skewedColValueLocationMaps_copy_value = other_element_value;

        __this__skewedColValueLocationMaps.put(__this__skewedColValueLocationMaps_copy_key, __this__skewedColValueLocationMaps_copy_value);
      }
      this.skewedColValueLocationMaps = __this__skewedColValueLocationMaps;
    }
  }

  public SkewedInfo deepCopy() {
    return new SkewedInfo(this);
  }

  @Override
  public void clear() {
    this.skewedColNames = null;
    this.skewedColValues = null;
    this.skewedColValueLocationMaps = null;
  }

  public int getSkewedColNamesSize() {
    return (this.skewedColNames == null) ? 0 : this.skewedColNames.size();
  }

  public java.util.Iterator<String> getSkewedColNamesIterator() {
    return (this.skewedColNames == null) ? null : this.skewedColNames.iterator();
  }

  public void addToSkewedColNames(String elem) {
    if (this.skewedColNames == null) {
      this.skewedColNames = new ArrayList<String>();
    }
    this.skewedColNames.add(elem);
  }

  public List<String> getSkewedColNames() {
    return this.skewedColNames;
  }

  public void setSkewedColNames(List<String> skewedColNames) {
    this.skewedColNames = skewedColNames;
  }

  public void unsetSkewedColNames() {
    this.skewedColNames = null;
  }

  /** Returns true if field skewedColNames is set (has been assigned a value) and false otherwise */
  public boolean isSetSkewedColNames() {
    return this.skewedColNames != null;
  }

  public void setSkewedColNamesIsSet(boolean value) {
    if (!value) {
      this.skewedColNames = null;
    }
  }

  public int getSkewedColValuesSize() {
    return (this.skewedColValues == null) ? 0 : this.skewedColValues.size();
  }

  public java.util.Iterator<List<String>> getSkewedColValuesIterator() {
    return (this.skewedColValues == null) ? null : this.skewedColValues.iterator();
  }

  public void addToSkewedColValues(List<String> elem) {
    if (this.skewedColValues == null) {
      this.skewedColValues = new ArrayList<List<String>>();
    }
    this.skewedColValues.add(elem);
  }

  public List<List<String>> getSkewedColValues() {
    return this.skewedColValues;
  }

  public void setSkewedColValues(List<List<String>> skewedColValues) {
    this.skewedColValues = skewedColValues;
  }

  public void unsetSkewedColValues() {
    this.skewedColValues = null;
  }

  /** Returns true if field skewedColValues is set (has been assigned a value) and false otherwise */
  public boolean isSetSkewedColValues() {
    return this.skewedColValues != null;
  }

  public void setSkewedColValuesIsSet(boolean value) {
    if (!value) {
      this.skewedColValues = null;
    }
  }

  public int getSkewedColValueLocationMapsSize() {
    return (this.skewedColValueLocationMaps == null) ? 0 : this.skewedColValueLocationMaps.size();
  }

  public void putToSkewedColValueLocationMaps(List<String> key, String val) {
    if (this.skewedColValueLocationMaps == null) {
      this.skewedColValueLocationMaps = new HashMap<List<String>,String>();
    }
    this.skewedColValueLocationMaps.put(key, val);
  }

  public Map<List<String>,String> getSkewedColValueLocationMaps() {
    return this.skewedColValueLocationMaps;
  }

  public void setSkewedColValueLocationMaps(Map<List<String>,String> skewedColValueLocationMaps) {
    this.skewedColValueLocationMaps = skewedColValueLocationMaps;
  }

  public void unsetSkewedColValueLocationMaps() {
    this.skewedColValueLocationMaps = null;
  }

  /** Returns true if field skewedColValueLocationMaps is set (has been assigned a value) and false otherwise */
  public boolean isSetSkewedColValueLocationMaps() {
    return this.skewedColValueLocationMaps != null;
  }

  public void setSkewedColValueLocationMapsIsSet(boolean value) {
    if (!value) {
      this.skewedColValueLocationMaps = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SKEWED_COL_NAMES:
      if (value == null) {
        unsetSkewedColNames();
      } else {
        setSkewedColNames((List<String>)value);
      }
      break;

    case SKEWED_COL_VALUES:
      if (value == null) {
        unsetSkewedColValues();
      } else {
        setSkewedColValues((List<List<String>>)value);
      }
      break;

    case SKEWED_COL_VALUE_LOCATION_MAPS:
      if (value == null) {
        unsetSkewedColValueLocationMaps();
      } else {
        setSkewedColValueLocationMaps((Map<List<String>,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SKEWED_COL_NAMES:
      return getSkewedColNames();

    case SKEWED_COL_VALUES:
      return getSkewedColValues();

    case SKEWED_COL_VALUE_LOCATION_MAPS:
      return getSkewedColValueLocationMaps();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SKEWED_COL_NAMES:
      return isSetSkewedColNames();
    case SKEWED_COL_VALUES:
      return isSetSkewedColValues();
    case SKEWED_COL_VALUE_LOCATION_MAPS:
      return isSetSkewedColValueLocationMaps();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SkewedInfo)
      return this.equals((SkewedInfo)that);
    return false;
  }

  public boolean equals(SkewedInfo that) {
    if (that == null)
      return false;

    boolean this_present_skewedColNames = true && this.isSetSkewedColNames();
    boolean that_present_skewedColNames = true && that.isSetSkewedColNames();
    if (this_present_skewedColNames || that_present_skewedColNames) {
      if (!(this_present_skewedColNames && that_present_skewedColNames))
        return false;
      if (!this.skewedColNames.equals(that.skewedColNames))
        return false;
    }

    boolean this_present_skewedColValues = true && this.isSetSkewedColValues();
    boolean that_present_skewedColValues = true && that.isSetSkewedColValues();
    if (this_present_skewedColValues || that_present_skewedColValues) {
      if (!(this_present_skewedColValues && that_present_skewedColValues))
        return false;
      if (!this.skewedColValues.equals(that.skewedColValues))
        return false;
    }

    boolean this_present_skewedColValueLocationMaps = true && this.isSetSkewedColValueLocationMaps();
    boolean that_present_skewedColValueLocationMaps = true && that.isSetSkewedColValueLocationMaps();
    if (this_present_skewedColValueLocationMaps || that_present_skewedColValueLocationMaps) {
      if (!(this_present_skewedColValueLocationMaps && that_present_skewedColValueLocationMaps))
        return false;
      if (!this.skewedColValueLocationMaps.equals(that.skewedColValueLocationMaps))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_skewedColNames = true && (isSetSkewedColNames());
    list.add(present_skewedColNames);
    if (present_skewedColNames)
      list.add(skewedColNames);

    boolean present_skewedColValues = true && (isSetSkewedColValues());
    list.add(present_skewedColValues);
    if (present_skewedColValues)
      list.add(skewedColValues);

    boolean present_skewedColValueLocationMaps = true && (isSetSkewedColValueLocationMaps());
    list.add(present_skewedColValueLocationMaps);
    if (present_skewedColValueLocationMaps)
      list.add(skewedColValueLocationMaps);

    return list.hashCode();
  }

  @Override
  public int compareTo(SkewedInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSkewedColNames()).compareTo(other.isSetSkewedColNames());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSkewedColNames()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.skewedColNames, other.skewedColNames);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSkewedColValues()).compareTo(other.isSetSkewedColValues());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSkewedColValues()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.skewedColValues, other.skewedColValues);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSkewedColValueLocationMaps()).compareTo(other.isSetSkewedColValueLocationMaps());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSkewedColValueLocationMaps()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.skewedColValueLocationMaps, other.skewedColValueLocationMaps);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SkewedInfo(");
    boolean first = true;

    sb.append("skewedColNames:");
    if (this.skewedColNames == null) {
      sb.append("null");
    } else {
      sb.append(this.skewedColNames);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("skewedColValues:");
    if (this.skewedColValues == null) {
      sb.append("null");
    } else {
      sb.append(this.skewedColValues);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("skewedColValueLocationMaps:");
    if (this.skewedColValueLocationMaps == null) {
      sb.append("null");
    } else {
      sb.append(this.skewedColValueLocationMaps);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SkewedInfoStandardSchemeFactory implements SchemeFactory {
    public SkewedInfoStandardScheme getScheme() {
      return new SkewedInfoStandardScheme();
    }
  }

  private static class SkewedInfoStandardScheme extends StandardScheme<SkewedInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SkewedInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SKEWED_COL_NAMES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list114 = iprot.readListBegin();
                struct.skewedColNames = new ArrayList<String>(_list114.size);
                String _elem115;
                for (int _i116 = 0; _i116 < _list114.size; ++_i116)
                {
                  _elem115 = iprot.readString();
                  struct.skewedColNames.add(_elem115);
                }
                iprot.readListEnd();
              }
              struct.setSkewedColNamesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SKEWED_COL_VALUES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list117 = iprot.readListBegin();
                struct.skewedColValues = new ArrayList<List<String>>(_list117.size);
                List<String> _elem118;
                for (int _i119 = 0; _i119 < _list117.size; ++_i119)
                {
                  {
                    org.apache.thrift.protocol.TList _list120 = iprot.readListBegin();
                    _elem118 = new ArrayList<String>(_list120.size);
                    String _elem121;
                    for (int _i122 = 0; _i122 < _list120.size; ++_i122)
                    {
                      _elem121 = iprot.readString();
                      _elem118.add(_elem121);
                    }
                    iprot.readListEnd();
                  }
                  struct.skewedColValues.add(_elem118);
                }
                iprot.readListEnd();
              }
              struct.setSkewedColValuesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SKEWED_COL_VALUE_LOCATION_MAPS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map123 = iprot.readMapBegin();
                struct.skewedColValueLocationMaps = new HashMap<List<String>,String>(2*_map123.size);
                List<String> _key124;
                String _val125;
                for (int _i126 = 0; _i126 < _map123.size; ++_i126)
                {
                  {
                    org.apache.thrift.protocol.TList _list127 = iprot.readListBegin();
                    _key124 = new ArrayList<String>(_list127.size);
                    String _elem128;
                    for (int _i129 = 0; _i129 < _list127.size; ++_i129)
                    {
                      _elem128 = iprot.readString();
                      _key124.add(_elem128);
                    }
                    iprot.readListEnd();
                  }
                  _val125 = iprot.readString();
                  struct.skewedColValueLocationMaps.put(_key124, _val125);
                }
                iprot.readMapEnd();
              }
              struct.setSkewedColValueLocationMapsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SkewedInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.skewedColNames != null) {
        oprot.writeFieldBegin(SKEWED_COL_NAMES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.skewedColNames.size()));
          for (String _iter130 : struct.skewedColNames)
          {
            oprot.writeString(_iter130);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.skewedColValues != null) {
        oprot.writeFieldBegin(SKEWED_COL_VALUES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, struct.skewedColValues.size()));
          for (List<String> _iter131 : struct.skewedColValues)
          {
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, _iter131.size()));
              for (String _iter132 : _iter131)
              {
                oprot.writeString(_iter132);
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.skewedColValueLocationMaps != null) {
        oprot.writeFieldBegin(SKEWED_COL_VALUE_LOCATION_MAPS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.LIST, org.apache.thrift.protocol.TType.STRING, struct.skewedColValueLocationMaps.size()));
          for (Map.Entry<List<String>, String> _iter133 : struct.skewedColValueLocationMaps.entrySet())
          {
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, _iter133.getKey().size()));
              for (String _iter134 : _iter133.getKey())
              {
                oprot.writeString(_iter134);
              }
              oprot.writeListEnd();
            }
            oprot.writeString(_iter133.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SkewedInfoTupleSchemeFactory implements SchemeFactory {
    public SkewedInfoTupleScheme getScheme() {
      return new SkewedInfoTupleScheme();
    }
  }

  private static class SkewedInfoTupleScheme extends TupleScheme<SkewedInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SkewedInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSkewedColNames()) {
        optionals.set(0);
      }
      if (struct.isSetSkewedColValues()) {
        optionals.set(1);
      }
      if (struct.isSetSkewedColValueLocationMaps()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetSkewedColNames()) {
        {
          oprot.writeI32(struct.skewedColNames.size());
          for (String _iter135 : struct.skewedColNames)
          {
            oprot.writeString(_iter135);
          }
        }
      }
      if (struct.isSetSkewedColValues()) {
        {
          oprot.writeI32(struct.skewedColValues.size());
          for (List<String> _iter136 : struct.skewedColValues)
          {
            {
              oprot.writeI32(_iter136.size());
              for (String _iter137 : _iter136)
              {
                oprot.writeString(_iter137);
              }
            }
          }
        }
      }
      if (struct.isSetSkewedColValueLocationMaps()) {
        {
          oprot.writeI32(struct.skewedColValueLocationMaps.size());
          for (Map.Entry<List<String>, String> _iter138 : struct.skewedColValueLocationMaps.entrySet())
          {
            {
              oprot.writeI32(_iter138.getKey().size());
              for (String _iter139 : _iter138.getKey())
              {
                oprot.writeString(_iter139);
              }
            }
            oprot.writeString(_iter138.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SkewedInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list140 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.skewedColNames = new ArrayList<String>(_list140.size);
          String _elem141;
          for (int _i142 = 0; _i142 < _list140.size; ++_i142)
          {
            _elem141 = iprot.readString();
            struct.skewedColNames.add(_elem141);
          }
        }
        struct.setSkewedColNamesIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list143 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.LIST, iprot.readI32());
          struct.skewedColValues = new ArrayList<List<String>>(_list143.size);
          List<String> _elem144;
          for (int _i145 = 0; _i145 < _list143.size; ++_i145)
          {
            {
              org.apache.thrift.protocol.TList _list146 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
              _elem144 = new ArrayList<String>(_list146.size);
              String _elem147;
              for (int _i148 = 0; _i148 < _list146.size; ++_i148)
              {
                _elem147 = iprot.readString();
                _elem144.add(_elem147);
              }
            }
            struct.skewedColValues.add(_elem144);
          }
        }
        struct.setSkewedColValuesIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TMap _map149 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.LIST, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.skewedColValueLocationMaps = new HashMap<List<String>,String>(2*_map149.size);
          List<String> _key150;
          String _val151;
          for (int _i152 = 0; _i152 < _map149.size; ++_i152)
          {
            {
              org.apache.thrift.protocol.TList _list153 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
              _key150 = new ArrayList<String>(_list153.size);
              String _elem154;
              for (int _i155 = 0; _i155 < _list153.size; ++_i155)
              {
                _elem154 = iprot.readString();
                _key150.add(_elem154);
              }
            }
            _val151 = iprot.readString();
            struct.skewedColValueLocationMaps.put(_key150, _val151);
          }
        }
        struct.setSkewedColValueLocationMapsIsSet(true);
      }
    }
  }

}

