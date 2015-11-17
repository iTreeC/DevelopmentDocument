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

-- 导出 geographic_service 的数据库结构
CREATE DATABASE IF NOT EXISTS `geographic_service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `geographic_service`;


-- 导出  表 geographic_service.business_position 结构
CREATE TABLE IF NOT EXISTS `business_position` (
  `BusinessID` int(12) NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `Location` varchar(9) NOT NULL DEFAULT '0' COMMENT '经纬度标识',
  `Lng` double(10,6) NOT NULL DEFAULT '0.000000' COMMENT '商家精度',
  `Lat` double(10,6) NOT NULL DEFAULT '0.000000' COMMENT '商家纬度',
  `Businessaddress` varchar(48) NOT NULL DEFAULT '0.000000' COMMENT '商家地址',
  `Rule` int(4) NOT NULL COMMENT '商家规则',
  `Province` int(4) NOT NULL COMMENT '省',
  `City` int(4) NOT NULL COMMENT '市',
  `County` int(4) NOT NULL COMMENT '县',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `BusinessID` (`BusinessID`),
  KEY `FK_province_city` (`Province`),
  KEY `FK_city_city` (`City`),
  KEY `FK_county_city` (`County`),
  KEY `FK_rule_rule` (`Rule`),
  CONSTRAINT `FK_rule_rule` FOREIGN KEY (`Rule`) REFERENCES `rule_position` (`RuleID`),
  CONSTRAINT `FK_city_city` FOREIGN KEY (`City`) REFERENCES `city_number` (`CityID`),
  CONSTRAINT `FK_county_city` FOREIGN KEY (`County`) REFERENCES `city_number` (`CityID`),
  CONSTRAINT `FK_province_city` FOREIGN KEY (`Province`) REFERENCES `city_number` (`CityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储商家实体';

-- 正在导出表  geographic_service.business_position 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `business_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `business_position` ENABLE KEYS */;


-- 导出  表 geographic_service.city_number 结构
CREATE TABLE IF NOT EXISTS `city_number` (
  `CityID` int(4) NOT NULL AUTO_INCREMENT COMMENT '城市标识',
  `City` varchar(10) NOT NULL DEFAULT '0' COMMENT '城市名称',
  `ParentCity` int(4) NOT NULL DEFAULT '0' COMMENT '所属上级城市',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `CityID` (`CityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储城市编号';

-- 正在导出表  geographic_service.city_number 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `city_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `city_number` ENABLE KEYS */;


-- 导出  表 geographic_service.rule_position 结构
CREATE TABLE IF NOT EXISTS `rule_position` (
  `RuleID` int(4) NOT NULL AUTO_INCREMENT COMMENT '规则标识',
  `Rule` varchar(60) NOT NULL COMMENT '规则的具体内容',
  `Usable` int(2) NOT NULL DEFAULT '1' COMMENT '是否可用标识',
  KEY `RuleID` (`RuleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储规则对象';

-- 正在导出表  geographic_service.rule_position 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `rule_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `rule_position` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
