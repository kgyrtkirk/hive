package org.apache.hive.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.apache.hive.jdbc.Utils.JdbcConnectionParams;

public class X0 {

  /**
   * Returns true is only if HA service discovery mode is enabled
   *
   * @param sessionConf - session configuration
   * @return true if serviceDiscoveryMode=zooKeeperHA is specified in JDBC URI
   */
  public static boolean isZkHADynamicDiscoveryMode(Map<String, String> sessionConf) {
    final String discoveryMode = sessionConf.get(JdbcConnectionParams.SERVICE_DISCOVERY_MODE);
    return (discoveryMode != null) &&
      JdbcConnectionParams.SERVICE_DISCOVERY_MODE_ZOOKEEPER_HA.equalsIgnoreCase(discoveryMode);
  }

  static List<JdbcConnectionParams> getDirectParamsList(JdbcConnectionParams connParams)
      throws ZooKeeperHiveClientException {
    CuratorFramework zooKeeperClient = null;
    try {
      zooKeeperClient = ZooKeeperHiveClientHelper.getZkClient(connParams);
      List<String> serverHosts = ZooKeeperHiveClientHelper.getServerHosts(connParams, zooKeeperClient);
      final List<JdbcConnectionParams> directParamsList = new ArrayList<>();
      // For each node
      for (String serverNode : serverHosts) {
        JdbcConnectionParams directConnParams = new JdbcConnectionParams(connParams);
        directParamsList.add(directConnParams);
        ZooKeeperHiveClientHelper.updateParamsWithZKServerNode(directConnParams, zooKeeperClient, serverNode);
      }
      return directParamsList;
    } catch (Exception e) {
      throw new ZooKeeperHiveClientException("Unable to read HiveServer2 configs from ZooKeeper", e);
    } finally {
      // Close the client connection with ZooKeeper
      if (zooKeeperClient != null) {
        zooKeeperClient.close();
      }
    }
  }

}
