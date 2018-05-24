--! qt:dataset:src
-- SORT_QUERY_RESULTS

CREATE TABLE DEST1_n163(key ARRAY<STRING>, value BIGINT) STORED AS TEXTFILE;
CREATE TABLE DEST2_n41(key MAP<STRING, STRING>, value BIGINT) STORED AS TEXTFILE;
CREATE TABLE DEST3_n7(key STRUCT<col1:STRING, col2:STRING>, value BIGINT) STORED AS TEXTFILE;

EXPLAIN
FROM SRC
INSERT OVERWRITE TABLE DEST1_n163 SELECT ARRAY(SRC.key), COUNT(1) GROUP BY ARRAY(SRC.key)
INSERT OVERWRITE TABLE DEST2_n41 SELECT MAP(SRC.key, SRC.value), COUNT(1) GROUP BY MAP(SRC.key, SRC.value)
INSERT OVERWRITE TABLE DEST3_n7 SELECT STRUCT(SRC.key, SRC.value), COUNT(1) GROUP BY STRUCT(SRC.key, SRC.value);

FROM SRC
INSERT OVERWRITE TABLE DEST1_n163 SELECT ARRAY(SRC.key), COUNT(1) GROUP BY ARRAY(SRC.key)
INSERT OVERWRITE TABLE DEST2_n41 SELECT MAP(SRC.key, SRC.value), COUNT(1) GROUP BY MAP(SRC.key, SRC.value)
INSERT OVERWRITE TABLE DEST3_n7 SELECT STRUCT(SRC.key, SRC.value), COUNT(1) GROUP BY STRUCT(SRC.key, SRC.value);

SELECT DEST1_n163.* FROM DEST1_n163;
SELECT DEST2_n41.* FROM DEST2_n41;
SELECT DEST3_n7.* FROM DEST3_n7;

