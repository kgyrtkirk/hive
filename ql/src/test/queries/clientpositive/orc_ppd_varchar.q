--! qt:dataset:src1
--! qt:dataset:src

set hive.vectorized.execution.enabled=false;
SET hive.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat;
SET mapred.min.split.size=1000;
SET mapred.max.split.size=5000;

create table newtypesorc_n1(c char(10), v varchar(10), d decimal(5,3), da date) stored as orc tblproperties("orc.stripe.size"="16777216");

insert overwrite table newtypesorc_n1 select * from (select cast("apple" as char(10)), cast("bee" as varchar(10)), 0.22, cast("1970-02-20" as date) from src src1 union all select cast("hello" as char(10)), cast("world" as varchar(10)), 11.22, cast("1970-02-27" as date) from src src2) uniontbl;

set hive.optimize.index.filter=false;

-- varchar data types (EQUAL, NOT_EQUAL, LESS_THAN, LESS_THAN_EQUALS, IN, BETWEEN tests)
explain
select hash(c),hash(v),hash(d),hash(da),*,hash(*) from newtypesorc_n1 where v="bee" ; -- or v="xbee";
select hash(c),hash(v),hash(d),hash(da),*,hash(*) from newtypesorc_n1 where v="bee" ; -- or v="xbee";
select sum(hash(*)) from newtypesorc_n1 where v="bee" or v="xbee";

set hive.optimize.index.filter=true;
select *,hash(*) from newtypesorc_n1 where v="bee";
select sum(hash(*)) from newtypesorc_n1 where v="bee";

create table t (a varchar(10));

insert into t values('bee'),('xxx');

-- select	assert_true(t0.v = t1.v) from
select	t0.v,t1.v from
	(select hash(a) as v from t where a='bee') as t0
join	(select hash(a) as v from t where a='bee' or a='xbee') as t1 on (true);


