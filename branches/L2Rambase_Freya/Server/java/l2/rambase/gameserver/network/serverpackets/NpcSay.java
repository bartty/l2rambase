/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class NpcSay extends L2GameServerPacket
{
	private static final String _S__30_NPCSAY = "[S] 30 NpcSay";
	private int _objectId;
	private int _textType;
	private int _npcId;
	private String _text;

	/**
	 * @param _characters
	 */
	public NpcSay(int objectId, int messageType, int npcId, String text)
	{
		_objectId = objectId;
		_textType = messageType;
		_npcId = 1000000+npcId;
		_text = text;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x30);
		writeD(_objectId);
		writeD(_textType);
		writeD(_npcId);
		writeS(_text);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__30_NPCSAY;
	}
}