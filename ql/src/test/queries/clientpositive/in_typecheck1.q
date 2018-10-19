
set hive.vectorized.execution.enabled=false;

create table ax(s char(1),t char(10));

insert into ax values ('a','a'),('a','a '),('b','bb');

explain
select 'expected 2',count(*) from ax where s = 'a' and t = 'a';
select 'expected 2',count(*) from ax where s = 'a' and t = 'a';

explain
select 'expected 3',count(*) from ax where (s,t) in (('a','a'),('b','bb'));
select 'expected 3',count(*) from ax where (s,t) in (('a','a'),('b','bb'));

select 'expected 2',* from ax where t = 'a         ';
select 'expected 2',* from ax where t = 'a          ';
select 'expected 0',* from ax where t = 'a          d';
