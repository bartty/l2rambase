/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.util.Map;

import javolution.util.FastMap;

import l2.rambase.gameserver.model.L2Object.InstanceType;

public class ActionHandler
{
	private Map<InstanceType, IActionHandler> _actions;
	private Map<InstanceType, IActionHandler> _actionsShift;

	public static ActionHandler getInstance()
	{
		return SingletonHolder._instance;
	}

	private ActionHandler()
	{
		_actions = new FastMap<InstanceType, IActionHandler>();
		_actionsShift = new FastMap<InstanceType, IActionHandler>();
	}

	public void registerActionHandler(IActionHandler handler)
	{
		_actions.put(handler.getInstanceType(), handler);
	}

	public void registerActionShiftHandler(IActionHandler handler)
	{
		_actionsShift.put(handler.getInstanceType(), handler);
	}

	public IActionHandler getActionHandler(InstanceType iType)
	{
		IActionHandler result = null;
		for (InstanceType t = iType; t != null; t = t.getParent())
		{
			result = _actions.get(t);
			if (result != null)
				break;
		}
		return result;
	}

	public IActionHandler getActionShiftHandler(InstanceType iType)
	{
		IActionHandler result = null;
		for (InstanceType t = iType; t != null; t = t.getParent())
		{
			result = _actionsShift.get(t);
			if (result != null)
				break;
		}
		return result;
	}

	public int size()
	{
		return _actions.size();
	}

	public int sizeShift()
	{
		return _actionsShift.size();
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ActionHandler _instance = new ActionHandler();
	}
}