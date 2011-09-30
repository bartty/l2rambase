/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.network.serverpackets.SiegeDefenderList;


public final class RequestSiegeDefenderList extends L2GameClientPacket
{
    private static final String _C__a3_RequestSiegeDefenderList = "[C] a3 RequestSiegeDefenderList";
    
    private int _castleId;

    @Override
	protected void readImpl()
    {
        _castleId = readD();
    }

    @Override
	protected void runImpl()
    {
    	Castle castle = CastleManager.getInstance().getCastleById(_castleId);
    	if (castle == null) 
    		return;
    	SiegeDefenderList sdl = new SiegeDefenderList(castle);
    	sendPacket(sdl);
    }

    @Override
	public String getType()
    {
        return _C__a3_RequestSiegeDefenderList;
    }
}