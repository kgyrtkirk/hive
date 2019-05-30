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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class ScheduledQueryMaintenanceRequest implements org.apache.thrift.TBase<ScheduledQueryMaintenanceRequest, ScheduledQueryMaintenanceRequest._Fields>, java.io.Serializable, Cloneable, Comparable<ScheduledQueryMaintenanceRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ScheduledQueryMaintenanceRequest");

  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SCHEDULE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("scheduleId", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("enabled", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField CLUSTER_FUCK_FIELD_DESC = new org.apache.thrift.protocol.TField("clusterFuck", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField SCHEDULE_FIELD_DESC = new org.apache.thrift.protocol.TField("schedule", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField USER_FIELD_DESC = new org.apache.thrift.protocol.TField("user", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField QUERY_FIELD_DESC = new org.apache.thrift.protocol.TField("query", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ScheduledQueryMaintenanceRequestStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ScheduledQueryMaintenanceRequestTupleSchemeFactory());
  }

  private EventRequestType type; // required
  private String scheduleId; // required
  private boolean enabled; // optional
  private String clusterFuck; // optional
  private Schedule schedule; // optional
  private String user; // optional
  private String query; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see EventRequestType
     */
    TYPE((short)1, "type"),
    SCHEDULE_ID((short)2, "scheduleId"),
    ENABLED((short)3, "enabled"),
    CLUSTER_FUCK((short)4, "clusterFuck"),
    SCHEDULE((short)5, "schedule"),
    USER((short)6, "user"),
    QUERY((short)7, "query");

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
        case 1: // TYPE
          return TYPE;
        case 2: // SCHEDULE_ID
          return SCHEDULE_ID;
        case 3: // ENABLED
          return ENABLED;
        case 4: // CLUSTER_FUCK
          return CLUSTER_FUCK;
        case 5: // SCHEDULE
          return SCHEDULE;
        case 6: // USER
          return USER;
        case 7: // QUERY
          return QUERY;
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
  private static final int __ENABLED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ENABLED,_Fields.CLUSTER_FUCK,_Fields.SCHEDULE,_Fields.USER,_Fields.QUERY};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, EventRequestType.class)));
    tmpMap.put(_Fields.SCHEDULE_ID, new org.apache.thrift.meta_data.FieldMetaData("scheduleId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENABLED, new org.apache.thrift.meta_data.FieldMetaData("enabled", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.CLUSTER_FUCK, new org.apache.thrift.meta_data.FieldMetaData("clusterFuck", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SCHEDULE, new org.apache.thrift.meta_data.FieldMetaData("schedule", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Schedule.class)));
    tmpMap.put(_Fields.USER, new org.apache.thrift.meta_data.FieldMetaData("user", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.QUERY, new org.apache.thrift.meta_data.FieldMetaData("query", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ScheduledQueryMaintenanceRequest.class, metaDataMap);
  }

  public ScheduledQueryMaintenanceRequest() {
  }

  public ScheduledQueryMaintenanceRequest(
    EventRequestType type,
    String scheduleId)
  {
    this();
    this.type = type;
    this.scheduleId = scheduleId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScheduledQueryMaintenanceRequest(ScheduledQueryMaintenanceRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetScheduleId()) {
      this.scheduleId = other.scheduleId;
    }
    this.enabled = other.enabled;
    if (other.isSetClusterFuck()) {
      this.clusterFuck = other.clusterFuck;
    }
    if (other.isSetSchedule()) {
      this.schedule = new Schedule(other.schedule);
    }
    if (other.isSetUser()) {
      this.user = other.user;
    }
    if (other.isSetQuery()) {
      this.query = other.query;
    }
  }

  public ScheduledQueryMaintenanceRequest deepCopy() {
    return new ScheduledQueryMaintenanceRequest(this);
  }

  @Override
  public void clear() {
    this.type = null;
    this.scheduleId = null;
    setEnabledIsSet(false);
    this.enabled = false;
    this.clusterFuck = null;
    this.schedule = null;
    this.user = null;
    this.query = null;
  }

  /**
   * 
   * @see EventRequestType
   */
  public EventRequestType getType() {
    return this.type;
  }

  /**
   * 
   * @see EventRequestType
   */
  public void setType(EventRequestType type) {
    this.type = type;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public String getScheduleId() {
    return this.scheduleId;
  }

  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
  }

  public void unsetScheduleId() {
    this.scheduleId = null;
  }

  /** Returns true if field scheduleId is set (has been assigned a value) and false otherwise */
  public boolean isSetScheduleId() {
    return this.scheduleId != null;
  }

  public void setScheduleIdIsSet(boolean value) {
    if (!value) {
      this.scheduleId = null;
    }
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    setEnabledIsSet(true);
  }

  public void unsetEnabled() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ENABLED_ISSET_ID);
  }

  /** Returns true if field enabled is set (has been assigned a value) and false otherwise */
  public boolean isSetEnabled() {
    return EncodingUtils.testBit(__isset_bitfield, __ENABLED_ISSET_ID);
  }

  public void setEnabledIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ENABLED_ISSET_ID, value);
  }

  public String getClusterFuck() {
    return this.clusterFuck;
  }

  public void setClusterFuck(String clusterFuck) {
    this.clusterFuck = clusterFuck;
  }

  public void unsetClusterFuck() {
    this.clusterFuck = null;
  }

  /** Returns true if field clusterFuck is set (has been assigned a value) and false otherwise */
  public boolean isSetClusterFuck() {
    return this.clusterFuck != null;
  }

  public void setClusterFuckIsSet(boolean value) {
    if (!value) {
      this.clusterFuck = null;
    }
  }

  public Schedule getSchedule() {
    return this.schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public void unsetSchedule() {
    this.schedule = null;
  }

  /** Returns true if field schedule is set (has been assigned a value) and false otherwise */
  public boolean isSetSchedule() {
    return this.schedule != null;
  }

  public void setScheduleIsSet(boolean value) {
    if (!value) {
      this.schedule = null;
    }
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

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public void unsetQuery() {
    this.query = null;
  }

  /** Returns true if field query is set (has been assigned a value) and false otherwise */
  public boolean isSetQuery() {
    return this.query != null;
  }

  public void setQueryIsSet(boolean value) {
    if (!value) {
      this.query = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((EventRequestType)value);
      }
      break;

    case SCHEDULE_ID:
      if (value == null) {
        unsetScheduleId();
      } else {
        setScheduleId((String)value);
      }
      break;

    case ENABLED:
      if (value == null) {
        unsetEnabled();
      } else {
        setEnabled((Boolean)value);
      }
      break;

    case CLUSTER_FUCK:
      if (value == null) {
        unsetClusterFuck();
      } else {
        setClusterFuck((String)value);
      }
      break;

    case SCHEDULE:
      if (value == null) {
        unsetSchedule();
      } else {
        setSchedule((Schedule)value);
      }
      break;

    case USER:
      if (value == null) {
        unsetUser();
      } else {
        setUser((String)value);
      }
      break;

    case QUERY:
      if (value == null) {
        unsetQuery();
      } else {
        setQuery((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPE:
      return getType();

    case SCHEDULE_ID:
      return getScheduleId();

    case ENABLED:
      return isEnabled();

    case CLUSTER_FUCK:
      return getClusterFuck();

    case SCHEDULE:
      return getSchedule();

    case USER:
      return getUser();

    case QUERY:
      return getQuery();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPE:
      return isSetType();
    case SCHEDULE_ID:
      return isSetScheduleId();
    case ENABLED:
      return isSetEnabled();
    case CLUSTER_FUCK:
      return isSetClusterFuck();
    case SCHEDULE:
      return isSetSchedule();
    case USER:
      return isSetUser();
    case QUERY:
      return isSetQuery();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ScheduledQueryMaintenanceRequest)
      return this.equals((ScheduledQueryMaintenanceRequest)that);
    return false;
  }

  public boolean equals(ScheduledQueryMaintenanceRequest that) {
    if (that == null)
      return false;

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_scheduleId = true && this.isSetScheduleId();
    boolean that_present_scheduleId = true && that.isSetScheduleId();
    if (this_present_scheduleId || that_present_scheduleId) {
      if (!(this_present_scheduleId && that_present_scheduleId))
        return false;
      if (!this.scheduleId.equals(that.scheduleId))
        return false;
    }

    boolean this_present_enabled = true && this.isSetEnabled();
    boolean that_present_enabled = true && that.isSetEnabled();
    if (this_present_enabled || that_present_enabled) {
      if (!(this_present_enabled && that_present_enabled))
        return false;
      if (this.enabled != that.enabled)
        return false;
    }

    boolean this_present_clusterFuck = true && this.isSetClusterFuck();
    boolean that_present_clusterFuck = true && that.isSetClusterFuck();
    if (this_present_clusterFuck || that_present_clusterFuck) {
      if (!(this_present_clusterFuck && that_present_clusterFuck))
        return false;
      if (!this.clusterFuck.equals(that.clusterFuck))
        return false;
    }

    boolean this_present_schedule = true && this.isSetSchedule();
    boolean that_present_schedule = true && that.isSetSchedule();
    if (this_present_schedule || that_present_schedule) {
      if (!(this_present_schedule && that_present_schedule))
        return false;
      if (!this.schedule.equals(that.schedule))
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

    boolean this_present_query = true && this.isSetQuery();
    boolean that_present_query = true && that.isSetQuery();
    if (this_present_query || that_present_query) {
      if (!(this_present_query && that_present_query))
        return false;
      if (!this.query.equals(that.query))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_scheduleId = true && (isSetScheduleId());
    list.add(present_scheduleId);
    if (present_scheduleId)
      list.add(scheduleId);

    boolean present_enabled = true && (isSetEnabled());
    list.add(present_enabled);
    if (present_enabled)
      list.add(enabled);

    boolean present_clusterFuck = true && (isSetClusterFuck());
    list.add(present_clusterFuck);
    if (present_clusterFuck)
      list.add(clusterFuck);

    boolean present_schedule = true && (isSetSchedule());
    list.add(present_schedule);
    if (present_schedule)
      list.add(schedule);

    boolean present_user = true && (isSetUser());
    list.add(present_user);
    if (present_user)
      list.add(user);

    boolean present_query = true && (isSetQuery());
    list.add(present_query);
    if (present_query)
      list.add(query);

    return list.hashCode();
  }

  @Override
  public int compareTo(ScheduledQueryMaintenanceRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetScheduleId()).compareTo(other.isSetScheduleId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetScheduleId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.scheduleId, other.scheduleId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnabled()).compareTo(other.isSetEnabled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnabled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.enabled, other.enabled);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClusterFuck()).compareTo(other.isSetClusterFuck());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClusterFuck()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clusterFuck, other.clusterFuck);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSchedule()).compareTo(other.isSetSchedule());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSchedule()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.schedule, other.schedule);
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
    lastComparison = Boolean.valueOf(isSetQuery()).compareTo(other.isSetQuery());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuery()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.query, other.query);
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
    StringBuilder sb = new StringBuilder("ScheduledQueryMaintenanceRequest(");
    boolean first = true;

    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("scheduleId:");
    if (this.scheduleId == null) {
      sb.append("null");
    } else {
      sb.append(this.scheduleId);
    }
    first = false;
    if (isSetEnabled()) {
      if (!first) sb.append(", ");
      sb.append("enabled:");
      sb.append(this.enabled);
      first = false;
    }
    if (isSetClusterFuck()) {
      if (!first) sb.append(", ");
      sb.append("clusterFuck:");
      if (this.clusterFuck == null) {
        sb.append("null");
      } else {
        sb.append(this.clusterFuck);
      }
      first = false;
    }
    if (isSetSchedule()) {
      if (!first) sb.append(", ");
      sb.append("schedule:");
      if (this.schedule == null) {
        sb.append("null");
      } else {
        sb.append(this.schedule);
      }
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
    if (isSetQuery()) {
      if (!first) sb.append(", ");
      sb.append("query:");
      if (this.query == null) {
        sb.append("null");
      } else {
        sb.append(this.query);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetType()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'type' is unset! Struct:" + toString());
    }

    if (!isSetScheduleId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'scheduleId' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
    if (schedule != null) {
      schedule.validate();
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

  private static class ScheduledQueryMaintenanceRequestStandardSchemeFactory implements SchemeFactory {
    public ScheduledQueryMaintenanceRequestStandardScheme getScheme() {
      return new ScheduledQueryMaintenanceRequestStandardScheme();
    }
  }

  private static class ScheduledQueryMaintenanceRequestStandardScheme extends StandardScheme<ScheduledQueryMaintenanceRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ScheduledQueryMaintenanceRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = org.apache.hadoop.hive.metastore.api.EventRequestType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SCHEDULE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.scheduleId = iprot.readString();
              struct.setScheduleIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.enabled = iprot.readBool();
              struct.setEnabledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CLUSTER_FUCK
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.clusterFuck = iprot.readString();
              struct.setClusterFuckIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SCHEDULE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.schedule = new Schedule();
              struct.schedule.read(iprot);
              struct.setScheduleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.user = iprot.readString();
              struct.setUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // QUERY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.query = iprot.readString();
              struct.setQueryIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ScheduledQueryMaintenanceRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.scheduleId != null) {
        oprot.writeFieldBegin(SCHEDULE_ID_FIELD_DESC);
        oprot.writeString(struct.scheduleId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetEnabled()) {
        oprot.writeFieldBegin(ENABLED_FIELD_DESC);
        oprot.writeBool(struct.enabled);
        oprot.writeFieldEnd();
      }
      if (struct.clusterFuck != null) {
        if (struct.isSetClusterFuck()) {
          oprot.writeFieldBegin(CLUSTER_FUCK_FIELD_DESC);
          oprot.writeString(struct.clusterFuck);
          oprot.writeFieldEnd();
        }
      }
      if (struct.schedule != null) {
        if (struct.isSetSchedule()) {
          oprot.writeFieldBegin(SCHEDULE_FIELD_DESC);
          struct.schedule.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.user != null) {
        if (struct.isSetUser()) {
          oprot.writeFieldBegin(USER_FIELD_DESC);
          oprot.writeString(struct.user);
          oprot.writeFieldEnd();
        }
      }
      if (struct.query != null) {
        if (struct.isSetQuery()) {
          oprot.writeFieldBegin(QUERY_FIELD_DESC);
          oprot.writeString(struct.query);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScheduledQueryMaintenanceRequestTupleSchemeFactory implements SchemeFactory {
    public ScheduledQueryMaintenanceRequestTupleScheme getScheme() {
      return new ScheduledQueryMaintenanceRequestTupleScheme();
    }
  }

  private static class ScheduledQueryMaintenanceRequestTupleScheme extends TupleScheme<ScheduledQueryMaintenanceRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ScheduledQueryMaintenanceRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.type.getValue());
      oprot.writeString(struct.scheduleId);
      BitSet optionals = new BitSet();
      if (struct.isSetEnabled()) {
        optionals.set(0);
      }
      if (struct.isSetClusterFuck()) {
        optionals.set(1);
      }
      if (struct.isSetSchedule()) {
        optionals.set(2);
      }
      if (struct.isSetUser()) {
        optionals.set(3);
      }
      if (struct.isSetQuery()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetEnabled()) {
        oprot.writeBool(struct.enabled);
      }
      if (struct.isSetClusterFuck()) {
        oprot.writeString(struct.clusterFuck);
      }
      if (struct.isSetSchedule()) {
        struct.schedule.write(oprot);
      }
      if (struct.isSetUser()) {
        oprot.writeString(struct.user);
      }
      if (struct.isSetQuery()) {
        oprot.writeString(struct.query);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ScheduledQueryMaintenanceRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.type = org.apache.hadoop.hive.metastore.api.EventRequestType.findByValue(iprot.readI32());
      struct.setTypeIsSet(true);
      struct.scheduleId = iprot.readString();
      struct.setScheduleIdIsSet(true);
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.enabled = iprot.readBool();
        struct.setEnabledIsSet(true);
      }
      if (incoming.get(1)) {
        struct.clusterFuck = iprot.readString();
        struct.setClusterFuckIsSet(true);
      }
      if (incoming.get(2)) {
        struct.schedule = new Schedule();
        struct.schedule.read(iprot);
        struct.setScheduleIsSet(true);
      }
      if (incoming.get(3)) {
        struct.user = iprot.readString();
        struct.setUserIsSet(true);
      }
      if (incoming.get(4)) {
        struct.query = iprot.readString();
        struct.setQueryIsSet(true);
      }
    }
  }

}

