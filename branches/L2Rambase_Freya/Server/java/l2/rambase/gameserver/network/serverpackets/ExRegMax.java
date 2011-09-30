/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class ExRegMax extends L2GameServerPacket
{

	private static final String _S__FE_01_EXREGMAX = "[S] FE:01 ExRegenMax";
	private double _max;
	private int _count;
	private int _time;

	public ExRegMax(double max, int count, int time)
	{
		_max = max;
		_count = count;
		_time = time;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x01);
		writeD(1);
		writeD(_count);
		writeD(_time);
		writeF(_max);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_01_EXREGMAX;
	}	
}