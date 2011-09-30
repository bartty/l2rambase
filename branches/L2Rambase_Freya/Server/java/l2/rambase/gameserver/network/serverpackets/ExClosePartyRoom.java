/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExClosePartyRoom extends L2GameServerPacket
{
	public ExClosePartyRoom()
	{
	}

	@Override
	public String getType()
	{
		return "[S] FE:09 ExClosePartyRoom";
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x09);
	}
}