--! qt:dataset:part
-- SORT_QUERY_RESULTS

--empty table
create table tempty(i int, j int);

CREATE TABLE part_null_n0 as select * from part;
insert into part_null_n0 values(NULL,NULL,NULL,NULL,NULL, NULL, NULL,NULL,NULL);

-- test all six comparison operators
explain cbo select count(*) from part where p_partkey = ANY (select p_partkey from part);
select count(*) from part where p_partkey = ANY (select p_partkey from part);

--explain cbo select count(*) from part where p_partkey <> ANY (select p_partkey from part);
--select count(*) from part where p_partkey <> ANY (select p_partkey from part);

explain cbo select count(*) from part where p_partkey > ANY (select p_partkey from part);
select count(*) from part where p_partkey > ANY (select p_partkey from part);

explain cbo select count(*) from part where p_partkey < ANY (select p_partkey from part);
select count(*) from part where p_partkey < ANY (select p_partkey from part);

explain cbo select count(*) from part where p_partkey >= ANY (select p_partkey from part);
select count(*) from part where p_partkey >= ANY (select p_partkey from part);

explain cbo select count(*) from part where p_partkey <= ANY (select p_partkey from part);
select count(*) from part where p_partkey <= ANY (select p_partkey from part);

-- SOME is same as ANY
explain cbo select count(*) from part where p_partkey = SOME(select min(p_partkey) from part);
select count(*) from part where p_partkey = SOME(select min(p_partkey) from part);

-- ANY with aggregate in subquery
explain cbo select count(*) from part where p_size < ANY (select max(p_size) from part group by p_partkey);
select count(*) from part where p_size < ANY (select max(p_size) from part group by p_partkey);

select count(*) from part where p_size < ANY (select max(null) from part group by p_partkey);

--empty row produces false with ANY
select count(*) from part where p_partkey = ANY(select i from tempty);

-- true + null, should produce results 
select count(*) from part where p_partkey = ANY (select p_partkey from part_null_n0);

-- false + null -> null
select count(*) from part where (p_size= ANY (select p_partkey from part_null_n0)) is null;

-- all null -> null
select count(*) from part where (p_partkey = ANY (select p_partkey from part_null_n0 where p_partkey is null)) is null;

-- false, should produce zero result
select count(*) from part where p_partkey > ANY (select max(p_partkey) from part_null_n0);

-- ANY in having
explain cbo select count(*) from part having count(*) > ANY (select count(*) from part group by p_partkey);
select count(*) from part having count(*) > ANY (select count(*) from part group by p_partkey);

-- multiple
explain cbo select count(*) from part where p_partkey >= ANY (select p_partkey from part) 
	AND p_size = ANY (select p_size from part group by p_size);
select count(*) from part where p_partkey >= ANY (select p_partkey from part) 
	AND p_size = ANY (select p_size from part group by p_size);

--nested
explain cbo select count(*) from part where p_partkey 
	>= ANY (select p_partkey from part where p_size >= ANY(select p_size from part_null_n0 group by p_size)) ;
select count(*) from part where p_partkey 
	>= ANY (select p_partkey from part where p_size >= ANY(select p_size from part_null_n0 group by p_size)) ;

-- subquery in SELECT
select p_partkey, (p_partkey > ANY (select p_partkey from part)) from part;

select p_partkey, (p_partkey > ANY (select p_partkey from part_null_n0)) from part_null_n0;

select p_partkey, (p_partkey > ANY (select null from part_null_n0)) from part_null_n0;

select p_partkey, (p_partkey > ANY (select i from tempty)) from part_null_n0;

DROP TABLE part_null_n0;
DROP TABLE tempty;
