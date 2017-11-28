create table alter_partition_change_col1 (c1 string, c2 string) partitioned by 
	(p1 string comment 'Column p1', p2 string comment 'Column p2');
desc alter_partition_change_col1;
alter table alter_partition_change_col1 partition column (p1 string comment 'Changed comment for p1');
desc alter_partition_change_col1;

