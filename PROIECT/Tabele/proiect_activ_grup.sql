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
-- Table structure for table `activ_grup`
--

DROP TABLE IF EXISTS `activ_grup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activ_grup` (
  `idactiv_grup` int NOT NULL,
  `id_grup_studiu` int NOT NULL,
  `nr_min` int DEFAULT NULL,
  `data_desf` varchar(20) DEFAULT NULL,
  `ora_exp` varchar(20) DEFAULT NULL,
  `nr_actual` int DEFAULT '0',
  `nume_activ` varchar(45) DEFAULT NULL,
  `idprof` int DEFAULT NULL,
  PRIMARY KEY (`idactiv_grup`),
  KEY `idcurs177_idx` (`id_grup_studiu`),
  CONSTRAINT `idcurs177` FOREIGN KEY (`id_grup_studiu`) REFERENCES `grup_studiu` (`idgrup_studiu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activ_grup`
--

LOCK TABLES `activ_grup` WRITE;
/*!40000 ALTER TABLE `activ_grup` DISABLE KEYS */;
INSERT INTO `activ_grup` VALUES (1,1,10,' 20.05.2023','12',17,'Pregatire_PC',1),(2,2,2,'17.04.2023','13',8,'Zi de push',NULL),(3,3,2,'20.02.2023','22',4,'Legea lui Ohm',NULL);
/*!40000 ALTER TABLE `activ_grup` ENABLE KEYS */;
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
