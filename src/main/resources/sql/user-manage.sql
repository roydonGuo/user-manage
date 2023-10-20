/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023 (8.0.23)
 Source Host           : localhost:3306
 Source Schema         : user-manage

 Target Server Type    : MySQL
 Target Server Version : 80023 (8.0.23)
 File Encoding         : 65001

 Date: 20/10/2023 21:30:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布人',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '青哥哥的文章标题', '# 我是青哥哥\n## 我是青哥哥2号\n\n::: hljs-center\n\n***++~~==我是那个哥哥==~~++***\n\n:::\n\n> 我是青哥哥的引用\n\n我是B站：程序员青戈\n\n[百度](https://www.baidu.com)\n\n```java\nclass Hello {\n  public static void main(String[] args) {\n    System.out.pringln(\"Hello 青哥哥\");\n  }\n}\n\n```\n\n![搜狗截图20220129174103.png](http://localhost:9090/file/8567a00d2bf740e0a63794baf600cec3.png)\n\n\n', '程序员青戈', '2022-03-20 19:22:58');
INSERT INTO `article` VALUES (2, '青哥哥文章2号', '青哥哥文章2号\n\n青哥哥文章2号\n\n青哥哥文章2号\n\n![QQ图片20220307194920.png](http://localhost:9090/file/5e40a867acd74d1f90b0ac9a765823e5.png)', '程序员青戈', '2022-05-22 19:22:58');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '楼栋',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单元',
  `pid` int NULL DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, '1栋', '1栋', NULL);
INSERT INTO `building` VALUES (2, '2栋', '2栋', NULL);
INSERT INTO `building` VALUES (3, '1单元', '1单元', 1);
INSERT INTO `building` VALUES (4, '2单元', '2单元', 1);
INSERT INTO `building` VALUES (5, '1单元', '1单元', 2);
INSERT INTO `building` VALUES (6, '2单元', '2单元', 2);
INSERT INTO `building` VALUES (7, '101', '101', 3);
INSERT INTO `building` VALUES (8, '201', '201', 3);
INSERT INTO `building` VALUES (9, '101', '101', 4);
INSERT INTO `building` VALUES (10, '201', '201', 4);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `score` int NULL DEFAULT NULL COMMENT '学分',
  `times` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '是否开课',
  `teacher_id` int NULL DEFAULT NULL COMMENT '授课老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '大学物理', 10, '40', 0, 17);
INSERT INTO `course` VALUES (2, '高等数学', 10, '45', NULL, 16);
INSERT INTO `course` VALUES (3, '大学英语', 10, '30', NULL, 16);

-- ----------------------------
-- Table structure for lawer_skill
-- ----------------------------
DROP TABLE IF EXISTS `lawer_skill`;
CREATE TABLE `lawer_skill`  (
  `lawer_id` bigint NOT NULL COMMENT '律师id',
  `skill_id` bigint NOT NULL COMMENT '专长id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '律师专长多对多表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lawer_skill
-- ----------------------------
INSERT INTO `lawer_skill` VALUES (2, 5);
INSERT INTO `lawer_skill` VALUES (2, 9);
INSERT INTO `lawer_skill` VALUES (1, 2);
INSERT INTO `lawer_skill` VALUES (1, 3);
INSERT INTO `lawer_skill` VALUES (1, 4);
INSERT INTO `lawer_skill` VALUES (1, 7);
INSERT INTO `lawer_skill` VALUES (1, 6);

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`student_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (28, 1);
INSERT INTO `student_course` VALUES (28, 2);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('user', 'el-icon-user', 'icon');
INSERT INTO `sys_dict` VALUES ('house', 'el-icon-house', 'icon');
INSERT INTO `sys_dict` VALUES ('menu', 'el-icon-menu', 'icon');
INSERT INTO `sys_dict` VALUES ('s-custom', 'el-icon-s-custom', 'icon');
INSERT INTO `sys_dict` VALUES ('s-grid', 'el-icon-s-grid', 'icon');
INSERT INTO `sys_dict` VALUES ('document', 'el-icon-document', 'icon');
INSERT INTO `sys_dict` VALUES ('coffee', 'el-icon-coffee\r\n', 'icon');
INSERT INTO `sys_dict` VALUES ('s-marketing', 'el-icon-s-marketing', 'icon');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (44, 'mc-bg-o1.jpg', 'jpg', 1650, 'http://localhost:9090/file/2172daf9032f4417914312a52d533f55.jpg', 'b959830f9320dc6fc9dea8ef86e64c50', 1, 0);
INSERT INTO `sys_file` VALUES (45, 'Ender_Dragon.png', 'png', 7466, 'http://localhost:9090/file/ed951bcd7cc84011af321b186d9b49ee.png', 'a234d860137395ba5ccd9d4a7818de62', 1, 1);
INSERT INTO `sys_file` VALUES (46, 'avator.jpg', 'jpg', 20, 'http://localhost:9090/file/47d9000ba0384ea3b42e57716ce58c83.jpg', 'e828349cf573b04b32fa07f209ee1ee1', 1, 1);
INSERT INTO `sys_file` VALUES (47, '3e1f390dbf53744d7414c36ee8bad5481661604980539.gif', 'gif', 1003, 'http://localhost:9090/file/f1733a3e972d432a81b9bf2bc2b866a7.gif', '3145eadec1986e231c3291d8f15b8f15', 1, 1);
INSERT INTO `sys_file` VALUES (48, '3e1f390dbf53744d7414c36ee8bad5481661604980539.gif', 'gif', 1003, 'http://localhost:9090/file/f1733a3e972d432a81b9bf2bc2b866a7.gif', '3145eadec1986e231c3291d8f15b8f15', 1, 1);
INSERT INTO `sys_file` VALUES (49, '363dcb9d999b2245f2f285323af67c731661605020831.gif', 'gif', 2564, 'http://localhost:9090/file/3fccc13d5ebf41aa88dce6c49f4cde45.gif', 'ef58bac619181b390d2e6b2f9dcb37d9', 1, 1);
INSERT INTO `sys_file` VALUES (50, '3e1f390dbf53744d7414c36ee8bad5481661604980539.gif', 'gif', 1003, 'http://localhost:9090/file/f1733a3e972d432a81b9bf2bc2b866a7.gif', '3145eadec1986e231c3291d8f15b8f15', 1, 1);
INSERT INTO `sys_file` VALUES (51, '53a1024adb3e4ed346e015c7fabe1af11661605493650.gif', 'gif', 663, 'http://localhost:9090/file/aab49d597f6840c3a90c524afdcf4478.gif', '2dafcfee61d72d5d8172f27060cd0401', 1, 1);
INSERT INTO `sys_file` VALUES (52, '3e1f390dbf53744d7414c36ee8bad5481661604980539.gif', 'gif', 1003, 'http://localhost:9090/file/f1733a3e972d432a81b9bf2bc2b866a7.gif', '3145eadec1986e231c3291d8f15b8f15', 1, 1);
INSERT INTO `sys_file` VALUES (53, '363dcb9d999b2245f2f285323af67c731661605020831.gif', 'gif', 2564, 'http://localhost:9090/file/3fccc13d5ebf41aa88dce6c49f4cde45.gif', 'ef58bac619181b390d2e6b2f9dcb37d9', 1, 1);
INSERT INTO `sys_file` VALUES (54, 'eee4ca9c4f1cb899be45a0c5cccf9e821661605147273.gif', 'gif', 690, 'http://localhost:9090/file/6b1d7d1a648c421280ff49fa402faa28.gif', '4d9f9288214a67bf2c6d4a5057d9e0d1', 1, 1);
INSERT INTO `sys_file` VALUES (55, 'favicon.png', 'png', 398, 'http://localhost:9090/file/bbda4cafea78437f9fbf5ec5219abc44.png', '85ec4204a4c03c785f55ab7e41e46d9a', 1, 1);
INSERT INTO `sys_file` VALUES (56, 'favicon.png', 'png', 398, 'http://localhost:9090/file/bbda4cafea78437f9fbf5ec5219abc44.png', '85ec4204a4c03c785f55ab7e41e46d9a', 0, 1);
INSERT INTO `sys_file` VALUES (57, 'avatar.png', 'png', 14, 'http://localhost:9090/file/5e4c3d7fec2840feb2d3c00eabfc6d5d.png', '04683afe85162a9350a73601f049715c', 0, 1);
INSERT INTO `sys_file` VALUES (58, 'icon_completed.png', 'png', 1, 'http://localhost:9090/file/ba64db87198e4f70955423335225bed7.png', '05ec690025599e0c27a9028065e6e2a0', 0, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int NULL DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面路径',
  `sort_num` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, '数据报表', '/dashbord', 'el-icon-house', '11', NULL, 'Dashbord', 100);
INSERT INTO `sys_menu` VALUES (4, '系统管理', NULL, 'el-icon-s-grid', NULL, NULL, NULL, 300);
INSERT INTO `sys_menu` VALUES (5, '用户管理', '/user', 'el-icon-menu', '12121', 4, 'User', 301);
INSERT INTO `sys_menu` VALUES (6, '角色管理', '/role', 'el-icon-s-custom', NULL, 4, 'Role', 302);
INSERT INTO `sys_menu` VALUES (7, '菜单管理', '/menu', 'el-icon-s-custom', NULL, 4, 'Menu', 303);
INSERT INTO `sys_menu` VALUES (8, '文件管理', '/file', 'el-icon-document', NULL, 4, 'File', 304);
INSERT INTO `sys_menu` VALUES (10, '主页', '/home', 'el-icon-house', NULL, NULL, 'Home', 0);
INSERT INTO `sys_menu` VALUES (50, '律师管理', NULL, 'el-icon-menu', NULL, NULL, NULL, 1000);
INSERT INTO `sys_menu` VALUES (51, '金牌律师', '/viplaw', 'el-icon-s-custom', NULL, 50, 'Viplaw', 1001);
INSERT INTO `sys_menu` VALUES (52, '律师专长', '/lawerskill', 'el-icon-coffee\r\n', NULL, 50, 'Lawerskill', 1002);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, '学生', '学生', 'ROLE_STUDENT');
INSERT INTO `sys_role` VALUES (3, '老师', '老师', 'ROLE_TEACHER');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 50);
INSERT INTO `sys_role_menu` VALUES (1, 51);
INSERT INTO `sys_role_menu` VALUES (1, 52);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (3, 10);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 225 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'roydon', '123456', 'roydon', '666666@qq.com', '13988997788', '河南省郑州市', '2022-01-22 21:10:27', 'http://localhost:9090/file/bbda4cafea78437f9fbf5ec5219abc44.png', 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (2, 'rose', '123456', 'rose', '31335845@qq.com', '18203707837', '河南省商丘市', '2022-02-26 22:10:18', 'http://localhost:9090/file/6b1d7d1a648c421280ff49fa402faa28.gif', 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (91, 'zhangsan', '123456', '张三', '78548515@qq.com', '3333333333', '河南省郑州市', '2022-05-15 19:52:19', '', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (92, 'lisi', '123456', '李斯', '21413412421@qq.com', '182236958956', '郑州', '2022-09-15 19:52:19', '', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (93, 'wangwu', '123456', '王五', '12412412412@qq.com', '152559284592', '商丘', '2022-09-15 19:52:19', '', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (94, 'zhaoliu', '123456', '赵六123', '12345325412@qq.com', '16355593759', '郑州市', '2022-09-15 19:52:19', 'http://localhost:9090/file/3fccc13d5ebf41aa88dce6c49f4cde45.gif', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (223, 'yicheng', '123456', 'yicheng', '3133010060@qq.com', '18203707837', '郑州市', '2022-09-18 00:37:12', 'http://localhost:9090/file/f1733a3e972d432a81b9bf2bc2b866a7.gif', 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (224, '233', '233', NULL, NULL, NULL, NULL, '2022-09-20 15:54:06', NULL, 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `user_id` int NULL DEFAULT NULL COMMENT '评论人id',
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论时间',
  `pid` int NULL DEFAULT NULL COMMENT '父id',
  `origin_id` int NULL DEFAULT NULL COMMENT '最上级评论id',
  `article_id` int NULL DEFAULT NULL COMMENT '关联文章的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', 1, '2022-03-22 20:00:00', NULL, NULL, 1);
INSERT INTO `t_comment` VALUES (2, '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_comment` VALUES (5, '回复内容', 1, '2022-03-22 21:01:00', NULL, NULL, 1);
INSERT INTO `t_comment` VALUES (6, '4444', 1, '2022-03-22 21:03:15', 4, 4, 1);
INSERT INTO `t_comment` VALUES (7, '5555', 1, '2022-03-22 21:04:11', 4, 4, 1);
INSERT INTO `t_comment` VALUES (8, '444444', 1, '2022-03-22 21:29:55', 7, 7, 1);
INSERT INTO `t_comment` VALUES (9, '5555', 1, '2022-03-22 21:30:04', 7, 7, 1);
INSERT INTO `t_comment` VALUES (10, '666', 1, '2022-03-22 21:34:05', 7, 4, 1);
INSERT INTO `t_comment` VALUES (11, '甄姬真的好大好大！！', 16, '2022-03-22 21:38:26', 10, 4, 1);
INSERT INTO `t_comment` VALUES (13, '哈哈哈哈，我是ddd', 28, '2022-03-22 21:46:01', 12, 12, 1);
INSERT INTO `t_comment` VALUES (14, '我是李信，我很萌', 20, '2022-03-22 21:46:48', 13, 12, 1);
INSERT INTO `t_comment` VALUES (15, '我在回复ddd', 20, '2022-03-22 21:47:03', 13, 12, 1);
INSERT INTO `t_comment` VALUES (16, '我是李信', 20, '2022-03-22 21:48:19', 4, 4, 1);
INSERT INTO `t_comment` VALUES (17, '33333', 20, '2022-03-22 21:48:42', 5, 5, 1);
INSERT INTO `t_comment` VALUES (19, '我是李信嗯嗯嗯', 20, '2022-03-22 21:49:21', 1, 1, 1);
INSERT INTO `t_comment` VALUES (21, '哈哈哈 我是ddd', 28, '2022-03-22 21:50:04', 20, 1, 1);

-- ----------------------------
-- Table structure for viplawer
-- ----------------------------
DROP TABLE IF EXISTS `viplawer`;
CREATE TABLE `viplawer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `province_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '省代码',
  `city_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '城市代码',
  `region_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区县代码',
  `telephone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '金牌律师' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of viplawer
-- ----------------------------
INSERT INTO `viplawer` VALUES (1, 'roydon', 'http://localhost:9090/file/5e4c3d7fec2840feb2d3c00eabfc6d5d.png', '41', '4114', '411481', '18203707837', '2023-10-19 19:35:42');
INSERT INTO `viplawer` VALUES (2, '郭意诚', 'http://localhost:9090/file/ba64db87198e4f70955423335225bed7.png', '11', '1101', '110105', '17752552722', '2023-10-19 19:39:18');

-- ----------------------------
-- Table structure for viplawer_skill
-- ----------------------------
DROP TABLE IF EXISTS `viplawer_skill`;
CREATE TABLE `viplawer_skill`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专长名称',
  `pid` int NULL DEFAULT 0 COMMENT '父id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '律师专长' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of viplawer_skill
-- ----------------------------
INSERT INTO `viplawer_skill` VALUES (2, '非诉讼类', 0, '2023-10-19 21:08:52');
INSERT INTO `viplawer_skill` VALUES (3, '法律顾问', 2, '2023-10-19 21:09:02');
INSERT INTO `viplawer_skill` VALUES (4, '公司专项类', 0, '2023-10-19 21:11:14');
INSERT INTO `viplawer_skill` VALUES (5, '经济类', 0, '2023-10-19 21:11:45');
INSERT INTO `viplawer_skill` VALUES (6, '民事类', 0, '2023-10-19 21:11:50');
INSERT INTO `viplawer_skill` VALUES (7, '数据合规', 4, '2023-10-19 21:12:07');
INSERT INTO `viplawer_skill` VALUES (8, '涉外法律类', 0, '2023-10-19 21:12:42');
INSERT INTO `viplawer_skill` VALUES (9, '刑事行政类', 0, '2023-10-19 21:13:08');

SET FOREIGN_KEY_CHECKS = 1;
