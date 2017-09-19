package org.apache.hadoop.hive.ql.stats;

import static org.junit.Assert.assertTrue;

import org.apache.hadoop.hive.ql.plan.ColStatistics.Range;
import org.junit.Test;

public class TestStatsUtils {

  @Test
  public void test1() {
    Range r1 = new Range(0.1f, 0.4f);
    Range r2 = new Range(0.3f, 0.9f);
    assertTrue(rangeContains(r1, 0.2f));
    Range r3 = StatsUtils.combineRange(r1, r2);
    System.out.println(r3);
    assertTrue(rangeContains(r3, 0.2f));
  }

  private boolean rangeContains(Range range, Number f) {
    double m = range.minValue.doubleValue();
    double M = range.maxValue.doubleValue();
    double v = f.doubleValue();
    return m <= v && v <= M;
  }

}
