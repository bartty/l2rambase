/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2ClanMember;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class RequestGiveNickName extends L2GameClientPacket
{
	private static final String _C__55_REQUESTGIVENICKNAME = "[C] 55 RequestGiveNickName";
	static Logger _log = Logger.getLogger(RequestGiveNickName.class.getName());

	private String _target;
	private String _title;

	@Override
	protected void readImpl()
	{
		_target = readS();
		_title  = readS();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;

		// Noblesse can bestow a title to themselves
		if (activeChar.isNoble() && _target.matches(activeChar.getName()))
		{
			activeChar.setTitle(_title);
			SystemMessage sm = new SystemMessage(SystemMessageId.TITLE_CHANGED);
			activeChar.sendPacket(sm);
			activeChar.broadcastTitleInfo();
		}
		//Can the player change/give a title?
		else if ((activeChar.getClanPrivileges() & L2Clan.CP_CL_GIVE_TITLE) == L2Clan.CP_CL_GIVE_TITLE)
		{
			if (activeChar.getClan().getLevel() < 3)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.CLAN_LVL_3_NEEDED_TO_ENDOWE_TITLE);
                activeChar.sendPacket(sm);
                sm = null;
				return;
			}

			L2ClanMember member1 = activeChar.getClan().getClanMember(_target);
            if (member1 != null)
            {
                L2PcInstance member = member1.getPlayerInstance();
                if (member != null)
                {
        			//is target from the same clan?
    				member.setTitle(_title);
    				SystemMessage sm = new SystemMessage(SystemMessageId.TITLE_CHANGED);
    				member.sendPacket(sm);
					member.broadcastTitleInfo();
					sm = null;
                }
                else
                    activeChar.sendMessage("Target needs to be online to get a title");
			}
            else
                activeChar.sendMessage("Target does not belong to your clan");
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__55_REQUESTGIVENICKNAME;
	}
}
