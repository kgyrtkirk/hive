package org.apache.hadoop.hive.common.metrics.metrics2;

import org.apache.hadoop.hive.conf.HiveConf;
import org.junit.Test;

public class TestCodahaleMetrics2 {

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
