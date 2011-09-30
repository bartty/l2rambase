/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


/**
 *
 * sample
 * <p>
 * 4c
 * 01 00 00 00
 * <p>
 *
 * format
 * cd
 */
public final class JoinParty extends L2GameServerPacket
{
	private static final String _S__4C_JOINPARTY = "[S] 3a JoinParty";	

	private int _response;

	/**
	 * @param int
	 */
	public JoinParty(int response)
	{
		_response = response;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x3a);
		writeD(_response);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__4C_JOINPARTY;
	}
}