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

package org.apache.hadoop.hive.ql.metadata.formatting;

import java.util.ArrayList;
import java.util.List;

import org.spark_project.guava.collect.Lists;

public class TextMetaDataTable {

  List<List<String>> table = new ArrayList<>();

  public void addHeader(String... values) {
    assert (values.length > 0);
    ArrayList<String> v = Lists.<String> newArrayList(values);
    v.set(0, "# " + v.get(0));
    table.add(v);
  }

  public void addRow(String... values) {
    table.add(Lists.<String> newArrayList(values));
  }

  public String renderTable(boolean isOutputPadded) {
    StringBuilder str = new StringBuilder();
    for (List<String> row : table) {
      MetaDataFormatUtils.formatOutput(row.toArray(new String[] {}), str, isOutputPadded);
    }
    return str.toString();
  }

  public void transpose() {
    throw new RuntimeException();
  }

}
