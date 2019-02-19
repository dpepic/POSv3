CREATE DATABASE  IF NOT EXISTS `pos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `pos`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: pos
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adresa` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ID pravnog lica` int(10) unsigned NOT NULL,
  `Postanski broj` varchar(6) NOT NULL,
  `Grad` varchar(45) NOT NULL,
  `Ulica` varchar(45) NOT NULL,
  `Broj` varchar(5) NOT NULL,
  `Potpuna adresa` varchar(110) GENERATED ALWAYS AS (concat(`Postanski broj`,_utf8mb4' ',`Grad`,_utf8mb4', ',`Ulica`,_utf8mb4' ',`Broj`)) VIRTUAL,
  PRIMARY KEY (`ID`),
  KEY `Pravno lice FK_idx` (`ID pravnog lica`),
  CONSTRAINT `Pravno lice FK` FOREIGN KEY (`ID pravnog lica`) REFERENCES `lice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresa`
--

LOCK TABLES `adresa` WRITE;
/*!40000 ALTER TABLE `adresa` DISABLE KEYS */;
INSERT INTO `adresa` (`ID`, `ID pravnog lica`, `Postanski broj`, `Grad`, `Ulica`, `Broj`) VALUES (7,125,'23300','Kikinda','Dositejeva','1256'),(8,127,'3456','Neki grad','Sa nekom ulicom','5'),(9,128,'234','Asd','ed','2'),(16,128,'23000','Kikinda','Dostijeva','124'),(17,136,'123','rrr','fdd','34'),(18,138,'330000','Grad','ulica','33'),(19,128,'223344','Neki tamo','123','321'),(47,128,'234234','sdfsdf','43t4','sdf'),(48,128,'11222','sdfsdfAsds','234234','sdf'),(49,128,'234234','sdfsdf','43t4','sdf'),(50,128,'234','Asd','ed','2'),(51,128,'234','Asd','ed','2'),(52,128,'23400','Asd','ed','2'),(53,128,'23400o','Asd','ed','2');
/*!40000 ALTER TABLE `adresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `artikal` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) NOT NULL,
  `Lager` double unsigned NOT NULL,
  `Merna jedinica` varchar(10) NOT NULL DEFAULT 'kom',
  `Ulazna cena` double unsigned NOT NULL,
  `Marza procenat` int(10) unsigned NOT NULL,
  `Cena sa marzom` double GENERATED ALWAYS AS ((`Ulazna cena` + (`Ulazna cena` * (`Marza procenat` / 100)))) STORED,
  `Porez procenat` int(10) unsigned NOT NULL,
  `Izlazna cena` double GENERATED ALWAYS AS ((`Cena sa marzom` + (`Cena sa marzom` * (`Porez procenat` / 100)))) STORED,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Naziv_UNIQUE` (`Naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` (`ID`, `Naziv`, `Lager`, `Merna jedinica`, `Ulazna cena`, `Marza procenat`, `Porez procenat`) VALUES (4,'Kafaaa',170,'g',20,35,20),(9,'Test',2,'kom',15,20,25),(10,'Plazam',117,'kom',300,25,17),(11,'Tamo nesto',175,'kom',25,17,15);
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lice`
--

DROP TABLE IF EXISTS `lice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lice` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `JIB` varchar(12) NOT NULL,
  `PIB` varchar(11) NOT NULL,
  `Naziv` varchar(45) DEFAULT NULL,
  `Tel` varchar(45) DEFAULT NULL,
  `Fax` varchar(45) DEFAULT NULL,
  `e-mail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `JIB_UNIQUE` (`JIB`),
  UNIQUE KEY `PIB_UNIQUE` (`PIB`),
  UNIQUE KEY `Naziv_UNIQUE` (`Naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lice`
--

LOCK TABLES `lice` WRITE;
/*!40000 ALTER TABLE `lice` DISABLE KEYS */;
INSERT INTO `lice` VALUES (125,'112233','332211','Osma ','12345','123456','asd@asd.asd'),(127,'667788','887766','Proba unosa','4455','5544','qwe@qwe.qwe'),(128,'45677','776654','Nova neka tamo','123','15235','asd@asd.asdasdasd'),(136,'qwe','ewq','asd','asd','asd','asd'),(137,'88776655','Nesto tamo','Polje','wfr','wef','33'),(138,'ttyy','yytt','Ova mora da radi','Mozda','Ako','Ponekad');
/*!40000 ALTER TABLE `lice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `licesaadresom`
--

DROP TABLE IF EXISTS `licesaadresom`;
/*!50001 DROP VIEW IF EXISTS `licesaadresom`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `licesaadresom` AS SELECT 
 1 AS `ID`,
 1 AS `Naziv`,
 1 AS `Tel`,
 1 AS `Fax`,
 1 AS `e-mail`,
 1 AS `Adresa`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `racun` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Datum izdavanja` datetime NOT NULL,
  `Datum valute` datetime DEFAULT NULL,
  `Lice` int(10) unsigned DEFAULT NULL,
  `Total` double unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PravnoLice_idx` (`Lice`),
  CONSTRAINT `PravnoLice` FOREIGN KEY (`Lice`) REFERENCES `lice` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
INSERT INTO `racun` VALUES (3,'2011-01-01 00:00:00',NULL,NULL,6636.15),(7,'1900-05-05 00:00:00',NULL,NULL,1496.25),(11,'2019-02-14 10:35:08',NULL,NULL,6480),(12,'2019-02-14 11:05:42',NULL,NULL,1620),(14,'2019-02-14 11:07:30',NULL,NULL,4486.5),(15,'2019-02-14 11:08:02',NULL,NULL,162),(16,'2019-02-14 11:08:19',NULL,NULL,112.5),(18,'2019-02-14 11:26:07',NULL,NULL,184.5),(19,'2019-02-18 11:10:53',NULL,NULL,816.1875);
/*!40000 ALTER TABLE `racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racun_artikal`
--

DROP TABLE IF EXISTS `racun_artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `racun_artikal` (
  `ID racuna` int(10) unsigned NOT NULL,
  `ID artikla` int(10) unsigned NOT NULL,
  `Kolicina` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID racuna`,`ID artikla`),
  KEY `artikalFK` (`ID artikla`),
  CONSTRAINT `artikalFK` FOREIGN KEY (`ID artikla`) REFERENCES `artikal` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `racunFK` FOREIGN KEY (`ID racuna`) REFERENCES `racun` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun_artikal`
--

LOCK TABLES `racun_artikal` WRITE;
/*!40000 ALTER TABLE `racun_artikal` DISABLE KEYS */;
INSERT INTO `racun_artikal` VALUES (3,4,1),(3,9,1),(3,10,15),(7,9,8),(7,10,3),(11,4,200),(12,4,50),(14,4,50),(14,10,5),(14,11,20),(15,4,5),(16,9,5),(18,4,5),(18,9,1),(19,4,20),(19,11,5);
/*!40000 ALTER TABLE `racun_artikal` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `racun_artikal_AFTER_INSERT` AFTER INSERT ON `racun_artikal` FOR EACH ROW BEGIN
	SELECT SUM(a.`Izlazna cena` * ar.kolicina) 
		FROM artikal a
		INNER JOIN racun_artikal ar ON a.ID = ar.`ID artikla`
		WHERE `ID racuna` = new.`ID racuna`
		INTO @totalZaRacun;
    
		UPDATE artikal
        SET Lager = Lager - new.`Kolicina`
        WHERE ID = new.`ID artikla`;
        
		UPDATE racun 
		SET Total = @totalZaRacun
		WHERE ID = new.`ID racuna`;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `racun_artikal_AFTER_UPDATE` AFTER UPDATE ON `racun_artikal` FOR EACH ROW BEGIN
SELECT SUM(a.`Izlazna cena` * ar.kolicina) 
		FROM artikal a
		INNER JOIN racun_artikal ar ON a.ID = ar.`ID artikla`
		WHERE `ID racuna` = new.`ID racuna`
		INTO @totalZaRacun;
    
		UPDATE racun 
		SET Total = @totalZaRacun
		WHERE ID = new.`ID racuna`;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `racun_artikal_AFTER_DELETE` AFTER DELETE ON `racun_artikal` FOR EACH ROW BEGIN
SELECT SUM(a.`Izlazna cena` * ar.kolicina) 
		FROM artikal a
		INNER JOIN racun_artikal ar ON a.ID = ar.`ID artikla`
		WHERE `ID racuna` = deleted.`ID racuna`
		INTO @totalZaRacun;
    
		UPDATE racun 
		SET Total = @totalZaRacun
		WHERE ID = deleted.`ID racuna`;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'pos'
--

--
-- Dumping routines for database 'pos'
--
/*!50003 DROP PROCEDURE IF EXISTS `dajAdrese` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajAdrese`(IN IDlica VARCHAR(45))
BEGIN
	SELECT * FROM adresa
    WHERE `ID pravnog lica` = IDlica;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dajArtikal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajArtikal`(IN IDart INT)
BEGIN
	SELECT * FROM artikal
    WHERE ID = IDart;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dajArtikleProdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajArtikleProdate`(IN IDracuna INT)
BEGIN

IF (IDRacuna > 0) THEN
	SELECT a.Naziv
		FROM artikal a
		INNER JOIN racun_artikal ra ON ra.`ID artikla` = a.ID
		WHERE ra.`ID racuna` = IDracuna;
ELSE 
	SELECT DISTINCT a.Naziv
		FROM artikal a
		INNER JOIN racun_artikal ra ON ra.`ID artikla` = a.ID;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dajArtikleSaRacuna` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajArtikleSaRacuna`(IN IDracuna VARCHAR(45))
BEGIN
	SELECT a.ID, a.Naziv, ra.Kolicina, a.`Izlazna cena` * ra.Kolicina As 'Prodajna cena'
	FROM racun_artikal ra
	INNER JOIN artikal a ON ra.`ID artikla` = ID
	WHERE `ID racuna` = IDracuna;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dajFirmu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajFirmu`(IN PK VARCHAR(45))
BEGIN
	IF (PK >= 0) THEN
		SELECT * FROM lice
		WHERE ID = PK;
	ELSE
		SELECT * FROM lice
        ORDER BY ID DESC
        LIMIT 1;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dajTabelu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dajTabelu`(IN tip VARCHAR(45))
BEGIN
		CASE (tip)
			WHEN "lica" THEN
				SELECT * FROM licesaadresom
                GROUP BY ID;
			WHEN "artikli" THEN
				SELECT ID, ID, Naziv, Lager, `Merna jedinica`,
                `Ulazna cena`, `Marza procenat`, `Cena sa marzom`, `Porez procenat`, `Izlazna cena` FROM artikal;
			WHEN "racuni" THEN
				SELECT ID, ID, `Datum izdavanja`, `Datum valute`  FROM racun;
        END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `izmenaAdrese` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `izmenaAdrese`(IN PKad VARCHAR(45), IN postanski VARCHAR(45), IN grad VARCHAR(45),
								 IN ulica VARCHAR(45), IN broj VARCHAR(45), IN PKlica VARCHAR(45))
BEGIN
	IF (PKad >= 0) THEN
		UPDATE adresa
		SET `Postanski broj` = postanski, `Grad` = grad,
			`Ulica` = ulica, `Broj` = broj
		WHERE ID = PKad;
	ELSE 
		INSERT INTO adresa
        (`ID pravnog lica`, `Postanski broj`, `Grad`, `Ulica`, `Broj`)
        VALUES (PKlica, postanski, grad, ulica, broj);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `izmenaArtikla` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `izmenaArtikla`(IN PK VARCHAR(45), IN nzv VARCHAR(45), IN lager VARCHAR(45), IN ulazna VARCHAR(45),
                                  IN marza VARCHAR(45), IN porez VARCHAR(45))
BEGIN
	IF (PK >= 0) THEN
		UPDATE artikal
        SET `Naziv` = nzv, `Lager` = lager, `Ulazna cena` = ulazna,
			`Marza procenat` = marza, `Porez procenat` = porez
		WHERE ID = PK;
    ELSE
		INSERT INTO artikal
		(`Naziv`, `Lager`, `Ulazna cena`, `Marza procenat`,
		`Porez procenat`)
		VALUES(nzv, lager, ulazna, marza, porez);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `izmenaFirme` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `izmenaFirme`(IN PK VARCHAR(45), JIB VARCHAR(45), PIB VARCHAR(45), Naziv VARCHAR(45), 
                            Tel VARCHAR(45), Fax VARCHAR(45), Mail VARCHAR(45))
BEGIN
	IF (PK >= 0) THEN 
		UPDATE lice 
		SET `JIB` = JIB, `PIB` = PIB, `Naziv` = Naziv, 
			`Tel`=Tel, `Fax` = Fax, `e-mail`= Mail
		WHERE ID = PK;
	ELSE 
		INSERT INTO lice
        (`JIB`, `PIB`, `Naziv`, `Tel`, `Fax`, `e-mail`)
        VALUES (Jib, Pib, Naziv, Tel, Fax, Mail);
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nemaNaStanju` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nemaNaStanju`()
BEGIN
SELECT * FROM artikal
WHERE Lager = 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obrisiRed` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obrisiRed`(IN IDreda VARCHAR(45), IN tabela VARCHAR(45))
BEGIN
CASE (tabela)
	WHEN "artikal" THEN
		DELETE FROM artikal
		WHERE ID = IDreda;
	WHEN "lice" THEN
		DELETE FROM lice
        WHERE ID = IDreda;
	WHEN "racun" THEN
		DELETE FROM racun
        WHERE ID = IDreda;
END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `totalZaRacun` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `totalZaRacun`(IN IDrac INT)
BEGIN
SELECT SUM(a.`Izlazna cena`) 
	FROM artikal a
	INNER JOIN racun_artikal ar ON a.ID = ar.`ID artikla`
	WHERE `ID racuna` = IDrac;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `unosArtiklaSaRacuna` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `unosArtiklaSaRacuna`(IN IDarti VARCHAR(45), IN kol VARCHAR(45))
BEGIN
	SELECT ID FROM pos.racun
	ORDER BY `Datum izdavanja` DESC
	LIMIT 1 
    INTO @IDracuna;
    
    INSERT INTO racun_artikal
    VALUES(@IDracuna, IDarti, kol);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `unosRacuna` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `unosRacuna`()
BEGIN
	INSERT INTO racun
    (`Datum izdavanja`)
    VALUES (TIME(NOW()));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `licesaadresom`
--

/*!50001 DROP VIEW IF EXISTS `licesaadresom`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `licesaadresom` AS select `l`.`ID` AS `ID`,`l`.`Naziv` AS `Naziv`,`l`.`Tel` AS `Tel`,`l`.`Fax` AS `Fax`,`l`.`e-mail` AS `e-mail`,`a`.`Potpuna adresa` AS `Adresa` from (`lice` `l` left join `adresa` `a` on((`l`.`ID` = `a`.`ID pravnog lica`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-19 11:43:48
