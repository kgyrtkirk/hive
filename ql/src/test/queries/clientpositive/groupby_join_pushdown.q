--! qt:dataset:src
--! qt:dataset:alltypesorc
set hive.mapred.mode=nonstrict;
set hive.transpose.aggr.join=true;
 
SELECT f.key, g.key, count(g.key)
FROM src f JOIN src g ON(f.key = g.key)
GROUP BY f.key, g.key;

 
SELECT f.key, g.key
FROM src f JOIN src g ON(f.key = g.key)
GROUP BY f.key, g.key;

 
SELECT DISTINCT f.value, g.value
FROM src f JOIN src g ON(f.value = g.value);

 
SELECT f.key, g.key, COUNT(*)
FROM src f JOIN src g ON(f.key = g.key)
GROUP BY f.key, g.key;

 
SELECT  f.ctinyint, g.ctinyint, SUM(f.cbigint)              
FROM alltypesorc f JOIN alltypesorc g ON(f.cint = g.cint)
GROUP BY f.ctinyint, g.ctinyint ;

 
SELECT  f.cbigint, g.cbigint, MAX(f.cint)              
FROM alltypesorc f JOIN alltypesorc g ON(f.cbigint = g.cbigint)
GROUP BY f.cbigint, g.cbigint ;

 
SELECT  f.ctinyint, g.ctinyint, MIN(f.ctinyint)              
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

 
SELECT   MIN(f.cint)     
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

 
SELECT   count(f.ctinyint)              
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

 
SELECT   count(f.cint), f.ctinyint              
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

 
SELECT   sum(f.cint), f.ctinyint            
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

ALTER TABLE alltypesorc ADD CONSTRAINT pk_alltypesorc_1 PRIMARY KEY (ctinyint) DISABLE RELY;

-- COLUMNS ARE UNIQUE, OPTIMIZATION IS NOT TRIGGERED
 
SELECT sum(f.cint), f.ctinyint            
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;

ALTER TABLE alltypesorc DROP CONSTRAINT pk_alltypesorc_1;

ALTER TABLE alltypesorc ADD CONSTRAINT uk_alltypesorc_1 UNIQUE (ctinyint) DISABLE RELY;

-- COLUMNS ARE UNIQUE, OPTIMIZATION IS NOT TRIGGERED
 
SELECT sum(f.cint), f.ctinyint            
FROM alltypesorc f JOIN alltypesorc g ON(f.ctinyint = g.ctinyint)
GROUP BY f.ctinyint, g.ctinyint;
