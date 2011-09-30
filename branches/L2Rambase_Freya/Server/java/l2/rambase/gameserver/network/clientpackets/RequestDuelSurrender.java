/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.DuelManager;

/**
 * Format:(ch)
 * 
 */
public final class RequestDuelSurrender extends L2GameClientPacket
{
	private static final String _C__D0_30_REQUESTDUELSURRENDER = "[C] D0:30 RequestDuelSurrender";

	@Override
	protected void readImpl()
	{
	}

	/**
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		DuelManager.getInstance().doSurrender(getClient().getActiveChar());
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_30_REQUESTDUELSURRENDER;
	}

}
