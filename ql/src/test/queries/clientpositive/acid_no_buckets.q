--! qt:dataset:srcpart
--this has 4 groups of tests
--Acid tables w/o bucketing
--the tests with bucketing (make sure we get the same results)
--same tests with and w/o vectorization

set hive.mapred.mode=nonstrict;
set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.vectorized.execution.enabled=false;
set hive.explain.user=false;
set hive.merge.cardinality.check=true;

drop table if exists srcpart_acid;
CREATE TABLE srcpart_acid (key STRING, value STRING) PARTITIONED BY (ds STRING, hr STRING) stored as ORC TBLPROPERTIES ('transactional'='true', 'transactional_properties'='default');
insert into srcpart_acid PARTITION (ds, hr) select * from srcpart;

--2 rows for 413, 1 row for 43, 2 for 213, 1 for 44 in kv1.txt (in each partition)

analyze table srcpart_acid PARTITION(ds, hr) compute statistics;
analyze table srcpart_acid PARTITION(ds, hr) compute statistics for columns;
explain update srcpart_acid set value = concat(value, 'updated') where cast(key as integer) in(413,43) and hr='11';
update srcpart_acid set value = concat(value, 'updated') where cast(key as integer) in(413,43) and hr='11';


