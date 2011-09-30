/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap;
import java.util.logging.Logger;

import l2.rambase.Config;


public class AdminCommandHandler
{
	private static Logger _log = Logger.getLogger(AdminCommandHandler.class.getName());
	
	private TIntObjectHashMap<IAdminCommandHandler> _datatable;
	
	public static AdminCommandHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private AdminCommandHandler()
	{
		_datatable = new TIntObjectHashMap<IAdminCommandHandler>();
	}
	
	public void registerAdminCommandHandler(IAdminCommandHandler handler)
	{
		String[] ids = handler.getAdminCommandList();
		for (int i = 0; i < ids.length; i++)
		{
			if (Config.DEBUG)
				_log.fine("Adding handler for command " + ids[i]);
			_datatable.put(ids[i].hashCode(), handler);
		}
	}
	
	public IAdminCommandHandler getAdminCommandHandler(String adminCommand)
	{
		String command = adminCommand;
		if (adminCommand.indexOf(" ") != -1)
		{
			command = adminCommand.substring(0, adminCommand.indexOf(" "));
		}
		if (Config.DEBUG)
			_log.fine("getting handler for command: " + command + " -> " + (_datatable.get(command.hashCode()) != null));
		return _datatable.get(command.hashCode());
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
		protected static final AdminCommandHandler _instance = new AdminCommandHandler();
	}
}
