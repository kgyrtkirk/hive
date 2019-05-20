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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class OpenTxnRequest implements org.apache.thrift.TBase<OpenTxnRequest, OpenTxnRequest._Fields>, java.io.Serializable, Cloneable, Comparable<OpenTxnRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("OpenTxnRequest");

  private static final org.apache.thrift.protocol.TField NUM_TXNS_FIELD_DESC = new org.apache.thrift.protocol.TField("num_txns", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField USER_FIELD_DESC = new org.apache.thrift.protocol.TField("user", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField HOSTNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("hostname", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField AGENT_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("agentInfo", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField REPL_POLICY_FIELD_DESC = new org.apache.thrift.protocol.TField("replPolicy", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField REPL_SRC_TXN_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("replSrcTxnIds", org.apache.thrift.protocol.TType.LIST, (short)6);
  private static final org.apache.thrift.protocol.TField TXN_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("txn_type", org.apache.thrift.protocol.TType.I32, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new OpenTxnRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new OpenTxnRequestTupleSchemeFactory());
  }

  private int num_txns; // required
  private String user; // required
  private String hostname; // required
  private String agentInfo; // optional
  private String replPolicy; // optional
  private List<Long> replSrcTxnIds; // optional
  private TxnType txn_type; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NUM_TXNS((short)1, "num_txns"),
    USER((short)2, "user"),
    HOSTNAME((short)3, "hostname"),
    AGENT_INFO((short)4, "agentInfo"),
    REPL_POLICY((short)5, "replPolicy"),
    REPL_SRC_TXN_IDS((short)6, "replSrcTxnIds"),
    /**
     * 
     * @see TxnType
     */
    TXN_TYPE((short)7, "txn_type");

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
        case 1: // NUM_TXNS
          return NUM_TXNS;
        case 2: // USER
          return USER;
        case 3: // HOSTNAME
          return HOSTNAME;
        case 4: // AGENT_INFO
          return AGENT_INFO;
        case 5: // REPL_POLICY
          return REPL_POLICY;
        case 6: // REPL_SRC_TXN_IDS
          return REPL_SRC_TXN_IDS;
        case 7: // TXN_TYPE
          return TXN_TYPE;
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
  private static final int __NUM_TXNS_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.AGENT_INFO,_Fields.REPL_POLICY,_Fields.REPL_SRC_TXN_IDS,_Fields.TXN_TYPE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NUM_TXNS, new org.apache.thrift.meta_data.FieldMetaData("num_txns", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.USER, new org.apache.thrift.meta_data.FieldMetaData("user", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOSTNAME, new org.apache.thrift.meta_data.FieldMetaData("hostname", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AGENT_INFO, new org.apache.thrift.meta_data.FieldMetaData("agentInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REPL_POLICY, new org.apache.thrift.meta_data.FieldMetaData("replPolicy", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REPL_SRC_TXN_IDS, new org.apache.thrift.meta_data.FieldMetaData("replSrcTxnIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.TXN_TYPE, new org.apache.thrift.meta_data.FieldMetaData("txn_type", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TxnType.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(OpenTxnRequest.class, metaDataMap);
  }

  public OpenTxnRequest() {
    this.agentInfo = "Unknown";

    this.txn_type = org.apache.hadoop.hive.metastore.api.TxnType.DEFAULT;

  }

  public OpenTxnRequest(
    int num_txns,
    String user,
    String hostname)
  {
    this();
    this.num_txns = num_txns;
    setNum_txnsIsSet(true);
    this.user = user;
    this.hostname = hostname;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public OpenTxnRequest(OpenTxnRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    this.num_txns = other.num_txns;
    if (other.isSetUser()) {
      this.user = other.user;
    }
    if (other.isSetHostname()) {
      this.hostname = other.hostname;
    }
    if (other.isSetAgentInfo()) {
      this.agentInfo = other.agentInfo;
    }
    if (other.isSetReplPolicy()) {
      this.replPolicy = other.replPolicy;
    }
    if (other.isSetReplSrcTxnIds()) {
      List<Long> __this__replSrcTxnIds = new ArrayList<Long>(other.replSrcTxnIds);
      this.replSrcTxnIds = __this__replSrcTxnIds;
    }
    if (other.isSetTxn_type()) {
      this.txn_type = other.txn_type;
    }
  }

  public OpenTxnRequest deepCopy() {
    return new OpenTxnRequest(this);
  }

  @Override
  public void clear() {
    setNum_txnsIsSet(false);
    this.num_txns = 0;
    this.user = null;
    this.hostname = null;
    this.agentInfo = "Unknown";

    this.replPolicy = null;
    this.replSrcTxnIds = null;
    this.txn_type = org.apache.hadoop.hive.metastore.api.TxnType.DEFAULT;

  }

  public int getNum_txns() {
    return this.num_txns;
  }

  public void setNum_txns(int num_txns) {
    this.num_txns = num_txns;
    setNum_txnsIsSet(true);
  }

  public void unsetNum_txns() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUM_TXNS_ISSET_ID);
  }

  /** Returns true if field num_txns is set (has been assigned a value) and false otherwise */
  public boolean isSetNum_txns() {
    return EncodingUtils.testBit(__isset_bitfield, __NUM_TXNS_ISSET_ID);
  }

  public void setNum_txnsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUM_TXNS_ISSET_ID, value);
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void unsetUser() {
    this.user = null;
  }

  /** Returns true if field user is set (has been assigned a value) and false otherwise */
  public boolean isSetUser() {
    return this.user != null;
  }

  public void setUserIsSet(boolean value) {
    if (!value) {
      this.user = null;
    }
  }

  public String getHostname() {
    return this.hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public void unsetHostname() {
    this.hostname = null;
  }

  /** Returns true if field hostname is set (has been assigned a value) and false otherwise */
  public boolean isSetHostname() {
    return this.hostname != null;
  }

  public void setHostnameIsSet(boolean value) {
    if (!value) {
      this.hostname = null;
    }
  }

  public String getAgentInfo() {
    return this.agentInfo;
  }

  public void setAgentInfo(String agentInfo) {
    this.agentInfo = agentInfo;
  }

  public void unsetAgentInfo() {
    this.agentInfo = null;
  }

  /** Returns true if field agentInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetAgentInfo() {
    return this.agentInfo != null;
  }

  public void setAgentInfoIsSet(boolean value) {
    if (!value) {
      this.agentInfo = null;
    }
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

  public int getReplSrcTxnIdsSize() {
    return (this.replSrcTxnIds == null) ? 0 : this.replSrcTxnIds.size();
  }

  public java.util.Iterator<Long> getReplSrcTxnIdsIterator() {
    return (this.replSrcTxnIds == null) ? null : this.replSrcTxnIds.iterator();
  }

  public void addToReplSrcTxnIds(long elem) {
    if (this.replSrcTxnIds == null) {
      this.replSrcTxnIds = new ArrayList<Long>();
    }
    this.replSrcTxnIds.add(elem);
  }

  public List<Long> getReplSrcTxnIds() {
    return this.replSrcTxnIds;
  }

  public void setReplSrcTxnIds(List<Long> replSrcTxnIds) {
    this.replSrcTxnIds = replSrcTxnIds;
  }

  public void unsetReplSrcTxnIds() {
    this.replSrcTxnIds = null;
  }

  /** Returns true if field replSrcTxnIds is set (has been assigned a value) and false otherwise */
  public boolean isSetReplSrcTxnIds() {
    return this.replSrcTxnIds != null;
  }

  public void setReplSrcTxnIdsIsSet(boolean value) {
    if (!value) {
      this.replSrcTxnIds = null;
    }
  }

  /**
   * 
   * @see TxnType
   */
  public TxnType getTxn_type() {
    return this.txn_type;
  }

  /**
   * 
   * @see TxnType
   */
  public void setTxn_type(TxnType txn_type) {
    this.txn_type = txn_type;
  }

  public void unsetTxn_type() {
    this.txn_type = null;
  }

  /** Returns true if field txn_type is set (has been assigned a value) and false otherwise */
  public boolean isSetTxn_type() {
    return this.txn_type != null;
  }

  public void setTxn_typeIsSet(boolean value) {
    if (!value) {
      this.txn_type = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NUM_TXNS:
      if (value == null) {
        unsetNum_txns();
      } else {
        setNum_txns((Integer)value);
      }
      break;

    case USER:
      if (value == null) {
        unsetUser();
      } else {
        setUser((String)value);
      }
      break;

    case HOSTNAME:
      if (value == null) {
        unsetHostname();
      } else {
        setHostname((String)value);
      }
      break;

    case AGENT_INFO:
      if (value == null) {
        unsetAgentInfo();
      } else {
        setAgentInfo((String)value);
      }
      break;

    case REPL_POLICY:
      if (value == null) {
        unsetReplPolicy();
      } else {
        setReplPolicy((String)value);
      }
      break;

    case REPL_SRC_TXN_IDS:
      if (value == null) {
        unsetReplSrcTxnIds();
      } else {
        setReplSrcTxnIds((List<Long>)value);
      }
      break;

    case TXN_TYPE:
      if (value == null) {
        unsetTxn_type();
      } else {
        setTxn_type((TxnType)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NUM_TXNS:
      return getNum_txns();

    case USER:
      return getUser();

    case HOSTNAME:
      return getHostname();

    case AGENT_INFO:
      return getAgentInfo();

    case REPL_POLICY:
      return getReplPolicy();

    case REPL_SRC_TXN_IDS:
      return getReplSrcTxnIds();

    case TXN_TYPE:
      return getTxn_type();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NUM_TXNS:
      return isSetNum_txns();
    case USER:
      return isSetUser();
    case HOSTNAME:
      return isSetHostname();
    case AGENT_INFO:
      return isSetAgentInfo();
    case REPL_POLICY:
      return isSetReplPolicy();
    case REPL_SRC_TXN_IDS:
      return isSetReplSrcTxnIds();
    case TXN_TYPE:
      return isSetTxn_type();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof OpenTxnRequest)
      return this.equals((OpenTxnRequest)that);
    return false;
  }

  public boolean equals(OpenTxnRequest that) {
    if (that == null)
      return false;

    boolean this_present_num_txns = true;
    boolean that_present_num_txns = true;
    if (this_present_num_txns || that_present_num_txns) {
      if (!(this_present_num_txns && that_present_num_txns))
        return false;
      if (this.num_txns != that.num_txns)
        return false;
    }

    boolean this_present_user = true && this.isSetUser();
    boolean that_present_user = true && that.isSetUser();
    if (this_present_user || that_present_user) {
      if (!(this_present_user && that_present_user))
        return false;
      if (!this.user.equals(that.user))
        return false;
    }

    boolean this_present_hostname = true && this.isSetHostname();
    boolean that_present_hostname = true && that.isSetHostname();
    if (this_present_hostname || that_present_hostname) {
      if (!(this_present_hostname && that_present_hostname))
        return false;
      if (!this.hostname.equals(that.hostname))
        return false;
    }

    boolean this_present_agentInfo = true && this.isSetAgentInfo();
    boolean that_present_agentInfo = true && that.isSetAgentInfo();
    if (this_present_agentInfo || that_present_agentInfo) {
      if (!(this_present_agentInfo && that_present_agentInfo))
        return false;
      if (!this.agentInfo.equals(that.agentInfo))
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

    boolean this_present_replSrcTxnIds = true && this.isSetReplSrcTxnIds();
    boolean that_present_replSrcTxnIds = true && that.isSetReplSrcTxnIds();
    if (this_present_replSrcTxnIds || that_present_replSrcTxnIds) {
      if (!(this_present_replSrcTxnIds && that_present_replSrcTxnIds))
        return false;
      if (!this.replSrcTxnIds.equals(that.replSrcTxnIds))
        return false;
    }

    boolean this_present_txn_type = true && this.isSetTxn_type();
    boolean that_present_txn_type = true && that.isSetTxn_type();
    if (this_present_txn_type || that_present_txn_type) {
      if (!(this_present_txn_type && that_present_txn_type))
        return false;
      if (!this.txn_type.equals(that.txn_type))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_num_txns = true;
    list.add(present_num_txns);
    if (present_num_txns)
      list.add(num_txns);

    boolean present_user = true && (isSetUser());
    list.add(present_user);
    if (present_user)
      list.add(user);

    boolean present_hostname = true && (isSetHostname());
    list.add(present_hostname);
    if (present_hostname)
      list.add(hostname);

    boolean present_agentInfo = true && (isSetAgentInfo());
    list.add(present_agentInfo);
    if (present_agentInfo)
      list.add(agentInfo);

    boolean present_replPolicy = true && (isSetReplPolicy());
    list.add(present_replPolicy);
    if (present_replPolicy)
      list.add(replPolicy);

    boolean present_replSrcTxnIds = true && (isSetReplSrcTxnIds());
    list.add(present_replSrcTxnIds);
    if (present_replSrcTxnIds)
      list.add(replSrcTxnIds);

    boolean present_txn_type = true && (isSetTxn_type());
    list.add(present_txn_type);
    if (present_txn_type)
      list.add(txn_type.getValue());

    return list.hashCode();
  }

  @Override
  public int compareTo(OpenTxnRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetNum_txns()).compareTo(other.isSetNum_txns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNum_txns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_txns, other.num_txns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUser()).compareTo(other.isSetUser());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUser()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.user, other.user);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHostname()).compareTo(other.isSetHostname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostname, other.hostname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAgentInfo()).compareTo(other.isSetAgentInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgentInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.agentInfo, other.agentInfo);
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
    lastComparison = Boolean.valueOf(isSetReplSrcTxnIds()).compareTo(other.isSetReplSrcTxnIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReplSrcTxnIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.replSrcTxnIds, other.replSrcTxnIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTxn_type()).compareTo(other.isSetTxn_type());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxn_type()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txn_type, other.txn_type);
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
    StringBuilder sb = new StringBuilder("OpenTxnRequest(");
    boolean first = true;

    sb.append("num_txns:");
    sb.append(this.num_txns);
    first = false;
    if (!first) sb.append(", ");
    sb.append("user:");
    if (this.user == null) {
      sb.append("null");
    } else {
      sb.append(this.user);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("hostname:");
    if (this.hostname == null) {
      sb.append("null");
    } else {
      sb.append(this.hostname);
    }
    first = false;
    if (isSetAgentInfo()) {
      if (!first) sb.append(", ");
      sb.append("agentInfo:");
      if (this.agentInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.agentInfo);
      }
      first = false;
    }
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
    if (isSetReplSrcTxnIds()) {
      if (!first) sb.append(", ");
      sb.append("replSrcTxnIds:");
      if (this.replSrcTxnIds == null) {
        sb.append("null");
      } else {
        sb.append(this.replSrcTxnIds);
      }
      first = false;
    }
    if (isSetTxn_type()) {
      if (!first) sb.append(", ");
      sb.append("txn_type:");
      if (this.txn_type == null) {
        sb.append("null");
      } else {
        sb.append(this.txn_type);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetNum_txns()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'num_txns' is unset! Struct:" + toString());
    }

    if (!isSetUser()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'user' is unset! Struct:" + toString());
    }

    if (!isSetHostname()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'hostname' is unset! Struct:" + toString());
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

  private static class OpenTxnRequestStandardSchemeFactory implements SchemeFactory {
    public OpenTxnRequestStandardScheme getScheme() {
      return new OpenTxnRequestStandardScheme();
    }
  }

  private static class OpenTxnRequestStandardScheme extends StandardScheme<OpenTxnRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, OpenTxnRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NUM_TXNS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.num_txns = iprot.readI32();
              struct.setNum_txnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.user = iprot.readString();
              struct.setUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HOSTNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.hostname = iprot.readString();
              struct.setHostnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // AGENT_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.agentInfo = iprot.readString();
              struct.setAgentInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // REPL_POLICY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.replPolicy = iprot.readString();
              struct.setReplPolicyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // REPL_SRC_TXN_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list610 = iprot.readListBegin();
                struct.replSrcTxnIds = new ArrayList<Long>(_list610.size);
                long _elem611;
                for (int _i612 = 0; _i612 < _list610.size; ++_i612)
                {
                  _elem611 = iprot.readI64();
                  struct.replSrcTxnIds.add(_elem611);
                }
                iprot.readListEnd();
              }
              struct.setReplSrcTxnIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // TXN_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.txn_type = org.apache.hadoop.hive.metastore.api.TxnType.findByValue(iprot.readI32());
              struct.setTxn_typeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, OpenTxnRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NUM_TXNS_FIELD_DESC);
      oprot.writeI32(struct.num_txns);
      oprot.writeFieldEnd();
      if (struct.user != null) {
        oprot.writeFieldBegin(USER_FIELD_DESC);
        oprot.writeString(struct.user);
        oprot.writeFieldEnd();
      }
      if (struct.hostname != null) {
        oprot.writeFieldBegin(HOSTNAME_FIELD_DESC);
        oprot.writeString(struct.hostname);
        oprot.writeFieldEnd();
      }
      if (struct.agentInfo != null) {
        if (struct.isSetAgentInfo()) {
          oprot.writeFieldBegin(AGENT_INFO_FIELD_DESC);
          oprot.writeString(struct.agentInfo);
          oprot.writeFieldEnd();
        }
      }
      if (struct.replPolicy != null) {
        if (struct.isSetReplPolicy()) {
          oprot.writeFieldBegin(REPL_POLICY_FIELD_DESC);
          oprot.writeString(struct.replPolicy);
          oprot.writeFieldEnd();
        }
      }
      if (struct.replSrcTxnIds != null) {
        if (struct.isSetReplSrcTxnIds()) {
          oprot.writeFieldBegin(REPL_SRC_TXN_IDS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.replSrcTxnIds.size()));
            for (long _iter613 : struct.replSrcTxnIds)
            {
              oprot.writeI64(_iter613);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.txn_type != null) {
        if (struct.isSetTxn_type()) {
          oprot.writeFieldBegin(TXN_TYPE_FIELD_DESC);
          oprot.writeI32(struct.txn_type.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class OpenTxnRequestTupleSchemeFactory implements SchemeFactory {
    public OpenTxnRequestTupleScheme getScheme() {
      return new OpenTxnRequestTupleScheme();
    }
  }

  private static class OpenTxnRequestTupleScheme extends TupleScheme<OpenTxnRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, OpenTxnRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.num_txns);
      oprot.writeString(struct.user);
      oprot.writeString(struct.hostname);
      BitSet optionals = new BitSet();
      if (struct.isSetAgentInfo()) {
        optionals.set(0);
      }
      if (struct.isSetReplPolicy()) {
        optionals.set(1);
      }
      if (struct.isSetReplSrcTxnIds()) {
        optionals.set(2);
      }
      if (struct.isSetTxn_type()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetAgentInfo()) {
        oprot.writeString(struct.agentInfo);
      }
      if (struct.isSetReplPolicy()) {
        oprot.writeString(struct.replPolicy);
      }
      if (struct.isSetReplSrcTxnIds()) {
        {
          oprot.writeI32(struct.replSrcTxnIds.size());
          for (long _iter614 : struct.replSrcTxnIds)
          {
            oprot.writeI64(_iter614);
          }
        }
      }
      if (struct.isSetTxn_type()) {
        oprot.writeI32(struct.txn_type.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, OpenTxnRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.num_txns = iprot.readI32();
      struct.setNum_txnsIsSet(true);
      struct.user = iprot.readString();
      struct.setUserIsSet(true);
      struct.hostname = iprot.readString();
      struct.setHostnameIsSet(true);
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.agentInfo = iprot.readString();
        struct.setAgentInfoIsSet(true);
      }
      if (incoming.get(1)) {
        struct.replPolicy = iprot.readString();
        struct.setReplPolicyIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list615 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.replSrcTxnIds = new ArrayList<Long>(_list615.size);
          long _elem616;
          for (int _i617 = 0; _i617 < _list615.size; ++_i617)
          {
            _elem616 = iprot.readI64();
            struct.replSrcTxnIds.add(_elem616);
          }
        }
        struct.setReplSrcTxnIdsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.txn_type = org.apache.hadoop.hive.metastore.api.TxnType.findByValue(iprot.readI32());
        struct.setTxn_typeIsSet(true);
      }
    }
  }

}

