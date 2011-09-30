/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class AllyCrest extends L2GameServerPacket
{
	private static final String _S__AF_ALLYCREST = "[S] af AllyCrest";
	private int _crestId;
	private int _crestSize;
	private byte[] _data;

	public AllyCrest(int crestId,byte[] data)
	{
		_crestId = crestId;
        _data = data;
        _crestSize = _data.length;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xaf);
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
		return _S__AF_ALLYCREST;
	}
}