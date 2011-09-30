/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.stat;

import l2.rambase.gameserver.model.actor.instance.L2ControllableAirShipInstance;

public class ControllableAirShipStat extends VehicleStat
{
  public ControllableAirShipStat(L2ControllableAirShipInstance activeChar)
  {
    super(activeChar);
  }

  public L2ControllableAirShipInstance getActiveChar()
  {
    return (L2ControllableAirShipInstance)super.getActiveChar();
  }

  public float getMoveSpeed()
  {
    if ((getActiveChar().isInDock()) || (getActiveChar().getFuel() > 0)) {
      return super.getMoveSpeed();
    }
    return super.getMoveSpeed() * 0.05F;
  }
}