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
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `houseId` int NOT NULL AUTO_INCREMENT,
  `houseName` varchar(255) NOT NULL,
  PRIMARY KEY (`houseId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,'Test1');
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `houseMember`
--

DROP TABLE IF EXISTS `houseMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houseMember` (
  `userId` int NOT NULL,
  `houseId` int NOT NULL,
  PRIMARY KEY (`userId`,`houseId`),
  KEY `FKk5edjijggns53o5hem34ng4e2` (`houseId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `houseMember`
--

LOCK TABLES `houseMember` WRITE;
/*!40000 ALTER TABLE `houseMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `houseMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `projectId` int NOT NULL AUTO_INCREMENT,
  `isArchived` tinyint(1) DEFAULT '0',
  `projectDeadline` date DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `projectOwnerUserId` int NOT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,0,'2020-09-30','House Chores',1),(2,0,'2020-12-31','Some Work',2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectStakeholder`
--

DROP TABLE IF EXISTS `projectStakeholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectStakeholder` (
  `userId` int NOT NULL,
  `projectId` int NOT NULL,
  PRIMARY KEY (`userId`,`projectId`),
  KEY `FKi5ijtxtchyedy37vqeuhyua4w` (`projectId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectStakeholder`
--

LOCK TABLES `projectStakeholder` WRITE;
/*!40000 ALTER TABLE `projectStakeholder` DISABLE KEYS */;
INSERT INTO `projectStakeholder` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(4,1);
/*!40000 ALTER TABLE `projectStakeholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'projectOwner'),(2,'taskOwner'),(3,'projectStakeholder'),(4,'houseMember');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT '1',
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profilePicUrl` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `mainProjectId` int NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'j1','UserOne',1,'DB','pwd',NULL,'User1',1,0),(2,'j2','UserTwo',1,'DB','pwd',NULL,'User2',1,0),(3,'j3','UserThree',1,'Online','pwd',NULL,'User3',1,0),(4,'j4','UserFour',1,'DB','pwd',NULL,'User4',1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userRole`
--

DROP TABLE IF EXISTS `userRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userRole` (
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK4t37uy1gysa7c7agb8q7tr638` (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRole`
--

LOCK TABLES `userRole` WRITE;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `userRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-12 18:15:24
