CREATE TEMPORARY TABLE empty_tab(
    a int,
    b double,
    c string,
    d boolean,
    e binary);

analyze table empty_tab compute statistics for columns a,b,c,d,e;

desc formatted empty_tab a;

insert into table empty_tab values (1,2,'3',4,null);
desc formatted empty_tab a;

insert into table empty_tab values (1,2,'3',4,null);
desc formatted empty_tab a;

