/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExVariationResult extends L2GameServerPacket
{
	private static final String _S__FE_55_EXVARIATIONRESULT = "[S] FE:56 ExVariationResult";

	private int _stat12;
	private int _stat34;
	private int _unk3;

	public ExVariationResult(int unk1, int unk2, int unk3)
	{
		_stat12 = unk1;
		_stat34 = unk2;
		_unk3 = unk3;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x56);
		writeD(_stat12);
		writeD(_stat34);
		writeD(_unk3);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_55_EXVARIATIONRESULT;
	}

}