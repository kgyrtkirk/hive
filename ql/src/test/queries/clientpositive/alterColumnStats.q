set hive.mapred.mode=nonstrict;

drop table p;

set hive.stats.column.autogather=false;

CREATE TABLE p(insert_num int, c1 tinyint, c2 smallint);

desc formatted p;

insert into p values (1,22,333);

desc formatted p;

alter table p replace columns (insert_num int, c1 STRING, c2 STRING);

set hive.stats.column.autogather=true;

explain insert into p values (1,'2','3');

insert into p values (1,'2','3');

desc formatted p;

desc formatted p c1;

desc formatted p c2;


drop table p;

set hive.stats.column.autogather=true;

CREATE TABLE p(insert_num int, c1 tinyint, c2 smallint);

desc formatted p;

explain insert into p values (1,22,333);

insert into p values (1,22,333);

desc formatted p;

desc formatted p c1;

alter table p replace columns (insert_num int, c1 STRING, c2 STRING, c3 STRING);

desc formatted p;

explain insert into p values (1,'2','3','4');

insert into p values (1,'2','3','4');

desc formatted p;

desc formatted p insert_num;

desc formatted p c2;

desc formatted p c3;
