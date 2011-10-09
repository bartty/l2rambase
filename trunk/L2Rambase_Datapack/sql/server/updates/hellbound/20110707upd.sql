INSERT INTO `spawnlist` VALUES
('tully_workshop_6th_floor',1,32467,-12752,279696,-13596,0,0,32768,60,0,0),
('tully_workshop_8th_floor',1,32467,-12752,279696,-10492,0,0,32768,60,0,0),
('tully_workshop_8th_floor',1,22387,-13274,278868,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-12085,278240,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13247,278451,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13305,278698,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13569,278852,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22431,-13040,278330,-10492,0,0,0,60,0,0),
('tully_workshop_8th_floor',1,22387,-11669,280590,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11837,280911,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11657,280832,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11912,280725,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-12115,280883,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22431,-12588,281087,-10492,0,0,0,60,0,0),
('tully_workshop_8th_floor',1,22387,-13644,279654,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13649,279757,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13285,280500,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13838,280320,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-13899,280410,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22431,-13514,280019,-10492,0,0,0,60,0,0),
('tully_workshop_8th_floor',1,22387,-11655,278578,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11528,279157,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11298,279453,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11662,278497,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22387,-11152,279177,-10492,0,0,0,80,0,0),
('tully_workshop_8th_floor',1,22431,-11036,279506,-10492,0,0,0,60,0,0),
('tully_workshop_roof',1,25614,21852,243974,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25614,21725,243948,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25614,21654,243363,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25614,22088,244692,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25614,23047,244080,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25615,21282,243458,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25615,21336,243891,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25615,22421,244233,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25615,22731,243916,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,25615,21019,243359,11087,0,0,0,60,0,0),
('tully_workshop_roof',1,32468,20728,244007,11088,0,0,0,60,0,0);

DELETE FROM `npcskills` WHERE skillid=4065 AND npcid=25602;
DELETE FROM `npcskills` WHERE skillid=5417 AND npcid=18506;

INSERT INTO `raidboss_spawnlist` VALUES
(25603,1,20886,244088,11062,0,43200,216000,0,2308600,4255);

INSERT INTO `minions` VALUES (18491,18493,1,1);

DELETE FROM `spawnlist` WHERE `npc_templateid` = 18506;

UPDATE `npc` SET `type` = 'L2Monster' WHERE `id` = 18506;
UPDATE `npc` SET `type` = 'L2Monster' WHERE `id` = 18491;
UPDATE `npc` SET `type` = 'L2Monster' WHERE `id` = 18493;

UPDATE `spawnlist` SET `randomx`=0, heading=16834 WHERE `location`='tully_workshop_1st_floor' AND `locx`=-12944 AND `locy`=273952 AND `locz`=-15296;
UPDATE `spawnlist` SET `randomx`=0, heading=16834 WHERE `location`='tully_workshop_2nd_floor' AND `locx`=-12768 AND `locy`=273440 AND `locz`=-13600;
UPDATE `spawnlist` SET `randomx`=0, heading=16834 WHERE `location`='tully_workshop_3rd_floor' AND `locx`=-13133 AND `locy`=275690 AND `locz`=-11925;
UPDATE `spawnlist` SET `randomx`=0, heading=16834 WHERE `location`='tully_workshop_4th_floor' AND `locx`=-12848 AND `locy`=273408 AND `locz`=-10496;

UPDATE `spawnlist` SET `randomx`=0, heading=40960 WHERE `location`='tully_workshop_3rd_floor' AND `npc_templateid`=22380;
UPDATE `spawnlist` SET `randomx`=0, heading=24576 WHERE `location`='tully_workshop_3rd_floor' AND `npc_templateid`=22381 AND `locx`=-12372;
UPDATE `spawnlist` SET `randomx`=0, heading=57343 WHERE `location`='tully_workshop_3rd_floor' AND `npc_templateid`=22381 AND `locx`=-12648;
UPDATE `spawnlist` SET `randomx`=0, heading=40960 WHERE `location`='tully_workshop_3rd_floor' AND `npc_templateid`=22382 AND `locx`=-12404;
UPDATE `spawnlist` SET `randomx`=0, heading=8192 WHERE `location`='tully_workshop_3rd_floor' AND `npc_templateid`=22382 AND `locx`=-12632;

UPDATE `spawnlist` SET `randomx`=0, heading=16834 WHERE `npc_templateid` BETWEEN 32745 AND 32752; 