package org.apache.hadoop.hive.ql.schq;

import java.util.concurrent.ExecutorService;

import org.apache.hadoop.hive.conf.HiveConf;

public class ScheduledQueryExecutionContext {

  public ExecutorService executor;
  public ScheduledQueryX schedulerService;
  public HiveConf conf;

  public ScheduledQueryExecutionContext(ExecutorService executor, HiveConf conf) {
    this.executor = executor;
    this.conf = conf;
  }

  public long getIdleSleepTime() {
    // FIXME make this configurable?
    return 1000;
  }

}
