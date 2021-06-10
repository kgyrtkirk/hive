/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class ColumnStatisticsDesc implements org.apache.thrift.TBase<ColumnStatisticsDesc, ColumnStatisticsDesc._Fields>, java.io.Serializable, Cloneable, Comparable<ColumnStatisticsDesc> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ColumnStatisticsDesc");

  private static final org.apache.thrift.protocol.TField IS_TBL_LEVEL_FIELD_DESC = new org.apache.thrift.protocol.TField("isTblLevel", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tableName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PART_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("partName", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField LAST_ANALYZED_FIELD_DESC = new org.apache.thrift.protocol.TField("lastAnalyzed", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField CAT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("catName", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnStatisticsDescStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnStatisticsDescTupleSchemeFactory();

  private boolean isTblLevel; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String dbName; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String tableName; // required
  private @org.apache.thrift.annotation.Nullable java.lang.String partName; // optional
  private long lastAnalyzed; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String catName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    IS_TBL_LEVEL((short)1, "isTblLevel"),
    DB_NAME((short)2, "dbName"),
    TABLE_NAME((short)3, "tableName"),
    PART_NAME((short)4, "partName"),
    LAST_ANALYZED((short)5, "lastAnalyzed"),
    CAT_NAME((short)6, "catName");

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
        case 1: // IS_TBL_LEVEL
          return IS_TBL_LEVEL;
        case 2: // DB_NAME
          return DB_NAME;
        case 3: // TABLE_NAME
          return TABLE_NAME;
        case 4: // PART_NAME
          return PART_NAME;
        case 5: // LAST_ANALYZED
          return LAST_ANALYZED;
        case 6: // CAT_NAME
          return CAT_NAME;
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
  private static final int __ISTBLLEVEL_ISSET_ID = 0;
  private static final int __LASTANALYZED_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.PART_NAME,_Fields.LAST_ANALYZED,_Fields.CAT_NAME};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.IS_TBL_LEVEL, new org.apache.thrift.meta_data.FieldMetaData("isTblLevel", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("tableName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PART_NAME, new org.apache.thrift.meta_data.FieldMetaData("partName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LAST_ANALYZED, new org.apache.thrift.meta_data.FieldMetaData("lastAnalyzed", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CAT_NAME, new org.apache.thrift.meta_data.FieldMetaData("catName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ColumnStatisticsDesc.class, metaDataMap);
  }

  public ColumnStatisticsDesc() {
  }

  public ColumnStatisticsDesc(
    boolean isTblLevel,
    java.lang.String dbName,
    java.lang.String tableName)
  {
    this();
    this.isTblLevel = isTblLevel;
    setIsTblLevelIsSet(true);
    this.dbName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(dbName);
    this.tableName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(tableName);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ColumnStatisticsDesc(ColumnStatisticsDesc other) {
    __isset_bitfield = other.__isset_bitfield;
    this.isTblLevel = other.isTblLevel;
    if (other.isSetDbName()) {
      this.dbName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(other.dbName);
    }
    if (other.isSetTableName()) {
      this.tableName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(other.tableName);
    }
    if (other.isSetPartName()) {
      this.partName = other.partName;
    }
    this.lastAnalyzed = other.lastAnalyzed;
    if (other.isSetCatName()) {
      this.catName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(other.catName);
    }
  }

  public ColumnStatisticsDesc deepCopy() {
    return new ColumnStatisticsDesc(this);
  }

  @Override
  public void clear() {
    setIsTblLevelIsSet(false);
    this.isTblLevel = false;
    this.dbName = null;
    this.tableName = null;
    this.partName = null;
    setLastAnalyzedIsSet(false);
    this.lastAnalyzed = 0;
    this.catName = null;
  }

  public boolean isIsTblLevel() {
    return this.isTblLevel;
  }

  public void setIsTblLevel(boolean isTblLevel) {
    this.isTblLevel = isTblLevel;
    setIsTblLevelIsSet(true);
  }

  public void unsetIsTblLevel() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISTBLLEVEL_ISSET_ID);
  }

  /** Returns true if field isTblLevel is set (has been assigned a value) and false otherwise */
  public boolean isSetIsTblLevel() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISTBLLEVEL_ISSET_ID);
  }

  public void setIsTblLevelIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISTBLLEVEL_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDbName() {
    return this.dbName;
  }

  public void setDbName(@org.apache.thrift.annotation.Nullable java.lang.String dbName) {
    this.dbName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(dbName);
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
    this.tableName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(tableName);
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

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getPartName() {
    return this.partName;
  }

  public void setPartName(@org.apache.thrift.annotation.Nullable java.lang.String partName) {
    this.partName = partName;
  }

  public void unsetPartName() {
    this.partName = null;
  }

  /** Returns true if field partName is set (has been assigned a value) and false otherwise */
  public boolean isSetPartName() {
    return this.partName != null;
  }

  public void setPartNameIsSet(boolean value) {
    if (!value) {
      this.partName = null;
    }
  }

  public long getLastAnalyzed() {
    return this.lastAnalyzed;
  }

  public void setLastAnalyzed(long lastAnalyzed) {
    this.lastAnalyzed = lastAnalyzed;
    setLastAnalyzedIsSet(true);
  }

  public void unsetLastAnalyzed() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LASTANALYZED_ISSET_ID);
  }

  /** Returns true if field lastAnalyzed is set (has been assigned a value) and false otherwise */
  public boolean isSetLastAnalyzed() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LASTANALYZED_ISSET_ID);
  }

  public void setLastAnalyzedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LASTANALYZED_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getCatName() {
    return this.catName;
  }

  public void setCatName(@org.apache.thrift.annotation.Nullable java.lang.String catName) {
    this.catName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(catName);
  }

  public void unsetCatName() {
    this.catName = null;
  }

  /** Returns true if field catName is set (has been assigned a value) and false otherwise */
  public boolean isSetCatName() {
    return this.catName != null;
  }

  public void setCatNameIsSet(boolean value) {
    if (!value) {
      this.catName = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case IS_TBL_LEVEL:
      if (value == null) {
        unsetIsTblLevel();
      } else {
        setIsTblLevel((java.lang.Boolean)value);
      }
      break;

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

    case PART_NAME:
      if (value == null) {
        unsetPartName();
      } else {
        setPartName((java.lang.String)value);
      }
      break;

    case LAST_ANALYZED:
      if (value == null) {
        unsetLastAnalyzed();
      } else {
        setLastAnalyzed((java.lang.Long)value);
      }
      break;

    case CAT_NAME:
      if (value == null) {
        unsetCatName();
      } else {
        setCatName((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case IS_TBL_LEVEL:
      return isIsTblLevel();

    case DB_NAME:
      return getDbName();

    case TABLE_NAME:
      return getTableName();

    case PART_NAME:
      return getPartName();

    case LAST_ANALYZED:
      return getLastAnalyzed();

    case CAT_NAME:
      return getCatName();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case IS_TBL_LEVEL:
      return isSetIsTblLevel();
    case DB_NAME:
      return isSetDbName();
    case TABLE_NAME:
      return isSetTableName();
    case PART_NAME:
      return isSetPartName();
    case LAST_ANALYZED:
      return isSetLastAnalyzed();
    case CAT_NAME:
      return isSetCatName();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof ColumnStatisticsDesc)
      return this.equals((ColumnStatisticsDesc)that);
    return false;
  }

  public boolean equals(ColumnStatisticsDesc that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_isTblLevel = true;
    boolean that_present_isTblLevel = true;
    if (this_present_isTblLevel || that_present_isTblLevel) {
      if (!(this_present_isTblLevel && that_present_isTblLevel))
        return false;
      if (this.isTblLevel != that.isTblLevel)
        return false;
    }

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

    boolean this_present_partName = true && this.isSetPartName();
    boolean that_present_partName = true && that.isSetPartName();
    if (this_present_partName || that_present_partName) {
      if (!(this_present_partName && that_present_partName))
        return false;
      if (!this.partName.equals(that.partName))
        return false;
    }

    boolean this_present_lastAnalyzed = true && this.isSetLastAnalyzed();
    boolean that_present_lastAnalyzed = true && that.isSetLastAnalyzed();
    if (this_present_lastAnalyzed || that_present_lastAnalyzed) {
      if (!(this_present_lastAnalyzed && that_present_lastAnalyzed))
        return false;
      if (this.lastAnalyzed != that.lastAnalyzed)
        return false;
    }

    boolean this_present_catName = true && this.isSetCatName();
    boolean that_present_catName = true && that.isSetCatName();
    if (this_present_catName || that_present_catName) {
      if (!(this_present_catName && that_present_catName))
        return false;
      if (!this.catName.equals(that.catName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isTblLevel) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetDbName()) ? 131071 : 524287);
    if (isSetDbName())
      hashCode = hashCode * 8191 + dbName.hashCode();

    hashCode = hashCode * 8191 + ((isSetTableName()) ? 131071 : 524287);
    if (isSetTableName())
      hashCode = hashCode * 8191 + tableName.hashCode();

    hashCode = hashCode * 8191 + ((isSetPartName()) ? 131071 : 524287);
    if (isSetPartName())
      hashCode = hashCode * 8191 + partName.hashCode();

    hashCode = hashCode * 8191 + ((isSetLastAnalyzed()) ? 131071 : 524287);
    if (isSetLastAnalyzed())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(lastAnalyzed);

    hashCode = hashCode * 8191 + ((isSetCatName()) ? 131071 : 524287);
    if (isSetCatName())
      hashCode = hashCode * 8191 + catName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ColumnStatisticsDesc other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetIsTblLevel(), other.isSetIsTblLevel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsTblLevel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isTblLevel, other.isTblLevel);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetDbName(), other.isSetDbName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbName, other.dbName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTableName(), other.isSetTableName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTableName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tableName, other.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetPartName(), other.isSetPartName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partName, other.partName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetLastAnalyzed(), other.isSetLastAnalyzed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastAnalyzed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastAnalyzed, other.lastAnalyzed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetCatName(), other.isSetCatName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCatName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.catName, other.catName);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ColumnStatisticsDesc(");
    boolean first = true;

    sb.append("isTblLevel:");
    sb.append(this.isTblLevel);
    first = false;
    if (!first) sb.append(", ");
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
    if (isSetPartName()) {
      if (!first) sb.append(", ");
      sb.append("partName:");
      if (this.partName == null) {
        sb.append("null");
      } else {
        sb.append(this.partName);
      }
      first = false;
    }
    if (isSetLastAnalyzed()) {
      if (!first) sb.append(", ");
      sb.append("lastAnalyzed:");
      sb.append(this.lastAnalyzed);
      first = false;
    }
    if (isSetCatName()) {
      if (!first) sb.append(", ");
      sb.append("catName:");
      if (this.catName == null) {
        sb.append("null");
      } else {
        sb.append(this.catName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetIsTblLevel()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'isTblLevel' is unset! Struct:" + toString());
    }

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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ColumnStatisticsDescStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ColumnStatisticsDescStandardScheme getScheme() {
      return new ColumnStatisticsDescStandardScheme();
    }
  }

  private static class ColumnStatisticsDescStandardScheme extends org.apache.thrift.scheme.StandardScheme<ColumnStatisticsDesc> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ColumnStatisticsDesc struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // IS_TBL_LEVEL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isTblLevel = iprot.readBool();
              struct.setIsTblLevelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
              struct.setDbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TABLE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tableName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
              struct.setTableNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PART_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.partName = iprot.readString();
              struct.setPartNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LAST_ANALYZED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastAnalyzed = iprot.readI64();
              struct.setLastAnalyzedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CAT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.catName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
              struct.setCatNameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ColumnStatisticsDesc struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(IS_TBL_LEVEL_FIELD_DESC);
      oprot.writeBool(struct.isTblLevel);
      oprot.writeFieldEnd();
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
      if (struct.partName != null) {
        if (struct.isSetPartName()) {
          oprot.writeFieldBegin(PART_NAME_FIELD_DESC);
          oprot.writeString(struct.partName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetLastAnalyzed()) {
        oprot.writeFieldBegin(LAST_ANALYZED_FIELD_DESC);
        oprot.writeI64(struct.lastAnalyzed);
        oprot.writeFieldEnd();
      }
      if (struct.catName != null) {
        if (struct.isSetCatName()) {
          oprot.writeFieldBegin(CAT_NAME_FIELD_DESC);
          oprot.writeString(struct.catName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ColumnStatisticsDescTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ColumnStatisticsDescTupleScheme getScheme() {
      return new ColumnStatisticsDescTupleScheme();
    }
  }

  private static class ColumnStatisticsDescTupleScheme extends org.apache.thrift.scheme.TupleScheme<ColumnStatisticsDesc> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ColumnStatisticsDesc struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeBool(struct.isTblLevel);
      oprot.writeString(struct.dbName);
      oprot.writeString(struct.tableName);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPartName()) {
        optionals.set(0);
      }
      if (struct.isSetLastAnalyzed()) {
        optionals.set(1);
      }
      if (struct.isSetCatName()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetPartName()) {
        oprot.writeString(struct.partName);
      }
      if (struct.isSetLastAnalyzed()) {
        oprot.writeI64(struct.lastAnalyzed);
      }
      if (struct.isSetCatName()) {
        oprot.writeString(struct.catName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ColumnStatisticsDesc struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.isTblLevel = iprot.readBool();
      struct.setIsTblLevelIsSet(true);
      struct.dbName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
      struct.setDbNameIsSet(true);
      struct.tableName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
      struct.setTableNameIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.partName = iprot.readString();
        struct.setPartNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.lastAnalyzed = iprot.readI64();
        struct.setLastAnalyzedIsSet(true);
      }
      if (incoming.get(2)) {
        struct.catName = org.apache.hadoop.hive.metastore.utils.StringUtils.intern(iprot.readString());
        struct.setCatNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

