/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.EnchantResult;

public class RequestExCancelEnchantItem extends L2GameClientPacket
{
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:4E RequestExCancelEnchantItem";
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = this.getClient().getActiveChar();
		if (activeChar != null)
		{
			activeChar.sendPacket(new EnchantResult(2, 0, 0));
			activeChar.setActiveEnchantItem(null);
		}
	}
}
