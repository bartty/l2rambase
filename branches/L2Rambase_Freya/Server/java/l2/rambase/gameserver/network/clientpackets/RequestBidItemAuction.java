/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.ItemAuctionManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemauction.ItemAuction;
import l2.rambase.gameserver.model.itemauction.ItemAuctionInstance;
import l2.rambase.gameserver.model.itemcontainer.PcInventory;

public final class RequestBidItemAuction extends L2GameClientPacket
{
	private int _instanceId;
	private long _bid;
	
	@Override
	protected final void readImpl()
	{
		_instanceId = super.readD();
		_bid = super.readQ();
	}
	
	@Override
	protected final void runImpl()
	{
		final L2PcInstance activeChar = super.getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		// can't use auction fp here
		if (!activeChar.getFloodProtectors().getTransaction().tryPerformAction("auction"))
		{
			activeChar.sendMessage("You bidding too fast.");
			return;
		}
		
		if (_bid < 0 || _bid > PcInventory.MAX_ADENA)
			return;
		
		final ItemAuctionInstance instance = ItemAuctionManager.getInstance().getManagerInstance(_instanceId);
		if (instance != null)
		{
			final ItemAuction auction = instance.getCurrentAuction();
			if (auction != null)
				auction.registerBid(activeChar, _bid);
		}
	}
	
	@Override
	public final String getType()
	{
		return "[C] D0:39 RequestBidItemAuction";
	}
}