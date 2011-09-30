/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;
import l2.rambase.util.network.BaseSendablePacket; 

public class PlayerLogout extends BaseSendablePacket
{
	public PlayerLogout(String player)
	{
		writeC(0x03);
		writeS(player);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.gameserverpackets.GameServerBasePacket#getContent()
	 */
	@Override
	public byte[] getContent() throws IOException
	{
		return getBytes();
	}

}