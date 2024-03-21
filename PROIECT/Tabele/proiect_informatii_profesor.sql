-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: proiect
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `informatii_profesor`
--

DROP TABLE IF EXISTS `informatii_profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informatii_profesor` (
  `idinformatii_profesor` int NOT NULL AUTO_INCREMENT,
  `idprofesor` int NOT NULL,
  `idcurs` int DEFAULT NULL,
  `nr_min` int DEFAULT NULL,
  `nr_max` int DEFAULT NULL,
  `departament` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idinformatii_profesor`),
  KEY `idprofesor_idx` (`idprofesor`),
  KEY `idcurs_idx` (`idcurs`),
  CONSTRAINT `idcurs` FOREIGN KEY (`idcurs`) REFERENCES `cursuri` (`idcurs`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idprofesor` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informatii_profesor`
--

LOCK TABLES `informatii_profesor` WRITE;
/*!40000 ALTER TABLE `informatii_profesor` DISABLE KEYS */;
INSERT INTO `informatii_profesor` VALUES (1,1,3,5,10,'Informatica'),(2,2,1,10,12,'Matematica'),(3,5,6,10,20,'Sport'),(4,4,4,3,150,'Fizica');
/*!40000 ALTER TABLE `informatii_profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-18 15:01:46
