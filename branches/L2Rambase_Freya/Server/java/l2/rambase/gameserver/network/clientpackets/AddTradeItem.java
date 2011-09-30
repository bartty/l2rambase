/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.TradeList;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.network.serverpackets.TradeOtherAdd;
import l2.rambase.gameserver.network.serverpackets.TradeOwnAdd;

/**
 * This class ...
 *
 * @version $Revision: 1.5.2.2.2.5 $ $Date: 2005/03/27 15:29:29 $
 */
public final class AddTradeItem extends L2GameClientPacket
{
	private static final String _C__16_ADDTRADEITEM = "[C] 16 AddTradeItem";
	private static final Logger _log = Logger.getLogger(AddTradeItem.class.getName());
	
	private int _tradeId;
	private int _objectId;
	private long _count;
	
	public AddTradeItem()
	{
	}
	
	@Override
	protected void readImpl()
	{
		_tradeId = readD();
		_objectId = readD();
		_count = readQ();
	}
	
	@Override
	protected void runImpl()
	{
		final L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;
		
		final TradeList trade = player.getActiveTradeList();
		if (trade == null)
		{
			_log.warning("Character: " + player.getName() + " requested item:" + _objectId + " add without active tradelist:" + _tradeId);
			return;
		}
		
		final L2PcInstance partner = trade.getPartner();
		if (partner == null || L2World.getInstance().getPlayer(partner.getObjectId()) == null || partner.getActiveTradeList() == null)
		{
			// Trade partner not found, cancel trade
			if (partner != null)
				_log.warning("Character:" + player.getName() + " requested invalid trade object: " + _objectId);
			SystemMessage msg = new SystemMessage(SystemMessageId.TARGET_IS_NOT_FOUND_IN_THE_GAME);
			player.sendPacket(msg);
			player.cancelActiveTrade();
			return;
		}
		
		if (trade.isConfirmed() || partner.getActiveTradeList().isConfirmed())
		{
			player.sendPacket(new SystemMessage(SystemMessageId.CANNOT_ADJUST_ITEMS_AFTER_TRADE_CONFIRMED));
			return;
		}
		
		if (!player.getAccessLevel().allowTransaction())
		{
			player.sendMessage("Transactions are disable for your Access Level");
			player.cancelActiveTrade();
			return;
		}
		
		if (!player.validateItemManipulation(_objectId, "trade"))
		{
			player.sendPacket(new SystemMessage(SystemMessageId.NOTHING_HAPPENED));
			return;
		}
		
		final TradeList.TradeItem item = trade.addItem(_objectId, _count);
		if (item != null)
		{
			player.sendPacket(new TradeOwnAdd(item));
			trade.getPartner().sendPacket(new TradeOtherAdd(item));
		}
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__16_ADDTRADEITEM;
	}
}
