-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Denominacion_Social` varchar(45) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `Codigo_Postal` int(11) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Ciudad` varchar(45) DEFAULT NULL,
  `Provincia` varchar(45) DEFAULT NULL,
  `CreatedBy` int(11) NOT NULL,
  `CreatedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Denominacion_Social_UNIQUE` (`Denominacion_Social`),
  KEY `CreatedBy_idx` (`CreatedBy`),
  KEY `FK_CreatedBy_idx` (`CreatedBy`),
  CONSTRAINT `FK_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (54,'denominación de la empresa...','dirección de la empresa...',28003,125,'email...','Madrid','Madrid',10,NULL),(56,'june','dirección de la empresa...',45112,125,'email...','Burguillos de Toledo','Toledo',10,NULL),(60,'cliente1','dirección de la empresa...',21000,125,'email...','Huelva','Huelva',10,NULL),(61,'cliente3','dirección de la empresa...',28007,125,'email...','Madrid','Madrid',10,NULL),(62,'cliente4','dirección de la empresa...',28007,125,'email...','Madrid','Madrid',10,NULL),(64,'hg','',28007,125,'email...','Madrid','Madrid',10,NULL),(65,'ff','dddddd',15009,125,'mat@gmail.com','A Coruña','A Coruña',10,NULL),(66,'ffff','',28007,125,'email...','Madrid','Madrid',10,NULL),(67,'dd','ggg',28007,125,'m@hshdd.com','Madrid','Madrid',10,NULL),(69,'ee','',14008,666666,'msm@nf.com','Córdoba','Córdoba',10,NULL),(70,'aa','',28009,125,'email...','Madrid','Madrid',10,NULL),(71,'ii','calle',13005,125,'mat@h.com','Ciudad Real','Ciudad Real',10,NULL),(72,'aaa','',28007,125,'email...','Madrid','Madrid',10,NULL),(73,'wwww','',28007,125,'email...','Madrid','Madrid',10,NULL),(74,'www','',28007,125,'email...','Madrid','Madrid',10,NULL),(75,'cliente provvedor','clll',28009,159357,'email...','Madrid','Madrid',10,NULL),(76,'wsa','dd',28007,125,'email...','Madrid','Madrid',10,NULL),(77,'poiuy','ff',28007,125,'email...','Madrid','Madrid',10,NULL),(78,'swsw','fdfd',28009,125,'mmama@mama.com','Madrid','Madrid',10,NULL),(79,'kk','kk',28007,125,'email...','Madrid','Madrid',10,NULL),(80,'jjj','jjj',28007,125,'email...','Madrid','Madrid',10,NULL),(81,'oo','oo',28007,125,'email...','Madrid','Madrid',10,NULL),(82,'ddd','dd',28007,125,'email...','Madrid','Madrid',10,NULL),(83,'ppp','pppp',0,125,'email...','Madrid','Madrid',10,NULL),(84,'ytr','rrrr',29008,125,'email...','Málaga','Málaga',10,NULL),(85,'ooooo','ppp',0,125,'email...','Madrid','Madrid',10,NULL),(87,'yy','yy',0,125,'email...','Madrid','Madrid',10,NULL),(90,'uu','uu',28009,125,'email...','Madrid','Madrid',10,NULL),(91,'rr','rr',28009,125,'email...','Madrid','Madrid',10,NULL),(92,'cc','cc',28009,125,'email...','Madrid','Madrid',10,NULL),(93,'bb','bb',29008,125,'email...','Málaga','Málaga',10,NULL),(94,'pppp','jjj',28009,125,'email...','Madrid','Madrid',10,NULL),(95,'vvcv','freddddd',28009,1252555,'matttts@gmail.com','Madrid','Madrid',10,NULL),(96,'hgff','gf',29008,125,'email...','Málaga','Málaga',10,NULL),(97,'kjh','hh',28007,125,'mat@jj.com','Madrid','Madrid',10,NULL),(98,'hgf','dd',28009,125,'Fluid-Attacks@gmail.com','Madrid','Madrid',10,NULL),(99,'ewww','wwsss',28007,125,'matoo_4@hotmail.com','Madrid','Madrid',10,NULL),(100,'fffff','gfd',29008,125,'caro10@hotmail.info','Málaga','Málaga',10,NULL),(101,'new h','dd',28009,125,'wvwvv@wef.com','Madrid','Madrid',10,NULL),(103,'ffiii','dfb',28009,125,'mama@Hh.com','Madrid','Madrid',10,NULL),(104,'rfrffrf','wefwf',28009,125,'ma@haha.com','Madrid','Madrid',10,NULL),(105,'ggf','fwv',28009,125,'wrv@hshs.com','Madrid','Madrid',10,NULL),(106,'yhv','kjbv',28009,125,'jhv@ug.com','Madrid','Madrid',10,NULL),(107,'eq','ef',28009,125,'dv@hh.com','Madrid','Madrid',1,NULL),(108,'ebeb','erbr',28009,125,'berb@hh.com','Madrid','Madrid',1,NULL),(128,'wqf','wfq',28009,125,'qwf@k.com','Madrid','Madrid',10,NULL),(129,'qwf','wqf',28009,125,'qwf@k.com','Madrid','Madrid',10,NULL),(130,'wev','pwein',28009,125,'wec@k.com','Madrid','Madrid',10,NULL),(131,'vewev','ewv',28009,125,'wevwe@k.com','Madrid','Madrid',10,NULL),(132,'apnfe','lkonklnlkn',28005,6,'mm@j.com','Madrid','Madrid',10,NULL),(133,'onvowenv','onvwovn',28009,125,'sdv@wdv.com','Madrid','Madrid',10,NULL),(134,'na','na',28009,125,'mc@mc.com','Madrid','Madrid',10,NULL),(135,'new','new1',28009,125,'ma@jm.com','Madrid','Madrid',10,NULL),(136,'qevqe','qefq',28009,125,'mm@ffffff.om','Madrid','Madrid',10,NULL),(137,'av','aa',15005,125,'aa@aa.com','A Coruña','A Coruña',10,NULL),(138,'sdff','ff',28004,125,'nn@nn.com','Madrid','Madrid',10,NULL),(142,'jhhj','sdv',25006,125,'ju@jj.com','Lleida','Lleida',10,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-05 10:02:08
