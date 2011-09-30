/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * Format: (ch)cs
 * c: message type (0 - gm, 1 - finish, 2 - start, 3 - game over, 4 - 1, 5 - 2, 6 - 3, 7 - 4, 8 - 5)
 * s: message text (only when type is 0 - gm)
 */
public class ExEventMatchMessage extends L2GameServerPacket
{
	private static final String _S__FE_0F_EXEVENTMATCHMESSAGE = "[S] FE:0f ExEventMatchMessage";
	private int _type;
	private String _message;
	
	/**
	 * Create an event match message.
	 * @param type 0 - gm, 1 - finish, 2 - start, 3 - game over, 4 - 1, 5 - 2, 6 - 3, 7 - 4, 8 - 5
	 * @param message message to show, only when type is 0 - gm
	 */
	public ExEventMatchMessage(int type, String message)
	{
		_type = type;
		_message = message;
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x0f);
		writeC(_type);
		writeS(_message);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_0F_EXEVENTMATCHMESSAGE;
	}
}