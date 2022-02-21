-- 建表语句
CREATE TABLE `demo_tree` (
  `id` bigint(12) unsigned NOT NULL AUTO_INCREMENT,
  `ancestor` bigint(12) NOT NULL COMMENT '祖先id',
  `descendant` bigint(12) NOT NULL COMMENT '后代id',
  `depth` int(11) NOT NULL COMMENT '深度',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `descendant_idx` (`descendant`) USING BTREE COMMENT '后代id索引'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='券商闭包表';