/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.util.Point3D;


public class ExGetOnAirShip extends L2GameServerPacket
{
	private static final String _S__FE_63_EXGETONAIRSHIP = "[S] FE:63 ExGetOnAirShip";

	private final int _playerId, _airShipId;
	private final Point3D _pos;

	public ExGetOnAirShip(L2PcInstance player, L2Character ship)
	{
		_playerId = player.getObjectId();
		_airShipId = ship.getObjectId();
		_pos = player.getInVehiclePosition();
	}

	@Override
	protected void writeImpl()
	{
		writeC(254);
		writeH(99);

		writeD(_playerId);
		writeD(_airShipId);
		writeD(_pos.getX());
		writeD(_pos.getY());
		writeD(_pos.getZ());
	}

	@Override
	public String getType()
	{
		return _S__FE_63_EXGETONAIRSHIP;
	}
}