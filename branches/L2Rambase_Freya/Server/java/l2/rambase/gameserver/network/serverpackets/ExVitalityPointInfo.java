/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExVitalityPointInfo extends L2GameServerPacket
{
	private static final String _S__FE_A0_EXVITALITYPOINTINFO = "[S] FE:A0 ExVitalityPointInfo";
	private int _vitalityPoints;

	public ExVitalityPointInfo(int vitPoints)
	{
		_vitalityPoints = vitPoints;
	}

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
     */
    @Override
	protected void writeImpl()
    {
        writeC(0xfe);
        writeH(0xa0);
        writeD(_vitalityPoints);
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _S__FE_A0_EXVITALITYPOINTINFO;
    }
}