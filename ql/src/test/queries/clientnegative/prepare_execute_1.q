--! qt:dataset:src
prepare query1 from select count(*) from src where key > ? and value < ? group by ?;
execute query1 using 1,100,1;
