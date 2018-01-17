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

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.IDriver;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

import com.google.common.collect.Sets;

// FIXME: move this to somewhere else
public class HiveTestEnvSetup extends ExternalResource {

  public static final String HIVE_ROOT = getHiveRoot();

  TemporaryFolder tmpFolderRule = new TemporaryFolder(new File(HIVE_ROOT + "/target/tmp"));

  @Override
  protected void before() throws Throwable {

    tmpFolderRule.create();
    File tmpFolder = tmpFolderRule.getRoot();
    String tmpFolderPath = tmpFolder.getAbsolutePath();

    //    System.setProperty("datanucleus.schema.autoCreateAll", "true");

    // FIXME: change scope; remove myriad of vars
    String DATA_DIR = HIVE_ROOT + "/data/";
    FileUtils.copyDirectory(new File(DATA_DIR + "/conf"), new File(tmpFolder, "conf"));

    //    System.out.println(System.getProperty("project_loc"));
    //    -Xmx2048m -XX:MaxPermSize=512m -Dbuild.dir=${project.build.directory}
    //  -Dbuild.test.dir=${test.tmp.dir}
    System.setProperty("build.test.dir", tmpFolderPath);
    //  -Dderby.stream.error.file=${test.tmp.dir}/derby.log
    System.setProperty("derby.stream.error.file", tmpFolderPath + "/derby.log");
    //  -Dhadoop.bin.path=${hadoop.bin.path}
    System.setProperty("hadoop.bin.path", HIVE_ROOT + "/testutils/hadoop");

    //  -Dhadoop.log.dir=${test.tmp.dir}
    System.setProperty("hadoop.log.dir", tmpFolderPath);
    //  -Dhive.root=${hive.root}/
    //  -Dmapred.job.tracker=local
    System.setProperty("mapred.job.tracker", "local");
    //  -Dlog4j.configurationFile=file://${test.tmp.dir}/conf/hive-log4j2.properties
    System.setProperty("mapred.job.tracker", "file://" + tmpFolderPath + "/conf/hive-log4j2.properties");
    //  -Dlog4j.debug=true
    System.setProperty("log4j.debug", "true");
    //  -Djava.io.tmpdir=${test.tmp.dir}
    System.setProperty("java.io.tmpdir", tmpFolderPath);
    //  -Dtest.build.data=${test.tmp.dir}
    System.setProperty("test.build.data", tmpFolderPath);
    //  -Dtest.data.files=${hive.root}/data/files
    System.setProperty("test.data.files", DATA_DIR + "/files");
    //  -Dtest.data.dir=${hive.root}/data/files
    System.setProperty("test.data.dir", DATA_DIR + "/files");
    //  -Dtest.tmp.dir=${test.tmp.dir}
    System.setProperty("test.tmp.dir", tmpFolderPath);
    //  -Dtest.tmp.dir.uri="file://${test.tmp.dir}"
    System.setProperty("test.tmp.dir.uri", "file://" + tmpFolderPath);
    //  -Dtest.dfs.mkdir="-mkdir -p"
    System.setProperty("test.dfs.mkdir", "-mkdir -p");
    //  -Dtest.output.overwrite=false
    //  -Dtest.warehouse.dir=${test.tmp.dir}/../warehouse
    System.setProperty("test.warehouse.dir", tmpFolderPath + "/warehouse"); // this is changed to be *under* tmp dir
    //  -Djava.net.preferIPv4Stack=true
    System.setProperty("java.net.preferIPv4Stack", "true"); // not sure if this will have any effect..

    System.setProperty("test.src.tables", "src");

    File confFolder = new File(tmpFolder, "conf");
    HiveConf.setHiveSiteLocation(new File(confFolder, "hive-site.xml").toURI().toURL());
    HiveConf.setHivemetastoreSiteUrl(new File(confFolder, "hivemetastore-site.xml").toURI().toURL());
    // FIXME: hiveServer2SiteUrl is not settable?

    // XXX??
    HiveConf conf = new HiveConf(IDriver.class);
    //    initConf();
    //    initConfFromSetup();

    // renew the metastore since the cluster type is unencrypted
    //    Hive db = Hive.get(conf); // propagate new conf to meta store

  }

  @Override
  protected void after() {
    tmpFolderRule.delete();
  }

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

}
