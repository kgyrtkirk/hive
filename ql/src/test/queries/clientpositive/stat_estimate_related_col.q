set hive.explain.user=true;
set hive.query.results.cache.enabled=false;

drop table if exists t1;
drop table if exists t8;

create table t1 (a integer,b integer);
create table t8 like t1;

insert into t1 values (1,1),(2,2),(3,3),(4,4),(5,5);

insert into t8
select * from t1 union all select * from t1 union all select * from t1 union all select * from t1 union all
select * from t1 union all select * from t1 union all select * from t1 union all select * from t1
;

analyze table t1 compute statistics for columns;
analyze table t8 compute statistics for columns;

explain analyze select sum(a) from t8 where b in (2,3) group by b;
explain analyze select sum(a) from t8 where b=2 group by b;

explain analyze select sum(a) from t1 where b in (2,3) and b=2 group by b;
explain analyze select sum(a) from t8 where b in (2,3) and b=2 group by b;
