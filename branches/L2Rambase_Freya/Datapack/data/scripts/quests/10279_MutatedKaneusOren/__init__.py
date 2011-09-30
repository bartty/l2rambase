# L2Rambase Project
# checked 11.08.2010

import sys
from l2.rambase.gameserver.model.quest              import State
from l2.rambase.gameserver.model.quest              import QuestState
from l2.rambase.gameserver.model.quest.jython       import QuestJython as JQuest
from l2.rambase.util                                import Rnd

qn = "10279_MutatedKaneusOren"

#NPCs
Mouen = 30196
Rovia = 30189
KaimAbigore = 18566
KnightMontagnar = 18568

#items
KaimAbigoreTissue = 13836
KnightMontagnarTissue = 13837


class Quest (JQuest):
    def __init__(self,id,name,descr):
        JQuest.__init__(self,id,name,descr)
        self.questItemIds = [KaimAbigoreTissue,KnightMontagnarTissue]

    def onAdvEvent (self,event,npc, player):
        htmltext = event
        st = player.getQuestState(qn)
        if not st: return
        if event == "30196-03.htm":
            st.set("cond","1")
            st.setState(State.STARTED)
            st.playSound("ItemSound.quest_accept")
        elif event == "30189-02.htm":
            st.giveItems(57,100000)
            st.unset("cond")
            st.exitQuest(False)
            st.playSound("ItemSound.quest_finish")
        return htmltext

    def onTalk (self,npc,player):
        htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
        st = player.getQuestState(qn)
        if not st: return htmltext
        npcId = npc.getNpcId()
        id = st.getState()
        if id == State.COMPLETED:
            if npcId == Mouen:
                htmltext = "30196-0a.htm"
        elif id == State.CREATED and npcId == Mouen:
            if player.getLevel() >= 48:
                htmltext = "30196-01.htm"
            else:
                htmltext = "30196-00.htm"
        else:
            if npcId == Mouen:
                if st.getQuestItemsCount(KaimAbigoreTissue) == 0 or st.getQuestItemsCount(KnightMontagnarTissue) == 0:
                   htmltext = "30196-04.htm"
                elif st.getQuestItemsCount(KaimAbigoreTissue) > 0 and st.getQuestItemsCount(KnightMontagnarTissue) > 0:
                   htmltext = "30196-05.htm"
            elif npcId == Rovia:
                if player.getLevel() < 48:
                   return htmltext
                if st.getQuestItemsCount(KaimAbigoreTissue) > 0 and st.getQuestItemsCount(KnightMontagnarTissue) > 0:
                   htmltext = "30189-01.htm"
                else:
                   htmltext = "30189-01a.htm"
        return htmltext

    def onKill(self,npc,player,isPet):
        npcId = npc.getNpcId()
        party = player.getParty()
        if party:
            PartyQuestMembers = []
            if npcId == KaimAbigore:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(KaimAbigoreTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(KaimAbigoreTissue,1)
                st.playSound("ItemSound.quest_middle")
            elif npcId == KnightMontagnar:
                for player1 in party.getPartyMembers().toArray():
                    st1 = player1.getQuestState(qn)
                    if st1:
                        if st1.getState() == State.STARTED and st1.getQuestItemsCount(KnightMontagnarTissue) == 0:
                            PartyQuestMembers.append(st1)
                if len(PartyQuestMembers) == 0: return
                st = PartyQuestMembers[Rnd.get(len(PartyQuestMembers))]
                st.giveItems(KnightMontagnarTissue,1)
                st.playSound("ItemSound.quest_middle")
        else: # in case that party members disconnected or so
            st = player.getQuestState(qn)
            if not st: return
            if npcId == KaimAbigore:
                if st.getState() == State.STARTED and st.getQuestItemsCount(KaimAbigoreTissue) == 0:
                    st.giveItems(KaimAbigoreTissue,1)
                    st.playSound("ItemSound.quest_middle")
            elif npcId == KnightMontagnar:
                if st.getState() == State.STARTED and st.getQuestItemsCount(KnightMontagnarTissue) == 0:
                    st.giveItems(KnightMontagnarTissue,1)
                    st.playSound("ItemSound.quest_middle")
        return

QUEST       = Quest(10279,qn,"Mutated Kaneus")

QUEST.addStartNpc(Mouen)
QUEST.addTalkId(Mouen)
QUEST.addTalkId(Rovia)
QUEST.addKillId(KaimAbigore)
QUEST.addKillId(KnightMontagnar)