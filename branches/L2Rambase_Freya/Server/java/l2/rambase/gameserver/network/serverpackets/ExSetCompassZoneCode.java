/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExSetCompassZoneCode extends L2GameServerPacket
{
	private static final String _S__FE_32_EXSETCOMPASSZONECODE = "[S] FE:33 ExSetCompassZoneCode";

	public static final int ALTEREDZONE = 0x08;
	public static final int SIEGEWARZONE1 = 0x0A;
	public static final int SIEGEWARZONE2 = 0x0B;
	public static final int PEACEZONE = 0x0C;
	public static final int SEVENSIGNSZONE = 0x0D;
	public static final int PVPZONE = 0x0E;
	public static final int GENERALZONE = 0x0F;

	private int _zoneType;

	public ExSetCompassZoneCode(int val)
	{
		_zoneType = val;
	}

	/**
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x33);
		writeD(_zoneType);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_32_EXSETCOMPASSZONECODE;
	}
}