/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.SevenSigns;


public class ShowMiniMap extends L2GameServerPacket
{
	private static final String _S__A3_SHOWMINIMAP = "[S] a3 ShowMiniMap";
	private int _mapId;

	public ShowMiniMap(int mapId)
	{
		_mapId = mapId;
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xa3);
		writeD(_mapId);
		writeC(SevenSigns.getInstance().getCurrentPeriod());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A3_SHOWMINIMAP;
	}
}