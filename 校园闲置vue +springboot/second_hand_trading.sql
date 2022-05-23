/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : second_hand_trading

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2022-03-18 17:52:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sh_address
-- ----------------------------
DROP TABLE IF EXISTS `sh_address`;
CREATE TABLE `sh_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `consignee_name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(16) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(32) NOT NULL COMMENT '省',
  `city_name` varchar(32) NOT NULL COMMENT '市',
  `region_name` varchar(32) NOT NULL COMMENT '区',
  `detail_address` varchar(64) NOT NULL COMMENT '详细地址',
  `default_flag` tinyint(4) NOT NULL COMMENT '是否默认地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地址表';

-- ----------------------------
-- Records of sh_address
-- ----------------------------
INSERT INTO `sh_address` VALUES ('28', '张仪', '15896565656', '山西省', '太原市', '小店区', '幸福家园1号楼1102', '1', '37');
INSERT INTO `sh_address` VALUES ('29', '张仪', '15896565656', '山西省', '太原市', '小店区', '凯旋国际563', '0', '37');

-- ----------------------------
-- Table structure for sh_admin
-- ----------------------------
DROP TABLE IF EXISTS `sh_admin`;
CREATE TABLE `sh_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(16) NOT NULL COMMENT '密码',
  `admin_name` varchar(8) NOT NULL COMMENT '管理员名字',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account_number` (`account_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

-- ----------------------------
-- Records of sh_admin
-- ----------------------------
INSERT INTO `sh_admin` VALUES ('1', 'admin', '123456', '超级管理员');

-- ----------------------------
-- Table structure for sh_favorite
-- ----------------------------
DROP TABLE IF EXISTS `sh_favorite`;
CREATE TABLE `sh_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `create_time` datetime NOT NULL COMMENT '加入收藏的时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置物主键id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`,`idle_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收藏信息表';

-- ----------------------------
-- Records of sh_favorite
-- ----------------------------
INSERT INTO `sh_favorite` VALUES ('44', '2022-02-24 08:52:58', '37', '85');
INSERT INTO `sh_favorite` VALUES ('45', '2022-02-24 08:53:03', '37', '87');
INSERT INTO `sh_favorite` VALUES ('46', '2022-02-24 09:12:40', '36', '91');
INSERT INTO `sh_favorite` VALUES ('47', '2022-02-24 09:12:45', '36', '90');

-- ----------------------------
-- Table structure for sh_idle_item
-- ----------------------------
DROP TABLE IF EXISTS `sh_idle_item`;
CREATE TABLE `sh_idle_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `idle_name` varchar(64) NOT NULL COMMENT '闲置物名称',
  `idle_details` varchar(2048) NOT NULL COMMENT '详情',
  `picture_list` varchar(1024) NOT NULL COMMENT '图集',
  `idle_price` decimal(10,2) NOT NULL COMMENT '价格',
  `idle_place` varchar(32) NOT NULL COMMENT '发货地区',
  `idle_label` int(11) NOT NULL COMMENT '分类标签',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `idle_status` tinyint(4) NOT NULL COMMENT '状态（发布1、下架2、删除0）',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='二手商品表';

-- ----------------------------
-- Records of sh_idle_item
-- ----------------------------
INSERT INTO `sh_idle_item` VALUES ('81', '三星手机', '二手闲置三星手机', '[\"http://localhost:8080/image?imageName=file164569022369110021.jpg\"]', '1000.00', '市辖区', '1', '2022-02-24 08:10:28', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('82', '折叠手机', '几个月前买的折叠手机，不想要了', '[\"http://localhost:8080/image?imageName=file164569133572210032.jpg\"]', '2999.00', '市辖区', '1', '2022-02-24 08:28:59', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('83', '苹果6', '9成新 好使  需要联系吧  手机都是自己用的 手机很多  都能用 想要哪个都行 图片里选', '[\"http://localhost:8080/image?imageName=file1645691490496100431.jpg\",\"http://localhost:8080/image?imageName=file1645691493727100532.jpg\"]', '2800.00', '市辖区', '1', '2022-02-24 08:31:37', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('84', '新国标电动续航50公里到300公里', '新国标电动自行车，外卖电动自行车，库房直发，高配置电动自行车，全部采用钢丝真空轮胎，液晶仪表，汽车盘刹，LED灯光同城免费送货上门，满意后付款。', '[\"http://localhost:8080/image?imageName=file164569163128810064.jpg\"]', '1980.00', '市辖区', '5', '2022-02-24 08:34:05', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('85', '二手书直接卖', '毕业了不要了', '[\"http://localhost:8080/image?imageName=file164569183716710075.jpg\"]', '26.00', '市辖区', '4', '2022-02-24 08:37:21', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('86', '道德经妙解', '道德经妙解，看了都懂', '[\"http://localhost:8080/image?imageName=file164569192391610086.jpg\"]', '56.00', '市辖区', '4', '2022-02-24 08:38:48', '2', '36');
INSERT INTO `sh_idle_item` VALUES ('87', '从0到1', '创业必看', '[\"http://localhost:8080/image?imageName=file164569201071610097.jpg\"]', '66.00', '市辖区', '4', '2022-02-24 08:40:13', '2', '36');
INSERT INTO `sh_idle_item` VALUES ('88', '海尔老彩电', '家里换新，想要的联系', '[\"http://localhost:8080/image?imageName=file164569214053010108.jpg\"]', '260.00', '市辖区', '2', '2022-02-24 08:42:24', '1', '36');
INSERT INTO `sh_idle_item` VALUES ('89', '伊莱克斯滚筒洗衣机', '伊莱克斯5公斤滚筒洗衣机 二手滚筒洗衣机 二手洗衣机', '[\"http://localhost:8080/image?imageName=file164569235935910119.jpg\"]', '980.00', '太原市', '2', '2022-02-24 08:46:02', '1', '37');
INSERT INTO `sh_idle_item` VALUES ('90', '惠普4530笔记本电脑', '惠普4530二手笔记本电脑', '[\"http://localhost:8080/image?imageName=file1645692446793101210.jpg\"]', '900.00', '太原市', '1', '2022-02-24 08:47:36', '1', '37');
INSERT INTO `sh_idle_item` VALUES ('91', '二手Mac电脑', '换新了，需要的赶紧联系', '[\"http://localhost:8080/image?imageName=file1645692559553101311.jpg\"]', '4500.00', '太原市', '1', '2022-02-24 08:49:25', '1', '37');
INSERT INTO `sh_idle_item` VALUES ('92', '全自动两用帐篷', '全自动两用帐篷，8成新', '[\"http://localhost:8080/image?imageName=file1645692690642101412.jpg\"]', '150.00', '太原市', '3', '2022-02-24 08:51:34', '1', '37');

-- ----------------------------
-- Table structure for sh_message
-- ----------------------------
DROP TABLE IF EXISTS `sh_message`;
CREATE TABLE `sh_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置主键id',
  `content` varchar(256) NOT NULL COMMENT '留言内容',
  `create_time` datetime NOT NULL COMMENT '留言时间',
  `to_user` bigint(20) NOT NULL COMMENT '所回复的用户',
  `to_message` bigint(20) DEFAULT NULL COMMENT '所回复的留言',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE,
  KEY `idle_id_index` (`idle_id`) USING BTREE,
  KEY `to_user_index` (`to_user`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='留言表';

-- ----------------------------
-- Records of sh_message
-- ----------------------------
INSERT INTO `sh_message` VALUES ('36', '37', '85', '可以便宜吗', '2022-02-24 08:52:50', '36', null);
INSERT INTO `sh_message` VALUES ('37', '37', '85', '最低多少钱', '2022-03-18 04:57:20', '37', '36');

-- ----------------------------
-- Table structure for sh_order
-- ----------------------------
DROP TABLE IF EXISTS `sh_order`;
CREATE TABLE `sh_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_number` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置物品主键id',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  `payment_status` tinyint(4) NOT NULL COMMENT '支付状态',
  `payment_way` varchar(16) DEFAULT NULL COMMENT '支付方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- ----------------------------
-- Records of sh_order
-- ----------------------------
INSERT INTO `sh_order` VALUES ('63', '164569278420610002', '37', '87', '66.00', '0', null, '2022-02-24 08:53:04', null, '4', null);
INSERT INTO `sh_order` VALUES ('64', '164569321548210003', '37', '87', '66.00', '1', '支付宝', '2022-02-24 09:00:15', '2022-02-24 09:00:50', '2', null);

-- ----------------------------
-- Table structure for sh_order_address
-- ----------------------------
DROP TABLE IF EXISTS `sh_order_address`;
CREATE TABLE `sh_order_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `consignee_name` varchar(32) NOT NULL COMMENT '收货人',
  `consignee_phone` varchar(32) NOT NULL COMMENT '电话',
  `detail_address` varchar(128) NOT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `orderId` (`order_id`) USING BTREE,
  KEY `order_id_index` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单地址表';

-- ----------------------------
-- Records of sh_order_address
-- ----------------------------
INSERT INTO `sh_order_address` VALUES ('50', '64', '张仪', '15896565656', '山西省太原市小店区幸福家园1号楼1102');

-- ----------------------------
-- Table structure for sh_user
-- ----------------------------
DROP TABLE IF EXISTS `sh_user`;
CREATE TABLE `sh_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) NOT NULL COMMENT '账号（手机号）',
  `user_password` varchar(16) NOT NULL COMMENT '登录密码',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `avatar` varchar(256) NOT NULL COMMENT '头像',
  `sign_in_time` datetime NOT NULL COMMENT '注册时间',
  `user_status` tinyint(4) DEFAULT NULL COMMENT '状态（1代表封禁）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account_number` (`account_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sh_user
-- ----------------------------
INSERT INTO `sh_user` VALUES ('36', '15263698569', '123456', '猫头鹰源码', 'https://www.maotouyingcc.com/wp-content/uploads/2021/10/100-100.png', '2022-02-24 07:39:39', null);
INSERT INTO `sh_user` VALUES ('37', '15896565656', '123456', '我是谁呀', 'https://pic3.zhimg.com/80/v2-1eb39182306777041ad4a2caa5e4498d_720w.jpg?source=1940ef5c', '2022-02-24 08:43:04', null);

-- ----------------------------
-- Table structure for sh_user_item
-- ----------------------------
DROP TABLE IF EXISTS `sh_user_item`;
CREATE TABLE `sh_user_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置物主键id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入收藏的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户浏览物品表';

-- ----------------------------
-- Records of sh_user_item
-- ----------------------------
INSERT INTO `sh_user_item` VALUES ('48', '36', '92', '2022-03-18 05:38:12');
INSERT INTO `sh_user_item` VALUES ('49', '36', '91', '2022-03-18 05:39:32');
INSERT INTO `sh_user_item` VALUES ('50', '36', '89', '2022-03-18 05:39:36');
INSERT INTO `sh_user_item` VALUES ('51', '36', '88', '2022-03-18 05:39:39');
INSERT INTO `sh_user_item` VALUES ('52', '36', '84', '2022-03-18 05:39:43');
INSERT INTO `sh_user_item` VALUES ('53', '37', '92', '2022-03-18 05:40:14');
INSERT INTO `sh_user_item` VALUES ('54', '37', '84', '2022-03-18 05:40:17');
INSERT INTO `sh_user_item` VALUES ('55', '37', '83', '2022-03-18 05:40:21');
INSERT INTO `sh_user_item` VALUES ('56', '37', '85', '2022-03-18 05:40:24');
INSERT INTO `sh_user_item` VALUES ('57', '37', '90', '2022-03-18 05:40:27');
INSERT INTO `sh_user_item` VALUES ('58', '37', '88', '2022-03-18 05:40:29');
INSERT INTO `sh_user_item` VALUES ('62', '37', '92', '2022-03-18 05:51:24');
INSERT INTO `sh_user_item` VALUES ('63', '37', '89', '2022-03-18 09:48:30');
