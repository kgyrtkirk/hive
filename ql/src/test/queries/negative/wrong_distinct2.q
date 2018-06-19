--! qt:dataset:src

DROP TABLE IF EXISTS dest1;
CREATE TABLE dest1 (key STRING COMMENT 'default', value STRING COMMENT 'default')
STORED AS
INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat';

FROM src
INSERT OVERWRITE TABLE dest1 SELECT src.key, DISTINCT substr(src.value,4,1) GROUP BY src.key
