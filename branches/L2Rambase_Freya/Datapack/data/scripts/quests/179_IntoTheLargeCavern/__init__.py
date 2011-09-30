# author L2Rambase Project
import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "179_IntoTheLargeCavern"

#NPC'S
KEKROPUS = 32138
NORNIL = 32258

class Quest (JQuest) :

 def __init__(self,id,name,descr):
	 JQuest.__init__(self,id,name,descr)
 
 def onAdvEvent (self,event,npc, player) :
	htmltext = event
	st = player.getQuestState(qn)
	npcId = npc.getNpcId()
	if npcId == KEKROPUS :
		if event == "32138-03.htm" :
			st.setState(State.STARTED)
			st.set("cond", "1")
			st.playSound("ItemSound.quest_accept")
	elif npcId == NORNIL :
		if event == "32258-08.htm" :
			st.giveItems(391, 1)
			st.giveItems(413, 1)
			st.playSound("ItemSound.quest_finish")
			st.exitQuest(1)
		elif event == "32258-09.htm" :
			st.giveItems(847, 2)
			st.giveItems(890, 2)
			st.giveItems(910, 1)
			st.playSound("ItemSound.quest_finish")
			st.exitQuest(1)
	return htmltext

 def onTalk (self,npc,player):
	npcId = npc.getNpcId()
	htmltext = "<html><body></body></html>"
	st = player.getQuestState(qn)
	if not st : return htmltext
	id = st.getState()
	cond = st.getInt("cond")
	 
	if id == State.COMPLETED :
		htmltext = getAlreadyCompletedMsg(player)
	if id == State.CREATED :
		prevQ = player.getQuestState("178_IconicTrinity")
		if prevQ == null or prevQ.getState() != State.COMPLETED or player.getLevel() < 17 or player.getRace().ordinal() != 5 or player.getClassId().level() != 0 :
			htmltext = "32138-00.htm"
			st.exitQuest(1)
		else :
			htmltext = "32138-01.htm"
	if id == State.STARTED :
		if npcId == KEKROPUS :
			if st.getInt("cond") == 1 :
				htmltext = "32138-03.htm"
		if npcId == NORNIL :
			htmltext = "32258-01.htm"
	return htmltext

QUEST		= Quest(179,qn,"Into the Large Cavern")

QUEST.addStartNpc(KEKROPUS)
QUEST.addTalkId(KEKROPUS)
QUEST.addTalkId(NORNIL)
