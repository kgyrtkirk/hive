/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class ScheduledQueryPollRequest implements org.apache.thrift.TBase<ScheduledQueryPollRequest, ScheduledQueryPollRequest._Fields>, java.io.Serializable, Cloneable, Comparable<ScheduledQueryPollRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ScheduledQueryPollRequest");

  private static final org.apache.thrift.protocol.TField CLUSTER_NAMESPACE_FIELD_DESC = new org.apache.thrift.protocol.TField("clusterNamespace", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ScheduledQueryPollRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ScheduledQueryPollRequestTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable java.lang.String clusterNamespace; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CLUSTER_NAMESPACE((short)1, "clusterNamespace");

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
        case 1: // CLUSTER_NAMESPACE
          return CLUSTER_NAMESPACE;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CLUSTER_NAMESPACE, new org.apache.thrift.meta_data.FieldMetaData("clusterNamespace", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ScheduledQueryPollRequest.class, metaDataMap);
  }

  public ScheduledQueryPollRequest() {
  }

  public ScheduledQueryPollRequest(
    java.lang.String clusterNamespace)
  {
    this();
    this.clusterNamespace = clusterNamespace;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScheduledQueryPollRequest(ScheduledQueryPollRequest other) {
    if (other.isSetClusterNamespace()) {
      this.clusterNamespace = other.clusterNamespace;
    }
  }

  public ScheduledQueryPollRequest deepCopy() {
    return new ScheduledQueryPollRequest(this);
  }

  @Override
  public void clear() {
    this.clusterNamespace = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getClusterNamespace() {
    return this.clusterNamespace;
  }

  public void setClusterNamespace(@org.apache.thrift.annotation.Nullable java.lang.String clusterNamespace) {
    this.clusterNamespace = clusterNamespace;
  }

  public void unsetClusterNamespace() {
    this.clusterNamespace = null;
  }

  /** Returns true if field clusterNamespace is set (has been assigned a value) and false otherwise */
  public boolean isSetClusterNamespace() {
    return this.clusterNamespace != null;
  }

  public void setClusterNamespaceIsSet(boolean value) {
    if (!value) {
      this.clusterNamespace = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case CLUSTER_NAMESPACE:
      if (value == null) {
        unsetClusterNamespace();
      } else {
        setClusterNamespace((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CLUSTER_NAMESPACE:
      return getClusterNamespace();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CLUSTER_NAMESPACE:
      return isSetClusterNamespace();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ScheduledQueryPollRequest)
      return this.equals((ScheduledQueryPollRequest)that);
    return false;
  }

  public boolean equals(ScheduledQueryPollRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_clusterNamespace = true && this.isSetClusterNamespace();
    boolean that_present_clusterNamespace = true && that.isSetClusterNamespace();
    if (this_present_clusterNamespace || that_present_clusterNamespace) {
      if (!(this_present_clusterNamespace && that_present_clusterNamespace))
        return false;
      if (!this.clusterNamespace.equals(that.clusterNamespace))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetClusterNamespace()) ? 131071 : 524287);
    if (isSetClusterNamespace())
      hashCode = hashCode * 8191 + clusterNamespace.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ScheduledQueryPollRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetClusterNamespace()).compareTo(other.isSetClusterNamespace());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClusterNamespace()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clusterNamespace, other.clusterNamespace);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ScheduledQueryPollRequest(");
    boolean first = true;

    sb.append("clusterNamespace:");
    if (this.clusterNamespace == null) {
      sb.append("null");
    } else {
      sb.append(this.clusterNamespace);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetClusterNamespace()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'clusterNamespace' is unset! Struct:" + toString());
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ScheduledQueryPollRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ScheduledQueryPollRequestStandardScheme getScheme() {
      return new ScheduledQueryPollRequestStandardScheme();
    }
  }

  private static class ScheduledQueryPollRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<ScheduledQueryPollRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ScheduledQueryPollRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CLUSTER_NAMESPACE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.clusterNamespace = iprot.readString();
              struct.setClusterNamespaceIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ScheduledQueryPollRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.clusterNamespace != null) {
        oprot.writeFieldBegin(CLUSTER_NAMESPACE_FIELD_DESC);
        oprot.writeString(struct.clusterNamespace);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScheduledQueryPollRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ScheduledQueryPollRequestTupleScheme getScheme() {
      return new ScheduledQueryPollRequestTupleScheme();
    }
  }

  private static class ScheduledQueryPollRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<ScheduledQueryPollRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ScheduledQueryPollRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.clusterNamespace);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ScheduledQueryPollRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.clusterNamespace = iprot.readString();
      struct.setClusterNamespaceIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

