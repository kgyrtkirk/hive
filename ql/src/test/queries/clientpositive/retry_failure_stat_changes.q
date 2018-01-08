
create table tx(a int,u int);
insert into tx values (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(10,10);

create table px(a int,p int);
insert into px values (2,2),(3,3),(5,5),(7,7),(11,11);


set hive.explain.user=true;
set hive.query.reexecution.strategy=reoptimize;
set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

explain 
select sum(u*p) from tx join px on (u=p) where u<10 and p>2;

set hive.auto.convert.join=false;
explain analyze
select sum(u*p) from tx join px on (u=p) where u<10 and p>2;
set hive.auto.convert.join=true;
explain analyze
select sum(u*p) from tx join px on (u=p) where u<10 and p>2;

set zzz=1;
set reexec.overlay.zzz=2000;

select assert_true_oom(${hiveconf:zzz} > sum(u*p)) from tx join px on (tx.a=px.a) where u<10 and p>2;

