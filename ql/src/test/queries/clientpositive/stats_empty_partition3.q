set hive.stats.autogather=false;
set hive.explain.user=false;
drop table if exists t;

create table t (a integer);

insert into t values (1);

explain analyze
select x.a*y.a from 
	(
		select a from t
		union
		(select a from t limit 1)
	) x
join t y on (x.a=y.a);




explain 
select a from t group by a;
