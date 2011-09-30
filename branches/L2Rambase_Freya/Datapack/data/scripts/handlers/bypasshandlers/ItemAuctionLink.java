package handlers.bypasshandlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import l2.rambase.Config;
import l2.rambase.gameserver.handler.IBypassHandler;
import l2.rambase.gameserver.instancemanager.ItemAuctionManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemauction.ItemAuction;
import l2.rambase.gameserver.model.itemauction.ItemAuctionInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExItemAuctionInfoPacket;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

/**
 * 
 * @author L2Rambase Project
 *
 */
public class ItemAuctionLink implements IBypassHandler
{
	private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
	
	private static final String[] COMMANDS =
	{
		"ItemAuction"
	};
	
	public boolean useBypass(String command, L2PcInstance activeChar, L2Character target)
	{
		if (!(target instanceof L2Npc))
			return false;
		
		if (!Config.ALT_ITEM_AUCTION_ENABLED)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.NO_AUCTION_PERIOD));
			return true;
		}
		
		final ItemAuctionInstance au = ItemAuctionManager.getInstance().getManagerInstance(((L2Npc)target).getNpcId());
		if (au == null)
			return false;
		
		try
		{
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken(); // bypass "ItemAuction"
			if (!st.hasMoreTokens())
				return false;
			
			String cmd = st.nextToken();
			if ("show".equalsIgnoreCase(cmd))
			{
				if (!activeChar.getFloodProtectors().getItemAuction().tryPerformAction("RequestInfoItemAuction"))
					return false;
				
				if (activeChar.isItemAuctionPolling())
					return false;
				
				final ItemAuction currentAuction = au.getCurrentAuction();
				final ItemAuction nextAuction = au.getNextAuction();
				
				if (currentAuction == null)
				{
					activeChar.sendPacket(new SystemMessage(SystemMessageId.NO_AUCTION_PERIOD));
					
					if (nextAuction != null) // used only once when database is empty
						activeChar.sendMessage("The next auction will begin on the " + fmt.format(new Date(nextAuction.getStartingTime())) + ".");
					return true;
				}
				
				activeChar.sendPacket(new ExItemAuctionInfoPacket(false, currentAuction, nextAuction));
			}
			else if ("cancel".equalsIgnoreCase(cmd))
			{
				final ItemAuction[] auctions = au.getAuctionsByBidder(activeChar.getObjectId());
				boolean returned = false;
				for (final ItemAuction auction : auctions)
				{
					if (auction.cancelBid(activeChar))
						returned = true;
				}
				if (!returned)
					activeChar.sendPacket(new SystemMessage(SystemMessageId.NO_OFFERINGS_OWN_OR_MADE_BID_FOR));
			}
			else
				return false;
		}
		catch (Exception e)
		{
			_log.severe("Exception in: " + getClass().getSimpleName() + ":" + e.getMessage());
		}
		
		return true;
	}
	
	public String[] getBypassList()
	{
		return COMMANDS;
	}
}