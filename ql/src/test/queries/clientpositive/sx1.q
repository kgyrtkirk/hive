set hive.mapred.mode=nonstrict;
set hive.optimize.skewjoin.compiletime = true;
set hive.auto.convert.join=true;
--set hive.stats.fetch.column.stats=true;
set hive.explain.user=true;
CREATE TABLE tmpT1_n0(key STRING, val STRING) STORED AS TEXTFILE;
LOAD DATA LOCAL INPATH '../../data/files/T1.txt' INTO TABLE tmpT1_n0;
CREATE TABLE T1_n151(key INT, val STRING) SKEWED BY (key) ON ((2));
INSERT OVERWRITE TABLE T1_n151 SELECT key, val FROM tmpT1_n0;
CREATE TABLE tmpT2_n0(key STRING, val STRING) STORED AS TEXTFILE;
LOAD DATA LOCAL INPATH '../../data/files/T2.txt' INTO TABLE tmpT2_n0;
CREATE TABLE T2_n88(key INT, val STRING) SKEWED BY (key) ON ((3));
INSERT OVERWRITE TABLE T2_n88 SELECT key, val FROM tmpT2_n0;
EXPLAIN
SELECT a.*, b.* FROM T1_n151 a RIGHT OUTER JOIN T2_n88 b ON a.key = b.key;
