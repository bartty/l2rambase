/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

public class TutorialEnableClientEvent extends L2GameServerPacket
{
	private static final String _S__A8_TUTORIALENABLECLIENTEVENT = "[S] a8 TutorialEnableClientEvent";
	private int _eventId = 0;

	public TutorialEnableClientEvent(int event)
	{
		_eventId = event;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xa8);
		writeD(_eventId);
	}

	@Override
	public String getType()
	{
		return _S__A8_TUTORIALENABLECLIENTEVENT;
	}
}