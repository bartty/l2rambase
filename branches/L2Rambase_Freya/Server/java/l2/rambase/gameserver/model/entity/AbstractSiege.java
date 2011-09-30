/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.entity;

import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2SiegeClan;


public abstract class AbstractSiege
{
	public abstract boolean getIsInProgress();

	public abstract boolean checkIsAttacker(L2Clan clan);

	public abstract boolean checkIsDefender(L2Clan clan);

	public abstract L2SiegeClan getAttackerClan(L2Clan clan);

	public abstract L2SiegeClan getDefenderClan(L2Clan clan);
}