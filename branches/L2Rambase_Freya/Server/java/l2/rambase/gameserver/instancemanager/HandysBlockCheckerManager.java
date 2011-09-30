/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import gnu.trove.TIntIntHashMap;

import java.util.ArrayList;

import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.BlockCheckerEngine;
import l2.rambase.gameserver.model.olympiad.Olympiad;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameAddPlayer;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameChangeTeam;
import l2.rambase.gameserver.network.serverpackets.ExCubeGameRemovePlayer;
import l2.rambase.gameserver.network.serverpackets.L2GameServerPacket;


public final class HandysBlockCheckerManager 
{
	/*
	 * This class manage the player add/remove, team change and
	 * event arena status, as the clearance of the participants
	 * list or liberate the arena 
	 */
	
	// All the participants and their team classifed by arena
	private static ArenaParticipantsHolder[] _arenaPlayers;
	
	// Arena votes to start the game
	private static TIntIntHashMap _arenaVotes = new TIntIntHashMap();
	
	// Arena Status, True = is being used, otherwise, False
	private static FastMap<Integer, Boolean> _arenaStatus;
	
	/**
	 * Return the number of event-start votes for the spcified
	 * arena id
	 * @param arenaId
	 * @return int (number of votes)
	 */
	public synchronized int getArenaVotes(int arenaId)
	{
		return _arenaVotes.get(arenaId);
	}
	
	/**
	 * Add a new vote to start the event for the specified
	 * arena id
	 * @param arena
	 */
	public synchronized void increaseArenaVotes(int arena)
	{
		int newVotes = _arenaVotes.get(arena) + 1;
		
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		
		if(newVotes > holder.getAllPlayers().size() / 2 && !holder.getEvent().isStarted())
		{
			ThreadPoolManager.getInstance().executeTask(holder.getEvent().new StartEvent());
		}
		else
		{
			_arenaVotes.put(arena, newVotes);
		}
	}
	
	/**
	 * Will clear the votes queue (of event start) for the
	 * specified arena id
	 * @param arena
	 */
	public synchronized void clearArenaVotes(int arena)
	{
		_arenaVotes.put(arena, 0);
	}

	private HandysBlockCheckerManager()
	{
		// Initialize arena status
		if(_arenaStatus == null)
		{
			_arenaStatus = new FastMap<Integer, Boolean>();
			_arenaStatus.put(0, false);
			_arenaStatus.put(1, false);
			_arenaStatus.put(2, false);
			_arenaStatus.put(3, false);			
		}
	}
	
	/**
	 * Returns the players holder
	 * @param arena
	 * @return ArenaParticipantsHolder
	 */
	public ArenaParticipantsHolder getHolder(int arena)
	{
		return _arenaPlayers[arena];
	}
		
	/**
	 * Initializes the participants holder
	 */
	public void startUpParticipantsQueue()
	{
		_arenaPlayers = new ArenaParticipantsHolder[4];
		
		for(byte i = 0; i < 4; ++i)
		{
			_arenaPlayers[i] = new ArenaParticipantsHolder(i);
		}
	}
	
	/**
	 * Add the player to the specified arena (throught the specified
	 * arena manager) and send the needed server ->  client packets
	 * @param player
	 * @param arenaId
	 */
	public boolean addPlayerToArena(L2PcInstance player, int arenaId)
	{
		ArenaParticipantsHolder holder = _arenaPlayers[arenaId];
		
		synchronized(holder)
		{
			boolean isRed;
			
			for(int i = 0; i < 4; i++)
			{
				if(_arenaPlayers[i].getAllPlayers().contains(player))
				{
					player.sendMessage("You are alredy registered for a Block Checker Match!");
					return false;
				}
			}
				
			if(player.isCursedWeaponEquipped())
			{
				player.sendMessage("You may not participe holding a Cursed Weapon");
				return false;
			}			
			if(TvTEvent.isPlayerParticipant(player.getObjectId()) 
					|| PvPEvent.isPlayerParticipant(player.getObjectId())
					|| player.isInOlympiadMode())
			{
				player.sendMessage("Couldnt register you due other event participation");
				return false;
			}

			if(Olympiad.getInstance().isRegistered(player))
			{
				Olympiad.getInstance().unRegisterNoble(player);
				player.sendMessage("You have been unregistered from Olympiad due Handy's Block Checker Event");
			}				
			if(KrateisCubeManager.getInstance().isRegistered(player))
			{
				KrateisCubeManager.getInstance().removePlayer(player);
				player.sendMessage("You have been unregistered from Kratei's Cube due Handy's Block Checker Event");
			}
			
			if(holder.getBlueTeamSize() < holder.getRedTeamSize())
			{
				holder.addPlayer(player, 1);
				isRed = false;
			}
			else
			{
				holder.addPlayer(player, 0);
				isRed = true;
			}
			holder.broadCastPacketToTeam(new ExCubeGameAddPlayer(player, isRed));
			return true;
		}
	}
	
	/**
	 * Will remove the specified player from the specified 
	 * team and arena and will send the needed packet to all
	 * his team mates / enemy team mates
	 * @param player
	 * @param arenaId
	 */
	public void removePlayer(L2PcInstance player, int arenaId, int team)
	{
		synchronized(_arenaPlayers[arenaId])
		{
			boolean isRed = team == 0 ? true : false;
			
			_arenaPlayers[arenaId].removePlayer(player, team);
			_arenaPlayers[arenaId].broadCastPacketToTeam(new ExCubeGameRemovePlayer(player, isRed));
		}
	}
	
	/**
	 * Will change the player from one team to other (if possible)
	 * and will send the needed packets
	 * @param player
	 * @param arena
	 * @param team
	 */
	public void changePlayerToTeam(L2PcInstance player, int arena, int team)
	{
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		
		synchronized(holder)
		{
			boolean isFromRed = holder._redPlayers.contains(player);
			
			if(isFromRed && holder.getBlueTeamSize() == 6)
			{
				player.sendMessage("The team is full");
				return;
			}
			else if(!isFromRed && holder.getRedTeamSize() == 6)
			{
				player.sendMessage("The team is full");
				return;
			}
			
			int futureTeam = isFromRed? 1 : 0;
			holder.addPlayer(player, futureTeam);
			
			if(isFromRed)
				holder.removePlayer(player, 0);
			else
				holder.removePlayer(player, 1);
			holder.broadCastPacketToTeam(new ExCubeGameChangeTeam(player, isFromRed));
		}
	}
	
	/**
	 * Will erase all participants from the specified holder
	 * @param arenaId
	 */
	public synchronized void clearPaticipantQueueByArenaId(int arenaId)
	{
		_arenaPlayers[arenaId].clearPlayers();
	}
	
	/**
	 * Returns true if arena is holding an event at this momment
	 * @param arenaId
	 * @return Boolean
	 */
	public boolean arenaIsBeingUsed(int arenaId)
	{
		if(arenaId < 0 || arenaId > 3) 
			return false;
		return _arenaStatus.get(arenaId);
	}
	
	/**
	 * Set the specified arena as being used
	 * @param arenaId
	 */
	public void setArenaBeingUsed(int arenaId)
	{
		_arenaStatus.put(arenaId, true);
	}
	
	/**
	 * Set as free the specified arena for future
	 * events
	 * @param arenaId
	 */
	public void setArenaFree(int arenaId)
	{
		_arenaStatus.put(arenaId, false);
	}

	public static HandysBlockCheckerManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		private static HandysBlockCheckerManager _instance = new HandysBlockCheckerManager();
	}
	
	public class ArenaParticipantsHolder
	{
		int _arena;
		ArrayList<L2PcInstance> _redPlayers;
		ArrayList<L2PcInstance> _bluePlayers;
		BlockCheckerEngine _engine;
		
		public ArenaParticipantsHolder(int arena)
		{
			_arena = arena;
			_redPlayers = new ArrayList<L2PcInstance>(6);
			_bluePlayers = new ArrayList<L2PcInstance>(6);
			_engine = new BlockCheckerEngine(this, _arena);
		}
		
		public ArrayList<L2PcInstance> getRedPlayers()
		{
			return _redPlayers;
		}
		
		public ArrayList<L2PcInstance> getBluePlayers()
		{
			return _bluePlayers;
		}
		
		public ArrayList<L2PcInstance> getAllPlayers()
		{
			ArrayList<L2PcInstance> all = new ArrayList<L2PcInstance>(12);
			all.addAll(_redPlayers);
			all.addAll(_bluePlayers);
			return all;
		}
		
		public void addPlayer(L2PcInstance player, int team)
		{
			if(team == 0)
				_redPlayers.add(player);
			else
				_bluePlayers.add(player);
		}
		
		public void removePlayer(L2PcInstance player, int team)
		{
			if(team == 0)
				_redPlayers.remove(player);
			else
				_bluePlayers.remove(player);
		}
		
		public int getPlayerTeam(L2PcInstance player)
		{
			if(_redPlayers.contains(player))
				return 0;
			else if(_bluePlayers.contains(player))
				return 1;
			else
				return -1;
		}
		
		public int getRedTeamSize()
		{
			return _redPlayers.size();
		}
		
		public int getBlueTeamSize()
		{
			return _bluePlayers.size();
		}
		
		public void broadCastPacketToTeam(L2GameServerPacket packet)
		{
			ArrayList<L2PcInstance> team = new ArrayList<L2PcInstance>(12);
			team.addAll(_redPlayers);
			team.addAll(_bluePlayers);
			
			for(L2PcInstance p : team)
				p.sendPacket(packet);
		}
		
		public void clearPlayers()
		{
			_redPlayers.clear();
			_bluePlayers.clear();
		}
		
		public BlockCheckerEngine getEvent()
		{
			return _engine;
		}
		
		public void updateEvent()
		{
			_engine.updatePlayersOnStart(this);
		}		
	}
}
