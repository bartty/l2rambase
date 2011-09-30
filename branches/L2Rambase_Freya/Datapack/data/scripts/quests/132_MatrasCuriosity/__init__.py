# Update by pmq 08-07-2010

import sys
from l2.rambase.gameserver.instancemanager    import HellboundManager
from l2.rambase.gameserver.model.quest        import State
from l2.rambase.gameserver.model.quest        import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "132_MatrasCuriosity"

# NPC
MATRAS             = 32245

# MOB
DEMONPRINCE        = 25540
RANKU              = 25542

# ITEM
RANKUSBLUEPRINT    = 9800
PRINCESBLUEPRINT   = 9801

ROUGHOREOFFIRE     = 10521
ROUGHOREOFWATER    = 10522
ROUGHOREOFTHEEARTH = 10523
ROUGHOREOFWIND     = 10524
ROUGHOREOFDARKNESS = 10525
ROUGHOREOFDIVINITY = 10526

class Quest (JQuest) :

	def __init__(self, id, name, descr) : 
		JQuest.__init__(self, id, name, descr)
		self.questItemIds = [PRINCESBLUEPRINT,RANKUSBLUEPRINT]

	def onAdvEvent (self, event, npc, player) :
		st = player.getQuestState(qn)
		if not st: return
		htmltext = event
		if event == "32245-03.htm" :
			st.set("cond","1")
			st.playSound("ItemSound.quest_accept")
			st.setState(State.STARTED)
		elif event == "32245-07.htm" :
			st.giveItems(ROUGHOREOFFIRE,1)
			st.giveItems(ROUGHOREOFWATER,1)
			st.giveItems(ROUGHOREOFTHEEARTH,1)
			st.giveItems(ROUGHOREOFWIND,1)
			st.giveItems(ROUGHOREOFDARKNESS,1)
			st.giveItems(ROUGHOREOFDIVINITY,1)
			st.giveItems(57,65884)
			st.addExpAndSp(50541,5094)
			st.unset("cond")
			st.exitQuest(False)
			st.playSound("ItemSound.quest_finish")
		return htmltext

	def onTalk (self, npc, player) :
		htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
		st = player.getQuestState(qn)
		if not st : return htmltext

		npcId = npc.getNpcId()
		id = st.getState()
		cond = st.getInt("cond") 

		if id == State.COMPLETED :
			if npcId == MATRAS :
				htmltext = "<html><body>這是已經完成的任務。</body></html>"
		elif id == State.CREATED :
			if npcId == MATRAS and cond == 0 :
				hellboundLevel = HellboundManager.getInstance().getLevel()
				if hellboundLevel < 9:
					htmltext="32245-00.htm"
					st.exitQuest(1)
				if player.getLevel() >= 76 :
					htmltext="32245-01.htm"
				else :
					htmltext="32245-02.htm"
					st.exitQuest(1)
		elif id == State.STARTED :
			if npcId == MATRAS :
				if cond == 1 :
					htmltext = "32245-04.htm"
				elif cond == 2 :
					if st.getQuestItemsCount(PRINCESBLUEPRINT) >= 1 and st.getQuestItemsCount(RANKUSBLUEPRINT) >= 1 :
						htmltext = "32245-05.htm"
						st.takeItems(RANKUSBLUEPRINT,-1)
						st.takeItems(PRINCESBLUEPRINT,-1)
						st.set("cond","3")
						st.playSound("ItemSound.quest_middle")
				elif cond == 3 :
					htmltext = "32245-06.htm"
		return htmltext

	def onKill(self, npc, player, isPet) :
		st = player.getQuestState(qn)
		if not st : return

		npcId = npc.getNpcId()
		if npcId == DEMONPRINCE :
			party = player.getParty()
			if party :
				PartyQuestMembers = []
				for player1 in party.getPartyMembers().toArray() :
					st1 = player1.getQuestState(qn)
					if st1 :
						if st1.getState() == State.STARTED and st1.getQuestItemsCount(PRINCESBLUEPRINT) == 0 :
							PartyQuestMembers.append(st1)
							st.giveItems(PRINCESBLUEPRINT,1)
							st.playSound("ItemSound.quest_itemget")
			else :
				st = player.getQuestState(qn)
				if not st : return
				if st.getState() == State.STARTED and st.getQuestItemsCount(PRINCESBLUEPRINT) == 0:
					st.giveItems(PRINCESBLUEPRINT,1)
					st.playSound("ItemSound.quest_itemget")
		elif npcId == RANKU :
			party = player.getParty()
			if party :
				PartyQuestMembers = []
				for player1 in party.getPartyMembers().toArray() :
					st2 = player1.getQuestState(qn)
					if st2 :
						if st2.getState() == State.STARTED and st2.getQuestItemsCount(RANKUSBLUEPRINT) == 0 :
							PartyQuestMembers.append(st2)
							st.giveItems(RANKUSBLUEPRINT,1)
							st.playSound("ItemSound.quest_itemget")
			else :
				st = player.getQuestState(qn)
				if not st : return
				if st.getState() == State.STARTED and st.getQuestItemsCount(RANKUSBLUEPRINT) == 0:
					st.giveItems(RANKUSBLUEPRINT,1)
					st.playSound("ItemSound.quest_itemget")
		if st.getQuestItemsCount(PRINCESBLUEPRINT) >= 1 and st.getQuestItemsCount(RANKUSBLUEPRINT) >= 1 :
			st.set("cond","2")
			st.playSound("ItemSound.quest_middle")
		return

QUEST		= Quest(132, qn, "Matras Curiosity")

QUEST.addStartNpc(MATRAS)

QUEST.addTalkId(MATRAS)

QUEST.addKillId(DEMONPRINCE)
QUEST.addKillId(RANKU)