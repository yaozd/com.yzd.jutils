/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.30-CANAL
Source Server Version : 50638
Source Host           : 192.168.3.30:3306
Source Database       : bigdata

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-03-26 14:35:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `p_Id` bigint(20) DEFAULT NULL,
  `chineseName` varchar(100) DEFAULT NULL,
  `nameSex` varchar(100) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `road` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
