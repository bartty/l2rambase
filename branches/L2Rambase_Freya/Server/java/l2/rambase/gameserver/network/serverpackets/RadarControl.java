/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class RadarControl extends L2GameServerPacket
{
    private static final String _S__EB_RadarControl = "[S] f1 RadarControl";
    private int _showRadar;
    private int _type;
    private int _x;
    private int _y;
    private int _z;

    public RadarControl(int showRadar, int type, int x , int  y ,int z)
    {
        _showRadar = showRadar;         // showRader?? 0 = showradar; 1 = delete radar;
        _type = type;                   // radar type??
        _x = x;
        _y = y;
        _z = z;
    }

	@Override
	protected final void writeImpl()
    {
        writeC(0xf1);
        writeD(_showRadar);
        writeD(_type);     //type
        writeD(_x);    //x
        writeD(_y);    //y
        writeD(_z);    //z
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__EB_RadarControl;
    }
}