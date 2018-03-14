/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hive.benchmark;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hive.cli.control.CliConfigs;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.DriverFactory;
import org.apache.hadoop.hive.ql.IDriver;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.google.common.io.Files;

public class gfdg {

  @State(Scope.Benchmark)
  public static class MyState {

    static boolean initialized;

    public HiveTestEnvSetup env_setup = new HiveTestEnvSetup(HiveTestEnvSetup.XAA.TPCDS);

    @Setup(Level.Trial)
    public void doSetup() throws Throwable {
      System.out.println("Do Setup");
      if (initialized) {
        return;
      }
      initialized = true;
      env_setup.bClass();
      env_setup.bMethod();
    }

  }



  @Benchmark
  public void testQuery1x20(MyState st) throws Throwable {
    HiveConf conf = st.env_setup.getTestCtx().hiveConf;

    SessionState.start(conf);

    Set<File> qfiles = new CliConfigs.TezPerfCliConfig().getQueryFiles();
    File qfile = qfiles.iterator().next();

    //    for (File qfile : qfiles)
    for (int i = 0; i < 20; i++)
    {
      String queryStrs = Files.toString(qfile, Charset.defaultCharset());
      String[] parts = queryStrs.split(";");
      assertEquals(3, parts.length);

      try (IDriver driver = DriverFactory.newDriver(conf)) {

        driver.run(parts[1]);

        List res = new ArrayList();
        driver.getResults(res);
        System.out.println(res);
      }
    }
  }

  public static void main(String[] args) throws RunnerException {

    Options options = new OptionsBuilder().include(gfdg.class.getSimpleName()).forks(1).build();
    new Runner(options).run();
  }
}
