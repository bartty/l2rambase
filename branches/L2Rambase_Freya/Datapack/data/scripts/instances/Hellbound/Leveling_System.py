import sys
from java.lang import System
from l2.rambase import Config
from l2.rambase.gameserver import Announcements
from l2.rambase.gameserver.datatables import DoorTable
from l2.rambase.gameserver.datatables import SpawnTable
from l2.rambase.gameserver.instancemanager import HellboundManager
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
from l2.rambase.gameserver.network.serverpackets import CreatureSay
from l2.rambase.util import Rnd

ghostofderek = 18465
Captain = 18466
WLGuard = 18467
chimeras = [22349, 22350, 22351, 22352]
celtus = 22353
Solomon = 32355
Traitor = 32364
Kief = 32354
Buron = 32345
Keltas = 22341
KeltasSpawn = [ -29569, 252834, -3518 ]
KeltasMinions = [ 22342, 22343 ]
KeltasMinionsSpawns = {
0:[ -23553, 251677, -3361 ],
1:[ -24381, 251555, -3351 ],
2:[ -25367, 252178, -3252 ],
3:[ -25390, 252405, -3252 ],
4:[ -24222, 252270, -3140 ],
5:[ -24441, 252295, -3085 ],
6:[ -24106, 252711, -3054 ],
7:[ -26930, 251951, -3518 ],
8:[ -27918, 251498, -3518 ],
9:[ -27764, 251124, -3521 ],
10:[ -28415, 251995, -3521 ],
11:[ -28640, 250998, -3518 ],
12:[ -28389, 250050, -3473 ],
13:[ -28610, 250034, -3473 ],
14:[ -28719, 252281, -3518 ],
15:[ -29477, 253089, -3518 ],
16:[ -29360, 252953, -3518 ],
17:[ -29689, 252685, -3518 ],
18:[ -29117, 252925, -3518 ],
19:[ -23496, 251924, -3364 ],
20:[ -23812, 251522, -3368 ],
21:[ -25035, 251961, -3295 ],
22:[ -23869, 252040, -3306 ],
23:[ -24689, 252583, -3043 ],
24:[ -23887, 253034, -3038 ],
25:[ -27273, 251871, -3518 ],
26:[ -27211, 251139, -3518 ],
27:[ -28683, 251590, -3518 ],
28:[ -29615, 253045, -3518 ],
29:[ -29345, 252660, -3518 ],
30:[ -29145, 252709, -3518 ], }

LOCS = [
[4276, 237245, -3310],
[11437, 236788, -1949],
[7647, 235672, -1977], 
[1882, 233520, -3315]
]

def changeChimeraSpawnState(booleanValue):
    worldObjects = SpawnTable.getInstance().getSpawnTable().values()
    for i in worldObjects:
        npcId = i.getNpcid()
        if npcId in chimeras:
            xx, yy, zz = i.getLocx(), i.getLocy(), i.getLocz()
            if booleanValue == 1:
                respTime = i.getRespawnDelay()
                headg = i.getHeading()
                newNpc = HellboundManager.getInstance().addSpawn(npcId, xx, yy, zz, headg, respTime)
            if booleanValue == 0:
                i.stopRespawn()
                curNpc = i.getLastSpawn()
                curNpc.onDecay()

def changeBoxesSpawnState(booleanValue):
    worldObjects = SpawnTable.getInstance().getSpawnTable().values()
    for i in worldObjects:
        npcId = i.getNpcid()
        if npcId == 32361:
            xx, yy, zz = i.getLocx(), i.getLocy(), i.getLocz()
            if booleanValue == 1:
                respTime = i.getRespawnDelay()
                headg = i.getHeading()
                newNpc = HellboundManager.getInstance().addSpawn(npcId, xx, yy, zz, headg, respTime)
            if booleanValue == 0:
                i.stopRespawn()
                curNpc = i.getLastSpawn()
                curNpc.onDecay()

class Leveling_System (JQuest):

    def __init__(self, id, name, descr):
        JQuest.__init__(self, id, name, descr)
        self.hellboundTrust = 0
        self.hellboundLevel = 0
        self.hellboundMobs = {
                22320: {'points':10},
                22321: {'points':10},
                22324: {'points':1},
                22325: {'points':1},
                22327: {'points':3},
                22328: {'points':3},
                22329: {'points':3},
        }
        self.hellboundMobs1 = {
                22322: {'points':-10},
                22323: {'points':-10},
                22342: {'points':3},
                22343: {'points':3},
                22361: {'points':20},
                22449: {'points':50},
                22450: {'points':-10},
                25536: {'points':200}
        }
        self.buronloc = []
        self.kiefloc = []
        self.keltasmin = []
        self.keltasloc = []
        self.Keltas = 0
        self.hellboundLevel = HellboundManager.getInstance().getLevel()
        self.hellboundTrust = HellboundManager.getInstance().getTrust()
        if self.hellboundLevel >= 2:
            newFalk = self.addSpawn(32297, -19904, 250016, -3240, 12288, False, 0)
        if self.hellboundLevel < 7:
            changeChimeraSpawnState(0)
        if self.hellboundLevel < 5:
            self.buronloc = []
            self.kiefloc = []
            oldKief = self.addSpawn(Kief, -21271, 250238, -3314, 16384, False, 0)
            oldBuron = self.addSpawn(Buron, -11173, 236537, -3236, 41760, False, 0)
            self.buronloc.append(oldBuron)
            self.kiefloc.append(oldKief)
        if self.hellboundLevel >= 5:
            newSolomon = self.addSpawn(Solomon, -28916, 249381, -3472, 0, False, 0)
            newTraitor = self.addSpawn(Traitor, -27352, 252387, -3520, 5416, False, 0)
            newKief = self.addSpawn(Kief, -28357, 248993, -3472, 16384, False, 0)
            newBuron = self.addSpawn(Buron, -28567, 248994, -3472, 16384, False, 0)
        if self.hellboundLevel < 6:
            changeBoxesSpawnState(0)
        if self.hellboundLevel < 7:
            changeChimeraSpawnState(0)
        if self.hellboundLevel >= 3 and self.hellboundLevel < 5:
            self.keltasloc = []
            xx, yy, zz = KeltasSpawn
            newKeltas = self.addSpawn(Keltas, xx, yy, zz, 0, False, 0)
            self.keltasloc.append(newKeltas)
            self.keltasmin = []
            for i in range(31):
                xx, yy, zz = KeltasMinionsSpawns[i]
                RndMobSpawn = KeltasMinions[Rnd.get(len(KeltasMinions))]
                newKeltasMinion = self.addSpawn(RndMobSpawn, xx, yy, zz, 0, False, 0)
                self.keltasmin.append(newKeltasMinion)
        if self.hellboundLevel == 4:
            newDerek = self.addSpawn(ghostofderek, -28058, 256885, -1934, 0, False, 0)
        if self.hellboundLevel >= 7:
            worldObjects = SpawnTable.getInstance().getSpawnTable().values()
            for i in worldObjects:
                npcId = i.getNpcid()
                if npcId == WLGuard:
                    xx, yy, zz = i.getLocx(), i.getLocy(), i.getLocz()
                    i.stopRespawn()
                    curNpc = i.getLastSpawn()
                    curNpc.onDecay()
        self.startQuestTimer("LevelCheck", 60000, None, None, True)

    def onAdvEvent (self, event, npc, player) :
        if npc:
            npcId = npc.getNpcId()
            npcObjId = npc.getObjectId()
        if event == "keltasRespawn":
            if self.hellboundLevel >= 5: return
            self.keltasloc = []
            xx, yy, zz = KeltasSpawn
            newKeltas = self.addSpawn(Keltas, xx, yy, zz, 0, False, 0)
            self.keltasloc.append(newKeltas)
            self.keltasmin = []
            self.Keltas = 0
            for i in range(31):
                xx, yy, zz = KeltasMinionsSpawns[i]
                RndMobSpawn = KeltasMinions[Rnd.get(len(KeltasMinions))]
                newKeltasMinion = self.addSpawn(RndMobSpawn, xx, yy, zz, 0, False, 0)
                self.keltasmin.append(newKeltasMinion)
        if event == "LevelCheck":
              HellboundManager.getInstance().checkHellboundLevel()
              newLevel = HellboundManager.getInstance().getLevel()
              self.hellboundTrust = HellboundManager.getInstance().getTrust()
              if newLevel > self.hellboundLevel:
                  self.hellboundLevel = newLevel
                  print "---- Hellbound achieved Level " + str(self.hellboundLevel)
                  announce = "Hellbound now has now reached level: " + str(self.hellboundLevel)
                  Announcements.getInstance().announceToAll(announce)
                  if newLevel == 2:
                      newFalk = self.addSpawn(32297, -19904, 250016, -3240, 12288, False, 0)
                  if newLevel == 3:
                      xx, yy, zz = KeltasSpawn
                      newKeltas = self.addSpawn(Keltas, xx, yy, zz, 0, False, 0)
                      self.keltasloc.append(newKeltas)
                      self.Keltas = 0
                      self.keltasmin = []
                      for i in range(31):
                          xx, yy, zz = KeltasMinionsSpawns[i]
                          RndMobSpawn = KeltasMinions[Rnd.get(len(KeltasMinions))]
                          newKeltasMinion = self.addSpawn(RndMobSpawn, xx, yy, zz, 0, False, 0)
                          self.keltasmin.append(newKeltasMinion)
                  if newLevel == 4:
                      newDerek = self.addSpawn(ghostofderek, -28058, 256885, -1934, 0, False, 0)
                  if newLevel == 5:
                      for i in self.buronloc:
                          i.deleteMe()
                      for i in self.kiefloc:
                          i.deleteMe()
                      for i in self.keltasloc:
                          i.deleteMe()
                      for i in self.keltasmin:
                          i.deleteMe()
                      newSolomon = self.addSpawn(Solomon, -28916, 249381, -3472, 0, False, 0)
                      newTraitor = self.addSpawn(Traitor, -27352, 252387, -3520, 5416, False, 0)
                      newKief = self.addSpawn(Kief, -28357, 248993, -3472, 16384, False, 0)
                      newBuron = self.addSpawn(Buron, -28567, 248994, -3472, 16384, False, 0)
                  if newLevel == 6:
                      changeBoxesSpawnState(1)
                  if newLevel == 7:
                      changeChimeraSpawnState(1)
                      LOC = LOCS[Rnd.get(len(LOCS))]
                      CeltrespTime = (18 + Rnd.get(36)) * 100 #between 30m and 1h
                      newCeltus = HellboundManager.getInstance().addSpawn(celtus, LOC[0], LOC[1], LOC[2], 0, CeltrespTime)
                  if newLevel == 8:
                      newCaptain = HellboundManager.getInstance().addSpawn(Captain, 4766, 243995, -1928, 36561, 0)
              if self.hellboundLevel < 7:
                  DoorTable.getInstance().getDoor(20250002).closeMe()
              if self.hellboundLevel >= 7:
                  DoorTable.getInstance().getDoor(20250002).openMe()
              if self.hellboundLevel < 9:
                  DoorTable.getInstance().getDoor(20250001).closeMe()
              if self.hellboundLevel >= 9:
                  DoorTable.getInstance().getDoor(20250001).openMe()

    def onAttack(self, npc, player, damage, isPet, skill):
        id = npc.getNpcId()
        if self.Keltas == 0:
            self.Keltas = 1
            npc.broadcastPacket(CreatureSay(npc.getObjectId(), 1, npc.getName(), "Boys! Show them our power!!!!"))

    def onKill(self, npc, player, isPet):
        id = npc.getNpcId()
        st = player.getQuestState("Leveling_System")
        if not st:
            st = self.newQuestState(player)
        if npc:
           if id == Keltas:
               HellboundManager.getInstance().increaseTrust(100)
               if HellboundManager.getInstance().getLevel() > 4: return
               if HellboundManager.getInstance().getLevel() == 3: respTime = (72 + Rnd.get(144)) * 100 #between 2 and 4 hours respawn retail-like
               if HellboundManager.getInstance().getLevel() == 4: respTime = (288 + Rnd.get(576)) * 100 #between 8 and 16 hours respawn retail-like
               self.startQuestTimer("keltasRespawn", respTime, None, None)
               for i in self.keltasmin:
                   i.deleteMe()
           if id in self.hellboundMobs.keys():
               if HellboundManager.getInstance().getLevel() > 1: return
               HellboundManager.getInstance().increaseTrust(self.hellboundMobs[id]['points'])
           if id in self.hellboundMobs1.keys():
               HellboundManager.getInstance().increaseTrust(self.hellboundMobs1[id]['points'])
           if id == WLGuard:
               if HellboundManager.getInstance().getLevel() == 7:
                   worldObjects = SpawnTable.getInstance().getSpawnTable().values()
                   for i in worldObjects:
                       if i.getNpcid() == WLGuard:
                           i.stopRespawn()

QUEST = Leveling_System(-1, "Leveling_System", "instances")

for i in QUEST.hellboundMobs.keys() :
    QUEST.addKillId(i)

for i in QUEST.hellboundMobs1.keys() :
    QUEST.addKillId(i)

QUEST.addKillId(Keltas)
QUEST.addKillId(WLGuard)
QUEST.addAttackId(Keltas)
