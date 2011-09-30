/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import java.util.Collection;
import java.util.concurrent.Future;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.zone.L2ZoneType;


public class L2DamageZone extends L2ZoneType
{
	private int _damageHPPerSec;
	private int _damageMPPerSec;
	private Future<?> _task;

	private int _castleId;
	private Castle _castle;

	public L2DamageZone(int id)
	{
		super(id);
		
		// Setup default damage
		_damageHPPerSec = 200;
		_damageMPPerSec = 0;

		// no castle by default
		_castleId = 0;
		_castle = null;
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("dmgHPSec"))
		{
			_damageHPPerSec = Integer.parseInt(value);
		}
		else if (name.equals("dmgMPSec"))
		{
			_damageMPPerSec = Integer.parseInt(value);
		}
		else if (name.equals("castleId"))
		{
			_castleId = Integer.parseInt(value);
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (_task == null && (_damageHPPerSec != 0 || _damageMPPerSec != 0))
		{
			L2PcInstance player = character.getActingPlayer();
			if (getCastle() != null) // Castle zone
				if (!(getCastle().getSiege().getIsInProgress() && player != null && player.getSiegeState() != 2)) // Siege and no defender
					return;
			synchronized(this)
			{
				if (_task == null)
					_task = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new ApplyDamage(this), 10, 3300);
			}
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (_characterList.isEmpty() && _task != null)
		{
			stopTask();
		}
	}
	
	protected Collection<L2Character> getCharacterList()
	{
		return _characterList.values();
	}
	
	protected int getHPDamagePerSecond()
	{
		return _damageHPPerSec;
	}
	
	protected int getMPDamagePerSecond()
	{
		return _damageMPPerSec;
	}
	
	protected void stopTask()
	{
		if (_task != null)
		{
			_task.cancel(false);
			_task = null;
		}
	}

	private Castle getCastle()
	{
		if (_castleId > 0 &&_castle == null)
			_castle = CastleManager.getInstance().getCastleById(_castleId);

		return _castle;
	}

	class ApplyDamage implements Runnable
	{
		private final L2DamageZone _dmgZone;
		private final Castle _castle;
		
		ApplyDamage(L2DamageZone zone)
		{
			_dmgZone = zone;
			_castle = zone.getCastle();
		}
		
		public void run()
		{
			boolean siege = false;

			if (_castle != null)
			{
				siege = _castle.getSiege().getIsInProgress();
				// castle zones active only during siege
				if (!siege)
				{
					_dmgZone.stopTask();
					return;
				}
			}

			for (L2Character temp : _dmgZone.getCharacterList())
			{
				if (temp != null && !temp.isDead())
				{
					if (siege)
					{
						// during siege defenders not affected
						final L2PcInstance player = temp.getActingPlayer();
						if (player != null && player.isInSiege() && player.getSiegeState() == 2)
							continue;
					}

					if (getHPDamagePerSecond() != 0)
						temp.reduceCurrentHp(_dmgZone.getHPDamagePerSecond(), null, null);
					if (getMPDamagePerSecond() != 0)
						temp.reduceCurrentMp(_dmgZone.getMPDamagePerSecond());
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
}