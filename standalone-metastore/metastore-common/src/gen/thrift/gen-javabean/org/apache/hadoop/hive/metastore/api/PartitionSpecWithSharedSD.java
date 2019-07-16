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
public class PartitionSpecWithSharedSD implements org.apache.thrift.TBase<PartitionSpecWithSharedSD, PartitionSpecWithSharedSD._Fields>, java.io.Serializable, Cloneable, Comparable<PartitionSpecWithSharedSD> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PartitionSpecWithSharedSD");

  private static final org.apache.thrift.protocol.TField PARTITIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("partitions", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField SD_FIELD_DESC = new org.apache.thrift.protocol.TField("sd", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PartitionSpecWithSharedSDStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PartitionSpecWithSharedSDTupleSchemeFactory());
  }

  private List<PartitionWithoutSD> partitions; // required
  private StorageDescriptor sd; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PARTITIONS((short)1, "partitions"),
    SD((short)2, "sd");

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
        case 1: // PARTITIONS
          return PARTITIONS;
        case 2: // SD
          return SD;
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
    tmpMap.put(_Fields.PARTITIONS, new org.apache.thrift.meta_data.FieldMetaData("partitions", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PartitionWithoutSD.class))));
    tmpMap.put(_Fields.SD, new org.apache.thrift.meta_data.FieldMetaData("sd", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, StorageDescriptor.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PartitionSpecWithSharedSD.class, metaDataMap);
  }

  public PartitionSpecWithSharedSD() {
  }

  public PartitionSpecWithSharedSD(
    List<PartitionWithoutSD> partitions,
    StorageDescriptor sd)
  {
    this();
    this.partitions = partitions;
    this.sd = sd;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PartitionSpecWithSharedSD(PartitionSpecWithSharedSD other) {
    if (other.isSetPartitions()) {
      List<PartitionWithoutSD> __this__partitions = new ArrayList<PartitionWithoutSD>(other.partitions.size());
      for (PartitionWithoutSD other_element : other.partitions) {
        __this__partitions.add(new PartitionWithoutSD(other_element));
      }
      this.partitions = __this__partitions;
    }
    if (other.isSetSd()) {
      this.sd = new StorageDescriptor(other.sd);
    }
  }

  public PartitionSpecWithSharedSD deepCopy() {
    return new PartitionSpecWithSharedSD(this);
  }

  @Override
  public void clear() {
    this.partitions = null;
    this.sd = null;
  }

  public int getPartitionsSize() {
    return (this.partitions == null) ? 0 : this.partitions.size();
  }

  public java.util.Iterator<PartitionWithoutSD> getPartitionsIterator() {
    return (this.partitions == null) ? null : this.partitions.iterator();
  }

  public void addToPartitions(PartitionWithoutSD elem) {
    if (this.partitions == null) {
      this.partitions = new ArrayList<PartitionWithoutSD>();
    }
    this.partitions.add(elem);
  }

  public List<PartitionWithoutSD> getPartitions() {
    return this.partitions;
  }

  public void setPartitions(List<PartitionWithoutSD> partitions) {
    this.partitions = partitions;
  }

  public void unsetPartitions() {
    this.partitions = null;
  }

  /** Returns true if field partitions is set (has been assigned a value) and false otherwise */
  public boolean isSetPartitions() {
    return this.partitions != null;
  }

  public void setPartitionsIsSet(boolean value) {
    if (!value) {
      this.partitions = null;
    }
  }

  public StorageDescriptor getSd() {
    return this.sd;
  }

  public void setSd(StorageDescriptor sd) {
    this.sd = sd;
  }

  public void unsetSd() {
    this.sd = null;
  }

  /** Returns true if field sd is set (has been assigned a value) and false otherwise */
  public boolean isSetSd() {
    return this.sd != null;
  }

  public void setSdIsSet(boolean value) {
    if (!value) {
      this.sd = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PARTITIONS:
      if (value == null) {
        unsetPartitions();
      } else {
        setPartitions((List<PartitionWithoutSD>)value);
      }
      break;

    case SD:
      if (value == null) {
        unsetSd();
      } else {
        setSd((StorageDescriptor)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PARTITIONS:
      return getPartitions();

    case SD:
      return getSd();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PARTITIONS:
      return isSetPartitions();
    case SD:
      return isSetSd();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PartitionSpecWithSharedSD)
      return this.equals((PartitionSpecWithSharedSD)that);
    return false;
  }

  public boolean equals(PartitionSpecWithSharedSD that) {
    if (that == null)
      return false;

    boolean this_present_partitions = true && this.isSetPartitions();
    boolean that_present_partitions = true && that.isSetPartitions();
    if (this_present_partitions || that_present_partitions) {
      if (!(this_present_partitions && that_present_partitions))
        return false;
      if (!this.partitions.equals(that.partitions))
        return false;
    }

    boolean this_present_sd = true && this.isSetSd();
    boolean that_present_sd = true && that.isSetSd();
    if (this_present_sd || that_present_sd) {
      if (!(this_present_sd && that_present_sd))
        return false;
      if (!this.sd.equals(that.sd))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_partitions = true && (isSetPartitions());
    list.add(present_partitions);
    if (present_partitions)
      list.add(partitions);

    boolean present_sd = true && (isSetSd());
    list.add(present_sd);
    if (present_sd)
      list.add(sd);

    return list.hashCode();
  }

  @Override
  public int compareTo(PartitionSpecWithSharedSD other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPartitions()).compareTo(other.isSetPartitions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartitions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partitions, other.partitions);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSd()).compareTo(other.isSetSd());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSd()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sd, other.sd);
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
    StringBuilder sb = new StringBuilder("PartitionSpecWithSharedSD(");
    boolean first = true;

    sb.append("partitions:");
    if (this.partitions == null) {
      sb.append("null");
    } else {
      sb.append(this.partitions);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("sd:");
    if (this.sd == null) {
      sb.append("null");
    } else {
      sb.append(this.sd);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (sd != null) {
      sd.validate();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PartitionSpecWithSharedSDStandardSchemeFactory implements SchemeFactory {
    public PartitionSpecWithSharedSDStandardScheme getScheme() {
      return new PartitionSpecWithSharedSDStandardScheme();
    }
  }

  private static class PartitionSpecWithSharedSDStandardScheme extends StandardScheme<PartitionSpecWithSharedSD> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PartitionSpecWithSharedSD struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PARTITIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list268 = iprot.readListBegin();
                struct.partitions = new ArrayList<PartitionWithoutSD>(_list268.size);
                PartitionWithoutSD _elem269;
                for (int _i270 = 0; _i270 < _list268.size; ++_i270)
                {
                  _elem269 = new PartitionWithoutSD();
                  _elem269.read(iprot);
                  struct.partitions.add(_elem269);
                }
                iprot.readListEnd();
              }
              struct.setPartitionsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.sd = new StorageDescriptor();
              struct.sd.read(iprot);
              struct.setSdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PartitionSpecWithSharedSD struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.partitions != null) {
        oprot.writeFieldBegin(PARTITIONS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.partitions.size()));
          for (PartitionWithoutSD _iter271 : struct.partitions)
          {
            _iter271.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.sd != null) {
        oprot.writeFieldBegin(SD_FIELD_DESC);
        struct.sd.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PartitionSpecWithSharedSDTupleSchemeFactory implements SchemeFactory {
    public PartitionSpecWithSharedSDTupleScheme getScheme() {
      return new PartitionSpecWithSharedSDTupleScheme();
    }
  }

  private static class PartitionSpecWithSharedSDTupleScheme extends TupleScheme<PartitionSpecWithSharedSD> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PartitionSpecWithSharedSD struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPartitions()) {
        optionals.set(0);
      }
      if (struct.isSetSd()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetPartitions()) {
        {
          oprot.writeI32(struct.partitions.size());
          for (PartitionWithoutSD _iter272 : struct.partitions)
          {
            _iter272.write(oprot);
          }
        }
      }
      if (struct.isSetSd()) {
        struct.sd.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PartitionSpecWithSharedSD struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list273 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.partitions = new ArrayList<PartitionWithoutSD>(_list273.size);
          PartitionWithoutSD _elem274;
          for (int _i275 = 0; _i275 < _list273.size; ++_i275)
          {
            _elem274 = new PartitionWithoutSD();
            _elem274.read(iprot);
            struct.partitions.add(_elem274);
          }
        }
        struct.setPartitionsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.sd = new StorageDescriptor();
        struct.sd.read(iprot);
        struct.setSdIsSet(true);
      }
    }
  }

}

