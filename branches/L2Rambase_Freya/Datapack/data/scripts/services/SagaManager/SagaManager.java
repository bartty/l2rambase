/**
 * Saga Manager
 * 
 * Information:
 * Script only for Clan PvE Fights
 * 
 * @version 1.2
 * @param System created for fight without PvP. 
 * @param Registered clans must fight in special event with mobs.
 */

package services.SagaManager;

import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.util.Rnd;


public class SagaManager extends Quest
{
	private static final String qn = "SagaManager";	
	
	private boolean SAGA_STARTED = false;
	private boolean SAGA_LIGHT_WIN = false;
	private boolean SAGA_DARK_WIN = false;
	private static final int SAGA_TIME = 5;
	
	private static final int[] REWARDS_A_ROBE = 
	{
		5348,5350,5352
	};
	private static final int[] REWARDS_A_LIGHT = 
	{
		5338,5334,5332,5340,5336
	};
	private static final int[] REWARDS_A_HEAVY = 
	{
		5422,5418,5416,5424,5420
	};
	private static final int[] REWARDS_A_PARTS =
	{
		5368,5392,5426,5370,5394,5428,5380,5404,5430,5382,5406,5432
	};
	private static final int[] REWARDS_S_WEAPON =
	{
		6881,6883,6885,6887,6891,6893,6895,6897,6899,7580
	};
	
	// Monster List
	private static int[] SIDE_0_MONSTERS = 
	{
		22137,22138,22139,
		22140,22141,22142,22143,22144,22145,22146,22147,22148,22149,
		22150,22151,22152,22153,22154,22155,22156,22157,22158,22159,
		22160,22161,22162,22163,22164,22165,22166,22167,22168,22169,
		22170,22171,22172,22173,22174,22175,22176,
		22188,22189,
		22190,22191,22192,22193,22194,22195
	};
	
	private static int[] SIDE_1_MONSTERS = 
	{
		22122,22123,22124,22125,22126,22127,22128,22129,
		22130,22131,22132,22133,22134,22135
	};
	
	private long LIGHT_POINTS = 0;
	private long DARK_POINTS = 0;
	
	private static final int DARK_STONE = 7910;
	private static final int LIGHT_STONE = 7911;
	
	private static final int FIRST_LEVEL = 200;
	private static final int SECOND_LEVEL = 500;
	private static final int THIRD_LEVEL = 1000;
	private static final int FOUR_LEVEL = 5000;
	
	
	private int SAGA_NPC = 70012;	
		
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
		st.setState(State.STARTED);
		String htmltext = "";
		if (player.getClan() == null)
		{
			return "<html><body>You are not in clan. So you cannot start clan Saga.</body></html>";
		}
		if (st.get("side") != null)
		{
			int side = Integer.valueOf(st.get("side"));
			if (side == 0)
			{
				htmltext = showSaga(st,player,0);
			}
			else
			{
				htmltext = showSaga(st,player,1);
			}			
		}
		else
		{
			htmltext = selectSaga(st,player);
		}		
		return htmltext;
	}
	
	private String selectSaga(QuestState st, L2PcInstance player) 
	{
		String text = "";
		text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
		text += "In the old times two sides fights with each other. One side is LIGHT and second is DARK.";
		text += "LIGHT side is Monastery of Silence Alliance, and DARK is Pagan Temple Alliance.";
		text += "Now you can help one side win. So what side you select?<br>";
		text += "<a action=\"bypass -h Quest "+qn+" select_0\">Select LIGHT side</a><br>";
		text += "<a action=\"bypass -h Quest "+qn+" select_1\">Select DARK side</a>";
		text += "</body></html>";
		return text;
	}

	private String showSaga(QuestState st, L2PcInstance player, int i) 
	{
		String text = "";
		if (i == 0)
		{
			switch(Integer.valueOf(st.get("level")))
			{
				case 0:
					if (st.getQuestItemsCount(DARK_STONE) < FIRST_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "So you select Light and you must defend against evil from Pagan Temple";
						text += "Go kill 200 monsters in first hall, then back to me";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(DARK_STONE, FIRST_LEVEL);
						st.set("level","1");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "You killed 200 monsters so i believe that you are on light side.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";													
					}
					break;
				case 1:
					if (st.getQuestItemsCount(DARK_STONE) < SECOND_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" pagan_tp\">Teleport</a><br>";
						text += "Go kill 500 monsters in second hall, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(DARK_STONE, SECOND_LEVEL);
						st.set("level","2");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 2:
					if (st.getQuestItemsCount(DARK_STONE) < THIRD_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" pagan_tp\">Teleport</a><br1>";						
						text += "Go kill 1000 monsters in second hall, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(DARK_STONE, THIRD_LEVEL);
						st.set("level","3");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 3:
					if (st.getQuestItemsCount(DARK_STONE) < FOUR_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" pagan_tp\">Teleport</a><br1>";
						if (SAGA_LIGHT_WIN)
						{
							text += "<a action=\"bypass -h Quest "+qn+" pagan_keys\">Search for keys</a><br>";
						}
						text += "Go kill 2000 monsters in third hall, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(DARK_STONE, FOUR_LEVEL);
						st.set("level","4");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 4:
					text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					text += "You are the light warrior in Eternal Throne.<br>";
					text += "<a action=\"bypass -h Quest "+qn+" pagan_tp\">Teleport</a><br1>";
					if (SAGA_LIGHT_WIN)
					{
						text += "<a action=\"bypass -h Quest "+qn+" pagan_keys\">Search for keys</a><br1>";
						text += "<a action=\"bypass -h Quest "+qn+" pagan_recipe\">Read about recipes</a><br1>";
					}
					text += "</body></html>";
					break;
					
				default:
			}			
		}
		else if (i == 1)
		{
			switch(Integer.valueOf(st.get("level")))
			{
				case 0:
					if (st.getQuestItemsCount(LIGHT_STONE) < FIRST_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "So you select Light and you must defend against light from Monastery of Silence.";
						text += "Go kill 200 monsters in library, then back to me";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(LIGHT_STONE, FIRST_LEVEL);
						st.set("level","1");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "You killed 200 monsters so i believe that you are on dark side.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";													
					}
					break;
				case 1:
					if (st.getQuestItemsCount(LIGHT_STONE) < SECOND_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" monastery_tp\">Teleport</a><br>";						
						text += "Go kill 500 monsters in first floor, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(LIGHT_STONE, SECOND_LEVEL);
						st.set("level","2");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 2:
					if (st.getQuestItemsCount(LIGHT_STONE) < THIRD_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" monastery_tp\">Teleport</a><br1>";						
						text += "Go kill 1000 monsters in first floor, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(LIGHT_STONE, THIRD_LEVEL);
						st.set("level","3");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 3:
					if (st.getQuestItemsCount(LIGHT_STONE) < FOUR_LEVEL)
					{
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Thank you for your help. But we need more help.<br>";
						text += "<a action=\"bypass -h Quest "+qn+" monastery_tp\">Teleport</a><br1>";
						if (SAGA_DARK_WIN)
						{
							text += "<a action=\"bypass -h Quest "+qn+" monastery_keys\">Search for keys</a><br>";
						}
						text += "Go kill 2000 monsters in second floor, then back to me.";
						text += "</body></html>";
					}
					else
					{
						st.takeItems(LIGHT_STONE, FOUR_LEVEL);
						st.set("level","4");
						text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						text += "Oh you killed that monsters then now you are one of us.";
						text += "Give me a one minute break, then come back.";
						text += "</body></html>";						
					}
					break;
				case 4:
					text = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					text += "You are the dark warrior in Eternal Throne.<br>";
					text += "<a action=\"bypass -h Quest "+qn+" monastery_tp\">Teleport</a><br1>";
					if (SAGA_DARK_WIN)
					{
						text += "<a action=\"bypass -h Quest "+qn+" monastery_keys\">Search for keys</a><br1>";
						text += "<a action=\"bypass -h Quest "+qn+" monastery_recipe\">Read about recipes</a><br1>";
					}
					text += "</body></html>";
					break;					
				default:
					break;
			}
		}
		return text;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		if (event.startsWith("select_"))
		{
			if (event.equalsIgnoreCase("select_0"))
			{
				htmltext = showSaga(st,player,0);
				st.set("side", "0");
				st.set("level", "0");
			}
			else if (event.equalsIgnoreCase("select_1"))
			{
				htmltext = showSaga(st,player,1);
				st.set("side", "1");
				st.set("level", "0");
			}
		}
		else if (event.startsWith("pagan_"))
		{
			if (event.startsWith("pagan_tp"))
			{
				if (event.equalsIgnoreCase("pagan_tp_1"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,15);
						player.teleToLocation(-16329,-37338,-10733);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_2"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,15);
						player.teleToLocation(-21948,-41685,-10896);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_3"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,15);
						player.teleToLocation(-10719,-41689,-10922);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_4"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,25);
						player.teleToLocation(-16375,-44412,-10733);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_5"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,25);
						player.teleToLocation(-20623,-50588,-11021);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_6"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,25);
						player.teleToLocation(-12363,-50739,-11021);
					}	
				}
				else if (event.equalsIgnoreCase("pagan_tp_7"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,45);
						player.teleToLocation(-16370,-51032,-11008);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_8"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,45);
						player.teleToLocation(-18881,-57039,-10858);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_9"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,45);
						player.teleToLocation(-13972,-56983,-10858);
					}
				}
				else if (event.equalsIgnoreCase("pagan_tp_10"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 60)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,60);
						player.teleToLocation(-16419,-51959,-10611);
					}
				}
				else
				{
					switch(Integer.valueOf(st.get("level")))
					{
						case 1:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_1\">First Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_2\">First Hall Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_3\">First Hall Right Room (15 Stones)</a><br>";
							htmltext += "</body></html>";
							break;
						case 2:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_1\">First Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_2\">First Hall Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_3\">First Hall Right Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_4\">Second Hall Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_5\">Second Hall Darkness Room 1 (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_6\">Second Hall Darkness Room 2 (25 Stones)</a><br>";						
							htmltext += "</body></html>";
							break;
						case 3:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_1\">First Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_2\">First Hall Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_3\">First Hall Right Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_4\">Second Hall Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_5\">Second Hall Darkness Room 1 (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_6\">Second Hall Darkness Room 2 (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_7\">Third Hall Center of Darkness (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_8\">Third Hall Right Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_9\">Third Hall Left Room (45 Stones)</a><br1>";
							htmltext += "</body></html>";
							break;
						case 4:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_1\">First Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_2\">First Hall Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_3\">First Hall Right Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_4\">Second Hall Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_5\">Second Hall Darkness Room 1 (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_6\">Second Hall Darkness Room 2 (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_7\">Third Hall Center of Darkness (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_8\">Third Hall Right Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_9\">Third Hall Left Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_tp_10\">Last Hall Center Square (60 Stones)</a><br1>";
							htmltext += "</body></html>";
							break;
						default:
							break;
					}
				}
			}
			else if (event.startsWith("pagan_keys"))
			{
				if (event.equalsIgnoreCase("pagan_keys_a"))
				{
					L2Multisell.getInstance().separateAndSend(70012001, player, npc.getNpcId(), false, 0.0);
				}
				else if (event.equalsIgnoreCase("pagan_keys_s"))
				{
					L2Multisell.getInstance().separateAndSend(70012002, player, npc.getNpcId(), false, 0.0);
				}
				else
				{
					htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					htmltext += "Ok so for what keys you looking?<br>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_keys_a\">Search for A grade</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_keys_s\">Search for S grade</a><br1>";
					htmltext += "</body></html>";
				}
			}
			else if (event.startsWith("pagan_recipe"))
			{
				if (event.startsWith("pagan_recipe_a"))
				{
					if (event.equalsIgnoreCase("pagan_recipe_a_1"))
					{
						if (st.getQuestItemsCount(DARK_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(DARK_STONE,200);
							giveRandomRecipe("a_1",player,st);
						}
					}
					else if (event.equalsIgnoreCase("pagan_recipe_a_2"))
					{
						if (st.getQuestItemsCount(DARK_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(DARK_STONE,200);
							giveRandomRecipe("a_2",player,st);
						}
					}
					else if (event.equalsIgnoreCase("pagan_recipe_a_3"))
					{
						if (st.getQuestItemsCount(DARK_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(DARK_STONE,200);
							giveRandomRecipe("a_3",player,st);
						}
					}
					else if (event.equalsIgnoreCase("pagan_recipe_a_4"))
					{						
						if (st.getQuestItemsCount(DARK_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(DARK_STONE,200);
							giveRandomRecipe("a_4",player,st);
						}
					}
					else
					{
						htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						htmltext += "I have some A grade paper recipes. So for what type of recipe you looking?<br>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_a_1\">Random light armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_a_2\">Random heavy armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_a_3\">Random robe armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_a_4\">Random other parts (200 Stones)</a><br1>";
						htmltext += "</body></html>";
					}
				}
				else if (event.equalsIgnoreCase("pagan_recipe_s"))
				{
					if (st.getQuestItemsCount(DARK_STONE) < 500)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(DARK_STONE,500);
						giveRandomRecipe("s",player,st);
					}
				}
				else
				{
					htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					htmltext += "You looking for some recipes? What grade?<br>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_a\">A grade Recipe</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" pagan_recipe_s\">Random S grade weapon recipe (500 Stones)</a><br1>";					
					htmltext += "</body></html>";
				}
			}
		}
		else if (event.startsWith("monastery_"))
		{
			if (event.startsWith("monastery_tp"))
			{
				if (event.equalsIgnoreCase("monastery_tp_1"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,15);
						player.teleToLocation(115956,-87263,-3405);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_2"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,15);
						player.teleToLocation(116008,-79396,-3405);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_3"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 15)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,15);
						player.teleToLocation(118773,-81824,-2928);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_4"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,25);
						player.teleToLocation(116170,-81422,-1776);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_5"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,25);
						player.teleToLocation(111080,-81007,-1632);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_6"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 25)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,25);
						player.teleToLocation(110294,-79713,-1584);
					}	
				}
				else if (event.equalsIgnoreCase("monastery_tp_7"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,45);
						player.teleToLocation(109933,-74823,-1096);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_8"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,45);
						player.teleToLocation(109051,-72920,-868);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_9"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 45)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,45);
						player.teleToLocation(114592,-71681,-560);
					}
				}
				else if (event.equalsIgnoreCase("monastery_tp_10"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 60)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,60);
						player.teleToLocation(116504,-72803,-336);
					}
				}
				else
				{
					switch(Integer.valueOf(st.get("level")))
					{
						case 1:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_1\">Library Right Room(15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_2\">Library Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_3\">Library Hall (15 Stones)</a><br>";
							htmltext += "</body></html>";
							break;
						case 2:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_1\">Library Right Room(15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_2\">Library Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_3\">Library Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_4\">First Floor Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_5\">First Floor Right Room (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_6\">First Floor Left Room (25 Stones)</a><br>";						
							htmltext += "</body></html>";
							break;
						case 3:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "Ok so lets see where i can teleport you.<br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_1\">Library Right Room(15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_2\">Library Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_3\">Library Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_4\">First Floor Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_5\">First Floor Right Room (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_6\">Second Floor Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_7\">Second Floor Left Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_8\">Third Floor Entrance (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_9\">Third Floor Right Room (45 Stones)</a><br1>";
							htmltext += "</body></html>";
							break;
						case 4:
							htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_1\">Library Right Room(15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_2\">Library Left Room (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_3\">Library Hall (15 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_4\">First Floor Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_5\">First Floor Right Room (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_6\">Second Floor Entrance (25 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_7\">Second Floor Left Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_8\">Third Floor Entrance (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_9\">Third Floor Right Room (45 Stones)</a><br1>";
							htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_tp_10\">Four Floor Entrance (60 Stones)</a><br1>";
							htmltext += "</body></html>";
							break;
						default:
							break;
					}
				}
			}
			else if (event.startsWith("monastery_keys"))
			{
				if (event.equalsIgnoreCase("monastery_keys_a"))
				{
					L2Multisell.getInstance().separateAndSend(70012003, player, npc.getNpcId(), false, 0.0);
				}
				else if (event.equalsIgnoreCase("monastery_keys_s"))
				{
					L2Multisell.getInstance().separateAndSend(70012004, player, npc.getNpcId(), false, 0.0);
				}
				else
				{
					htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					htmltext += "Ok so for what keys you looking?<br>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_keys_a\">Search for A grade</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_keys_s\">Search for S grade</a><br1>";
					htmltext += "</body></html>";
				}
			}
			else if (event.startsWith("monastery_recipe"))
			{
				if (event.startsWith("monastery_recipe_a"))
				{
					if (event.equalsIgnoreCase("monastery_recipe_a_1"))
					{
						if (st.getQuestItemsCount(LIGHT_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(LIGHT_STONE,200);
							giveRandomRecipe("a_1",player,st);
						}
					}
					else if (event.equalsIgnoreCase("monastery_recipe_a_2"))
					{
						if (st.getQuestItemsCount(LIGHT_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(LIGHT_STONE,200);
							giveRandomRecipe("a_2",player,st);
						}
					}
					else if (event.equalsIgnoreCase("monastery_recipe_a_3"))
					{
						if (st.getQuestItemsCount(LIGHT_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(LIGHT_STONE,200);
							giveRandomRecipe("a_3",player,st);
						}
					}
					else if (event.equalsIgnoreCase("monastery_recipe_a_4"))
					{
						if (st.getQuestItemsCount(LIGHT_STONE) < 200)
						{
							player.sendMessage("You have enough stones");
						}
						else
						{
							st.takeItems(LIGHT_STONE,200);
							giveRandomRecipe("a_4",player,st);
						}
					}
					else
					{
						htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
						htmltext += "I have some A grade paper recipes. So for what type of recipe you looking?<br>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_a_1\">Random light armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_a_2\">Random heavy armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_a_3\">Random robe armor (200 Stones)</a><br1>";
						htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_a_4\">Random other parts (200 Stones)</a><br1>";
						htmltext += "</body></html>";
					}
				}
				else if (event.equalsIgnoreCase("monastery_recipe_s"))
				{
					if (st.getQuestItemsCount(LIGHT_STONE) < 500)
					{
						player.sendMessage("You have enough stones");
					}
					else
					{
						st.takeItems(LIGHT_STONE,500);
						giveRandomRecipe("s",player,st);
					}
				}
				else
				{
					htmltext = "<html><body><font color=LEVEL>Saga Manager</font><br>";
					htmltext += "You looking for some recipes? What grade?<br>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_a\">A grade Recipe</a><br1>";
					htmltext += "<a action=\"bypass -h Quest "+qn+" monastery_recipe_s\">Random S grade weapon recipe (500 Stones)</a><br1>";					
					htmltext += "</body></html>";
				}
			}
		}		
		return htmltext;
	}
	
	private void giveRandomRecipe(String gradeType, L2PcInstance player,QuestState st) 
	{
		int[] prize = null;
		if (gradeType.equalsIgnoreCase("a_1"))
		{
			prize = REWARDS_A_LIGHT;
		}
		else if (gradeType.equalsIgnoreCase("a_2"))
		{
			prize = REWARDS_A_HEAVY;
		}
		else if (gradeType.equalsIgnoreCase("a_3"))
		{
			prize = REWARDS_A_ROBE;
		}
		else if (gradeType.equalsIgnoreCase("a_4"))
		{
			prize = REWARDS_A_PARTS;
		}
		else if (gradeType.equalsIgnoreCase("s"))
		{
			prize = REWARDS_S_WEAPON;
		}
		if (prize != null)
		{
			st.giveItems(prize[Rnd.get(0,(prize.length-1))], 1);
		}
	}
	
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState("MissionMap");
		if(st == null)
		{
			st = newQuestState(player);
		}
		if (st.get("side") != null)
		{
			if (st.get("side") == "0")
			{
				if (SIDE_0_MONSTERS.equals(npc.getNpcId()))
				{
					st.giveItems(DARK_STONE, 2);
					if (SAGA_STARTED && !SAGA_DARK_WIN && !SAGA_LIGHT_WIN)
					{
						LIGHT_POINTS = LIGHT_POINTS + 1;
					}
				}
			}
			else if (st.get("side") == "1")
			{
				if (SIDE_1_MONSTERS.equals(npc.getNpcId()))
				{
					st.giveItems(LIGHT_STONE, 2);
					if (SAGA_STARTED && !SAGA_DARK_WIN && !SAGA_LIGHT_WIN)
					{
						DARK_POINTS = DARK_POINTS + 1;
					}
				}
			}
		}		
		return super.onKill(npc, player, isPet);	  
	}

	public SagaManager(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(SAGA_NPC);
		addFirstTalkId(SAGA_NPC);
		addTalkId(SAGA_NPC);
		for (int npcId : SIDE_0_MONSTERS)
		{
			addKillId(npcId);			
		}
		for (int npcId : SIDE_1_MONSTERS)
		{
			addKillId(npcId);			
		}
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckSaga(), 1, SAGA_TIME*3600*1000);        

	}
	
	private class CheckSaga implements Runnable 
	{
		public void run() 
		{
			if (SAGA_STARTED)
			{
				SAGA_STARTED = false;
				if (LIGHT_POINTS > 100 & DARK_POINTS > 100)
				{
					if (DARK_POINTS>LIGHT_POINTS)
					{
						SAGA_DARK_WIN = true;
					}
					else if (DARK_POINTS<LIGHT_POINTS)
					{
						SAGA_LIGHT_WIN = true;
					}					
				}
				Announcements.getInstance().announceToAll("=======================",1);
				Announcements.getInstance().announceToAll("=== Saga of Eternity ==",1);
				Announcements.getInstance().announceToAll("=======================",1);
				if (SAGA_DARK_WIN)
				{
					Announcements.getInstance().announceToAll("=== Dark Side Win =====",1);
				}
				else if (SAGA_LIGHT_WIN)
				{
					Announcements.getInstance().announceToAll("=== Light Side Win ====",1);
				}
				else
				{
					Announcements.getInstance().announceToAll("=== Fight Passed =====",1);
				}
				Announcements.getInstance().announceToAll("=======================",1);
				if (SAGA_LIGHT_WIN || SAGA_DARK_WIN)
				{
					Announcements.getInstance().announceToAll("= Get reward from NPC =",1);
				}
				Announcements.getInstance().announceToAll("=======================",1);				
			}
			else
			{
				SAGA_STARTED = true;
				SAGA_LIGHT_WIN = false;
				SAGA_DARK_WIN = false;
				DARK_POINTS = 0;
				LIGHT_POINTS = 0;
				Announcements.getInstance().announceToAll("=======================",1);
				Announcements.getInstance().announceToAll("=== Saga of Eternity ==",1);
				Announcements.getInstance().announceToAll("=======================",1);
			}
		}
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new SagaManager(-1,qn,"Saga Manager");
		_log.warning("Saga Manager System: Clan PvE System Loaded ...");
	}
}