# L2Rambase Project
# checked 12.08.2010

import sys
from l2.rambase import Config
from l2.rambase.gameserver.model.quest import State
from l2.rambase.gameserver.model.quest import QuestState
from l2.rambase.gameserver.model.quest.jython import QuestJython as JQuest
#Quest info
QUEST_NUMBER,QUEST_NAME,QUEST_DESCRIPTION = 374,"WhisperOfDreams1","Whisper of Dreams, part 1"
qn = "374_WhisperOfDreams1"

#Rewards
SHOP_LIST={
5485:["etc_leather_yellow_i00",4,10450,"Sealed Tallum Tunic Textures"],
5489:["etc_leather_gray_i00",  6,15550,"Sealed Tallum Stockings Fabrics"],
5479:["etc_leather_gray_i00",  3,4550,"Sealed Tallum Leather Armor Pattern"],
5521:["etc_leather_gray_i00",  3,13550,"Sealed Tallum Plate Armor Pattern"],
5486:["etc_leather_gray_i00",  3,2950,"Sealed Dark Crystal Robe Fabrics"],
5478:["etc_leather_gray_i00",  3,12550,"Sealed Dark Crystal Leather Armor Pattern"],
5482:["etc_leather_gray_i00",  2,10550,"Sealed Dark Crystal Leggings Design"],
5524:["etc_leather_gray_i00",  3,4550,"Sealed Dark Crystal Gaiters Pattern"],
5520:["etc_leather_gray_i00",  3,10550,"Sealed Dark Crystal Breastplate Pattern"],
5487:["etc_leather_gray_i00",  2,18050,"Sealed Robe of Nightmare Fabrics"],
5480:["etc_leather_gray_i00",  3,15550,"Sealed Leather Armor of Nightmare Fabric"],
5522:["etc_leather_gray_i00",  3,11550,"Sealed Armor of Nightmare Pattern"],
5481:["etc_leather_gray_i00",  3,12550,"Sealed Majestic Leather Armor Fabric"],
5523:["etc_leather_gray_i00",  3,10550,"Sealed Majestic Plate Armor Pattern"],
5488:["etc_leather_gray_i00",  2,18050,"Sealed Majestic Robe Fabrics"]
}
 
ADENA_X=int(Config.RATE_DROP_ITEMS_ID.get(57))
 
#Quest items
CB_TOOTH, DW_LIGHT, SEALD_MSTONE, MSTONE = range(5884,5888)
#Messages
default   = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>"
 
#NPCs
MANAKIA,TORAI = 30515, 30557
 
#Mobs & Drop
DROPLIST = {20620:[CB_TOOTH,"awaitTooth"],20621:[DW_LIGHT,"awaitLight"]}
 
def render_shop() :
    html = "<html><body><font color=\"LEVEL\">Robe Armor Fabrics:</font><table border=0 width=300>"
    for i in SHOP_LIST.keys() :
       html += "<tr><td width=35 height=45><img src=icon."+SHOP_LIST[i][0]+" width=32 height=32 align=left></td><td width=365 valign=top><table border=0 width=100%>"
       html += "<tr><td><a action=\"bypass -h Quest 374_WhisperOfDreams1 "+str(i)+"\"><font color=\"FFFFFF\">"+SHOP_LIST[i][3]+" x"+str(SHOP_LIST[i][1])+"</font></a></td></tr>"
       html += "<tr><td><a action=\"bypass -h Quest 374_WhisperOfDreams1 "+str(i)+"\"><font color=\"B09878\">"+str(SHOP_LIST[i][2]*ADENA_X)+" adena</font></a></td></tr></table></td></tr>"
    html += "</table></body></html>"
    return html
 
class Quest (JQuest) :
 
 def __init__(self,id,name,descr):
     JQuest.__init__(self,id,name,descr)
     self.questItemIds = [CB_TOOTH, DW_LIGHT]
 
 def onEvent (self,event,st) :
    id = st.getState() 
    htmltext = event
    if event == "30515-4.htm" :
       st.setState(State.STARTED)
       st.set("progress","PART1")
       st.set("awaitTooth","1") 
       st.set("awaitLight","1")
       st.set("awaitSealedMStone","1")
       st.set("cond","1")
       st.playSound("ItemSound.quest_accept")
    elif event == "30515-5.htm" :
       st.takeItems(CB_TOOTH,-1)
       st.takeItems(DW_LIGHT,-1)
       st.takeItems(SEALD_MSTONE,-1)
       st.exitQuest(1)
    elif event == "30515-6.htm" :
       if st.getQuestItemsCount(CB_TOOTH)>=65 and st.getQuestItemsCount(DW_LIGHT)>=65 :
          st.set("allow","1")
          st.takeItems(CB_TOOTH,65)
          st.takeItems(DW_LIGHT,65)
          htmltext = "30515-7.htm"
    elif event == "30515-8.htm" :
       if st.getQuestItemsCount(SEALD_MSTONE) :
          if id == State.STARTED and st.get("progress")=="PART1" :
             st.set("progress","PART2")
             st.set("cond","2")
             htmltext = "30515-9.htm"
          elif id == State.STARTED and st.get("progress")=="PART2":
             htmltext = "30515-10.htm"
    elif event == "buy" :
       htmltext = render_shop()
    elif int(event) in SHOP_LIST.keys() :
       st.set("allow","0")
       st.rewardItems(57,SHOP_LIST[int(event)][2])
       st.rewardItems(int(event),SHOP_LIST[int(event)][1])
       st.playSound("ItemSound.quest_finish")
       st.set("awaitTooth","1")
       st.set("awaitLight","1")
       htmltext = "30515-11.htm"
    return htmltext
 
 def onTalk (self,npc,player):
   htmltext = default
   st = player.getQuestState(qn)
   if not st : return htmltext

   npcId = npc.getNpcId()
   id = st.getState()
   if npcId != MANAKIA and id != State.STARTED : return htmltext

   if npcId == MANAKIA:
      if id == State.CREATED :
         st.set("cond","0")
         st.set("allow","0")
         htmltext = "30515-1.htm"
         if player.getLevel() < 56 :
            st.exitQuest(1)
            htmltext = "30515-2.htm"
      else :
         if st.getInt("allow") :
            htmltext = "30515-3.htm"
         else :
            htmltext = "30515-3a.htm"
   elif npcId == TORAI :
      if st.getQuestItemsCount(SEALD_MSTONE) :
         htmltext = "30557-1.htm"
         st.takeItems(SEALD_MSTONE,1)
         st.giveItems(MSTONE,1)
         st.set("cond","3")
         st.playSound("ItemSound.quest_middle")
   return htmltext
 
 def onKill(self,npc,player,isPet) :
     #both mobs may give SEALD_MSTONE to a player
     partyMember = self.getRandomPartyMember(player,"awaitSealedMStone","1")
     if partyMember :
        st = partyMember.getQuestState(qn)
        if st.getQuestItemsCount(SEALD_MSTONE) < 1 :
           st.giveItems(SEALD_MSTONE,1)
           st.unset("awaitSealedMStone")
           st.playSound("ItemSound.quest_middle")

     npcId = npc.getNpcId()
     item, partyCond = DROPLIST[npcId]
     partyMember = self.getRandomPartyMember(player,partyCond,"1")
     if partyMember :
         st = partyMember.getQuestState(qn)
         count = st.getQuestItemsCount(item)
         numItems = 5
         if count + numItems >= 65 :
            numItems = 65 - count
            st.playSound("ItemSound.quest_middle")
            st.unset(partyCond)
         else:
            st.playSound("ItemSound.quest_itemget")   
         st.giveItems(item,int(numItems))
     return  
 
# Quest class and state definition
QUEST       = Quest(QUEST_NUMBER, str(QUEST_NUMBER)+"_"+QUEST_NAME, QUEST_DESCRIPTION)
 
# Quest NPC starter initialization
QUEST.addStartNpc(MANAKIA)
# Quest initialization
QUEST.addTalkId(MANAKIA)

QUEST.addTalkId(TORAI)

for i in DROPLIST.keys() :
  QUEST.addKillId(i)