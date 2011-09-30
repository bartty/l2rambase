/**
 * @author L2Rambase Project
 */
package services.FightManagers.PvPManager;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;

public class PvPManager
{
	protected static final Logger _log = Logger.getLogger(PvPManager.class.getName());
	
	/** Task for event cycles<br> */
	private PvPStartTask _task;
		
	/**
	 * New instance only by getInstance()<br>
	 */
	private PvPManager()
	{
		if (Config.PVP_EVENT_ENABLED)
		{
			PvPEvent.init();
			_log.info("PvPEventEngine[PvPManager.PvPManager()]: Started.");
		}
		else
		{
			_log.info("PvPEventEngine[PvPManager.PvPManager()]: Engine is disabled.");
		}
	}
	
	/**
	 * Initialize new/Returns the one and only instance<br><br>
	 *
	 * @return PvPManager<br>
	 */
	public static PvPManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public class StartPvP implements Runnable 
	{
		public void run()
		{
			try 
			{
				if (TvTEvent.isInactive())
				{
					scheduleEventStart();
				}
			} 
			catch (Exception e)
			{}
		}
	}
	
	/**
	 * Starts PvPStartTask
	 */
	public void scheduleEventStart()
	{
		try
		{
			_task = new PvPStartTask(System.currentTimeMillis()+1);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
		catch (Exception e)
		{
			_log.warning("PvPEventEngine[PvPManager.scheduleEventStart()]: Error figuring out a start time. Check PvPEventInterval in config file.");
		}
	}
	
	/**
	 * Method to start participation
	 */
	public void startReg()
	{
		if (!PvPEvent.startParticipation())
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("PvP Event:",1);
			Announcements.getInstance().announceToAll("Event was cancelled.",1);
			Announcements.getInstance().announceToAll("=======================",1);
			_log.warning("PvPEventEngine[PvPManager.run()]: Error spawning event npc for participation.");
		}
		else
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("PvP Event:",1);
			Announcements.getInstance().announceToAll("Registration opened for " + Config.PVP_EVENT_PARTICIPATION_TIME
					+ " minute(s).",1);
			Announcements.getInstance().announceToAll("Go to Giran and register.",1);
			Announcements.getInstance().announceToAll("=======================",1);
			
			// schedule registration end
			_task.setStartTime(System.currentTimeMillis() + 60000L * Config.PVP_EVENT_PARTICIPATION_TIME);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
	}
	
	/**
	 * Method to start the fight
	 */
	public void startEvent()
	{
		if (!PvPEvent.startFight())
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("PvP Event:",1);
			Announcements.getInstance().announceToAll("Event cancelled due to lack of Participation.",1);
			Announcements.getInstance().announceToAll("=======================",1);
			_log.info("PvPEventEngine[PvPManager.run()]: Lack of registration, abort event.");
		}
		else
		{
			PvPEvent.sysMsgToAllParticipants("PvP Event: Teleporting participants to an arena in "
					+ Config.PVP_EVENT_START_LEAVE_TELEPORT_DELAY + " second(s).");
			_task.setStartTime(System.currentTimeMillis() + 60000L * Config.PVP_EVENT_RUNNING_TIME);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
	}
	
	/**
	 * Method to end the event and reward
	 */
	public void endEvent()
	{
		Announcements.getInstance().announceToAll(PvPEvent.calculateRewards(),1);
		PvPEvent.sysMsgToAllParticipants("PvP Event: Teleporting back to the registration npc in "
				+ Config.PVP_EVENT_START_LEAVE_TELEPORT_DELAY + " second(s).");
		PvPEvent.stopFight();
	}
	
	public void skipDelay()
	{
		if (_task.nextRun.cancel(false))
		{
			_task.setStartTime(System.currentTimeMillis());
			ThreadPoolManager.getInstance().executeTask(_task);
		}
	}
	
	/**
	 * Class for PvP cycles
	 */
	class PvPStartTask implements Runnable
	{
		private long _startTime;
		public ScheduledFuture<?> nextRun;
		
		public PvPStartTask(long startTime)
		{
			_startTime = startTime;
		}
		
		public void setStartTime(long startTime)
		{
			_startTime = startTime;
		}
		
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run()
		{
			int delay = (int) Math.round((_startTime - System.currentTimeMillis()) / 1000.0);
			
			if (delay > 0)
			{
				this.announce(delay);
			}
			
			int nextMsg = 0;
			if (delay > 3600)
			{
				nextMsg = delay - 3600;
			}
			else if (delay > 1800)
			{
				nextMsg = delay - 1800;
			}
			else if (delay > 900)
			{
				nextMsg = delay - 900;
			}
			else if (delay > 600)
			{
				nextMsg = delay - 600;
			}
			else if (delay > 300)
			{
				nextMsg = delay - 300;
			}
			else if (delay > 60)
			{
				nextMsg = delay - 60;
			}
			else if (delay > 5)
			{
				nextMsg = delay - 5;
			}
			else if (delay > 0)
			{
				nextMsg = delay;
			}
			else
			{
				// start
				if (PvPEvent.isInactive())
				{
					PvPManager.this.startReg();
				}
				else if (PvPEvent.isParticipating())
				{
					PvPManager.this.startEvent();
				}
				else
				{
					PvPManager.this.endEvent();
				}
			}
			
			if (delay > 0)
			{
				nextRun = ThreadPoolManager.getInstance().scheduleGeneral(this, nextMsg * 1000);
			}
		}
		
		private void announce(long time)
		{
			if (time >= 3600 && time % 3600 == 0)
			{
				if (PvPEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("PvP Event: " + (time / 60 / 60) + " hour(s) until registration is closed!",1);
				}
				else if (PvPEvent.isStarted())
				{
					PvPEvent.sysMsgToAllParticipants("PvP Event: " + (time / 60 / 60) + " hour(s) until event is finished!");
				}
			}
			else if (time >= 60)
			{
				if (PvPEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("PvP Event: " + (time / 60) + " minute(s) until registration is closed!",1);
				}
				else if (PvPEvent.isStarted())
				{
					PvPEvent.sysMsgToAllParticipants("PvP Event: " + (time / 60) + " minute(s) until the event is finished!");
				}
			}
			else
			{
				if (PvPEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("PvP Event: " + time + " second(s) until registration is closed!",1);
				}
				else if (PvPEvent.isStarted())
				{
					PvPEvent.sysMsgToAllParticipants("PvP Event: " + time + " second(s) until the event is finished!");
				}
			}
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final PvPManager _instance = new PvPManager();
	}
}