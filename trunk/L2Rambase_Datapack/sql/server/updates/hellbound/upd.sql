INSERT INTO admin_command_access_rights VALUES
('admin_hellbound_setlevel',1,'false');

-- Hellbound natives
INSERT INTO `minions` VALUES
(22320, 22322, 1, 1),
(22320, 22323, 1, 1),
(22321, 22322, 1, 1),
(22321, 22323, 1, 1),
-- Quarry Foreman
(22346, 22347, 2, 2),
-- Naia Failan
(18484, 22422, 4, 4),
-- Leodas
(22448, 22451, 5, 5),
-- Amaskari
(22449, 22450, 10, 10);

UPDATE `npc` SET `type`='L2QuestGuard' WHERE id=32299;
UPDATE `npc` SET `type`='L2Monster' WHERE id=18484;

UPDATE `npcaidata` SET enemyRange=300, enemyClan='quarry_slaves' WHERE npc_id BETWEEN 22344 AND 22347;
UPDATE `npcaidata` SET clan='quarry_slaves', clan_range=100 WHERE npc_id=32299;
UPDATE `npcaidata` SET `can_move`=0 WHERE npc_id=18484;

DELETE FROM `spawnlist` WHERE `location`='hellbound';
INSERT INTO `spawnlist` VALUES ('hellbound',1,32307,-5952,249344,-3117,0,0,0,60,0,0);