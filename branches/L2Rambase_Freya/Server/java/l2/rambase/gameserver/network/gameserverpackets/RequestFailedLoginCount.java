/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;
import l2.rambase.util.network.BaseSendablePacket; 


public class RequestFailedLoginCount extends BaseSendablePacket
{
	public RequestFailedLoginCount(String accountName)
    {
        writeC(8);
        writeS(accountName);
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