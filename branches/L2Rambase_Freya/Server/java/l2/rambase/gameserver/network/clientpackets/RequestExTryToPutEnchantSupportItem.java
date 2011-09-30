/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ExPutEnchantSupportItemResult;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public class RequestExTryToPutEnchantSupportItem extends AbstractEnchantPacket
{

	private int _supportObjectId;
	private int _enchantObjectId;

	/**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#getType()
     */
    @Override
    public String getType()
    {
	    return "[C] D0:50 RequestExTryToPutEnchantSupportItem";
    }

	/**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
     */
    @Override
    protected void readImpl()
    {
	    _supportObjectId = readD();
	    _enchantObjectId = readD();
    }

	/**
     * @see l2.rambase.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
     */
    @Override
    protected void runImpl()
    {
    	L2PcInstance activeChar = this.getClient().getActiveChar();
	    if (activeChar != null)
	    {
	    	if (activeChar.isEnchanting())
	    	{
	    		L2ItemInstance item = (L2ItemInstance) L2World.getInstance().findObject(_enchantObjectId);
	    		L2ItemInstance support = (L2ItemInstance) L2World.getInstance().findObject(_supportObjectId);

	    		if (item == null || support == null)
	    			return;

	    		EnchantItem supportTemplate = getSupportItem(support);
	    		
	    		if (supportTemplate == null || !supportTemplate.isValid(item))
	    		{
	    			// message may be custom
	    			activeChar.sendPacket(new SystemMessage(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITION));
		    		activeChar.setActiveEnchantSupportItem(null);
	    			activeChar.sendPacket(new ExPutEnchantSupportItemResult(0));
	    			return;
	    		}
	    		activeChar.setActiveEnchantSupportItem(support);
				activeChar.sendPacket(new ExPutEnchantSupportItemResult(_supportObjectId));
	    	}
	    }
    }
	
}
