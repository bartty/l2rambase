/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExOpenMPCC extends L2GameServerPacket
{

	private static final String _S__FE_25_EXOPENMPCC = "[S] FE:12 ExOpenMPCC";

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x12);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_25_EXOPENMPCC;
	}
}