
package org.apache.hadoop.hive.ql.udf.generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DescReader {

  static class Col {

    private String name;
    private String type;

    public Col(String name, String type) {
      this.name = name;
      this.type = type;
    }

  }

  static class DRX {

    private List<Col> cols = new ArrayList<>();

    public void add(Col col) {
      cols.add(col);
    }

  }

  public static void main(String[] args) throws Exception {
    List<DRX> tables = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("/home/kirk/Downloads/DDL.txt"))) {
      String l;
      DRX drx = null;
      while ((l = br.readLine()) != null) {
        l = l.trim();
        if (l.startsWith("hive>")) {
          if (drx != null) {
            throw new RuntimeException("FX: " + l);
          }
          drx = new DRX();
          continue;
        }

        if (drx != null) {
          if (l.startsWith("# P") || l.startsWith("# D")) {
            tables.add(drx);
            drx = null;
          } else if (l.startsWith("#") || l.startsWith("O") || l.length() == 0) {
          } else {
            String[] parts = l.split("[ ]+");
            if(parts.length != 2) {
              throw new RuntimeException("EE: " + l);
            }
            drx.add(new Col(parts[0], parts[1]));
          }
        }
      }
    }

  }

}
