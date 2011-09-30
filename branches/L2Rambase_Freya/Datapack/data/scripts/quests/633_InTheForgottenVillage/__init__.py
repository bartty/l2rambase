# L2Rambase Project
# checked 12.08.2010

import sys
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest

qn = "633_InTheForgottenVillage"

#NPC
MINA = 31388
#ITEMS
RIB_BONE = 7544
Z_LIVER = 7545
# Mobid : DROP CHANCES
DAMOBS = {
    21557 : 999,#Bone Snatcher
    21558 : 999,#Bone Snatcher
    21559 : 999,#Bone Maker
    21560 : 999,#Bone Shaper
    21563 : 999,#Bone Collector
    21564 : 999,#Skull Collector
    21565 : 999,#Bone Animator
    21566 : 999,#Skull Animator
    21567 : 999,#Bone Slayer
    21572 : 999,#Bone Sweeper
    21574 : 999,#Bone Grinder
    21575 : 999,#Bone Grinder
    21580 : 999,#Bone Caster
    21581 : 999,#Bone Puppeteer
    21583 : 999,#Bone Scavenger
    21584 : 999 #Bone Scavenger
    }
UNDEADS = {
    21553 : 999,#Trampled Man
    21554 : 999,#Trampled Man
    21561 : 999,#Sacrificed Man
    21578 : 999,#Behemoth Zombie
    21596 : 999,#Requiem Lord
    21597 : 999,#Requiem Behemoth
    21598 : 999,#Requiem Behemoth
    21599 : 999,#Requiem Priest
    21600 : 999,#Requiem Behemoth
    21601 : 999 #Requiem Behemoth
    }

class Quest (JQuest):
    
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [RIB_BONE, Z_LIVER]
    
    def onAdvEvent (self,event,npc, player) :
        htmltext = event
        st = player.getQuestState(qn)
        if not st : return
        if event == "accept" :
            st.set("cond","1")                        
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
            htmltext = "31388-04.htm"
        if event == "quit":
            st.takeItems(RIB_BONE, -1)
            st.playSound("ItemSound.quest_finish")
            htmltext = "31388-10.htm"
            st.exitQuest(1)
        elif event == "stay":
            htmltext = "31388-07.htm"
        elif event == "reward":
            if st.getInt("cond") == 2:
                if st.getQuestItemsCount(RIB_BONE) >= 200:
                    st.takeItems(RIB_BONE, 200)
                    st.rewardItems(57, 25000)
                    st.addExpAndSp(305235, 0)
                    st.playSound("ItemSound.quest_finish")
                    st.set("cond","1")
                    htmltext = "31388-08.htm"
                else :
                    htmltext = "31388-09.htm"
        return htmltext
    

    def onTalk (self,npc,player):        
        htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
        st = player.getQuestState(qn)
        if not st: return
        npcId = npc.getNpcId()
        if npcId == MINA:
            id = st.getState()
            cond = st.getInt("cond")
            if id == State.CREATED:
                if st.getPlayer().getLevel() > 64:
                    htmltext = "31388-01.htm"
                else:
                    htmltext = "31388-03.htm"
                    st.exitQuest(1)        
            elif cond == 1:
                htmltext = "31388-06.htm"            
            elif cond == 2:
                htmltext = "31388-05.htm"                
        return htmltext

    def onKill(self,npc,player,isPet):        
        npcId = npc.getNpcId()
        if npcId in UNDEADS.keys():            
            partyMember = self.getRandomPartyMemberState(player, State.STARTED)
            if not partyMember: return
            st = partyMember.getQuestState(qn)
            if not st : return
            st.giveItems(Z_LIVER, 3)  
            st.playSound("ItemSound.quest_itemget")  
        elif npcId in DAMOBS.keys():
            partyMember = self.getRandomPartyMember(player, "cond", "1")
            if not partyMember: return                
            st = partyMember.getQuestState(qn)
            if not st : return
            st.giveItems(RIB_BONE, 5)  
            if st.getQuestItemsCount(RIB_BONE) == 200:  
                st.set("cond","2")  
                st.playSound("ItemSound.quest_middle")  
            else:  
                st.playSound("ItemSound.quest_itemget") 
        return        

QUEST       = Quest(633, qn, "In The Forgotten Village")

for i in DAMOBS.keys():
    QUEST.addKillId(i)
for i in UNDEADS.keys():
    QUEST.addKillId(i)
    
QUEST.addStartNpc(MINA)
QUEST.addTalkId(MINA)