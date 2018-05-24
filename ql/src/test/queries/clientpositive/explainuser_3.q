--! qt:dataset:srcpart
--! qt:dataset:src
--! qt:dataset:alltypesorc
set hive.strict.checks.bucketing=false;

set hive.security.authorization.manager=org.apache.hadoop.hive.ql.security.authorization.DefaultHiveAuthorizationProvider;
set hive.metastore.filter.hook=org.apache.hadoop.hive.metastore.DefaultMetaStoreFilterHookImpl;
set hive.mapred.mode=nonstrict;
set hive.explain.user=true;

set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;

set hive.exec.dynamic.partition.mode=nonstrict;
set hive.vectorized.execution.enabled=true;

CREATE TABLE acid_vectorized_n0(a INT, b STRING) CLUSTERED BY(a) INTO 2 BUCKETS STORED AS ORC TBLPROPERTIES ('transactional'='true');
insert into table acid_vectorized_n0 select cint, cstring1 from alltypesorc where cint is not null order by cint limit 10;
analyze table acid_vectorized_n0 compute statistics for columns;
explain select a, b from acid_vectorized_n0 order by a, b;

explain select key, value
FROM srcpart LATERAL VIEW explode(array(1,2,3)) myTable AS myCol;

explain show tables;

explain create database newDB location "/tmp/";

create database newDB location "/tmp/";

explain describe database extended newDB;

describe database extended newDB;

explain use newDB;

use newDB;

create table tab_n1 (name string);

explain alter table tab_n1 rename to newName;

explain drop table tab_n1;

drop table tab_n1;

explain use default;

use default;

drop database newDB;

explain analyze table src compute statistics;

explain analyze table src compute statistics for columns;

explain
CREATE TEMPORARY MACRO SIGMOID (x DOUBLE) 1.0 / (1.0 + EXP(-x));

CREATE TEMPORARY MACRO SIGMOID (x DOUBLE) 1.0 / (1.0 + EXP(-x));

EXPLAIN SELECT SIGMOID(2) FROM src LIMIT 1;
explain DROP TEMPORARY MACRO SIGMOID;
DROP TEMPORARY MACRO SIGMOID;

explain create table src_autho_test_n3 as select * from src;
create table src_autho_test_n3 as select * from src;

set hive.security.authorization.enabled=true;

explain grant select on table src_autho_test_n3 to user hive_test_user;
grant select on table src_autho_test_n3 to user hive_test_user;

explain show grant user hive_test_user on table src_autho_test_n3;
explain show grant user hive_test_user on table src_autho_test_n3(key);

select key from src_autho_test_n3 order by key limit 20;

explain revoke select on table src_autho_test_n3 from user hive_test_user;

explain grant select(key) on table src_autho_test_n3 to user hive_test_user;

explain revoke select(key) on table src_autho_test_n3 from user hive_test_user;

explain 
create role sRc_roLE;

create role sRc_roLE;

explain
grant role sRc_roLE to user hive_test_user;

grant role sRc_roLE to user hive_test_user;

explain show role grant user hive_test_user;

explain drop role sRc_roLE;
drop role sRc_roLE;

set hive.security.authorization.enabled=false;
drop table src_autho_test_n3;

explain drop view v_n1;

explain create view v_n1 as with cte as (select * from src  order by key limit 5)
select * from cte;

explain with cte as (select * from src  order by key limit 5)
select * from cte;

create table orc_merge5_n0 (userid bigint, string1 string, subtype double, decimal1 decimal, ts timestamp) stored as orc;

load data local inpath '../../data/files/orc_split_elim.orc' into table orc_merge5_n0;

SET hive.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat;
SET mapred.min.split.size=1000;
SET mapred.max.split.size=50000;
SET hive.optimize.index.filter=true;
set hive.merge.orcfile.stripe.level=false;
set hive.merge.tezfiles=false;
set hive.merge.mapfiles=false;
set hive.merge.mapredfiles=false;
set hive.compute.splits.in.am=true;
set tez.grouping.min-size=1000;
set tez.grouping.max-size=50000;

set hive.merge.orcfile.stripe.level=true;
set hive.merge.tezfiles=true;
set hive.merge.mapfiles=true;
set hive.merge.mapredfiles=true;

explain insert overwrite table orc_merge5_n0 select userid,string1,subtype,decimal1,ts from orc_merge5_n0 where userid<=13;

drop table orc_merge5_n0;

set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask=true;
set hive.auto.convert.join.noconditionaltask.size=10000;

CREATE TABLE srcbucket_mapjoin_n3(key int, value string) partitioned by (ds string) CLUSTERED BY (key) INTO 2 BUCKETS STORED AS TEXTFILE;
CREATE TABLE tab_part_n2 (key int, value string) PARTITIONED BY(ds STRING) CLUSTERED BY (key) INTO 4 BUCKETS STORED AS TEXTFILE;
CREATE TABLE srcbucket_mapjoin_part_n3 (key int, value string) partitioned by (ds string) CLUSTERED BY (key) INTO 4 BUCKETS STORED AS TEXTFILE;

load data local inpath '../../data/files/bmj/000000_0' INTO TABLE srcbucket_mapjoin_n3 partition(ds='2008-04-08');
load data local inpath '../../data/files/bmj1/000001_0' INTO TABLE srcbucket_mapjoin_n3 partition(ds='2008-04-08');

load data local inpath '../../data/files/bmj/000000_0' INTO TABLE srcbucket_mapjoin_part_n3 partition(ds='2008-04-08');
load data local inpath '../../data/files/bmj/000001_0' INTO TABLE srcbucket_mapjoin_part_n3 partition(ds='2008-04-08');
load data local inpath '../../data/files/bmj/000002_0' INTO TABLE srcbucket_mapjoin_part_n3 partition(ds='2008-04-08');
load data local inpath '../../data/files/bmj/000003_0' INTO TABLE srcbucket_mapjoin_part_n3 partition(ds='2008-04-08');



set hive.optimize.bucketingsorting=false;
insert overwrite table tab_part_n2 partition (ds='2008-04-08')
select key,value from srcbucket_mapjoin_part_n3;

CREATE TABLE tab_n1(key int, value string) PARTITIONED BY(ds STRING) CLUSTERED BY (key) INTO 2 BUCKETS STORED AS TEXTFILE;
insert overwrite table tab_n1 partition (ds='2008-04-08')
select key,value from srcbucket_mapjoin_n3;

set hive.convert.join.bucket.mapjoin.tez = true;
explain
select a.key, a.value, b.value
from tab_n1 a join tab_part_n2 b on a.key = b.key;



