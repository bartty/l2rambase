CREATE TABLE IF NOT EXISTS `krateis_cube` (
  `charId` int(10) NOT NULL,
  `played_matchs` int(10) NOT NULL,
  `total_kills` int(10) NOT NULL,
  `total_coins` double(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY  (`charId`)
) DEFAULT CHARSET=utf8;