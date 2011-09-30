# L2Rambase Project
# checked 12.08.2010

import sys
from l2.rambase import Config
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "617_GatherTheFlames"

TORCH = 7264

HILDA = 31271
VULCAN = 31539
ROONEY = 32049

DROPLIST = {21381:99,21653:99,21387:99,21655:99,21390:99,21656:99,21389:99,21388:99,\
            21383:99,21392:99,21382:99,21654:99,21384:99,21394:99,21395:99,21385:99,\
            21391:99,21393:58,21657:57,21386:99,21652:99,21378:99,21376:99,21377:99,\
            21379:99,21380:99}

REWARDS = [ 6881,6883,6885,6887,6891,6893,6895,6897,6899,7580 ]
REWARDS2= [ 6882,6884,6886,6888,6892,6894,6896,6898,6900,7581 ]

#Change this value to 1 if you wish 100% recipes, default 60%
ALT_RP100=0

class Quest (JQuest) :

 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [TORCH]

 def onEvent (self,event,st) :
     htmltext = event
     torches = st.getQuestItemsCount(TORCH)
     if event == "31539-03.htm" :
       if st.getPlayer().getLevel() >= 74 :
         st.set("cond","1")
         st.setState(State.STARTED)
         st.playSound("ItemSound.quest_accept")
       else :
         htmltext = "31539-02.htm"
         st.exitQuest(1)
     elif event == "31231-03.htm" :
       st.set("cond","1")
       st.setState(State.STARTED)
       st.playSound("ItemSound.quest_accept")
     elif event == "31539-05.htm" and torches >= 1000 :
       htmltext = "31539-07.htm"
       st.takeItems(TORCH,1000)
       if ALT_RP100 == 1:
         st.giveItems(REWARDS2[st.getRandom(len(REWARDS2))],1)
       else:
         st.giveItems(REWARDS[st.getRandom(len(REWARDS))],1)
     elif event == "31539-08.htm" :
       st.takeItems(TORCH,-1)
       st.exitQuest(1)
     elif event.isdigit() and int(event) in REWARDS :
       if torches >= 1200 :
          st.takeItems(TORCH,1200)
          if ALT_RP100 == 1:
            st.giveItems(int(event)+1,1)
          else:
            st.giveItems(int(event),1)
          htmltext = None
       else :
          htmltext = "Incorrect item count"
     return htmltext    

 def onTalk (self,npc,player):
     htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
     st = player.getQuestState(qn)
     if not st : return htmltext
     id = st.getState()
     cond = st.getInt("cond")
     torches = st.getQuestItemsCount(TORCH)
     npcId = npc.getNpcId()
     if npcId == VULCAN :
       if id == State.CREATED :
         if player.getLevel() < 74 :
            st.exitQuest(1)
            htmltext = "31539-02.htm"
         else :
            htmltext = "31539-01.htm"
       elif torches < 1000 :
         htmltext = "31539-05.htm"
       else :
         htmltext = "31539-04.htm"
     elif npcId == HILDA :
       if id == State.CREATED :
         if player.getLevel() < 74 :
            st.exitQuest(1)
            htmltext = "31231-01.htm"
         else :
            htmltext = "31231-02.htm"
       else:
         htmltext = "31231-04.htm"
     elif npcId == ROONEY and id == State.STARTED :
       if torches >= 1200 :
          htmltext = "32049-01.htm"
       else :
          htmltext = "32049-02.htm"
     return htmltext

 def onKill(self,npc,player,isPet):
     partyMember = self.getRandomPartyMemberState(player, State.STARTED)
     if not partyMember: return
     st = partyMember.getQuestState(qn)
     if not st : return
     torches = st.getQuestItemsCount(TORCH)
     st.giveItems(TORCH,2)
     if divmod(torches+1,1000)[1] == 0  or divmod(torches+1,1200)[1] == 0 :
        st.playSound("ItemSound.quest_middle")
     else :
        st.playSound("ItemSound.quest_itemget")
     return

QUEST       = Quest(617, qn, "Gather The Flames")

QUEST.addStartNpc(VULCAN)
QUEST.addStartNpc(HILDA)

QUEST.addTalkId(VULCAN)
QUEST.addTalkId(ROONEY)
QUEST.addTalkId(HILDA)

for mob in DROPLIST.keys() :
  QUEST.addKillId(mob)