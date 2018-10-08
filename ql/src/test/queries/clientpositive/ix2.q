create table t (a int);

insert into t values(3),(10);

explain select a from t where
 (a>1 and null between 0 and 10) is null;
