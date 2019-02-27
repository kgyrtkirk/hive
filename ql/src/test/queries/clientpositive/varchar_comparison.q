--! qt:dataset:src

-- trailing space is significant for varchar
select
  cast('abc ' as varchar(10)) <> cast('abc' as varchar(10))
from src limit 1;
