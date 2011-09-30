/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.StartRotation;


public final class StartRotating extends L2GameClientPacket
{
	private static final String _C__4A_STARTROTATING = "[C] 4A StartRotating";

	private int _degree;
	private int _side;


	@Override
	protected void readImpl()
	{
		_degree = readD();
		_side = readD();
	}

	@Override
	protected void runImpl()
	{
		if (getClient().getActiveChar() == null)
		    return;
		L2PcInstance activeChar = getClient().getActiveChar();
		if(activeChar.isInAirShip() && activeChar.getAirShip().isCaptain(activeChar))
        {
            StartRotation br = new StartRotation(activeChar.getAirShip().getObjectId(), _degree, _side, 0);
            activeChar.getAirShip().broadcastPacket(br);
        } 
		else
        {
			StartRotation br = new StartRotation(getClient().getActiveChar().getObjectId(), _degree, _side, 0);
			getClient().getActiveChar().broadcastPacket(br);
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__4A_STARTROTATING;
	}
}