
create table tx(a int,f string);
insert into tx values (1,'non_existent_file');


set zzz=1;

set hive.reexecution.overlay.zzz=2;

select assert_true(${hiveconf:zzz} > 1);

-- select 
-- select a,assert_true(a<0) from tx group by a,f;
-- select a,in_file('somestring',f) from tx group by a,f;
