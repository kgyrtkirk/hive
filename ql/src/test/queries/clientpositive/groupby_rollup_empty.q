set hive.vectorized.execution.enabled=true;
create table tx2 (a integer,b integer,c integer,d double,u string) stored as orc;

select  sum(c),
	max(u),
	'asd',
        grouping(b),
	'NULL,1' as expected
from    tx2
where	a<0
group by a,b,d grouping sets ((), b, a, d);

