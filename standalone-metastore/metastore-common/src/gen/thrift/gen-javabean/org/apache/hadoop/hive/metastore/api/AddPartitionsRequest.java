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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class AddPartitionsRequest implements org.apache.thrift.TBase<AddPartitionsRequest, AddPartitionsRequest._Fields>, java.io.Serializable, Cloneable, Comparable<AddPartitionsRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AddPartitionsRequest");

  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TBL_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tblName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PARTS_FIELD_DESC = new org.apache.thrift.protocol.TField("parts", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField IF_NOT_EXISTS_FIELD_DESC = new org.apache.thrift.protocol.TField("ifNotExists", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField NEED_RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("needResult", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField CAT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("catName", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField VALID_WRITE_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("validWriteIdList", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AddPartitionsRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AddPartitionsRequestTupleSchemeFactory());
  }

  private String dbName; // required
  private String tblName; // required
  private List<Partition> parts; // required
  private boolean ifNotExists; // required
  private boolean needResult; // optional
  private String catName; // optional
  private String validWriteIdList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_NAME((short)1, "dbName"),
    TBL_NAME((short)2, "tblName"),
    PARTS((short)3, "parts"),
    IF_NOT_EXISTS((short)4, "ifNotExists"),
    NEED_RESULT((short)5, "needResult"),
    CAT_NAME((short)6, "catName"),
    VALID_WRITE_ID_LIST((short)7, "validWriteIdList");

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
        case 3: // PARTS
          return PARTS;
        case 4: // IF_NOT_EXISTS
          return IF_NOT_EXISTS;
        case 5: // NEED_RESULT
          return NEED_RESULT;
        case 6: // CAT_NAME
          return CAT_NAME;
        case 7: // VALID_WRITE_ID_LIST
          return VALID_WRITE_ID_LIST;
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
  private static final int __IFNOTEXISTS_ISSET_ID = 0;
  private static final int __NEEDRESULT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.NEED_RESULT,_Fields.CAT_NAME,_Fields.VALID_WRITE_ID_LIST};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TBL_NAME, new org.apache.thrift.meta_data.FieldMetaData("tblName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARTS, new org.apache.thrift.meta_data.FieldMetaData("parts", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Partition.class))));
    tmpMap.put(_Fields.IF_NOT_EXISTS, new org.apache.thrift.meta_data.FieldMetaData("ifNotExists", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.NEED_RESULT, new org.apache.thrift.meta_data.FieldMetaData("needResult", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.CAT_NAME, new org.apache.thrift.meta_data.FieldMetaData("catName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VALID_WRITE_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("validWriteIdList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AddPartitionsRequest.class, metaDataMap);
  }

  public AddPartitionsRequest() {
    this.needResult = true;

  }

  public AddPartitionsRequest(
    String dbName,
    String tblName,
    List<Partition> parts,
    boolean ifNotExists)
  {
    this();
    this.dbName = dbName;
    this.tblName = tblName;
    this.parts = parts;
    this.ifNotExists = ifNotExists;
    setIfNotExistsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AddPartitionsRequest(AddPartitionsRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetDbName()) {
      this.dbName = other.dbName;
    }
    if (other.isSetTblName()) {
      this.tblName = other.tblName;
    }
    if (other.isSetParts()) {
      List<Partition> __this__parts = new ArrayList<Partition>(other.parts.size());
      for (Partition other_element : other.parts) {
        __this__parts.add(new Partition(other_element));
      }
      this.parts = __this__parts;
    }
    this.ifNotExists = other.ifNotExists;
    this.needResult = other.needResult;
    if (other.isSetCatName()) {
      this.catName = other.catName;
    }
    if (other.isSetValidWriteIdList()) {
      this.validWriteIdList = other.validWriteIdList;
    }
  }

  public AddPartitionsRequest deepCopy() {
    return new AddPartitionsRequest(this);
  }

  @Override
  public void clear() {
    this.dbName = null;
    this.tblName = null;
    this.parts = null;
    setIfNotExistsIsSet(false);
    this.ifNotExists = false;
    this.needResult = true;

    this.catName = null;
    this.validWriteIdList = null;
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

  public int getPartsSize() {
    return (this.parts == null) ? 0 : this.parts.size();
  }

  public java.util.Iterator<Partition> getPartsIterator() {
    return (this.parts == null) ? null : this.parts.iterator();
  }

  public void addToParts(Partition elem) {
    if (this.parts == null) {
      this.parts = new ArrayList<Partition>();
    }
    this.parts.add(elem);
  }

  public List<Partition> getParts() {
    return this.parts;
  }

  public void setParts(List<Partition> parts) {
    this.parts = parts;
  }

  public void unsetParts() {
    this.parts = null;
  }

  /** Returns true if field parts is set (has been assigned a value) and false otherwise */
  public boolean isSetParts() {
    return this.parts != null;
  }

  public void setPartsIsSet(boolean value) {
    if (!value) {
      this.parts = null;
    }
  }

  public boolean isIfNotExists() {
    return this.ifNotExists;
  }

  public void setIfNotExists(boolean ifNotExists) {
    this.ifNotExists = ifNotExists;
    setIfNotExistsIsSet(true);
  }

  public void unsetIfNotExists() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IFNOTEXISTS_ISSET_ID);
  }

  /** Returns true if field ifNotExists is set (has been assigned a value) and false otherwise */
  public boolean isSetIfNotExists() {
    return EncodingUtils.testBit(__isset_bitfield, __IFNOTEXISTS_ISSET_ID);
  }

  public void setIfNotExistsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IFNOTEXISTS_ISSET_ID, value);
  }

  public boolean isNeedResult() {
    return this.needResult;
  }

  public void setNeedResult(boolean needResult) {
    this.needResult = needResult;
    setNeedResultIsSet(true);
  }

  public void unsetNeedResult() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NEEDRESULT_ISSET_ID);
  }

  /** Returns true if field needResult is set (has been assigned a value) and false otherwise */
  public boolean isSetNeedResult() {
    return EncodingUtils.testBit(__isset_bitfield, __NEEDRESULT_ISSET_ID);
  }

  public void setNeedResultIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NEEDRESULT_ISSET_ID, value);
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

  public String getValidWriteIdList() {
    return this.validWriteIdList;
  }

  public void setValidWriteIdList(String validWriteIdList) {
    this.validWriteIdList = validWriteIdList;
  }

  public void unsetValidWriteIdList() {
    this.validWriteIdList = null;
  }

  /** Returns true if field validWriteIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetValidWriteIdList() {
    return this.validWriteIdList != null;
  }

  public void setValidWriteIdListIsSet(boolean value) {
    if (!value) {
      this.validWriteIdList = null;
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

    case PARTS:
      if (value == null) {
        unsetParts();
      } else {
        setParts((List<Partition>)value);
      }
      break;

    case IF_NOT_EXISTS:
      if (value == null) {
        unsetIfNotExists();
      } else {
        setIfNotExists((Boolean)value);
      }
      break;

    case NEED_RESULT:
      if (value == null) {
        unsetNeedResult();
      } else {
        setNeedResult((Boolean)value);
      }
      break;

    case CAT_NAME:
      if (value == null) {
        unsetCatName();
      } else {
        setCatName((String)value);
      }
      break;

    case VALID_WRITE_ID_LIST:
      if (value == null) {
        unsetValidWriteIdList();
      } else {
        setValidWriteIdList((String)value);
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

    case PARTS:
      return getParts();

    case IF_NOT_EXISTS:
      return isIfNotExists();

    case NEED_RESULT:
      return isNeedResult();

    case CAT_NAME:
      return getCatName();

    case VALID_WRITE_ID_LIST:
      return getValidWriteIdList();

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
    case PARTS:
      return isSetParts();
    case IF_NOT_EXISTS:
      return isSetIfNotExists();
    case NEED_RESULT:
      return isSetNeedResult();
    case CAT_NAME:
      return isSetCatName();
    case VALID_WRITE_ID_LIST:
      return isSetValidWriteIdList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AddPartitionsRequest)
      return this.equals((AddPartitionsRequest)that);
    return false;
  }

  public boolean equals(AddPartitionsRequest that) {
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

    boolean this_present_parts = true && this.isSetParts();
    boolean that_present_parts = true && that.isSetParts();
    if (this_present_parts || that_present_parts) {
      if (!(this_present_parts && that_present_parts))
        return false;
      if (!this.parts.equals(that.parts))
        return false;
    }

    boolean this_present_ifNotExists = true;
    boolean that_present_ifNotExists = true;
    if (this_present_ifNotExists || that_present_ifNotExists) {
      if (!(this_present_ifNotExists && that_present_ifNotExists))
        return false;
      if (this.ifNotExists != that.ifNotExists)
        return false;
    }

    boolean this_present_needResult = true && this.isSetNeedResult();
    boolean that_present_needResult = true && that.isSetNeedResult();
    if (this_present_needResult || that_present_needResult) {
      if (!(this_present_needResult && that_present_needResult))
        return false;
      if (this.needResult != that.needResult)
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

    boolean this_present_validWriteIdList = true && this.isSetValidWriteIdList();
    boolean that_present_validWriteIdList = true && that.isSetValidWriteIdList();
    if (this_present_validWriteIdList || that_present_validWriteIdList) {
      if (!(this_present_validWriteIdList && that_present_validWriteIdList))
        return false;
      if (!this.validWriteIdList.equals(that.validWriteIdList))
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

    boolean present_parts = true && (isSetParts());
    list.add(present_parts);
    if (present_parts)
      list.add(parts);

    boolean present_ifNotExists = true;
    list.add(present_ifNotExists);
    if (present_ifNotExists)
      list.add(ifNotExists);

    boolean present_needResult = true && (isSetNeedResult());
    list.add(present_needResult);
    if (present_needResult)
      list.add(needResult);

    boolean present_catName = true && (isSetCatName());
    list.add(present_catName);
    if (present_catName)
      list.add(catName);

    boolean present_validWriteIdList = true && (isSetValidWriteIdList());
    list.add(present_validWriteIdList);
    if (present_validWriteIdList)
      list.add(validWriteIdList);

    return list.hashCode();
  }

  @Override
  public int compareTo(AddPartitionsRequest other) {
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
    lastComparison = Boolean.valueOf(isSetParts()).compareTo(other.isSetParts());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParts()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parts, other.parts);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIfNotExists()).compareTo(other.isSetIfNotExists());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIfNotExists()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ifNotExists, other.ifNotExists);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNeedResult()).compareTo(other.isSetNeedResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNeedResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.needResult, other.needResult);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    lastComparison = Boolean.valueOf(isSetValidWriteIdList()).compareTo(other.isSetValidWriteIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValidWriteIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.validWriteIdList, other.validWriteIdList);
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
    StringBuilder sb = new StringBuilder("AddPartitionsRequest(");
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
    sb.append("parts:");
    if (this.parts == null) {
      sb.append("null");
    } else {
      sb.append(this.parts);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ifNotExists:");
    sb.append(this.ifNotExists);
    first = false;
    if (isSetNeedResult()) {
      if (!first) sb.append(", ");
      sb.append("needResult:");
      sb.append(this.needResult);
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
    if (isSetValidWriteIdList()) {
      if (!first) sb.append(", ");
      sb.append("validWriteIdList:");
      if (this.validWriteIdList == null) {
        sb.append("null");
      } else {
        sb.append(this.validWriteIdList);
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

    if (!isSetParts()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'parts' is unset! Struct:" + toString());
    }

    if (!isSetIfNotExists()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'ifNotExists' is unset! Struct:" + toString());
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

  private static class AddPartitionsRequestStandardSchemeFactory implements SchemeFactory {
    public AddPartitionsRequestStandardScheme getScheme() {
      return new AddPartitionsRequestStandardScheme();
    }
  }

  private static class AddPartitionsRequestStandardScheme extends StandardScheme<AddPartitionsRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AddPartitionsRequest struct) throws org.apache.thrift.TException {
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
          case 3: // PARTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list514 = iprot.readListBegin();
                struct.parts = new ArrayList<Partition>(_list514.size);
                Partition _elem515;
                for (int _i516 = 0; _i516 < _list514.size; ++_i516)
                {
                  _elem515 = new Partition();
                  _elem515.read(iprot);
                  struct.parts.add(_elem515);
                }
                iprot.readListEnd();
              }
              struct.setPartsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IF_NOT_EXISTS
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.ifNotExists = iprot.readBool();
              struct.setIfNotExistsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // NEED_RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.needResult = iprot.readBool();
              struct.setNeedResultIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CAT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.catName = iprot.readString();
              struct.setCatNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // VALID_WRITE_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.validWriteIdList = iprot.readString();
              struct.setValidWriteIdListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AddPartitionsRequest struct) throws org.apache.thrift.TException {
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
      if (struct.parts != null) {
        oprot.writeFieldBegin(PARTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.parts.size()));
          for (Partition _iter517 : struct.parts)
          {
            _iter517.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(IF_NOT_EXISTS_FIELD_DESC);
      oprot.writeBool(struct.ifNotExists);
      oprot.writeFieldEnd();
      if (struct.isSetNeedResult()) {
        oprot.writeFieldBegin(NEED_RESULT_FIELD_DESC);
        oprot.writeBool(struct.needResult);
        oprot.writeFieldEnd();
      }
      if (struct.catName != null) {
        if (struct.isSetCatName()) {
          oprot.writeFieldBegin(CAT_NAME_FIELD_DESC);
          oprot.writeString(struct.catName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.validWriteIdList != null) {
        if (struct.isSetValidWriteIdList()) {
          oprot.writeFieldBegin(VALID_WRITE_ID_LIST_FIELD_DESC);
          oprot.writeString(struct.validWriteIdList);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AddPartitionsRequestTupleSchemeFactory implements SchemeFactory {
    public AddPartitionsRequestTupleScheme getScheme() {
      return new AddPartitionsRequestTupleScheme();
    }
  }

  private static class AddPartitionsRequestTupleScheme extends TupleScheme<AddPartitionsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AddPartitionsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.dbName);
      oprot.writeString(struct.tblName);
      {
        oprot.writeI32(struct.parts.size());
        for (Partition _iter518 : struct.parts)
        {
          _iter518.write(oprot);
        }
      }
      oprot.writeBool(struct.ifNotExists);
      BitSet optionals = new BitSet();
      if (struct.isSetNeedResult()) {
        optionals.set(0);
      }
      if (struct.isSetCatName()) {
        optionals.set(1);
      }
      if (struct.isSetValidWriteIdList()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetNeedResult()) {
        oprot.writeBool(struct.needResult);
      }
      if (struct.isSetCatName()) {
        oprot.writeString(struct.catName);
      }
      if (struct.isSetValidWriteIdList()) {
        oprot.writeString(struct.validWriteIdList);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AddPartitionsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.dbName = iprot.readString();
      struct.setDbNameIsSet(true);
      struct.tblName = iprot.readString();
      struct.setTblNameIsSet(true);
      {
        org.apache.thrift.protocol.TList _list519 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.parts = new ArrayList<Partition>(_list519.size);
        Partition _elem520;
        for (int _i521 = 0; _i521 < _list519.size; ++_i521)
        {
          _elem520 = new Partition();
          _elem520.read(iprot);
          struct.parts.add(_elem520);
        }
      }
      struct.setPartsIsSet(true);
      struct.ifNotExists = iprot.readBool();
      struct.setIfNotExistsIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.needResult = iprot.readBool();
        struct.setNeedResultIsSet(true);
      }
      if (incoming.get(1)) {
        struct.catName = iprot.readString();
        struct.setCatNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.validWriteIdList = iprot.readString();
        struct.setValidWriteIdListIsSet(true);
      }
    }
  }

}

