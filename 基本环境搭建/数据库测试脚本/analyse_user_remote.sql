/*
 Navicat MariaDB Data Transfer

 Source Server         : 118.25.22.27_prouse_TX
 Source Server Type    : MariaDB
 Source Server Version : 50560
 Source Host           : 118.25.22.27:3306
 Source Schema         : auxiliary

 Target Server Type    : MariaDB
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 26/02/2019 14:22:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for analyse_user
-- ----------------------------
DROP TABLE IF EXISTS `analyse_user`;
CREATE TABLE `analyse_user`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户分析统计信息ID',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信OpenID',
  `user_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '功能模块统计信息添加时间',
  `value` int(32) NOT NULL DEFAULT 0 COMMENT '数值',
  `wx_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'null' COMMENT '用户微信账号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of analyse_user
-- ----------------------------
INSERT INTO `analyse_user` VALUES (135, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn', '2019-02-19 11:14:09', 1, NULL);
INSERT INTO `analyse_user` VALUES (136, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-20 11:14:09', 251, '111');
INSERT INTO `analyse_user` VALUES (137, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn', '2019-02-20 11:14:09', 19, NULL);
INSERT INTO `analyse_user` VALUES (138, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-21 11:14:09', 34, '111');
INSERT INTO `analyse_user` VALUES (139, 'gb3h4nrdbesgf3rt45hjtymdnrg34ty5hj6ytn', '2019-02-21 11:14:09', 18, NULL);
INSERT INTO `analyse_user` VALUES (140, 'undefined', '2019-02-21 11:14:09', 4, NULL);
INSERT INTO `analyse_user` VALUES (141, 'o9ICH5Bu4JVAhbmQb1WTl_1fot-o', '2019-02-21 11:14:09', 2, NULL);
INSERT INTO `analyse_user` VALUES (142, 'o9ICH5J9fwDWs_vpKZuyiDxM8igw', '2019-02-21 11:14:09', 1, NULL);
INSERT INTO `analyse_user` VALUES (143, 'null', '2019-02-21 11:14:09', 1, NULL);
INSERT INTO `analyse_user` VALUES (144, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-22 11:14:09', 14, '111');
INSERT INTO `analyse_user` VALUES (145, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-23 11:14:09', 2, '111');
INSERT INTO `analyse_user` VALUES (146, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-24 11:14:09', 1, '111');
INSERT INTO `analyse_user` VALUES (147, 'o9ICH5JRciM-eyVZKHlyMKP-xdj0', '2019-02-25 11:14:09', 13, '111');

SET FOREIGN_KEY_CHECKS = 1;
