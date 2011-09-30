/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.SevenSigns;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.NpcHtmlMessage;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2DuskPriestInstance extends L2SignsPriestInstance
{
	public L2DuskPriestInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2DuskPriestInstance);
	}

	@Override
	public void onBypassFeedback(L2PcInstance player, String command)
	{
		if (command.startsWith("Chat"))
			showChatWindow(player);
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);

		String filename = SevenSigns.SEVEN_SIGNS_HTML_PATH;
		int sealGnosisOwner = SevenSigns.getInstance().getSealOwner(SevenSigns.SEAL_GNOSIS);
		int playerCabal = SevenSigns.getInstance().getPlayerCabal(player.getObjectId());
		boolean isSealValidationPeriod = SevenSigns.getInstance().isSealValidationPeriod();
		boolean isCompResultsPeriod = SevenSigns.getInstance().isCompResultsPeriod();
		int recruitPeriod = SevenSigns.getInstance().getCurrentPeriod();
		int compWinner = SevenSigns.getInstance().getCabalHighestScore();

		switch (playerCabal)
		{
		case SevenSigns.CABAL_DUSK:
			if (isCompResultsPeriod)
				filename += "dusk_priest_5.htm";
			else if (recruitPeriod == 0)
				filename += "dusk_priest_6.htm";
			else if (isSealValidationPeriod)
			{
				if (compWinner == SevenSigns.CABAL_DUSK)
				{
					if (compWinner != sealGnosisOwner)
						filename += "dusk_priest_2c.htm";
					else
						filename += "dusk_priest_2a.htm";
				}
				else if (compWinner == SevenSigns.CABAL_NULL)
					filename += "dusk_priest_2d.htm";
				else
					filename += "dusk_priest_2b.htm";
			}
			else
				filename += "dusk_priest_1b.htm";
			break;
		case SevenSigns.CABAL_DAWN:
			if (isSealValidationPeriod)
				filename += "dusk_priest_3a.htm";
			else
				filename += "dusk_priest_3b.htm";
			break;
		default:
			if (isCompResultsPeriod)
				filename += "dusk_priest_5.htm";
			else if (recruitPeriod == 0)
				filename += "dusk_priest_6.htm";
			else if (isSealValidationPeriod)
			{
				if (compWinner == SevenSigns.CABAL_DUSK)
					filename += "dusk_priest_4.htm";
				else if (compWinner == SevenSigns.CABAL_NULL)
					filename += "dusk_priest_2d.htm";
				else
					filename += "dusk_priest_2b.htm";
			}
			else
				filename += "dusk_priest_1a.htm";
			break;
		}

		NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getHtmlPrefix(), filename);
		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}
}