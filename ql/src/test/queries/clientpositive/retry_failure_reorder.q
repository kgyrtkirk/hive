
create table s (x int);
insert into s values (1),(2),(3),(4),(5),(6),(7),(8),(9),(10);
create table tu(id_uv int,id_uw int,u int);
create table tv(id_uv int,v int);
create table tw(id_uw int,w int);
from s
insert overwrite table tu
        select x,x,x
        where x<=6 or x=10
insert overwrite table tv
        select x,x
        where x<=3 or x=10
insert overwrite table tw
        select x,x
;


set hive.explain.user=true;

set zzz=1;
set reexec.overlay.zzz=2000;


select (${hiveconf:zzz} > sum(u*v*w)) from tu
        join tv on (tu.id_uv=tv.id_uv)
        join tw on (tu.id_uw=tw.id_uw)
        where w>9 and u>1 and v>3;

explain
select (${hiveconf:zzz} > sum(u*v*w)) from tu
        join tv on (tu.id_uv=tv.id_uv)
        join tw on (tu.id_uw=tw.id_uw)
        where w>9 and u>1 and v>3;


set hive.query.reexecution.strategy=reoptimize;
set hive.query.reexecution.explain=true;
set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;
set hive.optimize.ppd=false;

select assert_true_oom(${hiveconf:zzz} > sum(u*v*w)) from tu
        join tv on (tu.id_uv=tv.id_uv)
        join tw on (tu.id_uw=tw.id_uw)
        where w>9 and u>1 and v>3;

explain
select assert_true_oom(${hiveconf:zzz} > sum(u*v*w)) from tu
        join tv on (tu.id_uv=tv.id_uv)
        join tw on (tu.id_uw=tw.id_uw)
        where w>9 and u>1 and v>3;

