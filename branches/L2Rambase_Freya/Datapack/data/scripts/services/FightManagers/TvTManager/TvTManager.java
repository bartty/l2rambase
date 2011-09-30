/**
 * @author L2Rambase Project
 */
package services.FightManagers.TvTManager;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.Announcements;
import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;

public class TvTManager
{
	protected static final Logger _log = Logger.getLogger(TvTManager.class.getName());
	
	/** Task for event cycles<br> */
	private TvTStartTask _task;
		
	/**
	 * New instance only by getInstance()<br>
	 */
	private TvTManager()
	{
		if (Config.TVT_EVENT_ENABLED)
		{
			TvTEvent.init();

			_log.info("TvTEventEngine[TvTManager.TvTManager()]: Started.");
		}
		else
		{
			_log.info("TvTEventEngine[TvTManager.TvTManager()]: Engine is disabled.");
		}
	}
	
	/**
	 * Initialize new/Returns the one and only instance<br><br>
	 *
	 * @return TvTManager<br>
	 */
	public static TvTManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public class StartTvT implements Runnable 
	{
		public void run()
		{
			try 
			{
				if (PvPEvent.isInactive())
				{
					scheduleEventStart();
				}
			} 
			catch (Exception e)
			{}
		}
	}

	
	/**
	 * Starts TvTStartTask
	 */
	public void scheduleEventStart()
	{
		try
		{
			_task = new TvTStartTask(System.currentTimeMillis()+1);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
		catch (Exception e)
		{
			_log.warning("TvTEventEngine[TvTManager.scheduleEventStart()]: Error figuring out a start time. Check TvTEventInterval in config file.");
		}
	}
	
	/**
	 * Method to start participation
	 */
	public void startReg()
	{
		if (!TvTEvent.startParticipation())
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("TvT Event:",1);
			Announcements.getInstance().announceToAll("Event was cancelled.",1);
			Announcements.getInstance().announceToAll("=======================",1);
			_log.warning("TvTEventEngine[TvTManager.run()]: Error spawning event npc for participation.");
		}
		else
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("TvT Event:",1);
			Announcements.getInstance().announceToAll("Registration opened for " + Config.TVT_EVENT_PARTICIPATION_TIME
					+ " minute(s).",1);
			Announcements.getInstance().announceToAll("Go to Giran and register",1);
			Announcements.getInstance().announceToAll("=======================",1);
			
			// schedule registration end
			_task.setStartTime(System.currentTimeMillis() + 60000L * Config.TVT_EVENT_PARTICIPATION_TIME);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
	}
	
	/**
	 * Method to start the fight
	 */
	public void startEvent()
	{
		if (!TvTEvent.startFight())
		{
			Announcements.getInstance().announceToAll("=======================",1);
			Announcements.getInstance().announceToAll("TvT Event:",1);
			Announcements.getInstance().announceToAll("Event cancelled due to lack of Participation.",1);
			Announcements.getInstance().announceToAll("=======================",1);
			_log.info("TvTEventEngine[TvTManager.run()]: Lack of registration, abort event.");
		}
		else
		{
			TvTEvent.sysMsgToAllParticipants("TvT Event: Teleporting participants to an arena in "
					+ Config.TVT_EVENT_START_LEAVE_TELEPORT_DELAY + " second(s).");
			_task.setStartTime(System.currentTimeMillis() + 60000L * Config.TVT_EVENT_RUNNING_TIME);
			ThreadPoolManager.getInstance().executeTask(_task);
		}
	}
	
	/**
	 * Method to end the event and reward
	 */
	public void endEvent()
	{
		Announcements.getInstance().announceToAll(TvTEvent.calculateRewards(),1);
		TvTEvent.sysMsgToAllParticipants("TvT Event: Teleporting back to the registration npc in "
				+ Config.TVT_EVENT_START_LEAVE_TELEPORT_DELAY + " second(s).");
		TvTEvent.stopFight();
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
	 * Class for TvT cycles
	 */
	class TvTStartTask implements Runnable
	{
		private long _startTime;
		public ScheduledFuture<?> nextRun;
		
		public TvTStartTask(long startTime)
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
				if (TvTEvent.isInactive())
				{
					TvTManager.this.startReg();
				}
				else if (TvTEvent.isParticipating())
				{
					TvTManager.this.startEvent();
				}
				else
				{
					TvTManager.this.endEvent();
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
				if (TvTEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("TvT Event: " + (time / 60 / 60) + " hour(s) until registration is closed!",1);
				}
				else if (TvTEvent.isStarted())
				{
					TvTEvent.sysMsgToAllParticipants("TvT Event: " + (time / 60 / 60) + " hour(s) until event is finished!");
				}
			}
			else if (time >= 60)
			{
				if (TvTEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("TvT Event: " + (time / 60) + " minute(s) until registration is closed!",1);
				}
				else if (TvTEvent.isStarted())
				{
					TvTEvent.sysMsgToAllParticipants("TvT Event: " + (time / 60) + " minute(s) until the event is finished!");
				}
			}
			else
			{
				if (TvTEvent.isParticipating())
				{
					Announcements.getInstance().announceToAll("TvT Event: " + time + " second(s) until registration is closed!",1);
				}
				else if (TvTEvent.isStarted())
				{
					TvTEvent.sysMsgToAllParticipants("TvT Event: " + time + " second(s) until the event is finished!");
				}
			}
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final TvTManager _instance = new TvTManager();
	}
}