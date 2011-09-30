/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class TutorialShowHtml extends L2GameServerPacket
{
	private static final String _S__A6_TUTORIALSHOWHTML = "[S] a6 TutorialShowHtml";
	private String _html;

	public TutorialShowHtml(String html)
	{
		_html = html;
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xa6);
		writeS(_html);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A6_TUTORIALSHOWHTML;
	}
}