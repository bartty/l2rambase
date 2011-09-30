/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

/**
 * Format: (ch) d
 * @author  Deyoun
 *
 */
public final class AnswerJoinPartyRoom extends L2GameClientPacket
{
    private static final String _C__D0_15_ANSWERJOINPARTYROOM = "[C] D0:15 AnswerJoinPartyRoom";
    @SuppressWarnings("unused")
	private int _requesterID;

    @Override
	protected void readImpl()
    {
        _requesterID = readD();
    }

    /**
     * @see l2.rambase.util.network.BaseRecievePacket.ClientBasePacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
    }

    /**
     * @see l2.rambase.gameserver.BasePacket#getType()
     */
    @Override
    public String getType()
    {
        return _C__D0_15_ANSWERJOINPARTYROOM;
    }

}