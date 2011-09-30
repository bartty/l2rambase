/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExPutEnchantTargetItemResult;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public class RequestExTryToPutEnchantTargetItem extends AbstractEnchantPacket
{

	private int _objectId = 0;

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:4F RequestExTryToPutEnchantTargetItem";
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
		_objectId = readD();
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();

		if (_objectId == 0)
			return;

		if (activeChar != null)
		{
			if (activeChar.isEnchanting())
				return;

			L2ItemInstance item = (L2ItemInstance) L2World.getInstance().findObject(_objectId);
			L2ItemInstance scroll = activeChar.getActiveEnchantItem();

			if (item == null || scroll == null)
				return;

			// template for scroll
			EnchantScroll scrollTemplate = getEnchantScroll(scroll);

			if (!scrollTemplate.isValid(item) || !isEnchantable(item))
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.DOES_NOT_FIT_SCROLL_CONDITIONS));
				activeChar.setActiveEnchantItem(null);
				activeChar.sendPacket(new ExPutEnchantTargetItemResult(0));
				return;
			}
			activeChar.setIsEnchanting(true);
			activeChar.setActiveEnchantTimestamp(System.currentTimeMillis());
			activeChar.sendPacket(new ExPutEnchantTargetItemResult(_objectId));
		}
	}
}
