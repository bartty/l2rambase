/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExUISetting;


public class RequestKeyMapping extends L2GameClientPacket
{
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:21 RequestKeyMapping";
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
	}
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		if (Config.STORE_UI_SETTINGS)
			activeChar.sendPacket(new ExUISetting(activeChar));
	}
	
}
