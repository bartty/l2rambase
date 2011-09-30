/**
 * @author L2Rambase Project
 */

package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;

public final class RequestLinkHtml extends L2GameClientPacket
{
	private static Logger _log = Logger.getLogger(RequestLinkHtml.class.getName());
	private static final String REQUESTLINKHTML__C__20 = "[C] 20 RequestLinkHtml";
	private String _link;

	@Override
	protected void readImpl()
	{
		_link = readS();
	}

	@Override
	public void runImpl()
	{
		L2PcInstance actor = getClient().getActiveChar();
		if(actor == null)
			return;

		if(_link.contains("..") || !_link.contains(".htm"))
		{
			_log.warning("[RequestLinkHtml] hack? link contains prohibited characters: '"+_link+"', skipped");
			return;
		}
		try
		{
			String filename = "data/html/"+_link;
			NpcHtmlMessage msg = new NpcHtmlMessage(0);
			msg.disableValidation();
			msg.setFile(actor.getHtmlPrefix(), filename);
			sendPacket(msg);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Bad RequestLinkHtml: ", e);
		}
	}

	@Override
	public String getType()
	{
		return REQUESTLINKHTML__C__20;
	}
}
