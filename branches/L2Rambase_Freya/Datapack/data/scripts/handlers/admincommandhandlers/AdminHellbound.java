package handlers.admincommandhandlers;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import l2.rambase.gameserver.handler.IAdminCommandHandler;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class AdminHellbound implements IAdminCommandHandler
{
	private static Logger _log = Logger.getLogger(AdminHellbound.class.getName());
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_add_trust",
		"admin_set_hblevel"
	};
	
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		if (activeChar == null)
			return false;
		
		if (activeChar.getAccessLevel().getLevel() != 127)
		{
			activeChar.logout();
			_log.warning("Character " + activeChar.getName() + "(" + activeChar.getObjectId() + ") tryed to use an admin command.");
			return false;			
		}
		
		StringTokenizer st = new StringTokenizer(command, " ");
		String actualCommand = st.nextToken(); 
		
		if (actualCommand.equalsIgnoreCase("admin_add_trust"))
		{
			try
			{
				String val = command.substring(16);
				HellboundManager.getInstance().increaseTrust(Integer.parseInt(val));
			}
			catch (StringIndexOutOfBoundsException e)
			{
			}
		}
		else if (actualCommand.equalsIgnoreCase("admin_set_hblevel"))
		{
			try
			{
				String val = command.substring(18);
				HellboundManager.getInstance().changeLevel(Integer.parseInt(val));
			}
			catch (StringIndexOutOfBoundsException e)
			{
			}
		}
		return true;
	}
	
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}