# L2Rambase Project
# need check drop chance
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "264_KeenClaws"

WOLF_CLAW = 1367

DROP={20003:[[0,20,8],[0,20,2]],20456:[[0,20,2],[0,20,1]]}

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [WOLF_CLAW]

 def onAdvEvent (self,event,npc, player) :
    htmltext = event
    st = player.getQuestState(qn)
    if not st : return
    if event == "30136-03.htm" :
      st.set("cond","1")
      st.setState(State.STARTED)
      st.playSound("ItemSound.quest_accept")
    return htmltext

 def onTalk (self,npc,player):
   htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
   st = player.getQuestState(qn)
   if not st : return htmltext

   npcId = npc.getNpcId()
   id = st.getState()

   if id == State.CREATED :
     st.set("cond","0")
   if st.getInt("cond")==0 :
     if player.getLevel() >= 3 :
       htmltext = "30136-02.htm"
     else:
       htmltext = "30136-01.htm"
       st.exitQuest(1)
   else:
     count=st.getQuestItemsCount(WOLF_CLAW)
     if count<50 :
       htmltext = "30136-04.htm"
     else :
       st.takeItems(WOLF_CLAW,-1)
       if st.getRandom(17) < 6 :
          st.giveItems(5140,1)
       else :
          st.giveItems(734,1)
       htmltext = "30136-05.htm"
       st.exitQuest(1)
       st.playSound("ItemSound.quest_finish")
   return htmltext

 def onKill(self,npc,player,isPet):
   st = player.getQuestState(qn)
   if not st : return 
   if st.getState() != State.STARTED : return 
   
   if st.getInt("cond") == 1:
      npcId = npc.getNpcId()
      count=st.getQuestItemsCount(WOLF_CLAW)
      if count+5==50:
        st.playSound("ItemSound.quest_middle")
        st.set("cond","2")
      else :
        st.playSound("ItemSound.quest_itemget")
      st.giveItems(WOLF_CLAW,5)
   return

QUEST       = Quest(264,qn,"Keen Claws")

QUEST.addStartNpc(30136)

QUEST.addTalkId(30136)

QUEST.addKillId(20003)
QUEST.addKillId(20456)