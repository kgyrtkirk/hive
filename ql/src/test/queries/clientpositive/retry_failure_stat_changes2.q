
create table s (x int);

insert into s values 
(1),(2),(3),(4),(5),
(6),(7),(8),(9),(10);

create table tu(id_uv int,id_uw int,u int);
create table tv(id_uv int,v int);
create table tw(id_uw int,w int);

from s
insert overwrite table tu
	select x as id_uv,x as id_uw,x as u
	where x<=6 or x=10
insert overwrite table tv
	select x as id_uv,x as v
	where x<=3 or x=10
insert overwrite table tw
	select x as id_uw,x as w
;



set hive.explain.user=true;
-- set hive.query.reexecution.strategy=reoptimize;
set hive.query.reexecution.explain=true;
-- set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

select sum(u*v*w) from tu
	join tv on (tu.id_uv=tv.id_uv)
	join tw on (tu.id_uw=tw.id_uw)
	where w>9 and u>1 and v>3;

