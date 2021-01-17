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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL AUTO_INCREMENT,
  `fkcategoria` int(11) NOT NULL,
  `nombre_articulo` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `precio_compra` decimal(10,2) NOT NULL,
  `inventario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idarticulo`),
  UNIQUE KEY `nombre_articulo_UNIQUE` (`nombre_articulo`),
  KEY `categoria` (`fkcategoria`),
  KEY `nombre_articulo` (`nombre_articulo`),
  KEY `fk_precio_compta_idx` (`precio_compra`),
  CONSTRAINT `fk_CatProd` FOREIGN KEY (`fkcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (76,1,'Mac Air','nueva desc',25.00,5.00,1),(78,13,'w','w',9.00,9.00,NULL),(79,14,'uu','uuuu',12.50,25.60,NULL),(82,5,'new','articulo 2',10.00,1.00,6),(84,1,'MacAir','descrpcion del articulo',10.00,5.00,1),(85,14,'MaacAir','descrpcion del articulo',10.00,5.00,1),(86,28,'aa','aa',5.00,6.00,NULL),(87,30,'art1','test100',989.86,788.80,NULL),(88,31,'art10','test21',5.00,6.00,NULL),(89,30,'art2','test200',77.00,7.00,NULL),(90,34,'art 1.1','nuevo art	',20.00,4.00,NULL),(91,34,'art 1.2','nuevo art	',25.00,5.60,NULL),(92,34,'art1.1','nuevo art	',20.00,4.00,NULL),(93,37,'Nuevo articulo cat 1 ','Ordenador Acer ES 15, Intel@Core i5',2000.00,750.00,NULL),(94,37,'Nuevo articulo cat 2 ','Ordenador HP FP16, TurboBoost up to 3.1 Ghz',780.99,740.69,NULL),(95,38,'art1.2','descripción del articulo',5.68,4.00,NULL),(96,38,'art1.3','descripción del articulo 1.3',1000.00,18.99,NULL),(97,39,'art 1.6','descripcion articulo	',58.60,42.00,NULL),(98,39,'art 1.25','descripcion articulo 1.2',58.00,12.00,NULL),(99,40,'art 2.5','descripcion articulo 2.5',58.60,45.86,NULL),(100,40,'art 2.6','descripcion articulo 2.6',589.00,45.86,NULL);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
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
