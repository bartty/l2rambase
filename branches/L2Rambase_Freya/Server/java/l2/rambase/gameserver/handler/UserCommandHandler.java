/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap;

import java.util.logging.Logger;

import l2.rambase.Config;


public class UserCommandHandler
{
	private static Logger _log = Logger.getLogger(UserCommandHandler.class.getName());
	
	private TIntObjectHashMap<IUserCommandHandler> _datatable;
	
	public static UserCommandHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private UserCommandHandler()
	{
		_datatable = new TIntObjectHashMap<IUserCommandHandler>();
	}
	
	public void registerUserCommandHandler(IUserCommandHandler handler)
	{
		int[] ids = handler.getUserCommandList();
		for (int i = 0; i < ids.length; i++)
		{
			if (Config.DEBUG)
				_log.fine("Adding handler for user command " + ids[i]);
			_datatable.put(ids[i], handler);
		}
	}
	
	public IUserCommandHandler getUserCommandHandler(int userCommand)
	{
		if (Config.DEBUG)
			_log.fine("getting handler for user command: " + userCommand);
		return _datatable.get(userCommand);
	}
	
	/**
	 * @return
	 */
	public int size()
	{
		return _datatable.size();
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final UserCommandHandler _instance = new UserCommandHandler();
	}
}
