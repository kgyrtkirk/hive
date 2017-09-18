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
package org.apache.hadoop.hive.cli.control;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UnusedQOutWiper {

  public static class QOutFilter implements FilenameFilter {

    Pattern pattern = Pattern.compile(".*q.out$");

    @Override
    public boolean accept(File dir, String fileName) {
      return pattern.matcher(fileName).matches();
    }

  }

  public static class Params {
    @Parameter(names = "--delete", description = "Removes any unreferenced q.out")
    private boolean delete = false;

  }

  public static void main(String[] args) throws Exception {

    Params params = new Params();
    new JCommander(params, args);

    Set<File> outsFound = new HashSet<>();
    Map<File, AbstractCliConfig> outsNeeded = new HashMap<>();

    for (Class<?> clz : CliConfigs.class.getDeclaredClasses()) {
      if (clz == CliConfigs.DummyConfig.class) {
        continue;
      }
      AbstractCliConfig config = (AbstractCliConfig) clz.newInstance();
      Set<File> qfiles = config.getQueryFiles();
      for (File file : qfiles) {
        String baseName = file.getName();
        String rd = config.getResultsDir();
        File of = new File(rd, baseName + ".out");
        if (outsNeeded.containsKey(of)) {
          System.err.printf("duplicate: [%s;%s] %s\n", config.getClass().getSimpleName(), outsNeeded.get(of).getClass().getSimpleName(), of);
          // throw new RuntimeException("duplicate?!");
        }
        outsNeeded.put(of, config);
      }

      File od = new File(config.getResultsDir());
      for (File file : od.listFiles(new QOutFilter())) {
        outsFound.add(file);
      }
    }

    outsFound.removeAll(outsNeeded.keySet());
    for (File file : outsFound) {
      System.out.println(file);
      if (params.delete) {
        file.delete();
      }
    }

  }
}
