/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.communitybbs.CommunityBoard;

/**
 * Format SSSSSS
 *
 */
public final class RequestBBSwrite extends L2GameClientPacket
{
	private static final String _C__22_REQUESTBBSWRITE = "[C] 22 RequestBBSwrite";
	private String _url;
	private String _arg1;
	private String _arg2;
	private String _arg3;
	private String _arg4;
	private String _arg5;

	@Override
	protected final void readImpl()
	{
		_url = readS();
		_arg1 = readS();
		_arg2 = readS();
		_arg3 = readS();
		_arg4 = readS();
		_arg5 = readS();
	}

	@Override
	protected final void runImpl()
	{
		CommunityBoard.getInstance().handleWriteCommands(getClient(),_url,_arg1,_arg2, _arg3, _arg4, _arg5);
	}

	@Override
	public final String getType()
	{
		return _C__22_REQUESTBBSWRITE;
	}
}