/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class AskJoinPledge extends L2GameServerPacket
{
	private static final String _S__44_ASKJOINPLEDGE = "[S] 2c AskJoinPledge";

	private int _requestorObjId;
	private String _subPledgeName;
	private int _pledgeType;
	private String _pledgeName;

	public AskJoinPledge(int requestorObjId, String subPledgeName, int pledgeType, String pledgeName)
	{
		_requestorObjId = requestorObjId;
		_subPledgeName = subPledgeName;
		_pledgeType = pledgeType;
		_pledgeName = pledgeName;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x2c);
		writeD(_requestorObjId);
		if (_subPledgeName != null)
			writeS(_pledgeType > 0 ? _subPledgeName : _pledgeName);
		if (_pledgeType != 0)
			writeD(_pledgeType);
		writeS(_pledgeName);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__44_ASKJOINPLEDGE;
	}
}