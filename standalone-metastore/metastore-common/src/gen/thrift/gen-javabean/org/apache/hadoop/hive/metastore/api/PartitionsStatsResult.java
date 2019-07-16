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
public class PartitionsStatsResult implements org.apache.thrift.TBase<PartitionsStatsResult, PartitionsStatsResult._Fields>, java.io.Serializable, Cloneable, Comparable<PartitionsStatsResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PartitionsStatsResult");

  private static final org.apache.thrift.protocol.TField PART_STATS_FIELD_DESC = new org.apache.thrift.protocol.TField("partStats", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField IS_STATS_COMPLIANT_FIELD_DESC = new org.apache.thrift.protocol.TField("isStatsCompliant", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PartitionsStatsResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PartitionsStatsResultTupleSchemeFactory());
  }

  private Map<String,List<ColumnStatisticsObj>> partStats; // required
  private boolean isStatsCompliant; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PART_STATS((short)1, "partStats"),
    IS_STATS_COMPLIANT((short)2, "isStatsCompliant");

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
        case 1: // PART_STATS
          return PART_STATS;
        case 2: // IS_STATS_COMPLIANT
          return IS_STATS_COMPLIANT;
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
  private static final int __ISSTATSCOMPLIANT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.IS_STATS_COMPLIANT};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PART_STATS, new org.apache.thrift.meta_data.FieldMetaData("partStats", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnStatisticsObj.class)))));
    tmpMap.put(_Fields.IS_STATS_COMPLIANT, new org.apache.thrift.meta_data.FieldMetaData("isStatsCompliant", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PartitionsStatsResult.class, metaDataMap);
  }

  public PartitionsStatsResult() {
  }

  public PartitionsStatsResult(
    Map<String,List<ColumnStatisticsObj>> partStats)
  {
    this();
    this.partStats = partStats;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PartitionsStatsResult(PartitionsStatsResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetPartStats()) {
      Map<String,List<ColumnStatisticsObj>> __this__partStats = new HashMap<String,List<ColumnStatisticsObj>>(other.partStats.size());
      for (Map.Entry<String, List<ColumnStatisticsObj>> other_element : other.partStats.entrySet()) {

        String other_element_key = other_element.getKey();
        List<ColumnStatisticsObj> other_element_value = other_element.getValue();

        String __this__partStats_copy_key = other_element_key;

        List<ColumnStatisticsObj> __this__partStats_copy_value = new ArrayList<ColumnStatisticsObj>(other_element_value.size());
        for (ColumnStatisticsObj other_element_value_element : other_element_value) {
          __this__partStats_copy_value.add(new ColumnStatisticsObj(other_element_value_element));
        }

        __this__partStats.put(__this__partStats_copy_key, __this__partStats_copy_value);
      }
      this.partStats = __this__partStats;
    }
    this.isStatsCompliant = other.isStatsCompliant;
  }

  public PartitionsStatsResult deepCopy() {
    return new PartitionsStatsResult(this);
  }

  @Override
  public void clear() {
    this.partStats = null;
    setIsStatsCompliantIsSet(false);
    this.isStatsCompliant = false;
  }

  public int getPartStatsSize() {
    return (this.partStats == null) ? 0 : this.partStats.size();
  }

  public void putToPartStats(String key, List<ColumnStatisticsObj> val) {
    if (this.partStats == null) {
      this.partStats = new HashMap<String,List<ColumnStatisticsObj>>();
    }
    this.partStats.put(key, val);
  }

  public Map<String,List<ColumnStatisticsObj>> getPartStats() {
    return this.partStats;
  }

  public void setPartStats(Map<String,List<ColumnStatisticsObj>> partStats) {
    this.partStats = partStats;
  }

  public void unsetPartStats() {
    this.partStats = null;
  }

  /** Returns true if field partStats is set (has been assigned a value) and false otherwise */
  public boolean isSetPartStats() {
    return this.partStats != null;
  }

  public void setPartStatsIsSet(boolean value) {
    if (!value) {
      this.partStats = null;
    }
  }

  public boolean isIsStatsCompliant() {
    return this.isStatsCompliant;
  }

  public void setIsStatsCompliant(boolean isStatsCompliant) {
    this.isStatsCompliant = isStatsCompliant;
    setIsStatsCompliantIsSet(true);
  }

  public void unsetIsStatsCompliant() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISSTATSCOMPLIANT_ISSET_ID);
  }

  /** Returns true if field isStatsCompliant is set (has been assigned a value) and false otherwise */
  public boolean isSetIsStatsCompliant() {
    return EncodingUtils.testBit(__isset_bitfield, __ISSTATSCOMPLIANT_ISSET_ID);
  }

  public void setIsStatsCompliantIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISSTATSCOMPLIANT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PART_STATS:
      if (value == null) {
        unsetPartStats();
      } else {
        setPartStats((Map<String,List<ColumnStatisticsObj>>)value);
      }
      break;

    case IS_STATS_COMPLIANT:
      if (value == null) {
        unsetIsStatsCompliant();
      } else {
        setIsStatsCompliant((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PART_STATS:
      return getPartStats();

    case IS_STATS_COMPLIANT:
      return isIsStatsCompliant();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PART_STATS:
      return isSetPartStats();
    case IS_STATS_COMPLIANT:
      return isSetIsStatsCompliant();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PartitionsStatsResult)
      return this.equals((PartitionsStatsResult)that);
    return false;
  }

  public boolean equals(PartitionsStatsResult that) {
    if (that == null)
      return false;

    boolean this_present_partStats = true && this.isSetPartStats();
    boolean that_present_partStats = true && that.isSetPartStats();
    if (this_present_partStats || that_present_partStats) {
      if (!(this_present_partStats && that_present_partStats))
        return false;
      if (!this.partStats.equals(that.partStats))
        return false;
    }

    boolean this_present_isStatsCompliant = true && this.isSetIsStatsCompliant();
    boolean that_present_isStatsCompliant = true && that.isSetIsStatsCompliant();
    if (this_present_isStatsCompliant || that_present_isStatsCompliant) {
      if (!(this_present_isStatsCompliant && that_present_isStatsCompliant))
        return false;
      if (this.isStatsCompliant != that.isStatsCompliant)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_partStats = true && (isSetPartStats());
    list.add(present_partStats);
    if (present_partStats)
      list.add(partStats);

    boolean present_isStatsCompliant = true && (isSetIsStatsCompliant());
    list.add(present_isStatsCompliant);
    if (present_isStatsCompliant)
      list.add(isStatsCompliant);

    return list.hashCode();
  }

  @Override
  public int compareTo(PartitionsStatsResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPartStats()).compareTo(other.isSetPartStats());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartStats()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partStats, other.partStats);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsStatsCompliant()).compareTo(other.isSetIsStatsCompliant());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsStatsCompliant()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isStatsCompliant, other.isStatsCompliant);
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
    StringBuilder sb = new StringBuilder("PartitionsStatsResult(");
    boolean first = true;

    sb.append("partStats:");
    if (this.partStats == null) {
      sb.append("null");
    } else {
      sb.append(this.partStats);
    }
    first = false;
    if (isSetIsStatsCompliant()) {
      if (!first) sb.append(", ");
      sb.append("isStatsCompliant:");
      sb.append(this.isStatsCompliant);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetPartStats()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'partStats' is unset! Struct:" + toString());
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PartitionsStatsResultStandardSchemeFactory implements SchemeFactory {
    public PartitionsStatsResultStandardScheme getScheme() {
      return new PartitionsStatsResultStandardScheme();
    }
  }

  private static class PartitionsStatsResultStandardScheme extends StandardScheme<PartitionsStatsResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PartitionsStatsResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PART_STATS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map448 = iprot.readMapBegin();
                struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map448.size);
                String _key449;
                List<ColumnStatisticsObj> _val450;
                for (int _i451 = 0; _i451 < _map448.size; ++_i451)
                {
                  _key449 = iprot.readString();
                  {
                    org.apache.thrift.protocol.TList _list452 = iprot.readListBegin();
                    _val450 = new ArrayList<ColumnStatisticsObj>(_list452.size);
                    ColumnStatisticsObj _elem453;
                    for (int _i454 = 0; _i454 < _list452.size; ++_i454)
                    {
                      _elem453 = new ColumnStatisticsObj();
                      _elem453.read(iprot);
                      _val450.add(_elem453);
                    }
                    iprot.readListEnd();
                  }
                  struct.partStats.put(_key449, _val450);
                }
                iprot.readMapEnd();
              }
              struct.setPartStatsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IS_STATS_COMPLIANT
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isStatsCompliant = iprot.readBool();
              struct.setIsStatsCompliantIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PartitionsStatsResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.partStats != null) {
        oprot.writeFieldBegin(PART_STATS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, struct.partStats.size()));
          for (Map.Entry<String, List<ColumnStatisticsObj>> _iter455 : struct.partStats.entrySet())
          {
            oprot.writeString(_iter455.getKey());
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter455.getValue().size()));
              for (ColumnStatisticsObj _iter456 : _iter455.getValue())
              {
                _iter456.write(oprot);
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.isSetIsStatsCompliant()) {
        oprot.writeFieldBegin(IS_STATS_COMPLIANT_FIELD_DESC);
        oprot.writeBool(struct.isStatsCompliant);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PartitionsStatsResultTupleSchemeFactory implements SchemeFactory {
    public PartitionsStatsResultTupleScheme getScheme() {
      return new PartitionsStatsResultTupleScheme();
    }
  }

  private static class PartitionsStatsResultTupleScheme extends TupleScheme<PartitionsStatsResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PartitionsStatsResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.partStats.size());
        for (Map.Entry<String, List<ColumnStatisticsObj>> _iter457 : struct.partStats.entrySet())
        {
          oprot.writeString(_iter457.getKey());
          {
            oprot.writeI32(_iter457.getValue().size());
            for (ColumnStatisticsObj _iter458 : _iter457.getValue())
            {
              _iter458.write(oprot);
            }
          }
        }
      }
      BitSet optionals = new BitSet();
      if (struct.isSetIsStatsCompliant()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetIsStatsCompliant()) {
        oprot.writeBool(struct.isStatsCompliant);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PartitionsStatsResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TMap _map459 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
        struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map459.size);
        String _key460;
        List<ColumnStatisticsObj> _val461;
        for (int _i462 = 0; _i462 < _map459.size; ++_i462)
        {
          _key460 = iprot.readString();
          {
            org.apache.thrift.protocol.TList _list463 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            _val461 = new ArrayList<ColumnStatisticsObj>(_list463.size);
            ColumnStatisticsObj _elem464;
            for (int _i465 = 0; _i465 < _list463.size; ++_i465)
            {
              _elem464 = new ColumnStatisticsObj();
              _elem464.read(iprot);
              _val461.add(_elem464);
            }
          }
          struct.partStats.put(_key460, _val461);
        }
      }
      struct.setPartStatsIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.isStatsCompliant = iprot.readBool();
        struct.setIsStatsCompliantIsSet(true);
      }
    }
  }

}

