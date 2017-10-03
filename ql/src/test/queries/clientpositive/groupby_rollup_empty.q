drop table if exists tx1;
create table tx1 (a integer,b integer,c integer);

select sum(c) from tx1;
-- select sum(c) from tx1 group by b;

select  sum(c),
        grouping(b)
from    tx1
group by rollup (b);

explain analyze
select  sum(c),
        grouping(b)
from    tx1
group by rollup (b);
