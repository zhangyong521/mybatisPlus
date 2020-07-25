/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : mybatis_plus

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-25 13:05:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT '1' COMMENT '乐观锁字段',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Andy', '99', 'helen@qq.com', '2020-07-25 10:04:52', '2020-07-25 12:55:41', '2', '0');
INSERT INTO `user` VALUES ('2', 'Andy', '99', 'test2@baomidou.com', '2020-07-17 10:04:56', '2020-07-25 12:55:41', '1', '0');
INSERT INTO `user` VALUES ('3', 'Andy', '99', 'test3@baomidou.com', '2020-07-25 10:04:59', '2020-07-25 12:55:41', '1', '0');
INSERT INTO `user` VALUES ('4', 'Andy', '99', 'test4@baomidou.com', '2020-07-25 10:05:02', '2020-07-25 12:55:41', '1', '0');
INSERT INTO `user` VALUES ('5', 'Andy', '99', 'test5@baomidou.com', '2020-07-25 10:05:05', '2020-07-25 12:55:41', '1', '0');
