


create table t (
c1 integer,c2 integer,c3 integer,c4 integer,c5 integer,c6 integer,c7 integer,c8 integer,c9 integer,c10 integer,c11 integer,c12 integer,c13 integer,c14 integer,c15 integer,c16 integer
) stored as orc;

create table x1 stored as orc as select * from t;
create table x2 stored as orc as select * from t;
create table x3 stored as orc as select * from t;
create table x4 stored as orc as select * from t;
create table x5 stored as orc as select * from t;
create table x6 stored as orc as select * from t;

select qq,count(1) from (
select q as qq from (
select t.c1 as q,* from
	t
	left outer join x1 on(t.c1=x1.c1)
	left outer join x2 on(x1.c2=x2.c2)
	left outer join x3 on(x2.c3=x3.c3)
	left outer join x4 on(x3.c4=x4.c4)
	left outer join x5 on(x4.c5=x5.c5)
	left outer join x6 on(x5.c6=x6.c6)
union all
select t.c1 as q,* from
	t
	left outer join x1 on(t.c1=x1.c2)
	left outer join x2 on(x1.c2=x2.c3)
	left outer join x3 on(x2.c3=x3.c4)
	left outer join x4 on(x3.c4=x4.c5)
	left outer join x5 on(x4.c5=x5.c6)
	left outer join x6 on(x5.c6=x6.c7)
) t0
) tt
group by qq
;
