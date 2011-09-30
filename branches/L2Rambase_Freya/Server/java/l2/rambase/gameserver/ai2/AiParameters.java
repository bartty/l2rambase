/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai2;

import java.util.EnumSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;

import javolution.util.FastList;


public class AiParameters
{
	private Queue<AiEvent> _eventQueue;
	private EnumSet<AiEventType> _inhibitions;
	private L2Npc _actor;
	private List<Hated> _hated;
	private List<Liked> _liked;
	
	public class Hated
	{
		public L2Character character;
		public HateReason reason;
		public int degree;
		
	}
	
	public class Liked
	{
		public L2Character character;
		public LikeReason reason;
		public int degree;
	}
	
	public enum HateReason
	{
		GAVE_DAMMAGE,
		HEALS_ENNEMY,
		GAVE_DAMMAGE_TO_FRIEND,
		IS_ENNEMY
	}
	
	public enum LikeReason
	{
		FRIEND,
		HEALED,
		HEALED_FRIEND,
		GAVE_DAMMAGE_TO_ENNEMY
	}
	
	public AiParameters(L2Npc actor)
	{
		_eventQueue = new PriorityBlockingQueue<AiEvent>();
		_hated = new FastList<Hated>();
		_liked = new FastList<Liked>();
		_actor = actor;
		_inhibitions = EnumSet.noneOf(AiEventType.class);
	}
	
	/**
	 * @return
	 */
	public boolean hasEvents()
	{
		return _eventQueue.isEmpty();
	}
	
	/**
	 * @return
	 */
	public AiEvent nextEvent()
	{
		return _eventQueue.poll();
	}
	
	public void queueEvents(AiEvent set)
	{
		_eventQueue.offer(set);
	}
	
	public L2Npc getActor()
	{
		return _actor;
	}
	
	public List<Hated> getHated()
	{
		return _hated;
	}
	
	public List<Liked> getLiked()
	{
		return _liked;
	}
	
	public void addLiked(Liked cha)
	{
		_liked.add(cha);
	}
	
	public void addHated(Hated cha)
	{
		_hated.add(cha);
	}
	
	public void clear()
	{
		_hated.clear();
		_liked.clear();
		_eventQueue.clear();
		_inhibitions.clear();
	}
	
	public void inhibit(AiEventType type)
	{
		_inhibitions.add(type);
	}
	
	public void deInhibit(AiEventType type)
	{
		_inhibitions.remove(type);
	}
	
	/**
	 * @param type
	 * @return
	 */
	public boolean isEventInhibited(AiEventType type)
	{
		return _inhibitions.contains(type);
	}
	
}
