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

 Date: 21/02/2019 14:34:02
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
  PRIMARY KEY (`admin_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('abc1', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-21 11:54:53', 335, 1, 0, 0, 0, 'ceo');
INSERT INTO `admin_info` VALUES ('abc2', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-01-31 14:00:40', 23, 1, 0, 0, 0, 'ceo');
INSERT INTO `admin_info` VALUES ('abc3', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-11 15:25:57', 0, 0, 1, 1, 1, 'ceo');
INSERT INTO `admin_info` VALUES ('abc8', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-01-31 14:00:40', 23, 1, 0, 0, 0, 'ceo');
INSERT INTO `admin_info` VALUES ('abc9', '643', '202cb962ac59075b964b07152d234b70', 'manage', '2019-02-20 22:43:01', 0, 0, 0, 1, 0, 'ceo');

-- ----------------------------
-- Table structure for analyse_function
-- ----------------------------
DROP TABLE IF EXISTS `analyse_function`;
CREATE TABLE `analyse_function`  (
  `func_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能模块统计信息ID',
  `func_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '功能模块统计信息添加时间',
  `module_id` smallint(6) NOT NULL COMMENT '功能模块ID',
  `value` int(11) NOT NULL COMMENT '数值',
  PRIMARY KEY (`func_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of analyse_function
-- ----------------------------
INSERT INTO `analyse_function` VALUES (1, '2019-02-10 23:59:55', 1, 12);
INSERT INTO `analyse_function` VALUES (2, '2019-02-10 23:59:55', 2, 45);
INSERT INTO `analyse_function` VALUES (3, '2019-02-10 23:59:55', 3, 64);
INSERT INTO `analyse_function` VALUES (4, '2019-02-11 23:59:55', 1, 23);
INSERT INTO `analyse_function` VALUES (5, '2019-02-11 23:59:55', 2, 99);
INSERT INTO `analyse_function` VALUES (6, '2019-02-11 23:59:55', 3, 52);
INSERT INTO `analyse_function` VALUES (7, '2019-02-12 23:59:55', 1, 53);
INSERT INTO `analyse_function` VALUES (8, '2019-02-12 23:59:55', 2, 4);
INSERT INTO `analyse_function` VALUES (9, '2019-02-12 23:59:55', 3, 12);
INSERT INTO `analyse_function` VALUES (10, '2019-02-13 23:59:55', 1, 20);
INSERT INTO `analyse_function` VALUES (11, '2019-02-13 23:59:55', 2, 1);
INSERT INTO `analyse_function` VALUES (12, '2019-02-13 23:59:55', 3, 53);
INSERT INTO `analyse_function` VALUES (13, '2019-02-14 23:59:55', 1, 24);
INSERT INTO `analyse_function` VALUES (14, '2019-02-14 23:59:55', 2, 13);
INSERT INTO `analyse_function` VALUES (15, '2019-02-14 23:59:55', 3, 4);
INSERT INTO `analyse_function` VALUES (16, '2019-02-15 23:59:55', 1, 43);
INSERT INTO `analyse_function` VALUES (17, '2019-02-15 23:59:55', 2, 23);
INSERT INTO `analyse_function` VALUES (18, '2019-02-15 17:16:18', 3, 43);
INSERT INTO `analyse_function` VALUES (19, '2019-02-16 23:59:55', 1, 12);
INSERT INTO `analyse_function` VALUES (20, '2019-02-16 23:59:55', 2, 13);
INSERT INTO `analyse_function` VALUES (21, '2019-02-16 23:59:55', 3, 7);
INSERT INTO `analyse_function` VALUES (22, '2019-02-17 23:59:55', 1, 34);
INSERT INTO `analyse_function` VALUES (23, '2019-02-17 23:59:55', 2, 23);
INSERT INTO `analyse_function` VALUES (24, '2019-02-17 23:59:55', 3, 45);
INSERT INTO `analyse_function` VALUES (25, '2019-02-18 23:59:55', 1, 2);
INSERT INTO `analyse_function` VALUES (26, '2019-02-18 23:59:55', 2, 1);
INSERT INTO `analyse_function` VALUES (27, '2019-02-18 23:59:55', 3, 45);
INSERT INTO `analyse_function` VALUES (28, '2019-02-19 23:59:55', 1, 6);
INSERT INTO `analyse_function` VALUES (29, '2019-02-19 23:59:55', 2, 8);
INSERT INTO `analyse_function` VALUES (30, '2019-02-19 23:59:55', 3, 4);
INSERT INTO `analyse_function` VALUES (31, '2019-02-20 19:51:58', 1, 0);
INSERT INTO `analyse_function` VALUES (32, '2019-02-20 19:51:58', 2, 16);
INSERT INTO `analyse_function` VALUES (33, '2019-02-20 19:51:58', 3, 0);
INSERT INTO `analyse_function` VALUES (34, '2019-02-20 19:51:58', 7, 0);
INSERT INTO `analyse_function` VALUES (35, '2019-02-20 19:51:58', 8, 0);
INSERT INTO `analyse_function` VALUES (36, '2019-02-20 19:51:58', 11, 0);
INSERT INTO `analyse_function` VALUES (37, '2019-02-20 19:51:58', 13, 0);

-- ----------------------------
-- Table structure for analyse_performance
-- ----------------------------
DROP TABLE IF EXISTS `analyse_performance`;
CREATE TABLE `analyse_performance`  (
  `perf_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '性能统计信息ID',
  `perf_cpu` decimal(3, 1) NOT NULL COMMENT 'CPU性能数值',
  `perf_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '性能统计信息时间',
  `perf_gpu` decimal(3, 1) NOT NULL COMMENT 'GPU性能数值',
  `perf_mem` decimal(3, 1) NOT NULL COMMENT '内存性能数值',
  PRIMARY KEY (`perf_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of analyse_performance
-- ----------------------------
INSERT INTO `analyse_performance` VALUES (1, 1.0, '2019-01-27 11:19:00', 2.0, 3.0);
INSERT INTO `analyse_performance` VALUES (2, 2.0, '2019-01-27 11:19:05', 2.0, 1.0);
INSERT INTO `analyse_performance` VALUES (3, 0.0, '2019-01-27 11:19:10', 1.0, 4.0);
INSERT INTO `analyse_performance` VALUES (4, 3.0, '2019-01-27 11:19:15', 1.0, 5.0);
INSERT INTO `analyse_performance` VALUES (5, 1.0, '2019-01-27 11:19:20', 2.0, 6.0);
INSERT INTO `analyse_performance` VALUES (6, 3.0, '2019-01-27 11:20:25', 1.0, 5.0);
INSERT INTO `analyse_performance` VALUES (7, 5.0, '2019-01-27 11:20:30', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (8, 5.0, '2019-01-31 13:45:35', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (9, 5.0, '2019-01-31 13:45:40', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (10, 5.0, '2019-01-31 13:45:45', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (11, 5.0, '2019-01-31 13:45:50', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (12, 5.0, '2019-01-31 13:45:55', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (13, 5.0, '2019-01-31 13:46:00', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (14, 5.0, '2019-01-31 13:46:05', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (15, 5.0, '2019-01-31 13:46:10', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (16, 5.0, '2019-01-31 13:46:15', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (17, 5.0, '2019-01-31 13:46:20', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (18, 5.0, '2019-01-31 13:46:25', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (19, 5.0, '2019-01-31 13:46:30', 4.0, 5.0);
INSERT INTO `analyse_performance` VALUES (53, 11.0, '2019-02-20 21:22:51', 7.0, 12.0);
INSERT INTO `analyse_performance` VALUES (54, 53.0, '2019-02-20 21:22:57', 6.0, 13.0);
INSERT INTO `analyse_performance` VALUES (55, 5.0, '2019-02-20 21:23:06', 6.0, 9.0);
INSERT INTO `analyse_performance` VALUES (61, 5.0, '2019-02-20 21:38:33', 6.0, 9.0);
INSERT INTO `analyse_performance` VALUES (62, 11.0, '2019-02-20 21:38:37', 7.0, 12.0);
INSERT INTO `analyse_performance` VALUES (63, 53.0, '2019-02-20 21:38:39', 6.0, 13.0);
INSERT INTO `analyse_performance` VALUES (64, 32.0, '2019-02-20 21:38:42', 5.0, 12.0);
INSERT INTO `analyse_performance` VALUES (65, 12.0, '2019-02-20 21:38:44', 9.0, 14.0);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (9);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_info
-- ----------------------------
INSERT INTO `module_info` VALUES (1, '2019-02-20 19:27:24', '/home/pypro/theme.py', '主题提取模块，主要用于文本的主题提取', '主题提取');
INSERT INTO `module_info` VALUES (2, '2019-02-20 19:29:49', '/home/pypro/theme.py', '错别字校正模块，主要用于识别文本的错别字并输出纠正后的正确文本', '错别字校正');
INSERT INTO `module_info` VALUES (3, '2019-02-20 19:27:29', '/home/pypro/similarity.py', '文本相似度分析模块，主要用于分析两个文本的相似度', '相似度分析');
INSERT INTO `module_info` VALUES (15, '2019-02-21 11:55:00', '532432', '13251', '4342');

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings
-- ----------------------------
INSERT INTO `settings` VALUES (1, 'CPU使用率统计', 0);
INSERT INTO `settings` VALUES (2, 'GPU使用率统计', 0);
INSERT INTO `settings` VALUES (3, '内存使用率统计', 1);
INSERT INTO `settings` VALUES (5, '54300000000', 1);
INSERT INTO `settings` VALUES (6, '7354', 0);
INSERT INTO `settings` VALUES (7, '743', 1);
INSERT INTO `settings` VALUES (8, '54311', 0);
INSERT INTO `settings` VALUES (9, '12321', 1);
INSERT INTO `settings` VALUES (12, '6354354', 1);
INSERT INTO `settings` VALUES (15, 'MemUtilRecord', 0);
INSERT INTO `settings` VALUES (16, 't34rgb', 1);
INSERT INTO `settings` VALUES (17, '535344277', 0);
INSERT INTO `settings` VALUES (18, '4325324', 1);
INSERT INTO `settings` VALUES (19, '各个', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信OpenID',
  `last_use_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '用户上次登录时间',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('4534yrseg', '2019-02-10 22:11:50');
INSERT INTO `user_info` VALUES ('4tyystsdb34sy4', '2019-02-10 22:12:05');
INSERT INTO `user_info` VALUES ('54hnft5bv63s5c23bit7bvt34f5wc2s5vw35nm', '2019-01-26 10:33:56');
INSERT INTO `user_info` VALUES ('643srhs46s3rts', '2019-02-10 22:11:52');
INSERT INTO `user_info` VALUES ('645ht43h645te4b43g5644t45hby', '2019-01-26 10:31:10');
INSERT INTO `user_info` VALUES ('7546438767345uj56n56hbvy54nj3452523', '2019-01-26 10:38:18');
INSERT INTO `user_info` VALUES ('a23416oyl,ygjhnhgf', '2019-02-10 22:12:28');
INSERT INTO `user_info` VALUES ('b3y7sedjsretgrs', '2019-02-10 22:12:09');
INSERT INTO `user_info` VALUES ('bs46u4sgsdrfgrety', '2019-02-10 22:12:00');
INSERT INTO `user_info` VALUES ('bs4w654ikmngfvf', '2019-02-10 22:12:32');
INSERT INTO `user_info` VALUES ('bsewysehvbh', '2019-02-10 22:12:20');
INSERT INTO `user_info` VALUES ('bseysjrfgdfgx', '2019-02-10 22:12:23');
INSERT INTO `user_info` VALUES ('bseyuserfgber', '2019-02-10 22:12:17');
INSERT INTO `user_info` VALUES ('gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn', '2019-02-21 14:24:22');
INSERT INTO `user_info` VALUES ('gb3v456324ctvbv3643sc52wvby3r6be456cf35', '2019-01-26 10:33:51');
INSERT INTO `user_info` VALUES ('h34543g34th', '2019-02-10 22:11:48');
INSERT INTO `user_info` VALUES ('h346sjdse643', '2019-02-10 22:11:57');
INSERT INTO `user_info` VALUES ('h347ysrdg34b', '2019-02-10 22:12:03');
INSERT INTO `user_info` VALUES ('h54g347h34543g34', '2019-02-10 22:11:43');
INSERT INTO `user_info` VALUES ('h54tyersh3gsb', '2019-02-10 22:11:46');
INSERT INTO `user_info` VALUES ('hs34843w5b ydrbsd4', '2019-02-10 22:11:54');
INSERT INTO `user_info` VALUES ('hswe6tsehserthsd', '2019-02-10 22:12:14');
INSERT INTO `user_info` VALUES ('j4r5n3m487hby4vedc32asrtcdeftny5634', '2019-01-26 10:32:56');
INSERT INTO `user_info` VALUES ('nbsdy4srjsret43', '2019-02-10 22:12:11');
INSERT INTO `user_info` VALUES ('ne6t1345wbvy54nj2k4o7bg5eo7v4s34', '2019-01-26 10:31:27');
INSERT INTO `user_info` VALUES ('nseruij4976oltgbhx', '2019-02-10 22:12:26');
INSERT INTO `user_info` VALUES ('sgvsre53bwv5c1vctwe57bu4h5f34swt4s5ca34fc2a', '2019-01-26 10:50:06');
INSERT INTO `user_info` VALUES ('vret43yhnj34t5g23hnj5464h464rv', '2019-01-26 10:31:00');
INSERT INTO `user_info` VALUES ('yt3uh34tgrdg4e534', '2019-02-21 11:59:41');

-- ----------------------------
-- Table structure for user_input
-- ----------------------------
DROP TABLE IF EXISTS `user_input`;
CREATE TABLE `user_input`  (
  `in_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '输入数据ID',
  `date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '数据插入时间',
  `in_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输入数据',
  `log_id` int(11) NOT NULL COMMENT '日志ID',
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_input
-- ----------------------------
INSERT INTO `user_input` VALUES (6, '2019-01-26 11:47:10', '访问和hi和偶家围殴节日哦恩波呢 房客网开哦啊你哦 就胸径哦往年何炅 就 看就看计划已UI我比较二倍角be王俊凯 我还看见发你问喝酒还不忘接口be尽快吧教科文编辑框北位家口编辑框北位家口编辑框教科文UR和无和人去和从湖北琴女打开吧   就开放百旺金赋金额 标记物刚回归', 6);
INSERT INTO `user_input` VALUES (7, '2019-01-26 11:54:55', '访问和hi和偶家围殴节日哦恩波呢 房客网开哦啊你哦 就胸径哦往年何炅 就 看就看计划已UI我比较二倍角be王俊凯 我还看见发你问喝酒还不忘接口be尽快吧教科文编辑框北位家口编辑框北位家口编辑框教科文UR和无和人去和从湖北琴女打开吧   就开放百旺金赋金额 标记物刚回归', 9);
INSERT INTO `user_input` VALUES (8, '2019-01-26 11:54:57', '规划i就UI户u通用他与㝉修改酒鬼GiuliaGiulia芋头预告就 富富余防御防御愚夫愚妇鱼非鱼发育发育邞鱼非鱼䦽发育发育㝟biubiu好几款了noiseJoiUI个与任天野他羽绒服预告玉玉防御t76体育个迒他广东体育飞飞改好价格hiGV有UH从hi感觉hi背景hi壁挂红怪与干预改变湖公园体育飞飞鱼鼓魂高级鬼脚后跟预告预与v', 9);
INSERT INTO `user_input` VALUES (9, '2019-01-26 11:55:21', '6uiy 98gui gh9 y9G9ub jih 98b  UI哦黑背景开吧UI个UI不编辑框吧', 7);
INSERT INTO `user_input` VALUES (10, '2019-01-26 11:55:50', '6uiy 98gui gh9 y9G9ub jih 98b  UI哦黑背景开吧UI个UI不编辑框吧,图4欧锦3尽快吧UI哦和8你何必呢', 10);
INSERT INTO `user_input` VALUES (11, '2019-01-26 11:56:01', '图4欧锦3尽快吧UI哦和8你何必呢', 11);
INSERT INTO `user_input` VALUES (12, '2019-01-26 11:56:44', '供热个人GRE个', 15);
INSERT INTO `user_input` VALUES (13, '2019-01-31 13:47:51', '供热个人GRE个', 15);
INSERT INTO `user_input` VALUES (14, '2019-02-19 14:18:46', '发热和糖尿病味儿VR额人体 二姑娘v而儿童二黑色该如何的', 1);
INSERT INTO `user_input` VALUES (15, '2019-02-20 08:57:46', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 2);
INSERT INTO `user_input` VALUES (16, '2019-02-20 08:58:39', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 3);
INSERT INTO `user_input` VALUES (17, '2019-02-20 08:58:41', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 4);
INSERT INTO `user_input` VALUES (18, '2019-02-20 08:58:42', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 5);
INSERT INTO `user_input` VALUES (19, '2019-02-20 08:58:43', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 6);
INSERT INTO `user_input` VALUES (20, '2019-02-20 08:58:44', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 7);
INSERT INTO `user_input` VALUES (21, '2019-02-20 08:58:45', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 8);
INSERT INTO `user_input` VALUES (22, '2019-02-20 08:58:45', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 9);
INSERT INTO `user_input` VALUES (23, '2019-02-20 08:58:46', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 10);
INSERT INTO `user_input` VALUES (24, '2019-02-20 08:58:47', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 11);
INSERT INTO `user_input` VALUES (25, '2019-02-20 08:58:47', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 12);
INSERT INTO `user_input` VALUES (26, '2019-02-20 08:58:48', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 13);
INSERT INTO `user_input` VALUES (27, '2019-02-20 08:58:48', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 14);
INSERT INTO `user_input` VALUES (28, '2019-02-20 08:58:48', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 15);
INSERT INTO `user_input` VALUES (29, '2019-02-20 08:58:49', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 16);
INSERT INTO `user_input` VALUES (30, '2019-02-20 09:28:48', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 17);
INSERT INTO `user_input` VALUES (31, '2019-02-20 22:58:31', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 18);
INSERT INTO `user_input` VALUES (32, '2019-02-20 22:59:55', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 19);
INSERT INTO `user_input` VALUES (33, '2019-02-20 23:00:43', '发热和糖尿并味儿VR额人体 二姑娘v而儿童二黑色该如何的', 20);
INSERT INTO `user_input` VALUES (34, '2019-02-21 10:59:54', '习近平总书记在会见探月工程嫦娥四号任务参研参试人员时说了这些“金句”', 21);
INSERT INTO `user_input` VALUES (35, '2019-02-21 10:59:54', '希望双方媒体做友好交往的传播者、务实合作的推动者、和谐共处的守望者', 21);
INSERT INTO `user_input` VALUES (36, '2019-02-21 11:00:18', '习近平总书记在会见探月工程嫦娥四号任务参研参试人员时说了这些“金句”', 22);
INSERT INTO `user_input` VALUES (37, '2019-02-21 11:00:18', '希望双方媒体做友好交往的传播者、务实合作的推动者、和谐共处的守望者', 22);
INSERT INTO `user_input` VALUES (38, '2019-02-21 11:01:36', '习近平总书记在会见探月工程嫦娥四号任务参研参试人员时说了这些“金句”', 23);
INSERT INTO `user_input` VALUES (39, '2019-02-21 11:01:36', '希望双方媒体做友好交往的传播者、务实合作的推动者、和谐共处的守望者', 23);
INSERT INTO `user_input` VALUES (40, '2019-02-21 14:24:22', '当前PPI的阶段性下行，并非是由于货币供应或流动性不足带来的，而是上游生产资料价格波动引发的，其带来的影响总体上集中在工业领域。PPI虽然能够反映市场供求关系，但现阶段其变化的背后很大程度上缘自政策性因素，并不能作为判断工业运行状况的唯一依据\n近日，国家统计局公布了全国工业生产者出厂价格指数（PPI）。数据显示，今年1月份，PPI同比上涨0.1%，涨幅比上个月收窄0.8个百分点；环比下降0.6%，跌幅较上月略收窄0.4个百分点。\n自2018年7月份以来，PPI同比涨幅呈现逐月收窄下行态势。今年1月份0.1%的同比涨幅创下了近29个月以来的新低，比2018年全年同比涨幅收窄3.4个百分点，比2017年全年涨幅收窄6.2个百分点。\n总的来看，受上游大宗生产资料价格持续下跌等因素影响，PPI处于下行通道。自去年下半年以来，由于内外需求走弱压力较大，流通领域中主要生产资料价格出现了不同程度的回落，工业生产出现明显放缓。今年1月份，生产资料价格同比下跌0.1%，近29个月以来首次跌到负值。未来几个月，受工业需求减弱、生产放缓等因素持续影响，PPI同比由涨转跌并在一段时间内处于下跌状态，是大概率事件。\n从理论上说，PPI同比跌入负值，意味着工业企业的盈利空间受价格回落因素影响而收窄，部分企业甚至可能出现亏损。这将影响企业生产的积极性，也容易给经济的平稳运行带来不利影响。不过，结合当前我国经济运行的实际情况，不必对PPI同比跌入负值过于焦虑。\n一方面，当前PPI的阶段性变化，并非由于货币供应或流动性不足所致，而是上游生产资料价格波动引发的，这种阶段性变化带来的影响总体上集中在工业领域。从近几年情况看，即便经历了连续50多个月的PPI同比负增长，中国经济平稳运行、稳中向好的态势从未改变。\n另一方面，PPI虽然能够反映市场供求关系，但现阶段其变化的背后很大程度上受到政策性因素影响，并不能作为判断工业运行状况的唯一依据。2016年9月份以来，PPI同比实现由负转正，背后既有国际大宗商品价格波动的输入性因素，也有国内市场需求变化的影响，但更重要的是由于持续推进供给侧结构性改革，特别是围绕“三去一降一补”，不断优化供给结构和供给质量，这在客观上推动了上游原材料行业被动去库存、去产能，也带动了原材料价格回升，进而带动了PPI同比上涨。\n值得注意的是，在供给侧结构性改革持续推进的情况下，上游原材料价格不涨反跌，导致PPI同比由涨转跌，这可能会使部分上游行业企业的盈利压力加大，甚至会动摇个别企业对持续推进供给侧结构性改革的信心。\n不过，任何事物都具有两面性。最近一两年，PPI同比虽然持续上涨，但工业企业的利润主要集中在产品价格持续上涨的上游行业。中下游行业承受了原材料价格上涨的压力，但产品出厂价格并未明显“水涨船高”，企业盈利状况也没有得到有效改善。如今，随着上游生产资料价格的回落，将会缓解中下游行业企业的成本压力，有利于改善其盈利状况，进而激发企业生产的积极性。\n当然，也应该看到，PPI同比负增长的持续时间如果过长，可能会影响市场预期和信心。特别是在中国经济运行稳中有变、变中有忧的背景下，如果物价问题应对不好，可能会带来就业问题，并引发其他风险隐患。因此，当前仍应按照中央经济工作会议部署，统筹推进稳增长、促改革、调结构、惠民生、防风险工作，做好稳就业、稳金融、稳外贸、稳外资、稳投资、稳预期工作，强化逆周期调节，改善供求关系，保持经济运行在合理区间。', 24);

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `is_finished` tinyint(1) NULL DEFAULT NULL COMMENT '是否处理完成',
  `log_date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '日志插入时间',
  `module_id` smallint(6) NOT NULL COMMENT '模块ID',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户OpenID',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES (1, 1, '2019-02-19 17:05:35', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (2, 1, '2019-02-20 08:57:52', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (3, 1, '2019-02-20 08:58:41', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (4, 1, '2019-02-20 08:58:42', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (5, 1, '2019-02-20 08:58:43', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (6, 1, '2019-02-20 08:58:44', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (7, 1, '2019-02-20 08:58:45', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (8, 1, '2019-02-20 08:58:46', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (9, 1, '2019-02-20 08:58:48', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (10, 1, '2019-02-20 08:58:49', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (11, 1, '2019-02-20 08:58:50', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (12, 1, '2019-02-20 08:58:51', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (13, 1, '2019-02-20 08:58:52', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (14, 1, '2019-02-20 08:58:53', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (15, 1, '2019-02-20 08:58:54', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (16, 1, '2019-02-20 08:58:55', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (17, 1, '2019-02-20 09:28:49', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (18, 1, '2019-02-20 22:58:34', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (19, 1, '2019-02-20 22:59:56', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (20, 1, '2019-02-20 23:00:44', 2, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (21, 1, '2019-02-21 11:00:00', 3, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (22, 1, '2019-02-21 11:52:56', 3, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (23, 1, '2019-02-21 11:52:57', 3, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');
INSERT INTO `user_log` VALUES (24, 1, '2019-02-21 14:24:25', 1, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn');

-- ----------------------------
-- Table structure for user_output
-- ----------------------------
DROP TABLE IF EXISTS `user_output`;
CREATE TABLE `user_output`  (
  `out_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '输出数据ID',
  `date` timestamp(0) NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '数据插入时间',
  `log_id` int(11) NOT NULL COMMENT '日志ID',
  `out_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '输出数据',
  PRIMARY KEY (`out_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_output
-- ----------------------------
INSERT INTO `user_output` VALUES (4, '2019-01-26 11:58:14', 11, '规划i就UI户u通用他与㝉修改酒鬼GiuliaGiulia芋头预告就 富富余防御防御愚夫愚妇鱼非鱼发育发育邞鱼非鱼䦽发育发育㝟biubiu好几款了noiseJoiUI个与任天野他羽绒服预告玉玉防御t76体育个迒他广东体育飞飞改好价格hiGV有UH从hi感觉hi背景hi壁挂红怪与干预改变湖公园体育飞飞鱼鼓魂高级鬼脚后跟预告预与v');
INSERT INTO `user_output` VALUES (5, '2019-01-26 11:58:18', 10, '6uiy 98gui gh9 y9G9ub jih 98b  UI哦黑背景开吧UI个UI不编辑框吧');
INSERT INTO `user_output` VALUES (6, '2019-01-26 11:58:22', 9, '规划i就UI户u通用他与㝉修改酒鬼GiuliaGiulia芋头预告就 富富余防御防御愚夫愚妇鱼非鱼发育发育邞鱼非鱼䦽发育发育㝟biubiu好几款了noiseJoiUI个与任天野他羽绒服预告玉玉防御t76体育个迒他广东体育飞飞改好价格hiGV有UH从hi感觉hi背景hi壁挂红怪与干预改变湖公园体育飞飞鱼鼓魂高级鬼脚后跟预告预与v');
INSERT INTO `user_output` VALUES (7, '2019-01-26 11:58:25', 8, '好UI和尽可能字个字或');
INSERT INTO `user_output` VALUES (8, '2019-01-26 11:58:29', 7, '图4欧锦3尽快吧UI哦和8你何必呢');
INSERT INTO `user_output` VALUES (9, '2019-01-26 11:58:32', 6, '供热个人GRE个');
INSERT INTO `user_output` VALUES (10, '2019-01-31 13:47:58', 11, '好UI和尽可能字个字或');
INSERT INTO `user_output` VALUES (11, '2019-02-19 17:05:35', 1, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (12, '2019-02-20 08:57:52', 2, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (13, '2019-02-20 08:58:41', 3, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (14, '2019-02-20 08:58:42', 4, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (15, '2019-02-20 08:58:43', 5, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (16, '2019-02-20 08:58:45', 7, 'Typo Analyse: received:6uiy 98gui gh9 y9G9ub jih 98b  UIŶ�ڱ�������UI��UI���༭���result:\n');
INSERT INTO `user_output` VALUES (17, '2019-02-20 08:58:46', 8, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (18, '2019-02-20 08:58:49', 10, 'Typo Analyse: received:6uiy 98gui gh9 y9G9ub jih 98b  UIŶ�ڱ�������UI��UI���༭���,ͼ4ŷ��3�����UIŶ��8��α���result:\n');
INSERT INTO `user_output` VALUES (19, '2019-02-20 08:58:50', 11, 'Typo Analyse: received:ͼ4ŷ��3�����UIŶ��8��α���result:\n');
INSERT INTO `user_output` VALUES (20, '2019-02-20 08:58:51', 12, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (21, '2019-02-20 08:58:52', 13, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (22, '2019-02-20 08:58:53', 14, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (23, '2019-02-20 08:58:54', 15, 'Typo Analyse: received:���ȸ���GRE��result:\n');
INSERT INTO `user_output` VALUES (24, '2019-02-20 08:58:55', 16, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (25, '2019-02-20 09:28:49', 17, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (26, '2019-02-20 22:58:34', 18, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (27, '2019-02-20 22:59:56', 19, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (28, '2019-02-20 23:00:44', 20, 'Typo Analyse: received:���Ⱥ�����ζ��VR������ ������v����ͯ����ɫ����ε�result:\n');
INSERT INTO `user_output` VALUES (29, '2019-02-21 11:00:00', 21, 'Similarity Analyse: received:ϰ��ƽ������ڻ��̽�¹����϶��ĺ�������в�����Աʱ˵����Щ����䡱--ϣ��˫��ý�����Ѻý����Ĵ����ߡ���ʵ�������ƶ��ߡ���г������������\n');
INSERT INTO `user_output` VALUES (30, '2019-02-21 11:52:56', 22, 'Similarity Analyse: received:ϰ��ƽ������ڻ��̽�¹����϶��ĺ�������в�����Աʱ˵����Щ����䡱--ϣ��˫��ý�����Ѻý����Ĵ����ߡ���ʵ�������ƶ��ߡ���г������������\n');
INSERT INTO `user_output` VALUES (31, '2019-02-21 11:52:57', 23, 'Similarity Analyse: received:ϰ��ƽ������ڻ��̽�¹����϶��ĺ�������в�����Աʱ˵����Щ����䡱--ϣ��˫��ý�����Ѻý����Ĵ����ߡ���ʵ�������ƶ��ߡ���г������������\n');

SET FOREIGN_KEY_CHECKS = 1;
