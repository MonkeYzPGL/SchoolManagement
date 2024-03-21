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
-- Table structure for table `studenti_actvititate`
--

DROP TABLE IF EXISTS `studenti_actvititate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenti_actvititate` (
  `idstudenti_actvititate` int NOT NULL,
  `idstudent` int DEFAULT NULL,
  `idactiv_grup` int DEFAULT NULL,
  `idgrup_studiu` int DEFAULT NULL,
  PRIMARY KEY (`idstudenti_actvititate`),
  KEY `idactiv_grup_idx` (`idactiv_grup`),
  KEY `idgrup_studiu_idx` (`idgrup_studiu`),
  CONSTRAINT `idactiv_grup` FOREIGN KEY (`idactiv_grup`) REFERENCES `activ_grup` (`idactiv_grup`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idgrup_studiu` FOREIGN KEY (`idgrup_studiu`) REFERENCES `activ_grup` (`id_grup_studiu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti_actvititate`
--

LOCK TABLES `studenti_actvititate` WRITE;
/*!40000 ALTER TABLE `studenti_actvititate` DISABLE KEYS */;
INSERT INTO `studenti_actvititate` VALUES (3,3,3,3);
/*!40000 ALTER TABLE `studenti_actvititate` ENABLE KEYS */;
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
