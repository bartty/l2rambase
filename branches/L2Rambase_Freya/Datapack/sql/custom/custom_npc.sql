CREATE TABLE IF NOT EXISTS `custom_npc`(
  `id` decimal(11,0) NOT NULL default '0',
  `idTemplate` int(11) NOT NULL default '0',
  `name` varchar(200) default NULL,
  `serverSideName` int(1) default '0',
  `title` varchar(45) default '',
  `serverSideTitle` int(1) default '0',
  `class` varchar(200) default NULL,
  `collision_radius` decimal(5,2) default NULL,
  `collision_height` decimal(5,2) default NULL,
  `level` decimal(2,0) default NULL,
  `sex` varchar(6) default NULL,
  `type` varchar(20) default NULL,
  `attackrange` int(11) default NULL,
  `hp` decimal(8,0) default NULL,
  `mp` decimal(6,0) default NULL,
  `hpreg` decimal(8,2) default NULL,
  `mpreg` decimal(5,2) default NULL,
  `str` decimal(7,0) default NULL,
  `con` decimal(7,0) default NULL,
  `dex` decimal(7,0) default NULL,
  `int` decimal(7,0) default NULL,
  `wit` decimal(7,0) default NULL,
  `men` decimal(7,0) default NULL,
  `exp` decimal(9,0) default NULL,
  `sp` decimal(8,0) default NULL,
  `patk` decimal(5,0) default NULL,
  `pdef` decimal(5,0) default NULL,
  `matk` decimal(5,0) default NULL,
  `mdef` decimal(5,0) default NULL,
  `atkspd` decimal(3,0) default NULL,
  `aggro` decimal(6,0) default NULL,
  `matkspd` decimal(4,0) default NULL,
  `rhand` decimal(5,0) default NULL,
  `lhand` decimal(5,0) default NULL,
  `armor` decimal(1,0) default NULL,
  `enchant` INT NOT NULL default 0, 
  `walkspd` decimal(3,0) default NULL,
  `runspd` decimal(3,0) default NULL,
  `isUndead` int(11) default 0,
  `absorb_level` decimal(2,0) default 0,
  `absorb_type` enum('FULL_PARTY','LAST_HIT','PARTY_ONE_RANDOM') DEFAULT 'LAST_HIT' NOT NULL,
  `drop_herbs` enum('true','false') DEFAULT 'false' NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT IGNORE INTO `custom_npc` VALUES
(50007,31324,'Andromeda',1,'Wedding Manager',1,'NPC.a_casino_FDarkElf',8,23,70,'female','L2WeddingManager',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70010,31606,'Catrina',1,'TvT Event Manager',1,'Monster2.queen_of_cat',8,15,70,'female','L2Npc',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70011,31606,'Luise',1,'PvP Event Manager',1,'Monster2.queen_of_cat',8,15,70,'female','L2Npc',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70012,32471,'Saysha',1,'Eternal Oracle',1,'LineageNPC2.pr_K_F1_teleporter',13.00,23.50,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,60,60,0,0,'LAST_HIT','false'),
(1000003,32226,'Shiela',1,'NPC Buffer',1,'LineageNPC2.K_F1_grand',11,22.25,70,'male','L2NpcBuffer',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
-- Gm Buffer
(90001,18827, 'Junko', 1, 'GM Buffer', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Merchant',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(90002,18828, 'Zanko', 1, 'GM Shop', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Merchant',40,2444,2444,0,0,10,10,10,10,10,10,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
-- 10 Levels
(7000,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,10,'male','L2Monster',500,262,1494,0.00,0.00,30,33,20,11,10,20,0,0,53,31,57,32,253,800,333,0,0,0,0,60,120,0,0,'LAST_HIT','true'),
(7001,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,10,'male','L2Monster',40,262,1494,0.00,0.00,30,33,20,11,10,20,0,0,53,31,57,32,253,800,333,0,0,0,0,60,120,0,0,'LAST_HIT','true'),
(7002,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,13,'male','L2Monster',40,262,1494,0.00,0.00,40,43,30,21,20,20,0,0,53,31,57,32,253,800,333,0,0,0,0,60,120,0,0,'LAST_HIT','true'),
-- 20 Levels
(7003,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,25,'male','L2Monster',500,300,413,21.33,1.84,30,33,20,11,10,10,0,0,191,50,75,58,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7004,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,25,'male','L2Monster',40,300,413,42.66,1.84,30,33,20,11,10,10,0,0,106,57,45,58,278,800,333,0,0,0,0,110,181,1,0,'LAST_HIT','true'),
(7005,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,25,'male','L2Monster',40,300,446,42.66,1.84,30,33,20,11,10,10,0,0,200,50,79,57,278,800,333,0,0,0,0,55,181,1,0,'LAST_HIT','true'),
-- 30 Levels
(7006,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,35,'male','L2Monster',500,409,413,21.33,1.84,30,33,20,21,10,10,0,0,191,50,75,58,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7007,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,35,'male','L2Monster',40,409,413,42.66,1.84,30,33,20,21,10,10,0,0,166,57,75,58,278,800,333,0,0,0,0,110,181,1,0,'LAST_HIT','true'),
(7008,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,35,'male','L2Monster',40,420,446,42.66,1.84,30,33,20,21,10,10,0,0,232,50,89,57,278,800,333,0,0,0,0,55,181,1,0,'LAST_HIT','true'),
-- 40 Levels
(7009,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,45,'male','L2Monster',500,728,618,26.07,2.14,40,43,30,21,20,10,0,0,984,142,163,181,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7010,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,45,'male','L2Monster',40,728,618,52.14,2.14,40,43,30,21,20,10,0,0,332,223,163,181,278,800,333,0,0,0,0,66,0,1,0,'LAST_HIT','true'),
(7011,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,45,'male','L2Monster',40,770,677,52.14,2.14,40,43,30,21,20,10,0,0,457,196,188,193,278,800,333,0,0,0,0,77,181,1,0,'LAST_HIT','true'),
-- 50 Levels
(7012,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,55,'male','L2Monster',500,981,923,30.81,2.45,40,43,30,21,20,10,0,0,1782,194,319,247,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7013,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,55,'male','L2Monster',40,981,923,61.62,2.45,40,43,30,21,20,10,0,0,602,258,319,247,278,800,333,0,0,0,0,88,181,0,0,'LAST_HIT','true'),
(7014,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,55,'male','L2Monster',40,943,987,61.62,2.45,40,43,30,21,20,10,0,0,811,266,360,261,278,800,333,0,0,0,0,88,181,0,0,'LAST_HIT','true'),
-- 60 Levels
(7015,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,65,'male','L2Monster',500,1302,1251,35.55,2.78,40,43,30,21,20,10,0,0,2920,254,560,323,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7016,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,65,'male','L2Monster',40,1302,1251,71.10,2.78,40,43,30,21,20,10,0,0,987,338,560,323,278,800,333,0,0,0,0,88,181,0,0,'LAST_HIT','true'),
(7017,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,65,'male','L2Monster',40,1465,1319,71.10,2.78,40,43,30,21,20,10,0,0,1303,346,619,340,278,800,333,0,0,0,0,88,181,0,0,'LAST_HIT','true'),
-- 70 Levels
(7018,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,75,'male','L2Monster',500,1686,1601,40.29,3.09,40,43,30,21,20,10,0,0,4327,320,885,407,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7019,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,75,'male','L2Monster',40,1629,1673,40.29,3.09,40,43,30,21,20,10,0,0,1821,334,957,425,317,800,333,0,0,0,0,88,181,1,0,'LAST_HIT','true'),
(7020,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,75,'male','L2Monster',40,1629,1673,80.58,3.09,40,43,30,21,20,10,0,0,1892,432,957,425,278,800,333,0,0,0,0,110,181,1,0,'LAST_HIT','true'),
-- 80 Levels
(7021,22725,'Mission Invader Archer',1,'',0,'LineageMonster5.skeleton_boneshooter',18.00,27.50,82,'male','L2Monster',500,1916,1972,40.29,3.09,50,53,50,51,50,20,0,0,5796,1388,2258,1394,249,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7022,22735,'Mission Invader Shaman',1,'',0,'LineageMonster5.doll_claw',28.00,50.00,82,'male','L2Monster',40,1813,2049,80.58,3.09,50,53,50,51,50,20,0,0,3483,1320,2333,1311,278,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7023,22730,'Mission Invader Warrior',1,'',0,'LineageMonster5.dimension_caller',25.00,40.00,83,'male','L2Monster',40,1813,2049,80.58,3.09,50,53,50,51,50,20,0,0,3483,1320,2333,1311,278,800,333,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Raids
(7024,25641,'Mission Invader Boss',1,'Raid Boss',1,'LineageMonster4.death_knight_n_156p',34.32,62.40,45,'male','L2DungeonBoss',40,72800,618,52.14,2.14,60,57,73,76,70,80,0,0,532,715,36,387,278,0,3819,13982,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7025,25641,'Mission Invader Boss',1,'Raid Boss',1,'LineageMonster4.death_knight_n_156p',34.32,62.40,55,'male','L2DungeonBoss',40,98100,923,61.62,2.45,60,57,73,76,70,80,0,0,1046,966,111,523,278,0,3819,13982,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7026,25641,'Mission Invader Boss',1,'Raid Boss',1,'LineageMonster4.death_knight_n_156p',34.32,62.40,65,'male','L2DungeonBoss',40,130200,1251,71.10,2.78,60,57,73,76,70,80,0,0,3327,1256,758,680,278,0,3819,13982,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7027,25641,'Mission Invader Boss',1,'Raid Boss',1,'LineageMonster4.death_knight_n_156p',34.32,62.40,75,'male','L2DungeonBoss',40,162900,1673,40.29,3.09,60,57,73,76,70,80,0,0,4784,1571,1152,851,278,0,3819,13982,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7028,25641,'Mission Invader Boss',1,'Raid Boss',1,'LineageMonster4.death_knight_n_156p',34.32,62.40,82,'male','L2DungeonBoss',40,181300,2049,80.58,3.09,60,57,73,76,70,80,0,0,9325,1500,2020,1166,253,0,3819,13982,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Throne King Strategy
(7029,29020,'King of Eternity',1,'Throne Warden',1,'LineageMonster.baium',65.00,174.00,45,'male','L2DungeonBoss',40,72800,618,52.14,2.14,60,57,73,76,70,80,0,0,532,715,36,387,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7030,29020,'King of Eternity',1,'Throne Warden',1,'LineageMonster.baium',65.00,174.00,55,'male','L2DungeonBoss',40,98100,923,61.62,2.45,60,57,73,76,70,80,0,0,1046,966,111,523,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7031,29020,'King of Eternity',1,'Throne Warden',1,'LineageMonster.baium',65.00,174.00,65,'male','L2DungeonBoss',40,130200,1251,71.10,2.78,60,57,73,76,70,80,0,0,3327,1256,758,680,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7032,29020,'King of Eternity',1,'Throne Warden',1,'LineageMonster.baium',65.00,174.00,75,'male','L2DungeonBoss',40,162900,1673,40.29,3.09,60,57,73,76,70,80,0,0,4784,1571,1152,851,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7033,29020,'King of Eternity',1,'Throne Warden',1,'LineageMonster.baium',65.00,174.00,82,'male','L2DungeonBoss',40,181300,2049,80.58,3.09,60,57,73,76,70,80,0,0,9325,1500,2020,1166,253,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Throne Queen Strategy
(7034,25450,'Queen of Eternity',1,'Throne Warden',1,'LineageMonster.archangel_50_bi',22.00,120.00,45,'male','L2DungeonBoss',40,72800,618,52.14,2.14,60,57,73,76,70,80,0,0,532,715,36,387,278,0,3819,97,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7035,25450,'Queen of Eternity',1,'Throne Warden',1,'LineageMonster.archangel_50_bi',22.00,120.00,55,'male','L2DungeonBoss',40,98100,923,61.62,2.45,60,57,73,76,70,80,0,0,1046,966,111,523,278,0,3819,97,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7036,25450,'Queen of Eternity',1,'Throne Warden',1,'LineageMonster.archangel_50_bi',22.00,120.00,65,'male','L2DungeonBoss',40,130200,1251,71.10,2.78,60,57,73,76,70,80,0,0,3327,1256,758,680,278,0,3819,97,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7037,25450,'Queen of Eternity',1,'Throne Warden',1,'LineageMonster.archangel_50_bi',22.00,120.00,75,'male','L2DungeonBoss',40,162900,1673,40.29,3.09,60,57,73,76,70,80,0,0,4784,1571,1152,851,278,0,3819,97,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7038,25450,'Queen of Eternity',1,'Throne Warden',1,'LineageMonster.archangel_50_bi',22.00,120.00,82,'male','L2DungeonBoss',40,181300,2049,80.58,3.09,60,57,73,76,70,80,0,0,9325,1500,2020,1166,253,0,3819,97,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Throne Guard Strategy
(7039,25609,'Eternal Guardian',1,'Throne Warden',1,'LineageMonster4.guard_naia_c',60.00,99.50,45,'male','L2DungeonBoss',40,72800,618,52.14,2.14,60,57,73,76,70,80,0,0,532,715,36,387,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7040,25609,'Eternal Guardian',1,'Throne Warden',1,'LineageMonster4.guard_naia_c',60.00,99.50,55,'male','L2DungeonBoss',40,98100,923,61.62,2.45,60,57,73,76,70,80,0,0,1046,966,111,523,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7041,25609,'Eternal Guardian',1,'Throne Warden',1,'LineageMonster4.guard_naia_c',60.00,99.50,65,'male','L2DungeonBoss',40,130200,1251,71.10,2.78,60,57,73,76,70,80,0,0,3327,1256,758,680,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7042,25609,'Eternal Guardian',1,'Throne Warden',1,'LineageMonster4.guard_naia_c',60.00,99.50,75,'male','L2DungeonBoss',40,162900,1673,40.29,3.09,60,57,73,76,70,80,0,0,4784,1571,1152,851,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7043,25609,'Eternal Guardian',1,'Throne Warden',1,'LineageMonster4.guard_naia_c',60.00,99.50,82,'male','L2DungeonBoss',40,181300,2049,80.58,3.09,60,57,73,76,70,80,0,0,9325,1500,2020,1166,253,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Throne Pet Strategy
(7044,29065,'Eternal-rex',1,'Sealed Evil Warden',1,'LineageMonster3.sailren',100.00,82.10,45,'male','L2DungeonBoss',40,72800,618,52.14,2.14,60,57,73,76,70,80,0,0,532,715,36,387,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7045,29065,'Eternal-rex',1,'Sealed Evil Warden',1,'LineageMonster3.sailren',100.00,82.10,55,'male','L2DungeonBoss',40,98100,923,61.62,2.45,60,57,73,76,70,80,0,0,1046,966,111,523,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7046,29065,'Eternal-rex',1,'Sealed Evil Warden',1,'LineageMonster3.sailren',100.00,82.10,65,'male','L2DungeonBoss',40,130200,1251,71.10,2.78,60,57,73,76,70,80,0,0,3327,1256,758,680,278,0,3819,0,0,0,0,44,181,0,0,'LAST_HIT','true'),
(7047,29065,'Eternal-rex',1,'Sealed Evil Warden',1,'LineageMonster3.sailren',100.00,82.10,75,'male','L2DungeonBoss',40,162900,1673,40.29,3.09,60,57,73,76,70,80,0,0,4784,1571,1152,851,278,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
(7048,29065,'Eternal-rex',1,'Sealed Evil Warden',1,'LineageMonster3.sailren',100.00,82.10,82,'male','L2DungeonBoss',40,181300,2049,80.58,3.09,60,57,73,76,70,80,0,0,9325,1500,2020,1166,253,0,3819,0,0,0,0,44,181,1,0,'LAST_HIT','true'),
-- Couple Manager
(7100,32431,'Linda Love',1,'Couple Manager',1,'LineageNPC.pr_a_teleporter_FElf',8.50,23.50,70,'female','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,40,40,0,0,'LAST_HIT','false'),
-- Master of Dungeon Event Npc
(7101,18826, 'Mission Master', 1, 'Master of Dungeon', 1, 'NPC.e_fighterguild_teacher_MOrc', 18.00, 29.00, 70, 'MALE', 'L2Merchant',40,15444,15444,0,0,30,30,30,30,30,30,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
-- Ninja Event
(7102,32469, 'Ninja Master', 1, 'Ninja Adventures', 1, 'LineageNPC2.K_M1_elder',17.00,25.00, 70, 'male', 'L2Merchant',40,15444,15444,0,0,30,30,30,30,30,30,0,0,500,500,500,500,278,0,333,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
-- HunterG Manager
(7103,32586,'Deadly Hunter',1,'HunterG Event',1,'LineageNPC2.emperor_shuneimman',15.00,24.00,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,40,40,0,0,'LAST_HIT','false'),
-- Halloween Manager
(7104,21,'Skooldy',1,'Halloween Manager',1,'LineageMonster5.br_scooldie',23.00,15.00,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,0,0,60,120,0,0,'LAST_HIT','false'), 
(7105,25614,'Ghost',1,'Halloween Event',1,'LineageMonster4.roaming_Msoul',6.00,26.00,70,'male','L2Monster',40,1813,1444,0.00,0.00,10,10,10,10,10,10,0,0,300,300,300,300,278,0,333,0,0,NULL,0,60,60,0,0,'LAST_HIT','false'),
-- Santa Event
(7106,30,'Santa',1,'Merry Christmas',1,'LineageNPC2.br_xmas08_santa',9.00,16.00,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,0,0,60,120,0,0,'LAST_HIT','false'),
-- Super Star Event
(7107,18805,'Master of Star',1,'Super Star',1,'LineageNpcEV.riding_control_unit_c',19.00,28.00,83,'male','L2Npc',40,4639,1812,0.00,0.00,40,43,30,21,20,20,0,0,1303,671,607,482,253,0,333,0,0,0,0,1,1,0,0,'LAST_HIT','false'),
-- Friend Manager
(7108,32414,'Sayku Mayki',1,'Friend Manager',1,'LineageNPC.pr_yeti_cute',5.00,10.50,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,60,60,0,0,'LAST_HIT','false'),
-- PcCafe Manager
(7109,32425,'Yaksha Morphia',1,'PcCafe Manager',1,'LineageNPC.sw_e_smith_Fdwarf',8.00,17.50,70,'female','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,40,40,0,0,'LAST_HIT','false'),
-- PcCafe Storage
(7110,30627,'Tree',1,'Cut Me',1,'LineageNPC.human_tree',35.00,90.00,70,'etc','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,0,0,50,120,0,0,'LAST_HIT','false'),
(7111,32238,'Rock',1,'Smash Me',1,'LineageNpcEV.stone_q0236_p',60.00,25.00,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,1,120,0,0,'LAST_HIT','false'),
(7112,32531,'Fabric Cube',1,'Cut Me',1,'LineageNpcEV.tumor_immortality_a',42.00,55.00,70,'male','L2Npc',40,3862,1494,0.00,0.00,40,43,30,21,20,20,0,0,1303,471,607,382,253,0,333,0,0,NULL,0,60,60,0,0,'LAST_HIT','false'),
-- Ninja Monsters
(70001,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70002,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70003,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70004,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70005,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70006,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false'),
(70007,13156, 'Shinobi', 1, 'Great Ninja', 1, 'NPC.e_fighterguild_teacher_MOrc', 10.00, 22.00, 70, 'MALE', 'L2Monster',800,15444,15444,0,0,30,30,30,30,30,30,0,0,4500,1500,1500,2500,478,3000,633,0,0,NULL,0,28,120,0,0,'LAST_HIT','false');