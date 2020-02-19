SELECT 'Upgrading MetaStore schema from 3.2.0 to 4.0.0' AS MESSAGE;

-- HIVE-19416
ALTER TABLE TBLS ADD WRITE_ID bigint DEFAULT 0;
ALTER TABLE PARTITIONS ADD WRITE_ID bigint DEFAULT 0;


-- HIVE-20793
ALTER TABLE `WM_RESOURCEPLAN` ADD `NS` varchar(128);
UPDATE `WM_RESOURCEPLAN` SET `NS` = 'default' WHERE `NS` IS NULL;
ALTER TABLE `WM_RESOURCEPLAN` DROP KEY `UNIQUE_WM_RESOURCEPLAN`;
ALTER TABLE `WM_RESOURCEPLAN` ADD UNIQUE KEY `UNIQUE_WM_RESOURCEPLAN` (`NAME`, `NS`);

-- HIVE-21063
CREATE UNIQUE INDEX `NOTIFICATION_LOG_EVENT_ID` ON NOTIFICATION_LOG (`EVENT_ID`) USING BTREE;

-- HIVE-21337
ALTER TABLE COLUMNS_V2 MODIFY COMMENT varchar(4000) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL;

-- HIVE-22046 (DEFAULT HIVE)
ALTER TABLE TAB_COL_STATS ADD ENGINE varchar(128);
UPDATE `TAB_COL_STATS` SET `ENGINE` = 'hive' WHERE `ENGINE` IS NULL;
ALTER TABLE PART_COL_STATS ADD ENGINE varchar(128);
UPDATE `PART_COL_STATS` SET `ENGINE` = 'hive' WHERE `ENGINE` IS NULL;

CREATE TABLE SCHEDULED_QUERIES (
	SCHEDULED_QUERY_ID BIGINT NOT NULL,
	CLUSTER_NAMESPACE VARCHAR(256),
	ENABLED BOOLEAN NOT NULL,
	NEXT_EXECUTION INTEGER,
	QUERY VARCHAR(4000),
	SCHEDULE VARCHAR(256),
	SCHEDULE_NAME VARCHAR(256),
	`USER` VARCHAR(256),
	CONSTRAINT SCHEDULED_QUERIES_PK PRIMARY KEY (SCHEDULED_QUERY_ID)
);

CREATE TABLE SCHEDULED_EXECUTIONS (
	SCHEDULED_EXECUTION_ID BIGINT NOT NULL,
	END_TIME INTEGER,
	ERROR_MESSAGE VARCHAR(2000),
	EXECUTOR_QUERY_ID VARCHAR(256),
	LAST_UPDATE_TIME INTEGER,
	SCHEDULED_QUERY_ID BIGINT,
	START_TIME INTEGER,
	STATE VARCHAR(256),
	CONSTRAINT SCHEDULED_EXECUTIONS_PK PRIMARY KEY (SCHEDULED_EXECUTION_ID),
	CONSTRAINT SCHEDULED_EXECUTIONS_SCHQ_FK FOREIGN KEY (SCHEDULED_QUERY_ID) REFERENCES SCHEDULED_QUERIES(SCHEDULED_QUERY_ID) ON DELETE CASCADE
);

CREATE INDEX IDX_SCHEDULED_EXECUTIONS_LAST_UPDATE_TIME ON SCHEDULED_EXECUTIONS (LAST_UPDATE_TIME);
CREATE INDEX IDX_SCHEDULED_EXECUTIONS_SCHEDULED_QUERY_ID ON SCHEDULED_EXECUTIONS (SCHEDULED_QUERY_ID);
CREATE UNIQUE INDEX UNIQUE_SCHEDULED_EXECUTIONS_ID ON SCHEDULED_EXECUTIONS (SCHEDULED_EXECUTION_ID);

-- HIVE-22729
ALTER TABLE COMPACTION_QUEUE ADD CQ_ERROR_MESSAGE mediumtext;
ALTER TABLE COMPLETED_COMPACTIONS ADD CC_ERROR_MESSAGE mediumtext;

-- HIVE-22728
ALTER TABLE `KEY_CONSTRAINTS` DROP PRIMARY KEY;
ALTER TABLE `KEY_CONSTRAINTS` ADD CONSTRAINT `CONSTRAINTS_PK` PRIMARY KEY (`PARENT_TBL_ID`, `CONSTRAINT_NAME`, `POSITION`);

-- These lines need to be last.  Insert any changes above.
UPDATE VERSION SET SCHEMA_VERSION='4.0.0', VERSION_COMMENT='Hive release version 4.0.0' where VER_ID=1;
SELECT 'Finished upgrading MetaStore schema from 3.2.0 to 4.0.0' AS MESSAGE;
