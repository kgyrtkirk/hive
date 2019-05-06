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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class CommitTxnRequest implements org.apache.thrift.TBase<CommitTxnRequest, CommitTxnRequest._Fields>, java.io.Serializable, Cloneable, Comparable<CommitTxnRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CommitTxnRequest");

  private static final org.apache.thrift.protocol.TField TXNID_FIELD_DESC = new org.apache.thrift.protocol.TField("txnid", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField REPL_POLICY_FIELD_DESC = new org.apache.thrift.protocol.TField("replPolicy", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField WRITE_EVENT_INFOS_FIELD_DESC = new org.apache.thrift.protocol.TField("writeEventInfos", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField KEY_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("keyValue", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField REPL_LAST_ID_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("replLastIdInfo", org.apache.thrift.protocol.TType.STRUCT, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CommitTxnRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CommitTxnRequestTupleSchemeFactory());
  }

  private long txnid; // required
  private String replPolicy; // optional
  private List<WriteEventInfo> writeEventInfos; // optional
  private CommitTxnKeyValue keyValue; // optional
  private ReplLastIdInfo replLastIdInfo; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TXNID((short)1, "txnid"),
    REPL_POLICY((short)2, "replPolicy"),
    WRITE_EVENT_INFOS((short)3, "writeEventInfos"),
    KEY_VALUE((short)4, "keyValue"),
    REPL_LAST_ID_INFO((short)5, "replLastIdInfo");

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
        case 1: // TXNID
          return TXNID;
        case 2: // REPL_POLICY
          return REPL_POLICY;
        case 3: // WRITE_EVENT_INFOS
          return WRITE_EVENT_INFOS;
        case 4: // KEY_VALUE
          return KEY_VALUE;
        case 5: // REPL_LAST_ID_INFO
          return REPL_LAST_ID_INFO;
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
  private static final int __TXNID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.REPL_POLICY,_Fields.WRITE_EVENT_INFOS,_Fields.KEY_VALUE,_Fields.REPL_LAST_ID_INFO};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TXNID, new org.apache.thrift.meta_data.FieldMetaData("txnid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.REPL_POLICY, new org.apache.thrift.meta_data.FieldMetaData("replPolicy", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WRITE_EVENT_INFOS, new org.apache.thrift.meta_data.FieldMetaData("writeEventInfos", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, WriteEventInfo.class))));
    tmpMap.put(_Fields.KEY_VALUE, new org.apache.thrift.meta_data.FieldMetaData("keyValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CommitTxnKeyValue.class)));
    tmpMap.put(_Fields.REPL_LAST_ID_INFO, new org.apache.thrift.meta_data.FieldMetaData("replLastIdInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ReplLastIdInfo.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CommitTxnRequest.class, metaDataMap);
  }

  public CommitTxnRequest() {
  }

  public CommitTxnRequest(
    long txnid)
  {
    this();
    this.txnid = txnid;
    setTxnidIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CommitTxnRequest(CommitTxnRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.txnid = other.txnid;
    if (other.isSetReplPolicy()) {
      this.replPolicy = other.replPolicy;
    }
    if (other.isSetWriteEventInfos()) {
      List<WriteEventInfo> __this__writeEventInfos = new ArrayList<WriteEventInfo>(other.writeEventInfos.size());
      for (WriteEventInfo other_element : other.writeEventInfos) {
        __this__writeEventInfos.add(new WriteEventInfo(other_element));
      }
      this.writeEventInfos = __this__writeEventInfos;
    }
    if (other.isSetKeyValue()) {
      this.keyValue = new CommitTxnKeyValue(other.keyValue);
    }
    if (other.isSetReplLastIdInfo()) {
      this.replLastIdInfo = new ReplLastIdInfo(other.replLastIdInfo);
    }
  }

  public CommitTxnRequest deepCopy() {
    return new CommitTxnRequest(this);
  }

  @Override
  public void clear() {
    setTxnidIsSet(false);
    this.txnid = 0;
    this.replPolicy = null;
    this.writeEventInfos = null;
    this.keyValue = null;
    this.replLastIdInfo = null;
  }

  public long getTxnid() {
    return this.txnid;
  }

  public void setTxnid(long txnid) {
    this.txnid = txnid;
    setTxnidIsSet(true);
  }

  public void unsetTxnid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TXNID_ISSET_ID);
  }

  /** Returns true if field txnid is set (has been assigned a value) and false otherwise */
  public boolean isSetTxnid() {
    return EncodingUtils.testBit(__isset_bitfield, __TXNID_ISSET_ID);
  }

  public void setTxnidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TXNID_ISSET_ID, value);
  }

  public String getReplPolicy() {
    return this.replPolicy;
  }

  public void setReplPolicy(String replPolicy) {
    this.replPolicy = replPolicy;
  }

  public void unsetReplPolicy() {
    this.replPolicy = null;
  }

  /** Returns true if field replPolicy is set (has been assigned a value) and false otherwise */
  public boolean isSetReplPolicy() {
    return this.replPolicy != null;
  }

  public void setReplPolicyIsSet(boolean value) {
    if (!value) {
      this.replPolicy = null;
    }
  }

  public int getWriteEventInfosSize() {
    return (this.writeEventInfos == null) ? 0 : this.writeEventInfos.size();
  }

  public java.util.Iterator<WriteEventInfo> getWriteEventInfosIterator() {
    return (this.writeEventInfos == null) ? null : this.writeEventInfos.iterator();
  }

  public void addToWriteEventInfos(WriteEventInfo elem) {
    if (this.writeEventInfos == null) {
      this.writeEventInfos = new ArrayList<WriteEventInfo>();
    }
    this.writeEventInfos.add(elem);
  }

  public List<WriteEventInfo> getWriteEventInfos() {
    return this.writeEventInfos;
  }

  public void setWriteEventInfos(List<WriteEventInfo> writeEventInfos) {
    this.writeEventInfos = writeEventInfos;
  }

  public void unsetWriteEventInfos() {
    this.writeEventInfos = null;
  }

  /** Returns true if field writeEventInfos is set (has been assigned a value) and false otherwise */
  public boolean isSetWriteEventInfos() {
    return this.writeEventInfos != null;
  }

  public void setWriteEventInfosIsSet(boolean value) {
    if (!value) {
      this.writeEventInfos = null;
    }
  }

  public CommitTxnKeyValue getKeyValue() {
    return this.keyValue;
  }

  public void setKeyValue(CommitTxnKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void unsetKeyValue() {
    this.keyValue = null;
  }

  /** Returns true if field keyValue is set (has been assigned a value) and false otherwise */
  public boolean isSetKeyValue() {
    return this.keyValue != null;
  }

  public void setKeyValueIsSet(boolean value) {
    if (!value) {
      this.keyValue = null;
    }
  }

  public ReplLastIdInfo getReplLastIdInfo() {
    return this.replLastIdInfo;
  }

  public void setReplLastIdInfo(ReplLastIdInfo replLastIdInfo) {
    this.replLastIdInfo = replLastIdInfo;
  }

  public void unsetReplLastIdInfo() {
    this.replLastIdInfo = null;
  }

  /** Returns true if field replLastIdInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetReplLastIdInfo() {
    return this.replLastIdInfo != null;
  }

  public void setReplLastIdInfoIsSet(boolean value) {
    if (!value) {
      this.replLastIdInfo = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TXNID:
      if (value == null) {
        unsetTxnid();
      } else {
        setTxnid((Long)value);
      }
      break;

    case REPL_POLICY:
      if (value == null) {
        unsetReplPolicy();
      } else {
        setReplPolicy((String)value);
      }
      break;

    case WRITE_EVENT_INFOS:
      if (value == null) {
        unsetWriteEventInfos();
      } else {
        setWriteEventInfos((List<WriteEventInfo>)value);
      }
      break;

    case KEY_VALUE:
      if (value == null) {
        unsetKeyValue();
      } else {
        setKeyValue((CommitTxnKeyValue)value);
      }
      break;

    case REPL_LAST_ID_INFO:
      if (value == null) {
        unsetReplLastIdInfo();
      } else {
        setReplLastIdInfo((ReplLastIdInfo)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TXNID:
      return getTxnid();

    case REPL_POLICY:
      return getReplPolicy();

    case WRITE_EVENT_INFOS:
      return getWriteEventInfos();

    case KEY_VALUE:
      return getKeyValue();

    case REPL_LAST_ID_INFO:
      return getReplLastIdInfo();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TXNID:
      return isSetTxnid();
    case REPL_POLICY:
      return isSetReplPolicy();
    case WRITE_EVENT_INFOS:
      return isSetWriteEventInfos();
    case KEY_VALUE:
      return isSetKeyValue();
    case REPL_LAST_ID_INFO:
      return isSetReplLastIdInfo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CommitTxnRequest)
      return this.equals((CommitTxnRequest)that);
    return false;
  }

  public boolean equals(CommitTxnRequest that) {
    if (that == null)
      return false;

    boolean this_present_txnid = true;
    boolean that_present_txnid = true;
    if (this_present_txnid || that_present_txnid) {
      if (!(this_present_txnid && that_present_txnid))
        return false;
      if (this.txnid != that.txnid)
        return false;
    }

    boolean this_present_replPolicy = true && this.isSetReplPolicy();
    boolean that_present_replPolicy = true && that.isSetReplPolicy();
    if (this_present_replPolicy || that_present_replPolicy) {
      if (!(this_present_replPolicy && that_present_replPolicy))
        return false;
      if (!this.replPolicy.equals(that.replPolicy))
        return false;
    }

    boolean this_present_writeEventInfos = true && this.isSetWriteEventInfos();
    boolean that_present_writeEventInfos = true && that.isSetWriteEventInfos();
    if (this_present_writeEventInfos || that_present_writeEventInfos) {
      if (!(this_present_writeEventInfos && that_present_writeEventInfos))
        return false;
      if (!this.writeEventInfos.equals(that.writeEventInfos))
        return false;
    }

    boolean this_present_keyValue = true && this.isSetKeyValue();
    boolean that_present_keyValue = true && that.isSetKeyValue();
    if (this_present_keyValue || that_present_keyValue) {
      if (!(this_present_keyValue && that_present_keyValue))
        return false;
      if (!this.keyValue.equals(that.keyValue))
        return false;
    }

    boolean this_present_replLastIdInfo = true && this.isSetReplLastIdInfo();
    boolean that_present_replLastIdInfo = true && that.isSetReplLastIdInfo();
    if (this_present_replLastIdInfo || that_present_replLastIdInfo) {
      if (!(this_present_replLastIdInfo && that_present_replLastIdInfo))
        return false;
      if (!this.replLastIdInfo.equals(that.replLastIdInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_txnid = true;
    list.add(present_txnid);
    if (present_txnid)
      list.add(txnid);

    boolean present_replPolicy = true && (isSetReplPolicy());
    list.add(present_replPolicy);
    if (present_replPolicy)
      list.add(replPolicy);

    boolean present_writeEventInfos = true && (isSetWriteEventInfos());
    list.add(present_writeEventInfos);
    if (present_writeEventInfos)
      list.add(writeEventInfos);

    boolean present_keyValue = true && (isSetKeyValue());
    list.add(present_keyValue);
    if (present_keyValue)
      list.add(keyValue);

    boolean present_replLastIdInfo = true && (isSetReplLastIdInfo());
    list.add(present_replLastIdInfo);
    if (present_replLastIdInfo)
      list.add(replLastIdInfo);

    return list.hashCode();
  }

  @Override
  public int compareTo(CommitTxnRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTxnid()).compareTo(other.isSetTxnid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxnid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txnid, other.txnid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReplPolicy()).compareTo(other.isSetReplPolicy());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReplPolicy()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.replPolicy, other.replPolicy);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWriteEventInfos()).compareTo(other.isSetWriteEventInfos());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWriteEventInfos()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.writeEventInfos, other.writeEventInfos);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetKeyValue()).compareTo(other.isSetKeyValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKeyValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.keyValue, other.keyValue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReplLastIdInfo()).compareTo(other.isSetReplLastIdInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReplLastIdInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.replLastIdInfo, other.replLastIdInfo);
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
    StringBuilder sb = new StringBuilder("CommitTxnRequest(");
    boolean first = true;

    sb.append("txnid:");
    sb.append(this.txnid);
    first = false;
    if (isSetReplPolicy()) {
      if (!first) sb.append(", ");
      sb.append("replPolicy:");
      if (this.replPolicy == null) {
        sb.append("null");
      } else {
        sb.append(this.replPolicy);
      }
      first = false;
    }
    if (isSetWriteEventInfos()) {
      if (!first) sb.append(", ");
      sb.append("writeEventInfos:");
      if (this.writeEventInfos == null) {
        sb.append("null");
      } else {
        sb.append(this.writeEventInfos);
      }
      first = false;
    }
    if (isSetKeyValue()) {
      if (!first) sb.append(", ");
      sb.append("keyValue:");
      if (this.keyValue == null) {
        sb.append("null");
      } else {
        sb.append(this.keyValue);
      }
      first = false;
    }
    if (isSetReplLastIdInfo()) {
      if (!first) sb.append(", ");
      sb.append("replLastIdInfo:");
      if (this.replLastIdInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.replLastIdInfo);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetTxnid()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'txnid' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
    if (keyValue != null) {
      keyValue.validate();
    }
    if (replLastIdInfo != null) {
      replLastIdInfo.validate();
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

  private static class CommitTxnRequestStandardSchemeFactory implements SchemeFactory {
    public CommitTxnRequestStandardScheme getScheme() {
      return new CommitTxnRequestStandardScheme();
    }
  }

  private static class CommitTxnRequestStandardScheme extends StandardScheme<CommitTxnRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CommitTxnRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TXNID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.txnid = iprot.readI64();
              struct.setTxnidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // REPL_POLICY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.replPolicy = iprot.readString();
              struct.setReplPolicyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // WRITE_EVENT_INFOS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list634 = iprot.readListBegin();
                struct.writeEventInfos = new ArrayList<WriteEventInfo>(_list634.size);
                WriteEventInfo _elem635;
                for (int _i636 = 0; _i636 < _list634.size; ++_i636)
                {
                  _elem635 = new WriteEventInfo();
                  _elem635.read(iprot);
                  struct.writeEventInfos.add(_elem635);
                }
                iprot.readListEnd();
              }
              struct.setWriteEventInfosIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // KEY_VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.keyValue = new CommitTxnKeyValue();
              struct.keyValue.read(iprot);
              struct.setKeyValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // REPL_LAST_ID_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.replLastIdInfo = new ReplLastIdInfo();
              struct.replLastIdInfo.read(iprot);
              struct.setReplLastIdInfoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CommitTxnRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TXNID_FIELD_DESC);
      oprot.writeI64(struct.txnid);
      oprot.writeFieldEnd();
      if (struct.replPolicy != null) {
        if (struct.isSetReplPolicy()) {
          oprot.writeFieldBegin(REPL_POLICY_FIELD_DESC);
          oprot.writeString(struct.replPolicy);
          oprot.writeFieldEnd();
        }
      }
      if (struct.writeEventInfos != null) {
        if (struct.isSetWriteEventInfos()) {
          oprot.writeFieldBegin(WRITE_EVENT_INFOS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.writeEventInfos.size()));
            for (WriteEventInfo _iter637 : struct.writeEventInfos)
            {
              _iter637.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.keyValue != null) {
        if (struct.isSetKeyValue()) {
          oprot.writeFieldBegin(KEY_VALUE_FIELD_DESC);
          struct.keyValue.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.replLastIdInfo != null) {
        if (struct.isSetReplLastIdInfo()) {
          oprot.writeFieldBegin(REPL_LAST_ID_INFO_FIELD_DESC);
          struct.replLastIdInfo.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CommitTxnRequestTupleSchemeFactory implements SchemeFactory {
    public CommitTxnRequestTupleScheme getScheme() {
      return new CommitTxnRequestTupleScheme();
    }
  }

  private static class CommitTxnRequestTupleScheme extends TupleScheme<CommitTxnRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CommitTxnRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.txnid);
      BitSet optionals = new BitSet();
      if (struct.isSetReplPolicy()) {
        optionals.set(0);
      }
      if (struct.isSetWriteEventInfos()) {
        optionals.set(1);
      }
      if (struct.isSetKeyValue()) {
        optionals.set(2);
      }
      if (struct.isSetReplLastIdInfo()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetReplPolicy()) {
        oprot.writeString(struct.replPolicy);
      }
      if (struct.isSetWriteEventInfos()) {
        {
          oprot.writeI32(struct.writeEventInfos.size());
          for (WriteEventInfo _iter638 : struct.writeEventInfos)
          {
            _iter638.write(oprot);
          }
        }
      }
      if (struct.isSetKeyValue()) {
        struct.keyValue.write(oprot);
      }
      if (struct.isSetReplLastIdInfo()) {
        struct.replLastIdInfo.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CommitTxnRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.txnid = iprot.readI64();
      struct.setTxnidIsSet(true);
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.replPolicy = iprot.readString();
        struct.setReplPolicyIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list639 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.writeEventInfos = new ArrayList<WriteEventInfo>(_list639.size);
          WriteEventInfo _elem640;
          for (int _i641 = 0; _i641 < _list639.size; ++_i641)
          {
            _elem640 = new WriteEventInfo();
            _elem640.read(iprot);
            struct.writeEventInfos.add(_elem640);
          }
        }
        struct.setWriteEventInfosIsSet(true);
      }
      if (incoming.get(2)) {
        struct.keyValue = new CommitTxnKeyValue();
        struct.keyValue.read(iprot);
        struct.setKeyValueIsSet(true);
      }
      if (incoming.get(3)) {
        struct.replLastIdInfo = new ReplLastIdInfo();
        struct.replLastIdInfo.read(iprot);
        struct.setReplLastIdInfoIsSet(true);
      }
    }
  }

}

