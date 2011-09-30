--
-- Table structure for 'events'
--
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL,	
  `name` varchar(40) NOT NULL,
  `month` int(11) NOT NULL,
  `start_day` int(11) NOT NULL,
  `end_day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `events` VALUES
(1, 'MasterOfEnchanting', 1, 1, 31),
(2, 'TheValentineEvent', 2, 1, 28),
(3, 'HollyCow', 3, 1,31),
(4, 'AprilFools', 4, 1, 6),
(5, 'NinjaAdventures', 4, 7, 30),
(6, 'SuperStar', 5, 1, 31),
(7, 'SquashEvent', 6, 1, 30),
(8, 'L2Day', 7, 1, 31),
(9, 'HeavyMedal', 8, 1, 31),
(10, 'SchoolDays', 9, 1, 30),
(11, 'TrickorTransmutation', 10, 1, 31),
(12, 'HallowedYou', 11, 1, 30),
(13, 'ChristmasIsHere', 12, 1, 31);
