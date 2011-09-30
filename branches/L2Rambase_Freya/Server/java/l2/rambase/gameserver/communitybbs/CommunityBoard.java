/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs;

import l2.rambase.Config;
import l2.rambase.gameserver.communitybbs.Manager.ClanBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.FriendsBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.MailBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.PostBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.RegionBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.TopBBSManager;
import l2.rambase.gameserver.communitybbs.Manager.TopicBBSManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public class CommunityBoard
{
	private CommunityBoard()
	{
	}
	
	public static CommunityBoard getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public void handleCommands(L2GameClient client, String command)
	{
		L2PcInstance activeChar = client.getActiveChar();
		if (activeChar == null)
			return;
		if (Config.COMMUNITY_BOARD_ONLINE)
		{
			if (command.startsWith("_bbsclan"))
			{
				ClanBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("bbs_add_fav"))
			{
				//FIXME: CommunityBoard - fix bbs_add_fav
			}
			else if (command.startsWith("_bbsmemo"))
			{
				TopicBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_bbstopics"))
			{
				TopicBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_bbsposts"))
			{
				PostBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_bbstop"))
			{
				TopBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_bbshome"))
			{
				TopBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_bbsloc"))
			{
				RegionBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_maillist_0_1_0_"))
			{
				MailBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if(command.startsWith("_bbsgetfav"))
			{
				//FIXME: CommunityBoard - fix bbsgetfav
				TopBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else if (command.startsWith("_friendlist_0_"))
			{
				FriendsBBSManager.getInstance().parsecmd(command, activeChar);
			}
			else
			{
				TopBBSManager.getInstance().parsecmd(command, activeChar);
			}
		}
		else
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.CB_OFFLINE);       
            activeChar.sendPacket(sm);			
		}
	}
	
	/**
	 * @param client
	 * @param url
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 */
	public void handleWriteCommands(L2GameClient client, String url, String arg1, String arg2, String arg3, String arg4, String arg5)
	{
		L2PcInstance activeChar = client.getActiveChar();
		if (activeChar == null)
			return;
		
		if (url.equals("Topic"))
		{
			TopicBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
		else if (url.equals("Post"))
		{
			PostBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
		else if (url.equals("Region"))
		{
			RegionBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
		else if (url.equals("Notice"))
		{
			ClanBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
		else if (url.equals("Mail"))
		{
			MailBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
		else
		{
			TopBBSManager.getInstance().parsewrite(arg1, arg2, arg3, arg4, arg5, activeChar);
		}
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final CommunityBoard _instance = new CommunityBoard();
	}
}
