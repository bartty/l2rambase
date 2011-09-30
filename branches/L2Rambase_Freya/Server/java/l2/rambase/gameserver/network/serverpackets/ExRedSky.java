/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExRedSky extends L2GameServerPacket
{
	private static final String _S__FE_40_EXREDSKYPACKET = "[S] FE:41 ExRedSkyPacket";
	private int _duration;

	public ExRedSky(int duration)
	{
		_duration = duration;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x41);
		writeD(_duration);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_40_EXREDSKYPACKET;
	}
}