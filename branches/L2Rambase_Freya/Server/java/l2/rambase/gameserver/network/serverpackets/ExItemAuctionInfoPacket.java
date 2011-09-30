/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.ItemInfo;
import l2.rambase.gameserver.model.itemauction.ItemAuction;
import l2.rambase.gameserver.model.itemauction.ItemAuctionBid;
import l2.rambase.gameserver.model.itemauction.ItemAuctionState;

/**
 * Format: (cdqd)(dddqhhhdhhdddhhhhhhhhhhh)(ddd)(dddqhhhdhhdddhhhhhhhhhhh)
 */
public final class ExItemAuctionInfoPacket extends L2GameServerPacket
{
	private final boolean _refresh;
	private final int _timeRemaining;
	private final ItemAuction _currentAuction;
	private final ItemAuction _nextAuction;
	
	public ExItemAuctionInfoPacket(final boolean refresh, final ItemAuction currentAuction, final ItemAuction nextAuction)
	{
		if (currentAuction == null)
			throw new NullPointerException();
		
		if (currentAuction.getAuctionState() != ItemAuctionState.STARTED)
			_timeRemaining = 0;
		else
			_timeRemaining = (int) (currentAuction.getFinishingTimeRemaining() / 1000); // in seconds
		
		_refresh = refresh;
		_currentAuction = currentAuction;
		_nextAuction = nextAuction;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(254);
		writeC(104);
		writeC(0);
		writeC(_refresh ? 0x00 : 0x01);
		writeD(_currentAuction.getInstanceId());
		
		final ItemAuctionBid highestBid = _currentAuction.getHighestBid();
		writeQ(highestBid != null ? highestBid.getLastBid() : _currentAuction.getAuctionInitBid());
		
		writeD(_timeRemaining);
		writeItemInfo(_currentAuction.getItemInfo());
		
		if (_nextAuction != null)
		{
			writeQ(_nextAuction.getAuctionInitBid());
			writeD((int) (_nextAuction.getStartingTime() / 1000)); // unix time in seconds
			writeItemInfo(_nextAuction.getItemInfo());
		}
	}
	
	private final void writeItemInfo(final ItemInfo item)
	{
		writeD(item.getItem().getItemId());
		writeQ(item.getCount());
		writeH(item.getItem().getType2());
		writeH(item.getCustomType1());
		writeD(item.getItem().getBodyPart());
		writeH(item.getEnchant());
		writeH(item.getCustomType2());
		writeD(item.getAugmentationBonus());
		writeD(item.getMana());
		
		writeH(item.getAttackElementType());
		writeH(item.getAttackElementPower());
		for (byte i = 0; i < 6; i++)
			writeH(item.getElementDefAttr(i));
		
		writeH(0x00); // enchant effect 1
		writeH(0x00); // enchant effect 2
		writeH(0x00); // enchant effect 3
	}
	
	@Override
	public final String getType()
	{
		return "[S] fe:68:00 ExItemAuctionInfoPacket";
	}
}