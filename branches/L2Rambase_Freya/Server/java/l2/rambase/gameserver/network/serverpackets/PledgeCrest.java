/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class PledgeCrest extends L2GameServerPacket
{
	private static final String _S__84_PLEDGECREST = "[S] 6a PledgeCrest";
	private int _crestId;
	private int _crestSize;
	private byte[] _data;

	public PledgeCrest(int crestId,byte[] data)
	{
		_crestId = crestId;
        _data = data;
        _crestSize = _data.length;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x6a);
		writeD(_crestId);
		writeD(_crestSize);
		writeB(_data);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__84_PLEDGECREST;
	}
}