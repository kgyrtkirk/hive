--! qt:dataset:druid_table_alltypesorc
SET hive.ctas.external.tables=true;

SET hive.vectorized.execution.enabled=true;

 -- MATH AND STRING functions

explain
SELECT `__time`
FROM druid_table_alltypesorc
WHERE (`__time` BETWEEN '1968-01-01 00:00:00' AND '1970-01-01 00:00:00')
    OR (`__time` BETWEEN '1968-02-01 00:00:00' AND '1970-04-01 00:00:00') ORDER BY `__time` ASC LIMIT 10;

SELECT `__time`
FROM druid_table_alltypesorc
WHERE (`__time` BETWEEN '1968-01-01 00:00:00' AND '1970-01-01 00:00:00')
    OR (`__time` BETWEEN '1968-02-01 00:00:00' AND '1970-04-01 00:00:00') ORDER BY `__time` ASC LIMIT 10;


explain
SELECT `__time`
FROM druid_table_alltypesorc
WHERE ('1968-01-01 00:00:00' <= `__time` AND `__time` <= '1970-01-01 00:00:00')
    OR ('1968-02-01 00:00:00' <= `__time` AND `__time` <= '1970-04-01 00:00:00') ORDER BY `__time` ASC LIMIT 10;

SELECT `__time`
FROM druid_table_alltypesorc
WHERE ('1968-01-01 00:00:00' <= `__time` AND `__time` <= '1970-01-01 00:00:00')
    OR ('1968-02-01 00:00:00' <= `__time` AND `__time` <= '1970-04-01 00:00:00') ORDER BY `__time` ASC LIMIT 10;

