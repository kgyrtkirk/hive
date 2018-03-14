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

import java.io.File;
import java.nio.charset.Charset;
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

    public static HiveTestEnvSetup env_setup = new HiveTestEnvSetup(HiveTestEnvSetup.XAA.TPCDS);

    public String namePattern = "query64.q";

    @Setup(Level.Trial)
    public void doSetup() throws Throwable {
      System.out.println("Do Setup");
      if (initialized) {
        return;
      }
      initialized = true;
      env_setup.bClass();
      env_setup.bMethod();
      HiveConf conf = MyState.env_setup.getTestCtx().hiveConf;


      SessionState.start(conf);
    }

  }

  //Q1
  //Result "org.apache.hive.benchmark.gfdg.testQuery1x20":
  //  5.869 ±(99.9%) 0.340 ops/s [Average]
  //  (min, avg, max) = (4.819, 5.869, 6.280), stdev = 0.391
  //  CI (99.9%): [5.529, 6.208] (assumes normal distribution)

  //Q64
  //  Result "org.apache.hive.benchmark.gfdg.testQuery1x20":
  //    0.737 ±(99.9%) 0.022 ops/s [Average]
  //    (min, avg, max) = (0.697, 0.737, 0.783), stdev = 0.026
  //    CI (99.9%): [0.715, 0.760] (assumes normal distribution)
  //  Benchmark       Mode  Cnt  Score   Error  Units
  //  testQuery1x20  thrpt   20  0.737 ± 0.022  ops/s

  //  Result "org.apache.hive.benchmark.gfdg.testQuery1x20":
  //    0.031 ±(99.9%) 0.005 ops/s [Average]
  //    (min, avg, max) = (0.023, 0.031, 0.042), stdev = 0.006
  //    CI (99.9%): [0.026, 0.036] (assumes normal distribution)
  //
  //
  //  # Run complete. Total time: 00:21:43
  //
  //  Benchmark       Mode  Cnt  Score   Error  Units
  //  testQuery1x20  thrpt   20  0.031 ± 0.005  ops/s
  //


  @Benchmark
  public void testQuery1x20(MyState st) throws Throwable {

    HiveConf conf = st.env_setup.getTestCtx().hiveConf;

    Set<File> qfiles = new CliConfigs.TezPerfCliConfig().getQueryFiles();

    for (File qfile : qfiles) {
      if (!qfile.getName().matches(st.namePattern)) {
        continue;
      }
      String queryStrs = Files.toString(qfile, Charset.defaultCharset());
      String[] parts = queryStrs.split(";");

      for (String cmd : parts) {
        try (IDriver driver = DriverFactory.newDriver(conf)) {
          if (cmd.startsWith("set")) {
            continue;
          }
          driver.run(cmd);
        }
      }
    }
  }

  public static void main(String[] args) throws RunnerException {

    Options options = new OptionsBuilder().include(gfdg.class.getSimpleName()).forks(1).build();
    new Runner(options).run();
  }
}
