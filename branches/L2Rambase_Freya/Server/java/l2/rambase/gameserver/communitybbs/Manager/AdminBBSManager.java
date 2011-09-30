/**
 * AdminBBSManager
 * FIXME: AdminBBSManager - add list of players
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs.Manager;

import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ShowBoard;

public class AdminBBSManager extends BaseBBSManager
{
	/**
	 * @return
	 */
	public static AdminBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public class AdminCommands
	{
		String adminCommand;
		String accessLevels;
	}
	
	private AdminBBSManager()
	{
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.communitybbs.Manager.BaseBBSManager#parsecmd(java.lang.String, l2.rambase.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (!activeChar.isGM())
		{
			return;
		}
		if (command.startsWith("admin_bbs"))
		{
			String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/admin.htm");
			if (content == null)
			{
				content = "<html><body><br><br><center>404 :File Not foud: 'data/html/CommunityBoard/admin.htm' </center></body></html>";
			}
			separateAndSend(content, activeChar);
		}
		else
		{
			
			ShowBoard sb = new ShowBoard("<html><body><br><br><center>the command: " + command
					+ " is not implemented yet</center><br><br></body></html>", "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
		
	}
	
	/**
	 * 
	 * @see l2.rambase.gameserver.communitybbs.Manager.BaseBBSManager#parsewrite(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, l2.rambase.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (!activeChar.isGM())
		{
			return;
		}
		
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final AdminBBSManager _instance = new AdminBBSManager();
	}
}