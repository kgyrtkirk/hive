set hive.exec.dynamic.partition.mode=nonstrict;

create table source_table(name string,age int,height int);
insert into source_table values('vaibhav',1,1),('abjay',2,2);
create external table external_dynamic_partitions(name string,height int) partitioned by (age int);
insert into table external_dynamic_partitions partition(age) select * from source_table;
analyze table external_dynamic_partitions compute statistics for columns;
alter table external_dynamic_partitions rename to xyz;
alter table xyz drop partition(age=1);
