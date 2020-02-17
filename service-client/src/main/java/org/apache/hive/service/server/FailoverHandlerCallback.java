package org.apache.hive.service.server;

public class FailoverHandlerCallback implements FailoverHandler {
  private HS2ActivePassiveHARegistry hs2HARegistry;

  FailoverHandlerCallback(HS2ActivePassiveHARegistry hs2HARegistry) {
    this.hs2HARegistry = hs2HARegistry;
  }

  @Override
  public void failover() throws Exception {
    hs2HARegistry.failover();
  }
}