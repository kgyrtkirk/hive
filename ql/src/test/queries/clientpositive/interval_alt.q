
select
	(1) hour,
	(1) day;

select date '2012-01-01' + (30) day;
select date '2012-01-01' + (-30) day;

create table t (dt integer);
insert into t values (1),(2);

select date '2012-01-01' + (dt) day from t;

