import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hive.jdbc.miniHS2.MiniHS2;
import org.apache.hive.jdbc.miniHS2.MiniHS2.MiniClusterType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RunHS2 {
  private static MiniHS2 miniHS2;

  @BeforeClass
  public static void preTests() throws Exception {
    HiveConf hiveConf = new HiveConf();
    hiveConf.setVar(HiveConf.ConfVars.HIVE_LOCK_MANAGER,
        "org.apache.hadoop.hive.ql.lockmgr.EmbeddedLockManager");
    hiveConf.setBoolVar(HiveConf.ConfVars.HIVEOPTIMIZEMETADATAQUERIES, false);
    hiveConf.set(ConfVars.HIVE_SERVER2_LOGGING_OPERATION_LEVEL.varname, "verbose");
    miniHS2 = new MiniHS2(hiveConf, MiniClusterType.TEZ,true);

    Map<String, String> confOverlay = new HashMap<String, String>();
    miniHS2.start(confOverlay);

//    createTable();
  }
 
  @AfterClass
  public static void postTests() {
    if (miniHS2.isStarted()) {
      miniHS2.stop();
    }
  }

  @Test
  public void aawait() throws InterruptedException{
    while(true){
      System.out.println(miniHS2.getBaseJdbcURL());
      Thread.sleep(1000);
    }
  }
  
  
}
