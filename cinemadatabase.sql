/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : cinemadatabase

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 27/11/2018 15:56:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_cinema
-- ----------------------------
DROP TABLE IF EXISTS `city_cinema`;
CREATE TABLE `city_cinema`  (
  `CINEMA_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `CITY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CINEMA` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ADDRESS` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BUTTONCHAR` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CINEMA_NUM`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city_cinema
-- ----------------------------
INSERT INTO `city_cinema` VALUES (1, '汕头', '映像影院', '市区', NULL);
INSERT INTO `city_cinema` VALUES (2, '湛江', '映像影院', '广东海洋大学附近', NULL);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `COMMENT` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `PUBLISHER` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATE` date NULL DEFAULT NULL,
  `PHONE_FK` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MOVIE_NUM_FK` int(11) NULL DEFAULT NULL,
  `COMMENT_NUM` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`COMMENT_NUM`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('吗自行车女礼金卡开发', '3', '2017-08-30', '3', 3, 20);
INSERT INTO `comments` VALUES ('程序中形成v', '3', '2017-08-30', '3', 3, 21);
INSERT INTO `comments` VALUES ('啊手动阀手动阀撒', '3', '2017-08-30', '3', 3, 22);
INSERT INTO `comments` VALUES ('回复3:  玛卡瑞拉', '啦啦啦', '2017-09-05', '5', 3, 26);
INSERT INTO `comments` VALUES ('回复3:  HFKSDAHFK', '砺', '2017-09-09', '6', 3, 27);

-- ----------------------------
-- Table structure for movies
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies`  (
  `MOVIE_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DIRECTOR` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ACTORS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BRIEF_INTRODUCTION` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `DATE` date NULL DEFAULT NULL,
  `BUTTONCHAR` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CINEMA_NUM_FK` int(11) NULL DEFAULT NULL,
  `PRICE2D` decimal(10, 0) NULL DEFAULT NULL,
  `PRICE3D` decimal(10, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`MOVIE_NUM`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES (1, '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '这不是西游记的任何章节，这是悟空的故事，彼时孙悟空（彭于晏 饰）还不是震撼天地的齐天大圣，他只是只桀傲不驯的猴子。天庭毁掉他的花果山以掌控众生命运，他便决心跟天庭对抗，毁掉一切戒律。在天庭，孙悟空遇到不能爱的阿紫（倪妮 饰），一生的宿敌杨戬（余文乐 饰），和思念昔日爱人阿月（郑爽 饰）的天蓬（欧豪 饰），他们的身份注定永生相杀，但其实不甘命运摆布的又何止孙悟空一人？却没想到反抗却带来更大的浩劫。他们所做的一切，究竟是不知天高地厚的热血轻狂，还是无奈宿命难改的压抑绝望？难道命运真的早已注定？悟空不服，他再次挥动金箍棒，要让这诸佛都烟消云散！', NULL, NULL, 1, 40, 50);
INSERT INTO `movies` VALUES (2, NULL, NULL, NULL, NULL, '2017-06-01', NULL, NULL, NULL, NULL);
INSERT INTO `movies` VALUES (3, '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '这不是西游记的任何章节，这是悟空的故事，彼时孙悟空（彭于晏 饰）还不是震撼天地的齐天大圣，他只是只桀傲不驯的猴子。天庭毁掉他的花果山以掌控众生命运，他便决心跟天庭对抗，毁掉一切戒律。在天庭，孙悟空遇到不能爱的阿紫（倪妮 饰），一生的宿敌杨戬（余文乐 饰），和思念昔日爱人阿月（郑爽 饰）的天蓬（欧豪 饰），他们的身份注定永生相杀，但其实不甘命运摆布的又何止孙悟空一人？却没想到反抗却带来更大的浩劫。他们所做的一切，究竟是不知天高地厚的热血轻狂，还是无奈宿命难改的压抑绝望？难道命运真的早已注定？悟空不服，他再次挥动金箍棒，要让这诸佛都烟消云散！', '2017-06-01', NULL, 2, 40, 50);

-- ----------------------------
-- Table structure for personaldata
-- ----------------------------
DROP TABLE IF EXISTS `personaldata`;
CREATE TABLE `personaldata`  (
  `PHONE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PHONE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personaldata
-- ----------------------------
INSERT INTO `personaldata` VALUES ('1', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('123', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('15816641429', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('2', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('3', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('4', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('5', '6', '呖', '6@163.com');
INSERT INTO `personaldata` VALUES ('6', '6', '砺', '6@163.com');
INSERT INTO `personaldata` VALUES ('7', '7', '希', 'xi@163.com');

-- ----------------------------
-- Table structure for personalorder
-- ----------------------------
DROP TABLE IF EXISTS `personalorder`;
CREATE TABLE `personalorder`  (
  `CITY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CINEMA` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ADDRESS` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DIRECTOR` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATE` date NULL DEFAULT NULL,
  `PRICE` decimal(10, 0) NULL DEFAULT NULL,
  `PHONE_FK` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SEAT` int(11) NULL DEFAULT NULL,
  `MOVIE_NUM_FK` int(11) NULL DEFAULT NULL,
  `ORDER_NUM` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ORDER_NUM`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personalorder
-- ----------------------------
INSERT INTO `personalorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 50, '5', 45, 3, 25);
INSERT INTO `personalorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 50, '5', 25, 3, 32);
INSERT INTO `personalorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 50, '7', 31, 3, 33);
INSERT INTO `personalorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 40, '6', 5, 3, 34);

-- ----------------------------
-- Table structure for personalrecorder
-- ----------------------------
DROP TABLE IF EXISTS `personalrecorder`;
CREATE TABLE `personalrecorder`  (
  `CITY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CINEMA` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ADDRESS` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DIRECTOR` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATE` date NULL DEFAULT NULL,
  `SEAT` int(11) NULL DEFAULT NULL,
  `ORDER_NUM_FK` int(11) NULL DEFAULT NULL,
  `PRICE` decimal(10, 0) NULL DEFAULT NULL,
  `PHONE_FK` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personalrecorder
-- ----------------------------
INSERT INTO `personalrecorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 25, 32, 50, '5');
INSERT INTO `personalrecorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 31, 33, 50, '7');
INSERT INTO `personalrecorder` VALUES ('湛江', '映像影院', '广东海洋大学附近', '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', 5, 34, 40, '6');

-- ----------------------------
-- Table structure for seattable
-- ----------------------------
DROP TABLE IF EXISTS `seattable`;
CREATE TABLE `seattable`  (
  `MOVIE_NUM_FK` int(11) NULL DEFAULT NULL,
  `SEAT_1` int(11) NULL DEFAULT NULL,
  `SEAT_2` int(11) NULL DEFAULT NULL,
  `SEAT_3` int(11) NULL DEFAULT NULL,
  `SEAT_4` int(11) NULL DEFAULT NULL,
  `SEAT_5` int(11) NULL DEFAULT NULL,
  `SEAT_6` int(11) NULL DEFAULT NULL,
  `SEAT_7` int(11) NULL DEFAULT NULL,
  `SEAT_8` int(11) NULL DEFAULT NULL,
  `SEAT_9` int(11) NULL DEFAULT NULL,
  `SEAT_10` int(11) NULL DEFAULT NULL,
  `SEAT_11` int(11) NULL DEFAULT NULL,
  `SEAT_12` int(11) NULL DEFAULT NULL,
  `SEAT_13` int(11) NULL DEFAULT NULL,
  `SEAT_14` int(11) NULL DEFAULT NULL,
  `SEAT_15` int(11) NULL DEFAULT NULL,
  `SEAT_16` int(11) NULL DEFAULT NULL,
  `SEAT_17` int(11) NULL DEFAULT NULL,
  `SEAT_18` int(11) NULL DEFAULT NULL,
  `SEAT_19` int(11) NULL DEFAULT NULL,
  `SEAT_20` int(11) NULL DEFAULT NULL,
  `SEAT_21` int(11) NULL DEFAULT NULL,
  `SEAT_22` int(11) NULL DEFAULT NULL,
  `SEAT_23` int(11) NULL DEFAULT NULL,
  `SEAT_24` int(11) NULL DEFAULT NULL,
  `SEAT_25` int(11) NULL DEFAULT NULL,
  `SEAT_26` int(11) NULL DEFAULT NULL,
  `SEAT_27` int(11) NULL DEFAULT NULL,
  `SEAT_28` int(11) NULL DEFAULT NULL,
  `SEAT_29` int(11) NULL DEFAULT NULL,
  `SEAT_30` int(11) NULL DEFAULT NULL,
  `SEAT_31` int(11) NULL DEFAULT NULL,
  `SEAT_32` int(11) NULL DEFAULT NULL,
  `SEAT_33` int(11) NULL DEFAULT NULL,
  `SEAT_34` int(11) NULL DEFAULT NULL,
  `SEAT_35` int(11) NULL DEFAULT NULL,
  `SEAT_36` int(11) NULL DEFAULT NULL,
  `SEAT_37` int(11) NULL DEFAULT NULL,
  `SEAT_38` int(11) NULL DEFAULT NULL,
  `SEAT_39` int(11) NULL DEFAULT NULL,
  `SEAT_40` int(11) NULL DEFAULT NULL,
  `SEAT_41` int(11) NULL DEFAULT NULL,
  `SEAT_42` int(11) NULL DEFAULT NULL,
  `SEAT_43` int(11) NULL DEFAULT NULL,
  `SEAT_44` int(11) NULL DEFAULT NULL,
  `SEAT_45` int(11) NULL DEFAULT NULL,
  `SEAT_46` int(11) NULL DEFAULT NULL,
  `SEAT_47` int(11) NULL DEFAULT NULL,
  `SEAT_48` int(11) NULL DEFAULT NULL,
  `SEAT_49` int(11) NULL DEFAULT NULL,
  `SEAT_50` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seattable
-- ----------------------------
INSERT INTO `seattable` VALUES (3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 30, 31, 0, 0, 0, 35, 0, 0, 0, 39, 0, 0, 0, 0, 0, 45, 46, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for wanna_see
-- ----------------------------
DROP TABLE IF EXISTS `wanna_see`;
CREATE TABLE `wanna_see`  (
  `MOVIE_NUM_FK` int(11) NULL DEFAULT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DIRECTOR` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ACTOR` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DATE` date NULL DEFAULT NULL,
  `PHONE_FK` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wanna_see
-- ----------------------------
INSERT INTO `wanna_see` VALUES (3, '悟空传', '郭子健 ', ' 彭于晏  倪妮  欧豪  余文乐  郑爽  乔杉  俞飞鸿  杨迪 ', '2017-06-01', '3');

SET FOREIGN_KEY_CHECKS = 1;
