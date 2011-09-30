/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap; 
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;


public class BypassHandler
{
	private static Logger _log = Logger.getLogger(BypassHandler.class.getName());

	private TIntObjectHashMap<IBypassHandler> _datatable;

	public static BypassHandler getInstance()
	{
		return SingletonHolder._instance;
	}

	private BypassHandler()
	{
		_datatable = new TIntObjectHashMap<IBypassHandler>();
	}

	public void registerBypassHandler(IBypassHandler handler)
	{
		for (String element : handler.getBypassList())
		{
			if (Config.DEBUG)
				_log.log(Level.FINE, "Adding handler for command " + element);

			_datatable.put(element.toLowerCase().hashCode(), handler);
		}
	}

	public IBypassHandler getBypassHandler(String BypassCommand)
	{
		String command = BypassCommand;

		if (BypassCommand.indexOf(" ") != -1)
		{
			command = BypassCommand.substring(0, BypassCommand.indexOf(" "));
		}

		if (Config.DEBUG)
			_log.log(Level.FINE, "getting handler for command: " + command + " -> " + (_datatable.get(command.hashCode()) != null));

		return _datatable.get(command.toLowerCase().hashCode());
	}

	public int size()
	{
		return _datatable.size();
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final BypassHandler _instance = new BypassHandler();
	}
}