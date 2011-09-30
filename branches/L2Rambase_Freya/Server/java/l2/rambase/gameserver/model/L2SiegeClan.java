/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.util.List;

import l2.rambase.gameserver.model.actor.L2Npc;

import javolution.util.FastList;

public class L2SiegeClan
{
	// ==========================================================================================
	// Instance
	// ===============================================================
	// Data Field
	private int _clanId                = 0;
	private List<L2Npc> _flag  = new FastList<L2Npc>();
	private int _numFlagsAdded = 0;
	private SiegeClanType _type;

	public enum SiegeClanType
	{
		OWNER,
		DEFENDER,
		ATTACKER,
		DEFENDER_PENDING
	}

	// =========================================================
	// Constructor

	public L2SiegeClan(int clanId, SiegeClanType type)
	{
		_clanId = clanId;
		_type = type;
	}

	// =========================================================
	// Method - Public
	public int getNumFlags()
	{
		return _numFlagsAdded;
	}

	public void addFlag(L2Npc flag)
	{
		_numFlagsAdded++;
		getFlag().add(flag);
	}

	public boolean removeFlag(L2Npc flag)
	{
		if (flag == null) return false;
		boolean ret = getFlag().remove(flag);
		//check if null objects or duplicates remain in the list.
		//for some reason, this might be happening sometimes...
		// delete false duplicates: if this flag got deleted, delete its copies too.
		if (ret)
			while (getFlag().remove(flag)) ;

		flag.deleteMe();
		_numFlagsAdded--;
		return ret;
	}

	public void removeFlags()
	{
		for (L2Npc flag: getFlag())
			removeFlag(flag);
	}

	// =========================================================
	// Property
	public final int getClanId() { return _clanId; }

	public final List<L2Npc> getFlag()
	{
		if (_flag == null) _flag  = new FastList<L2Npc>();
		return _flag;
	}

	public SiegeClanType getType() { return _type; }

    public void setType(SiegeClanType setType) { _type = setType; }
}
