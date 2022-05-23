/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : ssmtsgjyglxthsg3922cd

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2022-04-12 16:17:22
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `allusers`
-- ----------------------------
DROP TABLE IF EXISTS `allusers`;
CREATE TABLE `allusers` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `pwd` varchar(50) default NULL,
  `cx` varchar(50) default '普通管理员',
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of allusers
-- ----------------------------
INSERT INTO `allusers` VALUES ('2', 'hsg', 'hsg', '超级管理员', '2022-03-25 03:53:53');
INSERT INTO `allusers` VALUES ('3', 'aaaaaa', 'aaaaaa', '普通管理员', '2022-03-27 03:28:33');

-- ----------------------------
-- Table structure for `chongzhijilu`
-- ----------------------------
DROP TABLE IF EXISTS `chongzhijilu`;
CREATE TABLE `chongzhijilu` (
  `id` int(11) NOT NULL auto_increment,
  `xuehao` varchar(50) default NULL,
  `xingming` varchar(50) default NULL,
  `yue` varchar(50) default NULL,
  `chongzhijine` varchar(50) default NULL,
  `beizhu` varchar(500) default NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of chongzhijilu
-- ----------------------------
INSERT INTO `chongzhijilu` VALUES ('10', '001', '易建联', '22', '33', 'fewgw', '2022-03-25 04:12:46');
INSERT INTO `chongzhijilu` VALUES ('11', '001', '易建联', '666', '2', 'egw', '2022-03-25 04:29:20');
INSERT INTO `chongzhijilu` VALUES ('12', '001', '易建联', '668', '4', 'gwegw', '2022-03-25 04:30:41');
INSERT INTO `chongzhijilu` VALUES ('13', '888', 'kukykuy', '0', '100', 'bewgw', '2022-03-27 03:29:26');

-- ----------------------------
-- Table structure for `jieyuejilu`
-- ----------------------------
DROP TABLE IF EXISTS `jieyuejilu`;
CREATE TABLE `jieyuejilu` (
  `id` int(11) NOT NULL auto_increment,
  `tushubianhao` varchar(50) default NULL,
  `mingcheng` varchar(50) default NULL,
  `leibie` varchar(50) default NULL,
  `zhuangtai` varchar(50) default NULL,
  `jieyueren` varchar(50) default NULL,
  `jieyueshijian` date default NULL,
  `guihuanshijian` date default NULL,
  `feiyong` varchar(50) default NULL,
  `shifouguihuan` varchar(50) default NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of jieyuejilu
-- ----------------------------
INSERT INTO `jieyuejilu` VALUES ('2', '002', 'Z名称', 'Q类', '空闲', '007', '2022-04-09', '2022-04-17', '24', '否', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('3', '001', 'C名称', 'N类', '借出', '018', '2022-03-11', '2022-03-23', '53', '否', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('4', '001', 'C名称', 'N类', '借出', '004', '2022-04-09', '2022-03-25', '52', '否', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('5', '019', 'X名称', 'Q类', '借出', '002', '2022-04-16', '2022-03-16', '53', '否', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('6', '019', 'X名称', 'Q类', '借出', '017', '2022-04-13', '2022-04-17', '76', '是', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('7', '019', 'X名称', 'Q类', '借出', '009', '2022-04-13', '2022-03-16', '76', '是', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('8', '008', 'K名称', 'J类', '借出', '015', '2022-03-13', '2022-04-08', '15', '否', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('9', '001', 'C名称', 'N类', '借出', '001', '2022-04-09', '2022-03-23', '15', '是', '2022-03-25 03:53:53');
INSERT INTO `jieyuejilu` VALUES ('10', '001', 'C名称', 'N类', '借出', '001', '2022-03-25', '2022-03-25', '12', '是', '2022-03-25 04:16:20');
INSERT INTO `jieyuejilu` VALUES ('11', '026', 'D名称', 'P类', '借出', '001', '2022-03-25', '2022-03-23', '5', '是', '2022-03-25 04:25:19');
INSERT INTO `jieyuejilu` VALUES ('12', '001', 'C名称', 'N类', '空闲', '001', '2022-03-25', '2022-03-25', '5', '是', '2022-03-25 04:31:40');
INSERT INTO `jieyuejilu` VALUES ('13', '001', 'C名称', 'N类', '已借', '001', '2022-03-25', '2022-04-09', '5', '是', '2022-03-25 04:31:46');
INSERT INTO `jieyuejilu` VALUES ('14', '001', 'S名称', 'H类', '空闲', '888', '2022-03-27', '2022-04-02', '0', '是', '2022-03-27 03:30:27');
INSERT INTO `jieyuejilu` VALUES ('15', '001', 'S名称', 'H类', '空闲', '888', '2022-03-27', '2022-03-26', '0', '是', '2022-03-27 03:31:14');
INSERT INTO `jieyuejilu` VALUES ('16', '001', 'S名称', 'H类', '已借', '001', '2022-03-28', '2022-03-28', '0', '是', '2022-03-28 03:49:46');
INSERT INTO `jieyuejilu` VALUES ('17', '001', 'S名称', 'H类', '已借', '001', '2022-03-28', '2022-03-28', '0', '是', '2022-03-28 03:49:54');
INSERT INTO `jieyuejilu` VALUES ('18', '001', 'S名称', 'H类', '已借', '001', '2022-03-28', '2022-04-27', '0', '是', '2022-03-28 03:50:29');

-- ----------------------------
-- Table structure for `tushuleibie`
-- ----------------------------
DROP TABLE IF EXISTS `tushuleibie`;
CREATE TABLE `tushuleibie` (
  `id` int(11) NOT NULL auto_increment,
  `leibie` varchar(50) default NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tushuleibie
-- ----------------------------
INSERT INTO `tushuleibie` VALUES ('2', 'J类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('3', 'N类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('4', 'C类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('5', 'A类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('6', 'E类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('7', 'Q类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('8', 'H类', '2022-03-25 03:53:53');
INSERT INTO `tushuleibie` VALUES ('9', 'gegerger', '2022-03-25 03:53:53');

-- ----------------------------
-- Table structure for `tushuxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `tushuxinxi`;
CREATE TABLE `tushuxinxi` (
  `id` int(11) NOT NULL auto_increment,
  `tushubianhao` varchar(50) default NULL,
  `ISBNma` varchar(50) default NULL,
  `mingcheng` varchar(50) default NULL,
  `leibie` varchar(50) default NULL,
  `tupian` varchar(50) default NULL,
  `jiage` varchar(50) default NULL,
  `chubanshe` varchar(50) default NULL,
  `chubandi` varchar(50) default NULL,
  `chubanriqi` varchar(50) default NULL,
  `suoshuhao` varchar(50) default NULL,
  `zhuangtai` varchar(50) default NULL,
  `jieyuecishu` varchar(50) default NULL,
  `zhaiyao` varchar(500) default NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tushuxinxi
-- ----------------------------
INSERT INTO `tushuxinxi` VALUES ('2', '026', '', 'J名称', 'A类', 'upload/nopic3.jpg', '41', '中国日报出版社', '四川', '2022-3-28', '015', '空闲', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('3', '010', '', 'E名称', 'N类', 'upload/nopic1.jpg', '84', '上海晚报出版社', '浙江', '2022-3-29', '005', '在馆', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('4', '008', '', 'P名称', 'J类', 'upload/nopic3.jpg', '10', '黎明出版社', '海南', '2022-3-29', '033', '在馆', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('5', '017', '', 'X名称', 'N类', 'upload/nopic6.jpg', '10', '温州新华出版社', '辽宁', '2022-3-29', '022', '在馆', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('6', '007', '', 'H名称', 'Q类', 'upload/nopic5.jpg', '84', '南京普迪出版社', '福建', '2022-3-11', '007', '在馆', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('7', '020', '', 'Q名称', 'Q类', 'upload/nopic3.jpg', '61', '青春出版社', '贵州', '2022-3-11', '011', '借出', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('8', '004', '', 'L名称', 'C类', 'upload/nopic1.jpg', '14', '旅游出版社', '湖南', '2022-3-1', '004', '借出', '', '', '2022-03-27 02:44:41');
INSERT INTO `tushuxinxi` VALUES ('9', '001', 'r325252', 'S名称', 'H类', 'upload/nopic2.jpg', '52', '北京新贺出版社', '河南', '2022-3-1', '001', '空闲', '5', '', '2022-03-27 02:44:41');

-- ----------------------------
-- Table structure for `xueshengxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `xueshengxinxi`;
CREATE TABLE `xueshengxinxi` (
  `id` int(11) NOT NULL auto_increment,
  `xuehao` varchar(50) default NULL,
  `mima` varchar(50) default NULL,
  `xingming` varchar(50) default NULL,
  `xingbie` varchar(50) default NULL,
  `shenfenzheng` varchar(50) default NULL,
  `dianhua` varchar(50) default NULL,
  `yuanxi` varchar(50) default NULL,
  `banji` varchar(50) default NULL,
  `jiguan` varchar(50) default NULL,
  `zhaopian` varchar(50) default NULL,
  `yue` varchar(50) default NULL,
  `beizhu` varchar(500) default NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xueshengxinxi
-- ----------------------------
INSERT INTO `xueshengxinxi` VALUES ('2', '015', '001', '方之明', '男', '330327198615482633', '13489665487', '化工学院', 'B02', '江苏', 'upload/xueshengxinxi1.jpg', '353', '没问题', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('3', '017', '001', '温宗达', '女', '330327198805060222', '13623256544', '文传学院', 'D02', '青海', 'upload/xueshengxinxi7.jpg', '5', '无', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('4', '018', '001', '霍建华', '女', '330327198811020456', '13748589658', '信息学院', 'D03', '辽宁', 'upload/xueshengxinxi6.jpg', '4', 'ok', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('5', '025', '001', '伍兆斌', '男', '330327198708070789', '13184865998', '基础医学', 'C01', '山西', 'upload/xueshengxinxi8.jpg', '6', '没问题', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('6', '032', '001', '鲁艾弈', '男', '330327198501020300', '13025896548', '机材学院', 'B03', '江西', 'upload/xueshengxinxi2.jpg', '363', 'ok', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('7', '010', '001', '林嘉俐', '女', '33032719900723568X', '13910215489', '土建学院', 'A01', '黑龙江', 'upload/xueshengxinxi3.jpg', '36', '无', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('8', '013', '001', '詹姆斯', '男', '330327199010142546', '17706641413', '艺术学院', 'E01', '浙江', 'upload/xueshengxinxi5.jpg', '3', '无', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('9', '001', '001', '易建联', '男', '330327199504059511', '17505772420', '护理学院', 'A02', '云南', 'upload/xueshengxinxi4.jpg', '467', 'ok', '2022-03-25 03:53:53');
INSERT INTO `xueshengxinxi` VALUES ('11', '7777', '7777', 'jyujtjt', '男', '330327198403060255', '13186835580', 'gwegwe', '', '', '', '0', '', '2022-03-27 03:27:19');
INSERT INTO `xueshengxinxi` VALUES ('12', '888', '888', 'kukykuy', '男', '330327198403060255', '13186835580', '', '', '', '', '80', '', '2022-03-27 03:28:17');
