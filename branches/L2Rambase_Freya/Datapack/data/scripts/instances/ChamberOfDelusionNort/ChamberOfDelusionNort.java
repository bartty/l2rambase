package instances.ChamberOfDelusionNort;

import javolution.util.FastMap;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.InstanceManager;
import l2.rambase.gameserver.instancemanager.InstanceManager.InstanceWorld;
import l2.rambase.gameserver.model.L2Party;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.util.Util;
import l2.rambase.util.Rnd;

/**
 * 
 * @author L2Rambase Project
 *
 */

public class ChamberOfDelusionNort extends Quest
{

	private class CDWorld extends InstanceWorld
	{
		private L2Npc manager,managera,managerb,managerc,managerd,managere,managerf,managerg,managerh,chesta,chestb,chestc,chestd,_aenkinel;
		public CDWorld()
		{
			InstanceManager.getInstance().super();
		}
	}

	private static final String qn = "ChamberOfDelusionNort";
	private static final int INSTANCEID = 130; // this is the client number
	//NPCs
	private static final int GKSTART = 32661;
	private static final int GKFINISH = 32667;
	private static final int AENKINEL = 25693;
	private static final int PRIZ = 18820;
	private static final int FAIL1 = 18819;
	private static final int FAIL2 = 18819;
	private static final int FAIL3 = 18819;
	private static final int ROOMRB = 4;
	private int rb = 0;
	private int a;
	public int instId = 0;
	private int b;
	private int g = 0;
	private int h = 0;
	private int c;
	private class teleCoord {int instanceId; int x; int y; int z;}
	private static final int[][] TELEPORT =
	{
		{ -122368,-152624,-6752},
		{ -122368,-153504,-6752},
		{ -120496,-154304,-6752},
		{ -120496,-155184,-6752},
		{ -121440,-154688,-6752},
		{ -121440,-151328,-6752},
		{ -120496,-153008,-6752},
		{ -122368,-154800,-6752},
		{ -121440,-153008,-6752}
	};
	
	/** Time for Chamber Re Enter **/
	private static int CHAMBER_RE_ENTER_TIME = 1440;
	/** Chamber enter status **/
	private final FastMap<Integer, ChamberStatus> _chamberStatus = new FastMap<Integer, ChamberStatus>();
	
	private class ChamberStatus
	{
		
		@SuppressWarnings("unused")
		private int _playerId;
		private boolean _canEnter;
		private long _enterTime;
		
		private ChamberStatus(int id) 
		{
			try 
			{
				_playerId = id;
				_enterTime = 0;
				_canEnter = false;
				ThreadPoolManager.getInstance().scheduleGeneral(new ChamberReEntranceTimerTask(), (CHAMBER_RE_ENTER_TIME*60)*1000);
			} 
			catch (RuntimeException e) 
			{
				_log.warning("");
			}
		}
		
		private class ChamberReEntranceTimerTask implements Runnable 
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
	
	private boolean checkConditions(L2PcInstance player)
	{

		L2Party party = player.getParty();
		if (party == null)
		{
			player.sendPacket(new SystemMessage(2101));
			return false;
		}
		if (party.getLeader() != player)
		{
			player.sendPacket(new SystemMessage(2185));
			return false;
		}
		for (L2PcInstance partyMember : party.getPartyMembers())
		{
			if (partyMember.getLevel() < 80)
			{
				SystemMessage sm = new SystemMessage(2097);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
			if (!Util.checkIfInRange(1000, player, partyMember, true))
			{
				SystemMessage sm = new SystemMessage(2096);
				sm.addPcName(partyMember);
				party.broadcastToPartyMembers(sm);
				return false;
			}
			ChamberStatus chamberStatus = null;
			if (_chamberStatus.isEmpty())
			{
				return true;
			}
			else if (_chamberStatus.equals(partyMember.getObjectId()))
			{
				chamberStatus = _chamberStatus.get(partyMember.getObjectId());
				if (!chamberStatus.getCanEnter())
				{
					player.sendMessage(partyMember.getName() + " cannot enter to instance because have limit.");
					partyMember.sendPacket(new SystemMessage(SystemMessageId.INSTANCE_ZONE_TIME_LIMIT));
					long canEnterTime = ((CHAMBER_RE_ENTER_TIME*60000) - (System.currentTimeMillis() - chamberStatus.getEnterTime()));
					if (canEnterTime < 60000)
					{
						canEnterTime = 1;
					}
					else
					{
						canEnterTime = canEnterTime / 60000;
					}
					partyMember.sendMessage("You cannot enter now. Wait: " + String.valueOf(canEnterTime) + " minutes");
					return false;
				}
			}
		}
		return true;
		
	}

	private void teleportplayer(L2PcInstance player, teleCoord teleto)
	{
		player.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		player.setInstanceId(teleto.instanceId);
		player.teleToLocation(teleto.x, teleto.y, teleto.z);
		return;
	}

	private void teleportrnd(L2PcInstance player, CDWorld world)
	{	
		int tp = Rnd.get(TELEPORT.length); 
		if (rb == 1&& tp == ROOMRB)
		{
			tp = Rnd.get(TELEPORT.length);
			for (int i = 0; i < TELEPORT.length; i++)
			{
				if (i == tp)
				{
					for (L2PcInstance partyMember : player.getParty().getPartyMembers())
					{
						partyMember.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
						partyMember.setInstanceId(instId);
						partyMember.teleToLocation(TELEPORT[i][0], TELEPORT[i][1], TELEPORT[i][2]);
					}
				}
			}
			a = player.getX();  
			b = player.getY();
			c = player.getZ();
		}
		else 
		{ 
		for (int i = 0; i < TELEPORT.length; i++)
		{
			if (i == tp)
			{
				for (L2PcInstance partyMember : player.getParty().getPartyMembers())
				{
					partyMember.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					partyMember.setInstanceId(instId);
					partyMember.teleToLocation(TELEPORT[i][0], TELEPORT[i][1], TELEPORT[i][2]);
				}
			}
		}
		a = player.getX();  
		b = player.getY();
		c = player.getZ();
		}
		
		return;
	}
	
	protected void spawnState(CDWorld world)
	{
		world._aenkinel = addSpawn(AENKINEL, -121463,-155094,-6752, 0, false, 0, false, world.instanceId);
		world._aenkinel.setIsNoRndWalk(false);
		world.manager = addSpawn(32667,-121440,-154688,-6752,0,false,0,false,world.instanceId);
		world.manager.setIsNoRndWalk(true);
		world.managerb = addSpawn(32667,-122368,-153504,-6752,0,false,0,false,world.instanceId);
		world.managerb.setIsNoRndWalk(true);
		world.managerc = addSpawn(32667,-120496,-154304,-6752,0,false,0,false,world.instanceId);
		world.managerc.setIsNoRndWalk(true);
		world.managerd = addSpawn(32667,-120496,-155184,-6752,0,false,0,false,world.instanceId);
		world.managerd.setIsNoRndWalk(true);
		world.managere = addSpawn(32667,-121440,-151328,-6752,0,false,0,false,world.instanceId);
		world.managere.setIsNoRndWalk(true);
		world.managerf = addSpawn(32667,-120496,-153008,-6752,0,false,0,false,world.instanceId);
		world.managerf.setIsNoRndWalk(true);
		world.managerg = addSpawn(32667,-122368,-154800,-6752,0,false,0,false,world.instanceId);
		world.managerg.setIsNoRndWalk(true);
		world.managerh = addSpawn(32667,-121440,-153008,-6752,0,false,0,false,world.instanceId);
		world.managerh.setIsNoRndWalk(true);
		world.managera = addSpawn(32667,-122368,-152624,-6752,0,false,0,false,world.instanceId);
		world.managera.setIsNoRndWalk(true);
	}
	protected int enterInstance(L2PcInstance player, String template)
	{
		int instanceId = 0;
		//check for existing instances for this player		
		InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
		//existing instance
		if (world != null)
		{
			if (!(world instanceof CDWorld))
			{
				player.sendPacket(new SystemMessage(SystemMessageId.ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER));
				return 0;
			}
			teleCoord tele = new teleCoord();
			tele.x = a;
			tele.y = b;
			tele.z = c;
			tele.instanceId = world.instanceId;
			teleportplayer(player,tele);
			return instanceId;
		}
		//New instance
		else
		{
			if (!checkConditions(player))
				return 0;
			L2Party party = player.getParty();
			instanceId = InstanceManager.getInstance().createDynamicInstance(template);
			world = new CDWorld();
			world.instanceId = instanceId;
			world.templateId = INSTANCEID;
			world.status = 0;
			InstanceManager.getInstance().addWorld(world);
			_log.info("Chamber Of Delusion started " + template + " Instance: " + instanceId + " created by player: " + player.getName());
			spawnState((CDWorld)world);
			instId = world.instanceId;

			for (L2PcInstance partyMember : party.getPartyMembers())
			{
				partyMember.setInChamber(true);
				teleportrnd(partyMember,(CDWorld)world);
				world.allowed.add(partyMember.getObjectId());
				if (_chamberStatus.get(partyMember.getObjectId()) == null)
				{
					_chamberStatus.put(partyMember.getObjectId(), new ChamberStatus(partyMember.getObjectId()));
					_chamberStatus.get(partyMember.getObjectId()).setEnterTime(System.currentTimeMillis());
				}
				else
				{
					_chamberStatus.remove(partyMember.getObjectId());
					_chamberStatus.put(partyMember.getObjectId(), new ChamberStatus(partyMember.getObjectId()));
					_chamberStatus.get(partyMember.getObjectId()).setEnterTime(System.currentTimeMillis());
				}
			}
			
			return instanceId;
		}
	}

	protected void exitInstance(L2PcInstance player, teleCoord tele)
	{
		player.setInstanceId(0);
		player.setInChamber(false);
		player.teleToLocation(tele.x, tele.y, tele.z);
		L2Summon pet = player.getPet();
		if (pet != null)
		{
			pet.setInstanceId(0);
			pet.teleToLocation(tele.x, tele.y, tele.z);
		}
	}
	
	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		InstanceWorld tmpworld = InstanceManager.getInstance().getPlayerWorld(player);
		if (tmpworld instanceof CDWorld)
		{
			CDWorld world = (CDWorld) tmpworld;
			L2Party party = player.getParty();
			instId = player.getInstanceId();
			if (event.equalsIgnoreCase("tproom"))
			{	

				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					if (player.isInChamber())
					{
						teleportrnd(partyMember,(CDWorld)world);
					}
				}
				startQuestTimer("tproom1",480000,null,player);
				h++;
			}
			else if (event.equalsIgnoreCase("tproom1"))
			{

				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					if (player.isInChamber())
					{
						teleportrnd(partyMember,(CDWorld)world);
					}
				}
				
				startQuestTimer("tproom2",480000,null,player);
				h++;
			}
			else if (event.equalsIgnoreCase("tproom2"))
			{

				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					if (player.isInChamber())
					{
						teleportrnd(partyMember,(CDWorld)world);
					}
				}
				
				startQuestTimer("tproom3",480000,null,player);
				h++;
			}
			else if (event.equalsIgnoreCase("tproom3"))
			{

				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					if (player.isInChamber())
					{
						teleportrnd(partyMember,(CDWorld)world);
					}
				}
				
			}
			else if ("7".equalsIgnoreCase(event))
			{	
				if (g ==0)
				{
				if (h == 0)
				{
				cancelQuestTimers("tproom");
				for (L2PcInstance partyMember : party.getPartyMembers())
				{
					if (player.isInChamber())
					{
						teleportrnd(partyMember,(CDWorld)world);
					}
				}
				startQuestTimer("tproom1",480000,null,player);
				g =1;
				}
				else if (h == 1)
				{				
					cancelQuestTimers("tproom1");
					for (L2PcInstance partyMember : party.getPartyMembers())
					{
						if (player.isInChamber())
						{
							teleportrnd(partyMember,(CDWorld)world);
						}
					}
					startQuestTimer("tproom2",480000,null,player);
					g =1;
				}
				else if (h == 2)
				{
					cancelQuestTimers("tproom2");
					for (L2PcInstance partyMember : party.getPartyMembers())
					{
						if (player.isInChamber())
						{
							teleportrnd(partyMember,(CDWorld)world);
						}
					}	
					startQuestTimer("tproom3",480000,null,player);
					g =1;
				}
				else if (h == 3)
				{
					cancelQuestTimers("tproom3");
					for (L2PcInstance partyMember : party.getPartyMembers())
					{
						if (player.isInChamber())
						{
							teleportrnd(partyMember,(CDWorld)world);
						}
					}	
					g =1;				
				}
			}
			}  
		
		}
		return "";
	}
	public String onAttack (L2Npc npc, L2PcInstance attacker)
	{
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		if (tmpworld instanceof CDWorld)
		{
			CDWorld world = (CDWorld) tmpworld;
			if (npc.getNpcId() == FAIL1 || npc.getNpcId() == FAIL2 || npc.getNpcId() == FAIL3)
			{
				world.chesta.deleteMe();
				world.chestb.deleteMe();
				world.chestc.deleteMe();
				world.chestd.deleteMe();			
			}
			else if (npc.getNpcId() == PRIZ)
			{
				world.chestb.deleteMe();
				world.chestc.deleteMe();
				world.chestd.deleteMe();	
			}
		}
		return null;
	}

	public String onKill( L2Npc npc, L2PcInstance player)
	{
		int npcId = npc.getNpcId();
		if (rb == 0 && npcId == AENKINEL)
		{
			_log.info("kill");
			rb = 1;
		InstanceWorld tmpworld = InstanceManager.getInstance().getWorld(npc.getInstanceId());
		if (tmpworld instanceof CDWorld)
		{
			CDWorld world = (CDWorld) tmpworld;

				world.chesta = addSpawn(PRIZ, -121524,-155073,-6752,0, false, 0, false, world.instanceId);
                                world.chesta.setIsNoRndWalk(true);
				world.chestb = addSpawn(FAIL1, -121486,-155070,-6752,0, false, 0, false, world.instanceId);
                                world.chestb.setIsNoRndWalk(true);
				world.chestc = addSpawn(FAIL2, -121457,-155071,-6752,0, false, 0, false, world.instanceId);
                                world.chestc.setIsNoRndWalk(true);
				world.chestd = addSpawn(FAIL3, -121428,-155070,-6752,0, false, 0, false, world.instanceId);
                                world.chestd.setIsNoRndWalk(true);
				_log.info("spawn");
			
		}
		}
		return "";
	}
	public final String onFirstTalk (L2Npc npc, L2PcInstance player)
	{
		return npc.getNpcId() + ".htm";
	}		

	public String onTalk (L2Npc npc, L2PcInstance player)
	{
		int npcId = npc.getNpcId();
		QuestState st = player.getQuestState(qn);
		if (st == null)
			st = newQuestState(player);
		if (npcId == GKSTART)
		{	
			enterInstance(player,"ChamberofDelusionNort.xml");
			startQuestTimer("tproom",480000,null,player);
			return "";
		}
		else if (npcId == GKFINISH)
		{	InstanceWorld world = InstanceManager.getInstance().getPlayerWorld(player);
			world.allowed.remove(world.allowed.indexOf(player.getObjectId()));
			teleCoord tele = new teleCoord();
			tele.instanceId = 0;
			tele.x = -114592;
			tele.y = -152509;
			tele.z = -6723;
			cancelQuestTimers("tproom");
			cancelQuestTimers("tproom1");
			cancelQuestTimers("tproom2");
			cancelQuestTimers("tproom3");
			for (L2PcInstance partyMember :  player.getParty().getPartyMembers())
			{
				exitInstance(partyMember,tele);
			}
		}
		return "";
	}

	public ChamberOfDelusionNort(int questId, String name, String descr)
	{
		super(questId, name, descr);

		addStartNpc(GKSTART);
		addTalkId(GKSTART);
		addStartNpc(GKFINISH);
		addFirstTalkId(GKFINISH);
		addTalkId(GKFINISH);
		addKillId(AENKINEL);
		addAttackId(PRIZ); 
		addAttackId(FAIL1); 
		addAttackId(FAIL2); 
		addAttackId(FAIL3);    		
	}

	public static void main(String[] args)
	{
		new ChamberOfDelusionNort(-1,qn,"instances");
	}
}