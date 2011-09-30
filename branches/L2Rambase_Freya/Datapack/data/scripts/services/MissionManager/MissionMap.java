package services.MissionManager;

import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.model.L2Multisell;
import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;

/**
 * Mission Dungeon for events
 * @author L2Rambase Project
 * @version 5.31
 * Solo Missions
 * @param Mission 1-85 lvl
 * @category Event Dungeon
 */
public class MissionMap extends Quest 
{
	/**
	 * Mission Maps
	 * @param FastMap of Mission Maps
	 */
	private final FastMap<Integer, Mission> _missionStars = new FastMap<Integer, Mission>();
	private final FastMap<Integer, MissionRaidMap> _missionRaid = new FastMap<Integer, MissionRaidMap>();
	private final FastMap<Integer, MissionRaidStatus> _missionRaidStatus = new FastMap<Integer, MissionRaidStatus>();
	private final FastMap<Integer, MissionStrategyMap> _missionStrategy = new FastMap<Integer, MissionStrategyMap>();
	private final FastMap<Integer, MissionStrategyStatus> _missionStrategyStatus = new FastMap<Integer, MissionStrategyStatus>();
	/**
	 * Npc of Instance	
	 */
	private static final int MissionMasterNpc = 7101;

	/**
	 * Mission Solo Map
	 */
	private class Mission 
	{		
		private int _leaderId;
		private int _missionStars;
		private int _instanceId;
		private boolean _canEnter;
		private long _enterTime;

		private class MissionReEntrenceTimerTask implements Runnable 
		{
			public void run()
			{
				_canEnter = true;
			}
		}

		private class MissionSpawnTask implements Runnable 
		{
			public void run() 
			{
				for (int[] loc: MissionMapConfig.Mission_Spawns)
				{
					MissionSpawn(MissionMapConfig.Mission_Monsters[Rnd.get(2)][getMissionStars()],loc);
				}
			}
		}
		
		private Mission(int id,int stars) 
		{
			try 
			{
				_leaderId = id;
				_missionStars = stars;
				_canEnter = false;
				_enterTime = System.currentTimeMillis();				
				_instanceId = InstanceManager.getInstance().createDynamicInstance("Mission.xml");
				ThreadPoolManager.getInstance().scheduleGeneral(new MissionReEntrenceTimerTask(), (MissionMapConfig.SOLO_RE_ENTER_TIME*60)*1000);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}

		private void MissionSpawn(int npcId,int[] loc) 
		{
			try 
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(npcId);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(loc[0]);
					spawnDat.setLocy(loc[1]);
					spawnDat.setLocz(loc[2]);
					spawnDat.setHeading(0);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(30);
					spawnDat.startRespawn();
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();
				} 
				else 
				{
					_log.warning("MissionMap: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("MissionMap: Spawns could not be initialized: "	+ e);
			}
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new MissionSpawnTask(), MissionMapConfig.SOLO_SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}
		
		private int getLeaderId() 
		{
			return _leaderId;
		}

		private int getMissionStars() 
		{
			try
			{
				return _missionStars;
			}
			catch (Exception e)
			{
				return 1;
			}
		}
		
		private long getEnterTime()
		{
			return _enterTime;
		}

		private boolean getCanEnter()
		{
			return _canEnter;
		}
	}
	/**
	 * Mission Raid Map
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class MissionRaidStatus
	{
		
		@SuppressWarnings("unused")
		private int _playerId;
		private boolean _canEnter;
		private long _enterTime;
		
		private MissionRaidStatus(int id) 
		{
			try 
			{
				_playerId = id;
				_enterTime = 0;
				_canEnter = false;
				ThreadPoolManager.getInstance().scheduleGeneral(new MissionRaidReEntrenceTimerTask(), (MissionMapConfig.RAID_RE_ENTER_TIME*60)*1000);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}
		
		private class MissionRaidReEntrenceTimerTask implements Runnable 
		{
			public void run()
			{
				_canEnter = true;
			}
		}
		
		private boolean getCanEnter() 
		{
			return _canEnter;
		}

		public void setEnterTime(long currentTimeMillis) 
		{
			_enterTime = currentTimeMillis;
		}
		
		public long getEnterTime()
		{
			return _enterTime;
		}
		
	}	
	private class MissionRaidMap 
	{		
		private int _missionId;
		private int _missionLevel;
		private int _instanceId;

		private class MissionRaidSpawnTask implements Runnable 
		{
			public void run() 
			{
				MissionRaidSpawn(getMissionLevel());
			}
		}

		private MissionRaidMap(int id,int missionLevel) 
		{
			try 
			{
				_missionId = id;
				_missionLevel = missionLevel;
				_instanceId = InstanceManager.getInstance().createDynamicInstance("MissionRaid.xml");				
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}
		private void MissionRaidSpawn(int missionLevel) 
		{
			for (int[] location: MissionMapConfig.Mission_Raid_Spawns)
			{
				try 
				{
					L2NpcTemplate template = NpcTable.getInstance().getTemplate(MissionMapConfig.Mobs[missionLevel][Rnd.get(0,2)]);
					L2Spawn spawnDat;
					if (template != null) 
					{
						spawnDat = new L2Spawn(template);
						spawnDat.setAmount(1);
						spawnDat.setLocx(location[0]);
						spawnDat.setLocy(location[1]);
						spawnDat.setLocz(location[2]);
						spawnDat.setHeading(0);
						spawnDat.setInstanceId(getInstanceId());
						spawnDat.setRespawnDelay(Rnd.get(15,20));
						spawnDat.startRespawn();
						SpawnTable.getInstance().addNewSpawn(spawnDat, false);
						spawnDat.doSpawn();
					} 
					else 
					{
						_log.warning("MissionRaid: Data missing in NPC table for ID: " + 0);
					}
				} 
				catch (Exception e) 
				{
					_log.warning("MissionRaid: Spawns could not be initialized: "	+ e);
				}
			}
			try 
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(MissionMapConfig.Mobs[missionLevel][3]);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(MissionMapConfig.raidLoc[0]);
					spawnDat.setLocy(MissionMapConfig.raidLoc[1]);
					spawnDat.setLocz(MissionMapConfig.raidLoc[2]);
					spawnDat.setHeading(0);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(15);
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();	
					spawnDat.stopRespawn();
				} 
				else 
				{
					_log.warning("MissionRaid: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("MissionRaid: Spawns could not be initialized: "	+ e);
			}
			
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new MissionRaidSpawnTask(), MissionMapConfig.RAID_SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}
		
		private int getMissionId() 
		{
			return _missionId;
		}
		
		private int getMissionLevel()
		{
			return _missionLevel;
		}
	}
	/**
	 * Mission Strategy Map
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	private class MissionStrategyStatus
	{		
		@SuppressWarnings("unused")
		private int _clanId;
		private boolean _canEnter;
		private long _enterTime;
		
		private MissionStrategyStatus(int id) 
		{
			try 
			{
				_clanId = id;
				_enterTime = 0;
				_canEnter = false;
				ThreadPoolManager.getInstance().scheduleGeneral(new MissionStrategyReEntrenceTimerTask(), (MissionMapConfig.STRATEGY_RE_ENTER_TIME*60)*1000);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}
		
		private class MissionStrategyReEntrenceTimerTask implements Runnable 
		{
			public void run()
			{
				_canEnter = true;
			}
		}
		
		private boolean getCanEnter() 
		{
			return _canEnter;
		}

		public void setEnterTime(long currentTimeMillis) 
		{
			_enterTime = currentTimeMillis;
		}
		
		public long getEnterTime()
		{
			return _enterTime;
		}		
	}	
	private class MissionStrategyMap
	{		
		@SuppressWarnings("unused")
		private int _missionId;
		private int _missionLevel;
		private int _instanceId;

		private class MissionStrategySpawnTask implements Runnable 
		{
			public void run() 
			{
				MissionStrategySpawn(getMissionLevel(),1);
			}
		}

		private MissionStrategyMap(int id,int missionLevel) 
		{
			try 
			{
				_missionId = id;
				_missionLevel = missionLevel;
				_instanceId = InstanceManager.getInstance().createDynamicInstance("MissionStrategy.xml");				
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}
		private void MissionStrategySpawn(int missionLevel,int stage) 
		{
			int[][] templateGroup;
			switch(stage)
			{
				case 1:
					templateGroup = MissionMapConfig.StrategyMobs_1;
					break;
				case 2:
					templateGroup = MissionMapConfig.StrategyMobs_2;
					break;
				case 3:
					templateGroup = MissionMapConfig.StrategyMobs_3;
					break;
				case 4:
					templateGroup = MissionMapConfig.StrategyMobs_4;
					break;
				default:
					templateGroup = MissionMapConfig.StrategyMobs_1;
					break;
			}
			for (int[] location: MissionMapConfig.Strategy_Spawns)
			{
				try 
				{
					L2NpcTemplate template = NpcTable.getInstance().getTemplate(templateGroup[missionLevel][Rnd.get(0,2)]);
					L2Spawn spawnDat;
					if (template != null) 
					{
						spawnDat = new L2Spawn(template);
						spawnDat.setAmount(1);
						spawnDat.setLocx(location[0]);
						spawnDat.setLocy(location[1]);
						spawnDat.setLocz(location[2]);
						spawnDat.setHeading(0);
						spawnDat.setInstanceId(getInstanceId());
						spawnDat.setRespawnDelay(15);						
						SpawnTable.getInstance().addNewSpawn(spawnDat, false);
						spawnDat.doSpawn();
						spawnDat.startRespawn();
					} 
					else 
					{
						_log.warning("MissionStrategy: Data missing in NPC table for ID: " + 0);
					}
				} 
				catch (Exception e) 
				{
					_log.warning("MissionStrategy: Spawns could not be initialized: "	+ e);
				}
			}
			try 
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(templateGroup[missionLevel][3]);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(MissionMapConfig.raidStrategyLoc[0]);
					spawnDat.setLocy(MissionMapConfig.raidStrategyLoc[1]);
					spawnDat.setLocz(MissionMapConfig.raidStrategyLoc[2]);
					spawnDat.setHeading(64055);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(0);
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();	
					spawnDat.stopRespawn();
				} 
				else 
				{
					_log.warning("MissionStrategy: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("MissionStrategy: Spawns could not be initialized: "	+ e);
			}
			
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new MissionStrategySpawnTask(), MissionMapConfig.RAID_SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}
		
		private int getMissionLevel()
		{
			return _missionLevel;
		}
	}
			
	/**
	 * Check Player Grade Level
	 * @param player
	 * @return
	 */
	private int checkLeaderLevel(L2PcInstance player) 
	{
		if (player.getLevel() > 19 && player.getLevel() < 30)
		{
			return 2;
		}
		else if (player.getLevel() > 29 && player.getLevel() < 40)
		{
			return 3;
		}
		else if (player.getLevel() > 39 && player.getLevel() < 50)
		{
			return 4;
		}
		else if (player.getLevel() > 49 && player.getLevel() < 60)
		{
			return 5;
		}
		else if (player.getLevel() > 59 && player.getLevel() < 70)
		{
			return 6;
		}
		else if (player.getLevel() > 69 && player.getLevel() < 80)
		{
			return 7;
		}
		else if (player.getLevel() > 79)
		{
			return 8;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * Check Conditions to Party Players
	 * @param party leader
	 */
	private boolean checkConditionsParty(L2PcInstance player, L2Npc npc,int raidLevel) 
	{
		L2Party party = player.getParty();
		if (party == null) 
		{
			player.sendPacket(new SystemMessage(SystemMessageId.NOT_IN_PARTY_CANT_ENTER));
			return false;
		}
		if (party.getLeader() != player) 
		{
			player.sendPacket(new SystemMessage(SystemMessageId.ONLY_PARTY_LEADER_CAN_ENTER));
			return false;
		}
		if (party.getMemberCount() > MissionMapConfig.MAX_RAID_PARTY_MEMBER_COUNT || party.getMemberCount() < MissionMapConfig.MIN_RAID_PARTY_MEMBER_COUNT)
		{
			player.sendPacket(new SystemMessage(SystemMessageId.PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER));
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers()) 
		{
			if (!partyMember.isInsideRadius(npc, 500, false, false)) 
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
			if (partyMember.getPet() != null)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.YOUR_SERVITOR_HAS_VANISHED);
				partyMember.getPet().unSummon(partyMember);
				partyMember.sendPacket(sm);
				return false;
			}
			int partyMemberLevel = partyMember.getLevel();
			if (raidLevel == 1)
			{
				if(partyMemberLevel < 40 && partyMemberLevel >=50)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}
			}
			else if (raidLevel == 2)
			{
				if (partyMemberLevel < 50 && partyMemberLevel >=60)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 3)
			{
				if (partyMemberLevel < 60 && partyMemberLevel >=70)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 4)
			{
				if (partyMemberLevel < 70 && partyMemberLevel >=80)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 5)
			{
				if (partyMemberLevel <80)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
		}
		return true;
	}
	
	/**
	 * Check Conditions to Party Players
	 * @param party leader
	 */
	private boolean checkConditionsStrategy(L2PcInstance player, L2Npc npc,int raidLevel) 
	{
		L2Party party = player.getParty();
		if (party == null) 
		{
			player.sendPacket(new SystemMessage(SystemMessageId.NOT_IN_PARTY_CANT_ENTER));
			return false;
		}
		if (party.getLeader() != player) 
		{
			player.sendPacket(new SystemMessage(SystemMessageId.ONLY_PARTY_LEADER_CAN_ENTER));
			return false;
		}
		if (player.getClan() == null)
		{
			player.sendMessage("You need clan to enter");
			return false;
		}
		else
		{
			if (!player.isClanLeader())
			{
				player.sendMessage("Only clan leader that are party leader can enter.");
				return false;
			}
			if (player.getClan().getLevel() < 5)
			{
				player.sendMessage("You must get 5 or higher level of clan");
				return false;
			}
		}
		if (party.getMemberCount() > MissionMapConfig.MAX_RAID_PARTY_MEMBER_COUNT || party.getMemberCount() < MissionMapConfig.MIN_RAID_PARTY_MEMBER_COUNT)
		{
			player.sendPacket(new SystemMessage(SystemMessageId.PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER));
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers()) 
		{
			if (partyMember.getClanId() != player.getClanId())
			{
				player.sendMessage("Player "+partyMember.getName()+" is not from your clan");
				partyMember.sendMessage("Only clan members from this same clan can enter to strategic mode");
				return false;
			}
			if (!partyMember.isInsideRadius(npc, 500, false, false)) 
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
			if (partyMember.getPet() != null)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.YOUR_SERVITOR_HAS_VANISHED);
				partyMember.getPet().unSummon(partyMember);
				partyMember.sendPacket(sm);
				return false;
			}
			int partyMemberLevel = partyMember.getLevel();
			if (raidLevel == 1)
			{
				if(partyMemberLevel < 40 && partyMemberLevel >=50)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}
			}
			else if (raidLevel == 2)
			{
				if (partyMemberLevel < 50 && partyMemberLevel >=60)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 3)
			{
				if (partyMemberLevel < 60 && partyMemberLevel >=70)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 4)
			{
				if (partyMemberLevel < 70 && partyMemberLevel >=80)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
			else if (raidLevel == 5)
			{
				if (partyMemberLevel <80)
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
					sm.addPcName(partyMember);
					player.sendPacket(sm);
					return false;
				}						
			}
		}
		return true;
	}
	/**
	 * Quests Talk Scripts
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param MissionMasterNpc 
	 **/
	public final String onFirstTalk(L2Npc npc, L2PcInstance player) 
	{
		QuestState st = player.getQuestState("MissionMap");
		if (st == null)
		{ 
			st = newQuestState(player);		
		} 
		String htmltext = "";		
		/**
		 * Mission Master
		 */
		if (npc.getNpcId() == MissionMasterNpc)
		{
			htmltext = MissionMapConfig.MissionMaster;			
		}
		return htmltext; 
	}
	/**
	 * Instance Enter Procedure
	 * @param MissionEnter
	 */
	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player) 
	{
		QuestState st = player.getQuestState("MissionMap"); 
		if (st == null)
		{
			st = newQuestState(player);
		}
		if (npc.getNpcId() == MissionMasterNpc)
		{
			/**
			 * Solo Missions
			 */
			if (event.startsWith("start")) 
			{
				int stars = 0;
				if (event.equalsIgnoreCase("start_1"))
				{
					stars = 1;
				}
				else if (event.equalsIgnoreCase("start_2"))
				{
					stars = 2;
				}
				else if (event.equalsIgnoreCase("start_3"))
				{
					stars = 3;
				}
				else if (event.equalsIgnoreCase("start_4"))
				{
					stars = 4;
				}
				else if (event.equalsIgnoreCase("start_5"))
				{
					stars = 5;
				}
				else if (event.equalsIgnoreCase("start_6"))
				{
					stars = 6;
				}
				else if (event.equalsIgnoreCase("start_7"))
				{
					stars = 7;
				}
				else if (event.equalsIgnoreCase("start_8"))
				{
					stars = 8;
				}
				if (stars == 0)
				{
					return "Error please contact with Developer or Admin";
				}
				Mission missionStars = null;
				if (player.getParty() != null)
				{
					player.sendMessage("You cannot enter here in party.");
					return MissionMapConfig.MissionMaster;
				}
				else
				{
					if (!_missionStars.isEmpty())
					{
						if (_missionStars.containsKey(player.getObjectId()))
						{							
							missionStars = _missionStars.get(player.getObjectId());
							if (missionStars.getCanEnter() == false)
							{
								player.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
								long canEnterTime = ((MissionMapConfig.SOLO_RE_ENTER_TIME*60000) - (System.currentTimeMillis() - missionStars.getEnterTime()));
								if (canEnterTime < 60000)
								{
									canEnterTime = 1;
								}
								else
								{
									canEnterTime = canEnterTime / 60000;
								}
								player.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");
								return MissionMapConfig.MissionMaster; 
							}
							else
							{									
								_missionStars.remove(missionStars);
								missionStars = new Mission(player.getObjectId(),stars);
								_missionStars.put(missionStars.getLeaderId(), missionStars);
							}
						}
					}
					if (missionStars == null) 
					{ 
						missionStars = new Mission(player.getObjectId(),stars);
						_missionStars.put(missionStars.getLeaderId(), missionStars);
					}					
					if (player.getPet() != null)
					{
						L2Summon pet = player.getPet();
						pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
						pet.setInstanceId(missionStars.getInstanceId());
						pet.teleToLocation(MissionMapConfig.MissionSoloLoc[0],MissionMapConfig.MissionSoloLoc[1],MissionMapConfig.MissionSoloLoc[2], true);
					}
					player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					player.setInstanceId(missionStars.getInstanceId());
					player.teleToLocation(MissionMapConfig.MissionSoloLoc[0],MissionMapConfig.MissionSoloLoc[1],MissionMapConfig.MissionSoloLoc[2], true);
					missionStars.initSpawn();
				}
			}
			if (event.equalsIgnoreCase("select"))
			{
				if (player.getParty() != null)
				{
					player.sendMessage("You cannot enter here in party.");
					return MissionMapConfig.MissionMaster;
				}
				else
				{
					String selecttext = "";
					selecttext += "<html><body><font color=LEVEL>Mission Master</font><br>Select your star mission:<br>";
					selecttext += "[<font color=LEVEL>SOLO</font>]<br>";
					selecttext += "Solo dungeon is for event areas. You must enter here alone, gain little of vitality."; 
					selecttext += "To gain all rewards you must kill all mobs. When you do that you teleported back.<br>";
					if (checkLeaderLevel(player) == 1)
					{
						selecttext += "<a action=\"bypass -h Quest MissionMap start_1\">Map (1-19 LvL)<br>";
					}
					else if (checkLeaderLevel(player) == 2)
					{						
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_2\">Map (20-29 LvL)<br>";
					}
					else if (checkLeaderLevel(player) == 3)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_3\">Map (30-39 LvL)<br>";
					}
					else if (checkLeaderLevel(player) == 4)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_4\">Map (40-49 LvL)</a><br>";
					}
					else if (checkLeaderLevel(player) == 5)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_5\">Map (50-59 LvL)</a><br>";
					}
					else if (checkLeaderLevel(player) == 6)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_6\">Map (60-69 LvL)</a><br>";
					}
					else if (checkLeaderLevel(player) == 7)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_7\">Map (70-79 LvL)</a><br>";
					}
					else if (checkLeaderLevel(player) == 8)
					{
						selecttext += "<br><a action=\"bypass -h Quest MissionMap start_8\">Map (80-85 LvL)</a><br>";
					}
					selecttext += "</body></html>";
					return selecttext;
				}
			}
			/**
			 * Mission Raid List
			 */
			MissionRaidMap missionRaid = null;
			MissionRaidStatus missionRaidStatus = null;
			if (event.startsWith("raid")) 
			{
				int missionRaidLevel = 0;
				if (event.equalsIgnoreCase("raid_1"))
				{
					missionRaidLevel = 1;
				}
				else if (event.equalsIgnoreCase("raid_2"))
				{
					missionRaidLevel = 2;
				}
				else if (event.equalsIgnoreCase("raid_3"))
				{
					missionRaidLevel = 3;
				}
				else if (event.equalsIgnoreCase("raid_4"))
				{
					missionRaidLevel = 4;
				}
				else if (event.equalsIgnoreCase("raid_5"))
				{
					missionRaidLevel = 5;
				}
				if (missionRaidLevel == 0)
				{
					return "Error, please contact with Developer or Administrator";
				}
				if (checkConditionsParty(player,npc,missionRaidLevel) == true)
				{
					if (!_missionRaidStatus.isEmpty())
					{
						for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
						{
							if(_missionRaidStatus.containsKey(player.getObjectId()))
							{
								missionRaidStatus = _missionRaidStatus.get(player.getObjectId());
								if (missionRaidStatus.getCanEnter() == false)
								{
									partyMember.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
									long canEnterTime = ((MissionMapConfig.RAID_RE_ENTER_TIME*60000) - (System.currentTimeMillis() - missionRaidStatus.getEnterTime()));
									if (canEnterTime < 60000)
									{
										canEnterTime = 1;
									}
									else
									{
										canEnterTime = canEnterTime / 60000;
									}
									partyMember.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");									
									player.sendMessage("PartyMember " + partyMember.getName() + " cannot enter now to this dungeon");
									return "";
								}
							}
						}
					}
					if (missionRaidStatus == null)
					{
						for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
						{
							missionRaidStatus = new MissionRaidStatus(partyMember.getObjectId());
							_missionRaidStatus.put(partyMember.getObjectId(), missionRaidStatus);
						}
					}
					if (!_missionRaid.isEmpty())
					{
						if (_missionRaid.containsKey(player.getObjectId()))
						{
							missionRaid = _missionRaid.get(player.getObjectId());							
							_missionRaid.remove(missionRaid);								
							missionRaid = new MissionRaidMap(player.getObjectId(),missionRaidLevel);
							_missionRaid.put(player.getObjectId(), missionRaid);							
						}
					}
					if (missionRaid == null) 
					{
						missionRaid = new MissionRaidMap(player.getObjectId(),missionRaidLevel);
						_missionRaid.put(missionRaid.getMissionId(), missionRaid);
					}
					for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
					{
						if (partyMember.getPet() != null)
						{
							L2Summon pet = partyMember.getPet();
							pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
							pet.setInstanceId(missionRaid.getInstanceId());
							pet.teleToLocation(MissionMapConfig.MissionRaidLoc[0], MissionMapConfig.MissionRaidLoc[1], MissionMapConfig.MissionRaidLoc[2], true);
						}
						partyMember.setInstanceId(missionRaid.getInstanceId());
						partyMember.teleToLocation(MissionMapConfig.MissionRaidLoc[0], MissionMapConfig.MissionRaidLoc[1], MissionMapConfig.MissionRaidLoc[2], true);
						missionRaidStatus = _missionRaidStatus.get(partyMember.getObjectId());
						missionRaidStatus.setEnterTime(System.currentTimeMillis());
					}
					missionRaid.initSpawn();						
				}
				else
				{
					return "";
				}
			}			
			if (event.equalsIgnoreCase("list_raid"))
			{
				if (player.getParty() == null)
				{
					player.sendMessage("You cannot enter here alone.");
				}
				else
				{
					String selecttext = "";
					selecttext += "<html><body><font color=LEVEL>Mission Master</font><br>Select raid mission:<br><br>";
					selecttext += "[<font color=LEVEL>PARTY</font>]<br>";
					selecttext += "Party dungeon is a race to raid kill. First you teleported to another dimension of Dark Omen Catacomb,"; 
					selecttext += "then you must go to enter gate of this Catacomb, there is special monster that have a lot of power so you must get a party to kill him.";
					selecttext += "An additional obstacle will be monsters in your road. <br>";
					selecttext += "<br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap raid_1\">Raid (40-49 LvL) </a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap raid_2\">Raid (50-59 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap raid_3\">Raid (60-69 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap raid_4\">Raid (70-79 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap raid_5\">Raid (80-85 LvL)</a><br>";					
					selecttext += "</body></html>";
					return selecttext;
				}
			}
			/**
			 * Mission Strategy List
			 */
			MissionStrategyMap missionStrategy = null;
			MissionStrategyStatus missionStrategyStatus = null;
			if (event.startsWith("strategy")) 
			{
				int missionStrategyLevel = 0;
				if (event.equalsIgnoreCase("strategy_1"))
				{
					missionStrategyLevel = 1;
				}
				else if (event.equalsIgnoreCase("strategy_2"))
				{
					missionStrategyLevel = 2;
				}
				else if (event.equalsIgnoreCase("strategy_3"))
				{
					missionStrategyLevel = 3;
				}
				else if (event.equalsIgnoreCase("strategy_4"))
				{
					missionStrategyLevel = 4;
				}
				else if (event.equalsIgnoreCase("strategy_5"))
				{
					missionStrategyLevel = 5;
				}
				if (missionStrategyLevel == 0)
				{
					return "Error, please contact with Developer or Administrator";
				}
				if (checkConditionsStrategy(player,npc,missionStrategyLevel) == true)
				{					
					if (!_missionStrategyStatus.isEmpty())
					{
						if(_missionStrategyStatus.containsKey(player.getClanId()))
						{
							missionStrategyStatus = _missionStrategyStatus.get(player.getClanId());
							if (missionStrategyStatus.getCanEnter() == false)
							{
								player.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
								long canEnterTime = ((MissionMapConfig.STRATEGY_RE_ENTER_TIME*60000) - (System.currentTimeMillis() - missionStrategyStatus.getEnterTime()));
								if (canEnterTime < 60000)
								{
									canEnterTime = 1;
								}
								else
								{
									canEnterTime = canEnterTime / 60000;
								}
								player.sendMessage("Your clan enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");									
								return "";
							}
						}
					}
					if (missionStrategyStatus == null)
					{
						missionStrategyStatus = new MissionStrategyStatus(player.getClanId());
						_missionStrategyStatus.put(player.getClanId(), missionStrategyStatus);						
					}
					if (!_missionStrategy.isEmpty())
					{
						if (_missionStrategy.containsKey(player.getClanId()))
						{
							missionStrategy = _missionStrategy.get(player.getClanId());							
							_missionStrategy.remove(missionStrategy);								
							missionStrategy = new MissionStrategyMap(player.getClanId(),missionStrategyLevel);
							_missionStrategy.put(player.getClanId(), missionStrategy);							
						}
					}
					if (missionStrategy == null) 
					{
						missionStrategy = new MissionStrategyMap(player.getClanId(),missionStrategyLevel);
						_missionStrategy.put(player.getClanId(), missionStrategy);
					}
					for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
					{
						if (partyMember.getPet() != null)
						{
							L2Summon pet = partyMember.getPet();
							pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
							pet.setInstanceId(missionStrategy.getInstanceId());
							pet.teleToLocation(MissionMapConfig.MissionStrategyLoc[0], MissionMapConfig.MissionStrategyLoc[1], MissionMapConfig.MissionStrategyLoc[2], true);
						}
						partyMember.setInstanceId(missionStrategy.getInstanceId());
						partyMember.teleToLocation(MissionMapConfig.MissionStrategyLoc[0], MissionMapConfig.MissionStrategyLoc[1], MissionMapConfig.MissionStrategyLoc[2], true);						
					}
					missionStrategyStatus = _missionStrategyStatus.get(player.getClanId());
					missionStrategyStatus.setEnterTime(System.currentTimeMillis());
					missionStrategy.initSpawn();						
				}
				else
				{
					return "";
				}
			}			
			if (event.equalsIgnoreCase("list_strategy"))
			{
				if (player.getParty() == null)
				{
					player.sendMessage("You cannot enter here alone.");
				}
				else
				{
					String selecttext = "";
					selecttext += "<html><body><font color=LEVEL>Mission Master</font><br>Select raid mission:<br><br>";
					selecttext += "[<font color=LEVEL>STRATEGY</font>]<br>";
					selecttext += "Strategy dungeon is a clan raid kill. First you teleported to another dimension, "; 
					selecttext += "then you must kill 4 raids in center of square.";
					selecttext += "Raid is very powerfull and give some prices like clan eggs, adena, enchants and reputation.<br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap strategy_1\">Strategy Battle (40-49 LvL) </a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap strategy_2\">Strategy Battle (50-59 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap strategy_3\">Strategy Battle (60-69 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap strategy_4\">Strategy Battle (70-79 LvL)</a><br>";
					selecttext += "<a action=\"bypass -h Quest MissionMap strategy_5\">Strategy Battle (80-85 LvL)</a><br>";
					selecttext += "</body></html>";
					return selecttext;
				}
			}			
			/**
			 * Exchange for rewards
			 */
			if (event.equalsIgnoreCase("reward"))				
			{
				String htmltext = "<html><body><font color=LEVEL>Mission Master: </font><br>";
				htmltext += "If you have Special <font color=LEVEL>Mouse Coins</font> you can obtain special prizes.<br1>";
				htmltext += "Now you can select from type a reward you like:<br>";
				htmltext += "<a action=\"bypass -h Quest MissionMap m_1\">Weapons</a><br>";
				htmltext += "<a action=\"bypass -h Quest MissionMap m_2\">Hats</a><br>";
				htmltext += "<a action=\"bypass -h Quest MissionMap m_3\">Toi Vortex Crystals</a><br>";
				htmltext += "<a action=\"bypass -h Quest MissionMap m_4\">Adventurer Soul Avatars</a><br>";
				htmltext += "</body></html>";
				return htmltext;
			}			
		}
		if (event.equalsIgnoreCase("teleportback"))
		{
			teleportBack(player);
		}
		if (event.equalsIgnoreCase("m_1"))
		{
			L2Multisell.getInstance().separateAndSend(7101001, player, npc.getNpcId(), false, 0.0);			
		}
		if (event.equalsIgnoreCase("m_2"))
		{
			L2Multisell.getInstance().separateAndSend(7101002, player, npc.getNpcId(), false, 0.0);			
		}
		if (event.equalsIgnoreCase("m_3"))
		{
			L2Multisell.getInstance().separateAndSend(7101003, player, npc.getNpcId(), false, 0.0);			
		}
		if (event.equalsIgnoreCase("m_4"))
		{
			L2Multisell.getInstance().separateAndSend(7101004, player, npc.getNpcId(), false, 0.0);			
		}		
		return super.onAdvEvent(event, npc, player); 
	}
	
	/**
	 * Killing Process
	 * @param MissionMap
	 **/ 
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		QuestState st = player.getQuestState("MissionMap");
		if(st == null)
		{
			st = newQuestState(player);
		}		
		Mission missionStars = null;
		if (player.getParty() != null)
		{			
			if (npc.getNpcId() == MissionMapConfig.Mobs[1][3] || npc.getNpcId() == MissionMapConfig.Mobs[2][3]
			              				|| npc.getNpcId() == MissionMapConfig.Mobs[3][3]
			              				|| npc.getNpcId() == MissionMapConfig.Mobs[4][3]
			              				|| npc.getNpcId() == MissionMapConfig.Mobs[5][3])
			{
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Raid);
						pst.giveAdena(Rnd.get(20000,50000), false);
						pst.addExpAndSp(MissionMapConfig.MISSION_RAID_EXP*partyMember.getLevel(), MissionMapConfig.MISSION_RAID_SP*partyMember.getLevel());
						teleportBack(partyMember);
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_1[1][3])
			{
				StrategySpawn(npc.getInstanceId(),1,2);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_C,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_C,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_C, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_1[2][3])
			{
				StrategySpawn(npc.getInstanceId(),2,2);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_B,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_B,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_B, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_1[3][3])
			{
				StrategySpawn(npc.getInstanceId(),3,2);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_A,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_A,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_A, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_1[4][3])
			{
				StrategySpawn(npc.getInstanceId(),4,2);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_1[5][3])
			{
				StrategySpawn(npc.getInstanceId(),5,2);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_2[1][3])
			{
				StrategySpawn(npc.getInstanceId(),1,3);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_C,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_C,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_C, 1);
						}
					}
				}	
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_2[2][3])
			{
				StrategySpawn(npc.getInstanceId(),2,3);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_B,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_B,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_B, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_2[3][3])
			{
				StrategySpawn(npc.getInstanceId(),3,3);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_A,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_A,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_A, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_2[4][3])
			{
				StrategySpawn(npc.getInstanceId(),4,3);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_2[5][3])
			{
				StrategySpawn(npc.getInstanceId(),5,3);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_3[1][3])
			{
				StrategySpawn(npc.getInstanceId(),1,4);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_C,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_C,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_C, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_3[2][3])
			{
				StrategySpawn(npc.getInstanceId(),2,4);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_B,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_B,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_B, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_3[3][3])
			{
				StrategySpawn(npc.getInstanceId(),3,4);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_A,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_A,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_A, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_3[4][3])
			{
				StrategySpawn(npc.getInstanceId(),4,4);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_3[5][3])
			{
				StrategySpawn(npc.getInstanceId(),5,4);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
						pst.giveAdena(Rnd.get(200000,500000), false);
						pst.addExpAndSp(MissionMapConfig.Strategy_Exp, MissionMapConfig.Strategy_Sp);
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_4[1][3])
			{
				exitStrategyMode(player);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_C,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_C,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_C, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_4[2][3])
			{
				exitStrategyMode(player);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_B,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_B,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_B, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_4[3][3])
			{
				exitStrategyMode(player);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_A,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_A,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_A, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_4[4][3])
			{
				exitStrategyMode(player);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else if (npc.getNpcId() == MissionMapConfig.StrategyMobs_4[5][3])
			{
				exitStrategyMode(player);
				for (L2PcInstance partyMember: player.getParty().getPartyMembers())
				{
					QuestState pst = partyMember.getQuestState("MissionMap");
					if (pst == null)
					{
						pst = newQuestState(partyMember);
					}
					if (partyMember.getInstanceId() != 0)
					{
						if (partyMember.getClan() != null)
						{
							if (partyMember.isClanLeader())
							{
								ClanTable.getInstance().getClan(partyMember.getClanId()).addReputationScore(350, true);
								if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
								{
									pst.giveItems(Rnd.get(9814,9818), 3);
								}
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Chance)
						{
							if (Rnd.get(2) < 2)
							{
								pst.giveItems(MissionMapConfig.Enchant_Armor_S,2);
							}
							else
							{
								pst.giveItems(MissionMapConfig.Enchant_Weapon_S,2);
							}
						}
						if (Rnd.get(100) < MissionMapConfig.Strategy_Low_Chance)
						{
							pst.giveItems(MissionMapConfig.Top_Life_Stone_S, 1);
						}
					}
				}
			}
			else
			{				
				if (Rnd.get(100) < 50)
				{
					st.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward);
				}
				try
				{
					player.setVitalityPoints(player.getVitalityPoints() + MissionMapConfig.MISSION_SOLO_VIT[8], false);
				}
				catch (Exception e)
				{					
				}
			}
		}
		else
		{			
			try 
			{
				missionStars = _missionStars.get(player.getObjectId());			
				player.setVitalityPoints(player.getVitalityPoints() + MissionMapConfig.MISSION_SOLO_VIT[missionStars.getMissionStars()], false);
				if (Rnd.get(100) < 50)
				{
					st.giveItems(MissionMapConfig.Special_Coin, 1);
				}
			}
			catch (Exception e)
			{				
			}
		}
		return super.onKill(npc, player, isPet);	  
	}
	
	private void StrategySpawn(int instanceId,int missionLevel,int stage) 
	{
		int[][] templateGroup;
		switch(stage)
		{
			case 1:
				templateGroup = MissionMapConfig.StrategyMobs_1;
				break;
			case 2:
				templateGroup = MissionMapConfig.StrategyMobs_2;
				break;
			case 3:
				templateGroup = MissionMapConfig.StrategyMobs_3;
				break;
			case 4:
				templateGroup = MissionMapConfig.StrategyMobs_4;
				break;
			default:
				templateGroup = MissionMapConfig.StrategyMobs_1;
				break;
		}		
		try 
		{
			L2NpcTemplate template = NpcTable.getInstance().getTemplate(templateGroup[missionLevel][3]);
			L2Spawn spawnDat;
			if (template != null) 
			{
				spawnDat = new L2Spawn(template);
				spawnDat.setAmount(1);
				spawnDat.setLocx(MissionMapConfig.raidStrategyLoc[0]);
				spawnDat.setLocy(MissionMapConfig.raidStrategyLoc[1]);
				spawnDat.setLocz(MissionMapConfig.raidStrategyLoc[2]);
				spawnDat.setHeading(0);
				spawnDat.setInstanceId(instanceId);
				spawnDat.setRespawnDelay(0);
				SpawnTable.getInstance().addNewSpawn(spawnDat, false);
				spawnDat.doSpawn();	
				spawnDat.stopRespawn();
			} 
			else 
			{
				_log.warning("MissionStrategy: Data missing in NPC table for ID: " + 0);
			}
		} 
		catch (Exception e) 
		{
			_log.warning("MissionStrategy: Spawns could not be initialized: "	+ e);
		}
	}
	
	private void exitStrategyMode(L2PcInstance player)
	{
		try
		{
			for (L2PcInstance partyMember: player.getParty().getPartyMembers())
			{
				QuestState pst = partyMember.getQuestState("MissionMap");
				if (pst == null)
				{
					pst = newQuestState(partyMember);
				}
				if (partyMember.getInstanceId() != 0)
				{
					pst.giveItems(MissionMapConfig.Special_Coin, MissionMapConfig.Coin_Reward_Strategy);
					pst.giveAdena(Rnd.get(200000,500000), false);
					pst.addExpAndSp(1500000, 750000);
					teleportBack(partyMember);
				}
			}
		}
		catch(Exception e)
		{
			teleportBack(player);
		}
	}
	
	/**
	 * TeleportBack command
	 * @param player
	 */
	private void teleportBack(L2PcInstance player) 
	{
		player.teleToLocation(MissionMapConfig.GiranLoc[0],MissionMapConfig.GiranLoc[1],MissionMapConfig.GiranLoc[2]);
		player.setInstanceId(0);
		SkillTable.getInstance().getInfo(2580, 1).getEffects(player, player);		
	}

	/**
	 * Instance Load Procedure
	 * @param questId
	 * @param name
	 * @param descr
	 */
	private MissionMap(int questId, String name, String descr) 
	{
		super(questId, name, descr);
		int[] mobs = 
		{ 
				MissionMapConfig.Mission_Monsters[0][1], MissionMapConfig.Mission_Monsters[1][1], MissionMapConfig.Mission_Monsters[2][1],
				MissionMapConfig.Mission_Monsters[0][2], MissionMapConfig.Mission_Monsters[1][2], MissionMapConfig.Mission_Monsters[2][2],
				MissionMapConfig.Mission_Monsters[0][3], MissionMapConfig.Mission_Monsters[1][3], MissionMapConfig.Mission_Monsters[2][3],
				MissionMapConfig.Mission_Monsters[0][4], MissionMapConfig.Mission_Monsters[1][4], MissionMapConfig.Mission_Monsters[2][4],
				MissionMapConfig.Mission_Monsters[0][5], MissionMapConfig.Mission_Monsters[1][5], MissionMapConfig.Mission_Monsters[2][5],
				MissionMapConfig.Mission_Monsters[0][6], MissionMapConfig.Mission_Monsters[1][6], MissionMapConfig.Mission_Monsters[2][6],
				MissionMapConfig.Mission_Monsters[0][7], MissionMapConfig.Mission_Monsters[1][7], MissionMapConfig.Mission_Monsters[2][7],
				MissionMapConfig.Mission_Monsters[0][8], MissionMapConfig.Mission_Monsters[1][8], MissionMapConfig.Mission_Monsters[2][8],
		};
		int[] raidMobs =
		{
			MissionMapConfig.Mobs[1][0],MissionMapConfig.Mobs[1][1],MissionMapConfig.Mobs[1][2],MissionMapConfig.Mobs[1][3],
			MissionMapConfig.Mobs[2][0],MissionMapConfig.Mobs[2][1],MissionMapConfig.Mobs[2][2],MissionMapConfig.Mobs[2][3],
			MissionMapConfig.Mobs[3][0],MissionMapConfig.Mobs[3][1],MissionMapConfig.Mobs[3][2],MissionMapConfig.Mobs[3][3],
			MissionMapConfig.Mobs[4][0],MissionMapConfig.Mobs[4][1],MissionMapConfig.Mobs[4][2],MissionMapConfig.Mobs[4][3],
			MissionMapConfig.Mobs[5][0],MissionMapConfig.Mobs[5][1],MissionMapConfig.Mobs[5][2],MissionMapConfig.Mobs[5][3],
		};
		int[] StrategyRb = 
		{ 
				MissionMapConfig.StrategyMobs_1[1][3], MissionMapConfig.StrategyMobs_2[1][3], MissionMapConfig.StrategyMobs_3[1][3], MissionMapConfig.StrategyMobs_4[1][3], 
				MissionMapConfig.StrategyMobs_1[2][3], MissionMapConfig.StrategyMobs_2[2][3], MissionMapConfig.StrategyMobs_3[2][3], MissionMapConfig.StrategyMobs_4[2][3],
				MissionMapConfig.StrategyMobs_1[3][3], MissionMapConfig.StrategyMobs_2[3][3], MissionMapConfig.StrategyMobs_3[3][3], MissionMapConfig.StrategyMobs_4[3][3],
				MissionMapConfig.StrategyMobs_1[4][3], MissionMapConfig.StrategyMobs_2[4][3], MissionMapConfig.StrategyMobs_3[4][3], MissionMapConfig.StrategyMobs_4[4][3],
				MissionMapConfig.StrategyMobs_1[5][3], MissionMapConfig.StrategyMobs_2[5][3], MissionMapConfig.StrategyMobs_3[5][3], MissionMapConfig.StrategyMobs_4[5][3]
		};
		for (int monsterId : raidMobs)
			addKillId(monsterId);
		for (int monsterId : StrategyRb)
			addKillId(monsterId);
		addStartNpc(MissionMasterNpc);
		addTalkId(MissionMasterNpc);
		addFirstTalkId(MissionMasterNpc);		
		for (int npcId : mobs)
			addKillId(npcId);		
	}	
	public static void main(String[] args) 
	{ 
		new MissionMap(-1,"MissionMap", "instances");
		_log.warning("MissionMap: Solo 8 Instances Loaded ");
		_log.warning("MissionMap: Raid 5 Instances Loaded ");
		_log.warning("MissionMap: Strategy 5 Instances Loaded ");
	}
}