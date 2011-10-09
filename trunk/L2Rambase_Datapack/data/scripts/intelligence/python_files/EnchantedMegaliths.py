import sys
from java.lang                                   import System
from l2.brick.gameserver.ai                 import CtrlIntention
from l2.brick.gameserver.instancemanager    import HellboundManager
from l2.brick.gameserver.model.quest        import State
from l2.brick.gameserver.model.quest        import QuestState
from l2.brick.gameserver.model.quest.jython import QuestJython as JQuest
from l2.brick.util                          import Rnd

#looks like a green heart of warding called Naia Failan
Megalith    = 18484
Hellinark   = 22326
KillMobsIds = [18484, 22326]

HellinarkSpawn = []

MegalithSpawnLocs = {
0:[ -24542, 245792, -3133, 19078 ],
1:[ -23839, 246056, -3133, 17772 ],
2:[ -23713, 244358, -3133, 53369 ],
3:[ -23224, 244524, -3133, 57472 ],
4:[ -24709, 245186, -3133, 63974 ],
5:[ -24394, 244379, -3133, 5923 ]
}

HellinarkSpawn = [-24430, 244764, -3133]

class Hellbound_megaliths (JQuest):

	# init function.  Add in here variables that you'd like to be inherited by subclasses (if any) 
	def __init__(self, id, name, descr):
		JQuest.__init__(self, id, name, descr)
		self.SpawnedMobs = {}
		self.hellboundLevel = HellboundManager.getInstance().getLevel()
		if self.hellboundLevel < 6:
			self.startQuestTimer("levelCheckMeg", 60000, None, None, True)
		megaliths = 6
		try:
			megaliths = int(self.loadGlobalQuestVar("megaliths_portals"))
		except:
			pass
		self.saveGlobalQuestVar("megaliths_portals", str(megaliths))
		if megaliths <= 0:
			HellboundManager.getInstance().setMegalithsCompleted(1)
		if self.hellboundLevel == 6:
			# spawn the megaliths and hellinark
			xx2, yy2, zz2 = HellinarkSpawn
			newHellinark = HellboundManager.getInstance().addSpawn(Hellinark, xx2, yy2, zz2, 5000, 0)
			if megaliths > 0:
				for i in range(megaliths):
					self.SpawnedMobs[i] = []
					xx, yy, zz, headg = MegalithSpawnLocs[i]
					respawnTime = Rnd.get(90, 180) # between 90 seconds and 3 minutes
					newMegalith = HellboundManager.getInstance().addSpawn(Megalith, xx, yy, zz, headg, respawnTime)
					self.SpawnedMobs[i].append(newMegalith)

	def onAdvEvent(self, event, npc, player) :
		if event == "levelCheckMeg":
			hellboundLevel = HellboundManager.getInstance().getLevel()
			if hellboundLevel > self.hellboundLevel and hellboundLevel == 6:
				self.hellboundLevel = 6
				megaliths = 6
				try:
					megaliths = int(self.loadGlobalQuestVar("megaliths_portals"))
				except:
					pass
				self.saveGlobalQuestVar("megaliths_portals", str(megaliths))
				if megaliths <= 0:
					HellboundManager.getInstance().setMegalithsCompleted(1)
					for i in self.SpawnedMobs.keys():
						for n in self.SpawnedMobs[i]:
							n.stopRespawn()
				if self.hellboundLevel == 6:
					self.cancelQuestTimers("levelCheckMeg")
					xx2, yy2, zz2 = HellinarkSpawn
					newHellinark = HellboundManager.getInstance().addSpawn(Hellinark, xx2, yy2, zz2, 18000, 0)
					if megaliths > 0:
						for i in range(megaliths):
							self.SpawnedMobs[i] = []
							xx, yy, zz, headg = MegalithSpawnLocs[i]
							respawnTime = (Rnd.get(90, 180) * 1000) # between 90 seconds and 3 minutes
							newMegalith = HellboundManager.getInstance().addSpawn(Megalith, xx, yy, zz, headg, respawnTime)
							self.SpawnedMobs[i].append(newMegalith)

	def onKill(self, npc, player, isPet):
		hellboundLevel = HellboundManager.getInstance().getLevel()
		if hellboundLevel != 6: return
		id = npc.getNpcId()
		objId = npc.getObjectId()
		if id == Hellinark:
			HellboundManager.getInstance().increaseTrust(10000)
			HellboundManager.getInstance().changeLevel(7)
			HellboundManager.getInstance().setMegalithsCompleted(1)
			return
		#if id in MegalithMinions:
		if id == Megalith:
			delNum = 4000
			mobsKilled = 0
			try:
				#### add the mobs to the leveling system(points)
				mobsKilled = int(self.loadGlobalQuestVar("megaliths_killed"))
			except:
				pass
			mobsKilled += 1
			if mobsKilled >= 500:
				mobsKilled = 0
				# actual number saved will be 1++, when in the array it will be 0++
				megaliths = int(self.loadGlobalQuestVar("megaliths_portals"))
				delNum = megaliths - 1
				for i in self.SpawnedMobs[delNum]:
					i.stopRespawn()
				megaliths = megaliths - 1
				if megaliths < 0:
					return
				self.saveGlobalQuestVar("megaliths_portals", str(megaliths))
			megalithCompleted = HellboundManager.getInstance().checkMegalithsCompleted()
			if megalithCompleted : return
			self.saveGlobalQuestVar("megaliths_killed", str(mobsKilled))

# Quest class and state definition
QUEST = Hellbound_megaliths(-1, "Hellbound_megaliths", "ai")

for i in KillMobsIds :
	QUEST.addKillId(i)

QUEST.addAttackId(Megalith)