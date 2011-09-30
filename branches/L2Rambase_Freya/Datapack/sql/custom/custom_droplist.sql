CREATE TABLE IF NOT EXISTS `custom_droplist` (
  `mobId` INT NOT NULL DEFAULT '0',
  `itemId` INT NOT NULL DEFAULT '0',
  `min` INT NOT NULL DEFAULT '0',
  `max` INT NOT NULL DEFAULT '0',
  `category` INT NOT NULL DEFAULT '0',
  `chance` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`mobId`,`itemId`,`category`),
  KEY `key_mobId` (`mobId`)
);

INSERT INTO `custom_droplist` VALUES
(7000,57,300,420,0,700000),-- Adena
(7000,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7000,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7000,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7000,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7001,57,300,420,0,700000),-- Adena
(7001,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7001,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7001,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7001,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7002,57,300,420,0,700000),-- Adena
(7002,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7002,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7002,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7002,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7003,57,300,420,0,700000),-- Adena
(7003,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7003,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7003,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7003,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7004,57,300,420,0,700000),-- Adena
(7004,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7004,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7004,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7004,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7005,57,300,420,0,700000),-- Adena
(7005,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7005,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7005,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7005,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7006,57,300,420,0,700000),-- Adena
(7006,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7006,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7006,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7006,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7007,57,300,420,0,700000),-- Adena
(7007,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7007,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7007,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7007,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7008,57,300,420,0,700000),-- Adena
(7008,955,1,1,2,200),-- Scroll: Enchant Weapon (Grade D)
(7008,956,1,1,2,450),-- Scroll: Enchant Armor (Grade D)
(7008,955,1,1,-1,700),-- Scroll: Enchant Weapon (Grade D)
(7008,956,1,1,-1,950),-- Scroll: Enchant Armor (Grade D)
(7009,57,400,520,0,700000),-- Adena
(7009,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7009,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7009,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7009,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7010,57,400,520,0,700000),-- Adena
(7010,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7010,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7010,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7010,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7011,57,400,520,0,700000),-- Adena
(7011,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7011,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7011,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7011,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7012,57,400,520,0,700000),-- Adena
(7012,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7012,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7012,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7012,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7013,57,400,520,0,700000),-- Adena
(7013,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7013,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7013,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7013,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7014,57,400,520,0,700000),-- Adena
(7014,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7014,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7014,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7014,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7015,57,450,620,0,700000),-- Adena
(7015,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7015,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7015,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7015,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7016,57,450,620,0,700000),-- Adena
(7016,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7016,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7016,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7016,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7017,57,450,620,0,700000),-- Adena
(7017,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7017,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7017,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7017,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7018,57,500,720,0,700000),-- Adena
(7018,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7018,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7018,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7018,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7019,57,500,720,0,700000),-- Adena
(7019,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7019,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7019,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7019,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7020,57,500,720,0,700000),-- Adena
(7020,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7020,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7020,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7020,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7021,57,500,720,0,700000),-- Adena
(7021,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7021,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7021,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7021,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7022,57,500,720,0,700000),-- Adena
(7022,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7022,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7022,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7022,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7023,57,500,720,0,700000),-- Adena
(7023,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7023,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7023,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7023,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
-- Cafeteria drop
(7111,57,300,420,0,700000),-- Adena
(7111,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7111,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7111,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7111,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7112,57,300,420,0,700000),-- Adena
(7112,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7112,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7112,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7112,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7113,57,300,420,0,700000),-- Adena
(7113,951,1,1,2,200),-- Scroll: Enchant Weapon (Grade C)
(7113,952,1,1,2,450),-- Scroll: Enchant Armor (Grade C)
(7113,951,1,1,-1,700),-- Scroll: Enchant Weapon (Grade C)
(7113,952,1,1,-1,950),-- Scroll: Enchant Armor (Grade C)
(7114,57,400,520,0,700000),-- Adena
(7114,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7114,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7114,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7114,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7115,57,400,520,0,700000),-- Adena
(7115,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7115,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7115,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7115,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7116,57,400,520,0,700000),-- Adena
(7116,947,1,1,2,200),-- Scroll: Enchant Weapon (Grade B)
(7116,948,1,1,2,450),-- Scroll: Enchant Armor (Grade B)
(7116,947,1,1,-1,700),-- Scroll: Enchant Weapon (Grade B)
(7116,948,1,1,-1,950),-- Scroll: Enchant Armor (Grade B)
(7117,57,500,620,0,700000),-- Adena
(7117,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7117,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7117,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7117,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7118,57,500,620,0,700000),-- Adena
(7118,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7118,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7118,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7118,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7119,57,500,620,0,700000),-- Adena
(7119,729,1,1,2,200),-- Scroll: Enchant Weapon (Grade A)
(7119,730,1,1,2,450),-- Scroll: Enchant Armor (Grade A)
(7119,729,1,1,-1,700),-- Scroll: Enchant Weapon (Grade A)
(7119,730,1,1,-1,950),-- Scroll: Enchant Armor (Grade A)
(7120,57,600,720,0,700000),-- Adena
(7120,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7120,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7120,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7120,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7121,57,600,720,0,700000),-- Adena
(7121,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7121,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7121,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7121,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7122,57,600,720,0,700000),-- Adena
(7122,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7122,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7122,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7122,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7123,57,600,720,0,700000),-- Adena
(7123,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7123,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7123,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7123,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7124,57,600,720,0,700000),-- Adena
(7024,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7024,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7024,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7024,960,1,1,-1,950),-- Scroll: Enchant Armor (Grade S)
(7125,57,600,720,0,700000),-- Adena
(7025,959,1,1,2,200),-- Scroll: Enchant Weapon (Grade S)
(7025,960,1,1,2,450),-- Scroll: Enchant Armor (Grade S)
(7025,959,1,1,-1,700),-- Scroll: Enchant Weapon (Grade S)
(7025,960,1,1,-1,950);-- Scroll: Enchant Armor (Grade S)