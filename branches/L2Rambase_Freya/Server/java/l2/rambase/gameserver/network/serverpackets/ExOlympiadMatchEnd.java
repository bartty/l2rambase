/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExOlympiadMatchEnd extends L2GameServerPacket
{
	public ExOlympiadMatchEnd()
	{		
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xFE);
		writeH(0x2D);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:2D ExOlympiadMatchEnd";
	}
}