/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.List;

import l2.rambase.gameserver.instancemanager.CursedWeaponsManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.network.serverpackets.ExCursedWeaponList;

import javolution.util.FastList;

/**
 * Format: (ch)
 * 
 */
public class RequestCursedWeaponList extends L2GameClientPacket
{
	private static final String _C__D0_22_REQUESTCURSEDWEAPONLIST = "[C] D0:22 RequestCursedWeaponList";

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

		//send a ExCursedWeaponList
		List<Integer> list = new FastList<Integer>();
		for (int id : CursedWeaponsManager.getInstance().getCursedWeaponsIds())
			list.add(id);

		activeChar.sendPacket(new ExCursedWeaponList(list));
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__D0_22_REQUESTCURSEDWEAPONLIST;
	}
}
