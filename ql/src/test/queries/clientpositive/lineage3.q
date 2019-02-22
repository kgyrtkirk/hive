--! qt:dataset:src1
--! qt:dataset:alltypesorc
SET hive.vectorized.execution.enabled=false;
set hive.mapred.mode=nonstrict;
set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.LineageLogger;
set hive.metastore.disallow.incompatible.col.type.changes=false;



explain
select key, value from src1
where key not in (select key+18 from src1) order by key;

select key, value from src1
where key not in (select key+18 from src1) order by key;

