


create table t (
c1 integer,c2 integer,c3 integer,c4 integer,c5 integer,c6 integer,c7 integer,c8 integer,c9 integer,c10 integer,c11 integer,c12 integer,c13 integer,c14 integer,c15 integer,c16 integer
);

create table x1 as select * from t;
create table x2 as select * from t;
create table x3 as select * from t;
create table x4 as select * from t;
create table x5 as select * from t;
create table x6 as select * from t;

select * from
	t
	left outer join x1 on(t.c1=x1.c1)
	left outer join x2 on(t.c2=x2.c2)
	left outer join x3 on(t.c3=x3.c3)
	left outer join x4 on(t.c4=x4.c4)
	left outer join x5 on(t.c5=x5.c5)
	left outer join x6 on(t.c6=x6.c6)
;
