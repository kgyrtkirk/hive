/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.hadoop.hive.metastore.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)")
@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public class FireEventRequestData extends org.apache.thrift.TUnion<FireEventRequestData, FireEventRequestData._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FireEventRequestData");
  private static final org.apache.thrift.protocol.TField INSERT_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("insertData", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField INSERT_DATAS_FIELD_DESC = new org.apache.thrift.protocol.TField("insertDatas", org.apache.thrift.protocol.TType.LIST, (short)2);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INSERT_DATA((short)1, "insertData"),
    INSERT_DATAS((short)2, "insertDatas");

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
        case 1: // INSERT_DATA
          return INSERT_DATA;
        case 2: // INSERT_DATAS
          return INSERT_DATAS;
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
    tmpMap.put(_Fields.INSERT_DATA, new org.apache.thrift.meta_data.FieldMetaData("insertData", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, InsertEventRequestData.class)));
    tmpMap.put(_Fields.INSERT_DATAS, new org.apache.thrift.meta_data.FieldMetaData("insertDatas", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, InsertEventRequestData.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FireEventRequestData.class, metaDataMap);
  }

  public FireEventRequestData() {
    super();
  }

  public FireEventRequestData(_Fields setField, java.lang.Object value) {
    super(setField, value);
  }

  public FireEventRequestData(FireEventRequestData other) {
    super(other);
  }
  public FireEventRequestData deepCopy() {
    return new FireEventRequestData(this);
  }

  public static FireEventRequestData insertData(InsertEventRequestData value) {
    FireEventRequestData x = new FireEventRequestData();
    x.setInsertData(value);
    return x;
  }

  public static FireEventRequestData insertDatas(java.util.List<InsertEventRequestData> value) {
    FireEventRequestData x = new FireEventRequestData();
    x.setInsertDatas(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, java.lang.Object value) throws java.lang.ClassCastException {
    switch (setField) {
      case INSERT_DATA:
        if (value instanceof InsertEventRequestData) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type InsertEventRequestData for field 'insertData', but got " + value.getClass().getSimpleName());
      case INSERT_DATAS:
        if (value instanceof java.util.List) {
          break;
        }
        throw new java.lang.ClassCastException("Was expecting value of type java.util.List<InsertEventRequestData> for field 'insertDatas', but got " + value.getClass().getSimpleName());
      default:
        throw new java.lang.IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected java.lang.Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case INSERT_DATA:
          if (field.type == INSERT_DATA_FIELD_DESC.type) {
            InsertEventRequestData insertData;
            insertData = new InsertEventRequestData();
            insertData.read(iprot);
            return insertData;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case INSERT_DATAS:
          if (field.type == INSERT_DATAS_FIELD_DESC.type) {
            java.util.List<InsertEventRequestData> insertDatas;
            {
              org.apache.thrift.protocol.TList _list910 = iprot.readListBegin();
              insertDatas = new java.util.ArrayList<InsertEventRequestData>(_list910.size);
              @org.apache.thrift.annotation.Nullable InsertEventRequestData _elem911;
              for (int _i912 = 0; _i912 < _list910.size; ++_i912)
              {
                _elem911 = new InsertEventRequestData();
                _elem911.read(iprot);
                insertDatas.add(_elem911);
              }
              iprot.readListEnd();
            }
            return insertDatas;
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
      case INSERT_DATA:
        InsertEventRequestData insertData = (InsertEventRequestData)value_;
        insertData.write(oprot);
        return;
      case INSERT_DATAS:
        java.util.List<InsertEventRequestData> insertDatas = (java.util.List<InsertEventRequestData>)value_;
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, insertDatas.size()));
          for (InsertEventRequestData _iter913 : insertDatas)
          {
            _iter913.write(oprot);
          }
          oprot.writeListEnd();
        }
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
        case INSERT_DATA:
          InsertEventRequestData insertData;
          insertData = new InsertEventRequestData();
          insertData.read(iprot);
          return insertData;
        case INSERT_DATAS:
          java.util.List<InsertEventRequestData> insertDatas;
          {
            org.apache.thrift.protocol.TList _list914 = iprot.readListBegin();
            insertDatas = new java.util.ArrayList<InsertEventRequestData>(_list914.size);
            @org.apache.thrift.annotation.Nullable InsertEventRequestData _elem915;
            for (int _i916 = 0; _i916 < _list914.size; ++_i916)
            {
              _elem915 = new InsertEventRequestData();
              _elem915.read(iprot);
              insertDatas.add(_elem915);
            }
            iprot.readListEnd();
          }
          return insertDatas;
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
      case INSERT_DATA:
        InsertEventRequestData insertData = (InsertEventRequestData)value_;
        insertData.write(oprot);
        return;
      case INSERT_DATAS:
        java.util.List<InsertEventRequestData> insertDatas = (java.util.List<InsertEventRequestData>)value_;
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, insertDatas.size()));
          for (InsertEventRequestData _iter917 : insertDatas)
          {
            _iter917.write(oprot);
          }
          oprot.writeListEnd();
        }
        return;
      default:
        throw new java.lang.IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case INSERT_DATA:
        return INSERT_DATA_FIELD_DESC;
      case INSERT_DATAS:
        return INSERT_DATAS_FIELD_DESC;
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


  public InsertEventRequestData getInsertData() {
    if (getSetField() == _Fields.INSERT_DATA) {
      return (InsertEventRequestData)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'insertData' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setInsertData(InsertEventRequestData value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.INSERT_DATA;
    value_ = value;
  }

  public java.util.List<InsertEventRequestData> getInsertDatas() {
    if (getSetField() == _Fields.INSERT_DATAS) {
      return (java.util.List<InsertEventRequestData>)getFieldValue();
    } else {
      throw new java.lang.RuntimeException("Cannot get field 'insertDatas' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setInsertDatas(java.util.List<InsertEventRequestData> value) {
    if (value == null) throw new java.lang.NullPointerException();
    setField_ = _Fields.INSERT_DATAS;
    value_ = value;
  }

  public boolean isSetInsertData() {
    return setField_ == _Fields.INSERT_DATA;
  }


  public boolean isSetInsertDatas() {
    return setField_ == _Fields.INSERT_DATAS;
  }


  public boolean equals(java.lang.Object other) {
    if (other instanceof FireEventRequestData) {
      return equals((FireEventRequestData)other);
    } else {
      return false;
    }
  }

  public boolean equals(FireEventRequestData other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(FireEventRequestData other) {
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
