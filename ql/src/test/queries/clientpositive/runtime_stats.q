set hive.exec.post.hooks = org.apache.hadoop.hive.ql.hooks.TezRuntimeStatisticsHook;

create view vx1 as
select count(1),sum(c*c+c+1) from
( select value,count(1) as c from src
	where	value != 'asdsad'
	group	by value
) as t;


create view vx2 as
select count(1),sum(c*c+c+1) from
( select value,count(1) as c from src
	where	value != 'asdsadX'
	group	by value
) as t;

explain
select * from vx1;

select * from vx1;

explain
select * from vx1;

explain
select * from vx2;

select * from vx1;

explain
select * from vx1;
