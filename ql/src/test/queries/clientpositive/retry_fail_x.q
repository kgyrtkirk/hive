SET hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

create table t_chicken(a int);
create table t_mammut(a int);

insert into t_chicken values (1),(10);
insert into t_mammut values (1),(2),(3),(4),(5),(6),(7),(8),(9),(11);

set hive.auto.convert.join.noconditionaltask.size=20000000;
set hive.auto.convert.join=true;
set hive.query.reexecution.enabled=true;
set hive.query.reexecution.strategies=overlay,reoptimize;

explain
select count(1) from t_mammut join t_chicken where t_mammut.a=t_chicken.a;

explain reoptimization
select count(1) from t_mammut join t_chicken where t_mammut.a=t_chicken.a;

