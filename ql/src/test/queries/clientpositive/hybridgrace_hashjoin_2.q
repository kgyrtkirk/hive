--! qt:dataset:srcpart
--! qt:dataset:src1
--! qt:dataset:src
set hive.mapred.mode=nonstrict;
set hive.explain.user=false;
-- Hybrid Grace Hash Join
SELECT 'Test n-way join';

--set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask=true;
set hive.auto.convert.join.noconditionaltask.size=10000000;
set hive.cbo.enable=false;


SELECT '4-way mapjoin (1 big table, 3 small tables)';

SELECT 'hash-off';
set hive.mapjoin.hybridgrace.hashtable=false;

EXPLAIN
SELECT COUNT(*)
FROM src1 x JOIN srcpart z ON (x.key = z.key)
JOIN srcpart w ON (x.key = w.key)
JOIN src y ON (y.key = x.key);

SELECT COUNT(*)
FROM src1 x JOIN srcpart z ON (x.key = z.key)
JOIN srcpart w ON (x.key = w.key)
JOIN src y ON (y.key = x.key);

SELECT x.key
FROM src1 x JOIN srcpart z ON (x.key = z.key)
JOIN srcpart w ON (x.key = w.key)
JOIN src y ON (y.key = x.key)
limit 1;

SELECT 'hash-on';
set hive.mapjoin.hybridgrace.hashtable=true;

EXPLAIN
SELECT COUNT(*)
FROM src1 x JOIN srcpart z ON (x.key = z.key)
JOIN srcpart w ON (x.key = w.key)
JOIN src y ON (y.key = x.key);

SELECT COUNT(*)
FROM src1 x JOIN srcpart z ON (x.key = z.key)
JOIN srcpart w ON (x.key = w.key)
JOIN src y ON (y.key = x.key);

