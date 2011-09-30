# pmq
import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "10272_LightFragment"

#NPC
NPC = 32560

class Quest (JQuest) :

	def __init__(self,id,name,descr):
		JQuest.__init__(self,id,name,descr)
		self.questItemIds = []

	def onAdvEvent (self,event,npc, player) :
		htmltext = event
		st = player.getQuestState(qn)
		if not st : return

	def onTalk (self,npc,player):
		htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
		st = player.getQuestState(qn)
		if not st : return htmltext

		npcId = npc.getNpcId()
		id = st.getState()
		cond = st.getInt("cond")

		if id == State.COMPLETED :
			if npcId == 32560 :
				htmltext = "<html><body>Quest already completed.</body></html>"
		elif id == State.CREATED and npcId == 32560 :
			if player.getLevel() >= 75 :
				htmltext = "32560-01.htm"
				st.exitQuest(1) 
			else :
				htmltext = "32560-00.htm"
				st.exitQuest(1) 
		return htmltext

QUEST		= Quest(10272,qn,"Light Fragment")

QUEST.addStartNpc(NPC)

QUEST.addTalkId(NPC)
