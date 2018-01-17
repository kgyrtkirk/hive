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

package org.apache.hadoop.hive.ql.plan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.rules.ExternalResource;

import com.google.common.collect.Sets;

// FIXME: move this to somewhere else
public class HiveTestEnvSetup extends ExternalResource {

  public static final String HIVE_ROOT = getHiveRoot();

  private static String getHiveRoot() {
    List<String> candidateSiblings = new ArrayList<>();
    if (System.getProperty("hive.root") != null) {
      try {
        candidateSiblings.add(new File(System.getProperty("hive.root")).getCanonicalPath());
      } catch (IOException e) {
        throw new RuntimeException("error getting hive.root", e);
      }
    }
    candidateSiblings.add(new File(".").getAbsolutePath());

    for (String string : candidateSiblings) {
      File curr = new File(string);
      do {
        Set<String> lls = Sets.newHashSet(curr.list());
        if (lls.contains("itests") && lls.contains("ql") && lls.contains("metastore")) {
          System.out.println("detected hiveRoot: " + curr);
          return ensurePathEndsInSlash(curr.getAbsolutePath());
        }
        curr = curr.getParentFile();
      } while (curr != null);
    }
    throw new RuntimeException("unable to find hiveRoot");
  }

  public static String ensurePathEndsInSlash(String path) {
    if (path == null) {
      throw new NullPointerException("Path cannot be null");
    }
    if (path.endsWith(File.separator)) {
      return path;
    } else {
      return path + File.separator;
    }
  }

  @Override
  protected void before() throws Throwable {

  }

  @Override
  protected void after() {
  }


}
