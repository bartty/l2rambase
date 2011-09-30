/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;


/**
 * Format: (c) dddd
 * d: dx
 * d: dy
 * d: dz
 * d: AirShip id ??
 * @author  -Wooden-
 * 
 */
public class ExGetOnAirShip extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(ExGetOnAirShip.class.getName());
	private int _x;
	private int _y;
	private int _z;
	private int _shipId;

    @Override
    protected void readImpl()
    {
    	_x = readD();
    	_y = readD();
    	_z = readD();
    	_shipId = readD();
    }

    @Override
    protected void runImpl()
    {
        _log.info("[T1:ExGetOnAirShip] x: "+_x);
        _log.info("[T1:ExGetOnAirShip] y: "+_y);
        _log.info("[T1:ExGetOnAirShip] z: "+_z);
        _log.info("[T1:ExGetOnAirShip] ship ID: "+_shipId);
    }

    @Override
    public String getType()
    {
        return "[C] 0xD0:0x36 ExGetOnAirShip";
    }

}
