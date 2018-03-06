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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class GetOpenTxnsResponse implements org.apache.thrift.TBase<GetOpenTxnsResponse, GetOpenTxnsResponse._Fields>, java.io.Serializable, Cloneable, Comparable<GetOpenTxnsResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetOpenTxnsResponse");

  private static final org.apache.thrift.protocol.TField TXN_HIGH_WATER_MARK_FIELD_DESC = new org.apache.thrift.protocol.TField("txn_high_water_mark", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField OPEN_TXNS_FIELD_DESC = new org.apache.thrift.protocol.TField("open_txns", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField MIN_OPEN_TXN_FIELD_DESC = new org.apache.thrift.protocol.TField("min_open_txn", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField ABORTED_BITS_FIELD_DESC = new org.apache.thrift.protocol.TField("abortedBits", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetOpenTxnsResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetOpenTxnsResponseTupleSchemeFactory());
  }

  private long txn_high_water_mark; // required
  private List<Long> open_txns; // required
  private long min_open_txn; // optional
  private ByteBuffer abortedBits; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TXN_HIGH_WATER_MARK((short)1, "txn_high_water_mark"),
    OPEN_TXNS((short)2, "open_txns"),
    MIN_OPEN_TXN((short)3, "min_open_txn"),
    ABORTED_BITS((short)4, "abortedBits");

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
        case 1: // TXN_HIGH_WATER_MARK
          return TXN_HIGH_WATER_MARK;
        case 2: // OPEN_TXNS
          return OPEN_TXNS;
        case 3: // MIN_OPEN_TXN
          return MIN_OPEN_TXN;
        case 4: // ABORTED_BITS
          return ABORTED_BITS;
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
  private static final int __TXN_HIGH_WATER_MARK_ISSET_ID = 0;
  private static final int __MIN_OPEN_TXN_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.MIN_OPEN_TXN};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TXN_HIGH_WATER_MARK, new org.apache.thrift.meta_data.FieldMetaData("txn_high_water_mark", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.OPEN_TXNS, new org.apache.thrift.meta_data.FieldMetaData("open_txns", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.MIN_OPEN_TXN, new org.apache.thrift.meta_data.FieldMetaData("min_open_txn", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ABORTED_BITS, new org.apache.thrift.meta_data.FieldMetaData("abortedBits", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetOpenTxnsResponse.class, metaDataMap);
  }

  public GetOpenTxnsResponse() {
  }

  public GetOpenTxnsResponse(
    long txn_high_water_mark,
    List<Long> open_txns,
    ByteBuffer abortedBits)
  {
    this();
    this.txn_high_water_mark = txn_high_water_mark;
    setTxn_high_water_markIsSet(true);
    this.open_txns = open_txns;
    this.abortedBits = org.apache.thrift.TBaseHelper.copyBinary(abortedBits);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetOpenTxnsResponse(GetOpenTxnsResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.txn_high_water_mark = other.txn_high_water_mark;
    if (other.isSetOpen_txns()) {
      List<Long> __this__open_txns = new ArrayList<Long>(other.open_txns);
      this.open_txns = __this__open_txns;
    }
    this.min_open_txn = other.min_open_txn;
    if (other.isSetAbortedBits()) {
      this.abortedBits = org.apache.thrift.TBaseHelper.copyBinary(other.abortedBits);
    }
  }

  public GetOpenTxnsResponse deepCopy() {
    return new GetOpenTxnsResponse(this);
  }

  @Override
  public void clear() {
    setTxn_high_water_markIsSet(false);
    this.txn_high_water_mark = 0;
    this.open_txns = null;
    setMin_open_txnIsSet(false);
    this.min_open_txn = 0;
    this.abortedBits = null;
  }

  public long getTxn_high_water_mark() {
    return this.txn_high_water_mark;
  }

  public void setTxn_high_water_mark(long txn_high_water_mark) {
    this.txn_high_water_mark = txn_high_water_mark;
    setTxn_high_water_markIsSet(true);
  }

  public void unsetTxn_high_water_mark() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TXN_HIGH_WATER_MARK_ISSET_ID);
  }

  /** Returns true if field txn_high_water_mark is set (has been assigned a value) and false otherwise */
  public boolean isSetTxn_high_water_mark() {
    return EncodingUtils.testBit(__isset_bitfield, __TXN_HIGH_WATER_MARK_ISSET_ID);
  }

  public void setTxn_high_water_markIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TXN_HIGH_WATER_MARK_ISSET_ID, value);
  }

  public int getOpen_txnsSize() {
    return (this.open_txns == null) ? 0 : this.open_txns.size();
  }

  public java.util.Iterator<Long> getOpen_txnsIterator() {
    return (this.open_txns == null) ? null : this.open_txns.iterator();
  }

  public void addToOpen_txns(long elem) {
    if (this.open_txns == null) {
      this.open_txns = new ArrayList<Long>();
    }
    this.open_txns.add(elem);
  }

  public List<Long> getOpen_txns() {
    return this.open_txns;
  }

  public void setOpen_txns(List<Long> open_txns) {
    this.open_txns = open_txns;
  }

  public void unsetOpen_txns() {
    this.open_txns = null;
  }

  /** Returns true if field open_txns is set (has been assigned a value) and false otherwise */
  public boolean isSetOpen_txns() {
    return this.open_txns != null;
  }

  public void setOpen_txnsIsSet(boolean value) {
    if (!value) {
      this.open_txns = null;
    }
  }

  public long getMin_open_txn() {
    return this.min_open_txn;
  }

  public void setMin_open_txn(long min_open_txn) {
    this.min_open_txn = min_open_txn;
    setMin_open_txnIsSet(true);
  }

  public void unsetMin_open_txn() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MIN_OPEN_TXN_ISSET_ID);
  }

  /** Returns true if field min_open_txn is set (has been assigned a value) and false otherwise */
  public boolean isSetMin_open_txn() {
    return EncodingUtils.testBit(__isset_bitfield, __MIN_OPEN_TXN_ISSET_ID);
  }

  public void setMin_open_txnIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MIN_OPEN_TXN_ISSET_ID, value);
  }

  public byte[] getAbortedBits() {
    setAbortedBits(org.apache.thrift.TBaseHelper.rightSize(abortedBits));
    return abortedBits == null ? null : abortedBits.array();
  }

  public ByteBuffer bufferForAbortedBits() {
    return org.apache.thrift.TBaseHelper.copyBinary(abortedBits);
  }

  public void setAbortedBits(byte[] abortedBits) {
    this.abortedBits = abortedBits == null ? (ByteBuffer)null : ByteBuffer.wrap(Arrays.copyOf(abortedBits, abortedBits.length));
  }

  public void setAbortedBits(ByteBuffer abortedBits) {
    this.abortedBits = org.apache.thrift.TBaseHelper.copyBinary(abortedBits);
  }

  public void unsetAbortedBits() {
    this.abortedBits = null;
  }

  /** Returns true if field abortedBits is set (has been assigned a value) and false otherwise */
  public boolean isSetAbortedBits() {
    return this.abortedBits != null;
  }

  public void setAbortedBitsIsSet(boolean value) {
    if (!value) {
      this.abortedBits = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TXN_HIGH_WATER_MARK:
      if (value == null) {
        unsetTxn_high_water_mark();
      } else {
        setTxn_high_water_mark((Long)value);
      }
      break;

    case OPEN_TXNS:
      if (value == null) {
        unsetOpen_txns();
      } else {
        setOpen_txns((List<Long>)value);
      }
      break;

    case MIN_OPEN_TXN:
      if (value == null) {
        unsetMin_open_txn();
      } else {
        setMin_open_txn((Long)value);
      }
      break;

    case ABORTED_BITS:
      if (value == null) {
        unsetAbortedBits();
      } else {
        setAbortedBits((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TXN_HIGH_WATER_MARK:
      return getTxn_high_water_mark();

    case OPEN_TXNS:
      return getOpen_txns();

    case MIN_OPEN_TXN:
      return getMin_open_txn();

    case ABORTED_BITS:
      return getAbortedBits();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TXN_HIGH_WATER_MARK:
      return isSetTxn_high_water_mark();
    case OPEN_TXNS:
      return isSetOpen_txns();
    case MIN_OPEN_TXN:
      return isSetMin_open_txn();
    case ABORTED_BITS:
      return isSetAbortedBits();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetOpenTxnsResponse)
      return this.equals((GetOpenTxnsResponse)that);
    return false;
  }

  public boolean equals(GetOpenTxnsResponse that) {
    if (that == null)
      return false;

    boolean this_present_txn_high_water_mark = true;
    boolean that_present_txn_high_water_mark = true;
    if (this_present_txn_high_water_mark || that_present_txn_high_water_mark) {
      if (!(this_present_txn_high_water_mark && that_present_txn_high_water_mark))
        return false;
      if (this.txn_high_water_mark != that.txn_high_water_mark)
        return false;
    }

    boolean this_present_open_txns = true && this.isSetOpen_txns();
    boolean that_present_open_txns = true && that.isSetOpen_txns();
    if (this_present_open_txns || that_present_open_txns) {
      if (!(this_present_open_txns && that_present_open_txns))
        return false;
      if (!this.open_txns.equals(that.open_txns))
        return false;
    }

    boolean this_present_min_open_txn = true && this.isSetMin_open_txn();
    boolean that_present_min_open_txn = true && that.isSetMin_open_txn();
    if (this_present_min_open_txn || that_present_min_open_txn) {
      if (!(this_present_min_open_txn && that_present_min_open_txn))
        return false;
      if (this.min_open_txn != that.min_open_txn)
        return false;
    }

    boolean this_present_abortedBits = true && this.isSetAbortedBits();
    boolean that_present_abortedBits = true && that.isSetAbortedBits();
    if (this_present_abortedBits || that_present_abortedBits) {
      if (!(this_present_abortedBits && that_present_abortedBits))
        return false;
      if (!this.abortedBits.equals(that.abortedBits))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_txn_high_water_mark = true;
    list.add(present_txn_high_water_mark);
    if (present_txn_high_water_mark)
      list.add(txn_high_water_mark);

    boolean present_open_txns = true && (isSetOpen_txns());
    list.add(present_open_txns);
    if (present_open_txns)
      list.add(open_txns);

    boolean present_min_open_txn = true && (isSetMin_open_txn());
    list.add(present_min_open_txn);
    if (present_min_open_txn)
      list.add(min_open_txn);

    boolean present_abortedBits = true && (isSetAbortedBits());
    list.add(present_abortedBits);
    if (present_abortedBits)
      list.add(abortedBits);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetOpenTxnsResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTxn_high_water_mark()).compareTo(other.isSetTxn_high_water_mark());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxn_high_water_mark()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txn_high_water_mark, other.txn_high_water_mark);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOpen_txns()).compareTo(other.isSetOpen_txns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOpen_txns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.open_txns, other.open_txns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMin_open_txn()).compareTo(other.isSetMin_open_txn());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMin_open_txn()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.min_open_txn, other.min_open_txn);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAbortedBits()).compareTo(other.isSetAbortedBits());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAbortedBits()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.abortedBits, other.abortedBits);
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
    StringBuilder sb = new StringBuilder("GetOpenTxnsResponse(");
    boolean first = true;

    sb.append("txn_high_water_mark:");
    sb.append(this.txn_high_water_mark);
    first = false;
    if (!first) sb.append(", ");
    sb.append("open_txns:");
    if (this.open_txns == null) {
      sb.append("null");
    } else {
      sb.append(this.open_txns);
    }
    first = false;
    if (isSetMin_open_txn()) {
      if (!first) sb.append(", ");
      sb.append("min_open_txn:");
      sb.append(this.min_open_txn);
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("abortedBits:");
    if (this.abortedBits == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.abortedBits, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetTxn_high_water_mark()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'txn_high_water_mark' is unset! Struct:" + toString());
    }

    if (!isSetOpen_txns()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'open_txns' is unset! Struct:" + toString());
    }

    if (!isSetAbortedBits()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'abortedBits' is unset! Struct:" + toString());
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

  private static class GetOpenTxnsResponseStandardSchemeFactory implements SchemeFactory {
    public GetOpenTxnsResponseStandardScheme getScheme() {
      return new GetOpenTxnsResponseStandardScheme();
    }
  }

  private static class GetOpenTxnsResponseStandardScheme extends StandardScheme<GetOpenTxnsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetOpenTxnsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TXN_HIGH_WATER_MARK
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.txn_high_water_mark = iprot.readI64();
              struct.setTxn_high_water_markIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // OPEN_TXNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
<<<<<<< HEAD
                org.apache.thrift.protocol.TList _list522 = iprot.readListBegin();
                struct.open_txns = new ArrayList<Long>(_list522.size);
                long _elem523;
                for (int _i524 = 0; _i524 < _list522.size; ++_i524)
                {
                  _elem523 = iprot.readI64();
                  struct.open_txns.add(_elem523);
=======
                org.apache.thrift.protocol.TList _list548 = iprot.readListBegin();
                struct.open_txns = new ArrayList<Long>(_list548.size);
                long _elem549;
                for (int _i550 = 0; _i550 < _list548.size; ++_i550)
                {
                  _elem549 = iprot.readI64();
                  struct.open_txns.add(_elem549);
>>>>>>> asf/master
                }
                iprot.readListEnd();
              }
              struct.setOpen_txnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MIN_OPEN_TXN
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.min_open_txn = iprot.readI64();
              struct.setMin_open_txnIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ABORTED_BITS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.abortedBits = iprot.readBinary();
              struct.setAbortedBitsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetOpenTxnsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TXN_HIGH_WATER_MARK_FIELD_DESC);
      oprot.writeI64(struct.txn_high_water_mark);
      oprot.writeFieldEnd();
      if (struct.open_txns != null) {
        oprot.writeFieldBegin(OPEN_TXNS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.open_txns.size()));
<<<<<<< HEAD
          for (long _iter525 : struct.open_txns)
          {
            oprot.writeI64(_iter525);
=======
          for (long _iter551 : struct.open_txns)
          {
            oprot.writeI64(_iter551);
>>>>>>> asf/master
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.isSetMin_open_txn()) {
        oprot.writeFieldBegin(MIN_OPEN_TXN_FIELD_DESC);
        oprot.writeI64(struct.min_open_txn);
        oprot.writeFieldEnd();
      }
      if (struct.abortedBits != null) {
        oprot.writeFieldBegin(ABORTED_BITS_FIELD_DESC);
        oprot.writeBinary(struct.abortedBits);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetOpenTxnsResponseTupleSchemeFactory implements SchemeFactory {
    public GetOpenTxnsResponseTupleScheme getScheme() {
      return new GetOpenTxnsResponseTupleScheme();
    }
  }

  private static class GetOpenTxnsResponseTupleScheme extends TupleScheme<GetOpenTxnsResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetOpenTxnsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.txn_high_water_mark);
      {
        oprot.writeI32(struct.open_txns.size());
<<<<<<< HEAD
        for (long _iter526 : struct.open_txns)
        {
          oprot.writeI64(_iter526);
=======
        for (long _iter552 : struct.open_txns)
        {
          oprot.writeI64(_iter552);
>>>>>>> asf/master
        }
      }
      oprot.writeBinary(struct.abortedBits);
      BitSet optionals = new BitSet();
      if (struct.isSetMin_open_txn()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetMin_open_txn()) {
        oprot.writeI64(struct.min_open_txn);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetOpenTxnsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.txn_high_water_mark = iprot.readI64();
      struct.setTxn_high_water_markIsSet(true);
      {
<<<<<<< HEAD
        org.apache.thrift.protocol.TList _list527 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
        struct.open_txns = new ArrayList<Long>(_list527.size);
        long _elem528;
        for (int _i529 = 0; _i529 < _list527.size; ++_i529)
        {
          _elem528 = iprot.readI64();
          struct.open_txns.add(_elem528);
=======
        org.apache.thrift.protocol.TList _list553 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
        struct.open_txns = new ArrayList<Long>(_list553.size);
        long _elem554;
        for (int _i555 = 0; _i555 < _list553.size; ++_i555)
        {
          _elem554 = iprot.readI64();
          struct.open_txns.add(_elem554);
>>>>>>> asf/master
        }
      }
      struct.setOpen_txnsIsSet(true);
      struct.abortedBits = iprot.readBinary();
      struct.setAbortedBitsIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.min_open_txn = iprot.readI64();
        struct.setMin_open_txnIsSet(true);
      }
    }
  }

}

