/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;


/**
 *  
 *  Packets Structure: chddd
 */
public final class RequestChangeBookMarkSlot extends L2GameClientPacket
{
	private static final String _C__51_REQUESTCHANGEBOOKMARKSLOT = "[C] 51 RequestChangeBookMarkSlot";

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
	}


	@Override
	public String getType()
	{
		return _C__51_REQUESTCHANGEBOOKMARKSLOT;
	}
}
