package org.apache.hive.service.server;

interface FailoverHandler {
  void failover() throws Exception;
}