# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest              import State
from l2.rambase.gameserver.model.quest              import QuestState
from l2.rambase.gameserver.model.quest.jython       import QuestJython as JQuest
from l2.rambase.util                                import Rnd

qn = "10281_MutatedKaneusRune"

#NPCs
Mathias = 31340
Kayan = 31335
WhiteAllosce = 18577

#items
Tissue = 13840


class Quest (JQuest) :
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [Tissue]

    def onAdvEvent (self,event,npc, player) :
        htmltext = event
        st = player.getQuestState(qn)
        if not st : return
        if event == "31340-03.htm" :
            st.set("cond","1")
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
        elif event == "31335-02.htm" :
            st.giveItems(57,360000)
            st.unset("cond")
            st.exitQuest(False)
            st.playSound("ItemSound.quest_finish")
        return htmltext

    def onTalk (self,npc,player):
        htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
        st = player.getQuestState(qn)
        if not st : return htmltext
        npcId = npc.getNpcId()
        id = st.getState()
        if id == State.COMPLETED :
            if npcId == Mathias :
                htmltext = "31340-0a.htm"
        elif id == State.CREATED and npcId == Mathias:
            if player.getLevel() >= 68 :
                htmltext = "31340-01.htm"
            else :
                htmltext = "31340-00.htm"
        else :
            if npcId == Mathias :
                if st.getQuestItemsCount(Tissue) == 0:
                   htmltext = "31340-04.htm"
                elif st.getQuestItemsCount(Tissue) > 0:
                   htmltext = "31340-05.htm"
            elif npcId == Kayan:
                if player.getLevel() < 68:
                   return htmltext
                if st.getQuestItemsCount(Tissue) > 0:
                   htmltext = "31335-01.htm"
                else :
                   htmltext = "31335-01a.htm"
        return htmltext

    def onKill(self,npc,player,isPet):
        party = player.getParty()
        if party :
            PartyQuestMembers = []
            for player1 in party.getPartyMembers().toArray() :
                st1 = player1.getQuestState(qn)
                if st1 :
                    if st1.getState() == State.STARTED and st1.getQuestItemsCount(Tissue) == 0 :
                        PartyQuestMembers.append(st1)
            if len(PartyQuestMembers) == 0 : return
            st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
            st.giveItems(Tissue,1)
            st.playSound("ItemSound.quest_middle")
        else : # in case that party members disconnected or so
            st = player.getQuestState(qn)
            if not st : return
            if st.getState() == State.STARTED and st.getQuestItemsCount(Tissue) == 0:
                st.giveItems(Tissue,1)
                st.playSound("ItemSound.quest_middle")
        return

QUEST       = Quest(10281,qn,"Mutated Kaneus")

QUEST.addStartNpc(Mathias)
QUEST.addTalkId(Mathias)
QUEST.addTalkId(Kayan)
QUEST.addKillId(WhiteAllosce)