package org.apache.hive.service.auth;

public class HiveAuthConstants {
  public enum AuthTypes {
    NOSASL("NOSASL"),
    NONE("NONE"),
    LDAP("LDAP"),
    KERBEROS("KERBEROS"),
    CUSTOM("CUSTOM"),
    PAM("PAM");
  
    private final String authType;
  
    AuthTypes(String authType) {
      this.authType = authType;
    }
  
    public String getAuthName() {
      return authType;
    }
  
  }
  public static final String HS2_PROXY_USER = "hive.server2.proxy.user";
  public static final String HS2_CLIENT_TOKEN = "hiveserver2ClientToken";
}
