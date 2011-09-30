/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.SevenSigns;
/**
 * Changes the sky color depending on the outcome
 * of the Seven Signs competition.
 *
 * packet type id 0xf8
 * format: c h
 */
public class SSQInfo extends L2GameServerPacket
{
    private static final String _S__F8_SSQINFO = "[S] 73 SSQInfo";

    private static int _state = 0;

    public SSQInfo()
    {
        int compWinner = SevenSigns.getInstance().getCabalHighestScore();

        if (SevenSigns.getInstance().isSealValidationPeriod())
            if (compWinner == SevenSigns.CABAL_DAWN)
                _state = 2;
            else if (compWinner == SevenSigns.CABAL_DUSK)
                _state = 1;
    }

    public SSQInfo(int state)
    {
        _state = state;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0x73);

        if (_state == 2) // Dawn Sky
        {
            writeH(258);
        }
        else if (_state == 1) // Dusk Sky
        {
            writeH(257);
        }
        else
        {
            writeH(256);
        }
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__F8_SSQINFO;
    }
}