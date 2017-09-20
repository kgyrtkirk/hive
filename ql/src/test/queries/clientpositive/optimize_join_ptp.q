set hive.mapred.mode=nonstrict;
set hive.explain.user=false;

create table t1 (v string, k int);
insert into t1 values ('people', 10), ('strangers', 20), ('parents', 30);

create table t2 (v string, k double);
insert into t2 values ('people', 10), ('strangers', 20), ('parents', 30);

explain
select t1.* from t1,t2
where
	t1.v=t2.v and t1.k=t2.k
and	t1.v='people'
and	t2.k=10
;

select t1.* from t1,t2
where
	t1.v=t2.v and t1.k=t2.k
and	t1.v='people'
and	t2.k=10
;



