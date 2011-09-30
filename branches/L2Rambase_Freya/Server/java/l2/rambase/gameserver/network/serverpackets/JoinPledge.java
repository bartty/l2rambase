/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class JoinPledge extends L2GameServerPacket
{
	private static final String _S__45_JOINPLEDGE = "[S] 2d JoinPledge";

	private int _pledgeId;

	public JoinPledge(int pledgeId)
	{
		_pledgeId = pledgeId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x2d);

		writeD(_pledgeId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__45_JOINPLEDGE;
	}
}