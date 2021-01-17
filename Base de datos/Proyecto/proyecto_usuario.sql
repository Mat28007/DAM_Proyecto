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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'l','1','pou'),(2,'ff','356a192b7913b04c54574d18c28d46e6395428ab','ff'),(3,'gg','356a192b7913b04c54574d18c28d46e6395428ab','gg'),(4,'pp','07c342be6e560e7f43842e2e21b774e61d85f047','pp'),(5,'k','516b9783fca517eecbd1d064da2d165310b19759','k'),(6,'','da39a3ee5e6b4b0d3255bfef95601890afd80709',''),(7,'k','13fbd79c3d390e5d6585a21e11ff5ec1970cff0c','k'),(8,'k','13fbd79c3d390e5d6585a21e11ff5ec1970cff0c','k'),(9,'gg','54fd1711209fb1c0781092374132c66e79e2241b','g'),(10,'f','4a0a19218e082a343a1b17e5333409af9d98f0f5','f'),(11,'f','4a0a19218e082a343a1b17e5333409af9d98f0f5','f'),(12,'f','4a0a19218e082a343a1b17e5333409af9d98f0f5','f'),(13,'f','4a0a19218e082a343a1b17e5333409af9d98f0f5','f'),(14,'d','3c363836cf4e16666669a25da280a1865c2d2874','d'),(15,'mat','cc7c5be316e48d137cbb549833b85d91034d799d','mat'),(16,'mat','cc7c5be316e48d137cbb549833b85d91034d799d','mat'),(17,'matg','5de331cf2df529f1418736bc35dcab7c1c0d900e','matg'),(18,'caro','732849d5bc9473ec25bcbd5fd48dc071f6c3ff12','caro'),(19,'oo','0343bb07c98f8a943e8eb80c0ba3d9758d372d22','oo'),(20,'s','a0f1490a20d0211c997b44bc357e1972deab8ae3','s'),(21,'fg','72f77e84ba0149b2af1051f1318128dccf60ab60','fg'),(22,'fddd','4a0a19218e082a343a1b17e5333409af9d98f0f5','p'),(23,'mathieu','66468173c2b1642fcdd5bbb3e6b0f5ea20554ded','mathieu'),(24,'mat1','f43814b18f3b5b55a51ca1ad1e0e137c3dd5bfe9','mat1'),(25,'cao','cc7c5be316e48d137cbb549833b85d91034d799d','mat'),(26,'mm','b8d09b4d8580aacbd9efc4540a9b88d2feb9d7e5','mm'),(27,'MMIGUELEZ','b8d09b4d8580aacbd9efc4540a9b88d2feb9d7e5','MARC MIGUELEZ'),(28,'m','6b0d31c0d563223024da45691584643ac78c96e8','m'),(29,'mmm','91223fd10ce86fc852b449583aa2196c304bf6e0','mmm'),(30,'fre','c441f164b1283bd56e0aa24dabb133150397df87','fre'),(31,'carolina','c33873c987bc9d5bc6a51e095311d747b85a78e1','carolina'),(32,'mathieu12','cc7c5be316e48d137cbb549833b85d91034d799d','mathieu'),(33,'fff','f6949a8c7d5b90b4a698660bbfb9431503fbb995','fff'),(34,'caro1','37637ca8cef464b61a55102107bfa404b85233c2','caro1'),(35,'ii','3918373cf5559c54b52c7066428f6c4118d31c23','ii'),(36,'da','cdd4f874095045f4ae6670038cbbd05fac9d4802','da'),(37,'wrbwbrb','da39a3ee5e6b4b0d3255bfef95601890afd80709','ererbrb'),(38,'ffbeff','da39a3ee5e6b4b0d3255bfef95601890afd80709','ebetb'),(39,'sdc','da39a3ee5e6b4b0d3255bfef95601890afd80709','sdc'),(40,'u','da39a3ee5e6b4b0d3255bfef95601890afd80709','j'),(41,'i','da39a3ee5e6b4b0d3255bfef95601890afd80709','i'),(42,'kiki','95752f86c99f1055feba64e03924cb71f0c08315','kiki'),(43,'nuevo','5043f762841a8c17c7385efd931b64d46ce0b044','nuevo'),(44,'usuario 2','6d3236ec3c88039ca534b81acad564e847ecb062','usuario 2'),(45,'usuario1.2','6d3236ec3c88039ca534b81acad564e847ecb062','usuario1.2'),(46,'usuario1.3','6d3236ec3c88039ca534b81acad564e847ecb062','usuario 1.3'),(47,'usuario1.4','6d3236ec3c88039ca534b81acad564e847ecb062','usuario1.4');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
