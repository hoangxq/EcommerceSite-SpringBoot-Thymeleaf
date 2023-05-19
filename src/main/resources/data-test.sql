-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_springboot
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (33,5,28,3),(34,3,28,4),(242,1,241,40),(243,2,241,114),(245,2,241,155);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK64t7ox312pqal3p7fg9o503c2` (`user_id`),
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (28,27),(30,29),(32,31),(162,161),(219,218),(222,221),(241,240),(247,246);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'This is a category','Phone'),(2,'This is a category','Tablet'),(76,'This is a category','Mouse & Keyboard'),(77,'This is a category','Charger & Cable'),(123,'This is a category','Camera & Webcam'),(251,'this is a category','Headphone');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (262);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `receiver_address` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_phone` varchar(255) DEFAULT NULL,
  `time_order` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (214,'KTX B2, Mộ Lao, Hà Đông, Hà Nội','Nguyễn Huy Hoàng','0838978446','2022-07-01 14:14:43',31,13818000),(228,'Thanh Xuân, Hà Nội','Phạm Văn Long','0987883924','2022-07-01 15:58:55',221,8945000),(253,'KTX B2, Mộ Lao, Hà Đông, Hà Nội','Nguyễn Huy Hoàng','0838978446','2022-07-02 18:28:07',31,275000),(258,'KTX B2, Mộ Lao, Hà Đông, Hà Nội','Nguyễn Huy Hoàng','0838978446','2022-07-03 22:02:56',31,11910000);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oser_items`
--

DROP TABLE IF EXISTS `oser_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oser_items` (
  `id` bigint NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3p2oecvrn9fg7yc1kc8gwc5y` (`order_id`),
  KEY `FK3lcviajoxt8e29x1bi0hp3fty` (`product_id`),
  CONSTRAINT `FK3lcviajoxt8e29x1bi0hp3fty` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKf3p2oecvrn9fg7yc1kc8gwc5y` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oser_items`
--

LOCK TABLES `oser_items` WRITE;
/*!40000 ALTER TABLE `oser_items` DISABLE KEYS */;
INSERT INTO `oser_items` VALUES (215,1,214,37),(216,2,214,114),(229,1,228,3),(230,1,228,152),(231,2,228,102),(254,1,253,156),(259,1,258,40),(260,2,258,112),(261,1,258,252);
/*!40000 ALTER TABLE `oser_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKo61fmio5yukmmiqgnxf8pnavn` (`name`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (3,'This is a phone','Samsung A53 5G','https://cf.shopee.vn/file/257161e1968ecabd2b28c21635997553',8500000,140,1),(4,'This is a Aplle ipad','Ipad Apple Air 4','https://shopdunk.com/wp-content/uploads/2021/07/iPad-Air-4-Rose-Silver-2.webp',16400000,50,2),(36,NULL,'Samsung Galaxy S22 Ultra 5G','https://cdn.tgdd.vn/Products/Images/42/235838/Galaxy-S22-Ultra-Burgundy-600x600.jpg',30990000,500,1),(37,NULL,'iPhone 11','https://cdn.tgdd.vn/Products/Images/42/153856/iphone-xi-xanhla-600x600.jpg',12490000,500,1),(38,NULL,'OPPO Reno7 series','https://cdn.tgdd.vn/Products/Images/42/271717/oppo-reno7-z-5g-thumb-1-1-600x600.jpg',10490000,500,1),(39,NULL,'Realme C35','https://cdn.tgdd.vn/Products/Images/42/261888/realme-c35-thumb-new-600x600.jpg',4290000,500,1),(40,NULL,'Xiaomi 11T','https://cdn.tgdd.vn/Products/Images/42/248293/Xiaomi-11T-White-1-2-3-600x600.jpg',10990000,500,1),(41,NULL,'Samsung Galaxy A03','https://cdn.tgdd.vn/Products/Images/42/251856/samsung-galaxy-a03-xanh-thumb-600x600.jpg',2990000,500,1),(42,NULL,'Xiaomi Redmi Note 11 Series','https://cdn.tgdd.vn/Products/Images/42/270471/xiaomi-redmi-note-11-pro-trang-thumb-600x600.jpg',7490000,500,1),(43,NULL,'Xiaomi Redmi Note 11S series','https://cdn.tgdd.vn/Products/Images/42/272668/xiaomi-redmi-note-11s-5g- thumb-600x600.jpg',6290000,500,1),(44,NULL,'iPhone 13 Pro Max','https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-gold-1-600x600.jpg',30490000,500,1),(46,NULL,'Vivo Y15 series','https://cdn.tgdd.vn/Products/Images/42/249720/Vivo-y15s-2021-xanh-den-660x600.jpg',3490000,500,1),(48,NULL,'Samsung Galaxy Z Fold3 5G','https://cdn.tgdd.vn/Products/Images/42/226935/samsung-galaxy-z-fold-3-silver-1-600x600.jpg',36990000,500,1),(51,NULL,'iPhone 13 Pro','https://cdn.tgdd.vn/Products/Images/42/230521/iphone-13-pro-sierra-blue-600x600.jpg',27490000,500,1),(52,NULL,'iPhone 12 Pro','https://cdn.tgdd.vn/Products/Images/42/228738/iphone-12-pro-xam-new-600x600-600x600.jpg',26290000,500,1),(53,NULL,'Samsung Galaxy S22+ 5G','https://cdn.tgdd.vn/Products/Images/42/242439/Galaxy-S22-Plus-White-600x600.jpg',25990000,500,1),(55,NULL,'Samsung Galaxy S22 5G','https://cdn.tgdd.vn/Products/Images/42/231110/Galaxy-S22-Black-600x600.jpg',21990000,500,1),(59,NULL,'iPad Pro M1 12.9 inch 5G','https://cdn.tgdd.vn/Products/Images/522/238649/ipad-pro-2021-129-inch-gray-600x600.jpg',29290000,500,2),(61,NULL,'iPad Pro M1 12.9 inch WiFi','https://cdn.tgdd.vn/Products/Images/522/237699/ipad-pro-m1-129-inch-wifi-gray-600x600.jpg',25490000,500,2),(62,NULL,'iPad Pro M1 11 inch 5G','https://cdn.tgdd.vn/Products/Images/522/238624/ipad-pro-2021-11-inch-silver-600x600.jpg',24490000,500,2),(63,NULL,'iPad Pro M1 11 inch WiFi','https://cdn.tgdd.vn/Products/Images/522/237695/ipad-pro-m1-11-inch-wifi-gray-9-600x600.jpg',19990000,500,2),(66,NULL,'iPad mini 6 WiFi Cellular','https://cdn.tgdd.vn/Products/Images/522/250734/ipad-mini-6-wifi-cellular-grey-1-600x600.jpg',17190000,500,2),(67,NULL,'iPad Air 4 10.9 inch 4G','https://cdn.tgdd.vn/Products/Images/522/228897/ipad-4-cellular-den-new-600x600-600x600.jpg',16490000,500,2),(69,NULL,'iPad Air 4 10.9 inch WiFi','https://cdn.tgdd.vn/Products/Images/522/228808/ipad-air-4-wifi-64gb-2020-bac-600x600-600x600.jpg',14590000,500,2),(71,NULL,'iPad mini 6 WiFi','https://cdn.tgdd.vn/Products/Images/522/248091/ipad-mini-6-wifi-pink-1-600x600.jpg',13690000,500,2),(73,NULL,'iPad 9 WiFi Cellular','https://cdn.tgdd.vn/Products/Images/522/250731/ipad-gen-9-wifi-cellular-sliver-600x600.jpg',12190000,500,2),(101,NULL,'Cáp Type C- Lightning Apple MM0A3','https://cdn.tgdd.vn/Products/Images/58/259283/cap-type-c-lightning-1m-apple-mm0a3-trang-thumb-600x600.jpeg',501000,500,77),(102,NULL,'Cáp Lightning 1m Hydrus CS-C-021','https://cdn.tgdd.vn/Products/Images/58/249407/caplightning1mhydruscs-c-021-ava-600x600.jpg',75000,500,77),(103,NULL,'Cáp Type C - Type C 2m Hydrus DS465','https://cdn.tgdd.vn/Products/Images/58/278299/cap-type-c-type-c-2m-hydrus-ds465-thumb-600x600.jpeg',100000,500,77),(104,NULL,'Cáp Type C - Type C 1m Hydrus DS464','https://cdn.tgdd.vn/Products/Images/58/278298/cap-type-c-type-c-1m-hydrus-ds464-thumb-600x600.jpeg',100000,500,77),(105,NULL,'Cáp Type C - Type C M-Best SMXU75','https://cdn.tgdd.vn/Products/Images/58/182154/cap-type-c-type-c1m-m-best-smxu75-trang-avatar-1-600x600.jpg',75000,500,77),(106,NULL,'Cáp Type C Xmobile MU09-1000X','https://cdn.tgdd.vn/Products/Images/58/216288/cap-type-c-1m-xmobile-mu09-1000x-trang-600x600.jpg',75000,500,77),(107,NULL,'Cáp Micro USB Anker PowerLine+ A8143','https://cdn.tgdd.vn/Products/Images/58/205786/cap-micro-usb-1m8-anker-a8143-xam-avatar-1-600x600.jpg',195000,500,77),(108,NULL,'Cáp Lightning e.VALU LTL-02','https://cdn.tgdd.vn/Products/Images/58/88355/cap-lightning-20cm-evalu-ltl-02-avatar-ava-600x600.jpg',63000,500,77),(109,NULL,'Cáp Lightning Anker PowerLine+ A8121xx2','https://cdn.tgdd.vn/Products/Images/58/205792/cap-lightning-mfi-09m-anker-a8121xx2-do-avatar-1-600x600.jpg',277000,500,77),(110,NULL,'Chuột không dây Gaming Asus TUF M4 WL','https://cdn.tgdd.vn/Products/Images/86/279454/chuot-khong-day-gaming-asus-tuf-m4-wl-thumb-1-600x600.jpeg',1190000,500,76),(111,NULL,'Chuột có dây Gaming Asus Keris','https://cdn.tgdd.vn/Products/Images/86/279453/chuot-co-day-gaming-asus-keris-thumb-1-600x600.jpeg',1250000,500,76),(112,NULL,'Chuột có dây DareU EM908 Hồng','https://cdn.tgdd.vn/Products/Images/86/275534/chuot-co-day-dareu-em908-hong-thumb-1-600x600.jpeg',335000,500,76),(113,NULL,'Chuột không dây DareU A918','https://cdn.tgdd.vn/Products/Images/86/272999/chuot-khong-day-dareu-a918-thumb-600x600.jpeg',479000,500,76),(114,NULL,'Chuột có dây DareU A960','https://cdn.tgdd.vn/Products/Images/86/272997/chuot-co-day-dareu-a960-thumb-1-600x600.jpeg',639000,500,76),(115,NULL,'Chuột có dây DareU EM908 Đen','https://cdn.tgdd.vn/Products/Images/86/272898/chuot-co-day-dareu-em908-den-thumb-1-600x600.jpeg',319000,500,76),(116,NULL,'Chuột Bluetooth A4Tech FB35 Đen','https://cdn.tgdd.vn/Products/Images/86/271840/chuot-bluetooth-a4tech-fb35-den-thumb-600x600.jpg',390000,500,76),(117,NULL,'Chuột Không Dây A4Tech FG35','https://cdn.tgdd.vn/Products/Images/86/271838/chuot-khong-day-a4tech-fg35-xam-thumb-600x600.jpeg',250000,500,76),(118,NULL,'Chuột không dây A4tech FG30','https://cdn.tgdd.vn/Products/Images/86/271835/chuot-khong-day-a4tech-fg30-xanh-thumb-600x600.jpeg',250000,500,76),(119,NULL,'Chuột không dây A4Tech FG20','https://cdn.tgdd.vn/Products/Images/86/271834/chuot-khong-day-a4tech-fg20-xam-den-thumb-600x600.jpeg',300000,500,76),(120,NULL,'Chuột không dây A4Tech G3-280N Đen đỏ','https://cdn.tgdd.vn/Products/Images/86/271832/chuot-khong-day-a4tech-g3-280n-den-do-thumb-600x600.jpeg',200000,500,76),(121,NULL,'Chuột Bluetooth Microsoft Ocean Plastic Xám Trắng','https://cdn.tgdd.vn/Products/Images/86/268634/chuot-bluetooth-microsoft-ocean-plastic-xam-trang-thumb-600x600.png',699000,500,76),(122,NULL,'Chuột Bluetooth Microsoft Camo','https://cdn.tgdd.vn/Products/Images/86/268632/chuot-bluetooth-microsoft-camo-xam-trang-thumb-1-600x600.png',699000,500,76),(124,NULL,'Camera IP Ngoài Trời 4MP TP-link Tapo C320WS Trắng','https://cdn.tgdd.vn/Products/Images/4728/278311/camera-ip-ngoai-troi-4mp-tp-link-tapo-c320ws-trang-0-600x600.jpg',1219000,500,123),(125,NULL,'Camera IP Ngoài Trời 2MP Imou Bullet 2E-D Trắng','https://cdn.tgdd.vn/Products/Images/4728/267681/camera-ip-ngoai-troi-2mp-imou-bullet-2e-d-trang-thumb-600x600.jpg',1253000,500,123),(126,NULL,'Camera IP Ngoài Trời 2MP Imou Bullet 2C-D Trắng','https://cdn.tgdd.vn/Products/Images/4728/267678/camera-ip-ngoai-troi-2mp-imou-bullet-2c-d-trang-thumb-600x600.jpg',1085000,500,123),(127,NULL,'Camera IP 1080P Imou Cue 2E-D Trắng','https://cdn.tgdd.vn/Products/Images/4728/263787/camera-ip-1080p-imou-cue-2e-d-trang-thumb-600x600.jpg',714000,500,123),(128,NULL,'Webcam 720P Logitech C505 Đen','https://cdn.tgdd.vn/Products/Images/4728/257340/webcam-720p-logitech-c505-den-600x600.jpg',909000,500,123),(129,NULL,'Webcam 720P Logitech C310 Đen','https://cdn.tgdd.vn/Products/Images/4728/256866/webcam-720p-logitech-c310-den-600x600.jpg',699000,500,123),(130,NULL,'Webcam 1080P A4Tech PK-920H Đen','https://cdn.tgdd.vn/Products/Images/4728/253261/webcam-1080p-a4tech-pk-920h-den-1.-600x600.jpg',800000,500,123),(131,NULL,'Webcam 480P A4Tech PK-635G Bạc','https://cdn.tgdd.vn/Products/Images/4728/253257/webcam-480p-a4tech-pk-635g-bac-1.-600x600.jpg',450000,500,123),(132,NULL,'Webcam 480P A4Tech PK-710G Đen','https://cdn.tgdd.vn/Products/Images/4728/253161/webcam-480p-a4tech-pk-710g-den-1.-600x600.jpg',550000,500,123),(133,NULL,'Webcam 720P Genius 1000X Đen','https://cdn.tgdd.vn/Products/Images/4728/252767/webcam-720p-genius-1000x-den-ava-600x600.jpg',419000,500,123),(134,NULL,'Camera Hành Trình 1080P Vietmap R1 Đen','https://cdn.tgdd.vn/Products/Images/4728/249271/camera-hanh-trinh-vietmap-r1-den-1.-1-600x600.jpg',3192000,500,123),(135,NULL,'Webcam 1080P Asus Rog Eye S Đen','https://cdn.tgdd.vn/Products/Images/4728/247730/webcam-1080p-asus-rog-eye-s-den-1.-600x600.jpg',1912000,500,123),(136,NULL,'Webcam 1080P Asus C3 Đen','https://cdn.tgdd.vn/Products/Images/4728/247710/webcam-1080p-asus-c3-den-1.-600x600.jpg',1000000,500,123),(137,NULL,'Camera IP Ngoài Trời 1080P Imou Bullet 2E Trắng','https://cdn.tgdd.vn/Products/Images/4728/245447/camera-ip-ngoai-troi-2mp-imou-bullet-2e-ava-600x600.jpg',1390000,500,123),(138,NULL,'Camera IP 360 Độ 1536P TP-Link Tapo C210 Trắng','https://cdn.tgdd.vn/Products/Images/4728/242566/camera-ip-360-do-3mp-tp-link-tapo-c210-1-1-600x600.jpg',899000,500,123),(139,NULL,'Camera IP 360 Độ 1080P Xiaomi Mi Home BHR4885GL Trắng','https://cdn.tgdd.vn/Products/Images/4728/242564/camera-ip-mi-home-360-do-1080p-xiaomi-bhr4885gl-ava-600x600.jpg',799000,500,123),(140,NULL,'Camera Ngoài Trời 1080P Imou Bullet 2C Trắng','https://cdn.tgdd.vn/Products/Images/4728/242562/camera-ip-ngoai-troi-2mp-imou-bullet-2c-ava-600x600.jpg',1090000,500,123),(141,NULL,'Camera IP 360 Độ 1080P Imou Ranger 2 Trắng','https://cdn.tgdd.vn/Products/Images/4728/242546/camera-ip-360-do-1080p-imou-ranger-2-1-2-600x600.jpg',1090000,500,123),(142,NULL,'Webcam 1440P Rapoo C280 Đen','https://cdn.tgdd.vn/Products/Images/4728/242329/webcam-1440p-rapoo-c280-ava-600x600.jpg',833000,500,123),(143,NULL,'Webcam 1080P Rapoo C260 Đen','https://cdn.tgdd.vn/Products/Images/4728/242328/webcam-1080p-rapoo-c260-ava-600x600.jpg',559000,500,123),(144,NULL,'Bàn phím Microsoft Surface Pro Type','https://cdn.tgdd.vn/Products/Images/4547/273093/microsoft-surface-pro-type-1.-600x600.jpg',3450000,500,76),(145,NULL,'Bàn Phím Bluetooth DareU EK868','https://cdn.tgdd.vn/Products/Images/4547/273016/bluetooth-dareu-ek868-1.-600x600.jpg',1166000,500,76),(146,NULL,'Bàn Phím Có Dây DareU EK87','https://cdn.tgdd.vn/Products/Images/4547/273011/co-day-dareu-ek87-1.-600x600.jpg',649000,500,76),(147,NULL,'Bộ Bàn Phím Chuột Không Dây A4tech FG1112 Đen','https://cdn.tgdd.vn/Products/Images/4547/271841/bo-chuot-khong-day-a4tech-fg1112-den-1.-600x600.jpg',455000,500,76),(148,NULL,'Bàn Phím Cơ Có Dây Gaming Rapoo V500Pro Vàng Xanh','https://cdn.tgdd.vn/Products/Images/4547/246178/co-co-day-gaming-rapoo-v500pro-vang-xanh-thumb-600x600.jpg',891000,500,76),(149,NULL,'Bàn Phím Cơ Có Dây Gaming Razer BlackWidow','https://cdn.tgdd.vn/Products/Images/4547/243202/co-co-day-gaming-razer-blackwidow-ava-600x600.jpg',2880000,500,76),(150,NULL,'Sạc 2 cổng Hydrus ACL2018','https://cdn.tgdd.vn/Products/Images/9499/272201/adapter-sac-usb-hydrus-acl2018-thumb-2-600x600.jpeg',100000,500,77),(151,NULL,'Sạc 3 cổng Samsung EP-T6530N','https://cdn.tgdd.vn/Products/Images/9499/271813/adapter-sac-usb-type-c-pd-65w-samsung-ep-t6530n-thumb-600x600.jpeg',1192000,500,77),(152,NULL,'Sạc 2 cổng Xmobile QP-1EU','https://cdn.tgdd.vn/Products/Images/9499/214802/adapter-sac-2-cong-type-c-pd-qc30-xmobile-qp-1eu-avatar-1-600x600.jpg',245000,500,77),(153,NULL,'Sạc Samsung EP-TA800N','https://cdn.tgdd.vn/Products/Images/9499/234361/type-c-pd-25w-samsung-ep-ta800n-den-thumb-1-2-600x600.jpg',490000,500,77),(154,NULL,'Sạc AVA DS433-WB','https://cdn.tgdd.vn/Products/Images/9499/197293/adapter-sac-21a-ava-ds433-wb-trang-600x600.jpg',72000,500,77),(155,NULL,'Sạc 2 cổng Xmobile DS714','https://cdn.tgdd.vn/Products/Images/9499/230267/adapter-2-cong-type-c-pd-18w-xmobile-ds714-600x600.jpg',320000,500,77),(156,NULL,'Đế sạc không dây Xmobile TS-C106W-B','https://cdn.tgdd.vn/Products/Images/9499/218375/de-sac-khong-day-qi-10w-xmobile-ts-c106w-b-den-avatar-1-600x600.jpg',225000,500,77),(157,NULL,'Sạc 4 cổng Xmobile DS931-WB','https://cdn.tgdd.vn/Products/Images/9499/216063/adapter-sac-4-cong-usb-type-c-48a-xmobile-ds931-wb-600x600.jpg',360000,500,77),(158,NULL,'Sạc xe hơi 2 cổng Xmobile SN-177','https://cdn.tgdd.vn/Products/Images/9499/274201/sac-xe-hoi-2-cong-24a-xmobile-sn-177-den-thumb-600x600.jpeg',250000,500,77),(159,NULL,'Sạc Belkin WCA003','https://cdn.tgdd.vn/Products/Images/9499/232644/adapter-sac-type-c-pd-20w-belkin-wca003-trang-avatar-1-600x600.jpg',376000,500,77),(160,NULL,'Đế sạc không dây Mbest AC63F3','https://cdn.tgdd.vn/Products/Images/9499/210429/sac-khong-day-qi-10w-mbest-ac63f3-den-avatar-1-600x600.jpg',175000,500,77),(238,'This is a ipad','Ipad air 5','\\image\\ipad-air-5-wifi-blue-thumb-600x600.jpg',8000000,2,2),(252,'This is a Headphone','Tai nghe sony 450 - 1','\\image\\tai-nghe-sony-450-1.jpg',200000,50,251);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfnranlqhubvw04boopn028e6` (`username`,`email`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (24,'admin@gmail.com','$2a$10$fQNUhUao9/2ohNkLmHiR9.1wB2f2l8u1AzsmNNjwGN0qUD39A6kwG','admin',1),(25,'admin1@gmail.com','$2a$10$jpbTqjcRSo8MKqwXo0y9ZOxrM9FhC9dSMjMoAj2q68q0xhM8a/u62','admin1',1),(26,'admin2@gmail.com','$2a$10$t6X6p86yaOgyDU0SLJgv4eVVeXBDKZWJZnxFvEWE/anJKyXH0xV1W','admin2',1),(27,'hoangxq01@gmail.com','$2a$10$v/tsPtBm8KohJ0OFLvBtZeZYaMEV7QVQ9TKatYWQYTODiFswFBoEK','hoangxq01',2),(29,'hoangxq02@gmail.com','$2a$10$IauI1QZeMBGI3LA1EaCRH.55Df0a.M5znvoUp3aiL5AAUYQIwrhB2','hoangxq02',2),(31,'hoangxq03@gmail.com','$2a$10$KFYtpdwHbUYy20KztNL7qOk5DQES7S09kZlVfCBsBTW79.ZGJx6C6','hoangxq03',2),(161,'hoangxq05@gmail.com','$2a$10$khnWOK5lDNtFvSX.3GYsjO4c0pguWoUz8eUwZif.phxfGketM8gEy','hoangxq05',2),(218,'hoangxq06@gmail.com','$2a$10$TRiBWkauRtXhAdeHxP43DehzGb4AIT7JJZmH0N3Hcs0rYAHXwhuau','hoangxq06',2),(221,'hoangxq07@gmail.com','$2a$10$j3mW2RPef.ZXhc7zoQOoS.amOtc.fcY5kUNIjCsUBMiYOyls9J72W','hoangxq07',2),(240,'hoangxq08@gmail.com','$2a$10$WAR1KudfqGRcQE0qe1IpU.wA3NcmC8p.s8hFdADi1yVp6nB.cCE4a','hoangxq08',2),(246,'hoangxq11@gmail.com','$2a$10$6SSguPSWsEKDsT09YRE7Ke2k3fCPn8qhpb4gnlxRo1AmzS2Z/iLKy','hoangxq11',2);
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

-- Dump completed on 2023-04-20 16:59:38
