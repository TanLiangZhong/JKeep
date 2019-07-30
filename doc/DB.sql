/*
 Navicat Premium Data Transfer

 Source Server         : my-docker
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : jkeep

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 30/07/2019 11:22:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_branch
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch`;
CREATE TABLE `sys_branch`  (
  `branch_id` bigint(20) NOT NULL COMMENT '用户Id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '昵称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `d_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `index_sys_branch_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典Id',
  `tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典key值',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_lst
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_lst`;
CREATE TABLE `sys_dict_lst`  (
  `dict_lst_id` bigint(20) NOT NULL COMMENT '字典列表Id',
  `dict_id` bigint(20) NULL DEFAULT NULL COMMENT '字典Id',
  `tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典key值.',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dict_lst_id`) USING BTREE,
  INDEX `index_sys_dict_lst_dict_id`(`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_element
-- ----------------------------
DROP TABLE IF EXISTS `sys_element`;
CREATE TABLE `sys_element`  (
  `element_id` bigint(20) NOT NULL COMMENT '页面元素Id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级Id(顶级为0)',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint(2) NULL DEFAULT 0 COMMENT '类型(0:其他 1:按钮 2:链接)',
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态(0-停用 1-启用)',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型(GIT POST PUT DELETE)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态(0:无效 1:有效)',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `d_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`element_id`) USING BTREE,
  INDEX `index_sys_element_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '页面元素表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL COMMENT '角色关系表主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '角色Id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单',
  `modules` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `time_consuming` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `gmt_created` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单Id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级主键(顶级为0）',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `href` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `show` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(1显示,0不显示)',
  `order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `d_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `index_sys_element_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色Id',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `describe` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态(0-停用 1-启用)',
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `d_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_link`;
CREATE TABLE `sys_role_link`  (
  `role_link_id` bigint(20) NOT NULL COMMENT '角色关系表主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色Id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单',
  `element_id` bigint(20) NULL DEFAULT NULL COMMENT '页面元素Id',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '类型( 0:菜单 1:页面元素 )',
  `gmt_created` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`role_link_id`) USING BTREE,
  INDEX `index_sys_role_link_role_id`(`role_id`) USING BTREE,
  INDEX `index_sys_role_link_menu_id`(`menu_id`) USING BTREE,
  INDEX `index_sys_role_link_element_id`(`element_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_sequence
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence`  (
  `seq_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `current_value` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`seq_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES ('seq_sys_branch', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_dict', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_dict_lst', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_element', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_log', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_menu', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_role', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_role_link', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_user', 0);
INSERT INTO `sys_sequence` VALUES ('seq_sys_user_role', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `branch_id` tinyint(20) NULL DEFAULT NULL COMMENT '机构Id',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `status` tinyint(1) NULL DEFAULT NULL,
  `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `d_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `userName`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'jkeep', 'JKeep', '$2a$10$QV1akWhRXjmXfnO.C7ycJeljadSNfRlUxLnZpPQYOGEnAq3Q569yW', 'liangzhong.tan@outlook.com', '15111213963', 1, '2019-07-30 03:22:05', 1, '2019-07-30 11:21:10', 1, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_role_id` bigint(20) NOT NULL COMMENT '用户角色关系表主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `role_id` bigint(20) NOT NULL COMMENT '角色Id',
  `gmt_created` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `index_sys_user_role_user_id`(`user_id`) USING BTREE,
  INDEX `index_sys_user_role_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表, 赋予用户的角色' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
