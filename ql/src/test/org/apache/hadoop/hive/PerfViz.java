/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PerfViz {

  static class PerfLogInfo {

    private boolean isClose;
    private Map<String, String> map = new HashMap<>();
    private List<PerfLogInfo> childs = new ArrayList<>();
    private String label;
    private HashMap<String, Long> statMap;

    private PerfLogInfo() {
    }

    public static PerfLogInfo parse(String l) {
      PerfLogInfo entry = new PerfLogInfo();
      Pattern p = Pattern.compile(".*(</?)PERFLOG(.*)>.*");
      Matcher m = p.matcher(l);
      if (!m.matches()) {
        System.err.println(l);
        throw new RuntimeException(l);
      }
      String b = m.group(1).trim();
      entry.isClose = b.endsWith("/");
      String g = m.group(2).trim();
      Pattern p2 = Pattern.compile("([^=]*)=([^=]*) ");
      Matcher m2 = p2.matcher(g);
      //      if (!m2.matches()) {
      //        System.err.println(l);
      //        throw new RuntimeException(l);
      //      }
      while (m2.find()) {

        String k = m2.group(1);
        String v = m2.group(2);
        //        System.out.println(k);
        //        System.out.println(v);
        entry.map.put(k, v);
      }
      return entry;

    }

    public void addChild(PerfLogInfo ent) {
      childs.add(ent);
    }

    public void merge(PerfLogInfo ent) {

      String string = map.get("method");
      String anObject = ent.map.get("method");
      if (!string.equals(anObject)) {
        throw new RuntimeException("inconsitent");
      }
      map.putAll(ent.map);
      computeStat();
    }

    private void computeStat() {
      statMap = new HashMap<String, Long>();
      for (PerfLogInfo c : childs) {
        String m = c.getMethod();
        long d = c.getDuration();
        Long v = statMap.getOrDefault(m, 0L);
        statMap.put(m, v + d);
      }
    }

    @Override
    public String toString() {
      return String.format("%s(%s[%d]):%s", map.get("method"), isClose, childs.size(), map.get("duration"));
    }

    long getDuration() {
      String d = map.get("duration");
      if (d == null) {
        return 0;
      }
      return Long.parseLong(d);
    }

    String getMethod() {
      String d = map.get("method");
      if (d == null) {
        return "?";
      }
      return d;
    }

  }

  static class PLB {

    Deque<PerfLogInfo> stack = new LinkedList<>();
    private PerfLogInfo root = PerfLogInfo.parse("<PERFLOG method=root>");

    public PLB() {
      stack.push(root);
    }

    public void add(PerfLogInfo ent) {
      if (ent.isClose) {
        if (stack.size() == 1) {
          throw new RuntimeException("more closes than open");
        }
        stack.peek().merge(ent);
        stack.pop();
      } else {
        PerfLogInfo parent = stack.peek();
        parent.addChild(ent);
        stack.push(ent);
      }
    }

  }

  @Test
  public void asd() throws FileNotFoundException {

    Scanner sc = new Scanner(new File("../_plog2"));
    PLB plb = new PLB();

    int lineNo = 0;
    while (sc.hasNextLine()) {
      try {
        String l = sc.nextLine();
        lineNo++;

        if (!l.contains("fc062c7a-a5b0-437f-8594-6776af29f987")) {
          //        if (!l.contains("2c81c6c1-aa6f-4609-8250-5b1a5360a8ba")) {
          continue;
        }
        PerfLogInfo ent = PerfLogInfo.parse(l);
        plb.add(ent);
      } catch (Exception e) {
        throw new RuntimeException("during processing line: " + lineNo, e);
      }
    }
    System.out.println(plb);

  }

}
