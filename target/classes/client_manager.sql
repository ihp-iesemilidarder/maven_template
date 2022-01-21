-- MariaDB dump 10.19  Distrib 10.5.12-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: client_manager
-- ------------------------------------------------------
-- Server version	10.5.12-MariaDB-0+deb11u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `cli_id` int(11) NOT NULL AUTO_INCREMENT,
  `cli_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cli_surname` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cli_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cli_phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cli_balance` double DEFAULT NULL,
  PRIMARY KEY (`cli_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Emilio_ElHacker','Merl','emerl@gmail.com','9879872193',99999999999),(4,'Fernando','Soler','fsoler@gmail.com','971231273',2000),(5,'Angel','Gutierrez','agutierrez@gmail.com','978676767',2500),(19,'IvÃ¡n','Heredia','ihp00@iesemiliarder.com','4532124234',10);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clixkar`
--

DROP TABLE IF EXISTS `clixkar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clixkar` (
  `cxk_cli_id` int(11) NOT NULL,
  `cxk_kar_id` int(11) NOT NULL,
  PRIMARY KEY (`cxk_cli_id`,`cxk_kar_id`),
  KEY `cxk_kar_FK` (`cxk_kar_id`),
  CONSTRAINT `cxk_cli_FK` FOREIGN KEY (`cxk_cli_id`) REFERENCES `client` (`cli_id`),
  CONSTRAINT `cxk_kar_FK` FOREIGN KEY (`cxk_kar_id`) REFERENCES `kart` (`kar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clixkar`
--

LOCK TABLES `clixkar` WRITE;
/*!40000 ALTER TABLE `clixkar` DISABLE KEYS */;
/*!40000 ALTER TABLE `clixkar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kart`
--

DROP TABLE IF EXISTS `kart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kart` (
  `kar_id` int(11) NOT NULL AUTO_INCREMENT,
  `kar_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kar_tipus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `kar_power` decimal(5,3) NOT NULL,
  `kar_price_minute` decimal(5,3) NOT NULL,
  PRIMARY KEY (`kar_id`),
  KEY `kar_tka_FK` (`kar_tipus`),
  CONSTRAINT `kar_tka_FK` FOREIGN KEY (`kar_tipus`) REFERENCES `tipus_kart` (`tka_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kart`
--

LOCK TABLES `kart` WRITE;
/*!40000 ALTER TABLE `kart` DISABLE KEYS */;
INSERT INTO `kart` VALUES (6,'hola','ejemplo',3.000,5.000);
/*!40000 ALTER TABLE `kart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipus_kart`
--

DROP TABLE IF EXISTS `tipus_kart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipus_kart` (
  `tka_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`tka_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipus_kart`
--

LOCK TABLES `tipus_kart` WRITE;
/*!40000 ALTER TABLE `tipus_kart` DISABLE KEYS */;
INSERT INTO `tipus_kart` VALUES ('ejemplo');
/*!40000 ALTER TABLE `tipus_kart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-17 13:01:45
