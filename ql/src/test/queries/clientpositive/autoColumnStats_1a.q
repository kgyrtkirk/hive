set hive.strict.checks.bucketing=false;

set hive.stats.column.autogather=true;
set hive.stats.fetch.column.stats=true;
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.auto.convert.join=true;
set hive.join.emit.interval=2;
set hive.auto.convert.join.noconditionaltask=true;
set hive.auto.convert.join.noconditionaltask.size=10000;
set hive.auto.convert.sortmerge.join.bigtable.selection.policy = org.apache.hadoop.hive.ql.optimizer.TableSizeBasedBigTableSelectorForAutoSMJ;
set hive.optimize.bucketingsorting=false;


create table t_input (c1 int);
create table c (c1 int);

insert into t_input values (1),(2),(3);


create table a like t_input;
create table b like t_input;

insert into c select * from t_input;

describe formatted a;
describe formatted b;
describe formatted c;

from t_input
insert overwrite table a select *
insert into table b select *;

describe formatted a;
describe formatted b;
