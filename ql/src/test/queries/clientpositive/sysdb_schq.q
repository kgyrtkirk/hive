--! qt:scheduledqueryservice
--! qt:dataset:src

source ../../metastore/scripts/upgrade/hive/hive-schema-4.0.0.hive.sql;

use sys;

create scheduled query asd cron '* * * * * ? *' defined as select 1;

!sleep 5;

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

select schedule_name from scheduled_queries where enabled = false;

!sleep 1;
