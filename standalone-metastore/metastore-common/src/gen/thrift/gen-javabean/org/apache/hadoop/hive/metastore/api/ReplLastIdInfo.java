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
public class ReplLastIdInfo implements org.apache.thrift.TBase<ReplLastIdInfo, ReplLastIdInfo._Fields>, java.io.Serializable, Cloneable, Comparable<ReplLastIdInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReplLastIdInfo");

  private static final org.apache.thrift.protocol.TField DATABASE_FIELD_DESC = new org.apache.thrift.protocol.TField("database", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField LAST_REPL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("lastReplId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TABLE_FIELD_DESC = new org.apache.thrift.protocol.TField("table", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CATALOG_FIELD_DESC = new org.apache.thrift.protocol.TField("catalog", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PARTITION_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("partitionList", org.apache.thrift.protocol.TType.LIST, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReplLastIdInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReplLastIdInfoTupleSchemeFactory());
  }

  private String database; // required
  private long lastReplId; // required
  private String table; // optional
  private String catalog; // optional
  private List<String> partitionList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DATABASE((short)1, "database"),
    LAST_REPL_ID((short)2, "lastReplId"),
    TABLE((short)3, "table"),
    CATALOG((short)4, "catalog"),
    PARTITION_LIST((short)5, "partitionList");

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
        case 1: // DATABASE
          return DATABASE;
        case 2: // LAST_REPL_ID
          return LAST_REPL_ID;
        case 3: // TABLE
          return TABLE;
        case 4: // CATALOG
          return CATALOG;
        case 5: // PARTITION_LIST
          return PARTITION_LIST;
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
  private static final int __LASTREPLID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TABLE,_Fields.CATALOG,_Fields.PARTITION_LIST};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DATABASE, new org.apache.thrift.meta_data.FieldMetaData("database", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LAST_REPL_ID, new org.apache.thrift.meta_data.FieldMetaData("lastReplId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TABLE, new org.apache.thrift.meta_data.FieldMetaData("table", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CATALOG, new org.apache.thrift.meta_data.FieldMetaData("catalog", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARTITION_LIST, new org.apache.thrift.meta_data.FieldMetaData("partitionList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReplLastIdInfo.class, metaDataMap);
  }

  public ReplLastIdInfo() {
  }

  public ReplLastIdInfo(
    String database,
    long lastReplId)
  {
    this();
    this.database = database;
    this.lastReplId = lastReplId;
    setLastReplIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReplLastIdInfo(ReplLastIdInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetDatabase()) {
      this.database = other.database;
    }
    this.lastReplId = other.lastReplId;
    if (other.isSetTable()) {
      this.table = other.table;
    }
    if (other.isSetCatalog()) {
      this.catalog = other.catalog;
    }
    if (other.isSetPartitionList()) {
      List<String> __this__partitionList = new ArrayList<String>(other.partitionList);
      this.partitionList = __this__partitionList;
    }
  }

  public ReplLastIdInfo deepCopy() {
    return new ReplLastIdInfo(this);
  }

  @Override
  public void clear() {
    this.database = null;
    setLastReplIdIsSet(false);
    this.lastReplId = 0;
    this.table = null;
    this.catalog = null;
    this.partitionList = null;
  }

  public String getDatabase() {
    return this.database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public void unsetDatabase() {
    this.database = null;
  }

  /** Returns true if field database is set (has been assigned a value) and false otherwise */
  public boolean isSetDatabase() {
    return this.database != null;
  }

  public void setDatabaseIsSet(boolean value) {
    if (!value) {
      this.database = null;
    }
  }

  public long getLastReplId() {
    return this.lastReplId;
  }

  public void setLastReplId(long lastReplId) {
    this.lastReplId = lastReplId;
    setLastReplIdIsSet(true);
  }

  public void unsetLastReplId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTREPLID_ISSET_ID);
  }

  /** Returns true if field lastReplId is set (has been assigned a value) and false otherwise */
  public boolean isSetLastReplId() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTREPLID_ISSET_ID);
  }

  public void setLastReplIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LASTREPLID_ISSET_ID, value);
  }

  public String getTable() {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public void unsetTable() {
    this.table = null;
  }

  /** Returns true if field table is set (has been assigned a value) and false otherwise */
  public boolean isSetTable() {
    return this.table != null;
  }

  public void setTableIsSet(boolean value) {
    if (!value) {
      this.table = null;
    }
  }

  public String getCatalog() {
    return this.catalog;
  }

  public void setCatalog(String catalog) {
    this.catalog = catalog;
  }

  public void unsetCatalog() {
    this.catalog = null;
  }

  /** Returns true if field catalog is set (has been assigned a value) and false otherwise */
  public boolean isSetCatalog() {
    return this.catalog != null;
  }

  public void setCatalogIsSet(boolean value) {
    if (!value) {
      this.catalog = null;
    }
  }

  public int getPartitionListSize() {
    return (this.partitionList == null) ? 0 : this.partitionList.size();
  }

  public java.util.Iterator<String> getPartitionListIterator() {
    return (this.partitionList == null) ? null : this.partitionList.iterator();
  }

  public void addToPartitionList(String elem) {
    if (this.partitionList == null) {
      this.partitionList = new ArrayList<String>();
    }
    this.partitionList.add(elem);
  }

  public List<String> getPartitionList() {
    return this.partitionList;
  }

  public void setPartitionList(List<String> partitionList) {
    this.partitionList = partitionList;
  }

  public void unsetPartitionList() {
    this.partitionList = null;
  }

  /** Returns true if field partitionList is set (has been assigned a value) and false otherwise */
  public boolean isSetPartitionList() {
    return this.partitionList != null;
  }

  public void setPartitionListIsSet(boolean value) {
    if (!value) {
      this.partitionList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DATABASE:
      if (value == null) {
        unsetDatabase();
      } else {
        setDatabase((String)value);
      }
      break;

    case LAST_REPL_ID:
      if (value == null) {
        unsetLastReplId();
      } else {
        setLastReplId((Long)value);
      }
      break;

    case TABLE:
      if (value == null) {
        unsetTable();
      } else {
        setTable((String)value);
      }
      break;

    case CATALOG:
      if (value == null) {
        unsetCatalog();
      } else {
        setCatalog((String)value);
      }
      break;

    case PARTITION_LIST:
      if (value == null) {
        unsetPartitionList();
      } else {
        setPartitionList((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DATABASE:
      return getDatabase();

    case LAST_REPL_ID:
      return getLastReplId();

    case TABLE:
      return getTable();

    case CATALOG:
      return getCatalog();

    case PARTITION_LIST:
      return getPartitionList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DATABASE:
      return isSetDatabase();
    case LAST_REPL_ID:
      return isSetLastReplId();
    case TABLE:
      return isSetTable();
    case CATALOG:
      return isSetCatalog();
    case PARTITION_LIST:
      return isSetPartitionList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReplLastIdInfo)
      return this.equals((ReplLastIdInfo)that);
    return false;
  }

  public boolean equals(ReplLastIdInfo that) {
    if (that == null)
      return false;

    boolean this_present_database = true && this.isSetDatabase();
    boolean that_present_database = true && that.isSetDatabase();
    if (this_present_database || that_present_database) {
      if (!(this_present_database && that_present_database))
        return false;
      if (!this.database.equals(that.database))
        return false;
    }

    boolean this_present_lastReplId = true;
    boolean that_present_lastReplId = true;
    if (this_present_lastReplId || that_present_lastReplId) {
      if (!(this_present_lastReplId && that_present_lastReplId))
        return false;
      if (this.lastReplId != that.lastReplId)
        return false;
    }

    boolean this_present_table = true && this.isSetTable();
    boolean that_present_table = true && that.isSetTable();
    if (this_present_table || that_present_table) {
      if (!(this_present_table && that_present_table))
        return false;
      if (!this.table.equals(that.table))
        return false;
    }

    boolean this_present_catalog = true && this.isSetCatalog();
    boolean that_present_catalog = true && that.isSetCatalog();
    if (this_present_catalog || that_present_catalog) {
      if (!(this_present_catalog && that_present_catalog))
        return false;
      if (!this.catalog.equals(that.catalog))
        return false;
    }

    boolean this_present_partitionList = true && this.isSetPartitionList();
    boolean that_present_partitionList = true && that.isSetPartitionList();
    if (this_present_partitionList || that_present_partitionList) {
      if (!(this_present_partitionList && that_present_partitionList))
        return false;
      if (!this.partitionList.equals(that.partitionList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_database = true && (isSetDatabase());
    list.add(present_database);
    if (present_database)
      list.add(database);

    boolean present_lastReplId = true;
    list.add(present_lastReplId);
    if (present_lastReplId)
      list.add(lastReplId);

    boolean present_table = true && (isSetTable());
    list.add(present_table);
    if (present_table)
      list.add(table);

    boolean present_catalog = true && (isSetCatalog());
    list.add(present_catalog);
    if (present_catalog)
      list.add(catalog);

    boolean present_partitionList = true && (isSetPartitionList());
    list.add(present_partitionList);
    if (present_partitionList)
      list.add(partitionList);

    return list.hashCode();
  }

  @Override
  public int compareTo(ReplLastIdInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDatabase()).compareTo(other.isSetDatabase());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatabase()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.database, other.database);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastReplId()).compareTo(other.isSetLastReplId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastReplId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastReplId, other.lastReplId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTable()).compareTo(other.isSetTable());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTable()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.table, other.table);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCatalog()).compareTo(other.isSetCatalog());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCatalog()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.catalog, other.catalog);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPartitionList()).compareTo(other.isSetPartitionList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartitionList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partitionList, other.partitionList);
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
    StringBuilder sb = new StringBuilder("ReplLastIdInfo(");
    boolean first = true;

    sb.append("database:");
    if (this.database == null) {
      sb.append("null");
    } else {
      sb.append(this.database);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastReplId:");
    sb.append(this.lastReplId);
    first = false;
    if (isSetTable()) {
      if (!first) sb.append(", ");
      sb.append("table:");
      if (this.table == null) {
        sb.append("null");
      } else {
        sb.append(this.table);
      }
      first = false;
    }
    if (isSetCatalog()) {
      if (!first) sb.append(", ");
      sb.append("catalog:");
      if (this.catalog == null) {
        sb.append("null");
      } else {
        sb.append(this.catalog);
      }
      first = false;
    }
    if (isSetPartitionList()) {
      if (!first) sb.append(", ");
      sb.append("partitionList:");
      if (this.partitionList == null) {
        sb.append("null");
      } else {
        sb.append(this.partitionList);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetDatabase()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'database' is unset! Struct:" + toString());
    }

    if (!isSetLastReplId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'lastReplId' is unset! Struct:" + toString());
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

  private static class ReplLastIdInfoStandardSchemeFactory implements SchemeFactory {
    public ReplLastIdInfoStandardScheme getScheme() {
      return new ReplLastIdInfoStandardScheme();
    }
  }

  private static class ReplLastIdInfoStandardScheme extends StandardScheme<ReplLastIdInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReplLastIdInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DATABASE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.database = iprot.readString();
              struct.setDatabaseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LAST_REPL_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastReplId = iprot.readI64();
              struct.setLastReplIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TABLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.table = iprot.readString();
              struct.setTableIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CATALOG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.catalog = iprot.readString();
              struct.setCatalogIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PARTITION_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list634 = iprot.readListBegin();
                struct.partitionList = new ArrayList<String>(_list634.size);
                String _elem635;
                for (int _i636 = 0; _i636 < _list634.size; ++_i636)
                {
                  _elem635 = iprot.readString();
                  struct.partitionList.add(_elem635);
                }
                iprot.readListEnd();
              }
              struct.setPartitionListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReplLastIdInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.database != null) {
        oprot.writeFieldBegin(DATABASE_FIELD_DESC);
        oprot.writeString(struct.database);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LAST_REPL_ID_FIELD_DESC);
      oprot.writeI64(struct.lastReplId);
      oprot.writeFieldEnd();
      if (struct.table != null) {
        if (struct.isSetTable()) {
          oprot.writeFieldBegin(TABLE_FIELD_DESC);
          oprot.writeString(struct.table);
          oprot.writeFieldEnd();
        }
      }
      if (struct.catalog != null) {
        if (struct.isSetCatalog()) {
          oprot.writeFieldBegin(CATALOG_FIELD_DESC);
          oprot.writeString(struct.catalog);
          oprot.writeFieldEnd();
        }
      }
      if (struct.partitionList != null) {
        if (struct.isSetPartitionList()) {
          oprot.writeFieldBegin(PARTITION_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.partitionList.size()));
            for (String _iter637 : struct.partitionList)
            {
              oprot.writeString(_iter637);
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

  private static class ReplLastIdInfoTupleSchemeFactory implements SchemeFactory {
    public ReplLastIdInfoTupleScheme getScheme() {
      return new ReplLastIdInfoTupleScheme();
    }
  }

  private static class ReplLastIdInfoTupleScheme extends TupleScheme<ReplLastIdInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReplLastIdInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.database);
      oprot.writeI64(struct.lastReplId);
      BitSet optionals = new BitSet();
      if (struct.isSetTable()) {
        optionals.set(0);
      }
      if (struct.isSetCatalog()) {
        optionals.set(1);
      }
      if (struct.isSetPartitionList()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetTable()) {
        oprot.writeString(struct.table);
      }
      if (struct.isSetCatalog()) {
        oprot.writeString(struct.catalog);
      }
      if (struct.isSetPartitionList()) {
        {
          oprot.writeI32(struct.partitionList.size());
          for (String _iter638 : struct.partitionList)
          {
            oprot.writeString(_iter638);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReplLastIdInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.database = iprot.readString();
      struct.setDatabaseIsSet(true);
      struct.lastReplId = iprot.readI64();
      struct.setLastReplIdIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.table = iprot.readString();
        struct.setTableIsSet(true);
      }
      if (incoming.get(1)) {
        struct.catalog = iprot.readString();
        struct.setCatalogIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list639 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.partitionList = new ArrayList<String>(_list639.size);
          String _elem640;
          for (int _i641 = 0; _i641 < _list639.size; ++_i641)
          {
            _elem640 = iprot.readString();
            struct.partitionList.add(_elem640);
          }
        }
        struct.setPartitionListIsSet(true);
      }
    }
  }

}

