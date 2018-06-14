drop table if exists testmaster1;
drop table if exists testfeed1;

create table testmaster1(key1 int, key2 int, attr1 int,attr2 int,attr4 int) partitioned by (attr5 int) stored as orc;
create table testfeed1(key1 int, key2 int, attr1 int,attr2 int,attr4 int,attr5 int) stored as orc;
insert into table testfeed1 values(1,2,5,6,7,1);
insert into table testmaster1 partition(attr5=1) values(1,2,5,6,8);

set hive.explain.user=false;
explain select count(1) from testfeed1 lf
 join testmaster1 rt on lf.key1=rt.key1 and lf.key2=rt.key2 and lf.attr5=rt.attr5
 group by lf.key1,lf.key2,lf.attr5;
set hive.auto.convert.join=false;
explain select count(1) from testfeed1 lf
 join testmaster1 rt on lf.key1=rt.key1 and lf.key2 = rt.key2 and lf.attr5 =rt.attr5
 group by lf.key1,lf.key2,lf.attr5;
select count(1) from testfeed1 lf
 join testmaster1 rt on lf.key1=rt.key1 and lf.key2 =rt.key2 and lf.attr5 = rt.attr5
 group by lf.key1,lf.key2,lf.attr5;


