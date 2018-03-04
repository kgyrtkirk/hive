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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class CreationMetadata implements org.apache.thrift.TBase<CreationMetadata, CreationMetadata._Fields>, java.io.Serializable, Cloneable, Comparable<CreationMetadata> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CreationMetadata");

  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TBL_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tblName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TABLES_USED_FIELD_DESC = new org.apache.thrift.protocol.TField("tablesUsed", org.apache.thrift.protocol.TType.SET, (short)3);
  private static final org.apache.thrift.protocol.TField VALID_TXN_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("validTxnList", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CreationMetadataStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CreationMetadataTupleSchemeFactory());
  }

  private String dbName; // required
  private String tblName; // required
  private Set<String> tablesUsed; // required
  private String validTxnList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_NAME((short)1, "dbName"),
    TBL_NAME((short)2, "tblName"),
    TABLES_USED((short)3, "tablesUsed"),
    VALID_TXN_LIST((short)4, "validTxnList");

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
        case 1: // DB_NAME
          return DB_NAME;
        case 2: // TBL_NAME
          return TBL_NAME;
        case 3: // TABLES_USED
          return TABLES_USED;
        case 4: // VALID_TXN_LIST
          return VALID_TXN_LIST;
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
  private static final _Fields optionals[] = {_Fields.VALID_TXN_LIST};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TBL_NAME, new org.apache.thrift.meta_data.FieldMetaData("tblName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLES_USED, new org.apache.thrift.meta_data.FieldMetaData("tablesUsed", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.VALID_TXN_LIST, new org.apache.thrift.meta_data.FieldMetaData("validTxnList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CreationMetadata.class, metaDataMap);
  }

  public CreationMetadata() {
  }

  public CreationMetadata(
    String dbName,
    String tblName,
    Set<String> tablesUsed)
  {
    this();
    this.dbName = dbName;
    this.tblName = tblName;
    this.tablesUsed = tablesUsed;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CreationMetadata(CreationMetadata other) {
    if (other.isSetDbName()) {
      this.dbName = other.dbName;
    }
    if (other.isSetTblName()) {
      this.tblName = other.tblName;
    }
    if (other.isSetTablesUsed()) {
      Set<String> __this__tablesUsed = new HashSet<String>(other.tablesUsed);
      this.tablesUsed = __this__tablesUsed;
    }
    if (other.isSetValidTxnList()) {
      this.validTxnList = other.validTxnList;
    }
  }

  public CreationMetadata deepCopy() {
    return new CreationMetadata(this);
  }

  @Override
  public void clear() {
    this.dbName = null;
    this.tblName = null;
    this.tablesUsed = null;
    this.validTxnList = null;
  }

  public String getDbName() {
    return this.dbName;
  }

  public void setDbName(String dbName) {
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

  public String getTblName() {
    return this.tblName;
  }

  public void setTblName(String tblName) {
    this.tblName = tblName;
  }

  public void unsetTblName() {
    this.tblName = null;
  }

  /** Returns true if field tblName is set (has been assigned a value) and false otherwise */
  public boolean isSetTblName() {
    return this.tblName != null;
  }

  public void setTblNameIsSet(boolean value) {
    if (!value) {
      this.tblName = null;
    }
  }

  public int getTablesUsedSize() {
    return (this.tablesUsed == null) ? 0 : this.tablesUsed.size();
  }

  public java.util.Iterator<String> getTablesUsedIterator() {
    return (this.tablesUsed == null) ? null : this.tablesUsed.iterator();
  }

  public void addToTablesUsed(String elem) {
    if (this.tablesUsed == null) {
      this.tablesUsed = new HashSet<String>();
    }
    this.tablesUsed.add(elem);
  }

  public Set<String> getTablesUsed() {
    return this.tablesUsed;
  }

  public void setTablesUsed(Set<String> tablesUsed) {
    this.tablesUsed = tablesUsed;
  }

  public void unsetTablesUsed() {
    this.tablesUsed = null;
  }

  /** Returns true if field tablesUsed is set (has been assigned a value) and false otherwise */
  public boolean isSetTablesUsed() {
    return this.tablesUsed != null;
  }

  public void setTablesUsedIsSet(boolean value) {
    if (!value) {
      this.tablesUsed = null;
    }
  }

  public String getValidTxnList() {
    return this.validTxnList;
  }

  public void setValidTxnList(String validTxnList) {
    this.validTxnList = validTxnList;
  }

  public void unsetValidTxnList() {
    this.validTxnList = null;
  }

  /** Returns true if field validTxnList is set (has been assigned a value) and false otherwise */
  public boolean isSetValidTxnList() {
    return this.validTxnList != null;
  }

  public void setValidTxnListIsSet(boolean value) {
    if (!value) {
      this.validTxnList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DB_NAME:
      if (value == null) {
        unsetDbName();
      } else {
        setDbName((String)value);
      }
      break;

    case TBL_NAME:
      if (value == null) {
        unsetTblName();
      } else {
        setTblName((String)value);
      }
      break;

    case TABLES_USED:
      if (value == null) {
        unsetTablesUsed();
      } else {
        setTablesUsed((Set<String>)value);
      }
      break;

    case VALID_TXN_LIST:
      if (value == null) {
        unsetValidTxnList();
      } else {
        setValidTxnList((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DB_NAME:
      return getDbName();

    case TBL_NAME:
      return getTblName();

    case TABLES_USED:
      return getTablesUsed();

    case VALID_TXN_LIST:
      return getValidTxnList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DB_NAME:
      return isSetDbName();
    case TBL_NAME:
      return isSetTblName();
    case TABLES_USED:
      return isSetTablesUsed();
    case VALID_TXN_LIST:
      return isSetValidTxnList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CreationMetadata)
      return this.equals((CreationMetadata)that);
    return false;
  }

  public boolean equals(CreationMetadata that) {
    if (that == null)
      return false;

    boolean this_present_dbName = true && this.isSetDbName();
    boolean that_present_dbName = true && that.isSetDbName();
    if (this_present_dbName || that_present_dbName) {
      if (!(this_present_dbName && that_present_dbName))
        return false;
      if (!this.dbName.equals(that.dbName))
        return false;
    }

    boolean this_present_tblName = true && this.isSetTblName();
    boolean that_present_tblName = true && that.isSetTblName();
    if (this_present_tblName || that_present_tblName) {
      if (!(this_present_tblName && that_present_tblName))
        return false;
      if (!this.tblName.equals(that.tblName))
        return false;
    }

    boolean this_present_tablesUsed = true && this.isSetTablesUsed();
    boolean that_present_tablesUsed = true && that.isSetTablesUsed();
    if (this_present_tablesUsed || that_present_tablesUsed) {
      if (!(this_present_tablesUsed && that_present_tablesUsed))
        return false;
      if (!this.tablesUsed.equals(that.tablesUsed))
        return false;
    }

    boolean this_present_validTxnList = true && this.isSetValidTxnList();
    boolean that_present_validTxnList = true && that.isSetValidTxnList();
    if (this_present_validTxnList || that_present_validTxnList) {
      if (!(this_present_validTxnList && that_present_validTxnList))
        return false;
      if (!this.validTxnList.equals(that.validTxnList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_dbName = true && (isSetDbName());
    list.add(present_dbName);
    if (present_dbName)
      list.add(dbName);

    boolean present_tblName = true && (isSetTblName());
    list.add(present_tblName);
    if (present_tblName)
      list.add(tblName);

    boolean present_tablesUsed = true && (isSetTablesUsed());
    list.add(present_tablesUsed);
    if (present_tablesUsed)
      list.add(tablesUsed);

    boolean present_validTxnList = true && (isSetValidTxnList());
    list.add(present_validTxnList);
    if (present_validTxnList)
      list.add(validTxnList);

    return list.hashCode();
  }

  @Override
  public int compareTo(CreationMetadata other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDbName()).compareTo(other.isSetDbName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbName, other.dbName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTblName()).compareTo(other.isSetTblName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTblName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tblName, other.tblName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTablesUsed()).compareTo(other.isSetTablesUsed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTablesUsed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tablesUsed, other.tablesUsed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValidTxnList()).compareTo(other.isSetValidTxnList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidTxnList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.validTxnList, other.validTxnList);
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
    StringBuilder sb = new StringBuilder("CreationMetadata(");
    boolean first = true;

    sb.append("dbName:");
    if (this.dbName == null) {
      sb.append("null");
    } else {
      sb.append(this.dbName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tblName:");
    if (this.tblName == null) {
      sb.append("null");
    } else {
      sb.append(this.tblName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tablesUsed:");
    if (this.tablesUsed == null) {
      sb.append("null");
    } else {
      sb.append(this.tablesUsed);
    }
    first = false;
    if (isSetValidTxnList()) {
      if (!first) sb.append(", ");
      sb.append("validTxnList:");
      if (this.validTxnList == null) {
        sb.append("null");
      } else {
        sb.append(this.validTxnList);
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

    if (!isSetTblName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tblName' is unset! Struct:" + toString());
    }

    if (!isSetTablesUsed()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'tablesUsed' is unset! Struct:" + toString());
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

  private static class CreationMetadataStandardSchemeFactory implements SchemeFactory {
    public CreationMetadataStandardScheme getScheme() {
      return new CreationMetadataStandardScheme();
    }
  }

  private static class CreationMetadataStandardScheme extends StandardScheme<CreationMetadata> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CreationMetadata struct) throws org.apache.thrift.TException {
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
          case 2: // TBL_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tblName = iprot.readString();
              struct.setTblNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TABLES_USED
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set670 = iprot.readSetBegin();
                struct.tablesUsed = new HashSet<String>(2*_set670.size);
                String _elem671;
                for (int _i672 = 0; _i672 < _set670.size; ++_i672)
                {
                  _elem671 = iprot.readString();
                  struct.tablesUsed.add(_elem671);
                }
                iprot.readSetEnd();
              }
              struct.setTablesUsedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VALID_TXN_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.validTxnList = iprot.readString();
              struct.setValidTxnListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CreationMetadata struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dbName != null) {
        oprot.writeFieldBegin(DB_NAME_FIELD_DESC);
        oprot.writeString(struct.dbName);
        oprot.writeFieldEnd();
      }
      if (struct.tblName != null) {
        oprot.writeFieldBegin(TBL_NAME_FIELD_DESC);
        oprot.writeString(struct.tblName);
        oprot.writeFieldEnd();
      }
      if (struct.tablesUsed != null) {
        oprot.writeFieldBegin(TABLES_USED_FIELD_DESC);
        {
          oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, struct.tablesUsed.size()));
          for (String _iter673 : struct.tablesUsed)
          {
            oprot.writeString(_iter673);
          }
          oprot.writeSetEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.validTxnList != null) {
        if (struct.isSetValidTxnList()) {
          oprot.writeFieldBegin(VALID_TXN_LIST_FIELD_DESC);
          oprot.writeString(struct.validTxnList);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CreationMetadataTupleSchemeFactory implements SchemeFactory {
    public CreationMetadataTupleScheme getScheme() {
      return new CreationMetadataTupleScheme();
    }
  }

  private static class CreationMetadataTupleScheme extends TupleScheme<CreationMetadata> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CreationMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.dbName);
      oprot.writeString(struct.tblName);
      {
        oprot.writeI32(struct.tablesUsed.size());
        for (String _iter674 : struct.tablesUsed)
        {
          oprot.writeString(_iter674);
        }
      }
      BitSet optionals = new BitSet();
      if (struct.isSetValidTxnList()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetValidTxnList()) {
        oprot.writeString(struct.validTxnList);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CreationMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.dbName = iprot.readString();
      struct.setDbNameIsSet(true);
      struct.tblName = iprot.readString();
      struct.setTblNameIsSet(true);
      {
        org.apache.thrift.protocol.TSet _set675 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.tablesUsed = new HashSet<String>(2*_set675.size);
        String _elem676;
        for (int _i677 = 0; _i677 < _set675.size; ++_i677)
        {
          _elem676 = iprot.readString();
          struct.tablesUsed.add(_elem676);
        }
      }
      struct.setTablesUsedIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.validTxnList = iprot.readString();
        struct.setValidTxnListIsSet(true);
      }
    }
  }

}

