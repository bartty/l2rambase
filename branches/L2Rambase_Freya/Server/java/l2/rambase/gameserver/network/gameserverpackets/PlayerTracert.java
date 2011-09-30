/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;
import l2.rambase.util.network.BaseSendablePacket; 


public class PlayerTracert extends BaseSendablePacket
{
	public PlayerTracert(String account, String pcIp, 
			String hop1, String hop2, String hop3, String hop4)
	{
		writeC(0x07);
		writeS(account);
		writeS(pcIp);
		writeS(hop1);
		writeS(hop2);
		writeS(hop3);
		writeS(hop4);
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