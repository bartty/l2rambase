/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.CharSelectionInfo;

/**
 * (ch)
 */
public class RequestGotoLobby extends L2GameClientPacket
{
	private static final String _C__D0_38_REQUESTGOTOLOBBY = "[C] D0:38 RequestGotoLobby";
	
	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2GameClient client = this.getClient();
		client.sendPacket(new CharSelectionInfo(client.getAccountName(), client.getSessionId().playOkID1));
	}

	/**
	 * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_38_REQUESTGOTOLOBBY;
	}
}
