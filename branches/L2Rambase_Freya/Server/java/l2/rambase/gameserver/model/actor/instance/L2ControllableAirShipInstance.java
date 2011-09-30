/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.util.concurrent.Future;
import java.util.logging.Level;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.idfactory.IdFactory;
import l2.rambase.gameserver.model.actor.stat.ControllableAirShipStat;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.DeleteObject;
import l2.rambase.gameserver.network.serverpackets.MyTargetSelected;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.chars.L2CharTemplate;


public class L2ControllableAirShipInstance extends L2AirShipInstance
{
	private static final int HELM = 13556;
	private static final int LOW_FUEL = 40;

	private int _fuel = 0;
	private int _maxFuel = 0;

	private int _ownerId;
	private int _helmId;	
	private L2PcInstance _captain = null;

	private Future<?> _consumeFuelTask;
	private Future<?> _checkTask;

	public L2ControllableAirShipInstance(int objectId, L2CharTemplate template, int ownerId)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2ControllableAirShipInstance);
		_ownerId = ownerId;
		_helmId = IdFactory.getInstance().getNextId(); // not forget to release !
	}

	@Override
	public ControllableAirShipStat getStat()
	{
		return (ControllableAirShipStat)super.getStat();
	}

	@Override
	public void initCharStat()
	{
		setStat(new ControllableAirShipStat(this));
	}

	@Override
	public boolean canBeControlled()
	{
		return super.canBeControlled() && !isInDock();
	}

	@Override
	public boolean isOwner(L2PcInstance player)
	{
		if (_ownerId == 0)
			return false;

		return player.getClanId() == _ownerId || player.getObjectId() == _ownerId;
	}

	@Override
	public int getOwnerId()
	{
		return _ownerId;
	}

	@Override
	public boolean isCaptain(L2PcInstance player)
	{
		return _captain != null && player == _captain;
	}

	@Override
	public int getCaptainId()
	{
		return _captain != null ? _captain.getObjectId() : 0;
	}

	@Override
	public int getHelmObjectId()
	{
		return _helmId;
	}

	@Override
	public int getHelmItemId()
	{
		return HELM;
	}

	@Override
	public boolean setCaptain(L2PcInstance player)
	{
		if (player == null)
			_captain = null;
		else
		{
			if (_captain == null && player.getAirShip() == this)
			{
				final int x = player.getInVehiclePosition().getX() - 366;
				final int y = player.getInVehiclePosition().getY();
				final int z = player.getInVehiclePosition().getZ() - 107;
				if (x * x + y * y + z * z > 2500)
				{
					player.sendPacket(new SystemMessage(SystemMessageId.CANNOT_CONTROL_TOO_FAR));
					return false;
				}
				_captain = player;
				player.broadcastUserInfo();
			}
			else
				return false;
		}
		updateAbnormalEffect();
		return true;
	}

	@Override
	public int getFuel()
	{
		return _fuel;
	}

	@Override
	public void setFuel(int f)
	{

		final int old = _fuel;
		if (f < 0)
			_fuel = 0;
		else if (f > _maxFuel)
			_fuel = _maxFuel;
		else
			_fuel = f;

		if (_fuel == 0 && old > 0)
			broadcastToPassengers(new SystemMessage(SystemMessageId.THE_AIRSHIP_FUEL_HAS_RUN_OUT));
		else if (_fuel < LOW_FUEL)
			broadcastToPassengers(new SystemMessage(SystemMessageId.THE_AIRSHIP_FUEL_WILL_SOON_RUN_OUT));
	}

	@Override
	public int getMaxFuel()
	{
		return _maxFuel;
	}

	@Override
	public void setMaxFuel(int mf)
	{
		_maxFuel = mf;
	}

	@Override
	public void oustPlayer(L2PcInstance player)
	{
		if (player == _captain)
			setCaptain(null); // no need to broadcast userinfo here

		super.oustPlayer(player);
	}

	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		player.sendPacket(new MyTargetSelected(_helmId, 0));
		super.onAction(player, interact);
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		_checkTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckTask(), 60000L, 10000L);
		_consumeFuelTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new ConsumeFuelTask(), 60000L, 60000L);
	}

	@Override
	public void deleteMe()
	{
		super.deleteMe();

		if (_checkTask != null)
		{
			_checkTask.cancel(false);
			_checkTask = null;
		}
		if (_consumeFuelTask != null)
		{
			_consumeFuelTask.cancel(false);
			_consumeFuelTask = null;
		}

		try
		{
			broadcastPacket(new DeleteObject(_helmId));
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "Failed decayMe():"+e.getMessage());
		}
	}

	@Override
	public void refreshID()
	{
		super.refreshID();
		IdFactory.getInstance().releaseId(_helmId);
		_helmId = IdFactory.getInstance().getNextId();
	}

	@Override
	public void sendInfo(L2PcInstance activeChar)
	{
		super.sendInfo(activeChar);
		if (_captain != null)
			_captain.sendInfo(activeChar);
	}

	private final class ConsumeFuelTask implements Runnable
	{
		public void run()
		{
			int fuel = getFuel();
			if (fuel > 0)
			{
				fuel -= 10;
				if (fuel < 0)
					fuel = 0;

				setFuel(fuel);
				updateAbnormalEffect();
			}
		}
	}

	private final class CheckTask implements Runnable
	{
		public void run()
		{
			if (isVisible()
					&& isEmpty()
					&& !isInDock())
				// deleteMe() can't be called from CheckTask because task should not cancel itself
				ThreadPoolManager.getInstance().executeTask(new DecayTask());
		}
	}

	private final class DecayTask implements Runnable
	{
		public void run()
		{
				deleteMe();
		}
	}
}
