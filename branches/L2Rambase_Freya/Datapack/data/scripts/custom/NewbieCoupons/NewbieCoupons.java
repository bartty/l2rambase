package custom.NewbieCoupons;

import l2.rambase.gameserver.model.L2Rambaseultisell;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class NewbieCoupons extends Quest
{
	private static final String qn = "5001_NewbieCoupons";
	
	private static final int[] NPCs =
	{
		30598,30599,30600,30601,30602,30603,31076,31077,32135
	};
	
	private static final int COUPON_ONE = 7832;
	private static final int COUPON_TWO = 7833;

	//Multisell
	private static final int WEAPON_MULTISELL = 305986001;
	private static final int ACCESORIES_MULTISELL = 305986002;

	//enable/disable coupon give
	private static final boolean NEWBIE_COUPONS_ENABLED = true;
	//Newbie/one time rewards section
	//Any quest should rely on a unique bit, but
	//it could be shared among quests that were mutually
	//exclusive or race restricted.
	//Bit #1 isn't used for backwards compatibility.
	//This script uses 2 bits, one for newbie coupons and another for travelers
	//These 2 bits happen to be the same used by the Miss Queen script
	private static final int NEWBIE_WEAPON = 16;
	private static final int NEWBIE_ACCESORY = 32;
	
	public NewbieCoupons(int id, String name, String descr)
	{
		super(id, name, descr);
		for (int i : NPCs)
		{
			addStartNpc(i);
		    addTalkId(i);
		}
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		htmltext = "<html><body>Newbie Guide:<br>";
		htmltext += "As token of congratulations, the Adventure Guild is pleased to open its warehouse to both apprentice adventurers";
		htmltext += "who have reached level 6 as well as travelers who have completed their first occupation change.";
		htmltext += "At level 6, you can borrow a <font color=LEVEL>No-Grade Shadow Weapon</font> with the";
		htmltext += "<font color=LEVEL>Apprentice Adventurer's Weapon Exchange Coupon</font>.";
		htmltext += "At level 20, you can borrow <font color=LEVEL>Shadow Armor</font> with the";
		htmltext += "<font color=LEVEL>Adventurer's Magic Armor Exchange Coupon</font>.";
		htmltext += "No-Grade Shadow Weapons can be exchanged up to level 19 and Shadow Armor can be exchanged up to level 39.";
		htmltext += "Now then: how can I help you?<br>(Note: chaotic characters may not use weapons or armor borrowed from the Adventure Guild.)<br>";
		htmltext += "<a action=\"bypass -h Quest 5001_NewbieCoupons newbie_give_weapon_coupon\">Get the Apprentice Adventurer's Weapon Exchange Coupon.</a><br>";
		htmltext += "<a action=\"bypass -h Quest 5001_NewbieCoupons newbie_show_weapon\">Use the weapon warehouse for apprentice adventurers.</a><br>";
		htmltext += "<a action=\"bypass -h Quest 5001_NewbieCoupons newbie_give_armor_coupon\">Get the Adventurer's Magic Armor Exchange Coupon.</a><br>";
		htmltext += "<a action=\"bypass -h Quest 5001_NewbieCoupons newbie_show_armor\">Use the Magic Armor warehouse for travelers.</a><br>";
		htmltext += "</body></html>";
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
		if (NEWBIE_COUPONS_ENABLED)
		{
			int newbie = player.getNewbie();
			int level = player.getLevel();
			int occupation_level = player.getClassId().level();
			int pkkills = player.getPkKills();
			if (event.equalsIgnoreCase("newbie_give_weapon_coupon"))
			{
				//@TODO: check if this is the very first character for this account
				//would need a bit of SQL, or a core method to determine it.
				//This condition should be stored by the core in the account_data table
				//upon character creation.
				if ((level >= 6 && level <= 39) && pkkills < 1 && occupation_level == 0)
				{
					//check the player state against this quest newbie rewarding mark.
					if (newbie > 0 || NEWBIE_WEAPON != newbie)
					{
						player.setNewbie(NEWBIE_WEAPON);
						st.giveItems(COUPON_ONE,5);
						htmltext = "<html><body>Newbie Guide:<br>";
						htmltext += "Here is the Apprentice Adventurer's Weapon Exchange Coupon.<br>";
						htmltext += "You can exchange this ticket for the No-Grade Shadow Weapon from the Adventure Guild warehouse.";
						htmltext += "Keep in mind that this Exchange Coupon is reserved only for you.";
						htmltext += "Should you lose either the ticket or the weapon you borrowed, you cannot get a new one.";
						htmltext += "Guard it carefully! You can use the weapon warehouse if you visit the Newbie Guide in each town.";
						htmltext += "I wish you a pleasant and prosperous journey!";
						htmltext += "</body></html>";
						return htmltext; //here's the coupon you requested
					}				
					else
					{
						htmltext = "<html><body>Newbie Guide:<br>";
						htmltext += "You have already received the Apprentice Adventurer's Weapon Exchange Coupon!";
						htmltext += "Trying to trick me, eh? Don't underestimate my memory for faces!";
						htmltext += "(Each character may only receive 1 Apprentice Adventurer's Weapon Exchange Coupon.)";
						htmltext += "</body></html>";
						return htmltext; //you got a coupon already!
					}
				}
				else
				{
					htmltext = "<html><body>Newbie Guide:<br>";
					htmltext += "You are not eligible to have the Apprentice Adventurer's Weapon Exchange Coupon!";
					htmltext += "It is reserved especially for new adventurers who are visiting the world of Lineage 2 for the first time.";
					htmltext += "You may not receive the Exchange Coupon if your level is under 6 or you have completed an occupation change.";
					htmltext += "(Only beginners who are between <font color=LEVEL>level 6 and level 19</font> may use it.)";
					htmltext += "</body></html>";
					return htmltext; //you're not eligible to get a coupon (level caps, pkkills or already changed class)
				}
			}
			else if (event.equalsIgnoreCase("newbie_give_armor_coupon"))
			{
				if ((level >= 6 && level <= 39) && pkkills < 1 && occupation_level == 1)
				{
					//check the player state against this quest newbie rewarding mark.
					if (newbie > 0 || NEWBIE_ACCESORY != newbie)
					{
						player.setNewbie(newbie|NEWBIE_ACCESORY);
						st.giveItems(COUPON_TWO,1);
						htmltext = "<html><body>Newbie Guide:<br>";
						htmltext += "Here is the Apprentice Adventurer's Armor Exchange Coupon.<br>";
						htmltext += "You can exchange this ticket for the No-Grade Shadow Armor from the Adventure Guild warehouse.";
						htmltext += "Keep in mind that this Exchange Coupon is reserved only for you.";
						htmltext += "Should you lose either the ticket or the weapon you borrowed, you cannot get a new one.";
						htmltext += "Guard it carefully! You can use the weapon warehouse if you visit the Newbie Guide in each town.";
						htmltext += "I wish you a pleasant and prosperous journey!";
						htmltext += "</body></html>";
						return htmltext; //here's the coupon you requested
					}
					else
					{
						htmltext = "<html><body>Newbie Guide:<br>";
						htmltext += "You have already received the Apprentice Adventurer's Armor Exchange Coupon!";
						htmltext += "Trying to trick me, eh? Don't underestimate my memory for faces!";
						htmltext += "(Each character may only receive 1 Apprentice Adventurer's Armor Exchange Coupon.)";
						htmltext += "</body></html>";
						return htmltext; //you got a coupon already!
					}
				}
				else
				{					
					htmltext = "<html><body>Newbie Guide:<br>";
					htmltext += "You are not eligible to receive the Apprentice Adventurer's Armor Exchange Coupon!";
					htmltext += "It is reserved for new adventurers visiting the world of Lineage 2 for the first time.";
					htmltext += "Even then, you can not receive the ticket unless you have completed the first occupation change!";
					htmltext += "(Only beginners who have <font color=LEVEL>completed the first occupation change</font> may use it.)";
					htmltext += "</body></html>";
					return htmltext; //you're not eligible to get a coupon (level caps, pkkills or didnt change class yet)
				}
			}
			else if (event.equalsIgnoreCase("newbie_show_weapon"))
			{
				if ((level >= 6 && level <= 39) && pkkills < 1 && occupation_level == 0)
				{
					L2Rambaseultisell.getInstance().separateAndSend(WEAPON_MULTISELL, player, npc.getNpcId(), false, 0.0);
				}
				else
				{
					htmltext = "<html><body>Newbie Guide:<br>";
					htmltext += "You are not eligible to use the warehouse.";
					htmltext += "You may not receive the Exchange Coupon if your level is under 6 or you have completed an occupation change.";
					htmltext += "(Only beginners who are between <font color=LEVEL>level 6 and level 19</font> may use the warehouse.)";
					htmltext += "</body></html>";
					return htmltext; //you're not eligible to use warehouse
				}
			}
			else if (event.equalsIgnoreCase("newbie_show_armor"))
			{
				if ((level >= 6 && level <= 39) && pkkills < 1 && occupation_level > 0)
				{
					L2Rambaseultisell.getInstance().separateAndSend(ACCESORIES_MULTISELL, player, npc.getNpcId(), false, 0.0);
				}
				else
				{
					htmltext = "<html><body>Newbie Guide:<br>";
					htmltext += "You are not eligible to use the warehouse.";
					htmltext += "You must complete your first class change!";
					htmltext += "(Only <font color=LEVEL>beginners who have completed the first class change</font> may use the warehouse.)";
					htmltext += "</body></html>";
					return htmltext; //you're not eligible to use warehouse
				}
			}
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new NewbieCoupons(-1, qn, "custom");
	}
}