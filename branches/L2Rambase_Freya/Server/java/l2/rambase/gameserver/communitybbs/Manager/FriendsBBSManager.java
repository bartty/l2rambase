/**
 * Friends BBS Manager
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs.Manager;

import java.util.StringTokenizer;
import javolution.text.TextBuilder;
import javolution.util.FastMap;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.base.Experience;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.network.serverpackets.ShowBoard;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public class FriendsBBSManager extends BaseBBSManager
{
	private FastMap<Integer,FastMap<Integer,String>> friendPage = new FastMap<Integer,FastMap<Integer,String>>();
	
    @Override
    public void parsecmd(String command, L2PcInstance activeChar)
    {
        if (command.equals("_friendlist_0_"))
        {
        	writeFriendPages(activeChar);
        	showFriendCommunity(activeChar, 0);
        }
        else if (command.startsWith("_friendlist_0_;playerdelete;"))
        {
            StringTokenizer st = new StringTokenizer(command, ";");
            st.nextToken();
            st.nextToken();
            String name = st.nextToken();
            activeChar.getFriendList().remove(CharNameTable.getInstance().getIdByName(name));
            showFriendsList(activeChar,1);
        }
        else if (command.startsWith("_friendlist_0_;playeradd;"))
        {
        	StringTokenizer st = new StringTokenizer(command, ";");
            st.nextToken();
            st.nextToken();
            String name = st.nextToken();
            activeChar.getFriendList().add(CharNameTable.getInstance().getIdByName(name));
            showFriendsList(activeChar,1);
        }
        else if (command.startsWith("_friendlist_0_;playerinfo;"))
        {
            StringTokenizer st = new StringTokenizer(command, ";");
            st.nextToken();
            st.nextToken();
            String name = st.nextToken();           
            showFriendsPI(activeChar, name);
        }
        else
        {
        	if (command.startsWith("_friendlist_0_;page;"))
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
    			writeFriendPages(activeChar);
    			showFriendCommunity(activeChar, page);
        	}
        	else
        	{
        		ShowBoard sb = new ShowBoard("<html><body><br><br><center>Sorry wrong page... Error 404</center><br><br></body></html>","101");
                activeChar.sendPacket(sb);
                activeChar.sendPacket(new ShowBoard(null,"102"));
                activeChar.sendPacket(new ShowBoard(null,"103"));
        	}
        }
    }

    private void showFriendCommunity(L2PcInstance activeChar, int page) 
    {
		showFriendsList(activeChar,page);	
	}

	private void writeFriendPages(L2PcInstance activeChar) 
    {
		FastMap<Integer,String> _communityPages = new FastMap<Integer,String>();		
    	int i = 0;
    	String ListCode = "";
    	int _friendPage = 0;
		for(int _friendId : activeChar.getFriendList())
		{
			i = i + 1;    			
			String friendName = CharNameTable.getInstance().getNameById(_friendId);    		
			L2PcInstance friend = (L2PcInstance)L2World.getInstance().findObject(_friendId);
			if (friend == null)
			{    				
				ListCode += "<table border=0 cellspacing=0 cellpadding=0 width=610>";
				ListCode += "<tr>";
				ListCode += "<td valign=middle align=center height=30 width=150>"+friendName+"</td>";
				ListCode += "<td valign=middle align=center height=30 width=150><font color=FF0000>OFFLINE</font></td>";
				ListCode += "<td valign=middle align=center height=30 width=150> </td>";
				ListCode += "<td valign=middle align=center height=30 width=150> </td>";
				ListCode += "</tr>";
				ListCode += "</table>";
				ListCode += "<img src=\"L2UI.SquareGray\" width=\"610\" height=\"1\">";
			}
			else
			{    				
				ListCode += "<table border=0 cellspacing=0 cellpadding=0 width=610>";
				ListCode += "<tr>";
				ListCode += "<td valign=middle align=center height=30 width=150><a action=\"bypass _friendlist_0_;playerinfo;" + friendName + "\">"+friendName+"</a></td>";
				ListCode += "<td valign=middle align=center height=30 width=150><font color=00FF00>ONLINE</font></td>";
				ListCode += "<td valign=middle align=center height=30 width=150>"+ getRegionName(friend) +"</td>";
				ListCode += "<td valign=middle align=center height=30 width=150><a action=\"bypass _friendlist_0_;playerdelete;" + friendName + "\">DELETE</a></td>";
				ListCode += "</tr>";
				ListCode += "</table>";
				ListCode += "<img src=\"L2UI.SquareGray\" width=\"610\" height=\"1\">";
			}
			if (activeChar.getFriendList().size() < 10)
			{
				if (activeChar.getFriendList().size() == i)
				{
					_communityPages.put(_friendPage,ListCode);
					ListCode = "";
				}
			}
			if (_friendPage > 0)
			{
				if (activeChar.getFriendList().size() < ((_friendPage+1) * 10))
				{
					if ((activeChar.getFriendList().size() - ((_friendPage)* 10)) == i)
					{
						ListCode += sendPagesList(_friendPage,Integer.valueOf(activeChar.getFriendList().size() / 10));
						_communityPages.put(_friendPage,ListCode);
						ListCode = "";
					}
				}
			}
			if (i >= 10)
			{
				ListCode += sendPagesList(_friendPage,Integer.valueOf(activeChar.getFriendList().size() / 10));
				_communityPages.put(_friendPage,ListCode);
				ListCode = "";
				_friendPage = _friendPage +1;
				i = 0;
			}								
		}
		friendPage.put(activeChar.getObjectId(), _communityPages);
	}

	/**
     * @param activeChar
     * @param name
     */
    private void showFriendsPI(L2PcInstance activeChar, String name)
    {
    	String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Friend/showFriendInfo.htm");
    	L2PcInstance player = L2World.getInstance().getPlayer(name);
    	if (player == null)
    	{
    		ShowBoard sb = new ShowBoard("<html><body><br><br><center>No player with name "+name+"</center><br><br></body></html>","101");
            activeChar.sendPacket(sb);
            activeChar.sendPacket(new ShowBoard(null,"102"));
            activeChar.sendPacket(new ShowBoard(null,"103"));
    	}
    	else
    	{
    		String sex = "Male";
    		if (player.getAppearance().getSex())
            {
                sex = "Female";
            }
    	   	content = content.replaceAll("%player_sex%", sex);
    	   	String levelApprox = "low";
            if (player.getLevel() >= 60)
                levelApprox = "very high";
            else if (player.getLevel() >= 40)
                levelApprox = "high";
            else if (player.getLevel() >= 20)
                levelApprox = "medium";
            content = content.replaceAll("%player_level_cap%", levelApprox);
            content = content.replaceAll("%player_name%",player.getName());
            content = content.replaceAll("%player_class%", player.getTemplate().className);
            if (activeChar != null && (activeChar.isGM() || player.getObjectId() == activeChar.getObjectId()
                    || Config.SHOW_LEVEL_COMMUNITYBOARD))
            {
            	long nextLevelExp = 0;
                long nextLevelExpNeeded = 0;
                if (player.getLevel() < 75)
                {
                    nextLevelExp = Experience.LEVEL[player.getLevel() + 1];
                    nextLevelExpNeeded = nextLevelExp-player.getExp();
                }
            	content = content.replaceAll("%player_level%", String.valueOf(player.getLevel()));
            	content = content.replaceAll("%player_exp%", String.valueOf(player.getExp()));
            	content = content.replaceAll("%player_next_level_exp%",String.valueOf(nextLevelExp));
            	content = content.replaceAll("%player_needed_exp%", String.valueOf(nextLevelExpNeeded));
            }
            else
            {
            	content = content.replaceAll("%player_level%", "<font color=FF0000>PROTECTED</font>");
            	content = content.replaceAll("%player_exp%", "<font color=FF0000>PROTECTED</font>");
            	content = content.replaceAll("%player_next_level_exp%","<font color=FF0000>PROTECTED</font>");
            	content = content.replaceAll("%player_needed_exp%", "<font color=FF0000>PROTECTED</font>");
            }
            int uptime = (int)player.getUptime()/1000;
            int h = uptime/3600;
            int m = (uptime-(h*3600))/60;
            int s = ((uptime-(h*3600))-(m*60));
            content = content.replaceAll("%player_uptime_h%",String.valueOf(h));
            content = content.replaceAll("%player_uptime_m%",String.valueOf(m));
            content = content.replaceAll("%player_uptime_s%",String.valueOf(s));
            if (player.getClan() != null)
            {
            	content = content.replaceAll("%player_clan%",player.getClan().getName());
            }
            else
            {
            	content = content.replaceAll("%player_clan%","<font color=FF0000> no clan </font>");
            }
            separateAndSend(content,activeChar);
        }
    }

    /**
     * @param activeChar
     */
    private void showFriendsList(L2PcInstance activeChar,int page)
    {    	
    	String content = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/Friend/showFriendList.htm");
    	String friendListInfo = "";
    	if (activeChar.getFriendList().isEmpty())
    	{
        	friendListInfo = "<font color=FF0000>Your Friends List is empty.</font><br>";
    	}
        else
        {
        	friendListInfo = "<font color=LEVEL>Your Friends List:</font><br>";
        }
    	content = content.replaceAll("%friendlist_info%", friendListInfo);    	
    	String friendListCode = "";    	
    	if (!activeChar.getFriendList().isEmpty())
    	{
    		friendListCode += "<table border=0 cellspacing=0 cellpadding=0 bgcolor=5A5A5A width=610>";
    		friendListCode += "<tr>";
        	friendListCode += "<td valign=middle align=center height=30 width=150><font color=LEVEL>NAME</font></td>";
        	friendListCode += "<td valign=middle align=center height=30 width=150><font color=LEVEL>STATUS</font></td>";
        	friendListCode += "<td valign=middle align=center height=30 width=150><font color=LEVEL>NEAR</font></td>";
        	friendListCode += "<td valign=middle align=center height=30 width=150><font color=LEVEL>OPTIONS</font></td>";
        	friendListCode += "</tr>";
        	friendListCode += "</table>";
        	FastMap<Integer,String> _communityPages = new FastMap<Integer,String>();
        	_communityPages = friendPage.get(activeChar.getObjectId());
        	friendListCode += _communityPages.get(page);
    	}
    	content = content.replaceAll("%friendlist%",friendListCode);
    	separateAndSend(content,activeChar);    	
    }

    private String sendPagesList(int page, int maxPages) 
    {
    	String text = "<center><table><tr>";
    	for(int i=0 ;i <= maxPages;i++)
    	{
    		if (i == page)
    		{
    			text +="<td>";
    			text += String.valueOf(page+1);
    			text += "</td>";
    		}
    		else
    		{
    			text += "<td><a action=\"bypass _friendlist_0_;page;" + String.valueOf(i) + "\"> "+ String.valueOf(i+1) + " </a></td>";
    		}
    	}
    	text += "</tr></table></center>";
		return text;
	}

	private String getRegionName(L2PcInstance friend) 
    {    	
    	String TownName = "";
    	try
    	{
    		TownName = MapRegionTable.getInstance().getClosestTownName(MapRegionTable.getInstance().getMapRegion(friend.getX(), friend.getY()));
    	}
    	catch (Exception e)
    	{
    		TownName = "???";
    	}
		return TownName;
	}

	/* (non-Javadoc)
     * @see net.sf.l2j.gameserver.communitybbs.Manager.BaseBBSManager#parsewrite(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, net.sf.l2j.gameserver.model.actor.instance.L2PcInstance)
     */
    @Override
    public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
    {
        if (activeChar == null)
            return;
       
        if (ar1.equals("PM"))
        {           
            TextBuilder htmlCode = new TextBuilder("<html><body><br>");
                htmlCode.append("<table border=0><tr><td FIXWIDTH=15></td><td align=center>L2 Community Board<img src=\"sek.cbui355\" width=610 height=1></td></tr><tr><td FIXWIDTH=15></td><td>");

                try
                {
                   
                    L2PcInstance reciever = L2World.getInstance().getPlayer(ar2);
                    if (reciever == null)
                    {
                        htmlCode.append("Player not found!<br><button value=\"Back\" action=\"bypass _friendlist_0_;playerinfo;"+ar2+"\" width=40 height=15 back=\"sek.cbui94\" fore=\"sek.cbui92\">");
                        htmlCode.append("</td></tr></table></body></html>");
                        separateAndSend(htmlCode.toString(),activeChar);
                        return;
                    }                   
                    
                    CreatureSay cs = new CreatureSay(activeChar.getObjectId(), Say2.TELL, activeChar.getName(), ar3);
                    if (!reciever.getMessageRefusal())
                    {
                        reciever.sendPacket(cs);
                        activeChar.sendPacket(cs);
                        htmlCode.append("Message Sent<br><button value=\"Back\" action=\"bypass _friendlist_0_;playerinfo;"+reciever.getName()+"\" width=40 height=15 back=\"sek.cbui94\" fore=\"sek.cbui92\">");
                        htmlCode.append("</td></tr></table></body></html>");
                        separateAndSend(htmlCode.toString(),activeChar)  ;
                    }
                    else
                    {
                        SystemMessage sm = new SystemMessage(SystemMessageId.THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE);       
                        activeChar.sendPacket(sm);
                        parsecmd("_friendlist_0_;playerinfo;"+reciever.getName(), activeChar);
                    }
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    // ignore
                }
        }
        else
        {
            ShowBoard sb = new ShowBoard("<html><body><br><br><center>the command: "+ar1+" is not implemented yet</center><br><br></body></html>","101");
            activeChar.sendPacket(sb);
            activeChar.sendPacket(new ShowBoard(null,"102"));
            activeChar.sendPacket(new ShowBoard(null,"103")); 
        }
    }
    private static FriendsBBSManager _instance = null;
    /**
     * @return
     */
    public static FriendsBBSManager getInstance()
    {
        if(_instance == null)
        {
            _instance = new FriendsBBSManager();
        }
        return _instance;
    }
} 