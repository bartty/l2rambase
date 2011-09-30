/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class PremiumState extends L2GameServerPacket
{
	private static final String _S__FE_AA_EXGETBOOKMARKINFO = "[S] FE:AA PremiumState";
	private int _objectId;
	private int _state;
	
	public PremiumState(int objectId, int state)
	{
		_objectId = objectId;
		_state = state;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xaa);
		writeD(_objectId);
		writeC(_state);
	}
	
	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_AA_EXGETBOOKMARKINFO;
	}
}