--dfs -mkdir ${system:test.tmp.dir}/;
-- dfs -cp ${system:hive.root}/data/files/ext_test ${system:test.tmp.dir}/analyze_external;

create table texternal(key string, val string) partitioned by (insertdate string) location 'pfile://${system:test.tmp.dir}/texternal0';
-- dfs ${system:test.dfs.mkdir} ${system:test.tmp.dir}/texternal;
alter table texternal add partition (insertdate='2008-01-01') location 'pfile://${system:test.tmp.dir}/texternal/2008-01-01';

