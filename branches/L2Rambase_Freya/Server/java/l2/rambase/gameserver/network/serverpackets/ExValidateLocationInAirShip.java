/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class ExValidateLocationInAirShip extends L2GameServerPacket
{
    private L2PcInstance _activeChar;

    public ExValidateLocationInAirShip(L2Character player)
    {
    	if (!(player instanceof L2PcInstance)) return;

		_activeChar = (L2PcInstance)player;

		if (_activeChar.getAirShip() == null)
        {
        }
    }

    @Override
	protected final void writeImpl()
    {
        writeC(254);
        writeH(111);
        writeD(_activeChar.getObjectId());
        writeD(_activeChar.getAirShip().getObjectId());
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
        return "[S] FE:6F ExValidateLocationInAirShip";
    }
}