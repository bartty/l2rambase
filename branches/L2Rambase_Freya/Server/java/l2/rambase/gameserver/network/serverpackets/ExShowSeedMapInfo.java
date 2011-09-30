/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

/**
 * format: 0xfe cd(dddd)
 * FE - packet id
 * A1 00 - packet subid
 * d - seed count
 * d - x pos
 * d - y pos
 * d - z pos
 * d - sys msg no
 *
 */
public class ExShowSeedMapInfo extends L2GameServerPacket
{
	private static final String _S__FE_A1_EXSHOWSEEDMAPINFO = "[S] FE:A1 ExShowSeedMapInfo";
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE); // Id
		writeH(0xa1); // SubId
		
		writeD(0); // seed count
		// for (int i = 0; i < seedCount; i++)
		writeD(0); // x coord
		writeD(0); // y coord
		writeD(0); // z coord
		writeD(0); // sys msg id
	}
	
	@Override
	public String getType()
	{
		return _S__FE_A1_EXSHOWSEEDMAPINFO;
	}
}