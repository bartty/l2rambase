/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


/**
 *  
 *  @structure: chdd
 */
public final class RequestDeleteBookMarkSlot extends L2GameClientPacket
{
	private static final String _C__51_REQUESTDELETEBOOKMARKSLOT = "[C] 51 RequestDeleteBookMarkSlot";
	
	private int id;

	@Override
	protected void readImpl()
	{
		id = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;
		
		activeChar.teleportBookmarkDelete(id);
	}


	@Override
	public String getType()
	{
		return _C__51_REQUESTDELETEBOOKMARKSLOT;
	}
}
