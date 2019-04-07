/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : java_estore

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-04-08 02:29:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `usr_user`
-- ----------------------------
DROP TABLE IF EXISTS `usr_user`;
CREATE TABLE `usr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `state` int(11) NOT NULL,
  `activationCode` varchar(255) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_user
-- ----------------------------
INSERT INTO `usr_user` VALUES ('1', 'tom', 'e10adc3949ba59abbe56e057f20f883e', 'tom', 'tom@163.com', 'user', '0', '1b5ebea0-5f1b-4fd4-84c6-cbd7fa503bbd', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('2', 'July', 'e10adc3949ba59abbe56e057f20f883e', 'July', 'July@163.com', 'user', '0', '2ae76c17-af4b-43a0-b203-fff188e60ddc', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('3', '李易峰', 'e10adc3949ba59abbe56e057f20f883e', '峰', 'lyf@qq.com', 'user', '0', 'fa37a797-4022-4030-b58a-a7c22ce20c15', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('10', '马国旺', 'e10adc3949ba59abbe56e057f20f883e', 'mgw', 'mgw@qq.com', 'user', '0', 'c0b13ab7-22a8-4818-ba36-2c810730d015', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('12', '刘盼盼', 'e10adc3949ba59abbe56e057f20f883e', 'lpp', 'lpp@qq.com', 'user', '0', '8cfad5eb-2668-4006-8965-d05513e36c56', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('14', '刘旺旺', 'e10adc3949ba59abbe56e057f20f883e', 'lww', '2710480141@qq.com', 'user', '1', 'f2fae9be-906a-4529-ac4e-8d3a085f9d98', '2019-04-08 01:55:03');
INSERT INTO `usr_user` VALUES ('15', '刘宝', 'e10adc3949ba59abbe56e057f20f883e', 'lb', 'lb@qq.com', 'user', '1', '0aa64362-3045-4b96-abbe-95309222f931', '2019-04-08 02:02:48');
INSERT INTO `usr_user` VALUES ('16', 'liubao', 'e10adc3949ba59abbe56e057f20f883e', 'lb', 'paulluis_dev@qq.com', 'user', '0', 'd041ff26-6fe3-4f8a-800c-d550c863e746', '2019-04-08 02:00:48');
INSERT INTO `usr_user` VALUES ('18', 'paulluis', 'e10adc3949ba59abbe56e057f20f883e', 'pl', 'paulluis.dev@gmail.com', 'user', '0', '1bc6921d-3690-4271-bf2a-6b5cf4e508a2', '2019-04-08 02:21:37');
