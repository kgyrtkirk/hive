package org.apache.hadoop.hive.common.metrics.metrics2;

import org.apache.hadoop.hive.conf.HiveConf;
import org.junit.Test;

public class TestCodahaleMetrics2 {

  // HIVE-15141: test if close / reconstruct of CodahaleMetrics is ok...earlier there was an exception in case HADOOP2 metrics reporter is used.
  @Test
  public void testCloseAndReopenIsWorking() throws Exception {
    HiveConf conf = new HiveConf();

    conf.setVar(HiveConf.ConfVars.HIVE_METRICS_REPORTER, MetricsReporting.HADOOP2.name());

    CodahaleMetrics cm = new CodahaleMetrics(conf);
    cm.close();
    cm = new CodahaleMetrics(conf);
    cm.close();
  }

}
