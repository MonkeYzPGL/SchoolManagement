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
-- Table structure for table `orar_student`
--

DROP TABLE IF EXISTS `orar_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orar_student` (
  `idorar_student` int NOT NULL,
  `idstudent` int DEFAULT NULL,
  `idcurs` int DEFAULT NULL,
  `zi_curs` varchar(45) DEFAULT NULL,
  `zi_lab` varchar(45) DEFAULT NULL,
  `zi_sem` varchar(45) DEFAULT NULL,
  `ora_curs` int DEFAULT NULL,
  `ora_lab` int DEFAULT NULL,
  `ora_sem` int DEFAULT NULL,
  PRIMARY KEY (`idorar_student`),
  CONSTRAINT `idorar_student` FOREIGN KEY (`idorar_student`) REFERENCES `inscriere` (`idinscriere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar_student`
--

LOCK TABLES `orar_student` WRITE;
/*!40000 ALTER TABLE `orar_student` DISABLE KEYS */;
INSERT INTO `orar_student` VALUES (1,1,3,'Marti','Luni','Marti',18,12,16),(2,2,3,'Vineri','Joi','Luni',18,8,10),(3,3,1,'Luni','Miercuri','Marti',14,8,18),(4,4,3,'Marti','Luni','Marti',18,12,16),(5,1,6,'','','Marti',8,21,8),(6,1,4,'Vineri','Marti','Luni',16,10,14),(7,1,1,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,3,'Vineri','Luni','Marti',18,12,16),(9,2,4,'Vineri','Marti','Luni',16,10,14),(10,1,2,NULL,NULL,NULL,NULL,NULL,NULL),(11,4,6,NULL,NULL,NULL,NULL,NULL,NULL),(12,2,6,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `orar_student` ENABLE KEYS */;
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
