/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExGetBookMarkInfoPacket;


/**
 *  Packets Structure: chddd
 */
public final class RequestBookMarkSlotInfo extends L2GameClientPacket
{
	private static final String _C__51_REQUESTBOOKMARKSLOTINFO = "[C] 51 RequestBookMarkSlotInfo";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		player.sendPacket(new ExGetBookMarkInfoPacket(player));
	}


	@Override
	public String getType()
	{
		return _C__51_REQUESTBOOKMARKSLOTINFO;
	}
}
