create table t (a int);

explain select a from t where
  (case when a>1 then a+1 else null end) between 0 and 10;
