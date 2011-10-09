import sys
from java.lang                                     import System
from l2.brick.gameserver.ai                   import CtrlIntention
from l2.brick.gameserver.datatables           import DoorTable
from l2.brick.gameserver.instancemanager      import HellboundManager
from l2.brick.gameserver.model.actor.instance import L2NpcInstance
from l2.brick.gameserver.model.quest          import State
from l2.brick.gameserver.model.quest          import QuestState
from l2.brick.gameserver.model.quest.jython   import QuestJython as JQuest
from l2.brick.util                            import Rnd

Captain          = 18466
EnceinteDefender = 22358

CaptainLoc  = [4881, 244038, -1927]
MinionsLocs = {
0: [5231, 243750, -1925],
1: [4866, 244463, -1925],
2: [4680, 244813, -1593],
3: [4788, 244792, -1589],
4: [5453, 243425, -1586],
5: [5496, 243558, -1588]
}

class Hellbound_outpost (JQuest):

	def __init__(self, id, name, descr):
		JQuest.__init__(self, id, name, descr)
		self.captainState = 0
		hellboundLevel = HellboundManager.getInstance().getLevel()
		if hellboundLevel != 8: return
		xx, yy, zz = CaptainLoc
		newCaptain = HellboundManager.getInstance().addSpawn(Captain, xx, yy, zz, 36561, 0)

	def onKill(self, npc, player, isPet):
		id = npc.getNpcId()
		if id == Captain:
			HellboundManager.getInstance().changeLevel(9)
			DoorTable.getInstance().getDoor(20250001).openMe()

	def onAttack (self, npc, player, damage, isPet, skill):
		id = npc.getNpcId()
		if self.captainState == 0:
			self.captainState = 1
			self.startQuestTimer("attackPlayer", 100, npc, player)

	def onFirstTalk (self, npc, player):
		npcId = npc.getNpcId()
		if npc.isInCombat() or self.captainState >= 1: return
		htmltext = "<html><body>Outpost Captain:<br>Hello young one.<br1>Please come closer so I can take a good look at you.</body></html>"
		self.startQuestTimer("attackPlayer", 5000, npc, player)
		self.startQuestTimer("checkState", 10000, npc, None)
		self.captainState = 1
		return htmltext

	def onAdvEvent (self, event, npc, player) :
		if event == "checkState":
			if self.captainState == 2: return
			self.startQuestTimer("checkState", 10000, npc, None)
			try:
				if not npc.isInCombat():
					self.captainState = 0
			except:
				pass
		if event == "attackPlayer":
			npc.setRunning()
			npc.addDamageHate(player, 0, 999)
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player)
			if self.captainState != 2:
				for i in range(6):
					xx, yy, zz = MinionsLocs[i]
					tempMinion = self.addSpawn(EnceinteDefender, xx, yy, zz, 0, False, 900000)
					tempMinion.addDamageHate(player, 0, 999)
					tempMinion.setIsNoRndWalk(1)
					tempMinion.setRunning()
					tempMinion.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player)
					self.captainState = 2

# Quest class and state definition
QUEST = Hellbound_outpost(-1, "Hellbound_outpost", "ai")

QUEST.addStartNpc(Captain)
QUEST.addFirstTalkId(Captain)
QUEST.addTalkId(Captain)
QUEST.addKillId(Captain)
QUEST.addAttackId(Captain)