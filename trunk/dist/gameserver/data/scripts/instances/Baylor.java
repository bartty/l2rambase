package instances;

import java.util.concurrent.atomic.AtomicInteger;
import lineage2.commons.threading.RunnableImpl;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.network.serverpackets.ExStartScenePlayer;
import lineage2.gameserver.utils.Location;
import lineage2.gameserver.listener.actor.OnDeathListener;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.Creature;

import lineage2.gameserver.model.Zone;
import lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;

/**
 *
 * @author Awakeninger
 */

public class Baylor extends Reflection {
	
	
	
	private static final int Baylor = 29213;
	private static final int BaylorDummy = 60001;
	NpcInstance trueBaylor;
	private static final int Golem1 = 23123;
	private static final int Golem2 = 23123;   
	private static final int Golem3 = 23123;
	private static final int Golem4 = 23123; 
	//private static final int Door = 
	private Location Golem1Loc = new Location(152648,142968,-12762);
	private Location Golem2Loc = new Location(152664,141160,-12762);
	private Location Golem3Loc = new Location(154488,141160,-12762);
	private Location Golem4Loc = new Location(154488,143000,-12762);
    private Location vullockspawn1 = new Location(153256,142056,-12762,0);
	private Location vullockspawn2 = new Location(153912,142088,-12762,32767);
	private static final long BeforeDelay = 60 * 1000L;
	private static final long BeforeDelayVDO = 47 * 1000L;
	
	private static final int Door = 24220008;
	private boolean _entryLocked = false;
	private boolean _startLaunched = false;
	private ZoneListener _epicZoneListener = new ZoneListener();
	
	private DeathListener _deathListener1 = new DeathListener();
	private DeathListener _deathListener2 = new DeathListener();	
	
	private AtomicInteger raidplayers = new AtomicInteger();
	
   
	@Override
	protected void onCreate()
	{
		super.onCreate();
		getZone("[baylor_epic]").addListener(_epicZoneListener);
	}	
	
	
	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			if(_entryLocked)
				return;

			Player player = cha.getPlayer();
			if(player == null || !cha.isPlayer())
				return;

			if(checkstartCond(raidplayers.incrementAndGet()))
			{
				ThreadPoolManager.getInstance().schedule(new FirstStage(), BeforeDelayVDO);
				ThreadPoolManager.getInstance().schedule(new BaylorSpawn(), BeforeDelay);
				_startLaunched = true;
			}
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			Player player = cha.getPlayer();
			if(player == null || !cha.isPlayer())
				return;

			raidplayers.decrementAndGet();
		}
	}
	private boolean checkstartCond(int raidplayers)
	{
		return !(raidplayers < getInstancedZone().getMinParty() || _startLaunched);
	}
	
   //ExStartScenePlayer.SCENE_BALROG_OPENING
		//player.showQuestMovie(SceneMovie.si_barlog_opening);
	private class FirstStage extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{

			_entryLocked = true;
			closeDoor(Door);

			for(Player player : getPlayers())
				player.showQuestMovie(ExStartScenePlayer.SCENE_SI_BARLOG_OPENING);
				_log.info("43 Video");
					
		}
	}
	
	private class DeathListener implements OnDeathListener
	{
		@Override
		public void onDeath(Creature self, Creature killer)
		{
			if(self.isNpc())
			{
					if(self.getNpcId() == BaylorDummy)
					{
			
						trueBaylor.setIsInvul(false);
					}
					else
					{
						for(Player player : getPlayers())
						{
							player.showQuestMovie(ExStartScenePlayer.SCENE_SI_BARLOG_STORY);
						}
					}
			}
		}
	}
	
	
	public class BaylorSpawn extends RunnableImpl {


        public BaylorSpawn() {

        }

        @Override
        public void runImpl() {
			Location Loc1 = Golem1Loc;
			Location Loc2 = Golem2Loc;
			Location Loc3 = Golem3Loc;
			Location Loc4 = Golem4Loc;
            Location Loc = vullockspawn1;
			Location Loc5 = vullockspawn2;
            trueBaylor=addSpawnWithoutRespawn(Baylor, Loc, 0);
            trueBaylor.addListener(_deathListener2);
			
			trueBaylor.setIsInvul(true);
            NpcInstance bDummy =addSpawnWithoutRespawn(BaylorDummy, Loc5, 0);
			bDummy.addListener(_deathListener1);
			
			addSpawnWithoutRespawn(Golem1, Loc1, 0);
			addSpawnWithoutRespawn(Golem2, Loc2, 0);
			addSpawnWithoutRespawn(Golem3, Loc3, 0);
			addSpawnWithoutRespawn(Golem4, Loc4, 0);
        }
    }
}