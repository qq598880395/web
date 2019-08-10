/*
Navicat MySQL Data Transfer

Source Server         : StarTower
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wools

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-08-10 08:18:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for food_list
-- ----------------------------
DROP TABLE IF EXISTS `food_list`;
CREATE TABLE `food_list` (
  `food_id` int(11) NOT NULL,
  `hotel_id` int(255) NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `food_price` decimal(10,2) NOT NULL,
  `food_details` varchar(255) DEFAULT NULL,
  `food_pic` varchar(255) NOT NULL,
  `foot_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `hotel_id` (`hotel_id`),
  CONSTRAINT `food_list_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_list
-- ----------------------------

-- ----------------------------
-- Table structure for food_order
-- ----------------------------
DROP TABLE IF EXISTS `food_order`;
CREATE TABLE `food_order` (
  `food_order_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `vip_id` varchar(255) NOT NULL,
  `food_id` int(11) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `order_time` datetime NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `hotel_room` int(255) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`food_order_id`),
  KEY `hotel_id` (`hotel_id`),
  KEY `food_id` (`food_id`),
  KEY `vip_id` (`vip_id`),
  CONSTRAINT `food_order_ibfk_3` FOREIGN KEY (`vip_id`) REFERENCES `vip_list` (`vip_id`),
  CONSTRAINT `food_order_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `food_order_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food_list` (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_order
-- ----------------------------

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(255) NOT NULL,
  `hotel_area` varchar(255) NOT NULL,
  `hotel_details` varchar(255) NOT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1001', 'woolls', '湖南', '湖南最大');
INSERT INTO `hotel` VALUES ('1002', 'wols', '广东', '广东最大');

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `img_id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) NOT NULL,
  `img_src` varchar(255) NOT NULL,
  `img_status` varchar(255) NOT NULL DEFAULT 'no',
  `template_id` int(11) NOT NULL,
  `img_href` varchar(255) DEFAULT '#',
  PRIMARY KEY (`img_id`),
  KEY `template_id` (`template_id`),
  CONSTRAINT `img_ibfk_1` FOREIGN KEY (`template_id`) REFERENCES `template` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES ('70', '201020782669088.jpg', 'img/lunbo/201020782669088.jpg', 'yes', '1', 'https://www.baidu.com');
INSERT INTO `img` VALUES ('75', '201204771792368.png', 'img/modnav/201204771792368.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('76', '201217105431540.png', 'img/modnav/201217105431540.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('77', '201228604003301.png', 'img/modnav/201228604003301.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('78', '201242514801871.png', 'img/modnav/201242514801871.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('79', '201260145108265.png', 'img/modnav/201260145108265.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('80', '201276770086431.png', 'img/modnav/201276770086431.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('81', '201296176027747.png', 'img/modnav/201296176027747.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('82', '201308032569259.png', 'img/modnav/201308032569259.png', 'yes', '3', '#');
INSERT INTO `img` VALUES ('83', '310019873565515.jpg', 'img/lunbo/310019873565515.jpg', 'yes', '1', '#');
INSERT INTO `img` VALUES ('84', '310019874151544.jpg', 'img/lunbo/310019874151544.jpg', 'yes', '1', '#');
INSERT INTO `img` VALUES ('87', '310061036684223.jpg', 'img/lunbo/310061036684223.jpg', 'yes', '1', '#');
INSERT INTO `img` VALUES ('88', '310061036744394.jpg', 'img/lunbo/310061036744394.jpg', 'yes', '1', '#');

-- ----------------------------
-- Table structure for modnav
-- ----------------------------
DROP TABLE IF EXISTS `modnav`;
CREATE TABLE `modnav` (
  `id` int(11) NOT NULL,
  `mod_title` varchar(255) NOT NULL,
  `img_id` int(11) NOT NULL,
  `mod_status` varchar(255) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of modnav
-- ----------------------------

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_name` varchar(255) NOT NULL,
  `page_src` varchar(255) NOT NULL,
  `page_status` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES ('30', '模板1', '/page/1565251239474.html', 'no', '2019-08-08 16:00:39');
INSERT INTO `page` VALUES ('31', '模板2', '/page/1565251336511.html', 'no', '2019-08-08 16:02:16');
INSERT INTO `page` VALUES ('32', '模板3', '/page/1565254138509.html', 'no', '2019-08-08 16:48:58');
INSERT INTO `page` VALUES ('33', '模板4', '/page/1565359036532.html', 'yes', '2019-08-09 21:57:16');

-- ----------------------------
-- Table structure for rc_case
-- ----------------------------
DROP TABLE IF EXISTS `rc_case`;
CREATE TABLE `rc_case` (
  `rc_caseid` int(11) NOT NULL,
  `rc_a` int(11) DEFAULT NULL,
  `rc_b` int(11) DEFAULT NULL,
  `rc_c` int(11) DEFAULT NULL,
  `rc_a_regiv` int(11) DEFAULT NULL,
  `rc_b_regiv` int(11) DEFAULT NULL,
  `rc_c_regiv` int(11) DEFAULT NULL,
  PRIMARY KEY (`rc_caseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rc_case
-- ----------------------------
INSERT INTO `rc_case` VALUES ('1', '100', '200', '300', '20', '45', '80');

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `rc_id` varchar(255) NOT NULL,
  `vip_id` varchar(255) NOT NULL,
  `vip_tel` varchar(255) DEFAULT NULL,
  `vip_name` varchar(255) DEFAULT NULL,
  `rc_cost_1st` decimal(11,2) DEFAULT NULL,
  `rc_cost` decimal(11,2) NOT NULL,
  `rc_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hotel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `vip_id` (`vip_id`),
  CONSTRAINT `recharge_ibfk_1` FOREIGN KEY (`vip_id`) REFERENCES `vip_list` (`vip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES ('29b85b40758f4f409be07cda3e5b703f', '77e363f44ea646c0b4156e41b8a87102', '13519237986', '陈洋', '200.00', '245.00', '2019-08-09 12:50:57', '1001');
INSERT INTO `recharge` VALUES ('3e640189c6c84341a681ccd2dbf856d0', '22222222', '15856658998', '???', '200.00', '245.00', '2019-08-05 23:06:13', '1002');
INSERT INTO `recharge` VALUES ('458a0a3ccc61415c9ca51c8af5bb04ea', '22222222', '15856658998', '???', '300.00', '3800.00', '2019-08-05 23:06:15', '1002');
INSERT INTO `recharge` VALUES ('640b668b58b5410282ee170bedaf5868', '6b17e570ee124e9d8fb24afd8ab1446c', '13973723157', '13973723157', '200.00', '245.00', '2019-08-05 23:06:17', '1001');
INSERT INTO `recharge` VALUES ('69ede5bb371c4cc497892c24dafcea1e', '22222222', '15856658998', '黄民豪', '500.00', '580.00', '2019-08-05 23:06:19', '1002');
INSERT INTO `recharge` VALUES ('715c5d5b3a2246b19245b39ac8e08be3', '6b17e570ee124e9d8fb24afd8ab1446c', '13973723157', '???', '3000.00', '3800.00', '2019-08-06 17:47:38', '1001');
INSERT INTO `recharge` VALUES ('767464b87c4e4f45b01ff970d66b095c', '22222222', '15856658998', '???', '100.00', '120.00', '2019-08-05 23:06:20', '1002');
INSERT INTO `recharge` VALUES ('8b31345e360c4a7ba37d417a96949713', '6b17e570ee124e9d8fb24afd8ab1446c', '13973723157', '???', '100.00', '120.00', '2019-08-06 18:04:51', '1001');
INSERT INTO `recharge` VALUES ('9752e33a318f4f9e8a6439c49fec6315', '22222222', '15856658998', '???', '100.00', '100.00', '2019-08-05 23:06:21', '1002');
INSERT INTO `recharge` VALUES ('a43c3a648c7b4696823c93153fc15661', '77e363f44ea646c0b4156e41b8a87102', '13519237986', '陈洋', '200.00', '245.00', '2019-08-08 16:26:10', '1001');
INSERT INTO `recharge` VALUES ('aab5ec338a524fcebf090e61ac8816b4', '22222222', '15856658998', '???', '10.00', '10.00', '2019-08-05 23:06:22', '1002');
INSERT INTO `recharge` VALUES ('bc59a4e209cd48e3962a6ad36968a5b5', '22222222', '15856658998', '???', '100.00', '120.00', '2019-08-05 23:06:24', '1002');
INSERT INTO `recharge` VALUES ('d50695e63160455dac139e9eddbe4021', '6b17e570ee124e9d8fb24afd8ab1446c', '13973723157', '13973723157', '100.00', '120.00', '2019-08-05 23:06:26', '1001');
INSERT INTO `recharge` VALUES ('e464c7c532f640df84178efcca1fab71', '22222222', '13973723157', '???', '100.00', '120.00', '2019-08-06 14:19:41', '1002');
INSERT INTO `recharge` VALUES ('f47924e0c19c44a2bbe3e7eb99c390e4', '6b17e570ee124e9d8fb24afd8ab1446c', '13973723157', '???', '3000.00', '3800.00', '2019-08-06 18:00:23', '1001');

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `template_id` int(11) NOT NULL,
  `template_name` varchar(255) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES ('1', 'lunbo');
INSERT INTO `template` VALUES ('2', '文章');
INSERT INTO `template` VALUES ('3', '模块');

-- ----------------------------
-- Table structure for vip_level
-- ----------------------------
DROP TABLE IF EXISTS `vip_level`;
CREATE TABLE `vip_level` (
  `level_id` int(10) NOT NULL,
  `level_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `level_need` decimal(10,0) DEFAULT NULL,
  `level_cost` decimal(10,2) DEFAULT NULL,
  `next_need` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vip_level
-- ----------------------------
INSERT INTO `vip_level` VALUES ('1', '青铜会员', '0', '1.00', '200.00');
INSERT INTO `vip_level` VALUES ('2', '白银会员', '200', '0.95', '800.00');
INSERT INTO `vip_level` VALUES ('3', '黄金会员', '800', '0.92', '2000.00');
INSERT INTO `vip_level` VALUES ('4', '铂金会员', '2000', '0.88', '5000.00');
INSERT INTO `vip_level` VALUES ('5', '钻石会员', '5000', '0.85', '12000.00');
INSERT INTO `vip_level` VALUES ('6', '尊贵会员', '12000', '0.80', '50000.00');
INSERT INTO `vip_level` VALUES ('7', '至尊会员', '50000', '0.75', null);

-- ----------------------------
-- Table structure for vip_list
-- ----------------------------
DROP TABLE IF EXISTS `vip_list`;
CREATE TABLE `vip_list` (
  `vip_id` varchar(255) NOT NULL,
  `openid` int(11) DEFAULT NULL,
  `vip_tel` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `vip_name` varchar(30) DEFAULT NULL,
  `vip_IDcard` varchar(200) DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  `vip_money` decimal(10,2) DEFAULT NULL,
  `vip_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `level_id` int(10) NOT NULL DEFAULT '1',
  `level_num` decimal(10,2) DEFAULT '0.00',
  `hotel_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vip_id`),
  KEY `hotel_id` (`hotel_id`),
  KEY `level_id` (`level_id`),
  CONSTRAINT `vip_list_ibfk_2` FOREIGN KEY (`level_id`) REFERENCES `vip_level` (`level_id`),
  CONSTRAINT `vip_list_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_list
-- ----------------------------
INSERT INTO `vip_list` VALUES ('11111111', '123456789', '13355556666', '陈洋', '13150419990323621x', '1001', '20000.00', '2019-08-06 15:41:54', '6', '20000.00', 'woolls');
INSERT INTO `vip_list` VALUES ('22222222', '987654321', '13973723157', '黄民豪', '165457199804301201', '1002', '15700.00', '2019-08-06 15:41:52', '6', '15700.00', 'wols');
INSERT INTO `vip_list` VALUES ('33333333', '542165487', '13973723156', '方慕槐', '430981199901176311', '1001', '200000.00', '2019-08-06 15:41:34', '7', '200000.00', 'woolls');
INSERT INTO `vip_list` VALUES ('444444444', null, '13456787978', '黄星华', '1213215464', '1001', '20000.00', '2019-08-06 15:41:32', '6', '20000.00', 'woolls');
INSERT INTO `vip_list` VALUES ('555555555', null, '13458795455', '强哥', '2131235454', '1001', '20.00', '2019-08-06 15:34:58', '1', '20.00', 'woolls');
INSERT INTO `vip_list` VALUES ('6b17e570ee124e9d8fb24afd8ab1446c', null, '13973723157', '???', '444433332222', '1001', '8085.10', '2019-08-06 18:04:51', '5', '6465.10', 'woolls');
INSERT INTO `vip_list` VALUES ('77e363f44ea646c0b4156e41b8a87102', null, '13519237986', '陈洋', 'sdjajdfkjasfldsj', '1001', '490.00', '2019-08-09 12:50:57', '2', '400.00', 'woolls');
