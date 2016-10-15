
select
	(1) second,
	(2) seconds,
	(1) minute,
	(2) minutes,
	(1) hour,
	(2) hours,
	(1) day,
	(2) days,
	(1) month,
	(2) months,
	(1) year;
	(2) years;

select date '2012-01-01' + (30) days;
select date '2012-01-01' + (-30) days;

create table t (dt int);
insert into t values (1),(2);

select
	date '2012-01-01' +          ( -dt*dt ) day,
	date '2012-01-01' - interval ( -dt*dt ) day
	from t;

