create table t (a int);

explain select a from t where
  a>0 and null between 0 and 10;
