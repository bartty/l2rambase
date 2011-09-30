package handlers.voicedcommandhandlers;

import l2.rambase.gameserver.handler.IVoicedCommandHandler;
import l2.rambase.gameserver.instancemanager.HellboundManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;

public class Hellbound_Voiced implements IVoicedCommandHandler 
{
	private static final String[] VOICED_COMMANDS = { "hellbound" };

	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target) 
	{
		if (command.startsWith("hellbound")	|| command.equalsIgnoreCase("hellbound")) 
		{
			HellboundManager.getInstance().checkHellboundLevel();
			boolean _isOpen = HellboundManager.getInstance().checkIsOpen();
			int hellboundLevel = HellboundManager.getInstance().getLevel();
			int hellboundTrust = HellboundManager.getInstance().getTrust();
			activeChar.sendMessage("- Hellbound Informations -");
			if (_isOpen == true) {
				activeChar.sendMessage("Status: Unlocked");
			}
			if (_isOpen == false) {
				activeChar.sendMessage("Status: Locked");
			}
			activeChar.sendMessage("Current Trust: " + hellboundTrust
					+ "\nCurrent Level: " + hellboundLevel);
		}
		return true;
	}

	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}