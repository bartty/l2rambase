/**
 * RegionBBSManager
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs.Manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.BlockList;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.base.Experience;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.network.serverpackets.ShowBoard;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.util.StringUtil;

public class RegionBBSManager extends BaseBBSManager
{
	private static Logger _logChat = Logger.getLogger("chat");
	
	private RegionBBSManager()
	{
	}
	
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (command.equals("_bbsloc"))
		{
			showCommunity(activeChar, 1);
		}
		else if (command.startsWith("_bbsloc;page;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			st.nextToken();
			int page = 0;
			try
			{
				page = Integer.parseInt(st.nextToken());
			}
			catch (NumberFormatException nfe)
			{
			}
			
			showCommunity(activeChar, page);
		}
		else if (command.startsWith("_bbsloc;playerinfo;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			st.nextToken();
			String name = st.nextToken();
			
			showPlayerInfo(activeChar, name);
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
	 * @param activeChar
	 * @param name
	 */
	private void showPlayerInfo(L2PcInstance activeChar, String name)
	{
		final StringBuilder htmlCode = StringUtil.startAppend(1000, "<html><body><br>"
				+ "<table border=0><tr><td FIXWIDTH=15></td><td align=center>Server Community Board<img src=\"sek.cbui355\" width=610 height=1></td></tr><tr><td FIXWIDTH=15></td><td>");
		L2PcInstance player = L2World.getInstance().getPlayer(name);
		
		if (player != null)
		{
			String sex = "Male";
			if (player.getAppearance().getSex())
			{
				sex = "Female";
			}
			String levelApprox = "low";
			if (player.getLevel() >= 60)
				levelApprox = "very high";
			else if (player.getLevel() >= 40)
				levelApprox = "high";
			else if (player.getLevel() >= 20)
				levelApprox = "medium";
			
			StringUtil.append(htmlCode, "<table border=0><tr><td>", player.getName(), " (", sex, " ", player.getTemplate().className, "):</td></tr>"
					+ "<tr><td>Level: ", levelApprox, "</td></tr>" + "<tr><td><br></td></tr>");
			
			if (activeChar != null
					&& (activeChar.isGM() || player.getObjectId() == activeChar.getObjectId() || Config.SHOW_LEVEL_COMMUNITYBOARD))
			{
				long nextLevelExp = 0;
				long nextLevelExpNeeded = 0;
				if (player.getLevel() < (Experience.MAX_LEVEL - 1))
				{
					nextLevelExp = Experience.LEVEL[player.getLevel() + 1];
					nextLevelExpNeeded = nextLevelExp - player.getExp();
				}
				
				StringUtil.append(htmlCode, "<tr><td>Level: ", String.valueOf(player.getLevel()), "</td></tr>" + "<tr><td>Experience: ", String.valueOf(player.getExp()), "/", String.valueOf(nextLevelExp), "</td></tr>"
						+ "<tr><td>Experience needed for level up: ", String.valueOf(nextLevelExpNeeded), "</td></tr>"
						+ "<tr><td><br></td></tr>");
			}
			
			int uptime = (int) player.getUptime() / 1000;
			int h = uptime / 3600;
			int m = (uptime - (h * 3600)) / 60;
			int s = ((uptime - (h * 3600)) - (m * 60));
			
			StringUtil.append(htmlCode, "<tr><td>Uptime: ", String.valueOf(h), "h ", String.valueOf(m), "m ", String.valueOf(s), "s</td></tr>"
					+ "<tr><td><br></td></tr>");
			
			if (player.getClan() != null)
			{
				StringUtil.append(htmlCode, "<tr><td>Clan: ", player.getClan().getName(), "</td></tr>" + "<tr><td><br></td></tr>");
			}
			
			StringUtil.append(htmlCode, "<tr><td><multiedit var=\"pm\" width=240 height=40><button value=\"Send PM\" action=\"Write Region PM ", player.getName(), " pm pm pm\" width=110 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr><tr><td><br><button value=\"Back\" action=\"bypass _bbsloc\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr></table>"
					+ "</td></tr></table>" + "</body></html>");
			separateAndSend(htmlCode.toString(), activeChar);
		}
		else
		{
			ShowBoard sb = new ShowBoard(StringUtil.concat("<html><body><br><br><center>No player with name ", name, "</center><br><br></body></html>"), "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
	}
	
	/**
	 * @param activeChar
	 */
	private void showCommunity(L2PcInstance activeChar, int page)
	{
		separateAndSend(getCommunityPage(page,activeChar.isGM() ? "gm" : "pl"), activeChar);
	}
	
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null)
			return;
		
		if (ar1.equals("PM"))
		{
			final StringBuilder htmlCode = StringUtil.startAppend(500, "<html><body><br>"
					+ "<table border=0><tr><td FIXWIDTH=15></td><td align=center>Server Community Board<img src=\"sek.cbui355\" width=610 height=1></td></tr><tr><td FIXWIDTH=15></td><td>");
			
			try
			{
				
				L2PcInstance receiver = L2World.getInstance().getPlayer(ar2);
				if (receiver == null)
				{
					StringUtil.append(htmlCode, "Player not found!<br><button value=\"Back\" action=\"bypass _bbsloc;playerinfo;", ar2, "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">"
							+ "</td></tr></table></body></html>");
					separateAndSend(htmlCode.toString(), activeChar);
					return;
				}
				if (Config.JAIL_DISABLE_CHAT && receiver.isInJail())
				{
					activeChar.sendMessage("Player is in jail.");
					return;
				}
				if (receiver.isChatBanned())
				{
					activeChar.sendMessage("Player is chat banned.");
					return;
				}
				if (activeChar.isInJail() && Config.JAIL_DISABLE_CHAT)
				{
					activeChar.sendMessage("You can not chat while in jail.");
					return;
				}
				if (activeChar.isChatBanned())
				{
					activeChar.sendMessage("You are banned from using chat");
					return;
				}
				
				if (Config.LOG_CHAT)
				{
					LogRecord record = new LogRecord(Level.INFO, ar3);
					record.setLoggerName("chat");
					record.setParameters(new Object[] { "TELL", "[" + activeChar.getName() + " to " + receiver.getName() + "]" });
					_logChat.log(record);
				}
				CreatureSay cs = new CreatureSay(activeChar.getObjectId(), Say2.TELL, activeChar.getName(), ar3);
				if (!receiver.isSilenceMode() && !BlockList.isBlocked(receiver, activeChar) ) 
				{ 
					receiver.sendPacket(cs); 
					activeChar.sendPacket(new CreatureSay(activeChar.getObjectId(), Say2.TELL, "->" + receiver.getName(), ar3)); 
					StringUtil.append(htmlCode, "Message Sent<br><button value=\"Back\" action=\"bypass _bbsloc;playerinfo;", receiver.getName(), "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">" 
							+ "</td></tr></table></body></html>"); 
					separateAndSend(htmlCode.toString(), activeChar);
				}
				else
				{
					SystemMessage sm = new SystemMessage(SystemMessageId.THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE);
					activeChar.sendPacket(sm);
					parsecmd("_bbsloc;playerinfo;" + receiver.getName(), activeChar);
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				// ignore
			}
		}
		else
		{
			ShowBoard sb = new ShowBoard(StringUtil.concat("<html><body><br><br><center>the command: ", ar1, " is not implemented yet</center><br><br></body></html>"), "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
		
	}
	private int _onlineCount = 0;
	private int _onlineCountGm = 0;
	private static FastMap<Integer, FastList<L2PcInstance>> _onlinePlayers = new FastMap<Integer, FastList<L2PcInstance>>().shared();
	private static FastMap<Integer, FastMap<String, String>> _communityPages = new FastMap<Integer, FastMap<String, String>>().shared();
	
	/**
	 * @return
	 */
	public static RegionBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public synchronized void changeCommunityBoard()
	{
		FastList<L2PcInstance> sortedPlayers = new FastList<L2PcInstance>();
		//synchronized (L2World.getInstance().getAllPlayers())
		{
			sortedPlayers.addAll(L2World.getInstance().getAllPlayers().values());
		}
		
		Collections.sort(sortedPlayers, new Comparator<L2PcInstance>() {
			public int compare(L2PcInstance p1, L2PcInstance p2)
			{
				return p1.getName().compareToIgnoreCase(p2.getName());
			}
		});
		
		_onlinePlayers.clear();
		_onlineCount = 0;
		_onlineCountGm = 0;
		
		for (L2PcInstance player : sortedPlayers)
		{
			addOnlinePlayer(player);
		}
		
		_communityPages.clear();
		writeCommunityPages();
	}
	
	private void addOnlinePlayer(L2PcInstance player)
	{
		boolean added = false;
		
		for (FastList<L2PcInstance> page : _onlinePlayers.values())
		{
			if (page.size() < Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
			{
				if (!page.contains(player))
				{
					page.add(player);
					if (!player.getAppearance().getInvisible())
						_onlineCount++;
					if (!player.getAppearance().getInvisible())
						_onlineCountGm++;
				}
				added = true;
				break;
			}
			else if (page.contains(player))
			{
				added = true;
				break;
			}
		}
		
		if (!added)
		{
			FastList<L2PcInstance> temp = new FastList<L2PcInstance>();
			int page = _onlinePlayers.size() + 1;
			if (temp.add(player))
			{
				_onlinePlayers.put(page, temp);
				if (!player.getAppearance().getInvisible())
					_onlineCount++;
				if (!player.getAppearance().getInvisible())
					_onlineCountGm++;
			}
		}
	}
	
	public String showRegionInfo(int page)
	{
		String content = HtmCache.getInstance().getHtm(null, "data/html/CommunityBoard/Region/showRegionInfo.htm");
		
		/*
		 * Owners of Castles
		 */
		String GludioMaster = "";
		String DionMaster = "";
		String GiranMaster = "";
		String OrenMaster = "";
		String AdenMaster = "";
		String InnadrilMaster = "";
		String GoddardMaster = "";
		String RuneMaster = "";
		String SchuttgartMaster = "";
		
		int GludioOwner = CastleManager.getInstance().getCastleById(1).getOwnerId();
		int DionOwner = CastleManager.getInstance().getCastleById(2).getOwnerId();
		int GiranOwner = CastleManager.getInstance().getCastleById(3).getOwnerId();
		int OrenOwner = CastleManager.getInstance().getCastleById(4).getOwnerId();
		int AdenOwner = CastleManager.getInstance().getCastleById(5).getOwnerId();
		int InnadrilOwner = CastleManager.getInstance().getCastleById(6).getOwnerId();
		int GoddardOwner = CastleManager.getInstance().getCastleById(7).getOwnerId();
		int RuneOwner = CastleManager.getInstance().getCastleById(8).getOwnerId();
		int SchuttgartOwner = CastleManager.getInstance().getCastleById(9).getOwnerId();
		
		String GludioTax = String.valueOf(CastleManager.getInstance().getCastleById(1).getTaxPercent());
		String DionTax = String.valueOf(CastleManager.getInstance().getCastleById(2).getTaxPercent());
		String GiranTax = String.valueOf(CastleManager.getInstance().getCastleById(3).getTaxPercent());
		String OrenTax = String.valueOf(CastleManager.getInstance().getCastleById(4).getTaxPercent());
		String AdenTax = String.valueOf(CastleManager.getInstance().getCastleById(5).getTaxPercent());
		String InnadrilTax = String.valueOf(CastleManager.getInstance().getCastleById(6).getTaxPercent());
		String GoddardTax = String.valueOf(CastleManager.getInstance().getCastleById(7).getTaxPercent());
		String RuneTax = String.valueOf(CastleManager.getInstance().getCastleById(8).getTaxPercent());
		String SchuttgartTax = String.valueOf(CastleManager.getInstance().getCastleById(9).getTaxPercent());
		
		if (GludioOwner == 0)
		{
			GludioMaster = "NPC";
		}
		else
		{
			GludioMaster = ClanTable.getInstance().getClan(GludioOwner).getName();
		}		
		if (DionOwner == 0)
		{
			DionMaster = "NPC";
		}
		else
		{
			DionMaster = ClanTable.getInstance().getClan(DionOwner).getName();
		}		
		if (GiranOwner == 0)
		{
			GiranMaster = "NPC";
		}
		else
		{
			GiranMaster = ClanTable.getInstance().getClan(GiranOwner).getName();
		}		
		if (OrenOwner == 0)
		{
			OrenMaster = "NPC";
		}
		else
		{
			OrenMaster = ClanTable.getInstance().getClan(OrenOwner).getName();
		}		
		if (AdenOwner == 0)
		{
			AdenMaster = "NPC";
		}
		else
		{
			AdenMaster = ClanTable.getInstance().getClan(AdenOwner).getName();
		}
		if (InnadrilOwner == 0)
		{
			InnadrilMaster = "NPC";
		}
		else
		{
			InnadrilMaster = ClanTable.getInstance().getClan(InnadrilOwner).getName();
		}
		if (GoddardOwner == 0)
		{
			GoddardMaster = "NPC";
		}
		else
		{
			GoddardMaster = ClanTable.getInstance().getClan(GoddardOwner).getName();
		}		
		if (RuneOwner == 0)
		{
			RuneMaster = "NPC";
		}
		else
		{
			RuneMaster = ClanTable.getInstance().getClan(RuneOwner).getName();
		}		
		if (SchuttgartOwner == 0)
		{
			SchuttgartMaster = "NPC";
		}
		else
		{
			SchuttgartMaster = ClanTable.getInstance().getClan(SchuttgartOwner).getName();
		}
		
		content = content.replaceAll("%gludio_master%",GludioMaster);
		content = content.replaceAll("%dion_master%",DionMaster);
		content = content.replaceAll("%giran_master%",GiranMaster);
		content = content.replaceAll("%oren_master%",OrenMaster);
		content = content.replaceAll("%aden_master%",AdenMaster);
		content = content.replaceAll("%innadril_master%",InnadrilMaster);
		content = content.replaceAll("%goddard_master%",GoddardMaster);
		content = content.replaceAll("%rune_master%",RuneMaster);
		content = content.replaceAll("%schuttgart_master%",SchuttgartMaster);
		
		content = content.replaceAll("%gludio_tax%",GludioTax +"%");
		content = content.replaceAll("%dion_tax%",DionTax + "%");
		content = content.replaceAll("%giran_tax%",GiranTax + "%");
		content = content.replaceAll("%oren_tax%",OrenTax + "%");
		content = content.replaceAll("%aden_tax%",AdenTax + "%");
		content = content.replaceAll("%innadril_tax%",InnadrilTax + "%");
		content = content.replaceAll("%goddard_tax%",GoddardTax + "%");
		content = content.replaceAll("%rune_tax%",RuneTax + "%");
		content = content.replaceAll("%schuttgart_tax%",SchuttgartTax + "%");
		
		//now online type info
		// low = 0 - 100
		//medium = 100 - 200
		//high = 200 - 500
		//very high = 500+ 
		/*
		 * Get info about online count
		 */
		String OnlineType = "";
		if (getOnlineCount("pl") < 50)
		{
			OnlineType ="<font color=FF0000>LOW</font>";
		}
		else if (getOnlineCount("pl") < 100 && getOnlineCount("pl") >= 50)
		{
			OnlineType = "<font color=LEVEL>MEDIUM</font>";
		}
		else if (getOnlineCount("pl") < 500 && getOnlineCount("pl") >= 100)
		{
			OnlineType = "<font color=CC3A11>HIGH</font>";
		}		
		else if (getOnlineCount("pl") < 1000 && getOnlineCount("pl")>= 500)
		{
			OnlineType = "<font color=AA0013>VERY HIGH</font>";
		}
		else
		{
			OnlineType = "<font color=FF6666>CROWDED</font>";
		}			
		content = content.replaceAll("%online_type%",OnlineType);
		content = content.replaceAll("%rate_exp%", String.valueOf(Config.RATE_XP));
		content = content.replaceAll("%rate_sp%", String.valueOf(Config.RATE_SP));
		content = content.replaceAll("%rate_drop%", String.valueOf(Config.RATE_DROP_ITEMS));
		content = content.replaceAll("%rate_spoil%", String.valueOf(Config.RATE_DROP_SPOIL));		
		content = content.replaceAll("%rate_adena%", String.valueOf(Config.RATE_DROP_ITEMS_ID.get(57)));
		content = content.replaceAll("%rate_raid_drop%", String.valueOf(Config.RATE_DROP_ITEMS_BY_RAID));		
		return content;
	}
	
	private void writeCommunityPages()
	{
		final StringBuilder htmlCode = new StringBuilder(2000);
		final String tdClose = "</td>";
		final String tdOpen = "<td align=left valign=top>";
		final String trClose = "</tr>";
		final String trOpen = "<tr>";
		final String colSpacer = "<td FIXWIDTH=15></td>";
		
		for (int page : _onlinePlayers.keySet())
		{
			FastMap<String, String> communityPage = new FastMap<String, String>();
			htmlCode.setLength(0);
			int cell = 0;
			if (Config.BBS_SHOW_PLAYERLIST)
			{
				StringUtil.append(htmlCode, "<html><body><br>" + "<table>" + trOpen + "<td width=300 align=left valign=top><font color=LEVEL>Online Players:</font>", tdClose
					+ trClose + "</table>");
				htmlCode.append("<table border=0><tr><td><table border=0>");
				
				for (L2PcInstance player : getOnlinePlayers(page))
				{
					cell++;
					
					if (cell == 1)
					{
						htmlCode.append(trOpen);
					}
					
					StringUtil.append(htmlCode, "<td align=left valign=top FIXWIDTH=110><a action=\"bypass _bbsloc;playerinfo;", player.getName(), "\">");
					
					if (player.isGM())
					{
						if (player.getAccessLevel().getLevel() == 127)
						{
							StringUtil.append(htmlCode, "<font color=\"LEVEL\">", player.getName(), "</font>");
						}
						else
						{
							StringUtil.append(htmlCode, "<font color=\"00EE00\">", player.getName(), "</font>");
						}
					}
					else
					{
						if (player.isClanLeader())
						{
							StringUtil.append(htmlCode, "<font color=\"0000EE\">", player.getName(), "</font>");
						}
						else
						{
							htmlCode.append(player.getName());
						}
					}
					
					htmlCode.append("</a></td>");
					
					if (cell < Config.NAME_PER_ROW_COMMUNITYBOARD)
						htmlCode.append(colSpacer);
					
					if (cell == Config.NAME_PER_ROW_COMMUNITYBOARD)
					{
						cell = 0;
						htmlCode.append(trClose);
					}
				}
				if (cell > 0 && cell < Config.NAME_PER_ROW_COMMUNITYBOARD)
				{
					htmlCode.append(trClose);
				}
				
				StringUtil.append(htmlCode, "</table><br></td></tr>" + trOpen + "<td><img src=\"sek.cbui355\" width=600 height=1><br></td>" + trClose
						+ trOpen + tdOpen, String.valueOf(getOnlineCount("gm")), "<font color=13AC46> Player(s) Online</font></td>" + trClose + "</table>");
				if (getOnlineCount("gm") > Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
				{
					htmlCode.append("<table border=0 width=600><tr>");
					if (page == 1)
					{
						htmlCode.append("<td align=right width=190><button value=\"Prev\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					else
					{
						StringUtil.append(htmlCode, "<td align=right width=190><button value=\"Prev\" action=\"bypass _bbsloc;page;", String.valueOf(page - 1), "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					
					StringUtil.append(htmlCode, "<td FIXWIDTH=10></td>" + "<td align=center valign=top width=200>Displaying ", String.valueOf(((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD) + 1), " - ", String.valueOf(((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
							+ getOnlinePlayers(page).size()), " player(s)</td>" + "<td FIXWIDTH=10></td>");
					if (getOnlineCount("gm") <= (page * Config.NAME_PAGE_SIZE_COMMUNITYBOARD))
					{
						htmlCode.append("<td width=190><button value=\"Next\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					else
					{
						StringUtil.append(htmlCode, "<td width=190><button value=\"Next\" action=\"bypass _bbsloc;page;", String.valueOf(page + 1), "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					htmlCode.append("</tr></table>");
				}
				htmlCode.append("</body></html>");
				communityPage.put("gm", htmlCode.toString());
			}
			else
			{
				communityPage.put("gm", showRegionInfo(page));
			}			
			
			htmlCode.setLength(0);
			if (Config.BBS_SHOW_PLAYERLIST)
			{
				StringUtil.append(htmlCode, "<html><body><br>" + "<table>" + trOpen + "<td width=300 align=left valign=top><font color=LEVEL>Online Players:</font>", tdClose
					+ trClose + "</table>");
				htmlCode.append("<table border=0><tr><td><table border=0>");
				
				cell = 0;
				for (L2PcInstance player : getOnlinePlayers(page))
				{
					if ((player == null) || (player.getAppearance().getInvisible()))
						continue; // Go to next
						
					cell++;
					
					if (cell == 1)
					{
						htmlCode.append(trOpen);
					}
					
					StringUtil.append(htmlCode, "<td align=left valign=top FIXWIDTH=110><a action=\"bypass _bbsloc;playerinfo;", player.getName(), "\">");
					
					if (player.isGM())
					{
						if (player.getAccessLevel().getLevel() == 127)
						{
							StringUtil.append(htmlCode, "<font color=\"LEVEL\">", player.getName(), "</font>");
						}
						else
						{
							StringUtil.append(htmlCode, "<font color=\"00EE00\">", player.getName(), "</font>");
						}
					}
					else
					{
						if (player.isClanLeader())
						{
							StringUtil.append(htmlCode, "<font color=\"0000EE\">", player.getName(), "</font>");
						}
						else
						{
							htmlCode.append(player.getName());
						}
					}
					
					htmlCode.append("</a></td>");
					
					if (cell < Config.NAME_PER_ROW_COMMUNITYBOARD)
						htmlCode.append(colSpacer);
					
					if (cell == Config.NAME_PER_ROW_COMMUNITYBOARD)
					{
						cell = 0;
						htmlCode.append(trClose);
					}
				}
				if (cell > 0 && cell < Config.NAME_PER_ROW_COMMUNITYBOARD)
					htmlCode.append(trClose);
				
				StringUtil.append(htmlCode, "</table><br></td></tr>" + trOpen + "<td><img src=\"sek.cbui355\" width=600 height=1><br></td>" + trClose
						+ trOpen + tdOpen, String.valueOf(getOnlineCount("gm")), "<font color=13AC46> Player(s) Online</font></td>" + trClose + "</table>");
				if (getOnlineCount("pl") > Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
				{
					htmlCode.append("<table border=0 width=600><tr>");
					
					if (page == 1)
					{
						htmlCode.append("<td align=right width=190><button value=\"Prev\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					else
					{
						StringUtil.append(htmlCode, "<td align=right width=190><button value=\"Prev\" action=\"bypass _bbsloc;page;", String.valueOf(page - 1), "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					
					StringUtil.append(htmlCode, "<td FIXWIDTH=10></td>" + "<td align=center valign=top width=200>Displaying ", String.valueOf(((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD) + 1), " - ", String.valueOf(((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
							+ getOnlinePlayers(page).size()), " player(s)</td>" + "<td FIXWIDTH=10></td>");
					
					if (getOnlineCount("pl") <= (page * Config.NAME_PAGE_SIZE_COMMUNITYBOARD))
					{
						htmlCode.append("<td width=190><button value=\"Next\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
					else
					{
						StringUtil.append(htmlCode, "<td width=190><button value=\"Next\" action=\"bypass _bbsloc;page;", String.valueOf(page + 1), "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
					}
				
					htmlCode.append("</tr></table>");
					htmlCode.append("</body></html>");
					
					communityPage.put("pl", htmlCode.toString());
				}
			}
			else
			{
				communityPage.put("pl", showRegionInfo(page));
			}
			
			_communityPages.put(page, communityPage);
		}
	}
	
	private int getOnlineCount(String type)
	{
		if (type.equalsIgnoreCase("gm"))
			return _onlineCountGm;
		else
			return _onlineCount;
	}
	
	private FastList<L2PcInstance> getOnlinePlayers(int page)
	{
		return _onlinePlayers.get(page);
	}
	
	public String getCommunityPage(int page, String type)
	{
		if (_communityPages.get(page) != null)
			return _communityPages.get(page).get(type);
		else
			return null;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final RegionBBSManager _instance = new RegionBBSManager();
	}
}