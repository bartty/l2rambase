/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2Clan;


public class ManagePledgePower extends L2GameServerPacket
{
    private static final String _S__30_MANAGEPLEDGEPOWER = "[S] 2a ManagePledgePower";

    private int _action;
    private L2Clan _clan;
    private int _rank;
    private int _privs;

    public ManagePledgePower(L2Clan clan, int action, int rank)
    {
        _clan = clan;
        _action = action;
        _rank = rank;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0x2a);
        if(_action == 1)
        {
        	_privs = _clan.getRankPrivs(_rank);
        }
        else
        {
            return;
        }        
        writeD(0);
        writeD(0);
        writeD(_privs);
   }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__30_MANAGEPLEDGEPOWER;
    }
}