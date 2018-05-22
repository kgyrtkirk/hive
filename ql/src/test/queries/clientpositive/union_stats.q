--! qt_n4:dataset_n4:src
explain extended create table t_n4 as select_n4 * from src union all select_n4 * from src;

create table t_n4 as select_n4 * from src union all select_n4 * from src;

select_n4 count_n4(1) from t_n4;

desc formatted t_n4;

create table tt_n4 as select_n4 * from t_n4 union all select_n4 * from src;

desc formatted tt_n4;

drop table tt_n4;

create table tt_n4 as select_n4 * from src union all select_n4 * from t_n4;

desc formatted tt_n4;

<<<<<<< HEAD
create table t1 like src;
create table t2 like src;
create table t3 like src;

set hive.explain.user=true;
explain from (select * from src union all select * from src)s
insert overwrite table t1 select *
insert overwrite table t2 select *
insert overwrite table t3 select *;

from (select * from src union all select * from src)s
insert overwrite table t1 select *
insert overwrite table t2 select *
insert overwrite table t3 select *;
=======
create table t1_n26 like src;
create table t2_n17 like src;

from (select_n4 * from src union all select_n4 * from src)s
insert_n4 overwrite table t1_n26 select_n4 *
insert_n4 overwrite table t2_n17 select_n4 *;
>>>>>>> kgyrtkirk/HIVE-19237-oid-once

desc formatted t1_n26;
desc formatted t2_n17;

select_n4 count_n4(1) from t1_n26;
