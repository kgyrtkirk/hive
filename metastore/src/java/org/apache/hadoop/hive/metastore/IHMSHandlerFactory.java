package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore.Iface;

public interface IHMSHandlerFactory {

  Iface newRetryingHMSHandler(String name, HiveConf conf, boolean local) throws MetaException;

  Iface newHMSHandler(String name, HiveConf conf, boolean init) throws MetaException;

}
