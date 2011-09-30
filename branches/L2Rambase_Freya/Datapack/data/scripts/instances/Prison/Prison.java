package instances.Prison;

import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.datatables.SpawnTable;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.QuestManager;
import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.L2Spawn;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.entity.Fort;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.model.quest.State;
import l2.rambase.gameserver.model.quest.jython.QuestJython;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;

/**
 * 
 * @author L2Rambase Project
 * @author Deyoun
 * @version 2.1
 * 
 */
public class Prison extends QuestJython 
{
	/**
	 * Text of Wardens
	 */
	private static final String fortwarden = "<html><font color=LEVEL>Detention Camp Warden: </font><br><br>Wait! There is beast and monsters on our Prison. If you want you can join to fight.First you must get a minimum 2 members or maximum 6 members in party, then you must kill 4 Prison Bosses. <br><br>...<br><br>I also need your help so when you have time please talk with me.<br><br><br><a action=\"bypass -h Quest PrisonDungeon start\">Enter into prison ground</a><br><br><a action=\"bypass -h Quest PrisonDungeon exchange\">Quest</a></body></html>";
	private static final String castlewarden = "<html><font color=LEVEL>Warden: </font><br><br>Wait! There is beast and monsters on our Prison. If you want you can join to fight.I also need your help so when you have time please talk with me.<br><br><br><a action=\"bypass -h Quest PrisonDungeon start\">Enter into prison ground</a><br><br><a action=\"bypass -h Quest PrisonDungeon exchange\">Quest</a></body></html>";
	/**
	 * FastMaps Prisons and cords
	 */
	private final FastMap<Integer, FortressPrison> _fprisons = new FastMap<Integer, FortressPrison>();
	private final FastMap<Integer, CastlePrison> _cprisons = new FastMap<Integer, CastlePrison>();
	/**
	 * Warden List
	 */
	private static final int[] FortWardens = {
			35666, 35698, 35735, 35767, 35804, 35835, 
			35867, 35904, 35936, 35974, 36011, 36043, 
			36081, 36118, 36149, 36181, 36219, 36257,
			36294, 36326, 36364 
			};
	private static final int[] CastleWardens = { 
			36403, 36404, 36405, 36406, 36407, 36408, 
			36409, 36410, 36411
			};
	/**
	 * Rewards List
	 * @param Castle
	 * @param Fortress
	 * Configuration Options
	 */
	private static final int FORT_1_VIT = 1000;
	private static final int FORT_2_VIT = 1250;
	private static final int FORT_3_VIT = 1500;
	private static final int FORT_4_VIT = 1750;
	private static final int FORT_1_EXP = 2500000;
	private static final int FORT_2_EXP = 3000000;
	private static final int FORT_3_EXP = 3500000;
	private static final int FORT_4_EXP = 4500000;
	private static final int FORT_1_SP = 25000;
	private static final int FORT_2_SP = 35000;
	private static final int FORT_3_SP = 45000;
	private static final int FORT_4_SP = 50000;
	private static final int CASTLE_1_VIT = 1250;
	private static final int CASTLE_2_VIT = 1500;
	private static final int CASTLE_3_VIT = 1750;
	private static final int CASTLE_4_VIT = 2000;
	private static final int CASTLE_1_EXP = 5000000;
	private static final int CASTLE_2_EXP = 5500000;
	private static final int CASTLE_3_EXP = 6000000;
	private static final int CASTLE_4_EXP = 6500000;
	private static final int CASTLE_1_SP = 25000;
	private static final int CASTLE_2_SP = 35000;
	private static final int CASTLE_3_SP = 45000;
	private static final int CASTLE_4_SP = 50000;
	/**
	 * Location List
	 */
	private static final int[] FortPrisonLoc = { 53300, 246052, -6585 };
	private static final int[] CastlePrisonLoc = { 11740, -49148, -3000 };
	private static final int[] GiranLoc = { 83385,148626,-3400 };
	/**
	 * List of Monsters 60-65 
	 * HAGER_THE_OUTLAW - lvl 61 
	 * ALL_SEEING_RANGO - lvl 62
	 * JAKARD - lvl 63 
	 * GILLIEN- lvl 65
	 * 
	 * Datapack Stats Fixed
	 */
	private static final int HAGER_THE_OUTLAW = 25572;
	private static final int ALL_SEEING_RANGO = 25575;
	private static final int JAKARD = 25578;
	private static final int HELSING = 25579;
	/**
	 * List of Monsters 65-70 
	 * GILLIEN - lvl 65 
	 * MEDICI - lvl 66 
	 * IMMORTAL_MUUS - lvl 67 
	 * BRAND_THE_EXILE - lvl 68
	 * COMANDER_KOENIG - lvl 69
	 * 
	 * Datapack Stats Fixed
	 */
	private static final int GILLIEN = 25582;
	private static final int MEDICI = 25585;
	private static final int IMMORTAL_MUUS = 25588;
	private static final int BRAND_THE_EXILE = 25589;
	private static final int COMANDER_KOENIG = 25592;
	/**
	 * List of Monsters 70-75 
	 * GERG - lvl 70
	 * RHIANNA_THE_TRAITOR - lvl 71 
	 * TESLA_THE_DECEIVER - lvl 72 
	 * SOUL_HUNTER_CHAKUNDEL - lvl 73 
	 * DURANGO_THE_CRUSHER - lvl 74
	 * 
	 * Datapack Stats Fixed
	 */
	private static final int GERG = 25593;
	private static final int RHIANNA_THE_TRAITOR = 25546;
	private static final int TESLA_THE_DECEIVER = 25549;
	private static final int SOUL_HUNTER_CHAKUNDEL = 25552;
	private static final int DURANGO_THE_CRUSHER = 25553;
	/**
	 * List of Monsters 75-80 
	 * BRUTUS_THE_OBSTINATE - lvl 75 
	 * RANGER_KARANKAWA - lvl 76 
	 * SARGON_THE_MAD - lvl 77 
	 * BEATIFUL_ATRIELLE - lvl 78
	 * NAGEN_THE_TOMBOY - lvl 79 
	 * JAX_THE_DESTROYER - lvl 80
	 * 
	 * Datapack Stats Fixed
	 */
	private static final int BRUTUS_THE_OBSTINATE = 25554;
	private static final int RANGER_KARANKAWA = 25557;
	private static final int SARGON_THE_MAD = 25560;
	private static final int BEATIFUL_ATRIELLE = 25563;
	private static final int NAGEN_THE_TOMBOY = 25566;
	private static final int JAX_THE_DESTROYER = 25569;
	/**
	 * List of DungeonBosses
	 */
	protected static final int [] _FortressState1	= { HAGER_THE_OUTLAW,JAKARD };
	protected static final int [] _FortressState2	= { GILLIEN,COMANDER_KOENIG};
	protected static final int [] _FortressState3	= { RHIANNA_THE_TRAITOR, GERG};
	protected static final int [] _FortressState4	= { BRUTUS_THE_OBSTINATE,SARGON_THE_MAD,NAGEN_THE_TOMBOY};
	protected static final int [] _CastleState1	= { ALL_SEEING_RANGO,HELSING};
	protected static final int [] _CastleState2	= { MEDICI,BRAND_THE_EXILE,IMMORTAL_MUUS};
	protected static final int [] _CastleState3	= { TESLA_THE_DECEIVER,DURANGO_THE_CRUSHER,SOUL_HUNTER_CHAKUNDEL,};
	protected static final int [] _CastleState4	= { RANGER_KARANKAWA,BEATIFUL_ATRIELLE,JAX_THE_DESTROYER};
	/**
	 * Config
	 * @param MAX_LEVEL_DIFERENCE - exp,sp,vitality
	 * @param SPAWN_TIME - time of dungeon boss spawn 1 min
	 * @param RE_ENTER_TIME - time to next enter 4h
	 */
	private static final int MAX_LEVEL_DIFFERENCE = 6;
	private static final int SPAWN_TIME = 30000;
	// changed from 2-6
	private static final int MIN_PARTY_MEMBER_COUNT = 2;
	private static final int MAX_PARTY_MEMBER_COUNT = 6;
	private final boolean debug = false;
	// -- 14400000;
	private static final int RE_ENTER_TIME = 14400000;
	/*
	 * Items List
	 */
	private static final int Dungeon_Leader_Mark = 9797;
	private static final int Dungeon_Leader_Mark_Part = 9798;
	private static final int Knights_Epaulettes = 9912;
	/**
	 * Special Created Maps
	 * 
	 * @param FortressPrison
	 * @param CastlePrison
	 **/
	
	private class FortressPrison 
	{		
		private int _fortId;
		private int _instanceId;
		private int _state;
		private boolean _canEnter;
		private long _enterTime;

		private final int STATE_1 = 1;
		private final int STATE_2 = 2;
		private final int STATE_3 = 3;
		private final int STATE_4 = 4;

		private class FortressReEntrenceTimerTask implements Runnable 
		{
			public void run()
			{
				_canEnter = true;
			}
		}

		private class FortressPrisonSpawnTask implements Runnable 
		{
			public void run() 
			{
				switch (getState()) 
				{
					case STATE_1:
						FortressSpawn(_FortressState1[Rnd.get(0,1)]);
						break;
					case STATE_2:
						FortressSpawn(_FortressState2[Rnd.get(0,1)]);
						break;
					case STATE_3:
						FortressSpawn(_FortressState3[Rnd.get(0,1)]);
						break;					
					case STATE_4:
						FortressSpawn(_FortressState4[Rnd.get(0,2)]);
						break;					
					default:
						break;
				}
			}
		}

		private FortressPrison(int id) 
		{
			try 
			{
				_fortId = id;
				_state = STATE_1;
				_canEnter = false;
				_enterTime = 0;
				_instanceId = InstanceManager.getInstance().createDynamicInstance("FPrison.xml");
				ThreadPoolManager.getInstance().scheduleGeneral(new FortressReEntrenceTimerTask(), RE_ENTER_TIME);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}

		private void FortressSpawn(int npcId) 
		{
			try 
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(npcId);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(53325);
					spawnDat.setLocy(246164);
					spawnDat.setLocz(-6576);
					spawnDat.setHeading(0);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(15);
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();
					spawnDat.stopRespawn();
				} 
				else 
				{
					_log.warning("PrisonDungeon: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("PrisonDungeon: Spawns could not be initialized: "	+ e);
			}
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new FortressPrisonSpawnTask(), SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}
		
		private int getFortId() 
		{
			return _fortId;
		}

		private int getState() 
		{
			return _state;
		}

		private void setState(int val) 
		{
			_state = val;
		}
		
		private long getEnterTime() 
		{
			return _enterTime;
		}

		private void setEnterTime(long val) 
		{
			_enterTime = val;
		}

		private boolean getCanEnter() 
		{
			return _canEnter;
		}
	}

	private class CastlePrison 
	{
		private int _castleId;
		private int _instanceId;
		private int _state;
		private boolean _canEnter;
		private long _enterTime;

		private final int STATE_1 = 1;
		private final int STATE_2 = 2;
		private final int STATE_3 = 3;
		private final int STATE_4 = 4;

		private class CastleReEntrenceTimerTask implements Runnable 
		{
			public void run() 
			{
				_canEnter = true;
			}
		}

		private class CastlePrisonSpawnTask implements Runnable 
		{
			public void run() 
			{
				switch (getCastleState()) 
				{
					case STATE_1:
						CastleSpawn(_CastleState1[Rnd.get(0,1)]);
						break;
					case STATE_2:
						CastleSpawn(_CastleState2[Rnd.get(0,2)]);
						break;
					case STATE_3:
						CastleSpawn(_CastleState3[Rnd.get(0,2)]);
						break;					
					case STATE_4:
						CastleSpawn(_CastleState4[Rnd.get(0,2)]);
						break;					
					default:
						break;
				}
			}
		}

		private CastlePrison(int id) 
		{
			try 
			{
				_castleId = id;
				_state = STATE_1;
				_canEnter = false;
				_enterTime = 0;
				_instanceId = InstanceManager.getInstance().createDynamicInstance("CPrison.xml");
				ThreadPoolManager.getInstance().scheduleGeneral(new CastleReEntrenceTimerTask(), RE_ENTER_TIME);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}

		private void CastleSpawn(int npcId) 
		{
			try {
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(npcId);
				L2Spawn spawnDat;
				if (template != null) 
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setLocx(11740);
					spawnDat.setLocy(-49148);
					spawnDat.setLocz(-3000);
					spawnDat.setHeading(0);
					spawnDat.setInstanceId(getInstanceId());
					spawnDat.setRespawnDelay(15);
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					spawnDat.doSpawn();
					spawnDat.stopRespawn();
				} 
				else 
				{
					_log.warning("PrisonDungeon: Data missing in NPC table for ID: " + 0);
				}
			} 
			catch (Exception e) 
			{
				_log.warning("PrisonDungeon: Spawns could not be initialized: "	+ e);
			}
		}

		private void initSpawn() 
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new CastlePrisonSpawnTask(), SPAWN_TIME);
		}

		private int getInstanceId() 
		{
			return _instanceId;
		}

		private int getCastleId() 
		{
			return _castleId;
		}

		private int getCastleState() 
		{
			return _state;
		}

		private void setState(int val) 
		{
			_state = val;
		}
		
		private long getEnterTime() 
		{
			return _enterTime;
		}

		private void setEnterTime(long l) 
		{
			_enterTime = l;
		}

		private boolean getCanEnter() 
		{
			return _canEnter;
		}
	}

	/**
	 * Check Conditions to Party Players
	 * @param party leader
	 */
	private boolean checkConditions(L2PcInstance player, L2Npc npc) 
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
		if (party.getMemberCount() > MAX_PARTY_MEMBER_COUNT || party.getMemberCount() < MIN_PARTY_MEMBER_COUNT)
		{
			player.sendPacket(new SystemMessage(SystemMessageId.PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER));
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers()) 
		{
			if (partyMember.getLevel() < 60) 
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
			if (!partyMember.isInsideRadius(npc, 500, false, false)) 
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				return false;
			}
			if (player.getClanId() != partyMember.getClanId())
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED);
				sm.addPcName(partyMember);
				player.sendPacket(sm);
				player.sendMessage("Player " + partyMember.getName() + " is not clan member.");
				return false;
			}
			if (partyMember.getPet() != null)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.YOUR_SERVITOR_HAS_VANISHED);
				partyMember.getPet().unSummon(partyMember);
				partyMember.sendPacket(sm);
				return false;
			}
		}
		return true;
	}
	/**
	 * First Talk Script
	 * @param Castle Wardens
	 * @param Fortress Wardens
	 **/
	public final String onFirstTalk(L2Npc npc, L2PcInstance player) 
	{
		QuestState st = player.getQuestState("PrisonDungeon"); 
		if (st == null)
		{ 
			st = newQuestState(player); 
		} 
		String htmltext = "";
		/**
		 * Castle First Talk
		 */
		for (int npcId : CastleWardens)
		{
			if (npcId == npc.getNpcId())
			{
				Castle clancastle = CastleManager.getInstance().getCastle(npc);
				if (clancastle.getOwnerId() == player.getClanId())
				{
					htmltext = castlewarden;
				}
				else
				{
					htmltext = "<html><body>Who are you? I don't know you .... </body></html>";
				}
			}		
		}
		/**
		 * Fortress First Talk
		 */
		for (int npcId : FortWardens)
		{
			if (npcId == npc.getNpcId())
			{
				Fort clanfort = FortManager.getInstance().getFort(npc);
				if (clanfort.getOwnerClan() == player.getClan())
				{
					htmltext = fortwarden;
				}
				else
				{
					htmltext = "<html><body>Who are you? I don't know you .... </body></html>";
				}
			}
		}
		return htmltext; 
	}
	/**
	 * Instance Enter Procedure
	 * @param Castle
	 * @param Fortress
	 */
	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player) 
	{
		QuestState st = player.getQuestState("PrisonDungeon"); 
		if (st == null)
		{
			st = newQuestState(player);
		}
		/**
		 * Castle
		 **/
		for (int npcId : CastleWardens)
		{
			if (npcId == npc.getNpcId())
			{
				CastlePrison cprison = null;
				Castle castle = CastleManager.getInstance().getCastleByOwner(player.getClan());				
				if (event.equalsIgnoreCase("start")) 
				{
					if (checkConditions(player,npc) == true)
					{
						if (!_cprisons.isEmpty())
						{
							if (_cprisons.containsKey(castle.getCastleId()))
							{
								cprison = _cprisons.get(castle.getCastleId());
								if (cprison.getCanEnter() == false)
								{
									player.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
									long canEnterTime = (RE_ENTER_TIME - (System.currentTimeMillis() - cprison.getEnterTime()));
									if (canEnterTime < 60000)
									{
										canEnterTime = 1;
									}
									else
									{
										canEnterTime = canEnterTime / 60000;
									}
									player.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");									
									return castlewarden; 
								}
								else
								{									
									_cprisons.remove(cprison);
									cprison = new CastlePrison(castle.getCastleId());
									_cprisons.put(cprison.getCastleId(), cprison);
								}
							}
						}
						if (cprison == null) 
						{ 
							cprison = new CastlePrison(castle.getCastleId());
							_cprisons.put(cprison.getCastleId(), cprison);
						}							
						for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
						{
							if (partyMember.getPet() != null)
							{
								L2Summon pet = partyMember.getPet();
								pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
								pet.setInstanceId(cprison.getInstanceId());
								pet.teleToLocation(CastlePrisonLoc[0],CastlePrisonLoc[1],CastlePrisonLoc[2], true);
							}
							partyMember.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
							partyMember.setInstanceId(cprison.getInstanceId());
							partyMember.teleToLocation(CastlePrisonLoc[0],CastlePrisonLoc[1],CastlePrisonLoc[2], true);
						}
						if (debug)
						{
							player.sendMessage("cprison == " + String.valueOf(cprison));
							player.sendMessage("cprisons == " + String.valueOf(_cprisons));
						}
						cprison.setEnterTime(System.currentTimeMillis());
						cprison.initSpawn();						
					}
					else
					{
						return castlewarden;
					}
				}
			}
		}
		/**
		 * Fortress
		 */
		for (int npcId : FortWardens)
		{
			if (npcId == npc.getNpcId())
			{
				FortressPrison fprison = null;
				Fort fort = FortManager.getInstance().getFortByOwner(player.getClan());
				if (event.equalsIgnoreCase("start")) 
				{
					if (checkConditions(player,npc) == true)
					{
						if (!_fprisons.isEmpty())
						{
							if (_fprisons.containsKey(fort.getFortId()))
							{
								fprison = _fprisons.get(fort.getFortId());
								if (fprison.getCanEnter() == false)
								{
									long canEnterTime = (RE_ENTER_TIME - (System.currentTimeMillis() - fprison.getEnterTime()));
									if (canEnterTime < 60000)
									{
										canEnterTime = 1;
									}
									else
									{
										canEnterTime = canEnterTime / 60000;
									}
									player.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");
									return fortwarden; 
								} 
								else
								{
									_fprisons.remove(fprison);
									fprison = new FortressPrison(fort.getFortId());
									_fprisons.put(fprison.getFortId(), fprison);
								}
							}
						}
						if (fprison == null) 
						{
							fprison = new FortressPrison(fort.getFortId());
							_fprisons.put(fprison.getFortId(), fprison);
						}
						for (L2PcInstance partyMember : player.getParty().getPartyMembers()) 
						{
							if (partyMember.getPet() != null)
							{
								L2Summon pet = partyMember.getPet();
								pet.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
								pet.setInstanceId(fprison.getInstanceId());
								pet.teleToLocation(FortPrisonLoc[0], FortPrisonLoc[1], FortPrisonLoc[2], true);
							}
							partyMember.setInstanceId(fprison.getInstanceId());
							partyMember.teleToLocation(FortPrisonLoc[0], FortPrisonLoc[1], FortPrisonLoc[2], true);
						}
						if (debug)
						{
							player.sendMessage("fprison == " + String.valueOf(fprison));
							player.sendMessage("fprisons == " + String.valueOf(_fprisons));
						}
						fprison.setEnterTime(System.currentTimeMillis());
						fprison.initSpawn();						
					}
					else
					{
						return fortwarden;
					}
				}
			}
		}
		if (event.equalsIgnoreCase("exchange"))
		{
			QuestState awl = player.getQuestState("AwlUnderFoot");
			if (awl == null)
		    {
				awl = QuestManager.getInstance().getQuest("AwlUnderFoot").newQuestState(player);
				awl.isCreated();
		    }
			String htmltext = "";
			long countMark = (100 * st.getQuestItemsCount(Dungeon_Leader_Mark));
			long countMarkPart = st.getQuestItemsCount(Dungeon_Leader_Mark_Part);
			if (countMark > 0 || countMarkPart > 0)
			{
				if (countMark > 0)
				{
					st.takeItems(Dungeon_Leader_Mark, -1);
					st.giveItems(Knights_Epaulettes, countMark);					
				}
				if (countMarkPart >0)
				{
					st.takeItems(Dungeon_Leader_Mark_Part, -1);
					st.giveItems(Knights_Epaulettes, countMarkPart);					
				}
				for (int npcId : FortWardens)
				{
					if (npc.getNpcId() == npcId)
					{
						htmltext = "<html><html><body><font color=LEVEL>Detention Camp Warden: </font><br><br>Thanks for help me here your Knights Epaulettes.</body></html>";
					}
				}
				for (int npcId : CastleWardens)
				{
					if (npc.getNpcId() == npcId)
					{
						htmltext = "<html><html><body><font color=LEVEL>Warden: </font><br><br>Thanks for help me here your Knights Epaulettes.</body></html>";
					}
				}
				awl.exitQuest(true);
				return htmltext;
			}
			else
			{
				for (int npcId : FortWardens)
				{
					if (npc.getNpcId() == npcId)
					{
						htmltext = "<html><html><body><font color=LEVEL>Detention Camp Warden: </font><br><br>You want help me? Nice. I need more that one time help!!<br> You must get a <font color=LEVEL> Dungeon Leader Mark's </font> by killing a bosses in prison.<br>When you finish job please... back to me and i give you prize for that.<br><br></body></html>";
					}
					awl.setState(State.STARTED);
				}
				for (int npcId : CastleWardens)
				{
					if (npc.getNpcId() == npcId)
					{
						htmltext = "<html><html><body><font color=LEVEL>Warden: </font><br><br>You want help me? Nice. I need more that one time help!!<br> You must get a <font color=LEVEL> Dungeon Leader Mark's </font> by killing a bosses in prison.<br>When you finish job please... back to me and i give you prize for that.<br><br></body></html>";
					}
					awl.setState(State.STARTED);
				}
				return htmltext;
			}	
		}
		return super.onAdvEvent(event, npc, player); 
	}
	/**
	 * Killing Process
	 * @param Fortress
	 * @param Castle
	 **/ 
	public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		for (int npcId : _FortressState1)
		{
			if (npcId == npc.getNpcId())
			{
				FortressPrison fprison = null;
				L2Party party = player.getParty();
				Fort fort = FortManager.getInstance().getFortByOwner(player.getClan());
				fprison = _fprisons.get(fort.getFortId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Fort Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + FORT_1_VIT, true);
							partyMember.addExpAndSp(FORT_1_EXP, FORT_1_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
				}
				fprison.setState(2);
				fprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison : " + String.valueOf(_fprisons));
					player.sendMessage("Prison State: " + String.valueOf(fprison.getState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(fprison.getInstanceId()));
				}							
			}
		}
		for (int npcId : _FortressState2)
		{
			if (npcId == npc.getNpcId())
			{
				FortressPrison fprison = null;
				L2Party party = player.getParty();
				Fort fort = FortManager.getInstance().getFortByOwner(player.getClan());
				fprison = _fprisons.get(fort.getFortId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Fort Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + FORT_2_VIT, true);
							partyMember.addExpAndSp(FORT_2_EXP, FORT_2_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
				}
				fprison.setState(3);
				fprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison : " + String.valueOf(_fprisons));
					player.sendMessage("Prison State: " + String.valueOf(fprison.getState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(fprison.getInstanceId()));
				}								
			}
		}
		for (int npcId : _FortressState3)
		{
			if (npcId == npc.getNpcId())
			{
				FortressPrison fprison = null;
				L2Party party = player.getParty();
				Fort fort = FortManager.getInstance().getFortByOwner(player.getClan());
				fprison = _fprisons.get(fort.getFortId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Fort Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + FORT_3_VIT, true);
							partyMember.addExpAndSp(FORT_3_EXP, FORT_3_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
				}
				fprison.setState(4);
				fprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison : " + String.valueOf(_fprisons));
					player.sendMessage("Prison State: " + String.valueOf(fprison.getState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(fprison.getInstanceId()));
				}								
			}
		}
		for (int npcId : _FortressState4)
		{
			if (npcId == npc.getNpcId())
			{
				FortressPrison fprison = null;
				L2Party party = player.getParty();
				Fort fort = FortManager.getInstance().getFortByOwner(player.getClan());
				fprison = _fprisons.get(fort.getFortId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Fort Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == fprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + FORT_4_VIT, true);
							partyMember.addExpAndSp(FORT_4_EXP, FORT_4_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					Fort fortOwner = FortManager.getInstance().getFortByOwner(partyMember.getClan());
					partyMember.teleToLocation(fortOwner.getFortZone().getSpawnLoc().getX(),fortOwner.getFortZone().getSpawnLoc().getY(), fortOwner.getFortZone().getSpawnLoc().getZ());
					L2Summon pet = partyMember.getPet();						
					if (debug)
					{
						player.sendMessage("Prison : " + String.valueOf(_fprisons));
						player.sendMessage("Prison State: " + String.valueOf(fprison.getState()));
						player.sendMessage("Prison instance Id: " + String.valueOf(fprison.getInstanceId()));
					}
					if (pet != null)
					{							
						pet.teleToLocation(fortOwner.getFortZone().getSpawnLoc().getX(),fortOwner.getFortZone().getSpawnLoc().getY(), fortOwner.getFortZone().getSpawnLoc().getZ());
						pet.setInstanceId(0);
					}
					fprison.setState(1);
					partyMember.setInstanceId(0);					
				}								
			}			
		}
		/**
		 * Castle
		 **/
		for (int npcId : _CastleState1)
		{
			if (npcId == npc.getNpcId())
			{
				CastlePrison cprison = null;
				L2Party party = player.getParty();
				Castle castle = CastleManager.getInstance().getCastleByOwner(player.getClan());
				cprison = _cprisons.get(castle.getCastleId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Castle Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + CASTLE_1_VIT, true);
							partyMember.addExpAndSp(CASTLE_1_EXP, CASTLE_1_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}									
					}
				}
				cprison.setState(2);
				cprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison State: " + String.valueOf(cprison.getCastleState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(cprison.getInstanceId()));
				}
			}
		}
		for (int npcId : _CastleState2)
		{
			if (npcId == npc.getNpcId())
			{
				CastlePrison cprison = null;
				L2Party party = player.getParty();
				Castle castle = CastleManager.getInstance().getCastleByOwner(player.getClan());
				cprison = _cprisons.get(castle.getCastleId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Castle Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + CASTLE_2_VIT, true);
							partyMember.addExpAndSp(CASTLE_2_EXP, CASTLE_2_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}									
					}
				}
				cprison.setState(3);
				cprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison State: " + String.valueOf(cprison.getCastleState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(cprison.getInstanceId()));
				}				
			}
		}
		for (int npcId : _CastleState3)
		{
			if (npcId == npc.getNpcId())
			{
				CastlePrison cprison = null;
				L2Party party = player.getParty();
				Castle castle = CastleManager.getInstance().getCastleByOwner(player.getClan());
				cprison = _cprisons.get(castle.getCastleId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Castle Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + CASTLE_3_VIT, true);
							partyMember.addExpAndSp(CASTLE_3_EXP, CASTLE_3_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}									
					}
				}
				cprison.setState(4);
				cprison.initSpawn();
				if (debug)
				{
					player.sendMessage("Prison State: " + String.valueOf(cprison.getCastleState()));
					player.sendMessage("Prison instance Id: " + String.valueOf(cprison.getInstanceId()));
				}				
			}
		}
		for (int npcId : _CastleState4)
		{
			if (npcId == npc.getNpcId())
			{
				CastlePrison cprison = null;
				L2Party party = player.getParty();
				Castle castle = CastleManager.getInstance().getCastleByOwner(player.getClan());
				cprison = _cprisons.get(castle.getCastleId());
				for (L2PcInstance partyMember : party.getPartyMembers()) 
				{ 
					int nLvl = npc.getLevel();
					int pLvl = partyMember.getLevel();
					if (nLvl - pLvl > MAX_LEVEL_DIFFERENCE || pLvl - nLvl > MAX_LEVEL_DIFFERENCE)
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.sendMessage("======================");
							partyMember.sendMessage("= Castle Prison Info : ");
							partyMember.sendMessage("= You cannot aquire exp, sp or vitality.");
							partyMember.sendMessage("= Your level is too high or too low.");
							partyMember.sendMessage("======================");
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}
					}
					else
					{
						if (partyMember.getInstanceId() == cprison.getInstanceId())
						{
							partyMember.setVitalityPoints(partyMember.getVitalityPoints() + CASTLE_4_VIT, true);
							partyMember.addExpAndSp(CASTLE_4_EXP, CASTLE_4_SP);
						}
						else
						{
							if (party.getMemberCount() < 3)
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);
								player.setInstanceId(0);
								player.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								return "";
							}
							else
							{
								partyMember.teleToLocation(GiranLoc[0],GiranLoc[1],GiranLoc[2]);
								player.getParty().removePartyMember(partyMember);								
							}
						}									
					}
					if (debug)
					{
						player.sendMessage("Prison State: " + String.valueOf(cprison.getCastleState()));
						player.sendMessage("Prison instance Id: " + String.valueOf(cprison.getInstanceId()));
					}
					Castle castleOwner = CastleManager.getInstance().getCastleByOwner(partyMember.getClan());
					partyMember.teleToLocation(castleOwner.getCastleZone().getSpawnLoc().getX(),castleOwner.getCastleZone().getSpawnLoc().getY(), castleOwner.getCastleZone().getSpawnLoc().getZ());
					L2Summon pet = partyMember.getPet();
					if (pet != null)
					{							
						pet.teleToLocation(castleOwner.getCastleZone().getSpawnLoc().getX(),castleOwner.getCastleZone().getSpawnLoc().getY(), castleOwner.getCastleZone().getSpawnLoc().getZ());
						pet.setInstanceId(0);
					}
					cprison.setState(1);
					partyMember.setInstanceId(0);
				}				
			}
		}
		return super.onKill(npc, player, isPet);	  
	}
	/**
	 * Instance Load Procedure
	 * @param questId
	 * @param name
	 * @param descr
	 */
	private Prison(int questId, String name, String descr) 
	{
		super(questId, name, descr);
		int[] mobs = { HAGER_THE_OUTLAW, ALL_SEEING_RANGO, HELSING, GILLIEN,
				MEDICI, BRAND_THE_EXILE, RHIANNA_THE_TRAITOR,
				TESLA_THE_DECEIVER, BRUTUS_THE_OBSTINATE, RANGER_KARANKAWA,
				SARGON_THE_MAD, BEATIFUL_ATRIELLE, NAGEN_THE_TOMBOY,
				JAX_THE_DESTROYER, JAKARD, IMMORTAL_MUUS,
				SOUL_HUNTER_CHAKUNDEL, DURANGO_THE_CRUSHER, COMANDER_KOENIG, GERG };
		for (int npcId : FortWardens)
			addStartNpc(npcId);
		for (int npcId : FortWardens)
			addTalkId(npcId);
		for (int npcId : FortWardens)
			addFirstTalkId(npcId);
		for (int npcId : CastleWardens)
			addStartNpc(npcId);
		for (int npcId : CastleWardens)
			addTalkId(npcId);
		for (int npcId : CastleWardens)
			addFirstTalkId(npcId);
		for (int npcId : mobs)
			addKillId(npcId);
	}
	
	public static void main(String[] args) 
	{ 
		new Prison(-1,"PrisonDungeon", "instances");
		_log.warning("PrisonDungeon: 30 Prison Instances Loaded "); 
	}
}