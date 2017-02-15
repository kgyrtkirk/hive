-- SORT_QUERY_RESULTS

CREATE TABLE broken (c int) PARTITIONED BY (b1 BOOLEAN, s STRING, b2 BOOLEAN, i INT);

-- Insert a few variants of 'false' partition-key values.;
INSERT INTO TABLE broken PARTITION(b1=false,s='a',b2=false,i=0) VALUES(1);
INSERT INTO TABLE broken PARTITION(b1=FALSE,s='a',b2=false,i=0) VALUES(3);
INSERT INTO TABLE broken PARTITION(b1=false,s='a',b2=False,i=0) VALUES(5);
INSERT INTO TABLE broken PARTITION(b1=false,s='a',b2=FalsE,i=0) VALUES(7);

-- Insert a few variants of 'true' partition-key values.;
INSERT INTO TABLE broken PARTITION(b1=true,s='a',b2=true,i=0) VALUES(2);
INSERT INTO TABLE broken PARTITION(b1=TRUE,s='a',b2=true,i=0) VALUES(4);
INSERT INTO TABLE broken PARTITION(b1=true,s='a',b2=True,i=0) VALUES(6);
INSERT INTO TABLE broken PARTITION(b1=true,s='a',b2=TruE,i=0) VALUES(8);

-- Insert a few variants of mixed 'true'/'false' partition-key values.;
INSERT INTO TABLE broken PARTITION(b1=false,s='a',b2=true,i=0) VALUES(100);
INSERT INTO TABLE broken PARTITION(b1=FALSE,s='a',b2=TRUE,i=0) VALUES(1000);
INSERT INTO TABLE broken PARTITION(b1=true,s='a',b2=false,i=0) VALUES(10000);
INSERT INTO TABLE broken PARTITION(b1=tRUe,s='a',b2=fALSe,i=0) VALUES(100000);

select count(*) from broken;
select * from broken;

ALTER TABLE broken DROP PARTITION(b1=true,s='a',b2=true,i=0);

select count(*) from broken;
select * from broken;
