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
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class ScheduledQuery implements org.apache.thrift.TBase<ScheduledQuery, ScheduledQuery._Fields>, java.io.Serializable, Cloneable, Comparable<ScheduledQuery> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ScheduledQuery");

  private static final org.apache.thrift.protocol.TField SCHEDULE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("scheduleName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("enabled", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField CLUSTER_FUCK_FIELD_DESC = new org.apache.thrift.protocol.TField("clusterFuck", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField SCHEDULE_FIELD_DESC = new org.apache.thrift.protocol.TField("schedule", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField USER_FIELD_DESC = new org.apache.thrift.protocol.TField("user", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField QUERY_FIELD_DESC = new org.apache.thrift.protocol.TField("query", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField NEXT_EXECUTION_FIELD_DESC = new org.apache.thrift.protocol.TField("nextExecution", org.apache.thrift.protocol.TType.I32, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ScheduledQueryStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ScheduledQueryTupleSchemeFactory());
  }

  private String scheduleName; // required
  private boolean enabled; // optional
  private String clusterFuck; // optional
  private Schedule schedule; // optional
  private String user; // optional
  private String query; // optional
  private int nextExecution; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SCHEDULE_NAME((short)1, "scheduleName"),
    ENABLED((short)2, "enabled"),
    CLUSTER_FUCK((short)3, "clusterFuck"),
    SCHEDULE((short)4, "schedule"),
    USER((short)5, "user"),
    QUERY((short)6, "query"),
    NEXT_EXECUTION((short)7, "nextExecution");

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
        case 1: // SCHEDULE_NAME
          return SCHEDULE_NAME;
        case 2: // ENABLED
          return ENABLED;
        case 3: // CLUSTER_FUCK
          return CLUSTER_FUCK;
        case 4: // SCHEDULE
          return SCHEDULE;
        case 5: // USER
          return USER;
        case 6: // QUERY
          return QUERY;
        case 7: // NEXT_EXECUTION
          return NEXT_EXECUTION;
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
  private static final int __NEXTEXECUTION_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ENABLED,_Fields.CLUSTER_FUCK,_Fields.SCHEDULE,_Fields.USER,_Fields.QUERY,_Fields.NEXT_EXECUTION};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SCHEDULE_NAME, new org.apache.thrift.meta_data.FieldMetaData("scheduleName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
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
    tmpMap.put(_Fields.NEXT_EXECUTION, new org.apache.thrift.meta_data.FieldMetaData("nextExecution", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ScheduledQuery.class, metaDataMap);
  }

  public ScheduledQuery() {
  }

  public ScheduledQuery(
    String scheduleName)
  {
    this();
    this.scheduleName = scheduleName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScheduledQuery(ScheduledQuery other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetScheduleName()) {
      this.scheduleName = other.scheduleName;
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
    this.nextExecution = other.nextExecution;
  }

  public ScheduledQuery deepCopy() {
    return new ScheduledQuery(this);
  }

  @Override
  public void clear() {
    this.scheduleName = null;
    setEnabledIsSet(false);
    this.enabled = false;
    this.clusterFuck = null;
    this.schedule = null;
    this.user = null;
    this.query = null;
    setNextExecutionIsSet(false);
    this.nextExecution = 0;
  }

  public String getScheduleName() {
    return this.scheduleName;
  }

  public void setScheduleName(String scheduleName) {
    this.scheduleName = scheduleName;
  }

  public void unsetScheduleName() {
    this.scheduleName = null;
  }

  /** Returns true if field scheduleName is set (has been assigned a value) and false otherwise */
  public boolean isSetScheduleName() {
    return this.scheduleName != null;
  }

  public void setScheduleNameIsSet(boolean value) {
    if (!value) {
      this.scheduleName = null;
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

  public int getNextExecution() {
    return this.nextExecution;
  }

  public void setNextExecution(int nextExecution) {
    this.nextExecution = nextExecution;
    setNextExecutionIsSet(true);
  }

  public void unsetNextExecution() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NEXTEXECUTION_ISSET_ID);
  }

  /** Returns true if field nextExecution is set (has been assigned a value) and false otherwise */
  public boolean isSetNextExecution() {
    return EncodingUtils.testBit(__isset_bitfield, __NEXTEXECUTION_ISSET_ID);
  }

  public void setNextExecutionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NEXTEXECUTION_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SCHEDULE_NAME:
      if (value == null) {
        unsetScheduleName();
      } else {
        setScheduleName((String)value);
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

    case NEXT_EXECUTION:
      if (value == null) {
        unsetNextExecution();
      } else {
        setNextExecution((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SCHEDULE_NAME:
      return getScheduleName();

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

    case NEXT_EXECUTION:
      return getNextExecution();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SCHEDULE_NAME:
      return isSetScheduleName();
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
    case NEXT_EXECUTION:
      return isSetNextExecution();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ScheduledQuery)
      return this.equals((ScheduledQuery)that);
    return false;
  }

  public boolean equals(ScheduledQuery that) {
    if (that == null)
      return false;

    boolean this_present_scheduleName = true && this.isSetScheduleName();
    boolean that_present_scheduleName = true && that.isSetScheduleName();
    if (this_present_scheduleName || that_present_scheduleName) {
      if (!(this_present_scheduleName && that_present_scheduleName))
        return false;
      if (!this.scheduleName.equals(that.scheduleName))
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

    boolean this_present_nextExecution = true && this.isSetNextExecution();
    boolean that_present_nextExecution = true && that.isSetNextExecution();
    if (this_present_nextExecution || that_present_nextExecution) {
      if (!(this_present_nextExecution && that_present_nextExecution))
        return false;
      if (this.nextExecution != that.nextExecution)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_scheduleName = true && (isSetScheduleName());
    list.add(present_scheduleName);
    if (present_scheduleName)
      list.add(scheduleName);

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

    boolean present_nextExecution = true && (isSetNextExecution());
    list.add(present_nextExecution);
    if (present_nextExecution)
      list.add(nextExecution);

    return list.hashCode();
  }

  @Override
  public int compareTo(ScheduledQuery other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetScheduleName()).compareTo(other.isSetScheduleName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetScheduleName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.scheduleName, other.scheduleName);
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
    lastComparison = Boolean.valueOf(isSetNextExecution()).compareTo(other.isSetNextExecution());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNextExecution()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nextExecution, other.nextExecution);
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
    StringBuilder sb = new StringBuilder("ScheduledQuery(");
    boolean first = true;

    sb.append("scheduleName:");
    if (this.scheduleName == null) {
      sb.append("null");
    } else {
      sb.append(this.scheduleName);
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
    if (isSetNextExecution()) {
      if (!first) sb.append(", ");
      sb.append("nextExecution:");
      sb.append(this.nextExecution);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetScheduleName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'scheduleName' is unset! Struct:" + toString());
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

  private static class ScheduledQueryStandardSchemeFactory implements SchemeFactory {
    public ScheduledQueryStandardScheme getScheme() {
      return new ScheduledQueryStandardScheme();
    }
  }

  private static class ScheduledQueryStandardScheme extends StandardScheme<ScheduledQuery> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ScheduledQuery struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SCHEDULE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.scheduleName = iprot.readString();
              struct.setScheduleNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.enabled = iprot.readBool();
              struct.setEnabledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CLUSTER_FUCK
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.clusterFuck = iprot.readString();
              struct.setClusterFuckIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SCHEDULE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.schedule = new Schedule();
              struct.schedule.read(iprot);
              struct.setScheduleIsSet(true);
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
          case 6: // QUERY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.query = iprot.readString();
              struct.setQueryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // NEXT_EXECUTION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.nextExecution = iprot.readI32();
              struct.setNextExecutionIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ScheduledQuery struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.scheduleName != null) {
        oprot.writeFieldBegin(SCHEDULE_NAME_FIELD_DESC);
        oprot.writeString(struct.scheduleName);
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
      if (struct.isSetNextExecution()) {
        oprot.writeFieldBegin(NEXT_EXECUTION_FIELD_DESC);
        oprot.writeI32(struct.nextExecution);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScheduledQueryTupleSchemeFactory implements SchemeFactory {
    public ScheduledQueryTupleScheme getScheme() {
      return new ScheduledQueryTupleScheme();
    }
  }

  private static class ScheduledQueryTupleScheme extends TupleScheme<ScheduledQuery> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ScheduledQuery struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.scheduleName);
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
      if (struct.isSetNextExecution()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
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
      if (struct.isSetNextExecution()) {
        oprot.writeI32(struct.nextExecution);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ScheduledQuery struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.scheduleName = iprot.readString();
      struct.setScheduleNameIsSet(true);
      BitSet incoming = iprot.readBitSet(6);
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
      if (incoming.get(5)) {
        struct.nextExecution = iprot.readI32();
        struct.setNextExecutionIsSet(true);
      }
    }
  }

}

