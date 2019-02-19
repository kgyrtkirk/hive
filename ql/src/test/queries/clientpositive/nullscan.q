set hive.fetch.task.conversion=none;

create table t(a integer) stored as orc;

insert into t values(1),(2),(2),(2),(3);

explain analyze
select * from t where a = 2;
select * from t where a = 2;

explain analyze
select * from t where 1 = 2;
select * from t where 1 = 2;

