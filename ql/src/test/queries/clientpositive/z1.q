set hive.compute.query.using.stats=false;

drop table if exists partcoltypenum;
create table partcoltypenum (key int, value string) partitioned by (tint tinyint, sint smallint, bint bigint);

-- insert partition
insert into partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) 
	(key,value) values (1,'a');

-- analyze partition statistics and columns statistics
analyze table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) compute statistics;
-- XXX should see stats here
describe extended partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);

analyze table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) compute statistics for columns;
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) key;
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) value;

-- change table column type for partition
alter table partcoltypenum change key key decimal(10,0);
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);
alter table partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L) change key key decimal(10,0);
describe formatted partcoltypenum partition (tint=110Y, sint=22000S, bint=330000000000L);

