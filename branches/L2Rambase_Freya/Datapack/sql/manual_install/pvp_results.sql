--
-- Table structure for 'pvp_results'
--
CREATE TABLE IF NOT EXISTS `pvp_results` (
  `char_name` VARCHAR(35) NOT NULL,	
  `level` TINYINT UNSIGNED DEFAULT NULL,
  `classid` TINYINT UNSIGNED DEFAULT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`char_name`)
) DEFAULT CHARSET=utf8;