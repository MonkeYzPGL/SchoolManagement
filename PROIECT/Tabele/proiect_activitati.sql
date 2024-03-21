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
-- Table structure for table `activitati`
--

DROP TABLE IF EXISTS `activitati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activitati` (
  `idcurs` int NOT NULL,
  `curspr` int DEFAULT NULL,
  `seminarpr` int DEFAULT NULL,
  `laboratorpr` int DEFAULT NULL,
  `zi_lab` varchar(45) DEFAULT NULL,
  `zi_curs` varchar(45) DEFAULT NULL,
  `zi_seminar` varchar(45) DEFAULT NULL,
  `ora_lab` int DEFAULT NULL,
  `ora_curs` int DEFAULT NULL,
  `ora_sem` int DEFAULT NULL,
  PRIMARY KEY (`idcurs`),
  CONSTRAINT `id18` FOREIGN KEY (`idcurs`) REFERENCES `cursuri` (`idcurs`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitati`
--

LOCK TABLES `activitati` WRITE;
/*!40000 ALTER TABLE `activitati` DISABLE KEYS */;
INSERT INTO `activitati` VALUES (1,20,30,50,'Miercuri','Luni','Marti',8,14,18),(3,60,35,5,'Luni','Vineri','Marti',12,18,16),(4,50,30,20,'Marti','Vineri','Luni',10,16,14);
/*!40000 ALTER TABLE `activitati` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-18 15:01:47
