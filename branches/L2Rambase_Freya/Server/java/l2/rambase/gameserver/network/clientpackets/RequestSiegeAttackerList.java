/**
 * @author L2Rambase Project
 */ 
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.network.serverpackets.SiegeAttackerList;

public final class RequestSiegeAttackerList extends L2GameClientPacket
{

    private static final String _C__A2_RequestSiegeAttackerList = "[C] a2 RequestSiegeAttackerList";


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
    	SiegeAttackerList sal = new SiegeAttackerList(castle);
    	sendPacket(sal);      
    }


    @Override
	public String getType()
    {
        return _C__A2_RequestSiegeAttackerList;
    }
}
