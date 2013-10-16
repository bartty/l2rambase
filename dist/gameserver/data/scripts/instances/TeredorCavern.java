package instances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.utils.Location;
import lineage2.commons.threading.RunnableImpl;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.instancemanager.WorldStatisticsManager;
import lineage2.gameserver.listener.actor.OnCurrentHpDamageListener;
import lineage2.gameserver.listener.actor.OnDeathListener;
import lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Party;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.model.Zone;

public class TeredorCavern extends Reflection
{
	
	private int player_count=0;
	private int instanceId=160;
	private static final Logger _log = LoggerFactory.getLogger(TeredorCavern.class);
	private static int Teredor = 25785;
	private static int AdventurerId=50038;
	
	private boolean cleanup_ready=true;
	
	
	
	private static Location TeredorSpawnCoords= new Location (176160, -185200, -3800);
	private static Location AdventurerSpawnCoords= new Location (177048, -185832, -3826);
	
	
	
	private DeathListener _deathListener = new DeathListener();
	private ZoneListener zl = new ZoneListener();
	
	
	
	

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			Player player = cha.getPlayer();
			if(player == null || !cha.isPlayer())
				return;
			++player_count;
			_log.info("Teredor player_count set to "+player_count);
			
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			Player player = cha.getPlayer();
			if(player == null || !cha.isPlayer())
				return;
			--player_count;
			_log.info("Teredor player_count set to "+player_count);
			if(player_count==0)
			{
				cleanup_ready=true;
				ThreadPoolManager.getInstance().schedule(new FinalAndCollapse(),60*1000);
			}

		}
	}
	
	private class DeathListener implements OnDeathListener
	{
		@Override
		public void onDeath(Creature self, Creature killer)
		{
			if(self.isNpc() && self.getNpcId() == Teredor )
			{
	
				
				cleanup_ready=false;
				addSpawnWithoutRespawn(AdventurerId, TeredorSpawnCoords, 0);
				ThreadPoolManager.getInstance().schedule(new FinalAndCollapse(), 5*60*1000);
				_log.info("Teredor cleanup scheduled ...");
				for ( Player p : killer.getPlayer().getParty().getPartyMembers())
				{
					if(p!=null)
					{
						p.setInstanceReuse(instanceId, System.currentTimeMillis());
						p.sendPacket(new SystemMessage(SystemMessage.THIS_DUNGEON_WILL_EXPIRE_IN_S1_MINUTES).addNumber(5));
					}
				}
				
	
			}
		}
	}
	
	

	private class FinalAndCollapse extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			cleanup_ready=true;
			_log.info("Teredor cleanup running ...");
			startCollapseTimer(10);
		}
	}
	
	@Override
	protected void onCreate()
	{
		super.onCreate();
		_log.info("Teredor Spawned...");
	
		/* missing zone in xml/zone/epic.xml */
		
		/*
		Zone tz=getZone("[Teredor_zone]");
		
		if(tz!=null)
		{
			tz.addListener(zl);
		}
		else
		{
			_log.info("Teredor instance couldn't get zone , have zones "+getZones().size());
		}
		*/
		NpcInstance teredor_npc=addSpawnWithoutRespawn(Teredor, TeredorSpawnCoords, 0);
		teredor_npc.addListener(_deathListener);
	}
	
	
	public void clearReflection(int timeInMinutes, boolean message)
	{
		if(!cleanup_ready)
		{
			_log.info("Teredor trying to clear ... should fail ");
			return;
		}
		_log.info("Teredor forward cleanup");
		super.clearReflection(timeInMinutes,message);
	}


	
}