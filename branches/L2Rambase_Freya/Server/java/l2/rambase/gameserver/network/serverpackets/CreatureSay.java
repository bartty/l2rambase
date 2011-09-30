/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class CreatureSay extends L2GameServerPacket
{
	private static final String _S__4A_CREATURESAY = "[S] 4A CreatureSay";
	private int _objectId;
	private int _textType;
	private String _charName = null;
	private int _charId = 0;
	private String _text = null;
	private int _msgId = 0;

	/**
	 * @param _characters
	 */
	public CreatureSay(int objectId, int messageType, String charName, String text)
	{
		_objectId = objectId;
		_textType = messageType;
		_charName = charName;
		_text = text;
	}

	public CreatureSay(int objectId, int messageType, int charId, int msgId)
	{
		_objectId = objectId;
		_textType = messageType;
		_charId = charId;
		_msgId = msgId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x4a);
		writeD(_objectId);
		writeD(_textType);
		if (_charName != null)
			writeS(_charName);
		else
			writeD(_charId);
		if (_text != null)
			writeS(_text);
		else
			writeD(_msgId);
	}

	@Override
	public final void runImpl()
	{
		L2PcInstance _pci = getClient().getActiveChar();
		if (_pci != null)
		{
			_pci.broadcastSnoop(_textType,_charName,_text);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__4A_CREATURESAY;
	}
}