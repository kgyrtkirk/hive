--! qt:authorizer
--! qt:dataset:src
set hive.mapred.mode=nonstrict;

create table authorization_create_temp_table_1 as select * from src limit 10;
grant select on authorization_create_temp_table_1 to user user1;

set user.name=user1;

create temporary table tmp1(c1 string, c2 string);

insert overwrite table tmp1 select * from authorization_create_temp_table_1;

select c1, count(*) from tmp1 group by c1 order by c1;

drop table tmp1;
