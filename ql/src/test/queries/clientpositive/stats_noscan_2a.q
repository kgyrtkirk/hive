dfs -cp ${system:hive.root}/data/files/ext_test ${system:test.tmp.dir}/analyze_external;
dfs ${system:test.dfs.mkdir} ${system:test.tmp.dir}/texternal/2008-01-01;

create external table t0 (key string,val string) location 'pfile://${system:test.tmp.dir}/texternal/2008-01-01';
insert into t0 values ('a','b'),('a','b'), ('a','b');
drop table t0;

-- create external table
CREATE external TABLE anaylyze_external (key string, val string) partitioned by (insertdate string) LOCATION "pfile://${system:test.tmp.dir}/texternal"; 
describe formatted anaylyze_external;
ALTER TABLE anaylyze_external ADD PARTITION (insertdate='2008-01-01') location 'pfile://${system:test.tmp.dir}/texternal/2008-01-01';
describe formatted anaylyze_external PARTITION (insertdate='2008-01-01');
explain select count(*) from anaylyze_external where insertdate='2008-01-01';
select count(*) from anaylyze_external where insertdate='2008-01-01';
select count(*) from anaylyze_external;

-- analyze
analyze table anaylyze_external PARTITION (insertdate='2008-01-01') compute statistics noscan;
describe formatted anaylyze_external PARTITION (insertdate='2008-01-01');
analyze table anaylyze_external PARTITION (insertdate='2008-01-01') compute statistics;
describe formatted anaylyze_external PARTITION (insertdate='2008-01-01');
dfs -rmr ${system:test.tmp.dir}/texternal;
drop table anaylyze_external;



