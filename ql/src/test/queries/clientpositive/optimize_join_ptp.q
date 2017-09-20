set hive.mapred.mode=nonstrict;
set hive.explain.user=false;

create table t1 (s1 string, i1 int);
insert into t1 values ('people', 10), ('strangers', 20), ('parents', 30);

create table t2 (s2 string, i2 int);
insert into t2 values ('people', 10), ('strangers', 20), ('parents', 30);


select * from t1 where t1.i1 in (select avg(t2.i2) where t2.s2=t1.s1);


