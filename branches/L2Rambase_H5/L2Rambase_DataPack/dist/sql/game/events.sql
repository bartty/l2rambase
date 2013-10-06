-- ----------------------------
-- Table structure for `events`
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `month` int(11) NOT NULL,
  `start_day` int(11) NOT NULL,
  `end_day` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of events
-- ----------------------------
INSERT INTO `events` VALUES ('1', 'MasterOfEnchanting', '1', '1', '30');
INSERT INTO `events` VALUES ('2', 'TheValentineEvent', '2', '1', '28');
INSERT INTO `events` VALUES ('3', 'HollyCow', '3', '1', '30');
INSERT INTO `events` VALUES ('4', 'AprilFools', '4', '1', '7');
INSERT INTO `events` VALUES ('5', 'NinjaAdventures', '9', '8', '30');
INSERT INTO `events` VALUES ('6', 'SuperStar', '9', '1', '30');
INSERT INTO `events` VALUES ('7', 'SquashEvent', '10', '1', '31');
INSERT INTO `events` VALUES ('8', 'L2Day', '5', '1', '30');
INSERT INTO `events` VALUES ('9', 'NewEra', '8', '1', '30');
INSERT INTO `events` VALUES ('10', 'SchoolDays', '6', '1', '30');
INSERT INTO `events` VALUES ('11', 'TrickorTransmutation', '7', '1', '30');
INSERT INTO `events` VALUES ('12', 'HallowedYou', '11', '1', '30');
INSERT INTO `events` VALUES ('13', 'ChristmasIsHere', '12', '1', '31');
