/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.cache.HtmCache;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.clientpackets.RequestBypassToServer;


public final class NpcQuestHtmlMessage extends L2GameServerPacket
{

	private static Logger _log = Logger.getLogger(RequestBypassToServer.class.getName());
	private int _npcObjId;
	private String _html;
	private int _questId = 0;
	
	/**
	 * 
	 * @param npcObjId
	 * @param text
	 * @param questId
	 */
	public NpcQuestHtmlMessage(int npcObjId, int questId)
	{
		_npcObjId = npcObjId;
		_questId = questId;
	}

	@Override
	public void runImpl()
	{
		if (Config.BYPASS_VALIDATION)
			buildBypassCache(getClient().getActiveChar());
	}

	public void setHtml(String text)
	{
        if(text.length() > 8192)
		{
			_log.warning("Html is too long! this will crash the client!");
			_html = "<html><body>Html was too long</body></html>";
			return;
		}
		_html = text;
	}

	public boolean setFile(String path)
	{
		String content = HtmCache.getInstance().getHtm(getClient().getActiveChar().getHtmlPrefix(), path); 

		if (content == null)
		{
			setHtml("<html><body>My Text is missing:<br>"+path+"</body></html>");
			_log.warning("missing html page "+path);
			return false;
		}

        setHtml(content);
        return true;
	}

	public void replace(String pattern, String value)
	{
		_html = _html.replaceAll(pattern, value);
	}

	private final void buildBypassCache(L2PcInstance activeChar)
	{
        if (activeChar == null)
            return;

        activeChar.clearBypass();
		int len = _html.length();
		for(int i=0; i<len; i++)
		{
			int start = _html.indexOf("bypass -h", i);
			int finish = _html.indexOf("\"", start);

			if(start < 0 || finish < 0)
				break;

			start += 10;
			i = finish;
			int finish2 = _html.indexOf("$",start);
			if (finish2 < finish && finish2 > 0)
                activeChar.addBypass2(_html.substring(start, finish2).trim());
			else
                activeChar.addBypass(_html.substring(start, finish).trim());
		}
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xfe);
		writeH(0x8d);
		writeD(_npcObjId);
		writeS(_html);
		writeD(_questId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] FE:8D NpcQuestHtmlMessage";
	}
}