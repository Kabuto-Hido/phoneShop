-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: webphone
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Apple','https://banner2.cleanpng.com/20180527/vc/kisspng-iphone-6-apple-logo-clip-art-5b0b05c4a6da61.2209565515274490286834.jpg',NULL),(2,'Samsung','https://images.samsung.com/is/image/samsung/assets/vn/about-us/brand/logo/mo/256_144_4.png?$512_N_PNG$',NULL),(3,'Oppo','https://media.loveitopcdn.com/3807/logo-oppo-1.jpg',NULL),(7,'Redmi','https://fdn.gsmarena.com/imgroot/news/21/03/xiaomi-new-logo/-1200/gsmarena_004.jpg','Is a subsidiary company owned by the Chinese electronics company Xiaomi');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `unit_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (6,1,2,2,24000000),(7,1,3,2,5600000);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `voucher` double DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `ship_code` varchar(45) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_ibpk` (`user_id`),
  CONSTRAINT `orders_ibpk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,1,1,'2022-05-17 18:33:49',NULL,7500000,'Da thanh toan'),(3,1,1,'2022-05-17 18:34:38',NULL,12000000,'Da thanh toan'),(4,1,1,'2022-05-26 13:50:22',NULL,29600000,'Chua thanh toan');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orrderdetail`
--

DROP TABLE IF EXISTS `orrderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orrderdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit_price` double NOT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderdetail_ibpk_idx` (`product_id`),
  KEY `orderdetail_ibpk2_idx` (`order_id`),
  CONSTRAINT `orderdetail_ibpk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `orderdetail_ibpk2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orrderdetail`
--

LOCK TABLES `orrderdetail` WRITE;
/*!40000 ALTER TABLE `orrderdetail` DISABLE KEYS */;
INSERT INTO `orrderdetail` VALUES (3,4,1,7500000,2),(4,2,1,12000000,3);
/*!40000 ALTER TABLE `orrderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `sale` double DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `amount` int NOT NULL DEFAULT '1',
  `price` double NOT NULL,
  `id_brand` int DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_ibfk_1_idx` (`id_brand`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'Iphone X',100,6000000,1,'The iPhone X (Roman numeral \"X\" pronounced \"ten\", also known as iPhone 10)[11][12] is a smartphone designed, developed and marketed by Apple Inc. ','https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-hh-600x600.jpg'),(1,2,'Iphone 11',50,12000000,1,'Thiết kế iPhone 11 không khác nhiều so với các thông tin được rò rỉ trước đó. Và điều khiến tôi thất vọng nhất trong thiết kế là mặt trước. Apple vẫn trung thành với màn hình “tai thỏ”. Trong khi đó các dòng điện thoại cao cấp Android đã sử dụng màn hình notch, màn hình không viền vô cùng ấn tượng.','https://cdn.hoanghamobile.com/i/preview/Uploads/2020/09/17/apple-iphone-11-64gb-6.1.jpg'),(0.2,3,'Samsung A12',20,3500000,2,'Samsung Galaxy A12 mobile was launched on 24th November 2020. The phone comes with a 6.50-inch touchscreen display offering a resolution of 720x1600 pixels and an aspect ratio of 20:9. Samsung Galaxy A12 is powered by a 1.8GHz octa-core MediaTek Helio P35 (MT6765) processor that features 4 cores clocked at 1.8GHz and 4 cores clocked at 2.3GHz.','https://cdn.tgdd.vn/Products/Images/42/251886/samsung-galaxy-a12-2021-blue-600x600.jpg'),(1,4,'Oppo Reno 7',27,7500000,3,'It has a full-HD+ resolution, 90Hz refresh rate and Corning Gorilla Glass 5 protection. Oppo hasn\'t bothered refreshing the SoC in the Reno 7 as it\'s still the MediaTek Dimensity 900, along with has 8GB of RAM and 256GB of storage.','https://cdn.tgdd.vn/Products/Images/42/247361/oppo-reno7-5g-den-thumb-1-600x600.jpg'),(1,7,'Redmi Note 11',8000,4900000,7,'Best selling price','https://i01.appmifile.com/webfile/globalimg/products/pc/redmi-note-11-pro/specs01.png'),(1,8,'Iphone 11 Pro Max',500,16500000,1,'The iPhone 11 Pro Max display has rounded corners that follow a beautiful curved design, and these corners are within a standard rectangle. When measured as a standard rectangular shape, the screen is 6.46 inches diagonally.','https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-600x600.jpg'),(1,9,'Iphone 12 Pro Max',300,27000000,1,' Is powered by the new Apple A14 Bionic processor, comes with a 6.7 inche Super Retina XDR OLED capacitive touchscreen and 2778 x 1284 p resolution. It features an HDR display and True Tone, and Wide color (P3) gamut.','https://iphonepro.vn/wp-content/uploads/2020/10/apple-iphone-12-pro-max-5878-16.jpg'),(0.2,10,'Iphone XR',440,13000000,1,'The iPhone XR is the least expensive device in Apple\'s twelfth generation of iPhones which also includes the iPhone XS and XS Max. Considered an \"affordable flagship\" or \"budget flagship\" at its release, the XR shares key internal hardware but with features removed/downgraded to reduce the price.','https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-do-600-1-200x200.jpg'),(1,11,'Iphone SE',430,11200000,1,'The iPhone SE is Apple\'s entry-level iPhone, starting at $429. The device offers an affordable entry point to the iPhone lineup, with many important features such as a powerful A-series chip, a high-quality camera with 4K video recording, Haptic Touch, wireless charging, water and dust resistance, and more.','https://cdn.tgdd.vn/Products/Images/42/230412/iphone-se-2020-do-600x600-200x200.jpg'),(1,12,'Iphone 12 Mini',9000,18900000,1,'The iPhone 12 mini is the smallest of four iPhone models Apple revealed in its October 2020 \"Hi, Speed\" event. It has a 5.4-inch Super Retina XDR display, the A14 Bionic processor, and a dual-camera system.','https://cdn.tgdd.vn/Products/Images/42/228741/iphone-12-mini-xanh-duong-200x200.jpg'),(0.1,13,'Iphone 11 Pro',510,15900000,1,'Apple iPhone 11 Pro is powered by the Apple A13 Bionic processor. The smartphone comes with a 5.8 inches Super Retina XDR OLED capacitive touchscreen and 1125 x 2436 pixels resolution. The screen of the device is protected by Scratch-resistant glass and oleophobic coating.','https://didongviet.vn/pub/media/catalog/product//i/p/iphone-11-pro-256gb-didongviet_12.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `permission` int NOT NULL,
  `registerdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Kabuto','123456','a@gmail.com','0937302331','sssss','Kabuto',0,'2022-05-11 00:00:00'),(3,'admin','admin','ngobuituananh@gmail.com','0123456789','Thu Duc','admin',1,'2022-05-18 00:00:00'),(5,'phianh','phianh2001','phianh@gmail.com','0123654789','Thu Duc','Phi Anh',1,'2022-05-25 12:18:54');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-30  8:34:59
