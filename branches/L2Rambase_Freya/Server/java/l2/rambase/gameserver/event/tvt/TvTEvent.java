/**
 * @author L2Rambase Project
 * FIXME: TvTEvent - need rewrite
 */
package l2.rambase.gameserver.event.tvt;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2DoorInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;
import l2.rambase.gameserver.model.itemcontainer.PcInventory;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.network.serverpackets.MagicSkillUse;
import l2.rambase.gameserver.network.serverpackets.StatusUpdate;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;

import javolution.util.FastMap;


public class TvTEvent
{
	enum EventState
	{
		INACTIVE,
		INACTIVATING,
		PARTICIPATING,
		STARTING,
		STARTED,
		REWARDING
	}
	
	protected static final Logger _log = Logger.getLogger(TvTEvent.class.getName());	
	public static FastMap<Integer,TvTEventTeam> _teams = new FastMap<Integer,TvTEventTeam>();	
	private static EventState _state = EventState.INACTIVE;	
	private static L2Spawn _npcSpawn = null;
	private static L2Npc _lastNpcSpawn = null;
	private static int _TvTEventInstance = 0;
	
	private TvTEvent()
	{
	}
		
	public static void init()
	{
		_teams.put(0,new TvTEventTeam(Config.TVT_EVENT_TEAM_1_NAME, Config.TVT_EVENT_TEAM_1_COORDINATES));
		_teams.put(1,new TvTEventTeam(Config.TVT_EVENT_TEAM_2_NAME, Config.TVT_EVENT_TEAM_2_COORDINATES));
	}
	
	public static boolean startParticipation()
	{		
		L2NpcTemplate tmpl = NpcTable.getInstance().getTemplate(Config.TVT_EVENT_PARTICIPATION_NPC_ID);
		
		if (tmpl == null)
		{
			_log.warning("TvTEventEngine[TvTEvent.startParticipation()]: L2NpcTemplate is a NullPointer -> Invalid npc id in configs?");
			return false;
		}
		
		try
		{
			_npcSpawn = new L2Spawn(tmpl);
			
			_npcSpawn.setLocx(Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[0]);
			_npcSpawn.setLocy(Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[1]);
			_npcSpawn.setLocz(Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[2]);
			_npcSpawn.setAmount(1);
			_npcSpawn.setHeading(Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[3]);
			_npcSpawn.setRespawnDelay(1);
			// later no need to delete spawn from db, we don't store it (false)
			SpawnTable.getInstance().addNewSpawn(_npcSpawn, false);
			_npcSpawn.init();
			_lastNpcSpawn = _npcSpawn.getLastSpawn();
			_lastNpcSpawn.setCurrentHp(_lastNpcSpawn.getMaxHp());
			_lastNpcSpawn.setTitle("TvT Event Participation");
			_lastNpcSpawn.isAggressive();
			_lastNpcSpawn.decayMe();
			_lastNpcSpawn.spawnMe(_npcSpawn.getLastSpawn().getX(), _npcSpawn.getLastSpawn().getY(), _npcSpawn.getLastSpawn().getZ());
			_lastNpcSpawn.broadcastPacket(new MagicSkillUse(_lastNpcSpawn, _lastNpcSpawn, 1034, 1, 1, 1));
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "TvTEventEngine[TvTEvent.startParticipation()]: exception: " + e.getMessage(), e);
			return false;
		}
		setState(EventState.PARTICIPATING);
		return true;
	}

	private static int highestLevelPcInstanceOf(Map< Integer, L2PcInstance > players)
	{
		int maxLevel = Integer.MIN_VALUE, maxLevelId = -1;
		for (L2PcInstance player : players.values())
		{
			if (player.getLevel() >= maxLevel)
			{
				maxLevel = player.getLevel();
				maxLevelId = player.getObjectId();
			}
		}
		return maxLevelId;
	}

	/**
	 * Starts the TvTEvent fight<br>
	 * 1. Set state EventState.STARTING<br>
	 * 2. Close doors specified in configs<br>
	 * 3. Abort if not enought participants(return false)<br>
	 * 4. Set state EventState.STARTED<br>
	 * 5. Teleport all participants to team spot<br><br>
	 *
	 * @return boolean: true if success, otherwise false<br>
	 */
	public static boolean startFight()
	{
		// Set state to STARTING
		setState(EventState.STARTING);

		// Randomize and balance team distribution
		Map< Integer, L2PcInstance > allParticipants = new FastMap< Integer, L2PcInstance >();
		allParticipants.putAll(_teams.get(0).getParticipatedPlayers());
		allParticipants.putAll(_teams.get(1).getParticipatedPlayers());
		_teams.get(0).cleanMe();
		_teams.get(1).cleanMe();
		int balance[] = { 0, 0 }, priority = 0, highestLevelPlayerId;
		L2PcInstance highestLevelPlayer;		
		while (!allParticipants.isEmpty())
		{
			// Priority team gets one player
			highestLevelPlayerId = highestLevelPcInstanceOf(allParticipants);
			highestLevelPlayer = allParticipants.get(highestLevelPlayerId);
			allParticipants.remove(highestLevelPlayerId);
			_teams.get(priority).addPlayer(highestLevelPlayer);
			balance[priority] += highestLevelPlayer.getLevel();
			// Exiting if no more players
			if (allParticipants.isEmpty()) break;
			// The other team gets one player			
			priority = 1-priority;
			highestLevelPlayerId = highestLevelPcInstanceOf(allParticipants);
			highestLevelPlayer = allParticipants.get(highestLevelPlayerId);
			allParticipants.remove(highestLevelPlayerId);
			_teams.get(priority).addPlayer(highestLevelPlayer);
			balance[priority] += highestLevelPlayer.getLevel();
			// Recalculating priority
			priority = balance[0] > balance[1] ? 1 : 0;
		}

		// Check for enought participants
		if (_teams.get(0).getParticipatedPlayerCount() < Config.TVT_EVENT_MIN_PLAYERS_IN_TEAMS || _teams.get(1).getParticipatedPlayerCount() < Config.TVT_EVENT_MIN_PLAYERS_IN_TEAMS)
		{
			// Set state INACTIVE
			setState(EventState.INACTIVE);
			// Cleanup of teams
			_teams.get(0).cleanMe();
			_teams.get(1).cleanMe();
			// Unspawn the event NPC
			return false;
		}

		try
		{
			_TvTEventInstance = InstanceManager.getInstance().createDynamicInstance(Config.TVT_EVENT_INSTANCE_FILE);
			InstanceManager.getInstance().getInstance(_TvTEventInstance).setAllowSummon(false);
			InstanceManager.getInstance().getInstance(_TvTEventInstance).setPvPInstance(true);
			InstanceManager.getInstance().getInstance(_TvTEventInstance).setEmptyDestroyTime(Config.TVT_EVENT_START_LEAVE_TELEPORT_DELAY * 1000 + 60000L);
		}
		catch (Exception e)
		{
			_TvTEventInstance = 0;
			_log.log(Level.WARNING, "TvTEventEngine[TvTEvent.createDynamicInstance]: exception: " + e.getMessage(), e);
		}		

		// Opens all doors specified in configs for tvt
		openDoors(Config.TVT_DOORS_IDS_TO_OPEN);
		closeDoors(Config.TVT_DOORS_IDS_TO_CLOSE);
		// Set state STARTED
		setState(EventState.STARTED);
		
		// Iterate over all teams
		for (TvTEventTeam team : _teams.values())
		{
			// Iterate over all participated player instances in this team
			for (L2PcInstance playerInstance : team.getParticipatedPlayers().values())
			{
				if (playerInstance != null)
				{
					// Teleporter implements Runnable and starts itself
					new TvTEventTeleporter(playerInstance, team.getCoordinates(), false, false);
				}
			}
		}
		
		return true;
	}
	
	public static String calculateRewards()
	{
		if (_teams.get(0).getPoints() == _teams.get(1).getPoints())
		{
			// Check if one of the teams have no more players left
			if (_teams.get(0).getParticipatedPlayerCount() == 0 || _teams.get(1).getParticipatedPlayerCount() == 0)
			{
				// set state to rewarding
				setState(EventState.REWARDING);
				// return here, the fight can't be completed
				return "TvT Event: Event has ended. No team won due to inactivity!";
			}
			
			// Both teams have equals points
			sysMsgToAllParticipants("TvT Event: Event has ended, both teams have tied.");
			if (Config.TVT_REWARD_TEAM_TIE)
			{
				rewardTeamOne();
				rewardTeamTwo();
				return "TvT Event: Event has ended with both teams tying.";
			}
			else
			{
				return "TvT Event: Event has ended with both teams tying.";
			}
		}
		
		// Set state REWARDING so nobody can point anymore
		setState(EventState.REWARDING);
		
		// Get team which has more points
		TvTEventTeam team = _teams.get(_teams.get(0).getPoints() > _teams.get(1).getPoints() ? 0 : 1);
		
		if (team == _teams.get(0))
		{
			rewardTeamOne();
		}
		else
		{
			rewardTeamTwo();
		}
		
		return "TvT Event: Event finish. Team " + team.getName() + " won with " + team.getPoints() + " kills.";
	}
	
	private static void rewardTeamOne()
	{
		TvTEventTeam team = _teams.get(0);
		// Iterate over all participated player instances of the winning team
		for (L2PcInstance playerInstance : team.getParticipatedPlayers().values())
		{
			// Check for nullpointer
			if (playerInstance == null)
			{
				continue;
			}
			SystemMessage systemMessage = null;
				                       
			// Iterate over all tvt event rewards
			for (int[] reward : Config.TVT_EVENT_REWARDS)
			{
				PcInventory inv = playerInstance.getInventory();
				
				// Check for stackable item, non stackabe items need to be added one by one
				if (ItemTable.getInstance().createDummyItem(reward[0]).isStackable())
				{
					inv.addItem("TvT Event", reward[0], reward[1], playerInstance, playerInstance);
					
					if (reward[1] > 1)
					{
						systemMessage = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
						systemMessage.addItemName(reward[0]);
						systemMessage.addItemNumber(reward[1]);
					}
					else
					{
						systemMessage = new SystemMessage(SystemMessageId.EARNED_ITEM);
						systemMessage.addItemName(reward[0]);
					}
					
					playerInstance.sendPacket(systemMessage);
				}
				else
				{
					for (int i = 0; i < reward[1]; ++i)
					{
						inv.addItem("TvT Event", reward[0], 1, playerInstance, playerInstance);
						systemMessage = new SystemMessage(SystemMessageId.EARNED_ITEM);
						systemMessage.addItemName(reward[0]);
						playerInstance.sendPacket(systemMessage);
					}
				}
			}
			
			StatusUpdate statusUpdate = new StatusUpdate(playerInstance.getObjectId());			
			statusUpdate.addAttribute(StatusUpdate.CUR_LOAD, playerInstance.getCurrentLoad());			
			playerInstance.sendPacket(statusUpdate);			
		}
	}
	
	private static void rewardTeamTwo()
	{
		TvTEventTeam team = _teams.get(1);
		
		// Iterate over all participated player instances of the winning team
		for (L2PcInstance playerInstance : team.getParticipatedPlayers().values())
		{
			// Check for nullpointer
			if (playerInstance == null)
			{
				continue;
			}		
			SystemMessage systemMessage = null;
				                       
			// Iterate over all tvt event rewards
			for (int[] reward : Config.TVT_EVENT_REWARDS)
			{
				PcInventory inv = playerInstance.getInventory();
				
				// Check for stackable item, non stackabe items need to be added one by one
				if (ItemTable.getInstance().createDummyItem(reward[0]).isStackable())
				{
					inv.addItem("TvT Event", reward[0], reward[1], playerInstance, playerInstance);
					
					if (reward[1] > 1)
					{
						systemMessage = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
						systemMessage.addItemName(reward[0]);
						systemMessage.addItemNumber(reward[1]);
					}
					else
					{
						systemMessage = new SystemMessage(SystemMessageId.EARNED_ITEM);
						systemMessage.addItemName(reward[0]);
					}
					
					playerInstance.sendPacket(systemMessage);
				}
				else
				{
					for (int i = 0; i < reward[1]; ++i)
					{
						inv.addItem("TvT Event", reward[0], 1, playerInstance, playerInstance);
						systemMessage = new SystemMessage(SystemMessageId.EARNED_ITEM);
						systemMessage.addItemName(reward[0]);
						playerInstance.sendPacket(systemMessage);
					}
				}
			}
			
			StatusUpdate statusUpdate = new StatusUpdate(playerInstance.getObjectId());
			statusUpdate.addAttribute(StatusUpdate.CUR_LOAD, playerInstance.getCurrentLoad());			
			playerInstance.sendPacket(statusUpdate);			
		}
	}
	
	public static void stopFight()
	{
		// Set state INACTIVATING
		setState(EventState.INACTIVATING);

		openDoors(Config.TVT_DOORS_IDS_TO_CLOSE);
		closeDoors(Config.TVT_DOORS_IDS_TO_OPEN);
		
		// Iterate over all teams
		for (TvTEventTeam team : _teams.values())
		{
			for (L2PcInstance playerInstance : team.getParticipatedPlayers().values())
			{
				// Check for nullpointer
				if (playerInstance != null)
				{
					new TvTEventTeleporter(playerInstance, Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES, false, false);
				}
			}
		}
		
		// Cleanup of teams
		_teams.get(0).cleanMe();
		_teams.get(1).cleanMe();
		// Set state INACTIVE
		setState(EventState.INACTIVE);
	}
	
	public static synchronized boolean addParticipant(L2PcInstance playerInstance)
	{
		// Check for nullpoitner
		if (playerInstance == null)
		{
			return false;
		}
		
		byte teamId = 0;
		
		// Check to which team the player should be added
		if (_teams.get(0).getParticipatedPlayerCount() == _teams.get(1).getParticipatedPlayerCount())
		{
			teamId = (byte) (Rnd.get(2));
		}
		else
		{
			teamId = (byte) (_teams.get(0).getParticipatedPlayerCount() > _teams.get(1).getParticipatedPlayerCount() ? 1 : 0);
		}
		
		return _teams.get(teamId).addPlayer(playerInstance);
	}
	
	public static boolean removeParticipant(int playerObjectId)
	{
		// Get the teamId of the player
		byte teamId = getParticipantTeamId(playerObjectId);
		
		// Check if the player is participant
		if (teamId != -1)
		{
			// Remove the player from team
			_teams.get(teamId).removePlayer(playerObjectId);
			return true;
		}
		
		return false;
	}

	public static void sysMsgToAllParticipants(String message)
	{
		for (L2PcInstance playerInstance : _teams.get(0).getParticipatedPlayers().values())
		{
			if (playerInstance != null)
			{
				playerInstance.sendMessage(message);
			}
		}
		
		for (L2PcInstance playerInstance : _teams.get(1).getParticipatedPlayers().values())
		{
			if (playerInstance != null)
			{
				playerInstance.sendMessage(message);
			}
		}
	}
	
	private static void closeDoors(List<Integer> doors)
	{
		for (int doorId : doors)
		{
			L2DoorInstance doorInstance = DoorTable.getInstance().getDoor(doorId);
			
			if (doorInstance != null)
			{
				doorInstance.closeMe();
			}
		}
	}
	
	private static void openDoors(List<Integer> doors)
	{
		for (int doorId : doors)
		{
			L2DoorInstance doorInstance = DoorTable.getInstance().getDoor(doorId);
			
			if (doorInstance != null)
			{
				doorInstance.openMe();
			}
		}
	}
	
	public static void onLogin(L2PcInstance playerInstance)
	{
		if (playerInstance == null || (!isStarting() && !isStarted()))
		{
			return;
		}
		
		byte teamId = getParticipantTeamId(playerInstance.getObjectId());
		
		if (teamId == -1)
		{
			return;
		}
		
		_teams.get(teamId).addPlayer(playerInstance);
		new TvTEventTeleporter(playerInstance, _teams.get(teamId).getCoordinates(), true, false);
	}
	
	public static void onLogout(L2PcInstance playerInstance)
	{
		if (playerInstance != null && (isStarting() || isStarted() || isParticipating()))
		{
			if (removeParticipant(playerInstance.getObjectId()))
				playerInstance.setXYZInvisible(Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[0] + Rnd.get(101)-50,
						Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[1] + Rnd.get(101)-50,
						Config.TVT_EVENT_PARTICIPATION_NPC_COORDINATES[2]);
		}
	}
	
	public static boolean onAction(L2PcInstance playerInstance, int targetedPlayerObjectId)
	{
		if (playerInstance == null || !isStarted())
		{
			return true;
		}
		
		if (playerInstance.isGM())
		{
			return true;
		}
		
		byte playerTeamId = getParticipantTeamId(playerInstance.getObjectId());
		byte targetedPlayerTeamId = getParticipantTeamId(targetedPlayerObjectId);
		
		if ((playerTeamId != -1 && targetedPlayerTeamId == -1) || (playerTeamId == -1 && targetedPlayerTeamId != -1))
		{
			return false;
		}
		
		if (playerTeamId != -1 && targetedPlayerTeamId != -1 && playerTeamId == targetedPlayerTeamId && playerInstance.getObjectId() != targetedPlayerObjectId && !Config.TVT_EVENT_TARGET_TEAM_MEMBERS_ALLOWED)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean onScrollUse(int playerObjectId)
	{
		if (!isStarted())
			return true;
		
		if (isPlayerParticipant(playerObjectId) && !Config.TVT_EVENT_SCROLL_ALLOWED)
			return false;
		
		return true;
	}
	
	public static boolean onPotionUse(int playerObjectId)
	{
		if (!isStarted())
			return true;
		
		if (isPlayerParticipant(playerObjectId) && !Config.TVT_EVENT_POTIONS_ALLOWED)
			return false;
		
		return true;
	}
	
	public static boolean onEscapeUse(int playerObjectId)
	{
		if (!isStarted())
		{
			return true;
		}
		
		if (isPlayerParticipant(playerObjectId))
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean onItemSummon(int playerObjectId)
	{
		if (!isStarted())
		{
			return true;
		}
		
		if (isPlayerParticipant(playerObjectId) && !Config.TVT_EVENT_SUMMON_BY_ITEM_ALLOWED)
		{
			return false;
		}
		
		return true;
	}
	
	public static void onKill(L2Character killerCharacter, L2PcInstance killedPlayerInstance)
	{
		if (killedPlayerInstance == null || !isStarted())
		{
			return;
		}
		
		byte killedTeamId = getParticipantTeamId(killedPlayerInstance.getObjectId());
		
		if (killedTeamId == -1)
		{
			return;
		}
		
		new TvTEventTeleporter(killedPlayerInstance, _teams.get(killedTeamId).getCoordinates(), false, false);
		
		if (killerCharacter == null)
		{
			return;
		}
		
		L2PcInstance killerPlayerInstance = null;
		
		if (killerCharacter instanceof L2PetInstance || killerCharacter instanceof L2SummonInstance)
		{
			killerPlayerInstance = ((L2Summon) killerCharacter).getOwner();
			
			if (killerPlayerInstance == null)
			{
				return;
			}
		}
		else if (killerCharacter instanceof L2PcInstance)
		{
			killerPlayerInstance = (L2PcInstance) killerCharacter;
		}
		else
		{
			return;
		}
		
		byte killerTeamId = getParticipantTeamId(killerPlayerInstance.getObjectId());
		
		if (killerTeamId != -1 && killedTeamId != -1 && killerTeamId != killedTeamId)
		{
			TvTEventTeam killerTeam = _teams.get(killerTeamId);
			
			killerTeam.increasePoints();
			
			CreatureSay cs = new CreatureSay(killerPlayerInstance.getObjectId(), Say2.TELL, killerPlayerInstance.getName(), "I have killed " + killedPlayerInstance.getName() + "!");
			
			for (L2PcInstance playerInstance : _teams.get(killerTeamId).getParticipatedPlayers().values())
			{
				if (playerInstance != null)
				{
					playerInstance.sendPacket(cs);
				}
			}
		}
	}

	public static void onTeleported(L2PcInstance playerInstance)
	{
		if (!isStarted() || playerInstance == null || !isPlayerParticipant(playerInstance.getObjectId()))
			return;

		if (playerInstance.isMageClass())
		{
			if (Config.TVT_EVENT_MAGE_BUFFS != null && !Config.TVT_EVENT_MAGE_BUFFS.isEmpty())
			{
				for (int i : Config.TVT_EVENT_MAGE_BUFFS.keys())
				{
					L2Skill skill = SkillTable.getInstance().getInfo(i, Config.TVT_EVENT_MAGE_BUFFS.get(i));
					if (skill != null)
						skill.getEffects(playerInstance, playerInstance);
				}
			}
		}
		else
		{
			if (Config.TVT_EVENT_FIGHTER_BUFFS != null && !Config.TVT_EVENT_FIGHTER_BUFFS.isEmpty())
			{
				for (int i : Config.TVT_EVENT_FIGHTER_BUFFS.keys())
				{
					L2Skill skill = SkillTable.getInstance().getInfo(i, Config.TVT_EVENT_FIGHTER_BUFFS.get(i));
					if (skill != null)
						skill.getEffects(playerInstance, playerInstance);
				}
			}
		}
	}

	public static final boolean checkForSkill(L2PcInstance source, L2PcInstance target, L2Skill skill)
	{
    	if (!isStarted())
    		return true;
    	// TvT is started
    	final int sourcePlayerId = source.getObjectId();
    	final int targetPlayerId = target.getObjectId();
    	final boolean isSourceParticipant = isPlayerParticipant(sourcePlayerId);
    	final boolean isTargetParticipant = isPlayerParticipant(targetPlayerId);

    	// both players not participating
    	if (!isSourceParticipant && !isTargetParticipant)
    		return true;
    	// one player not participating
    	if (!(isSourceParticipant && isTargetParticipant))
    		return false;
    	// players in the different teams ?
    	if (getParticipantTeamId(sourcePlayerId) != getParticipantTeamId(targetPlayerId))
    	{
    		if (!skill.isOffensive())
    			return false;
    	}
    	return true;
    }

	private static void setState(EventState state)
	{
		synchronized (_state)
		{
			_state = state;
		}
	}
	
	public static boolean isInactive()
	{
		boolean isInactive;
		
		synchronized (_state)
		{
			isInactive = _state == EventState.INACTIVE;
		}
		
		return isInactive;
	}
	
	public static boolean isInactivating()
	{
		boolean isInactivating;
		
		synchronized (_state)
		{
			isInactivating = _state == EventState.INACTIVATING;
		}
		
		return isInactivating;
	}
	
	public static boolean isParticipating()
	{
		boolean isParticipating;
		
		synchronized (_state)
		{
			isParticipating = _state == EventState.PARTICIPATING;
		}
		
		return isParticipating;
	}
	
	public static boolean isStarting()
	{
		boolean isStarting;
		
		synchronized (_state)
		{
			isStarting = _state == EventState.STARTING;
		}
		
		return isStarting;
	}

	public static boolean isStarted()
	{
		boolean isStarted;
		
		synchronized (_state)
		{
			isStarted = _state == EventState.STARTED;
		}
		
		return isStarted;
	}

	public static boolean isRewarding()
	{
		boolean isRewarding;
		
		synchronized (_state)
		{
			isRewarding = _state == EventState.REWARDING;
		}
		
		return isRewarding;
	}

	public static byte getParticipantTeamId(int playerObjectId)
	{
		return (byte) (_teams.get(0).containsPlayer(playerObjectId) ? 0 : (_teams.get(1).containsPlayer(playerObjectId) ? 1 : -1));
	}

	public static TvTEventTeam getParticipantTeam(int playerObjectId)
	{
		return (_teams.get(0).containsPlayer(playerObjectId) ? _teams.get(0) : (_teams.get(1).containsPlayer(playerObjectId) ? _teams.get(1) : null));
	}

	public static TvTEventTeam getParticipantEnemyTeam(int playerObjectId)
	{
		return (_teams.get(0).containsPlayer(playerObjectId) ? _teams.get(1) : (_teams.get(1).containsPlayer(playerObjectId) ? _teams.get(0) : null));
	}

	public static int[] getParticipantTeamCoordinates(int playerObjectId)
	{
		return _teams.get(0).containsPlayer(playerObjectId) ? _teams.get(0).getCoordinates() : (_teams.get(1).containsPlayer(playerObjectId) ? _teams.get(1).getCoordinates() : null);
	}

	public static boolean isPlayerParticipant(int playerObjectId)
	{
		if (!isParticipating() && !isStarting() && !isStarted())
		{
			return false;
		}
		
		return _teams.get(0).containsPlayer(playerObjectId) || _teams.get(1).containsPlayer(playerObjectId);
	}
	
	public static int getParticipatedPlayersCount()
	{
		if (!isParticipating() && !isStarting() && !isStarted())
		{
			return 0;
		}
		
		return _teams.get(0).getParticipatedPlayerCount() + _teams.get(1).getParticipatedPlayerCount();
	}
	
	public static String[] getTeamNames()
	{
		return new String[]
		{
			_teams.get(0).getName(), _teams.get(1).getName()
		};
	}
	
	public static int[] getTeamsPlayerCounts()
	{
		return new int[]
		{
			_teams.get(0).getParticipatedPlayerCount(), _teams.get(1).getParticipatedPlayerCount()
		};
	}
	
	public static int[] getTeamsPoints()
	{
		return new int[]
		{
			_teams.get(0).getPoints(), _teams.get(1).getPoints()
		};
	}
	
	public static int getTvTEventInstance()
	{
		return _TvTEventInstance;
	}
}