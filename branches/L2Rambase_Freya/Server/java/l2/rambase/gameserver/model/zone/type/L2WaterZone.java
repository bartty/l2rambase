/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import java.util.Collection;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.serverpackets.AbstractNpcInfo;
import l2.rambase.gameserver.network.serverpackets.ServerObjectInfo;


public class L2WaterZone extends L2ZoneType
{
	public L2WaterZone(int id)
	{
		super(id);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_WATER, true);
		
		if (character instanceof L2PcInstance)
		{
			if (((L2PcInstance) character).isTransformed() && !((L2PcInstance) character).isCursedWeaponEquipped())
			{
				character.stopTransformation(null);
			}
			else
				((L2PcInstance) character).broadcastUserInfo();
		}
		else if (character instanceof L2Npc)
		{
			Collection<L2PcInstance> plrs = character.getKnownList().getKnownPlayers().values();
			//synchronized (character.getKnownList().getKnownPlayers())
			{
				for (L2PcInstance player : plrs)
				{
					if (character.getRunSpeed() == 0)
						player.sendPacket(new ServerObjectInfo((L2Npc)character, player));
					else
						player.sendPacket(new AbstractNpcInfo.NpcInfo((L2Npc) character, player));
				}
			}
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_WATER, false);
		
		if (character instanceof L2PcInstance)
		{
			((L2PcInstance) character).broadcastUserInfo();
		}
		else if (character instanceof L2Npc)
		{
			Collection<L2PcInstance> plrs = character.getKnownList().getKnownPlayers().values();
			//synchronized (character.getKnownList().getKnownPlayers())
			{
				for (L2PcInstance player : plrs)
				{
					if (character.getRunSpeed() == 0)
						player.sendPacket(new ServerObjectInfo((L2Npc)character, player));
					else
						player.sendPacket(new AbstractNpcInfo.NpcInfo((L2Npc) character, player));
				}
			}
		}
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
	}
	
	@Override
	public void onReviveInside(L2Character character)
	{
	}
	
	public int getWaterZ()
	{
		return getZone().getHighZ();
	}
}