/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ShowXMasSeal extends L2GameServerPacket
{
	private static final String _S__F2_SHOWXMASSEAL = "[S] f8 ShowXMasSeal";
	private int _item;

	public ShowXMasSeal(int item)
	{
		_item = item;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xf8);
		writeD(_item);
	}

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__F2_SHOWXMASSEAL;
	}
}