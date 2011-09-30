/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class ExStopMoveInAirShip extends L2GameServerPacket
{
	private L2Character _activeChar;
	private int _shipObjId;


	public ExStopMoveInAirShip(L2Character player, int shipObjId)
    {
    	_activeChar = player;
    	_shipObjId = shipObjId;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(254);
        writeH(110);
        writeD(_activeChar.getObjectId());
        writeD(_shipObjId);
        writeD(_activeChar.getX());
        writeD(_activeChar.getY());
        writeD(_activeChar.getZ());
        writeD(_activeChar.getHeading());
    }

    /* (non-Javadoc)
     * @see ru.l2nw.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return "[S] FE:6e ExStopMoveAirShip";
    }
}