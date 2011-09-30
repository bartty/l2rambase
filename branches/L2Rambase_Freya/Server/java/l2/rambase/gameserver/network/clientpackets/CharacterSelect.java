/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;


import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.CharNameTable;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.L2GameClient.GameClientState;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.CharSelected;
import l2.rambase.gameserver.network.serverpackets.SSQInfo;


/**
 * This class ...
 * 
 * @version $Revision: 1.5.2.1.2.5 $ $Date: 2005/03/27 15:29:30 $
 */
public class CharacterSelect extends L2GameClientPacket
{
	private static final String _C__0D_CHARACTERSELECT = "[C] 0D CharacterSelect";
	private static final Logger _log = Logger.getLogger(CharacterSelect.class.getName());
	protected static final Logger _logAccounting = Logger.getLogger("accounting");

	// cd
	private int _charSlot;

	@SuppressWarnings("unused")
	private int _unk1; 	// new in C4
	@SuppressWarnings("unused")
	private int _unk2;	// new in C4
	@SuppressWarnings("unused")
	private int _unk3;	// new in C4
	@SuppressWarnings("unused")
	private int _unk4;	// new in C4

	@Override
    protected void readImpl()
	{
		_charSlot = readD();
		_unk1 = readH();
		_unk2 = readD();
		_unk3 = readD();
		_unk4 = readD();
	}

	@Override
    protected void runImpl()
	{
		// if there is a playback.dat file in the current directory, it will
		// be sent to the client instead of any regular packets
		// to make this work, the first packet in the playback.dat has to
		// be a  [S]0x21 packet
		// after playback is done, the client will not work correct and need to exit
		//playLogFile(getConnection()); // try to play log file


		// we should always be abble to acquire the lock
		// but if we cant lock then nothing should be done (ie repeated packet)
		if (this.getClient().getActiveCharLock().tryLock())
		{
			try
			{
				// should always be null
				// but if not then this is repeated packet and nothing should be done here
				if (this.getClient().getActiveChar() == null)
				{
					// The L2PcInstance must be created here, so that it can be attached to the L2GameClient
					if (Config.DEBUG)
					{
						_log.fine("selected slot:" + _charSlot);
					}
					
					//load up character from disk
					L2PcInstance cha = getClient().loadCharFromDisk(_charSlot);
					if (cha == null)
					{
						_log.severe("Character could not be loaded (slot:"+_charSlot+")");
						sendPacket(ActionFailed.STATIC_PACKET);
						return;
					}
					L2PcInstance existPlayer = L2World.getInstance().getPlayer(cha.getName());
					if (existPlayer != null)
					{
						if (existPlayer.isOnline() > 0 || existPlayer.getClient().getState() == GameClientState.CONNECTED)
						{
							_log.severe("Character "+cha.getName()+" try use multiplication bug");							
							existPlayer.getClient().closeNow();
							cha.getClient().closeNow();
							return;
						}
					}
					if (cha.getAccessLevel().getLevel() < 0)
					{
						cha.logout();
						return;
					}
					
					CharNameTable.getInstance().addName(cha);
					
					cha.setClient(this.getClient());
					getClient().setActiveChar(cha);
                    
                    sendPacket(new SSQInfo());
                    
					this.getClient().setState(GameClientState.IN_GAME);
					CharSelected cs = new CharSelected(cha, getClient().getSessionId().playOkID1);
					sendPacket(cs);
				}
			}
			finally
			{
				this.getClient().getActiveCharLock().unlock();
			}

			LogRecord record = new LogRecord(Level.INFO, "Logged in");
	    	record.setParameters(new Object[]{this.getClient()});
			_logAccounting.log(record);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
    public String getType()
	{
		return _C__0D_CHARACTERSELECT;
	}	
}
