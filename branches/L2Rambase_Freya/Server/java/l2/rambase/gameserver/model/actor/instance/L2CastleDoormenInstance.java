/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.util.StringTokenizer;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2CastleDoormenInstance extends L2DoormenInstance
{
	public L2CastleDoormenInstance(int objectID, L2NpcTemplate template)
	{
		super(objectID, template);
		setInstanceType(InstanceType.L2CastleDoormenInstance);
	}

	@Override
	protected final void openDoors(L2PcInstance player, String command)
	{
		StringTokenizer st = new StringTokenizer(command.substring(10), ", ");
		st.nextToken();

		while (st.hasMoreTokens())
		{
			getCastle().openDoor(player, Integer.parseInt(st.nextToken()));
		}
	}

	@Override
	protected final void closeDoors(L2PcInstance player, String command)
	{
		StringTokenizer st = new StringTokenizer(command.substring(11), ", ");
		st.nextToken();

		while (st.hasMoreTokens())
		{
			getCastle().closeDoor(player, Integer.parseInt(st.nextToken()));
		}
	}

	@Override
	protected final boolean isOwnerClan(L2PcInstance player)
	{
		if (player.getClan() != null && getCastle() != null)
		{
			// player should have privileges to open doors
			if (player.getClanId() == getCastle().getOwnerId()
					&& (player.getClanPrivileges() & L2Clan.CP_CS_OPEN_DOOR) == L2Clan.CP_CS_OPEN_DOOR)
				return true;
		}
		return false;
	}

	@Override
	protected final boolean isUnderSiege()
	{
		return getCastle().getZone().isActive();
	}
}