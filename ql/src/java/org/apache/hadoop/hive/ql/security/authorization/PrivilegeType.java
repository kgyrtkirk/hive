/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.security.authorization;

import java.util.HashMap;
import java.util.Map;

/**
 * Privilege type
 */
public enum PrivilegeType {

  ALL("All"),
  ALTER_DATA("Update"),
  ALTER_METADATA("Alter"),
  CREATE("Create"),
  DROP("Drop"),
  INDEX("Index"),
  LOCK("Lock"),
  SELECT("Select"),
  SHOW_DATABASE("Show_Database"),
  INSERT("Insert"),
  DELETE("Delete"),
  UNKNOWN(null);

  private final String name;

  PrivilegeType(String name){
    this.name = name;
  }

  @Override
  public String toString(){
    return name == null ? "unkown" : name;
  }

  private static Map<String, PrivilegeType> name2Type;

  /**
   * Do case insensitive lookup of PrivilegeType with this name
   * @param privilegeName
   * @return corresponding PrivilegeType
   */
  public static PrivilegeType getPrivTypeByName(String privilegeName) {
    populateName2Type();
    String canonicalizedName = privilegeName.toLowerCase();
    PrivilegeType privType = name2Type.get(canonicalizedName);
    if(privType != null){
      return privType;
    }
    return PrivilegeType.UNKNOWN;
  }

  private static synchronized void populateName2Type() {
    if(name2Type != null){
      return;
    }
    name2Type = new HashMap<String, PrivilegeType>();
    for(PrivilegeType privType : PrivilegeType.values()){
      name2Type.put(privType.toString().toLowerCase(), privType);
    }
  }
}
