set hive.mapred.mode=nonstrict;

CREATE EXTERNAL TABLE header_footer_table_1 (name string, message string, id int) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' tblproperties ("skip.header.line.count"="1", "skip.footer.line.count"="2");

LOAD DATA LOCAL INPATH '../../data/files/header_footer_table_1' OVERWRITE INTO TABLE header_footer_table_1;

SELECT * FROM header_footer_table_1;

set hive.vectorized.execution.enabled=false;
SELECT name,count(*) FROM header_footer_table_1 group by name;
set hive.vectorized.execution.enabled=true;
SELECT name,count(*) FROM header_footer_table_1 group by name;

explain
SELECT count(distinct name) FROM header_footer_table_1;
SELECT count(distinct name) FROM header_footer_table_1;

