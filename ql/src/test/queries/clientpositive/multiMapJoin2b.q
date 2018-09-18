--! qt:dataset:src1
--! qt:dataset:src

set hive.transpose.aggr.join=true;

set hive.mapred.mode=nonstrict;
set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecutePrinter,org.apache.hadoop.hive.ql.hooks.PrintCompletedTasksHook;
set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask=true;
set hive.auto.convert.join.noconditionaltask.size=6000;
set hive.optimize.semijoin.conversion=true;


set hive.auto.convert.join.noconditionaltask.size=400;
set hive.auto.convert.join.noconditionaltask.size=6000;
set hive.optimize.correlation=false;
set hive.optimize.correlation=true;
set hive.optimize.correlation=false;
set hive.optimize.correlation=true;
set hive.auto.convert.join.noconditionaltask.size=10000000;
set hive.optimize.correlation=false;
-- HIVE-5891 Alias conflict when merging multiple mapjoin tasks into their common
-- child mapred task
EXPLAIN   
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

