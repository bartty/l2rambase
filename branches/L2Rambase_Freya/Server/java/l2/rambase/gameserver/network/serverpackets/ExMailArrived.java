/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExMailArrived extends L2GameServerPacket
{
	private static final String _S__FE_2D_EXMAILARRIVED = "[S] FE:2e ExMailArrived";
	public static final ExMailArrived STATIC_PACKET = new ExMailArrived();

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected
	void writeImpl()
	{
		writeC(0xfe);
		writeH(0x2e);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_2D_EXMAILARRIVED;
	}
}