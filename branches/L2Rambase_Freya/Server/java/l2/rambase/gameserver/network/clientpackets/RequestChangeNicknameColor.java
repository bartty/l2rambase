/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class RequestChangeNicknameColor extends L2GameClientPacket
{
	private static final int COLORS[] =
	{
		0x9393FF,	// Pink
		0x7C49FC,	// Rose Pink
		0x97F8FC,	// Lemon Yellow
		0xFA9AEE,	// Lilac
		0xFF5D93,	// Cobalt Violet
		0x00FCA0,	// Mint Green
		0xA0A601,	// Peacock Green
		0x7898AF,	// Yellow Ochre
		0x486295,	// Chocolate
		0x999999	// Silver
	};

	private int _colorNum, _itemObjectId;
	private String _title;

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
		_colorNum = readD();
		_title = readS();
		_itemObjectId = readD();
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (_colorNum < 0 || _colorNum >= COLORS.length)
			return;

		final L2ItemInstance item = activeChar.getInventory().getItemByObjectId(_itemObjectId);
		if (item == null
				|| item.getEtcItem() == null
				|| !item.getEtcItem().getHandlerName().equalsIgnoreCase("NicknameColor"))
			return;

		if (activeChar.destroyItem("Consume", item, 1, null, true))
		{
			activeChar.setTitle(_title);
			activeChar.getAppearance().setTitleColor(COLORS[_colorNum]);
			activeChar.broadcastUserInfo();
		}
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:4F RequestChangeNicknameColor";
	}
}
