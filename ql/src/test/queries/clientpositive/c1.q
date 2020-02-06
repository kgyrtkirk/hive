drop table if exists ODS_D_CRY_RAT ;
drop table if exists tmp_udr_trx_agr_tag_rat ;
drop table if exists tmp_udr_trx_agr_tag_lst_3m ;
drop table if exists tmp_udr_trx_agr_tag_lst_6m ;
drop table if exists tmp_udr_trx_agr_tag_lst_1y ;
drop table if exists pub_s_act_trx_agr_m1 ;
drop table if exists pub_h_s_act_trx_agr_m1 ;

drop table if exists ODS_D_CRY_RAT;

create table ODS_D_CRY_RAT(
con_usd   double
);

CREATE TABLE tmp_udr_trx_agr_tag_rat(
mth int,
con_usd double);

create table tmp_udr_trx_agr_tag_lst_3m(
M           int,
CON_USD     double
);

create table tmp_udr_trx_agr_tag_lst_6m(
M           int,
CON_USD     double
);

create table tmp_udr_trx_agr_tag_lst_1y(
M            int,
CON_USD      double
);

create table pub_s_act_trx_agr_m1(
act_nbr  string
);

create table pub_h_s_act_trx_agr_m1(
m    int
);


set hive.cbo.enable=true;

----------------------------------------------------------------------------------------------------
explain
from(
          select /*+ mapjoin(d)*/ c.*,d.CON_USD from (
                    select    cast(regexp_replace(substring(add_months(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),-1),1,7),'-','') as int) as M
                    from pub_s_act_trx_agr_m1
                  union all
                    select   M
                    from pub_h_s_act_trx_agr_m1
                    where M >= 201812
                              and M >= cast(regexp_replace(substring(add_months(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),-12),1,7),'-','') as int)
          ) c join tmp_udr_trx_agr_tag_rat d on c.M = d.MTH
) e
insert into table tmp_udr_trx_agr_tag_lst_3m
select    M,
          CON_USD 
where M >= cast(regexp_replace(substring(add_months(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),-3),1,7),'-','') as int)
insert into table tmp_udr_trx_agr_tag_lst_6m
select  M,
          CON_USD
where M >= cast(regexp_replace(substring(add_months(from_unixtime(unix_timestamp(),'yyyy-MM-dd'),-6),1,7),'-','') as int)
insert into table tmp_udr_trx_agr_tag_lst_1y
select  M,
        CON_USD;





 
 
 
 
		
