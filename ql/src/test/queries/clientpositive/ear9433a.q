CREATE TABLE widetable ( c1 int, c2 int);

explain
select c1,c2 from widetable group by struct(c1,c2),c1,c2;
select c1,c2 from widetable group by struct(c1,c2),c1,c2;

explain
select distinct * from widetable;
select distinct * from widetable;
