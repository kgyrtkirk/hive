--! qt:scheduledqueryservice
--! qt:dataset:src

source ../../metastore/scripts/upgrade/hive/hive-schema-4.0.0.hive.sql;
set role admin;
create role src_role_wadmin;
grant  src_role_wadmin to user hive_test_user with admin option;

set hive.test.authz.sstd.hs2.mode=true;
set hive.security.authorization.manager=org.apache.hadoop.hive.ql.security.authorization.plugin.sqlstd.SQLStdHiveAuthorizerFactoryForTest;
set hive.security.authenticator.manager=org.apache.hadoop.hive.ql.security.SessionStateConfigUserAuthenticator;

set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;


set user.name=hive_admin_user;



set role admin;

use sys;

create scheduled query asd cron '* * * * * ? *' defined as select 1;

!sleep 10;

desc formatted scheduled_queries;

select 
	scheduled_query_id,
	schedule_name,
	enabled,
	cluster_namespace,
	`schedule`,
	`user`,
	query,
	next_execution>0
 from scheduled_queries;

select	scheduled_execution_id,
	scheduled_query_id,
	state,
	start_time>0,
	end_time>=start_time,
	error_message,
	last_update_time>=start_time
		from scheduled_executions order by SCHEDULED_EXECUTION_ID limit 1;

alter scheduled query asd disable;

select schedule_name,enabled from scheduled_queries;

!sleep 1;
