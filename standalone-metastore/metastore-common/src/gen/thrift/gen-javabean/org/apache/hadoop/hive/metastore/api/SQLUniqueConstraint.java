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
public class SQLUniqueConstraint implements org.apache.thrift.TBase<SQLUniqueConstraint, SQLUniqueConstraint._Fields>, java.io.Serializable, Cloneable, Comparable<SQLUniqueConstraint> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SQLUniqueConstraint");

  private static final org.apache.thrift.protocol.TField CAT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("catName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TABLE_DB_FIELD_DESC = new org.apache.thrift.protocol.TField("table_db", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("table_name", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField COLUMN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("column_name", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField KEY_SEQ_FIELD_DESC = new org.apache.thrift.protocol.TField("key_seq", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField UK_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("uk_name", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField ENABLE_CSTR_FIELD_DESC = new org.apache.thrift.protocol.TField("enable_cstr", org.apache.thrift.protocol.TType.BOOL, (short)7);
  private static final org.apache.thrift.protocol.TField VALIDATE_CSTR_FIELD_DESC = new org.apache.thrift.protocol.TField("validate_cstr", org.apache.thrift.protocol.TType.BOOL, (short)8);
  private static final org.apache.thrift.protocol.TField RELY_CSTR_FIELD_DESC = new org.apache.thrift.protocol.TField("rely_cstr", org.apache.thrift.protocol.TType.BOOL, (short)9);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SQLUniqueConstraintStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SQLUniqueConstraintTupleSchemeFactory());
  }

  private String catName; // required
  private String table_db; // required
  private String table_name; // required
  private String column_name; // required
  private int key_seq; // required
  private String uk_name; // required
  private boolean enable_cstr; // required
  private boolean validate_cstr; // required
  private boolean rely_cstr; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CAT_NAME((short)1, "catName"),
    TABLE_DB((short)2, "table_db"),
    TABLE_NAME((short)3, "table_name"),
    COLUMN_NAME((short)4, "column_name"),
    KEY_SEQ((short)5, "key_seq"),
    UK_NAME((short)6, "uk_name"),
    ENABLE_CSTR((short)7, "enable_cstr"),
    VALIDATE_CSTR((short)8, "validate_cstr"),
    RELY_CSTR((short)9, "rely_cstr");

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
        case 1: // CAT_NAME
          return CAT_NAME;
        case 2: // TABLE_DB
          return TABLE_DB;
        case 3: // TABLE_NAME
          return TABLE_NAME;
        case 4: // COLUMN_NAME
          return COLUMN_NAME;
        case 5: // KEY_SEQ
          return KEY_SEQ;
        case 6: // UK_NAME
          return UK_NAME;
        case 7: // ENABLE_CSTR
          return ENABLE_CSTR;
        case 8: // VALIDATE_CSTR
          return VALIDATE_CSTR;
        case 9: // RELY_CSTR
          return RELY_CSTR;
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
  private static final int __KEY_SEQ_ISSET_ID = 0;
  private static final int __ENABLE_CSTR_ISSET_ID = 1;
  private static final int __VALIDATE_CSTR_ISSET_ID = 2;
  private static final int __RELY_CSTR_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CAT_NAME, new org.apache.thrift.meta_data.FieldMetaData("catName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLE_DB, new org.apache.thrift.meta_data.FieldMetaData("table_db", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("table_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COLUMN_NAME, new org.apache.thrift.meta_data.FieldMetaData("column_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.KEY_SEQ, new org.apache.thrift.meta_data.FieldMetaData("key_seq", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.UK_NAME, new org.apache.thrift.meta_data.FieldMetaData("uk_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENABLE_CSTR, new org.apache.thrift.meta_data.FieldMetaData("enable_cstr", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.VALIDATE_CSTR, new org.apache.thrift.meta_data.FieldMetaData("validate_cstr", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.RELY_CSTR, new org.apache.thrift.meta_data.FieldMetaData("rely_cstr", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SQLUniqueConstraint.class, metaDataMap);
  }

  public SQLUniqueConstraint() {
  }

  public SQLUniqueConstraint(
    String catName,
    String table_db,
    String table_name,
    String column_name,
    int key_seq,
    String uk_name,
    boolean enable_cstr,
    boolean validate_cstr,
    boolean rely_cstr)
  {
    this();
    this.catName = catName;
    this.table_db = table_db;
    this.table_name = table_name;
    this.column_name = column_name;
    this.key_seq = key_seq;
    setKey_seqIsSet(true);
    this.uk_name = uk_name;
    this.enable_cstr = enable_cstr;
    setEnable_cstrIsSet(true);
    this.validate_cstr = validate_cstr;
    setValidate_cstrIsSet(true);
    this.rely_cstr = rely_cstr;
    setRely_cstrIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SQLUniqueConstraint(SQLUniqueConstraint other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCatName()) {
      this.catName = other.catName;
    }
    if (other.isSetTable_db()) {
      this.table_db = other.table_db;
    }
    if (other.isSetTable_name()) {
      this.table_name = other.table_name;
    }
    if (other.isSetColumn_name()) {
      this.column_name = other.column_name;
    }
    this.key_seq = other.key_seq;
    if (other.isSetUk_name()) {
      this.uk_name = other.uk_name;
    }
    this.enable_cstr = other.enable_cstr;
    this.validate_cstr = other.validate_cstr;
    this.rely_cstr = other.rely_cstr;
  }

  public SQLUniqueConstraint deepCopy() {
    return new SQLUniqueConstraint(this);
  }

  @Override
  public void clear() {
    this.catName = null;
    this.table_db = null;
    this.table_name = null;
    this.column_name = null;
    setKey_seqIsSet(false);
    this.key_seq = 0;
    this.uk_name = null;
    setEnable_cstrIsSet(false);
    this.enable_cstr = false;
    setValidate_cstrIsSet(false);
    this.validate_cstr = false;
    setRely_cstrIsSet(false);
    this.rely_cstr = false;
  }

  public String getCatName() {
    return this.catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
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

  public String getTable_db() {
    return this.table_db;
  }

  public void setTable_db(String table_db) {
    this.table_db = table_db;
  }

  public void unsetTable_db() {
    this.table_db = null;
  }

  /** Returns true if field table_db is set (has been assigned a value) and false otherwise */
  public boolean isSetTable_db() {
    return this.table_db != null;
  }

  public void setTable_dbIsSet(boolean value) {
    if (!value) {
      this.table_db = null;
    }
  }

  public String getTable_name() {
    return this.table_name;
  }

  public void setTable_name(String table_name) {
    this.table_name = table_name;
  }

  public void unsetTable_name() {
    this.table_name = null;
  }

  /** Returns true if field table_name is set (has been assigned a value) and false otherwise */
  public boolean isSetTable_name() {
    return this.table_name != null;
  }

  public void setTable_nameIsSet(boolean value) {
    if (!value) {
      this.table_name = null;
    }
  }

  public String getColumn_name() {
    return this.column_name;
  }

  public void setColumn_name(String column_name) {
    this.column_name = column_name;
  }

  public void unsetColumn_name() {
    this.column_name = null;
  }

  /** Returns true if field column_name is set (has been assigned a value) and false otherwise */
  public boolean isSetColumn_name() {
    return this.column_name != null;
  }

  public void setColumn_nameIsSet(boolean value) {
    if (!value) {
      this.column_name = null;
    }
  }

  public int getKey_seq() {
    return this.key_seq;
  }

  public void setKey_seq(int key_seq) {
    this.key_seq = key_seq;
    setKey_seqIsSet(true);
  }

  public void unsetKey_seq() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __KEY_SEQ_ISSET_ID);
  }

  /** Returns true if field key_seq is set (has been assigned a value) and false otherwise */
  public boolean isSetKey_seq() {
    return EncodingUtils.testBit(__isset_bitfield, __KEY_SEQ_ISSET_ID);
  }

  public void setKey_seqIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __KEY_SEQ_ISSET_ID, value);
  }

  public String getUk_name() {
    return this.uk_name;
  }

  public void setUk_name(String uk_name) {
    this.uk_name = uk_name;
  }

  public void unsetUk_name() {
    this.uk_name = null;
  }

  /** Returns true if field uk_name is set (has been assigned a value) and false otherwise */
  public boolean isSetUk_name() {
    return this.uk_name != null;
  }

  public void setUk_nameIsSet(boolean value) {
    if (!value) {
      this.uk_name = null;
    }
  }

  public boolean isEnable_cstr() {
    return this.enable_cstr;
  }

  public void setEnable_cstr(boolean enable_cstr) {
    this.enable_cstr = enable_cstr;
    setEnable_cstrIsSet(true);
  }

  public void unsetEnable_cstr() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ENABLE_CSTR_ISSET_ID);
  }

  /** Returns true if field enable_cstr is set (has been assigned a value) and false otherwise */
  public boolean isSetEnable_cstr() {
    return EncodingUtils.testBit(__isset_bitfield, __ENABLE_CSTR_ISSET_ID);
  }

  public void setEnable_cstrIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ENABLE_CSTR_ISSET_ID, value);
  }

  public boolean isValidate_cstr() {
    return this.validate_cstr;
  }

  public void setValidate_cstr(boolean validate_cstr) {
    this.validate_cstr = validate_cstr;
    setValidate_cstrIsSet(true);
  }

  public void unsetValidate_cstr() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VALIDATE_CSTR_ISSET_ID);
  }

  /** Returns true if field validate_cstr is set (has been assigned a value) and false otherwise */
  public boolean isSetValidate_cstr() {
    return EncodingUtils.testBit(__isset_bitfield, __VALIDATE_CSTR_ISSET_ID);
  }

  public void setValidate_cstrIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VALIDATE_CSTR_ISSET_ID, value);
  }

  public boolean isRely_cstr() {
    return this.rely_cstr;
  }

  public void setRely_cstr(boolean rely_cstr) {
    this.rely_cstr = rely_cstr;
    setRely_cstrIsSet(true);
  }

  public void unsetRely_cstr() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RELY_CSTR_ISSET_ID);
  }

  /** Returns true if field rely_cstr is set (has been assigned a value) and false otherwise */
  public boolean isSetRely_cstr() {
    return EncodingUtils.testBit(__isset_bitfield, __RELY_CSTR_ISSET_ID);
  }

  public void setRely_cstrIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RELY_CSTR_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CAT_NAME:
      if (value == null) {
        unsetCatName();
      } else {
        setCatName((String)value);
      }
      break;

    case TABLE_DB:
      if (value == null) {
        unsetTable_db();
      } else {
        setTable_db((String)value);
      }
      break;

    case TABLE_NAME:
      if (value == null) {
        unsetTable_name();
      } else {
        setTable_name((String)value);
      }
      break;

    case COLUMN_NAME:
      if (value == null) {
        unsetColumn_name();
      } else {
        setColumn_name((String)value);
      }
      break;

    case KEY_SEQ:
      if (value == null) {
        unsetKey_seq();
      } else {
        setKey_seq((Integer)value);
      }
      break;

    case UK_NAME:
      if (value == null) {
        unsetUk_name();
      } else {
        setUk_name((String)value);
      }
      break;

    case ENABLE_CSTR:
      if (value == null) {
        unsetEnable_cstr();
      } else {
        setEnable_cstr((Boolean)value);
      }
      break;

    case VALIDATE_CSTR:
      if (value == null) {
        unsetValidate_cstr();
      } else {
        setValidate_cstr((Boolean)value);
      }
      break;

    case RELY_CSTR:
      if (value == null) {
        unsetRely_cstr();
      } else {
        setRely_cstr((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CAT_NAME:
      return getCatName();

    case TABLE_DB:
      return getTable_db();

    case TABLE_NAME:
      return getTable_name();

    case COLUMN_NAME:
      return getColumn_name();

    case KEY_SEQ:
      return getKey_seq();

    case UK_NAME:
      return getUk_name();

    case ENABLE_CSTR:
      return isEnable_cstr();

    case VALIDATE_CSTR:
      return isValidate_cstr();

    case RELY_CSTR:
      return isRely_cstr();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CAT_NAME:
      return isSetCatName();
    case TABLE_DB:
      return isSetTable_db();
    case TABLE_NAME:
      return isSetTable_name();
    case COLUMN_NAME:
      return isSetColumn_name();
    case KEY_SEQ:
      return isSetKey_seq();
    case UK_NAME:
      return isSetUk_name();
    case ENABLE_CSTR:
      return isSetEnable_cstr();
    case VALIDATE_CSTR:
      return isSetValidate_cstr();
    case RELY_CSTR:
      return isSetRely_cstr();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SQLUniqueConstraint)
      return this.equals((SQLUniqueConstraint)that);
    return false;
  }

  public boolean equals(SQLUniqueConstraint that) {
    if (that == null)
      return false;

    boolean this_present_catName = true && this.isSetCatName();
    boolean that_present_catName = true && that.isSetCatName();
    if (this_present_catName || that_present_catName) {
      if (!(this_present_catName && that_present_catName))
        return false;
      if (!this.catName.equals(that.catName))
        return false;
    }

    boolean this_present_table_db = true && this.isSetTable_db();
    boolean that_present_table_db = true && that.isSetTable_db();
    if (this_present_table_db || that_present_table_db) {
      if (!(this_present_table_db && that_present_table_db))
        return false;
      if (!this.table_db.equals(that.table_db))
        return false;
    }

    boolean this_present_table_name = true && this.isSetTable_name();
    boolean that_present_table_name = true && that.isSetTable_name();
    if (this_present_table_name || that_present_table_name) {
      if (!(this_present_table_name && that_present_table_name))
        return false;
      if (!this.table_name.equals(that.table_name))
        return false;
    }

    boolean this_present_column_name = true && this.isSetColumn_name();
    boolean that_present_column_name = true && that.isSetColumn_name();
    if (this_present_column_name || that_present_column_name) {
      if (!(this_present_column_name && that_present_column_name))
        return false;
      if (!this.column_name.equals(that.column_name))
        return false;
    }

    boolean this_present_key_seq = true;
    boolean that_present_key_seq = true;
    if (this_present_key_seq || that_present_key_seq) {
      if (!(this_present_key_seq && that_present_key_seq))
        return false;
      if (this.key_seq != that.key_seq)
        return false;
    }

    boolean this_present_uk_name = true && this.isSetUk_name();
    boolean that_present_uk_name = true && that.isSetUk_name();
    if (this_present_uk_name || that_present_uk_name) {
      if (!(this_present_uk_name && that_present_uk_name))
        return false;
      if (!this.uk_name.equals(that.uk_name))
        return false;
    }

    boolean this_present_enable_cstr = true;
    boolean that_present_enable_cstr = true;
    if (this_present_enable_cstr || that_present_enable_cstr) {
      if (!(this_present_enable_cstr && that_present_enable_cstr))
        return false;
      if (this.enable_cstr != that.enable_cstr)
        return false;
    }

    boolean this_present_validate_cstr = true;
    boolean that_present_validate_cstr = true;
    if (this_present_validate_cstr || that_present_validate_cstr) {
      if (!(this_present_validate_cstr && that_present_validate_cstr))
        return false;
      if (this.validate_cstr != that.validate_cstr)
        return false;
    }

    boolean this_present_rely_cstr = true;
    boolean that_present_rely_cstr = true;
    if (this_present_rely_cstr || that_present_rely_cstr) {
      if (!(this_present_rely_cstr && that_present_rely_cstr))
        return false;
      if (this.rely_cstr != that.rely_cstr)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_catName = true && (isSetCatName());
    list.add(present_catName);
    if (present_catName)
      list.add(catName);

    boolean present_table_db = true && (isSetTable_db());
    list.add(present_table_db);
    if (present_table_db)
      list.add(table_db);

    boolean present_table_name = true && (isSetTable_name());
    list.add(present_table_name);
    if (present_table_name)
      list.add(table_name);

    boolean present_column_name = true && (isSetColumn_name());
    list.add(present_column_name);
    if (present_column_name)
      list.add(column_name);

    boolean present_key_seq = true;
    list.add(present_key_seq);
    if (present_key_seq)
      list.add(key_seq);

    boolean present_uk_name = true && (isSetUk_name());
    list.add(present_uk_name);
    if (present_uk_name)
      list.add(uk_name);

    boolean present_enable_cstr = true;
    list.add(present_enable_cstr);
    if (present_enable_cstr)
      list.add(enable_cstr);

    boolean present_validate_cstr = true;
    list.add(present_validate_cstr);
    if (present_validate_cstr)
      list.add(validate_cstr);

    boolean present_rely_cstr = true;
    list.add(present_rely_cstr);
    if (present_rely_cstr)
      list.add(rely_cstr);

    return list.hashCode();
  }

  @Override
  public int compareTo(SQLUniqueConstraint other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCatName()).compareTo(other.isSetCatName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCatName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.catName, other.catName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTable_db()).compareTo(other.isSetTable_db());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTable_db()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.table_db, other.table_db);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTable_name()).compareTo(other.isSetTable_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTable_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.table_name, other.table_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetColumn_name()).compareTo(other.isSetColumn_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColumn_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.column_name, other.column_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetKey_seq()).compareTo(other.isSetKey_seq());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKey_seq()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key_seq, other.key_seq);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUk_name()).compareTo(other.isSetUk_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUk_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uk_name, other.uk_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnable_cstr()).compareTo(other.isSetEnable_cstr());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnable_cstr()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.enable_cstr, other.enable_cstr);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValidate_cstr()).compareTo(other.isSetValidate_cstr());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidate_cstr()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.validate_cstr, other.validate_cstr);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRely_cstr()).compareTo(other.isSetRely_cstr());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRely_cstr()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rely_cstr, other.rely_cstr);
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
    StringBuilder sb = new StringBuilder("SQLUniqueConstraint(");
    boolean first = true;

    sb.append("catName:");
    if (this.catName == null) {
      sb.append("null");
    } else {
      sb.append(this.catName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("table_db:");
    if (this.table_db == null) {
      sb.append("null");
    } else {
      sb.append(this.table_db);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("table_name:");
    if (this.table_name == null) {
      sb.append("null");
    } else {
      sb.append(this.table_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("column_name:");
    if (this.column_name == null) {
      sb.append("null");
    } else {
      sb.append(this.column_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("key_seq:");
    sb.append(this.key_seq);
    first = false;
    if (!first) sb.append(", ");
    sb.append("uk_name:");
    if (this.uk_name == null) {
      sb.append("null");
    } else {
      sb.append(this.uk_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("enable_cstr:");
    sb.append(this.enable_cstr);
    first = false;
    if (!first) sb.append(", ");
    sb.append("validate_cstr:");
    sb.append(this.validate_cstr);
    first = false;
    if (!first) sb.append(", ");
    sb.append("rely_cstr:");
    sb.append(this.rely_cstr);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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

  private static class SQLUniqueConstraintStandardSchemeFactory implements SchemeFactory {
    public SQLUniqueConstraintStandardScheme getScheme() {
      return new SQLUniqueConstraintStandardScheme();
    }
  }

  private static class SQLUniqueConstraintStandardScheme extends StandardScheme<SQLUniqueConstraint> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SQLUniqueConstraint struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CAT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.catName = iprot.readString();
              struct.setCatNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TABLE_DB
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.table_db = iprot.readString();
              struct.setTable_dbIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TABLE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.table_name = iprot.readString();
              struct.setTable_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // COLUMN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.column_name = iprot.readString();
              struct.setColumn_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // KEY_SEQ
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.key_seq = iprot.readI32();
              struct.setKey_seqIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // UK_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uk_name = iprot.readString();
              struct.setUk_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // ENABLE_CSTR
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.enable_cstr = iprot.readBool();
              struct.setEnable_cstrIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // VALIDATE_CSTR
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.validate_cstr = iprot.readBool();
              struct.setValidate_cstrIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // RELY_CSTR
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.rely_cstr = iprot.readBool();
              struct.setRely_cstrIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SQLUniqueConstraint struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.catName != null) {
        oprot.writeFieldBegin(CAT_NAME_FIELD_DESC);
        oprot.writeString(struct.catName);
        oprot.writeFieldEnd();
      }
      if (struct.table_db != null) {
        oprot.writeFieldBegin(TABLE_DB_FIELD_DESC);
        oprot.writeString(struct.table_db);
        oprot.writeFieldEnd();
      }
      if (struct.table_name != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeString(struct.table_name);
        oprot.writeFieldEnd();
      }
      if (struct.column_name != null) {
        oprot.writeFieldBegin(COLUMN_NAME_FIELD_DESC);
        oprot.writeString(struct.column_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(KEY_SEQ_FIELD_DESC);
      oprot.writeI32(struct.key_seq);
      oprot.writeFieldEnd();
      if (struct.uk_name != null) {
        oprot.writeFieldBegin(UK_NAME_FIELD_DESC);
        oprot.writeString(struct.uk_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ENABLE_CSTR_FIELD_DESC);
      oprot.writeBool(struct.enable_cstr);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VALIDATE_CSTR_FIELD_DESC);
      oprot.writeBool(struct.validate_cstr);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RELY_CSTR_FIELD_DESC);
      oprot.writeBool(struct.rely_cstr);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SQLUniqueConstraintTupleSchemeFactory implements SchemeFactory {
    public SQLUniqueConstraintTupleScheme getScheme() {
      return new SQLUniqueConstraintTupleScheme();
    }
  }

  private static class SQLUniqueConstraintTupleScheme extends TupleScheme<SQLUniqueConstraint> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SQLUniqueConstraint struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCatName()) {
        optionals.set(0);
      }
      if (struct.isSetTable_db()) {
        optionals.set(1);
      }
      if (struct.isSetTable_name()) {
        optionals.set(2);
      }
      if (struct.isSetColumn_name()) {
        optionals.set(3);
      }
      if (struct.isSetKey_seq()) {
        optionals.set(4);
      }
      if (struct.isSetUk_name()) {
        optionals.set(5);
      }
      if (struct.isSetEnable_cstr()) {
        optionals.set(6);
      }
      if (struct.isSetValidate_cstr()) {
        optionals.set(7);
      }
      if (struct.isSetRely_cstr()) {
        optionals.set(8);
      }
      oprot.writeBitSet(optionals, 9);
      if (struct.isSetCatName()) {
        oprot.writeString(struct.catName);
      }
      if (struct.isSetTable_db()) {
        oprot.writeString(struct.table_db);
      }
      if (struct.isSetTable_name()) {
        oprot.writeString(struct.table_name);
      }
      if (struct.isSetColumn_name()) {
        oprot.writeString(struct.column_name);
      }
      if (struct.isSetKey_seq()) {
        oprot.writeI32(struct.key_seq);
      }
      if (struct.isSetUk_name()) {
        oprot.writeString(struct.uk_name);
      }
      if (struct.isSetEnable_cstr()) {
        oprot.writeBool(struct.enable_cstr);
      }
      if (struct.isSetValidate_cstr()) {
        oprot.writeBool(struct.validate_cstr);
      }
      if (struct.isSetRely_cstr()) {
        oprot.writeBool(struct.rely_cstr);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SQLUniqueConstraint struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(9);
      if (incoming.get(0)) {
        struct.catName = iprot.readString();
        struct.setCatNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.table_db = iprot.readString();
        struct.setTable_dbIsSet(true);
      }
      if (incoming.get(2)) {
        struct.table_name = iprot.readString();
        struct.setTable_nameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.column_name = iprot.readString();
        struct.setColumn_nameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.key_seq = iprot.readI32();
        struct.setKey_seqIsSet(true);
      }
      if (incoming.get(5)) {
        struct.uk_name = iprot.readString();
        struct.setUk_nameIsSet(true);
      }
      if (incoming.get(6)) {
        struct.enable_cstr = iprot.readBool();
        struct.setEnable_cstrIsSet(true);
      }
      if (incoming.get(7)) {
        struct.validate_cstr = iprot.readBool();
        struct.setValidate_cstrIsSet(true);
      }
      if (incoming.get(8)) {
        struct.rely_cstr = iprot.readBool();
        struct.setRely_cstrIsSet(true);
      }
    }
  }

}

