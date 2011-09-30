/**
 * Couple Manager
 * 
 * Information:
 * Couple Manager is quest manager for couples quest.
 */
package services.CoupleManager;

import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.stat.PcStat;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;


public class CoupleManager extends Quest
{
	private static final String qn = "CoupleManager";	
	
	/**
	 * List of Quests
	 * @param QuestName
	 */
	public static final String[][] QUEST_LIST =
	{
		{"",	""},                    										//null value of the list
		{"Help My Love",		"5k Vitality Points"}, 							// 01 - Vitality - 5k points
		{"Defend Your Feelings","2,5kk Exp, 350k Sp and 15k Adena"},			// 02 - 2,5kk Exp. 350k Sp, 15k Adena
		{"Sweet Home",			"1kk Sp"},				        				// 03 - 1kk Sp
		{"Count Your Love",		"Agathion of Love (30 Days)"},					// 04 - Agathion of Love - 30 days (13023)
		{"Missing Heart",		"Teddy Agathion (30 Days)"},					// 05 - Teddy Boy/ Teddy Girl Agathion - 30 days (14059,14060)
		{"Kiss Me Please",		"Juju Agathion (14 Days)"},						// 06 - Juju Agathion - 14 Days (14776)
		{"Pirate And Queen",	"Queen Ant Ring and Zaken Earing (15 Days)"},	// 07 - Queen Ant Ring - 15 Days (20206), Zaken Earing - 15 Days (20209) 
		{"Ride Me Honey",		"Steam or Tawned Lion Bracelet (30 Days)"},		// 08 - Steam Bracelet - 30 Days (20535) or Tawned Lion Bracelet - 30 Days (20536) 
		{"Love Adventure",		"Adventure Agathion Male or Female"},			// 09 - Agathion Adventurer Male (20819), Agathion Adventure Female (20821)
		{"Burn My Wallet",		"1kk Adena and 200k Ancient Adena"},			// 10 - 1kk Adena. 200k Ancient Adena
		{"Hot Partner",			"3kk Exp and 550k Sp"},							// 11 - 3kk Exp, 550k Sp
		{"Energy Kiss",			"Recover Vitality"},							// 12 - Vitality - Full Recover
		{"Write Your Feelings",	"400k Adena"},									// 13 - 400k Adena
		{"Find Marriage Gift",	"Agathion of Love with Energy (30 Days)"},		// 14 - Agathion of Love 30 Days (20203)
		{"Summon Your Love",	"Summon Bracelet (7 Days)"}						// 15 - Summon Bracelet (20628)	
	};
	
	/**
	 * Html texts of Couple Npc
	 * @param String
	 */
	private static final String start = "<html><body><font color=LEVEL>Couple Manager</font><br>Welcome here young marriage. Here is yours travel to get more that one time be better marriage. Please help me and i help you.<br><a action=\"bypass -h Quest CoupleManager select\">Help her</a><br><a action=\"bypass -h Quest CoupleManager cancel\">Cancel last job</a></body></html>";	
	private static final String not_maried = "<html><body><font color=LEVEL>Couple Manager</font><br>Welcome here. I searching for couples to help me in jobs. Couples can get rewards for good finished job. You are not married so you must first get married <br1></body></html>";	

	private final static String getQuestList(String[][] list)
	{
		String text = "";
		for (int i = 0; i<list.length;i++)
		{
			if (list[i][0] != "")
			{
				text += "<font color=LEVEL>Quest Reward:" + list[i][1] + "</font><br>";
				text += "<a action=\"bypass -h Quest CoupleManager " + i + "_start\">Quest: " + list[i][0] + "</a><br1>";
			}
		}
		return text;
	}
	
	/**
	 * Couple Npc Manager
	 * @param npc
	 */
	private static final int COUPLE_NPC = 7100;

	/**
	 * On First Talk Script
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		if (player.isMarried())
		{
			htmltext = start;	
		}
		else
		{
			player.sendMessage("First get married, then come back.");
			htmltext = not_maried;
		}
		return htmltext;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.startsWith("1_"))
		{
			htmltext = selectCoupleQuest(player,1,event);
		}
		else if (event.startsWith("2_"))
		{
			htmltext = selectCoupleQuest(player,2,event);
		}
		else if (event.startsWith("3_"))
		{
			htmltext = selectCoupleQuest(player,3,event);	
		}
		else if (event.startsWith("4_"))
		{
			htmltext = selectCoupleQuest(player,4,event);	
		}
		else if (event.startsWith("5_"))
		{
			htmltext = selectCoupleQuest(player,5,event);	
		}
		else if (event.startsWith("6_"))
		{
			htmltext = selectCoupleQuest(player,6,event);
		}
		else if (event.startsWith("7_"))
		{
			htmltext = selectCoupleQuest(player,7,event);
		}
		else if (event.startsWith("8_"))
		{
			htmltext = selectCoupleQuest(player,8,event);
		}
		else if (event.startsWith("9_"))
		{
			htmltext = selectCoupleQuest(player,9,event);
		}
		else if (event.startsWith("10_"))
		{
			htmltext = selectCoupleQuest(player,10,event);
		}
		else if (event.startsWith("11_"))
		{
			htmltext = selectCoupleQuest(player,11,event);
		}
		else if (event.startsWith("12_"))
		{
			htmltext = selectCoupleQuest(player,12,event);
		}
		else if (event.startsWith("13_"))
		{
			htmltext = selectCoupleQuest(player,13,event);
		}
		else if (event.startsWith("14_"))
		{
			htmltext = selectCoupleQuest(player,14,event);
		}
		else if (event.startsWith("15_"))
		{
			htmltext = selectCoupleQuest(player,15,event);
		}
		if (event.equalsIgnoreCase("cancel"))
		{			
			L2PcInstance partner = L2World.getInstance().getPlayer(player.getPartnerId());
			if (partner == null || partner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState pst = partner.getQuestState(qn);
			if (st.get("questStarted") == "1")
			{
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
				st.set("killcount","0");
				pst.set("killcount","0");
			}
			htmltext = CoupleManagerConfig.q_cancel;
		}
		if (event.equalsIgnoreCase("select"))
		{
			L2PcInstance partner = L2World.getInstance().getPlayer(player.getPartnerId());
			if (partner == null || partner.isOnline() == 0)
			{
				return "<html><body><font color=LEVEL>Couple Manager</font><br>Your partner must me online to start quests.<br1></body></html>";
			}
			QuestState pst = partner.getQuestState(qn);			
			int listFirstPartnerNumber = 0;
			int listSecondPartnerNumber = 0;
			try
			{
				listFirstPartnerNumber = Integer.valueOf(st.get("questNumber"));
			}
			catch (Exception e)
			{
				st.set("questNumber","0");
			}
			try
			{
				listSecondPartnerNumber = Integer.valueOf(pst.get("questNumber"));
			}
			catch (Exception e)
			{
				if (pst == null)
				{
					pst = newQuestState(partner);
				}
				pst.set("questNumber","0");
			}
			if (listFirstPartnerNumber != 0)
			{
				pst.set("questNumber",String.valueOf(listFirstPartnerNumber));				
				htmltext = listOfCoupleQuests(listFirstPartnerNumber);	
			}
			else if (listSecondPartnerNumber != 0)
			{
				st.set("questNumber", String.valueOf(listSecondPartnerNumber));
				htmltext = listOfCoupleQuests(listSecondPartnerNumber);				
			}
			else
			{
				htmltext = listOfCoupleQuests(0);
			}
		}
		return htmltext;
	}
	
	private String listOfCoupleQuests(int listNumber) 
	{
		String value = "";
		if (listNumber == 0)
		{
			value = "<html><body><font color=LEVEL>Couple Manager</font><br>You want help me in ?<br>" + getQuestList(QUEST_LIST) + "</body></html>";
		}
		else
		{
			value = "<html><body><font color=LEVEL>Couple Manager</font><br>You want help me in ?<br><a action=\"bypass -h Quest CoupleManager " + String.valueOf(listNumber) + "_start\">" + QUEST_LIST[listNumber][0] + "</a><br1>";
		}
		return value;
	}

	private String selectCoupleQuest(L2PcInstance player, int i, String event) 
	{
		L2PcInstance partner = L2World.getInstance().getPlayer(player.getPartnerId());
		if (partner == null || partner.isOnline() == 0)
		{
			return "<html><body><font color=LEVEL>Couple Manager</font><br>Your partner must me online to select quest.<br1></body></html>";
		}
		QuestState st = player.getQuestState(qn);
		QuestState pst = partner.getQuestState(qn);
		if (event.equalsIgnoreCase(String.valueOf(i)+"_start"))
		{			
			boolean eventStarted = false;
			try
			{
				int started = Integer.valueOf(st.get("questStarted"));						
				if (started > 0)
				{
					eventStarted = true;
				}						
			}
			catch (Exception e)
			{					
				eventStarted = false;
			}
			if (!eventStarted)
			{
				st.set("questNumber",String.valueOf(i));
				pst.set("questNumber",String.valueOf(i));
				//start quest procedure
				return questStartProcedure(player,i);				
			}
			else
			{
				//check finish quest
				return questFinishProcedure(player,i);
			}
		}		
		return "";
	}

	private String questFinishProcedure(L2PcInstance player,int i) 
	{
		String htmltext = "";
		/**
		 * Quest 1 - Help My Love
		 */
		if (i == 1)
		{
			L2Party party = player.getParty();
			int _partnerId = player.getPartnerId();
			L2PcInstance partner = L2World.getInstance().getPlayer(_partnerId);
			if (partner == null || partner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = partner.getQuestState(qn);
			if (party == null)
			{					
				player.sendMessage("You must be in party with your partner.");					
			}
			else
			{
				if (party.getMemberCount() > 2)
				{
					player.sendMessage("You must be only with partner in party");
				}
				else
				{
					for (L2PcInstance partyMember: party.getPartyMembers())
					{
						if (partyMember.getObjectId() == _partnerId)
						{
							if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
							{
								htmltext = CoupleManagerConfig.q1_finished;
								partyMember.setVitalityPoints(partyMember.getVitalityPoints() + CoupleManagerConfig.Q1_VITALITY_REWARD, false);
								player.setVitalityPoints(player.getVitalityPoints() + CoupleManagerConfig.Q1_VITALITY_REWARD, false);
								st.set("questStarted","0");
								st.set("questFinished","0");
								pst.set("questStarted","0");
								pst.set("questFinished","0");
								st.set("questNumber", "0");
								pst.set("questNumber", "0");
							}
							else
							{
								htmltext = CoupleManagerConfig.q1_1;
							}
						}
						if (partyMember.getObjectId() != player.getObjectId() && partyMember.getObjectId() != _partnerId)
						{
							player.sendMessage("Your party member are not your Partner.");
						}
					}
				}
			}
		}
		/**
		 * Quest 2 - Defend Your Feelings
		 */
		if (i == 2)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q2_finished;
				st.giveItems(CoupleManagerConfig.Q2_ADENA, CoupleManagerConfig.Q2_ADENA_COUNT);
				st.addExpAndSp(CoupleManagerConfig.Q2_EXP_REWARD, CoupleManagerConfig.Q2_SP_REWARD);
				pst.giveItems(CoupleManagerConfig.Q2_ADENA, CoupleManagerConfig.Q2_ADENA_COUNT);
				pst.addExpAndSp(CoupleManagerConfig.Q2_EXP_REWARD, CoupleManagerConfig.Q2_SP_REWARD);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q2_1;
			}			
		}
		/**
		 * Quest 3 - Sweet Home
		 */
		if (i == 3)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (player.getAppearance().getSex() == true)
			{
				if (st.getQuestItemsCount(CoupleManagerConfig.Q3_COAL) >= CoupleManagerConfig.Q3_COAL_COUNT && st.getQuestItemsCount(CoupleManagerConfig.Q3_SOP) >= CoupleManagerConfig.Q3_SOP_COUNT)
				{
					if (pst.getQuestItemsCount(CoupleManagerConfig.Q3_CHARCOAL) >= CoupleManagerConfig.Q3_CHARCOAL_COUNT && pst.getQuestItemsCount(CoupleManagerConfig.Q3_ORIHARUKON_ORE) >= CoupleManagerConfig.Q3_ORIHARUKON_ORE_COUNT)
					{
						st.takeItems(CoupleManagerConfig.Q3_COAL, CoupleManagerConfig.Q3_COAL_COUNT);
						st.takeItems(CoupleManagerConfig.Q3_SOP, CoupleManagerConfig.Q3_SOP_COUNT);									
						pst.takeItems(CoupleManagerConfig.Q3_CHARCOAL, CoupleManagerConfig.Q3_CHARCOAL_COUNT);
						pst.takeItems(CoupleManagerConfig.Q3_ORIHARUKON_ORE, CoupleManagerConfig.Q3_ORIHARUKON_ORE_COUNT);
						pst.addExpAndSp(0, CoupleManagerConfig.Q3_SP);
						st.addExpAndSp(0, CoupleManagerConfig.Q3_SP);
						htmltext = CoupleManagerConfig.q3_finished;
						st.set("questStarted","0");
						st.set("questFinished","0");
						pst.set("questStarted","0");
						pst.set("questFinished","0");
						st.set("questNumber", "0");
						pst.set("questNumber", "0");
					}
					else
					{
						htmltext = CoupleManagerConfig.q3_1;
						marriedPartner.sendMessage("You don't have required materials for house.");
					}
				}
				else
				{
					htmltext = CoupleManagerConfig.q3_1;
					player.sendMessage("You don't have required materials for house.");
				}
			}
			else
			{
				if (st.getQuestItemsCount(CoupleManagerConfig.Q3_CHARCOAL) >= CoupleManagerConfig.Q3_CHARCOAL_COUNT && st.getQuestItemsCount(CoupleManagerConfig.Q3_ORIHARUKON_ORE) >= CoupleManagerConfig.Q3_ORIHARUKON_ORE_COUNT)
				{
					if (pst.getQuestItemsCount(CoupleManagerConfig.Q3_COAL) >= CoupleManagerConfig.Q3_COAL_COUNT && pst.getQuestItemsCount(CoupleManagerConfig.Q3_SOP) >= CoupleManagerConfig.Q3_SOP_COUNT)
					{
						pst.takeItems(CoupleManagerConfig.Q3_COAL, CoupleManagerConfig.Q3_COAL_COUNT);
						pst.takeItems(CoupleManagerConfig.Q3_SOP, CoupleManagerConfig.Q3_SOP_COUNT);									
						st.takeItems(CoupleManagerConfig.Q3_CHARCOAL, CoupleManagerConfig.Q3_CHARCOAL_COUNT);
						st.takeItems(CoupleManagerConfig.Q3_ORIHARUKON_ORE, CoupleManagerConfig.Q3_ORIHARUKON_ORE_COUNT);
						pst.addExpAndSp(0, CoupleManagerConfig.Q3_SP);
						st.addExpAndSp(0, CoupleManagerConfig.Q3_SP);
						htmltext = CoupleManagerConfig.q3_finished;
						st.set("questStarted","0");
						st.set("questFinished","0");
						pst.set("questStarted","0");
						pst.set("questFinished","0");
						st.set("questNumber", "0");
						pst.set("questNumber", "0");
					}
					else
					{
						htmltext = CoupleManagerConfig.q3_1;
						marriedPartner.sendMessage("You don't have required materials for house.");
					}
				}
				else
				{
					htmltext = CoupleManagerConfig.q3_1;
					player.sendMessage("You don't have required materials for house.");
				}
			}
		}
		/**
		 * Quest 4 - Count Your Love
		 */
		if (i == 4)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				st.giveItems(CoupleManagerConfig.Q4_AGATHION_OF_LOVE, 1);
				pst.giveItems(CoupleManagerConfig.Q4_AGATHION_OF_LOVE, 1);
				htmltext = CoupleManagerConfig.q4_finished;
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q4_1;				
			}
		}
		/**
		 * Quest 5 - Missing Heart
		 */
		if (i == 5)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				if (st.getQuestItemsCount(CoupleManagerConfig.Q5_ECHO_OF_LOVE) >= CoupleManagerConfig.Q5_SONG_COUNT && pst.getQuestItemsCount(CoupleManagerConfig.Q5_ECHO_OF_LOVE) >= CoupleManagerConfig.Q5_SONG_COUNT)
				{
					st.takeItems(CoupleManagerConfig.Q5_ECHO_OF_LOVE,CoupleManagerConfig.Q5_SONG_COUNT);
					pst.takeItems(CoupleManagerConfig.Q5_ECHO_OF_LOVE,CoupleManagerConfig.Q5_SONG_COUNT);
					if (player.getAppearance().getSex() == true)
					{
						st.giveItems(CoupleManagerConfig.Q5_TEDDY_GIRL_AGATHION, 1);
						pst.giveItems(CoupleManagerConfig.Q5_TEDDY_BOY_AGATHION, 1);
					}
					else
					{
						pst.giveItems(CoupleManagerConfig.Q5_TEDDY_GIRL_AGATHION, 1);
						st.giveItems(CoupleManagerConfig.Q5_TEDDY_BOY_AGATHION, 1);
					}					
					htmltext = CoupleManagerConfig.q5_finished;
					st.set("questStarted","0");
					st.set("questFinished","0");
					pst.set("questStarted","0");
					pst.set("questFinished","0");
					st.set("questNumber", "0");
					pst.set("questNumber", "0");				
				}
				else
				{
					htmltext = CoupleManagerConfig.q5_1;
				}
			}
			else
			{
				htmltext = CoupleManagerConfig.q5_1;				
			}
		}
		/**
		 * Quest 6 - Kiss Me Please
		 */
		if (i == 6)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				st.giveItems(CoupleManagerConfig.Q6_JUJU_AGATHION, 1);
				pst.giveItems(CoupleManagerConfig.Q6_JUJU_AGATHION, 1);
				htmltext = CoupleManagerConfig.q6_finished;
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q6_1;				
			}
		}
		/**
		 * Quest 7 - Pirate and Queen
		 */
		if (i == 7)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q7_finished;
				st.giveItems(CoupleManagerConfig.Q7_QUEEN_ANT_RING, 1);
				st.giveItems(CoupleManagerConfig.Q7_ZAKEN_EARRING, 1);
				pst.giveItems(CoupleManagerConfig.Q7_QUEEN_ANT_RING, 1);
				pst.giveItems(CoupleManagerConfig.Q7_ZAKEN_EARRING, 1);				
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q7_1;
			}			
		}
		/**
		 * Quest 8 - Ride Me Honey
		 */
		if (i == 8)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);			
			if (st.getQuestItemsCount(CoupleManagerConfig.Q8_RED_SOUL_CRYSTAL_5) > 0 && st.getQuestItemsCount(CoupleManagerConfig.Q8_GREEN_SOUL_CRYSTAL_5) > 0)
			{
				st.takeItems(CoupleManagerConfig.Q8_RED_SOUL_CRYSTAL_5, 1);
				st.takeItems(CoupleManagerConfig.Q8_GREEN_SOUL_CRYSTAL_5, 1);
				st.giveItems(CoupleManagerConfig.Q8_STEAM_BRACELET, 1);
				st.set("questFinished", "1");
				htmltext = CoupleManagerConfig.q8_finished;
			}
			else if (st.getQuestItemsCount(CoupleManagerConfig.Q8_BLUE_SOUL_CRYSTAL_5) > 0 && st.getQuestItemsCount(CoupleManagerConfig.Q8_GREEN_SOUL_CRYSTAL_5) > 0)
			{
				st.takeItems(CoupleManagerConfig.Q8_BLUE_SOUL_CRYSTAL_5, 1);
				st.takeItems(CoupleManagerConfig.Q8_GREEN_SOUL_CRYSTAL_5, 1);
				st.giveItems(CoupleManagerConfig.Q8_LION_BRACELET, 1);
				st.set("questFinished", "1");
				htmltext = CoupleManagerConfig.q8_finished;
			}
			else
			{
				htmltext = CoupleManagerConfig.q8_1;
			}
			if (st.get("questFinished") == "1")
			{
				if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
				{					
					st.set("questStarted","0");
					st.set("questFinished","0");
					pst.set("questStarted","0");
					pst.set("questFinished","0");
					st.set("questNumber", "0");
					pst.set("questNumber", "0");
				}
				htmltext = CoupleManagerConfig.q8_finished;				
			}
		}
		/**
		 * Quest 9 - Love Adventure
		 */
		if (i == 9)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q9_finished;
				if (player.getAppearance().getSex() == true)
				{
					st.giveItems(CoupleManagerConfig.Q9_AD_AGATHION_F, 1);
					pst.giveItems(CoupleManagerConfig.Q9_AD_AGATHION_M, 1);
				}
				else
				{
					pst.giveItems(CoupleManagerConfig.Q9_AD_AGATHION_F, 1);
					st.giveItems(CoupleManagerConfig.Q9_AD_AGATHION_M, 1);
				}
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q9_1;
			}			
		}
		/**
		 * Quest 10 - Burn My Wallet
		 */
		if (i == 10)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);			
			if (st.getQuestItemsCount(CoupleManagerConfig.Q10_MAGMA_DUST) >=  CoupleManagerConfig.Q10_MAGMA_DUST_COUNT
					&& pst.getQuestItemsCount(CoupleManagerConfig.Q10_MAGMA_DUST) >=  CoupleManagerConfig.Q10_MAGMA_DUST_COUNT
					&& st.getQuestItemsCount(CoupleManagerConfig.Q10_MOON_DUST) >=  CoupleManagerConfig.Q10_MOON_DUST_COUNT
					&& pst.getQuestItemsCount(CoupleManagerConfig.Q10_MOON_DUST) >=  CoupleManagerConfig.Q10_MOON_DUST_COUNT)
			{
				st.takeItems(CoupleManagerConfig.Q10_MAGMA_DUST, CoupleManagerConfig.Q10_MAGMA_DUST_COUNT);
				st.takeItems(CoupleManagerConfig.Q10_MOON_DUST, CoupleManagerConfig.Q10_MOON_DUST_COUNT);
				pst.takeItems(CoupleManagerConfig.Q10_MAGMA_DUST, CoupleManagerConfig.Q10_MAGMA_DUST_COUNT);
				pst.takeItems(CoupleManagerConfig.Q10_MOON_DUST, CoupleManagerConfig.Q10_MOON_DUST_COUNT);
				st.giveItems(CoupleManagerConfig.Q10_ADENA, CoupleManagerConfig.Q10_ADENA_COUNT);
				st.giveItems(CoupleManagerConfig.Q10_ANCIENT_ADENA, CoupleManagerConfig.Q10_ANCIENT_ADENA_COUNT);
				htmltext = CoupleManagerConfig.q10_finished;
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q10_1;
			}
		}
		/**
		 * Quest 11 - Hot Partner
		 */
		if (i == 11)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q11_finished;				
				st.addExpAndSp(CoupleManagerConfig.Q11_EXP_REWARD, CoupleManagerConfig.Q11_SP_REWARD);				
				pst.addExpAndSp(CoupleManagerConfig.Q11_EXP_REWARD, CoupleManagerConfig.Q11_SP_REWARD);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q11_1;
			}			
		}
		/**
		 * Quest 12 - Energy Kiss
		 */
		if (i == 12)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q12_finished;				
				player.setVitalityPoints(PcStat.MAX_VITALITY_POINTS,true);
				marriedPartner.setVitalityPoints(PcStat.MAX_VITALITY_POINTS,true);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q12_1;
			}			
		}
		/**
		 * Quest 13 - Write Your Feelings
		 */
		if (i == 13)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questFinished") == "1" && pst.get("questFinished") == "1")
			{
				htmltext = CoupleManagerConfig.q13_finished;				
				st.giveItems(CoupleManagerConfig.Q13_ADENA, CoupleManagerConfig.Q13_ADENA_COUNT);
				pst.giveItems(CoupleManagerConfig.Q13_ADENA, CoupleManagerConfig.Q13_ADENA_COUNT);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q13_1;
			}			
		}
		/**
		 * Quest 14 - Find Marriage Gift
		 */
		if (i == 14)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.getQuestItemsCount(CoupleManagerConfig.Q14_GEMSTONE_C)>=50 
					&& st.getQuestItemsCount(CoupleManagerConfig.Q14_SPIRIT_BEAD) >= 50
					&& pst.getQuestItemsCount(CoupleManagerConfig.Q14_GEMSTONE_C)>=50
					&& pst.getQuestItemsCount(CoupleManagerConfig.Q14_SPIRIT_BEAD) >= 50)
			{
				htmltext = CoupleManagerConfig.q14_finished;
				st.takeItems(CoupleManagerConfig.Q14_SPIRIT_BEAD, 50);
				st.takeItems(CoupleManagerConfig.Q14_GEMSTONE_C, 50);
				st.giveItems(CoupleManagerConfig.Q14_AGATHION_OF_LOVE, 1);
				pst.takeItems(CoupleManagerConfig.Q14_SPIRIT_BEAD, 50);
				pst.takeItems(CoupleManagerConfig.Q14_GEMSTONE_C, 50);
				pst.giveItems(CoupleManagerConfig.Q14_AGATHION_OF_LOVE, 1);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q14_1;
			}			
		}
		/**
		 * Quest 15 - Summon Your Love
		 */
		if (i == 15)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.getQuestItemsCount(CoupleManagerConfig.Q15_COIN)>=150 
					&& pst.getQuestItemsCount(CoupleManagerConfig.Q15_COIN)>=150)
			{
				htmltext = CoupleManagerConfig.q15_finished;
				st.takeItems(CoupleManagerConfig.Q15_COIN, 150);
				st.giveItems(CoupleManagerConfig.Q15_SUMMON_LOVE_BRACELET, 1);
				pst.takeItems(CoupleManagerConfig.Q15_COIN, 150);
				pst.giveItems(CoupleManagerConfig.Q15_SUMMON_LOVE_BRACELET, 1);
				st.set("questStarted","0");
				st.set("questFinished","0");
				pst.set("questStarted","0");
				pst.set("questFinished","0");
				st.set("questNumber", "0");
				pst.set("questNumber", "0");
			}
			else
			{
				htmltext = CoupleManagerConfig.q15_1;
			}			
		}
		return htmltext;
	}

	private String questStartProcedure(L2PcInstance player,int i) 
	{
		String htmltext = "";
		/**
		 * Quest: 1 - Help My Love
		 */
		if (i == 1)
		{
			L2Party party = player.getParty();
			int _partnerId = player.getPartnerId();
			L2PcInstance partner = L2World.getInstance().getPlayer(_partnerId);
			if (partner == null || partner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = partner.getQuestState(qn);			
			if (party == null)
			{					
				player.sendMessage("You must be in party with your partner.");					
			}
			else
			{
				if (party.getMemberCount() > 2)
				{
					player.sendMessage("You must be only with partner in party");
				}
				else
				{
					for (L2PcInstance partyMember: party.getPartyMembers())
					{
						if (partyMember.getObjectId() == _partnerId)
						{
							htmltext = CoupleManagerConfig.q1_0;
							st.set("questStarted", "1");
							pst.set("questStarted","1");
							st.set("questFinished", "0");
							pst.set("questFinished", "0");
						}
						else if (partyMember.getObjectId() != player.getObjectId() && partyMember.getObjectId() != _partnerId)
						{
							player.sendMessage("Your party member are not your Partner.");
						}
					}
				}
			}
		}
		/**
		 * Quest 2 - Defend Your Feelings
		 */
		if (i == 2)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q2_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 3 - Sweet Home
		 */
		if (i == 3)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q3_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 4 - Count Your Love
		 */
		if (i == 4)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q4_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 5 - Missing Hearth
		 */
		if (i == 5)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q5_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 6 - Kiss Me Please
		 */
		if (i == 6)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q6_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 7 - Pirate and Queen
		 */
		if (i == 7)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q7_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 8 - Ride Me Honey
		 */
		if (i == 8)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q8_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 9 - Love Adventure
		 */
		if (i == 9)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q9_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 10 - Burn My Wallet
		 */
		if (i == 10)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q10_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 11 - Hot Partner
		 */
		if (i == 11)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q11_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 12 - Energy Kiss
		 */
		if (i == 12)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q12_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 13 - Write Your Feelings
		 */
		if (i == 13)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q13_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 14 - Find Marriage Gift
		 */
		if (i == 14)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q14_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		/**
		 * Quest 15 - Summon Your Love 
		 */
		if (i == 15)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner == null || marriedPartner.isOnline() == 0)
			{
				return CoupleManagerConfig.partnerIsNotOnline;
			}
			QuestState st = player.getQuestState(qn);
			QuestState pst = marriedPartner.getQuestState(qn);
			if (st.get("questStarted") == "0")
			{
				htmltext = CoupleManagerConfig.q15_0;
				st.set("questStarted", "1");
				pst.set("questStarted","1");
				st.set("questFinished", "0");
				pst.set("questFinished", "0");
			}
		}
		return htmltext;
	}
	
	private void killProcedure(L2PcInstance player, L2Npc npc, int questNumber) 
	{
		/**
		 * Quest 1 - Help My Love
		 */
		if (questNumber == 1)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q1_HOT_SPRINGS_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								L2Party party = player.getParty();
								if (party != null)
								{
									if (party.getMemberCount() == 2)
									{
										for (L2PcInstance partyMember: party.getPartyMembers())
										{
											if (partyMember.getObjectId() == _partnerId)
											{						
												if (st.get("killcount") != null)
												{
													if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q1_KILL_COUNT)
													{
														st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount")) + 1));
														player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q1_KILL_COUNT + " Monsters.");													
													}
													if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q1_KILL_COUNT)
													{
														st.set("questFinished", "1");
														st.unset("killcount");
													}
												}
												else
												{												
													st.set("killcount", "1");
													player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q1_KILL_COUNT + " Monsters.");
												}											
											}
										}
									}
								}					
							}
						}
					}
				}
			}			
		}
		/**
		 * Quest 2 - Defend Your Feelings
		 */
		if (questNumber == 2)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (player.getAppearance().getSex() == false)
					{
						if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
						{
							for (int monsterId : CoupleManagerConfig.Q2_DEVILS_ISLE_MONSTERS)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q2_KILL_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q2_KILL_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q2_KILL_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}									
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q2_KILL_COUNT + " Monsters.");
									}
								}							
							}
						}
					}
					else
					{
						if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
						{
							for (int monsterId : CoupleManagerConfig.Q2_GARDEN_OF_EVA_MONSTERS)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q2_KILL_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q2_KILL_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q2_KILL_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q2_KILL_COUNT + " Monsters.");
									}
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 3 - Sweet Home
		 * this quest don't have kill procedure
		 */
		
		/**
		 * Quest 4 - Count Your Love
		 */
		if (questNumber == 4)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q4_DUNGEON_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								if (st.get("killcount") != null)
								{
									if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q4_KILL_COUNT)
									{
										st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q4_KILL_COUNT + " Monsters.");										
									}
									if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q4_KILL_COUNT)
									{
										st.set("questFinished", "1");
										st.unset("killcount");
									}
								}
								else
								{
									st.set("killcount", "1");
									player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q4_KILL_COUNT + " Monsters.");
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 5 - Missing Heart
		 */
		if (questNumber == 5)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q4_DUNGEON_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								if (st.getQuestItemsCount(CoupleManagerConfig.Q5_MUSICAL_THEME_OF_LOVE) < 1)
								{
									st.giveItems(CoupleManagerConfig.Q5_MUSICAL_THEME_OF_LOVE,1);
									st.set("questFinished", "1");
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 6 - Kiss Me Please
		 */
		if (questNumber == 6)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					QuestState pst = marriedPartner.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						if (player.getAppearance().getSex() == false)
						{
							for (int monsterId: CoupleManagerConfig.Q6_BAD_MONSTER)
							{
								if (npc.getNpcId() == monsterId)
								{
									st.set("questFinished", "1");
									pst.set("questFinished", "1");
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 7 - Pirate and Queen
		 */
		if (questNumber == 7)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						if (player.getAppearance().getSex() == true)
						{
							for (int monsterId : CoupleManagerConfig.Q7_ANT)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q7_ANT_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q7_ANT_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q7_ANT_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q7_ANT_COUNT + " Monsters.");
									}
								}
							}
						}
						else
						{
							for (int monsterId : CoupleManagerConfig.Q7_PIRATE)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q7_PIRATE_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q7_PIRATE_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q7_PIRATE_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q7_PIRATE_COUNT + " Monsters.");
									}
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 8 - Ride Me Honey
		 * this quest don't have kill procedure
		 */
		/**
		 * Quest 9 - Love Adventure
		 */
		if (questNumber == 9)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (player.getAppearance().getSex() == false)
					{
						if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
						{
							for (int monsterId : CoupleManagerConfig.Q9_MOS_MONSTERS)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q9_KILL_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q9_KILL_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q9_KILL_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}									
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q9_KILL_COUNT + " Monsters.");
									}
								}							
							}
						}
					}
					else
					{
						if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
						{
							for (int monsterId : CoupleManagerConfig.Q9_PAGAN_MONSTERS)
							{
								if (npc.getNpcId() == monsterId)
								{
									if (st.get("killcount") != null)
									{
										if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q9_KILL_COUNT)
										{
											st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
											player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q9_KILL_COUNT + " Monsters.");										
										}
										if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q9_KILL_COUNT)
										{
											st.set("questFinished", "1");
											st.unset("killcount");
										}
									}
									else
									{
										st.set("killcount", "1");
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q9_KILL_COUNT + " Monsters.");
									}
								}
							}
						}
					}
				}
			}
		}
		/**
		 * Quest 10 - don't have kill procedure
		 */
		/**
		 * Quest 11 - Hot Partner
		 */
		if (questNumber == 11)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q11_FORGE_OF_THE_GODS_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								if (st.get("killcount") != null)
								{
									if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q11_KILL_COUNT)
									{
										st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q11_KILL_COUNT + " Monsters.");										
									}
									if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q11_KILL_COUNT)
									{
										st.set("questFinished", "1");
										st.unset("killcount");
									}									
								}
								else
								{
									st.set("killcount", "1");
									player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q11_KILL_COUNT + " Monsters.");
								}
							}							
						}
					}
				}
			}
		}
		/**
		 * Quest 12 - Energy Kiss
		 */
		if (questNumber == 12)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q12_IMPERIAL_TOMB_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								if (st.get("killcount") != null)
								{
									if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q12_KILL_COUNT)
									{
										st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q12_KILL_COUNT + " Monsters.");										
									}
									if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q12_KILL_COUNT)
									{
										st.set("questFinished", "1");
										st.unset("killcount");
									}									
								}
								else
								{
									st.set("killcount", "1");
									player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q12_KILL_COUNT + " Monsters.");
								}
							}							
						}
					}
				}
			}
		}
		/**
		 * Quest 13 - Write Your Feelings
		 */
		if (questNumber == 13)
		{
			int _partnerId = player.getPartnerId();
			L2PcInstance marriedPartner = (L2PcInstance) L2World.getInstance().getPlayer(_partnerId);
			if (marriedPartner != null)
			{
				if (marriedPartner.isOnline() > 0)
				{
					QuestState st = player.getQuestState(qn);
					if (st.get("questStarted") == "1" && st.get("questFinished") == "0")
					{
						for (int monsterId : CoupleManagerConfig.Q13_URSUS_MONSTERS)
						{
							if (npc.getNpcId() == monsterId)
							{
								if (st.get("killcount") != null)
								{
									if (Integer.valueOf(st.get("killcount")) < CoupleManagerConfig.Q13_KILL_COUNT)
									{
										st.set("killcount", String.valueOf(Integer.valueOf(st.get("killcount"))+ 1));
										player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q13_KILL_COUNT + " Monsters.");										
									}
									if (Integer.valueOf(st.get("killcount")) == CoupleManagerConfig.Q13_KILL_COUNT)
									{
										st.set("questFinished", "1");
										st.unset("killcount");
									}									
								}
								else
								{
									st.set("killcount", "1");
									player.sendMessage("You killed " + st.get("killcount") + " of " + CoupleManagerConfig.Q13_KILL_COUNT + " Monsters.");
								}
							}							
						}
					}
				}
			}
		}
		/**
		 * Quest 14 - don't have kill procedure
		 */
		/**
		 * Quest 15 - don't have kill procedure
		 */
	}
	public final String onKill(L2Npc npc,L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		int questNumber = 0; 
		try
		{
			questNumber = Integer.valueOf(st.get("questNumber"));
		}
		catch (Exception e)
		{
			return super.onKill(npc, player, isPet);
		}
		if (questNumber >0)
		{
			killProcedure(player,npc,questNumber);
		}
		return super.onKill(npc, player, isPet);
	}

	public CoupleManager(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(COUPLE_NPC);
		addFirstTalkId(COUPLE_NPC);
		addTalkId(COUPLE_NPC);
		/**
		 * Quest 1 - Monsters
		 */
		for (int npcId : CoupleManagerConfig.Q1_HOT_SPRINGS_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 2 - Monsters
		 */
		for (int npcId : CoupleManagerConfig.Q2_DEVILS_ISLE_MONSTERS)
			addKillId(npcId);
		for (int npcId : CoupleManagerConfig.Q2_GARDEN_OF_EVA_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 3 - don't have kill procedure
		 */
		
		/**
		 * Quest 4 - Monsters 
		 */
		for (int npcId: CoupleManagerConfig.Q4_DUNGEON_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 5 -the same as quest 4
		 */
		/**
		 * Quest 6
		 */
		for (int npcId: CoupleManagerConfig.Q6_BAD_MONSTER)
			addKillId(npcId);
		/**
		 * Quest 7
		 */
		for (int npcId: CoupleManagerConfig.Q7_PIRATE)
			addKillId(npcId);
		for (int npcId: CoupleManagerConfig.Q7_ANT)
			addKillId(npcId);
		/**
		 * Quest 8 - don't have kill procedure
		 */
		/**
		 * Quest 9 - Love Adventure
		 */
		for (int npcId: CoupleManagerConfig.Q9_MOS_MONSTERS)
			addKillId(npcId);
		for (int npcId: CoupleManagerConfig.Q9_PAGAN_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 10 - don't have kill procedure
		 */
		/**
		 * Quest 11 - Hot Partner
		 */
		for (int npcId: CoupleManagerConfig.Q11_FORGE_OF_THE_GODS_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 12 - Energy Kiss
		 */
		for (int npcId: CoupleManagerConfig.Q12_IMPERIAL_TOMB_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 13 - Write Your Feelings
		 */
		for (int npcId: CoupleManagerConfig.Q13_URSUS_MONSTERS)
			addKillId(npcId);
		/**
		 * Quest 14 - don't have kill procedure
		 */
		/**
		 * Quest 15 - don't have kill procedure
		 */
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new CoupleManager(-1,qn,"Couple Manager");
		_log.warning("Couple Manager System: 15 Couple Quests Installed ...");
	}
}