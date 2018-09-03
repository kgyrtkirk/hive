set hive.vectorized.execution.mapjoin.native.fast.hashtable.enabled=true;

create external table t_bad0 (id bigint,str bigint,s1 double)
        row format delimited FIELDS TERMINATED BY '\u0009'
        stored as textfile
        TBLPROPERTIES('transactional'='false');

create external table t_good0 (id bigint,str bigint,s2 double)
        row format delimited FIELDS TERMINATED BY '\u0009'
        stored as textfile
        TBLPROPERTIES('transactional'='false');

LOAD DATA LOCAL INPATH '../../../hwx/data/files/I100M2.data.txt' INTO TABLE t_bad0;
LOAD DATA LOCAL INPATH '../../../hwx/data/files/I100M2.data.txt' INTO TABLE t_good0;

set hive.mapred.mode=nonstrict;
set hive.security.authorization.manager=org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactoryForTest;
set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;

set hive.stats.fetch.column.stats=true;
create table t_bad stored as orc
        TBLPROPERTIES('transactional'='true')
	as select * from t_bad0
;
create table t_good stored as orc
        TBLPROPERTIES('transactional'='true')
	as select * from t_good0
;


analyze table t_bad compute statistics for columns;
analyze table t_good compute statistics for columns;

-- LOAD DATA LOCAL INPATH '../../data/files/100M.data.txt' INTO TABLE t_bad;

set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask = true;
set hive.auto.convert.join.noconditionaltask.size = 2000000000;


explain
select sum(length(l.str||l.id||r.id||r.str||l.s1||r.s2)) from t_bad l join t_good r on (l.id=r.id);

select sum(length(l.str||l.id||r.id||r.str||l.s1||r.s2)),count(*) from t_bad l join t_good r on (l.id=r.id);

