--! qt:dataset:lineitem

set hive.mapred.mode=nonstrict;
set hive.security.authorization.manager=org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactoryForTest;
set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;

create table lineitem2
 stored as orc  TBLPROPERTIES ('transactional'='true')
  as select * from lineitem;
create table lineitem_stage
 stored as orc  TBLPROPERTIES ('transactional'='true')
  as select * from lineitem limit 1;

explain
merge into lineitem2 using (select * from lineitem_stage) sub on sub.L_ORDERKEY = lineitem2.L_ORDERKEY when matched then delete;

merge into lineitem2 using (select * from lineitem_stage) sub on sub.L_ORDERKEY = lineitem2.L_ORDERKEY when matched then delete;

	
