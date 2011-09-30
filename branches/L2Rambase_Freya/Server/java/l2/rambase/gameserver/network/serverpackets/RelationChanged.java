/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class RelationChanged extends L2GameServerPacket
{
	public static final int RELATION_PARTY1       = 0x00001; // party member
	public static final int RELATION_PARTY2       = 0x00002; // party member
	public static final int RELATION_PARTY3       = 0x00004; // party member
	public static final int RELATION_PARTY4       = 0x00008; // party member (for information, see L2PcInstance.getRelation())
	public static final int RELATION_PARTYLEADER  = 0x00010; // true if is party leader
	public static final int RELATION_HAS_PARTY    = 0x00020; // true if is in party
	public static final int RELATION_CLAN_MEMBER  = 0x00040; // true if is in clan
	public static final int RELATION_LEADER 	  = 0x00080; // true if is clan leader
	public static final int RELATION_CLAN_MATE    = 0x00100; // true if is in same clan
	public static final int RELATION_INSIEGE   	  = 0x00200; // true if in siege
	public static final int RELATION_ATTACKER     = 0x00400; // true when attacker
	public static final int RELATION_ALLY         = 0x00800; // blue siege icon, cannot have if red
	public static final int RELATION_ENEMY        = 0x01000; // true when red icon, doesn't matter with blue
	public static final int RELATION_MUTUAL_WAR   = 0x04000; // double fist
	public static final int RELATION_1SIDED_WAR   = 0x08000; // single fist
	public static final int RELATION_ALLY_MEMBER  = 0x10000; // clan is in alliance
	public static final int RELATION_TERRITORY_WAR= 0x80000; // show Territory War icon

	private static final String _S__CE_RELATIONCHANGED = "[S] ce RelationChanged";

	private int _objId, _relation, _autoAttackable, _karma, _pvpFlag;

	public RelationChanged(L2Playable activeChar, int relation, boolean autoattackable)
	{
		_objId = activeChar.getObjectId();
		_relation = relation;
		_autoAttackable = autoattackable ? 1 : 0;

		if (activeChar instanceof L2PcInstance)
		{
			_karma = ((L2PcInstance)activeChar).getKarma();
			_pvpFlag = ((L2PcInstance)activeChar).getPvpFlag();
			_invisible = ((L2PcInstance)activeChar).getAppearance().getInvisible();
		}
		else if (activeChar instanceof L2Summon)
		{
			_karma =  ((L2Summon)activeChar).getOwner().getKarma();
			_pvpFlag = ((L2Summon)activeChar).getOwner().getPvpFlag();
			_invisible = ((L2Summon)activeChar).getOwner().getAppearance().getInvisible();
		}
	}

	/**
	 * @see l2.rambase.util.network.BaseSendablePacket.ServerBasePacket#writeImpl()
	 */
	@Override
	protected final void writeImpl()
	{
		writeC(0xce);
		writeD(1); // CT24 unknown
		writeD(_objId);
		writeD(_relation);
		writeD(_autoAttackable);
		writeD(_karma);
		writeD(_pvpFlag);
	}

	/**
	 * @see l2.rambase.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__CE_RELATIONCHANGED;
	}
}