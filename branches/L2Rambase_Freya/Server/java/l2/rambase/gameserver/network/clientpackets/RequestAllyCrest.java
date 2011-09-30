/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.CrestCache;
import l2.rambase.gameserver.network.serverpackets.AllyCrest;


/**
 * This class ...
 *
 * @version $Revision: 1.3.4.4 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestAllyCrest extends L2GameClientPacket
{
	private static final String _C__88_REQUESTALLYCREST = "[C] 88 RequestAllyCrest";
	private static Logger _log = Logger.getLogger(RequestAllyCrest.class.getName());

	private int _crestId;
	/**
	 * packet type id 0x88 format: cd
	 *
	 * @param rawPacket
	 */
	@Override
	protected void readImpl()
	{
		_crestId = readD();
	}

	@Override
	protected void runImpl()
	{
		if (Config.DEBUG) _log.fine("allycrestid " + _crestId + " requested");

        byte[] data = CrestCache.getInstance().getAllyCrest(_crestId);

		if (data != null)
		{
			AllyCrest ac = new AllyCrest(_crestId,data);
			sendPacket(ac);
		}
		else
		{
			if (Config.DEBUG) _log.fine("allycrest is missing:" + _crestId);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__88_REQUESTALLYCREST;
	}
}
