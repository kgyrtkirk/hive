--! qt:disabled:flaky
--! qt:dataset:srcpart
set hive.strict.checks.bucketing=false; 

set hive.mapred.mode=strict;

EXPLAIN
SELECT count(1) FROM srcPART;

SELECT count(1) FROM srcPART;
