--! qt:dataset:src
set hive.mapred.mode=nonstrict;
set hive.fetch.task.conversion=more;

DESCRIBE FUNCTION reflect2;
DESCRIBE FUNCTION EXTENDED reflect2;

EXPLAIN EXTENDED
SELECT key,
       reflect2(key,   "byteValue"),
       reflect2(key,   "shortValue"),
       reflect2(key,   "intValue"),
       reflect2(key,   "longValue"),
       reflect2(key,   "floatValue"),
       reflect2(key,   "doubleValue"),
       reflect2(key,   "toString"),
       value,
       reflect2(value, "concat", "_concat"),
       reflect2(value, "contains", "86"),
       reflect2(value, "startsWith", "v"),
       reflect2(value, "endsWith", "6"),
       reflect2(value, "equals", "val_86"),
       reflect2(value, "equalsIgnoreCase", "VAL_86"),
       reflect2(value, "getBytes"),
       reflect2(value, "indexOf", "1"),
       reflect2(value, "lastIndexOf", "1"),
       reflect2(value, "replace", "val", "VALUE"),
       reflect2(value, "substring", 1),
       reflect2(value, "substring", 1, 5),
       reflect2(value, "toUpperCase"),
       reflect2(value, "trim"),
       ts,
       reflect2(ts, "getYear"),
       reflect2(ts, "getMonth"),
       reflect2(ts, "getDay"),
       reflect2(ts, "getHours"),
       reflect2(ts, "getMinutes"),
       reflect2(ts, "getSeconds"),
       reflect2(ts, "toEpochMilli")
FROM (select cast(key as int) key, value, cast('2013-02-15 19:41:20' as timestamp) ts from src) a LIMIT 5;


SELECT key,
       reflect2(key,   "byteValue"),
       reflect2(key,   "shortValue"),
       reflect2(key,   "intValue"),
       reflect2(key,   "longValue"),
       reflect2(key,   "floatValue"),
       reflect2(key,   "doubleValue"),
       reflect2(key,   "toString"),
       value,
       reflect2(value, "concat", "_concat"),
       reflect2(value, "contains", "86"),
       reflect2(value, "startsWith", "v"),
       reflect2(value, "endsWith", "6"),
       reflect2(value, "equals", "val_86"),
       reflect2(value, "equalsIgnoreCase", "VAL_86"),
       reflect2(value, "getBytes"),
       reflect2(value, "indexOf", "1"),
       reflect2(value, "lastIndexOf", "1"),
       reflect2(value, "replace", "val", "VALUE"),
       reflect2(value, "substring", 1),
       reflect2(value, "substring", 1, 5),
       reflect2(value, "toUpperCase"),
       reflect2(value, "trim"),
       ts,
       reflect2(ts, "getYear"),
       reflect2(ts, "getMonth"),
       reflect2(ts, "getDay"),
       reflect2(ts, "getHours"),
       reflect2(ts, "getMinutes"),
       reflect2(ts, "getSeconds"),
       reflect2(ts, "toEpochMilli")
FROM (select cast(key as int) key, value, cast('2013-02-15 19:41:20' as timestamp) ts from src) a LIMIT 5;
