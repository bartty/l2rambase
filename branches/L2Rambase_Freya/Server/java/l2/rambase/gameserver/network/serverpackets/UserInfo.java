/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.NpcTable;
import l2.rambase.gameserver.instancemanager.CursedWeaponsManager;
import l2.rambase.gameserver.instancemanager.TerritoryWarManager;
import l2.rambase.gameserver.model.Elementals;
import l2.rambase.gameserver.model.L2Transformation;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.itemcontainer.Inventory;
import l2.rambase.gameserver.skills.AbnormalEffect;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public final class UserInfo extends L2GameServerPacket
{
    private static final String _S__04_USERINFO = "[S] 32 UserInfo";
    private L2PcInstance _activeChar;
    
    /**
     * Run speed, swimming run speed and flying run speed
     */
    private int _runSpd;
    /**
     * Walking speed, swimming walking speed and flying walking speed
     */
    private int _walkSpd;
    private int _relation;
    private float _moveMultiplier;
    private int _territoryId;
    private boolean _isDisguised;
    private int _vehicleObjectId;
    private int _airShipHelm;

    /**
     * @param _characters
     */
    public UserInfo(L2PcInstance character)
    {
        _activeChar = character;

        _moveMultiplier = _activeChar.getMovementSpeedMultiplier();
        _runSpd = (int) (_activeChar.getRunSpeed() / _moveMultiplier);
        _walkSpd = (int) (_activeChar.getWalkSpeed() / _moveMultiplier);
        _territoryId = TerritoryWarManager.getInstance().getRegisteredTerritoryId(character);
        _relation = _activeChar.isClanLeader() ? 0x40 : 0;
        if (_activeChar.getSiegeState() == 1)
        {
        	if (_territoryId == 0)
        		_relation |= 0x180;
        	else
        		_relation |= 0x1000;
        }
        if (_activeChar.getSiegeState() == 2) 
        	_relation |= 0x80;
        _isDisguised = TerritoryWarManager.getInstance().isDisguised(character.getObjectId());
        if (_activeChar.isInBoat())
        	_vehicleObjectId = _activeChar.getBoat().getObjectId();
        else if (_activeChar.isInAirShip())
        	_vehicleObjectId = _activeChar.getAirShip().getObjectId();
        else
        	_vehicleObjectId = 0;
        if(_activeChar.isInAirShip() && _activeChar.getAirShip().isCaptain(_activeChar))
            _airShipHelm = _activeChar.getAirShip().getHelmItemId();
        else
            _airShipHelm = 0;
        	
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0x32);

        writeD(_activeChar.getX());
        writeD(_activeChar.getY());
        writeD(_activeChar.getZ());
        if(_activeChar.getVehicle() != null)
        	writeD(_vehicleObjectId); // heading from CT2.3 no longer used inside userinfo, here is now vehicle id (boat,airship)
        else
        	writeD(0);
        writeD(_activeChar.getObjectId());
        writeS(_activeChar.getName());
        writeD(_activeChar.getRace().ordinal());
        writeD(_activeChar.getAppearance().getSex()? 1 : 0);

        if (_activeChar.getClassIndex() == 0) 
        	writeD(_activeChar.getClassId().getId());
        else 
        	writeD(_activeChar.getBaseClass());

        writeD(_activeChar.getLevel());
        writeQ(_activeChar.getExp());
        writeD(_activeChar.getSTR());
        writeD(_activeChar.getDEX());
        writeD(_activeChar.getCON());
        writeD(_activeChar.getINT());
        writeD(_activeChar.getWIT());
        writeD(_activeChar.getMEN());
        writeD(_activeChar.getMaxVisibleHp());
        writeD((int) _activeChar.getCurrentHp());
        writeD(_activeChar.getMaxMp());
        writeD((int) _activeChar.getCurrentMp());
        writeD(_activeChar.getSp());
        writeD(_activeChar.getCurrentLoad());
        writeD(_activeChar.getMaxLoad());

        writeD(_activeChar.getActiveWeaponItem() != null ? 40 : 20); // 20 no weapon, 40 weapon equipped
        
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_UNDER));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_REAR));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEAR));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
        if(_airShipHelm == 0)
        {
            writeD(_activeChar.getInventory().getPaperdollObjectId(9));
            writeD(_activeChar.getInventory().getPaperdollObjectId(10));
        } else
        {
            writeD(0);
            writeD(0);
        }
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LRHAND));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR2));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RBRACELET));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LBRACELET));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO1));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO2));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO3));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO4));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO5));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DECO6));
        writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BELT)); // CT2.3
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_UNDER));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_NECK));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
        if(_airShipHelm == 0)
        {
            writeD(_activeChar.getInventory().getPaperdollItemId(9));
            writeD(_activeChar.getInventory().getPaperdollItemId(10));
        } else
        {
            writeD(0);
            writeD(0);
        }
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR2));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RBRACELET));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LBRACELET));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO1));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO2));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO3));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO4));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO5));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DECO6));
        writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BELT)); // CT2.3
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_UNDER));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_REAR));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_LEAR));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_NECK));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_RFINGER));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_LFINGER));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_HEAD));
        if(_airShipHelm == 0)
        {
            writeD(_activeChar.getInventory().getPaperdollAugmentationId(9));
            writeD(_activeChar.getInventory().getPaperdollAugmentationId(10));
        } else
        {
            writeD(0);
            writeD(0);
        }
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_GLOVES));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_CHEST));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_LEGS));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_FEET));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_BACK));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_LRHAND));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_HAIR));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_HAIR2));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_RBRACELET));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_LBRACELET));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO1));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO2));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO3));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO4));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO5));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_DECO6));
        writeD(_activeChar.getInventory().getPaperdollAugmentationId(Inventory.PAPERDOLL_BELT)); // CT2.3
        writeD(_activeChar.getInventory().getMaxTalismanCount()); // CT2.3
        writeD(_activeChar.getInventory().getCloakStatus()); // CT2.3
        writeD(_activeChar.getPAtk(null));
        writeD(_activeChar.getPAtkSpd());
        writeD(_activeChar.getPDef(null));
        writeD(_activeChar.getEvasionRate(null));
        writeD(_activeChar.getAccuracy());
        writeD(_activeChar.getCriticalHit(null, null));
        writeD(_activeChar.getMAtk(null, null));

        writeD(_activeChar.getMAtkSpd());
        writeD(_activeChar.getPAtkSpd());

        writeD(_activeChar.getMDef(null, null));

        writeD(_activeChar.getPvpFlag()); // 0-non-pvp  1-pvp = violett name
        writeD(_activeChar.getKarma());

        writeD(_runSpd);
        writeD(_walkSpd);
        writeD(_runSpd); // swim run speed
        writeD(_walkSpd); // swim walk speed
        writeD(0);
        writeD(0);
        writeD(_activeChar.isFlying() ? _runSpd : 0); // fly speed
        writeD(_activeChar.isFlying() ? _walkSpd : 0); // fly speed
        writeF(_moveMultiplier);
        writeF(_activeChar.getAttackSpeedMultiplier());

        L2Summon pet = _activeChar.getPet();
        L2Transformation trans;
        if (_activeChar.getMountType() != 0 && pet != null)
        {
            writeF(pet.getTemplate().fCollisionRadius);
            writeF(pet.getTemplate().fCollisionHeight);
        }
        else if ((trans = _activeChar.getTransformation()) != null)
        {
            writeF(trans.getCollisionRadius());
            writeF(trans.getCollisionHeight());
        }
        else
        {
            writeF(_activeChar.getCollisionRadius());
            writeF(_activeChar.getCollisionHeight());
        }

        writeD(_activeChar.getAppearance().getHairStyle());
        writeD(_activeChar.getAppearance().getHairColor());
        writeD(_activeChar.getAppearance().getFace());
        writeD(_activeChar.isGM() ? 1 : 0); // builder level

        String title = _activeChar.getTitle();
     	if (_activeChar.getAppearance().getInvisible() && _activeChar.isGM()) title = "Invisible";
        if (_activeChar.getPoly().isMorphed())
        {
        	L2NpcTemplate polyObj = NpcTable.getInstance().getTemplate(_activeChar.getPoly().getPolyId());
        	if(polyObj != null)
        		title += " - " + polyObj.name;
        }
        writeS(title);

        writeD(_activeChar.getClanId());
        writeD(_activeChar.getClanCrestId());
        writeD(_activeChar.getAllyId());
        writeD(_activeChar.getAllyCrestId()); // ally crest id
        // 0x40 leader rights
        // siege flags: attacker - 0x180 sword over name, defender - 0x80 shield, 0xC0 crown (|leader), 0x1C0 flag (|leader)
        writeD(_relation);
        writeC(_activeChar.getMountType()); // mount type
        writeC(_activeChar.getPrivateStoreType());
        writeC(_activeChar.hasDwarvenCraft() ? 1 : 0);
        writeD(_activeChar.getPkKills());
        writeD(_activeChar.getPvpKills());

        writeH(_activeChar.getCubics().size());
        for (int id : _activeChar.getCubics().keySet())
            writeH(id);

        writeC(_activeChar.isInPartyMatchRoom() ? 1 : 0);

        if (_activeChar.getAppearance().getInvisible() && _activeChar.isGM())
        	writeD(_activeChar.getAbnormalEffect() | AbnormalEffect.STEALTH.getMask());
        else
        	writeD(_activeChar.getAbnormalEffect());
        writeC(_activeChar.isFlyingMounted() ? 2 : 0);

        writeD(_activeChar.getClanPrivileges());

        writeH(_activeChar.getRecomLeft()); //c2  recommendations remaining
        writeH(_activeChar.getRecomHave()); //c2  recommendations received
        writeD(_activeChar.getMountNpcId() > 0 ? _activeChar.getMountNpcId() + 1000000 : 0);
        writeH(_activeChar.getInventoryLimit());

        writeD(_activeChar.getClassId().getId());
        writeD(0x00); // special effects? circles around player...
        writeD(_activeChar.getMaxCp());
        writeD((int) _activeChar.getCurrentCp());
        writeC(!_activeChar.isMounted() && _airShipHelm == 0 ? _activeChar.getEnchantEffect() : 0);

        if(_activeChar.getTeam()==1)
        	writeC(0x01); //team circle around feet 1= Blue, 2 = red
        else if(_activeChar.getTeam()==2)
        	writeC(0x02); //team circle around feet 1= Blue, 2 = red
        else
        	writeC(0x00); //team circle around feet 1= Blue, 2 = red

        writeD(_activeChar.getClanCrestLargeId());
        writeC(_activeChar.isNoble() ? 1 : 0); //0x01: symbol on char menu ctrl+I
        writeC(_activeChar.isHero() || (_activeChar.isGM() && Config.GM_HERO_AURA) ? 1 : 0); //0x01: Hero Aura

        writeC(_activeChar.isFishing() ? 1 : 0); //Fishing Mode
        writeD(_activeChar.getFishx()); //fishing x
        writeD(_activeChar.getFishy()); //fishing y
        writeD(_activeChar.getFishz()); //fishing z
        writeD(_activeChar.getAppearance().getNameColor());

		//new c5
       	writeC(_activeChar.isRunning() ? 0x01 : 0x00); //changes the Speed display on Status Window

        writeD(_activeChar.getPledgeClass()); //changes the text above CP on Status Window
        writeD(_activeChar.getPledgeType());

        writeD(_activeChar.getAppearance().getTitleColor());

        if (_activeChar.isCursedWeaponEquipped())
        	writeD(CursedWeaponsManager.getInstance().getLevel(_activeChar.getCursedWeaponEquippedId()));
        else
        	writeD(0x00);
        
        // T1 Starts
        writeD(_activeChar.getTransformationId());
        
        byte attackAttribute = _activeChar.getAttackElement();
		writeH(attackAttribute);
		writeH(_activeChar.getAttackElementValue(attackAttribute));
        writeH(_activeChar.getDefenseElementValue(Elementals.FIRE));
        writeH(_activeChar.getDefenseElementValue(Elementals.WATER));
        writeH(_activeChar.getDefenseElementValue(Elementals.WIND));
        writeH(_activeChar.getDefenseElementValue(Elementals.EARTH));
        writeH(_activeChar.getDefenseElementValue(Elementals.HOLY));
        writeH(_activeChar.getDefenseElementValue(Elementals.DARK));
        
        writeD(_activeChar.getAgathionId());
        
        // T2 Starts
        writeD(_activeChar.getFame());  // Fame
        writeD(_activeChar.isMinimapAllowed() ? 1: 0); // Minimap on Hellbound
        writeD(_activeChar.getVitalityPoints());  // Vitality Points
        writeD(_activeChar.getSpecialEffect());
        writeD(_territoryId); // CT2.3
        writeD(_isDisguised ? 0x01: 0x00); // CT2.3
        writeD(_territoryId); // CT2.3
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__04_USERINFO;
    }
}