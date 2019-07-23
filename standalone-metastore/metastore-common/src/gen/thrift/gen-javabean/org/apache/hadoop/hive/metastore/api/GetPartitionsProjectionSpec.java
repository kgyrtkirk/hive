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
public class GetPartitionsProjectionSpec implements org.apache.thrift.TBase<GetPartitionsProjectionSpec, GetPartitionsProjectionSpec._Fields>, java.io.Serializable, Cloneable, Comparable<GetPartitionsProjectionSpec> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetPartitionsProjectionSpec");

  private static final org.apache.thrift.protocol.TField FIELD_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("fieldList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField INCLUDE_PARAM_KEY_PATTERN_FIELD_DESC = new org.apache.thrift.protocol.TField("includeParamKeyPattern", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXCLUDE_PARAM_KEY_PATTERN_FIELD_DESC = new org.apache.thrift.protocol.TField("excludeParamKeyPattern", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetPartitionsProjectionSpecStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetPartitionsProjectionSpecTupleSchemeFactory());
  }

  private List<String> fieldList; // required
  private String includeParamKeyPattern; // required
  private String excludeParamKeyPattern; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FIELD_LIST((short)1, "fieldList"),
    INCLUDE_PARAM_KEY_PATTERN((short)2, "includeParamKeyPattern"),
    EXCLUDE_PARAM_KEY_PATTERN((short)3, "excludeParamKeyPattern");

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
        case 1: // FIELD_LIST
          return FIELD_LIST;
        case 2: // INCLUDE_PARAM_KEY_PATTERN
          return INCLUDE_PARAM_KEY_PATTERN;
        case 3: // EXCLUDE_PARAM_KEY_PATTERN
          return EXCLUDE_PARAM_KEY_PATTERN;
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
    tmpMap.put(_Fields.FIELD_LIST, new org.apache.thrift.meta_data.FieldMetaData("fieldList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.INCLUDE_PARAM_KEY_PATTERN, new org.apache.thrift.meta_data.FieldMetaData("includeParamKeyPattern", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXCLUDE_PARAM_KEY_PATTERN, new org.apache.thrift.meta_data.FieldMetaData("excludeParamKeyPattern", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetPartitionsProjectionSpec.class, metaDataMap);
  }

  public GetPartitionsProjectionSpec() {
  }

  public GetPartitionsProjectionSpec(
    List<String> fieldList,
    String includeParamKeyPattern,
    String excludeParamKeyPattern)
  {
    this();
    this.fieldList = fieldList;
    this.includeParamKeyPattern = includeParamKeyPattern;
    this.excludeParamKeyPattern = excludeParamKeyPattern;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetPartitionsProjectionSpec(GetPartitionsProjectionSpec other) {
    if (other.isSetFieldList()) {
      List<String> __this__fieldList = new ArrayList<String>(other.fieldList);
      this.fieldList = __this__fieldList;
    }
    if (other.isSetIncludeParamKeyPattern()) {
      this.includeParamKeyPattern = other.includeParamKeyPattern;
    }
    if (other.isSetExcludeParamKeyPattern()) {
      this.excludeParamKeyPattern = other.excludeParamKeyPattern;
    }
  }

  public GetPartitionsProjectionSpec deepCopy() {
    return new GetPartitionsProjectionSpec(this);
  }

  @Override
  public void clear() {
    this.fieldList = null;
    this.includeParamKeyPattern = null;
    this.excludeParamKeyPattern = null;
  }

  public int getFieldListSize() {
    return (this.fieldList == null) ? 0 : this.fieldList.size();
  }

  public java.util.Iterator<String> getFieldListIterator() {
    return (this.fieldList == null) ? null : this.fieldList.iterator();
  }

  public void addToFieldList(String elem) {
    if (this.fieldList == null) {
      this.fieldList = new ArrayList<String>();
    }
    this.fieldList.add(elem);
  }

  public List<String> getFieldList() {
    return this.fieldList;
  }

  public void setFieldList(List<String> fieldList) {
    this.fieldList = fieldList;
  }

  public void unsetFieldList() {
    this.fieldList = null;
  }

  /** Returns true if field fieldList is set (has been assigned a value) and false otherwise */
  public boolean isSetFieldList() {
    return this.fieldList != null;
  }

  public void setFieldListIsSet(boolean value) {
    if (!value) {
      this.fieldList = null;
    }
  }

  public String getIncludeParamKeyPattern() {
    return this.includeParamKeyPattern;
  }

  public void setIncludeParamKeyPattern(String includeParamKeyPattern) {
    this.includeParamKeyPattern = includeParamKeyPattern;
  }

  public void unsetIncludeParamKeyPattern() {
    this.includeParamKeyPattern = null;
  }

  /** Returns true if field includeParamKeyPattern is set (has been assigned a value) and false otherwise */
  public boolean isSetIncludeParamKeyPattern() {
    return this.includeParamKeyPattern != null;
  }

  public void setIncludeParamKeyPatternIsSet(boolean value) {
    if (!value) {
      this.includeParamKeyPattern = null;
    }
  }

  public String getExcludeParamKeyPattern() {
    return this.excludeParamKeyPattern;
  }

  public void setExcludeParamKeyPattern(String excludeParamKeyPattern) {
    this.excludeParamKeyPattern = excludeParamKeyPattern;
  }

  public void unsetExcludeParamKeyPattern() {
    this.excludeParamKeyPattern = null;
  }

  /** Returns true if field excludeParamKeyPattern is set (has been assigned a value) and false otherwise */
  public boolean isSetExcludeParamKeyPattern() {
    return this.excludeParamKeyPattern != null;
  }

  public void setExcludeParamKeyPatternIsSet(boolean value) {
    if (!value) {
      this.excludeParamKeyPattern = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FIELD_LIST:
      if (value == null) {
        unsetFieldList();
      } else {
        setFieldList((List<String>)value);
      }
      break;

    case INCLUDE_PARAM_KEY_PATTERN:
      if (value == null) {
        unsetIncludeParamKeyPattern();
      } else {
        setIncludeParamKeyPattern((String)value);
      }
      break;

    case EXCLUDE_PARAM_KEY_PATTERN:
      if (value == null) {
        unsetExcludeParamKeyPattern();
      } else {
        setExcludeParamKeyPattern((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FIELD_LIST:
      return getFieldList();

    case INCLUDE_PARAM_KEY_PATTERN:
      return getIncludeParamKeyPattern();

    case EXCLUDE_PARAM_KEY_PATTERN:
      return getExcludeParamKeyPattern();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FIELD_LIST:
      return isSetFieldList();
    case INCLUDE_PARAM_KEY_PATTERN:
      return isSetIncludeParamKeyPattern();
    case EXCLUDE_PARAM_KEY_PATTERN:
      return isSetExcludeParamKeyPattern();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetPartitionsProjectionSpec)
      return this.equals((GetPartitionsProjectionSpec)that);
    return false;
  }

  public boolean equals(GetPartitionsProjectionSpec that) {
    if (that == null)
      return false;

    boolean this_present_fieldList = true && this.isSetFieldList();
    boolean that_present_fieldList = true && that.isSetFieldList();
    if (this_present_fieldList || that_present_fieldList) {
      if (!(this_present_fieldList && that_present_fieldList))
        return false;
      if (!this.fieldList.equals(that.fieldList))
        return false;
    }

    boolean this_present_includeParamKeyPattern = true && this.isSetIncludeParamKeyPattern();
    boolean that_present_includeParamKeyPattern = true && that.isSetIncludeParamKeyPattern();
    if (this_present_includeParamKeyPattern || that_present_includeParamKeyPattern) {
      if (!(this_present_includeParamKeyPattern && that_present_includeParamKeyPattern))
        return false;
      if (!this.includeParamKeyPattern.equals(that.includeParamKeyPattern))
        return false;
    }

    boolean this_present_excludeParamKeyPattern = true && this.isSetExcludeParamKeyPattern();
    boolean that_present_excludeParamKeyPattern = true && that.isSetExcludeParamKeyPattern();
    if (this_present_excludeParamKeyPattern || that_present_excludeParamKeyPattern) {
      if (!(this_present_excludeParamKeyPattern && that_present_excludeParamKeyPattern))
        return false;
      if (!this.excludeParamKeyPattern.equals(that.excludeParamKeyPattern))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_fieldList = true && (isSetFieldList());
    list.add(present_fieldList);
    if (present_fieldList)
      list.add(fieldList);

    boolean present_includeParamKeyPattern = true && (isSetIncludeParamKeyPattern());
    list.add(present_includeParamKeyPattern);
    if (present_includeParamKeyPattern)
      list.add(includeParamKeyPattern);

    boolean present_excludeParamKeyPattern = true && (isSetExcludeParamKeyPattern());
    list.add(present_excludeParamKeyPattern);
    if (present_excludeParamKeyPattern)
      list.add(excludeParamKeyPattern);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetPartitionsProjectionSpec other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFieldList()).compareTo(other.isSetFieldList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFieldList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fieldList, other.fieldList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIncludeParamKeyPattern()).compareTo(other.isSetIncludeParamKeyPattern());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIncludeParamKeyPattern()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.includeParamKeyPattern, other.includeParamKeyPattern);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExcludeParamKeyPattern()).compareTo(other.isSetExcludeParamKeyPattern());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExcludeParamKeyPattern()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.excludeParamKeyPattern, other.excludeParamKeyPattern);
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
    StringBuilder sb = new StringBuilder("GetPartitionsProjectionSpec(");
    boolean first = true;

    sb.append("fieldList:");
    if (this.fieldList == null) {
      sb.append("null");
    } else {
      sb.append(this.fieldList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("includeParamKeyPattern:");
    if (this.includeParamKeyPattern == null) {
      sb.append("null");
    } else {
      sb.append(this.includeParamKeyPattern);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("excludeParamKeyPattern:");
    if (this.excludeParamKeyPattern == null) {
      sb.append("null");
    } else {
      sb.append(this.excludeParamKeyPattern);
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

  private static class GetPartitionsProjectionSpecStandardSchemeFactory implements SchemeFactory {
    public GetPartitionsProjectionSpecStandardScheme getScheme() {
      return new GetPartitionsProjectionSpecStandardScheme();
    }
  }

  private static class GetPartitionsProjectionSpecStandardScheme extends StandardScheme<GetPartitionsProjectionSpec> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetPartitionsProjectionSpec struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FIELD_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list1040 = iprot.readListBegin();
                struct.fieldList = new ArrayList<String>(_list1040.size);
                String _elem1041;
                for (int _i1042 = 0; _i1042 < _list1040.size; ++_i1042)
                {
                  _elem1041 = iprot.readString();
                  struct.fieldList.add(_elem1041);
                }
                iprot.readListEnd();
              }
              struct.setFieldListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INCLUDE_PARAM_KEY_PATTERN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.includeParamKeyPattern = iprot.readString();
              struct.setIncludeParamKeyPatternIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXCLUDE_PARAM_KEY_PATTERN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.excludeParamKeyPattern = iprot.readString();
              struct.setExcludeParamKeyPatternIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetPartitionsProjectionSpec struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.fieldList != null) {
        oprot.writeFieldBegin(FIELD_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.fieldList.size()));
          for (String _iter1043 : struct.fieldList)
          {
            oprot.writeString(_iter1043);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.includeParamKeyPattern != null) {
        oprot.writeFieldBegin(INCLUDE_PARAM_KEY_PATTERN_FIELD_DESC);
        oprot.writeString(struct.includeParamKeyPattern);
        oprot.writeFieldEnd();
      }
      if (struct.excludeParamKeyPattern != null) {
        oprot.writeFieldBegin(EXCLUDE_PARAM_KEY_PATTERN_FIELD_DESC);
        oprot.writeString(struct.excludeParamKeyPattern);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetPartitionsProjectionSpecTupleSchemeFactory implements SchemeFactory {
    public GetPartitionsProjectionSpecTupleScheme getScheme() {
      return new GetPartitionsProjectionSpecTupleScheme();
    }
  }

  private static class GetPartitionsProjectionSpecTupleScheme extends TupleScheme<GetPartitionsProjectionSpec> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetPartitionsProjectionSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFieldList()) {
        optionals.set(0);
      }
      if (struct.isSetIncludeParamKeyPattern()) {
        optionals.set(1);
      }
      if (struct.isSetExcludeParamKeyPattern()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetFieldList()) {
        {
          oprot.writeI32(struct.fieldList.size());
          for (String _iter1044 : struct.fieldList)
          {
            oprot.writeString(_iter1044);
          }
        }
      }
      if (struct.isSetIncludeParamKeyPattern()) {
        oprot.writeString(struct.includeParamKeyPattern);
      }
      if (struct.isSetExcludeParamKeyPattern()) {
        oprot.writeString(struct.excludeParamKeyPattern);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetPartitionsProjectionSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list1045 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.fieldList = new ArrayList<String>(_list1045.size);
          String _elem1046;
          for (int _i1047 = 0; _i1047 < _list1045.size; ++_i1047)
          {
            _elem1046 = iprot.readString();
            struct.fieldList.add(_elem1046);
          }
        }
        struct.setFieldListIsSet(true);
      }
      if (incoming.get(1)) {
        struct.includeParamKeyPattern = iprot.readString();
        struct.setIncludeParamKeyPatternIsSet(true);
      }
      if (incoming.get(2)) {
        struct.excludeParamKeyPattern = iprot.readString();
        struct.setExcludeParamKeyPatternIsSet(true);
      }
    }
  }

}

