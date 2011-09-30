/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.communitybbs.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;

import l2.rambase.L2DatabaseFactory;
import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.datatables.ItemTable;
import l2.rambase.gameserver.instancemanager.MailManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Message;


public class MailBBSManager extends BaseBBSManager
{
	private static Logger _log = Logger.getLogger(MailBBSManager.class.getName());
	private int _lastid = 0;
	private static final String SELECT_SENDER_ITEMS = "SELECT * FROM items WHERE owner_id = ? AND loc_data = ? AND loc = \"MAIL\" ORDER BY item_id";
	
	// <SenderId, List<Items>>
	private Map<Integer, FastList<Items>> _attachItems;
	
	public class Items
	{
		int owner_id;
		int item_id;
		long count;
		int enchant_level;
		int loc_data;
	}	
		
	public static MailBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private	MailBBSManager() 
	{	
		_attachItems = new FastMap<Integer, FastList<Items>>();
		_log.info(getClass().getSimpleName()+": Initialized. Last mail id: "+_lastid);
	}
	
	/*
	 * FastList of Items
	 */
	private FastList<Items> getAttachItems(int senderId, int messageId)
	{
		FastList<Items> _letters = _attachItems.get(senderId);
		_letters = new FastList<Items>();
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(SELECT_SENDER_ITEMS);
			statement.setInt(1, senderId);
			statement.setInt(2, messageId);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				Items letter = new Items();
				letter.owner_id = result.getInt("owner_id");
				letter.loc_data = result.getInt("loc_data");
				letter.item_id = result.getInt("item_id");
				letter.count = result.getLong("count");
				letter.enchant_level = result.getInt("enchant_level");
				_letters.addFirst(letter);
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			_log.warning("couldnt load items for SenderID:" + senderId + " " + e.getMessage());
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
			}
		}
		_attachItems.put(senderId, _letters);		
		return _letters;
	}
	
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (command.equals("_maillist_0_1_0_"))
			showInbox(activeChar, 1);
		else if (command.equals("_maillist_0_1_0_sentbox"))
			showOutbox(activeChar, 1);
		else if (command.startsWith("_maillist_0_1_0_sentbox "))
			showOutbox(activeChar, Integer.parseInt(command.substring(24)));
		else if (command.startsWith("_maillist_0_1_0_ "))
			showInbox(activeChar, Integer.parseInt(command.substring(17)));		
		else if (command.startsWith("_maillist_0_1_0_view "))
		{
			Message message = MailManager.getInstance().getMessage(Integer.parseInt(command.substring(21)));
			showLetterView(activeChar, message);
			if (MailManager.getInstance().hasUnreadPost(activeChar))
				MailManager.getInstance().markAsReadInDb(Integer.parseInt(command.substring(21)));
		}
		else if (command.startsWith("_maillist_0_1_0_delete "))
		{
			Message message = MailManager.getInstance().getMessage(Integer.parseInt(command.substring(23)));
			if (message != null && message.hasAttachments() == false)
				MailManager.getInstance().deleteMessageInDb(message.getId());
			if (message.hasAttachments() == true)
				activeChar.sendMessage("You cannot delete message with items");
			showLastForum(activeChar);
		}
		else if (command.startsWith("_maillist_0_1_0_viewlist"))
		{
			showLastForum(activeChar);
		}
		else
			activeChar.sendMessage(command + " not implemented yet.");
	}

	private String abbreviate(String s, int maxWidth)
	{
		return s.length() > maxWidth ? s.substring(0, maxWidth) : s;
	}	

	private void showInbox(L2PcInstance activeChar, int page)
	{		
		int countAllMails = MailManager.getInstance().getInboxSize(activeChar.getObjectId());
		int maxpage = getMaxPageId(MailManager.getInstance().getInboxSize(activeChar.getObjectId()));
		if (page > maxpage)
			page = maxpage;
		if (page < 1) page = 1;	
		int index = 0, minIndex = 0, maxIndex = 0;
		maxIndex = (page == 1 ? page * 14 : (page * 15) - 1);
		minIndex = maxIndex - 14;
		final StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("<body><br><br>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=810><tr><td width=10></td><td width=800 height=30 align=left>");		
		html.append("</td></tr>");
		html.append("</table>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=810 bgcolor=808080>");
		html.append("<tr><td height=10></td></tr>");
		html.append("<tr>");
		html.append("<td fixWIDTH=5></td>");
		html.append("<td fixWIDTH=760>");
		html.append("<a action=\"bypass _maillist_0_1_0_\">[|| Inbox ||]</a> (").append(MailManager.getInstance().getInboxSize(activeChar.getObjectId())).append(")&nbsp;").append("<a action=\"bypass _maillist_0_1_0_sentbox\">[|| Outbox ||]</a> (").append(MailManager.getInstance().getOutboxSize(activeChar.getObjectId())).append(")&nbsp;</td>");
		html.append("<td fixWIDTH=5></td>");
		html.append("</tr>");
		html.append("<tr><td height=10></td></tr>");
		html.append("</table>");
		html.append("<br>");
		if (countAllMails > 0)
		{
			html.append("<table border=0 cellspacing=0 cellpadding=2 bgcolor=808080 width=770>");
			html.append("<tr>");
			html.append("<td FIXWIDTH=5></td>");
			html.append("<td FIXWIDTH=250 align=center>Author</td>");
			html.append("<td FIXWIDTH=360 align=left>Title</td>");
			html.append("<td FIXWIDTH=150 align=center> Expiration </td>");
			html.append("<td FIXWIDTH=5></td>");
			html.append("</tr></table>");
			
			for (Message message : MailManager.getInstance().getInbox(activeChar.getObjectId()))
			{				
				if (message.isDeletedByReceiver() == false && message.isDeletedBySender() == false)
				{
					if (index < minIndex)
					{
						index++;
						continue;
					}
					if (index > maxIndex)
						break;
					String tempName = getCharName(message.getSenderId());
					html.append("<table border=0 cellspacing=0 cellpadding=2 width=770>");
					html.append("<tr>");
					html.append("<td FIXWIDTH=5></td>");
					html.append("<td FIXWIDTH=150 align=center>").append(abbreviate(tempName, 6)).append("</td>");
					html.append("<td FIXWIDTH=460 align=left>");
					html.append("<a action=\"bypass _maillist_0_1_0_view ").append(message.getId()).append("\">");
					if (message.isUnread())
						html.append("<font color=LEVEL>");
					html.append(abbreviate(message.getSubject(), 51));
					if (message.isUnread())
						html.append("</font>");
					long countExpiration = ((message.getExpiration() / 360000) / 10000);
					String expireName = "Hours";
					long countExpire = countExpiration;
					if (countExpiration > 24)
					{
						expireName = "Days";
						countExpire = (countExpiration / 24);
					}										
					html.append("</a></td><td FIXWIDTH=150 align=center>").append(String.valueOf(countExpire));
					html.append( "&nbsp;" + expireName + " </td>");
					html.append("<td FIXWIDTH=5></td>");
					html.append("</tr></table>");
					html.append("<img src=\"L2UI.SquareBlank\" width=\"770\" height=\"3\">");
					html.append("<img src=\"L2UI.SquareGrey\" width=\"770\" height=\"1\">");
					index++;
				}				
			}
		}
		else
		{
			html.append("<font color=CC109F> You dont have any mail </font>");
		}
		html.append("<table width=770><tr>");
		html.append("<td align=right></td>");
		html.append("</tr></table>");
		html.append("<center><table  cellpadding=0 cellspacing=2 border=0><tr>");
		html.append("<td><button action=\"bypass _maillist_0_1_0_ ").append(page == 1 ? page : page - 1).append("\" width=16 height=16 back=\"L2UI_ct1.button_df_left_down\" fore=\"L2UI_ct1.button_df_left\"></td>");
		for (int i = 1; i <= maxpage; i++)
		{
			if (i == page)
				html.append("<td>"+i+"</td>");
			else
				html.append("<td><a action=\"bypass _maillist_0_1_0_ ").append(i).append("\">").append(i).append("</a></td>");
		}
		html.append("<td><button action=\"bypass _maillist_0_1_0_ ").append(page + 1).append("\" width=16 height=16 back=\"L2UI_ct1.button_df_right_down\" fore=\"L2UI_ct1.button_df_right\"></td>");
		html.append("</tr></table>");
		html.append("</center>");
		html.append("</body></html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	private void showOutbox(L2PcInstance activeChar, int page)
	{		
		int countAllMails = MailManager.getInstance().getOutboxSize(activeChar.getObjectId());
		int maxpage = getMaxPageId(MailManager.getInstance().getOutboxSize(activeChar.getObjectId()));
		if (page > maxpage)
			page = maxpage;
		if (page < 1) page = 1;	
		int index = 0, minIndex = 0, maxIndex = 0;
		maxIndex = (page == 1 ? page * 14 : (page * 15) - 1);
		minIndex = maxIndex - 14;
		final StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("<body><br><br>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=810><tr><td width=10></td><td width=800 height=30 align=left>");		
		html.append("</td></tr>");
		html.append("</table>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=810 bgcolor=808080>");
		html.append("<tr><td height=10></td></tr>");
		html.append("<tr>");
		html.append("<td fixWIDTH=5></td>");
		html.append("<td fixWIDTH=760>");
		html.append("<a action=\"bypass _maillist_0_1_0_\">[|| Inbox ||]</a> (").append(MailManager.getInstance().getInboxSize(activeChar.getObjectId())).append(")&nbsp;").append("<a action=\"bypass _maillist_0_1_0_sentbox\">[|| Outbox ||]</a> (").append(MailManager.getInstance().getOutboxSize(activeChar.getObjectId())).append(")&nbsp;</td>");
		html.append("<td fixWIDTH=5></td>");
		html.append("</tr>");
		html.append("<tr><td height=10></td></tr>");
		html.append("</table>");
		html.append("<br>");
		if (countAllMails > 0)
		{
			html.append("<table border=0 cellspacing=0 cellpadding=2 bgcolor=808080 width=770>");
			html.append("<tr>");
			html.append("<td FIXWIDTH=5></td>");
			html.append("<td FIXWIDTH=150 align=center>Author</td>");
			html.append("<td FIXWIDTH=460 align=left>Title</td>");
			html.append("<td FIXWIDTH=150 align=center>Expiration</td>");
			html.append("<td FIXWIDTH=5></td>");
			html.append("</tr></table>");
			
			for (Message message : MailManager.getInstance().getOutbox(activeChar.getObjectId()))
			{				
				if (index < minIndex)
				{
					index++;
					continue;
				}
				if (index > maxIndex)
					break;
				String tempName = getCharName(message.getSenderId());
				html.append("<table border=0 cellspacing=0 cellpadding=2 width=770>");
				html.append("<tr>");
				html.append("<td FIXWIDTH=5></td>");
				html.append("<td FIXWIDTH=150 align=center>").append(abbreviate(tempName, 6)).append("</td>");
				html.append("<td FIXWIDTH=460 align=left>");
				html.append("<a action=\"bypass _maillist_0_1_0_view ").append(message.getId()).append("\">");
				if (message.isUnread())
					html.append("<font color=LEVEL>");
				html.append(abbreviate(message.getSubject(), 51));
				if (message.isUnread())
					html.append("</font>");
				long countExpiration = ((message.getExpiration()) / 360000) / 10000;
				String expireName = "Hours";
				long countExpire = countExpiration;
				if (countExpiration > 24)
				{
					expireName = "Days";
					countExpire = (countExpiration / 24);
				}										
				html.append("</a></td><td FIXWIDTH=150 align=center>").append(String.valueOf(countExpire));
				html.append( "&nbsp;" + expireName + " </td>");
				html.append("<td FIXWIDTH=5></td>");
				html.append("</tr></table>");
				html.append("<img src=\"L2UI.SquareBlank\" width=\"770\" height=\"3\">");
				html.append("<img src=\"L2UI.SquareGrey\" width=\"770\" height=\"1\">");
				index++;
			}		
		}
		else
		{
			html.append("<font color=CC109F> You dont send any mails </font>");
		}
		html.append("<table width=770><tr>");
		html.append("<td align=right></td>");
		html.append("</tr></table>");
		html.append("<center><table  cellpadding=0 cellspacing=2 border=0><tr>");
		html.append("<td><button action=\"bypass _maillist_0_1_0_ ").append(page == 1 ? page : page - 1).append("\" width=16 height=16 back=\"L2UI_ct1.button_df_left_down\" fore=\"L2UI_ct1.button_df_left\"></td>");
		for (int i = 1; i <= maxpage; i++)
		{
			if (i == page)
				html.append("<td>"+i+"</td>");
			else
				html.append("<td><a action=\"bypass _maillist_0_1_0_ ").append(i).append("\">").append(i).append("</a></td>");
		}
		html.append("<td><button action=\"bypass _maillist_0_1_0_ ").append(page + 1).append("\" width=16 height=16 back=\"L2UI_ct1.button_df_right_down\" fore=\"L2UI_ct1.button_df_right\"></td>");
		html.append("</tr></table>");
		html.append("</center>");
		html.append("</body></html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	private void showLetterView(L2PcInstance activeChar, Message message)
	{		
		final StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("<body><br><br>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=770><tr><td width=10></td><td height=30 width=760 align=left>");
		html.append("&nbsp;<a action=\"bypass _maillist_0_1_0_\">[|| Show Mails ||]</a>");
		html.append("</td></tr>");
		html.append("</table>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=770 bgcolor=808080>");
		html.append("<tr><td height=10></td></tr>");
		html.append("<tr>");
		html.append("<td FIXWIDTH=5 height=20></td>");
		html.append("<td FIXWIDTH=160 height=20 align=right>Sender:&nbsp;</td>");
		html.append("<td FIXWIDTH=160 height=20 align=left>").append(getCharName(message.getSenderId())).append("</td>");//
		html.append("<td FIXWIDTH=150 height=20 align=right>Expiration:&nbsp;</td>");
		long countExpiration = ((message.getExpiration()) / 360000) / 10000;
		String expireName = "Hours";
		long countExpire = countExpiration;
		if (countExpiration > 24)
		{
			expireName = "Days";
			countExpire = (countExpiration / 24);
		}										
		html.append("<td FIXWIDTH=150 height=20 align=left>").append(String.valueOf(countExpire)).append("&nbsp;").append(expireName + "</td>");//
		html.append("<td fixWIDTH=5 height=20></td>");
		html.append("</tr><tr>");
		html.append("<td FIXWIDTH=5 height=20></td>");
		html.append("<td FIXWIDTH=100 height=20 align=right>Recipient:&nbsp;</td>");
		html.append("<td FIXWIDTH=360 height=20 align=left>").append(getCharName(message.getReceiverId())).append("</td>");//
		html.append("<td fixWIDTH=5 height=20></td>");
		html.append("</tr><tr>");
		html.append("<td FIXWIDTH=5 height=20></td>");
		html.append("<td FIXWIDTH=100 height=20 align=right>Title:&nbsp;</td>");
		html.append("<td FIXWIDTH=360 height=20 align=left>").append(message.getSubject()).append("</td>");//
		html.append("<td FIXWIDTH=150 height=20></td>");
		html.append("<td FIXWIDTH=150 height=20></td>");
		html.append("<td fixWIDTH=5 height=20></td>");
		html.append("</tr>");
		html.append("</table>");
		html.append("<table width=770><tr>");
		html.append("<td FIXWIDTH=100></td>");
		html.append("<td FIXWIDTH=530>").append(message.getContent()).append("</td>");
		html.append("<td FIXWIDTH=150>");		
		html.append("</td>");
		html.append("</tr></table>");
		html.append("<table align=center><tr><td width=235></td>");
		html.append("<td>");
		if (message.getReqAdena() > 0)
			html.append("<font color=FF0000> This mail require ").append(String.valueOf(message.getReqAdena())).append(" Adena.</font>");	
		if (message.hasAttachments() == true)
		{			
			html.append("&nbsp;&nbsp;<font color=FF0000>Items you can get from Mailbox in Peace Zone.</font>");
			html.append("<table bgcolor=808080>");
			html.append("<tr><td width=150> Item Name: </td><td width=85> Count: </td></tr>");
			FastList<Items> ItemList = getAttachItems(message.getSenderId(), message.getId());
			if (ItemList != null)
			{
				for (Items attachedItems : ItemList)
				{						
					String ItemTemplate = ItemTable.getInstance().getTemplate(attachedItems.item_id).getName();
					html.append("<tr><td><font color=LEVEL>");
					if (attachedItems.enchant_level > 0)
						html.append(ItemTemplate + "&nbsp;(+" + String.valueOf(attachedItems.enchant_level) + ")");
					else
						html.append(ItemTemplate);					
					html.append("</font>");
					html.append("<img src=\"L2UI.SquareBlank\" height=\"3\">");
					html.append("<img src=\"L2UI.SquareGrey\" height=\"1\">");
					html.append("</td>");
					html.append("<td><font color=12C1A6>");
					html.append(String.valueOf(attachedItems.count));
					html.append("</font>");
					html.append("<img src=\"L2UI.SquareBlank\" height=\"3\">");
					html.append("<img src=\"L2UI.SquareGrey\" height=\"1\">");
					html.append("</td></tr>");						
				}
			}
			ItemList = null;			
			html.append("</table>");
		}
		html.append("<img src=\"L2UI.SquareBlank\" width=\"770\" height=\"3\">");
		html.append("<img src=\"L2UI.SquareGrey\" width=\"770\" height=\"1\">");
		html.append("</td></tr></table>");
		html.append("<table width=770><tr>");
		html.append("<td align=left></td>");
		html.append("<td FIXWIDTH=300></td>");
		html.append("<td align=right></td>");
		html.append("<td align=right></td>");
		if (!message.hasAttachments() && message.getReqAdena() < 1)
			html.append("<td align=right><button value=\"Delete\" action=\"bypass _maillist_0_1_0_delete ").append(message.getId()).append("\" width=70 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
		else if (message.getReqAdena() < 1 && message.hasAttachments())
			html.append("<td align=right></td>");
		else if (!message.hasAttachments() && message.getReqAdena() > 0)
			html.append("<td align=right></td>");
		else
			//send nothing
		html.append("</tr></table>");
		html.append("</body></html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	private String getCharName(int charId)
	{
		String name = CharNameTable.getInstance().getNameById(charId);
		return name == null ? "Unknown" : name;
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{		
	}
	
	private void showLastForum(L2PcInstance activeChar)
	{
		int page = 1;
		int type = 1;
		switch (type)
		{
			case 0:
				showInbox(activeChar, page);
				break;
			case 1:
				showOutbox(activeChar, page);
				break;
			default:
				break;
		}
	}	
	
	private int getMaxPageId(int letterCount)
	{
		if (letterCount < 1) 
			return 1; 
		if (letterCount % 15 == 0)
			return letterCount / 15;
		else
			return (letterCount / 15)+1;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final MailBBSManager _instance = new MailBBSManager();
	}
}