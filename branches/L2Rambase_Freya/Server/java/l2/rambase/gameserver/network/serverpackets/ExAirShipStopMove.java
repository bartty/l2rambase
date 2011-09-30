/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExAirShipStopMove extends L2GameServerPacket
{
	
	private static final String _S__FE_66_EXAIRSHIPSTOPMOVE = "[S] FE:66 ExAirShipStopMove";

	private final int _playerId, _airShipId, _x, _y, _z;
	
	public ExAirShipStopMove (L2PcInstance player, L2AirShipInstance ship, int x, int y, int z)
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
	    writeH(102);
	    
	    writeD(_airShipId);
	    writeD(_playerId);	    
	    writeD(_x);
	    writeD(_y);
	    writeD(_z);
	}

	@Override
    public String getType()
    {
	    return _S__FE_66_EXAIRSHIPSTOPMOVE;
    }
}