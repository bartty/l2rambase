/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.entity;

import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager;
import l2.rambase.gameserver.instancemanager.HandysBlockCheckerManager.ArenaParticipantsHolder;
import l2.rambase.gameserver.model.L2Effect;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2BlockInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.PcInventory;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.ExBasicActionList;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameChangePoints;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameCloseUI;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameEnd;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameExtendedChangePoints;
import l2.rambase.gameserver.network.serverpackets.RelationChanged;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public final class BlockCheckerEngine
{
	private static final Logger _log = Logger.getLogger(BlockCheckerEngine.class.getName());
	// The object which holds all basic members info
	private HandysBlockCheckerManager.ArenaParticipantsHolder _holder;
	// Maps to hold player of each team and his points
	private FastMap<L2PcInstance, Integer> _redTeamPoints = new FastMap<L2PcInstance, Integer>();
	private FastMap<L2PcInstance, Integer> _blueTeamPoints = new FastMap<L2PcInstance, Integer>();
	// The initial points of the event
	private int _redPoints = 15;
	private int _bluePoints = 15;
	// Current used arena
	private int _arena = -1;
	// All blocks
	private FastList<L2Spawn> _spawns = new FastList<L2Spawn>();
	// Sets if the red team won the event at the end of this (used for packets)
	private boolean _isRedWinner;
	// Time when the event starts. Used on packet sending
	private long _startedTime;
	// The needed arena coordinates
	// Arena X: team1X, team1Y, team2X, team2Y, ArenaCenterX, ArenaCenterY
	private static final int[][] _arenaCoordinates =
	{
		// Arena 0 - Team 1 XY, Team 2 XY - CENTER XY
		{ -58368, -62745, -57751, -62131, -58053, -62417 },
		// Arena 1 - Team 1 XY, Team 2 XY - CENTER XY
		{ -58350, -63853, -57756, -63266, -58053, -63551 },
		// Arena 2 - Team 1 XY, Team 2 XY - CENTER XY
		{ -57194, -63861, -56580, -63249, -56886, -63551 },
		// Arena 3 - Team 1 XY, Team 2 XY - CENTER XY
		{ -57200, -62727, -56584, -62115, -56850, -62391 }	
	};
	// Common z coordinate
	private static final int _zCoord = -2405;
	// Girl Npc
	private L2Npc _girlNpc;
	// List of dropped items in event (for later deletion)
	private FastList<L2ItemInstance> _drops = new FastList<L2ItemInstance>();
	// Default arena
	private static final byte DEFAULT_ARENA = -1;
	// Event is started
	private boolean _isStarted = false;
	
	private static final int FIRST_PRICE = 50;
	private static final int SECOND_PRICE = 25;
	private static final int NORMAL_PRICE = 10;
	private static final int LOSER_PRICE = 2;
	
	public BlockCheckerEngine(HandysBlockCheckerManager.ArenaParticipantsHolder holder, int arena)
	{
		_holder = holder;
		if(arena > -1 && arena < 4)
			_arena = arena;
		
		for(L2PcInstance player : holder.getRedPlayers())
			_redTeamPoints.put(player, 0);
		for(L2PcInstance player : holder.getBluePlayers())
			_blueTeamPoints.put(player, 0);
	}
	
	/**
	 * Updates the player holder before the event starts
	 * to synchronize all info
	 * @param holder
	 */
	public void updatePlayersOnStart(ArenaParticipantsHolder holder)
	{
		_holder = holder;
	}
	
	/**
	 * Returns the current holder object of this
	 * object engine
	 * @return HandysBlockCheckerManager.ArenaParticipantsHolder
	 */
	public ArenaParticipantsHolder getHolder()
	{
		return _holder;
	}
	
	/**
	 * Will return the id of the arena used
	 * by this event
	 * @return false;
	 */
	public int getArena()
	{
		return _arena;
	}
	
	/**
	 * Returns the time when the event
	 * started
	 * @return long
	 */
	public long getStarterTime()
	{
		return _startedTime;
	}
	
	/**
	 * Returns the current red team points
	 * @return int
	 */
	public int getRedPoints()
	{
		synchronized(this)
		{
			return _redPoints;
		}
	}
	
	/**
	 * Returns the current blue team points
	 * @return int
	 */
	public int getBluePoints()
	{
		synchronized(this)
		{
			return _bluePoints;
		}
	}
	
	/**
	 * Returns the player points
	 * @param player
	 * @param isRed
	 * @return int
	 */
	public int getPlayerPoints(L2PcInstance player, boolean isRed)
	{
		if(!_redTeamPoints.containsKey(player) && !_blueTeamPoints.containsKey(player))
			return 0;
		
		if(isRed)
		{
			return _redTeamPoints.get(player);
		}
		else
			return _blueTeamPoints.get(player);
	}
	
	/**
	 * Increases player points for his teams
	 * @param player
	 * @param team
	 */
	public synchronized void increasePlayerPoints(L2PcInstance player, int team)
	{
		if(player == null)
			return;
		
		if(team == 0)
		{
			int points = _redTeamPoints.get(player) + 1;
			_redTeamPoints.put(player, points);
			_redPoints++;
			_bluePoints--;
		}
		else
		{
			int points = _blueTeamPoints.get(player) + 1;
			_blueTeamPoints.put(player, points);
			_bluePoints++;
			_redPoints--;
		}
	}
	
	/**
	 * Will add a new drop into the list of
	 * dropped items
	 * @param item
	 */
	public void addNewDrop(L2ItemInstance item)
	{
		if(item != null)
			_drops.add(item);
	}
	
	/**
	 * Will return true if the event is alredy
	 * started
	 * @return boolean
	 */
	public boolean isStarted()
	{
		return _isStarted;
	}
	
	/**
	 * Will send all packets for the event members with 
	 * the relation info
	 */
	private void broadcastRelationChanged(L2PcInstance plr)
	{
		for(L2PcInstance p : _holder.getAllPlayers())
		{
			p.sendPacket(new RelationChanged(plr, plr.getRelation(p), plr.isAutoAttackable(p)));
		}
	}
	
	/**
	 * This inner class set ups all player
	 * and arena parameters to start the event
	 */
	public class StartEvent implements Runnable
	{		
		// In event used skills
		private L2Skill _freeze, _transformationRed, _transformationBlue;
		// Common and unparametizer packet
		private final ExCubeGameCloseUI _closeUserInterface = new ExCubeGameCloseUI();
		
		public StartEvent()
		{
			// Initialize all used skills
			_freeze = SkillTable.getInstance().getInfo(6034, 1);
			_transformationRed = SkillTable.getInstance().getInfo(6035, 1);
			_transformationBlue = SkillTable.getInstance().getInfo(6036, 1);
		}
				
		/**
		 * Will set up all player parameters and 
		 * port them to their respective location 
		 * based on their teams
		 */
		private void setUpPlayers()
		{
			// Set current arena as being used
			HandysBlockCheckerManager.getInstance().setArenaBeingUsed(_arena);
			
			// Initialize packets avoiding create a new one per player
			_redPoints = _spawns.size() / 2;
			_bluePoints = _spawns.size() / 2;
			final ExCubeGameChangePoints initialPoints = new ExCubeGameChangePoints(300,_bluePoints,_redPoints);			
			ExCubeGameExtendedChangePoints clientSetUp;
			
			for(L2PcInstance player : _holder.getAllPlayers())
			{
				final ExBasicActionList actionList = new ExBasicActionList(player);
				if(player == null) continue;
							
				// Send the secret client packet set up
				boolean isRed = _holder.getRedPlayers().contains(player);
				int team = isRed ? 0 : 1;
				
				clientSetUp = new ExCubeGameExtendedChangePoints(300, _bluePoints, _redPoints, isRed, player, 0);
				player.sendPacket(clientSetUp);
				
				player.sendPacket(ActionFailed.STATIC_PACKET);
				
				// Teleport Player - Array access
				// Team 0 * 2 = 0; 0 = 0, 0 + 1 = 1.
				// Team 1 * 2 = 2; 2 = 2, 2 + 1 = 3
				int tc = team * 2;
				// Get x and y coordinates
				int x = _arenaCoordinates[_arena][tc];
				int y = _arenaCoordinates[_arena][tc + 1];
				player.teleToLocation(x, y, _zCoord);
				// Set the player team
				if(isRed)
				{
					_redTeamPoints.put(player,0);
					player.setTeam(2);
				}
				else
				{
					_blueTeamPoints.put(player,0);
					player.setTeam(1);
				}
				// Give the player start up effects
				// Freeze
				_freeze.getEffects(player, player);
				// Tranformation
				if(team == 0)
					_transformationRed.getEffects(player, player);
				else
					_transformationBlue.getEffects(player, player);
				// Set the current player arena
				player.setBlockCheckerArena((byte) _arena);
				player.setInsideZone(L2Character.ZONE_PVP, true);
				// Send needed packets
				player.sendPacket(initialPoints);
				player.sendPacket(_closeUserInterface);
				// ExBasicActionList
				player.sendPacket(actionList);	
				broadcastRelationChanged(player);
			}
		}
				
		@Override
		public void run()
		{
			// Wrong arena passed, stop event
			if(_arena == -1)
			{
				_log.severe("Couldnt set up the arena Id for the Block Checker event, cancelling event...");
				return;
			}
			// Spawn the blocks
			ThreadPoolManager.getInstance().executeTask(new SpawnRound(16, 1));
			// Start up player parameters
			setUpPlayers();
			// Set the started time
			_startedTime = System.currentTimeMillis() + 300000;
			_isStarted = true;
		}
	}
	
	/**
	 * This class spawns the second round of boxes
	 * and schedules the event end
	 */
	class SpawnRound implements Runnable
	{
		int _numOfBoxes;
		int _round;
		
		SpawnRound(int numberOfBoxes, int round)
		{
			_numOfBoxes = numberOfBoxes;
			_round = round;
		}
		
		@Override
		public void run()
		{
			switch(_round)
			{
				case 1:
					// Schedule second spawn round
					ThreadPoolManager.getInstance().scheduleGeneral(new SpawnRound(20, 2), 60000);
					break;
				case 2:
					// Schedule third spawn round
					ThreadPoolManager.getInstance().scheduleGeneral(new SpawnRound(14, 3), 60000);
					break;
				case 3:
					// Schedule Event End
					ThreadPoolManager.getInstance().scheduleGeneral(new EndEvent(), 180000);
					break;
			}
			// random % 2, if == 0 will spawn a red block
			// if != 0, will spawn a blue block
			byte random = 2;
			// common template
			final L2NpcTemplate template = NpcTable.getInstance().getTemplate(18672);
			// Spawn blocks
			try
			{
				// Creates 50 new blocks
				for(int i = 0; i < _numOfBoxes; i++)
				{
					L2Spawn spawn = new L2Spawn(template);
					spawn.setLocx(_arenaCoordinates[_arena][4] + Rnd.get(-400,400));
					spawn.setLocy(_arenaCoordinates[_arena][5] + Rnd.get(-400,400));
					spawn.setLocz(_zCoord);
					spawn.setAmount(1);
					spawn.setHeading(1);
					spawn.setRespawnDelay(1);
					SpawnTable.getInstance().addNewSpawn(spawn, false);
					spawn.init();
					L2BlockInstance block = (L2BlockInstance)spawn.getLastSpawn();
					// switch color
					if(random % 2 == 0) block.setRed(true);
					else block.setRed(false);
					
					block.disableCoreAI(true);
					_spawns.add(spawn);
					random++;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			// Spawn the block carrying girl
			if(_round == 1 || _round == 2)
			{
				L2NpcTemplate girl = NpcTable.getInstance().getTemplate(18676);
				try
				{
					final L2Spawn girlSpawn = new L2Spawn(girl);
					girlSpawn.setLocx(_arenaCoordinates[_arena][4] + Rnd.get(-400,400));
					girlSpawn.setLocy(_arenaCoordinates[_arena][5] + Rnd.get(-400,400));
					girlSpawn.setLocz(_zCoord);
					girlSpawn.setAmount(1);
					girlSpawn.setHeading(1);
					girlSpawn.setRespawnDelay(1);
					SpawnTable.getInstance().addNewSpawn(girlSpawn, false);
					girlSpawn.init();
					_girlNpc = girlSpawn.getLastSpawn();
					// Schedule his deletion after 9 secs of spawn
					ThreadPoolManager.getInstance().scheduleGeneral(new Runnable()
					{
						@Override
						public void run()
						{
							if(_girlNpc == null) return;
							_girlNpc.deleteMe();
							SpawnTable.getInstance().deleteSpawn(_girlNpc.getSpawn(), false);
						}
					}, 9000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			_redPoints += _numOfBoxes / 2;
			_bluePoints += _numOfBoxes / 2;
			
			int timeLeft = (int)((getStarterTime() - System.currentTimeMillis()) / 1000);
			ExCubeGameChangePoints changePoints = new ExCubeGameChangePoints(timeLeft, getBluePoints(), getRedPoints());
			getHolder().broadCastPacketToTeam(changePoints);
		}
	}
		
	/**
	 * This class erase all event parameters on player
	 * and port them back near Handy. Also, unspawn
	 * blocks, runs a garbage collector and set as free
	 * the used arena
	 */
	class EndEvent implements Runnable
	{		
		// Garbage collector and arena free setter
		private void clearMe()
		{
			HandysBlockCheckerManager.getInstance().clearPaticipantQueueByArenaId(_arena);
			_holder.clearPlayers();
			_blueTeamPoints.clear();
			_redTeamPoints.clear();
			HandysBlockCheckerManager.getInstance().setArenaFree(_arena);
			
			for(L2Spawn spawn : _spawns)
			{
				spawn.stopRespawn();
				spawn.getLastSpawn().deleteMe();
				SpawnTable.getInstance().deleteSpawn(spawn, false);
				spawn = null;
			}
			_spawns.clear();
			
			for(L2ItemInstance item : _drops)
			{
				// npe
				if(item == null)
					continue;
				
				// a player has it, it will be deleted later
				if(!item.isVisible() || item.getOwnerId() != 0)
					continue;
				
				item.decayMe();
				L2World.getInstance().removeObject(item);
			}
			_drops.clear();
		}

		/**
		 * Reward players after event.
		 * Tie - No Reward
		 */
		private void rewardPlayers()
		{
			if(_redPoints == _bluePoints)
				return;
			
			_isRedWinner = _redPoints > _bluePoints ? true : false;
			
			if(_isRedWinner)
			{
				rewardAsWinner(true);
				rewardAsLooser(false);
			}
			else
			{
				rewardAsWinner(false);
				rewardAsLooser(true);
			}
		}
		
		/**
		 * Reward the speicifed team as a winner team
		 * 1) Higher score - 8 extra
		 * 2) Higher score - 5 extra
		 * @param isRed
		 */
		private void rewardAsWinner(boolean isRed)
		{
			FastMap<L2PcInstance, Integer> tempPoints = isRed? _redTeamPoints : _blueTeamPoints;
			
			// Main give
			for(L2PcInstance pc : tempPoints.keySet())
			{
				if(pc == null)
					continue;
				
				if(tempPoints.get(pc) >= 10)
					pc.addItem("Block Checker", 13067, NORMAL_PRICE, pc, true);
				else
					tempPoints.remove(pc);
			}
			
			int first = 0, second = 0;
			L2PcInstance winner1 = null, winner2 = null;
			for(L2PcInstance pc : tempPoints.keySet())
			{
				int pcPoints = tempPoints.get(pc);
				if(pcPoints > first)
				{
					// Move old data
					second = first;
					winner2 = winner1;
					// Set new data
					first = pcPoints;
					winner1 = pc;
				}
				else if(pcPoints > second)
				{
					second = pcPoints;
					winner2 = pc;
				}
			}
			if(winner1 != null)
				winner1.addItem("Block Checker", 13067, FIRST_PRICE, winner1, true);
			if(winner2 != null)
				winner2.addItem("Block Checker", 13067, SECOND_PRICE, winner2, true);
		}
		
		/**
		 * Will reward the looser team with the
		 * predefined rewards
		 * Player got >= 10 points: 2 coins
		 * Player got < 10 points: 0 coins
		 * @param isRed
		 */
		private void rewardAsLooser(boolean isRed)
		{
			FastMap<L2PcInstance, Integer> tempPoints = isRed? _redTeamPoints : _blueTeamPoints;
			
			for(L2PcInstance player : tempPoints.keySet())
			{
				if(player != null && tempPoints.get(player) >= 10)
				{
					player.addItem("Block Checker", 13067, LOSER_PRICE, player, true);
				}
			}
		}
		
		/**
		 * Telport players back, give status back and 
		 * send final packet
		 */
		private void setPlayersBack()
		{
			final ExCubeGameEnd end = new ExCubeGameEnd(_isRedWinner);
			
			for(L2PcInstance player : _holder.getAllPlayers())
			{
				if(player == null) continue;
				
				// Remove transformation
				L2Effect effect;
				if((effect = player.getFirstEffect(6035)) != null)
					effect.exit();
				else if((effect = player.getFirstEffect(6036)) != null)
					effect.exit();
				
				// Remove team aura
				player.setTeam(0);
				
				// Set default arena
				player.setBlockCheckerArena(DEFAULT_ARENA);
				
				// Remove the event items
				PcInventory inv = player.getInventory();
				if(inv.getItemByItemId(13787) != null)
				{
					long count = inv.getInventoryItemCount(13787, 0);
					inv.destroyItemByItemId("Handys Block Checker", 13787, count, player, player);
				}
				if(inv.getItemByItemId(13788) != null)
				{
					long count = inv.getInventoryItemCount(13788, 0);
					inv.destroyItemByItemId("Handys Block Checker", 13788, count, player, player);
				}
				broadcastRelationChanged(player);
				// Teleport Back
				player.teleToLocation(-57478, -60367, -2370);
				player.setInsideZone(L2Character.ZONE_PVP, false);
				// Send end packet
				player.sendPacket(end);
				player.broadcastUserInfo();
			}
		}
				
		@Override
		public void run()
		{
			rewardPlayers();
			setPlayersBack();
			clearMe();
			_isStarted = false;
		}
	}
}