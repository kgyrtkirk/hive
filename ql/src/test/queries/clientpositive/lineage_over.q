SET hive.vectorized.execution.enabled=false;
set hive.mapred.mode=nonstrict;

CREATE TABLE source_tbl(col_001 INT, col_002 INT, col_003 INT);

CREATE TABLE target_tbl(col_001 INT, col_002 INT, col_003 INT);

INSERT INTO target_tbl SELECT v1.col_001, v1.col_002, v1.col_003 FROM (SELECT col_001, col_002, col_003, ROW_NUMBER() OVER() AS r_num FROM source_tbl) v1;

--INSERT INTO target_tbl SELECT col_001, col_002, col_003, 1 as rnum FROM source_tbl;

select '^^^expectation of this test is to not see unrelated columns (like col_002 as a lineatge dependency for col_001)';
