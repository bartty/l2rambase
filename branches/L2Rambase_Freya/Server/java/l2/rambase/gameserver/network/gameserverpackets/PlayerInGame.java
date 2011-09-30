/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;

import javolution.util.FastList;
import l2.rambase.util.network.BaseSendablePacket; 

public class PlayerInGame extends BaseSendablePacket
{
	public PlayerInGame (String player)
	{
		writeC(0x02);
		writeH(1);
		writeS(player);
	}

	public PlayerInGame (FastList<String> players)
	{
		writeC(0x02);
		writeH(players.size());
		for(String pc : players)
			writeS(pc);
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