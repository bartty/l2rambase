-- ----------------------------
-- Table structure for `character_lov_bonus`
-- ----------------------------
CREATE TABLE `character_lov_bonus` (
  `charId` int(10) unsigned NOT NULL,
  `advent_time` bigint(13) unsigned NOT NULL default '0',
  `advent_points` int(10) unsigned NOT NULL default '0',
  UNIQUE KEY `charId` (`charId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;