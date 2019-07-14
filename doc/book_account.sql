/*
Navicat MySQL Data Transfer

Source Server         : tzl
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : minebook

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-14 22:27:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_account
-- ----------------------------
DROP TABLE IF EXISTS `book_account`;
CREATE TABLE `book_account` (
  `id` int(11) NOT NULL COMMENT '账户id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_account
-- ----------------------------
INSERT INTO `book_account` VALUES ('2', '现金账户');
INSERT INTO `book_account` VALUES ('3', '金融账户');
INSERT INTO `book_account` VALUES ('4', '虚拟账户');
INSERT INTO `book_account` VALUES ('5', '信用账户');
INSERT INTO `book_account` VALUES ('6', '负债账户');
INSERT INTO `book_account` VALUES ('7', '债权账户');
INSERT INTO `book_account` VALUES ('8', '投资账户');
INSERT INTO `book_account` VALUES ('9', '保险账户');

-- ----------------------------
-- Table structure for book_account_branch
-- ----------------------------
DROP TABLE IF EXISTS `book_account_branch`;
CREATE TABLE `book_account_branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户id',
  `account_balance` varchar(255) DEFAULT NULL COMMENT '账户余额',
  `account_inflows` varchar(255) DEFAULT NULL COMMENT '账户流入金额',
  `account_outflow` varchar(255) DEFAULT NULL COMMENT '账户流出',
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_account_branch
-- ----------------------------

-- ----------------------------
-- Table structure for book_keeping
-- ----------------------------
DROP TABLE IF EXISTS `book_keeping`;
CREATE TABLE `book_keeping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记账id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_keeping
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `password` varchar(32) NOT NULL COMMENT '用户匿名',
  `rid` int(11) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('24', 'tzl520', 'c41f269285544c422feb980dc12e5259', null, 'f6eee22ca33834b136a03b9a0ec1a2ff');
SET FOREIGN_KEY_CHECKS=1;
