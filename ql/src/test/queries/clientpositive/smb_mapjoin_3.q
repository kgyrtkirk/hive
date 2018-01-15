set hive.cbo.enable=false;
set hive.strict.checks.bucketing=false;

-- SORT_QUERY_RESULTS



create table smb_bucket_1(key int, value string) CLUSTERED BY (key) SORTED BY (key) INTO 1 BUCKETS STORED AS RCFILE; 
create table smb_bucket_2(key int, value string) CLUSTERED BY (key) SORTED BY (key) INTO 1 BUCKETS STORED AS RCFILE; 
create table smb_bucket_3(key int, value string) CLUSTERED BY (key) SORTED BY (key) INTO 1 BUCKETS STORED AS RCFILE;

load data local inpath '../../data/files/smb_rc1/000000_0' overwrite into table smb_bucket_1;
load data local inpath '../../data/files/smb_rc2/000000_0' overwrite into table smb_bucket_2;
load data local inpath '../../data/files/smb_rc3/000000_0' overwrite into table smb_bucket_3;

set hive.optimize.bucketmapjoin = true;
set hive.optimize.bucketmapjoin.sortedmerge = true;
set hive.input.format = org.apache.hadoop.hive.ql.io.BucketizedHiveInputFormat;
 
explain
select /*+mapjoin(a)*/ * from smb_bucket_2 a join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(a)*/ * from smb_bucket_2 a join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(a)*/ * from smb_bucket_2 a left outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(a)*/ * from smb_bucket_2 a left outer join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(a)*/ * from smb_bucket_2 a right outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(a)*/ * from smb_bucket_2 a right outer join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(a)*/ * from smb_bucket_2 a full outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(a)*/ * from smb_bucket_2 a full outer join smb_bucket_3 b on a.key = b.key;


explain
select /*+mapjoin(b)*/ * from smb_bucket_2 a join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(b)*/ * from smb_bucket_2 a join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(b)*/ * from smb_bucket_2 a left outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(b)*/ * from smb_bucket_2 a left outer join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(b)*/ * from smb_bucket_2 a right outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(b)*/ * from smb_bucket_2 a right outer join smb_bucket_3 b on a.key = b.key;

explain
select /*+mapjoin(b)*/ * from smb_bucket_2 a full outer join smb_bucket_3 b on a.key = b.key;
select /*+mapjoin(b)*/ * from smb_bucket_2 a full outer join smb_bucket_3 b on a.key = b.key;

 



