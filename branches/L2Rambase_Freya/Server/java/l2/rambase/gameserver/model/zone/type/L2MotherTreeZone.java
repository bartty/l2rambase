/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;


public class L2MotherTreeZone extends L2ZoneType
{
	private int _enterMsg;
	private int _leaveMsg;
	private int _mpRegen;
	private int _hpRegen;
	
	public L2MotherTreeZone(int id)
	{
		super(id);
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("enterMsgId"))
		{
			_enterMsg = Integer.valueOf(value);
		}
		else if (name.equals("leaveMsgId"))
		{
			_leaveMsg = Integer.valueOf(value);
		}
		else if (name.equals("MpRegenBonus"))
		{
			_mpRegen = Integer.valueOf(value);
		}
		else if (name.equals("HpRegenBonus"))
		{
			_hpRegen = Integer.valueOf(value);
		}
		else
			super.setParameter(name, value);
		
	}
	
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			L2PcInstance player = (L2PcInstance) character;
			player.setInsideZone(L2Character.ZONE_MOTHERTREE, true);
			if (_enterMsg != 0)
				player.sendPacket(new SystemMessage(_enterMsg));
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (character instanceof L2PcInstance)
		{
			L2PcInstance player = (L2PcInstance) character;
			player.setInsideZone(L2Character.ZONE_MOTHERTREE, false);
			if (_leaveMsg != 0)
				player.sendPacket(new SystemMessage(_leaveMsg));
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

	/**
	 * @return the _mpRegen
	 */
	public int getMpRegenBonus()
	{
		return _mpRegen;
	}

	/**
	 * @return the _hpRegen
	 */
	public int getHpRegenBonus()
	{
		return _hpRegen;
	}	
}