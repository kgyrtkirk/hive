/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class AllocateTableWriteIdsRequest implements org.apache.thrift.TBase<AllocateTableWriteIdsRequest, AllocateTableWriteIdsRequest._Fields>, java.io.Serializable, Cloneable, Comparable<AllocateTableWriteIdsRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AllocateTableWriteIdsRequest");

  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tableName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TXN_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("txnIds", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField REPL_POLICY_FIELD_DESC = new org.apache.thrift.protocol.TField("replPolicy", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField SRC_TXN_TO_WRITE_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("srcTxnToWriteIdList", org.apache.thrift.protocol.TType.LIST, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AllocateTableWriteIdsRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AllocateTableWriteIdsRequestTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable java.lang.String dbName; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String tableName; // required
  private @org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> txnIds; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String replPolicy; // optional
  private @org.apache.thrift.annotation.Nullable java.util.List<TxnToWriteId> srcTxnToWriteIdList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_NAME((short)1, "dbName"),
    TABLE_NAME((short)2, "tableName"),
    TXN_IDS((short)3, "txnIds"),
    REPL_POLICY((short)4, "replPolicy"),
    SRC_TXN_TO_WRITE_ID_LIST((short)5, "srcTxnToWriteIdList");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DB_NAME
          return DB_NAME;
        case 2: // TABLE_NAME
          return TABLE_NAME;
        case 3: // TXN_IDS
          return TXN_IDS;
        case 4: // REPL_POLICY
          return REPL_POLICY;
        case 5: // SRC_TXN_TO_WRITE_ID_LIST
          return SRC_TXN_TO_WRITE_ID_LIST;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.TXN_IDS,_Fields.REPL_POLICY,_Fields.SRC_TXN_TO_WRITE_ID_LIST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("tableName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TXN_IDS, new org.apache.thrift.meta_data.FieldMetaData("txnIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.REPL_POLICY, new org.apache.thrift.meta_data.FieldMetaData("replPolicy", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SRC_TXN_TO_WRITE_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("srcTxnToWriteIdList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TxnToWriteId.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AllocateTableWriteIdsRequest.class, metaDataMap);
  }

  public AllocateTableWriteIdsRequest() {
  }

  public AllocateTableWriteIdsRequest(
    java.lang.String dbName,
    java.lang.String tableName)
  {
    this();
    this.dbName = dbName;
    this.tableName = tableName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AllocateTableWriteIdsRequest(AllocateTableWriteIdsRequest other) {
    if (other.isSetDbName()) {
      this.dbName = other.dbName;
    }
    if (other.isSetTableName()) {
      this.tableName = other.tableName;
    }
    if (other.isSetTxnIds()) {
      java.util.List<java.lang.Long> __this__txnIds = new java.util.ArrayList<java.lang.Long>(other.txnIds);
      this.txnIds = __this__txnIds;
    }
    if (other.isSetReplPolicy()) {
      this.replPolicy = other.replPolicy;
    }
    if (other.isSetSrcTxnToWriteIdList()) {
      java.util.List<TxnToWriteId> __this__srcTxnToWriteIdList = new java.util.ArrayList<TxnToWriteId>(other.srcTxnToWriteIdList.size());
      for (TxnToWriteId other_element : other.srcTxnToWriteIdList) {
        __this__srcTxnToWriteIdList.add(new TxnToWriteId(other_element));
      }
      this.srcTxnToWriteIdList = __this__srcTxnToWriteIdList;
    }
  }

  public AllocateTableWriteIdsRequest deepCopy() {
    return new AllocateTableWriteIdsRequest(this);
  }

  @Override
  public void clear() {
    this.dbName = null;
    this.tableName = null;
    this.txnIds = null;
    this.replPolicy = null;
    this.srcTxnToWriteIdList = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDbName() {
    return this.dbName;
  }

  public void setDbName(@org.apache.thrift.annotation.Nullable java.lang.String dbName) {
    this.dbName = dbName;
  }

  public void unsetDbName() {
    this.dbName = null;
  }

  /** Returns true if field dbName is set (has been assigned a value) and false otherwise */
  public boolean isSetDbName() {
    return this.dbName != null;
  }

  public void setDbNameIsSet(boolean value) {
    if (!value) {
      this.dbName = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getTableName() {
    return this.tableName;
  }

  public void setTableName(@org.apache.thrift.annotation.Nullable java.lang.String tableName) {
    this.tableName = tableName;
  }

  public void unsetTableName() {
    this.tableName = null;
  }

  /** Returns true if field tableName is set (has been assigned a value) and false otherwise */
  public boolean isSetTableName() {
    return this.tableName != null;
  }

  public void setTableNameIsSet(boolean value) {
    if (!value) {
      this.tableName = null;
    }
  }

  public int getTxnIdsSize() {
    return (this.txnIds == null) ? 0 : this.txnIds.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.lang.Long> getTxnIdsIterator() {
    return (this.txnIds == null) ? null : this.txnIds.iterator();
  }

  public void addToTxnIds(long elem) {
    if (this.txnIds == null) {
      this.txnIds = new java.util.ArrayList<java.lang.Long>();
    }
    this.txnIds.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.lang.Long> getTxnIds() {
    return this.txnIds;
  }

  public void setTxnIds(@org.apache.thrift.annotation.Nullable java.util.List<java.lang.Long> txnIds) {
    this.txnIds = txnIds;
  }

  public void unsetTxnIds() {
    this.txnIds = null;
  }

  /** Returns true if field txnIds is set (has been assigned a value) and false otherwise */
  public boolean isSetTxnIds() {
    return this.txnIds != null;
  }

  public void setTxnIdsIsSet(boolean value) {
    if (!value) {
      this.txnIds = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getReplPolicy() {
    return this.replPolicy;
  }

  public void setReplPolicy(@org.apache.thrift.annotation.Nullable java.lang.String replPolicy) {
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

  public int getSrcTxnToWriteIdListSize() {
    return (this.srcTxnToWriteIdList == null) ? 0 : this.srcTxnToWriteIdList.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<TxnToWriteId> getSrcTxnToWriteIdListIterator() {
    return (this.srcTxnToWriteIdList == null) ? null : this.srcTxnToWriteIdList.iterator();
  }

  public void addToSrcTxnToWriteIdList(TxnToWriteId elem) {
    if (this.srcTxnToWriteIdList == null) {
      this.srcTxnToWriteIdList = new java.util.ArrayList<TxnToWriteId>();
    }
    this.srcTxnToWriteIdList.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<TxnToWriteId> getSrcTxnToWriteIdList() {
    return this.srcTxnToWriteIdList;
  }

  public void setSrcTxnToWriteIdList(@org.apache.thrift.annotation.Nullable java.util.List<TxnToWriteId> srcTxnToWriteIdList) {
    this.srcTxnToWriteIdList = srcTxnToWriteIdList;
  }

  public void unsetSrcTxnToWriteIdList() {
    this.srcTxnToWriteIdList = null;
  }

  /** Returns true if field srcTxnToWriteIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetSrcTxnToWriteIdList() {
    return this.srcTxnToWriteIdList != null;
  }

  public void setSrcTxnToWriteIdListIsSet(boolean value) {
    if (!value) {
      this.srcTxnToWriteIdList = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case DB_NAME:
      if (value == null) {
        unsetDbName();
      } else {
        setDbName((java.lang.String)value);
      }
      break;

    case TABLE_NAME:
      if (value == null) {
        unsetTableName();
      } else {
        setTableName((java.lang.String)value);
      }
      break;

    case TXN_IDS:
      if (value == null) {
        unsetTxnIds();
      } else {
        setTxnIds((java.util.List<java.lang.Long>)value);
      }
      break;

    case REPL_POLICY:
      if (value == null) {
        unsetReplPolicy();
      } else {
        setReplPolicy((java.lang.String)value);
      }
      break;

    case SRC_TXN_TO_WRITE_ID_LIST:
      if (value == null) {
        unsetSrcTxnToWriteIdList();
      } else {
        setSrcTxnToWriteIdList((java.util.List<TxnToWriteId>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DB_NAME:
      return getDbName();

    case TABLE_NAME:
      return getTableName();

    case TXN_IDS:
      return getTxnIds();

    case REPL_POLICY:
      return getReplPolicy();

    case SRC_TXN_TO_WRITE_ID_LIST:
      return getSrcTxnToWriteIdList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DB_NAME:
      return isSetDbName();
    case TABLE_NAME:
      return isSetTableName();
    case TXN_IDS:
      return isSetTxnIds();
    case REPL_POLICY:
      return isSetReplPolicy();
    case SRC_TXN_TO_WRITE_ID_LIST:
      return isSetSrcTxnToWriteIdList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof AllocateTableWriteIdsRequest)
      return this.equals((AllocateTableWriteIdsRequest)that);
    return false;
  }

  public boolean equals(AllocateTableWriteIdsRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_dbName = true && this.isSetDbName();
    boolean that_present_dbName = true && that.isSetDbName();
    if (this_present_dbName || that_present_dbName) {
      if (!(this_present_dbName && that_present_dbName))
        return false;
      if (!this.dbName.equals(that.dbName))
        return false;
    }

    boolean this_present_tableName = true && this.isSetTableName();
    boolean that_present_tableName = true && that.isSetTableName();
    if (this_present_tableName || that_present_tableName) {
      if (!(this_present_tableName && that_present_tableName))
        return false;
      if (!this.tableName.equals(that.tableName))
        return false;
    }

    boolean this_present_txnIds = true && this.isSetTxnIds();
    boolean that_present_txnIds = true && that.isSetTxnIds();
    if (this_present_txnIds || that_present_txnIds) {
      if (!(this_present_txnIds && that_present_txnIds))
        return false;
      if (!this.txnIds.equals(that.txnIds))
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

    boolean this_present_srcTxnToWriteIdList = true && this.isSetSrcTxnToWriteIdList();
    boolean that_present_srcTxnToWriteIdList = true && that.isSetSrcTxnToWriteIdList();
    if (this_present_srcTxnToWriteIdList || that_present_srcTxnToWriteIdList) {
      if (!(this_present_srcTxnToWriteIdList && that_present_srcTxnToWriteIdList))
        return false;
      if (!this.srcTxnToWriteIdList.equals(that.srcTxnToWriteIdList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDbName()) ? 131071 : 524287);
    if (isSetDbName())
      hashCode = hashCode * 8191 + dbName.hashCode();

    hashCode = hashCode * 8191 + ((isSetTableName()) ? 131071 : 524287);
    if (isSetTableName())
      hashCode = hashCode * 8191 + tableName.hashCode();

    hashCode = hashCode * 8191 + ((isSetTxnIds()) ? 131071 : 524287);
    if (isSetTxnIds())
      hashCode = hashCode * 8191 + txnIds.hashCode();

    hashCode = hashCode * 8191 + ((isSetReplPolicy()) ? 131071 : 524287);
    if (isSetReplPolicy())
      hashCode = hashCode * 8191 + replPolicy.hashCode();

    hashCode = hashCode * 8191 + ((isSetSrcTxnToWriteIdList()) ? 131071 : 524287);
    if (isSetSrcTxnToWriteIdList())
      hashCode = hashCode * 8191 + srcTxnToWriteIdList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(AllocateTableWriteIdsRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDbName()).compareTo(other.isSetDbName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbName, other.dbName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTableName()).compareTo(other.isSetTableName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTableName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tableName, other.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTxnIds()).compareTo(other.isSetTxnIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxnIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txnIds, other.txnIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetReplPolicy()).compareTo(other.isSetReplPolicy());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReplPolicy()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.replPolicy, other.replPolicy);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSrcTxnToWriteIdList()).compareTo(other.isSetSrcTxnToWriteIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSrcTxnToWriteIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.srcTxnToWriteIdList, other.srcTxnToWriteIdList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("AllocateTableWriteIdsRequest(");
    boolean first = true;

    sb.append("dbName:");
    if (this.dbName == null) {
      sb.append("null");
    } else {
      sb.append(this.dbName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tableName:");
    if (this.tableName == null) {
      sb.append("null");
    } else {
      sb.append(this.tableName);
    }
    first = false;
    if (isSetTxnIds()) {
      if (!first) sb.append(", ");
      sb.append("txnIds:");
      if (this.txnIds == null) {
        sb.append("null");
      } else {
        sb.append(this.txnIds);
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
    if (isSetSrcTxnToWriteIdList()) {
      if (!first) sb.append(", ");
      sb.append("srcTxnToWriteIdList:");
      if (this.srcTxnToWriteIdList == null) {
        sb.append("null");
      } else {
        sb.append(this.srcTxnToWriteIdList);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetDbName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'dbName' is unset! Struct:" + toString());
    }

    if (!isSetTableName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tableName' is unset! Struct:" + toString());
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AllocateTableWriteIdsRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AllocateTableWriteIdsRequestStandardScheme getScheme() {
      return new AllocateTableWriteIdsRequestStandardScheme();
    }
  }

  private static class AllocateTableWriteIdsRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<AllocateTableWriteIdsRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AllocateTableWriteIdsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbName = iprot.readString();
              struct.setDbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TABLE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tableName = iprot.readString();
              struct.setTableNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TXN_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list764 = iprot.readListBegin();
                struct.txnIds = new java.util.ArrayList<java.lang.Long>(_list764.size);
                long _elem765;
                for (int _i766 = 0; _i766 < _list764.size; ++_i766)
                {
                  _elem765 = iprot.readI64();
                  struct.txnIds.add(_elem765);
                }
                iprot.readListEnd();
              }
              struct.setTxnIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // REPL_POLICY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.replPolicy = iprot.readString();
              struct.setReplPolicyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SRC_TXN_TO_WRITE_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list767 = iprot.readListBegin();
                struct.srcTxnToWriteIdList = new java.util.ArrayList<TxnToWriteId>(_list767.size);
                @org.apache.thrift.annotation.Nullable TxnToWriteId _elem768;
                for (int _i769 = 0; _i769 < _list767.size; ++_i769)
                {
                  _elem768 = new TxnToWriteId();
                  _elem768.read(iprot);
                  struct.srcTxnToWriteIdList.add(_elem768);
                }
                iprot.readListEnd();
              }
              struct.setSrcTxnToWriteIdListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AllocateTableWriteIdsRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dbName != null) {
        oprot.writeFieldBegin(DB_NAME_FIELD_DESC);
        oprot.writeString(struct.dbName);
        oprot.writeFieldEnd();
      }
      if (struct.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeString(struct.tableName);
        oprot.writeFieldEnd();
      }
      if (struct.txnIds != null) {
        if (struct.isSetTxnIds()) {
          oprot.writeFieldBegin(TXN_IDS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.txnIds.size()));
            for (long _iter770 : struct.txnIds)
            {
              oprot.writeI64(_iter770);
            }
            oprot.writeListEnd();
          }
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
      if (struct.srcTxnToWriteIdList != null) {
        if (struct.isSetSrcTxnToWriteIdList()) {
          oprot.writeFieldBegin(SRC_TXN_TO_WRITE_ID_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.srcTxnToWriteIdList.size()));
            for (TxnToWriteId _iter771 : struct.srcTxnToWriteIdList)
            {
              _iter771.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AllocateTableWriteIdsRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AllocateTableWriteIdsRequestTupleScheme getScheme() {
      return new AllocateTableWriteIdsRequestTupleScheme();
    }
  }

  private static class AllocateTableWriteIdsRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<AllocateTableWriteIdsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AllocateTableWriteIdsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.dbName);
      oprot.writeString(struct.tableName);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTxnIds()) {
        optionals.set(0);
      }
      if (struct.isSetReplPolicy()) {
        optionals.set(1);
      }
      if (struct.isSetSrcTxnToWriteIdList()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetTxnIds()) {
        {
          oprot.writeI32(struct.txnIds.size());
          for (long _iter772 : struct.txnIds)
          {
            oprot.writeI64(_iter772);
          }
        }
      }
      if (struct.isSetReplPolicy()) {
        oprot.writeString(struct.replPolicy);
      }
      if (struct.isSetSrcTxnToWriteIdList()) {
        {
          oprot.writeI32(struct.srcTxnToWriteIdList.size());
          for (TxnToWriteId _iter773 : struct.srcTxnToWriteIdList)
          {
            _iter773.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AllocateTableWriteIdsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.dbName = iprot.readString();
      struct.setDbNameIsSet(true);
      struct.tableName = iprot.readString();
      struct.setTableNameIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list774 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.txnIds = new java.util.ArrayList<java.lang.Long>(_list774.size);
          long _elem775;
          for (int _i776 = 0; _i776 < _list774.size; ++_i776)
          {
            _elem775 = iprot.readI64();
            struct.txnIds.add(_elem775);
          }
        }
        struct.setTxnIdsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.replPolicy = iprot.readString();
        struct.setReplPolicyIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list777 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.srcTxnToWriteIdList = new java.util.ArrayList<TxnToWriteId>(_list777.size);
          @org.apache.thrift.annotation.Nullable TxnToWriteId _elem778;
          for (int _i779 = 0; _i779 < _list777.size; ++_i779)
          {
            _elem778 = new TxnToWriteId();
            _elem778.read(iprot);
            struct.srcTxnToWriteIdList.add(_elem778);
          }
        }
        struct.setSrcTxnToWriteIdListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

