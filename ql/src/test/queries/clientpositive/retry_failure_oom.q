
set hive.llap.skip.compile.udf.check=true;
-- set hive.llap.execution.mode=auto;

compile `import org.apache.hadoop.hive.ql.exec.UDF \;

public class Pyth extends UDF {
  public double evaluate(double a, double b){
    throw new OutOfMemoryError("Fake-OOM from retry_failure_oom.q")\;
  }
} ` AS GROOVY NAMED Pyth.groovy;
CREATE TEMPORARY FUNCTION Pyth as 'Pyth';

create table t (a integer,b integer);
insert into t values (1,1),(2,2);

SELECT Pyth(a,4) from t group by a,b;

DROP TEMPORARY FUNCTION Pyth;

