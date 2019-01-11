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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class GetPartitionsRequest implements org.apache.thrift.TBase<GetPartitionsRequest, GetPartitionsRequest._Fields>, java.io.Serializable, Cloneable, Comparable<GetPartitionsRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GetPartitionsRequest");

  private static final org.apache.thrift.protocol.TField CAT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("catName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TBL_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tblName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField WITH_AUTH_FIELD_DESC = new org.apache.thrift.protocol.TField("withAuth", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField USER_FIELD_DESC = new org.apache.thrift.protocol.TField("user", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField GROUP_NAMES_FIELD_DESC = new org.apache.thrift.protocol.TField("groupNames", org.apache.thrift.protocol.TType.LIST, (short)6);
  private static final org.apache.thrift.protocol.TField PROJECTION_SPEC_FIELD_DESC = new org.apache.thrift.protocol.TField("projectionSpec", org.apache.thrift.protocol.TType.STRUCT, (short)7);
  private static final org.apache.thrift.protocol.TField FILTER_SPEC_FIELD_DESC = new org.apache.thrift.protocol.TField("filterSpec", org.apache.thrift.protocol.TType.STRUCT, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GetPartitionsRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GetPartitionsRequestTupleSchemeFactory());
  }

  private String catName; // optional
  private String dbName; // required
  private String tblName; // required
  private boolean withAuth; // optional
  private String user; // optional
  private List<String> groupNames; // optional
  private GetPartitionsProjectionSpec projectionSpec; // required
  private GetPartitionsFilterSpec filterSpec; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CAT_NAME((short)1, "catName"),
    DB_NAME((short)2, "dbName"),
    TBL_NAME((short)3, "tblName"),
    WITH_AUTH((short)4, "withAuth"),
    USER((short)5, "user"),
    GROUP_NAMES((short)6, "groupNames"),
    PROJECTION_SPEC((short)7, "projectionSpec"),
    FILTER_SPEC((short)8, "filterSpec");

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
        case 2: // DB_NAME
          return DB_NAME;
        case 3: // TBL_NAME
          return TBL_NAME;
        case 4: // WITH_AUTH
          return WITH_AUTH;
        case 5: // USER
          return USER;
        case 6: // GROUP_NAMES
          return GROUP_NAMES;
        case 7: // PROJECTION_SPEC
          return PROJECTION_SPEC;
        case 8: // FILTER_SPEC
          return FILTER_SPEC;
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
  private static final int __WITHAUTH_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.CAT_NAME,_Fields.WITH_AUTH,_Fields.USER,_Fields.GROUP_NAMES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CAT_NAME, new org.apache.thrift.meta_data.FieldMetaData("catName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TBL_NAME, new org.apache.thrift.meta_data.FieldMetaData("tblName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WITH_AUTH, new org.apache.thrift.meta_data.FieldMetaData("withAuth", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.USER, new org.apache.thrift.meta_data.FieldMetaData("user", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.GROUP_NAMES, new org.apache.thrift.meta_data.FieldMetaData("groupNames", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.PROJECTION_SPEC, new org.apache.thrift.meta_data.FieldMetaData("projectionSpec", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GetPartitionsProjectionSpec.class)));
    tmpMap.put(_Fields.FILTER_SPEC, new org.apache.thrift.meta_data.FieldMetaData("filterSpec", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GetPartitionsFilterSpec.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GetPartitionsRequest.class, metaDataMap);
  }

  public GetPartitionsRequest() {
  }

  public GetPartitionsRequest(
    String dbName,
    String tblName,
    GetPartitionsProjectionSpec projectionSpec,
    GetPartitionsFilterSpec filterSpec)
  {
    this();
    this.dbName = dbName;
    this.tblName = tblName;
    this.projectionSpec = projectionSpec;
    this.filterSpec = filterSpec;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GetPartitionsRequest(GetPartitionsRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCatName()) {
      this.catName = other.catName;
    }
    if (other.isSetDbName()) {
      this.dbName = other.dbName;
    }
    if (other.isSetTblName()) {
      this.tblName = other.tblName;
    }
    this.withAuth = other.withAuth;
    if (other.isSetUser()) {
      this.user = other.user;
    }
    if (other.isSetGroupNames()) {
      List<String> __this__groupNames = new ArrayList<String>(other.groupNames);
      this.groupNames = __this__groupNames;
    }
    if (other.isSetProjectionSpec()) {
      this.projectionSpec = new GetPartitionsProjectionSpec(other.projectionSpec);
    }
    if (other.isSetFilterSpec()) {
      this.filterSpec = new GetPartitionsFilterSpec(other.filterSpec);
    }
  }

  public GetPartitionsRequest deepCopy() {
    return new GetPartitionsRequest(this);
  }

  @Override
  public void clear() {
    this.catName = null;
    this.dbName = null;
    this.tblName = null;
    setWithAuthIsSet(false);
    this.withAuth = false;
    this.user = null;
    this.groupNames = null;
    this.projectionSpec = null;
    this.filterSpec = null;
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

  public boolean isWithAuth() {
    return this.withAuth;
  }

  public void setWithAuth(boolean withAuth) {
    this.withAuth = withAuth;
    setWithAuthIsSet(true);
  }

  public void unsetWithAuth() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WITHAUTH_ISSET_ID);
  }

  /** Returns true if field withAuth is set (has been assigned a value) and false otherwise */
  public boolean isSetWithAuth() {
    return EncodingUtils.testBit(__isset_bitfield, __WITHAUTH_ISSET_ID);
  }

  public void setWithAuthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WITHAUTH_ISSET_ID, value);
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

  public int getGroupNamesSize() {
    return (this.groupNames == null) ? 0 : this.groupNames.size();
  }

  public java.util.Iterator<String> getGroupNamesIterator() {
    return (this.groupNames == null) ? null : this.groupNames.iterator();
  }

  public void addToGroupNames(String elem) {
    if (this.groupNames == null) {
      this.groupNames = new ArrayList<String>();
    }
    this.groupNames.add(elem);
  }

  public List<String> getGroupNames() {
    return this.groupNames;
  }

  public void setGroupNames(List<String> groupNames) {
    this.groupNames = groupNames;
  }

  public void unsetGroupNames() {
    this.groupNames = null;
  }

  /** Returns true if field groupNames is set (has been assigned a value) and false otherwise */
  public boolean isSetGroupNames() {
    return this.groupNames != null;
  }

  public void setGroupNamesIsSet(boolean value) {
    if (!value) {
      this.groupNames = null;
    }
  }

  public GetPartitionsProjectionSpec getProjectionSpec() {
    return this.projectionSpec;
  }

  public void setProjectionSpec(GetPartitionsProjectionSpec projectionSpec) {
    this.projectionSpec = projectionSpec;
  }

  public void unsetProjectionSpec() {
    this.projectionSpec = null;
  }

  /** Returns true if field projectionSpec is set (has been assigned a value) and false otherwise */
  public boolean isSetProjectionSpec() {
    return this.projectionSpec != null;
  }

  public void setProjectionSpecIsSet(boolean value) {
    if (!value) {
      this.projectionSpec = null;
    }
  }

  public GetPartitionsFilterSpec getFilterSpec() {
    return this.filterSpec;
  }

  public void setFilterSpec(GetPartitionsFilterSpec filterSpec) {
    this.filterSpec = filterSpec;
  }

  public void unsetFilterSpec() {
    this.filterSpec = null;
  }

  /** Returns true if field filterSpec is set (has been assigned a value) and false otherwise */
  public boolean isSetFilterSpec() {
    return this.filterSpec != null;
  }

  public void setFilterSpecIsSet(boolean value) {
    if (!value) {
      this.filterSpec = null;
    }
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

    case WITH_AUTH:
      if (value == null) {
        unsetWithAuth();
      } else {
        setWithAuth((Boolean)value);
      }
      break;

    case USER:
      if (value == null) {
        unsetUser();
      } else {
        setUser((String)value);
      }
      break;

    case GROUP_NAMES:
      if (value == null) {
        unsetGroupNames();
      } else {
        setGroupNames((List<String>)value);
      }
      break;

    case PROJECTION_SPEC:
      if (value == null) {
        unsetProjectionSpec();
      } else {
        setProjectionSpec((GetPartitionsProjectionSpec)value);
      }
      break;

    case FILTER_SPEC:
      if (value == null) {
        unsetFilterSpec();
      } else {
        setFilterSpec((GetPartitionsFilterSpec)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CAT_NAME:
      return getCatName();

    case DB_NAME:
      return getDbName();

    case TBL_NAME:
      return getTblName();

    case WITH_AUTH:
      return isWithAuth();

    case USER:
      return getUser();

    case GROUP_NAMES:
      return getGroupNames();

    case PROJECTION_SPEC:
      return getProjectionSpec();

    case FILTER_SPEC:
      return getFilterSpec();

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
    case DB_NAME:
      return isSetDbName();
    case TBL_NAME:
      return isSetTblName();
    case WITH_AUTH:
      return isSetWithAuth();
    case USER:
      return isSetUser();
    case GROUP_NAMES:
      return isSetGroupNames();
    case PROJECTION_SPEC:
      return isSetProjectionSpec();
    case FILTER_SPEC:
      return isSetFilterSpec();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GetPartitionsRequest)
      return this.equals((GetPartitionsRequest)that);
    return false;
  }

  public boolean equals(GetPartitionsRequest that) {
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

    boolean this_present_withAuth = true && this.isSetWithAuth();
    boolean that_present_withAuth = true && that.isSetWithAuth();
    if (this_present_withAuth || that_present_withAuth) {
      if (!(this_present_withAuth && that_present_withAuth))
        return false;
      if (this.withAuth != that.withAuth)
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

    boolean this_present_groupNames = true && this.isSetGroupNames();
    boolean that_present_groupNames = true && that.isSetGroupNames();
    if (this_present_groupNames || that_present_groupNames) {
      if (!(this_present_groupNames && that_present_groupNames))
        return false;
      if (!this.groupNames.equals(that.groupNames))
        return false;
    }

    boolean this_present_projectionSpec = true && this.isSetProjectionSpec();
    boolean that_present_projectionSpec = true && that.isSetProjectionSpec();
    if (this_present_projectionSpec || that_present_projectionSpec) {
      if (!(this_present_projectionSpec && that_present_projectionSpec))
        return false;
      if (!this.projectionSpec.equals(that.projectionSpec))
        return false;
    }

    boolean this_present_filterSpec = true && this.isSetFilterSpec();
    boolean that_present_filterSpec = true && that.isSetFilterSpec();
    if (this_present_filterSpec || that_present_filterSpec) {
      if (!(this_present_filterSpec && that_present_filterSpec))
        return false;
      if (!this.filterSpec.equals(that.filterSpec))
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

    boolean present_dbName = true && (isSetDbName());
    list.add(present_dbName);
    if (present_dbName)
      list.add(dbName);

    boolean present_tblName = true && (isSetTblName());
    list.add(present_tblName);
    if (present_tblName)
      list.add(tblName);

    boolean present_withAuth = true && (isSetWithAuth());
    list.add(present_withAuth);
    if (present_withAuth)
      list.add(withAuth);

    boolean present_user = true && (isSetUser());
    list.add(present_user);
    if (present_user)
      list.add(user);

    boolean present_groupNames = true && (isSetGroupNames());
    list.add(present_groupNames);
    if (present_groupNames)
      list.add(groupNames);

    boolean present_projectionSpec = true && (isSetProjectionSpec());
    list.add(present_projectionSpec);
    if (present_projectionSpec)
      list.add(projectionSpec);

    boolean present_filterSpec = true && (isSetFilterSpec());
    list.add(present_filterSpec);
    if (present_filterSpec)
      list.add(filterSpec);

    return list.hashCode();
  }

  @Override
  public int compareTo(GetPartitionsRequest other) {
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
    lastComparison = Boolean.valueOf(isSetWithAuth()).compareTo(other.isSetWithAuth());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWithAuth()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.withAuth, other.withAuth);
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
    lastComparison = Boolean.valueOf(isSetGroupNames()).compareTo(other.isSetGroupNames());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroupNames()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groupNames, other.groupNames);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProjectionSpec()).compareTo(other.isSetProjectionSpec());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProjectionSpec()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.projectionSpec, other.projectionSpec);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFilterSpec()).compareTo(other.isSetFilterSpec());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFilterSpec()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.filterSpec, other.filterSpec);
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
    StringBuilder sb = new StringBuilder("GetPartitionsRequest(");
    boolean first = true;

    if (isSetCatName()) {
      sb.append("catName:");
      if (this.catName == null) {
        sb.append("null");
      } else {
        sb.append(this.catName);
      }
      first = false;
    }
    if (!first) sb.append(", ");
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
    if (isSetWithAuth()) {
      if (!first) sb.append(", ");
      sb.append("withAuth:");
      sb.append(this.withAuth);
      first = false;
    }
    if (isSetUser()) {
      if (!first) sb.append(", ");
      sb.append("user:");
      if (this.user == null) {
        sb.append("null");
      } else {
        sb.append(this.user);
      }
      first = false;
    }
    if (isSetGroupNames()) {
      if (!first) sb.append(", ");
      sb.append("groupNames:");
      if (this.groupNames == null) {
        sb.append("null");
      } else {
        sb.append(this.groupNames);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("projectionSpec:");
    if (this.projectionSpec == null) {
      sb.append("null");
    } else {
      sb.append(this.projectionSpec);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("filterSpec:");
    if (this.filterSpec == null) {
      sb.append("null");
    } else {
      sb.append(this.filterSpec);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (projectionSpec != null) {
      projectionSpec.validate();
    }
    if (filterSpec != null) {
      filterSpec.validate();
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

  private static class GetPartitionsRequestStandardSchemeFactory implements SchemeFactory {
    public GetPartitionsRequestStandardScheme getScheme() {
      return new GetPartitionsRequestStandardScheme();
    }
  }

  private static class GetPartitionsRequestStandardScheme extends StandardScheme<GetPartitionsRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
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
          case 2: // DB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbName = iprot.readString();
              struct.setDbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TBL_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tblName = iprot.readString();
              struct.setTblNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // WITH_AUTH
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.withAuth = iprot.readBool();
              struct.setWithAuthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.user = iprot.readString();
              struct.setUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // GROUP_NAMES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list1008 = iprot.readListBegin();
                struct.groupNames = new ArrayList<String>(_list1008.size);
                String _elem1009;
                for (int _i1010 = 0; _i1010 < _list1008.size; ++_i1010)
                {
                  _elem1009 = iprot.readString();
                  struct.groupNames.add(_elem1009);
                }
                iprot.readListEnd();
              }
              struct.setGroupNamesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PROJECTION_SPEC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.projectionSpec = new GetPartitionsProjectionSpec();
              struct.projectionSpec.read(iprot);
              struct.setProjectionSpecIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // FILTER_SPEC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.filterSpec = new GetPartitionsFilterSpec();
              struct.filterSpec.read(iprot);
              struct.setFilterSpecIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.catName != null) {
        if (struct.isSetCatName()) {
          oprot.writeFieldBegin(CAT_NAME_FIELD_DESC);
          oprot.writeString(struct.catName);
          oprot.writeFieldEnd();
        }
      }
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
      if (struct.isSetWithAuth()) {
        oprot.writeFieldBegin(WITH_AUTH_FIELD_DESC);
        oprot.writeBool(struct.withAuth);
        oprot.writeFieldEnd();
      }
      if (struct.user != null) {
        if (struct.isSetUser()) {
          oprot.writeFieldBegin(USER_FIELD_DESC);
          oprot.writeString(struct.user);
          oprot.writeFieldEnd();
        }
      }
      if (struct.groupNames != null) {
        if (struct.isSetGroupNames()) {
          oprot.writeFieldBegin(GROUP_NAMES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.groupNames.size()));
            for (String _iter1011 : struct.groupNames)
            {
              oprot.writeString(_iter1011);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.projectionSpec != null) {
        oprot.writeFieldBegin(PROJECTION_SPEC_FIELD_DESC);
        struct.projectionSpec.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.filterSpec != null) {
        oprot.writeFieldBegin(FILTER_SPEC_FIELD_DESC);
        struct.filterSpec.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GetPartitionsRequestTupleSchemeFactory implements SchemeFactory {
    public GetPartitionsRequestTupleScheme getScheme() {
      return new GetPartitionsRequestTupleScheme();
    }
  }

  private static class GetPartitionsRequestTupleScheme extends TupleScheme<GetPartitionsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCatName()) {
        optionals.set(0);
      }
      if (struct.isSetDbName()) {
        optionals.set(1);
      }
      if (struct.isSetTblName()) {
        optionals.set(2);
      }
      if (struct.isSetWithAuth()) {
        optionals.set(3);
      }
      if (struct.isSetUser()) {
        optionals.set(4);
      }
      if (struct.isSetGroupNames()) {
        optionals.set(5);
      }
      if (struct.isSetProjectionSpec()) {
        optionals.set(6);
      }
      if (struct.isSetFilterSpec()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetCatName()) {
        oprot.writeString(struct.catName);
      }
      if (struct.isSetDbName()) {
        oprot.writeString(struct.dbName);
      }
      if (struct.isSetTblName()) {
        oprot.writeString(struct.tblName);
      }
      if (struct.isSetWithAuth()) {
        oprot.writeBool(struct.withAuth);
      }
      if (struct.isSetUser()) {
        oprot.writeString(struct.user);
      }
      if (struct.isSetGroupNames()) {
        {
          oprot.writeI32(struct.groupNames.size());
          for (String _iter1012 : struct.groupNames)
          {
            oprot.writeString(_iter1012);
          }
        }
      }
      if (struct.isSetProjectionSpec()) {
        struct.projectionSpec.write(oprot);
      }
      if (struct.isSetFilterSpec()) {
        struct.filterSpec.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.catName = iprot.readString();
        struct.setCatNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.dbName = iprot.readString();
        struct.setDbNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tblName = iprot.readString();
        struct.setTblNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.withAuth = iprot.readBool();
        struct.setWithAuthIsSet(true);
      }
      if (incoming.get(4)) {
        struct.user = iprot.readString();
        struct.setUserIsSet(true);
      }
      if (incoming.get(5)) {
        {
          org.apache.thrift.protocol.TList _list1013 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.groupNames = new ArrayList<String>(_list1013.size);
          String _elem1014;
          for (int _i1015 = 0; _i1015 < _list1013.size; ++_i1015)
          {
            _elem1014 = iprot.readString();
            struct.groupNames.add(_elem1014);
          }
        }
        struct.setGroupNamesIsSet(true);
      }
      if (incoming.get(6)) {
        struct.projectionSpec = new GetPartitionsProjectionSpec();
        struct.projectionSpec.read(iprot);
        struct.setProjectionSpecIsSet(true);
      }
      if (incoming.get(7)) {
        struct.filterSpec = new GetPartitionsFilterSpec();
        struct.filterSpec.read(iprot);
        struct.setFilterSpecIsSet(true);
      }
    }
  }

}

