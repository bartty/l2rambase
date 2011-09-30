/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.TaskPriority;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.AirShipManager;
import l2.rambase.gameserver.model.L2CharPosition;
import l2.rambase.gameserver.model.VehiclePathPoint;
import l2.rambase.gameserver.model.actor.instance.L2AirShipInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;

public class MoveToLocationAirShip extends L2GameClientPacket
{
	public static final int MIN_Z = -895;
	public static final int MAX_Z = 6105;
	public static final int STEP = 300;

	private static final String _C__D0_38_MOVETOLOCATIONAIRSHIP = "[C] D0:38 MoveToLocationAirShip";

	private int _command;
	private int _param1;
	private int _param2 = 0;

	public TaskPriority getPriority()
	{
		return TaskPriority.PR_HIGH;
	}

	@Override
	protected void readImpl()
	{
		_command = readD();
		_param1 = readD();
		if (_buf.remaining() > 0)
			_param2 = readD();
	}

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (!activeChar.isInAirShip())
			return;

		final L2AirShipInstance ship = activeChar.getAirShip();
		if (!ship.isCaptain(activeChar))
			return;

		int z = ship.getZ();

		switch (_command)
		{
			case 0:
				if (!ship.canBeControlled())
					return;
				if (_param1 < 0xfffd76e8)
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new L2CharPosition(_param1, _param2, z, 0));
				break;
			case 1:
				if (!ship.canBeControlled())
					return;
				ship.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				break;
			case 2:
				if (!ship.canBeControlled())
					return;
				if (z < 6105)
				{
					z = Math.min(z + STEP, 6105);
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new L2CharPosition(ship.getX(), ship.getY(), z, 0));
				}
				break;
			case 3:
				if (!ship.canBeControlled())
					return;
				if (z > -895)
				{
					z = Math.max(z - STEP, -895);
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new L2CharPosition(ship.getX(), ship.getY(), z, 0));
				}
				break;
			case 4:
				if (!ship.isInDock() || ship.isMoving())
					return;

				final VehiclePathPoint[] dst = AirShipManager.getInstance().getTeleportDestination(ship.getDockId(), _param1);
				if (dst == null)
					return;

				// Consume fuel, if needed
				final int fuelConsumption = AirShipManager.getInstance().getFuelConsumption(ship.getDockId(), _param1);
				if (fuelConsumption > 0)
				{
					if (fuelConsumption > ship.getFuel())
					{
						activeChar.sendPacket(new SystemMessage(SystemMessageId.THE_AIRSHIP_CANNOT_TELEPORT));
						return;
					}
					ship.setFuel(ship.getFuel() - fuelConsumption);
				}

				ship.executePath(dst);
				break;
		}
	}

	/* (non-Javadoc)
	 * @see ru.l2nw.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_38_MOVETOLOCATIONAIRSHIP;
	}
}
