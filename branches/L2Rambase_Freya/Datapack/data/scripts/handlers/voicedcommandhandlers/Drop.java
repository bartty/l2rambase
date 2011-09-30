package handlers.voicedcommandhandlers;

import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class Drop implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS = { "drop" };

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (command.equalsIgnoreCase("drop"))
		{
			if (activeChar != null)
			{
				if (activeChar.canGetDrop() == true)
				{
					activeChar.setCanDrop(false);
					activeChar.sendMessage("Drop limit function activated.");
				}
				else
				{
					activeChar.setCanDrop(true);
					activeChar.sendMessage("Drop limit function deactivated.");
				}
			}
		}
		return true;
	}
	
	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}