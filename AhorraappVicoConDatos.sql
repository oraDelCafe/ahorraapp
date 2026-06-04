-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ahorrappvico
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
-- Table structure for table `cuentasapp`
--

DROP TABLE IF EXISTS `cuentasapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentasapp` (
  `CodCuentasApp` int NOT NULL AUTO_INCREMENT,
  `CodUsuarios_cuentasapp` int DEFAULT NULL,
  `SaldoCuentas` decimal(10,2) DEFAULT NULL,
  `FechaCuenta` date DEFAULT NULL,
  `CuentaBancoExterna` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`CodCuentasApp`),
  KEY `fk_usuarios_cuentasapp` (`CodUsuarios_cuentasapp`),
  CONSTRAINT `fk_usuarios_cuentasapp` FOREIGN KEY (`CodUsuarios_cuentasapp`) REFERENCES `usuarios` (`CodUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentasapp`
--

LOCK TABLES `cuentasapp` WRITE;
/*!40000 ALTER TABLE `cuentasapp` DISABLE KEYS */;
INSERT INTO `cuentasapp` VALUES (1,1,1500.00,'2026-06-04','ES1234567890123456789012'),(2,2,2500.00,'2026-06-04','ES9876543210987654321098'),(3,3,1000.00,'2026-06-04','ES1111222233334444555566');
/*!40000 ALTER TABLE `cuentasapp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleobjetivoprogramados`
--

DROP TABLE IF EXISTS `detalleobjetivoprogramados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleobjetivoprogramados` (
  `CodCuentasApp_detprog` int NOT NULL,
  `CodObjetivos_detprog` int NOT NULL,
  `CodDetalleObjetivos_detprog` int NOT NULL AUTO_INCREMENT,
  `fechaini_detprog` date DEFAULT NULL,
  `cantidadFija_detprog` decimal(11,2) unsigned DEFAULT NULL,
  `fechainactividad_detprog` date DEFAULT NULL,
  `fechafin_detprog` date DEFAULT NULL,
  `periodicidad_detprog` enum('diario','quicena','mensual','trimestral') DEFAULT NULL,
  PRIMARY KEY (`CodDetalleObjetivos_detprog`,`CodCuentasApp_detprog`,`CodObjetivos_detprog`),
  KEY `fk_cuentasapp_detalleprogramado` (`CodCuentasApp_detprog`),
  KEY `fk_objetivos_detalleprogramado` (`CodObjetivos_detprog`),
  CONSTRAINT `fk_cuentasapp_detalleprogramado` FOREIGN KEY (`CodCuentasApp_detprog`) REFERENCES `cuentasapp` (`CodCuentasApp`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_objetivos_detalleprogramado` FOREIGN KEY (`CodObjetivos_detprog`) REFERENCES `objetivos` (`CodObjetivos`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleobjetivoprogramados`
--

LOCK TABLES `detalleobjetivoprogramados` WRITE;
/*!40000 ALTER TABLE `detalleobjetivoprogramados` DISABLE KEYS */;
INSERT INTO `detalleobjetivoprogramados` VALUES (1,1,1,'2026-06-04',300.00,NULL,'2029-05-04','mensual'),(2,2,2,'2026-06-04',150.00,NULL,'2028-11-04','mensual'),(3,3,3,'2026-06-04',100.00,NULL,'2027-02-04','mensual');
/*!40000 ALTER TABLE `detalleobjetivoprogramados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleobjetivopuntual`
--

DROP TABLE IF EXISTS `detalleobjetivopuntual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleobjetivopuntual` (
  `CodDetalleObjetivoPuntual` int NOT NULL AUTO_INCREMENT,
  `CodObjetivos_detpunt` int NOT NULL,
  `CodCuentasApp_detpunt` int NOT NULL,
  `cantidad_detpunt` decimal(11,2) unsigned NOT NULL,
  `fecha_detpunt` datetime NOT NULL,
  PRIMARY KEY (`CodDetalleObjetivoPuntual`),
  KEY `fk_cuentasapp_detallepuntual` (`CodCuentasApp_detpunt`),
  KEY `fk_objetivos_detallepuntual` (`CodObjetivos_detpunt`),
  CONSTRAINT `fk_cuentasapp_detallepuntual` FOREIGN KEY (`CodCuentasApp_detpunt`) REFERENCES `cuentasapp` (`CodCuentasApp`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_objetivos_detallepuntual` FOREIGN KEY (`CodObjetivos_detpunt`) REFERENCES `objetivos` (`CodObjetivos`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleobjetivopuntual`
--

LOCK TABLES `detalleobjetivopuntual` WRITE;
/*!40000 ALTER TABLE `detalleobjetivopuntual` DISABLE KEYS */;
INSERT INTO `detalleobjetivopuntual` VALUES (1,1,1,200.00,'2026-06-04 10:30:00'),(2,2,2,100.00,'2026-06-04 11:00:00'),(3,3,3,50.00,'2026-06-04 11:30:00');
/*!40000 ALTER TABLE `detalleobjetivopuntual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivos`
--

DROP TABLE IF EXISTS `objetivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivos` (
  `CodObjetivos` int NOT NULL AUTO_INCREMENT,
  `nombreObj` varchar(100) DEFAULT NULL,
  `cantidadObj` decimal(10,2) unsigned DEFAULT NULL,
  `descipObj` varchar(300) DEFAULT NULL,
  `cantConseObj` decimal(11,2) DEFAULT NULL,
  `CodUsuarios_obj` int DEFAULT NULL,
  PRIMARY KEY (`CodObjetivos`),
  KEY `fk_usuarios_objetivos` (`CodUsuarios_obj`),
  CONSTRAINT `fk_usuarios_objetivos` FOREIGN KEY (`CodUsuarios_obj`) REFERENCES `usuarios` (`CodUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivos`
--

LOCK TABLES `objetivos` WRITE;
/*!40000 ALTER TABLE `objetivos` DISABLE KEYS */;
INSERT INTO `objetivos` VALUES (1,'Coche',12000.00,'Comprar coche',1500.00,1),(2,'Viaje',3000.00,'Viaje de vacaciones',500.00,2),(3,'Portatil',1000.00,'Comprar portatil nuevo',200.00,3);
/*!40000 ALTER TABLE `objetivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencia`
--

DROP TABLE IF EXISTS `transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencia` (
  `CodTransferencia` int NOT NULL AUTO_INCREMENT,
  `fechaTrans` date NOT NULL,
  `tipoTrans` enum('Ingreso','Gasto') DEFAULT NULL,
  `cantidadTrans` decimal(10,2) DEFAULT NULL,
  `conceptoTrans` varchar(300) DEFAULT NULL,
  `codigoTransaccion` varchar(100) DEFAULT NULL,
  `CodCuentas_trans` int DEFAULT NULL,
  PRIMARY KEY (`CodTransferencia`),
  KEY `fk_cuentas_cuentasapp` (`CodCuentas_trans`),
  CONSTRAINT `fk_cuentas_cuentasapp` FOREIGN KEY (`CodCuentas_trans`) REFERENCES `cuentasapp` (`CodCuentasApp`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia`
--

LOCK TABLES `transferencia` WRITE;
/*!40000 ALTER TABLE `transferencia` DISABLE KEYS */;
INSERT INTO `transferencia` VALUES (1,'2026-06-04','Ingreso',1500.00,'Ingreso inicial','INPR1760000000001',1),(2,'2026-06-04','Gasto',100.00,'Compra supermercado','GAPR1760000000002',1),(3,'2026-06-04','Ingreso',2500.00,'Ingreso inicial','INPE1760000000003',2),(4,'2026-06-04','Gasto',200.00,'Pago factura','GAPE1760000004',2),(5,'2026-06-04','Ingreso',1000.00,'Ingreso inicial','INMA1760000000005',3);
/*!40000 ALTER TABLE `transferencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `CodUsuarios` int NOT NULL AUTO_INCREMENT,
  `nombreUsu` varchar(50) DEFAULT NULL,
  `Ape1Usu` varchar(50) DEFAULT NULL,
  `Ape2Usu` varchar(50) DEFAULT NULL,
  `pass` varchar(9) NOT NULL,
  `emailUsu` varchar(100) DEFAULT NULL,
  `dniUsu` varchar(9) DEFAULT NULL,
  `dirUsu` varchar(150) DEFAULT NULL,
  `tlfUsu` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`CodUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'prueba','Lopez','Garcia','prueba123','prueba12@gmail.com','12345678A','Calle Prueba 1','600111222'),(2,'Pedro','Ortega','Nuñez','Pedro123','pedro12@gmail.com','87654321B','Calle Luna 5','600333444'),(3,'Maria','Ruiz','Martin','Maria123','maria12@gmail.com','11223344C','Avenida Sol 10','600555666');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-05  0:08:15
