CREATE TABLE WM_RESOURCEPLAN (
    `RP_ID` bigint(20) NOT NULL,
    `NAME` varchar(128) NOT NULL,
    `QUERY_PARALLELISM` int(11),
    `STATUS` varchar(20) NOT NULL,
    PRIMARY KEY (`RP_ID`),
    KEY `UNIQUE_WM_RESOURCEPLAN` (`NAME`),
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE WM_POOL
(
    `POOL_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `PATH` varchar(1024) NOT NULL,
    `PARENT_POOL_ID` bigint(20),
    `ALLOC_FRACTION` DOUBLE,
    `QUERY_PARALLELISM` int(11),
    PRIMARY KEY (`POOL_ID`),
    KEY `UNIQUE_WM_POOL` (`RP_ID`, `PATH`),
    CONSTRAINT `WM_POOL_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`),
    CONSTRAINT `WM_POOL_FK2` FOREIGN KEY (`PARENT_POOL_ID`) REFERENCES `WM_POOL` (`POOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE WM_TRIGGER
(
    `TRIGGER_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `NAME` varchar(128) NOT NULL,
    `TRIGGER_EXPRESSION` varchar(1024),
    `ACTION_EXPRESSION` varchar(1024),
    PRIMARY KEY (`TRIGGER_ID`),
    KEY `UNIQUE_WM_TRIGGER` (`RP_ID`, `NAME`),
    CONSTRAINT `WM_TRIGGER_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE WM_POOL_TO_TRIGGER
(
    `POOL_ID` bigint(20) NOT NULL,
    `TRIGGER_ID` bigint(20) NOT NULL,
    PRIMARY KEY (`POOL_ID`, `TRIGGER_ID`),
    CONSTRAINT `WM_POOL_TO_TRIGGER_FK1` FOREIGN KEY (`POOL_ID`) REFERENCES `WM_POOL` (`POOL_ID`),
    CONSTRAINT `WM_POOL_TO_TRIGGER_FK2` FOREIGN KEY (`TRIGGER_ID`) REFERENCES `WM_TRIGGER` (`TRIGGER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE WM_MAPPING
(
    `MAPPING_ID` bigint(20) NOT NULL,
    `RP_ID` bigint(20) NOT NULL,
    `ENTITY_TYPE` varchar(10) NOT NULL,
    `ENTITY_NAME` varchar(128) NOT NULL,
    `POOL_ID` bigint(20) NOT NULL,
    `ORDERING int,
    PRIMARY KEY (`MAPPING_ID`),
    KEY `UNIQUE_WM_MAPPING` (`RP_ID`, `ENTITY_TYPE`, `ENTITY_NAME`),
    CONSTRAINT `WM_MAPPING_FK1` FOREIGN KEY (`RP_ID`) REFERENCES `WM_RESOURCEPLAN` (`RP_ID`),
    CONSTRAINT `WM_MAPPING_FK2` FOREIGN KEY (`POOL_ID`) REFERENCES `WM_POOL` (`POOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
