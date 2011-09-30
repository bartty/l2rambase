/**
 * @author L2Rambase Project
 */

package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.ItemAuctionManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemauction.ItemAuction;
import l2.rambase.gameserver.model.itemauction.ItemAuctionInstance;
import l2.rambase.gameserver.network.serverpackets.ExItemAuctionInfoPacket;

public final class RequestInfoItemAuction extends L2GameClientPacket
{
	private int _instanceId;
	
	@Override
	protected final void readImpl()
	{
		_instanceId = super.readD();
	}
	
	@Override
	protected final void runImpl()
	{
		final L2PcInstance activeChar = super.getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		if (!activeChar.getFloodProtectors().getItemAuction().tryPerformAction("RequestInfoItemAuction"))
			return;
		
		final ItemAuctionInstance instance = ItemAuctionManager.getInstance().getManagerInstance(_instanceId);
		if (instance == null)
			return;
		
		final ItemAuction auction = instance.getCurrentAuction();
		if (auction == null)
			return;
		
		activeChar.updateLastItemAuctionRequest();
		activeChar.sendPacket(new ExItemAuctionInfoPacket(true, auction, instance.getNextAuction()));
	}
	
	@Override
	public final String getType()
	{
		return "[C] D0:3A RequestBidItemAuction";
	}
}