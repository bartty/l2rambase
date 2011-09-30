/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.StopRotation;

public final class FinishRotating extends L2GameClientPacket
{
	private static final String _C__4B_FINISHROTATING = "[C] 4B FinishRotating";

	private int _degree;
	@SuppressWarnings("unused")
    private int _unknown;

	@Override
	protected void readImpl()
	{
		_degree = readD();
		_unknown = readD();
	}

	@Override
	protected void runImpl()
	{
		if (getClient().getActiveChar() == null)
		    return;
		L2PcInstance activeChar = getClient().getActiveChar();
		if(activeChar.isInAirShip() && activeChar.getAirShip().isCaptain(activeChar))
        {
            activeChar.getAirShip().setHeading(_degree);
            StopRotation sr = new StopRotation(activeChar.getAirShip().getObjectId(), _degree, 0);
            activeChar.getAirShip().broadcastPacket(sr);
        } 
		else
        {
        	StopRotation sr = new StopRotation(activeChar.getObjectId(), _degree, 0);
        	getClient().getActiveChar().broadcastPacket(sr);
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__4B_FINISHROTATING;
	}
}
