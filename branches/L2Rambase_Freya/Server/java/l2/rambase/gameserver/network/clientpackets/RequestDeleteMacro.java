/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

public final class RequestDeleteMacro extends L2GameClientPacket
{
	private int _id;

	private static final String _C__C2_REQUESTDELETEMACRO = "[C] C2 RequestDeleteMacro";

	@Override
	protected void readImpl()
	{
		_id = readD();
	}

	@Override
	protected void runImpl()
	{
		if (getClient().getActiveChar() == null)
			return;
		getClient().getActiveChar().deleteMacro(_id);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__C2_REQUESTDELETEMACRO;
	}

}
