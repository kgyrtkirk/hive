
set hive.strict.checks.bucketing=false;

set hive.mapred.mode=nonstrict;

set hive.auto.convert.join=true;
set hive.auto.convert.sortmerge.join=true;
set hive.optimize.bucketmapjoin = true;
set hive.optimize.bucketmapjoin.sortedmerge = true;



CREATE TABLE bucket_medium (key string, value string) partitioned by (ds string)
CLUSTERED BY (key) SORTED BY (key) INTO 3 BUCKETS STORED AS TEXTFILE;
load data local inpath '../../data/files/smallsrcsortbucket1outof4.txt' INTO TABLE bucket_medium partition(ds='2008-04-08');
load data local inpath '../../data/files/smallsrcsortbucket2outof4.txt' INTO TABLE bucket_medium partition(ds='2008-04-08');
load data local inpath '../../data/files/smallsrcsortbucket3outof4.txt' INTO TABLE bucket_medium partition(ds='2008-04-08');

explain select key,max(value) from bucket_medium group by key;
