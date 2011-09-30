/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class TutorialCloseHtml extends L2GameServerPacket
{
	private static final String _S__A9_TUTORIALCLOSEHTML = "[S] a9 TutorialCloseHtml";

	@Override
	protected void writeImpl()
	{
		writeC(0xa9);
	}

	@Override
	public String getType()
	{
		return _S__A9_TUTORIALCLOSEHTML;
	}
} 