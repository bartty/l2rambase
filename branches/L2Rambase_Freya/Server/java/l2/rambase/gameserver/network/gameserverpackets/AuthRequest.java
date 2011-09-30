/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;

import l2.rambase.util.network.BaseSendablePacket; 

public class AuthRequest extends BaseSendablePacket
{
	/**
	 * Format: cccSddb
	 * c desired ID
	 * c accept alternative ID
	 * c reserve Host
	 * s ExternalHostName
	 * s InetranlHostName
	 * d max players
	 * d hexid size
	 * b hexid
	 *
	 * @param id
	 * @param acceptAlternate
	 * @param hexid
	 * @param externalHost
	 * @param internalHost
	 * @param reserveHost
	 * @param maxplayer
	 */
	public AuthRequest(int id, boolean acceptAlternate, byte[] hexid, String externalHost,String internalHost, int port, boolean reserveHost, int maxplayer)
	{
		writeC(0x01);
		writeC(id);
		writeC(acceptAlternate? 0x01 : 0x00);
		writeC(reserveHost? 0x01 : 0x00);
		writeS(externalHost);
		writeS(internalHost);
		writeH(port);
		writeD(maxplayer);
		writeD(hexid.length);
		writeB(hexid);
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