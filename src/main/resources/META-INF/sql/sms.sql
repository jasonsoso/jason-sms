/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.19 : Database - sms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `sms`;

/*Table structure for table `security_authority` */

DROP TABLE IF EXISTS `security_authority`;

CREATE TABLE `security_authority` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permission` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `permission` (`permission`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `security_authority` */

insert  into `security_authority`(`id`,`name`,`permission`) values (15,'修改密码','admin:changepwd');

/*Table structure for table `security_role` */

DROP TABLE IF EXISTS `security_role`;

CREATE TABLE `security_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `show_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `security_role` */

insert  into `security_role`(`id`,`name`,`show_name`) values (1,'admin','超级管理员'),(2,'test','测试人员');

/*Table structure for table `security_role_authority` */

DROP TABLE IF EXISTS `security_role_authority`;

CREATE TABLE `security_role_authority` (
  `role_id` bigint(32) NOT NULL,
  `authority_id` bigint(32) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `authority_id` (`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `security_role_authority` */

insert  into `security_role_authority`(`role_id`,`authority_id`) values (1,15);

/*Table structure for table `security_user` */

DROP TABLE IF EXISTS `security_user`;

CREATE TABLE `security_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `role_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `account_non_locked` (`account_non_locked`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `security_user` */

insert  into `security_user`(`id`,`username`,`password`,`account_non_locked`,`role_id`) values (1,'admin','f6fdffe48c908deb0f4c3bd36c032e72','',1),(2,'test','05a671c66aefea124cc08b76ea6d30bb','',2);

/*Table structure for table `security_user_runas` */

DROP TABLE IF EXISTS `security_user_runas`;

CREATE TABLE `security_user_runas` (
  `from_user_id` bigint(20) NOT NULL DEFAULT '0',
  `to_user_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`from_user_id`,`to_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_user_runas` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
