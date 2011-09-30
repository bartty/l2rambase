/**
 * @author L2Rambase Project 
 */
package l2.rambase.gameserver.event.pvp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.datatables.DoorTable;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.L2World;
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

import javolution.util.FastList;
import javolution.util.FastMap;


public class PvPEvent
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
	
	protected static final Logger _log = Logger.getLogger(PvPEvent.class.getName());
	public static FastMap<Integer,PvPEventMember> _members = new FastMap<Integer,PvPEventMember>();
	private static EventState _state = EventState.INACTIVE;
	private static L2Spawn _npcSpawn = null;
	private static L2Npc _lastNpcSpawn = null;
	private static int _PvPEventInstance = 0;

	private PvPEvent()
	{
	}
	
	public static void init()
	{
	}

	/**
	 * PvP Event start and stop procedure
	 * @return
	 */
	public static boolean startParticipation()
	{
		L2NpcTemplate tmpl = NpcTable.getInstance().getTemplate(Config.PVP_EVENT_PARTICIPATION_NPC_ID);
		
		if (tmpl == null)
		{
			_log.warning("PvPEventEngine[PvPEvent.startParticipation()]: L2NpcTemplate is a NullPointer -> Invalid npc id in configs?");
			return false;
		}
		
		try
		{
			_npcSpawn = new L2Spawn(tmpl);
			
			_npcSpawn.setLocx(Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[0]);
			_npcSpawn.setLocy(Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[1]);
			_npcSpawn.setLocz(Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[2]);
			_npcSpawn.setAmount(1);
			_npcSpawn.setHeading(Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[3]);
			_npcSpawn.setRespawnDelay(1);
			SpawnTable.getInstance().addNewSpawn(_npcSpawn, false);
			_npcSpawn.init();
			_lastNpcSpawn = _npcSpawn.getLastSpawn();
			_lastNpcSpawn.setCurrentHp(_lastNpcSpawn.getMaxHp());
			_lastNpcSpawn.setTitle("PvP Event Participation");
			_lastNpcSpawn.isAggressive();
			_lastNpcSpawn.decayMe();
			_lastNpcSpawn.spawnMe(_npcSpawn.getLastSpawn().getX(), _npcSpawn.getLastSpawn().getY(), _npcSpawn.getLastSpawn().getZ());
			_lastNpcSpawn.broadcastPacket(new MagicSkillUse(_lastNpcSpawn, _lastNpcSpawn, 1034, 1, 1, 1));
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "PvPEventEngine[PvPEvent.startParticipation()]: exception: " + e.getMessage(), e);
			return false;
		}
		
		setState(EventState.PARTICIPATING);
		return true;
	}

	public static boolean startFight()
	{
		setState(EventState.STARTING);

		if (_members.isEmpty() || _members.size() < Config.PVP_EVENT_MIN_PLAYERS)
		{
			setState(EventState.INACTIVE);
			_members.clear();
			unSpawnNpc();
			return false;
		}

		try
		{
			_PvPEventInstance = InstanceManager.getInstance().createDynamicInstance(Config.PVP_EVENT_INSTANCE_FILE);
			InstanceManager.getInstance().getInstance(_PvPEventInstance).setAllowSummon(false);
			InstanceManager.getInstance().getInstance(_PvPEventInstance).setPvPInstance(true);
			InstanceManager.getInstance().getInstance(_PvPEventInstance).setEmptyDestroyTime(Config.PVP_EVENT_START_LEAVE_TELEPORT_DELAY * 1000 + 60000L);
		}
		catch (Exception e)
		{
			_PvPEventInstance = 0;
			_log.log(Level.WARNING, "PvPEventEngine[PvPEvent.createDynamicInstance]: exception: " + e.getMessage(), e);
		}		
		
		openDoors(Config.PVP_DOORS_IDS_TO_OPEN,_PvPEventInstance);
		closeDoors(Config.PVP_DOORS_IDS_TO_CLOSE,_PvPEventInstance);
		
		setState(EventState.STARTED);
		
		for (PvPEventMember PvPMember : _members.values())
		{
			L2PcInstance playerInstance = L2World.getInstance().getPlayer(PvPMember.getPlayerId());
			if (playerInstance != null)
			{
				new PvPEventTeleporter(playerInstance, PvPMember.getCoordinates(), false, false);
			}			
		}		
		return true;
	}	

	public static void stopFight()
	{
		setState(EventState.INACTIVATING);
		unSpawnNpc();
		openDoors(Config.PVP_DOORS_IDS_TO_CLOSE,_PvPEventInstance);
		closeDoors(Config.PVP_DOORS_IDS_TO_OPEN,_PvPEventInstance);
		
		for (PvPEventMember PvPMember : _members.values())
		{
			L2PcInstance playerInstance = L2World.getInstance().getPlayer(PvPMember.getPlayerId());
			if (playerInstance != null)
			{
				new PvPEventTeleporter(playerInstance, Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES, false, false);
			}			
		}		
		_members.clear();
		setState(EventState.INACTIVE);
	}
	/**
	 * Participant Add and Remove scripts
	 * 
	 */	
	public static synchronized boolean addParticipant(L2PcInstance playerInstance)
	{
		if (playerInstance == null)
		{
			return false;
		}
		try
		{
			if (!_members.isEmpty())
			{
				if (_members.equals(playerInstance.getObjectId()))
				{
					_members.remove(playerInstance.getObjectId());
				}
			}
			int[] loc = { 0,0,0 };
			_members.put(playerInstance.getObjectId(), new PvPEventMember(playerInstance.getObjectId(),playerInstance.getName(),loc));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public static boolean removeParticipant(int playerObjectId)
	{
		if (!_members.isEmpty())
		{
			int teamId = playerObjectId;
			if (teamId != 0)
			{
				_members.remove(playerObjectId);
				return true;
			}
		}
		return false;
	}

	/**
	 * Send Message to all participants
	 * @param message
	 */
	public static void sysMsgToAllParticipants(String message)
	{
		for (PvPEventMember PvPMember : _members.values())
		{
			L2PcInstance playerInstance = L2World.getInstance().getPlayer(PvPMember.getPlayerId());
			if (playerInstance != null)
			{
				playerInstance.sendMessage(message);
			}
		}		
	}
	
	/**
	 * Open and close doors in instance
	 */
	private static void closeDoors(List<Integer> doors, int pvPEventInstance)
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
	
	private static void openDoors(List<Integer> doors, int pvPEventInstance)
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
	
	/**
	 * UnSpawns the PvPEvent Npc
	 */
	private static void unSpawnNpc()
	{
		_lastNpcSpawn.deleteMe();
		SpawnTable.getInstance().deleteSpawn(_lastNpcSpawn.getSpawn(), false); 
		_npcSpawn.stopRespawn();
		_npcSpawn = null;
		_lastNpcSpawn = null;
	}
	
	/**
	 * On Login and on Logout procedures
	 * 
	 */
	public static void onLogin(L2PcInstance playerInstance)
	{
		if (playerInstance == null || (!isStarting() && !isStarted()))
		{
			return;
		}
		
		if (_members.isEmpty())
		{
			return;
		}
		if (_members.get(playerInstance.getObjectId()) == null)
		{
			return;
		}
		new PvPEventTeleporter(playerInstance, _members.get(playerInstance.getObjectId()).getCoordinates(), true, false);
	}
	
	public static void onLogout(L2PcInstance playerInstance)
	{
		if (playerInstance != null && (isStarting() || isStarted() || isParticipating()))
		{
			if (removeParticipant(playerInstance.getObjectId()))
				playerInstance.setXYZInvisible(Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[0] + Rnd.get(101)-50,
						Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[1] + Rnd.get(101)-50,
						Config.PVP_EVENT_PARTICIPATION_NPC_COORDINATES[2]);
		}
	}
	
	/**
	 * Action procedures: onAction, onScroll, onPotion
	 * 
	 */
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
		int playerTeamId = 0;
		if (!_members.isEmpty() && _members.containsKey(playerInstance.getObjectId()))
		{
			playerTeamId = playerInstance.getObjectId();
		}
		int targetedPlayerTeamId = 0;
		if (!_members.isEmpty() && _members.containsKey(targetedPlayerObjectId))
		{
			targetedPlayerTeamId = targetedPlayerObjectId;
		}
		
		if ((playerTeamId != 0 && targetedPlayerTeamId == 0) || (playerTeamId == 0 && targetedPlayerTeamId != 0))
		{
			return false;
		}		
		if (playerTeamId != -1 && targetedPlayerTeamId != -1 && playerTeamId == targetedPlayerTeamId && playerInstance.getObjectId() != targetedPlayerObjectId/* && !Config.PvP_EVENT_TARGET_TEAM_MEMBERS_ALLOWED*/)
		{
			return false;
		}		
		return true;
	}
	
	public static boolean onScrollUse(int playerObjectId)
	{
		if (!isStarted())
			return true;
		
		if (isPlayerParticipant(playerObjectId)/* && !Config.PvP_EVENT_SCROLL_ALLOWED*/)
			return false;
		
		return true;
	}
	

	public static boolean onPotionUse(int playerObjectId)
	{
		if (!isStarted())
			return true;
		
		if (isPlayerParticipant(playerObjectId)/* && !Config.PvP_EVENT_POTIONS_ALLOWED*/)
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
		
		if (isPlayerParticipant(playerObjectId)/* && !Config.PvP_EVENT_SUMMON_BY_ITEM_ALLOWED*/)
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
		int killedTeamId = 0;
		if (!_members.isEmpty() && _members.containsKey(killedPlayerInstance.getObjectId()))
		{
			killedTeamId = killedPlayerInstance.getObjectId();
		}
		
		if (killedTeamId == 0)
		{
			return;
		}
		
		new PvPEventTeleporter(killedPlayerInstance, _members.get(killedTeamId).getCoordinates(), false, false);
		
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
		
		int killerTeamId = 0;
		if (!_members.isEmpty() && _members.containsKey(killerPlayerInstance.getObjectId()))
		{
			killerTeamId = killerPlayerInstance.getObjectId();
		}		
		if (killerTeamId != 0 && killedTeamId != 0 && killerTeamId != killedTeamId)
		{
			PvPEventMember killerTeam = _members.get(killerTeamId);
			
			killerTeam.increasePoints();
			
			CreatureSay cs = new CreatureSay(killerPlayerInstance.getObjectId(), Say2.TELL, killerPlayerInstance.getName(), "I have killed " + killedPlayerInstance.getName() + "!");
			
			L2PcInstance playerInstance = L2World.getInstance().getPlayer(killerTeamId);
			if (playerInstance != null)
			{
				playerInstance.sendPacket(cs);
			}			
		}
	}

	public static void onTeleported(L2PcInstance playerInstance)
	{
		if (!isStarted() || playerInstance == null || !isPlayerParticipant(playerInstance.getObjectId()))
			return;
	}

    public static final boolean checkForSkill(L2PcInstance source, L2PcInstance target, L2Skill skill)
    {
    	if (!isStarted())
    		return true;
    	// PvP is started
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
    	if (sourcePlayerId != targetPlayerId)
    	{
    		if (!skill.isOffensive())
    			return false;
    	}
    	return true;
    }

	/**
	 * Status check and set
	 */
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
	/**
	 *  Info about participation Players
	 * 
	 */	
	public static boolean isPlayerParticipant(int playerObjectId)
	{
		if (!isParticipating() && !isStarting() && !isStarted())
		{
			return false;
		}
		if (_members != null)
		{
			if (!_members.isEmpty())
			{
				if (_members.containsKey(playerObjectId))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int getParticipatedPlayersCount()
	{
		if (!isParticipating() && !isStarting() && !isStarted())
		{
			return 0;
		}
		if (_members.isEmpty())
		{
			return 0;
		}
		return _members.size();
	}
	/**
	 * Reward options
	 * 
	 * 
	 * 
	 *
	 */
	public static String calculateRewards()
	{
		FastList<String> bestName = new FastList<String>();
		int bestCount = 0;
		for (PvPEventMember PvPMember : _members.values())
		{
			if (PvPMember.getPoints() > bestCount)
			{
				bestCount = PvPMember.getPoints();				
				bestName.clear();
				bestName.add(PvPMember.getName());
			}
			else if (PvPMember.getPoints() == bestCount)
			{
				bestName.add(PvPMember.getName());
			}
		}
		setState(EventState.REWARDING);
		String winners = "";		
		for (String winnerName : bestName)
		{
			giveReward(winnerName);
			if (bestName.size() > 1)
			{
				winners += "";
				winners += winnerName;
				winners += " ";
			}
			else
			{
				winners += winnerName;
			}
		}
		return "PvP Event: Event finished. " + winners + " won with " + bestCount + " kills.";
	}
	
	private static void giveReward(String winnerName) 
	{
		for (PvPEventMember PvPMember : _members.values())
		{			
			L2PcInstance playerInstance = L2World.getInstance().getPlayer(PvPMember.getPlayerId());
			savePoints(playerInstance,PvPMember.getPoints());
			if (PvPMember.getName() == winnerName)
			{
				// Check for nullpointer
				if (playerInstance == null)
				{
					continue;
				}
				SystemMessage systemMessage = null;
				
				for (int[] reward : Config.PVP_EVENT_REWARDS)
				{
					PcInventory inv = playerInstance.getInventory();
					
					// Check for stackable item, non stackabe items need to be added one by one
					if (ItemTable.getInstance().createDummyItem(reward[0]).isStackable())
					{
						inv.addItem("PvP Event", reward[0], reward[1], playerInstance, playerInstance);
						
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
							inv.addItem("PvP Event", reward[0], 1, playerInstance, playerInstance);
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
	}
	
	/**
	 * Add PvP Points to Database pvp_results
	 * @return
	 */
	private static void savePoints(L2PcInstance playerInstance,int pvpPoints) 
	{
		int points = 0;
		try
		{
			points = getPoints(playerInstance) + pvpPoints;
		}
		catch(Exception e)
		{
			points = pvpPoints;
		}
		Connection con = null;
		/*
		 * Delete winner from list
		 */
		try
		{			
			con = L2DatabaseFactory.getInstance().getConnection();
			
			// Delete all his reports from database
			PreparedStatement delStatement = con.prepareStatement("DELETE FROM pvp_results WHERE char_name = ?");
			delStatement.setString(1, playerInstance.getName());
			delStatement.execute();
			delStatement.close();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}		
		/*
		 * Add winner to list
		 */
		try
		{			
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO pvp_results VALUES ( ?, ?, ?, ?)");
			statement.setString(1, playerInstance.getName());
			statement.setInt(2, playerInstance.getLevel());
			statement.setInt(3, playerInstance.getClassId().getId());
			statement.setInt(4, points);
			statement.execute();
			statement.close();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	private static int getPoints(L2PcInstance playerInstance) 
	{
		Connection con = null;
		int value = 0;
		try
		{			
	        con = L2DatabaseFactory.getInstance().getConnection();
	        PreparedStatement statement = con.prepareStatement("SELECT * FROM pvp_results WHERE name=?");
	        statement.setString(1, playerInstance.getName());
	        ResultSet result = statement.executeQuery();	        
	        value = result.getInt("points");
	        result.close();
	        statement.close();
		}
		catch (Exception e)
		{			
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		return value;
	}
	
	public static int getPvPEventInstance()
	{
		return _PvPEventInstance;
	}
}