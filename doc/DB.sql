/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 139.196.160.176:3306
 Source Schema         : jkeep

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 09/09/2019 16:08:21
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
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
                               `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 (1-启用 0-禁用)',
                               `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
                               `gmt_modified` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
                               `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
                               `d_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标记 (1-正常 0-删除)',
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
                                `type` tinyint(1) NULL DEFAULT 0 COMMENT '类型(0:其他 1:按钮 2:链接)',
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
                                `d_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标记 (1-正常 0-删除)',
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
                             `show` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(1-显示,0-不显示)',
                             `order` int(11) NULL DEFAULT 0 COMMENT '排序',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
                             `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
                             `d_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标记 (1-正常 0-删除)',
                             PRIMARY KEY (`menu_id`) USING BTREE,
                             INDEX `index_sys_element_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, 'far fas fa-cog', 'system-manage', 1, 0, '', '2019-09-09 06:23:06', 1, '2019-09-09 06:23:06', 1, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', NULL, NULL, 'system-manage-user', 1, 0, '', '2019-09-09 02:48:54', 1, '2019-09-09 02:48:54', 1, 0);
INSERT INTO `sys_menu` VALUES (3, 1, '菜单管理', NULL, NULL, 'system-manage-menu', 1, 1, NULL, '2019-09-09 02:52:43', 1, '2019-09-09 02:52:43', 1, 0);
INSERT INTO `sys_menu` VALUES (4, 1, '角色管理', NULL, NULL, 'system-manage-role', 1, 1, NULL, '2019-09-09 02:52:43', 1, '2019-09-09 02:52:43', 1, 0);
INSERT INTO `sys_menu` VALUES (5, 1, '字典管理', NULL, NULL, 'system-manage-dict', 1, 1, NULL, '2019-09-09 02:52:43', 1, '2019-09-09 02:52:43', 1, 0);
INSERT INTO `sys_menu` VALUES (6, 1, '系统日志', '/system/log/page.html', NULL, 'system-manage-log', 1, 1, NULL, '2019-09-09 03:33:49', 1, '2019-09-09 03:33:49', 1, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` bigint(20) NOT NULL COMMENT '角色Id',
                             `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
                             `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
                             `describe` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                             `status` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态(0-停用 1-启用)',
                             `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
                             `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
                             `d_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标记 (1-正常 0-删除)',
                             PRIMARY KEY (`role_id`) USING BTREE,
                             UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'system-manage', NULL, '1', '2019-09-09 08:02:59', 1, '2019-09-09 08:02:59', 1, 0);

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
-- Records of sys_role_link
-- ----------------------------
INSERT INTO `sys_role_link` VALUES (1, 1, 1, NULL, 0, '2019-09-09 10:56:21');
INSERT INTO `sys_role_link` VALUES (2, 1, 2, NULL, 0, '2019-09-09 10:56:21');
INSERT INTO `sys_role_link` VALUES (3, 1, 3, NULL, 0, '2019-09-09 10:56:21');
INSERT INTO `sys_role_link` VALUES (4, 1, 4, NULL, 0, '2019-09-09 10:56:21');
INSERT INTO `sys_role_link` VALUES (5, 1, 5, NULL, 0, '2019-09-09 10:56:21');
INSERT INTO `sys_role_link` VALUES (6, 1, 6, NULL, 0, '2019-09-09 10:56:21');

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
INSERT INTO `sys_sequence` VALUES ('seq_sys_menu', 6);
INSERT INTO `sys_sequence` VALUES ('seq_sys_role', 1);
INSERT INTO `sys_sequence` VALUES ('seq_sys_role_link', 6);
INSERT INTO `sys_sequence` VALUES ('seq_sys_user', 2);
INSERT INTO `sys_sequence` VALUES ('seq_sys_user_role', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` bigint(20) NOT NULL COMMENT '用户Id',
                             `branch_id` bigint(20) NULL DEFAULT NULL COMMENT '机构Id',
                             `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
                             `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
                             `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                             `email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
                             `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态值(1-正常, 0-禁用)',
                             `gmt_created` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
                             `gmt_modified` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
                             `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
                             `d_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标记 (1-正常 0-删除)',
                             PRIMARY KEY (`user_id`) USING BTREE,
                             UNIQUE INDEX `userName`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, '以梅佐酒', '/plugins/adminLTE/img/user3-128x128.jpg', 'Jkeep', '$2a$10$OkhbU26I868z0DSiza3Zi.cC5ibvaHesuSthE1x0AQhPqgHmGCRve', 'liangzhong.tan@outlook.com', '15111213963', 0, '2019-09-09 07:56:52', 1, '2019-07-30 11:21:10', 1, 0);
INSERT INTO `sys_user` VALUES (2, 1, '玉藻前', '/plugins/adminLTE/img/user3-128x128.jpg', 'admin', '$10$OkhbU26I868z0DSiza3Zi.cC5ibvaHesuSthE1x0AQhPqgHmGCRve', 'liangzhong.tan@outlook.com', '15111213963', 0, '2019-09-09 08:05:27', 1, '2019-09-09 16:05:10', 1, 0);

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

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2019-09-09 11:23:54');

SET FOREIGN_KEY_CHECKS = 1;
