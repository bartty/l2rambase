/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.gameserverpackets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

import l2.rambase.util.network.BaseSendablePacket;


public class BlowFishKey extends BaseSendablePacket
{
	private static Logger _log = Logger.getLogger(BlowFishKey.class.getName());
	/**
	 * @param blowfishKey
	 * @param publicKey
	 */
	public BlowFishKey(byte[] blowfishKey, RSAPublicKey publicKey)
	{
		writeC(0x00);
		byte[] encrypted =null;
		try
		{
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/nopadding");
	        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        encrypted = rsaCipher.doFinal(blowfishKey);
		}
		catch(GeneralSecurityException e)
		{
			_log.log(Level.SEVERE, "Error While encrypting blowfish key for transmision (Crypt error): " + e.getMessage(), e);
		}
		writeD(encrypted.length);
		writeB(encrypted);
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
