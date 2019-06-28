--! qt:dataset:src

set hive.explain.user=false;
set hive.compute.query.using.stats=false;
set hive.optimize.metadataonly=false;
set hive.fetch.task.conversion=none;

set hive.cbo.enable=false;
explain extended
select count(1) from src group by true;
