--! qt:dataset:src
--! qt:dataset:part
--! qt:dataset:lineitem
set hive.mapred.mode=nonstrict;
set hive.explain.user=false;

-- SORT_QUERY_RESULTS

create table tnull(i int, c char(2));
insert into tnull values(NULL, NULL), (NULL, NULL);

create table tempty(c char(2));
 
CREATE TABLE part_null(
    p_partkey INT,
    p_name STRING,
    p_mfgr STRING,
    p_brand STRING,
    p_type STRING,
    p_size INT,
    p_container STRING,
    p_retailprice DOUBLE,
    p_comment STRING
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ","
;

LOAD DATA LOCAL INPATH '../../data/files/part_tiny_nulls.txt' overwrite into table part_null;

insert into part_null values(78487,NULL,'Manufacturer#6','Brand#52','LARGE BRUSHED BRASS', 23, 'MED BAG',1464.48,'hely blith');

-- corr, mix of IN/NOT IN
explain cbo select * from part_null where p_name IN ( select p_name from part where part.p_type = part_null.p_type)
        AND p_brand NOT IN (select p_container from part where part.p_type = part_null.p_type
                                AND p_brand IN (select p_brand from part pp where part.p_type = pp.p_type));

explain select * from part_null where p_name IN ( select p_name from part where part.p_type = part_null.p_type)
        AND p_brand NOT IN (select p_container from part where part.p_type = part_null.p_type
                                AND p_brand IN (select p_brand from part pp where part.p_type = pp.p_type));

select * from part_null where p_name IN ( select p_name from part where part.p_type = part_null.p_type)
        AND p_brand NOT IN (select p_container from part where part.p_type = part_null.p_type
                                AND p_brand IN (select p_brand from part pp where part.p_type = pp.p_type));
