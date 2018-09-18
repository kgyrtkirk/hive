--! qt:dataset:src

set hive.transpose.aggr.join=true;
set hive.vectorized.execution.enabled=false;

set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask.size=10000000;



set hive.vectorized.execution.enabled=true;

-- HIVE-5891 Alias conflict when merging multiple mapjoin tasks into their common
-- child mapred task
EXPLAIN ANALYZE
SELECT x.key FROM (
  SELECT c.key FROM
    (SELECT a.key FROM src a JOIN src b ON a.key=b.key GROUP BY a.key) tmp
    JOIN src c ON tmp.key=c.key
  UNION ALL
  SELECT c.key FROM
    (SELECT a.key FROM src a JOIN src b ON a.key=b.key GROUP BY a.key) tmp
    JOIN src c ON tmp.key=c.key
) x order by x.key;

SELECT x.key FROM (
  SELECT c.key FROM
    (SELECT a.key FROM src a JOIN src b ON a.key=b.key GROUP BY a.key) tmp
    JOIN src c ON tmp.key=c.key
  UNION ALL
  SELECT c.key FROM
    (SELECT a.key FROM src a JOIN src b ON a.key=b.key GROUP BY a.key) tmp
    JOIN src c ON tmp.key=c.key
) x order by x.key;

