--! qt:dataset:src
--! qt:dataset:src1

set hive.strict.checks.cartesian.product=false;

-- Function with multiple inputs on one side
EXPLAIN
SELECT *
FROM src1 JOIN src
ON (src1.key= 100 and src.key=100)
LIMIT 10;


-- Function with multiple inputs on one side
EXPLAIN
SELECT *
FROM src1 JOIN src
ON ((src1.key,src.key) IN ((100,100),(101,101),(102,102)))
LIMIT 10;

SELECT *
FROM src1 JOIN src
ON ((src1.key,src.key) IN ((100,100),(101,101),(102,102)))
LIMIT 10;


EXPLAIN
 SELECT *
 FROM src1 JOIN src
 ON ((src1.key,src.key) IN ((100,100),(101,101),(102,102)))
 LIMIT 10

