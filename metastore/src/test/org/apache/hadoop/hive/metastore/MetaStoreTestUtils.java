package org.apache.hadoop.hive.metastore;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStore.HMSHandler;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaStoreTestUtils {

  protected static final Logger LOG = LoggerFactory.getLogger("hive.log");
  
  public static int startMetaStore() throws Exception {
    return MetaStoreTestUtils.startMetaStore(ShimLoader.getHadoopThriftAuthBridge(), null);
  }

  public static int startMetaStore(final HadoopThriftAuthBridge bridge, HiveConf conf) throws Exception {
    int port = MetaStoreUtils.findFreePort();
    MetaStoreTestUtils.startMetaStore(port, bridge, conf);
    return port;
  }

  public static int startMetaStore(HiveConf conf) throws Exception {
    return startMetaStore(ShimLoader.getHadoopThriftAuthBridge(), conf);
  }

  public static void startMetaStore(final int port, final HadoopThriftAuthBridge bridge) throws Exception {
    MetaStoreTestUtils.startMetaStore(port, bridge, null);
  }

  public static void startMetaStore(final int port,
        final HadoopThriftAuthBridge bridge, HiveConf hiveConf)
        throws Exception{
      if (hiveConf == null) {
        hiveConf = new HiveConf(HMSHandler.class);
      }
      final HiveConf finalHiveConf = hiveConf;
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            HiveMetaStore.startMetaStore(port, bridge, finalHiveConf);
          } catch (Throwable e) {
            LOG.error("Metastore Thrift Server threw an exception...",e);
          }
        }
      });
      thread.setDaemon(true);
      thread.start();
      loopUntilHMSReady(port);
    }

  /**
   * A simple connect test to make sure that the metastore is up
   * @throws Exception
   */
  private static void loopUntilHMSReady(int port) throws Exception {
    int retries = 0;
    Exception exc = null;
    while (true) {
      try {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(port), 5000);
        socket.close();
        return;
      } catch (Exception e) {
        if (retries++ > 60) { //give up
          exc = e;
          break;
        }
        Thread.sleep(1000);
      }
    }
    // something is preventing metastore from starting
    // print the stack from all threads for debugging purposes
    LOG.error("Unable to connect to metastore server: " + exc.getMessage());
    LOG.info("Printing all thread stack traces for debugging before throwing exception.");
    LOG.info(getAllThreadStacksAsString());
    throw exc;
  }

  private static String getAllThreadStacksAsString() {
    Map<Thread, StackTraceElement[]> threadStacks = Thread.getAllStackTraces();
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Thread, StackTraceElement[]> entry : threadStacks.entrySet()) {
      Thread t = entry.getKey();
      sb.append(System.lineSeparator());
      sb.append("Name: ").append(t.getName()).append(" State: " + t.getState());
      addStackString(entry.getValue(), sb);
    }
    return sb.toString();
  }

  private static void addStackString(StackTraceElement[] stackElems, StringBuilder sb) {
    sb.append(System.lineSeparator());
    for (StackTraceElement stackElem : stackElems) {
      sb.append(stackElem).append(System.lineSeparator());
    }
  }

}
