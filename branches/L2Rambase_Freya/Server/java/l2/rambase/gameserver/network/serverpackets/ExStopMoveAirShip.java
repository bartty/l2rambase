/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class ExStopMoveAirShip extends L2GameServerPacket
{
	final int _objectId, _x, _y, _z, _heading;

    public ExStopMoveAirShip(L2Character ship)
    {
		_objectId = ship.getObjectId();
		_x = ship.getX();
		_y = ship.getY();
		_z = ship.getZ();
		_heading = ship.getHeading();
    }

    @Override
	protected final void writeImpl()
    {
        writeC(254);
        writeH(102);
        writeD(_objectId);
        writeD(_x);
        writeD(_y);
        writeD(_z);
        writeD(_heading);
    }

    /* (non-Javadoc)
     * @see ru.l2nw.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return "[S] FE:66 ExStopMoveAirShip";
    }
}