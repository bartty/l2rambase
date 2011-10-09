CREATE TABLE IF NOT EXISTS `hellbound_data` (
  `var`  VARCHAR(20) NOT NULL DEFAULT '',
  `value` VARCHAR(255) ,
  PRIMARY KEY (`var`)
);

INSERT IGNORE INTO `hellbound_data` VALUES
('level','0'),
('trust','0');
