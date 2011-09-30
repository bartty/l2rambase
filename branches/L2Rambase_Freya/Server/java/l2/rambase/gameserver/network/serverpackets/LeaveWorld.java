/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public final class LeaveWorld extends L2GameServerPacket
{
	private static final String _S__96_LEAVEWORLD = "[S] 84 LeaveWorld";
	public static final LeaveWorld STATIC_PACKET = new LeaveWorld();
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x84);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__96_LEAVEWORLD;
	}
}