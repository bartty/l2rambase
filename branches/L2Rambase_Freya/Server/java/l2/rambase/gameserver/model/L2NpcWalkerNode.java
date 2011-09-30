/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.templates.StatsSet;


public class L2NpcWalkerNode 
{
	private int _routeId;
	private int _npcId;
	private String _movePoint;
	private String _chatText;
	private int _moveX;
	private int _moveY;
	private int _moveZ;
	private int _delay;
	
	private boolean _running;

	public void setRunning(boolean val)
	{
		_running = val;
	}
	
	public void setRouteId(int id)
	{
		_routeId = id;
	}

	public void setNpcId(int id)
	{
		_npcId = id;
	}

	public void setMovePoint(String val)
	{
		_movePoint = val;
	}

	public void setChatText(String val)
	{
		_chatText = val;
	}

	public void setMoveX(int val)
	{
		_moveX = val;
	}

	public void setMoveY(int val)
	{
		_moveY = val;
	}

	public void setMoveZ(int val)
	{
		_moveZ = val;
	}

	public void setDelay(int val)
	{
		_delay = val;
	}

	public int getRouteId()
	{
		return _routeId;
	}

	public int getNpcId()
	{
		return _npcId;
	}

	public String getMovePoint()
	{
		return _movePoint;
	}

	public String getChatText()
	{
		return _chatText;
	}

	public int getMoveX()
	{
		return _moveX;
	}

	public int getMoveY()
	{
		return _moveY;
	}

	public int getMoveZ()
	{
		return _moveZ;
	}

	public int getDelay()
	{
		return _delay;
	}
	
	public boolean getRunning()
	{
		return _running;
	}
	
	/**
	 * Constructor of L2NpcWalker.<BR><BR>
	 */
	public L2NpcWalkerNode()
	{}
	
	/**
	 * Constructor of L2NpcWalker.<BR><BR>
	 * @param set The StatsSet object to transfer data to the method
	 */
	public L2NpcWalkerNode(StatsSet set)
	{		
		_npcId       = set.getInteger("npc_id");
		_movePoint   = set.getString("move_point");
		_chatText    = set.getString("chatText");
		_moveX       = set.getInteger("move_x");
		_moveX       = set.getInteger("move_y");
		_moveX       = set.getInteger("move_z");
		_delay       = set.getInteger("delay");
	}
}
