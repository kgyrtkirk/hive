--! qt:dataset:src
set hive.fetch.task.conversion=more;

DESCRIBE FUNCTION nvl;
DESCRIBE FUNCTION EXTENDED nvl;

create table t (a integer, b integer);
insert into t values (null,1);

EXPLAIN
SELECT NVL( a , b ) AS COL1,
       NVL( NULL, a ) AS COL2
FROM t;

SELECT NVL( a , b ) AS COL1,
       NVL( NULL, a ) AS COL2
FROM t;

