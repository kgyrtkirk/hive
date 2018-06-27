-- create view over literals
create view view17 as select 1 as v;
select * from view17;

create view view18 as select v+1 from (select 1 as v) t;
select * from view18;

