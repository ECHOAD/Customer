CREATE DATABASE IF NOT EXISTS `customers`
USE `customers`;

CREATE TABLE IF NOT EXISTS `customer` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_CARD_NUMBER` varchar(14) NOT NULL,
  `NAME` bigint(20) NOT NULL,
  `LAST_NAME` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UQ_CUSTOMER_ID_CARD_NUMBER` (`ID_CARD_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `customer_address` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_CUSTOMER` bigint(20) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `FK_CUSTOMER_ADDRESS__CUSTOMER` (`ID_CUSTOMER`),
  CONSTRAINT `FK_CUSTOMER_ADDRESS__CUSTOMER` FOREIGN KEY (`ID_CUSTOMER`) REFERENCES `customer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


