drop table if exists tx1;
create table tx1 (a integer,b integer,c integer);

insert into tx1 values (1,1,1);

select  sum(c),
        grouping(b),
	'NULL,1' as expected
from    tx1
where	a<0
group by a,b grouping sets ((), b, a);

select  sum(c),
        grouping(b),
	'no rows' as expected
from    tx1
where	a<0
group by b grouping sets (b);

select  sum(c),
        grouping(b),
	'NULL,1' as expected
from    tx1
where	a<0
group by rollup (b);
