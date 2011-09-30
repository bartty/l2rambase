/**
 * @author L2Rambase Project
 * 
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.L2GameClient;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.ExPVPMatchCCRecord;


public abstract class RequestStartShowCrataeCubeRank extends L2GameClientPacket
{

    public RequestStartShowCrataeCubeRank()
    {
    }

    protected void readImpl()
    {
    }

    protected void runImpl()
    {
        L2PcInstance player = ((L2GameClient)getClient()).getActiveChar();
        if(player == null)
        {
            return;
        }
        else
        {
        	player.sendPacket(new ExPVPMatchCCRecord(2, ExPVPMatchCCRecord.EMPTY_ARRAY));
        	player.sendPacket(ActionFailed.STATIC_PACKET);
        	return; 
        }
    }
}