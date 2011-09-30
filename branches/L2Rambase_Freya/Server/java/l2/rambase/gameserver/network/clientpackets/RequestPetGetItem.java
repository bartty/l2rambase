/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.FortSiegeManager;
import l2.rambase.gameserver.instancemanager.MercTicketManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;


public final class RequestPetGetItem extends L2GameClientPacket
{
	private static final String _C__8f_REQUESTPETGETITEM= "[C] 8F RequestPetGetItem";

	private int _objectId;

	@Override
	protected void readImpl()
	{
		_objectId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2World world = L2World.getInstance();
		L2ItemInstance item = (L2ItemInstance)world.findObject(_objectId);
		if (item == null || getClient().getActiveChar() == null)
		    return;
		
		int castleId = MercTicketManager.getInstance().getTicketCastleId(item.getItemId());		
		if (castleId > 0) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}			
		
		if(getClient().getActiveChar().getPet() instanceof L2SummonInstance)
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		L2PetInstance pet = (L2PetInstance)getClient().getActiveChar().getPet();
		if (pet == null || pet.isDead() || pet.isOutOfControl())
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if(FortSiegeManager.getInstance().isCombat(item.getItemId()) )
		{
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		pet.getAI().setIntention(CtrlIntention.AI_INTENTION_PICK_UP, item);
	}

	@Override
	public String getType()
	{
		return _C__8f_REQUESTPETGETITEM;
	}

}
