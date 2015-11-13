/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-11-13 18:21:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_article`
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `author` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '作者',
  `cover` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '封面',
  `summary` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '摘要',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '状态',
  `category_id` bigint(32) NOT NULL COMMENT '分类',
  `create_id` bigint(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(32) NOT NULL COMMENT '最后更新人',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('2', '标题11', 'Jason11', '封面11', '摘要11', '内容111', '0', '3', '1', '2015-11-13 12:00:17', '1', '2015-11-13 16:36:07');
INSERT INTO `cms_article` VALUES ('3', 'test2', 'test2', 'test2', null, 'test2', '0', '2', '1', '2015-11-13 16:36:20', '1', '2015-11-13 16:36:20');

-- ----------------------------
-- Table structure for `cms_category`
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `create_id` bigint(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(32) NOT NULL COMMENT '最后更新人',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章分类';

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES ('1', '要闻', '1', '2015-11-13 16:02:03', '1', '2015-11-13 16:03:26');
INSERT INTO `cms_category` VALUES ('2', '娱乐', '1', '2015-11-13 16:02:16', '1', '2015-11-13 16:02:16');
INSERT INTO `cms_category` VALUES ('3', '房产', '1', '2015-11-13 16:03:06', '1', '2015-11-13 16:03:06');
INSERT INTO `cms_category` VALUES ('4', '体育', '1', '2015-11-13 16:03:16', '1', '2015-11-13 16:03:16');

-- ----------------------------
-- Table structure for `cms_channel`
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(96) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '栏目名称',
  `path` varchar(96) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '栏目路径',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '栏目排序',
  `father_id` int(11) DEFAULT NULL COMMENT '栏目外键',
  `is_display` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `template_id` bigint(32) NOT NULL COMMENT '模板Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='频道';

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('48', '联系我们', 'contact', '24', '52', '1', '11');
INSERT INTO `cms_channel` VALUES ('49', '新闻', 'news', '10', null, '1', '8');
INSERT INTO `cms_channel` VALUES ('50', '成功案例', 'case', '40', null, '1', '13');
INSERT INTO `cms_channel` VALUES ('51', '解决方案', 'solution', '30', null, '1', '12');
INSERT INTO `cms_channel` VALUES ('52', '关于我们', 'about', '20', null, '1', '9');
INSERT INTO `cms_channel` VALUES ('53', '公司介绍', 'profile', '22', '52', '1', '10');

-- ----------------------------
-- Table structure for `cms_template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_template`;
CREATE TABLE `cms_template` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '模板名称',
  `file_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件名称',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `physical_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '物理路径',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模板';

-- ----------------------------
-- Records of cms_template
-- ----------------------------
INSERT INTO `cms_template` VALUES ('1', '首页模板', 'welcome', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				<ul>\r\n					<li>\r\n						<a href=\"/admin/login\">\r\n							<div class=\"tu tu1\"><b></b></div>\r\n							<h2>管理系统</h2>\r\n						</a>\r\n					</li>\r\n					<li>\r\n						<a href=\"javascript:void(0);\">\r\n							<div class=\"tu tu2\"><b></b></div>\r\n							<h2>学校统计分析</h2>\r\n						</a>\r\n					</li>\r\n					<li>\r\n						<a href=\"javascript:void(0);\">\r\n							<div class=\"tu tu3\"><b></b></div>\r\n							<h2>家长报告查询</h2>\r\n						</a>\r\n					</li>\r\n				</ul>\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', 'WEB-INF/front/template/welcome.ftl', '0', '2015-11-12 10:55:43');
INSERT INTO `cms_template` VALUES ('2', '底部模板', 'footer', '<div id=\"footer\">\r\n			<div class=\"wrap\">\r\n			  	<p>Copyright © 2015 Jason 版权所有.</p>\r\n	 	 	 	 <#include \"/WEB-INF/front/template/channel.ftl\" />\r\n			  	<p>粤ICP备14050083号</p>\r\n			</div>\r\n		</div>', 'WEB-INF/front/template/footer.ftl', '100', '2015-11-12 15:51:04');
INSERT INTO `cms_template` VALUES ('3', '头部模板', 'navigation', '<div id=\"header\" class=\"bluebg\">\r\n			<div class=\"wrap\">\r\n				<h1 class=\"logo fl\">SMS</h1>\r\n			</div>\r\n		</div>', 'WEB-INF/front/template/navigation.ftl', '100', '2015-11-12 10:53:11');
INSERT INTO `cms_template` VALUES ('4', '分页模板', 'paging', '<#assign totalPages=\"${(page.totalPages gt 2000)?string(\'2000\',page.totalPages)}\" >\r\n<div class=\"global-page\">\r\n<#if totalPages?number gt 1>\r\n	<#if (page.pageNo?number) gt 1>\r\n		<a class=\"previous-page\" href=\"${pagingUrl}/page/${((page.pageNo?number-1) gt 0)?string(page.pageNo?number-1,page.pageNo?number)}/${page.orderBy}/${page.order}\">上一页</a>\r\n	</#if>\r\n	<#if page.pageNo?number == 1>\r\n		<a class=\"previous-page\" href=\"${pagingUrl}/page/1/${page.orderBy}/${page.order}\">1</a>&nbsp;\r\n	<#else>\r\n		<a href=\"${pagingUrl }/page/1/${page.orderBy}/${page.order}\">1</a>&nbsp;\r\n	</#if>\r\n	<#if totalPages?number gt 0>\r\n		<#if page.pageNo?number lt 10>\r\n			<#if page.pageNo?number gte 2>\r\n			<#list 2..page.pageNo?number as i>\r\n				<#if page.pageNo?number==i>\r\n					<a class=\"previous-page\" href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;\r\n				<#else>\r\n					<a href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;  \r\n				</#if>\r\n			</#list>\r\n			</#if>\r\n		<#else>\r\n			  ...&nbsp;  \r\n			  <#list page.pageNo?number-4..page.pageNo?number as i>\r\n				<#if page.pageNo?number==i>\r\n					<a class=\"previous-page\" href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;\r\n				<#else>\r\n					<a href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;  \r\n				</#if>\r\n			  </#list>\r\n		</#if>\r\n		<#if page.pageNo?number gte totalPages?number-4 || totalPages?number-4 lte 0>\r\n			<#if page.pageNo?number+1 lte totalPages?number>\r\n			<#list page.pageNo?number+1..totalPages?number as i>\r\n				<a href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;  \r\n				<#if page.pageNo?number == i>\r\n					<a class=\"previous-page\" href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;\r\n				</#if>\r\n			</#list>\r\n			</#if>\r\n		<#else>\r\n			<#list page.nextPage..page.pageNo?number+4 as i>\r\n				<#if page.pageNo?number == i>\r\n					<a class=\"previous-page\"  href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;\r\n				<#else>\r\n					<a href=\"${pagingUrl }/page/${i}/${page.orderBy}/${page.order}\">${i}</a>&nbsp;  \r\n				</#if>\r\n			</#list>\r\n			  ...&nbsp;  \r\n			  <a href=\"${pagingUrl }/page/${totalPages}/${page.orderBy}/${page.order}\">${totalPages}</a>&nbsp;  \r\n		</#if>\r\n		<#if page.pageNo?number lt totalPages?number>\r\n			<a class=\"next-page\" href=\"${pagingUrl }/page/${((page.nextPage) lt totalPages?number)?string(page.nextPage,totalPages?number)}/${page.orderBy}/${page.order}\">下一页</a>\r\n		</#if>\r\n	</#if>\r\n	\r\n</#if>\r\n</div>\r\n', 'WEB-INF/front/template/paging.ftl', '100', '2015-11-12 10:48:57');
INSERT INTO `cms_template` VALUES ('5', '标签模板', 'taglibs', '<#assign ctx=\"${springMacroRequestContext.contextPath}\">\r\n<#setting url_escaping_charset=\'utf-8\'> ', 'WEB-INF/front/template/taglibs.ftl', '100', '2015-11-12 10:48:51');
INSERT INTO `cms_template` VALUES ('6', '公共header模板', 'common-header', '<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"${ctx}/resources/images/favicon.ico\"/>\r\n\r\n<link href=\"${ctx}/resources/css/common.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<link href=\"${ctx}/resources/js/artDialog/ui-dialog.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<link href=\"${ctx}/resources/css/index.css\"  type=\"text/css\" rel=\"stylesheet\"/>\r\n\r\n<script src=\"${ctx}/resources/js/jquery-1.8.3.min.js\" type=\"text/javascript\"></script>\r\n<script src=\"${ctx}/resources/js/artDialog/dialog-min.js\" type=\"text/javascript\"></script>', 'WEB-INF/front/template/common-header.ftl', '100', '2015-11-12 15:01:29');
INSERT INTO `cms_template` VALUES ('7', '栏目/频道模板', 'channel', '<p>栏目：<a href=\"${ctx}/\">首页</a>\r\n	 <@cms_channel_list path=\'man\';channels>\r\n	 	 <#list channels as channels>\r\n		 	 <a href=\"${ctx}${channels.path}\">${channels.name}</a>\r\n		 	 <#if channels.children?size gt 0>\r\n		 	 	 [<#list channels.children as channel>\r\n		 	 	 <a href=\"${ctx}${channel.path}\">${channel.name}</a>\r\n		 	 	 </#list>]\r\n		 	 </#if>\r\n		 </#list>\r\n	</@cms_channel_list>\r\n</p>', 'WEB-INF/front/template/channel.ftl', '100', '2015-11-12 15:49:44');
INSERT INTO `cms_template` VALUES ('8', '新闻列表模板', 'news', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				新闻列表\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', 'WEB-INF/front/template/news.ftl', '0', '2015-11-12 15:53:33');
INSERT INTO `cms_template` VALUES ('9', '关于我们模板', 'about', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				关于我们\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', '/WEB-INF/front/template/about.ftl', '0', '2015-11-12 15:53:20');
INSERT INTO `cms_template` VALUES ('10', '公司介绍模板', 'profile', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				公司介绍\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', '/WEB-INF/front/template/profile.ftl', '0', '2015-11-12 15:53:11');
INSERT INTO `cms_template` VALUES ('11', '联系我们模板', 'contact', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n			联系我们\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', '/WEB-INF/front/template/contact.ftl', '0', '2015-11-12 15:53:02');
INSERT INTO `cms_template` VALUES ('12', '解决方案模板', 'solution', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				解决方案\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', '/WEB-INF/front/template/solution.ftl', '0', '2015-11-12 15:52:40');
INSERT INTO `cms_template` VALUES ('13', '成功案例模板', 'case', '<#include \"/WEB-INF/front/template/taglibs.ftl\" />\r\n<!DOCTYPE html>\r\n<html>\r\n	<head>\r\n		<meta charset=\"utf-8\" />\r\n		<title>SMS</title>\r\n		<meta name=\"keywords\" content=\"SMS\" />\r\n		<meta name=\"description\" content=\"SMS\" />\r\n		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n	 	 <#include \"/WEB-INF/front/template/common-header.ftl\" />\r\n	</head>\r\n	<body>\r\n		<!-- Header Start -->\r\n		<#include \"/WEB-INF/front/template/navigation.ftl\" />\r\n		<!-- Header end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Main Start -->\r\n		<div id=\"main\">\r\n			\r\n			<div class=\"navbar wrap\">\r\n				成功案例\r\n			</div>\r\n\r\n		</div>\r\n		<!-- Main end -->\r\n		<div class=\"cl\"></div>\r\n		<!-- Footer Start -->\r\n	 	 <#include \"/WEB-INF/front/template/footer.ftl\" />\r\n		<!-- Footer end -->\r\n	</body>\r\n</html>', '/WEB-INF/front/template/case.ftl', '0', '2015-11-12 15:52:29');

-- ----------------------------
-- Table structure for `security_authority`
-- ----------------------------
DROP TABLE IF EXISTS `security_authority`;
CREATE TABLE `security_authority` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permission` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `permission` (`permission`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of security_authority
-- ----------------------------
INSERT INTO `security_authority` VALUES ('15', '修改密码', 'admin:changepwd');

-- ----------------------------
-- Table structure for `security_role`
-- ----------------------------
DROP TABLE IF EXISTS `security_role`;
CREATE TABLE `security_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `show_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of security_role
-- ----------------------------
INSERT INTO `security_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `security_role` VALUES ('2', 'test', '测试人员');

-- ----------------------------
-- Table structure for `security_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `security_role_authority`;
CREATE TABLE `security_role_authority` (
  `role_id` bigint(32) NOT NULL,
  `authority_id` bigint(32) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `authority_id` (`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of security_role_authority
-- ----------------------------
INSERT INTO `security_role_authority` VALUES ('1', '15');

-- ----------------------------
-- Table structure for `security_user`
-- ----------------------------
DROP TABLE IF EXISTS `security_user`;
CREATE TABLE `security_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `role_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `account_non_locked` (`account_non_locked`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of security_user
-- ----------------------------
INSERT INTO `security_user` VALUES ('1', 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', '', '1');
INSERT INTO `security_user` VALUES ('2', 'test', '05a671c66aefea124cc08b76ea6d30bb', '', '2');

-- ----------------------------
-- Table structure for `security_user_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `security_user_login_log`;
CREATE TABLE `security_user_login_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `ip` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of security_user_login_log
-- ----------------------------
INSERT INTO `security_user_login_log` VALUES ('1', '1', '2015-11-10 11:35:43', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('2', '1', '2015-11-11 12:59:18', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('3', '1', '2015-11-11 16:00:36', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('4', '1', '2015-11-11 16:02:23', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('5', '1', '2015-11-11 16:33:11', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('6', '1', '2015-11-12 10:29:57', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('7', '1', '2015-11-12 11:00:55', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('8', '1', '2015-11-12 11:10:02', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('9', '1', '2015-11-12 12:00:11', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('10', '1', '2015-11-12 14:54:40', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('11', '1', '2015-11-12 15:11:32', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('12', '1', '2015-11-12 15:31:57', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('13', '1', '2015-11-12 16:11:29', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('14', '1', '2015-11-12 16:23:53', '192.168.0.191');
INSERT INTO `security_user_login_log` VALUES ('15', '1', '2015-11-13 11:05:07', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('16', '1', '2015-11-13 11:38:16', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('17', '1', '2015-11-13 11:53:06', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('18', '1', '2015-11-13 15:41:36', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('19', '1', '2015-11-13 15:58:56', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('20', '1', '2015-11-13 16:01:50', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('21', '1', '2015-11-13 16:13:16', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('22', '1', '2015-11-13 16:20:06', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('23', '1', '2015-11-13 16:24:21', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('24', '1', '2015-11-13 16:32:06', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('25', '1', '2015-11-13 16:34:40', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('26', '1', '2015-11-13 16:42:38', '127.0.0.1');
INSERT INTO `security_user_login_log` VALUES ('27', '1', '2015-11-13 17:52:34', '127.0.0.1');

-- ----------------------------
-- Table structure for `security_user_runas`
-- ----------------------------
DROP TABLE IF EXISTS `security_user_runas`;
CREATE TABLE `security_user_runas` (
  `from_user_id` bigint(20) NOT NULL DEFAULT '0',
  `to_user_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`from_user_id`,`to_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of security_user_runas
-- ----------------------------
