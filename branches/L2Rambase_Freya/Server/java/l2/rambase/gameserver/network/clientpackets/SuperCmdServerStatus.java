/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

/**
 * Format ch
 * c: (id) 0x39
 * h: (subid) 0x02
 *
 */
public final class SuperCmdServerStatus extends L2GameClientPacket
{
	private static final String _C__39_02_SUPERCMDSERVERSTATUS = "[C] 39:02 SuperCmdServerStatus";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
	}

	@Override
	public String getType()
	{
		return _C__39_02_SUPERCMDSERVERSTATUS;
	}
}