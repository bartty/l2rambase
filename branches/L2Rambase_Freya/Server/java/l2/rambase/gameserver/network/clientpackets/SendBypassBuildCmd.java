/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.AdminCommandAccessRights;
import l2.rambase.gameserver.handler.AdminCommandHandler;
import l2.rambase.gameserver.handler.IAdminCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.util.GMAudit;


public final class SendBypassBuildCmd extends L2GameClientPacket
{
	private static Logger _log = Logger.getLogger(SendBypassBuildCmd.class.getName());
	private static final String _C__5B_SENDBYPASSBUILDCMD = "[C] 5b SendBypassBuildCmd";
	public final static int GM_MESSAGE = 9;
	public final static int ANNOUNCEMENT = 10;

	private String _command;

	@Override
	protected void readImpl()
	{
		_command = readS();
		if (_command != null)
			_command = _command.trim();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
        if(activeChar == null)
            return;

        String command = "admin_" + _command.split(" ")[0];

		IAdminCommandHandler ach = AdminCommandHandler.getInstance().getAdminCommandHandler(command);
				
		if (ach == null)
		{
			if ( activeChar.isGM() )
				activeChar.sendMessage("The command " + command.substring(6) + " does not exists!");

			_log.warning("No handler registered for admin command '" + command + "'");
			return;
		}

		if (!AdminCommandAccessRights.getInstance().hasAccess(command , activeChar.getAccessLevel()))
		{
			activeChar.sendMessage("You don't have the access right to use this command!");
			_log.warning("Character " + activeChar.getName() + " tryed to use admin command " + command + ", but have no access to it!");
			return;
		}
		
		if (Config.GMAUDIT)
			GMAudit.auditGMAction(activeChar.getName()+" ["+activeChar.getObjectId()+"]", _command, (activeChar.getTarget() != null?activeChar.getTarget().getName():"no-target"));

		ach.useAdminCommand("admin_" + _command, activeChar);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__5B_SENDBYPASSBUILDCMD;
	}
}
