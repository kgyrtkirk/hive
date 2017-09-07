set hive.mapred.mode=nonstrict;
set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.optimize.sort.dynamic.partition=true;


set hive.stats.autogather=true;
set hive.stats.column.autogather=true;

-- single level partition, sorted dynamic partition enabled
drop table acid;
CREATE TABLE acid(key string, value string) PARTITIONED BY(ds string) CLUSTERED BY(key) INTO 2 BUCKETS STORED AS ORC TBLPROPERTIES ('transactional'='true');

desc formatted acid;

insert into table acid partition(ds)  select 'a' as key,'v' as value,'2008-04-08' as ds;

desc formatted acid partition(ds='2008-04-08');

set hive.compute.query.using.stats=false;
explain select count(*) from acid where ds='2008-04-08';
select count(*) from acid where ds='2008-04-08';

set hive.compute.query.using.stats=true;
explain select count(*) from acid where ds='2008-04-08';
select count(*) from acid where ds='2008-04-08';

analyze table acid partition(ds='2008-04-08') compute statistics;

desc formatted acid partition(ds='2008-04-08');

analyze table acid partition(ds='2008-04-08') compute statistics for columns;

desc formatted acid partition(ds='2008-04-08');

set hive.compute.query.using.stats=false;
explain select count(*) from acid where ds='2008-04-08';
select count(*) from acid where ds='2008-04-08';

set hive.compute.query.using.stats=true;
explain select count(*) from acid where ds='2008-04-08';
select count(*) from acid where ds='2008-04-08';

insert into table acid partition(ds)  select 'a' as key,'v' as value,'2008-04-08' as ds;

desc formatted acid partition(ds='2008-04-08');

analyze table acid partition(ds='2008-04-08') compute statistics;

desc formatted acid partition(ds='2008-04-08');

analyze table acid partition(ds='2008-04-08') compute statistics for columns;

