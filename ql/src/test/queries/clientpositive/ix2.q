create table t(a integer);
create table t2(b integer);

insert into t values (1),(2),(3);
insert into t2 values (1),(2),(3);

explain
select * from t,t2 where
	a*a=b+3 and (
		(a=1 and b=3) or (a=1 and a<b)
			)
	;

