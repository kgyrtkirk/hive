set hive.mapred.mode=nonstrict;
SET hive.vectorized.execution.enabled = true;
SET hive.int.timestamp.conversion.in.seconds=false;
set hive.fetch.task.conversion=none;

create table t (s string) stored as orc;

insert into t values ('false');
insert into t values ('FALSE');
insert into t values ('FaLsE');
insert into t values ('true');
insert into t values ('TRUE');
insert into t values ('TrUe');
insert into t values ('');
insert into t values ('Other');

explain SELECT CAST(s AS BOOLEAN) FROM t;

SELECT CAST(s AS BOOLEAN) FROM t;
