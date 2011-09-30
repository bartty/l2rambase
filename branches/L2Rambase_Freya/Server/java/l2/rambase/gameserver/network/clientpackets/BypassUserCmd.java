/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.handler.IUserCommandHandler;
import l2.rambase.gameserver.handler.UserCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


/**
 * This class ...
 *
 * @version $Revision: 1.1.2.1.2.2 $ $Date: 2005/03/27 15:29:30 $
 */
public class BypassUserCmd extends L2GameClientPacket
{
	private static final String _C__B3_BYPASSUSERCMD = "[C] b3 BypassUserCmd";
	static Logger _log = Logger.getLogger(BypassUserCmd.class.getName());

	private int _command;


	@Override
	protected void readImpl()
	{
		_command = readD();
	}

	@Override
	protected void runImpl()
	{
        L2PcInstance player = getClient().getActiveChar();
        if (player == null)
            return;

        IUserCommandHandler handler = UserCommandHandler.getInstance().getUserCommandHandler(_command);

        if (handler == null)
        {
            if (player.isGM())
                player.sendMessage("User commandID "+_command+" not implemented yet.");
        }
        else
        {
            handler.useUserCommand(_command, getClient().getActiveChar());
        }
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__B3_BYPASSUSERCMD;
	}
}
