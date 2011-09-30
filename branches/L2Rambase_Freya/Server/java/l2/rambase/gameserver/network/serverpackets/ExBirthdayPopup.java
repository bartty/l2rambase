/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;


public class ExBirthdayPopup extends L2GameServerPacket
{
	private static final String _S__FE_8F_EXBIRTHDAYPOPUP = "[S] FE:8F ExBirthdayPopup";

	public ExBirthdayPopup()
	{		
	}

	@Override
    protected void writeImpl()
    {
	    writeC(0xfe);
	    writeH(0x8f);
    }
	
	@Override
    public String getType()
    {
	    return _S__FE_8F_EXBIRTHDAYPOPUP;
    }
}