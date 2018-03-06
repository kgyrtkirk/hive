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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class PartitionsStatsResult implements org.apache.thrift.TBase<PartitionsStatsResult, PartitionsStatsResult._Fields>, java.io.Serializable, Cloneable, Comparable<PartitionsStatsResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PartitionsStatsResult");

  private static final org.apache.thrift.protocol.TField PART_STATS_FIELD_DESC = new org.apache.thrift.protocol.TField("partStats", org.apache.thrift.protocol.TType.MAP, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PartitionsStatsResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PartitionsStatsResultTupleSchemeFactory());
  }

  private Map<String,List<ColumnStatisticsObj>> partStats; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PART_STATS((short)1, "partStats");

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
    tmpMap.put(_Fields.PART_STATS, new org.apache.thrift.meta_data.FieldMetaData("partStats", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
                new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnStatisticsObj.class)))));
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
  }

  public PartitionsStatsResult deepCopy() {
    return new PartitionsStatsResult(this);
  }

  @Override
  public void clear() {
    this.partStats = null;
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PART_STATS:
      if (value == null) {
        unsetPartStats();
      } else {
        setPartStats((Map<String,List<ColumnStatisticsObj>>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PART_STATS:
      return getPartStats();

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

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_partStats = true && (isSetPartStats());
    list.add(present_partStats);
    if (present_partStats)
      list.add(partStats);

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
<<<<<<< HEAD
                org.apache.thrift.protocol.TMap _map392 = iprot.readMapBegin();
                struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map392.size);
                String _key393;
                List<ColumnStatisticsObj> _val394;
                for (int _i395 = 0; _i395 < _map392.size; ++_i395)
                {
                  _key393 = iprot.readString();
                  {
                    org.apache.thrift.protocol.TList _list396 = iprot.readListBegin();
                    _val394 = new ArrayList<ColumnStatisticsObj>(_list396.size);
                    ColumnStatisticsObj _elem397;
                    for (int _i398 = 0; _i398 < _list396.size; ++_i398)
                    {
                      _elem397 = new ColumnStatisticsObj();
                      _elem397.read(iprot);
                      _val394.add(_elem397);
                    }
                    iprot.readListEnd();
                  }
                  struct.partStats.put(_key393, _val394);
=======
                org.apache.thrift.protocol.TMap _map418 = iprot.readMapBegin();
                struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map418.size);
                String _key419;
                List<ColumnStatisticsObj> _val420;
                for (int _i421 = 0; _i421 < _map418.size; ++_i421)
                {
                  _key419 = iprot.readString();
                  {
                    org.apache.thrift.protocol.TList _list422 = iprot.readListBegin();
                    _val420 = new ArrayList<ColumnStatisticsObj>(_list422.size);
                    ColumnStatisticsObj _elem423;
                    for (int _i424 = 0; _i424 < _list422.size; ++_i424)
                    {
                      _elem423 = new ColumnStatisticsObj();
                      _elem423.read(iprot);
                      _val420.add(_elem423);
                    }
                    iprot.readListEnd();
                  }
                  struct.partStats.put(_key419, _val420);
>>>>>>> asf/master
                }
                iprot.readMapEnd();
              }
              struct.setPartStatsIsSet(true);
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
<<<<<<< HEAD
          for (Map.Entry<String, List<ColumnStatisticsObj>> _iter399 : struct.partStats.entrySet())
          {
            oprot.writeString(_iter399.getKey());
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter399.getValue().size()));
              for (ColumnStatisticsObj _iter400 : _iter399.getValue())
              {
                _iter400.write(oprot);
=======
          for (Map.Entry<String, List<ColumnStatisticsObj>> _iter425 : struct.partStats.entrySet())
          {
            oprot.writeString(_iter425.getKey());
            {
              oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, _iter425.getValue().size()));
              for (ColumnStatisticsObj _iter426 : _iter425.getValue())
              {
                _iter426.write(oprot);
>>>>>>> asf/master
              }
              oprot.writeListEnd();
            }
          }
          oprot.writeMapEnd();
        }
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
<<<<<<< HEAD
        for (Map.Entry<String, List<ColumnStatisticsObj>> _iter401 : struct.partStats.entrySet())
        {
          oprot.writeString(_iter401.getKey());
          {
            oprot.writeI32(_iter401.getValue().size());
            for (ColumnStatisticsObj _iter402 : _iter401.getValue())
            {
              _iter402.write(oprot);
=======
        for (Map.Entry<String, List<ColumnStatisticsObj>> _iter427 : struct.partStats.entrySet())
        {
          oprot.writeString(_iter427.getKey());
          {
            oprot.writeI32(_iter427.getValue().size());
            for (ColumnStatisticsObj _iter428 : _iter427.getValue())
            {
              _iter428.write(oprot);
>>>>>>> asf/master
            }
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PartitionsStatsResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
<<<<<<< HEAD
        org.apache.thrift.protocol.TMap _map403 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
        struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map403.size);
        String _key404;
        List<ColumnStatisticsObj> _val405;
        for (int _i406 = 0; _i406 < _map403.size; ++_i406)
        {
          _key404 = iprot.readString();
          {
            org.apache.thrift.protocol.TList _list407 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            _val405 = new ArrayList<ColumnStatisticsObj>(_list407.size);
            ColumnStatisticsObj _elem408;
            for (int _i409 = 0; _i409 < _list407.size; ++_i409)
            {
              _elem408 = new ColumnStatisticsObj();
              _elem408.read(iprot);
              _val405.add(_elem408);
            }
          }
          struct.partStats.put(_key404, _val405);
=======
        org.apache.thrift.protocol.TMap _map429 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
        struct.partStats = new HashMap<String,List<ColumnStatisticsObj>>(2*_map429.size);
        String _key430;
        List<ColumnStatisticsObj> _val431;
        for (int _i432 = 0; _i432 < _map429.size; ++_i432)
        {
          _key430 = iprot.readString();
          {
            org.apache.thrift.protocol.TList _list433 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            _val431 = new ArrayList<ColumnStatisticsObj>(_list433.size);
            ColumnStatisticsObj _elem434;
            for (int _i435 = 0; _i435 < _list433.size; ++_i435)
            {
              _elem434 = new ColumnStatisticsObj();
              _elem434.read(iprot);
              _val431.add(_elem434);
            }
          }
          struct.partStats.put(_key430, _val431);
>>>>>>> asf/master
        }
      }
      struct.setPartStatsIsSet(true);
    }
  }

}

