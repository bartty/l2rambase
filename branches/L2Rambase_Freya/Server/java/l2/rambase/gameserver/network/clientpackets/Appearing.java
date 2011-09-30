/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2.rambase.gameserver.network.serverpackets.UserInfo;

/**
 * Appearing Packet Handler<p>
 * <p>
 * 0000: 30 <p>
 * <p>
 *
 * @version $Revision: 1.3.4.4 $ $Date: 2005/03/29 23:15:33 $
 */
public final class Appearing extends L2GameClientPacket
{
	private static final String _C__30_APPEARING = "[C] 30 Appearing";

	@Override
	protected void readImpl()
	{

	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
        if(activeChar == null) return;
        if (activeChar.isTeleporting()) activeChar.onTeleported();

        sendPacket(new UserInfo(activeChar));
        sendPacket(new ExBrExtraUserInfo(activeChar));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__30_APPEARING;
	}
}
