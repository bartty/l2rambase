/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.zone.L2ZoneType;


public class L2SwampZone extends L2ZoneType
{
	private int _move_bonus;

	private int _castleId;
	private Castle _castle;

	public L2SwampZone(int id)
	{
		super(id);
		
		// Setup default speed reduce (in %)
		_move_bonus = -50;

		// no castle by default
		_castleId = 0;
		_castle = null;
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("move_bonus"))
		{
			_move_bonus = Integer.parseInt(value);
		}
		else if (name.equals("castleId"))
		{
			_castleId = Integer.parseInt(value);
		}
		else
			super.setParameter(name, value);
	}
	
	private Castle getCastle()
	{
		if (_castleId > 0 && _castle == null)
			_castle = CastleManager.getInstance().getCastleById(_castleId);

		return _castle;
	}

	@Override
	protected void onEnter(L2Character character)
	{
		if (getCastle() != null)
		{
			// castle zones active only during siege
			if (!getCastle().getSiege().getIsInProgress() || !getCastle().getSiege().isTrapsActive())
				return;

			// defenders not affected
			final L2PcInstance player = character.getActingPlayer();
			if (player != null && player.isInSiege() && player.getSiegeState() == 2)
				return;
		}

		character.setInsideZone(L2Character.ZONE_SWAMP, true);
		if (character instanceof L2PcInstance)
		{
			((L2PcInstance) character).broadcastUserInfo();
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		// don't broadcast info if not needed
		if (character.isInsideZone(L2Character.ZONE_SWAMP))
		{
			character.setInsideZone(L2Character.ZONE_SWAMP, false);
			if (character instanceof L2PcInstance)
			{
				((L2PcInstance) character).broadcastUserInfo();
			}
		}
	}
	
	public int getMoveBonus()
	{
		return _move_bonus;
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
	}
	
	@Override
	public void onReviveInside(L2Character character)
	{
	}	
}