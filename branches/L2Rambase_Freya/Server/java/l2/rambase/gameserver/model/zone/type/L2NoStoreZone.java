/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;


public class L2NoStoreZone extends L2ZoneType
{
	public L2NoStoreZone(final int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(final L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			character.setInsideZone(L2Character.ZONE_NOSTORE, true);
		}
	}

	@Override
	protected void onExit(final L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			character.setInsideZone(L2Character.ZONE_NOSTORE, false);
		}
	}

	@Override
	public void onDieInside(final L2Character character)
	{
	}

	@Override
	public void onReviveInside(final L2Character character)
	{
	}
}