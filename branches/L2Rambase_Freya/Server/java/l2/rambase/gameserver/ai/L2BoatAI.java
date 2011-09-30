/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.ai;

import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.actor.instance.L2BoatInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.VehicleDeparture;
import l2.rambase.gameserver.network.serverpackets.VehicleInfo;
import l2.rambase.gameserver.network.serverpackets.VehicleStarted;


public class L2BoatAI extends L2VehicleAI
{
	public L2BoatAI(L2BoatInstance.AIAccessor accessor)
	{
		super(accessor);
	}

	@Override
	protected void moveTo(int x, int y, int z)
	{
		if (!_actor.isMovementDisabled())
		{
			if (!_clientMoving)
				_actor.broadcastPacket(new VehicleStarted(getActor(), 1));

			_clientMoving = true;
			_accessor.moveTo(x, y, z);
			_actor.broadcastPacket(new VehicleDeparture(getActor()));
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
			_actor.broadcastPacket(new VehicleStarted(getActor(), 0));
			_actor.broadcastPacket(new VehicleInfo(getActor()));
		}
	}

	@Override
	public void describeStateToPlayer(L2PcInstance player)
	{
		if (_clientMoving)
			player.sendPacket(new VehicleDeparture(getActor()));
	}

	@Override
	public L2BoatInstance getActor()
	{
		return (L2BoatInstance)_actor;
	}
}