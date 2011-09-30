/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import gnu.trove.TIntObjectHashMap;

import java.util.logging.Logger;

import l2.rambase.Config;


public class ChatHandler
{
	private static Logger _log = Logger.getLogger(ChatHandler.class.getName());
	
	private TIntObjectHashMap<IChatHandler> _datatable;
	
	public static ChatHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	/**
	 * Singleton constructor
	 */
	private ChatHandler()
	{
		_datatable = new TIntObjectHashMap<IChatHandler>();
	}
	
	/**
	 * Register a new chat handler
	 * @param handler
	 */
	public void registerChatHandler(IChatHandler handler)
	{
		int[] ids = handler.getChatTypeList();
		for (int i = 0; i < ids.length; i++)
		{
			if (Config.DEBUG)
				_log.fine("Adding handler for chat type " + ids[i]);
			_datatable.put(ids[i], handler);
		}
	}
	
	/**
	 * Get the chat handler for the given chat type
	 * @param chatType
	 * @return
	 */
	public IChatHandler getChatHandler(int chatType)
	{
		return _datatable.get(chatType);
	}
	
	/**
	 * Returns the size
	 * @return
	 */
	public int size()
	{
		return _datatable.size();
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ChatHandler _instance = new ChatHandler();
	}
}