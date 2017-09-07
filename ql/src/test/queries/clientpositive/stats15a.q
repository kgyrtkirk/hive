set datanucleus.cache.collections=false;

create table my_src (key string, value string);
insert into my_src values ('1','2'),('3','4');

create table my_srcpart (key string, value string) partitioned by (ds string,hr string);

create table stats_part like my_srcpart;

insert overwrite table stats_part partition (ds='2010-04-08', hr = '11') select key, value from my_src;
insert overwrite table stats_part partition (ds='2010-04-08', hr = '12') select key, value from my_src;

analyze table stats_part partition(ds='2010-04-08', hr='11') compute statistics;
analyze table stats_part partition(ds='2010-04-08', hr='12') compute statistics;

insert overwrite table stats_part partition (ds='2010-04-08', hr = '13') select key, value from my_src;

desc formatted stats_part;
desc formatted stats_part partition (ds='2010-04-08', hr = '11');
desc formatted stats_part partition (ds='2010-04-08', hr = '12');

analyze table stats_part partition(ds, hr) compute statistics;
desc formatted stats_part;

drop table stats_part;
