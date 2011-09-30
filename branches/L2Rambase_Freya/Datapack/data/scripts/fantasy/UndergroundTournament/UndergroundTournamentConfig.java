package fantasy.UndergroundTournament;

import javolution.util.FastList;

import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;

public class UndergroundTournamentConfig extends Quest
{
	private static final String qn = "UndergroundTournamentConfig";
	
	/**
	 * Status of Tournaments
	 * @param Type of Tournaments
	 */
	public static final boolean PVP_TOURNAMENT = false;
	public static final boolean TVT_TOURNAMENT = false;
	public static final boolean CLAN_TOURNAMENT = false;
	/**
	 * String list
	 */
	public static final String Rules = "<html><title>Tournament Manager</title><body>"
		+ "<font color=LEVEL>[ PvP Rules ]</font><br>"
		+ "When you enter to arena you fight with players.<br>"		
		+ "<font color=LEVEL>[ TvT Rules ]</font><br>"
		+ "When you enter with your party to arena you fight with other team.<br>"
		+ "<font color=LEVEL>[ CvC Rules ]</font><br>"
		+ "When you enter with your clan members to arena you fight with other clan groups.<br><br>"
		+ "For killing players you get points.<br>";
	public static final String RegisteredPvP = "";
	public static final String CannotRegisterPvP = "";
	public static final String RegisteredTvT = "";
	public static final String CannotRegisterTvT = "";
	public static final String RegisteredClan = "";
	public static final String CannotRegisterClan = "";
	/**
	 * Arena Door List
	 * @param Integer[]
	 */
	public static final int Arena1_Doors[] = {
		17160017,17160018,
		17160019,17160020,
		17160021,17160022,
		17160023,17160024
	};
	public static final int Arena2_Doors[] = {
		17160025,17160026,		
		17160027,17160028,
		17160029,17160030,
		17160031,17160032
	};
	public static final int Arena3_Doors[] = {
		17160033,17160034,
		17160035,17160036,
		17160037,17160038,
		17160039,17160040
	};
	public static final int Arena4_Doors[] = {
		17160001,17160002,
		17160003,17160004,
		17160005,17160006,
		17160007,17160008		
	};
	public static final int Arena5_Doors[] = {
		17160009,17160010,
		17160011,17160012,
		17160013,17160014,
		17160015,17160016
	};
	/**
	 * Spawns of Arenas
	 */
	public static final int Arena1_Locs[][] = {
		{ -80676,-44285,-11496 },
		{ -82953,-44341,-11501 },
		{ -82546,-47027,-11506 },
		{ -81215,-47069,-11506 }
	};

	public static final int Arena2_Locs[][] = {
		{ -83720,-47849,-11504 },
		{ -84121,-49111,-11504 },
		{ -86816,-48786,-11501 },
		{ -86251,-46589,-11501 }
	};

	public static final int Arena3_Locs[][] = {
		{ -83677,-50536,-11504 },
		{ -82645,-51366,-11506 },
		{ -83916,-53765,-11496 },
		{ -85758,-52453,-11501 }
	};

	public static final int Arena4_Locs[][] = {
		{ -80107,-50553,-11506 },
		{ -78163,-52434,-11501 },
		{ -79938,-53847,-11496 },
		{ -81192,-51299,-11504 }
	};

	public static final int Arena5_Locs[][] = {
		{ -79638,-49190,-11504 },
		{ -79633,-49184,-11504 },
		{ -76837,-48777,-11496 },
		{ -77627,-46646,-11501 }
	};
	
	public static final int Back_Loc[][] = {
		{-82096,-48965,-10336}
	};
	
	/**
	 * Arena Class
	 */
	public static class UArena
	{
		int _arenaId;
		int teamOne;
		int teamTwo;
		int teamThree;
		int teamFour;
		boolean arenaStatus;
		
		public UArena(int arenaId)
		{
			_arenaId = arenaId;
			teamOne = 0;
			teamTwo = 0;
			teamThree = 0;
			arenaStatus = false;
		}
		
		public boolean getStatus()
		{
			return arenaStatus;
		}
		
		public void setArenaStatus(boolean value)
		{
			arenaStatus = value;
		}
		
		public void setTeam(int number, int value)
		{
			switch(number)
			{
				case 1:
					teamOne = value;
					break;
				case 2:
					teamTwo = value;
					break;
				case 3:
					teamThree = value;
					break;
				case 4:
					teamFour = value;
					break;
				default:
					break;
			}
		}
		
		public boolean isTeamExist(int number)
		{
			switch(number)
			{
				case 1:
					if (teamOne == 0)
					{
						return false;
					}						
					break;
				case 2:
					if (teamTwo == 0)
					{
						return false;
					}
					break;
				case 3:
					if (teamThree == 0)
					{
						return false;
					}
					break;
				case 4:
					if (teamFour == 0)
					{
						return false;
					}
					break;
				default:
					break;				
			}
			return true;
		}		
	}
	
	/**
	 * Team Class
	 */
	public static class UTeam
	{
		// Number of Team from leader of party
		int _teamNumber;
		// List of Team
		FastList<Integer> _teamList;
		
		public UTeam(int teamNumber)
		{
			_teamNumber = teamNumber;
			_teamList = new FastList<Integer>();
		}
		
		public void addMember(L2PcInstance player)
		{
			if (!_teamList.contains(player.getObjectId()))
			{
				_teamList.add(player.getObjectId());
			}
		}
		
		public void removeMember(L2PcInstance player)
		{
			if (_teamList.contains(player.getObjectId()))
			{
				_teamList.remove(player.getObjectId());
			}
		}
		
		public void setEventFlag(boolean value)
		{
			if (value)
			{
				for (int playerId: _teamList)
				{
					L2PcInstance player = L2World.getInstance().getPlayer(playerId);
					if (player != null)
					{
						player.isAtEvent(true);
					}
				}
			}
			else
			{
				for (int playerId: _teamList)
				{
					L2PcInstance player = L2World.getInstance().getPlayer(playerId);
					if (player != null)
					{
						player.isAtEvent(false);
					}
				}
			}
		}
		
		public void checkOnline()
		{
			for (int playerId: _teamList)
			{
				L2PcInstance player = L2World.getInstance().getPlayer(playerId);
				if (player == null)
				{
					_teamList.remove(playerId);
				}
			}
		}
		
		public int getTeamSize()
		{
			return _teamList.size();
		}
		
		public int getTeamNumber()
		{
			return _teamNumber;
		}
	}
	
	public UndergroundTournamentConfig(int questId, String name, String descr)
	{
		super(questId, name, descr);
	}
	
	public static void main(String[] args)
	{
		new UndergroundTournamentConfig(-1,qn,"fantasy");
		_log.warning("Underground Tournamen: Configuration installed ...");
	}
}