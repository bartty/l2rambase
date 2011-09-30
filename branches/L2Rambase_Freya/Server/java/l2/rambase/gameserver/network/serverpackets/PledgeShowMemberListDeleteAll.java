/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class PledgeShowMemberListDeleteAll extends L2GameServerPacket
{
	private static final String _S__9B_PLEDGESHOWMEMBERLISTDELETEALL = "[S] 88 PledgeShowMemberListDeleteAll";

	public PledgeShowMemberListDeleteAll()
	{
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x88);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__9B_PLEDGESHOWMEMBERLISTDELETEALL;
	}
}