/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import l2.rambase.gameserver.TaskPriority;
import l2.rambase.gameserver.instancemanager.BoatManager;
import l2.rambase.gameserver.model.actor.instance.L2BoatInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.MoveToLocationInVehicle;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.item.L2WeaponType;
import l2.rambase.util.Point3D;


public final class RequestMoveToLocationInVehicle extends L2GameClientPacket
{
	private static final String _C__75_MOVETOLOCATIONINVEHICLE = "[C] 75 RequestMoveToLocationInVehicle";

	private int _boatId;
	private Point3D _pos;
	private Point3D _origin_pos;

	public TaskPriority getPriority() { return TaskPriority.PR_HIGH; }

	@Override
	protected void readImpl()
	{
		int _x, _y, _z;
		_boatId  = readD();   //objectId of boat
		_x = readD();
		_y = readD();
		_z = readD();
		_pos = new Point3D(_x, _y, _z);
		_x = readD();
		_y = readD();
		_z = readD();
		_origin_pos = new Point3D(_x, _y, _z);
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected
	void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (activeChar.isAttackingNow()
				&& activeChar.getActiveWeaponItem() != null
				&& (activeChar.getActiveWeaponItem().getItemType() == L2WeaponType.BOW))
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isSitting() || activeChar.isMovementDisabled())
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.getPet() != null)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.RELEASE_PET_ON_BOAT));
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isTransformed())
		{
			activeChar.sendPacket(new SystemMessage(SystemMessageId.CANT_POLYMORPH_ON_BOAT));
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final L2BoatInstance boat;
		if (activeChar.isInBoat())
		{
			boat = activeChar.getBoat();
			if (boat.getObjectId() != _boatId)
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
		}
		else
		{
			boat = BoatManager.getInstance().getBoat(_boatId);
			if (boat == null)
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			activeChar.setVehicle(boat);
		}

		activeChar.setInVehiclePosition(_pos);
		activeChar.broadcastPacket(new MoveToLocationInVehicle(activeChar, _pos, _origin_pos));
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__75_MOVETOLOCATIONINVEHICLE;
	}
}