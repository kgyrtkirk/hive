package org.apache.hadoop.hive.ql.scheduled;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.security.SessionStateConfigUserAuthenticator;

public class XL1 {

  public static void setupCC(HiveConf conf, String user) {
    conf.setVar(HiveConf.ConfVars.HIVE_AUTHENTICATOR_MANAGER, SessionStateConfigUserAuthenticator.class.getName());
    conf.set("user.name", user);
  }

}
