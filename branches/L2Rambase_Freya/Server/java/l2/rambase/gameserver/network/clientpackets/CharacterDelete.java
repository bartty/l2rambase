/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.network.serverpackets.CharDeleteFail;
import l2.rambase.gameserver.network.serverpackets.CharDeleteSuccess;
import l2.rambase.gameserver.network.serverpackets.CharSelectionInfo;


/**
 * This class ...
 *
 * @version $Revision: 1.8.2.1.2.3 $ $Date: 2005/03/27 15:29:30 $
 */
public final class CharacterDelete extends L2GameClientPacket
{
	private static final String _C__0C_CHARACTERDELETE = "[C] 0C CharacterDelete";
	private static Logger _log = Logger.getLogger(CharacterDelete.class.getName());

	// cd
	private int _charSlot;


	@Override
	protected void readImpl()
	{
		_charSlot = readD();
	}

	@Override
	protected void runImpl()
	{
		if (Config.DEBUG) _log.fine("deleting slot:" + _charSlot);
		
		try
		{
			byte answer = getClient().markToDeleteChar(_charSlot);
			
			switch(answer)
			{
				default:
				case -1: // Error
					break;
				case 0: // Success!
					sendPacket(new CharDeleteSuccess());
					break;
				case 1:
					sendPacket(new CharDeleteFail(CharDeleteFail.REASON_YOU_MAY_NOT_DELETE_CLAN_MEMBER));
					break;
				case 2:
					sendPacket(new CharDeleteFail(CharDeleteFail.REASON_CLAN_LEADERS_MAY_NOT_BE_DELETED));
					break;			
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Error:", e);
		}

		CharSelectionInfo cl = new CharSelectionInfo(getClient().getAccountName(), getClient().getSessionId().playOkID1, 0);
		sendPacket(cl);
		getClient().setCharSelection(cl.getCharInfo());
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__0C_CHARACTERDELETE;
	}
}
