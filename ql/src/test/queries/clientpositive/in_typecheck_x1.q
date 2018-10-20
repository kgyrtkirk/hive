create table t (a string);

insert into t values ('1'),('x'),('2.0');

explain
select * from t where a in (1.0,'x',2);
select * from t where a in (1.0,'x',2);
