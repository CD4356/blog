/*
Navicat MySQL Data Transfer

Source Server         : 远程MySQL
Source Server Version : 50726
Source Host           : 106.15.201.150:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-03-30 17:53:50
*/

CREATE DATABASE blog DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE blog;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------

DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `blog_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '文章自增id',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `description` varchar(1000) DEFAULT NULL COMMENT '该篇博客的描述',
  `first_picture` varchar(255) DEFAULT NULL COMMENT '文章首图',
  `flag` varchar(25) DEFAULT NULL COMMENT '博文标志：原创、转载、翻译',
  `views` int(10) DEFAULT '0' COMMENT '浏览次数',
  `appreciation` int(1) NOT NULL DEFAULT '0' COMMENT '是否开启打赏功能：0 不开启、1 开启',
  `share_statement` int(1) NOT NULL DEFAULT '0' COMMENT '是否开启版权声明：0 不开启、1 开启',
  `comment_status` int(1) NOT NULL DEFAULT '0' COMMENT '是否开启评论功能：0 不开启，1 开启',
  `published` int(1) NOT NULL DEFAULT '0' COMMENT '博客状态：0 草稿、1 发布',
  `recommend` int(1) NOT NULL DEFAULT '0' COMMENT '是否推荐：0(false)不推荐、1(true)推荐',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `user_id` int(10) DEFAULT NULL COMMENT '作者',
  `type_id` int(10) DEFAULT NULL COMMENT '博客分类',
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag` (
  `blog_id` int(10) NOT NULL,
  `tag_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL COMMENT '评论者的昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '评论者的邮箱',
  `avatar` varchar(1000) DEFAULT NULL COMMENT '评论者的头像',
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '发表评论的时间',
  `blog_id` int(10) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL COMMENT '一级评论者的id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `tag_id` int(10) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(100) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `type_id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(55) DEFAULT NULL COMMENT '昵称',
  `username` varchar(55) NOT NULL COMMENT '用户名',
  `password` varchar(55) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `user_img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
