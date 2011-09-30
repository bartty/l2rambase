/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


/**
 *  @structure chdSdS
 */
public final class RequestSaveBookMarkSlot extends L2GameClientPacket
{
	private static final String _C__51_REQUESTSAVEBOOKMARKSLOT = "[C] 51 RequestSaveBookMarkSlot";
	
	private int icon;
	private String name,tag;

	@Override
	protected void readImpl()
	{
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
		activeChar.teleportBookmarkAdd(activeChar.getX(), activeChar.getY(), activeChar.getZ(), icon, tag, name);
	}


	@Override
	public String getType()
	{
		return _C__51_REQUESTSAVEBOOKMARKSLOT;
	}
}
