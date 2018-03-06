CREATE TABLE IF NOT EXISTS WM_RESOURCEPLAN (
    `RP_ID` bigint(20) NOT NULL,
    `NAME` varchar(128) NOT NULL,
    `QUERY_PARALLELISM` int(11),
    `STATUS` varchar(20) NOT NULL,
    `DEFAULT_POOL_ID` bigint(20),
    PRIMARY KEY (`RP_ID`),
    UNIQUE KEY `UNIQUE_WM_RESOURCEPLAN` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS WM_POOL
(
    `POOL_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `PATH` varchar(767) NOT NULL,
    `ALLOC_FRACTION` DOUBLE,
    `QUERY_PARALLELISM` int(11),
    `SCHEDULING_POLICY` varchar(767),
    PRIMARY KEY (`POOL_ID`),
    UNIQUE KEY `UNIQUE_WM_POOL` (`RP_ID`, `PATH`),
    CONSTRAINT `WM_POOL_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `WM_RESOURCEPLAN` ADD CONSTRAINT `WM_RESOURCEPLAN_FK1` FOREIGN KEY (`DEFAULT_POOL_ID`) REFERENCES `WM_POOL`(`POOL_ID`);

CREATE TABLE IF NOT EXISTS WM_TRIGGER
(
    `TRIGGER_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `NAME` varchar(128) NOT NULL,
    `TRIGGER_EXPRESSION` varchar(1024),
    `ACTION_EXPRESSION` varchar(1024),
    `IS_IN_UNMANAGED` bit(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`TRIGGER_ID`),
    UNIQUE KEY `UNIQUE_WM_TRIGGER` (`RP_ID`, `NAME`),
    CONSTRAINT `WM_TRIGGER_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS WM_POOL_TO_TRIGGER
(
    `POOL_ID` bigint(20) NOT NULL,
    `TRIGGER_ID` bigint(20) NOT NULL,
    PRIMARY KEY (`POOL_ID`, `TRIGGER_ID`),
    CONSTRAINT `WM_POOL_TO_TRIGGER_FK1` FOREIGN KEY (`POOL_ID`) REFERENCES `WM_POOL` (`POOL_ID`),
    CONSTRAINT `WM_POOL_TO_TRIGGER_FK2` FOREIGN KEY (`TRIGGER_ID`) REFERENCES `WM_TRIGGER` (`TRIGGER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS WM_MAPPING
(
    `MAPPING_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `ENTITY_TYPE` varchar(128) NOT NULL,
    `ENTITY_NAME` varchar(128) NOT NULL,
    `POOL_ID` bigint(20),
    `ORDERING` int,
    PRIMARY KEY (`MAPPING_ID`),
    UNIQUE KEY `UNIQUE_WM_MAPPING` (`RP_ID`, `ENTITY_TYPE`, `ENTITY_NAME`),
    CONSTRAINT `WM_MAPPING_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`),
    CONSTRAINT `WM_MAPPING_FK2` FOREIGN KEY (`POOL_ID`) REFERENCES `WM_POOL` (`POOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
