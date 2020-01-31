set hive.optimize.point.lookup=false;
drop materialized view if exists mv1;
drop materialized view if exists mv2;

drop table if exists emps;
drop table if exists depts;

drop materialized view if exists mv1_n2;
drop materialized view if exists mv1_n0;

drop table if exists emps_n3;
drop table if exists depts_n2;
drop table if exists dependents_n2;
drop table if exists locations_n2;


drop table if exists emps_n0;
drop table if exists depts_n0;
drop table if exists dependents_n0;
drop table if exists locations_n0;


set hive.support.concurrency=true;
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;
set hive.strict.checks.cartesian.product=false;
set hive.stats.fetch.column.stats=true;
set hive.materializedview.rewriting=true;

-- create some tables
CREATE TABLE emps (
  empid INT,
  deptno INT,
  name VARCHAR(256),
  salary FLOAT,
  hire_date int)
STORED AS ORC
TBLPROPERTIES ('transactional'='true');
 
CREATE TABLE depts (
  deptno INT,
  deptname VARCHAR(256),
  locationid INT)
STORED AS ORC
TBLPROPERTIES ('transactional'='true');

-- load data
insert into emps values (100, 10, 'Bill', 10000, 1000), (200, 20, 'Eric', 8000, 500),
  (150, 10, 'Sebastian', 7000, null), (110, 10, 'Theodore', 10000, 250), (120, 10, 'Bill', 10000, 250)
  ;
insert into depts values (10, 'Sales', 10), (30, 'Marketing', null), (20, 'HR', 20);


alter table emps add constraint pk1 primary key (empid) disable novalidate rely;
alter table depts add constraint pk2 primary key (deptno) disable novalidate rely;
alter table emps add constraint fk1 foreign key (deptno) references depts(deptno) disable novalidate rely;

-- create mv
CREATE MATERIALIZED VIEW mv1
AS
SELECT empid, deptname, hire_date
FROM emps JOIN depts
  using (deptno)
  -- ON (emps.deptno = depts.deptno)
WHERE hire_date >= 500;


-- expected to see that materialzed view is being used; however it doesnt:
explain
SELECT empid, deptname
FROM emps
JOIN depts
  using (deptno)
WHERE hire_date >= 600
    AND hire_date <= 1200 
;


-- now we can see that the materialzed view is being used:
explain
SELECT empid, deptname
FROM emps
JOIN depts
  using (deptno)
WHERE hire_date >= 600
--    AND hire_date <= 1200  
;



