
create table t1 (id int);
create table t2 (id int);

insert into t1 values (1),(10);
insert into t2 values (1),(2),(3),(4),(5);

explain
select t1.* from t1 join t2 on (t1.id=t2.id);

explain analyze
select t1.* from t1 join t2 on (t1.id=t2.id);

explain reoptimization
select t1.* from t1 join t2 on (t1.id=t2.id);
