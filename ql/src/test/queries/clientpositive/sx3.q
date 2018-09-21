
set hive.vectorized.execution.enabled=false;

create table ax(l array<string>);

insert into ax values (array(1,2,3));

--explain
--select 'expected 1',count(*) from ax where l = array(1,2,3);
--select 'expected 1',count(*) from ax where l = array(1,2,3);

explain
select 'expected 1',count(*) from ax where l in ( array(1,2,3), array(2,3,4) );
select 'expected 1',count(*) from ax where l in ( array(1,2,3), array(2,3,4) );
