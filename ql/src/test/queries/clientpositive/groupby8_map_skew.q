--! qt:dataset:src
set hive.map.aggr=true;
set hive.groupby.skewindata=true;
set mapred.reduce.tasks=31;

-- SORT_QUERY_RESULTS

CREATE TABLE DEST1_n87(key INT, value STRING) STORED AS TEXTFILE;
CREATE TABLE DEST2_n22(key INT, value STRING) STORED AS TEXTFILE;

EXPLAIN
FROM SRC
INSERT OVERWRITE TABLE DEST1_n87 SELECT SRC.key, COUNT(DISTINCT SUBSTR(SRC.value,5)) GROUP BY SRC.key
INSERT OVERWRITE TABLE DEST2_n22 SELECT SRC.key, COUNT(DISTINCT SUBSTR(SRC.value,5)) GROUP BY SRC.key;

FROM SRC
INSERT OVERWRITE TABLE DEST1_n87 SELECT SRC.key, COUNT(DISTINCT SUBSTR(SRC.value,5)) GROUP BY SRC.key
INSERT OVERWRITE TABLE DEST2_n22 SELECT SRC.key, COUNT(DISTINCT SUBSTR(SRC.value,5)) GROUP BY SRC.key;

SELECT DEST1_n87.* FROM DEST1_n87;
SELECT DEST2_n22.* FROM DEST2_n22;

