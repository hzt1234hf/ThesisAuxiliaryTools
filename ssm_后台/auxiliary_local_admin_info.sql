/*
 Navicat MariaDB Data Transfer

 Source Server         : Localhost_root
 Source Server Type    : MariaDB
 Source Server Version : 100311
 Source Host           : localhost:3306
 Source Schema         : auxiliary

 Target Server Type    : MariaDB
 Target Server Version : 100311
 File Encoding         : 65001

 Date: 23/02/2019 17:29:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `admin_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `admin_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `admin_password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `department` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员所属部门',
  `last_login_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_count` int(11) NOT NULL COMMENT '登录次数',
  `permis_analyse` tinyint(1) NOT NULL COMMENT '统计分析查看权限',
  `permis_content` tinyint(1) NOT NULL COMMENT '内容修改查看权限',
  `permis_setting` tinyint(1) NOT NULL COMMENT '设置查看修改权限',
  `permis_user` tinyint(1) NOT NULL COMMENT '用户设置修改权限',
  `position` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员职位',
  `super_admin` tinyint(1) NULL DEFAULT NULL COMMENT '设置超级管理员',
  PRIMARY KEY (`admin_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('abc1', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-23 17:18:16', 398, 1, 1, 1, 1, 'ceo', 1);
INSERT INTO `admin_info` VALUES ('abc2', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-23 17:14:03', 26, 1, 0, 1, 0, 'ceo', 0);
INSERT INTO `admin_info` VALUES ('abc3', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-23 17:14:02', 0, 0, 1, 0, 1, 'ceo', 0);
INSERT INTO `admin_info` VALUES ('abc8', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-23 17:13:51', 23, 1, 0, 1, 0, 'ceo', 0);
INSERT INTO `admin_info` VALUES ('abc9', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-23 17:13:51', 0, 0, 1, 0, 1, 'ceo', 0);

SET FOREIGN_KEY_CHECKS = 1;
