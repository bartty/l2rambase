/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

/**
 * Format: (ch) S
 * 
 */
public final class RequestPCCafeCouponUse extends L2GameClientPacket
{
    protected static final Logger _log = Logger.getLogger(RequestPCCafeCouponUse.class.getName());
    private static final String _C__D0_20_REQUESTPCCAFECOUPONUSE = "[C] D0:19 RequestPCCafeCouponUse";
    private String _str;


    @Override
	protected void readImpl()
    {
        _str = readS();
    }

    @Override
	protected void runImpl()
    {
        _log.info("C5: RequestPCCafeCouponUse: S: "+_str);
    }

    @Override
    public String getType()
    {
        return _C__D0_20_REQUESTPCCAFECOUPONUSE;
    }
}