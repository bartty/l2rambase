/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


/**
 *  
 *  @structure chddSdS
 */
public final class RequestModifyBookMarkSlot extends L2GameClientPacket
{
	private static final String _C__51_REQUESTMODIFYBOOKMARKSLOT = "[C] 51 RequestModifyBookMarkSlot";

	private int id,icon;
	private String name,tag;
	
	@Override
	protected void readImpl()
	{
		id = readD();
		name = readS();
		icon = readD();
		tag = readS();
		
	}
	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		activeChar.teleportBookmarkModify(id, icon, tag, name);
	}


	@Override
	public String getType()
	{
		return _C__51_REQUESTMODIFYBOOKMARKSLOT;
	}
}
