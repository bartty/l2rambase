package custom.SubclassQuest;

import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.base.Race;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;


public class SubclassQuest extends Quest
{
	private static final String qn = "SubclassQuest";
	//NPCs
	private static final int SUBCLASS_MASTER = 31212;
	//ITEMs
	private static final int ADENA  = 57;
	private static final int ANCIENT_ADENA = 5575;
	//SUBCLASs COST
	private static final long ADENA_COST = 40000000;
	private static final long ANCIENT_ADENA_COST = 10000000;
	//Spawns
	private static final int SpawnLocs[][] = {
		{ 82400,147529,-3477,15689 }, // Giran
		{ 147826,25593,-2015,23775 }, // Aden
		{ 147402,-56644,-2783,4836 }, // Goddard
		{ 44187,-48506,-792,31045 } // Rune
	};
	private static boolean SUBCLASS_FAST = true;
	
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);		
		String htmltext = "";
		if (st == null)
		{
			st = newQuestState(player);
		}
		htmltext = "<html><body>Subclass Master: <br>";
		htmltext += "<br>";
		htmltext += "2 years ago i must complete subclass quest and i must doing stupid things. I found mysterious man that told me how to fast finish subclass quest.<br>";
		htmltext += "I know what to do but i cannot give you that information, but i can do this quest for you. Of course this is not free help.<br>";
		htmltext += "<a action=\"bypass -h Quest SubclassQuest subclass\">Yes of course ...</a><br1>";
		htmltext += "</body></html>";
		return htmltext;
	}
	
	public final String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		String htmltext = "";
		if (event.equalsIgnoreCase("subclass"))
		{
			//first time when a player join the quest			
			if (st.getState() == State.CREATED)
			{
				if (player.getLevel() >= 75)
				{
					htmltext = "<html><body>Subclass Master: <br>";
					htmltext += "<br>";
					htmltext += "I know future and how to finish fast <font color=LEVEL> Subclass </font>.<br>"; 
					htmltext += "Ok so you must pay me <font color=LEVEL>" + String.valueOf(ADENA_COST)+"</font> Adena and <font color=LEVEL>"+String.valueOf(ANCIENT_ADENA_COST)+"</font> Ancient Adena. <br><br>";
					htmltext += "<a action=\"bypass -h Quest SubclassQuest getsubclass\">Uhh there is that money ...</a><br1>";
					htmltext += "</body></html>";
					return htmltext;
				}
				else
				{
	    		  st.exitQuest(true);
	    		  htmltext = "<html><body>Subclass Master: <br>";
	    		  htmltext += "<br>You need 75 level to fast finish subclass quest.";
	    		  htmltext += "<br><br>";
	    		  htmltext += "</body></html>";
	    		  return htmltext;
				}
			}
			//if quest is already State.COMPLETED
			if (st.getState() == State.COMPLETED)
			{
				st.exitQuest(false);
				return "<html><body>This quest has already been completed.</body></html>";				
			}
		}
		if (event.equalsIgnoreCase("getsubclass"))
		{			
			if (player.getRace() == Race.Human || player.getRace() == Race.Elf || player.getRace() == Race.DarkElf || player.getRace() == Race.Orc || player.getRace() == Race.Dwarf)
			{
				QuestState subclass1 = player.getQuestState("234_FatesWhisper");				
				QuestState subclass2 = player.getQuestState("235_MimirsElixir");					
				if (st.getQuestItemsCount(ADENA) >= ADENA_COST && st.getQuestItemsCount(ANCIENT_ADENA) >= ANCIENT_ADENA_COST)
				{
					if (subclass1 == null)
				    {
						subclass1 = QuestManager.getInstance().getQuest("234_FatesWhisper").newQuestState(player);
						subclass1.isCreated();
				    }
					if (subclass2 == null)
				    {
						subclass2 = QuestManager.getInstance().getQuest("235_MimirsElixir").newQuestState(player);
						subclass2.isCreated();
				    }
					st.takeItems(ADENA, ADENA_COST);
					st.takeItems(ANCIENT_ADENA,ANCIENT_ADENA_COST);					
					subclass1.setState(State.COMPLETED);
					subclass1.exitQuest(false);
					subclass2.setState(State.COMPLETED);
					subclass2.exitQuest(false);
					st.setState(State.COMPLETED);
					st.exitQuest(false);
					htmltext = "<html><body>Subclass Master: <br>";
					htmltext += "<br>You finished subclass quest.";
					htmltext += "<br><br>";
					htmltext += "</body></html>";
				}
				else
				{
					htmltext = "<html><body>Subclass Master: <br>";
					htmltext += "<br>You don't have "+String.valueOf(ADENA_COST)+"and "+String.valueOf(ANCIENT_ADENA_COST)+" Ancient Adena.";
					htmltext += "<br><br>";
					htmltext += "<a action=\"bypass -h Quest SubclassQuest back\">I will back when i have ...</a><br1>";
					htmltext += "</body></html>";
				}
			}			
			if (player.getRace() == Race.Kamael)
			{
				QuestState subclass1 = player.getQuestState("234_FatesWhisper");
				QuestState subclass3 = player.getQuestState("236_SeedsOfChaos");	
				if (st.getQuestItemsCount(ADENA) >= ADENA_COST && st.getQuestItemsCount(ANCIENT_ADENA) >= ANCIENT_ADENA_COST)
				{
					if (subclass1 == null)
				    {
						subclass1 = QuestManager.getInstance().getQuest("234_FatesWhisper").newQuestState(player);
						subclass1.isCreated();
				    }
					if (subclass3 == null)
				    {
						subclass3 = QuestManager.getInstance().getQuest("236_SeedsOfChaos").newQuestState(player);
						subclass3.isCreated();
				    }
					st.takeItems(ADENA, ADENA_COST);
					st.takeItems(ANCIENT_ADENA,ANCIENT_ADENA_COST);
					subclass1.setState(State.COMPLETED);
					subclass1.exitQuest(false);
					subclass3.setState(State.COMPLETED);
					subclass3.exitQuest(false);
					st.setState(State.COMPLETED);
					htmltext = "<html><body>Subclass Master: <br>";
					htmltext += "<br>You finished subclass quest.";
					htmltext += "<br><br>";
					htmltext += "</body></html>";
				}
				else
				{
					htmltext = "<html><body>Subclass Master: <br>";
					htmltext += "<br>You don't have "+String.valueOf(ADENA_COST)+" Adena and "+String.valueOf(ANCIENT_ADENA_COST)+" Ancient Adena.";
					htmltext += "<br><br>";
					htmltext += "<a action=\"bypass -h Quest SubclassQuest back\">I will back when i have ...</a><br1>";
					htmltext += "</body></html>";
				}
			}
		}
		if (event.equalsIgnoreCase("back"))
		{
			htmltext = "<html><body>Subclass Master: <br>";
			htmltext += "<br>";
			htmltext += "I know future and how to finish fast <font color=LEVEL> Subclass </font>.<br>"; 
			htmltext += "Ok so you must pay me <font color=LEVEL>" + String.valueOf(ADENA_COST)+"</font> Adena and <font color=LEVEL>"+String.valueOf(ANCIENT_ADENA_COST)+"</font> Ancient Adena. <br><br>";
			htmltext += "<a action=\"bypass -h Quest SubclassQuest getsubclass\">Uhh there is that money ...</a><br1>";
			htmltext += "</body></html>";			
		}		
       	return htmltext;
	}
	
	public SubclassQuest(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addStartNpc(SUBCLASS_MASTER);
		addFirstTalkId(SUBCLASS_MASTER);
		addTalkId(SUBCLASS_MASTER);		
		if (SUBCLASS_FAST)
		{
			for (int i = 0; i < 4; i += 1)
			{
				addSpawn(SUBCLASS_MASTER, SpawnLocs[i][0], SpawnLocs[i][1], SpawnLocs[i][2], SpawnLocs[i][3], false, 0, true);
			}
		}
	}
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new SubclassQuest(-1,qn,"custom");
		_log.warning("Subclass System: Custom SubclassQuest Lodaded ...");
	}
}