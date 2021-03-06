-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.21 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 recruitment_service 的数据库结构
CREATE DATABASE IF NOT EXISTS `recruitment_service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recruitment_service`;


-- 导出  表 recruitment_service.business_position 结构
CREATE TABLE IF NOT EXISTS `business_position` (
  `ID` int(4) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CompanyID` int(12) NOT NULL COMMENT '公司ID',
  `Address` varchar(50) NOT NULL COMMENT '地址',
  `Province` int(4) NOT NULL COMMENT '省',
  `City` int(4) NOT NULL COMMENT '市',
  `County` int(4) DEFAULT NULL COMMENT '县',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `FK_province_city` (`Province`),
  KEY `FK_city_city` (`City`),
  KEY `FK_county_city` (`County`),
  KEY `BusinessID` (`ID`),
  KEY `FK_companyid_company` (`CompanyID`),
  CONSTRAINT `FK_city_city` FOREIGN KEY (`City`) REFERENCES `city_number` (`CityID`),
  CONSTRAINT `FK_companyid_company` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`ID`),
  CONSTRAINT `FK_province_city` FOREIGN KEY (`Province`) REFERENCES `provincial_number` (`ProID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='存储公司位置信息。梁艳飞于2015年10月15日编写';

-- 正在导出表  recruitment_service.business_position 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `business_position` DISABLE KEYS */;
INSERT INTO `business_position` (`ID`, `CompanyID`, `Address`, `Province`, `City`, `County`, `Usable`) VALUES
	(2, 5, '河北省石家庄市裕华区珠峰大街287号', 5, 6, NULL, 1),
	(3, 3, '河北省石家庄市裕华区珠峰大街286号', 5, 6, NULL, 1),
	(4, 4, '河北省石家庄市裕华区珠峰大街285号', 5, 6, NULL, 1),
	(5, 2, '河北省石家庄市裕华区珠峰大街284号', 5, 6, NULL, 1),
	(1, 1, '河北省石家庄市裕华区珠峰大街288号', 5, 6, NULL, 1),
	(6, 6, '北京市朝阳区', 1, 1, NULL, 1),
	(7, 7, '河北省唐山市', 5, 7, NULL, 1);
/*!40000 ALTER TABLE `business_position` ENABLE KEYS */;


-- 导出  表 recruitment_service.city_number 结构
CREATE TABLE IF NOT EXISTS `city_number` (
  `CityID` int(4) NOT NULL AUTO_INCREMENT COMMENT '城市标识',
  `City` varchar(15) NOT NULL DEFAULT '0' COMMENT '城市名称',
  `ParentCity` int(4) NOT NULL DEFAULT '0' COMMENT '所属上级城市',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `CityID` (`CityID`),
  KEY `FK1_parentCity_proID` (`ParentCity`),
  CONSTRAINT `FK1_parentCity_proID` FOREIGN KEY (`ParentCity`) REFERENCES `provincial_number` (`ProID`)
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8 COMMENT='存储城市编号。梁艳飞于2015年10月15日编写';

-- 正在导出表  recruitment_service.city_number 的数据：~278 rows (大约)
/*!40000 ALTER TABLE `city_number` DISABLE KEYS */;
INSERT INTO `city_number` (`CityID`, `City`, `ParentCity`, `Usable`) VALUES
	(1, '北京市', 1, 1),
	(2, '北京市', 1, 1),
	(3, '天津市', 2, 1),
	(4, '上海市', 3, 1),
	(5, '重庆市', 4, 1),
	(6, '石家庄市', 5, 1),
	(7, '唐山市', 5, 1),
	(8, '秦皇岛市', 5, 1),
	(9, '邯郸市', 5, 1),
	(10, '邢台市', 5, 1),
	(11, '保定市', 5, 1),
	(12, '张家口市', 5, 1),
	(13, '承德市', 5, 1),
	(14, '沧州市', 5, 1),
	(15, '廊坊市', 5, 1),
	(16, '衡水市', 5, 1),
	(17, '太原市', 6, 1),
	(18, '大同市', 6, 1),
	(19, '阳泉市', 6, 1),
	(20, '长治市', 6, 1),
	(21, '晋城市', 6, 1),
	(22, '朔州市', 6, 1),
	(23, '晋中市', 6, 1),
	(24, '运城市', 6, 1),
	(25, '忻州市', 6, 1),
	(26, '临汾市', 6, 1),
	(27, '吕梁市', 6, 1),
	(28, '台北市', 7, 1),
	(29, '高雄市', 7, 1),
	(30, '基隆市', 7, 1),
	(31, '台中市', 7, 1),
	(32, '台南市', 7, 1),
	(33, '新竹市', 7, 1),
	(34, '嘉义市', 7, 1),
	(35, '台北县', 7, 1),
	(36, '宜兰县', 7, 1),
	(37, '桃园县', 7, 1),
	(38, '新竹县', 7, 1),
	(39, '苗栗县', 7, 1),
	(40, '台中县', 7, 1),
	(41, '彰化县', 7, 1),
	(42, '南投县', 7, 1),
	(43, '云林县', 7, 1),
	(44, '嘉义县', 7, 1),
	(45, '台南县', 7, 1),
	(46, '高雄县', 7, 1),
	(47, '屏东县', 7, 1),
	(48, '澎湖县', 7, 1),
	(49, '台东县', 7, 1),
	(50, '花莲县', 7, 1),
	(51, '沈阳市', 8, 1),
	(52, '大连市', 8, 1),
	(53, '鞍山市', 8, 1),
	(54, '抚顺市', 8, 1),
	(55, '本溪市', 8, 1),
	(56, '丹东市', 8, 1),
	(57, '锦州市', 8, 1),
	(58, '营口市', 8, 1),
	(59, '阜新市', 8, 1),
	(60, '辽阳市', 8, 1),
	(61, '盘锦市', 8, 1),
	(62, '铁岭市', 8, 1),
	(63, '朝阳市', 8, 1),
	(64, '葫芦岛市', 8, 1),
	(65, '长春市', 9, 1),
	(66, '吉林市', 9, 1),
	(67, '四平市', 9, 1),
	(68, '辽源市', 9, 1),
	(69, '通化市', 9, 1),
	(70, '白山市', 9, 1),
	(71, '松原市', 9, 1),
	(72, '白城市', 9, 1),
	(73, '延边朝鲜族自治州', 9, 1),
	(74, '哈尔滨市', 10, 1),
	(75, '齐齐哈尔市', 10, 1),
	(76, '鹤 岗 市', 10, 1),
	(77, '双鸭山市', 10, 1),
	(78, '鸡 西 市', 10, 1),
	(79, '大 庆 市', 10, 1),
	(80, '伊 春 市', 10, 1),
	(81, '牡丹江市', 10, 1),
	(82, '佳木斯市', 10, 1),
	(83, '七台河市', 10, 1),
	(84, '黑 河 市', 10, 1),
	(85, '绥 化 市', 10, 1),
	(86, '大兴安岭地区', 10, 1),
	(87, '南京市', 11, 1),
	(88, '无锡市', 11, 1),
	(89, '徐州市', 11, 1),
	(90, '常州市', 11, 1),
	(91, '苏州市', 11, 1),
	(92, '南通市', 11, 1),
	(93, '连云港市', 11, 1),
	(94, '淮安市', 11, 1),
	(95, '盐城市', 11, 1),
	(96, '扬州市', 11, 1),
	(97, '镇江市', 11, 1),
	(98, '泰州市', 11, 1),
	(99, '宿迁市', 11, 1),
	(100, '杭州市', 12, 1),
	(101, '宁波市', 12, 1),
	(102, '温州市', 12, 1),
	(103, '嘉兴市', 12, 1),
	(104, '湖州市', 12, 1),
	(105, '绍兴市', 12, 1),
	(106, '金华市', 12, 1),
	(107, '衢州市', 12, 1),
	(108, '舟山市', 12, 1),
	(109, '台州市', 12, 1),
	(110, '丽水市', 12, 1),
	(111, '合肥市', 13, 1),
	(112, '芜湖市', 13, 1),
	(113, '蚌埠市', 13, 1),
	(114, '淮南市', 13, 1),
	(115, '马鞍山市', 13, 1),
	(116, '淮北市', 13, 1),
	(117, '铜陵市', 13, 1),
	(118, '安庆市', 13, 1),
	(119, '黄山市', 13, 1),
	(120, '滁州市', 13, 1),
	(121, '阜阳市', 13, 1),
	(122, '宿州市', 13, 1),
	(123, '巢湖市', 13, 1),
	(124, '六安市', 13, 1),
	(125, '亳州市', 13, 1),
	(126, '池州市', 13, 1),
	(127, '宣城市', 13, 1),
	(128, '福州市', 14, 1),
	(129, '厦门市', 14, 1),
	(130, '莆田市', 14, 1),
	(131, '三明市', 14, 1),
	(132, '泉州市', 14, 1),
	(133, '漳州市', 14, 1),
	(134, '南平市', 14, 1),
	(135, '龙岩市', 14, 1),
	(136, '宁德市', 14, 1),
	(137, '南昌市', 15, 1),
	(138, '景德镇市', 15, 1),
	(139, '萍乡市', 15, 1),
	(140, '九江市', 15, 1),
	(141, '新余市', 15, 1),
	(142, '鹰潭市', 15, 1),
	(143, '赣州市', 15, 1),
	(144, '吉安市', 15, 1),
	(145, '宜春市', 15, 1),
	(146, '抚州市', 15, 1),
	(147, '上饶市', 15, 1),
	(148, '济南市', 16, 1),
	(149, '青岛市', 16, 1),
	(150, '淄博市', 16, 1),
	(151, '枣庄市', 16, 1),
	(152, '东营市', 16, 1),
	(153, '烟台市', 16, 1),
	(154, '潍坊市', 16, 1),
	(155, '济宁市', 16, 1),
	(156, '泰安市', 16, 1),
	(157, '威海市', 16, 1),
	(158, '日照市', 16, 1),
	(159, '莱芜市', 16, 1),
	(160, '临沂市', 16, 1),
	(161, '德州市', 16, 1),
	(162, '聊城市', 16, 1),
	(163, '滨州市', 16, 1),
	(164, '菏泽市', 16, 1),
	(165, '郑州市', 17, 1),
	(166, '开封市', 17, 1),
	(167, '洛阳市', 17, 1),
	(168, '平顶山市', 17, 1),
	(169, '安阳市', 17, 1),
	(170, '鹤壁市', 17, 1),
	(171, '新乡市', 17, 1),
	(172, '焦作市', 17, 1),
	(173, '濮阳市', 17, 1),
	(174, '许昌市', 17, 1),
	(175, '漯河市', 17, 1),
	(176, '三门峡市', 17, 1),
	(177, '南阳市', 17, 1),
	(178, '商丘市', 17, 1),
	(179, '信阳市', 17, 1),
	(180, '周口市', 17, 1),
	(181, '驻马店市', 17, 1),
	(182, '济源市', 17, 1),
	(183, '武汉市', 18, 1),
	(184, '黄石市', 18, 1),
	(185, '十堰市', 18, 1),
	(186, '荆州市', 18, 1),
	(187, '宜昌市', 18, 1),
	(188, '襄樊市', 18, 1),
	(189, '鄂州市', 18, 1),
	(190, '荆门市', 18, 1),
	(191, '孝感市', 18, 1),
	(192, '黄冈市', 18, 1),
	(193, '咸宁市', 18, 1),
	(194, '随州市', 18, 1),
	(195, '仙桃市', 18, 1),
	(196, '天门市', 18, 1),
	(197, '潜江市', 18, 1),
	(198, '神农架林区', 18, 1),
	(199, '恩施土家族苗族自治州', 18, 1),
	(200, '长沙市', 19, 1),
	(201, '株洲市', 19, 1),
	(202, '湘潭市', 19, 1),
	(203, '衡阳市', 19, 1),
	(204, '邵阳市', 19, 1),
	(205, '岳阳市', 19, 1),
	(206, '常德市', 19, 1),
	(207, '张家界市', 19, 1),
	(208, '益阳市', 19, 1),
	(209, '郴州市', 19, 1),
	(210, '永州市', 19, 1),
	(211, '怀化市', 19, 1),
	(212, '娄底市', 19, 1),
	(213, '湘西土家族苗族自治州', 19, 1),
	(214, '广州市', 20, 1),
	(215, '深圳市', 20, 1),
	(216, '珠海市', 20, 1),
	(217, '汕头市', 20, 1),
	(218, '韶关市', 20, 1),
	(219, '佛山市', 20, 1),
	(220, '江门市', 20, 1),
	(221, '湛江市', 20, 1),
	(222, '茂名市', 20, 1),
	(223, '肇庆市', 20, 1),
	(224, '惠州市', 20, 1),
	(225, '梅州市', 20, 1),
	(226, '汕尾市', 20, 1),
	(227, '河源市', 20, 1),
	(228, '阳江市', 20, 1),
	(229, '清远市', 20, 1),
	(230, '东莞市', 20, 1),
	(231, '中山市', 20, 1),
	(232, '潮州市', 20, 1),
	(233, '揭阳市', 20, 1),
	(234, '云浮市', 20, 1),
	(235, '兰州市', 21, 1),
	(236, '金昌市', 21, 1),
	(237, '白银市', 21, 1),
	(238, '天水市', 21, 1),
	(239, '嘉峪关市', 21, 1),
	(240, '武威市', 21, 1),
	(241, '张掖市', 21, 1),
	(242, '平凉市', 21, 1),
	(243, '酒泉市', 21, 1),
	(244, '庆阳市', 21, 1),
	(245, '定西市', 21, 1),
	(246, '陇南市', 21, 1),
	(247, '临夏回族自治州', 21, 1),
	(248, '甘南藏族自治州', 21, 1),
	(249, '成都市', 22, 1),
	(250, '自贡市', 22, 1),
	(251, '攀枝花市', 22, 1),
	(252, '泸州市', 22, 1),
	(253, '德阳市', 22, 1),
	(254, '绵阳市', 22, 1),
	(255, '广元市', 22, 1),
	(256, '遂宁市', 22, 1),
	(257, '内江市', 22, 1),
	(258, '乐山市', 22, 1),
	(259, '南充市', 22, 1),
	(260, '眉山市', 22, 1),
	(261, '宜宾市', 22, 1),
	(262, '广安市', 22, 1),
	(263, '达州市', 22, 1),
	(264, '雅安市', 22, 1),
	(265, '巴中市', 22, 1),
	(266, '资阳市', 22, 1),
	(267, '阿坝藏族羌族自治州', 22, 1),
	(268, '甘孜藏族自治州', 22, 1),
	(269, '凉山彝族自治州', 22, 1),
	(270, '贵阳市', 23, 1),
	(271, '六盘水市', 23, 1),
	(272, '遵义市', 23, 1),
	(273, '安顺市', 23, 1),
	(274, '铜仁地区', 23, 1),
	(275, '毕节地区', 23, 1),
	(276, '黔西南布依族苗族自治州', 23, 1),
	(277, '黔东南苗族侗族自治州', 23, 1),
	(278, '黔南布依族苗族自治州', 23, 1);
/*!40000 ALTER TABLE `city_number` ENABLE KEYS */;


-- 导出  表 recruitment_service.company 结构
CREATE TABLE IF NOT EXISTS `company` (
  `ID` int(4) NOT NULL AUTO_INCREMENT COMMENT '招聘公司id',
  `Company` varchar(50) NOT NULL DEFAULT '0' COMMENT '公司名称',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='招聘公司信息（非正式，测试用）。梁艳飞于2015年10月15日编写';

-- 正在导出表  recruitment_service.company 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`ID`, `Company`, `Usable`) VALUES
	(1, '测试数据1', 1),
	(2, '测试数据2', 1),
	(3, '测试数据3', 1),
	(4, '测试数据4', 1),
	(6, '测试数据6', 1),
	(7, '测试数据7', 1),
	(9, '测试数据9', 1),
	(5, '测试数据5', 1),
	(10, '测试数据10', 1),
	(11, '测试数据11', 1),
	(8, '测试数据8', 1);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- 导出  表 recruitment_service.provincial_number 结构
CREATE TABLE IF NOT EXISTS `provincial_number` (
  `ProID` int(4) DEFAULT NULL COMMENT '市标识',
  `Provincial` varchar(10) NOT NULL DEFAULT '0' COMMENT '市名称',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `CityID` (`ProID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储市编号。梁艳飞于2015年10月15日编写';

-- 正在导出表  recruitment_service.provincial_number 的数据：~34 rows (大约)
/*!40000 ALTER TABLE `provincial_number` DISABLE KEYS */;
INSERT INTO `provincial_number` (`ProID`, `Provincial`, `Usable`) VALUES
	(1, '北京市', 1),
	(2, '天津市', 1),
	(3, '上海市', 1),
	(4, '重庆市', 1),
	(5, '河北省', 1),
	(6, '山西省', 1),
	(7, '台湾省', 1),
	(8, '辽宁省', 1),
	(9, '吉林省', 1),
	(10, '黑龙江省', 1),
	(11, '江苏省', 1),
	(12, '浙江省', 1),
	(13, '安徽省', 1),
	(14, '福建省', 1),
	(15, '江西省', 1),
	(16, '山东省', 1),
	(17, '河南省', 1),
	(18, '湖北省', 1),
	(19, '湖南省', 1),
	(20, '广东省', 1),
	(21, '甘肃省', 1),
	(22, '四川省', 1),
	(23, '贵州省', 1),
	(24, '海南省', 1),
	(25, '云南省', 1),
	(26, '青海省', 1),
	(27, '陕西省', 1),
	(28, '广西壮族自治区', 1),
	(29, '西藏自治区', 1),
	(30, '宁夏回族自治区', 1),
	(31, '新疆维吾尔自治区', 1),
	(32, '内蒙古自治区', 1),
	(33, '澳门特别行政区', 1),
	(34, '香港特别行政区', 1);
/*!40000 ALTER TABLE `provincial_number` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
