/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExAskJoinMPCC extends L2GameServerPacket
{

	private static final String _S__FE_27_EXASKJOINMPCC = "[S] FE:1a ExAskJoinMPCC";

	private String _requestorName;
	/**
	 * @param String Name of CCLeader
	 */
	public ExAskJoinMPCC(String requestorName)
	{
		_requestorName = requestorName;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x1a);
		writeS(_requestorName);  // name of CCLeader

	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_27_EXASKJOINMPCC;
	}
}