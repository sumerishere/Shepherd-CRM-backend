-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: templategen
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `lead_name` varchar(255) DEFAULT NULL,
  `lead_uid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKll94ee3qq7j4cxsb4h7v343ov` (`lead_uid`),
  CONSTRAINT `FKll94ee3qq7j4cxsb4h7v343ov` FOREIGN KEY (`lead_uid`) REFERENCES `lead_follow_up` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_table`
--

DROP TABLE IF EXISTS `data_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_table` (
  `uid` bigint NOT NULL,
  `fields_data` json NOT NULL,
  `image` longblob,
  `pdf_files` longblob,
  `template_id` bigint NOT NULL,
  PRIMARY KEY (`uid`),
  KEY `FK4xob3ocmpgc66toyr3yg9s3j3` (`template_id`),
  CONSTRAINT `FK4xob3ocmpgc66toyr3yg9s3j3` FOREIGN KEY (`template_id`) REFERENCES `form_templates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_table`
--

LOCK TABLES `data_table` WRITE;
/*!40000 ALTER TABLE `data_table` DISABLE KEYS */;
INSERT INTO `data_table` VALUES (3892,'{\"email\": \"test@example.us\", \"Address\": \"Pune\", \"Full Name\": \"naushad shaikh\", \"course fees\": \"30000\", \"course type\": \"java fullstack development\", \"mobile number\": \"83783443\", \"fees compeletion\": \"30000\", \"course compeleted\": \"Yes\"}',NULL,NULL,1),(4550,'{\"email\": \"test@example.us\", \"Address\": \"pune\", \"Full Name\": \"priyanka kapadi\", \"course fees\": \"30000\", \"course type\": \"java fullstack development\", \"mobile number\": \"487387587\", \"fees compeletion\": \"20000\", \"course compeleted\": \"No\"}',NULL,NULL,1),(5076,'{\"email\": \"harkirat@gmail.com\", \"Address\": \"Pune\", \"Full Name\": \"harkirat singh\", \"course fees\": \"25000\", \"course type\": \"MERN Stack\", \"mobile number\": \"784784835\", \"fees compeletion\": \"10000\", \"course compeleted\": \"No\"}',NULL,NULL,1);
/*!40000 ALTER TABLE `data_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_fields_data`
--

DROP TABLE IF EXISTS `form_fields_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_fields_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `column_name` varchar(255) NOT NULL,
  `data_type` varchar(255) NOT NULL,
  `field_id` int NOT NULL,
  `template_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo4tm9n6bg02sjfijg82ap0ou2` (`template_id`),
  CONSTRAINT `FKo4tm9n6bg02sjfijg82ap0ou2` FOREIGN KEY (`template_id`) REFERENCES `form_templates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_fields_data`
--

LOCK TABLES `form_fields_data` WRITE;
/*!40000 ALTER TABLE `form_fields_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `form_fields_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_templates`
--

DROP TABLE IF EXISTS `form_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_templates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at(date_time)` datetime(6) DEFAULT NULL,
  `fields` json NOT NULL,
  `form_name(organization name)` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKak4rx45bj6uoqjsolm9dij8vr` (`user_name`),
  CONSTRAINT `FKak4rx45bj6uoqjsolm9dij8vr` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_templates`
--

LOCK TABLES `form_templates` WRITE;
/*!40000 ALTER TABLE `form_templates` DISABLE KEYS */;
INSERT INTO `form_templates` VALUES (1,'2024-09-23 15:36:00.000000','[{\"fieldId\": 1, \"dataType\": \"Text(String)\", \"columnName\": \"Full Name\"}, {\"fieldId\": 2, \"dataType\": \"Text(String)\", \"columnName\": \"Address\"}, {\"fieldId\": 3, \"dataType\": \"Number(int)\", \"columnName\": \"mobile number\"}, {\"fieldId\": 4, \"dataType\": \"Text(String)\", \"columnName\": \"email\"}, {\"fieldId\": 5, \"dataType\": \"Number(int)\", \"columnName\": \"fees compeletion\"}, {\"fieldId\": 6, \"dataType\": \"Number(int)\", \"columnName\": \"course fees\"}, {\"fieldId\": 7, \"dataType\": \"Text(String)\", \"columnName\": \"course type\"}, {\"fieldId\": 8, \"dataType\": \"Yes/No button(Radio)\", \"columnName\": \"course compeleted\"}]','testing shastra','$2a$10$WUyrpqI5vA/A0nHYNWS.ke0nKbkXXn69lH3hDT/82VAaog.N/0.pq','sumer@12');
/*!40000 ALTER TABLE `form_templates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_table`
--

DROP TABLE IF EXISTS `invoice_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `candidate_mail` varchar(255) DEFAULT NULL,
  `candidate_mobile` varchar(255) DEFAULT NULL,
  `candidate_name` varchar(255) DEFAULT NULL,
  `invoice_pdf` longblob,
  `organization_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_table`
--

LOCK TABLES `invoice_table` WRITE;
/*!40000 ALTER TABLE `invoice_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead_follow_up`
--

DROP TABLE IF EXISTS `lead_follow_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lead_follow_up` (
  `uid` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `assign_to` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `course_type` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `follow_up_date` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `refer_name` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead_follow_up`
--

LOCK TABLES `lead_follow_up` WRITE;
/*!40000 ALTER TABLE `lead_follow_up` DISABLE KEYS */;
INSERT INTO `lead_follow_up` VALUES (4298,'pune','shakila','hot','Java fullStack development','2024-09-23 19:00:00.000000','ksumer57@gmail.com','2024-09-27T00:30','83474844','sumer khan','BE/BTech','arnav','Referral',NULL),(4460,'pune','shakila','cold','Java fullStack development','2024-09-23 19:24:00.000000','offsumer597@gmail.com','2024-09-25T00:54','83474844','nilesh bhagat','BE/BTech','sumer','Referral',NULL),(6670,'pune','Assign name here','hot','Java fullStack development','2024-09-23 19:09:00.000000','ksumer@gmail.com','2024-09-27T00:38','83474844','ritika rai','BE/BTech','arnav','Referral',NULL),(7327,'pune','shakila','hot','Java fullStack development','2024-09-27 11:49:00.000000','ksumer597@gmail.com','2024-09-30T17:19','83474844','nilesh bhagat','BE/BTech','sumer','Referral',NULL),(8884,'pune','Sumer','warm','Automation Testing','2024-09-23 11:06:00.000000','offsumer59@gmail.com','2024-09-28T16:36','83474844','shakila shaikh','BE/BTech','sumer','Referral',NULL);
/*!40000 ALTER TABLE `lead_follow_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miss_out_lead`
--

DROP TABLE IF EXISTS `miss_out_lead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `miss_out_lead` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miss_out_lead`
--

LOCK TABLES `miss_out_lead` WRITE;
/*!40000 ALTER TABLE `miss_out_lead` DISABLE KEYS */;
/*!40000 ALTER TABLE `miss_out_lead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `logo` longblob,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0\ôx\Ôú\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\04ŸIDATx^\í\Ý	x”\å¹\Æ\ñg²o@B6 lÁD«€U\ÔÖ‚\ÕV)Öªµj­­v9]\Ü+Vl=ui­¶¶¶u\ÅV­\Äµln(\"\n\na_B !$!{Î¼“—ž\Ñ,$™ù\Þoûÿzµ\ò\Ì9\õ²0™¹¿¹\ç{\Þ@KK‹\0\0\0‰\Ñ\0\0>B\0\0\0À‡\0\0\0ø\0\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€\0\0\0\ð!\0\0\0>D\0\0\0À‡\0\0\0ø\0\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€ZZZ\ô/{fî¢·#û\0\0€n›>eb@ÿ²Gø\0\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€\0\0\0\ð!\0\0\0>D\0\0\0À‡,\Ý#\çLš \'„\Û_U-‹—¤§¶2z§É©\'Œ\ÒÂ­Z¿I6\ï,\ÑS[cFJA^¶žn\Þ\âw¤£Ÿù@  \Ó&Ÿ¤\'„\ÛV\\*+\×é©­!ýse\Ô\ð¡zB¸¥+VIye•žÚš<n´\ôIKÕ“\÷¨Ÿ¶ÿ\Ú,/\ío} þ>¶F:¹Lg\0\0\0u\÷²\Ý=z\ó7\0\0\0€\Þ\ÛU-w¼\Ñ\ñ§•v#\0\0\0e\åµMr\éÜ­\Ò\Ð\ì\Ümù\0\0\0¢H½\å\÷\Åm²­²¾\õ‡\"\0\0\0EN\îý\Ã\0\0\0ˆ§\÷þ\á\0\0\0Dzÿp\0\0\0\"\ä–\Þ?\0\0€\Ý\ã’\Þ?\0\0€¨\ÞÿW.\éý\Ã\0\0\0\è!·\õþ\á\0\0\0\ô€{ÿp\0\0\0zÀ½8\0\0\0Ý´|W+{ÿp\0\0\0º¡¢¶I.™»Å•½8\0\0\0]¤\Þ\ò/Ÿ¿Õµ½8\0\0\0]t\ï²RY°±RO\îF\0\0\0 T\ïû\Åzr?\0\0\0‡\á•\Þ?\0\0€N„\î\÷É½\÷ûw„\0\0\0@\'T\ï?ƒùûý\ãbúW\Ö \0\0\0\Ð»zÿ\ñù©O\0\0\0À<\Õû_:\Ï|ïŸ‘+L¤\'\ë\0\0\0øœC½ÿ\Öýf{u\Íÿ§/HA\ï„\Ö,D\0\0\0\às\î{×ž\Þÿ\'r\äË…}\ôd-\0\0\0a\ì\ìýo<%WO\Ö#\0\0\0 \êý\ë›\ì\éý­þ\â_8\0\0\0A~\èý\Ã\0\0\0úM½ÿ\r\öþ\á\0\0\0\ß{¿¸Ff\Ù\Ôû\ßd°\÷G\0\0\0øÚ¡=ÿ~\èý\Ã\0\0\0¾\å·\Þ?\0\0\à[~\ëý\Ã\0\0\0¾\ä\Ç\Þ?\0\0\à;v\õþ\é6\÷þ\á\0\0\0_±µ\÷ÿ’½½8\0\0ÀW~ÿž=½ÿ&d\Ë9G\Ø\Ûû‡#\0\0\0|C\õþ·-5\ßû\ËO‘›O\ÉÓ“3\0\0\0¾`g\ïÿ\è\ôÁŽ\èý\Ã\0\0\0ž§\Þ\ò¯¦\÷ÿ\0\0À\óT\ïÿ½ÿg\0\0\0žF\ï\ß>\0\0À³\èý;F\0\0\0x½\ç\0\0\0Oºß¦\Þÿ‡\':·\÷G\0\0\0xŽ\êýI\ï\ß)\0\0ÀST\ï©]{þ§\r–„X\ç\öþ\á\0\0\0\Ï8\Ôûo±©\÷\Ô\ÇÙ½8\0\0À3\èý»Ž\0\0\0\ðzÿ\î!\0\0\0\\Þ¿û\0\0\0W£\÷\ï\0\0À\Õþ°|-½ÿu.\ìý\Ã\0\0\0®µ¢¸Fn]²KO\æŒ\ÍK‘[\\\Øû‡#\0\0\0\\i½{þ\Ý\Øû‡#\0\0\0\\‡\Þ?r\0\0€\ë¨\Þ\ÞzzÿH\0\0\0®B\ï\0\0€k\ÐûG\0\0\à\nv\öþ\ôH\ïŽ\0\0\0p…l\êý¯Ÿ-_\ñH\ïŽ\0\0\0p<\Õû\ßbS\ï\ë©\Þ\éý\Ã\0\0\0ŽF\ïo\r\0\0À±\èý­C\0\0\08½¿u\0\0\0G¢\÷·\0\0\à8v\öþx¸\÷G\0\0\08ŠÝ½ÿ`\÷þ\á\0\0\0Gy\ð}{zÿø \÷hi‰\ìã•¹‹\Þ\î\ðo\Èq#\õ„p5µu²v\Ó6=µ•šœ$#†\Ô\Âm/)•\Ò}¿8\ÎÏ‘¾\é½\õ„p¬-’Ž~\æÁŸ\×\ãùym\×ÞŠJÙ²k·ž\Ú\Ê\Î\ì#s³\õ„p\ë6o—êƒµzjk\ä\ÐIIJÔ“\È\ê²z¹\è¥2ih6û\Ñÿ¨¬y\âKYãœþ?þ¼6w\ò=}\ÊÄˆþa-\r\0\0\0tUMS@nü8QJ\ë\Ì~8\Z\×\"¿:ºN²›\õ#\îi\0 \0\0\ØN]IþyS¼\ñ7\õz\åz×½ùG\0\0`»»\ãdyyœž\Ì9;¯Q\Æf4\é\É_\0\0\0[mªŽ‘§¶™ÿ\æý°\Ôf¹p€\Ù;\rœ„\0\0\0°\êý\ï/JF\Ã\ß&K‰m‘\ï\ÖKœs¾\óg\0\0`\Ûzÿ¡þ\ìý\Ã\0\0\0¶x¥Ä¦\Þ?·A\Æù´\÷g\ém€\ê¾\âþ\ÙYzB¸†\ÆFÙ½·\\Om%\Ä\ÇKvfºž®¢²Jª\ÔS[½{…\ö( ­»Ë‚ÿ\ÙÑl@\ä\ð\ó\Úu{y\å=µ•–œ,\é½\Ó\ô„p¥û*¤¾¡AOÿO\õþ³\Ö$JC‹\Ù\Ï\àG¤\Ç\ÈNN–x\\þ\î,-\ëpo‡\â\è=\0±11rÎ¤	zB¸ýUÕ²xùGzj+#øbr\ê	£\ô„p«\Öo’\Í;K\ô\ÔÖ˜…R\ÇR–\ö\Ì[üN§‹€¦M>IO·­¸TV®+\ÒS[Cú\çÊ¨\áC\õ„pKW¬\n†§*=µ²\ë~ÿ>‰±\ò\ÖeGºf\Õ\ïü%Ë¤©¹ãš‚=\0\0\0W±«\÷\÷Óžÿ® \0\0\0Œ±\ë~ÿ\ï\ë\'Ó†ûg\ÏW\0\0\0F´\Þ\ï¯\'sN\ÈK‘\Û&\å\ë	‡\0\0\0–;t¿¿\é/ý©\ÞÿQŸœ\ï\ß]\0\0€\åþ¼9\Þ\ßa\0\0\0K…zÿ}±z2\ç\ZzÿN\0\0\0–\ÙP\Ùb[\ï?‹Þ¿S\0\0€%\ö\×5É¯W·\Ðû;\0\0`‰ï½¼]Š;^\Úi	zÿ®#\0\0\0¢\îO+\ö\È\ÜO+\ôd½\×\0\0\0Qµ²¤Fn\\´KO\æ\Ðûw\0\05U\õ\Ír\Ù[¥®)²sfº‹Þ¿û\0\0€¨ùÁ‚\íR´¯NOf\Ðû\÷\0\0ª\÷fM\ÇÇœ[\å{c\éý{‚\0\0\0ˆ\Ø\êÒƒr\Ó\âb=™£zÿ\Û\'\Óû\÷\0\0\Õû_<w‹\Ô6v|v½\èý#C\0\0\0D„\Þß\0\0€{\èƒ2[zÿ«\éý#F\0\0\0\ô\È\Ç{\ÊM6\Ü\ï|.½4\0\0\0\Ý\êýŸ\ß\"m\êý\éý#F\0\0\0tÛµ¶\ËÃ½¿¢zÿ!\é\ôþ\Ñ@\0\0\0t‹\êýŸ\æ~\×#\0\0\0ºŒ\Þ\ß;\0\0€.¡\÷\÷\0\0 K\èý½…\0\0\08¬?\Û\Ôû_}½¿U\0\0€N©\ÞßŽ\óýU\ïÿ«)\ôþV!\0\0\0:TM\ï\ïY\0\0@‡Ôž;zÿ\ÏH\ïo1\0\0 ]v\öþÓL\×¬B\0\0\0´A\ï\ï}\0\0Àg\Ðûû\0\0\ðv\Ý\ïO\ïo\0\0\ð_YY&ÿ´¡\÷ÿ.½¿q\0\0@ˆ\êýoXh¾\÷“›\"w\ÐûG\0\0\0\Ø\Öû§\Æ\ä\ï_D\ïo\0\0À¶\Þÿ®\É9R˜™¨\'˜D\0\0\0Ÿ³«\÷Ÿš\Ó(\ç\ï¥\'˜F\0\0\0³«\÷/Hi–o¬\×\ì@\0\0\0Ÿ²«\÷OŠm‘\ë\n\ë%w [\ñ\Û\0>eW\ïÿÁ\õ’›d6t -\0\0ø\Ð\Ãv\õþ\Ù\rrR\ß&=ÁN\0\0\ð™O\ö\Ô\Ê\õv\ôþ\É-\ò‚=Án\0\0\ð‘P\ï?×ž\Þÿ\Ú#\ê\èý„?\n\0\ð\Õû¯\ß[«\'s.\\/y\ôþŽB\0\0\0Ÿ°³\÷ŸH\ï\ï8\0\0\ð»zÿ!iz‡\"\0\0€\Ç\Ù\Õû§%\Ä\È\õ£\ôþ\Å\0x\Üu¯\Ú\ÓûÿþÌ2 Ep\0\0x˜\êýÿ\ñ‰ù\Þÿ\Ê\ã³ä‚£2\ô\'\"\0\0€G\Ù\Õû\Ó/™\óý]€\0\0\0dWïŸš#Ÿ;X’\ãx{q:þ„\0Àƒ\ì\êý\ï?s Áùþ®@\0\0\0¡\÷GW\0\0ÀC\èý\ÑU\0\0\ðztZ\0\à\ôþ\è\0\0xÀ_?\ÜkK\ï\Åz·\"\0\0€Ë©\ÞÿÿÙ©\'sT\ï?û4z·\"\0\0€‹\Ñû£§ø“\0³s\Ï?½¿»\0\0À¥þfc\ï!½¿\ë\0\0À…Ö¨\Þ!½?zŽ\0\0\0.s¨\÷¯i \÷G\Ï\ñ§\0.\ó\ÃWwÈ§\ôþˆ\0\0\\D\õþO}²OO\æ|‡\Þ\ßs\0\0\àv\õþG\÷K’;\éý=‡\0\0\0.`k\ï?\Þß‹ø\0°³\÷\Þ7IO\ð’@KK‹þe\Ï\Ì]\ôv‡ƒ@\ð_¹ý\èŒ\Ú\Ó\Ð\Ø$e\åû\õ\ÔV||¬d¥\÷\Ñ\Â\í¯ª–šƒuzj«O¯TII\â‹J\í).\Û\'\Ò\ÑOl@$/+SWS[\'ûTë©­”\äDé“–ª§è›¿­I\îYÝ \'s¦\rŠ“§§ž)«\Ø/\r\rMzj++£\Ä\Ç\Å\ê	\áJ\ö”\\;~ž>eb\ð§¶\ç,\r\0\0€\Èì¨‰‘[\Ö$I\ÙOþe`J³\Ì:ªVøœØ±\"\r\0ü\Ñ€C\Õ\ß\ô·1\Ñø›bl‹\\;¬Ž7\ã\0\ê\ï›e\×Áˆ.\òz\äÛƒ\Z$?™w½Ž\0\0\0´pOœ¼¹\×|7~zv£|!«QO\ð2\0\08Œ\êýŸØš \'sT\ïQA½ž\àu\0\0pz˜\Â5\08½?L±\ô6À˜@@\Æ=\\O§\î+þ¸h‹ž\ÚJKN’£†\r\Ò\ÂmÝµ[v\ï«\ÐS[Cû\ç†\î-F[\Ë?ùT:ú‘þ¸Ê¸£\ÔÂ©›v–è©­œ\Ìt”Ÿ£§ž{ú\Ó*¹\ñ-\ó\çû\Ï‘&·M´fgËš[¥\ê`\ÇŒŽ)\ÌÞŽ¼ÿ\Ézi\î\ä=\Ú\Ñ{\0bcb\äœI\ô„pj™\Í\â\åé©­Œ\Þir\ê	£\ô„p«\Öo’Í¼Q(y\ÙzB¸y‹\ß	€\ödÁ0m\òIzB¸mÅ¥²r]‘ž\Ú\Z£†\ÕSÏ¨=ÿ“_o|Õ¯\Ú\ó¿ø[\Ã-[\õ»t\Å*)¯¬\ÒS[“Ç¶t‰’›\Í_²Lšš;~>°\0\0\\®:ø¦Ïž˜ÆŸ:\0\Ø\ìG6\íùÿ\ÝT\öüû\0\0l\ô\ÈG{e\Î\Ç\æ\Ï\÷¿ü¸¾2\ãh\Îj\ñ3\0\0\ØD\õþ?û=\çûÿú‹ý\õ¿\"\0\0€\r\èýa7ž\0`zØ\0\0\0†=ºŠ\Þ\ö#\0\0€Ak\Ëjå§¯\Óû\Ã~\0\00„\ÞNÂ³\0Q½ÿº2\ó½ÿ}S\Ðû£\r\0\0`W\ïÿ\í\ãú\Ê7Ž\Î\Ô\ðÿ\0\0`1»zÿ£T\ï\Z½?\ÚG\0\0\0\Ù\Ýû§\Ä\ó2\ö\ñ\Ì\0\0ý\Ø\Æ\ÞÿHzt‚\0\0\0yl\Õ>y’\ÞE\0\0\0¨\Þÿ^ß¡\'s\èý\ÑU\0\0ˆ2\Ûzÿxzt\Ï\0ˆ2\Ûzÿ3\éý\Ñu\0\0ˆ\"»zÿ\ËF\Óû£{\0\0%v\öþ¿a\Ï?º‰\0\0\0Q@\ï·\á\0Q@\ï·!\0\0@„\æom \÷‡\ë\0\0 ;\Æ\È=«\Ì_ù\Óû#R\0\0\è¡\Úf‘\ß%Hmc‹~\ÄzD\Ï\0\è¡G·$\ÈÎƒ\æ_F\ïe\Ï?¢€\0\0\0=°¤,N–ÿmÚ¥£û\Ê\Ìc\èý9\0\0t“\êý\ÕÕ¿iª\÷¿‹\ÞQB\0\0€n¨oy`c‚Ô™½ÝŸ\ÞQ\Ç3	\0º\á\ï[d[ù—NzD\0\0ºè½±²t½?¼\0\0\0]PR#\Û\ÐûÌ¢\÷‡5\0\0pµM-\ò;u¿S@?b½?¬Ä³\n\0\ã\öw\÷\Û\ÖûÈ¢\÷‡5\0\0Ð‰gÖ”Ë¿‹j\ôd\Î%£\èýa-\0\0t h_ü`Áv=™£zÿÿ=\Þ\Ö\"\0\0@;j[\Ï\÷¯R7þD\ïSx†@;~\ô\êY]zPO\æ\Ðû\Ã\0\0|Ž\êý_mþ|z˜D\0\0€0\ôþ\ð\0\0h\ôþ\ðžm\0 \Ù\Õû\ßC\ï\0\0 \èÙµ\ö\ôþ\ß\Z•)\Ñû\Ã\0\0¾§zÿ\ï¿bO\ïÿ\Û\Ó\è	0‹\0\0À\×\èý\áW<\ó\0øÚ_£\÷‡?\0\0ø–\êý[e¾\÷¿øXzØ\0\0À—\ì\ìý\ï>ƒ\Þ\ö#\0\0\ðz€\0\0À‡\èý\0\0Ÿ¡\÷Z\0\0ø\Æ\Ærz\à\0\0_\õþ\Ï\Óû‡\ðŒ\à?ym§¬¢\÷þ‹\0\0À\óT\ïÿèª½z2\ç›\ôþp0\0\0O³«\÷/\ÌLd\Ï?\0\0À³\ì\êý“\âZ{ÿ´^b\á\\<;x–]½ÿ}SÈ±\Ù\Ézœ‰\0\0À“þµ¶Â–\Þÿ\ëGe„ºÀ\é\0\0<\'\Ôû/°§\÷¿ÿÌzœ\0\0ÀS\õþ\êš\ô#f\Ðû\Ãmx¦\ð”ÿy\Þ\è\n\0\0\ÏP½ÿ#™\ïý\ÏO\ï\×!\0\0\ð»zÿÜ¤fùùq\\ù\Ã}\0\0\\Ï®\Þ_\Õý\×\ÖKJœ~\0p\0\0×³«\÷¿tp½¤˜]2D\0€«\Ù\ÕûŸÔ·I&e5\ê	p\0\0×²³\÷ÿN\ð\êp3\0\0WR½ÿ·\æ\Ú\×û\'Å¶\èG\0w\n´´D\ö$ž»\èm~\n\0\÷\×\Í	²pùo\ß]9´žþ\áÓ§L\è_\öŸ\0\0pe{\ãly\ó§\÷‡—\0\0¸JIm@Þ’ \'s\èý\á5\0\0®¡Ž\õ¿c¢4[û\ÓûÃ“\0\0\\\ã\ñm	²¥\Úü\Ë\Ö%ƒ¸\ß\ÞC\0\0\à\n\ïî‹“…¥\ö\ôþ“û\Ñû\Ã{\0\0owm@þ²™\Þˆ&\0\0GS½ÿ\ï\éý¨³t@lLŒœ3i‚žnUµ,^þ‘ž\Ú\Ê\è&§ž0JO·jý&Ù¼³DOmQ(y\ÙzB¸y‹ß‘Ž~\æ€L›|’žœ\ã\Ú\Û\åoš_\õû\à\Ù\ò­Q­Gün+.••\ëŠB¿nÏþ¹2jøP=!\Ü\Ò«¤¼²JOmM7Zú¤¥\ê	\á\æ/Y&M\Í\÷„=\0\0<\ë¹u¶¼ùmd\Æ\ßü¯\"\0\0p¤M\åur\Í+\æ\÷ü\ËH”?œ5PO€w\0\08N\è|\öü\'\Å\Å\È\ã\ç–4\õ\0À\ãx–pœŸÿg§|´\ÛüùþwŸ\Ñ_Fe\'\ë	\ð6\0\0GQ½ÿ_m\êý/\ÕWO€\÷\0\08½?`\0€#\Ðûf\ñŒ\à\ôþ€Y\0\0¶³«\÷?d:½?|‹\0\0ÀV¶\öþg\ÒûÃ¿\0\0lS\×\Ô\"ß²±\÷\ï•«ü‡\0\àcûƒ/ºs;©\ÞÿCzÿßžN\ï\0|ìš—·\Ë\Õ/m“\Æfb\0\ÌS½ÿ\Ã+\Ë\ôdŽ\êý/M\ï\0|\ê½]\Õ2\÷\Ó\nyb\õ>¹øù-¡[°\0S\èýû\0|\ê†E»þû\ñÿ\ö\Ëù\Ïn’*u\ð:`1zÀ\0>¤>z]¶£ZO­–l­’/ÿ£H\ölÔ\0Ö \÷œ\0\à3\õÁ«¯_.-\Ö\Óg­(®‘©O\É\Î\rú º\èý\ç \0ø\ÌC”…ú×Ž|º·V\Îxbƒ\í\ëøÿ\è	zÀY\0>RQ\Û$ÿû\În=ul[e½L³Á–µ¬\ð&;{ÿÇ¦\Óû\í!\0ø\Èo\Þ.‘}]\ìøK«\åì§Š\ä\í\Ï}W\0\è	»zÿÿ=½¿ŒÎ¡\÷\ÚC\0\ð‰-û\ë\å\Ït¯{­^­}\åE2oý~ý\Ð}ÿ¶±\÷¿Œ\Þ\èÀ\'n^¼+\ô1lw©ÿŽ\Ú\ðø\ê}ú \ë\èý\ç\"\0øÀ\ò]5\ò|\ð*¬§šZZ\ä{/m“û—\ïÑ\0‡w¨\÷WŸ$™D\ït\rÀ®_´3\âÿ\ê¿ýÂrË’]­\0‡aW\ï\×\éý® \0xœ\ê_?¿\ô\'\÷,+•½ºC8>\0±«\÷?oDº|û8z +\0\Ö\ÙÒŸHü%ø\Â~ùü­\Ò@\n@;6W\Ø\Óû\ÍH”Î¢\÷ºŠ\0\àa\ê[ÿ;Yú‰gÖ”\ËWŸ\Þ$Õœ€0‡¾4jG\ïÿ8½?\Ð-\0Rgý\ßÕ…¥?‘X¼\õ@\èü€®\î€\÷ý‚\Þp\r€Gý\æ\í\ÝFÞ˜\ß/®‘3\ç\É.\Î\ð=\Õû«z\È4z g\0¤–þ<´\Â\Ü-{k\Ëje\ê“:=c\0\ÞF\ï¸Àƒn]Ò³¥?‘P¡\ãŒ\'‹du)\çø\r½?\àN\0Q\É?·¶\çK\"±»ºAÎšS\Õ\Û\á|v\õþ¿¡\÷\"B\0\ðµ¬\ÇÎ›\óÔ—§=½Q^\ÝT©—=ÿ©}½ÿ\å\ôþ@D\027øbüŽ®¾k\Zše\Æs›\åÙµ\åúx‘\êý¿\÷2½?\àV\0PKynYý¥?=¥–]6o«<\ð>\çxQ\ëžÿ­\ôþ€‹\0<\â/.ý\é)UE¨}\ðœ\à=ªjZYR£\'s\èý\è!\0x€\ê\Ýý¶µK\"¡\Îø\Ékœ\àª\÷W[&M£\÷¢‹\0\àwZú‰‡‚oWp~€\ë\Ñû\ÞA\0p¹­û\ë\åO—þD\âŸk\Êe\æs›\å`#\ç¸‘]½bl€\Þ°\0À\ån]Rl|\éO$^\ÞX)\ç>½\Éø›\"G\ïxÀ\Å\ÔÒŸ¹\ðV»·¶W\É\éOn\â*\Îp;{ÿ\ïŒ\É\Ò€h\"\0¸\Ø\r6/ý‰Äš=­\ç¨N\ÎF\ïxÀ¥\æ­\ß/o»|\å\î\æŠ\Ö\ó>	†8“½ÿc\ôþ€¥\0.Ôº\ô\Ç\÷Ö—T5È™s6È»;9?À‰Ô§Lv\õþ\Ç\Ñû–\"\0¸\Ð\Ã+Ë¤hŸw>:¯¨m’iÿ\Ü(¯o> ¨\Þ_Ý¾i\Z½?`ÀeBK\Þr\îÒŸžªnh–þµIž[g\ÏI†ø,zÀû\0.\ó\Ûwv\Ë^‡/ý\é©C\çü\íÃ½ú\ØAUL—¿°\Þ\ð8€‹¨¥?\\aþ#Y“šZZ\äº\Ûe\ö›%ú˜vý\Â]\ò\Þ.\ó\ßÉ \÷\Ì\"\0¸\ÈmK‹¥\Ö[\ôÔ­³\ß*‘Ÿ¾\î\Þ\Û\Ýê¥¢JyÈ†Í’_¥\÷Œ#\0¸ÄŠ\â\Zyf™¥?ýW»ý1øFt\Í\Ë\ÛBŸ\nÀz»\ëb\ä;\ó·\Z]ª\÷\Þ0Ž\0\à\ê¶?S/\Ì\÷N \ß\Z•©\'{=¶jŸ\Ì|n\çXLm“~°(ž\Þ\ð€¨¥?K¶V\é\ÉZ\Ãû&É¥£û\ÊgÈµ\ã³\õ£\öz±h¿œ\÷\Ì&9Àù–yb[‚l¨2ÿr@\ïØ‡\0\àp\Í-¡\îß”\ÙS\ò%.&ªÔ¯gM\Êoý?\Ø\ìmU\ò¥l”²\Zo\Þa§*\â\äµ\Ýqz2‡\Þ°À\á\ÔÒŸO\÷šY•{JAšœ5¬·žZýxB¶\Ü7u€3\í\ÔFº©sŠdw\r\ß	ˆ–²ú€üic¼\ñ\ÞH:\÷ûv#\08XU}³\Ü\õŽ™¥?\ê\r^]\ñ·G]¥ý\õœA\ï€°>†®XR-;jx\êFJ\õþ(J”\ê&³®¡\óý\Ï,½\éý[\ñ*\ê`w½S\"¥\Õf>\òžqt¦Œ\ÉM\ÑS[_?*Cþ}ÁPIM°ÿ)SV\Û,³\Ö&\Ú\ÒY{É“6\õþ¿¦\÷WP‡*«ù“¡¥?Iq1r\ó)¹z\ê\Ø\äA½\ä\Å…’™l¾/þ<u\Õzç§‰²º’«ÈžP½ÿ«6\õþW\ÐûŽ@\0p¨¿oh‘š3·¾}l?\Ø;AO›—\"fJ~¯xýˆ}\ê‚!\à\îO\ä½}„€\î \÷ \0hKuŒ,\Ùm\æ\å9+%.\ôE¿\î™•$¯]t„\ËHÔØ§¡% \÷%Ê¢Rû?•pz\0‡\0h\Î\öi6tyv\ÃÉ¹=zQ\Ô\'A^½¨PFe\Û\ß\åª\ÏIþº%A\æ\Ûÿ©„\Ó=±5ž\Þ@Àa\Þ/•O*\Íü±f&\Êe\Ç\õ\ÕS\÷\å¤\Æ\Ë\Ë3\å¤©úû¨¼\ô\Ô\öxù\Ç\óm»…\Ú\óO\ï\à€ƒ¨gŸ¾™rû\äüˆo\í\ë“+s/&S‡~v€]^\Ø/7¾UZ „ÿ·½²^®z\ÑüžzÀ¹\0²°4Nv4\óGrbÿT9\çˆ>zŠLJ|Œ<}þ¹\ègœ\ðü\Æ\Z¹øù-¾89±+\Ôùþ—\Î\Û*\åµv\ìùD\ï8À!j›\ò\ï\àÕ«	\êšÿŽ)ùQ=\õO­þÓ—äš±ý\ô#\öza\Ã~9ÿ\ÙM¡eJ~w\ã¢]\ò\îN\ó\çû\ßyZÿNwK\0°À!\æíŠ“ý\rf¾™}þ\È™\Ð?ú½½ú§W‡»8\åü\0u€Ò—ÿQ${ú\÷ü€—7V\Ê\ß7¾ÿ¹G¦Ë•\Ç\ÓûNF\0p€}\õye·™«ÿ„Ø€\Üz\ê\á—þDB\ÝVx\Ï\Î8?`EqL}²HvhÐø‡\êý¯´\á|\Õû?x6½?\àt\0P\ß\\¯3\ôI\õwO\èz¶šºúû‹C\ÎP‡)\ñ\Ä)\ÚW§\ñ>z\0‡C\0°\ÙÖšy»\ÌÌ­Y\éI±\ò?\Ý\\ú‰Ê9\ç\r‘\ä8ûŸfÛ‚W\ÃS\çlvÔx½?€\Ã!\0\ØL\Èb\ê#\Ú_L\Ì5¾\Çÿ\ìa½\åù†:\âŠP¬t\öSE\ò\ö\óoŒ&\Ñû\èŠ@KKdo?s½\Ý\á\ß &±G\×\Â\Õ\Ô\ÖÉ£\ïo—{Ö›Y§;0-N^ùZž\Øu˜\ßú\ò¹lÁ)­1û‘t{\Ô\ïÁ½S²d\ê \ïm¦\ÛU\Õ(\Ó\æ\î–ý¦:%­ Wœ<?=Gz9\à´H+”•\ï—M;K\ô\ÔVNfº\Ê\Ï\ÑÂ­Ù¸Uª\Öê©­c\nKJ’ýkÅ\èýO\ÖKs\'\ï\ÑÓ§LŒ¨cµ4\0 c\ê\åùú\ÕI²\Ã\Ð}ÿ\×\ÖË‰™\ö~¾´.F\î\\—ü«ý\ßP¿\ë—©—\Éý¼s‡€Z$uû\Ú$\ã«~\ã-r\ëQu2$•[.“\"\r\0ÞŒ\ë. –þ˜z\ó|aoó›¿’\Ø,·Ž¬•‚dû3£z«zxs‚¼T\ìC„\æ\Øt¾ÿ\ÌA¼ù.D\0°Ah\é\ÏÎ®¿\r\ß,hˆ\êÒŸH¤\'´\ÈMÁpDšýo*†<¹=!t†Û­¬ˆ•6\ìùŸ\Ù$S³ýw‹%\à\0\Ì^uVz\Í|\Þ\Ëþ\Þ=\\j\\‹übD­ŒNw\ÆU£:?\à\ï[\Ì}3\ÚB\çûoJ4þÏŸ\Ø\"W\ñÏ­•€\×\0-ý)1s\Å¼\ìŸ1 ^OÎ’|\æýøˆZÛ¿—p\È\ë¥q\òÀ\Æ\ÄP\î&‡\Î\÷¯2üÛ¨zÿk\ë$™\Ûý\×\"\0\ö\ôŽcKN\Ïn”\Ü$ç¾£\Å\Ê\÷\ë\å‹ÁN\'xgo¬ü\æ\Ó$©krJarx\ôþ\0zŠ\0`Ð¶šy³\Ì\Ì%SRl‹œ\Û\ßùÝ¬z~{p½\Ì\àŒ\ÖO*c\äŽuæ¯¨{Â®\Þÿ„\ô&9ƒ\Þp=Koÿ•\Û/COø\é»\õ²¢\Ì\ÌU\Ó#\â\å\Ã\Ü\õùì¿·4\Êk\Z\Å	Gù\î»\Æ\'HV’3?\r(=(r\å›uRYo\ö7+\'9 >%Qz¹ÿ{“Ý¢\öv\ì?\Ð\ñ©”\äD\é“ý¶¼ ¬b¿44tü=¤¬Œ>G—Ôž’=\å\Ò\ÒÉ·{½ 6&FÎ™4AOþ¦¶³}ý\ÙMz²Vÿ\à«\ó\Ê+F†\Î\éw›|R.\ß}i›4: \î“ \ó.&C3œµ¤D\íù?kN‘\ñU¿\ê\\‡\Êø|ÿ½\Ñm+.••\ëŠ\ô\ÔÖþ¹2jøP=!\Ü\Ò«¤¼²JOmM7š\ðÔùK–ISs\Ç\ìp¦`Èºe\ñ.=Yo\Ö\ä|W¾ù+3ŽÎ9_u\Æù[\ö\×\ËO\Éju¹\í 7ŸK\ö\ìù\Ï\÷\å›?\àU\0þþ\á^Y[\Ö\ñ*\Ìh\Z•,_\é\î\Ú\åK…½\å\ß•^8?`wuC\èj{™C\ÎPŸ$=°\Üüž\õgr\Õ	ý\ôÀ\0«®o–;\ßÚ­\'\ë\Í^¥9\áþH}a`š¼4c˜d¥Ø¿©o]“L{z£¼º©R?b•\rrÕ‹ÛŒ\ß\ï_\Ð;Aú\ò \Ç,“\0‹Ý½lw\è*\Òu•6yP/=¹Ÿ:V\öÕ™…20ød·š†f™\ñ\Üfyvm¹~\Ä,\Õû_2o‹\ì;h\ö\ö\Õû?2}d$\ñ%-Àk\0\Úu Aþ`\èX\Ö\Ø@@n›”¯\'\ï\Þ7I^|„\Ý/I?bŸú¦¹l\ÞVyÀ†£v\éýDÀB·--]9šp\Ùq}ed–ýo’V\ÈK‹—Wf\áˆ7\"\õ\ñû\Ïÿ³SnYb\îK¯\Ðû°\0À\"\ê›\ãO}²OO\ÖJMˆ‘\ëO\ö\öY\ä\ê#\èf“/qF\ÅqÏ²Rù\Ék;,\ßY zÿ+m\èýU\íB\ïxÀ\"7.\Úel¡ÍOÌ‘œT\ïofI‘g\Î*S\ò\ñ¿\õ¡\Ê\äŠù[Cý¼l\íý§\Ñû^G\0°€ú\Èv\á–z²–úxüû\ãü\ó1mBl@nŸ$§\õsÆ®\Þ®)—™\Ïm–ƒÑ¯z\Ô\î;zu\'É‰ý\éý¯#\0DYh\éÁ~ø–S\óBW\Æ~È·‡\Ô\Ë9y\Î\ØG¯\î\Í?\÷\éMRY½c—UˆüƒM½ÿw\éý_ \0D\Ù#\í“5{\Ì,ý9¦_²\Ì<ÆŸg-¨nú\ä\â‚zG\ô\Ôom¯’ÓŸ\Ü \ÅU‘‡»zÿ¬„zÀG\0QÔº\ô§DOÖ»cJ~\è\ö??;+·Q®\ZZü}\Ð\ØH¿©Á°¹¢N?\Ò}v\õþ\ê\÷O\ÍL\ïø Š\î}·TJ¢p\Ø§\é\å˜o\Ä\Ûí”¬&¹¶°N\ðl\Þ\\\Ñz~À\Ç{zv~€]½ÿE\õ2¼\çû~B\0ˆ\õ\Ñ\ïý\ËK\õd-µ\ê×‹K\"16£I~zd$;\àV…À\Ðù\Ý|#·«\÷“\Þ$Ssœ\ñ¥J\0\æ\0¢d\Ö\Òb©6´\ô\ç\âcû\Ê\èœd=á£z5\É\r#j%3\Éþ§uEm“Lÿ\çFy}s\×\î±³\÷¿z˜3¾GÀ,@¨{\ç|lfG¼:&\÷ú“s\õ„\Ïš\Ú,™%zÛ¿+@\ÂþµIž[W¡i_cs‹\\ú‚]½¤ÆšŽ\0œ€\0jéºýÏ„ž˜\íˆ77\'\Ú\'N^½\è9\"3Q?bŸC\çü\íÃ½ú‘¶nYRl\Ëq\Ã3\Ö\Ëi\ôþ€_\0\"´`c¥ü§‹\óFª_Jœ\\ë£¥?‘PG\Ø.¼x¸Œ\ËOÑ\ØG…\Ã\ël—\Ùo¶½CD\õþ\÷¿g\æ»#\áT\ïf.½?\àg€¨\ö›\r.ý¹\é”<\é•\ÈmZ]¥ni›a¡Llÿ\Ý\ê\ó¡\Ùo•\ÈO_\ßùßžŸ\Þ€\0x\Ô\à\Òu,\î%£2\õ„®R%=ûµ¡2ý\Ètýˆ½þ¸b\\9[hu0½?\0;\0zH-ý¹£t­2{J¾Ä©ûÿ\Ðm‰Áw½G§\r’™\Ç8#@©S\"ÿ\Ë:[zÿ‹\n\Z\èý„\0z\è¾\÷Jewµ™¥?§¤\ÉY\Ãz\ë	=¡\Â\ÓC_.\ë\Æg\ëGìµ½²^ÿÊœ³ƒ\Ï!z\0‡\0z@-ýù½¡/n©‹~u\õÈ©\ÏO\Ôú\äY>\\¢\Ôz¾½?€ÿ\"\0\ôÀ\ío˜[ús\áQ™2&\×þo²{É\'d\Ë}S„Â•:\ß?39N?\0€nSKž\\mf\éOR\\Œ\Ü|*K¬\ð1Y\òÄ¹CB\ß\ðº_M\á|\0m\0º\É\äÒŸk\Æ\ö\Ý\ÏkL\ÞGžûú0Is\Â)BQ½ÿ\÷‚\Ï#\0ø<@7¼¶\É\Ü\Òµ\ì\ï\'œ\ñ…5/›4(M^œQ(}=ø\ñ8½?€\Î\0ºH]\õß´\Ø\ÜÒŸ‹†¤7KŒ8!/E\Ì,”þ½¼³b™\ÞÀ\á\0º\è\ñUû\äCK²[\ä¬|®\ÛL\Z‘•:?`X†ý\çD½?€\Ã!\0tú\Æÿ¯.ý™YP¼‚\ÓŒ\Ô\'A^†€Q\Ù\î>j™\Þ@W\ð6\Ó¿{·TJª\Ì,ýQ[\Ú\Æf4\é	¦e§\Æ\É\Ë3\å¤\î¼zV\'E\Òû\è\nÀa”V7\Êï—›Yú£^´\Õ\Õ?/\Þ\ö\ê“+\óg†\îp“\Ö\Þ0½?€.!\0\ÆmK‹¥ª\Þ\ÌÒŸ	}›d8{\ÚA\íxlú`¹øX\÷Àtû\ä|™@\ï ‹\0XWV+O~¼OOÖŠ^\ö_0Àü~xtL\ð\à—\n\ä\ãœß§«\Þÿ\Züsp@\'~±p§46›Yú35·1\ô\í8‹ªc\î<­¿£\Ï \÷\Ð€,\ÙZ%¯Zú“\Z\×\"\Ó\ó¸úw2u~À½<?€\Þ@O\0Ú¡.úoX´SO\Ö;7¿Q\Òxýv¼+\Æd\É\Ã\ç\n½\é:½?€ž\"\0´\ã\ñ\Õ{\å£\Ý\õd­\ì\Äf9#‡3\Ú\Ýâ‚£2\ä©\ó†HŠ5œE\ï €\Ï9\Ø\Ø,w¾en\éÏŒ ûw\õÆ«\Î°\ócw\Õûÿ™\Þ@\0Ÿsï»¥²£\Ò\ÌÒŸa©\Í2>“«7\Z—\ßz~@¾\r\ç\Ðûˆ@˜=5\òû\÷\Ì,ýQ¾Y\ÐÀœ‹\Ô\ç5|~À,z\0Q@\03\Ë\àÒŸ³‡¦\Ê\ð^¬üu»Á¡\ó\n\åXC\ç¨ú\áû\ôþ\0¢€\0 }º·V_mf\éú\÷†“x\÷Šœ\Ôxyù…2Á\â\ó\èýD@»~\á.cK.“%CÓ½s\ö<DÒ“be\Þ\ÃäŒ¡½\õ#\ÑE\ï \Ú\0AK·UÉ«›*\õd­´„ù\ÙI9z‚—¨[ÿy\Þ9dº~$z\èýD›\ï@h\é\ÏBsK~vRn\è\ÈYxSBl\ë•ú5Q<Ÿ\Þ€|\0\Ôa?\ZZúÓ¿W¼|\÷„,=Á«TGÿ›/F\çü\0z\0V\ñu\0PK\îx³XOÖ»-ø†\à„\rr0Cp\÷=??@Fø\ÈW\èýX\Ã\×\ïF\÷\\ú3*;9´Fþr\Õ\ñYÁ+øž0kRž\åw\0\ð/\ß€\ÐÒŸ\å{\ôd½;¦\ä;\î$9˜1\ã\è™s\ÞIŽ\ëú›\êý0>[O\0}¾\r\0·¿Q,\ê\Ì,\â9;øb>ep/=Á\Ôs\àù†J\ï\ÄXýH\Ç\èý˜\à\Ë\0 –þ<¶\Ê\ÌÒŸ\Ø@ tp\òÀ4y\é…\Ò/¥\ãNŸ\Þ€)¾\07,2·\ô\ç²\ãú†v\Æ\Êq9É²\ð\â\á2$½ý\ó\èý˜\â»\0 –þ,\Øhf\éOjBŒ\\2K\ðYC\Ò[\Ï8º\ßgƒ\á™\ôþ\0\òU\0Pý7¯þMùÑ‰Ù¡=\ñÀ\ç\å¦\ÅË‚™GÈ‰z»½?\0\Ó|\0\æ|¼OV–\Ô\è\ÉZyÁøŒ\ãjp\á°\Ð7þU\ïß—\Þ€A¾	\05\rÍ¡oþ›rË©y’\Ê\Ò†zŽ<ûµ¡\ôþ\0Œ\ó\Í;\Ôý\Ë\÷\È\Îf–þ\Ó/Yf\Ã\Ò\0€sù\"\0¨¥?\÷½Wª\'ë©¥?\ê\ö?\0\0œ\Ê\àŽ7KŒ-ý™4(M¾8„¥?\0\0g\ó|\0X¿·Vùh¯ž¬¥VýÎž\Ò_O\0\08—\ç€É¥?\ß<6SF\ç$\ë	\0\0\ç\òt\0xc[•¼bh\é:\èå†“\ó\ô\0€³y6\0¨‹~u\õo\Êu\ã³C\Ë\\\0\0p\Ï€§>1·\ôG\îr\Ýø~z\0Àù<\066Ë¯\Þ(Ñ“\õn:%Ozu\á˜W\0\0œÂ“@-ý\Ù^Y¯\'k\r\ï›$—Œ\Ê\Ô\0\0\î\à¹\0PV\Ó(\÷¾kv\é:\Ã\0\07\ñ\\\0ø•Á¥?_˜&g\ë­\'\0\0\Ü#\Ð\Ò\Ù=\òs½\Ý\á\ß Hÿ\ì,=Yo{U‹\\º¸F\Z\r\Ü\ö¯®ùÿtj²Œ\èÓ³\Õ\Ð\Ø(»\÷–ë©­„øx\É\ÎL\×\ÂUTVI\ÕÁƒzj+£w/IMþ\ìYûhµcwY\ð?;ú	È€s?¯nR}°V\Ê+è©­´\ädIï¦\'„+\ÝW!\õ\rŸÃ’\Ó7C\â\ã8	³=;KË¤³\÷\è\éS&F\ô\ñ³¥À´»\×\'\ÈfžH_\è\Û$W«\Ó\0\0fE\Z\0<S¬;c\ì\Í?!ø»\ö\õf¾d\0€<\0\ÔGOnKh8+§Q²\óÁ\0\0\Ý\æ‰\0\ð\Ö\ÞX\ÙTm\æJ¯¸™–\Ï\Õ?\0À\Ý\\\0\ê›Ež\Ùn\î\êÿ¼þ\r’\Ì\Î\0€Ë¹>\0¼¼;^\Ê\ê\ÍÜ‡Ÿ\Ø\"§e7\ê	\0\0\÷ru\08\Ðv™;€gfA½Ä±\ó\0\à–\Þ\Èq#\õ}³Þ©§>­Ö“µ\Æd\'È“_\êºÿ?\Zjj\ëd\í¦mzjK\Ý\Ç>b\È@=!\Ü\ö’R)Ý·_Om\r\ÎÏ‘¾\é,hj\Ïk‹:¼¯X\í\í8\ÞÂŸW7\Û[Q)[v\í\ÖS[Ù™}d`n¶žn\Ý\æ\í¡=\n9´@R’\õ„p^›Ýº 6&FÎ™4AOÑµa_Œÿ\ë:iP\çþZLý¿\ö\Í#dBÿ\Ô\Ö¢`Uµ,^þ‘ž\Ú\Ê\è&§ž0JO·jý&Ù¼³\ãÃžÆŒ(”‚<^Œ\Û3o\ñ;€i“O\Ò\Âm+.••\ëŠ\ô\ÔÖþ¹2jøP=!\Ü\Ò«¤¼²JOmM7Zú¤E\ïµ\ÕK\æ/Y&M\Í\ÍzjË·{\0nZ´\ËÈ›¿r\Þ\È\ô¨¾ù\0`7W€7·WÉ‹EM	±¹\õ”<=\0\à\r®\0\êšÿ†…»Z®:>K†f\ÐO\0¼\Åu\àŸŸ”\Ë%5z²VzR¬ü\ô¤=\0\à®\n\0µ\Í2ki±ž¬\÷ó‰¹’™\Ì)U\0\0\ïqU\0x\àý=²­\Ò\Ì\Z\ÞÁ}\ä\Ê\ã9\Z\0\àM®	\0{6\Ê\Ý\ËJ\õd½\Û&\åKb,[\0\0\Þ\äš\00û\Í©¬kÒ“µ\Ææ¥„ný\0À«\\\0Š\ö\Õ\É\ß>Ü«\'\ë\Í>­\Ô6þ\0\àD®\07/6·\ôg\Ú\ð>2q\0K\0\0\Þ\æø\0\ð\Ö\ö*yaƒ™¥?q1¹\õT–þ\0\0¼\Ï\Ñ ´\ôg‘¹¥?\ß“%G\öM\Ò\0\0\Þ\å\è\0\ðÌšrYQlf\éOZBŒüŒ¥?\0\0Ÿpl\0¨oj‘\Û\ß0·\ôGmü\ËNe\é\0À\0þ°|l®0³\ô\'¿W¼\\}B?=\0\à}Ž\0\åµMrï»»\õd½\Û&\åIJ¼£\Û\0\0¢Ê‘\ïzw¼Y\n&Œ\ÊN–\Ê\Ô\0\0þ\à¸\0°¹B-ý)Ó“\õî˜’/1lý\0øŒ\ãÀ‹v…¾\0h\Â\Ù\ÃzË”Á½\ô\0€8*\0¼»³Z^Xof\éOl  ³&\ç\ë	\0\0qL\08´\ô\ÇÌµ¿È¥£3edK\0\0þ\ä˜\0\ð\ìš\ò\Ð\'\0&¤&\Ä\È\õ\'\ç\ê	\0\0ÿqD\0Pÿ¬7J\ôd½˜-¹i\ñz\0À\0|_-ý©Ó“µ\ò‚oü?—­\'\0\0ü\É\ö\0PQ\Û$w/3·\ô\ç\æS\ò$•¥?\0\0Ÿ³ýp\ö[\æ–þ\Ý/I.:6CO\0\0ø—­@\íúÿ\ëJ“Kú‡nÿ\0À\ïl\r\07.\Ú)u†–þL\Z”&§a\é\0\0ŠmÀ\ä\Òµ\êW]ý\0€V¶\0\ÓK¾yl¦—“¬\'\0\0`K\0ø\×ZsK’\ãbä†“\ó\ô\0\0\ã@-ý¹m©¹¥?×Ï–½Yú\0@8\ã\à+\Ì-ý\é—\0ý\ô\0\01\Z\0\ÔÒŸß¾cn\éÏ_È•^‰±z\0\0‡\r\0w\Z\\ú3¼o’\\:º¯ž\0\0@8c@-ýy\Ø\èÒŸ|‰S\÷ÿ\0€6Œ€›\ï2¶\ô\ç\Ó\ä\ìa½\õ\0\0>\ÏH\0xoWµ\Ìý´BO\ÖR\×ü³O\Ëo\0\0@»Œ\0“K.<:CŽ\ÏM\Ñ\0\0h\å\à¹u²l‡™¥?Iq1rË©,ý\0\àp,\0¿\\Z¬e½\ïÍ’‚\Þ	z\0\0±4\046·È¦r3K2’b\åG\'\æ\è	\0\0t\Æ\ò\0`ŠZú£B\0\0\08<K€©·ÿ!\é	\ò\í\ã²\ô\0\0\Ç\ò\ï\0˜ \ÎúOˆe\é\0\0]\åú\00>?U¾2¼ž\0\0@W¸:\0„–þL\Éý\0\0t«ÀWG¤Ë„©z\0\0]\å\Ú\0 :ÿ_²\ô\0€qm\0¸\êø,š‘¨\'\0\0\Ð®\0}c\å§\'±\ô\0€žre\0øù\Ä\ÉLŽ\Ó\0\0\è.\×€A}\äªú\é	\0\0\ô„\ëÀm“\ò$‘¥?\0\0D\ÄU`l^Šœ?2CO\0\0 §\\\0fŸÖŸ¥?\0\0DkÀ´\á}d\"K\0\0ˆ\nW€¸˜€\Ü\Ê\Ò\0\0¢\Æ\à\ò\ãúÊ‘}“\ô\0\0\"\åø\0–#?Ÿ˜«\'\0\0\rŽ\0j\ã_v*K\0\0ˆ&G€ü^\ñr5K\0\0ˆ:G\0µ\ô\'%\Þ_S\0\0ÀUû\îzlv²\\xT¦ž\0\0@496\0Ìž’/1lý\0ÀŽ\0g\r\ë-S\÷\Ò\0\0ˆ6\Ç€\Ø@@fM\Ê\×\0\0°‚\ãÀ%£3\å¨~,ý\0ÀJŽ\n\0©	1r\Ã\É,ý\0Àj––ýËž™»\è\íNÿ\õ\Íú]\íý\÷\õ›\Ö\Ü\Ü\ñoŽú]ˆ‰q\ìw4m\Õ|\Îv\ö¼	$ü7\Új\ê\ä9§\Ä\òœk—z¾©\ç]G\Ô\óM=\ïÐ–z\ë\ìMB½\Î\ñ;×¾\Ãý¼NŸ21¢\ß:\Ë\0\0\0ˆ¾H\0q\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€\0\0\0\ð!\0\0\0>\ñm€\0\0À}ø\0\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€\0\0\0\ð!\0\0\0>D\0\0\0À‡\0\0\0ø\0\0\0\"\0\0\0\àC\0\0\0|ˆ\0\0\0€\0\0\0\ð!\0\0\0>D\0\0\0ÀwDþÃ›¸hw—9\ê\0\0\0\0IEND®B`‚','pune','ksumer597@gmail.com','sumer khan','34567890','$2a$10$WUyrpqI5vA/A0nHYNWS.ke0nKbkXXn69lH3hDT/82VAaog.N/0.pq','sumer@12');
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

-- Dump completed on 2024-09-27 19:29:42
