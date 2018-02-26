SELECT 'Upgrading MetaStore schema from 2.3.0 to 3.0.0' AS Status from dual;

--@041-HIVE-16556.oracle.sql;
CREATE TABLE METASTORE_DB_PROPERTIES
(
  PROPERTY_KEY VARCHAR(255) NOT NULL,
  PROPERTY_VALUE VARCHAR(1000) NOT NULL,
  DESCRIPTION VARCHAR(1000)
);

ALTER TABLE METASTORE_DB_PROPERTIES ADD CONSTRAINT PROPERTY_KEY_PK PRIMARY KEY (PROPERTY_KEY);

--@042-HIVE-16575.oracle.sql;
CREATE INDEX CONSTRAINTS_CT_INDEX ON KEY_CONSTRAINTS(CONSTRAINT_TYPE);

--@043-HIVE-16922.oracle.sql;
UPDATE SERDE_PARAMS
SET PARAM_KEY='collection.delim'
WHERE PARAM_KEY='colelction.delim';

--@044-HIVE-16997.oracle.sql;
ALTER TABLE PART_COL_STATS ADD BIT_VECTOR BLOB NULL;
ALTER TABLE TAB_COL_STATS ADD BIT_VECTOR BLOB NULL;

--@045-HIVE-16886.oracle.sql;
INSERT INTO NOTIFICATION_SEQUENCE (NNI_ID, NEXT_EVENT_ID) SELECT 1,1 FROM DUAL WHERE NOT EXISTS ( SELECT NEXT_EVENT_ID FROM NOTIFICATION_SEQUENCE);

--@046-HIVE-17566.oracle.sql;
CREATE TABLE WM_RESOURCEPLAN
(
    RP_ID NUMBER NOT NULL,
    "NAME" VARCHAR2(128) NOT NULL,
    QUERY_PARALLELISM NUMBER(10),
    STATUS VARCHAR2(20) NOT NULL,
    DEFAULT_POOL_ID NUMBER
);

ALTER TABLE WM_RESOURCEPLAN ADD CONSTRAINT WM_RESOURCEPLAN_PK PRIMARY KEY (RP_ID);

CREATE UNIQUE INDEX UNIQUE_WM_RESOURCEPLAN ON WM_RESOURCEPLAN ("NAME");


CREATE TABLE WM_POOL
(
    POOL_ID NUMBER NOT NULL,
    RP_ID NUMBER NOT NULL,
    PATH VARCHAR2(1024) NOT NULL,
    ALLOC_FRACTION NUMBER,
    QUERY_PARALLELISM NUMBER(10),
    SCHEDULING_POLICY VARCHAR2(1024)
);

ALTER TABLE WM_POOL ADD CONSTRAINT WM_POOL_PK PRIMARY KEY (POOL_ID);

CREATE UNIQUE INDEX UNIQUE_WM_POOL ON WM_POOL (RP_ID, PATH);
ALTER TABLE WM_POOL ADD CONSTRAINT WM_POOL_FK1 FOREIGN KEY (RP_ID) REFERENCES WM_RESOURCEPLAN (RP_ID);


CREATE TABLE WM_TRIGGER
(
    TRIGGER_ID NUMBER NOT NULL,
    RP_ID NUMBER NOT NULL,
    "NAME" VARCHAR2(128) NOT NULL,
    TRIGGER_EXPRESSION VARCHAR2(1024),
    ACTION_EXPRESSION VARCHAR2(1024),
    IS_IN_UNMANAGED NUMBER(1) DEFAULT 0 NOT NULL CHECK (IS_IN_UNMANAGED IN (1,0))
);

ALTER TABLE WM_TRIGGER ADD CONSTRAINT WM_TRIGGER_PK PRIMARY KEY (TRIGGER_ID);

CREATE UNIQUE INDEX UNIQUE_WM_TRIGGER ON WM_TRIGGER (RP_ID, "NAME");

ALTER TABLE WM_TRIGGER ADD CONSTRAINT WM_TRIGGER_FK1 FOREIGN KEY (RP_ID) REFERENCES WM_RESOURCEPLAN (RP_ID);


CREATE TABLE WM_POOL_TO_TRIGGER
(
    POOL_ID NUMBER NOT NULL,
    TRIGGER_ID NUMBER NOT NULL
);

ALTER TABLE WM_POOL_TO_TRIGGER ADD CONSTRAINT WM_POOL_TO_TRIGGER_PK PRIMARY KEY (POOL_ID, TRIGGER_ID);

ALTER TABLE WM_POOL_TO_TRIGGER ADD CONSTRAINT WM_POOL_TO_TRIGGER_FK1 FOREIGN KEY (POOL_ID) REFERENCES WM_POOL (POOL_ID);

ALTER TABLE WM_POOL_TO_TRIGGER ADD CONSTRAINT WM_POOL_TO_TRIGGER_FK2 FOREIGN KEY (TRIGGER_ID) REFERENCES WM_TRIGGER (TRIGGER_ID);


CREATE TABLE WM_MAPPING
(
    MAPPING_ID NUMBER NOT NULL,
    RP_ID NUMBER NOT NULL,
    ENTITY_TYPE VARCHAR2(128) NOT NULL,
    ENTITY_NAME VARCHAR2(128) NOT NULL,
    POOL_ID NUMBER NOT NULL,
    ORDERING NUMBER(10)
);

ALTER TABLE WM_MAPPING ADD CONSTRAINT WM_MAPPING_PK PRIMARY KEY (MAPPING_ID);

CREATE UNIQUE INDEX UNIQUE_WM_MAPPING ON WM_MAPPING (RP_ID, ENTITY_TYPE, ENTITY_NAME);

ALTER TABLE WM_MAPPING ADD CONSTRAINT WM_MAPPING_FK1 FOREIGN KEY (RP_ID) REFERENCES WM_RESOURCEPLAN (RP_ID);

ALTER TABLE WM_MAPPING ADD CONSTRAINT WM_MAPPING_FK2 FOREIGN KEY (POOL_ID) REFERENCES WM_POOL (POOL_ID);

UPDATE VERSION SET SCHEMA_VERSION='3.0.0', VERSION_COMMENT='Hive release version 3.0.0' where VER_ID=1;
SELECT 'Finished upgrading MetaStore schema from 2.3.0 to 3.0.0' AS Status from dual;

-- 048-HIVE-14498
CREATE TABLE MV_CREATION_METADATA
(
    MV_CREATION_METADATA_ID NUMBER NOT NULL,
    DB_NAME VARCHAR2(128) NOT NULL,
    TBL_NAME VARCHAR2(256) NOT NULL,
    TXN_LIST CLOB NULL
);

ALTER TABLE MV_CREATION_METADATA ADD CONSTRAINT MV_CREATION_METADATA_PK PRIMARY KEY (MV_CREATION_METADATA_ID);

CREATE UNIQUE INDEX UNIQUE_TABLE ON MV_CREATION_METADATA ("DB_NAME", "TBL_NAME");

CREATE TABLE MV_TABLES_USED
(
    MV_CREATION_METADATA_ID NUMBER NOT NULL,
    TBL_ID NUMBER NOT NULL
);

ALTER TABLE MV_TABLES_USED ADD CONSTRAINT MV_TABLES_USED_FK1 FOREIGN KEY (MV_CREATION_METADATA_ID) REFERENCES MV_CREATION_METADATA (MV_CREATION_METADATA_ID);

ALTER TABLE MV_TABLES_USED ADD CONSTRAINT MV_TABLES_USED_FK2 FOREIGN KEY (TBL_ID) REFERENCES TBLS (TBL_ID);

ALTER TABLE COMPLETED_TXN_COMPONENTS ADD CTC_TIMESTAMP timestamp NULL;

UPDATE COMPLETED_TXN_COMPONENTS SET CTC_TIMESTAMP = CURRENT_TIMESTAMP;

ALTER TABLE COMPLETED_TXN_COMPONENTS MODIFY(CTC_TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

ALTER TABLE COMPLETED_TXN_COMPONENTS MODIFY(CTC_TIMESTAMP NOT NULL);

CREATE INDEX COMPLETED_TXN_COMPONENTS_INDEX ON COMPLETED_TXN_COMPONENTS (CTC_DATABASE, CTC_TABLE, CTC_PARTITION);

-- 049-HIVE-18489
UPDATE FUNC_RU
  SET RESOURCE_URI = 's3a' || SUBSTR(RESOURCE_URI, 4)
  WHERE RESOURCE_URI LIKE 's3n://%' ;

UPDATE SKEWED_COL_VALUE_LOC_MAP
  SET LOCATION = 's3a' || SUBSTR(LOCATION, 4)
  WHERE LOCATION LIKE 's3n://%' ;

UPDATE SDS
  SET LOCATION = 's3a' || SUBSTR(LOCATION, 4)
  WHERE LOCATION LIKE 's3n://%' ;

UPDATE DBS
  SET DB_LOCATION_URI = 's3a' || SUBSTR(DB_LOCATION_URI, 4)
  WHERE DB_LOCATION_URI LIKE 's3n://%' ;

-- HIVE-18192
CREATE TABLE TXN_TO_WRITE_ID (
  T2W_TXNID number(19) NOT NULL,
  T2W_DATABASE varchar(128) NOT NULL,
  T2W_TABLE varchar(256) NOT NULL,
  T2W_WRITEID number(19) NOT NULL
);

CREATE UNIQUE INDEX TXN_TO_WRITE_ID_IDX ON TXN_TO_WRITE_ID (T2W_DATABASE, T2W_TABLE, T2W_TXNID);

CREATE TABLE NEXT_WRITE_ID (
  NWI_DATABASE varchar(128) NOT NULL,
  NWI_TABLE varchar(256) NOT NULL,
  NWI_NEXT number(19) NOT NULL
);

CREATE UNIQUE INDEX NEXT_WRITE_ID_IDX ON NEXT_WRITE_ID (NWI_DATABASE, NWI_TABLE);

ALTER TABLE COMPACTION_QUEUE RENAME COLUMN CQ_HIGHEST_TXN_ID TO CQ_HIGHEST_WRITE_ID;

ALTER TABLE COMPLETED_COMPACTIONS RENAME COLUMN CC_HIGHEST_TXN_ID TO CC_HIGHEST_WRITE_ID;

-- Modify txn_components/completed_txn_components tables to add write id.
ALTER TABLE TXN_COMPONENTS ADD TC_WRITEID number(19);
ALTER TABLE COMPLETED_TXN_COMPONENTS ADD CTC_WRITEID number(19);
