/**
 * @author L2Rambase Project
 */
package handlers.itemhandlers;

import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2ControllableAirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;

public class EnergyStarStone extends ItemSkills
{
	/**
	 * 
	 * @see l2.rambase.gameserver.handler.IItemHandler#useItem(l2.rambase.gameserver.model.actor.L2Playable, l2.rambase.gameserver.model.L2ItemInstance)
	 */
	@Override
	public void useItem(L2Playable playable, L2ItemInstance item)
	{
		if (!(playable instanceof L2PcInstance))
			return;

		L2AirShipInstance ship = ((L2PcInstance)playable).getAirShip();
		if (ship == null)
		{
			playable.sendMessage("You cannot use that item because you don't summon airship yet.");
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if (!(ship instanceof L2ControllableAirShipInstance))
		{
			playable.sendMessage("This is not clan airship, so you can't use that item here.");
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		if (ship.getFuel() >= ship.getMaxFuel())
		{
			playable.sendMessage("Your airship don't need more fuel.");
			playable.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		super.useItem(playable, item);
	}
}