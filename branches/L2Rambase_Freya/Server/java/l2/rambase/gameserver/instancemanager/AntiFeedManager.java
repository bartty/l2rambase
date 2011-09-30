/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

import java.util.Map;

import l2.rambase.Config;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.L2GameClient;

import javolution.util.FastMap;

public class AntiFeedManager
{
	private Map<Integer,Long> _lastDeathTimes;

	public static final AntiFeedManager getInstance()
	{
		return SingletonHolder._instance;
	}

	private AntiFeedManager()
	{
		_lastDeathTimes = new FastMap<Integer,Long>().shared();
	}

	/**
	 * Set time of the last player's death to current
	 * @param objectId Player's objectId
	 */
	public final void setLastDeathTime(int objectId)
	{
		_lastDeathTimes.put(objectId, System.currentTimeMillis());
	}

	/**
	 * Check if current kill should be counted as non-feeded.
	 * @param attacker Attacker character
	 * @param target Target character
	 * @return True if kill is non-feeded.
	 */
	public final boolean check(L2Character attacker, L2Character target)
	{
		if (!Config.MOD_ANTIFEED_ENABLE)
			return true;

		if (target == null)
			return false;

		final L2PcInstance targetPlayer = target.getActingPlayer();
		if (targetPlayer == null)
			return false;

		if (Config.MOD_ANTIFEED_INTERVAL > 0
				&& _lastDeathTimes.containsKey(targetPlayer.getObjectId()))
			return (System.currentTimeMillis() - _lastDeathTimes.get(targetPlayer.getObjectId())) > Config.MOD_ANTIFEED_INTERVAL;

		if (Config.MOD_ANTIFEED_DUALBOX && attacker != null)
		{
			final L2PcInstance attackerPlayer = attacker.getActingPlayer();
			if (attackerPlayer == null)
				return false;

			final L2GameClient targetClient = targetPlayer.getClient();
			final L2GameClient attackerClient = attackerPlayer.getClient();
			if (targetClient == null
					|| attackerClient == null
					|| targetClient.isDetached()
					|| attackerClient.isDetached())
				// unable to check ip address
				return !Config.MOD_ANTIFEED_DISCONNECTED_AS_DUALBOX;

			return !targetClient.getConnection().getInetAddress().equals(attackerClient.getConnection().getInetAddress());
		}

		return true;
	}

	/**
	 * Clears all timestamps
	 */
	public final void clear()
	{
		_lastDeathTimes.clear();
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final AntiFeedManager _instance = new AntiFeedManager();
	}
}