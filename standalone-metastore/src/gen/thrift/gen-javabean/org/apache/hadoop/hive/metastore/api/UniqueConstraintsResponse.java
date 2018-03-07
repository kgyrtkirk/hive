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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class UniqueConstraintsResponse implements org.apache.thrift.TBase<UniqueConstraintsResponse, UniqueConstraintsResponse._Fields>, java.io.Serializable, Cloneable, Comparable<UniqueConstraintsResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UniqueConstraintsResponse");

  private static final org.apache.thrift.protocol.TField UNIQUE_CONSTRAINTS_FIELD_DESC = new org.apache.thrift.protocol.TField("uniqueConstraints", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new UniqueConstraintsResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new UniqueConstraintsResponseTupleSchemeFactory());
  }

  private List<SQLUniqueConstraint> uniqueConstraints; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    UNIQUE_CONSTRAINTS((short)1, "uniqueConstraints");

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
        case 1: // UNIQUE_CONSTRAINTS
          return UNIQUE_CONSTRAINTS;
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
    tmpMap.put(_Fields.UNIQUE_CONSTRAINTS, new org.apache.thrift.meta_data.FieldMetaData("uniqueConstraints", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, SQLUniqueConstraint.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UniqueConstraintsResponse.class, metaDataMap);
  }

  public UniqueConstraintsResponse() {
  }

  public UniqueConstraintsResponse(
    List<SQLUniqueConstraint> uniqueConstraints)
  {
    this();
    this.uniqueConstraints = uniqueConstraints;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UniqueConstraintsResponse(UniqueConstraintsResponse other) {
    if (other.isSetUniqueConstraints()) {
      List<SQLUniqueConstraint> __this__uniqueConstraints = new ArrayList<SQLUniqueConstraint>(other.uniqueConstraints.size());
      for (SQLUniqueConstraint other_element : other.uniqueConstraints) {
        __this__uniqueConstraints.add(new SQLUniqueConstraint(other_element));
      }
      this.uniqueConstraints = __this__uniqueConstraints;
    }
  }

  public UniqueConstraintsResponse deepCopy() {
    return new UniqueConstraintsResponse(this);
  }

  @Override
  public void clear() {
    this.uniqueConstraints = null;
  }

  public int getUniqueConstraintsSize() {
    return (this.uniqueConstraints == null) ? 0 : this.uniqueConstraints.size();
  }

  public java.util.Iterator<SQLUniqueConstraint> getUniqueConstraintsIterator() {
    return (this.uniqueConstraints == null) ? null : this.uniqueConstraints.iterator();
  }

  public void addToUniqueConstraints(SQLUniqueConstraint elem) {
    if (this.uniqueConstraints == null) {
      this.uniqueConstraints = new ArrayList<SQLUniqueConstraint>();
    }
    this.uniqueConstraints.add(elem);
  }

  public List<SQLUniqueConstraint> getUniqueConstraints() {
    return this.uniqueConstraints;
  }

  public void setUniqueConstraints(List<SQLUniqueConstraint> uniqueConstraints) {
    this.uniqueConstraints = uniqueConstraints;
  }

  public void unsetUniqueConstraints() {
    this.uniqueConstraints = null;
  }

  /** Returns true if field uniqueConstraints is set (has been assigned a value) and false otherwise */
  public boolean isSetUniqueConstraints() {
    return this.uniqueConstraints != null;
  }

  public void setUniqueConstraintsIsSet(boolean value) {
    if (!value) {
      this.uniqueConstraints = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case UNIQUE_CONSTRAINTS:
      if (value == null) {
        unsetUniqueConstraints();
      } else {
        setUniqueConstraints((List<SQLUniqueConstraint>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case UNIQUE_CONSTRAINTS:
      return getUniqueConstraints();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case UNIQUE_CONSTRAINTS:
      return isSetUniqueConstraints();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof UniqueConstraintsResponse)
      return this.equals((UniqueConstraintsResponse)that);
    return false;
  }

  public boolean equals(UniqueConstraintsResponse that) {
    if (that == null)
      return false;

    boolean this_present_uniqueConstraints = true && this.isSetUniqueConstraints();
    boolean that_present_uniqueConstraints = true && that.isSetUniqueConstraints();
    if (this_present_uniqueConstraints || that_present_uniqueConstraints) {
      if (!(this_present_uniqueConstraints && that_present_uniqueConstraints))
        return false;
      if (!this.uniqueConstraints.equals(that.uniqueConstraints))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_uniqueConstraints = true && (isSetUniqueConstraints());
    list.add(present_uniqueConstraints);
    if (present_uniqueConstraints)
      list.add(uniqueConstraints);

    return list.hashCode();
  }

  @Override
  public int compareTo(UniqueConstraintsResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUniqueConstraints()).compareTo(other.isSetUniqueConstraints());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUniqueConstraints()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uniqueConstraints, other.uniqueConstraints);
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
    StringBuilder sb = new StringBuilder("UniqueConstraintsResponse(");
    boolean first = true;

    sb.append("uniqueConstraints:");
    if (this.uniqueConstraints == null) {
      sb.append("null");
    } else {
      sb.append(this.uniqueConstraints);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetUniqueConstraints()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uniqueConstraints' is unset! Struct:" + toString());
    }

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

  private static class UniqueConstraintsResponseStandardSchemeFactory implements SchemeFactory {
    public UniqueConstraintsResponseStandardScheme getScheme() {
      return new UniqueConstraintsResponseStandardScheme();
    }
  }

  private static class UniqueConstraintsResponseStandardScheme extends StandardScheme<UniqueConstraintsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UniqueConstraintsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // UNIQUE_CONSTRAINTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list328 = iprot.readListBegin();
                struct.uniqueConstraints = new ArrayList<SQLUniqueConstraint>(_list328.size);
                SQLUniqueConstraint _elem329;
                for (int _i330 = 0; _i330 < _list328.size; ++_i330)
                {
                  _elem329 = new SQLUniqueConstraint();
                  _elem329.read(iprot);
                  struct.uniqueConstraints.add(_elem329);
                }
                iprot.readListEnd();
              }
              struct.setUniqueConstraintsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, UniqueConstraintsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.uniqueConstraints != null) {
        oprot.writeFieldBegin(UNIQUE_CONSTRAINTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.uniqueConstraints.size()));
          for (SQLUniqueConstraint _iter331 : struct.uniqueConstraints)
          {
            _iter331.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UniqueConstraintsResponseTupleSchemeFactory implements SchemeFactory {
    public UniqueConstraintsResponseTupleScheme getScheme() {
      return new UniqueConstraintsResponseTupleScheme();
    }
  }

  private static class UniqueConstraintsResponseTupleScheme extends TupleScheme<UniqueConstraintsResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UniqueConstraintsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.uniqueConstraints.size());
        for (SQLUniqueConstraint _iter332 : struct.uniqueConstraints)
        {
          _iter332.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UniqueConstraintsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list333 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.uniqueConstraints = new ArrayList<SQLUniqueConstraint>(_list333.size);
        SQLUniqueConstraint _elem334;
        for (int _i335 = 0; _i335 < _list333.size; ++_i335)
        {
          _elem334 = new SQLUniqueConstraint();
          _elem334.read(iprot);
          struct.uniqueConstraints.add(_elem334);
        }
      }
      struct.setUniqueConstraintsIsSet(true);
    }
  }

}

