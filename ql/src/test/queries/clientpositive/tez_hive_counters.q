set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

create table t (a integer);
insert into t values (1),(2),(4);

-- 3 records everywhere
explain select count(*) from t group by a;
select count(*) from t group by a;

-- 1 record after FIL
explain select count(*) from t where a<4 and a>1 group by a;
select count(*) from t where a<4 and a>1 group by a;
