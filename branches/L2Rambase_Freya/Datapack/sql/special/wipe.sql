DELETE FROM fort;
INSERT IGNORE INTO `fort` VALUES 
(101,'Shanty',0,0,0,0,0,0,0),
(102,'Southern',0,0,0,1,0,0,0),
(103,'Hive',0,0,0,0,0,0,0),
(104,'Valley',0,0,0,1,0,0,0),
(105,'Ivory',0,0,0,0,0,0,0),
(106,'Narsell',0,0,0,0,0,0,0),
(107,'Bayou',0,0,0,1,0,0,0),
(108,'White Sands',0,0,0,0,0,0,0),
(109,'Borderland',0,0,0,1,0,0,0),
(110,'Swamp',0,0,0,1,0,0,0),
(111,'Archaic',0,0,0,0,0,0,0),
(112,'Floran',0,0,0,1,0,0,0),
(113,'Cloud Mountain',0,0,0,1,0,0,0),
(114,'Tanor',0,0,0,0,0,0,0),
(115,'Dragonspine',0,0,0,0,0,0,0),
(116,'Antharas',0,0,0,1,0,0,0),
(117,'Western',0,0,0,1,0,0,0),
(118,'Hunters',0,0,0,1,0,0,0),
(119,'Aaru',0,0,0,0,0,0,0),
(120,'Demon',0,0,0,0,0,0,0),
(121,'Monastic',0,0,0,0,0,0,0);
-- 
DELETE FROM auction;
DELETE FROM auction_bid;
DELETE FROM auction_watch;
-- 
DELETE FROM castle_functions;
DELETE FROM castle_manor_procure;
DELETE FROM castle_manor_production;
-- Chars Deletion
DELETE FROM character_friends;
DELETE FROM character_hennas;
DELETE FROM character_instance_time;
DELETE FROM character_macroses;
DELETE FROM character_quest_global_data;
DELETE FROM character_quests;
DELETE FROM character_raid_points;
DELETE FROM character_recipebook;
DELETE FROM character_recipeshoplist;
DELETE FROM character_recomends;
DELETE FROM character_shortcuts;
DELETE FROM character_skills;
DELETE FROM character_skills_save;
DELETE FROM character_subclasses;
DELETE FROM character_tpbookmark;
DELETE FROM character_ui_actions;
DELETE FROM character_ui_categories;
DELETE FROM characters;
-- Clan options
DELETE FROM clan_data;
DELETE FROM clan_notices;
DELETE FROM clan_privs;
DELETE FROM clan_skills;
DELETE FROM clan_subpledges;
DELETE FROM clan_wars;
-- 
UPDATE clanhall SET ownerId=0;
DELETE FROM clanhall_functions;
DELETE FROM cursed_weapons;
DELETE FROM custom_npcskills;
--
DELETE FROM fortsiege_clans;
DELETE FROM games;
DELETE FROM grandboss_list;
DELETE FROM heroes;
DELETE FROM heroes_diary;
DELETE FROM item_attributes;
DELETE FROM items;
DELETE FROM itemsonground;
DELETE FROM merchant_lease;
DELETE FROM messages;
DELETE FROM olympiad_data;
DELETE FROM olympiad_fights;
DELETE FROM olympiad_nobles;
DELETE FROM olympiad_nobles_eom;
DELETE FROM pets;
DELETE FROM posts;
DELETE FROM quest_global_data;
DELETE FROM seven_signs;
-- seven signs update
DELETE FROM seven_signs_festival;
INSERT IGNORE INTO `seven_signs_festival` VALUES 
(0, "dawn", 1, 0, 0, ""),
(1, "dawn", 1, 0, 0, ""),
(2, "dawn", 1, 0, 0, ""),
(3, "dawn", 1, 0, 0, ""),
(4, "dawn", 1, 0, 0, ""),
(0, "dusk", 1, 0, 0, ""),
(1, "dusk", 1, 0, 0, ""),
(2, "dusk", 1, 0, 0, ""),
(3, "dusk", 1, 0, 0, ""),
(4, "dusk", 1, 0, 0, "");
DELETE FROM seven_signs_status;
INSERT IGNORE INTO `seven_signs_status` VALUES
(0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
--
DELETE FROM siege_clans;
--
DELETE FROM territories;
INSERT IGNORE INTO `territories` VALUES
(81,1,101,'81;'),
(82,2,103,'82;'),
(83,3,104,'83;'),
(84,4,105,'84;'),
(85,5,106,'85;'),
(86,6,108,'86;'),
(87,7,109,'87;'),
(88,8,110,'88;'),
(89,9,111,'89;');
DELETE FROM territory_registrations;
DELETE FROM territory_spawnlist;
DELETE FROM topic;
-- normal tables is wiped now manual
DELETE FROM airships;
DELETE FROM character_pccafe_points;
DELETE FROM clanhall_siege;
INSERT INTO `clanhall_siege` VALUES
(21, 'Fortress of Resistance', 0),
(34, 'Devastated Castle', 0),
(35, 'Bandit Stronghold', 0);
DELETE FROM gracia_seeds_data;
INSERT IGNORE INTO `gracia_seeds_data` VALUES
('SoDTiatKilled','0'),
('SoDState','1'),
('SoDLastStateChangeDate','0');
DELETE FROM hellbound;
INSERT IGNORE INTO `hellbound` VALUES
("HellBound", '0', '0');
DELETE FROM item_auction;
DELETE FROM item_auction_bid;
DELETE FROM kamaloka_results;
DELETE FROM krateis_cube;
-- Script finished