/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class BooleanColumnStatsData implements org.apache.thrift.TBase<BooleanColumnStatsData, BooleanColumnStatsData._Fields>, java.io.Serializable, Cloneable, Comparable<BooleanColumnStatsData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BooleanColumnStatsData");

  private static final org.apache.thrift.protocol.TField NUM_TRUES_FIELD_DESC = new org.apache.thrift.protocol.TField("numTrues", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField NUM_FALSES_FIELD_DESC = new org.apache.thrift.protocol.TField("numFalses", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField NUM_NULLS_FIELD_DESC = new org.apache.thrift.protocol.TField("numNulls", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField BIT_VECTORS_FIELD_DESC = new org.apache.thrift.protocol.TField("bitVectors", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new BooleanColumnStatsDataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new BooleanColumnStatsDataTupleSchemeFactory();

  private long numTrues; // required
  private long numFalses; // required
  private long numNulls; // required
  private @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer bitVectors; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NUM_TRUES((short)1, "numTrues"),
    NUM_FALSES((short)2, "numFalses"),
    NUM_NULLS((short)3, "numNulls"),
    BIT_VECTORS((short)4, "bitVectors");

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
        case 1: // NUM_TRUES
          return NUM_TRUES;
        case 2: // NUM_FALSES
          return NUM_FALSES;
        case 3: // NUM_NULLS
          return NUM_NULLS;
        case 4: // BIT_VECTORS
          return BIT_VECTORS;
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
  private static final int __NUMTRUES_ISSET_ID = 0;
  private static final int __NUMFALSES_ISSET_ID = 1;
  private static final int __NUMNULLS_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.BIT_VECTORS};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NUM_TRUES, new org.apache.thrift.meta_data.FieldMetaData("numTrues", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.NUM_FALSES, new org.apache.thrift.meta_data.FieldMetaData("numFalses", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.NUM_NULLS, new org.apache.thrift.meta_data.FieldMetaData("numNulls", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.BIT_VECTORS, new org.apache.thrift.meta_data.FieldMetaData("bitVectors", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BooleanColumnStatsData.class, metaDataMap);
  }

  public BooleanColumnStatsData() {
  }

  public BooleanColumnStatsData(
    long numTrues,
    long numFalses,
    long numNulls)
  {
    this();
    this.numTrues = numTrues;
    setNumTruesIsSet(true);
    this.numFalses = numFalses;
    setNumFalsesIsSet(true);
    this.numNulls = numNulls;
    setNumNullsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BooleanColumnStatsData(BooleanColumnStatsData other) {
    __isset_bitfield = other.__isset_bitfield;
    this.numTrues = other.numTrues;
    this.numFalses = other.numFalses;
    this.numNulls = other.numNulls;
    if (other.isSetBitVectors()) {
      this.bitVectors = org.apache.thrift.TBaseHelper.copyBinary(other.bitVectors);
    }
  }

  public BooleanColumnStatsData deepCopy() {
    return new BooleanColumnStatsData(this);
  }

  @Override
  public void clear() {
    setNumTruesIsSet(false);
    this.numTrues = 0;
    setNumFalsesIsSet(false);
    this.numFalses = 0;
    setNumNullsIsSet(false);
    this.numNulls = 0;
    this.bitVectors = null;
  }

  public long getNumTrues() {
    return this.numTrues;
  }

  public void setNumTrues(long numTrues) {
    this.numTrues = numTrues;
    setNumTruesIsSet(true);
  }

  public void unsetNumTrues() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NUMTRUES_ISSET_ID);
  }

  /** Returns true if field numTrues is set (has been assigned a value) and false otherwise */
  public boolean isSetNumTrues() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NUMTRUES_ISSET_ID);
  }

  public void setNumTruesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NUMTRUES_ISSET_ID, value);
  }

  public long getNumFalses() {
    return this.numFalses;
  }

  public void setNumFalses(long numFalses) {
    this.numFalses = numFalses;
    setNumFalsesIsSet(true);
  }

  public void unsetNumFalses() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NUMFALSES_ISSET_ID);
  }

  /** Returns true if field numFalses is set (has been assigned a value) and false otherwise */
  public boolean isSetNumFalses() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NUMFALSES_ISSET_ID);
  }

  public void setNumFalsesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NUMFALSES_ISSET_ID, value);
  }

  public long getNumNulls() {
    return this.numNulls;
  }

  public void setNumNulls(long numNulls) {
    this.numNulls = numNulls;
    setNumNullsIsSet(true);
  }

  public void unsetNumNulls() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NUMNULLS_ISSET_ID);
  }

  /** Returns true if field numNulls is set (has been assigned a value) and false otherwise */
  public boolean isSetNumNulls() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NUMNULLS_ISSET_ID);
  }

  public void setNumNullsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NUMNULLS_ISSET_ID, value);
  }

  public byte[] getBitVectors() {
    setBitVectors(org.apache.thrift.TBaseHelper.rightSize(bitVectors));
    return bitVectors == null ? null : bitVectors.array();
  }

  public java.nio.ByteBuffer bufferForBitVectors() {
    return org.apache.thrift.TBaseHelper.copyBinary(bitVectors);
  }

  public void setBitVectors(byte[] bitVectors) {
    this.bitVectors = bitVectors == null ? (java.nio.ByteBuffer)null   : java.nio.ByteBuffer.wrap(bitVectors.clone());
  }

  public void setBitVectors(@org.apache.thrift.annotation.Nullable java.nio.ByteBuffer bitVectors) {
    this.bitVectors = org.apache.thrift.TBaseHelper.copyBinary(bitVectors);
  }

  public void unsetBitVectors() {
    this.bitVectors = null;
  }

  /** Returns true if field bitVectors is set (has been assigned a value) and false otherwise */
  public boolean isSetBitVectors() {
    return this.bitVectors != null;
  }

  public void setBitVectorsIsSet(boolean value) {
    if (!value) {
      this.bitVectors = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case NUM_TRUES:
      if (value == null) {
        unsetNumTrues();
      } else {
        setNumTrues((java.lang.Long)value);
      }
      break;

    case NUM_FALSES:
      if (value == null) {
        unsetNumFalses();
      } else {
        setNumFalses((java.lang.Long)value);
      }
      break;

    case NUM_NULLS:
      if (value == null) {
        unsetNumNulls();
      } else {
        setNumNulls((java.lang.Long)value);
      }
      break;

    case BIT_VECTORS:
      if (value == null) {
        unsetBitVectors();
      } else {
        if (value instanceof byte[]) {
          setBitVectors((byte[])value);
        } else {
          setBitVectors((java.nio.ByteBuffer)value);
        }
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NUM_TRUES:
      return getNumTrues();

    case NUM_FALSES:
      return getNumFalses();

    case NUM_NULLS:
      return getNumNulls();

    case BIT_VECTORS:
      return getBitVectors();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NUM_TRUES:
      return isSetNumTrues();
    case NUM_FALSES:
      return isSetNumFalses();
    case NUM_NULLS:
      return isSetNumNulls();
    case BIT_VECTORS:
      return isSetBitVectors();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof BooleanColumnStatsData)
      return this.equals((BooleanColumnStatsData)that);
    return false;
  }

  public boolean equals(BooleanColumnStatsData that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_numTrues = true;
    boolean that_present_numTrues = true;
    if (this_present_numTrues || that_present_numTrues) {
      if (!(this_present_numTrues && that_present_numTrues))
        return false;
      if (this.numTrues != that.numTrues)
        return false;
    }

    boolean this_present_numFalses = true;
    boolean that_present_numFalses = true;
    if (this_present_numFalses || that_present_numFalses) {
      if (!(this_present_numFalses && that_present_numFalses))
        return false;
      if (this.numFalses != that.numFalses)
        return false;
    }

    boolean this_present_numNulls = true;
    boolean that_present_numNulls = true;
    if (this_present_numNulls || that_present_numNulls) {
      if (!(this_present_numNulls && that_present_numNulls))
        return false;
      if (this.numNulls != that.numNulls)
        return false;
    }

    boolean this_present_bitVectors = true && this.isSetBitVectors();
    boolean that_present_bitVectors = true && that.isSetBitVectors();
    if (this_present_bitVectors || that_present_bitVectors) {
      if (!(this_present_bitVectors && that_present_bitVectors))
        return false;
      if (!this.bitVectors.equals(that.bitVectors))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(numTrues);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(numFalses);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(numNulls);

    hashCode = hashCode * 8191 + ((isSetBitVectors()) ? 131071 : 524287);
    if (isSetBitVectors())
      hashCode = hashCode * 8191 + bitVectors.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(BooleanColumnStatsData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetNumTrues(), other.isSetNumTrues());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumTrues()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.numTrues, other.numTrues);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetNumFalses(), other.isSetNumFalses());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumFalses()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.numFalses, other.numFalses);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetNumNulls(), other.isSetNumNulls());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumNulls()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.numNulls, other.numNulls);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetBitVectors(), other.isSetBitVectors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBitVectors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bitVectors, other.bitVectors);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("BooleanColumnStatsData(");
    boolean first = true;

    sb.append("numTrues:");
    sb.append(this.numTrues);
    first = false;
    if (!first) sb.append(", ");
    sb.append("numFalses:");
    sb.append(this.numFalses);
    first = false;
    if (!first) sb.append(", ");
    sb.append("numNulls:");
    sb.append(this.numNulls);
    first = false;
    if (isSetBitVectors()) {
      if (!first) sb.append(", ");
      sb.append("bitVectors:");
      if (this.bitVectors == null) {
        sb.append("null");
      } else {
        org.apache.thrift.TBaseHelper.toString(this.bitVectors, sb);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetNumTrues()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'numTrues' is unset! Struct:" + toString());
    }

    if (!isSetNumFalses()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'numFalses' is unset! Struct:" + toString());
    }

    if (!isSetNumNulls()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'numNulls' is unset! Struct:" + toString());
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

  private static class BooleanColumnStatsDataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BooleanColumnStatsDataStandardScheme getScheme() {
      return new BooleanColumnStatsDataStandardScheme();
    }
  }

  private static class BooleanColumnStatsDataStandardScheme extends org.apache.thrift.scheme.StandardScheme<BooleanColumnStatsData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BooleanColumnStatsData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NUM_TRUES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.numTrues = iprot.readI64();
              struct.setNumTruesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NUM_FALSES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.numFalses = iprot.readI64();
              struct.setNumFalsesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NUM_NULLS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.numNulls = iprot.readI64();
              struct.setNumNullsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // BIT_VECTORS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.bitVectors = iprot.readBinary();
              struct.setBitVectorsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BooleanColumnStatsData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(NUM_TRUES_FIELD_DESC);
      oprot.writeI64(struct.numTrues);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NUM_FALSES_FIELD_DESC);
      oprot.writeI64(struct.numFalses);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NUM_NULLS_FIELD_DESC);
      oprot.writeI64(struct.numNulls);
      oprot.writeFieldEnd();
      if (struct.bitVectors != null) {
        if (struct.isSetBitVectors()) {
          oprot.writeFieldBegin(BIT_VECTORS_FIELD_DESC);
          oprot.writeBinary(struct.bitVectors);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BooleanColumnStatsDataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BooleanColumnStatsDataTupleScheme getScheme() {
      return new BooleanColumnStatsDataTupleScheme();
    }
  }

  private static class BooleanColumnStatsDataTupleScheme extends org.apache.thrift.scheme.TupleScheme<BooleanColumnStatsData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BooleanColumnStatsData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.numTrues);
      oprot.writeI64(struct.numFalses);
      oprot.writeI64(struct.numNulls);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetBitVectors()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetBitVectors()) {
        oprot.writeBinary(struct.bitVectors);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BooleanColumnStatsData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.numTrues = iprot.readI64();
      struct.setNumTruesIsSet(true);
      struct.numFalses = iprot.readI64();
      struct.setNumFalsesIsSet(true);
      struct.numNulls = iprot.readI64();
      struct.setNumNullsIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.bitVectors = iprot.readBinary();
        struct.setBitVectorsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

