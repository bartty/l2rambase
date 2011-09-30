/**
 * @author L2Rambase Project
 */
package custom.ControlDeviceOfTheGiants;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.ThreadPoolManager;


public class ControlDeviceOfTheGiants extends Quest
{
	private static final String qn = "307_ControlDeviceOfTheGiants";

	// NPCs
	private static int DROPH = 32711;

	//RBs
	private static int GORGOLOS = 25681;
	private static int LAST_TITAN_UTENUS = 25684;
	private static int GIANT_MARPANAK = 25680;
	private static int HEKATON_PRIME = 25687;
	private static final int[] RAIDBOSS = {GORGOLOS, LAST_TITAN_UTENUS, GIANT_MARPANAK, HEKATON_PRIME};
	
	// ITEMS
	private static int CET_1_SHEET = 14829;
	private static int CET_2_SHEET = 14830;	
	private static int CET_3_SHEET = 14831;
	private static int SUPPLY_BOX = 14850;
	private int Resp = 0;
	private static final int[] ITEMS = {CET_1_SHEET, CET_2_SHEET, CET_3_SHEET};
	
	public ControlDeviceOfTheGiants(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(DROPH);
		addTalkId(DROPH);
		questItemIds = ITEMS;
		for (int mobId : RAIDBOSS)
			addKillId(mobId);	
	}

	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = player.getQuestState(qn);
		String htmltext = event;
		if(event.equalsIgnoreCase("32711-02.htm"))
		{
			st.set("cond", "1");
			st.setState(State.STARTED);
			st.playSound("ItemSound.quest_accept");
			htmltext = "<html> <body> Dwarf Traveller Droph: <br>";
			htmltext += "The challenge will be not easy! In this place inhabited by three ancient Giant. This Giant Marpanak * * * * and * Gorgolos Last Giant Utenus *. <br>";
			htmltext += "Each of them keeps the secret of the Giant * Hekaton Prima *. Killing three giants you can get the papyrus.";
			htmltext += "Once you have collected all three papyrus back to me and I'll show you where dwells Hekaton Prime.";
			htmltext += "</body></html>";
			return htmltext;
		}
		if(event.equalsIgnoreCase("dospawn"))
		{
			st.addSpawn(HEKATON_PRIME, 191975, 56959, -7616, 1800000);
			Resp = 1;
			htmltext = "<html><body>Dwarf Traveller Droph:<br>";
			htmltext += "Success! Here it is * Last Giant Hekaton Prime *. <br>";
			htmltext += "Good luck hunting! As soon win him back to me for a reward.";
			htmltext += "</body></html>";
			return htmltext;
		}
		if(event.equalsIgnoreCase("32711-12.htm"))
		{
			htmltext = "<html><body>Dwarf Traveller Droph:<br>";
			htmltext += "Sorry, but it's your choice! If you want to still see Prima, you know where to find me. Good luck!";
			htmltext += "</body></html>";
		}
		return htmltext;
	}

	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		String htmltext = "<html><body>You are either not on a quest that involves this NPC, or you don't meet this NPC's minimum quest requirements.</body></html>";
		final QuestState st = player.getQuestState(qn);
		int id = st.getState();
		final int cond = st.getInt("cond");
		int npcId = npc.getNpcId();
		if(npcId == DROPH)
		{
			if(id == State.CREATED && cond == 0)
			{
				if(player.getLevel() >= 79)
				{
					htmltext = "<html><body>Dwarf Traveller Droph:<br>";
					htmltext += "Hail, puteshesvennik!";
					htmltext += "I have long been studying the site and found something interesting related to the ancient giants. Them there are not many and they keep the secret of the Giant Prima.";
					htmltext += "After examining all the data I have come to the conclusion that if we assemble all the parts of the manuscript, it is possible to find and very Prima. <br>";
					htmltext += "Would you like to help me with the collection of manuscripts? Then, I could repay you generously. <br>";
					htmltext += "<a action=\"bypass -h Quest 307_ControlDeviceOfTheGiants 32711-02.htm\">Yes I will help</a><br>";
					htmltext += "<a action=\"bypass -h Quest 307_ControlDeviceOfTheGiants 32711-12.htm\">No thank you</a>";
					htmltext += "</body></html>";
					return htmltext;
				}
				else
				{	
					st.exitQuest(true);
					htmltext = "<html><body>Dwarf Traveller Droph:<br>";
					htmltext += "You are not too intelligent just want to get involved in such a dangerous adventure. <br>";
					htmltext += "(This quest can be fulfilled only by characters level 79 and above.)";
					htmltext += "</body></html>";
					return htmltext;
				}	
			}		
			else if(id == State.STARTED)
			{
				if(npcId == DROPH)
				{
					if(cond == 2)
					{
						st.takeItems(CET_1_SHEET,1);
						st.takeItems(CET_2_SHEET,1);
						st.takeItems(CET_3_SHEET,1);
						st.giveItems(SUPPLY_BOX,1);
						st.exitQuest(true);
						st.playSound("ItemSound.quest_finish");
						htmltext = "<html><body>Dwarf Traveller Droph: <br>";
						htmltext += "That is, keep your reward! Thank you for the help!";
						htmltext += "If you want more time to see the prime, now you know where to find me. Good luck!";
						htmltext += "</body></html>";
						return htmltext; //quest completed html
					}	
					else if(st.getQuestItemsCount(CET_1_SHEET) == 0 || st.getQuestItemsCount(CET_2_SHEET) == 0 || st.getQuestItemsCount(CET_3_SHEET) == 0)
					{
						htmltext = "<html><body>Dwarf Traveller Droph:<br>";
						htmltext += "You want me to cheat? Go and recover all three papyrus, if you want to meet with Hekaton Primeau. <br>";
						htmltext += "<a action=\"bypass -h Quest 307_ControlDeviceOfTheGiants 32711-12.htm\">Unsubscribe job</a>";
						htmltext += "</body></html>";	
						return htmltext; //didn't killed all RBs html
					}
					else if(cond == 1 && st.getQuestItemsCount(CET_1_SHEET) >= 1 && st.getQuestItemsCount(CET_2_SHEET) >= 1 && st.getQuestItemsCount(CET_3_SHEET) >= 1 && Resp == 0)
					{
						htmltext = "<html><body>Dwarf Traveller Droph:<br>";
						htmltext += "Oh, you've mastered the three giants! Excellent! <br>";
						htmltext += "Now give me all three of the papyrus, so that I can decipher them. Be prepared for the fact that in this room will";
						htmltext += "Last Giant Hekaton Prime and you have to fight with him. <br>";
						htmltext += "<a action=\"bypass -h Quest 307_ControlDeviceOfTheGiants dospawn\">Send papyrus</a><br>";
						htmltext += "<a action=\"bypass -h Quest 307_ControlDeviceOfTheGiants 32711-12.htm\">Unsubscribe job</a>";
						htmltext += "</body></html>";
						return htmltext; //quest set raid boss spawn html
					}
					else if(Resp == 1)
					{
						htmltext = "<html><body>Dwarf Traveller Droph:<br>";
						htmltext += "Can not you see? Hekaton Prime was already here and it will appear only after 3 hours.";
						htmltext += "</body></html>";
						return htmltext;
					}
				}
			}
		}
		return htmltext;
	}

	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);		
		int npcId = npc.getNpcId();
		if (player.getParty() != null)
		{
			for (L2PcInstance partyMember: player.getParty().getPartyMembers())
			{
				QuestState pst = partyMember.getQuestState(qn);
				if (pst == null)
				{
					pst = newQuestState(partyMember);
				}
				final int cond = pst.getInt("cond");			
				if(cond == 1)
				{
					if(npcId == GORGOLOS)
					{
						pst.giveItems(CET_1_SHEET,1);
						pst.playSound("ItemSound.quest_itemget");
					}	
					else if(npcId == LAST_TITAN_UTENUS)
					{
						pst.giveItems(CET_2_SHEET,1);
						pst.playSound("ItemSound.quest_itemget");
					}		
					else if(npcId == GIANT_MARPANAK)
					{
						pst.giveItems(CET_3_SHEET,1);
						pst.playSound("ItemSound.quest_itemget");
					}	
					else if(npcId == HEKATON_PRIME)
					{
						pst.set("cond","2");
						ThreadPoolManager.getInstance().scheduleGeneral(new setresp(), 1000*60*60*3);
					}
				}
			}
		}
		else
		{
			final int cond = st.getInt("cond");			
			if(cond == 1)
			{
				if(npcId == GORGOLOS)
				{
					st.giveItems(CET_1_SHEET,1);
					st.playSound("ItemSound.quest_itemget");
				}	
				else if(npcId == LAST_TITAN_UTENUS)
				{
					st.giveItems(CET_2_SHEET,1);
					st.playSound("ItemSound.quest_itemget");
				}		
				else if(npcId == GIANT_MARPANAK)
				{
					st.giveItems(CET_3_SHEET,1);
					st.playSound("ItemSound.quest_itemget");
				}	
				else if(npcId == HEKATON_PRIME)
				{
					st.set("cond","2");
					ThreadPoolManager.getInstance().scheduleGeneral(new setresp(), 1000*60*60*3);
				}
			}
		}
		return super.onKill(npc, player, isPet);			
	}
		
	private class setresp implements Runnable
	{
		public void run()
		{
			Resp = 0;
		}
	}

	public static void main(String[] args)
	{
		new ControlDeviceOfTheGiants(307, qn, "Control Device of The Giants");
	}
}