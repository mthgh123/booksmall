SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- author
-- link https://github.com/
-- Table structure for books_mall_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `books_mall_admin_user`;
CREATE TABLE `books_mall_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- author
-- link https://github.com/
-- Records of books_mall_admin_user
-- ----------------------------
INSERT INTO `books_mall_admin_user` VALUES (1, 'xiaoyu', '123456', 'admin');

-- ----------------------------
-- author yu
-- link https://github.com/
-- Table structure for books_mall_carousel
-- ----------------------------
DROP TABLE IF EXISTS `books_mall_carousel`;
CREATE TABLE `books_mall_carousel`  (
  `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
  `carousel_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '轮播图',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\'##\'' COMMENT '点击后的跳转地址(默认不跳转)',
  `carousel_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- author yu
-- link https://github.com/
-- Records of books_mall_carousel
-- ----------------------------
INSERT INTO `books_mall_carousel` VALUES (2, 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner1.png', 'https://www.codeyu.cn/', 13, 0, '2020-05-19 00:00:00', 0, '2020-05-19 00:00:00', 0);
INSERT INTO `books_mall_carousel` VALUES (5, 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png', 'https://www.codeyu.cn/', 0, 0, '2020-05-19 00:00:00', 0, '2020-05-19 00:00:00', 0);

-- ----------------------------
-- author yu
-- link https://github.com/
-- Table structure for books_mall_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `books_mall_goods_category`;
CREATE TABLE `books_mall_goods_category`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '分类级别(1-一级分类 2-二级分类 3-三级分类)',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父分类id',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- author yu
-- link https://github.com/
-- Records of books_mall_goods_category
-- ----------------------------
INSERT INTO `books_mall_goods_category` VALUES (1, 1, 0, '小说', 100, 0, '2020-05-22 18:45:40', 0, '2020-05-22 18:45:40', 0);
INSERT INTO `books_mall_goods_category` VALUES (2, 2, 1, '言情', 60, 0, '2020-05-22 18:46:07', 0, '2020-05-22 18:46:07', 0);
INSERT INTO `books_mall_goods_category` VALUES (3, 2, 1, '历史', 50, 0, '2020-05-22 18:46:32', 0, '2020-05-22 18:46:32', 0);
INSERT INTO `books_mall_goods_category` VALUES (4, 2, 1, '科幻', 40, 0, '2020-05-22 18:46:43', 0, '2020-05-22 18:46:43', 0);
INSERT INTO `books_mall_goods_category` VALUES (5, 2, 1, '军事', 30, 0, '2020-05-22 18:46:52', 0, '2020-05-22 18:46:52', 0);
INSERT INTO `books_mall_goods_category` VALUES (6, 2, 1, '现当代小说', 20, 0, '2020-05-22 18:47:38', 0, '2020-05-22 18:47:38', 0);
INSERT INTO `books_mall_goods_category` VALUES (7, 2, 1, '外国文学', 10, 0, '2020-05-22 18:47:49', 0, '2020-05-22 18:47:49', 0);
INSERT INTO `books_mall_goods_category` VALUES (8, 1, 0, '经济管理', 90, 0, '2020-05-22 18:47:58', 0, '2020-05-22 18:47:58', 0);
INSERT INTO `books_mall_goods_category` VALUES (9, 2, 8, '企业管理', 60, 0, '2020-05-22 18:48:06', 0, '2020-05-22 18:48:06', 0);
INSERT INTO `books_mall_goods_category` VALUES (10, 2, 8, '经济金融', 50, 0, '2020-05-22 18:48:12', 0, '2020-05-22 18:48:12', 0);
INSERT INTO `books_mall_goods_category` VALUES (11, 2, 8, '投资理财', 40, 0, '2020-05-22 18:48:26', 0, '2020-05-22 18:48:26', 0);
INSERT INTO `books_mall_goods_category` VALUES (12, 2, 8, '市场营销', 30, 0, '2020-05-22 18:48:40', 0, '2020-05-22 18:48:40', 0);
INSERT INTO `books_mall_goods_category` VALUES (13, 2, 8, '财会统计', 20, 0, '2020-05-22 18:48:50', 0, '2020-05-22 18:48:50', 0);
INSERT INTO `books_mall_goods_category` VALUES (14, 1, 0, '历史传记', 80, 0, '2020-05-22 18:49:09', 0, '2020-05-22 18:49:09', 0);
INSERT INTO `books_mall_goods_category` VALUES (15, 2, 14, '人物传记', 60, 0, '2020-05-22 18:49:19', 0, '2020-05-22 18:49:19', 0);
INSERT INTO `books_mall_goods_category` VALUES (16, 2, 14, '普及读物', 50, 0, '2020-05-22 18:49:30', 0, '2020-05-22 18:49:30', 0);
INSERT INTO `books_mall_goods_category` VALUES (17, 2, 14, '世界各国史', 40, 0, '2020-05-22 18:49:50', 0, '2020-05-22 18:49:50', 0);
INSERT INTO `books_mall_goods_category` VALUES (18, 2, 14, '中国史', 30, 0, '2020-05-22 18:49:55', 0, '2020-05-22 18:49:55', 0);
INSERT INTO `books_mall_goods_category` VALUES (19, 1, 0, '生活', 70, 0, '2020-05-22 18:50:08', 0, '2020-05-22 18:50:08', 0);
INSERT INTO `books_mall_goods_category` VALUES (20, 2, 19, '烹饪饮食', 60, 0, '2020-05-22 18:50:24', 0, '2020-05-22 18:50:24', 0);
INSERT INTO `books_mall_goods_category` VALUES (21, 2, 19, '健康养生', 50, 0, '2020-05-22 18:50:36', 0, '2020-05-22 18:50:36', 0);
INSERT INTO `books_mall_goods_category` VALUES (22, 2, 19, '运动健身', 40, 0, '2020-05-22 18:50:57', 0, '2020-05-22 18:50:57', 0);
INSERT INTO `books_mall_goods_category` VALUES (23, 2, 19, '休闲娱乐', 30, 0, '2020-05-22 18:52:15', 0, '2020-05-22 18:52:15', 0);
INSERT INTO `books_mall_goods_category` VALUES (24, 1, 0, '文学艺术', 60, 0, '2020-05-22 18:52:26', 0, '2020-05-22 18:52:26', 0);
INSERT INTO `books_mall_goods_category` VALUES (25, 2, 24, '散文随笔', 60, 0, '2020-05-22 18:52:46', 0, '2020-05-22 18:52:46', 0);
INSERT INTO `books_mall_goods_category` VALUES (26, 2, 24, '诗词歌赋', 50, 0, '2020-05-22 18:53:01', 0, '2020-05-22 18:53:01', 0);
INSERT INTO `books_mall_goods_category` VALUES (27, 2, 24, '国学经典', 40, 0, '2020-05-22 18:53:08', 0, '2020-05-22 18:53:08', 0);
INSERT INTO `books_mall_goods_category` VALUES (28, 2, 24, '世界名著', 30, 0, '2020-05-22 18:53:14', 0, '2020-05-22 18:53:14', 0);
INSERT INTO `books_mall_goods_category` VALUES (29, 2, 24, '纪实文学', 89, 0, '2020-05-22 18:53:49', 0, '2020-05-22 18:54:38', 0);
INSERT INTO `books_mall_goods_category` VALUES (30, 1, 0, '社会科学', 50, 0, '2020-05-22 18:53:59', 0, '2020-09-18 13:40:59', 0);
INSERT INTO `books_mall_goods_category` VALUES (31, 2, 30, '社会学', 98, 0, '2020-05-22 18:54:20', 0, '2020-09-18 13:40:51', 0);
INSERT INTO `books_mall_goods_category` VALUES (32, 2, 30, '宗教哲学', 88, 0, '2020-05-22 18:54:49', 0, '2020-09-18 13:40:32', 0);
INSERT INTO `books_mall_goods_category` VALUES (33, 2, 30, '时事政治', 79, 0, '2020-05-22 18:55:03', 0, '2020-05-22 18:55:13', 0);

-- ----------------------------
-- author yu
-- link https://github.com/
-- Table structure for books_mall_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `books_mall_goods_info`;
CREATE TABLE `books_mall_goods_info`  (
  `goods_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '书籍表主键id',
  `goods_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '书籍名',
  `books_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '作者姓名',
  `goods_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联分类id',
  `goods_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '书籍主图',
  `goods_carousel` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '书籍轮播图',
  `goods_detail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍详情',
  `original_price` int(11) NOT NULL DEFAULT 1 COMMENT '书籍原价',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '书籍实际售价',
  `stock_num` int(11) NOT NULL DEFAULT 0 COMMENT '书籍库存数量',
  `goods_sell_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '书籍上架状态 0-下架 1-上架',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '添加者主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '书籍添加时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者主键id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '书籍修改时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10896 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
