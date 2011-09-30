package handlers.voicedcommandhandlers;

import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class Exp implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS = { "exp" };

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (command.equalsIgnoreCase("exp"))
		{
			if (activeChar != null)
			{
				if (activeChar.canGetExp() == true)
				{
					activeChar.setCanGetExp(false);
					activeChar.sendMessage("Gain EXP & SP: Disabled");
				}
				else
				{
					activeChar.setCanGetExp(true);
					activeChar.sendMessage("Gain EXP & SP: Enabled");
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
