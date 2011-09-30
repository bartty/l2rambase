/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public interface IChatHandler
{
	/**
	 * Handles a specific type of chat messages
	 * @param type
	 * @param activeChar
	 * @param target
	 */
	public void handleChat(int type, L2PcInstance activeChar, String target, String text);
	
	/**
	 * Returns a list of all chat types registered to this handler
	 * @return
	 */
	public int[] getChatTypeList();
}