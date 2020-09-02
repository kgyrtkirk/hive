/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hive.service.rpc.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.hive.common.classification.InterfaceAudience.Public @org.apache.hadoop.hive.common.classification.InterfaceStability.Stable public class TColumnValue extends org.apache.thrift.TUnion<TColumnValue, TColumnValue._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TColumnValue");
  private static final org.apache.thrift.protocol.TField BOOL_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("boolVal", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField BYTE_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("byteVal", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField I16_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("i16Val", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField I32_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("i32Val", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField I64_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("i64Val", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField DOUBLE_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("doubleVal", org.apache.thrift.protocol.TType.STRUCT, (short)6);
  private static final org.apache.thrift.protocol.TField STRING_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("stringVal", org.apache.thrift.protocol.TType.STRUCT, (short)7);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BOOL_VAL((short)1, "boolVal"),
    BYTE_VAL((short)2, "byteVal"),
    I16_VAL((short)3, "i16Val"),
    I32_VAL((short)4, "i32Val"),
    I64_VAL((short)5, "i64Val"),
    DOUBLE_VAL((short)6, "doubleVal"),
    STRING_VAL((short)7, "stringVal");

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
        case 1: // BOOL_VAL
          return BOOL_VAL;
        case 2: // BYTE_VAL
          return BYTE_VAL;
        case 3: // I16_VAL
          return I16_VAL;
        case 4: // I32_VAL
          return I32_VAL;
        case 5: // I64_VAL
          return I64_VAL;
        case 6: // DOUBLE_VAL
          return DOUBLE_VAL;
        case 7: // STRING_VAL
          return STRING_VAL;
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

  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BOOL_VAL, new org.apache.thrift.meta_data.FieldMetaData("boolVal", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TBoolValue.class)));
    tmpMap.put(_Fields.BYTE_VAL, new org.apache.thrift.meta_data.FieldMetaData("byteVal", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TByteValue.class)));
    tmpMap.put(_Fields.I16_VAL, new org.apache.thrift.meta_data.FieldMetaData("i16Val", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TI16Value.class)));
    tmpMap.put(_Fields.I32_VAL, new org.apache.thrift.meta_data.FieldMetaData("i32Val", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TI32Value.class)));
    tmpMap.put(_Fields.I64_VAL, new org.apache.thrift.meta_data.FieldMetaData("i64Val", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TI64Value.class)));
    tmpMap.put(_Fields.DOUBLE_VAL, new org.apache.thrift.meta_data.FieldMetaData("doubleVal", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TDoubleValue.class)));
    tmpMap.put(_Fields.STRING_VAL, new org.apache.thrift.meta_data.FieldMetaData("stringVal", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TStringValue.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TColumnValue.class, metaDataMap);
  }

  public TColumnValue() {
    super();
  }

  public TColumnValue(_Fields setField, java.lang.Object value) {
    super(setField, value);
  }

  public TColumnValue(TColumnValue other) {
    super(other);
  }
  public TColumnValue deepCopy() {
    return new TColumnValue(this);
  }

  public static TColumnValue boolVal(TBoolValue value) {
    TColumnValue x = new TColumnValue();
    x.setBoolVal(value);
    return x;
  }

  public static TColumnValue byteVal(TByteValue value) {
    TColumnValue x = new TColumnValue();
    x.setByteVal(value);
    return x;
  }

  public static TColumnValue i16Val(TI16Value value) {
    TColumnValue x = new TColumnValue();
    x.setI16Val(value);
    return x;
  }

  public static TColumnValue i32Val(TI32Value value) {
    TColumnValue x = new TColumnValue();
    x.setI32Val(value);
    return x;
  }

  public static TColumnValue i64Val(TI64Value value) {
    TColumnValue x = new TColumnValue();
    x.setI64Val(value);
    return x;
  }

  public static TColumnValue doubleVal(TDoubleValue value) {
    TColumnValue x = new TColumnValue();
    x.setDoubleVal(value);
    return x;
  }

  public static TColumnValue stringVal(TStringValue value) {
    TColumnValue x = new TColumnValue();
    x.setStringVal(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, java.lang.Object value) throws java.lang.ClassCastException {
    switch (setField) {
      case BOOL_VAL:
        if (value instanceof TBoolValue) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TBoolValue for field 'boolVal', but got " + value.getClass().getSimpleName());
      case BYTE_VAL:
        if (value instanceof TByteValue) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TByteValue for field 'byteVal', but got " + value.getClass().getSimpleName());
      case I16_VAL:
        if (value instanceof TI16Value) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TI16Value for field 'i16Val', but got " + value.getClass().getSimpleName());
      case I32_VAL:
        if (value instanceof TI32Value) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TI32Value for field 'i32Val', but got " + value.getClass().getSimpleName());
      case I64_VAL:
        if (value instanceof TI64Value) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TI64Value for field 'i64Val', but got " + value.getClass().getSimpleName());
      case DOUBLE_VAL:
        if (value instanceof TDoubleValue) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TDoubleValue for field 'doubleVal', but got " + value.getClass().getSimpleName());
      case STRING_VAL:
        if (value instanceof TStringValue) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type TStringValue for field 'stringVal', but got " + value.getClass().getSimpleName());
      default:
        throw new java.lang.IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected java.lang.Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case BOOL_VAL:
          if (field.type == BOOL_VAL_FIELD_DESC.type) {
            TBoolValue boolVal;
            boolVal = new TBoolValue();
            boolVal.read(iprot);
            return boolVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case BYTE_VAL:
          if (field.type == BYTE_VAL_FIELD_DESC.type) {
            TByteValue byteVal;
            byteVal = new TByteValue();
            byteVal.read(iprot);
            return byteVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case I16_VAL:
          if (field.type == I16_VAL_FIELD_DESC.type) {
            TI16Value i16Val;
            i16Val = new TI16Value();
            i16Val.read(iprot);
            return i16Val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case I32_VAL:
          if (field.type == I32_VAL_FIELD_DESC.type) {
            TI32Value i32Val;
            i32Val = new TI32Value();
            i32Val.read(iprot);
            return i32Val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case I64_VAL:
          if (field.type == I64_VAL_FIELD_DESC.type) {
            TI64Value i64Val;
            i64Val = new TI64Value();
            i64Val.read(iprot);
            return i64Val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DOUBLE_VAL:
          if (field.type == DOUBLE_VAL_FIELD_DESC.type) {
            TDoubleValue doubleVal;
            doubleVal = new TDoubleValue();
            doubleVal.read(iprot);
            return doubleVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case STRING_VAL:
          if (field.type == STRING_VAL_FIELD_DESC.type) {
            TStringValue stringVal;
            stringVal = new TStringValue();
            stringVal.read(iprot);
            return stringVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new java.lang.IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case BOOL_VAL:
        TBoolValue boolVal = (TBoolValue)value_;
        boolVal.write(oprot);
        return;
      case BYTE_VAL:
        TByteValue byteVal = (TByteValue)value_;
        byteVal.write(oprot);
        return;
      case I16_VAL:
        TI16Value i16Val = (TI16Value)value_;
        i16Val.write(oprot);
        return;
      case I32_VAL:
        TI32Value i32Val = (TI32Value)value_;
        i32Val.write(oprot);
        return;
      case I64_VAL:
        TI64Value i64Val = (TI64Value)value_;
        i64Val.write(oprot);
        return;
      case DOUBLE_VAL:
        TDoubleValue doubleVal = (TDoubleValue)value_;
        doubleVal.write(oprot);
        return;
      case STRING_VAL:
        TStringValue stringVal = (TStringValue)value_;
        stringVal.write(oprot);
        return;
      default:
        throw new java.lang.IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected java.lang.Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case BOOL_VAL:
          TBoolValue boolVal;
          boolVal = new TBoolValue();
          boolVal.read(iprot);
          return boolVal;
        case BYTE_VAL:
          TByteValue byteVal;
          byteVal = new TByteValue();
          byteVal.read(iprot);
          return byteVal;
        case I16_VAL:
          TI16Value i16Val;
          i16Val = new TI16Value();
          i16Val.read(iprot);
          return i16Val;
        case I32_VAL:
          TI32Value i32Val;
          i32Val = new TI32Value();
          i32Val.read(iprot);
          return i32Val;
        case I64_VAL:
          TI64Value i64Val;
          i64Val = new TI64Value();
          i64Val.read(iprot);
          return i64Val;
        case DOUBLE_VAL:
          TDoubleValue doubleVal;
          doubleVal = new TDoubleValue();
          doubleVal.read(iprot);
          return doubleVal;
        case STRING_VAL:
          TStringValue stringVal;
          stringVal = new TStringValue();
          stringVal.read(iprot);
          return stringVal;
        default:
          throw new java.lang.IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new org.apache.thrift.protocol.TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case BOOL_VAL:
        TBoolValue boolVal = (TBoolValue)value_;
        boolVal.write(oprot);
        return;
      case BYTE_VAL:
        TByteValue byteVal = (TByteValue)value_;
        byteVal.write(oprot);
        return;
      case I16_VAL:
        TI16Value i16Val = (TI16Value)value_;
        i16Val.write(oprot);
        return;
      case I32_VAL:
        TI32Value i32Val = (TI32Value)value_;
        i32Val.write(oprot);
        return;
      case I64_VAL:
        TI64Value i64Val = (TI64Value)value_;
        i64Val.write(oprot);
        return;
      case DOUBLE_VAL:
        TDoubleValue doubleVal = (TDoubleValue)value_;
        doubleVal.write(oprot);
        return;
      case STRING_VAL:
        TStringValue stringVal = (TStringValue)value_;
        stringVal.write(oprot);
        return;
      default:
        throw new java.lang.IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case BOOL_VAL:
        return BOOL_VAL_FIELD_DESC;
      case BYTE_VAL:
        return BYTE_VAL_FIELD_DESC;
      case I16_VAL:
        return I16_VAL_FIELD_DESC;
      case I32_VAL:
        return I32_VAL_FIELD_DESC;
      case I64_VAL:
        return I64_VAL_FIELD_DESC;
      case DOUBLE_VAL:
        return DOUBLE_VAL_FIELD_DESC;
      case STRING_VAL:
        return STRING_VAL_FIELD_DESC;
      default:
        throw new java.lang.IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public TBoolValue getBoolVal() {
    if (getSetField() == _Fields.BOOL_VAL) {
      return (TBoolValue)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'boolVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setBoolVal(TBoolValue value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.BOOL_VAL;
    value_ = value;
  }

  public TByteValue getByteVal() {
    if (getSetField() == _Fields.BYTE_VAL) {
      return (TByteValue)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'byteVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setByteVal(TByteValue value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.BYTE_VAL;
    value_ = value;
  }

  public TI16Value getI16Val() {
    if (getSetField() == _Fields.I16_VAL) {
      return (TI16Value)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'i16Val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setI16Val(TI16Value value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.I16_VAL;
    value_ = value;
  }

  public TI32Value getI32Val() {
    if (getSetField() == _Fields.I32_VAL) {
      return (TI32Value)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'i32Val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setI32Val(TI32Value value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.I32_VAL;
    value_ = value;
  }

  public TI64Value getI64Val() {
    if (getSetField() == _Fields.I64_VAL) {
      return (TI64Value)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'i64Val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setI64Val(TI64Value value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.I64_VAL;
    value_ = value;
  }

  public TDoubleValue getDoubleVal() {
    if (getSetField() == _Fields.DOUBLE_VAL) {
      return (TDoubleValue)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'doubleVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDoubleVal(TDoubleValue value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.DOUBLE_VAL;
    value_ = value;
  }

  public TStringValue getStringVal() {
    if (getSetField() == _Fields.STRING_VAL) {
      return (TStringValue)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'stringVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setStringVal(TStringValue value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.STRING_VAL;
    value_ = value;
  }

  public boolean isSetBoolVal() {
    return setField_ == _Fields.BOOL_VAL;
  }


  public boolean isSetByteVal() {
    return setField_ == _Fields.BYTE_VAL;
  }


  public boolean isSetI16Val() {
    return setField_ == _Fields.I16_VAL;
  }


  public boolean isSetI32Val() {
    return setField_ == _Fields.I32_VAL;
  }


  public boolean isSetI64Val() {
    return setField_ == _Fields.I64_VAL;
  }


  public boolean isSetDoubleVal() {
    return setField_ == _Fields.DOUBLE_VAL;
  }


  public boolean isSetStringVal() {
    return setField_ == _Fields.STRING_VAL;
  }


  public boolean equals(java.lang.Object other) {
    if (other instanceof TColumnValue) {
      return equals((TColumnValue)other);
    } else {
      return false;
    }
  }

  public boolean equals(TColumnValue other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(TColumnValue other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    java.util.List<java.lang.Object> list = new java.util.ArrayList<java.lang.Object>();
    list.add(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      list.add(setField.getThriftFieldId());
      java.lang.Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        list.add(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        list.add(value);
      }
    }
    return list.hashCode();
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


}
