set hive.compute.query.using.stats=false;

drop table if exists partcoltypenum;
create table partcoltypenum (key int, value string) partitioned by (tint tinyint, sint smallint, bint bigint);

-- add partition
alter table partcoltypenum add partition(tint=100Y, sint=20000S, bint=300000000000L);

-- describe partition
describe formatted partcoltypenum partition (tint=100, sint=20000S, bint='300000000000');

-- change partition file format
alter table partcoltypenum partition(tint=100, sint=20000S, bint='300000000000') set fileformat rcfile;
describe formatted partcoltypenum partition (tint=100Y, sint=20000S, bint=300000000000L);

-- change partition clusterby, sortby and bucket
alter table partcoltypenum partition(tint='100', sint=20000, bint=300000000000L) clustered by (key) sorted by (key desc) into 4 buckets;
describe formatted partcoltypenum partition (tint=100Y, sint=20000S, bint=300000000000L);

-- rename partition
alter table partcoltypenum partition(tint=100, sint=20000, bint=300000000000) rename to partition (tint=110Y, sint=22000S, bint=330000000000L);
describe formatted partcoltypenum partition (tint=110Y, sint=22000, bint='330000000000');

-- insert partition
insert into partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) select key, value from src limit 10;
insert into partcoltypenum partition (tint=110, sint=22000, bint=330000000000) select key, value from src limit 20;

-- analyze partition statistics and columns statistics
analyze table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) compute statistics;
-- XXX should see stats here
describe extended partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);

analyze table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) compute statistics for columns;
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) key;
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) value;

-- change table column type for partition
alter table partcoltypenum change key key decimal(10,0);
alter table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) change key key decimal(10,0);
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);

