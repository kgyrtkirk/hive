set hive.stats.column.autogather=false;
set hive.stats.fetch.column.stats=true;
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.auto.convert.join=true;
set hive.join.emit.interval=2;
set hive.auto.convert.join.noconditionaltask=true;
set hive.auto.convert.join.noconditionaltask.size=10000;
set hive.auto.convert.sortmerge.join.bigtable.selection.policy = org.apache.hadoop.hive.ql.optimizer.TableSizeBasedBigTableSelectorForAutoSMJ;
set hive.optimize.bucketingsorting=false;

set hive.stats.column.autogather=true;

create table if not exists nzhang_part14 (key string, value string)
  partitioned by (ds string, hr string);


desc formatted nzhang_part14;
analyze table nzhang_part14 partition (ds,hr) compute statistics for columns;
desc formatted nzhang_part14;

insert into table nzhang_part14 partition(ds, hr)
select key, value, ds, hr from (
  select '1' as ds,'3' as hr,'k' as key, 'v' as value
  union all
  select '2' as ds,'1' as hr,'k' as key, 'v' as value
) T;

desc formatted nzhang_part14 partition(ds='1', hr='3');

desc formatted nzhang_part14 partition(ds='2', hr='1');

desc formatted nzhang_part14;

