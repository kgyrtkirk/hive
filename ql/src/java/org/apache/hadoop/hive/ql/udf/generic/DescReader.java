
package org.apache.hadoop.hive.ql.udf.generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

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
    private String tableName;
    private List<Col> parts = new ArrayList<>();

    public DRX(String tableName) {
      this.tableName = tableName;
    }

    public void add(Col col, boolean partition2) {
      if(partition2) {
        parts.add(col);
      } else {
        cols.add(col);
      }
    }

    public String buildSrc() {
      StringBuilder sb = new StringBuilder("create table ");
      sb.append(tableName);

      sb.append(" (\n  " + getColsDemoteComplexToString(cols) + ")\n ");
      if (parts.size() != 0) {
        sb.append("partitioned by (\n  " + getColsDemoteComplexToString(parts) + ")\n ");
      }
      sb.append(";");


      return sb.toString();
    }

    private String getColsDemoteComplexToString(List<Col> parts2) {
      List<String> c1 = new ArrayList<>();
      for (Col col : parts2) {
        if (isComplex(col.type)) {
          c1.add(col.name + " string");
        } else {
          c1.add(col.name + " " + col.type);
        }
      }
      return Joiner.on(",\n  ").join(c1);
    }

    public String buildView() {
      StringBuilder sb = new StringBuilder("create view ");
      sb.append(tableName);
      sb.append("_view");
      if (parts.size() != 0) {
        sb.append(" partitioned on (" + colNamesOf(parts) + ") ");
      }
      sb.append(" as \n select\n  ");
      ArrayList<Col> allCols = new ArrayList<>();
      allCols.addAll(cols);
      allCols.addAll(parts);
      List<String> viewC = new ArrayList<>();
      for (Col c : allCols) {
        String colStr;
        if (isComplex(c.type)) {
          colStr = String.format("json_parse(%s,'%s') as %s", c.name, c.type, c.name);
        } else {
          colStr = c.name;
        }
        viewC.add(colStr);
      }
      sb.append(Joiner.on(",\n  ").join(viewC));
      sb.append("\n from " + tableName + ";");

      return sb.toString();
    }

    private boolean isComplex(String type) {
      return type.contains("array") || type.contains("struct");
    }

    private String colNamesOf(List<Col> cols) {
      StringBuffer sb = new StringBuffer();
      for (Col col : cols) {
        if (sb.length() > 0) {
          sb.append(",");
        }
        sb.append(col.name);
      }
      return sb.toString();
    }

    public void setPart() {
    }

  }

  public static void main(String[] args) throws Exception {
    List<DRX> tables = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("/home/kirk/Downloads/DDL.txt"))) {
      String l;
      DRX drx = null;
      boolean partition = false;
      while ((l = br.readLine()) != null) {
        l = l.trim();
        if (l.startsWith("hive>")) {
          if (drx != null) {
            throw new RuntimeException("FX: " + l);
          }
          String[] pp = l.split("[ ;]+");
          if (pp.length != 4) {
            throw new RuntimeException();
          }
          drx = new DRX(pp[3]);
          partition = false;
          continue;
        }

        if (drx != null) {
          if (l.startsWith("# P")) {
            partition = true;
          } else if (l.startsWith("# D")) {
            tables.add(drx);
            drx = null;
          } else if (l.startsWith("#") || l.startsWith("O") || l.length() == 0) {
          } else {
            String[] parts = l.split("[ ]+");
            if (parts.length != 2) {
              throw new RuntimeException("EE: " + l);
            }
            drx.add(new Col(parts[0], parts[1]), partition);
          }
        }
      }
    }

    for (DRX drx : tables) {
      System.out.println(drx.buildSrc());
      System.out.println(drx.buildView());
    }

  }

}
