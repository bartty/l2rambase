/**
 * @author L2Rambase Project
 */
package handlers.chathandlers;

import java.util.Collection;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.MapRegionTable;
import l2.rambase.gameserver.handler.IChatHandler;
import l2.rambase.gameserver.model.BlockList;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;


/**
 * Trade chat handler.
 *
 */
public class ChatTrade implements IChatHandler
{
	private static final int[] COMMAND_IDS =
	{
		8
	};
	
	/**
	 * Handle chat type 'trade'
	 * @see l2.rambase.gameserver.handler.IChatHandler#handleChat(int, l2.rambase.gameserver.model.actor.instance.L2PcInstance, java.lang.String)
	 */
	public void handleChat(int type, L2PcInstance activeChar, String target, String text)
	{
		CreatureSay cs = new CreatureSay(activeChar.getObjectId(), type, activeChar.getName(), text);
		
		Collection<L2PcInstance> pls = L2World.getInstance().getAllPlayers().values();
		
		if (Config.DEFAULT_TRADE_CHAT.equalsIgnoreCase("on") || (Config.DEFAULT_TRADE_CHAT.equalsIgnoreCase("gm") && activeChar.isGM()))
		{
			int region = MapRegionTable.getInstance().getMapRegion(activeChar.getX(), activeChar.getY());
			
			if (!activeChar.isGM() && !activeChar.getFloodProtectors().getGlobalChat().tryPerformAction("global chat"))
			{
				activeChar.sendMessage("Do not spam trade channel wait moment to write next message.");
				return;
			}
			
			if (text.startsWith("WTB") 
					|| text.startsWith("WTT") 
					|| text.startsWith("WTS") 
					|| text.startsWith("WTC")
					|| text.startsWith("LFB") 
					|| text.startsWith("LFT") 
					|| text.startsWith("LFS") 
					|| text.startsWith("LFC"))
			{			
				for (L2PcInstance player : pls)
				{
					if (region == MapRegionTable.getInstance().getMapRegion(player.getX(), player.getY()) && !BlockList.isBlocked(player, activeChar) && player.getInstanceId() == activeChar.getInstanceId())
						player.sendPacket(cs);
				}
			}
			else
			{
				activeChar.sendMessage("Your message must start with:");
				activeChar.sendMessage("* WTB(Want to Buy)");
				activeChar.sendMessage("* WTS(Want to Sell)");
				activeChar.sendMessage("* WTC(Want to Craft)");
				activeChar.sendMessage("* WTT(Want to Exchange)");
				activeChar.sendMessage("* LFB(Looking for Buy)");
				activeChar.sendMessage("* LFS(Looking for Sell)");
				activeChar.sendMessage("* LFC(Looking for Craft)");
				activeChar.sendMessage("Others chats that not have trade options are prohibited and rewarded with jail.");
				activeChar.sendMessage("For party members looking use Party Matching");
			}
		}
		else if (Config.DEFAULT_TRADE_CHAT.equalsIgnoreCase("global"))
		{
			if (!activeChar.isGM() && !activeChar.getFloodProtectors().getGlobalChat().tryPerformAction("global chat"))
			{
				activeChar.sendMessage("Do not spam trade channel wait moment to write next message.");
				return;
			}
			if (text.startsWith("WTB") 
					|| text.startsWith("WTT") 
					|| text.startsWith("WTS") 
					|| text.startsWith("WTC")
					|| text.startsWith("LFB") 
					|| text.startsWith("LFT") 
					|| text.startsWith("LFS") 
					|| text.startsWith("LFC"))
			{	
				for (L2PcInstance player : pls)
				{
					if (!BlockList.isBlocked(player, activeChar))
						player.sendPacket(cs);
				}
			}
			else
			{
				activeChar.sendMessage("Your message must start with:");
				activeChar.sendMessage("* WTB(Want to Buy)");
				activeChar.sendMessage("* WTS(Want to Sell)");
				activeChar.sendMessage("* WTC(Want to Craft)");
				activeChar.sendMessage("* WTT(Want to Exchange)");
				activeChar.sendMessage("* LFB(Looking for Buy)");
				activeChar.sendMessage("* LFS(Looking for Sell)");
				activeChar.sendMessage("* LFC(Looking for Craft)");
				activeChar.sendMessage("Others chats that not have trade options are prohibited and rewarded with jail.");
				activeChar.sendMessage("For party members looking use Party Matching");				
			}
		}
	}
	
	/**
	 * Returns the chat types registered to this handler
	 * @see l2.rambase.gameserver.handler.IChatHandler#getChatTypeList()
	 */
	public int[] getChatTypeList()
	{
		return COMMAND_IDS;
	}
}