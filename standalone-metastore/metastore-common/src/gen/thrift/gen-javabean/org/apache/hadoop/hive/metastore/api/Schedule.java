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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class Schedule implements org.apache.thrift.TBase<Schedule, Schedule._Fields>, java.io.Serializable, Cloneable, Comparable<Schedule> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Schedule");

  private static final org.apache.thrift.protocol.TField CRON_FIELD_DESC = new org.apache.thrift.protocol.TField("cron", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ScheduleStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ScheduleTupleSchemeFactory());
  }

  private String cron; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CRON((short)1, "cron");

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
        case 1: // CRON
          return CRON;
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
  private static final _Fields optionals[] = {_Fields.CRON};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CRON, new org.apache.thrift.meta_data.FieldMetaData("cron", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Schedule.class, metaDataMap);
  }

  public Schedule() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Schedule(Schedule other) {
    if (other.isSetCron()) {
      this.cron = other.cron;
    }
  }

  public Schedule deepCopy() {
    return new Schedule(this);
  }

  @Override
  public void clear() {
    this.cron = null;
  }

  public String getCron() {
    return this.cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public void unsetCron() {
    this.cron = null;
  }

  /** Returns true if field cron is set (has been assigned a value) and false otherwise */
  public boolean isSetCron() {
    return this.cron != null;
  }

  public void setCronIsSet(boolean value) {
    if (!value) {
      this.cron = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CRON:
      if (value == null) {
        unsetCron();
      } else {
        setCron((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CRON:
      return getCron();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CRON:
      return isSetCron();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Schedule)
      return this.equals((Schedule)that);
    return false;
  }

  public boolean equals(Schedule that) {
    if (that == null)
      return false;

    boolean this_present_cron = true && this.isSetCron();
    boolean that_present_cron = true && that.isSetCron();
    if (this_present_cron || that_present_cron) {
      if (!(this_present_cron && that_present_cron))
        return false;
      if (!this.cron.equals(that.cron))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_cron = true && (isSetCron());
    list.add(present_cron);
    if (present_cron)
      list.add(cron);

    return list.hashCode();
  }

  @Override
  public int compareTo(Schedule other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCron()).compareTo(other.isSetCron());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCron()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cron, other.cron);
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
    StringBuilder sb = new StringBuilder("Schedule(");
    boolean first = true;

    if (isSetCron()) {
      sb.append("cron:");
      if (this.cron == null) {
        sb.append("null");
      } else {
        sb.append(this.cron);
      }
      first = false;
    }
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

  private static class ScheduleStandardSchemeFactory implements SchemeFactory {
    public ScheduleStandardScheme getScheme() {
      return new ScheduleStandardScheme();
    }
  }

  private static class ScheduleStandardScheme extends StandardScheme<Schedule> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Schedule struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CRON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.cron = iprot.readString();
              struct.setCronIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Schedule struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.cron != null) {
        if (struct.isSetCron()) {
          oprot.writeFieldBegin(CRON_FIELD_DESC);
          oprot.writeString(struct.cron);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScheduleTupleSchemeFactory implements SchemeFactory {
    public ScheduleTupleScheme getScheme() {
      return new ScheduleTupleScheme();
    }
  }

  private static class ScheduleTupleScheme extends TupleScheme<Schedule> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Schedule struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCron()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetCron()) {
        oprot.writeString(struct.cron);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Schedule struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.cron = iprot.readString();
        struct.setCronIsSet(true);
      }
    }
  }

}

