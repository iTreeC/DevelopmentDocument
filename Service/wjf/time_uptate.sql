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

-- 导出 time_update 的数据库结构
DROP DATABASE IF EXISTS `time_update`;
CREATE DATABASE IF NOT EXISTS `time_update` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `time_update`;


-- 导出  表 time_update.t_rule 结构
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE IF NOT EXISTS `t_rule` (
  `RULE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则id',
  `S_YEARMONDAY` varchar(255) DEFAULT NULL COMMENT '开始时间的“年月日”',
  `E_YEARMONDAY` varchar(255) DEFAULT NULL COMMENT '结束时间的“年月日”',
  `S_HOURMIN` varchar(255) DEFAULT NULL COMMENT '开始时间的“时分”',
  `E_HOURMIN` varchar(255) DEFAULT NULL COMMENT '结束时间的“时分”',
  `S_WEEK` varchar(255) DEFAULT NULL COMMENT '开始时间的“周”',
  `E_WEEK` varchar(255) DEFAULT NULL COMMENT '结束时间的“周”',
  PRIMARY KEY (`RULE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='规则表';

-- 正在导出表  time_update.t_rule 的数据：~3 rows (大约)
DELETE FROM `t_rule`;
/*!40000 ALTER TABLE `t_rule` DISABLE KEYS */;
INSERT INTO `t_rule` (`RULE_ID`, `S_YEARMONDAY`, `E_YEARMONDAY`, `S_HOURMIN`, `E_HOURMIN`, `S_WEEK`, `E_WEEK`) VALUES
	(1, '2015-01-01', '2015-12-31', '00:00', '23:59', '1', '7'),
	(2, '2013-01-01', '2015-02-03', '08:00', '20:00', '6', '6'),
	(3, '2005-02-15', '2015-10-11', '14:30', '17:30', '2', '2');
/*!40000 ALTER TABLE `t_rule` ENABLE KEYS */;


-- 导出  表 time_update.t_rule_shop 结构
DROP TABLE IF EXISTS `t_rule_shop`;
CREATE TABLE IF NOT EXISTS `t_rule_shop` (
  `RUlE_ID` int(11) NOT NULL COMMENT '规则id',
  `SHOP_ID` int(11) NOT NULL COMMENT '商家id',
  PRIMARY KEY (`RUlE_ID`,`SHOP_ID`),
  KEY `FK_1g2yhuj4900fh4925ni8wmoyd` (`SHOP_ID`),
  KEY `FK_2dl9wkq0dape7o5etn9wscfxb` (`RUlE_ID`),
  CONSTRAINT `FK_r_rs` FOREIGN KEY (`RUlE_ID`) REFERENCES `t_rule` (`RULE_ID`),
  CONSTRAINT `FK_s_rs` FOREIGN KEY (`SHOP_ID`) REFERENCES `t_shop` (`SHOP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中间表';

-- 正在导出表  time_update.t_rule_shop 的数据：~6 rows (大约)
DELETE FROM `t_rule_shop`;
/*!40000 ALTER TABLE `t_rule_shop` DISABLE KEYS */;
INSERT INTO `t_rule_shop` (`RUlE_ID`, `SHOP_ID`) VALUES
	(1, 1),
	(2, 1),
	(1, 2),
	(3, 2),
	(2, 3),
	(3, 3);
/*!40000 ALTER TABLE `t_rule_shop` ENABLE KEYS */;


-- 导出  表 time_update.t_shop 结构
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE IF NOT EXISTS `t_shop` (
  `SHOP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `SHOP_NAME` varchar(50) DEFAULT NULL COMMENT '商家名称',
  PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商家表';

-- 正在导出表  time_update.t_shop 的数据：~3 rows (大约)
DELETE FROM `t_shop`;
/*!40000 ALTER TABLE `t_shop` DISABLE KEYS */;
INSERT INTO `t_shop` (`SHOP_ID`, `SHOP_NAME`) VALUES
	(1, '商家一'),
	(2, '商家二'),
	(3, '商家三');
/*!40000 ALTER TABLE `t_shop` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
