/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class ExDominionWarStart extends L2GameServerPacket
{
	private static final String TYPE = "[S] FE:A3 ExDominionWarStart";
	
	private int _objId;
	private int _terId;
	private boolean _isDisguised;
	
	public ExDominionWarStart(L2PcInstance player)
	{
		_objId = player.getObjectId();
		_terId = TerritoryWarManager.getInstance().getRegisteredTerritoryId(player);
		_isDisguised = TerritoryWarManager.getInstance().isDisguised(_objId);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0xA3);
		writeD(_objId);
		writeD(1); //??
		writeD(_terId);
		writeD(_isDisguised ? 1 : 0);
		writeD(_isDisguised ? _terId : 0);
	}
	
	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
	public String getType()
	{
		return TYPE;
	}	
}