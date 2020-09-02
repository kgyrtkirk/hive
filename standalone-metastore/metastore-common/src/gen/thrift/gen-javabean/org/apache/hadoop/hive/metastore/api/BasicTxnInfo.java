/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class BasicTxnInfo implements org.apache.thrift.TBase<BasicTxnInfo, BasicTxnInfo._Fields>, java.io.Serializable, Cloneable, Comparable<BasicTxnInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BasicTxnInfo");

  private static final org.apache.thrift.protocol.TField ISNULL_FIELD_DESC = new org.apache.thrift.protocol.TField("isnull", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("time", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TXNID_FIELD_DESC = new org.apache.thrift.protocol.TField("txnid", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField DBNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbname", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField TABLENAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tablename", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField PARTITIONNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("partitionname", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new BasicTxnInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new BasicTxnInfoTupleSchemeFactory();

  private boolean isnull; // required
  private long time; // optional
  private long txnid; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String dbname; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String tablename; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String partitionname; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ISNULL((short)1, "isnull"),
    TIME((short)2, "time"),
    TXNID((short)3, "txnid"),
    DBNAME((short)4, "dbname"),
    TABLENAME((short)5, "tablename"),
    PARTITIONNAME((short)6, "partitionname");

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
        case 1: // ISNULL
          return ISNULL;
        case 2: // TIME
          return TIME;
        case 3: // TXNID
          return TXNID;
        case 4: // DBNAME
          return DBNAME;
        case 5: // TABLENAME
          return TABLENAME;
        case 6: // PARTITIONNAME
          return PARTITIONNAME;
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
  private static final int __ISNULL_ISSET_ID = 0;
  private static final int __TIME_ISSET_ID = 1;
  private static final int __TXNID_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TIME,_Fields.TXNID,_Fields.DBNAME,_Fields.TABLENAME,_Fields.PARTITIONNAME};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ISNULL, new org.apache.thrift.meta_data.FieldMetaData("isnull", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("time", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TXNID, new org.apache.thrift.meta_data.FieldMetaData("txnid", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.DBNAME, new org.apache.thrift.meta_data.FieldMetaData("dbname", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLENAME, new org.apache.thrift.meta_data.FieldMetaData("tablename", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARTITIONNAME, new org.apache.thrift.meta_data.FieldMetaData("partitionname", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BasicTxnInfo.class, metaDataMap);
  }

  public BasicTxnInfo() {
  }

  public BasicTxnInfo(
    boolean isnull)
  {
    this();
    this.isnull = isnull;
    setIsnullIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BasicTxnInfo(BasicTxnInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.isnull = other.isnull;
    this.time = other.time;
    this.txnid = other.txnid;
    if (other.isSetDbname()) {
      this.dbname = other.dbname;
    }
    if (other.isSetTablename()) {
      this.tablename = other.tablename;
    }
    if (other.isSetPartitionname()) {
      this.partitionname = other.partitionname;
    }
  }

  public BasicTxnInfo deepCopy() {
    return new BasicTxnInfo(this);
  }

  @Override
  public void clear() {
    setIsnullIsSet(false);
    this.isnull = false;
    setTimeIsSet(false);
    this.time = 0;
    setTxnidIsSet(false);
    this.txnid = 0;
    this.dbname = null;
    this.tablename = null;
    this.partitionname = null;
  }

  public boolean isIsnull() {
    return this.isnull;
  }

  public void setIsnull(boolean isnull) {
    this.isnull = isnull;
    setIsnullIsSet(true);
  }

  public void unsetIsnull() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  /** Returns true if field isnull is set (has been assigned a value) and false otherwise */
  public boolean isSetIsnull() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  public void setIsnullIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISNULL_ISSET_ID, value);
  }

  public long getTime() {
    return this.time;
  }

  public void setTime(long time) {
    this.time = time;
    setTimeIsSet(true);
  }

  public void unsetTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  /** Returns true if field time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIME_ISSET_ID);
  }

  public void setTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIME_ISSET_ID, value);
  }

  public long getTxnid() {
    return this.txnid;
  }

  public void setTxnid(long txnid) {
    this.txnid = txnid;
    setTxnidIsSet(true);
  }

  public void unsetTxnid() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TXNID_ISSET_ID);
  }

  /** Returns true if field txnid is set (has been assigned a value) and false otherwise */
  public boolean isSetTxnid() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TXNID_ISSET_ID);
  }

  public void setTxnidIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TXNID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDbname() {
    return this.dbname;
  }

  public void setDbname(@org.apache.thrift.annotation.Nullable java.lang.String dbname) {
    this.dbname = dbname;
  }

  public void unsetDbname() {
    this.dbname = null;
  }

  /** Returns true if field dbname is set (has been assigned a value) and false otherwise */
  public boolean isSetDbname() {
    return this.dbname != null;
  }

  public void setDbnameIsSet(boolean value) {
    if (!value) {
      this.dbname = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getTablename() {
    return this.tablename;
  }

  public void setTablename(@org.apache.thrift.annotation.Nullable java.lang.String tablename) {
    this.tablename = tablename;
  }

  public void unsetTablename() {
    this.tablename = null;
  }

  /** Returns true if field tablename is set (has been assigned a value) and false otherwise */
  public boolean isSetTablename() {
    return this.tablename != null;
  }

  public void setTablenameIsSet(boolean value) {
    if (!value) {
      this.tablename = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getPartitionname() {
    return this.partitionname;
  }

  public void setPartitionname(@org.apache.thrift.annotation.Nullable java.lang.String partitionname) {
    this.partitionname = partitionname;
  }

  public void unsetPartitionname() {
    this.partitionname = null;
  }

  /** Returns true if field partitionname is set (has been assigned a value) and false otherwise */
  public boolean isSetPartitionname() {
    return this.partitionname != null;
  }

  public void setPartitionnameIsSet(boolean value) {
    if (!value) {
      this.partitionname = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ISNULL:
      if (value == null) {
        unsetIsnull();
      } else {
        setIsnull((java.lang.Boolean)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((java.lang.Long)value);
      }
      break;

    case TXNID:
      if (value == null) {
        unsetTxnid();
      } else {
        setTxnid((java.lang.Long)value);
      }
      break;

    case DBNAME:
      if (value == null) {
        unsetDbname();
      } else {
        setDbname((java.lang.String)value);
      }
      break;

    case TABLENAME:
      if (value == null) {
        unsetTablename();
      } else {
        setTablename((java.lang.String)value);
      }
      break;

    case PARTITIONNAME:
      if (value == null) {
        unsetPartitionname();
      } else {
        setPartitionname((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ISNULL:
      return isIsnull();

    case TIME:
      return getTime();

    case TXNID:
      return getTxnid();

    case DBNAME:
      return getDbname();

    case TABLENAME:
      return getTablename();

    case PARTITIONNAME:
      return getPartitionname();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ISNULL:
      return isSetIsnull();
    case TIME:
      return isSetTime();
    case TXNID:
      return isSetTxnid();
    case DBNAME:
      return isSetDbname();
    case TABLENAME:
      return isSetTablename();
    case PARTITIONNAME:
      return isSetPartitionname();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof BasicTxnInfo)
      return this.equals((BasicTxnInfo)that);
    return false;
  }

  public boolean equals(BasicTxnInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_isnull = true;
    boolean that_present_isnull = true;
    if (this_present_isnull || that_present_isnull) {
      if (!(this_present_isnull && that_present_isnull))
        return false;
      if (this.isnull != that.isnull)
        return false;
    }

    boolean this_present_time = true && this.isSetTime();
    boolean that_present_time = true && that.isSetTime();
    if (this_present_time || that_present_time) {
      if (!(this_present_time && that_present_time))
        return false;
      if (this.time != that.time)
        return false;
    }

    boolean this_present_txnid = true && this.isSetTxnid();
    boolean that_present_txnid = true && that.isSetTxnid();
    if (this_present_txnid || that_present_txnid) {
      if (!(this_present_txnid && that_present_txnid))
        return false;
      if (this.txnid != that.txnid)
        return false;
    }

    boolean this_present_dbname = true && this.isSetDbname();
    boolean that_present_dbname = true && that.isSetDbname();
    if (this_present_dbname || that_present_dbname) {
      if (!(this_present_dbname && that_present_dbname))
        return false;
      if (!this.dbname.equals(that.dbname))
        return false;
    }

    boolean this_present_tablename = true && this.isSetTablename();
    boolean that_present_tablename = true && that.isSetTablename();
    if (this_present_tablename || that_present_tablename) {
      if (!(this_present_tablename && that_present_tablename))
        return false;
      if (!this.tablename.equals(that.tablename))
        return false;
    }

    boolean this_present_partitionname = true && this.isSetPartitionname();
    boolean that_present_partitionname = true && that.isSetPartitionname();
    if (this_present_partitionname || that_present_partitionname) {
      if (!(this_present_partitionname && that_present_partitionname))
        return false;
      if (!this.partitionname.equals(that.partitionname))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isnull) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetTime()) ? 131071 : 524287);
    if (isSetTime())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(time);

    hashCode = hashCode * 8191 + ((isSetTxnid()) ? 131071 : 524287);
    if (isSetTxnid())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(txnid);

    hashCode = hashCode * 8191 + ((isSetDbname()) ? 131071 : 524287);
    if (isSetDbname())
      hashCode = hashCode * 8191 + dbname.hashCode();

    hashCode = hashCode * 8191 + ((isSetTablename()) ? 131071 : 524287);
    if (isSetTablename())
      hashCode = hashCode * 8191 + tablename.hashCode();

    hashCode = hashCode * 8191 + ((isSetPartitionname()) ? 131071 : 524287);
    if (isSetPartitionname())
      hashCode = hashCode * 8191 + partitionname.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(BasicTxnInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetIsnull()).compareTo(other.isSetIsnull());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsnull()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isnull, other.isnull);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time, other.time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTxnid()).compareTo(other.isSetTxnid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTxnid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.txnid, other.txnid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDbname()).compareTo(other.isSetDbname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbname, other.dbname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTablename()).compareTo(other.isSetTablename());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTablename()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tablename, other.tablename);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPartitionname()).compareTo(other.isSetPartitionname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartitionname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partitionname, other.partitionname);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("BasicTxnInfo(");
    boolean first = true;

    sb.append("isnull:");
    sb.append(this.isnull);
    first = false;
    if (isSetTime()) {
      if (!first) sb.append(", ");
      sb.append("time:");
      sb.append(this.time);
      first = false;
    }
    if (isSetTxnid()) {
      if (!first) sb.append(", ");
      sb.append("txnid:");
      sb.append(this.txnid);
      first = false;
    }
    if (isSetDbname()) {
      if (!first) sb.append(", ");
      sb.append("dbname:");
      if (this.dbname == null) {
        sb.append("null");
      } else {
        sb.append(this.dbname);
      }
      first = false;
    }
    if (isSetTablename()) {
      if (!first) sb.append(", ");
      sb.append("tablename:");
      if (this.tablename == null) {
        sb.append("null");
      } else {
        sb.append(this.tablename);
      }
      first = false;
    }
    if (isSetPartitionname()) {
      if (!first) sb.append(", ");
      sb.append("partitionname:");
      if (this.partitionname == null) {
        sb.append("null");
      } else {
        sb.append(this.partitionname);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetIsnull()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'isnull' is unset! Struct:" + toString());
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class BasicTxnInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BasicTxnInfoStandardScheme getScheme() {
      return new BasicTxnInfoStandardScheme();
    }
  }

  private static class BasicTxnInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<BasicTxnInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BasicTxnInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ISNULL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isnull = iprot.readBool();
              struct.setIsnullIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.time = iprot.readI64();
              struct.setTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TXNID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.txnid = iprot.readI64();
              struct.setTxnidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DBNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbname = iprot.readString();
              struct.setDbnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TABLENAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tablename = iprot.readString();
              struct.setTablenameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PARTITIONNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.partitionname = iprot.readString();
              struct.setPartitionnameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BasicTxnInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ISNULL_FIELD_DESC);
      oprot.writeBool(struct.isnull);
      oprot.writeFieldEnd();
      if (struct.isSetTime()) {
        oprot.writeFieldBegin(TIME_FIELD_DESC);
        oprot.writeI64(struct.time);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTxnid()) {
        oprot.writeFieldBegin(TXNID_FIELD_DESC);
        oprot.writeI64(struct.txnid);
        oprot.writeFieldEnd();
      }
      if (struct.dbname != null) {
        if (struct.isSetDbname()) {
          oprot.writeFieldBegin(DBNAME_FIELD_DESC);
          oprot.writeString(struct.dbname);
          oprot.writeFieldEnd();
        }
      }
      if (struct.tablename != null) {
        if (struct.isSetTablename()) {
          oprot.writeFieldBegin(TABLENAME_FIELD_DESC);
          oprot.writeString(struct.tablename);
          oprot.writeFieldEnd();
        }
      }
      if (struct.partitionname != null) {
        if (struct.isSetPartitionname()) {
          oprot.writeFieldBegin(PARTITIONNAME_FIELD_DESC);
          oprot.writeString(struct.partitionname);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BasicTxnInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BasicTxnInfoTupleScheme getScheme() {
      return new BasicTxnInfoTupleScheme();
    }
  }

  private static class BasicTxnInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<BasicTxnInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BasicTxnInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeBool(struct.isnull);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTime()) {
        optionals.set(0);
      }
      if (struct.isSetTxnid()) {
        optionals.set(1);
      }
      if (struct.isSetDbname()) {
        optionals.set(2);
      }
      if (struct.isSetTablename()) {
        optionals.set(3);
      }
      if (struct.isSetPartitionname()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetTime()) {
        oprot.writeI64(struct.time);
      }
      if (struct.isSetTxnid()) {
        oprot.writeI64(struct.txnid);
      }
      if (struct.isSetDbname()) {
        oprot.writeString(struct.dbname);
      }
      if (struct.isSetTablename()) {
        oprot.writeString(struct.tablename);
      }
      if (struct.isSetPartitionname()) {
        oprot.writeString(struct.partitionname);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BasicTxnInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.isnull = iprot.readBool();
      struct.setIsnullIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.time = iprot.readI64();
        struct.setTimeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.txnid = iprot.readI64();
        struct.setTxnidIsSet(true);
      }
      if (incoming.get(2)) {
        struct.dbname = iprot.readString();
        struct.setDbnameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.tablename = iprot.readString();
        struct.setTablenameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.partitionname = iprot.readString();
        struct.setPartitionnameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

