
create table t (a int);

insert into t values (1),(2),(3);

explain
select * from t join (select 1 as v) s where s.v=t.a;
