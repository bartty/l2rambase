/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.datatables.ClanTable;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.GMHennaInfo;
import l2.rambase.gameserver.network.serverpackets.GMViewCharacterInfo;
import l2.rambase.gameserver.network.serverpackets.GMViewItemList;
import l2.rambase.gameserver.network.serverpackets.GMViewPledgeInfo;
import l2.rambase.gameserver.network.serverpackets.GMViewSkillInfo;
import l2.rambase.gameserver.network.serverpackets.GMViewWarehouseWithdrawList;
import l2.rambase.gameserver.network.serverpackets.GmViewQuestInfo;


public final class RequestGMCommand extends L2GameClientPacket
{
	private static final String _C__6E_REQUESTGMCOMMAND = "[C] 6e RequestGMCommand";
	static Logger _log = Logger.getLogger(RequestGMCommand.class.getName());

	private String _targetName;
	private int _command;


	@Override
	protected void readImpl()
	{
		_targetName = readS();
		_command    = readD();
	}

	@Override
	protected void runImpl()
	{
		// prevent non gm or low level GMs from vieweing player stuff
		if (!getClient().getActiveChar().isGM() || !getClient().getActiveChar().getAccessLevel().allowAltG())
			return;

		L2PcInstance player = L2World.getInstance().getPlayer(_targetName);

		L2Clan clan = ClanTable.getInstance().getClanByName(_targetName);
		
		// player name was incorrect?
		if (player == null && (clan == null || _command != 6))
		{
			return;
		}

		switch(_command)
		{
		    case 1: // player status
		    {
		        sendPacket(new GMViewCharacterInfo(player));
                sendPacket(new GMHennaInfo(player));
		        break;
		    }
		    case 2: // player clan
		    {
		        if (player.getClan() != null)
		            sendPacket(new GMViewPledgeInfo(player.getClan(),player));
		        break;
		    }
		    case 3: // player skills
		    {
		        sendPacket(new GMViewSkillInfo(player));
		        break;
		    }
		    case 4: // player quests
		    {
		        sendPacket(new GmViewQuestInfo(player));
		        break;
		    }
		    case 5: // player inventory
		    {
		        sendPacket(new GMViewItemList(player));
		        break;
		    }
		    case 6: // player warehouse
		    {
		        // gm warehouse view to be implemented
		    	if (player != null)
		    		sendPacket(new GMViewWarehouseWithdrawList(player));
		    	// clan warehouse
		    	else
		    		sendPacket(new GMViewWarehouseWithdrawList(clan));
		        break;
		    }
		    
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__6E_REQUESTGMCOMMAND;
	}
}
