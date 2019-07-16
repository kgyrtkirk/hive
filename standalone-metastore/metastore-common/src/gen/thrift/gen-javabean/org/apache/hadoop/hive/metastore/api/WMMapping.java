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
public class WMMapping implements org.apache.thrift.TBase<WMMapping, WMMapping._Fields>, java.io.Serializable, Cloneable, Comparable<WMMapping> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WMMapping");

  private static final org.apache.thrift.protocol.TField RESOURCE_PLAN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("resourcePlanName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENTITY_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("entityType", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ENTITY_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("entityName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField POOL_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("poolPath", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ORDERING_FIELD_DESC = new org.apache.thrift.protocol.TField("ordering", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField NS_FIELD_DESC = new org.apache.thrift.protocol.TField("ns", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WMMappingStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WMMappingTupleSchemeFactory());
  }

  private String resourcePlanName; // required
  private String entityType; // required
  private String entityName; // required
  private String poolPath; // optional
  private int ordering; // optional
  private String ns; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESOURCE_PLAN_NAME((short)1, "resourcePlanName"),
    ENTITY_TYPE((short)2, "entityType"),
    ENTITY_NAME((short)3, "entityName"),
    POOL_PATH((short)4, "poolPath"),
    ORDERING((short)5, "ordering"),
    NS((short)6, "ns");

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
        case 1: // RESOURCE_PLAN_NAME
          return RESOURCE_PLAN_NAME;
        case 2: // ENTITY_TYPE
          return ENTITY_TYPE;
        case 3: // ENTITY_NAME
          return ENTITY_NAME;
        case 4: // POOL_PATH
          return POOL_PATH;
        case 5: // ORDERING
          return ORDERING;
        case 6: // NS
          return NS;
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
  private static final int __ORDERING_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.POOL_PATH,_Fields.ORDERING,_Fields.NS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESOURCE_PLAN_NAME, new org.apache.thrift.meta_data.FieldMetaData("resourcePlanName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENTITY_TYPE, new org.apache.thrift.meta_data.FieldMetaData("entityType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENTITY_NAME, new org.apache.thrift.meta_data.FieldMetaData("entityName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.POOL_PATH, new org.apache.thrift.meta_data.FieldMetaData("poolPath", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORDERING, new org.apache.thrift.meta_data.FieldMetaData("ordering", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.NS, new org.apache.thrift.meta_data.FieldMetaData("ns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WMMapping.class, metaDataMap);
  }

  public WMMapping() {
  }

  public WMMapping(
    String resourcePlanName,
    String entityType,
    String entityName)
  {
    this();
    this.resourcePlanName = resourcePlanName;
    this.entityType = entityType;
    this.entityName = entityName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WMMapping(WMMapping other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetResourcePlanName()) {
      this.resourcePlanName = other.resourcePlanName;
    }
    if (other.isSetEntityType()) {
      this.entityType = other.entityType;
    }
    if (other.isSetEntityName()) {
      this.entityName = other.entityName;
    }
    if (other.isSetPoolPath()) {
      this.poolPath = other.poolPath;
    }
    this.ordering = other.ordering;
    if (other.isSetNs()) {
      this.ns = other.ns;
    }
  }

  public WMMapping deepCopy() {
    return new WMMapping(this);
  }

  @Override
  public void clear() {
    this.resourcePlanName = null;
    this.entityType = null;
    this.entityName = null;
    this.poolPath = null;
    setOrderingIsSet(false);
    this.ordering = 0;
    this.ns = null;
  }

  public String getResourcePlanName() {
    return this.resourcePlanName;
  }

  public void setResourcePlanName(String resourcePlanName) {
    this.resourcePlanName = resourcePlanName;
  }

  public void unsetResourcePlanName() {
    this.resourcePlanName = null;
  }

  /** Returns true if field resourcePlanName is set (has been assigned a value) and false otherwise */
  public boolean isSetResourcePlanName() {
    return this.resourcePlanName != null;
  }

  public void setResourcePlanNameIsSet(boolean value) {
    if (!value) {
      this.resourcePlanName = null;
    }
  }

  public String getEntityType() {
    return this.entityType;
  }

  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  public void unsetEntityType() {
    this.entityType = null;
  }

  /** Returns true if field entityType is set (has been assigned a value) and false otherwise */
  public boolean isSetEntityType() {
    return this.entityType != null;
  }

  public void setEntityTypeIsSet(boolean value) {
    if (!value) {
      this.entityType = null;
    }
  }

  public String getEntityName() {
    return this.entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public void unsetEntityName() {
    this.entityName = null;
  }

  /** Returns true if field entityName is set (has been assigned a value) and false otherwise */
  public boolean isSetEntityName() {
    return this.entityName != null;
  }

  public void setEntityNameIsSet(boolean value) {
    if (!value) {
      this.entityName = null;
    }
  }

  public String getPoolPath() {
    return this.poolPath;
  }

  public void setPoolPath(String poolPath) {
    this.poolPath = poolPath;
  }

  public void unsetPoolPath() {
    this.poolPath = null;
  }

  /** Returns true if field poolPath is set (has been assigned a value) and false otherwise */
  public boolean isSetPoolPath() {
    return this.poolPath != null;
  }

  public void setPoolPathIsSet(boolean value) {
    if (!value) {
      this.poolPath = null;
    }
  }

  public int getOrdering() {
    return this.ordering;
  }

  public void setOrdering(int ordering) {
    this.ordering = ordering;
    setOrderingIsSet(true);
  }

  public void unsetOrdering() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ORDERING_ISSET_ID);
  }

  /** Returns true if field ordering is set (has been assigned a value) and false otherwise */
  public boolean isSetOrdering() {
    return EncodingUtils.testBit(__isset_bitfield, __ORDERING_ISSET_ID);
  }

  public void setOrderingIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ORDERING_ISSET_ID, value);
  }

  public String getNs() {
    return this.ns;
  }

  public void setNs(String ns) {
    this.ns = ns;
  }

  public void unsetNs() {
    this.ns = null;
  }

  /** Returns true if field ns is set (has been assigned a value) and false otherwise */
  public boolean isSetNs() {
    return this.ns != null;
  }

  public void setNsIsSet(boolean value) {
    if (!value) {
      this.ns = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RESOURCE_PLAN_NAME:
      if (value == null) {
        unsetResourcePlanName();
      } else {
        setResourcePlanName((String)value);
      }
      break;

    case ENTITY_TYPE:
      if (value == null) {
        unsetEntityType();
      } else {
        setEntityType((String)value);
      }
      break;

    case ENTITY_NAME:
      if (value == null) {
        unsetEntityName();
      } else {
        setEntityName((String)value);
      }
      break;

    case POOL_PATH:
      if (value == null) {
        unsetPoolPath();
      } else {
        setPoolPath((String)value);
      }
      break;

    case ORDERING:
      if (value == null) {
        unsetOrdering();
      } else {
        setOrdering((Integer)value);
      }
      break;

    case NS:
      if (value == null) {
        unsetNs();
      } else {
        setNs((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RESOURCE_PLAN_NAME:
      return getResourcePlanName();

    case ENTITY_TYPE:
      return getEntityType();

    case ENTITY_NAME:
      return getEntityName();

    case POOL_PATH:
      return getPoolPath();

    case ORDERING:
      return getOrdering();

    case NS:
      return getNs();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RESOURCE_PLAN_NAME:
      return isSetResourcePlanName();
    case ENTITY_TYPE:
      return isSetEntityType();
    case ENTITY_NAME:
      return isSetEntityName();
    case POOL_PATH:
      return isSetPoolPath();
    case ORDERING:
      return isSetOrdering();
    case NS:
      return isSetNs();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WMMapping)
      return this.equals((WMMapping)that);
    return false;
  }

  public boolean equals(WMMapping that) {
    if (that == null)
      return false;

    boolean this_present_resourcePlanName = true && this.isSetResourcePlanName();
    boolean that_present_resourcePlanName = true && that.isSetResourcePlanName();
    if (this_present_resourcePlanName || that_present_resourcePlanName) {
      if (!(this_present_resourcePlanName && that_present_resourcePlanName))
        return false;
      if (!this.resourcePlanName.equals(that.resourcePlanName))
        return false;
    }

    boolean this_present_entityType = true && this.isSetEntityType();
    boolean that_present_entityType = true && that.isSetEntityType();
    if (this_present_entityType || that_present_entityType) {
      if (!(this_present_entityType && that_present_entityType))
        return false;
      if (!this.entityType.equals(that.entityType))
        return false;
    }

    boolean this_present_entityName = true && this.isSetEntityName();
    boolean that_present_entityName = true && that.isSetEntityName();
    if (this_present_entityName || that_present_entityName) {
      if (!(this_present_entityName && that_present_entityName))
        return false;
      if (!this.entityName.equals(that.entityName))
        return false;
    }

    boolean this_present_poolPath = true && this.isSetPoolPath();
    boolean that_present_poolPath = true && that.isSetPoolPath();
    if (this_present_poolPath || that_present_poolPath) {
      if (!(this_present_poolPath && that_present_poolPath))
        return false;
      if (!this.poolPath.equals(that.poolPath))
        return false;
    }

    boolean this_present_ordering = true && this.isSetOrdering();
    boolean that_present_ordering = true && that.isSetOrdering();
    if (this_present_ordering || that_present_ordering) {
      if (!(this_present_ordering && that_present_ordering))
        return false;
      if (this.ordering != that.ordering)
        return false;
    }

    boolean this_present_ns = true && this.isSetNs();
    boolean that_present_ns = true && that.isSetNs();
    if (this_present_ns || that_present_ns) {
      if (!(this_present_ns && that_present_ns))
        return false;
      if (!this.ns.equals(that.ns))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_resourcePlanName = true && (isSetResourcePlanName());
    list.add(present_resourcePlanName);
    if (present_resourcePlanName)
      list.add(resourcePlanName);

    boolean present_entityType = true && (isSetEntityType());
    list.add(present_entityType);
    if (present_entityType)
      list.add(entityType);

    boolean present_entityName = true && (isSetEntityName());
    list.add(present_entityName);
    if (present_entityName)
      list.add(entityName);

    boolean present_poolPath = true && (isSetPoolPath());
    list.add(present_poolPath);
    if (present_poolPath)
      list.add(poolPath);

    boolean present_ordering = true && (isSetOrdering());
    list.add(present_ordering);
    if (present_ordering)
      list.add(ordering);

    boolean present_ns = true && (isSetNs());
    list.add(present_ns);
    if (present_ns)
      list.add(ns);

    return list.hashCode();
  }

  @Override
  public int compareTo(WMMapping other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetResourcePlanName()).compareTo(other.isSetResourcePlanName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourcePlanName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourcePlanName, other.resourcePlanName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEntityType()).compareTo(other.isSetEntityType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEntityType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.entityType, other.entityType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEntityName()).compareTo(other.isSetEntityName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEntityName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.entityName, other.entityName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPoolPath()).compareTo(other.isSetPoolPath());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPoolPath()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.poolPath, other.poolPath);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOrdering()).compareTo(other.isSetOrdering());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrdering()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ordering, other.ordering);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNs()).compareTo(other.isSetNs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ns, other.ns);
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
    StringBuilder sb = new StringBuilder("WMMapping(");
    boolean first = true;

    sb.append("resourcePlanName:");
    if (this.resourcePlanName == null) {
      sb.append("null");
    } else {
      sb.append(this.resourcePlanName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("entityType:");
    if (this.entityType == null) {
      sb.append("null");
    } else {
      sb.append(this.entityType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("entityName:");
    if (this.entityName == null) {
      sb.append("null");
    } else {
      sb.append(this.entityName);
    }
    first = false;
    if (isSetPoolPath()) {
      if (!first) sb.append(", ");
      sb.append("poolPath:");
      if (this.poolPath == null) {
        sb.append("null");
      } else {
        sb.append(this.poolPath);
      }
      first = false;
    }
    if (isSetOrdering()) {
      if (!first) sb.append(", ");
      sb.append("ordering:");
      sb.append(this.ordering);
      first = false;
    }
    if (isSetNs()) {
      if (!first) sb.append(", ");
      sb.append("ns:");
      if (this.ns == null) {
        sb.append("null");
      } else {
        sb.append(this.ns);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetResourcePlanName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'resourcePlanName' is unset! Struct:" + toString());
    }

    if (!isSetEntityType()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'entityType' is unset! Struct:" + toString());
    }

    if (!isSetEntityName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'entityName' is unset! Struct:" + toString());
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

  private static class WMMappingStandardSchemeFactory implements SchemeFactory {
    public WMMappingStandardScheme getScheme() {
      return new WMMappingStandardScheme();
    }
  }

  private static class WMMappingStandardScheme extends StandardScheme<WMMapping> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WMMapping struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESOURCE_PLAN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resourcePlanName = iprot.readString();
              struct.setResourcePlanNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENTITY_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.entityType = iprot.readString();
              struct.setEntityTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ENTITY_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.entityName = iprot.readString();
              struct.setEntityNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // POOL_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.poolPath = iprot.readString();
              struct.setPoolPathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ORDERING
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ordering = iprot.readI32();
              struct.setOrderingIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // NS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ns = iprot.readString();
              struct.setNsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, WMMapping struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.resourcePlanName != null) {
        oprot.writeFieldBegin(RESOURCE_PLAN_NAME_FIELD_DESC);
        oprot.writeString(struct.resourcePlanName);
        oprot.writeFieldEnd();
      }
      if (struct.entityType != null) {
        oprot.writeFieldBegin(ENTITY_TYPE_FIELD_DESC);
        oprot.writeString(struct.entityType);
        oprot.writeFieldEnd();
      }
      if (struct.entityName != null) {
        oprot.writeFieldBegin(ENTITY_NAME_FIELD_DESC);
        oprot.writeString(struct.entityName);
        oprot.writeFieldEnd();
      }
      if (struct.poolPath != null) {
        if (struct.isSetPoolPath()) {
          oprot.writeFieldBegin(POOL_PATH_FIELD_DESC);
          oprot.writeString(struct.poolPath);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetOrdering()) {
        oprot.writeFieldBegin(ORDERING_FIELD_DESC);
        oprot.writeI32(struct.ordering);
        oprot.writeFieldEnd();
      }
      if (struct.ns != null) {
        if (struct.isSetNs()) {
          oprot.writeFieldBegin(NS_FIELD_DESC);
          oprot.writeString(struct.ns);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WMMappingTupleSchemeFactory implements SchemeFactory {
    public WMMappingTupleScheme getScheme() {
      return new WMMappingTupleScheme();
    }
  }

  private static class WMMappingTupleScheme extends TupleScheme<WMMapping> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WMMapping struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.resourcePlanName);
      oprot.writeString(struct.entityType);
      oprot.writeString(struct.entityName);
      BitSet optionals = new BitSet();
      if (struct.isSetPoolPath()) {
        optionals.set(0);
      }
      if (struct.isSetOrdering()) {
        optionals.set(1);
      }
      if (struct.isSetNs()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetPoolPath()) {
        oprot.writeString(struct.poolPath);
      }
      if (struct.isSetOrdering()) {
        oprot.writeI32(struct.ordering);
      }
      if (struct.isSetNs()) {
        oprot.writeString(struct.ns);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WMMapping struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.resourcePlanName = iprot.readString();
      struct.setResourcePlanNameIsSet(true);
      struct.entityType = iprot.readString();
      struct.setEntityTypeIsSet(true);
      struct.entityName = iprot.readString();
      struct.setEntityNameIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.poolPath = iprot.readString();
        struct.setPoolPathIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ordering = iprot.readI32();
        struct.setOrderingIsSet(true);
      }
      if (incoming.get(2)) {
        struct.ns = iprot.readString();
        struct.setNsIsSet(true);
      }
    }
  }

}

