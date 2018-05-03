 
set hive.optimize.metadataonly=true;

create table t1 (a int) ;
insert into t1 values (1);

create table t2a  as 
	select * from t1
	union all
	select * from t1
;

select 2,count(*) from t2a;

create table t2b  as select * from
(
	select * from (select * from t1) sq1
	union all
	select * from (select * from t1) sq2
) tt
;


select 2,count(*) from t2b;
