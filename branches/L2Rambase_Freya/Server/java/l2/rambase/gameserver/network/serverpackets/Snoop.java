/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class Snoop extends L2GameServerPacket
{
	private static final String _S__D5_SNOOP = "[S] db Snoop";
	private int _convoId;
	private String _name;
	private int _type;
	private String _speaker;
	private String _msg;

	public Snoop(int id, String name, int type, String speaker, String msg)
	{
		_convoId = id;
		_name = name;
		_type = type;
		_speaker = speaker;
		_msg = msg;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xdb);
		writeD(_convoId);
		writeS(_name);
		writeD(0x00); //??
		writeD(_type);
		writeS(_speaker);
		writeS(_msg);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__D5_SNOOP;
	}
}