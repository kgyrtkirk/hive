package org.apache.hadoop.hive.ql.schq;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.ScheduledQueryPollRequest;
import org.apache.hadoop.hive.metastore.api.ScheduledQueryPollResponse;
import org.apache.hadoop.hive.metastore.api.ScheduledQueryProgressInfo;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetastoreBasedScheduledQueryService implements IScheduledQueryMaintenanceService {

  private static final Logger LOG = LoggerFactory.getLogger(MetastoreBasedScheduledQueryService.class);
  private HiveConf conf;

  public MetastoreBasedScheduledQueryService(HiveConf conf) {
    this.conf = conf;
  }

  @Override
  public ScheduledQueryPollResponse scheduledQueryPoll() {
    try {
      ScheduledQueryPollRequest request = new ScheduledQueryPollRequest();
      request.setClusterNamespace(getClusterNamespace());
      ScheduledQueryPollResponse resp = getMSC().scheduledQueryPoll(request);
      return resp;
    } catch (Exception e) {
      logException("Exception while polling scheduled queries", e);
      return null;
    }
  }

  @Override
  public void scheduledQueryProgress(ScheduledQueryProgressInfo info) {
    try {
      getMSC().scheduledQueryProgress(info);
    } catch (Exception e) {
      logException("Exception while updating scheduled execution status of: " + info.getScheduledExecutionId(), e);
    }
  }

  private IMetaStoreClient getMSC() throws MetaException, HiveException {
    return Hive.get(conf).getMSC();
  }

  static void logException(String msg, Exception e) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(msg, e);
    } else {
      LOG.info(msg + ": " + e.getMessage());
    }
  }

  @Override
  public String getClusterNamespace() {
    return conf.getVar(ConfVars.HIVE_SCHEDULED_QUERIES_NAMESPACE);

  }

}
