set hive.optimize.index.filter      =true;
set hive.mapjoin.hybridgrace.hashtable=true;
set hive.vectorized.execution.enabled=true;
set hive.vectorized.execution.mapjoin.minmax.enabled=true;
set hive.vectorized.execution.mapjoin.native.enabled=true;
set hive.vectorized.execution.mapjoin.native.fast.hashtable.enabled=true;
set hive.vectorized.execution.reduce.enabled=true;
set hive.vectorized.groupby.checkinterval=4096;
set hive.vectorized.groupby.flush.percent=0.1;
set hive.vectorized.groupby.maxentries=100000;
set hive.explain.user=true;
set hive.auto.convert.join=true;
set hive.auto.convert.join      =true;
set hive.auto.convert.join.noconditionaltask      =true;
set hive.auto.convert.join.noconditionaltask.size      =1145324612;
set hive.auto.convert.sortmerge.join      =true;
set hive.auto.convert.sortmerge.join.to.mapjoin      =false;
set hive.convert.join.bucket.mapjoin.tez      =false;
set hive.enforce.sortmergebucketmapjoin      =true;
set hive.mapjoin.bucket.cache.size      =10000;
set hive.mapjoin.hybridgrace.hashtable      =true;
set hive.mapjoin.optimized.hashtable      =true;
set hive.optimize.bucketmapjoin      =true;
set hive.optimize.bucketmapjoin.sortedmerge      =false;
set hive.optimize.dynamic.partition.hashjoin      =true;
set hive.smbjoin.cache.rows      =10000;
set hive.vectorized.execution.mapjoin.minmax.enabled      =true;
set hive.vectorized.execution.mapjoin.native.enabled      =true;
set hive.vectorized.execution.mapjoin.native.fast.hashtable.enabled      =true;

drop table if exists x1_store_sales;
drop table if exists x1_date_dim;
drop table if exists x1_item;

create table x1_store_sales 
(
	ss_item_sk	int
)
partitioned by (ss_sold_date_sk int)
stored as orc;

create table x1_date_dim
(
	d_date_sk	int,
	d_month_seq	int,
	d_year		int,
	d_moy		int
)
stored as orc;

create table x1_item
(
	i_item_sk	int,
	i_category	char(10),
	i_current_price	decimal(7,2)
)
stored as orc;

insert into x1_date_dim values	(1,1,2000,2),
				(1,2,2001,2);
insert into x1_store_sales partition (ss_sold_date_sk=1) values (1);
insert into x1_store_sales partition (ss_sold_date_sk=2) values (2);

insert into x1_item values (1,2,1),(1,2,1),(2,2,1);

alter table x1_store_sales partition (ss_sold_date_sk=1) update statistics set(
'numRows'='123456',
'rawDataSize'='1234567');

alter table x1_date_dim update statistics set(
'numRows'='56',
'rawDataSize'='81449');

alter table x1_item update statistics set(
'numRows'='18',
'rawDataSize'='32710');

explain 
select   count(*) cnt
 from
     x1_store_sales s
     ,x1_date_dim d
     ,x1_item i
 where  
	1=1
 	and s.ss_sold_date_sk = d.d_date_sk
 	and s.ss_item_sk = i.i_item_sk
 	and d.d_month_seq in	 
 	     (select distinct (d_month_seq)
 	      from x1_date_dim
               where d_year = 2000 and d_year*d_moy > 200000
 	        and d_moy = 2 )
 	and i.i_current_price > 
            (select min(j.i_current_price) 
 	     from x1_item j 
 	     where j.i_category = i.i_category)

 group by d.d_month_seq
 order by cnt 
 limit 100;


select   count(*) cnt
 from
     x1_store_sales s
     ,x1_date_dim d
     ,x1_item i
 where  
	1=1
 	and s.ss_sold_date_sk = d.d_date_sk
 	and s.ss_item_sk = i.i_item_sk
 	and d.d_month_seq in	 
 	     (select distinct (d_month_seq)
 	      from x1_date_dim
               where d_year = 2000 and d_year*d_moy > 200000
 	        and d_moy = 2 )
 	and i.i_current_price > 
            (select min(j.i_current_price) 
 	     from x1_item j 
 	     where j.i_category = i.i_category)

 group by d.d_month_seq
 order by cnt 
 limit 100;

