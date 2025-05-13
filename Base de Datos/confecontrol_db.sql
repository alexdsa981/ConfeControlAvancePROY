-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: confecontrol
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `detalle_descuentos`
--

DROP TABLE IF EXISTS `detalle_descuentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_descuentos` (
  `id_detalle_descuento` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `monto` decimal(38,2) NOT NULL,
  `tipo_descuento_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_detalle_descuento`),
  KEY `FKfmkwwfx9i690lkhwpppl1asaj` (`tipo_descuento_id`),
  KEY `FKe1dx9snyxcwpdrokxhu42u8y1` (`usuario_id`),
  CONSTRAINT `FKe1dx9snyxcwpdrokxhu42u8y1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKfmkwwfx9i690lkhwpppl1asaj` FOREIGN KEY (`tipo_descuento_id`) REFERENCES `tipo_descuento` (`id_detalle_descuento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_paquete_lote`
--

DROP TABLE IF EXISTS `detalle_paquete_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_paquete_lote` (
  `id_detalle_paquete_lote` bigint NOT NULL AUTO_INCREMENT,
  `observacion` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `operacion_prenda_id` bigint NOT NULL,
  `paquete_lote_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_detalle_paquete_lote`),
  KEY `FKoobrteuayqbeo7gq1lye8dv4y` (`operacion_prenda_id`),
  KEY `FKpn39uyfbktj4qbtgqcaoxmh2i` (`paquete_lote_id`),
  KEY `FKbsvp3edd4u6t4meyeyalij1ax` (`usuario_id`),
  CONSTRAINT `FKbsvp3edd4u6t4meyeyalij1ax` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKoobrteuayqbeo7gq1lye8dv4y` FOREIGN KEY (`operacion_prenda_id`) REFERENCES `operacion_prenda` (`id_operacion_prenda`),
  CONSTRAINT `FKpn39uyfbktj4qbtgqcaoxmh2i` FOREIGN KEY (`paquete_lote_id`) REFERENCES `paquete_lote` (`id_paquete_lote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_trabajo`
--

DROP TABLE IF EXISTS `detalle_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_trabajo` (
  `id_detalle_trabajo` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `monto` decimal(38,2) NOT NULL,
  `detalle_paquete_lote_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_detalle_trabajo`),
  KEY `FKth6nj4d272n50k8vrq4ckpun6` (`detalle_paquete_lote_id`),
  KEY `FKly67kb2ekaan0udtktlfd25ls` (`usuario_id`),
  CONSTRAINT `FKly67kb2ekaan0udtktlfd25ls` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKth6nj4d272n50k8vrq4ckpun6` FOREIGN KEY (`detalle_paquete_lote_id`) REFERENCES `detalle_paquete_lote` (`id_detalle_paquete_lote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lote` (
  `id_lote` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_prenda` int NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `is_active` bit(1) NOT NULL,
  `usuario_id` bigint NOT NULL,
  `prenda_id` bigint NOT NULL,
  PRIMARY KEY (`id_lote`),
  UNIQUE KEY `UK3y6af4fhn32f43p7mui2cvlom` (`codigo`),
  KEY `FKkige82m6ssg4uuuxvukl8owly` (`usuario_id`),
  KEY `FK55buij99etq84t0lw1upwh1yw` (`prenda_id`),
  CONSTRAINT `FK55buij99etq84t0lw1upwh1yw` FOREIGN KEY (`prenda_id`) REFERENCES `prenda` (`id_prenda`),
  CONSTRAINT `FKkige82m6ssg4uuuxvukl8owly` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcacion`
--

DROP TABLE IF EXISTS `marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcacion` (
  `id_marcacion` bigint NOT NULL AUTO_INCREMENT,
  `estado_llegada` bit(1) DEFAULT NULL,
  `estado_salida` bit(1) DEFAULT NULL,
  `fecha` datetime(6) NOT NULL,
  `hora_entrada` time(6) DEFAULT NULL,
  `hora_salida` time(6) DEFAULT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_marcacion`),
  KEY `FK116uakg3ribxfiiw3ahgydg7p` (`id_usuario`),
  CONSTRAINT `FK116uakg3ribxfiiw3ahgydg7p` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operacion_prenda`
--

DROP TABLE IF EXISTS `operacion_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion_prenda` (
  `id_operacion_prenda` bigint NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio_feriado` decimal(38,2) NOT NULL,
  `precio_horas_extra` decimal(38,2) NOT NULL,
  `precio_normal` decimal(38,2) NOT NULL,
  `prenda_id` bigint NOT NULL,
  PRIMARY KEY (`id_operacion_prenda`),
  KEY `FKklol07yi09828569sg283ori0` (`prenda_id`),
  CONSTRAINT `FKklol07yi09828569sg283ori0` FOREIGN KEY (`prenda_id`) REFERENCES `prenda` (`id_prenda`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pago_trabajo`
--

DROP TABLE IF EXISTS `pago_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago_trabajo` (
  `id_pago_trabajo` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `subtotal_descuento` decimal(38,2) NOT NULL,
  `subtotal_pago` decimal(38,2) NOT NULL,
  `totalapagar` decimal(38,2) NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_pago_trabajo`),
  KEY `FKhx1dhpl9gw39gfnt0g9g6lm26` (`usuario_id`),
  CONSTRAINT `FKhx1dhpl9gw39gfnt0g9g6lm26` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paquete_lote`
--

DROP TABLE IF EXISTS `paquete_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete_lote` (
  `id_paquete_lote` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `lote_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_paquete_lote`),
  KEY `FKnnfc6ywtv0gmjncktv8i6icwh` (`lote_id`),
  KEY `FK9xw8jmsrmbiui1w2yfx7pxak0` (`usuario_id`),
  CONSTRAINT `FK9xw8jmsrmbiui1w2yfx7pxak0` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FKnnfc6ywtv0gmjncktv8i6icwh` FOREIGN KEY (`lote_id`) REFERENCES `lote` (`id_lote`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pieza_prenda`
--

DROP TABLE IF EXISTS `pieza_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pieza_prenda` (
  `id_pieza_prenda` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `prenda_id` bigint NOT NULL,
  PRIMARY KEY (`id_pieza_prenda`),
  KEY `FKdlloyk177kp3p3ey8ap24fqh2` (`prenda_id`),
  CONSTRAINT `FKdlloyk177kp3p3ey8ap24fqh2` FOREIGN KEY (`prenda_id`) REFERENCES `prenda` (`id_prenda`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenda` (
  `id_prenda` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) NOT NULL,
  `costo_total` decimal(38,2) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_prenda`),
  UNIQUE KEY `UK5ff9calexcg0wpjp0m8n0xo4u` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_usuario` (
  `id_rol` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_descuento`
--

DROP TABLE IF EXISTS `tipo_descuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_descuento` (
  `id_detalle_descuento` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_detalle_descuento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `changed_pass` bit(1) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `dni` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `num_celular` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `rol_usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UKma71x4n4tydibsd9qt0m71le7` (`dni`),
  UNIQUE KEY `UK863n1y3x0jalatoir4325ehal` (`username`),
  KEY `FKhldivvrmovo1lkxebk495cecv` (`rol_usuario_id`),
  CONSTRAINT `FKhldivvrmovo1lkxebk495cecv` FOREIGN KEY (`rol_usuario_id`) REFERENCES `rol_usuario` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-12 18:02:59
