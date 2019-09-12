--! qt:dataset:alltypesorc
set hive.vectorized.execution.enabled=false;
set hive.fetch.task.conversion=none;

set hive.cbo.enable=false;

explain
select count (distinct cint) from alltypesorc where cstring1 and cint;

explain
select count (distinct cint) from alltypesorc where cstring1;

select count (distinct cint) from alltypesorc where cstring1;

explain
select count (distinct cint) from alltypesorc where cint;

select count (distinct cint) from alltypesorc where cint;

explain
select count (distinct cint) from alltypesorc where cfloat;

select count (distinct cint) from alltypesorc where cfloat;

explain
select count (distinct cint) from alltypesorc where ctimestamp1;

select count (distinct cint) from alltypesorc where ctimestamp1;

set hive.cbo.enable=true;

explain
select count (distinct cint) from alltypesorc where cstring1 and cint;

explain
select count (distinct cint) from alltypesorc where cstring1;

select count (distinct cint) from alltypesorc where cstring1;

explain
select count (distinct cint) from alltypesorc where cint;

select count (distinct cint) from alltypesorc where cint;

explain
select count (distinct cint) from alltypesorc where cfloat;

select count (distinct cint) from alltypesorc where cfloat;

explain
select count (distinct cint) from alltypesorc where ctimestamp1;

select count (distinct cint) from alltypesorc where ctimestamp1;
