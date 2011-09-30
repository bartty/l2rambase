-- ----------------------------
-- Table structure for hellbound
-- ----------------------------
CREATE TABLE IF NOT EXISTS `hellbound` (
  `name` varchar(15) NOT NULL,
  `trustLevel` INT UNSIGNED NOT NULL DEFAULT 0,
  `zonesLevel` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`name`)
);

INSERT IGNORE INTO `hellbound` VALUES
("HellBound", '0', '0');