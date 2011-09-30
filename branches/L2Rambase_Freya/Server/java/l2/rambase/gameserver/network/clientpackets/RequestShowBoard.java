/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.communitybbs.CommunityBoard;


public final class RequestShowBoard extends L2GameClientPacket
{
	private static final String _C__57_REQUESTSHOWBOARD = "[C] 57 RequestShowBoard";

	@SuppressWarnings("unused")
    private int _unknown;

	/**
	 * packet type id 0x57
	 *
	 * sample
	 *
	 * 57
	 * 01 00 00 00		// unknown (always 1?)
	 *
	 * format:		cd
	 * @param decrypt
	 */
	@Override
	protected final void readImpl()
	{
		_unknown = readD();
	}

	@Override
	protected void runImpl()
	{
		CommunityBoard.getInstance().handleCommands(getClient(), Config.BBS_DEFAULT);
	}

	@Override
	public final String getType()
	{
		return _C__57_REQUESTSHOWBOARD;
	}
}
