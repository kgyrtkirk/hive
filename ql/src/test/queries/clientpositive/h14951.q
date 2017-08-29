set hive.auto.convert.sortmerge.join=true;
set hive.auto.convert.join=false;
create table t1 (a int, b string);
create table t2 (a int, b string);
insert into t1 values (81, 'one'), (82, 'two');
insert into t2 values (91, 'one'), (92, 'two');
set hive.explain.user=true;
select * from (select distinct a from t1) as t1a, (select distinct a from t2) as t2a where t1a.a = t2a.a;
set hive.explain.user=false;
select * from (select distinct a from t1) as t1a, (select distinct a from t2) as t2a where t1a.a = t2a.a;

