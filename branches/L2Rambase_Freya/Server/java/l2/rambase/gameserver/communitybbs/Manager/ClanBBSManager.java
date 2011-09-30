/**
 * ClanBBSManager
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs.Manager;

import java.util.StringTokenizer;

import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.StringUtil;


public class ClanBBSManager extends BaseBBSManager
{
	private ClanBBSManager()
	{
	}
	
	/**
	 * @return
	 */
	public static ClanBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	/**
	 * @param command
	 * @param activeChar
	 */
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (command.equals("_bbsclan"))
		{
			if (activeChar.getClan() == null || activeChar.getClan().getLevel() < 2)
				clanlist(activeChar, 1);
			else
				clanhome(activeChar);
		}
		else if (command.startsWith("_bbsclan_clanlist"))
		{
			if (command.equals("_bbsclan_clanlist"))
			{
				clanlist(activeChar, 1);
			}
			else if (command.startsWith("_bbsclan_clanlist;"))
			{
				StringTokenizer st = new StringTokenizer(command, ";");
				st.nextToken();
				int index = Integer.parseInt(st.nextToken());
				clanlist(activeChar, index);
			}
		}
		else if (command.startsWith("_bbsclan_clanhome"))
		{
			if (command.equals("_bbsclan_clanhome"))
			{
				clanhome(activeChar);
			}
			else if (command.startsWith("_bbsclan_clanhome;"))
			{
				StringTokenizer st = new StringTokenizer(command, ";");
				st.nextToken();
				int index = Integer.parseInt(st.nextToken());
				clanhome(activeChar, index);
			}
		}
		else if (command.startsWith("_bbsclan_clannotice_edit;"))
		{
			clanNotice(activeChar, activeChar.getClan().getClanId());
		}
		else if (command.startsWith("_bbsclan_clannotice_enable"))
		{
			activeChar.getClan().setNoticeEnabled(true);
			clanNotice(activeChar, activeChar.getClan().getClanId());
		}
		else if (command.startsWith("_bbsclan_clannotice_disable"))
		{
			activeChar.getClan().setNoticeEnabled(false);
			clanNotice(activeChar, activeChar.getClan().getClanId());
		}
		else
		{
			separateAndSend("<html><body><br><br><center>Command : " + command + " needs core development</center><br><br></body></html>", activeChar);
			
		}
	}
	
	private void clanNotice(L2PcInstance activeChar, int clanId)
	{
		L2Clan cl = ClanTable.getInstance().getClan(clanId);
		if (cl != null)
		{
			if (cl.getLevel() < 2)
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.NO_CB_IN_MY_CLAN));
				parsecmd("_bbsclan_clanlist", activeChar);
			}
			else
			{
				if (activeChar.isClanLeader())
				{
					String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Clan/clanNoticeLeader.htm");
					content = content.replaceAll("%clan_id%", String.valueOf(cl.getClanId()));
					if (activeChar.getClan().isNoticeEnabled())
					{
						content = content.replaceAll("%notice_function%","<tr><td fixwidth=610> Clan Notice Function:&nbsp;&nbsp;&nbsp;on&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<a action=\"bypass _bbsclan_clannotice_disable\">off</a>");
					}
					else
					{
						content = content.replaceAll("%notice_function%","<tr><td fixwidth=610> Clan Notice Function:&nbsp;&nbsp;&nbsp;<a action=\"bypass _bbsclan_clannotice_enable\">on</a>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;off");
					}
					send1001(content, activeChar);
					send1002(activeChar, activeChar.getClan().getNotice(), " ", "0");
				}
				else
				{
					String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Clan/clanNotice.htm");
					content = content.replaceAll("%clan_id%", String.valueOf(cl.getClanId()));
					if (activeChar.getClan().isNoticeEnabled())
					{
						content = content.replaceAll("%clan_notice%","<table border=0 cellspacing=0 cellpadding=0>" + "<tr>"
								+ "<td>The current clan notice:</td>" + "</tr>" + "<tr><td fixwidth=5></td>"
								+ "<td FIXWIDTH=600 align=left>" + activeChar.getClan().getNotice() + "</td>" + "<td fixqqwidth=5></td>"
								+ "</tr>" + "</table>");
					}
					else
					{
						content = content.replaceAll("%clan_notice%","<table border=0 cellspacing=0 cellpadding=0>" + "<tr>"
								+ "<td>The clan notice is disabled.</td>" + "</tr></table>");
					}							
					separateAndSend(content, activeChar);
				}
			}
		}
	}
		
	/**
	 * @param activeChar
	 */
	private void clanlist(L2PcInstance activeChar, int index)
	{
		if (index < 1)
		{
			index = 1;
		}
		L2Clan clan = activeChar.getClan();
		
		String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Clan/clanList.htm");
		if (clan != null)
		{
			content = content.replaceAll("%clan_home_link%","<a action=\"bypass _bbsclan_clanhome;" + String.valueOf((activeChar.getClan() != null) ? activeChar.getClan().getClanId() : 0) + "\">[GO TO MY CLAN]</a>&nbsp;&nbsp;");
		}
		else
		{
			content = content.replaceAll("%clan_home_link%","&nbsp;&nbsp;");
		}
		final StringBuilder listOfClans = StringUtil.startAppend(2000, "");
		int i = 0;
		for (L2Clan cl : ClanTable.getInstance().getClans())
		{
			if (i > (index + 1) * 7)
			{
				break;
			}
			
			if (i++ >= (index - 1) * 7)
			{
				StringUtil.append(listOfClans, "<img src=\"L2UI.SquareBlank\" width=\"610\" height=\"3\">"
						+ "<table border=0 cellspacing=0 cellpadding=0 width=610>" + "<tr> " + "<td FIXWIDTH=5></td>"
						+ "<td FIXWIDTH=200 align=center><a action=\"bypass _bbsclan_clanhome;", String.valueOf(cl.getClanId()), "\">", cl.getName(), "</a></td>"
						+ "<td FIXWIDTH=200 align=center>", cl.getLeaderName(), "</td>" + "<td FIXWIDTH=100 align=center>", String.valueOf(cl.getLevel()), "</td>"
						+ "<td FIXWIDTH=100 align=center>", String.valueOf(cl.getMembersCount()), "</td>" + "<td FIXWIDTH=5></td>"
						+ "</tr>" + "<tr><td height=5></td></tr>" + "</table>"
						+ "<img src=\"L2UI.SquareBlank\" width=\"610\" height=\"3\">"
						+ "<img src=\"L2UI.SquareGray\" width=\"610\" height=\"1\">");
			}
		}
		content = content.replaceAll("%list_of_clans%",listOfClans.toString());
		final StringBuilder listOfPages = StringUtil.startAppend(2000, "");
		if (index == 1)
		{
			listOfPages.append("<td><button action=\"\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		else
		{
			StringUtil.append(listOfPages, "<td><button action=\"_bbsclan_clanlist;", String.valueOf(index - 1), "\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		
		i = 0;
		int nbp;
		nbp = ClanTable.getInstance().getClans().length / 8;
		if (nbp * 8 != ClanTable.getInstance().getClans().length)
		{
			nbp++;
		}
		for (i = 1; i <= nbp; i++)
		{
			if (i == index)
			{
				StringUtil.append(listOfPages, "<td> ", String.valueOf(i), " </td>");
			}
			else
			{
				StringUtil.append(listOfPages, "<td><a action=\"bypass _bbsclan_clanlist;", String.valueOf(i), "\"> ", String.valueOf(i), " </a></td>");
			}
			
		}
		if (index == nbp)
		{
			listOfPages.append("<td><button action=\"\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		else
		{
			StringUtil.append(listOfPages, "<td><button action=\"bypass _bbsclan_clanlist;", String.valueOf(index + 1), "\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		content = content.replaceAll("%list_of_pages",listOfPages.toString());
		separateAndSend(content, activeChar);
	}
	
	/**
	 * @param activeChar
	 */
	private void clanhome(L2PcInstance activeChar)
	{
		clanhome(activeChar, activeChar.getClan().getClanId());
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ClanBBSManager _instance = new ClanBBSManager();
	}
	
	/**
	 * @param activeChar
	 * @param clanId
	 */
	private void clanhome(L2PcInstance activeChar, int clanId)
	{
		L2Clan cl = ClanTable.getInstance().getClan(clanId);
		if (cl != null)
		{
			if (cl.getLevel() < 2)
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.NO_CB_IN_MY_CLAN));
				parsecmd("_bbsclan_clanlist", activeChar);
			}
			else
			{
				if (activeChar.getClanId() == cl.getClanId() && activeChar.isClanLeader())
				{
					String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Clan/clanHomeLeader.htm");
					content = content.replaceAll("%clan_id%",String.valueOf(clanId));
					content = content.replaceAll("%clan_name%",cl.getName());
					content = content.replaceAll("%clan_level%", String.valueOf(cl.getLevel()));
					content = content.replaceAll("%clan_members_count%", String.valueOf(cl.getMembersCount()));
					content = content.replaceAll("%clan_leader_name%",cl.getLeaderName());
					content = content.replaceAll("%clan_ally_name%", (cl.getAllyName() != null) ? cl.getAllyName() : "<font color=FF0000> no alliance </font>");
					content = content.replaceAll("%clan_online_members%", String.valueOf(cl.getOnlineMembersCount()));
					content = content.replaceAll("%clan_reputation%", String.valueOf(cl.getReputationScore()));
					if (activeChar.getClan().isNoticeEnabled())
					{
						String noticeClan = "<font color=LEVEL>NOTICE</font>:<br> " + activeChar.getClan().getNotice().replaceAll("\r\n", "<br>") + "<br>";						
						if (noticeClan != null)
						{
							content = content.replaceAll("%notice_text%",noticeClan);
						}
						else
						{
							content = content.replaceAll("%notice_text%", "");
						}
					}
					else
					{
						content = content.replaceAll("%notice_text%","");
					}
					separateAndSend(content, activeChar);
				}
				else
				{
					String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Clan/clanHome.htm");
					content = content.replaceAll("%clan_id%",String.valueOf(clanId));
					content = content.replaceAll("%clan_name%",cl.getName());
					content = content.replaceAll("%clan_level%", String.valueOf(cl.getLevel()));
					content = content.replaceAll("%clan_members_count%", String.valueOf(cl.getMembersCount()));
					content = content.replaceAll("%clan_leader_name%",cl.getLeaderName());
					content = content.replaceAll("%clan_ally_name%", (cl.getAllyName() != null) ? cl.getAllyName() : "<font color=FF0000> no alliance </font>");
					content = content.replaceAll("%clan_online_members%", String.valueOf(cl.getOnlineMembersCount()));
					content = content.replaceAll("%clan_reputation%", String.valueOf(cl.getReputationScore()));
					if (activeChar.getClanId() == clanId)
					{
						if (activeChar.getClan().isNoticeEnabled())
						{
							String noticeClan = "<font color=LEVEL>NOTICE</font>:<br> " + activeChar.getClan().getNotice().replaceAll("\r\n", "<br>") + "<br>";	
							if (noticeClan != null)
							{
								content = content.replaceAll("%notice_text%",noticeClan);
							}
							else
							{
								content = content.replaceAll("%notice_text%", "");
							}
						}
						else
						{
							content = content.replaceAll("%notice_text%","<font color=LEVEL>NOTICE</font>:<br><font color=FF0000>NO NOTICE</font><br>");
						}
					}
					else
					{
						content = content.replaceAll("%notice_text%", "");
					}
					separateAndSend(content, activeChar);
				}				
			}
		}
	}
	
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{		
		if (ar1.equals("Set"))
		{
			activeChar.getClan().setNotice(ar4);
			parsecmd("_bbsclan_clanhome;" + activeChar.getClan().getClanId(), activeChar);
		}		
	}	
}