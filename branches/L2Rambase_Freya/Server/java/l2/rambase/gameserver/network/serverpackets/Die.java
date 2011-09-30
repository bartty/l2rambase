/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.datatables.AccessLevels;
import l2.rambase.gameserver.event.pvp.PvPEvent;
import l2.rambase.gameserver.event.tvt.TvTEvent;
import l2.rambase.gameserver.instancemanager.CastleManager;
import l2.rambase.gameserver.instancemanager.FortManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.L2AccessLevel;
import l2.rambase.gameserver.model.L2Clan;
import l2.rambase.gameserver.model.L2SiegeClan;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.entity.Castle;
import l2.rambase.gameserver.model.entity.Fort;



public class Die extends L2GameServerPacket
{
    private static final String _S__0B_DIE = "[S] 00 Die";
    private int _charObjId;
    private boolean _canTeleport;
    private boolean _sweepable;
    private L2AccessLevel _access = AccessLevels._userAccessLevel;
    private L2Clan _clan;
    L2Character _activeChar;
    private boolean _showFeather;
    
    public static final int FEATHER_OF_BLESSING_1 = 10649;
    public static final int FEATHER_OF_BLESSING_2 = 13300;
    public static final int PHOENIX_FEATHER = 13128;

    /**
     * @param _characters
     */
    public Die(L2Character cha)
    {
    	_access = AccessLevels._userAccessLevel;
        _showFeather = false;
        _activeChar = cha;
        if(cha instanceof L2PcInstance)
        {
            L2PcInstance player = (L2PcInstance)cha;
            _access = player.getAccessLevel();
            _clan = player.getClan();
            _showFeather = _access.allowFixedRes();
        }
        _charObjId = cha.getObjectId();
        _canTeleport = !(cha instanceof L2PcInstance) || (!TvTEvent.isStarted() || !TvTEvent.isPlayerParticipant(_charObjId)) || (!PvPEvent.isStarted() || !PvPEvent.isPlayerParticipant(_charObjId)) && !cha.isPendingRevive();
        if(cha instanceof L2Attackable)
            _sweepable = ((L2Attackable)cha).isSweepActive();
    }

    @Override
	protected final void writeImpl()
    {

        writeC(0x00);

        writeD(_charObjId);

        writeD(_canTeleport ? 0x01 : 0);
        if(_activeChar instanceof L2PcInstance)
        {
            _showFeather |= _activeChar.getInventory().getItemByItemId(FEATHER_OF_BLESSING_1) != null;
            _showFeather |= _activeChar.getInventory().getItemByItemId(FEATHER_OF_BLESSING_2) != null;
            _showFeather |= _activeChar.getInventory().getItemByItemId(PHOENIX_FEATHER) != null;
        }
        if (_canTeleport && _clan != null)
        {
            Boolean isInCastleDefense = false;
            Boolean isInFortDefense = false;

        	L2SiegeClan siegeClan = null;
            Castle castle = CastleManager.getInstance().getCastle(_activeChar);
			Fort fort = FortManager.getInstance().getFort(_activeChar);
			if (castle != null && castle.getSiege().getIsInProgress())
            {
            	//siege in progress
                siegeClan = castle.getSiege().getAttackerClan(_clan);
                if (siegeClan == null && castle.getSiege().checkIsDefender(_clan)){
                	isInCastleDefense = true;
                }
            }
            else if (fort != null && fort.getSiege().getIsInProgress())
            {
                //siege in progress
                siegeClan = fort.getSiege().getAttackerClan(_clan);
                if (siegeClan == null && fort.getSiege().checkIsDefender(_clan)){
                	isInFortDefense = true;
                }
            }

            writeD(_clan.getHasHideout() > 0 ? 0x01 : 0x00);            // 6d 01 00 00 00 - to hide away
            writeD(_clan.getHasCastle() > 0  ||
            		isInCastleDefense? 0x01 : 0x00);             		// 6d 02 00 00 00 - to castle
            writeD((TerritoryWarManager.getInstance().getFlagForClan(_clan) != null)
            		|| (siegeClan != null && !isInCastleDefense && !isInFortDefense
            		&& !siegeClan.getFlag().isEmpty()) ? 0x01 : 0x00);       // 6d 03 00 00 00 - to siege HQ
            writeD(_sweepable ? 0x01 : 0x00);                               // sweepable  (blue glow)
            writeD(_showFeather ? 0x01: 0x00);                  // 6d 04 00 00 00
            writeD(_clan.getHasFort() > 0  || isInFortDefense? 0x01 : 0x00);    // 6d 05 00 00 00 - to fortress
        }
        else
        {
            writeD(0x00);                                               // 6d 01 00 00 00 - to hide away
            writeD(0x00);                                               // 6d 02 00 00 00 - to castle
            writeD(0x00);                                               // 6d 03 00 00 00 - to siege HQ
            writeD(_sweepable ? 0x01 : 0x00);                               // sweepable  (blue glow)
            writeD(_showFeather ? 0x01: 0x00);                  // 6d 04 00 00 00
            writeD(0x00);    // 6d 05 00 00 00 - to fortress            
        }
    }

    @Override
	public String getType()
    {
        return _S__0B_DIE;
    }
}