-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: justDOit
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `taskId` int NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dependsOn` int DEFAULT NULL,
  `isComplete` tinyint(1) DEFAULT '0',
  `isDependedOnBy` int DEFAULT NULL,
  `lastModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `photoUrl` varchar(255) DEFAULT NULL,
  `projectId` int NOT NULL,
  `taskAddedNotificationOn` tinyint(1) DEFAULT '1',
  `taskCompleteNotificationOn` tinyint(1) DEFAULT '1',
  `taskDeadline` date NOT NULL,
  `taskDeadlineNotificationOn` tinyint(1) DEFAULT '1',
  `taskDescription` varchar(255) NOT NULL,
  `taskOwnerUserId` int NOT NULL,
  `taskPriority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'2020-07-20 11:12:43',NULL,0,NULL,'2020-07-20 11:12:43',NULL,1,1,1,'2020-07-20',1,'Clean the Loo! Updated',2,'Low'),(2,'2020-08-11 11:22:01',NULL,0,NULL,'2020-08-11 11:22:01',NULL,1,1,1,'2020-08-20',1,'Clean the garden',2,'Medium'),(3,'2020-08-11 11:22:01',NULL,0,NULL,'2020-08-11 11:22:01',NULL,1,1,1,'2020-08-31',1,'Set up outdoor gym',3,'High');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-12 18:10:32
