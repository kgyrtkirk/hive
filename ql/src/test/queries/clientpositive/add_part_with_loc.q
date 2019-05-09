set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.dynamic.partition=true;
set hive.vectorized.execution.enabled=true;


set hive.fileformat.check=true;
--dfs -mkdir ${system:test.tmp.dir}/;
-- dfs -cp ${system:hive.root}/data/files/ext_test ${system:test.tmp.dir}/analyze_external;


-- dfs -rmr -f hdfs:///tmp/supply;
-- dfs -rmr -f hdfs:///tmp/supply4;
-- dfs -mkdir -p hdfs:///tmp/whroot_ext;


create table supply (id int, part string, quantity int) partitioned by (day int)
	stored as orc
	location 'hdfs:///tmp/a1'
	TBLPROPERTIES ('transactional'='true')
;


explain alter table supply add partition (day=20110102) location 
	'hdfs:///tmp/a2';

alter table supply add partition (day=20110103) location 
	'hdfs:///tmp/a3';

-- create table texternal(key string, val string) partitioned by (insertdate string) stored as orc location 'pfile://${system:test.tmp.dir}/texternal0';
-- dfs ${system:test.dfs.mkdir} ${system:test.tmp.dir}/texternal;
-- alter table texternal add partition (insertdate='2008-01-01') location 'pfile://${system:test.tmp.dir}/texternal/2008-01-01';

