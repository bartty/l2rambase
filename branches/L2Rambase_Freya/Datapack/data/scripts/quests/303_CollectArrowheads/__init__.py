# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "303_CollectArrowheads"

ORCISH_ARROWHEAD = 963
ADENA = 57

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [ORCISH_ARROWHEAD]

 def onAdvEvent (self,event,npc, player) :
    htmltext = event
    st = player.getQuestState(qn)
    if not st : return
    if event == "30029-04.htm" :
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
     if player.getLevel() >= 10 :
        htmltext = "30029-03.htm"
     else:
        htmltext = "30029-02.htm"
        st.exitQuest(1)
   else :
     if st.getQuestItemsCount(ORCISH_ARROWHEAD)<10 :
       htmltext = "30029-05.htm"
     else :
       st.giveItems(ADENA,1000)
       st.takeItems(ORCISH_ARROWHEAD,-1)
       st.playSound("ItemSound.quest_finish")
       st.addExpAndSp(2000,0)
       htmltext = "30029-06.htm"
       st.exitQuest(1)
   return htmltext

 def onKill(self,npc,player,isPet):
   st = player.getQuestState(qn)
   if not st : return 
   if st.getState() != State.STARTED : return 
   
   count=st.getQuestItemsCount(ORCISH_ARROWHEAD)
   if count<10 :
     if count < 8 :
       st.giveItems(ORCISH_ARROWHEAD,2)
       st.playSound("ItemSound.quest_itemget")
     else :
       st.giveItems(ORCISH_ARROWHEAD,2)
       st.set("cond","2") 
       st.playSound("ItemSound.quest_middle")
   return

QUEST       = Quest(303,qn,"Collect Arrowheads")

QUEST.addStartNpc(30029)

QUEST.addTalkId(30029)

QUEST.addKillId(20361)