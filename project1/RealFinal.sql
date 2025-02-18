-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `cno` int NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `cname` varchar(100) DEFAULT NULL,
  `cemail` varchar(100) DEFAULT NULL,
  `cpass` varchar(255) DEFAULT NULL,
  `cgrade` varchar(10) DEFAULT NULL,
  `cstatus` varchar(10) DEFAULT NULL,
  `cphone` varchar(20) DEFAULT NULL,
  `cadd1` text,
  `cadd2` text,
  `cadd3` text,
  `cdateq` datetime DEFAULT NULL,
  `cgender` int DEFAULT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'admin','admin','test@naver.com','1234','M001',NULL,'010-2222-3333','testsi','null','null','2024-11-27 00:00:00',1),(2,'test2','test2','test2@naver.com','12345','M001',NULL,'010-3333-4444','testsi testgu testdong','null','null','2024-12-09 00:00:00',1),(3,'test3','test3','test5@naver.com','4567','M001',NULL,'010-9999-8888','경기도 수원시 어쩌구 저쩌동 MBC 아카데미','null','null','2024-12-13 00:00:00',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `eno` int NOT NULL AUTO_INCREMENT,
  `ntitle` varchar(500) DEFAULT NULL,
  `ncontent` text,
  `ndate` datetime DEFAULT NULL,
  `nimg` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`eno`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (47,'오늘의 공지사항!','123','2024-12-12 00:46:52','img/uploadFiles/8370e35b-9086-4f8d-bfff-c3205625d81a_1001_2.jpg');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `iid` int NOT NULL AUTO_INCREMENT,
  `cno` int DEFAULT NULL,
  `oid` int DEFAULT NULL,
  `ono` varchar(100) DEFAULT NULL,
  `pno` int DEFAULT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `orderquan` int DEFAULT NULL,
  `pprice` int DEFAULT NULL,
  `ostatus` varchar(100) DEFAULT NULL,
  `pcat` varchar(100) DEFAULT NULL,
  `odate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ototal` int DEFAULT NULL,
  PRIMARY KEY (`iid`),
  KEY `OrderItem_Customer_idx` (`cno`),
  KEY `OrderItem_Product_idx` (`pno`),
  CONSTRAINT `OrderItem_Customer` FOREIGN KEY (`cno`) REFERENCES `customer` (`cno`),
  CONSTRAINT `OrderItem_Product` FOREIGN KEY (`pno`) REFERENCES `product` (`pno`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (7,1,3,'ORDER003',18,'ONE THING CICA B5 TONER',3,23000,'D001','Toner','2024-12-03 15:00:00',69000),(9,1,3,'ORDER003',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-11-03 15:00:00',68700),(10,1,4,'ORDER004',18,'ONE THING CICA B5 TONER',3,23000,'D001','Toner','2024-11-03 15:00:00',69000),(11,1,4,'ORDER004',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-03 15:00:00',19800),(12,1,4,'ORDER004',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-03 15:00:00',68700),(13,1,5,'ORDER005',18,'ONE THING CICA B5 TONER',3,23000,'D001','Toner','2024-12-03 15:00:00',69000),(14,1,5,'ORDER005',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-03 15:00:00',19800),(15,1,5,'ORDER005',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-11-03 15:00:00',68700),(18,1,6,'ORDER006',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-03 15:00:00',68700),(20,1,7,'ORDER007',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(21,1,7,'ORDER007',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(22,1,7,'ORDER007',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-11-04 15:00:00',39600),(23,1,8,'ORDER008',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-12-04 15:00:00',115000),(24,1,8,'ORDER008',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-11-04 15:00:00',19800),(25,1,8,'ORDER008',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(26,1,8,'ORDER008',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(27,1,9,'ORDER009',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-12-04 15:00:00',115000),(28,1,9,'ORDER009',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(29,1,9,'ORDER009',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(30,1,9,'ORDER009',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(31,1,10,'ORDER0010',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-12-04 15:00:00',115000),(32,1,10,'ORDER0010',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(33,1,10,'ORDER0010',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(34,1,10,'ORDER0010',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(35,1,11,'ORDER0011',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-10-04 15:00:00',115000),(36,1,11,'ORDER0011',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-10-04 15:00:00',19800),(37,1,11,'ORDER0011',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-10-04 15:00:00',68700),(38,1,11,'ORDER0011',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(39,1,12,'ORDER0012',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-12-04 15:00:00',115000),(40,1,12,'ORDER0012',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(41,1,12,'ORDER0012',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(42,1,12,'ORDER0012',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(43,1,12,'ORDER0012',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(44,1,13,'ORDER0013',18,'ONE THING CICA B5 TONER',5,23000,'D001','Toner','2024-12-04 15:00:00',115000),(45,1,13,'ORDER0013',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(46,1,13,'ORDER0013',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(47,1,13,'ORDER0013',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(48,1,13,'ORDER0013',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(49,1,14,'ORDER0014',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(50,1,14,'ORDER0014',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(51,1,14,'ORDER0014',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(52,1,14,'ORDER0014',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(53,1,14,'ORDER0014',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(54,1,14,'ORDER0014',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(55,1,14,'ORDER0014',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(56,1,14,'ORDER0014',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(57,1,15,'ORDER0015',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(58,1,15,'ORDER0015',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(59,1,15,'ORDER0015',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(60,1,15,'ORDER0015',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(61,1,15,'ORDER0015',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(62,1,15,'ORDER0015',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(63,1,15,'ORDER0015',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(64,1,15,'ORDER0015',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(65,1,16,'ORDER0016',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(66,1,16,'ORDER0016',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(67,1,16,'ORDER0016',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(68,1,16,'ORDER0016',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(69,1,16,'ORDER0016',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(70,1,16,'ORDER0016',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(71,1,16,'ORDER0016',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(72,1,16,'ORDER0016',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(73,1,17,'ORDER0017',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(74,1,17,'ORDER0017',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(75,1,17,'ORDER0017',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(76,1,17,'ORDER0017',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(77,1,17,'ORDER0017',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(78,1,17,'ORDER0017',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(79,1,17,'ORDER0017',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(80,1,17,'ORDER0017',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(81,1,18,'ORDER0018',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(82,1,18,'ORDER0018',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(83,1,18,'ORDER0018',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(84,1,18,'ORDER0018',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(85,1,18,'ORDER0018',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(86,1,18,'ORDER0018',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(87,1,18,'ORDER0018',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(88,1,18,'ORDER0018',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(89,1,19,'ORDER0019',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(90,1,19,'ORDER0019',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(91,1,19,'ORDER0019',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(92,1,19,'ORDER0019',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(93,1,19,'ORDER0019',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(94,1,19,'ORDER0019',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(95,1,19,'ORDER0019',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(96,1,19,'ORDER0019',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(97,1,20,'ORDER0020',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(98,1,20,'ORDER0020',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(99,1,20,'ORDER0020',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(100,1,20,'ORDER0020',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(101,1,20,'ORDER0020',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(102,1,20,'ORDER0020',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(103,1,20,'ORDER0020',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(104,1,20,'ORDER0020',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(105,1,21,'ORDER0021',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(106,1,21,'ORDER0021',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(107,1,21,'ORDER0021',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(108,1,21,'ORDER0021',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(109,1,21,'ORDER0021',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(110,1,21,'ORDER0021',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(111,1,21,'ORDER0021',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(112,1,21,'ORDER0021',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(113,1,22,'ORDER0022',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-04 15:00:00',138000),(114,1,22,'ORDER0022',6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash',1,19800,'D001','Bodywash','2024-12-04 15:00:00',19800),(115,1,22,'ORDER0022',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',3,22900,'D001','Treatment','2024-12-04 15:00:00',68700),(116,1,22,'ORDER0022',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-04 15:00:00',39600),(117,1,22,'ORDER0022',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-04 15:00:00',56000),(118,1,22,'ORDER0022',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-04 15:00:00',7000),(119,1,22,'ORDER0022',13,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-04 15:00:00',14000),(120,1,22,'ORDER0022',9,'CENTELLA ASIATICA EXTRACT',1,20000,'D001','Toner','2024-12-04 15:00:00',20000),(121,1,23,'ORDER0023',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-06 06:59:17',138000),(122,1,23,'ORDER0023',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-06 06:59:17',39600),(123,1,23,'ORDER0023',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-06 06:59:17',56000),(124,1,23,'ORDER0023',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-06 06:59:17',7000),(125,1,23,'ORDER0023',13,'CENTELLA ASIATICA EXTRACT',2,14000,'D001','Toner','2024-12-06 06:59:17',28000),(126,1,24,'ORDER0024',18,'ONE THING CICA B5 TONER',6,23000,'D001','Toner','2024-12-06 07:01:00',138000),(127,1,24,'ORDER0024',5,'Dr.SEED Super Seed Bomb Treatment',2,19800,'D001','Treatment','2024-12-06 07:01:00',39600),(128,1,24,'ORDER0024',16,'ONE THING CICA B5 MASK',2,28000,'D001','Mask','2024-12-06 07:01:00',56000),(129,1,24,'ORDER0024',14,'CENTELLA ASIATICA EXTRACT',1,7000,'D001','Toner','2024-12-06 07:01:00',7000),(130,1,24,'ORDER0024',13,'CENTELLA ASIATICA EXTRACT',2,14000,'D001','Toner','2024-12-06 07:01:00',28000),(131,1,25,'ORDER0025',2,'Dr.SEED Peppermint and Lemon Vegan Dandruff Cool Anti Hairloss Shampoo',3,19800,'D001','Shampoo','2024-12-11 08:13:43',59400),(132,1,25,'ORDER0025',5,'Dr.SEED Super Seed Bomb Treatment',1,19800,'D001','Treatment','2024-12-11 08:13:43',19800),(133,2,26,'ORDER0026',10,'CENTELLA ASIATICA EXTRACT',1,14000,'D001','Toner','2024-12-12 05:42:46',14000),(135,1,27,'ORDER0027',17,'Cica Soothing Mask',1,20000,'D001','Mask','2024-12-12 05:47:32',20000),(136,1,28,'ORDER0028',3,'Dr.SEED Teatree and Line Vegan No-sebum Cool Anti Hairloss Shampoo',1,19800,'D001','Shampoo','2024-12-12 05:53:17',19800),(137,2,29,'ORDER0029',17,'Cica Soothing Mask',1,20000,'D001','Mask','2024-12-12 06:42:36',20000),(138,1,30,'ORDER0030',16,'ONE THING CICA B5 MASK',4,28000,'D001','Mask','2024-12-12 07:13:17',112000),(139,1,30,'ORDER0030',15,'CENTELLA ASIATICA SERUM',5,25600,'D001','Serum','2024-12-12 07:13:17',128000),(140,1,30,'ORDER0030',15,'CENTELLA ASIATICA SERUM',5,25600,'D001','Serum','2024-12-12 07:13:17',128000),(141,3,31,'ORDER0031',2,'Dr.SEED Peppermint and Lemon Vegan Dandruff Cool Anti Hairloss Shampoo',3,19800,'D001','Shampoo','2024-12-13 01:33:23',59400),(142,3,31,'ORDER0031',7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment',5,22900,'D001','Treatment','2024-12-13 01:33:23',114500),(143,3,32,'ORDER0032',1,'Dr.SEED Black Bean Vegan Anti-Hairloss Shampoo',2,19800,'D001','Shampoo','2024-12-13 01:35:57',39600),(144,3,32,'ORDER0032',2,'Dr.SEED Peppermint and Lemon Vegan Dandruff Cool Anti Hairloss Shampoo',2,19800,'D001','Shampoo','2024-12-13 01:35:57',39600),(145,3,32,'ORDER0032',3,'Dr.SEED Teatree and Line Vegan No-sebum Cool Anti Hairloss Shampoo',1,19800,'D001','Shampoo','2024-12-13 01:35:57',19800),(146,3,32,'ORDER0032',4,'Dr.SEED Super Seed Bomb Shampoo',1,19800,'D001','Shampoo','2024-12-13 01:35:57',19800),(147,3,32,'ORDER0032',5,'Dr.SEED Super Seed Bomb Treatment',1,19800,'D001','Treatment','2024-12-13 01:35:57',19800),(148,3,32,'ORDER0032',9,'CENTELLA ASIATICA EXTRACT',2,20000,'D001','Toner','2024-12-13 01:35:57',40000),(149,3,32,'ORDER0032',13,'CENTELLA ASIATICA EXTRACT',2,14000,'D001','Toner','2024-12-13 01:35:57',28000);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `pno` int NOT NULL AUTO_INCREMENT,
  `pname` varchar(100) DEFAULT NULL,
  `bname` varchar(100) DEFAULT NULL,
  `pdes` text,
  `pvol` varchar(100) DEFAULT NULL,
  `pprice` int DEFAULT NULL,
  `pcat` varchar(100) DEFAULT NULL,
  `pdel` varchar(10) DEFAULT NULL,
  `pqt` int DEFAULT NULL,
  `preadcnt` int DEFAULT NULL,
  `pop1` int DEFAULT NULL,
  `pimg1` varchar(1500) DEFAULT NULL,
  `pimg2` varchar(1500) DEFAULT NULL,
  `pimg3` varchar(1500) DEFAULT NULL,
  `pimg4` varchar(1500) DEFAULT NULL,
  `pimg5` varchar(1500) DEFAULT NULL,
  `pimg6` varchar(1500) DEFAULT NULL,
  `pimg7` varchar(1500) DEFAULT NULL,
  `pimg8` varchar(1500) DEFAULT NULL,
  `pimg9` varchar(1500) DEFAULT NULL,
  `pimg10` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`pno`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dr.SEED Black Bean Vegan Anti-Hairloss Shampoo','Dr. Seed','FRUITY FLORAL MUSK','500ml',19800,'Shampoo','G001',50,18,5000,'img/uploadFiles/e055a202-d150-4f42-86dd-d475c03cad65_1001.jpg','img/uploadFiles/7ee2417e-5ff1-4920-93ae-79bb457a4aeb_1001_1.jpg','img/uploadFiles/62d47fba-bea1-4317-ba7d-73678b085e15_1001_3.jpg','img/uploadFiles/00c83fcb-b7a3-40cc-9d9f-12ee4d578fb3_1001_4.jpg','img/uploadFiles/ffcf7c32-9a87-43fa-a51e-48c9aa740e90_1001_main.jpg',NULL,NULL,NULL,NULL,NULL),(2,'Dr.SEED Peppermint and Lemon Vegan Dandruff Cool Anti Hairloss Shampoo','Dr. Seed','CITRUS FRUITY FLORAL\r\n','500ml',19800,'Shampoo','G001',50,29,500,'img/uploadFiles/ef314fe7-bb35-4f1b-9644-07622ef7ded7_1002.jpg','img/uploadFiles/473f5a62-7d30-4ce1-9e21-c2155e1c0421_1002_1.jpg','img/uploadFiles/ff8e98d9-d1cf-4cbd-bd1b-1d88dbc60b28_1002_2.jpg','img/uploadFiles/b56c7c1a-33da-43ad-8f0a-02920c496f19_1002_4.jpg','img/uploadFiles/1928fb64-7737-4866-b62b-5a3dc3874be9_1002_main.png',NULL,NULL,NULL,NULL,NULL),(3,'Dr.SEED Teatree and Line Vegan No-sebum Cool Anti Hairloss Shampoo','Dr. Seed','CITRUS HERB FLORAL\r\n','500ml',19800,'Shampoo','G001',50,33,500,'img/uploadFiles/7b1b6765-9aa2-4e27-aec7-5f6499da7717_1003.png','img/uploadFiles/828f710d-fb5f-437c-b91f-9c06adcfbcdb_1003_1.png','img/uploadFiles/c6bc32f8-f981-4357-8817-93a8aa7bbb50_1003_2.png','img/uploadFiles/4558e989-f6d4-449d-b4d1-7b9bb2e8bf0e_1003_3.png','img/uploadFiles/4d0077a8-fb4c-46ae-8dbf-41f1e6c1ce44_1003_5.png','img/uploadFiles/d306cdec-36e7-46c5-82e8-65690274302f_1003_main.png',NULL,NULL,NULL,NULL),(4,'Dr.SEED Super Seed Bomb Shampoo','Dr. Seed','Bebe Musk\r\n','1000ml',19800,'Shampoo','G001',50,45,1000,'img/uploadFiles/34d385d4-b41d-4d11-9b22-7c780d0585ab_1004_1.jpg','img/uploadFiles/091d9193-e368-46d7-901c-fbfeae26b8e2_1004_2.jpg','img/uploadFiles/60542f3a-6ada-49e5-901b-106fc43cd737_1004_3.jpg','img/uploadFiles/75af826a-6962-4ee2-b4df-2e569988d49e_1004_main.png','img/uploadFiles/75af826a-6962-4ee2-b4df-2e569988d49e_1004_main.png',NULL,NULL,NULL,NULL,NULL),(5,'Dr.SEED Super Seed Bomb Treatment','Dr. Seed','Bebe Musk\r\n','1000ml',19800,'Treatment','G001',50,53,1000,'img/uploadFiles/cdc0bba5-8bd7-4667-85b9-97cbbb6c0bf4_1004.jpg','img/uploadFiles/80cdbb8d-b453-4345-acdb-f0aa011df09a_1004_1.jpg','img/uploadFiles/9114a9a4-798c-4f76-9d73-0043444a98d7_1004_3.jpg','img/uploadFiles/af3d3b8d-9ae8-4881-be5e-b74969cd77d7_1004_main.png','img/uploadFiles/af3d3b8d-9ae8-4881-be5e-b74969cd77d7_1004_main.png',NULL,NULL,NULL,NULL,NULL),(6,'Dr.SEED Honey & Milk Bomb Moisture Body Wash','Dr. Seed','Midnight Rose\r\n','1000ml',19800,'Bodywash','G001',50,60,1000,'img/uploadFiles/eabcee27-4c7b-4861-b0ae-0124e0116b61_1006.jpg','img/uploadFiles/22aca599-91cd-42aa-bdbe-6348c803738c_1006_1.jpg','img/uploadFiles/9ef19625-6c72-414b-a959-8e197d110688_1006_2.jpg','img/uploadFiles/b228c909-50cc-4faf-b8fa-7746618469de_1006_main.png','img/uploadFiles/b228c909-50cc-4faf-b8fa-7746618469de_1006_main.png',NULL,NULL,NULL,NULL,NULL),(7,'Dr. SEED Heartleaf B7 Vegan Repair Water Treatment','Dr. Seed','','500ml',22900,'Treatment','G001',50,74,500,'img/uploadFiles/033f1d79-523c-4544-860a-c8c2ecca2872_1007_1.png','img/uploadFiles/3ae00c15-536c-4ba1-8c7b-1b068a7d28de_1007_2.png','img/uploadFiles/eb2432c3-2d59-4578-b0b2-ab55e24429df_1007_3.jpg','img/uploadFiles/74f6eb55-f025-44a2-9e3b-00bbf742705b_1007_4.jpg','img/uploadFiles/0b148496-3609-44b0-a893-89fb02f67e60_1007_main.png',NULL,NULL,NULL,NULL,NULL),(8,'Dr.SEED Shampoo, Treatment, Body Wash 미니어처 3 종 세트','Dr. Seed','Bebe Musk, Midnight Rose\r\n','1000ml',10000,'Set','G001',50,81,1000,'img/uploadFiles/4cccbf54-ed1e-4f21-b578-1bdc4703580e_1008_main.png','img/uploadFiles/4cccbf54-ed1e-4f21-b578-1bdc4703580e_1008_main.PNG','img/uploadFiles/4cccbf54-ed1e-4f21-b578-1bdc4703580e_1008_main.PNG','img/uploadFiles/4cccbf54-ed1e-4f21-b578-1bdc4703580e_1008_main.PNG','img/uploadFiles/4cccbf54-ed1e-4f21-b578-1bdc4703580e_1008_main.PNG',NULL,NULL,NULL,NULL,NULL),(9,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Centella Asiatica Line for Soothing Care \r\n2. Domestically Sourced Centella Asiatica \r\n3. Moisturizing Toner Formulation\"\r\n','300ml',20000,'Toner','G001',50,95,300,'img/uploadFiles/8748c474-e9f5-4d02-93d5-aab9fe00a509_1009_1.jpg','img/uploadFiles/dcba2cd0-d63c-4d2f-8151-8a0251241290_1009_2.jpg','img/uploadFiles/f7177482-43a5-49c2-ad6e-fe3a6d50c3b4_1009_3.jpg','img/uploadFiles/51033fec-9ade-4405-93fa-bdc4e251b1e5_1009_4.jpg','img/uploadFiles/cf250025-625d-4f9e-86ff-485e766e86ef_1009_main.PNG',NULL,NULL,NULL,NULL,NULL),(10,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Centella Asiatica Line for Soothing Care \r\n2. Domestically Sourced Centella Asiatica \r\n3. Moisturizing Toner Formulation\"\r\n','100ml',14000,'Toner','G001',50,101,100,'img/uploadFiles/97da0a44-4fa0-4dc5-9baf-8f4b00f8d165_1010_1.jpg','img/uploadFiles/254cce71-2edc-4734-b234-2187d3a0092d_1010_2.jpg','img/uploadFiles/8be790fb-d8ca-49ef-ad80-969cc12c35c0_1010_3.jpg','img/uploadFiles/7349503b-8c41-4e0e-956f-3b3a885ef80a_1010_4.jpg','img/uploadFiles/51cf156f-a8f0-44f0-895b-0bd1fe424df7_1010_main.PNG',NULL,NULL,NULL,NULL,NULL),(11,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Centella Asiatica Line for Soothing Care \r\n2. Domestically Sourced Centella Asiatica \r\n3. Moisturizing Toner Formulation\"\r\n','50ml',7000,'Toner','G001',50,111,50,'img/uploadFiles/cae78456-cd14-42f5-9c9e-dcc6b3524796_1011_1.jpg','img/uploadFiles/52dc761b-e882-4219-a4eb-7c03b4217a6d_1011_2.jpg','img/uploadFiles/37d57cc8-6d50-4257-aeef-2c96b174f988_1011_3.jpg','img/uploadFiles/3d0f5f5a-e4c4-4d6c-b1ea-acae9c200d50_1011_4.jpg','img/uploadFiles/f2c7c8fd-ed68-4d79-8cd3-1f437a6e27dc_1011_main.jpg',NULL,NULL,NULL,NULL,NULL),(12,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Houttuynia Cordata Line for Acne-Prone Skin \r\n2. Domestically Sourced Houttuynia Cordata \r\n3. Moisturizing Toner Formulation\"\r\n','300ml',20000,'Toner','G001',50,121,300,'img/uploadFiles/13618ef4-5a36-4cd9-b646-41b13abc7412_1012.jpg','img/uploadFiles/e7c88365-95cf-4386-89ff-8419645205c5_1012_1.jpg','img/uploadFiles/ae5d2473-e96b-4d66-83a9-ceb09954b23c_1012_2.jpg','img/uploadFiles/89854aae-2d1d-4c1d-ab2e-395d7ce67f18_1012_3.jpg','img/uploadFiles/ec1a08fd-21e6-4d36-b5c5-aba1e95ce82a_1012_4.jpg',NULL,NULL,NULL,NULL,NULL),(13,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Houttuynia Cordata Line for Acne-Prone Skin \r\n2. Domestically Sourced Houttuynia Cordata \r\n3. Moisturizing Toner Formulation\"\r\n','100ml',14000,'Toner','G001',50,131,100,'img/uploadFiles/3450d93a-9c4a-485f-98b8-b2daea690696_1012.jpg','img/uploadFiles/27418d13-2604-4ee6-bf4a-f2243c05ed28_1012_1.jpg','img/uploadFiles/c684eeb3-8109-493d-860f-86204a386761_1012_3.jpg','img/uploadFiles/fff21d31-0979-41db-82d0-bd7e4c3ead57_1012_4.jpg','img/uploadFiles/f0cbb528-9f61-4d67-8965-b98f05a9c0f3_1012_5.jpg',NULL,NULL,NULL,NULL,NULL),(14,'CENTELLA ASIATICA EXTRACT','ONE THING','\"1. ONE THING Houttuynia Cordata Line for Acne-Prone Skin \r\n2. Domestically Sourced Houttuynia Cordata \r\n3. Moisturizing Toner Formulation\"\r\n','50ml',7000,'Toner','G001',50,140,50,'img/uploadFiles/9edff983-e3ed-4f20-ad23-fbaf55dbe2c0_1012.jpg','img/uploadFiles/8d23bf2e-4759-44bc-920f-380d6b9206a8_1012_1.jpg','img/uploadFiles/f7a84955-7abc-4eee-a805-4f7a3d4cefe2_1012_2.jpg','img/uploadFiles/0fea3dbd-22fd-4418-acb9-547accd51d25_1012_3.jpg','img/uploadFiles/8db01724-20d9-4f3b-9886-91aa4ea50ffe_1012_5.jpg',NULL,NULL,NULL,NULL,NULL),(15,'CENTELLA ASIATICA SERUM','ONE THING','\"1. Serum That Will Keep Your Skin Hydrated All Night Long \r\n2. Contains 4 Key Ingredients Derived from Centella Asiatica \r\n3. Serum Made with Real Centella Asiatica Leaves \r\n\"\r\n','50ml',25600,'Serum','G001',50,156,50,'img/uploadFiles/c27a6c59-d8d5-4e11-8f29-f50e3e88fb0a_1015_1.png','img/uploadFiles/78bd787f-4848-4f5a-b66b-10c97848af61_1015_2.png','img/uploadFiles/d8421d16-de71-46bd-b23c-14a92e20cd77_1015_3.png','img/uploadFiles/404b88c5-50b1-43c5-9e01-5ff4255ca69e_1015_4.png','img/uploadFiles/f1c761d9-ed91-4b59-bd28-9d88e56b4c15_1015_main.png',NULL,NULL,NULL,NULL,NULL),(16,'ONE THING CICA B5 MASK','ONE THING','\"1. Visible Reduction of Imperfections After 2 weeks \r\n2. Addressing Blemishes with Niacinamide and Green Tangerine \r\n3. Moisturizing Formula That Does Not Leave Behind Any Stickiness\"\r\n','50ml',28000,'Mask','G001',50,165,50,'img/uploadFiles/939fa92e-be23-47dd-834f-1d50a8126447_1016_1.jpg','img/uploadFiles/1f024a9b-50ba-4595-a555-7ae8ff045719_1016_2.jpg','img/uploadFiles/1c9ab6cb-964b-455d-9d27-272d1620e78c_1016_3.jpg','img/uploadFiles/320d4070-7dd9-4c6d-b16f-034d0e46748c_1016_4.jpg','img/uploadFiles/460bb5b7-8777-4ac9-a2a7-e0973c6c6942_1016_main.png',NULL,NULL,NULL,NULL,NULL),(17,'Cica Soothing Mask','ONE THING','\"1. The Power of CICA with a Skin-Calming Effect \r\n2. 4-Step Skin Soothing Solution \r\n3. Facial Mask That Perfectly Adheres to the Skin\"\r\n','50ml',20000,'Mask','G001',50,172,50,'img/uploadFiles/29a41beb-abdd-4d6f-a806-f741afcd6afc_1016.png','img/uploadFiles/04a11787-64a7-41f0-8f31-600603fb5b66_1016_1.jpg','img/uploadFiles/e7f3ea6e-46ff-4297-9d29-fc815064a5f2_1016_2.jpg','img/uploadFiles/f1283649-e9f5-4615-a6e1-00d4e19719c3_1016_3.jpg','img/uploadFiles/24943ad0-20f3-4d7a-9340-5ad57c55425c_1016_4.jpg',NULL,NULL,NULL,NULL,NULL),(18,'ONE THING CICA B5 TONER','ONE THING','\"1. Serum That Will Keep Your Skin Hydrated All Night Long \r\n2. Contains 4 Key Ingredients Derived from Centella Asiatica \r\n3. Serum Made with Real Centella Asiatica Leaves\"\r\n','150ml',23000,'Toner','G001',50,182,150,'img/uploadFiles/83a12e0c-c7ab-4987-ab38-4d3cd79f48c1_1017.png','img/uploadFiles/557a4a1d-69d3-48e2-9ac8-ec83bd727589_1017_1.jpg','img/uploadFiles/d6626e38-73d2-49cc-b5a4-f45d0efa6900_1017_2.jpg','img/uploadFiles/e4a57066-0182-40aa-955b-b10d975f7042_1017_3.jpg','img/uploadFiles/659fcdcb-1bc4-4468-b580-656b715264c1_1017_4.jpg',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `pno` int NOT NULL,
  `cno` int NOT NULL,
  `cid` varchar(100) DEFAULT NULL,
  `rating` int NOT NULL,
  `rcontext` text,
  `rdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rdel` int DEFAULT '1',
  PRIMARY KEY (`rid`),
  KEY `Review_Product_idx` (`pno`),
  KEY `Review_Customer_idx` (`cno`),
  CONSTRAINT `Review_Customer` FOREIGN KEY (`cno`) REFERENCES `customer` (`cno`),
  CONSTRAINT `Review_Product` FOREIGN KEY (`pno`) REFERENCES `product` (`pno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,7,3,'test3',3,'wdsa','2024-12-13 01:14:38',1);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 10:59:35
