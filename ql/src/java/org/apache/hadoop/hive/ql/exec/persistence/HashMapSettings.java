/*
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

package org.apache.hadoop.hive.ql.exec.persistence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;

public class HashMapSettings {


  private float keyCountAdj;
  private int hashTableThreshold;
  private float loadFactor;
  private int wbSize;

  public HashMapSettings(Configuration hconf) {
    keyCountAdj = HiveConf.getFloatVar(hconf, HiveConf.ConfVars.HIVEHASHTABLEKEYCOUNTADJUSTMENT);
    hashTableThreshold = HiveConf.getIntVar(hconf, HiveConf.ConfVars.HIVEHASHTABLETHRESHOLD);
    loadFactor = HiveConf.getFloatVar(hconf, HiveConf.ConfVars.HIVEHASHTABLELOADFACTOR);
    wbSize = HiveConf.getIntVar(hconf, HiveConf.ConfVars.HIVEHASHTABLEWBSIZE);
  }

  public HashMapSettings() {
    keyCountAdj = HiveConf.ConfVars.HIVEHASHTABLEKEYCOUNTADJUSTMENT.defaultFloatVal;
    hashTableThreshold = HiveConf.ConfVars.HIVEHASHTABLETHRESHOLD.defaultIntVal;
    loadFactor = HiveConf.ConfVars.HIVEHASHTABLELOADFACTOR.defaultFloatVal;
    wbSize = HiveConf.ConfVars.HIVEHASHTABLEWBSIZE.defaultIntVal;
  }

  public float getKeyCountAdj() {
    return keyCountAdj;
  }

  public int getThreshold() {
    return hashTableThreshold;

  }

  public float getLoadFactor() {
    return loadFactor;
  }

  public float loadFactor() {
    return loadFactor;
  }

  public int wbSize() {
    return wbSize;
  }

  public int getWB() {
    return wbSize;
  }

}
