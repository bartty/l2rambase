/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExDominionWarEnd extends L2GameServerPacket
{
	private static final String TYPE = "[S] FE:A4 ExDominionWarEnd";
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0xA4);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return TYPE;
	}
	
}