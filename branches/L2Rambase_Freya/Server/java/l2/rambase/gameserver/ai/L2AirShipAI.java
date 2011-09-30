/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai;

import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.ExMoveToLocationAirShip;
import l2.rambase.gameserver.network.serverpackets.ExStopMoveAirShip;


public class L2AirShipAI extends L2VehicleAI
{
	public L2AirShipAI(L2AirShipInstance.AIAccessor accessor)
	{
		super(accessor);
	}

	@Override
	protected void moveTo(int x, int y, int z)
	{
		if (!_actor.isMovementDisabled())
		{
			_clientMoving = true;
			_accessor.moveTo(x, y, z);
			_actor.broadcastPacket(new ExMoveToLocationAirShip(getActor()));
		}
	}

	@Override
	protected void clientStopMoving(L2CharPosition pos)
	{
		if (_actor.isMoving())
			_accessor.stopMove(pos);

		if (_clientMoving || pos != null)
		{
			_clientMoving = false;
			_actor.broadcastPacket(new ExStopMoveAirShip(getActor()));
		}
	}

	@Override
	public void describeStateToPlayer(L2PcInstance player)
	{
		if (_clientMoving)
			player.sendPacket(new ExMoveToLocationAirShip(getActor()));
	}

	@Override
	public L2AirShipInstance getActor()
	{
		return (L2AirShipInstance)_actor;
	}
}