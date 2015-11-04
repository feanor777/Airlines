CREATE DATABASE  IF NOT EXISTS `airlines` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `airlines`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: airlines
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_description` text NOT NULL,
  `team_id` int(11) DEFAULT NULL,
  `users_id` int(11) DEFAULT NULL,
  `application_status_id` int(11) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_application_team_idx` (`team_id`),
  KEY `fk_application_users_idx` (`users_id`),
  KEY `fk_application_application_status_idx` (`application_status_id`),
  CONSTRAINT `fk_application_application_status` FOREIGN KEY (`application_status_id`) REFERENCES `application_status` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_application_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_application_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'sdafs',NULL,NULL,NULL,'feanor94@ukr.net');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_status`
--

DROP TABLE IF EXISTS `application_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_status`
--

LOCK TABLES `application_status` WRITE;
/*!40000 ALTER TABLE `application_status` DISABLE KEYS */;
INSERT INTO `application_status` VALUES (0,'performed'),(1,'denied');
/*!40000 ALTER TABLE `application_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_status`
--

DROP TABLE IF EXISTS `flight_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_status`
--

LOCK TABLES `flight_status` WRITE;
/*!40000 ALTER TABLE `flight_status` DISABLE KEYS */;
INSERT INTO `flight_status` VALUES (0,'A'),(1,'C'),(2,'D'),(3,'L'),(4,'S');
/*!40000 ALTER TABLE `flight_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL,
  `from_city` varchar(45) NOT NULL,
  `to_city` varchar(45) NOT NULL,
  `date_of_departure` date NOT NULL,
  `price` int(11) NOT NULL,
  `flight_status_id` int(11) NOT NULL DEFAULT '4',
  PRIMARY KEY (`id`),
  KEY `fk_flights_flight_status_idx` (`flight_status_id`),
  CONSTRAINT `fk_flights_flight_status` FOREIGN KEY (`flight_status_id`) REFERENCES `flight_status` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,'323','Kharkiv','Lviv','2015-08-28',800,4),(4,'230','Kiev','Kharkiv','2015-08-29',900,4),(5,'838','Kharkiv','Hanoi','2015-08-29',6600,4),(6,'317','Paris','Milan','2015-08-27',3500,4),(15,'838','Milan','Praha','2015-08-30',2000,4),(17,'1123','Lviv','Berlin','2015-08-28',4300,4),(19,'7377','Kharkiv','Odessa','2015-08-30',650,4),(22,'1254','Lublin','Driagovichi','2015-08-30',12000,4);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profession`
--

DROP TABLE IF EXISTS `profession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profession_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profession`
--

LOCK TABLES `profession` WRITE;
/*!40000 ALTER TABLE `profession` DISABLE KEYS */;
INSERT INTO `profession` VALUES (0,'pilot'),(1,'navigator'),(2,'operator'),(3,'flight_attendant');
/*!40000 ALTER TABLE `profession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'administrator'),(1,'dispatcher');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `is_free` tinyint(1) NOT NULL DEFAULT '1',
  `profession_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_staff_proffesion1_idx` (`profession_id`),
  CONSTRAINT `fk_staff_profession` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'John','Johnson',0,0,0),(2,'Nicolas','Vein',1,0,0),(3,'Stephan','Kreig',0,1,0),(4,'Yian','Viktorovich',1,1,0),(5,'Vasul','Fedotov',0,2,0),(6,'Ioan','Rodomirov',1,2,0),(7,'Elena','Lodji',0,3,0),(8,'Anna','Hukova',0,3,0),(9,'Violetta','Jikova',0,0,0),(10,'Inna','Daho',0,3,0),(12,'Kiril','Kolchenko',1,0,0),(13,'Yurii','Kluev',0,0,0),(14,'Igil','Abhanov',0,0,0),(15,'Ramil','Ignatzi',0,1,0),(16,'Stepan','Moiko',1,1,0),(17,'Dmytro','Kihtev',1,2,0),(18,'Darya','Kotova',0,2,0),(21,'Iosif','Lutiy',1,0,0),(22,'Katerina','Osadcha',0,3,0),(23,'Evgeniz','Malikova',0,3,0),(24,'Juanna','Koretta',0,3,0),(25,'Влад','Кочищик',1,0,0),(28,'Виктор','Цой',1,3,0),(58,'Родомир','Ковиртев',1,1,0),(59,'Ия','Осипова',1,3,0),(60,'Макар','Макаров',1,3,0),(66,'Ivan','Ivanov',1,0,0),(68,'Hura','Ovaeva',1,2,0),(69,'Makar','Makarov',1,3,0);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flights_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_flights_idx` (`flights_id`),
  KEY `fk_team_users_idx` (`users_id`),
  CONSTRAINT `fk_team_flights` FOREIGN KEY (`flights_id`) REFERENCES `flights` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_team_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,15,2),(2,6,2);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_staff`
--

DROP TABLE IF EXISTS `team_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_staff` (
  `team_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  PRIMARY KEY (`team_id`,`staff_id`),
  KEY `team_id_fk_idx` (`team_id`),
  KEY `staff_id_fk_idx` (`staff_id`),
  CONSTRAINT `staff_id_fk` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_staff`
--

LOCK TABLES `team_staff` WRITE;
/*!40000 ALTER TABLE `team_staff` DISABLE KEYS */;
INSERT INTO `team_staff` VALUES (1,5),(1,8),(1,9),(1,10),(1,14),(1,15),(1,24),(2,1),(2,3),(2,7),(2,13),(2,18),(2,22),(2,23);
/*!40000 ALTER TABLE `team_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `email_password` varchar(45) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles_idx` (`roles_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','Maksymoff','Maks','maksym0207@gmail.com','feanor777',0),(2,'dispatcher','dispatcher','Ivanov','Ivan','feanor94@ukr.net','feanor7098452',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-27 14:03:13
