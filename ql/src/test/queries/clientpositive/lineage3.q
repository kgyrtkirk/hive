--! qt:dataset:src1
--! qt:dataset:alltypesorc
SET hive.vectorized.execution.enabled=false;
set hive.mapred.mode=nonstrict;
set hive.exec.post.hooks=org.apache.hadoop.hive.ql.hooks.LineageLogger;
set hive.metastore.disallow.incompatible.col.type.changes=false;

-- xxx

select x.ctinyint, x.cint, c.cbigint-100, c.cstring1
from alltypesorc c
join (
   select a.ctinyint ctinyint, b.cint cint
   from (select * from alltypesorc a where cboolean1=false) a
   join alltypesorc b on (a.cint = b.cbigint - 224870380)
 ) x on (x.cint = c.cint)
where x.ctinyint > 10
and x.cint < 4.5
and x.ctinyint + length(c.cstring2) < 1000;

