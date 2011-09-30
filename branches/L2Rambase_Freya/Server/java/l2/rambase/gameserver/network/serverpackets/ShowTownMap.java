/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ShowTownMap extends L2GameServerPacket
{
	private static final String _S__DE_ShowTownMap = "[S] ea ShowTownMap";
	private String _texture;
	private int _x;
	private int _y;

	/**
	 */
	public ShowTownMap(String texture, int x, int y)
	{
		_texture = texture;
		_x = x;
		_y = y;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xea);
		writeS(_texture);
		writeD(_x);
		writeD(_y);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__DE_ShowTownMap;
	}
}