
create table t2 (d2 integer);
create table t3 (d3 integer);
create table t5 (d5 integer);
create table t10 (d10 integer);

insert into t2  values (1),(2);
insert into t3  values (1),(2),(3);
insert into t5  values (1),(2),(3),(4),(5);
insert into t10 values (1),(2),(3),(4),(5),(6),(7),(8),(9),(10);

create table t as select * from t2 join t3 join t5  join t10;


explain analyze select count(*) from t where d2=1 and (d3=1 or (d3=2 and d10=1));