set hive.cbo.enable=false;

drop table if exists tx1;
create table tx1 (a integer,b integer);
insert into tx1	values  (1, 1),
                        (1, 2),
                        (1, 3);

explain
select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a);

select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a);


select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a and u.b <> v.b);

