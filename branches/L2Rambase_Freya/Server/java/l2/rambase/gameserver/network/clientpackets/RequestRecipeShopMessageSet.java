/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.Config; 
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.util.Util;


public class RequestRecipeShopMessageSet extends L2GameClientPacket
{
 	private static final String _C__B1_RequestRecipeShopMessageSet = "[C] b1 RequestRecipeShopMessageSet"; 
 	
 	private static final int MAX_MSG_LENGTH = 29;
 	private String _name; 
 	
 	@Override 
	protected void readImpl()
    {
        _name = readS();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance player = getClient().getActiveChar(); 
		if (player == null) 
			return;
		if (_name != null && _name.length() > MAX_MSG_LENGTH) 
		{ 
			Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to overflow recipe shop message", Config.DEFAULT_PUNISH); 
			return; 
		} 
		
		
		if (player.getCreateList() != null) 
			player.getCreateList().setStoreName(_name); 
	} 
	
	@Override 
	public String getType()
	{ 
		return _C__B1_RequestRecipeShopMessageSet; 
	} 
}