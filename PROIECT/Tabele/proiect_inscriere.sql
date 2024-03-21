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
-- Table structure for table `inscriere`
--

DROP TABLE IF EXISTS `inscriere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscriere` (
  `idinscriere` int NOT NULL,
  `idstudent` int DEFAULT NULL,
  `idprofesor` int DEFAULT NULL,
  `idcurs` int DEFAULT NULL,
  `data_inscriere` date DEFAULT NULL,
  `data_renunt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idinscriere`),
  KEY `idstudent2_idx` (`idstudent`),
  KEY `idprofesor2_idx` (`idprofesor`),
  KEY `idcurs3_idx` (`idcurs`),
  CONSTRAINT `idcurs3` FOREIGN KEY (`idcurs`) REFERENCES `cursuri` (`idcurs`),
  CONSTRAINT `idprofesor2` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`),
  CONSTRAINT `idstudent2` FOREIGN KEY (`idstudent`) REFERENCES `student` (`idstudent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscriere`
--

LOCK TABLES `inscriere` WRITE;
/*!40000 ALTER TABLE `inscriere` DISABLE KEYS */;
INSERT INTO `inscriere` VALUES (1,1,1,3,'2023-03-01',''),(2,2,1,3,'2023-03-02',''),(3,3,2,1,'2023-04-15',''),(4,5,4,4,'2023-02-27',''),(5,1,4,4,'2023-03-04','2023-07-20'),(6,1,5,6,'2023-02-21','2023-08-01'),(7,1,2,1,'2023-01-16','2023-01-16'),(8,6,1,3,'2023-01-16',NULL),(9,2,4,4,'2023-01-16',NULL),(10,1,1,2,'2023-01-16',NULL),(11,5,1,3,'2023-01-17',NULL),(12,4,1,3,'2023-01-18',NULL),(13,4,5,6,'2023-01-18',NULL),(14,2,5,6,'2023-01-18',NULL);
/*!40000 ALTER TABLE `inscriere` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-18 15:01:45
