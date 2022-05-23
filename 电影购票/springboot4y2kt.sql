/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : springboot4y2kt

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2022-05-10 18:43:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COMMENT '提问',
  `reply` longtext COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1652168520299 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='在线咨询';

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES ('61', '2022-04-13 10:26:35', '1', '1', '提问1', '回复1', '1');
INSERT INTO `chat` VALUES ('62', '2022-04-13 10:26:35', '2', '2', '提问2', '回复2', '2');
INSERT INTO `chat` VALUES ('63', '2022-04-13 10:26:35', '3', '3', '提问3', '回复3', '3');
INSERT INTO `chat` VALUES ('64', '2022-04-13 10:26:35', '4', '4', '提问4', '回复4', '4');
INSERT INTO `chat` VALUES ('65', '2022-04-13 10:26:35', '5', '5', '提问5', '回复5', '5');
INSERT INTO `chat` VALUES ('66', '2022-04-13 10:26:35', '6', '6', '提问6', '回复6', '6');
INSERT INTO `chat` VALUES ('1649817945962', '2022-04-13 10:45:45', '1649817845523', null, '这里是在线咨询的地方', null, '0');
INSERT INTO `chat` VALUES ('1649818265055', '2022-04-13 10:51:05', '1649817845523', '1', null, '这里是回复用户的在线咨询的地方', '0');
INSERT INTO `chat` VALUES ('1652167901733', '2022-05-10 15:31:41', '1649817845523', null, '111', null, '0');
INSERT INTO `chat` VALUES ('1652168008358', '2022-05-10 15:33:27', '1649817845523', null, '12', null, '0');
INSERT INTO `chat` VALUES ('1652168024365', '2022-05-10 15:33:43', '1649817845523', '1', null, '13', '0');
INSERT INTO `chat` VALUES ('1652168035354', '2022-05-10 15:33:54', '1649817845523', null, '14', null, '0');
INSERT INTO `chat` VALUES ('1652168082840', '2022-05-10 15:34:42', '1649817845523', '1', null, '15', '0');
INSERT INTO `chat` VALUES ('1652168116872', '2022-05-10 15:35:16', '1649817845523', null, '16', null, '0');
INSERT INTO `chat` VALUES ('1652168125572', '2022-05-10 15:35:25', '1649817845523', null, '17', null, '0');
INSERT INTO `chat` VALUES ('1652168473158', '2022-05-10 15:41:12', '1649817845523', null, '121', null, '0');
INSERT INTO `chat` VALUES ('1652168494967', '2022-05-10 15:41:33', '1649817845523', null, '12312', null, '0');
INSERT INTO `chat` VALUES ('1652168498098', '2022-05-10 15:41:37', '1649817845523', null, '12', null, '0');
INSERT INTO `chat` VALUES ('1652168500712', '2022-05-10 15:41:39', '1649817845523', null, '34', null, '0');
INSERT INTO `chat` VALUES ('1652168510429', '2022-05-10 15:41:49', '1649817845523', '1', null, '213', '0');
INSERT INTO `chat` VALUES ('1652168520298', '2022-05-10 15:41:59', '1649817845523', null, '1', null, '1');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置文件';

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', 'picture1', 'upload/1652090541194.jpeg');
INSERT INTO `config` VALUES ('2', 'picture2', 'upload/1652090583112.jpg');
INSERT INTO `config` VALUES ('3', 'picture3', 'upload/1652090612419.jpeg');

-- ----------------------------
-- Table structure for dianyingfenlei
-- ----------------------------
DROP TABLE IF EXISTS `dianyingfenlei`;
CREATE TABLE `dianyingfenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dianyingfenlei` varchar(200) NOT NULL COMMENT '电影分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649818044674 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='电影分类';

-- ----------------------------
-- Records of dianyingfenlei
-- ----------------------------
INSERT INTO `dianyingfenlei` VALUES ('31', '2022-04-13 10:26:35', '剧情');
INSERT INTO `dianyingfenlei` VALUES ('32', '2022-04-13 10:26:35', '爱情');
INSERT INTO `dianyingfenlei` VALUES ('33', '2022-04-13 10:26:35', '动画');
INSERT INTO `dianyingfenlei` VALUES ('34', '2022-04-13 10:26:35', '科幻');
INSERT INTO `dianyingfenlei` VALUES ('35', '2022-04-13 10:26:35', '恐怖');
INSERT INTO `dianyingfenlei` VALUES ('1649818044673', '2022-04-13 10:47:23', '喜剧');

-- ----------------------------
-- Table structure for dianyingxinxi
-- ----------------------------
DROP TABLE IF EXISTS `dianyingxinxi`;
CREATE TABLE `dianyingxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dianyingmingcheng` varchar(200) NOT NULL COMMENT '电影名称',
  `dianyingfengmian` varchar(200) DEFAULT NULL COMMENT '电影封面',
  `dianyingfenlei` varchar(200) NOT NULL COMMENT '电影分类',
  `daoyan` varchar(200) NOT NULL COMMENT '导演',
  `yanyuan` varchar(200) DEFAULT NULL COMMENT '演员',
  `faxinggongsi` varchar(200) DEFAULT NULL COMMENT '发行公司',
  `dianyingjianjie` longtext COMMENT '电影简介',
  `dianyingxiangqing` longtext COMMENT '电影详情',
  `yingyuanmingcheng` varchar(200) NOT NULL COMMENT '影院名称',
  `yingyuandizhi` varchar(200) DEFAULT NULL COMMENT '影院地址',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `price` float DEFAULT NULL COMMENT '价格',
  `number` int(11) DEFAULT NULL COMMENT '座位总数',
  `selected` longtext COMMENT '已选座位[用,号隔开]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1652090404970 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='电影信息';

-- ----------------------------
-- Records of dianyingxinxi
-- ----------------------------
INSERT INTO `dianyingxinxi` VALUES ('21', '2022-04-13 10:26:35', '印度女孩', 'upload/1652089006925.jpeg', '电影分类1', '导演1', '演员1', '发行公司1', '电影简介1', '<p>电影详情1</p>', '影院名称1', '影院地址1', '2022-05-10 15:42:39', '15', '99.9', '20', '1,3,5,7,9,14,15,16,13');
INSERT INTO `dianyingxinxi` VALUES ('1652089577361', '2022-05-09 17:46:16', '坏蛋联盟', 'upload/1652089537987.jpg', '动画', '彼埃尔·佩里菲尔', '', '', '梦工场最新动画喜剧电影《坏蛋联盟》(The Bad Guys)改编自同名畅销漫画，全球出版1600万册。本片由《功夫熊猫》系列电影资深动画师彼埃尔·佩里菲尔执导，《魔发精灵》《宝贝老板》王牌制片人保驾护航。漫画由艾伦·布拉培创作，黑白风格，围绕五个“坏蛋”：大坏狼、美肚鲨、贪心蛇、食人鱼、骇客蛛展开故事，它们是令人闻风丧胆的“坏蛋联盟”，然而它们却决定成为模范公民。它们是真的想改“邪”归“正”吗？', '', '影院名称1', '影院地址1', '2022-05-10 15:30:37', '2', '38', '90', '32');
INSERT INTO `dianyingxinxi` VALUES ('1652089708542', '2022-05-09 17:48:28', '边缘行者', 'upload/1652089662537.jpg', '剧情', '黄明升', '', '', '90年代初香港卧底警察阿骆为调查黑帮贩毒的幕后真相，身陷黑帮争位与黑警阴谋的漩涡之中，同只手遮天的隐秘势力斗智斗勇，最终揭露惊天权利阴谋的故事。', '', '影院名称4', '影院地址4', '2022-05-10 15:30:22', '2', '46', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652089765097', '2022-05-09 17:49:24', '我是真的讨厌异地恋', 'upload/1652089736697.jpg', '爱情', '吴洋', '', '', '要相信：熬过异地，就是一生。故事围绕赵一一（任敏 饰）和许嘉树（辛云来 饰）展开，高考之后，赵一一终于向暗恋多年的许嘉树表白，却阴差阳错开启一场异地恋。他们坚信：熬过异地，就是一生。可远距离恋爱的天然阻碍考验着他们，明明相爱，却不断缺席对方的生活。赛赫（陈宥维 饰）的出现以及闺蜜乔乔（周雨彤 饰）和李唐（李孝谦 饰）一波三折的爱情都让赵一一感触良多。当异地恋升级为异国恋，是继续坚持，还是一别两宽，各自成长的二人不得不面对自己内心的答案…', '', '影院名称1', '影院地址1', null, '0', '46', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652089876135', '2022-05-09 17:51:15', '神秘海域', 'upload/1652089831292.jpg', '科幻', '鲁本·弗雷斯彻', '', '', '足智多谋的内森·德雷克（汤姆·赫兰德 饰）和经验丰富的寻宝者维克多·苏利文（马克·沃尔伯格 饰）组成搭档，追寻费迪南·麦哲伦500年前遗失的宝藏。一开始，这似乎只是个简单的寻宝工作，但很快演变成了一场惊险刺激、横跨全球的竞赛。他们必须赶在冷酷无情的蒙卡达（安东尼奥·班德拉斯 饰）之前寻得宝藏。蒙卡达坚信他和他的家族才是宝藏的正当继承人。若内森和苏利文能破译线索，解开这世界上最古老的谜团，他们便能找到价值50亿美元的宝藏，甚至还可能找到内森失踪已久的哥哥…但首先，他们必须学会通力合作。', '', '影院名称3', '影院地址3', null, '0', null, null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652089936652', '2022-05-09 17:52:16', '误杀瞒天记', 'upload/1652089916441.jpg', '剧情', '尼西卡特·卡马特', '', '', '男主人公维杰经营着一家网络公司，有着幸福的家庭。某天，大女儿安玖不堪男孩萨姆骚扰而错手将其杀死。这场黑夜里的误杀，打破了维杰一家人安宁的生活。为了捍卫女儿和家庭，只有小学文凭的维杰，开始了一些列瞒天过海的计划。该片是电影《误杀》的印度原版，因其缜密的悬疑推理和精巧的反转叙事被奉为悬疑犯罪片经典，并被多国翻拍。', '', '某某影院', '某某地址', null, '0', '65', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652090222915', '2022-05-09 17:57:02', '神奇动物:邓布利多之谜', 'upload/1652090202031.jpg', '科幻', '大卫·叶茨', '', '', '阿不思·邓布利多教授（裘德·洛 饰）意识到强大的黑巫师盖勒特·格林德沃（麦斯·米科尔森 饰）正试图夺取魔法世界的控制权。邓布利多了解仅凭他一人之力，将无法阻止格林德沃，于是他委托魔法动物学家纽特·斯卡曼德（埃迪·雷德梅恩 饰）带领一支精良的团队——成员包括男巫、女巫及一位勇敢的麻瓜面包师，来共同执行这项危险任务。一路上他们会遇到各式各样的神奇动物，既有老朋友，也有前所未见的神秘生物；同时他们也随时会与格林德沃不断壮大的追随者发生冲突。这次行动的风险是如此之高……邓布利多还能袖手旁观多久？', '', '影院名称4', '影院地址4', null, '0', '98', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652090270520', '2022-05-09 17:57:49', '月球陨落', 'upload/1652090240410.jpg', '科幻', '罗兰·艾默里奇', '', '', '仰望星空，⽉亮⼀向是那么的永恒。但在不久的将来，⽉球被⼀股神秘的⼒量驱使离开⽉球轨道，向我们熟知的地球⻜速⽽来，⽽在众⼈都放弃希望，地球上如世界末⽇⼀般时，⼀⽀看似乌合之众的团队决定为地球进⾏最后⼀搏，冒险为保卫⼈类最后⼀战。', '', '影院名称1', '影院地址1', null, '0', '36', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652090314845', '2022-05-09 17:58:34', '新蝙蝠侠', 'upload/1652090289216.jpg', '科幻', '马特·里夫斯', '', '', '布鲁斯·韦恩化身蝙蝠侠于哥谭市行侠仗义两年后，罪犯皆闻风丧胆，他也因此深入接触到哥谭市的阴暗面。他潜行于哥谭市腐败的政要名流关系网中，身边仅有的几个值得信赖的盟友——管家阿尔弗雷德·潘尼沃斯与詹姆斯·戈登警长。这位独行的“义警侠探”在哥谭市民心中已成为“复仇”二字最当仁不让的代名词。', '', '影院名称6', '影院地址6', null, '0', '16', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652090362451', '2022-05-09 17:59:22', '花束般的恋爱', 'upload/1652090334772.jpg', '爱情', '土井裕泰', '', '', '影片是《四重奏》导演土井裕泰，编剧坂元裕二的大银幕首部合作作品。故事讲述男女主人公因在东京明大前站错过末班电车而偶然相识，之后展开时代、时间、地点、全部的偶然都像命运一样同步的5年恋爱，展现了他们在既非大人也非孩童的时期里，迷茫前行的模样。', '', '影院名称3', '影院地址3', null, '0', '63', null, '');
INSERT INTO `dianyingxinxi` VALUES ('1652090404969', '2022-05-09 18:00:04', '长津湖之水门桥', 'upload/1652090381509.png', '剧情', '徐克', '', '', '电影《长津湖之水门桥》以抗美援朝战争第二次战役中的长津湖战役为背景，讲述了在结束了新兴里和下碣隅里的战斗之后，七连战士们又接到了更艰巨的任务……', '', '影院名称3', '影院地址3', null, '0', '33', null, '');

-- ----------------------------
-- Table structure for discussdianyingxinxi
-- ----------------------------
DROP TABLE IF EXISTS `discussdianyingxinxi`;
CREATE TABLE `discussdianyingxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` longtext NOT NULL COMMENT '评论内容',
  `reply` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1652168410677 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='电影信息评论表';

-- ----------------------------
-- Records of discussdianyingxinxi
-- ----------------------------
INSERT INTO `discussdianyingxinxi` VALUES ('111', '2022-04-13 10:26:35', '1', '1', '用户名1', '评论内容1', '回复内容1');
INSERT INTO `discussdianyingxinxi` VALUES ('112', '2022-04-13 10:26:35', '2', '2', '用户名2', '评论内容2', '回复内容2');
INSERT INTO `discussdianyingxinxi` VALUES ('113', '2022-04-13 10:26:35', '3', '3', '用户名3', '评论内容3', '回复内容3');
INSERT INTO `discussdianyingxinxi` VALUES ('114', '2022-04-13 10:26:35', '4', '4', '用户名4', '评论内容4', '回复内容4');
INSERT INTO `discussdianyingxinxi` VALUES ('115', '2022-04-13 10:26:35', '5', '5', '用户名5', '评论内容5', '回复内容5');
INSERT INTO `discussdianyingxinxi` VALUES ('116', '2022-04-13 10:26:35', '6', '6', '用户名6', '评论内容6', '回复内容6');
INSERT INTO `discussdianyingxinxi` VALUES ('1649817997510', '2022-04-13 10:46:37', '25', '1649817845523', '11', '用户要购买后才能进行评论，这里的点击次数越高，在首页的推荐排序就越靠前', '这里是回复用户的评论或者删除不良评论的地方');
INSERT INTO `discussdianyingxinxi` VALUES ('1652168410676', '2022-05-10 15:40:10', '25', '1649817845523', '11', '1', null);

-- ----------------------------
-- Table structure for discussyingshizixun
-- ----------------------------
DROP TABLE IF EXISTS `discussyingshizixun`;
CREATE TABLE `discussyingshizixun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` longtext NOT NULL COMMENT '评论内容',
  `reply` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1652168428175 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影视资讯评论表';

-- ----------------------------
-- Records of discussyingshizixun
-- ----------------------------
INSERT INTO `discussyingshizixun` VALUES ('121', '2022-04-13 10:26:35', '1', '1', '用户名1', '评论内容1', '回复内容1');
INSERT INTO `discussyingshizixun` VALUES ('122', '2022-04-13 10:26:35', '2', '2', '用户名2', '评论内容2', '回复内容2');
INSERT INTO `discussyingshizixun` VALUES ('123', '2022-04-13 10:26:35', '3', '3', '用户名3', '评论内容3', '回复内容3');
INSERT INTO `discussyingshizixun` VALUES ('124', '2022-04-13 10:26:35', '4', '4', '用户名4', '评论内容4', '回复内容4');
INSERT INTO `discussyingshizixun` VALUES ('125', '2022-04-13 10:26:35', '5', '5', '用户名5', '评论内容5', '回复内容5');
INSERT INTO `discussyingshizixun` VALUES ('126', '2022-04-13 10:26:35', '6', '6', '用户名6', '评论内容6', '回复内容6');
INSERT INTO `discussyingshizixun` VALUES ('1652167806611', '2022-05-10 15:30:05', '1649818179593', '1649817845523', '11', '1', null);
INSERT INTO `discussyingshizixun` VALUES ('1652168428174', '2022-05-10 15:40:27', '52', '1649817845523', '11', '2', null);

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '留言人id',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` longtext NOT NULL COMMENT '留言内容',
  `cpicture` varchar(200) DEFAULT NULL COMMENT '留言图片',
  `reply` longtext COMMENT '回复内容',
  `rpicture` varchar(200) DEFAULT NULL COMMENT '回复图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649817929448 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='留言反馈';

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES ('101', '2022-04-13 10:26:35', '1', '用户名1', '留言内容1', 'upload/messages_cpicture1.jpg', '回复内容1', 'upload/messages_rpicture1.jpg');
INSERT INTO `messages` VALUES ('102', '2022-04-13 10:26:35', '2', '用户名2', '留言内容2', 'upload/messages_cpicture2.jpg', '回复内容2', 'upload/messages_rpicture2.jpg');
INSERT INTO `messages` VALUES ('103', '2022-04-13 10:26:35', '3', '用户名3', '留言内容3', 'upload/messages_cpicture3.jpg', '回复内容3', 'upload/messages_rpicture3.jpg');
INSERT INTO `messages` VALUES ('104', '2022-04-13 10:26:35', '4', '用户名4', '留言内容4', 'upload/messages_cpicture4.jpg', '回复内容4', 'upload/messages_rpicture4.jpg');
INSERT INTO `messages` VALUES ('105', '2022-04-13 10:26:35', '5', '用户名5', '留言内容5', 'upload/messages_cpicture5.jpg', '回复内容5', 'upload/messages_rpicture5.jpg');
INSERT INTO `messages` VALUES ('106', '2022-04-13 10:26:35', '6', '用户名6', '留言内容6', 'upload/messages_cpicture6.jpg', '回复内容6', 'upload/messages_rpicture6.jpg');
INSERT INTO `messages` VALUES ('1649817929447', '2022-04-13 10:45:29', '1649817845523', '11', '这里是留言反馈的地方', 'upload/1649817928431.jpg', '这里是回复用户的留言反馈的地方', null);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `introduction` longtext COMMENT '简介',
  `picture` varchar(200) NOT NULL COMMENT '图片',
  `content` longtext NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649818239009 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='公告信息';

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('93', '2022-04-13 10:26:35', '挫折路上，坚持常在心间', '回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。', 'upload/news_picture3.jpg', '<p>回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?</p><p>清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。</p><p>是的，面对道途上那无情的嘲讽，面对步伐中那重复的摔跤，面对激流与硬石之间猛烈的碰撞，我们必须选择那富于阴雨，却最终见到彩虹的荆棘路。也许，经历了那暴风雨的洗礼，我们便会变得自信，幸福也随之而来。</p><p>司马迁屡遭羞辱，却依然在狱中撰写《史记》，作为一名史学家，不因王权而极度赞赏，也不因卑微而极度批判，然而他在坚持自己操守的同时，却依然要受统治阶级的阻碍，他似乎无权选择自己的本职。但是，他不顾于此，只是在面对道途的阻隔之时，他依然选择了走下去的信念。终于一部开山巨作《史记》诞生，为后人留下一份馈赠，也许在他完成毕生的杰作之时，他微微地笑了，没有什么比梦想实现更快乐的了......</p><p>	或许正如“长风破浪会有时，直挂云帆济沧海”一般，欣欣然地走向看似深渊的崎岖路，而在一番耕耘之后，便会发现这里另有一番天地。也许这就是困难与快乐的交融。</p><p>也许在形形色色的社会中，我们常能看到一份坚持，一份自信，但这里却还有一类人。这类人在暴风雨来临之际，只会闪躲，从未懂得这也是一种历炼，这何尝不是一份快乐。在阴暗的角落里，总是独自在哭，带着伤愁，看不到一点希望。</p><p>我们不能堕落于此，而要像海燕那般，在苍茫的大海上，高傲地飞翔，任何事物都无法阻挡，任何事都是幸福快乐的。</p>');
INSERT INTO `news` VALUES ('94', '2022-04-13 10:26:35', '挫折是另一个生命的开端', '当遇到挫折或失败，你是看见失败还是看见机会?挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。', 'upload/news_picture4.jpg', '<p>当遇到挫折或失败，你是看见失败还是看见机会?</p><p>挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。</p><p>人生在世，从古到今，不分天子平民，机遇虽有不同，但总不免有身陷困境或遭遇难题之处，这时候唯有通权达变，才能使人转危为安，甚至反败为胜。</p><p>大部分的人，一生当中，最痛苦的经验是失去所爱的人，其次是丢掉一份工作。其实，经得起考验的人，就算是被开除也不会惊慌，要学会面对。</p><p>	“塞翁失马，焉知非福。”人生的道路，并不是每一步都迈向成功，这就是追求的意义。我们还要认识到一点，挫折作为一种情绪状态和一种个人体验，各人的耐受性是大不相同的，有的人经历了一次次挫折，就能够坚忍不拔，百折不挠;有的人稍遇挫折便意志消沉，一蹶不振。所以，挫折感是一种主观感受，因为人的目的和需要不同，成功标准不同，所以同一种活动对于不同的人可能会造成不同的挫折感受。</p><p>凡事皆以平常心来看待，对于生命顺逆不要太执著。能够“破我执”是很高层的人生境界。</p><p>人事的艰难就是一种考验。就像—支剑要有磨刀来磨，剑才会利:一块璞玉要有粗石来磨，才会发出耀眼的光芒。我们能够做到的，只是如何减少、避免那些由于自身的原因所造成的挫折，而在遇到痛苦和挫折之后，则力求化解痛苦，争取幸福。我们要知道，痛苦和挫折是双重性的，它既是我们人生中难以完全避免的，也是我们在争取成功时，不可缺少的一种动力。因为我认为，推动我们奋斗的力量，不仅仅是对成功的渴望，还有为摆脱痛苦和挫折而进行的奋斗。</p>');
INSERT INTO `news` VALUES ('95', '2022-04-13 10:26:35', '你要去相信，没有到不了的明天', '有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。', 'upload/news_picture5.jpg', '<p>有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。</p><p>不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。</p><p>	不管你现在是在图书馆里背着怎么也看不进去的英语单词，还是你现在迷茫地看不清未来的方向不知道要往哪走。</p><p>不管你现在是在努力着去实现梦想却没能拉近与梦想的距离，还是你已经慢慢地找不到自己的梦想了。</p><p>你都要去相信，没有到不了的明天。</p><p>	有的时候你的梦想太大，别人说你的梦想根本不可能实现;有的时候你的梦想又太小，又有人说你胸无大志;有的时候你对死党说着将来要去环游世界的梦想，却换来他的不屑一顾，于是你再也不提自己的梦想;有的时候你突然说起将来要开个小店的愿望，却发现你讲述的那个人，并没有听到你在说什么。</p><p>不过又能怎么样呢，未来始终是自己的，梦想始终是自己的，没有人会来帮你实现它。</p><p>也许很多时候我们只是需要朋友的一句鼓励，一句安慰，却也得不到。但是相信我，世界上还有很多人，只是想要和你说说话。</p><p>因为我们都一样。一样的被人说成固执，一样的在追逐他们眼里根本不在意的东西。</p><p>所以，又有什么关系呢，别人始终不是你、不能懂你的心情，你又何必多去解释呢。这个世界会来阻止你，困难也会接踵而至，其实真正关键的只有自己，有没有那个倔强。</p><p>这个世界上没有不带伤的人，真正能治愈自己的，只有自己。</p>');
INSERT INTO `news` VALUES ('96', '2022-04-13 10:26:35', '离开是一种痛苦，是一种勇气，但同样也是一个考验，是一个新的开端', '无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。', 'upload/news_picture6.jpg', '<p>无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。离别的确是一种痛苦，但同样也是我们走入社会，走向新环境、新领域的一个开端，希望大家在以后新的工作岗位上能够确定自己的新起点，坚持不懈，向着更新、更高的目标前进，因为人生最美好的东西永远都在最前方!</p><p>忆往昔峥嵘岁月，看今朝潮起潮落，望未来任重而道远。作为新时代的我们，就应在失败时，能拼搏奋起，去谱写人生的辉煌。在成功时，亦能居安思危，不沉湎于一时的荣耀、鲜花和掌声中，时时刻刻怀着一颗积极寻找自己新的奶酪的心，处变不惊、成败不渝，始终踏着自己坚实的步伐，从零开始，不断向前迈进，这样才能在这风起云涌、变幻莫测的社会大潮中成为真正的弄潮儿!</p>');
INSERT INTO `news` VALUES ('1649818239008', '2022-04-13 10:50:38', '这里是发布公告信息的地方', '公告的简介', 'upload/1649818226070.jpg', '<p>公告的内容</p><p><img src=\"http://localhost:8080/springboot4y2kt/upload/1649818237457.jpg\"></p>');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) DEFAULT 'dianyingxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` float NOT NULL DEFAULT '0' COMMENT '价格/积分',
  `discountprice` float DEFAULT '0' COMMENT '折扣价格',
  `total` float NOT NULL DEFAULT '0' COMMENT '总价格/总积分',
  `discounttotal` float DEFAULT '0' COMMENT '折扣总价格',
  `type` int(11) DEFAULT '1' COMMENT '支付类型',
  `status` varchar(200) DEFAULT NULL COMMENT '状态',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) DEFAULT NULL COMMENT '收货人',
  `logistics` longtext COMMENT '物流',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `orderid` (`orderid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1652168390210 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1649817893894', '2022-04-13 10:44:53', '20224131044575845177', 'dianyingxinxi', '1649817845523', '25', '电影名称5', 'upload/dianyingxinxi_dianyingfengmian5.jpg', '2', '99.9', '99.9', '199.8', '199.8', '1', '已完成', '16,17', null, null, null);
INSERT INTO `orders` VALUES ('1649817955532', '2022-04-13 10:45:55', '202241310455935387522', 'dianyingxinxi', '1649817845523', '26', '电影名称6', 'upload/dianyingxinxi_dianyingfengmian6.jpg', '1', '99.9', '99.9', '99.9', '99.9', '1', '已退款', '20', null, null, null);
INSERT INTO `orders` VALUES ('1652167397949', '2022-05-10 15:23:17', '202251015231732294961', 'dianyingxinxi', '1649817845523', '21', '印度女孩', 'upload/1652089006925.jpeg', '3', '99.9', '99.9', '299.7', '299.7', '1', '已完成', '14,15,16', null, null, null);
INSERT INTO `orders` VALUES ('1652167838585', '2022-05-10 15:30:37', '202251015303773335299', 'dianyingxinxi', '1649817845523', '1652089577361', '坏蛋联盟', 'upload/1652089537987.jpg', '1', '38', '38', '38', '38', '1', '未支付', '32', null, null, null);
INSERT INTO `orders` VALUES ('1652168390209', '2022-05-10 15:39:49', '202251015394950872420', 'dianyingxinxi', '1649817845523', '21', '印度女孩', 'upload/1652089006925.jpeg', '1', '99.9', '99.9', '99.9', '99.9', '1', '已支付', '13', null, null, null);

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) DEFAULT NULL COMMENT '表名',
  `name` varchar(200) NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) NOT NULL COMMENT '收藏图片',
  `type` varchar(200) DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩)',
  `inteltype` varchar(200) DEFAULT NULL COMMENT '推荐类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收藏表';

-- ----------------------------
-- Records of storeup
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='token表';

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('1', '1649817845523', '11', 'yonghu', '用户', '7z6ki4uua0z74ntifxtt2jlbro7cjtow', '2022-04-13 10:44:10', '2022-05-10 16:44:13');
INSERT INTO `token` VALUES ('2', '1', 'abo', 'users', '管理员', '5we3lmyi2o096dh9qb2hfmc71eavemvx', '2022-04-13 10:47:09', '2022-05-10 16:41:20');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '123456', '管理员', '2022-04-13 10:26:35');

-- ----------------------------
-- Table structure for yingshizixun
-- ----------------------------
DROP TABLE IF EXISTS `yingshizixun`;
CREATE TABLE `yingshizixun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zixunbiaoti` varchar(200) NOT NULL COMMENT '资讯标题',
  `zixunfengmian` varchar(200) DEFAULT NULL COMMENT '资讯封面',
  `dianyingmingcheng` varchar(200) NOT NULL COMMENT '电影名称',
  `shangyingriqi` date NOT NULL COMMENT '上映日期',
  `zixunjianjie` longtext COMMENT '资讯简介',
  `zixunneirong` longtext COMMENT '资讯内容',
  `faburiqi` date DEFAULT NULL COMMENT '发布日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649818179594 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影视资讯';

-- ----------------------------
-- Records of yingshizixun
-- ----------------------------
INSERT INTO `yingshizixun` VALUES ('51', '2022-04-13 10:26:35', '资讯标题1', 'upload/yingshizixun_zixunfengmian1.jpg', '电影名称1', '2022-04-13', '资讯简介1', '资讯内容1', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('52', '2022-04-13 10:26:35', '资讯标题2', 'upload/yingshizixun_zixunfengmian2.jpg', '电影名称2', '2022-04-13', '资讯简介2', '资讯内容2', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('53', '2022-04-13 10:26:35', '资讯标题3', 'upload/yingshizixun_zixunfengmian3.jpg', '电影名称3', '2022-04-13', '资讯简介3', '资讯内容3', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('54', '2022-04-13 10:26:35', '资讯标题4', 'upload/yingshizixun_zixunfengmian4.jpg', '电影名称4', '2022-04-13', '资讯简介4', '资讯内容4', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('55', '2022-04-13 10:26:35', '资讯标题5', 'upload/yingshizixun_zixunfengmian5.jpg', '电影名称5', '2022-04-13', '资讯简介5', '资讯内容5', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('56', '2022-04-13 10:26:35', '资讯标题6', 'upload/yingshizixun_zixunfengmian6.jpg', '电影名称6', '2022-04-13', '资讯简介6', '资讯内容6', '2022-04-13');
INSERT INTO `yingshizixun` VALUES ('1649818179593', '2022-04-13 10:49:39', '某某电影资讯', 'upload/1649818158476.png', '某某电影', '2022-04-14', '这里输入资讯的简介', '<p>这里输入资讯的内容</p><p><img src=\"http://localhost:8080/springboot4y2kt/upload/1649818178325.jpg\"></p>', '2022-04-13');

-- ----------------------------
-- Table structure for yingyuanxinxi
-- ----------------------------
DROP TABLE IF EXISTS `yingyuanxinxi`;
CREATE TABLE `yingyuanxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yingyuanmingcheng` varchar(200) NOT NULL COMMENT '影院名称',
  `yingyuandizhi` varchar(200) NOT NULL COMMENT '影院地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649818058208 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影院信息';

-- ----------------------------
-- Records of yingyuanxinxi
-- ----------------------------
INSERT INTO `yingyuanxinxi` VALUES ('41', '2022-04-13 10:26:35', '影院名称1', '影院地址1');
INSERT INTO `yingyuanxinxi` VALUES ('42', '2022-04-13 10:26:35', '影院名称2', '影院地址2');
INSERT INTO `yingyuanxinxi` VALUES ('43', '2022-04-13 10:26:35', '影院名称3', '影院地址3');
INSERT INTO `yingyuanxinxi` VALUES ('44', '2022-04-13 10:26:35', '影院名称4', '影院地址4');
INSERT INTO `yingyuanxinxi` VALUES ('45', '2022-04-13 10:26:35', '影院名称5', '影院地址5');
INSERT INTO `yingyuanxinxi` VALUES ('46', '2022-04-13 10:26:35', '影院名称6', '影院地址6');
INSERT INTO `yingyuanxinxi` VALUES ('1649818058207', '2022-04-13 10:47:37', '某某影院', '某某地址');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) NOT NULL COMMENT '账号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xingming` varchar(200) NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `youxiang` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `shoujihaoma` varchar(200) DEFAULT NULL COMMENT '手机号码',
  `xiangpian` varchar(200) DEFAULT NULL COMMENT '相片',
  `money` float DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `zhanghao` (`zhanghao`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1649817845524 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES ('11', '2022-04-13 10:26:35', '账号1', '123456', '姓名1', '男', '773890001@qq.com', '13823888881', 'upload/yonghu_xiangpian1.jpg', '100');
INSERT INTO `yonghu` VALUES ('12', '2022-04-13 10:26:35', '账号2', '123456', '姓名2', '男', '773890002@qq.com', '13823888882', 'upload/yonghu_xiangpian2.jpg', '100');
INSERT INTO `yonghu` VALUES ('13', '2022-04-13 10:26:35', '账号3', '123456', '姓名3', '男', '773890003@qq.com', '13823888883', 'upload/yonghu_xiangpian3.jpg', '100');
INSERT INTO `yonghu` VALUES ('14', '2022-04-13 10:26:35', '账号4', '123456', '姓名4', '男', '773890004@qq.com', '13823888884', 'upload/yonghu_xiangpian4.jpg', '100');
INSERT INTO `yonghu` VALUES ('15', '2022-04-13 10:26:35', '账号5', '123456', '姓名5', '男', '773890005@qq.com', '13823888885', 'upload/yonghu_xiangpian5.jpg', '100');
INSERT INTO `yonghu` VALUES ('16', '2022-04-13 10:26:35', '账号6', '123456', '姓名6', '男', '773890006@qq.com', '13823888886', 'upload/yonghu_xiangpian6.jpg', '100');
INSERT INTO `yonghu` VALUES ('1649817845523', '2022-04-13 10:44:05', '11', '11', '张三', '男', '131@163.com', '13111111111', 'upload/1649817861824.jpg', '0.6');
