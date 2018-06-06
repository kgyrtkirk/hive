set hive.mapjoin.hybridgrace.hashtable=false;
SET hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.PostExecTezSummaryPrinter;

create table t_bad (id integer,str integer)
        row format delimited FIELDS TERMINATED BY '\u0009'
        stored as textfile
        TBLPROPERTIES('transactional'='false');

create table t_good (id integer,str integer)
        row format delimited FIELDS TERMINATED BY '\u0009'
        stored as textfile
        TBLPROPERTIES('transactional'='false');


LOAD DATA LOCAL INPATH '../../data/files/I100M.data.txt' INTO TABLE t_bad;
LOAD DATA LOCAL INPATH '../../data/files/I100M.data.txt' INTO TABLE t_good;

analyze table t_bad compute statistics for columns;
analyze table t_good compute statistics for columns;

-- LOAD DATA LOCAL INPATH '../../data/files/I100M.data.txt' INTO TABLE t_bad;

set hive.auto.convert.join=true;
set hive.auto.convert.join.noconditionaltask = true;
set hive.auto.convert.join.noconditionaltask.size = 2000000000;


explain
select sum(length(l.str||l.id||r.id||r.str)) from t_bad l join t_good r on (l.id=r.id);

select sum(length(l.str||l.id||r.id||r.str)),count(*) from t_bad l join t_good r on (l.id=r.id);

