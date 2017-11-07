set hive.fetch.task.conversion=more;

use default;
-- Test sort_array() UDF

DESCRIBE FUNCTION sort_array;
DESCRIBE FUNCTION EXTENDED sort_array;

create table t (a integer);
insert into t values (1);

-- Evaluate function against STRING valued keys
EXPLAIN
SELECT sort_array(array("b", "d", "c", "a")) FROM t;
