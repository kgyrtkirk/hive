/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class Materialization implements org.apache.thrift.TBase<Materialization, Materialization._Fields>, java.io.Serializable, Cloneable, Comparable<Materialization> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Materialization");

  private static final org.apache.thrift.protocol.TField SOURCE_TABLES_UPDATE_DELETE_MODIFIED_FIELD_DESC = new org.apache.thrift.protocol.TField("sourceTablesUpdateDeleteModified", org.apache.thrift.protocol.TType.BOOL, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MaterializationStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MaterializationTupleSchemeFactory();

  private boolean sourceTablesUpdateDeleteModified; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SOURCE_TABLES_UPDATE_DELETE_MODIFIED((short)1, "sourceTablesUpdateDeleteModified");

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
        case 1: // SOURCE_TABLES_UPDATE_DELETE_MODIFIED
          return SOURCE_TABLES_UPDATE_DELETE_MODIFIED;
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
  private static final int __SOURCETABLESUPDATEDELETEMODIFIED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SOURCE_TABLES_UPDATE_DELETE_MODIFIED, new org.apache.thrift.meta_data.FieldMetaData("sourceTablesUpdateDeleteModified", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Materialization.class, metaDataMap);
  }

  public Materialization() {
  }

  public Materialization(
    boolean sourceTablesUpdateDeleteModified)
  {
    this();
    this.sourceTablesUpdateDeleteModified = sourceTablesUpdateDeleteModified;
    setSourceTablesUpdateDeleteModifiedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Materialization(Materialization other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sourceTablesUpdateDeleteModified = other.sourceTablesUpdateDeleteModified;
  }

  public Materialization deepCopy() {
    return new Materialization(this);
  }

  @Override
  public void clear() {
    setSourceTablesUpdateDeleteModifiedIsSet(false);
    this.sourceTablesUpdateDeleteModified = false;
  }

  public boolean isSourceTablesUpdateDeleteModified() {
    return this.sourceTablesUpdateDeleteModified;
  }

  public void setSourceTablesUpdateDeleteModified(boolean sourceTablesUpdateDeleteModified) {
    this.sourceTablesUpdateDeleteModified = sourceTablesUpdateDeleteModified;
    setSourceTablesUpdateDeleteModifiedIsSet(true);
  }

  public void unsetSourceTablesUpdateDeleteModified() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SOURCETABLESUPDATEDELETEMODIFIED_ISSET_ID);
  }

  /** Returns true if field sourceTablesUpdateDeleteModified is set (has been assigned a value) and false otherwise */
  public boolean isSetSourceTablesUpdateDeleteModified() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SOURCETABLESUPDATEDELETEMODIFIED_ISSET_ID);
  }

  public void setSourceTablesUpdateDeleteModifiedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SOURCETABLESUPDATEDELETEMODIFIED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case SOURCE_TABLES_UPDATE_DELETE_MODIFIED:
      if (value == null) {
        unsetSourceTablesUpdateDeleteModified();
      } else {
        setSourceTablesUpdateDeleteModified((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SOURCE_TABLES_UPDATE_DELETE_MODIFIED:
      return isSourceTablesUpdateDeleteModified();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SOURCE_TABLES_UPDATE_DELETE_MODIFIED:
      return isSetSourceTablesUpdateDeleteModified();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Materialization)
      return this.equals((Materialization)that);
    return false;
  }

  public boolean equals(Materialization that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sourceTablesUpdateDeleteModified = true;
    boolean that_present_sourceTablesUpdateDeleteModified = true;
    if (this_present_sourceTablesUpdateDeleteModified || that_present_sourceTablesUpdateDeleteModified) {
      if (!(this_present_sourceTablesUpdateDeleteModified && that_present_sourceTablesUpdateDeleteModified))
        return false;
      if (this.sourceTablesUpdateDeleteModified != that.sourceTablesUpdateDeleteModified)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((sourceTablesUpdateDeleteModified) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(Materialization other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSourceTablesUpdateDeleteModified()).compareTo(other.isSetSourceTablesUpdateDeleteModified());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSourceTablesUpdateDeleteModified()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sourceTablesUpdateDeleteModified, other.sourceTablesUpdateDeleteModified);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Materialization(");
    boolean first = true;

    sb.append("sourceTablesUpdateDeleteModified:");
    sb.append(this.sourceTablesUpdateDeleteModified);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetSourceTablesUpdateDeleteModified()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'sourceTablesUpdateDeleteModified' is unset! Struct:" + toString());
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MaterializationStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MaterializationStandardScheme getScheme() {
      return new MaterializationStandardScheme();
    }
  }

  private static class MaterializationStandardScheme extends org.apache.thrift.scheme.StandardScheme<Materialization> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Materialization struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SOURCE_TABLES_UPDATE_DELETE_MODIFIED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.sourceTablesUpdateDeleteModified = iprot.readBool();
              struct.setSourceTablesUpdateDeleteModifiedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Materialization struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SOURCE_TABLES_UPDATE_DELETE_MODIFIED_FIELD_DESC);
      oprot.writeBool(struct.sourceTablesUpdateDeleteModified);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MaterializationTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MaterializationTupleScheme getScheme() {
      return new MaterializationTupleScheme();
    }
  }

  private static class MaterializationTupleScheme extends org.apache.thrift.scheme.TupleScheme<Materialization> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Materialization struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeBool(struct.sourceTablesUpdateDeleteModified);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Materialization struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.sourceTablesUpdateDeleteModified = iprot.readBool();
      struct.setSourceTablesUpdateDeleteModifiedIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

