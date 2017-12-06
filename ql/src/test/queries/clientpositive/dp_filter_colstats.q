set hive.stats.column.autogather=true;

set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.max.dynamic.partitions.pernode=200;
set hive.exec.max.dynamic.partitions=200;
set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;

create table i0 (p int,v int);
insert into i0 values
	(0,0),
	(1,1),
	(2,2),
	(3,3);

create table p0 (v int) partitioned by (p int) stored as orc
 tblproperties ("transactional"="true", "transactional_properties"="insert_only");

explain insert overwrite table p0 partition (p) select * from i0 where v < 2;
insert overwrite table p0 partition (p) select * from i0 where v < 2;
select * from p0 order by v;

explain insert into table p0 partition (p) select * from i0 where v < 3;
insert into table p0 partition (p) select * from i0 where v < 3;
select * from p0 order by v;

