package xl;

public class DebugHelper {

  public static String INTEREST_PATTERN = "ALTER TABLE anaylyze_external ADD PARTITION";
  public static String query = null;
  public static boolean interesting = false;
  public static int i0 = 0;
  public static int i1 = 0;
  public static int i2 = 0;

  public static boolean isQueryIntresting1(String q0) {
    query = q0;
    interesting = query.contains(INTEREST_PATTERN);
    return isQueryIntresting();
  }

  public static boolean isQueryIntresting() {
    return interesting;
  }
}
