/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public final class TutorialShowQuestionMark extends L2GameServerPacket
{
	private static final String _S__A7_TUTORIALSHOWQUESTIONMARK = "[S] a7 TutorialShowQuestionMark";
	private int _markId;

	public TutorialShowQuestionMark(int blink)
	{
		_markId = blink; 
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xa7);
		writeD(_markId);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__A7_TUTORIALSHOWQUESTIONMARK;
	}
}