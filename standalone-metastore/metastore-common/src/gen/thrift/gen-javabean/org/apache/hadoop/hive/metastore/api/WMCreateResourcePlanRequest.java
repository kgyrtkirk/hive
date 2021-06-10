/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class WMCreateResourcePlanRequest implements org.apache.thrift.TBase<WMCreateResourcePlanRequest, WMCreateResourcePlanRequest._Fields>, java.io.Serializable, Cloneable, Comparable<WMCreateResourcePlanRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WMCreateResourcePlanRequest");

  private static final org.apache.thrift.protocol.TField RESOURCE_PLAN_FIELD_DESC = new org.apache.thrift.protocol.TField("resourcePlan", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField COPY_FROM_FIELD_DESC = new org.apache.thrift.protocol.TField("copyFrom", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new WMCreateResourcePlanRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new WMCreateResourcePlanRequestTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable WMResourcePlan resourcePlan; // optional
  private @org.apache.thrift.annotation.Nullable java.lang.String copyFrom; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESOURCE_PLAN((short)1, "resourcePlan"),
    COPY_FROM((short)2, "copyFrom");

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
        case 1: // RESOURCE_PLAN
          return RESOURCE_PLAN;
        case 2: // COPY_FROM
          return COPY_FROM;
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
  private static final _Fields optionals[] = {_Fields.RESOURCE_PLAN,_Fields.COPY_FROM};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESOURCE_PLAN, new org.apache.thrift.meta_data.FieldMetaData("resourcePlan", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, WMResourcePlan.class)));
    tmpMap.put(_Fields.COPY_FROM, new org.apache.thrift.meta_data.FieldMetaData("copyFrom", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WMCreateResourcePlanRequest.class, metaDataMap);
  }

  public WMCreateResourcePlanRequest() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WMCreateResourcePlanRequest(WMCreateResourcePlanRequest other) {
    if (other.isSetResourcePlan()) {
      this.resourcePlan = new WMResourcePlan(other.resourcePlan);
    }
    if (other.isSetCopyFrom()) {
      this.copyFrom = other.copyFrom;
    }
  }

  public WMCreateResourcePlanRequest deepCopy() {
    return new WMCreateResourcePlanRequest(this);
  }

  @Override
  public void clear() {
    this.resourcePlan = null;
    this.copyFrom = null;
  }

  @org.apache.thrift.annotation.Nullable
  public WMResourcePlan getResourcePlan() {
    return this.resourcePlan;
  }

  public void setResourcePlan(@org.apache.thrift.annotation.Nullable WMResourcePlan resourcePlan) {
    this.resourcePlan = resourcePlan;
  }

  public void unsetResourcePlan() {
    this.resourcePlan = null;
  }

  /** Returns true if field resourcePlan is set (has been assigned a value) and false otherwise */
  public boolean isSetResourcePlan() {
    return this.resourcePlan != null;
  }

  public void setResourcePlanIsSet(boolean value) {
    if (!value) {
      this.resourcePlan = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getCopyFrom() {
    return this.copyFrom;
  }

  public void setCopyFrom(@org.apache.thrift.annotation.Nullable java.lang.String copyFrom) {
    this.copyFrom = copyFrom;
  }

  public void unsetCopyFrom() {
    this.copyFrom = null;
  }

  /** Returns true if field copyFrom is set (has been assigned a value) and false otherwise */
  public boolean isSetCopyFrom() {
    return this.copyFrom != null;
  }

  public void setCopyFromIsSet(boolean value) {
    if (!value) {
      this.copyFrom = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case RESOURCE_PLAN:
      if (value == null) {
        unsetResourcePlan();
      } else {
        setResourcePlan((WMResourcePlan)value);
      }
      break;

    case COPY_FROM:
      if (value == null) {
        unsetCopyFrom();
      } else {
        setCopyFrom((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case RESOURCE_PLAN:
      return getResourcePlan();

    case COPY_FROM:
      return getCopyFrom();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case RESOURCE_PLAN:
      return isSetResourcePlan();
    case COPY_FROM:
      return isSetCopyFrom();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof WMCreateResourcePlanRequest)
      return this.equals((WMCreateResourcePlanRequest)that);
    return false;
  }

  public boolean equals(WMCreateResourcePlanRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_resourcePlan = true && this.isSetResourcePlan();
    boolean that_present_resourcePlan = true && that.isSetResourcePlan();
    if (this_present_resourcePlan || that_present_resourcePlan) {
      if (!(this_present_resourcePlan && that_present_resourcePlan))
        return false;
      if (!this.resourcePlan.equals(that.resourcePlan))
        return false;
    }

    boolean this_present_copyFrom = true && this.isSetCopyFrom();
    boolean that_present_copyFrom = true && that.isSetCopyFrom();
    if (this_present_copyFrom || that_present_copyFrom) {
      if (!(this_present_copyFrom && that_present_copyFrom))
        return false;
      if (!this.copyFrom.equals(that.copyFrom))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetResourcePlan()) ? 131071 : 524287);
    if (isSetResourcePlan())
      hashCode = hashCode * 8191 + resourcePlan.hashCode();

    hashCode = hashCode * 8191 + ((isSetCopyFrom()) ? 131071 : 524287);
    if (isSetCopyFrom())
      hashCode = hashCode * 8191 + copyFrom.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(WMCreateResourcePlanRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetResourcePlan(), other.isSetResourcePlan());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourcePlan()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourcePlan, other.resourcePlan);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetCopyFrom(), other.isSetCopyFrom());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCopyFrom()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.copyFrom, other.copyFrom);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("WMCreateResourcePlanRequest(");
    boolean first = true;

    if (isSetResourcePlan()) {
      sb.append("resourcePlan:");
      if (this.resourcePlan == null) {
        sb.append("null");
      } else {
        sb.append(this.resourcePlan);
      }
      first = false;
    }
    if (isSetCopyFrom()) {
      if (!first) sb.append(", ");
      sb.append("copyFrom:");
      if (this.copyFrom == null) {
        sb.append("null");
      } else {
        sb.append(this.copyFrom);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (resourcePlan != null) {
      resourcePlan.validate();
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class WMCreateResourcePlanRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public WMCreateResourcePlanRequestStandardScheme getScheme() {
      return new WMCreateResourcePlanRequestStandardScheme();
    }
  }

  private static class WMCreateResourcePlanRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<WMCreateResourcePlanRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WMCreateResourcePlanRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESOURCE_PLAN
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.resourcePlan = new WMResourcePlan();
              struct.resourcePlan.read(iprot);
              struct.setResourcePlanIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COPY_FROM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.copyFrom = iprot.readString();
              struct.setCopyFromIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, WMCreateResourcePlanRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.resourcePlan != null) {
        if (struct.isSetResourcePlan()) {
          oprot.writeFieldBegin(RESOURCE_PLAN_FIELD_DESC);
          struct.resourcePlan.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.copyFrom != null) {
        if (struct.isSetCopyFrom()) {
          oprot.writeFieldBegin(COPY_FROM_FIELD_DESC);
          oprot.writeString(struct.copyFrom);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WMCreateResourcePlanRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public WMCreateResourcePlanRequestTupleScheme getScheme() {
      return new WMCreateResourcePlanRequestTupleScheme();
    }
  }

  private static class WMCreateResourcePlanRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<WMCreateResourcePlanRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WMCreateResourcePlanRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetResourcePlan()) {
        optionals.set(0);
      }
      if (struct.isSetCopyFrom()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetResourcePlan()) {
        struct.resourcePlan.write(oprot);
      }
      if (struct.isSetCopyFrom()) {
        oprot.writeString(struct.copyFrom);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WMCreateResourcePlanRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.resourcePlan = new WMResourcePlan();
        struct.resourcePlan.read(iprot);
        struct.setResourcePlanIsSet(true);
      }
      if (incoming.get(1)) {
        struct.copyFrom = iprot.readString();
        struct.setCopyFromIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

