package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore.Iface;

public class HMSHandlerFactory implements IHMSHandlerFactory {

  @Override
  public Iface newRetryingHMSHandler(String name, HiveConf conf, boolean local)
      throws MetaException {
    return HiveMetaStore.newRetryingHMSHandler(name, conf, local);
  }

  @Override
  public Iface newHMSHandler(String name, HiveConf conf, boolean local) throws MetaException {
    return new HiveMetaStore.HMSHandler(name, conf, true);
  }

}
