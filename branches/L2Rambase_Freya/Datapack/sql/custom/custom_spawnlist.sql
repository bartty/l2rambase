CREATE TABLE IF NOT EXISTS `custom_spawnlist` (
  `id` int(11) NOT NULL auto_increment,
  `location` varchar(40) NOT NULL default '',
  `count` int(9) NOT NULL default '0',
  `npc_templateid` int(9) NOT NULL default '0',
  `locx` int(9) NOT NULL default '0',
  `locy` int(9) NOT NULL default '0',
  `locz` int(9) NOT NULL default '0',
  `randomx` int(9) NOT NULL default '0',
  `randomy` int(9) NOT NULL default '0',
  `heading` int(9) NOT NULL default '0',
  `respawn_delay` int(9) NOT NULL default '0',
  `loc_id` int(9) NOT NULL default '0',
  `periodOfDay` decimal(2,0) default '0',
  PRIMARY KEY (`id`),
  KEY `key_npc_templateid` (`npc_templateid`)
);
INSERT INTO `custom_spawnlist` VALUES
(null,'Donate Area',1,31213,82153,148608,-3464,0,0,65039,10,0,0);