create table PROPERTIES (
    `seq` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    APPLICATION varchar(100) not null,
    PROFILE varchar(50) not null,
    LABEL varchar(1000) not null,
    `KEY` varchar(1000) not null,
    `VALUE` varchar(4000) not null
);
