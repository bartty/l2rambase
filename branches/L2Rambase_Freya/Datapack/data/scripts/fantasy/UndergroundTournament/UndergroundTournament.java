/**
 * Information about script:
 * --> UTeam - team on Arena (script ready)
 * --> UArena - arena for fight (script ready)
 * --> FastMap registrationMap - registration lists of all Arenas
 * --> startPeriod - function to start current period (values: REGISTER, FIGHT)
 * --> stopPeriod - function to stop current period (values: FIGHT) 
 * 
 * XXX: need create a specific option to resurect dead player and fight for time like (5 minutes of fight with announce and always resurect)
 * XXX: need create a period functions
 * XXX: need create onFirstTalk, onAdvEvent
 * XXX: need create runnable scripts for time
 *   
 * @author L2Rambase Project
 */
package fantasy.UndergroundTournament;

import javolution.util.FastList;
import javolution.util.FastMap;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

import fantasy.UndergroundTournament.UndergroundTournamentConfig;
import fantasy.UndergroundTournament.UndergroundTournamentConfig.UArena;
import fantasy.UndergroundTournament.UndergroundTournamentConfig.UTeam;


public class UndergroundTournament extends Quest
{
	private static final String qn = "UndergroundTournament";

	//private boolean debug = true;
	/**
	 * Configuration List
	 * @param Arenas, Teams, Registration List
	 */
	protected UTeam ArenaOneTeamOne = new UTeam(11);
	protected UTeam ArenaOneTeamTwo = new UTeam(12);
	protected UTeam ArenaOneTeamThree = new UTeam(13);
	protected UTeam ArenaOneTeamFour = new UTeam(14);
	protected UTeam ArenaTwoTeamOne = new UTeam(21);
	protected UTeam ArenaTwoTeamTwo = new UTeam(22);
	protected UTeam ArenaTwoTeamThree = new UTeam(23);
	protected UTeam ArenaTwoTeamFour = new UTeam(24);
	protected UTeam ArenaThreeTeamOne = new UTeam(31);
	protected UTeam ArenaThreeTeamTwo = new UTeam(32);
	protected UTeam ArenaThreeTeamThree = new UTeam(33);
	protected UTeam ArenaThreeTeamFour = new UTeam(34);
	protected UTeam ArenaFourTeamOne = new UTeam(41);
	protected UTeam ArenaFourTeamTwo = new UTeam(42);
	protected UTeam ArenaFourTeamThree = new UTeam(43);
	protected UTeam ArenaFourTeamFour = new UTeam(44);
	protected UTeam ArenaFiveTeamOne = new UTeam(51);
	protected UTeam ArenaFiveTeamTwo = new UTeam(52);
	protected UTeam ArenaFiveTeamThree = new UTeam(53);
	protected UTeam ArenaFiveTeamFour = new UTeam(54);	
	protected UArena ArenaOne = new UArena(1);
	protected UArena ArenaTwo = new UArena(2);
	protected UArena ArenaThree = new UArena(3);
	protected UArena ArenaFour = new UArena(4);
	protected UArena ArenaFive = new UArena(5);
	protected FastMap<Integer,FastList<L2PcInstance>> registrationMap = new FastMap<Integer,FastList<L2PcInstance>>();
	/**
	 * Npc 
	 * @param npcId
	 */
	private static final int Kuram = 32377;
	/**
	 * On First Talk Code
	 */
	public final String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);	
		}
		String htmltext = "";
		htmltext = "<html><title>Tournament Manager</title><body>";
		htmltext += "This arena is open to all characters. Only the winner will survive, so only challenge if you feel confident!<br>";
		if (UndergroundTournamentConfig.PVP_TOURNAMENT)
		{
			htmltext += "<font color=LEVEL>[ PVP TOURNAMENT ]</font><br>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament reg_pvp\">Register in PvP Fights.</a><br>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament list_pvp\">View participating list.</a><br>";
		}
		if (UndergroundTournamentConfig.TVT_TOURNAMENT)
		{
			htmltext += "<font color=LEVEL>[ TVT TOURNAMENT ]</font><br>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament reg_tvt\">Register in TvT Fights.</a><br>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament list_tvt\">View participating list.</a><br>";
		}
		if (UndergroundTournamentConfig.CLAN_TOURNAMENT)
		{
			htmltext += "<font color=LEVEL>[ CLAN TOURNAMENT ]</font>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament reg_clan\">Register in Clan Fights.</a><br>";
			htmltext += "<a action=\"bypass -h Quest UndergroundTournament list_clan\">View participating list.</a><br>";
		}
		htmltext += "<font color=LEVEL> [ OTHERS ]</font><br>";
		htmltext += "<a action=\"bypass -h Quest UndergroundTournament rules\">View the game rules.</a><br>";
		htmltext += "<a action=\"bypass -h npc_"+String.valueOf(npc.getObjectId())+"_goto 12059\">Move to Fantasy Isle.</a>";
		htmltext += "</body></html>";		
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
		if (event.endsWith("pvp"))
		{
			if (event.startsWith("reg"))
			{
				htmltext = mode(1,player,st,"reg");
			}
			else if (event.startsWith("list"))
			{
				htmltext = mode(1,player,st,"list");
			}
		}
		else if (event.endsWith("tvt"))
		{
			if (event.startsWith("reg"))
			{
				htmltext = mode(2,player,st,"reg");
			}
			else if (event.startsWith("list"))
			{
				htmltext = mode(2,player,st,"list");
			}
		}
		else if (event.endsWith("clan"))
		{
			if (event.startsWith("reg"))
			{
				htmltext = mode(3,player,st,"reg");
			}
			else if (event.startsWith("list"))
			{
				htmltext = mode(3,player,st,"list");
			}
		}
		else if (event.equalsIgnoreCase("rules"))
		{
			htmltext = UndergroundTournamentConfig.Rules;
		}		
		return htmltext;
	}
	
	private String mode(int i, L2PcInstance player, QuestState st, String string) 
	{
		String vText = "";
		switch (i)
		{
			case 1:
				vText = modePvP(player,st,string);
				break;
			case 2:
				vText = modeTvT(player,st,string);
				break;
			case 3:
				vText = modeClan(player,st,string);
				break;
			default:
		}
		return vText;
	}

	private String modePvP(L2PcInstance player, QuestState st, String string) 
	{
		String returnText = "";
		if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(1).contains(player))
				{
					registrationMap.get(1).remove(player);
				}
				registrationMap.get(1).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredPvP;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterPvP;
			}
		}
		else if (!ArenaTwo.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(1).contains(player))
				{
					registrationMap.get(1).remove(player);
				}
				registrationMap.get(1).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredPvP;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterPvP;
			}
		}
		else if (!ArenaThree.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(1).contains(player))
				{
					registrationMap.get(1).remove(player);
				}
				registrationMap.get(1).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredPvP;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterPvP;
			}
		}
		else if (!ArenaFour.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(1).contains(player))
				{
					registrationMap.get(1).remove(player);
				}
				registrationMap.get(1).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredPvP;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterPvP;
			}
		}
		else if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(1).contains(player))
				{
					registrationMap.get(1).remove(player);
				}
				registrationMap.get(1).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredPvP;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterPvP;
			}
		}
		else
		{
			player.atEvent = false;
			returnText = UndergroundTournamentConfig.CannotRegisterPvP;
		}
		return returnText;
	}

	private String modeTvT(L2PcInstance player, QuestState st, String string) 
	{
		String returnText = "";
		if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(2).contains(player))
				{
					registrationMap.get(2).remove(player);
				}
				registrationMap.get(2).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredTvT;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterTvT;
			}
		}
		else if (!ArenaTwo.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(2).contains(player))
				{
					registrationMap.get(2).remove(player);
				}
				registrationMap.get(2).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredTvT;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterTvT;
			}
		}
		else if (!ArenaThree.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(2).contains(player))
				{
					registrationMap.get(2).remove(player);
				}
				registrationMap.get(2).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredTvT;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterTvT;
			}
		}
		else if (!ArenaFour.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(2).contains(player))
				{
					registrationMap.get(2).remove(player);
				}
				registrationMap.get(2).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredTvT;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterTvT;
			}
		}
		else if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(2).contains(player))
				{
					registrationMap.get(2).remove(player);
				}
				registrationMap.get(2).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredTvT;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterTvT;
			}
		}
		else
		{
			player.atEvent = false;
			returnText = UndergroundTournamentConfig.CannotRegisterTvT;
		}
		return returnText;
	}
	
	private String modeClan(L2PcInstance player, QuestState st, String string) 
	{
		String returnText = "";
		if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(3).contains(player))
				{
					registrationMap.get(3).remove(player);
				}
				registrationMap.get(3).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredClan;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterClan;
			}
		}
		else if (!ArenaTwo.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(3).contains(player))
				{
					registrationMap.get(3).remove(player);
				}
				registrationMap.get(3).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredClan;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterClan;
			}
		}
		else if (!ArenaThree.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(3).contains(player))
				{
					registrationMap.get(3).remove(player);
				}
				registrationMap.get(3).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredClan;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterClan;
			}
		}
		else if (!ArenaFour.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(3).contains(player))
				{
					registrationMap.get(3).remove(player);
				}
				registrationMap.get(3).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredClan;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterClan;
			}
		}
		else if (!ArenaOne.getStatus())
		{
			if (checkCanRegister(player))
			{
				if (registrationMap.get(3).contains(player))
				{
					registrationMap.get(3).remove(player);
				}
				registrationMap.get(3).add(player);
				player.atEvent = true;
				returnText = UndergroundTournamentConfig.RegisteredClan;
			}
			else
			{
				returnText = UndergroundTournamentConfig.CannotRegisterClan;
			}
		}
		else
		{
			player.atEvent = false;
			returnText = UndergroundTournamentConfig.CannotRegisterClan;
		}
		return returnText;
	}
	
	private boolean checkCanRegister(L2PcInstance player) 
	{
		//XXX: Create register check
		return false;
	}

	public UndergroundTournament(int questId, String name, String descr)
	{
		super(questId, name, descr);		
		addStartNpc(Kuram);
		addFirstTalkId(Kuram);
		addTalkId(Kuram);
	}
	
	public static void main(String[] args)
	{
		// now call the constructor (starts up the)
		new UndergroundTournament(-1,qn,"fantasy");
		_log.warning("Fantasy System: Underground Tournament loaded ...");
	}
}