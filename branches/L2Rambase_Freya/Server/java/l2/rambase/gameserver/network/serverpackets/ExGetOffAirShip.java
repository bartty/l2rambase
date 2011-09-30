/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class ExGetOffAirShip extends L2GameServerPacket
{
	private static final String _S__FE_63_EXGETOFFAIRSHIP = "[S] FE:63 ExGetOffAirShip";

	private final int _playerId, _airShipId, _x, _y, _z;

	public ExGetOffAirShip(L2Character player, L2Character ship, int x, int y, int z)
	{
		_playerId = player.getObjectId();
		_airShipId = ship.getObjectId();
		_x = x;
		_y = y;
		_z = z;
	}

	@Override
	protected void writeImpl()
	{
		writeC(254);
		writeH(100);

		writeD(_playerId);
		writeD(_airShipId);
		writeD(_x);
		writeD(_y);
		writeD(_z);
	}

	@Override
	public String getType()
	{
		return _S__FE_63_EXGETOFFAIRSHIP;
	}
}