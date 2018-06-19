--! qt:dataset:src1
--! qt:dataset:src

DROP TABLE IF EXISTS dest1;
CREATE TABLE dest1 (key STRING COMMENT 'default', value STRING COMMENT 'default')
STORED AS
INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat';

FROM src src1 JOIN src src2 ON src1.key = src2.key
INSERT OVERWRITE TABLE dest1 SELECT key
