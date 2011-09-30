/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExShowSeedMapInfo;


public class RequestSeedPhase extends L2GameClientPacket
{
	private static final String _C__REQUESTSEEDPHASE = "[C] RequestSeedPhase";
	
	@Override
	protected void readImpl()
	{
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		activeChar.sendPacket(new ExShowSeedMapInfo());
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__REQUESTSEEDPHASE;
	}
}
