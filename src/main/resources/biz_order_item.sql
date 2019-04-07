/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : java_estore

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-04-07 16:26:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biz_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `biz_order_item`;
CREATE TABLE `biz_order_item` (
  `orderId` varchar(255) NOT NULL DEFAULT '',
  `productId` varchar(255) NOT NULL DEFAULT '',
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`,`productId`),
  KEY `productId` (`productId`),
  CONSTRAINT `biz_order_item_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `biz_product` (`id`),
  CONSTRAINT `biz_order_item_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `biz_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_order_item
-- ----------------------------
