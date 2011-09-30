/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class RestartResponse extends L2GameServerPacket
{
	private static final String _S__74_RESTARTRESPONSE = "[S] 71 RestartResponse";
	private static final RestartResponse STATIC_PACKET_TRUE = new RestartResponse(true);
	private static final RestartResponse STATIC_PACKET_FALSE = new RestartResponse(false);
	
	public static final RestartResponse valueOf(boolean result)
	{
		return result ? STATIC_PACKET_TRUE : STATIC_PACKET_FALSE;
	}
	
	private boolean _result;

	public RestartResponse(boolean result)
	{
		_result = result;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x71);
		writeD(_result ? 1 : 0);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__74_RESTARTRESPONSE;
	}
}