package org.apache.hadoop.hive.ql.schq;

public class ScheduledQueryX implements IScheduledQueryX {
  int id = 0;

  @Override
  public ScheduledQueryPollResp scheduledQueryPoll(String catalog) {

    ScheduledQueryPollResp r = new ScheduledQueryPollResp();
    r.executionId = 1;
    r.queryString = "select 1";
    if (id == 0) {
      return r;
    } else {
      return null;
    }
  }

  @Override
  public void scheduledQueryProgress(int executionId, String state, String errorMsg) {
    System.out.printf("%d, state: %s, error: %s", executionId, state, errorMsg);
  }

}
