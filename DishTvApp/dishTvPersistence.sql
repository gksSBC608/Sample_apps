-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.53-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dish_db
--

CREATE DATABASE IF NOT EXISTS dish_db;
USE dish_db;

--
-- Definition of table `channels`
--

DROP TABLE IF EXISTS `channels`;
CREATE TABLE `channels` (
  `channel_id` int(11) NOT NULL,
  `channel_name` varchar(15) DEFAULT NULL,
  `cost_per_month` double DEFAULT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `channels`
--

/*!40000 ALTER TABLE `channels` DISABLE KEYS */;
INSERT INTO `channels` (`channel_id`,`channel_name`,`cost_per_month`) VALUES 
 (105,'Star Plus',3.5),
 (110,'Zee TV',2.9),
 (113,'Sony',4.1),
 (115,'Star Movies HD',3.9),
 (116,'Times Now',5.1);
/*!40000 ALTER TABLE `channels` ENABLE KEYS */;


--
-- Definition of table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `subscriber_id` bigint(10) NOT NULL,
  `registered_mobile` varchar(10) DEFAULT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`subscriber_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`subscriber_id`,`registered_mobile`,`first_name`,`last_name`) VALUES 
 (1100123456,'9456801231','Larry','Gomes'),
 (1100123457,'9876543456','Gaurav','Sharan'),
 (1100123458,'9809897865','Gopal','Krishna');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`id`,`username`,`password`) VALUES 
 (1,'gaurav','12345');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE `subscriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subscription_date` date NOT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `subscriber_id` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channel_id` (`channel_id`),
  KEY `subscriber_id` (`subscriber_id`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`channel_id`),
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`subscriber_id`) REFERENCES `customers` (`subscriber_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscriptions`
--

/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` (`id`,`subscription_date`,`channel_id`,`subscriber_id`) VALUES 
 (1,'2015-05-02',105,1100123456),
 (2,'2015-05-02',105,1100123457),
 (3,'2015-04-02',110,1100123456),
 (4,'2015-04-01',113,1100123456),
 (5,'2015-05-04',116,1100123456),
 (6,'2015-05-04',115,1100123458),
 (7,'2015-04-03',116,1100123457),
 (8,'2015-04-04',105,1100123458),
 (9,'2015-04-04',105,1100123458),
 (10,'2015-04-04',105,1100123458),
 (12,'0017-09-05',105,1100123457),
 (13,'2015-04-03',115,1100123457),
 (14,'2015-04-03',105,1100123457),
 (15,'2015-02-03',110,1100123456),
 (16,'2015-02-03',110,1100123456);
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;


--
-- Definition of procedure `insertChannels`
--

DROP PROCEDURE IF EXISTS `insertChannels`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertChannels`(IN channel_id int,IN channel_name VARCHAR(15),IN cost_per_month double)
BEGIN
       INSERT INTO channels VALUES (channel_id,channel_name,cost_per_month);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `insertCustomers`
--

DROP PROCEDURE IF EXISTS `insertCustomers`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCustomers`(IN subscriber_id bigint(10),IN mobile varchar(10),IN first_name VARCHAR(15),IN last_name VARCHAR(15))
BEGIN
       INSERT INTO customers VALUES (subscriber_id,mobile,first_name,last_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
