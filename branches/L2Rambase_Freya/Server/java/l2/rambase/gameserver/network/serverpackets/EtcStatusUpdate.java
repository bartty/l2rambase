/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class EtcStatusUpdate extends L2GameServerPacket
{
	private static final String _S__F3_ETCSTATUSUPDATE = "[S] f9 EtcStatusUpdate";

	private L2PcInstance _activeChar;

	public EtcStatusUpdate(L2PcInstance activeChar)
	{
		 _activeChar = activeChar;
	}

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xf9); // several icons to a separate line (0 = disabled)
		writeD(_activeChar.getCharges()); // 1-7 increase force, lvl
		writeD(_activeChar.getWeightPenalty()); // 1-4 weight penalty, lvl (1=50%, 2=66.6%, 3=80%, 4=100%)
		writeD((_activeChar.getMessageRefusal() || _activeChar.isChatBanned() || _activeChar.isSilenceMode()) ? 1 : 0); // 1 = block all chat 
		writeD(_activeChar.isInsideZone(L2Character.ZONE_DANGERAREA) ? 1 : 0); // 1 = danger area
		writeD(_activeChar.getExpertiseWeaponPenalty()); // Weapon Grade Penalty [1-4]
		writeD(_activeChar.getExpertiseArmorPenalty()); // Armor Grade Penalty [1-4]
		writeD(_activeChar.getCharmOfCourage() ? 1 : 0); // 1 = charm of courage (allows resurrection on the same spot upon death on the siege battlefield)
		writeD(_activeChar.getDeathPenaltyBuffLevel()); // 1-15 death penalty, lvl (combat ability decreased due to death)
		writeD(_activeChar.getSouls());        
	}

	/**
	 * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
	 */
	@Override
    public String getType()
	{
		return _S__F3_ETCSTATUSUPDATE;
	}
}