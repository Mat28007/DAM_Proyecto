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
-- Table structure for table `supplier_invoice_line_items`
--

DROP TABLE IF EXISTS `supplier_invoice_line_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_invoice_line_items` (
  `idsupplier_invoice_line_items` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) NOT NULL,
  `id_num_factura` int(11) NOT NULL,
  `cantidad_comprada` int(11) DEFAULT NULL,
  `tax_rate_charged` int(11) DEFAULT NULL,
  `precio_compra` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idsupplier_invoice_line_items`),
  KEY `fk_prod_idx` (`id_producto`),
  KEY `fk_num_fac_idx` (`id_num_factura`),
  CONSTRAINT `fk_num_fac` FOREIGN KEY (`id_num_factura`) REFERENCES `supplier_invoices` (`idSupplier_invoices`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_prod` FOREIGN KEY (`id_producto`) REFERENCES `articulo` (`idarticulo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_invoice_line_items`
--

LOCK TABLES `supplier_invoice_line_items` WRITE;
/*!40000 ALTER TABLE `supplier_invoice_line_items` DISABLE KEYS */;
INSERT INTO `supplier_invoice_line_items` VALUES (13,89,45,NULL,NULL,NULL),(14,89,48,NULL,NULL,NULL),(15,76,55,NULL,NULL,NULL),(16,84,56,3,21,2.00),(17,89,56,35,4,20.00),(18,89,57,2,21,5.00),(19,87,57,20,21,50.60),(20,84,59,4,4,4.00),(21,84,60,4,10,4.00),(22,76,62,5,10,5.00),(23,90,63,25,21,8.00),(24,76,64,4,0,4.00),(25,84,65,5,10,5.00),(26,76,66,5,10,5.00),(27,84,67,4,10,4.00),(28,88,67,6,10,425.69),(29,93,68,5,21,954.50),(30,95,69,5,21,58.50),(31,84,69,5,21,89.00),(32,76,74,58,4,58.00),(33,99,77,9,21,47.00),(34,100,77,1,21,89.00);
/*!40000 ALTER TABLE `supplier_invoice_line_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-05 10:02:07
