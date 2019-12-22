/*
 Navicat MariaDB Data Transfer

 Source Server         : localhost_root
 Source Server Type    : MariaDB
 Source Server Version : 100406
 Source Host           : localhost:3306
 Source Schema         : auxiliary

 Target Server Type    : MariaDB
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 21/12/2019 21:54:37
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
  `department` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员所属部门',
  `last_login_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_count` int(32) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `permis_analyse` tinyint(1) NOT NULL DEFAULT 0 COMMENT '统计分析查看权限',
  `permis_content` tinyint(1) NOT NULL DEFAULT 0 COMMENT '内容修改查看权限',
  `permis_setting` tinyint(1) NOT NULL DEFAULT 0 COMMENT '设置查看修改权限',
  `permis_user` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户设置修改权限',
  `position` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员职位',
  `super_admin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '设置超级管理员',
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

-- ----------------------------
-- Table structure for analyse_function
-- ----------------------------
DROP TABLE IF EXISTS `analyse_function`;
CREATE TABLE `analyse_function`  (
  `func_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '功能模块统计信息ID',
  `func_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '功能模块统计信息添加时间',
  `module_id` smallint(6) NOT NULL COMMENT '功能模块ID',
  `value` int(32) NOT NULL COMMENT '数值',
  PRIMARY KEY (`func_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for analyse_performance
-- ----------------------------
DROP TABLE IF EXISTS `analyse_performance`;
CREATE TABLE `analyse_performance`  (
  `perf_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '性能统计信息ID',
  `perf_cpu` int(8) NOT NULL COMMENT 'CPU性能数值',
  `perf_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '性能统计信息时间',
  `perf_gpu` int(8) NOT NULL COMMENT 'GPU性能数值',
  `perf_mem` int(8) NOT NULL COMMENT '内存性能数值',
  PRIMARY KEY (`perf_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for analyse_user
-- ----------------------------
DROP TABLE IF EXISTS `analyse_user`;
CREATE TABLE `analyse_user`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户分析统计信息ID',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信OpenID',
  `user_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '功能模块统计信息添加时间',
  `value` int(32) NOT NULL DEFAULT 0 COMMENT '数值',
  `wx_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'null' COMMENT '用户微信账号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for module_info
-- ----------------------------
DROP TABLE IF EXISTS `module_info`;
CREATE TABLE `module_info`  (
  `module_id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT '模块模块ID',
  `add_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '功能模块添加时间 ',
  `execution_path` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能模块路径 ',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能模块介绍 ',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能模块名称 ',
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `notice_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '通知信息ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知信息内容 ',
  `is_scrolling` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是滚动显示内容',
  `notice_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '通知信息添加时间',
  `title` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知信息标题 ',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for settings
-- ----------------------------
DROP TABLE IF EXISTS `settings`;
CREATE TABLE `settings`  (
  `set_id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT '设置信息ID',
  `set_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设置名称',
  `set_value` tinyint(1) NOT NULL COMMENT '设置值',
  PRIMARY KEY (`set_id`) USING BTREE,
  UNIQUE INDEX `UK_7o96qjcv2cjlbxoqlyb3vhvhb`(`set_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings
-- ----------------------------
INSERT INTO `settings` VALUES (1, 'CPU使用率统计', 1);
INSERT INTO `settings` VALUES (2, 'GPU使用率统计', 1);
INSERT INTO `settings` VALUES (3, '内存使用率统计', 1);

-- ----------------------------
-- Table structure for user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback`  (
  `feedback_info_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '反馈信息ID',
  `contact` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式 ',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈内容 ',
  `date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '用户上次登录时间',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户OpenID',
  PRIMARY KEY (`feedback_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信OpenID',
  `last_use_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '用户上次登录时间',
  `wx_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户微信账号',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_input
-- ----------------------------
DROP TABLE IF EXISTS `user_input`;
CREATE TABLE `user_input`  (
  `in_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '输入数据ID',
  `date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '数据插入时间',
  `in_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输入数据',
  `log_id` int(32) NOT NULL COMMENT '日志ID',
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log`  (
  `log_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `is_finished` tinyint(1) NULL DEFAULT NULL COMMENT '是否处理完成',
  `log_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '日志插入时间',
  `module_id` smallint(6) NOT NULL COMMENT '模块ID',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户OpenID',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_output
-- ----------------------------
DROP TABLE IF EXISTS `user_output`;
CREATE TABLE `user_output`  (
  `out_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '输出数据ID',
  `date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '数据插入时间',
  `log_id` int(32) NOT NULL COMMENT '日志ID',
  `out_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输出数据',
  PRIMARY KEY (`out_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
