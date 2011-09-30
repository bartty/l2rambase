/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class RequestResetNickname extends L2GameClientPacket
{

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
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		activeChar.getAppearance().setTitleColor(0xFFFF77);
		activeChar.setTitle("");
		activeChar.broadcastUserInfo();
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:53 RequestResetNickname";
	}
}
