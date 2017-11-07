set hive.fetch.task.conversion=more;

use default;
-- Test sort_array() UDF

DESCRIBE FUNCTION sort_array;
DESCRIBE FUNCTION EXTENDED sort_array;

create table t (a integer,x array<string>);
insert into t 
select 1,array('1','2');

-- Evaluate function against STRING valued keys
EXPLAIN
SELECT sort_array(array("b", "d", "c", "a")),array("1","2") FROM t;

EXPLAIN
SELECT sort_array(array(a,3,0)),array("1","2") FROM t;

EXPLAIN
SELECT x FROM t;
