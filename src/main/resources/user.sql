# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.25)
# Database: user
# Generation Time: 2015-10-23 14:52:22 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `sex` smallint(2) DEFAULT '0',
  `email` varchar(256) DEFAULT NULL,
  `phone` varchar(256) DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `image_url` varchar(256) DEFAULT 'img/user-anonymous.jpg',
  `image_thumb_url` varchar(256) DEFAULT NULL,
  `role` varchar(15) DEFAULT 'USER',
  `enabled` smallint(6) DEFAULT '1',
  `password` varchar(256) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_last_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`id`, `name`, `sex`, `email`, `phone`, `level`, `image_url`, `image_thumb_url`, `role`, `enabled`, `password`, `gmt_create`, `gmt_last_modified`)
VALUES
	(20,'root',0,'123@123.com',NULL,100,'img/user-anonymous.jpg',NULL,'USER',1,NULL,NULL,NULL),
	(21,'test',0,'test@123.com','123456789000',1,'img/user-anonymous.jpg',NULL,'USER',1,NULL,NULL,NULL),
	(22,'shit',0,'test@123.com',NULL,3,'img/user-anonymous.jpg',NULL,'USER',0,NULL,NULL,NULL),
	(23,'fuck',0,'test@123.com','123456789000',2,'img/user-anonymous.jpg',NULL,'USER',1,NULL,NULL,NULL),
	(24,NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(25,NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(26,NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(27,NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(40,NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(41,'sdf',NULL,NULL,'',100,NULL,NULL,NULL,NULL,NULL,'2015-10-21 00:00:00',NULL);

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table User_Detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User_Detail`;

CREATE TABLE `User_Detail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
