/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : java_estore

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-04-07 16:26:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biz_order`
-- ----------------------------
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE `biz_order` (
  `id` varchar(255) NOT NULL,
  `money` double DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `payState` int(11) DEFAULT NULL,
  `orderTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `biz_order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usr_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_order
-- ----------------------------
