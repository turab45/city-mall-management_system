/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.0.22-community-nt : Database - shop_management_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop_management_system` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `shop_management_system`;

/*Table structure for table `accounts` */

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL auto_increment,
  `account_title` varchar(255) default NULL,
  `bank_name` varchar(255) default NULL,
  `account_number` varchar(255) default NULL,
  `current_balance` double(11,2) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `accounts` */

LOCK TABLES `accounts` WRITE;

insert  into `accounts`(`account_id`,`account_title`,`bank_name`,`account_number`,`current_balance`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,'HBL Account','HBL','782134120901',-785.00,NULL,'2020-10-20',0,'2020-12-08',0,1),(2,'JazzCash','JazzCash','265026860811',115.00,NULL,'2020-10-20',0,'2020-11-22',0,1),(9,'Paypal','Paypal','442016451461',0.00,NULL,'2020-10-20',0,NULL,NULL,0),(10,'Test','Test','007060152722',1234.00,NULL,'2020-12-08',0,NULL,NULL,0),(11,'T1','T21','577621773154',12.00,NULL,'2020-12-08',0,'2020-12-08',0,0),(12,'T','T','561106180175',12.00,NULL,'2020-12-08',0,NULL,NULL,0);

UNLOCK TABLES;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL auto_increment,
  `category` varchar(255) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `category` */

LOCK TABLES `category` WRITE;

insert  into `category`(`category_id`,`category`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,'General',NULL,'2020-10-20',1,'2020-10-21',0,1),(2,'Test',NULL,'2020-10-20',1,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL auto_increment,
  `order_code` varchar(255) default NULL,
  `order_date` date default NULL,
  `customer_name` varchar(255) default NULL,
  `customer_address` varchar(255) default NULL,
  `grand_total` double(11,2) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order` */

LOCK TABLES `order` WRITE;

insert  into `order`(`order_id`,`order_code`,`order_date`,`customer_name`,`customer_address`,`grand_total`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (47,'AY-4376','2020-10-23','Turab','Bajeer Mohalla islamkot, Tharparkar',560.00,NULL,'2020-10-23',0,NULL,NULL,1),(48,'RG-5449','2020-10-24','turaf','acas',290.00,NULL,'2020-10-24',0,NULL,NULL,1),(49,'LV-3321','2020-10-25','ayoob','akfjsdfvnwk',585.00,NULL,'2020-10-25',0,NULL,NULL,1),(50,'MV-1487','2020-10-25','112','211',340.00,NULL,'2020-10-25',0,NULL,NULL,1),(51,'MQ-5156','2020-10-25','112','211',220.00,NULL,'2020-10-25',0,NULL,NULL,1),(52,'JN-9525','2020-10-25','tuhao','kjfnask',340.00,NULL,'2020-10-25',0,NULL,NULL,1),(53,'VZ-6104','2020-10-25','kjk','ln',700.00,NULL,'2020-10-25',0,NULL,NULL,1),(54,'AS-2347','2020-11-22','Jatender','uk',265.00,NULL,'2020-11-22',0,NULL,NULL,1),(55,'XM-3216','2020-11-22','Jatendr','uk',265.00,NULL,'2020-11-22',0,NULL,NULL,1),(56,'GG-3635','2020-11-22','n','n',25.00,NULL,'2020-11-22',0,NULL,NULL,1),(57,'QP-3078','2020-11-22','ja','k',120.00,NULL,'2020-11-22',0,NULL,NULL,1),(58,'QF-6793','2020-11-22','1','1',120.00,NULL,'2020-11-22',0,NULL,NULL,1),(59,'DV-6552','2020-11-22','j','j',25.00,NULL,'2020-11-22',0,NULL,NULL,1),(60,'UQ-6398','2020-11-22','Jatender','uk',265.00,NULL,'2020-11-22',0,NULL,NULL,1),(61,'EG-0077','2020-11-22','Mirza','ao',480.00,NULL,'2020-11-22',0,NULL,NULL,1),(62,'SM-1822','2020-11-22','jaj','ama',50.00,NULL,'2020-11-22',0,NULL,NULL,1),(63,'IN-9460','2020-11-23','Naresh','uk',170.00,NULL,'2020-11-23',0,NULL,NULL,1),(64,'WN-3877','2020-11-23','Naresh2','m',240.00,NULL,'2020-11-23',0,NULL,NULL,1),(65,'BL-8717','2020-12-08','Turab','Islamkot, Tharparkar',480.00,NULL,'2020-12-08',0,NULL,NULL,1),(66,'ZB-2741','2020-12-09','turab','ikot, thar',465.00,NULL,'2020-12-09',0,NULL,NULL,1),(67,'DG-3387','2020-12-09','Turab','Hyderabad, Sindh',635.00,NULL,'2020-12-09',0,NULL,NULL,1),(68,'KQ-0115','2020-12-09','Turab','Hyderabad, Sindh',660.00,NULL,'2020-12-09',0,NULL,NULL,1),(69,'GU-8408','2020-12-09','Turab','Hyderabad, Sindh',610.00,NULL,'2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `order_details` */

DROP TABLE IF EXISTS `order_details`;

CREATE TABLE `order_details` (
  `order_details_id` int(11) NOT NULL auto_increment,
  `product_id` int(11) default NULL,
  `selling_price` double(11,2) default NULL,
  `quantity` int(11) default NULL,
  `total` double(11,2) default NULL,
  `order_code` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`order_details_id`),
  KEY `product_id` (`product_id`),
  KEY `order_id` (`order_code`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_details` */

LOCK TABLES `order_details` WRITE;

insert  into `order_details`(`order_details_id`,`product_id`,`selling_price`,`quantity`,`total`,`order_code`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (68,1,120.00,3,360.00,'AY-4376','2020-10-23',0,NULL,NULL,1),(69,2,25.00,2,50.00,'AY-4376','2020-10-23',0,NULL,NULL,1),(70,3,25.00,4,100.00,'AY-4376','2020-10-23',0,NULL,NULL,1),(71,4,50.00,1,50.00,'AY-4376','2020-10-23',0,NULL,NULL,1),(72,1,120.00,2,240.00,'RG-5449','2020-10-24',0,NULL,NULL,1),(73,2,25.00,2,50.00,'RG-5449','2020-10-24',0,NULL,NULL,1),(74,3,25.00,1,25.00,'DU-9748','2020-10-25',0,NULL,NULL,1),(75,1,120.00,3,360.00,'LV-3321','2020-10-25',0,NULL,NULL,1),(76,2,25.00,2,50.00,'LV-3321','2020-10-25',0,NULL,NULL,1),(77,3,25.00,1,25.00,'LV-3321','2020-10-25',0,NULL,NULL,1),(78,4,50.00,3,150.00,'LV-3321','2020-10-25',0,NULL,NULL,1),(79,1,120.00,2,240.00,'JN-9525','2020-10-25',0,NULL,NULL,1),(80,2,25.00,1,25.00,'JN-9525','2020-10-25',0,NULL,NULL,1),(81,3,25.00,1,25.00,'JN-9525','2020-10-25',0,NULL,NULL,1),(82,4,50.00,1,50.00,'JN-9525','2020-10-25',0,NULL,NULL,1),(83,1,120.00,5,600.00,'VZ-6104','2020-10-25',0,NULL,NULL,1),(84,2,25.00,1,25.00,'VZ-6104','2020-10-25',0,NULL,NULL,1),(85,3,25.00,1,25.00,'VZ-6104','2020-10-25',0,NULL,NULL,1),(86,4,50.00,1,50.00,'VZ-6104','2020-10-25',0,NULL,NULL,1),(87,1,120.00,2,240.00,'AS-2347','2020-11-22',0,NULL,NULL,1),(88,2,25.00,1,25.00,'AS-2347','2020-11-22',0,NULL,NULL,1),(89,1,120.00,2,240.00,'XM-3216','2020-11-22',0,NULL,NULL,1),(90,2,25.00,1,25.00,'XM-3216','2020-11-22',0,NULL,NULL,1),(91,2,25.00,1,25.00,'GG-3635','2020-11-22',0,NULL,NULL,1),(92,1,120.00,1,120.00,'QP-3078','2020-11-22',0,NULL,NULL,1),(93,1,120.00,1,120.00,'QF-6793','2020-11-22',0,NULL,NULL,1),(94,2,25.00,1,25.00,'DV-6552','2020-11-22',0,NULL,NULL,1),(95,1,120.00,2,240.00,'UQ-6398','2020-11-22',0,NULL,NULL,1),(96,2,25.00,1,25.00,'UQ-6398','2020-11-22',0,NULL,NULL,1),(97,1,120.00,4,480.00,'EG-0077','2020-11-22',0,NULL,NULL,1),(98,4,50.00,1,50.00,'SM-1822','2020-11-22',0,NULL,NULL,1),(99,1,120.00,1,120.00,'IN-9460','2020-11-23',0,NULL,NULL,1),(100,2,25.00,2,50.00,'IN-9460','2020-11-23',0,NULL,NULL,1),(101,1,120.00,2,240.00,'WN-3877','2020-11-23',0,NULL,NULL,1),(102,1,120.00,4,480.00,'BL-8717','2020-12-08',0,NULL,NULL,1),(103,1,120.00,2,240.00,'ZB-2741','2020-12-09',0,NULL,NULL,1),(104,2,25.00,3,75.00,'ZB-2741','2020-12-09',0,NULL,NULL,1),(105,3,25.00,2,50.00,'ZB-2741','2020-12-09',0,NULL,NULL,1),(106,4,50.00,2,100.00,'ZB-2741','2020-12-09',0,NULL,NULL,1),(107,1,120.00,3,360.00,'DG-3387','2020-12-09',0,NULL,NULL,1),(108,2,25.00,4,100.00,'DG-3387','2020-12-09',0,NULL,NULL,1),(109,3,25.00,1,25.00,'DG-3387','2020-12-09',0,NULL,NULL,1),(110,4,50.00,3,150.00,'DG-3387','2020-12-09',0,NULL,NULL,1),(111,1,120.00,3,360.00,'KQ-0115','2020-12-09',0,NULL,NULL,1),(112,2,25.00,2,50.00,'KQ-0115','2020-12-09',0,NULL,NULL,1),(113,3,25.00,4,100.00,'KQ-0115','2020-12-09',0,NULL,NULL,1),(114,4,50.00,3,150.00,'KQ-0115','2020-12-09',0,NULL,NULL,1),(115,1,120.00,3,360.00,'GU-8408','2020-12-09',0,NULL,NULL,1),(116,2,25.00,2,50.00,'GU-8408','2020-12-09',0,NULL,NULL,1),(117,3,25.00,2,50.00,'GU-8408','2020-12-09',0,NULL,NULL,1),(118,4,50.00,3,150.00,'GU-8408','2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL auto_increment,
  `permission` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `permission` */

LOCK TABLES `permission` WRITE;

insert  into `permission`(`permission_id`,`permission`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,'Add Product','2020-10-19',0,NULL,NULL,1),(2,'Update Product','2020-10-20',0,NULL,NULL,1),(3,'Delete Product','2020-10-13',0,NULL,NULL,1),(4,'Update Product','2020-12-08',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL auto_increment,
  `product_name` varchar(255) default NULL,
  `quantity` int(11) default NULL,
  `price` double(11,2) default NULL,
  `category_id` int(11) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`product_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product` */

LOCK TABLES `product` WRITE;

insert  into `product`(`product_id`,`product_name`,`quantity`,`price`,`category_id`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,'Shampoo',36,120.00,1,NULL,'2020-10-21',0,'2020-10-21',0,1),(2,'Soap',77,25.00,1,NULL,'2020-10-21',0,'2020-10-21',0,1),(3,'Surf',77,25.00,1,NULL,'2020-10-21',0,'2020-10-21',0,1),(4,'Cup',108,50.00,1,NULL,'2020-10-21',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL auto_increment,
  `vendor_id` int(11) default NULL,
  `purchase_code` varchar(255) NOT NULL,
  `purchase_date` date default NULL,
  `grand_total` double(11,2) default NULL,
  `discount` double(11,2) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`purchase_id`),
  KEY `vendor_id` (`vendor_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase` */

LOCK TABLES `purchase` WRITE;

insert  into `purchase`(`purchase_id`,`vendor_id`,`purchase_code`,`purchase_date`,`grand_total`,`discount`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,1,'JW-4412','2020-11-21',480.00,0.00,NULL,'2020-11-21',0,NULL,NULL,1),(2,1,'RO-4475','2020-11-21',125.00,0.00,NULL,'2020-11-21',0,NULL,NULL,1),(3,2,'PC-9639','2020-11-21',600.00,0.00,NULL,'2020-11-21',0,NULL,NULL,1),(4,2,'HR-9945','2020-11-21',600.00,0.00,NULL,'2020-11-21',0,NULL,NULL,1),(5,1,'MG-2577','2020-11-22',240.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(6,1,'EX-1798','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(7,1,'TR-0065','2020-11-22',240.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(8,2,'TF-0897','2020-11-22',480.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(9,1,'BQ-0474','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(10,1,'WO-9288','2020-11-22',480.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(11,2,'VG-8990','2020-11-22',500.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(12,2,'SN-1131','2020-11-22',25.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(13,2,'KR-4521','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(14,1,'ZX-7496','2020-11-22',120.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(15,2,'JA-8282','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(16,1,'NA-7485','2020-11-22',120.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(17,2,'GF-3938','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(18,1,'LH-6441','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(19,2,'RM-3974','2020-11-22',50.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(20,2,'PO-1815','2020-11-22',25.00,0.00,NULL,'2020-11-22',0,NULL,NULL,1),(21,1,'IJ-6488','2020-12-09',2400.00,0.00,NULL,'2020-12-09',0,NULL,NULL,1),(22,1,'KU-6117','2020-12-09',775.00,0.00,NULL,'2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `purchase_details` */

DROP TABLE IF EXISTS `purchase_details`;

CREATE TABLE `purchase_details` (
  `purchase_details_id` int(11) NOT NULL auto_increment,
  `purchase_code` varchar(255) default NULL,
  `total` double(11,2) default NULL,
  `product_id` int(11) default NULL,
  `quantity` int(11) default NULL,
  `purchase_price` double(11,2) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  `remarks` varchar(255) default NULL,
  PRIMARY KEY  (`purchase_details_id`),
  KEY `purchase_id` (`purchase_code`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `purchase_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_details` */

LOCK TABLES `purchase_details` WRITE;

insert  into `purchase_details`(`purchase_details_id`,`purchase_code`,`total`,`product_id`,`quantity`,`purchase_price`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`,`remarks`) values (17,'JA-8282',50.00,4,1,50.00,'2020-11-22',1,NULL,NULL,1,'2020-11-22'),(22,'PO-1815',25.00,3,1,25.00,'2020-11-22',0,NULL,NULL,1,NULL),(23,'IJ-6488',2400.00,1,20,120.00,'2020-12-09',0,NULL,NULL,1,NULL),(24,'KU-6117',600.00,1,5,120.00,'2020-12-09',0,NULL,NULL,1,NULL),(25,'KU-6117',75.00,2,3,25.00,'2020-12-09',0,NULL,NULL,1,NULL),(26,'KU-6117',50.00,3,2,25.00,'2020-12-09',0,NULL,NULL,1,NULL),(27,'KU-6117',50.00,4,1,50.00,'2020-12-09',0,NULL,NULL,1,NULL);

UNLOCK TABLES;

/*Table structure for table `purchase_transaction_history` */

DROP TABLE IF EXISTS `purchase_transaction_history`;

CREATE TABLE `purchase_transaction_history` (
  `purchase_transaction_history_id` int(11) NOT NULL auto_increment,
  `account_id` int(11) default NULL,
  `purchase_date` date default NULL,
  `purchase_id` int(11) default NULL,
  `transaction_ammount` double(11,2) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`purchase_transaction_history_id`),
  KEY `account_id` (`account_id`),
  KEY `purchase_id` (`purchase_id`),
  CONSTRAINT `purchase_transaction_history_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `purchase_transaction_history_ibfk_2` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchase_transaction_history` */

LOCK TABLES `purchase_transaction_history` WRITE;

insert  into `purchase_transaction_history`(`purchase_transaction_history_id`,`account_id`,`purchase_date`,`purchase_id`,`transaction_ammount`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,2,'2020-11-21',4,600.00,NULL,'2020-11-21',0,NULL,NULL,1),(2,1,'2020-11-22',7,240.00,NULL,'2020-11-22',0,NULL,NULL,1),(3,1,'2020-11-22',8,480.00,NULL,'2020-11-22',0,NULL,NULL,1),(4,1,'2020-11-22',9,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(5,2,'2020-11-22',10,480.00,NULL,'2020-11-22',0,NULL,NULL,1),(6,1,'2020-11-22',11,500.00,NULL,'2020-11-22',0,NULL,NULL,1),(7,1,'2020-11-22',12,25.00,NULL,'2020-11-22',0,NULL,NULL,1),(8,1,'2020-11-22',13,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(9,1,'2020-11-22',14,120.00,NULL,'2020-11-22',0,NULL,NULL,1),(10,1,'2020-11-22',15,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(11,2,'2020-11-22',16,120.00,NULL,'2020-11-22',0,NULL,NULL,1),(12,2,'2020-11-22',17,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(13,2,'2020-11-22',18,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(14,1,'2020-11-22',19,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(15,2,'2020-11-22',20,25.00,NULL,'2020-11-22',0,NULL,NULL,1),(16,1,'2020-12-09',21,2400.00,NULL,'2020-12-09',0,NULL,NULL,1),(17,1,'2020-12-09',22,775.00,NULL,'2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(4) default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

LOCK TABLES `role` WRITE;

insert  into `role`(`role_id`,`role_name`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,'admin','2020-10-19',NULL,NULL,NULL,1),(2,'Worker','2020-10-19',1,NULL,NULL,1),(3,'Owner','2020-10-19',1,NULL,NULL,1),(4,'Test','2020-10-19',1,'2020-10-19',0,1),(5,'Test1','2020-10-19',1,'2020-10-19',0,1),(6,'Test2','2020-10-19',1,NULL,NULL,1),(7,'Test3','2020-10-19',1,NULL,NULL,1),(8,'Test4','2020-10-19',1,NULL,NULL,1),(9,'Test5','2020-10-19',1,NULL,NULL,1),(10,'Test6','2020-10-19',1,NULL,NULL,1),(11,'Test7','2020-10-19',1,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_permission_id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `permission_id` int(11) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`role_permission_id`),
  KEY `role_id` (`role_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role_permission` */

LOCK TABLES `role_permission` WRITE;

insert  into `role_permission`(`role_permission_id`,`role_id`,`permission_id`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (1,2,1,'2020-10-24',0,NULL,NULL,1),(2,2,2,'2020-10-24',0,NULL,NULL,1),(3,2,3,'2020-10-24',0,NULL,NULL,1),(4,1,1,'2020-12-09',0,NULL,NULL,1),(5,1,4,'2020-12-09',0,NULL,NULL,1),(6,1,3,'2020-12-09',0,NULL,NULL,1),(7,1,4,'2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `sell_transaction_history` */

DROP TABLE IF EXISTS `sell_transaction_history`;

CREATE TABLE `sell_transaction_history` (
  `sell_transaction_history_id` int(11) NOT NULL auto_increment,
  `account_id` int(11) default NULL,
  `selling_date` date default NULL,
  `order_id` int(11) default NULL,
  `amount` double(11,2) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`sell_transaction_history_id`),
  KEY `account_id` (`account_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `sell_transaction_history_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `sell_transaction_history_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sell_transaction_history` */

LOCK TABLES `sell_transaction_history` WRITE;

insert  into `sell_transaction_history`(`sell_transaction_history_id`,`account_id`,`selling_date`,`order_id`,`amount`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`) values (6,2,'2020-10-23',47,560.00,NULL,'2020-10-23',0,NULL,NULL,1),(7,1,'2020-10-24',48,290.00,NULL,'2020-10-24',0,NULL,NULL,1),(8,1,'2020-10-25',49,585.00,NULL,'2020-10-25',0,NULL,NULL,1),(9,1,'2020-10-25',50,340.00,NULL,'2020-10-25',0,NULL,NULL,1),(10,1,'2020-10-25',51,220.00,NULL,'2020-10-25',0,NULL,NULL,1),(11,1,'2020-10-25',53,700.00,NULL,'2020-10-25',0,NULL,NULL,1),(12,1,'2020-11-22',59,25.00,NULL,'2020-11-22',0,NULL,NULL,1),(13,1,'2020-11-22',60,265.00,NULL,'2020-11-22',0,NULL,NULL,1),(14,1,'2020-11-22',61,480.00,NULL,'2020-11-22',0,NULL,NULL,1),(15,1,'2020-11-22',62,50.00,NULL,'2020-11-22',0,NULL,NULL,1),(16,1,'2020-11-23',63,170.00,NULL,'2020-11-23',0,NULL,NULL,1),(17,2,'2020-11-23',64,240.00,NULL,'2020-11-23',0,NULL,NULL,1),(18,1,'2020-12-08',65,480.00,NULL,'2020-12-08',0,NULL,NULL,1),(19,1,'2020-12-09',66,465.00,NULL,'2020-12-09',0,NULL,NULL,1),(20,1,'2020-12-09',69,610.00,NULL,'2020-12-09',0,NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(255) default NULL,
  `role_id` int(11) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `contact` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `created_date` date default NULL,
  `modified_by` int(11) default NULL,
  `modified_date` date default NULL,
  `status` tinyint(1) default NULL,
  PRIMARY KEY  (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`user_id`,`user_name`,`role_id`,`password`,`email`,`contact`,`address`,`created_by`,`created_date`,`modified_by`,`modified_date`,`status`) values (1,'Turab',1,'turab','turabbajeer202@gmail.com','+923360207419','Bajeer Mohalla Islamkot',0,'2020-10-19',NULL,NULL,1),(2,'Jatender',2,'jatender','jatender1234@gmail.com','03465751058','Khatri Mohalla, Umerkot',0,'2020-10-19',NULL,NULL,1);

UNLOCK TABLES;

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL auto_increment,
  `vendor_name` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `contact` varchar(255) default NULL,
  `remarks` varchar(255) default NULL,
  `created_date` date default NULL,
  `created_by` int(11) default NULL,
  `modified_date` date default NULL,
  `modified_by` int(11) default NULL,
  `status` tinyint(1) default NULL,
  `vendor_account` varchar(255) default NULL,
  `vendor_account_balance` double(11,2) default NULL,
  PRIMARY KEY  (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendor` */

LOCK TABLES `vendor` WRITE;

insert  into `vendor`(`vendor_id`,`vendor_name`,`email`,`address`,`contact`,`remarks`,`created_date`,`created_by`,`modified_date`,`modified_by`,`status`,`vendor_account`,`vendor_account_balance`) values (1,'Ayoob','ayoob.seller@gmail.com','Nawabshah','0348843923',NULL,NULL,NULL,NULL,NULL,1,'27890913456789',0.00),(2,'Jatender','j.seller@yahoo.com','Umerkot','03209878657',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
