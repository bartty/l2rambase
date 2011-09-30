/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.CursedWeaponsManager;
import l2.rambase.gameserver.model.CursedWeapon;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.network.serverpackets.ExCursedWeaponLocation;
import l2.rambase.gameserver.network.serverpackets.ExCursedWeaponLocation.CursedWeaponInfo;
import l2.rambase.util.Point3D;

import javolution.util.FastList;

/**
 * Format: (ch)
 * 
 */
public final class RequestCursedWeaponLocation extends L2GameClientPacket
{
	private static final String _C__D0_23_REQUESTCURSEDWEAPONLOCATION = "[C] D0:23 RequestCursedWeaponLocation";

	@Override
	protected void readImpl()
	{
	}

	/**
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2Character activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		List<CursedWeaponInfo> list = new FastList<CursedWeaponInfo>();
		for (CursedWeapon cw : CursedWeaponsManager.getInstance().getCursedWeapons())
		{
			if (!cw.isActive())
				continue;

			Point3D pos = cw.getWorldPosition();
			if (pos != null)
				list.add(new CursedWeaponInfo(pos, cw.getItemId(), cw.isActivated() ? 1 : 0));
		}

		//send the ExCursedWeaponLocation
		if (!list.isEmpty())
			activeChar.sendPacket(new ExCursedWeaponLocation(list));
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_23_REQUESTCURSEDWEAPONLOCATION;
	}
}
