package custom.MissQueen;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;

public class MissQueen extends Quest
{
	private static final String qn = "MissQueen";

	private static final int SOULSHOT_D = 1463;
	private static final int B_SPIRITSHOT_D = 3948;
	private static final int ADENA = 57;
	
	private static final int[] RECIPES_REWARD = {
		1808,1807,1806,1805,1804, // Soulshots Recipes
		3957,3956,3955,3954,3953  // Blessed Spiritshots Recipes
	};

	private static final int[] NPCs = {
		31760,31761,31762,31763,31764,31765,31766,31767
	};

	//enable/disable coupon give
	// now is disabled - only newbie work
	private static final boolean QUEEN_ENABLED = true;
	//Newbie/one time rewards section
	//Any quest should rely on a unique bit, but
	//it could be shared among quest that were mutually
	//exclusive or race restricted.
	//Bit #1 isn't used for backwards compatibility.
	//This script uses 2 bits, one for newbie coupons and another for travelers
	private static final int TRAVELER_REWARD = 32;

	public MissQueen(int id, String name, String descr)
	{
		super(id, name, descr);
		for (int i : NPCs)
		{
			addStartNpc(i);
		    addFirstTalkId(i);
		    addTalkId(i);
		}
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		htmltext = "welcome.htm";
		return htmltext;
	}

	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
	    if (QUEEN_ENABLED)
	    {
	    	st.setState(State.STARTED);
	    	int newbie = player.getNewbie();
	    	int level = player.getLevel();
	    	int occupation_level = player.getClassId().level();
	    	int pkkills = player.getPkKills();
	    	if (event.equalsIgnoreCase("reached20"))
	    	{
	    		int getReward1 = 0;
	    		try
	    		{
	    			getReward1 = Integer.valueOf(st.get("reward1"));
	    		}
	    		catch (Exception e)
	    		{
	    		}
	    		if (getReward1 == 0 && (newbie > 0 || TRAVELER_REWARD != newbie))
	    		{
	    			//check level of player, pk and class
	    			if (level >= 20 && pkkills == 0 && player.getKarma() == 0 && occupation_level == 1)
	    			{
	    				st.giveItems(SOULSHOT_D,1500);
	    				st.giveItems(B_SPIRITSHOT_D,1000);
	    				htmltext = "<html><body>Promotion Miss Queen:<br>";
	    				htmltext += "Here's the reward you requested. You're now can go into world of Lineage II without problems. Good luck!";
	    				htmltext += "</body></html>";
	    				st.set("reward1", "1");
	    				return htmltext;//you got reward already!
	    			}
	    			else
	    			{
	    				htmltext = "<html><body>Promotion Miss Queen:<br>";
	    				htmltext += "You cannot get reward! You need 20+ level, ocupation class 1 and don't get karma or pk.";
	    				htmltext += "Do you actually think you can fool Miss Queen?<br>";
	    				htmltext += "</body></html>";
	    				return htmltext;//you got reward already!
	    			}
	    		}
	    		else
	    		{
	    			htmltext = "<html><body>Promotion Miss Queen:<br>";
    				htmltext += "You've already received a reward! Do you actually think you can fool Miss Queen?<br>(Players are only allowed one time get promotion reward.)";
    				htmltext += "</body></html>";
    				return htmltext;//you got reward already!
	    		}
	    	}	    	
	    	if (event.equalsIgnoreCase("reached35"))
	    	{
	    		int getReward2 = 0;
	    		try
	    		{
	    			getReward2 = Integer.valueOf(st.get("reward2"));
	    		}
	    		catch (Exception e)
	    		{
	    		}
	    		if (player.getClassId().getId() == 56)
	    		{
	    			if (getReward2 == 0 && (newbie > 0 || TRAVELER_REWARD != newbie))
	    			{
	    				//check level of player, pk and class
	    				if (level >= 35 && pkkills == 0 && player.getKarma() == 0 && occupation_level == 1)
	    				{
	    					for (int recipeId: RECIPES_REWARD)
	    					{
	    						st.giveItems(recipeId,1);
	    					}	    					
	    					htmltext = "<html><body>Promotion Miss Queen:<br>";
	    					htmltext += "Here's the reward you requested. You're now can go into world of Lineage II without problems. Good luck!";
	    					htmltext += "</body></html>";
	    					st.set("reward2", "1");
	    					return htmltext;//you got reward already!
	    				}
	    				else
	    				{
	    					htmltext = "<html><body>Promotion Miss Queen:<br>";
	    					htmltext += "You cannot get reward! You need 35+ level, ocupation class 1 and don't get karma or pk.";
	    					htmltext += "Do you actually think you can fool Miss Queen?<br>";
	    					htmltext += "</body></html>";
	    					return htmltext;//you got reward already!
	    				}
	    			}
	    			else
	    			{
	    				htmltext = "<html><body>Promotion Miss Queen:<br>";
	    				htmltext += "You've already received a reward! Do you actually think you can fool Miss Queen?<br>(Players are only allowed one time get promotion reward.)";
	    				htmltext += "</body></html>";
	    				return htmltext;//you got reward already!
	    			}
	    		}
	    		else
    			{
    				htmltext = "<html><body>Promotion Miss Queen:<br>";
    				htmltext += "You cannot get a craftsman reward. You are not craft. Do you actually think you can fool Miss Queen?<br>(Players that can craft only allowed get promotion reward.)";
    				htmltext += "</body></html>";
    				return htmltext;//you got reward already!
    			}
	    	}
	    	if (event.equalsIgnoreCase("clan"))
	    	{
	    		int getReward3 = 0;
	    		try
	    		{
	    			getReward3 = Integer.valueOf(st.get("reward3"));
	    		}
	    		catch (Exception e)
	    		{
	    		}
	    		if (getReward3 == 0 && (newbie > 0 || TRAVELER_REWARD != newbie))
	    		{
	    			//check level of player, pk and class
	    			if (player.getClan() != null)
	    			{
	    				if (player.isAcademyMember() && player.getClan().getMembersCount() >= 100)
	    				{
	    					if (level >= 35 && pkkills == 0 && player.getKarma() == 0 && occupation_level == 1)
	    					{
	    						st.giveItems(ADENA,1500000);
	    						htmltext = "<html><body>Promotion Miss Queen:<br>";
	    						htmltext += "Here's the reward you requested. You're now can go into world of Lineage II without problems. Good luck!";
	    						htmltext += "</body></html>";
	    						st.set("reward3", "1");
	    						return htmltext;//you got reward already!
	    					}
	    					else
	    					{
	    						htmltext = "<html><body>Promotion Miss Queen:<br>";
	    						htmltext += "You cannot get reward! You need 35+ level, ocupation class 1 and don't get karma or pk.";
	    						htmltext += "Do you actually think you can fool Miss Queen?<br>";
	    						htmltext += "</body></html>";
	    						return htmltext;//you got reward already!
	    					}
	    				}
	    				else
	    				{
	    					htmltext = "<html><body>Promotion Miss Queen:<br>";
    						htmltext += "You cannot get reward! You need be in clan academy with 100 Clan Members or more.";
    						htmltext += "Do you actually think you can fool Miss Queen?<br>";
    						htmltext += "</body></html>";
    						return htmltext;//you got reward already!
	    				}
	    			}
	    			else
	    			{
	    				htmltext = "<html><body>Promotion Miss Queen:<br>";
						htmltext += "You cannot get reward! You are not clan academy member.";
						htmltext += "Do you actually think you can fool Miss Queen?<br>";
						htmltext += "</body></html>";
						return htmltext;//you got reward already!
	    			}
	    		}
	    		else
	    		{
	    			htmltext = "<html><body>Promotion Miss Queen:<br>";
	    			htmltext += "You've already received a reward! Do you actually think you can fool Miss Queen?<br>(Players are only allowed one time get promotion reward.)";
	    			htmltext += "</body></html>";
	    			return htmltext;//you got reward already!
	    		}
	    	}
	    }
	    else
	    {
	    	htmltext = "<html><body>Promotion Miss Queen:<br>";
			htmltext += "You looking for help right? But i don't have time now to help you i have self problems.";
			htmltext += "</body></html>";
	    }
		return htmltext;
	}

	public static void main(String[] args)
	{
		new MissQueen(-1, qn, "custom");
	}
}