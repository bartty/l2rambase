/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.network.serverpackets.KeyPacket;

/**
 * This class ...
 *
 * @version $Revision: 1.5.2.8.2.8 $ $Date: 2005/04/02 10:43:04 $
 */
public final class ProtocolVersion extends L2GameClientPacket
{
	private static final String _C__00_PROTOCOLVERSION = "[C] 00 ProtocolVersion";
	private static final Logger _log = Logger.getLogger(ProtocolVersion.class.getName());
	private static final Logger _logAccounting = Logger.getLogger("accounting");

	private int _version;

	@Override
	protected void readImpl()
	{
		_version  = readD();
	}

	@Override
	protected void runImpl()
	{
		// this packet is never encrypted
		if (_version == -2)
		{
			if (Config.DEBUG)
				_log.info("Ping received");
			// this is just a ping attempt from the new C2 client
			getClient().closeNow();
		}
		else if (!Config.PROTOCOL_LIST.contains(_version))
		{
			LogRecord record = new LogRecord(Level.WARNING, "Wrong protocol");
			record.setParameters(new Object[]{_version, getClient()});
			_logAccounting.log(record);
			KeyPacket pk = new KeyPacket(getClient().enableCrypt(),0);
			getClient().sendPacket(pk);
			getClient().setProtocolOk(false);
		}
		else
		{
			if (Config.DEBUG)
				_log.fine("Client Protocol Revision is ok: "+_version);

			KeyPacket pk = new KeyPacket(getClient().enableCrypt(),1);
			getClient().sendPacket(pk);
			getClient().setProtocolOk(true);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__00_PROTOCOLVERSION;
	}
}
