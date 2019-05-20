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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class AbortTxnsRequest implements org.apache.thrift.TBase<AbortTxnsRequest, AbortTxnsRequest._Fields>, java.io.Serializable, Cloneable, Comparable<AbortTxnsRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AbortTxnsRequest");

  private static final org.apache.thrift.protocol.TField TXN_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("txn_ids", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AbortTxnsRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AbortTxnsRequestTupleSchemeFactory());
  }

  private List<Long> txn_ids; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TXN_IDS((short)1, "txn_ids");

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
        case 1: // TXN_IDS
          return TXN_IDS;
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
    tmpMap.put(_Fields.TXN_IDS, new org.apache.thrift.meta_data.FieldMetaData("txn_ids", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AbortTxnsRequest.class, metaDataMap);
  }

  public AbortTxnsRequest() {
  }

  public AbortTxnsRequest(
    List<Long> txn_ids)
  {
    this();
    this.txn_ids = txn_ids;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AbortTxnsRequest(AbortTxnsRequest other) {
    if (other.isSetTxn_ids()) {
      List<Long> __this__txn_ids = new ArrayList<Long>(other.txn_ids);
      this.txn_ids = __this__txn_ids;
    }
  }

  public AbortTxnsRequest deepCopy() {
    return new AbortTxnsRequest(this);
  }

  @Override
  public void clear() {
    this.txn_ids = null;
  }

  public int getTxn_idsSize() {
    return (this.txn_ids == null) ? 0 : this.txn_ids.size();
  }

  public java.util.Iterator<Long> getTxn_idsIterator() {
    return (this.txn_ids == null) ? null : this.txn_ids.iterator();
  }

  public void addToTxn_ids(long elem) {
    if (this.txn_ids == null) {
      this.txn_ids = new ArrayList<Long>();
    }
    this.txn_ids.add(elem);
  }

  public List<Long> getTxn_ids() {
    return this.txn_ids;
  }

  public void setTxn_ids(List<Long> txn_ids) {
    this.txn_ids = txn_ids;
  }

  public void unsetTxn_ids() {
    this.txn_ids = null;
  }

  /** Returns true if field txn_ids is set (has been assigned a value) and false otherwise */
  public boolean isSetTxn_ids() {
    return this.txn_ids != null;
  }

  public void setTxn_idsIsSet(boolean value) {
    if (!value) {
      this.txn_ids = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TXN_IDS:
      if (value == null) {
        unsetTxn_ids();
      } else {
        setTxn_ids((List<Long>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TXN_IDS:
      return getTxn_ids();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TXN_IDS:
      return isSetTxn_ids();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AbortTxnsRequest)
      return this.equals((AbortTxnsRequest)that);
    return false;
  }

  public boolean equals(AbortTxnsRequest that) {
    if (that == null)
      return false;

    boolean this_present_txn_ids = true && this.isSetTxn_ids();
    boolean that_present_txn_ids = true && that.isSetTxn_ids();
    if (this_present_txn_ids || that_present_txn_ids) {
      if (!(this_present_txn_ids && that_present_txn_ids))
        return false;
      if (!this.txn_ids.equals(that.txn_ids))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_txn_ids = true && (isSetTxn_ids());
    list.add(present_txn_ids);
    if (present_txn_ids)
      list.add(txn_ids);

    return list.hashCode();
  }

  @Override
  public int compareTo(AbortTxnsRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTxn_ids()).compareTo(other.isSetTxn_ids());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxn_ids()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txn_ids, other.txn_ids);
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
    StringBuilder sb = new StringBuilder("AbortTxnsRequest(");
    boolean first = true;

    sb.append("txn_ids:");
    if (this.txn_ids == null) {
      sb.append("null");
    } else {
      sb.append(this.txn_ids);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetTxn_ids()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'txn_ids' is unset! Struct:" + toString());
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

  private static class AbortTxnsRequestStandardSchemeFactory implements SchemeFactory {
    public AbortTxnsRequestStandardScheme getScheme() {
      return new AbortTxnsRequestStandardScheme();
    }
  }

  private static class AbortTxnsRequestStandardScheme extends StandardScheme<AbortTxnsRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AbortTxnsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TXN_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list626 = iprot.readListBegin();
                struct.txn_ids = new ArrayList<Long>(_list626.size);
                long _elem627;
                for (int _i628 = 0; _i628 < _list626.size; ++_i628)
                {
                  _elem627 = iprot.readI64();
                  struct.txn_ids.add(_elem627);
                }
                iprot.readListEnd();
              }
              struct.setTxn_idsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AbortTxnsRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.txn_ids != null) {
        oprot.writeFieldBegin(TXN_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.txn_ids.size()));
          for (long _iter629 : struct.txn_ids)
          {
            oprot.writeI64(_iter629);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AbortTxnsRequestTupleSchemeFactory implements SchemeFactory {
    public AbortTxnsRequestTupleScheme getScheme() {
      return new AbortTxnsRequestTupleScheme();
    }
  }

  private static class AbortTxnsRequestTupleScheme extends TupleScheme<AbortTxnsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AbortTxnsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.txn_ids.size());
        for (long _iter630 : struct.txn_ids)
        {
          oprot.writeI64(_iter630);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AbortTxnsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list631 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
        struct.txn_ids = new ArrayList<Long>(_list631.size);
        long _elem632;
        for (int _i633 = 0; _i633 < _list631.size; ++_i633)
        {
          _elem632 = iprot.readI64();
          struct.txn_ids.add(_elem632);
        }
      }
      struct.setTxn_idsIsSet(true);
    }
  }

}

