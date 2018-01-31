set hive.vectorized.execution.enabled=false;

drop table if exists tx2;

set hive.vectorized.execution.enabled=true;

set hive.optimize.null.scan=false;

create table tx2 (a integer,b integer,c integer,d double,u string,bi binary) stored as orc;

select  sum(c),
	max(u),
	'asd',
        grouping(b),
	'NULL,1' as expected
from    tx2
where	a<0
group by a,b,d grouping sets ((), b, a, d);


insert into tx2 values
(1,2,3,1.1,'x','b'),
(3,2,3,1.1,'y','b');

select  sum(a),
	u,
	bi,
	'asd',
        grouping(bi),
	'NULL,1' as expected
from    tx2
where	a=2
group by a,u,bi grouping sets ( u, (), bi);
