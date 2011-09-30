/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExPCCafePointInfo extends L2GameServerPacket
{
	private static final String _S__FE_31_EXPCCAFEPOINTINFO = "[S] FE:32 ExPCCafePointInfo";
	private int _pointType, _points, _mAddPoint, _remainTime, _mPeriodType = 0;

    public ExPCCafePointInfo()
    {
        _pointType = 0;
        _points = 0;
        _mAddPoint = 0;
        _remainTime = 0;
        _mPeriodType = 0;
        _pointType = 0;
    }
    
    public ExPCCafePointInfo(int points, int modify_points, boolean mod, boolean _double, int hours_left)
    {
        _pointType = 0;
        _points = points;
        _mAddPoint = modify_points;
        _remainTime = hours_left;
        if(mod && _double)
        {
            _mPeriodType = 1;
            _pointType = 0;
        } else
        if(mod)
        {
            _mPeriodType = 1;
            _pointType = 1;
        } else
        {
            _mPeriodType = 2;
            _pointType = 2;
        }
    }

    public ExPCCafePointInfo(int val1, int val2, int val3, int val4, int val5, int val6)
    {
        _pointType = 0;
        _points = val1;
        _mAddPoint = val2;
        _mPeriodType = val3;
        _remainTime = val4;
        _remainTime = val5;
        _pointType = val6;
    }
    
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x32);
		writeD(_points); // num points
		writeD(_mAddPoint); // points inc display
		writeC(_mPeriodType); // period(0=don't show window,1=acquisition,2=use points)
		writeD(_remainTime); // period hours left
		writeC(_pointType); // points inc display color(0=yellow,1=cyan-blue,2=red,all other black)
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_31_EXPCCAFEPOINTINFO;
	}
}