/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;


public class ExMoveToLocationAirShip extends L2GameServerPacket
{
	
	private static final String _S__FE_65_EXAIRSHIPMOVETOLOCATION = "[S] FE:65 ExMoveToLocationAirShip";

	private final int _objId, _destx, _desty, _destz, _x, _y, _z;
	
	public ExMoveToLocationAirShip(L2Character cha)
	{
		_objId = cha.getObjectId();
		_destx = cha.getXdestination();
		_desty = cha.getYdestination();
		_destz = cha.getZdestination();
		_x = cha.getX();
		_y = cha.getY();
		_z = cha.getZ();
	}
	
	@Override
    protected void writeImpl()
    {
	    writeC(254);
	    writeH(101);
	    
		writeD(_objId);
		writeD(_destx);
		writeD(_desty);
		writeD(_destz);
		writeD(_x);
		writeD(_y);
		writeD(_z);
	}
	
	@Override
    public String getType()
    {
		return _S__FE_65_EXAIRSHIPMOVETOLOCATION;
    }	
}