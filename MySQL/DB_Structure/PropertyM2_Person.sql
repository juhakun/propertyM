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
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Person` (
  `idPerson` int NOT NULL AUTO_INCREMENT,
  `formOfAddress` varchar(100) DEFAULT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) NOT NULL,
  `eMail` varchar(200) NOT NULL,
  `onlyRenter` varchar(10) DEFAULT NULL,
  `ownerAndRenter` varchar(10) DEFAULT NULL,
  `onlyOwner` varchar(45) DEFAULT NULL,
  `rent` decimal(6,2) DEFAULT NULL,
  `monthlyNKInAdvance` decimal(6,2) DEFAULT NULL,
  `noOfPeople` int DEFAULT NULL,
  `dateMoveIn` date DEFAULT NULL,
  `dateMoveOut` date DEFAULT NULL,
  `hasExtraAddress` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `Renter_idHouse` int DEFAULT NULL,
  `Address_idAddress` int DEFAULT NULL,
  PRIMARY KEY (`idPerson`),
  KEY `fk_Person_House1_idx` (`Renter_idHouse`),
  KEY `fk_Person_Address1_idx` (`Address_idAddress`),
  CONSTRAINT `fk_Person_Address1` FOREIGN KEY (`Address_idAddress`) REFERENCES `Address` (`idAddress`),
  CONSTRAINT `fk_Person_House1` FOREIGN KEY (`Renter_idHouse`) REFERENCES `House` (`idHouse`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-26 11:27:52
