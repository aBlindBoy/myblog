/*
Navicat MySQL Data Transfer

Source Server         : mysql55
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-01-31 14:50:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article_info`
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `article_title` varchar(500) NOT NULL,
  `article_content` varchar(10000) NOT NULL,
  `article_img` varchar(1000) NOT NULL,
  `article_recom` varchar(10) NOT NULL,
  `article_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `article_mark` varchar(10) NOT NULL,
  `page_view` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`article_id`),
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`category_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`category_id`) REFERENCES `category_info` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES ('1', '1', '1', 'mybatis逆向工程', '什么是逆向工程\nMyBatis的一个主要的特点就是需要程序员自己编写sql，那么如果表太多的话，难免会很麻烦，所以mybatis官方提供了一个逆向工程', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '1', '2018-10-24 10:48:13', '1', '1');
INSERT INTO `article_info` VALUES ('21', '1', '4', '构建微服务：Spring boot 入门篇', '<h1><strong><strong><strong>什么是</strong></strong>spring boot</strong></h1>\r\n\r\n<p>Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。用我的话来理解，就是spring boot其实不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，spring boot整合了所有的框架（不知道这样比喻是否合适）。</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h1><strong><strong><strong>使用</strong></strong>spring boot<strong><strong>有什么好处</strong></strong></strong></h1>\r\n\r\n<p>其实就是简单、快速、方便！平时如果我们需要搭建一个spring web项目的时候需要怎么做呢？</p>\r\n\r\n<p>1）配置web.xml，加载spring和spring mvc</p>\r\n\r\n<p>2）配置数据库连接、配置spring事务</p>\r\n\r\n<p>3）配置加载配置文件的读取，开启注解</p>\r\n\r\n<p>4）配置日志文件</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>...</p>\r\n\r\n<p>配置完成之后部署tomcat 调试</p>\r\n\r\n<p>...</p>\r\n\r\n<p>现在非常流行微服务，如果我这个项目仅仅只是需要发送一个邮件，如果我的项目仅仅是生产一个积分；我都需要这样折腾一遍!</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>但是如果使用spring boot呢？</p>\r\n\r\n<p>很简单，我仅仅只需要非常少的几个配置就可以迅速方便的搭建起来一套web项目或者是构建一个微服务！</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>使用sping boot到底有多爽，用下面这幅图来表达</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-10-31 22:39:38', '1', '14');
INSERT INTO `article_info` VALUES ('22', '1', '4', 'IDEA下从零开始搭建SpringBoot工程', '<p>SpringBoot的具体介绍可以参看其他网上介绍，这里就不多说了，就这几天的学习，个人理解，简而言之：&nbsp;<br />\r\n（1）它是Spring的升级版，Spring容器能做到的事情，它都能做到，而且更简便，从配置形式上来说，SpringBoot完全抛弃了繁琐的XML文件配置方式，而是替代性地用注解方式来实现，虽然本质来说，是差不多的（类似包扫描，注解扫描，类加载之类）。&nbsp;<br />\r\n（2）SpringBoot集成的插件更多，从而使用很多服务，都只是引入一个依赖，几个注解和Java类就可以用了，具体的参考相关手册。&nbsp;<br />\r\n（3）在Web应用开发这一块，之前的应用一般来说是打包成war包，再发布到相关服务器容器下（例如Tomcat），虽然SpringBoot也可以这么做，但在SpringBoot下更常见的形式是将SpringBoot应用打包成可执行jar包文件。之所以这么做，源于你可以直接将SpringBoot应用看成是一个Java Application，其Web应用可以没有webapp目录（更不用说web.xml了），它推荐使用html页面，并将其作为静态资源使用。&nbsp;<br />\r\n下面具体记录一下，如何在IDEA下从零开始，一步步搭建SpringBoot Web应用，这里采用的是maven作依赖管理，新手起步，有任何疑问，请参考SpringBoot官网。&nbsp;<br />\r\n需要说明的是SpringBoot依赖的JDK版本为1.8及以上。&nbsp;<br />\r\n（1）File-&gt;new,选择maven，创建一个空项目，直接next.&nbsp;<br />\r\n---------------------&nbsp;<br />\r\n作者：slanf&nbsp;<br />\r\n来源：CSDN&nbsp;<br />\r\n原文：https://blog.csdn.net/u013248535/article/details/55100979&nbsp;<br />\r\n版权声明：本文为博主原创文章，转载请附上博文链接！</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-10-31 22:40:59', '1', '1');
INSERT INTO `article_info` VALUES ('23', '1', '4', 'layer 实现弹窗提交信息', '<p>版权声明：本文为博主原创文章，未经博主允许不得转载。&nbsp;&nbsp; &nbsp;https://blog.csdn.net/antony9118/article/details/53575597<br />\r\nlayer是非常好用的弹窗插件，具体参见官方文档&nbsp;<br />\r\n点击这里进入。官方有很多示例，使用方法也非常简单，引入官方下载的css和js就可以了。但是有时候，弹窗提醒并不能满足我们的所有要求。例如，在奖品界面，希望点击&ldquo;编辑奖品&rdquo;的时候，能弹出对话框让我们来填写信息，如下图所示：</p>\r\n\r\n<p>如何用简洁美观的 layer 实现这一功能呢？官方并没有给我们提供示例，那就自己动手写吧。下面把代码贴出来。</p>\r\n\r\n<p>给&ldquo;添加奖品&rdquo;按钮加上单击触发confirmUpdateAward方法<br />\r\n---------------------&nbsp;<br />\r\n作者：橙子wj&nbsp;<br />\r\n来源：CSDN&nbsp;<br />\r\n原文：https://blog.csdn.net/antony9118/article/details/53575597&nbsp;<br />\r\n版权声明：本文为博主原创文章，转载请附上博文链接！</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '1', '2018-10-31 22:47:08', '1', '1');
INSERT INTO `article_info` VALUES ('24', '1', '2', 'layer 实现弹窗提交信息', '<p>layer是非常好用的弹窗插件，具体参见官方文档&nbsp;<br />\r\n<a href=\"http://layer.layui.com/\" rel=\"nofollow\" target=\"_blank\">点击这里进入</a>。官方有很多示例，使用方法也非常简单，引入官方下载的css和js就可以了。但是有时候，弹窗提醒并不能满足我们的所有要求。例如，在奖品界面，希望点击&ldquo;编辑奖品&rdquo;的时候，能弹出对话框让我们来填写信息，如下图所示：</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-10-31 23:08:58', '1', '1');
INSERT INTO `article_info` VALUES ('25', '1', '1', 'mybatis逆向工程', 'mybatis逆向工程', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '1', '2018-11-08 13:07:26', '1', '1');
INSERT INTO `article_info` VALUES ('26', '1', '3', '用心伴着爱情走', '<p>爱情就像是一块水晶，干干净净，透彻清凉，却也会反射出艳丽的五光十色。它是坚韧的，又是<a href=\"http://www.duwenzhang.com/huati/cuiruo/index1.html\" style=\"color:#333333; font-family:Microsoft YaHei; font-size:14px; font-style:normal; font-variant:normal; font-weight:400; letter-spacing:normal; orphans:2; text-align:left; text-decoration:none; text-transform:none; -webkit-text-stroke-width:0px; white-space:normal; word-spacing:0px\">脆弱的</a>，既可以为所欲为的欣赏，又需要小心翼翼的呵护&hellip;&hellip;</p>\r\n\r\n<p>当你<a href=\"http://www.duwenzhang.com/huati/yongyou/index1.html\" style=\"color:#333333; text-decoration:none\">拥有</a>爱情是，你<a href=\"http://www.duwenzhang.com/huati/xingfu/index1.html\" style=\"color:#333333; text-decoration:none\">是幸福的</a>，你拥有的是一块能折射自己的水晶，这无价之宝将带给你<a href=\"http://www.duwenzhang.com/huati/zeren/index1.html\" style=\"color:#333333; text-decoration:none\">责任</a>感和力量，也带给你<a href=\"http://www.duwenzhang.com/huati/yongqi/index1.html\" style=\"color:#333333; text-decoration:none\">勇气</a>和新生。</p>\r\n\r\n<p>　　真正的爱情是&ldquo;死生契阔，与子成悦；执子之手，与子偕老&rdquo;。你的<a href=\"http://www.duwenzhang.com/wenzhang/aiqingwenzhang/\" style=\"color:#333333; text-decoration:none\">爱情</a>是哪样的呢？</p>\r\n\r\n<p>　　真正的爱也许不仅仅是<a href=\"http://www.duwenzhang.com/huati/langman/index1.html\" style=\"color:#333333; text-decoration:none\">浪漫</a>的相遇，热烈的吸引，醉人的蜜语和澎湃的激情&mdash;&mdash;也许更应该是深广的<a href=\"http://www.duwenzhang.com/huati/kuanrong/index1.html\" style=\"color:#333333; text-decoration:none\">宽容</a>，细微的疼惜，淡远的关爱和无声的表达&hellip;</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-11-04 17:56:56', '1', '4');
INSERT INTO `article_info` VALUES ('28', '1', '1', 'Java编程之java日期与字符串转化', '<pre>\r\nimport java.util.Date;\r\nimport java.text.SimpleDateFormat;\r\npublic class Demo {\r\npublic static void main(String[] args) {\r\n　　Date now=new Date();\r\n　　SimpleDateFormat f=newSimpleDateFormat(&quot;今天是&quot;+&quot;yyyy年MM月dd日 E kk点mm分&quot;);\r\n　　System.out.println(f.format(now));\r\n\r\n　　f=new SimpleDateFormat(&quot;a hh点mm分ss秒&quot;);\r\n　　System.out.println(f.format(now));\r\n　　}\r\n，细微的疼惜，淡远的关爱和无声的表达', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-11-04 20:23:16', '1', '1');
INSERT INTO `article_info` VALUES ('30', '1', '4', 'Java编程之java日期与字符串转化', '<pre>\r\nimport java.util.Date;\r\nimport java.text.SimpleDateFormat;\r\npublic class Demo {\r\npublic static void main(String[] args) {\r\n　　Date now=new Date();\r\n　　SimpleDateFormat f=newSimpleDateFormat(&quot;今天是&quot;+&quot;yyyy年MM月dd日 E kk点mm分&quot;);\r\n　　System.out.println(f.format(now));\r\n\r\n　　f=new SimpleDateFormat(&quot;a hh点mm分ss秒&quot;);\r\n　　System.out.println(f.format(now));\r\n　　}\r\n，细微的疼惜，淡远的关爱和无声的表达', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-11-04 20:29:00', '1', '1');
INSERT INTO `article_info` VALUES ('31', '1', '1', '【solr专题之三】Solr常见异常', '<p>解决方法：<br />\r\n在使用Tomcat部署Solr后，Collection1的地址为：http://ip:8080/solr/#/collection1，但使用SolrJ进行索引的时候，应该使用http://ip:8080/solr/collection1，即无中间的#号。</p>\r\n\r\n<p>即正确代码为：</p>\r\n\r\n<p>String serverUrl = (args != null &amp;&amp; args.length &gt; 0) ? args[0]<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;: &quot;http://ip:8080/solr/collection1&quot;;<br />\r\n错误代码为：</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-11-04 21:00:27', '1', '5');
INSERT INTO `article_info` VALUES ('32', '1', '2', '别再说...', '<p>别再说，地铁太挤工作不易，你可曾见过火车上肩扛手提，许多我们叫<a href=\"http://www.duwenzhang.com/huati/fuqin/index1.html\">爸爸</a>的人拖着比自己都高的行李奔赴生计！</p>\r\n\r\n<p>　　别再说，压力太大<a href=\"http://www.duwenzhang.com/wenzhang/shenghuosuibi/\">生活</a>艰辛，你可曾知道有人已经年逾古稀依然起早贪黑，只为解决一家温饱！</p>\r\n\r\n<p>　　别再说，灯红酒绿索然无味，你可曾想过穿梭城市的民工也许连一瓶矿泉水都狠不下心来买！</p>\r\n\r\n<p>　　别再说，写不完的作业背不完的公式让你身心疲惫，你可曾听过遥远的山村有赤脚的<a href=\"http://www.duwenzhang.com/huati/haizi/index1.html\">孩子</a>趴在窗前偷听<a href=\"http://www.duwenzhang.com/huati/laoshi/index1.html\">老师</a>讲课！</p>\r\n\r\n<p>　　别再说，世界那么大我想去看看，你可曾知道多少人即使行将就木，依然没有出过方圆十里的村落！</p>\r\n\r\n<p>　　别再说，昨夜王者精神不振，你可知道有个<a href=\"http://www.duwenzhang.com/huati/muqin/index1.html\">母亲</a>孤灯夜下穿针引线为千里之外的孩子添补衣裳，只因一句天冷了！</p>\r\n\r\n<p>　　别再说，空调房的你都吼不住炎炎夏日，那是你没见过烫人的黄土混合<a href=\"http://www.duwenzhang.com/huati/yanlei/index1.html\">眼泪</a>的汗流浃背，有个<a href=\"http://www.duwenzhang.com/huati/fuqin/index1.html\">父亲</a>顶着烈日给他的孩子挣学费！</p>', 'http://139.159.192.231:8081/fileserver/file/title.jpg', '-1', '2018-11-04 21:01:58', '1', '7');

-- ----------------------------
-- Table structure for `category_info`
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(300) NOT NULL,
  `category_alias` varchar(200) NOT NULL,
  `category_desc` varchar(2000) NOT NULL,
  `category_mark` varchar(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES ('1', '后端技术', 'Java', 'Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程', '1');
INSERT INTO `category_info` VALUES ('2', '前端技术', 'Web', 'web（World Wide Web）即全球广域网，也称为万维网，它是一种基于超文本和HTTP的、全球性的、动态交互的、跨平台的分布式图形信息系统。是建立在Internet上的一种网络服务，为浏览者在Internet上查找和浏览信息提供了图形化的、易于访问的直观界面，其中的文档及超级链接将Internet上的信息节点组织成一个互为关联的网状结构', '1');
INSERT INTO `category_info` VALUES ('3', 'Mysql', 'Mysql', 'MySQL是一个关系型数据库管理系统，由瑞典MySQL AB 公司开发，目前属于 Oracle 旗下产品。MySQL 是最流行的关系型数据库管理系统之一，在 WEB 应用方面，MySQL是最好的 RDBMS (Relational Database Management System，关系数据库管理系统) 应用软件。', '1');
INSERT INTO `category_info` VALUES ('4', 'SpringBoot', 'SpringBoot', 'Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。', '1');
INSERT INTO `category_info` VALUES ('5', 'oracle', 'Oracle', 'oracleoracleoracleoracle', '1');

-- ----------------------------
-- Table structure for `message_info`
-- ----------------------------
DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_content` varchar(4000) NOT NULL,
  `message_time` datetime NOT NULL,
  `message_name` varchar(200) NOT NULL,
  `message_mark` varchar(10) NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_info
-- ----------------------------
INSERT INTO `message_info` VALUES ('1', '感谢分享', '2018-10-27 16:59:26', '张三', '1');
INSERT INTO `message_info` VALUES ('2', '博主nb', '2018-10-26 17:00:01', '李四', '-1');
INSERT INTO `message_info` VALUES ('3', '感谢博主', '2018-10-30 22:38:42', '天虹', '-1');
INSERT INTO `message_info` VALUES ('4', '大佬牛逼,可以教教我嘛?', '2018-10-30 22:47:01', '康康', '-1');
INSERT INTO `message_info` VALUES ('5', 'dalao', '2018-11-05 15:36:20', '康康', '1');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_phone` varchar(100) NOT NULL,
  `user_account` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_mark` varchar(10) NOT NULL COMMENT '-1 ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '李伟', '18312274344', 'liwei', 'admin', '1');
INSERT INTO `user_info` VALUES ('2', '老储', '18312274333', 'laochu', 'admin', '1');
INSERT INTO `user_info` VALUES ('3', '康康', '18312274343', 'kangkang', 'admin', '1');
