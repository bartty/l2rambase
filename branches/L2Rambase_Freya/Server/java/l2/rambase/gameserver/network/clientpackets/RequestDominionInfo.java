/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.network.serverpackets.ExReplyDominionInfo;
import l2.rambase.gameserver.network.serverpackets.ExShowOwnthingPos;


public class RequestDominionInfo extends L2GameClientPacket
{
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:58 RequestDominionInfo";
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
		sendPacket(new ExReplyDominionInfo());
		sendPacket(new ExShowOwnthingPos());
	}
}
