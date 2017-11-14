
create table tx(a int,f string);
insert into tx values (1,'non_existent_file');

select a,assert_true(a<0) from tx group by a,f;
-- select a,in_file('somestring',f) from tx group by a,f;
