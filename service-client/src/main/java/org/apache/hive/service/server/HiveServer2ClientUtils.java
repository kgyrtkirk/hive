package org.apache.hive.service.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;

public class HiveServer2ClientUtils {

  public static boolean isHTTPTransportMode(Configuration hiveConf) {
    String transportMode = System.getenv("HIVE_SERVER2_TRANSPORT_MODE");
    if (transportMode == null) {
      transportMode = hiveConf.get(ConfVars.HIVE_SERVER2_TRANSPORT_MODE.varname);
    }
    if (transportMode != null && (transportMode.equalsIgnoreCase("http"))) {
      return true;
    }
    return false;
  }

  public static boolean isKerberosAuthMode(Configuration hiveConf) {
    String authMode = hiveConf.get(ConfVars.HIVE_SERVER2_AUTHENTICATION.varname);
    if (authMode != null && (authMode.equalsIgnoreCase("KERBEROS"))) {
      return true;
    }
    return false;
  }

}
