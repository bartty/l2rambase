/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

/**
 * Format: (ch) d[d]
 */
public class ExCursedWeaponList extends L2GameServerPacket
{
	private static final String _S__FE_45_EXCURSEDWEAPONLIST = "[S] FE:46 ExCursedWeaponList";
	private List<Integer> _cursedWeaponIds;

	public ExCursedWeaponList(List<Integer> cursedWeaponIds)
	{
		_cursedWeaponIds = cursedWeaponIds;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x46);

		writeD(_cursedWeaponIds.size());
		for (int i : _cursedWeaponIds)
		{
			writeD(i);
		}
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_45_EXCURSEDWEAPONLIST;
	}
}