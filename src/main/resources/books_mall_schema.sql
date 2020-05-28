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

-- ----------------------------
-- author 13
-- link https://github.com/
-- Records of books_mall_goods_info
-- ----------------------------
/*小说-》言情（categoryId=2） 5条  */                                                                                      
INSERT INTO `books_mall_goods_info` VALUES (10003, '念远', '谢小禾', 2, '/books-img/58ee3d6d55fbb2fbff4c018f414a20a44723dccc.jpg', '/books-img/58ee3d6d55fbb2fbff4c018f414a20a44723dccc.jpg', '商品介绍加载中...', 0, 0, 30, 0, 0, '2020-05-24 15:08:47', 0, '2020-05-24 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10004, '尽余生', '白槿湖', 2, '/books-img/5243fbf2b21193133214113d6d380cd790238dec.jpg', '/books-img/5243fbf2b21193133214113d6d380cd790238dec.jpg', '商品介绍加载中...', 20, 23, 40, 0, 0, '2020-05-24 15:08:47', 0, '2020-05-24 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10005, '何以青衫薄', '墨苏黎', 2, '/books-img/a9d3fd1f4134970a568464669bcad1c8a6865d87.jpg', '/books-img/a9d3fd1f4134970a568464669bcad1c8a6865d87.jpg', '商品介绍加载中...', 13, 15, 5, 0, 0, '2020-05-24 15:08:47', 0, '2020-05-24 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10006, '花开半夏', '九夜茴', 2, '/books-img/b812c8fcc3cec3fdc27e15c7d088d43f8794273b.jpg', '/books-img/b812c8fcc3cec3fdc27e15c7d088d43f8794273b.jpg', '商品介绍加载中...', 19, 20, 15, 0, 0, '2020-05-24 15:08:47', 0, '2020-05-24 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10007, '悲伤逆流成河', '郭敬明', 2, '/books-img/b03533fa828ba61e2d18e22a4c34970a314e59c4.jpg', '/books-img/b03533fa828ba61e2d18e22a4c34970a314e59c4.jpg', '商品介绍加载中...', 16, 20, 30, 0, 0, '2020-05-24 15:08:47', 0, '2020-05-24 15:08:47');
/*小说-》历史（categoryId=3） 5条 */
INSERT INTO `books_mall_goods_info` VALUES (10008, '寻找', '张贵清', 3, '/books-img/a5c27d1ed21b0ef4c2644081d3c451da80cb3e65.png', '/books-img/a5c27d1ed21b0ef4c2644081d3c451da80cb3e65.png', '商品介绍加载中...', 25, 32.99, 26, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10009, '两地书（上）', '鲁迅；景宋', 3, '/books-img/c8ea15ce36d3d5393bfafbd03687e950342ab0da.jpg', '/books-img/c8ea15ce36d3d5393bfafbd03687e950342ab0da.jpg', '商品介绍加载中...', 7, 8.99, 10, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10010, '永乐大帝', '云石', 3, '/books-img/f2deb48f8c5494eeab6617ea21f5e0fe98257ef8.jpg', '/books-img/f2deb48f8c5494eeab6617ea21f5e0fe98257ef8.jpg', '商品介绍加载中...', 30, 35.19, 5, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10011, '欧游杂记', '朱自清', 3, '/books-img/91529822720e0cf3210447a20446f21fbf09aafc.jpg', '/books-img/91529822720e0cf3210447a20446f21fbf09aafc.jpg', '商品介绍加载中...', 7, 9.99, 10, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10012, '韩非子（下）', '韩非', 3, '/books-img/a8ec8a13632762d0749c0cacacec08fa513dc607.jpg', '/books-img/a8ec8a13632762d0749c0cacacec08fa513dc607.jpg', '商品介绍加载中...', 6, 8.99, 10, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*小说-》科幻（categoryId=4） 4条 */
INSERT INTO `books_mall_goods_info` VALUES (10013, '三体（全3册）', '刘慈欣', 4, '/books-img/9f510fb30f2442a7b7c8bccfd743ad4bd0130291.jpg', '/books-img/9f510fb30f2442a7b7c8bccfd743ad4bd0130291.jpg', '商品介绍加载中...', 15, 18, 40, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10014, '流浪地球', '刘慈欣', 4, '/books-img/d1160924ab18972b9346be22e8cd7b899f510add.jpg', '/books-img/d1160924ab18972b9346be22e8cd7b899f510add.jpg', '商品介绍加载中...', 7, 9.8, 30, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10015, '全频带阻塞干扰', '刘慈欣', 4, '/books-img/18d8bc3eb13533fa94c52dc6a6d3fd1f40345be2.jpg', '/books-img/18d8bc3eb13533fa94c52dc6a6d3fd1f40345be2.jpg', '商品介绍加载中...', 7, 9.8, 32, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10016, '云球（第三部）', '白丁', 4, '/books-img/d62a6059252dd42ac1e56ec00c3b5bb5c8eab8e9.jpg', '/books-img/d62a6059252dd42ac1e56ec00c3b5bb5c8eab8e9.jpg', '商品介绍加载中...', 13, 16, 20, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*小说-》军事（categoryId=5） 3条*/
INSERT INTO `books_mall_goods_info` VALUES (10017, '海权论', '[美]马汉', 5, '/books-img/359b033b5bb5c9ea749bd962d239b6003bf3b3c3.jpg', '/books-img/359b033b5bb5c9ea749bd962d239b6003bf3b3c3.jpg', '商品介绍加载中...', 10, 13, 10, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10018, '二战风云', '丛书编委会', 5, '/books-img/6f061d950a7b0208af34464460d9f2d3562cc892.jpg', '/books-img/6f061d950a7b0208af34464460d9f2d3562cc892.jpg', '商品介绍加载中...', 15, 19.50, 22, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10019, '南京往事', '魏启能', 5, '/books-img/14ce36d3d539b6002472089fe250352ac65cb75d.jpg', '/books-img/14ce36d3d539b6002472089fe250352ac65cb75d.jpg', '商品介绍加载中...', 22, 25, 31, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*小说-》现当代小说（categoryId=6） 3条*/
INSERT INTO `books_mall_goods_info` VALUES (10020, '暗恋·橘生淮南', '八月长安', 6, '/books-img/d01373f082025aaff6c7d93df7edab64024f1a4a.jpg', '/books-img/d01373f082025aaff6c7d93df7edab64024f1a4a.jpg', '商品介绍加载中...', 10, 12.99, 27, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10021, '阳台上', '任晓雯', 6, '/books-img/faedab64034f78f0bae40d707e310a55b2191c41.jpg', '/books-img/faedab64034f78f0bae40d707e310a55b2191c41.jpg', '商品介绍加载中...', 10, 12, 33, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10022, '房思琪的初恋乐园', '林奕含', 6, '/books-img/4034970a304e251f4ccb023aac86c9177f3e5330.jpg', '/books-img/14ce36d3d539b6002472089fe250352ac65cb75d.jpg', '商品介绍加载中...', 38, 45, 30, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*小说-》外国文学（categoryId=7） 3条*/
INSERT INTO `books_mall_goods_info` VALUES (10023, '人间失格', '太宰治', 7, '/books-img/c8ea15ce36d3d539b336704f3187e950352ab036.jpg', '/books-img/c8ea15ce36d3d539b336704f3187e950352ab036.jpg', '商品介绍加载中...', 32, 38, 15, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10024, '莫泊桑短篇小说', '居伊·德·莫', 7, '/books-img/b8014a90f603738dfac89eddbf1bb051f919ec4b.jpg', '/books-img/b8014a90f603738dfac89eddbf1bb051f919ec4b.jpg', '商品介绍加载中...', 20, 24, 3, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10025, '那不勒斯四部曲', '埃莱娜·费兰', 7, '/books-img/5bafa40f4bfbfbed8a8ac6c375f0f736aec31fbe.jpg', '/books-img/5bafa40f4bfbfbed8a8ac6c375f0f736aec31fbe.jpg', '商品介绍加载中...', 190, 225, 5, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*经济管理-》企业管理（categoryId=9） 5条*/
INSERT INTO `books_mall_goods_info` VALUES (10026, '商业的本质', '[美]杰克·韦', 9, '/books-img/3bf33a87e950352a7246b4bd5443fbf2b2118b64.jpg', '/books-img/3bf33a87e950352a7246b4bd5443fbf2b2118b64.jpg', '商品介绍加载中...', 25, 29.40, 17, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10027, '卓有成效的管理者', '彼得·德鲁克', 9, '/books-img/203fb80e7bec54e7be57c548b7389b504fc26a29.jpg', '/books-img/203fb80e7bec54e7be57c548b7389b504fc26a29.jpg', '商品介绍加载中...', 64, 69, 20, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10028, '黑天鹅', '[美]纳西', 9, '/books-img/377adab44aed2e7302f7caa68901a18b87d6fa1a.jpg', '/books-img/377adab44aed2e7302f7caa68901a18b87d6fa1a.jpg', '商品介绍加载中...', 36, 41, 15, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10029, '管理学32定律', '彦涛', 9, '/books-img/c995d143ad4bd1132e0408d75cafa40f4afb050d.jpg', '/books-img/c995d143ad4bd1132e0408d75cafa40f4afb050d.jpg', '商品介绍加载中...', 30, 36, 15, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10030, '美丽新世界', '陈斯文', 9, '/books-img/77094b36acaf2edd77fee6c7841001e939019395.jpg', '/books-img/77094b36acaf2edd77fee6c7841001e939019395.jpg', '商品介绍加载中...', 23, 28.8, 5, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
/*经济管理-》经济金融（categoryId=10） 3条*/
INSERT INTO `books_mall_goods_info` VALUES (10031, '国富论', '亚当·斯密', 10, '/books-img/3b87e950352ac65cbffffa7bf3f2b21192138ac4.jpg', '/books-img/3b87e950352ac65cbffffa7bf3f2b21192138ac4.jpg', '商品介绍加载中...', 40, 48, 13, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10032, '21世纪资本论', '[法]托马斯', 10, '/books-img/b151f8198618367aa0b8dadd2d738bd4b31ce5f9.jpg', '/books-img/b151f8198618367aa0b8dadd2d738bd4b31ce5f9.jpg', '商品介绍加载中...', 50, 58, 20, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');
INSERT INTO `books_mall_goods_info` VALUES (10033, '中国经济猜想', '陈斯文', 10, '/books-img/aa18972bd40735faf1e4b2099c510fb30f24086d.jpg', '/books-img/aa18972bd40735faf1e4b2099c510fb30f24086d.jpg', '商品介绍加载中...', 26, 32.79, 22, 0, 0, '2020-05-28 15:08:47', 0, '2020-05-28 15:08:47');

-- ----------------------------
-- author yu
-- link https://github.com/
-- Table structure for books_mall_index_config
-- ----------------------------
DROP TABLE IF EXISTS `books_mall_index_config`;
CREATE TABLE `books_mall_index_config`  (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id',
  `config_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
  `config_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id 默认为0',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
  `config_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- author yu
-- link https://github.com/
-- Records of books_mall_index_config
-- ----------------------------
INSERT INTO `books_mall_index_config` VALUES (1, '热门推荐 南京往事', 3, 10019, '##', 30, 0, '2020-05-28 17:04:56', 0, '2020-05-28 17:04:56', 0);
INSERT INTO `books_mall_index_config` VALUES (2, '热门推荐 海权论', 3, 10017, '##', 70, 0, '2020-05-28 17:05:27', 0, '2020-05-28 17:05:27', 0);
INSERT INTO `books_mall_index_config` VALUES (3, '热门推荐 云球（第三部）', 3, 10016, '##', 90, 0, '2020-05-28 17:08:02', 0, '2020-05-28 17:08:02', 0);
INSERT INTO `books_mall_index_config` VALUES (4, '热门推荐 三体（全三册）', 3, 10013, '##', 100, 0, '2020-05-28 17:08:56', 0, '2020-05-28 17:08:56', 0);
INSERT INTO `books_mall_index_config` VALUES (5, '热门推荐 永乐大帝', 3, 10010, '##', 50, 0, '2020-05-28 23:23:38', 0, '2020-05-28 23:23:38', 0);
INSERT INTO `books_mall_index_config` VALUES (6, '热门推荐 韩非子（下）', 3, 10012, '##', 95, 0, '2020-05-28 17:10:36', 0, '2020-05-28 17:10:36', 0);
INSERT INTO `books_mall_index_config` VALUES (7, '新书上线 花开半夏', 4, 10006, '##', 100, 0, '2020-05-28 17:11:05', 0, '2020-05-28 17:11:05', 0);
INSERT INTO `books_mall_index_config` VALUES (8, '新书上线 尽余生', 4, 10004, '##', 102, 0, '2020-05-28 17:11:44', 0, '2020-05-28 17:11:44', 0);
INSERT INTO `books_mall_index_config` VALUES (9, '新书上线 何以青衫薄', 4, 10005, '##', 101, 0, '2020-05-28 17:11:58', 0, '2020-05-28 17:11:58', 0);
INSERT INTO `books_mall_index_config` VALUES (10, '新书上线 寻找', 4, 10008, '##', 100, 0, '2020-05-28 17:12:29', 0, '2020-05-28 17:12:29', 0);
INSERT INTO `books_mall_index_config` VALUES (11, '新书上线 管理学32定律', 4, 10029, '##', 50, 0, '2020-05-28 23:26:05', 0, '2020-05-28 23:26:05', 0);
INSERT INTO `books_mall_index_config` VALUES (12, '新书上线 黑天鹅', 4, 10028, '##', 105, 0, '2020-05-28 23:26:32', 0, '2020-05-28 23:26:32', 0);
