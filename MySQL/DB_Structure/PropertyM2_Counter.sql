-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: PropertyM2
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Counter`
--

DROP TABLE IF EXISTS `Counter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Counter` (
  `counterNo` varchar(100) NOT NULL,
  `houseOrUnit` varchar(100) NOT NULL,
  `unitName` varchar(45) DEFAULT NULL,
  `type` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL COMMENT 'z.B. ‚replaced‘, ‚active’ etc.',
  `status` varchar(20) DEFAULT NULL,
  `recentlyReplaced` varchar(10) DEFAULT NULL,
  `dateReplacement` datetime DEFAULT NULL,
  `House_idHouse` int NOT NULL,
  `Unit_idUnit` int NOT NULL,
  PRIMARY KEY (`counterNo`),
  UNIQUE KEY `counterNo_UNIQUE` (`counterNo`),
  KEY `fk_Counter_House1_idx` (`House_idHouse`),
  KEY `fk_Unit_idx` (`Unit_idUnit`),
  CONSTRAINT `fk_Counter_House1` FOREIGN KEY (`House_idHouse`) REFERENCES `House` (`idHouse`),
  CONSTRAINT `fk_Unit` FOREIGN KEY (`Unit_idUnit`) REFERENCES `Unit` (`idUnit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-26 11:27:51
