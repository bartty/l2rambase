/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.quest;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class QuestTimer
{
	protected static final Logger _log = Logger.getLogger(QuestTimer.class.getName());
	// =========================================================
	// Schedule Task
	public class ScheduleTimerTask implements Runnable
	{
		public void run()
		{
			if (this == null || !getIsActive())
				return;
			
			try
			{
				if (!getIsRepeating())
					cancel();
				getQuest().notifyEvent(getName(), getNpc(), getPlayer());
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "", e);
			}
		}
	}
	
	// =========================================================
	// Data Field
	private boolean _isActive = true;
	private String _name;
	private Quest _quest;
	private L2Npc _npc;
	private L2PcInstance _player;
	private boolean _isRepeating;
	private ScheduledFuture<?> _schedular;
	
	// =========================================================
	// Constructor
	public QuestTimer(Quest quest, String name, long time, L2Npc npc, L2PcInstance player, boolean repeating)
	{
		_name = name;
		_quest = quest;
		_player = player;
		_npc = npc;
		_isRepeating = repeating;
		if (repeating)
			_schedular = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new ScheduleTimerTask(), time, time); // Prepare auto end task
		else
			_schedular = ThreadPoolManager.getInstance().scheduleGeneral(new ScheduleTimerTask(), time); // Prepare auto end task
	}
	
	public QuestTimer(Quest quest, String name, long time, L2Npc npc, L2PcInstance player)
	{
		this(quest, name, time, npc, player, false);
	}
	
	public QuestTimer(QuestState qs, String name, long time)
	{
		this(qs.getQuest(), name, time, null, qs.getPlayer(), false);
	}
	
	// =========================================================
	// Method - Public
	public void cancel()
	{
		_isActive = false;
		
		if (_schedular != null)
			_schedular.cancel(false);
		
		getQuest().removeQuestTimer(this);
	}
	
	/**
	 * public method to compare if this timer matches with the key attributes passed.
	 * @param quest : Quest instance to which the timer is attached
	 * @param name : Name of the timer
	 * @param npc : Npc instance attached to the desired timer (null if no npc attached)
	 * @param player : Player instance attached to the desired timer (null if no player attached)
	 */
	public boolean isMatch(Quest quest, String name, L2Npc npc, L2PcInstance player)
	{
		if ((quest == null) || (name == null))
			return false;
		if ((quest != getQuest()) || name.compareToIgnoreCase(getName()) != 0)
			return false;
		return ((npc == getNpc()) && (player == getPlayer()));
	}
	
	// =========================================================
	// Property - Public
	public final boolean getIsActive()
	{
		return _isActive;
	}
	
	public final boolean getIsRepeating()
	{
		return _isRepeating;
	}
	
	public final Quest getQuest()
	{
		return _quest;
	}
	
	public final String getName()
	{
		return _name;
	}
	
	public final L2Npc getNpc()
	{
		return _npc;
	}
	
	public final L2PcInstance getPlayer()
	{
		return _player;
	}
	
	@Override
	public final String toString()
	{
		return _name;
	}
}