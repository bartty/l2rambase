/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.util.Rnd;

/**
 * This class defines the spawn data of a Minion type
 * In a group mob, there are one master called RaidBoss and several slaves called Minions.
 *
 * <B><U> Data</U> :</B><BR><BR>
 * <li>_minionId : The Identifier of the L2Minion to spawn </li>
 * <li>_minionAmount :  The number of this Minion Type to spawn </li><BR><BR>
 *
 */

public class L2MinionData
{

	/** The Identifier of the L2Minion */
	private int _minionId;

	/** The number of this Minion Type to spawn */
	private int _minionAmount;
	private int _minionAmountMin;
	private int _minionAmountMax;


	/**
	 * Set the Identifier of the Minion to spawn.<BR><BR>
	 *
	 * @param if The L2Character Identifier to spawn
	 *
	 */
	public void setMinionId(int id)
	{
		_minionId = id;
	}

	/**
	 * Return the Identifier of the Minion to spawn.<BR><BR>
	 */
	public int getMinionId()
	{
		return _minionId;
	}

	/**
	 * Set the minimum of minions to amount.<BR><BR>
	 *
	 * @param amountMin The minimum quantity of this Minion type to spawn
	 *
	 */
	public void setAmountMin(int amountMin)
	{
		_minionAmountMin = amountMin;
	}

	/**
	 * Set the maximum of minions to amount.<BR><BR>
	 *
	 * @param amountMax The maximum quantity of this Minion type to spawn
	 *
	 */
	public void setAmountMax(int amountMax)
	{
		_minionAmountMax = amountMax;
	}

	/**
	 * Set the amount of this Minion type to spawn.<BR><BR>
	 *
	 * @param amount The quantity of this Minion type to spawn
	 *
	 */
	public void setAmount(int amount)
	{
		_minionAmount = amount;
	}

	/**
	 * Return the amount of this Minion type to spawn.<BR><BR>
	 */
	public int getAmount()
	{
		if (_minionAmountMax > _minionAmountMin)
		{
			_minionAmount = Rnd.get(_minionAmountMin, _minionAmountMax);
			return _minionAmount;
		}
		else
		{
			return _minionAmountMin;
		}
	}

}
