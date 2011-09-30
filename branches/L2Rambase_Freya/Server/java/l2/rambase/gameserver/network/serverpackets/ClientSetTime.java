/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.GameTimeController;

public class ClientSetTime extends L2GameServerPacket
{
	private static final String _S__EC_CLIENTSETTIME = "[S] f2 ClientSetTime [dd]";
	public static final ClientSetTime STATIC_PACKET = new ClientSetTime();

	private ClientSetTime()
	{
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0xf2);
		writeD(GameTimeController.getInstance().getGameTime()); // time in client minutes
		writeD(6); //constant to match the server time( this determines the speed of the client clock)
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__EC_CLIENTSETTIME;
	}
}