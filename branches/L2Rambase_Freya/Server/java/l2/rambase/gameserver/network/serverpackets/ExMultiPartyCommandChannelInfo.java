/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2CommandChannel;
import l2.rambase.gameserver.model.L2Party;


public class ExMultiPartyCommandChannelInfo extends L2GameServerPacket
{
	private static final String _S__FE_31_EXMULTIPARTYCOMMANDCHANNELINFO = "[S] FE:31 ExMultiPartyCommandChannelInfo";
	private L2CommandChannel _channel;
	
	
	public ExMultiPartyCommandChannelInfo(L2CommandChannel channel)
	{
		this._channel = channel;
	}
	
	/**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
	    return _S__FE_31_EXMULTIPARTYCOMMANDCHANNELINFO;
    }

	/**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
    	if (_channel == null)
    		return;
    	
	    writeC(0xfe);
	    writeH(0x31);
	    
	    writeS(_channel.getChannelLeader().getName()); // Channelowner
	    writeD(0); // Channelloot 0 or 1
	    writeD(_channel.getMemberCount());
	    
	    writeD(_channel.getPartys().size());
	    for(L2Party p : _channel.getPartys())
	    {
	    	writeS(p.getLeader().getName()); // Leadername
	    	writeD(p.getPartyLeaderOID()); // Leaders ObjId
	    	writeD(p.getMemberCount()); // Membercount
	    }
    }	
}