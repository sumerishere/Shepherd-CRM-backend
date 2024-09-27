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
INSERT INTO `users` VALUES (1,_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0\�x\��\0\0\0sRGB\0�\�\�\0\0\0gAMA\0\0���a\0\0\0	pHYs\0\0\�\0\0\�\�o�d\0\04�IDATx^\�\�	x�\�\�\�g�o@B6 l�D��U\�ւ\�V)֪�j��v9]\�+Vl=ui����u\�V�\��ln(\"\n\na_B !$!{μ���\�,$��\�o��z�\�\�9\��0����\�{\�@KK�\0\0\0�\�\0\0>B\0\0\0��\0\0\0�\0\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�\0\0\0\�!\0\0\0>D\0\0\0��\0\0\0�\0\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�ZZZ\�/{f#�\0\0�n�>eb@��G�\0\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�\0\0\0\�!\0\0\0>D\0\0\0��,\�#\�L��\'�\�_U-�����2z�ɩ\'�\�­Z�I6\�,\�S[cFJA^��n\�\�w����@  \�&��\'�\�V\\*+\�驭!�se\�\�zB��+VIye��ښ<n�\�IKՓ\�����\�,/\�o}��>�F:�Lg\0\0\0u\��\�=z\�7�\0\0\0�\�\�U-w�\�\�v#\0\0\0e\�Mr\�ܭ\�\�\�\�m�\0\0\0�H�\�\�\�m����\��\"\0\0\0EN\��\�\0\0\0��\��\�\0\0\0D�z�p\0\0\0\"\�\�?\0\0�\�\�\�?\0\0��\��W.\��\�\0\0\0\�!�\��\�\0\0\0\�{�p\0\0\0z���8\0\0\0ݴ|W�+{�p\0\0\0����I.��ŕ�8\0\0\0]�\�\�/��յ�8\0\0\0]t\�RY��RO\�F\0\0\0�T\��\�zr?\0\0\0�\�\�?\0\0�N�\�\�ɽ\��w�\0\0\0@\'T\�?����\�b�W\� \0\0\0\��z�\���O\0\0\0�<\��_:\�|+�L�\'\�\0\0\0��C��\��f{u\���/HA\�\�,D\0\0\0\�s\�{מ\��\'r\�˅}\�d-\0\0\0a\�\��o<%WO\�#\0\0\0�\��\�\�\����\�_8\0\0\0A~\��\�\0\0\0��M���\r\��\�\0\0\0\�{��Ff\�\��\�d�\�G\0\0\0�ڡ=�~\��\�\0\0\0�\�\�?\0\0\�[~\��\�\0\0\0�\�\�\�?\0\0\�;v\��\�6\��\�\0\0\0_��\�����8\0\0�W~��=���&d\�9G\�\���#\0\0\0|C\���-5\���\�O��O\�ӓ3\0\0\0�`g\��\�\���\��\�\0\0\0��\�\�\��\0\0�\�T\����g\0\0\0�F\�\�>\0\0��\��;F\0\0\0x�\�\0\0\0O�ߦ\���\':�\�G\0\0\0x�\��I\�\�)\0\0�ST\��]{��\r��X\�\��\�\0\0\0\�8\��o��\�\�\�ٽ8\0\0�3\����\0\0\0\�z�\�!\0\0\0\\�޿�\0\0\0W�\�\�\0\0�\���|�-��u.\��\�\0\0\0����Fn]�KO\�\�K�[\\\���#\0\0\0\\i��{�\�\���#\0\0\0\\�\�?r\0\0�\�\�\�zz�H\0\0\0�B\�\0\0�k\��G\0\0\�\nv\��\�H\��\0\0\0p�l\����-_\�H\��\0\0\0p<\��\�bS\�\�\�\��\�\0\0\0�F\�o\r\0\0��\���C\0\0\08��u\0\0\0G�\��\0\0\�8v\���x�\�G\0\0\08�ݽ�`\��\�\0\0\0Gy\�}{z���\�hi�\�㕹�\�\�\�o\�q#\��p5�u�v\�6=����$#�\�\�m/)�\�}�8\�ϑ�\�\��p�-��~\���\�\��ym\�ފJٲk��\�\�\�\�#s�\��p\�6o�ꃵzjk\�\�IIJԓ\�\�z�\�2ih6�\����y\�KY㜏�?��6w\�=}\�Ĉ�a-\r\0\0\0tUMS@n�8QJ\�\�~8�\Z\�\"�:�N��\�#\�i\0�\0\0\�N]I�yS�\�7\�z\�z׽�G\0\0`��\�dyy��\�9;�Q\�f4\�\�_\0\0\0[m�������\���\�f�p�\�;\r��\0\0\0��\��\�/J�F\�\�&K�m�\�\�K�s�\�g\0\0`\�z���\��\�\0\0\0�x�Ħ\�?�A\���\�g\�m�\�\��\�YzB��\�Fٽ�\\Om%\�\�Kvf�����J�\�S[�{�\�(���˂�\�яl@\�\�\�\�u{y\�=����,\�\�\�p��*���AO�O\���\�$JC�\�\�\�G�\�\�NN�x\\�\�,-\�po�\�\�=\0�11rΤ	zB��Uղx�Gzj+#�br\�	�\�p�\�o�\�;K\�\�֘�R�\�R�\�\�[�N����M>IO���TV�+\�S[C�\�ʨ\�C\��pKW�\n��*=��\�~�>��\�\�eG�f\�\��%ˤ��㚂=\0\0\0W��\�\�Ӟ�� \0\0\0��\�~�\�\�\'ӆ�g\�W\0\0\0F�\�\��\'sN\�K�\�&\�\�	�\0\0\0�;t��\�/��\��Q��\�\�]\0\0�\���9�\�\�a\0\0\0K�z�}�z2\�\Zz�N\0\0\0�\�P\�b[\�?�޿S\0\0�%\�\�5ɯW�\��;\0\0`�ｼ]�;^\�i	z��#\0\0\0�\�O+\�\�\�O+\�d�\�\0\0\0Q���Fn\\�KO\�\��w\0\05U\�\�r\�[��)�sf��޿�\0\0�����\�R��NOf\��\�\0\0�\�fM\�ǜ[\�{c\��{�\0\0\0�\�\�҃r\�\�b=��z�\�\'\��\�\0\0\��_<w�\�6v|v�\��#C\0\0\0D�\�ߝ\0\0�{\�2[z��\��#F\0\0\0\�\�\�{\�M6\�\�|.�4\0\0\0\�\���\�\"m\��\��#F\0\0\0t۵�\�ý��z�!\�\��\�@\0\0\0t�\���\�~\�#\0\0\0��\�\�;\0\0�.�\�\�\0\0�K\����\0\0\08�?\�\��_}��U\0\0�N�\�ߎ\��U\���)\��V!\0\0\0:TM\�\�Y\0\0@�Ԟ;z�\�H\�o1\0\0�]v\��ӏL\��B\0\0\0�A\�\�}\0\0�g\���\0\0\�v\�\�O\�o\0\0\�_YY&���\��.��q\0\0@�\��oXh�\���\"w\��G\0\0\0\�\���\�\�\�_D\�o\0\0��\���\�9R���\'�D\0\0\0���\���\�(\�\�\'�F\0\0\0��\�/Hi�o�\�\�@\0\0\0���\�O�m�\�\n\�%�w [\�\�\0>eW\����\���d6t�-\0\0�\�\�v\��\�\rrR\�&=�N\0\0\�O\�\�\�\�v\��\�-\�=�n\0\0\�P\�?מ\��\�#\�\���?\n\0\�\���\�[�\'s.\\/y\���B\0\0\0���\��H\�\�8\0\0\��z�!iz�\"\0\0�\�\�\���%\�\�\��\��\�\0x\�u�\�\����́2 Ep\0\0x�\���\��\��\�\�䂣2\�\'\"\0\0�G\�\��\�/�\��]�\0\0\0dW#��;X�\�x{q:��\0��\�\��\�?s�����@\0\0\0��\�GW\0\0�C\��\�U\0\0\�ztZ\0\�\��\�\0\0x�_?\�kK\�\�z�\"\0\0�˩\���٩\'sT\�?�4z�\"\0\0��\������\0�s\�?���\0\0���fc\�!��\�\0\0��֨\�!�?z�\0\0\0.s�\��i�\�G\�\�\0.\�\�Wwȧ\���\0\0\\D\��O}�OO\�|�\�\�s\0\0\�v\��G\�K�;\��=�\0\0\0.`k\�?�\�ߋ�\0��\�\�7IO\�@KK��e\�\�]\�v��@\�_��\�\�\�\�\�$e\��\�\�V||�d�\�\�\�\����uzj�O�TII\�J\�).\�\'\�\�Ol@$/+SWS[\'�T멭�\�D铖��蛿�I\�Yݠ\'s�\r�����)�\�/\r\rMzj++��\�\�\�\�	\�J\��\\;~��>eb\�\�,\r\0\0�\�쨉�[\�$I�\�O�e`J�\�:�V��ر\"\r\0�\��C\�\�\��1\���bl�\\;��7�\�\0\�\�e\���.\�z\�ۃ\Z$?�w��\0\0\0�pO���\�|7~zv�|!�QO\�2\0\08�\���ؚ�\'sT\�QA��\�u\0\0pz�\�5\08�?L�\�6��@@\�=\\O�\�+��h��\�JKN���\r\�\�mݵ[v\�\�S[C�\�\�-F[\�?�T:����ʸ��\�©��v�詭�\�t�����{�\�*�\�-\�\��\��&�M�fg˚�[�\�`\���)\�ގ��\�zi\�\�=\�\�{\0bcb\�I\�pj�\�\�\�驭�\�ir\�	�\�p�\�o�͝��Q(y\�zB�y�\�	�\�d�0m\�IzB�mť�r]��\�\Z���\�SϨ=��_o|կ\�\��[\�-[\��t\�*)��\�S[�Ǎ�t���\�_�L��;~>�\0\0\\�:��Ϟ�Ɵ:\0\�\�G6\���\�T\���\0\0l\�\�G{e\�\�\�\�\�����2\�h\�j\�3\0\0\�D\��?��=\������\��\"\0\0�\r\��a7�\0`z؍\0\0\0�=��\�\�#\0\0�Ak\�j姯\��\�~\0\00�\�N³\0Q���2\��}S\���\r\0\0`W\��\�\��\�7�\�\�\��\0\0`1�z��T\�\Z�?\�G\0\0\0\�\���\�\�2�\�\�\�\0\0�\�\�\��Hzt�\0\0\0yl\�>y�\�E\0\0\0�\��^ߡ\'s\��\�U\0\0�2\�z�xzt\�\0�2\�z�3\��\�u\0\0�\"�z�\�F\���{\0\0%v\���a\�?��\0\0\0Q@\��\�\0Q@\��!\0\0@�\�om�\��\�\0\0 ;\�\�=�\�_�\��#R\0\0\�\�f�\�%Hmc�~\�zD\�\0\�G�$\�΃\�_F\�e\�?��\0\0\0=��,N��mڥ��\�\�c\��9\0\0t�\��\�տi�\���\�QB\0\0�n�oy`c�ԙ�ݟ\�Q\�3	\0�\�\�[d[���NzD\0\0�蝽��t�?��\0\0\0]PR#\�\���̢\��5\0\0p�M-\�;u�S@?b�?�ĳ\n\0\�\�w\�\�\���Ȣ\��5\0\0Љg֔˿�j\�d\�%�\��a-\0\0t�h_��`�v=��z��=�\�\�\"\0\0@;j[\�\��R7�D\�Sx�@;~\�\�Y]zPO\�\��\�\0\0|�\��_m�|z�D\0\0�0\��\�\0\0h\��\��m\0�\�\��\�C\�\0\0 \�ٵ\�\��\�\Z�)\��\�\0\0��z�\�bO\��\�\�\�	0�\0\0�\�\��\�W<\�\0�ڏ_�\��?\0\0��\��[e�\���Xz؏\0\0��\�\��\�>�\�\�#\0\0\�z�\0\0��\��\0\0��\�Z\0\0�\�\�rz\�\0\0_\��\�\���\��\�?ym���\���\0\0�\�T\��誽z2\�\��p0\0\0O��\�/\�Ld\�?�\0\0��\�\���\�Z{��^b\�\\<;x�]��}Sȱ\�\�z��\0\0�����\��\�Ge���\�\0\0<\'\��/��\���́z��\0\0�S\��\�\�#f\��\�mx�\��y�\�\�\n\0\0\�P��#�\��\�O\�\�!\0\0\��z�ܤf��q\\�\�}\0\0\\Ϯ\�_\��\�\�KJ�~\0p\0\0׳�\��tp���]2D\0��\�\���ԷI&e5\�	p\0\0ײ�\��N\�\�p3\0\0WR���\�\�\��\'Ŷ\�G\0w\n��D\�$��\�m~\n\0\�\�\�	�p��o\�]9����\�ӧL\�_\��\0\0p�e{\�ly\�\���\0\0�JIm@ޒ�\'s\��\�5\0\0���\��c�4[�\��Ó\0\0\\\�\�m	��\��\�\�%��\�\�C\0\0\�\n\���\�\����\��\�{\0\0owm@���\��&\0\0GS��\�\�����t@lL��3i��nU�,^���\�\�\�&��0JO�j�&ټ�DOm�Q(y\�zB�y�ߑ�~\���L�|���\�\�\�\�o�_\��\�\�\�Q�G�n+.��\�B�nϐ��2j�P=!\�\�����JOmM7Z���\�	\�\�/Y&M\�\��=\0\0<\�u���md\�\���\"\0\0p�M\�ur\�+\�\��\�H�?�5PO�w\0\08N\�|\��\'\�\�\�\�\��4\�\0�\�x�p���g�|�\����w�\�_Fe\'\�	\�6\0\0GQ��_m\��/\�WO�\�\0\08�?`\0�#\��f\�\�\���Y\0\0���\�?d:�?|�\0\0�V�\��g\��ÿ\0\0lS\�\�\"߲�\�\����\0\�c��/�s;�\��Cz�ߞN\�\0|욗�\�\�/m�\�fb\0\�S��\�+\�\�d�\��/M\�\0|\�]\�2\�\�\nyb\�>���-�[�\0S\���\0|\�E���\��\�\��\�n�*u\�:`1z�\0>�>z]��ZO��l��/��H\�lԏ\0֠\���\0\�3\����_.-\�\�g�(���O\�\�\r� �\��\� \0�\�C���׎|��V\�xb�\�\���\�	z�Y\0>RQ\�$��\�n=ul[e�L������\�&;{�Ǧ\��\�!\0�\�o\�.�}]\��K�\�짊\�\�\�}W\0\�	�z��=���Ρ\�\�C\0\��-�\�\�\�t�{�^�}\�E2o�~�\�}���\���\�\��\'n^�+\�1lw���\�\��\�}��\�\��\�\"\0��\�]5\�|\�*���ZZ\�{/m���\�я\0�w�\�W�$�D\�t\r��_�3\��\��r˒]�\0�aW\�\�\���� \0x�\�_?�\�\'\�,+���C8>\0���\�?oD�|�8z�+\0\�\�ҟH�%�\�~���\�@\n@;6W\�\��\�H�΢\���\0\�a\�[�;Y��g֔\�W�\�$՜�0��4jG\��8�?\�-\0�Rg�\�Յ�?�X�\�@\����\��\���\�p\r�G�\�\�\�Fޘ\�/��3\�\�.\�\�=\���z\�4z�g\0���<�\�\�-{k\�je\�:=c\0\�F\����n]ҳ�?�P�\�\'�du)\��\r�?\�N\0�Q\�?��\�K\"���AΚS\�\�\�|v\����\�\"B\0\���\�Λ\�ԗ�=�Q^\�T���=��}��\�\��@D\027�b����k\Z�e\�s�\�ٵ\��x�\���\�2�?\�V\0�PKynY��?=��]6o�<\�>\�xQ\���\����\0<\�/.�\�)UE�}\�\�=�jZYR�\'s\���\�!\0x�\�\����K\"�\��\�k�\��\�W[&M�\���\0\�wZ����oWp~�\�\��\�A\0p���\�\�O��D\�k\�e\�s�\�`#\���]�bl�\��\0�\�n]Rl|\�O$^\�X)\�>�\���\"G\�x�\�\�ҟ�\�V���W\�\�On�\�*\�p;{�\�\�\��h\"\0�\�\r6/��Ě=�\��N\�F\�x��\�\�/o�|\�\�\�\�\�>	�8����c\����\0.Ժ\�\�\�֗T5șs6Ȼ;9?��ԧLv\��\�\���\"\0�\�\�+ˤh�w>:��m�i�\�(�o>���\�_ݾi\Z�?`�eBK\�r\�ҟ��nh���I�[g\�I��,z��\0.\�\�wv\�^�/�\�C\��\�ý�\�AUL����\�\�8����?\\a�#Y��ZZ\�\�e\��%��v�\�]\�\�.\�\�ɠ\�\�\"\0�\�mK��\�[\�ԭ��\�*���\�\�\�\�ꥢJyȆ͒_�\��#\0�Ċ\�\Zyf���?�W��1�Ft\�\�\�B�\n�z�\�b\�;\�\Z]�\��\�0�\0\�\�?S/\�\�N \�\Z��\'{=�j�\�|n\�XLm�~�(�\�\����?K�V\�\�Z\��&ɥ��\�gȵ\�\��\�z�h��\�\�&9���yb[�l�2�r@\�؇\0\�p�\�-�\�ߔ\�S\�%.&�ԯgM\�o�?\�\�mU\�l��\Zo\�a�*\�\�\�qz2�\���\�\�ҟO\��Y�{JA��5���Z�xB�\�7u�3�\�\�F��s�dw\r\�	������ic�\�\�H:\��v#\08XU}�\�\����?\�\r^]\�G]��\��A\��>��XR-;jx\�FJ\��(J�\�&���\��\�,�\��[\�*\�`w�S\"�\�f>\�qt��\�M\�S[_?*C�}�PIM��)SV\�,�\�&\�\�Y{ɓ6\����\��WP�*�����?Iq1r\�)�z\�\�\�A�\�\����l�/�<u\�z秉����ȞP���6\��W\���@\0p��oh��3��}l?\�;AO���\"fJ~�x��}\�!\�\�O\�}��\�\��\0hKu�,\�m\�\�9+%.\�E�\���$�]t�\�Hԏا�% \�%ʢR�?�pz\0�\0h\�\�i6tyv\�ɹ=zQ\�\'A^��PFe\�\�\�\�I��%A\�\����\�=�5�\�@�a\�/��O*\���f&\�e\�\�\�S\�\�\�\�\�3\������\�\�\�x�\�\�m��\�\�O\�\�����g����r�\���o\�\�+s/&S�~v�]^\�/7�UZ������^�z\���z��\0��4Nv4\�Grb�T9\�>z�LJ|�<}��\�g�\��\�\Z���-�89�+\����\�\�*\�v\��D\�8�!j�\�\�\�ի	\���)�Q=\�O��ӗ䚱�\�#\�za\�~9�\�M�eJ~w\�]\�\�N\�\��\�yZ�NwK\0��!\�튓�\rf��}�\��\�?�����W��8\��\0u�җ�Q${�\����7V\�\�7���G�˕\�\��NF\0p�}\�ye�����؀\�z\�\��DB\�Vx\�\�8?`Eq�L}�HvhЏ��\����\�|\��?x6�?\�t\0P\�\\�3\�I\�wO\�z�������C\�P�)�\�\�)\�W�\�>z\0�C\0�\�֚y�\�̭Y\�I�\�?\�\\���ʐ9\�\r�\�8��fۂW\�S\�l��vԏx�?�\�!\0\�L\�b\�#\�_L\�5�\��\�a�\���:\�P�t\�SE\�\�\�o�&\��\�@KKdo?s�\�\�\� &��G\�\�\�\�\�ɣ\�o�{֛Y�;0-N^�Z�\�u�\��\��l�)�1��t{\�\���S�d\� \�m�\�U\�(\�\�\���:%��W�<?=Gz9\�H+��\�M;K\�\�VNf�\�\�\�­ٸU�\�ꩭc\nKJ��kŝ\��O\�Ks\'\�\�ӧL��c�4\0�c\�\���\�I�\�\�}�\�\�ˉ�\�~��.F\�\\����\�P�\���\���s��Z$u�\�$\�~\�-r\�Qu2$�[.�\"\r\0ތ\�.����z\�|ao󛿒�\�,�����d�3�z�zxs��T\�C�\�\�t��\�A���.D\0�Ah\�\�ή�\r\�,h�\�ҟH�\'�\�M�pD��o*�<�=!t�ۭ���6\���\�$S��w�%\�\0\�^uVz\�|�\�\��\�=\\j\\��bD��Nw\�U�:?\�\�[\�}3\�B\��oJ4�ϟ�\�\"W\�ϭ��\�\0-�)1s\��\�1�^OΒ|\����Zۿ�p\�\�q\��\�\�P�\�&�\�\��2�ۨz�k\�$�\��\�\"\0\�\�cKN\�n�\�$羣\�\�\�\�\��N\'xgo��\�\�$�krJarx\��\0z�\0`ж�y�\�\�%SRl��\�\��ݬz~{p�\�\��\�O*c\�u毨{®\���\�&9�\�p=Ko��\�/CO�\�\���\�\�U\�#\�\�\�\�\��쿷4\�k\Z\�	G�\���\�\'HV�3?\r(=(r\�uRYo\�7+\'9 >%Qz��{�ݢ\�v\�?\�\���\�D\������b�44t�=���>G�Ԟ�=\�\�\�ɷ{� 6&FΙ4AO����}�\�Mz�V�\�\�\�+F�\�\�w�|R.\�}i�4: \� \�.&C3���D\��?kN�\�U�\�\\�\��|��\�m+.��\�\�\�֐��2j�P=!\�\�����JOmM7�\�ԁ�K�ISs\��\�p��`Ⱥe\�.=Yo\�\�|W��+3�ΐ9_u\��[\�\�\�O\�ju�\� 7�K\�\��\�\�\�?\�U\0��\�^Y[\�\�*\�h\Z��,_\�\�\�\�K��\�\��^8?`wuC\�j{�C\�P�$=�\���\�gr\�	�\��\0��o�;\�ڭ\'\�\�^�9\��H}a`��4c�d�ؿ�o]�L{z����R?b��\rrՋی\�\�_\�;A�\� \�,�\0�ݽlw\�*\�u�6yP/=��:V\�ՙ�20�d���f�\�\�fyvm�~\�,\��_2o�\�;h\�\�\��?2}�d$\�%-�k\0\�u�A�`\�X\�\�@@n���\'\�\�7I^|�\�/I?b����l\�Vy���v\��D�B�--]9�p\�q}ed��o�V\�K��Wf\�7\"\�\��\���SnYb\�K��\���\0�\"\�\�O}�OO\�JM��\�O\�\�Y\�\�#\�f�/qF\�qϲR�\�k;,\�Y�z�+m\��U\�B\�x�\"7.\�el�͏Ȏ�T\�ofI���g\�*S\�\�\��\�\��[C��l\���\��^G\0���\�v\�z���x��\��\�1mBl@n�$�\�sƮ\��)��\�m���ѯz\�\�;zu\'ɉ�\���#\0DYh\��~��S\�BW\�~ȷ�\�\�9y\�\�G�\�\�?\�\�MRY�c�U���M��w\��_ \0D\�#\�5{\�,�9�_�\�<Ɵg-�n�\�\�zG\�\�om��ӟ\� \�U���z���z�G\0QԺ\��DOֻcJ~\�\�??;+�Q�\ZZ�}\�\�H������N?\�}v\��\�\�O\�L\�� �\�}�TJ�p\��\�\�o\�\�픬&���N\�l\�\\\�z~�\�{zv~�]��E\�2�\��~B\0�\�\�\��\�K\�d-�\�׋K\"16�I~zd�$;\�V��\��\�|#��\��\�$Ss�\�J\0\�\0�d\�\�b�6�\�\�\�c�\�\�d=ᐣz5\�\r#j%3\���uEm�L�\�Fy}s\�\���\��z�3�G�,@��{\�|lfG�:&\���s\��\��\�,���%zۿ+@\���I�[W�i_cs�\\��]���ƚ�\0��\0j鏺�τ��\�77\'\�\'N^�\�9\"3Q?b�C\��\�ý���nYRl\�q\�3\�\�i\���_\0\"�`c����\�F�_J�\\룥?�PG\�.�x��\�Oя\�G�\�\�l�\�o��CD\��\��g\�#\�T\�f.�?\�g��\��\r.��\�<\�\�mZ]�ni�a�Ll�\�\�\�\�o�\�O_\��ߞ�\���\0x\�\�\�u,\�%�2\���R%=���2�\�t�����b�\\9[hu0�?\0;\0zH-�����t�2{J�ĩ��\�m��w�G�\r��\�8#@�S\"��\�:[z��\n\Z\���\0z\�\�Jew���?��\�Y\�z\�	=�\�\�C_.�\�\�g\�G쵽�^�ʜ��\�!z\0�\0z@-����/n��~u\��ȩ\�O\��\�Y>\\�\�z��?��\"\0\��\�o�[�s\�Q�2&\��o�{ɏ\'d\�}S�:\�?39N?\0�nSK�\\mf\�OR\\�\�|*K�\�1Y\�ĹCB\�\�_M\�|\0m\0�\�\�ҟk\�\�\�\�kL\�G���0Is\�)BQ��\��\�#\0�<@7��\�\�\��\�\�\'�\�5/�4(M^�Q(}=�\�8�?�\�\0�H]\�ߴ\�\�ҟ���7K�8!/E\�,�����b�\��\�\0�\�\�U�\�CK�[\�|�\�L\Z��:?`X��\�D�?�\�!\0t��\���.��YP��\��\�\'A^��Q\�\�>j�\�@W\�6\��{�TJ�\�,�Q[\�\�f4\�	�e�\�\�\�3\�\�zV\'E\��\�\n�a�V7\�Y��^�\�\�?/\�\�\�+\�g�\�p�\�\�0�?�.!\0\�mK���\�\�ҟ	}�d8{\�A\�xl�`��X\��t�\�|�@\���\0�XWV+O~�OO֊^\�_0��~xtL�\�\��\n\�\�ߧ�\��\Z�sp@\'~�p�46�Y�35�1\�\�8��c\�<���\��\�\��,\�Z%�Z��\Z\�\"\�\��w2u~��<?�\�@O\0ڡ.�oX�SO\�;7�Q\�x�v�+\�d\�\�\�\n�\�:�?��\"\0�\�\�\�{\�\�\�d�\�\�f9#�3\�\�₣2\�\�H�5�E\� �\�9\�\�,w�en\�ό����w\�ƫ\��\�cw\����\�@\0�sﻥ��\�\�ҟa�\�2>��7\Z�\�z~@�\r\�\���@�=5�\��\�\�,�Q�Y\�����\�\�5|~�,z\0Q@\03\�\�ҟ���\�\�^��u���\�\n\�XC\���\��\��\0��\0�}��V_mf\��\���x\���\�xy��2�\�\�\��D@�~\�.cK.�%Cӽs\�<Dғbe\�\�䌡�\�#\�E\� \�\0AK�Uɫ�*\�d����\�I9z���[�y\�9d�~$z\��D�\�@h\�\�BsK~vRn\�\�YxSBl\��5Q<��\��|\0\�a?\ZZ�ӿW�|\��,=��TG��/F\��\0z\0V\�u\0PK\�x�XOֻ-��\��\rr0C�p\�=??@�F�\�W\��X\�\�\�F\�\\�3*;9�F�r\�\�Y�+���0kR�\�w\0\�/\��\�ҟ\�{\�d�;�\�;\�$9�1\�\��s\�I�\����\��0>[O\0}�\r\0��Q,\�\�,\�9;�b>ep/=��\�s\���J\�\�X�H\�\���\�\�\0���<�\�\�ҟ\�@ tp\��4y\��\�/�\�N�\��)�\07,2�\�\�\���v\�\�q9ɲ\�\�\�2$��\�\���\�\0���,\�hf\�OjB�\\2K\�YC\�[\�8�\�g�\�\��\0\�U\0P�7��M�щ١=\��\�\�\�˂�Gȉz��?\0\�|\0\�|�OV�\�\�\�Zy���\�j�p\�\�7�U\�ߗ\��A�	\05\r͡o��r˩y�\�\��z�<���\��\0�\�\�;\��\�\�\�\�f��\�/Yf\�\�\0�s�\"\0��?\��W�\'멥?\�\�?\0\0�\�\��7K�-��4(M�8��?\0\0g\�|\0X��V�h����V�Ξ\�_O\0\08�\��ɥ?\�<6SF\�$\�	\0\0\�\�t\0xc[��bh\�:\�冓\�\�\0��y6\0��~u\�o\�u\�C\�\\\0\0p\���>1�\�G\�r\��~z\0��<\066˯\�(ѓ\�n:%Ozu\�W\0\0�@-�\�^Y�\'k\r\�$��\�\�\0\0\�\�\0PV\�(\��kv\�:\�\0\07\�\\\0����?_�&g\�\'\0\0\�#\�\�\�=\�s�\�\�\� H�\�,=Yo{U�\\��F\Z\r\�\�����tj��\�ӳ\�\�\�(�\��멭��x\�\�L\�\�UTVI\���zj+�w/IM�\�Y�h�cwY\�?;�	Ȁs?�nR}�V\�+詭�\�dI\'�+\�W!\�\r�Ò\�7C\�\�8	�=;Kˤ�\�\�\�S&F\�\����\�\'\�f�H_\�\�$W�\�\0\0fE\Z\0<S�;c\�\�?!��\�\��f�d\0�<\0\�GOnKh8+�Q�\��\0\0\�\�\0\�\�\�X\�Tm\�J����\�\�?\0�\�\\\0\�E�\�n\�\����\r�\�\�\0�˹>\0��;^\�\�\�܇��\�\"�e7\�	\0\0\�ru\08\��v�;�gfA�ı\�\0\��\�\�q#\�}�ީ��>�֓�\�d\'ȓ_\���?\Zjj\�d\�mzjK\�\�>b\�@=!\�\��R)ݷ_Om\r\�ϑ�\�,hj\�k�:��X\�\�8\�W7\�[Q)[v\�\�S[ٙ}d`n��n\�\�\�=\n9�@R�\��p^�ݺ 6&FΙ4AOѵa_���\�:iP\��ZL��\�\�#dB�\�\��`U�,^���\�\�\�&��0JO�j�&ټ�\�Þƌ(��<^�\�3o\�;��i�O\�\�m+.��\�\�\�֐��2j�P=!\�\�����JOmM7Z��E\�\�K\�/Y&M\�\�zj˷{\0nZ�\�ț�r\�\�\����\0`7W�7�WɋEM	��\��<=\0\�\r�\0\�����Z�:>K�f\�O\0�\�u\����\�%5z�VzR��\��=\0\��\n\0��\�2ki���\�󉹒�\�)U\0\0\�qU\0x\��=��\�\�\Z\��}\�\�\�9\Z\0\�M�	\0{6\�\�\�J\�d�\�&\�Kb,[\0\0\�\�\00�\���kғ�\�楄n�\0��\\\0�\�\�\�\�>ܫ\'\�\�>�\�6�\0\�D�\07/6�\�g\�\�>2q\0K\0\0\�\��\0\�\�\�*ya���?q1�\�T��\0\0�\�\� �\�g���?\��%G\�M\�\0\0\�\�\�\0\�̚rYQlf\�OZB����?\0\0�pl\0�oj�\�\�0�\�Gm�\�Ne\�\0�\0��|�l�0�\�\'�W�\\}B?=\0\�}�\0\�Mrﻻ\�d�\�&\�IJ��\�\0\0�ʑ\�zw�Y\n&�\�N��\�\�\0\0�\�\0��B-�)ӓ\�/1l�\0��\����v��\0h\�\�\�z˔��\�\0�8*\0���Z^Xof\�Ol  �&\�\�	\0\0qL\08�\�\�̵�ȥ�3edK\0\0�\�\0\�\�\�\�\'\0&�&\�\�\�\'\�\�	\0\0�qD\0P���7J\�d���-�i\�z\0�\0|_-��ӓ�\�o�?��\'\0\0�\�\�\0PQ\�$w/3�\�\�\�S\�$��?\0\0����p\�[\��\�/I.:6CO\0\0���@\���\�J�K��n�\0�\�l\r\07.\�)u���L\Z�&�a\�\0\0�m�\�\��\�W]�\0�V�\0\�K�yl����\'\0\0`K\0�\�ZsK�\�b䆓\�\�\0\0\�@-��m���?׍ϖ�Y�\0@8\�\��+\�-�\�\0�\�\0\01\Z\0\�ҟ߾cn\�ύ_ȕ^��z\0\0�\r\0w\Z\\�3�o�\\:���\0\0@8c@-�y\�\�ҟ|�S\��\0�6���\�2�\�\�\�\�\�a�\�\0\0>\�H\0xoW�\���BO\�R\���O\�o\0\0@��\0�K.<:C�\�M\�\0\0h�\�\�u�l���?Iq1r˩,�\0\�p,\0�\\Z�e�\�͒�\�	z\0\0�4\046�Ȧr3K2�b\�G\'\�\�	\0\0t\�\�\0`�Z��B\0\0\08<K����!\�	\�\�\�\�\0\0\�\�\�\0��\��O�e\�\0\0]\��\00>?U�2���\0\0@W�:\0���L\��\0\0t���WG�˄�z\0\0]\�\�\0�:�_�\�\0�qm\0�\��,���\'\0\0\��\0}c\�\'�\�\0��re\0��\�\�L�\�\0\0\�.\��A}\��\�	\0\0\�\��m�\�$��?\0\0D\�U`l^��?2CO\0\0��\\\0f�֟�?\0\0D�k��\�}d\"K\0\0�\nW����\�\�\�\0\0�\�\�\�\��ʑ}�\�\0\0\"\��\0��#?���\'\0\0\r�\0j\�_v*K\0\0�&G��^\�r5K\0\0�:G\0�\�\'%\�_S\0\0�U�\�zlv�\\xT��\0\0@496\0̞�/1l�\0��\0g\r\�-S\�\�\0\0�6\��\�@@fM\�\�\0\0��\��%�3\�~,�\0�J�\n\0�	1r\�\�,�\0�j����˞��\�\�N�\�\��]\��\�\��\�\�\�\�o��]��q\�w4m\�|\�v\���	$�7\�j\�\�9�\�\�k�z��\�]G\�\�M=\�Жz�\�\�MB�\�\�;׾\���N�21�\�:\�\0\0\0��H\0q\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�\0\0\0\�!\0\0\0>\�m�\0\0�}�\0\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�\0\0\0\�!\0\0\0>D\0\0\0��\0\0\0�\0\0\0\"\0\0\0\�C\0\0\0|�\0\0\0�\0\0\0\�!\0\0\0>D\0\0\0�wD�Û�hw�9\�\0\0\0\0IEND�B`�','pune','ksumer597@gmail.com','sumer khan','34567890','$2a$10$WUyrpqI5vA/A0nHYNWS.ke0nKbkXXn69lH3hDT/82VAaog.N/0.pq','sumer@12');
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
