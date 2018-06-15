SET hive.vectorized.execution.enabled=true;
SET hive.vectorized.execution.reduce.enabled=true;
set hive.mapred.mode=nonstrict;
set hive.map.aggr=true;
set hive.groupby.skewindata=false;
set hive.fetch.task.conversion=none;

-- SORT_QUERY_RESULTS

CREATE TABLE T1_n90(key STRING, val STRING) STORED AS TEXTFILE;

LOAD DATA LOCAL INPATH '../../data/files/T1.txt' INTO TABLE T1_n90;

EXPLAIN VECTORIZATION DETAIL
SELECT key, val, count(1) FROM T1_n90 GROUP BY key, val with cube;
EXPLAIN VECTORIZATION DETAIL
SELECT key, val, count(1) FROM T1_n90 GROUP BY CUBE(key, val);

SELECT key, val, count(1) FROM T1_n90 GROUP BY key, val with cube;

EXPLAIN VECTORIZATION DETAIL
SELECT key, val, GROUPING__ID, count(1) FROM T1_n90 GROUP BY key, val with cube;

SELECT key, val, GROUPING__ID, count(1) FROM T1_n90 GROUP BY key, val with cube;

EXPLAIN VECTORIZATION DETAIL
SELECT key, count(distinct val) FROM T1_n90 GROUP BY key with cube;

SELECT key, count(distinct val) FROM T1_n90 GROUP BY key with cube;

set hive.groupby.skewindata=true;

EXPLAIN VECTORIZATION DETAIL
SELECT key, val, count(1) FROM T1_n90 GROUP BY key, val with cube;

SELECT key, val, count(1) FROM T1_n90 GROUP BY key, val with cube;

EXPLAIN VECTORIZATION DETAIL
SELECT key, count(distinct val) FROM T1_n90 GROUP BY key with cube;

SELECT key, count(distinct val) FROM T1_n90 GROUP BY key with cube;


set hive.multigroupby.singlereducer=true;

CREATE TABLE T2_n55(key1 STRING, key2 STRING, val INT) STORED AS TEXTFILE;
CREATE TABLE T3_n19(key1 STRING, key2 STRING, val INT) STORED AS TEXTFILE;

EXPLAIN VECTORIZATION DETAIL
FROM T1_n90
INSERT OVERWRITE TABLE T2_n55 SELECT key, val, count(1) group by key, val with cube
INSERT OVERWRITE TABLE T3_n19 SELECT key, val, sum(1) group by key, val with cube;


FROM T1_n90
INSERT OVERWRITE TABLE T2_n55 SELECT key, val, count(1) group by key, val with cube
INSERT OVERWRITE TABLE T3_n19 SELECT key, val, sum(1) group by key, val with cube;

