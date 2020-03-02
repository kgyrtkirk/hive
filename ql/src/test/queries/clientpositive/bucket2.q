--! qt:dataset:src
set hive.explain.user=false;
;
set hive.exec.reducers.max = 1;

-- SORT_QUERY_RESULTS

CREATE TABLE bucket2_1(key int, value string) CLUSTERED BY (key) INTO 2 BUCKETS;

explain extended
insert overwrite table bucket2_1
select * from src;
