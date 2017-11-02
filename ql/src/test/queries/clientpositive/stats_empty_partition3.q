set hive.exec.count=1;
create table t (a integer);
set hive.exec.count=4;
insert into t values (4);
set hive.exec.count=1;
select 'cnt',count(*) from t;
