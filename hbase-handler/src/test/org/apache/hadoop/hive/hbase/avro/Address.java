/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.apache.hadoop.hive.hbase.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Address\",\"namespace\":\"org.apache.hadoop.hive.hbase.avro\",\"fields\":[{\"name\":\"address1\",\"type\":\"string\"},{\"name\":\"address2\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"zipcode\",\"type\":\"long\"},{\"name\":\"county\",\"type\":[{\"type\":\"record\",\"name\":\"HomePhone\",\"fields\":[{\"name\":\"areaCode\",\"type\":\"long\"},{\"name\":\"number\",\"type\":\"long\"}]},{\"type\":\"record\",\"name\":\"OfficePhone\",\"fields\":[{\"name\":\"areaCode\",\"type\":\"long\"},{\"name\":\"number\",\"type\":\"long\"}]},\"string\",\"null\"]},{\"name\":\"aliases\",\"type\":[{\"type\":\"array\",\"items\":\"string\"},\"null\"]},{\"name\":\"metadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"string\"}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence address1;
  @Deprecated public java.lang.CharSequence address2;
  @Deprecated public java.lang.CharSequence city;
  @Deprecated public long zipcode;
  @Deprecated public java.lang.Object county;
  @Deprecated public java.util.List<java.lang.CharSequence> aliases;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Address() {}

  /**
   * All-args constructor.
   */
  public Address(java.lang.CharSequence address1, java.lang.CharSequence address2, java.lang.CharSequence city, java.lang.Long zipcode, java.lang.Object county, java.util.List<java.lang.CharSequence> aliases, java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata) {
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.zipcode = zipcode;
    this.county = county;
    this.aliases = aliases;
    this.metadata = metadata;
  }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return address1;
    case 1: return address2;
    case 2: return city;
    case 3: return zipcode;
    case 4: return county;
    case 5: return aliases;
    case 6: return metadata;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: address1 = (java.lang.CharSequence)value$; break;
    case 1: address2 = (java.lang.CharSequence)value$; break;
    case 2: city = (java.lang.CharSequence)value$; break;
    case 3: zipcode = (java.lang.Long)value$; break;
    case 4: county = value$; break;
    case 5: aliases = (java.util.List<java.lang.CharSequence>)value$; break;
    case 6: metadata = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'address1' field.
   */
  public java.lang.CharSequence getAddress1() {
    return address1;
  }

  /**
   * Sets the value of the 'address1' field.
   * @param value the value to set.
   */
  public void setAddress1(java.lang.CharSequence value) {
    this.address1 = value;
  }

  /**
   * Gets the value of the 'address2' field.
   */
  public java.lang.CharSequence getAddress2() {
    return address2;
  }

  /**
   * Sets the value of the 'address2' field.
   * @param value the value to set.
   */
  public void setAddress2(java.lang.CharSequence value) {
    this.address2 = value;
  }

  /**
   * Gets the value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }

  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'zipcode' field.
   */
  public java.lang.Long getZipcode() {
    return zipcode;
  }

  /**
   * Sets the value of the 'zipcode' field.
   * @param value the value to set.
   */
  public void setZipcode(java.lang.Long value) {
    this.zipcode = value;
  }

  /**
   * Gets the value of the 'county' field.
   */
  public java.lang.Object getCounty() {
    return county;
  }

  /**
   * Sets the value of the 'county' field.
   * @param value the value to set.
   */
  public void setCounty(java.lang.Object value) {
    this.county = value;
  }

  /**
   * Gets the value of the 'aliases' field.
   */
  public java.util.List<java.lang.CharSequence> getAliases() {
    return aliases;
  }

  /**
   * Sets the value of the 'aliases' field.
   * @param value the value to set.
   */
  public void setAliases(java.util.List<java.lang.CharSequence> value) {
    this.aliases = value;
  }

  /**
   * Gets the value of the 'metadata' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMetadata() {
    return metadata;
  }

  /**
   * Sets the value of the 'metadata' field.
   * @param value the value to set.
   */
  public void setMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.metadata = value;
  }

  /** Creates a new Address RecordBuilder */
  public static org.apache.hadoop.hive.hbase.avro.Address.Builder newBuilder() {
    return new org.apache.hadoop.hive.hbase.avro.Address.Builder();
  }
  
  /** Creates a new Address RecordBuilder by copying an existing Builder */
  public static org.apache.hadoop.hive.hbase.avro.Address.Builder newBuilder(org.apache.hadoop.hive.hbase.avro.Address.Builder other) {
    return new org.apache.hadoop.hive.hbase.avro.Address.Builder(other);
  }
  
  /** Creates a new Address RecordBuilder by copying an existing Address instance */
  public static org.apache.hadoop.hive.hbase.avro.Address.Builder newBuilder(org.apache.hadoop.hive.hbase.avro.Address other) {
    return new org.apache.hadoop.hive.hbase.avro.Address.Builder(other);
  }
  
  /**
   * RecordBuilder for Address instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Address>
    implements org.apache.avro.data.RecordBuilder<Address> {

    private java.lang.CharSequence address1;
    private java.lang.CharSequence address2;
    private java.lang.CharSequence city;
    private long zipcode;
    private java.lang.Object county;
    private java.util.List<java.lang.CharSequence> aliases;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata;

    /** Creates a new Builder */
    private Builder() {
      super(org.apache.hadoop.hive.hbase.avro.Address.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.apache.hadoop.hive.hbase.avro.Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.address1)) {
        this.address1 = data().deepCopy(fields()[0].schema(), other.address1);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address2)) {
        this.address2 = data().deepCopy(fields()[1].schema(), other.address2);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.zipcode)) {
        this.zipcode = data().deepCopy(fields()[3].schema(), other.zipcode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.county)) {
        this.county = data().deepCopy(fields()[4].schema(), other.county);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.aliases)) {
        this.aliases = data().deepCopy(fields()[5].schema(), other.aliases);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.metadata)) {
        this.metadata = data().deepCopy(fields()[6].schema(), other.metadata);
        fieldSetFlags()[6] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Address instance */
    private Builder(org.apache.hadoop.hive.hbase.avro.Address other) {
            super(org.apache.hadoop.hive.hbase.avro.Address.SCHEMA$);
      if (isValidValue(fields()[0], other.address1)) {
        this.address1 = data().deepCopy(fields()[0].schema(), other.address1);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address2)) {
        this.address2 = data().deepCopy(fields()[1].schema(), other.address2);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.city)) {
        this.city = data().deepCopy(fields()[2].schema(), other.city);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.zipcode)) {
        this.zipcode = data().deepCopy(fields()[3].schema(), other.zipcode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.county)) {
        this.county = data().deepCopy(fields()[4].schema(), other.county);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.aliases)) {
        this.aliases = data().deepCopy(fields()[5].schema(), other.aliases);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.metadata)) {
        this.metadata = data().deepCopy(fields()[6].schema(), other.metadata);
        fieldSetFlags()[6] = true;
      }
    }

    /** Gets the value of the 'address1' field */
    public java.lang.CharSequence getAddress1() {
      return address1;
    }
    
    /** Sets the value of the 'address1' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setAddress1(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.address1 = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'address1' field has been set */
    public boolean hasAddress1() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'address1' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearAddress1() {
      address1 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'address2' field */
    public java.lang.CharSequence getAddress2() {
      return address2;
    }
    
    /** Sets the value of the 'address2' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setAddress2(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.address2 = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'address2' field has been set */
    public boolean hasAddress2() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'address2' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearAddress2() {
      address2 = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'city' field */
    public java.lang.CharSequence getCity() {
      return city;
    }
    
    /** Sets the value of the 'city' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.city = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'city' field has been set */
    public boolean hasCity() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'city' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'zipcode' field */
    public java.lang.Long getZipcode() {
      return zipcode;
    }
    
    /** Sets the value of the 'zipcode' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setZipcode(long value) {
      validate(fields()[3], value);
      this.zipcode = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'zipcode' field has been set */
    public boolean hasZipcode() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'zipcode' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearZipcode() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'county' field */
    public java.lang.Object getCounty() {
      return county;
    }
    
    /** Sets the value of the 'county' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setCounty(java.lang.Object value) {
      validate(fields()[4], value);
      this.county = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'county' field has been set */
    public boolean hasCounty() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'county' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearCounty() {
      county = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'aliases' field */
    public java.util.List<java.lang.CharSequence> getAliases() {
      return aliases;
    }
    
    /** Sets the value of the 'aliases' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setAliases(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[5], value);
      this.aliases = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'aliases' field has been set */
    public boolean hasAliases() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'aliases' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearAliases() {
      aliases = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'metadata' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMetadata() {
      return metadata;
    }
    
    /** Sets the value of the 'metadata' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder setMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[6], value);
      this.metadata = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'metadata' field has been set */
    public boolean hasMetadata() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'metadata' field */
    public org.apache.hadoop.hive.hbase.avro.Address.Builder clearMetadata() {
      metadata = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public Address build() {
      try {
        Address record = new Address();
        record.address1 = fieldSetFlags()[0] ? this.address1 : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.address2 = fieldSetFlags()[1] ? this.address2 : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.city = fieldSetFlags()[2] ? this.city : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.zipcode = fieldSetFlags()[3] ? this.zipcode : (java.lang.Long) defaultValue(fields()[3]);
        record.county = fieldSetFlags()[4] ? this.county : (java.lang.Object) defaultValue(fields()[4]);
        record.aliases = fieldSetFlags()[5] ? this.aliases : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[5]);
        record.metadata = fieldSetFlags()[6] ? this.metadata : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
