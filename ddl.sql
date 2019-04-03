-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- ------------------------------------------------------
-- Server version	5.6.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_auth_resource`
--

DROP TABLE IF EXISTS `t_auth_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_resource` (
  `id` int(8) NOT NULL COMMENT '标识符 8位数字组成 \n第1位: 资源类型。9: 系统资源；1: 业务资源\n第2~3位: ENDPOINT。\n第4~6位: 资源分类标识。\n第7~8位: 动词标识。',
  `verb` varchar(16) NOT NULL COMMENT '操作动词',
  `type` varchar(1) DEFAULT NULL COMMENT '资源类型 1: 业务资源 8: 域管理资源 9: 超级资源',
  `uri_pattern` varchar(256) NOT NULL COMMENT 'URI模式',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_auth_role`
--

DROP TABLE IF EXISTS `t_auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识符',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `status` varchar(1) NOT NULL COMMENT '1: 启用; 0: 禁用',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_auth_role_resource_rel`
--

DROP TABLE IF EXISTS `t_auth_role_resource_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_role_resource_rel` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `resource_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_auth_user`
--

DROP TABLE IF EXISTS `t_auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识符',
  `login_name` varchar(128) NOT NULL COMMENT '登录名',
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `type` varchar(1) NOT NULL DEFAULT '1' COMMENT '1: 普通用户; 9: 超级用户',
  `status` varchar(1) NOT NULL COMMENT '1: 启用; 0: 禁用',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '最近一次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_LOGIN_NAME` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_auth_user_role_rel`
--

DROP TABLE IF EXISTS `t_auth_user_role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_auth_user_role_rel` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-03  9:19:19
