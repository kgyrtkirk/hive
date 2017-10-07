drop table if exists tx1;
create table tx1 (a integer,b integer);
insert into tx1	values  (1, 1),
                        (1, 2),
                        (1, 3);

explain
select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a);

explain
select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a and u.b <> v.b);

select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a and u.b <> v.b);

select count(*) as result,3 as expected from tx1 u
where exists (select count(*) from tx1 v where u.a=v.a and u.b <> v.b);

explain
select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a and u.b <> v.b limit 1);

select count(*) as result,3 as expected from tx1 u
where exists (select * from tx1 v where u.a=v.a and u.b <> v.b limit 1);

