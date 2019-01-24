set hive.fetch.task.conversion=more;
SET hive.ctas.external.tables=true;
SET hive.external.table.purge.default = true;

drop table if exists druid_t1;

create external table druid_t1(`__time` timestamp with local time zone, n string, v integer)
STORED BY 'org.apache.hadoop.hive.druid.DruidStorageHandler'
TBLPROPERTIES ("druid.segment.granularity" = "HOUR");


insert into druid_t1 values
	(cast('2010-01-01 00:00:01 America/Los_Angeles' as timestamp with local time zone), 'x1', 1),
	(cast('2010-02-01 00:00:01 America/Los_Angeles' as timestamp with local time zone), 'v2', 2),
	(cast('2010-02-01 23:00:01 America/Los_Angeles' as timestamp with local time zone), 'v3', 3),
	(cast('2010-02-02 23:59:59 America/Los_Angeles' as timestamp with local time zone), 'v4', 4),
	(cast('2010-02-03 00:00:01 America/Los_Angeles' as timestamp with local time zone), 'v5', 5),
	(cast('2010-02-03 00:59:59 America/Los_Angeles' as timestamp with local time zone), 'v5', 6),
	(cast('2010-02-03 01:00:00 America/Los_Angeles' as timestamp with local time zone), 'x5', 7),
	(cast('2010-02-03 01:00:01 America/Los_Angeles' as timestamp with local time zone), 'x5', 8),
	(cast('2010-03-01 00:00:01 America/Los_Angeles' as timestamp with local time zone), 'x6', 9);



EXPLAIN
SELECT *
FROM druid_t1
WHERE floor_hour(`__time`)
    BETWEEN CAST('2010-02-01 00:00:00 America/Los_Angeles' AS TIMESTAMP WITH LOCAL TIME ZONE)
        AND CAST('2010-02-03 00:00:00 America/Los_Angeles' AS TIMESTAMP WITH LOCAL TIME ZONE)
order by `__time`;

SELECT *
FROM druid_t1
WHERE floor_hour(`__time`)
    BETWEEN CAST('2010-02-01 00:00:00 America/Los_Angeles' AS TIMESTAMP WITH LOCAL TIME ZONE)
        AND CAST('2010-02-03 00:00:00 America/Los_Angeles' AS TIMESTAMP WITH LOCAL TIME ZONE)
order by `__time`;
