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
public class ColumnStatistics implements org.apache.thrift.TBase<ColumnStatistics, ColumnStatistics._Fields>, java.io.Serializable, Cloneable, Comparable<ColumnStatistics> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ColumnStatistics");

  private static final org.apache.thrift.protocol.TField STATS_DESC_FIELD_DESC = new org.apache.thrift.protocol.TField("statsDesc", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField STATS_OBJ_FIELD_DESC = new org.apache.thrift.protocol.TField("statsObj", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField IS_STATS_COMPLIANT_FIELD_DESC = new org.apache.thrift.protocol.TField("isStatsCompliant", org.apache.thrift.protocol.TType.BOOL, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ColumnStatisticsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ColumnStatisticsTupleSchemeFactory());
  }

  private ColumnStatisticsDesc statsDesc; // required
  private List<ColumnStatisticsObj> statsObj; // required
  private boolean isStatsCompliant; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STATS_DESC((short)1, "statsDesc"),
    STATS_OBJ((short)2, "statsObj"),
    IS_STATS_COMPLIANT((short)3, "isStatsCompliant");

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
        case 1: // STATS_DESC
          return STATS_DESC;
        case 2: // STATS_OBJ
          return STATS_OBJ;
        case 3: // IS_STATS_COMPLIANT
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
    tmpMap.put(_Fields.STATS_DESC, new org.apache.thrift.meta_data.FieldMetaData("statsDesc", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnStatisticsDesc.class)));
    tmpMap.put(_Fields.STATS_OBJ, new org.apache.thrift.meta_data.FieldMetaData("statsObj", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnStatisticsObj.class))));
    tmpMap.put(_Fields.IS_STATS_COMPLIANT, new org.apache.thrift.meta_data.FieldMetaData("isStatsCompliant", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ColumnStatistics.class, metaDataMap);
  }

  public ColumnStatistics() {
  }

  public ColumnStatistics(
    ColumnStatisticsDesc statsDesc,
    List<ColumnStatisticsObj> statsObj)
  {
    this();
    this.statsDesc = statsDesc;
    this.statsObj = statsObj;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ColumnStatistics(ColumnStatistics other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetStatsDesc()) {
      this.statsDesc = new ColumnStatisticsDesc(other.statsDesc);
    }
    if (other.isSetStatsObj()) {
      List<ColumnStatisticsObj> __this__statsObj = new ArrayList<ColumnStatisticsObj>(other.statsObj.size());
      for (ColumnStatisticsObj other_element : other.statsObj) {
        __this__statsObj.add(new ColumnStatisticsObj(other_element));
      }
      this.statsObj = __this__statsObj;
    }
    this.isStatsCompliant = other.isStatsCompliant;
  }

  public ColumnStatistics deepCopy() {
    return new ColumnStatistics(this);
  }

  @Override
  public void clear() {
    this.statsDesc = null;
    this.statsObj = null;
    setIsStatsCompliantIsSet(false);
    this.isStatsCompliant = false;
  }

  public ColumnStatisticsDesc getStatsDesc() {
    return this.statsDesc;
  }

  public void setStatsDesc(ColumnStatisticsDesc statsDesc) {
    this.statsDesc = statsDesc;
  }

  public void unsetStatsDesc() {
    this.statsDesc = null;
  }

  /** Returns true if field statsDesc is set (has been assigned a value) and false otherwise */
  public boolean isSetStatsDesc() {
    return this.statsDesc != null;
  }

  public void setStatsDescIsSet(boolean value) {
    if (!value) {
      this.statsDesc = null;
    }
  }

  public int getStatsObjSize() {
    return (this.statsObj == null) ? 0 : this.statsObj.size();
  }

  public java.util.Iterator<ColumnStatisticsObj> getStatsObjIterator() {
    return (this.statsObj == null) ? null : this.statsObj.iterator();
  }

  public void addToStatsObj(ColumnStatisticsObj elem) {
    if (this.statsObj == null) {
      this.statsObj = new ArrayList<ColumnStatisticsObj>();
    }
    this.statsObj.add(elem);
  }

  public List<ColumnStatisticsObj> getStatsObj() {
    return this.statsObj;
  }

  public void setStatsObj(List<ColumnStatisticsObj> statsObj) {
    this.statsObj = statsObj;
  }

  public void unsetStatsObj() {
    this.statsObj = null;
  }

  /** Returns true if field statsObj is set (has been assigned a value) and false otherwise */
  public boolean isSetStatsObj() {
    return this.statsObj != null;
  }

  public void setStatsObjIsSet(boolean value) {
    if (!value) {
      this.statsObj = null;
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
    case STATS_DESC:
      if (value == null) {
        unsetStatsDesc();
      } else {
        setStatsDesc((ColumnStatisticsDesc)value);
      }
      break;

    case STATS_OBJ:
      if (value == null) {
        unsetStatsObj();
      } else {
        setStatsObj((List<ColumnStatisticsObj>)value);
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
    case STATS_DESC:
      return getStatsDesc();

    case STATS_OBJ:
      return getStatsObj();

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
    case STATS_DESC:
      return isSetStatsDesc();
    case STATS_OBJ:
      return isSetStatsObj();
    case IS_STATS_COMPLIANT:
      return isSetIsStatsCompliant();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ColumnStatistics)
      return this.equals((ColumnStatistics)that);
    return false;
  }

  public boolean equals(ColumnStatistics that) {
    if (that == null)
      return false;

    boolean this_present_statsDesc = true && this.isSetStatsDesc();
    boolean that_present_statsDesc = true && that.isSetStatsDesc();
    if (this_present_statsDesc || that_present_statsDesc) {
      if (!(this_present_statsDesc && that_present_statsDesc))
        return false;
      if (!this.statsDesc.equals(that.statsDesc))
        return false;
    }

    boolean this_present_statsObj = true && this.isSetStatsObj();
    boolean that_present_statsObj = true && that.isSetStatsObj();
    if (this_present_statsObj || that_present_statsObj) {
      if (!(this_present_statsObj && that_present_statsObj))
        return false;
      if (!this.statsObj.equals(that.statsObj))
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

    boolean present_statsDesc = true && (isSetStatsDesc());
    list.add(present_statsDesc);
    if (present_statsDesc)
      list.add(statsDesc);

    boolean present_statsObj = true && (isSetStatsObj());
    list.add(present_statsObj);
    if (present_statsObj)
      list.add(statsObj);

    boolean present_isStatsCompliant = true && (isSetIsStatsCompliant());
    list.add(present_isStatsCompliant);
    if (present_isStatsCompliant)
      list.add(isStatsCompliant);

    return list.hashCode();
  }

  @Override
  public int compareTo(ColumnStatistics other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStatsDesc()).compareTo(other.isSetStatsDesc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatsDesc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.statsDesc, other.statsDesc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatsObj()).compareTo(other.isSetStatsObj());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatsObj()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.statsObj, other.statsObj);
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
    StringBuilder sb = new StringBuilder("ColumnStatistics(");
    boolean first = true;

    sb.append("statsDesc:");
    if (this.statsDesc == null) {
      sb.append("null");
    } else {
      sb.append(this.statsDesc);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("statsObj:");
    if (this.statsObj == null) {
      sb.append("null");
    } else {
      sb.append(this.statsObj);
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
    if (!isSetStatsDesc()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'statsDesc' is unset! Struct:" + toString());
    }

    if (!isSetStatsObj()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'statsObj' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
    if (statsDesc != null) {
      statsDesc.validate();
    }
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

  private static class ColumnStatisticsStandardSchemeFactory implements SchemeFactory {
    public ColumnStatisticsStandardScheme getScheme() {
      return new ColumnStatisticsStandardScheme();
    }
  }

  private static class ColumnStatisticsStandardScheme extends StandardScheme<ColumnStatistics> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ColumnStatistics struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STATS_DESC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.statsDesc = new ColumnStatisticsDesc();
              struct.statsDesc.read(iprot);
              struct.setStatsDescIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STATS_OBJ
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list276 = iprot.readListBegin();
                struct.statsObj = new ArrayList<ColumnStatisticsObj>(_list276.size);
                ColumnStatisticsObj _elem277;
                for (int _i278 = 0; _i278 < _list276.size; ++_i278)
                {
                  _elem277 = new ColumnStatisticsObj();
                  _elem277.read(iprot);
                  struct.statsObj.add(_elem277);
                }
                iprot.readListEnd();
              }
              struct.setStatsObjIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // IS_STATS_COMPLIANT
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ColumnStatistics struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.statsDesc != null) {
        oprot.writeFieldBegin(STATS_DESC_FIELD_DESC);
        struct.statsDesc.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.statsObj != null) {
        oprot.writeFieldBegin(STATS_OBJ_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.statsObj.size()));
          for (ColumnStatisticsObj _iter279 : struct.statsObj)
          {
            _iter279.write(oprot);
          }
          oprot.writeListEnd();
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

  private static class ColumnStatisticsTupleSchemeFactory implements SchemeFactory {
    public ColumnStatisticsTupleScheme getScheme() {
      return new ColumnStatisticsTupleScheme();
    }
  }

  private static class ColumnStatisticsTupleScheme extends TupleScheme<ColumnStatistics> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ColumnStatistics struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.statsDesc.write(oprot);
      {
        oprot.writeI32(struct.statsObj.size());
        for (ColumnStatisticsObj _iter280 : struct.statsObj)
        {
          _iter280.write(oprot);
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
    public void read(org.apache.thrift.protocol.TProtocol prot, ColumnStatistics struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.statsDesc = new ColumnStatisticsDesc();
      struct.statsDesc.read(iprot);
      struct.setStatsDescIsSet(true);
      {
        org.apache.thrift.protocol.TList _list281 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.statsObj = new ArrayList<ColumnStatisticsObj>(_list281.size);
        ColumnStatisticsObj _elem282;
        for (int _i283 = 0; _i283 < _list281.size; ++_i283)
        {
          _elem282 = new ColumnStatisticsObj();
          _elem282.read(iprot);
          struct.statsObj.add(_elem282);
        }
      }
      struct.setStatsObjIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.isStatsCompliant = iprot.readBool();
        struct.setIsStatsCompliantIsSet(true);
      }
    }
  }

}

