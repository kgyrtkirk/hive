create table t (a int);

insert into t values (1);
insert into t values (1);
insert into t values (2);

select count(*) from t group by a;
select count(*) from t group by (a);
select count(*) from t group by ();
