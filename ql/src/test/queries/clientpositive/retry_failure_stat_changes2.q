
create table tu(id_uv int,id_uw int,u int);
insert into tx values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10);

create table tv as select id_uv,u as v from tu;
create table tw as select id_uw,u as w from tu;

set hive.explain.user=true;
set hive.query.reexecution.strategy=reoptimize;
set hive.query.reexecution.explain=true;
-- set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

select sum(u*v*w) from tu
	join tv on (tu.id_uv=tv.id_uv)
	join tw on (tu.id_uw=tw.id_uw)
	where u<10 and v<5 and w<3;

