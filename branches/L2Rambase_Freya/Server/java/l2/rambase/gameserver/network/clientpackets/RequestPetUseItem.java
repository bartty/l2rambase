/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.rambase.Config;
import l2.rambase.gameserver.datatables.PetDataTable;
import l2.rambase.gameserver.handler.IItemHandler;
import l2.rambase.gameserver.handler.ItemHandler;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.PetItemList;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.templates.item.L2ArmorType;
import l2.rambase.gameserver.templates.item.L2Item;

public final class RequestPetUseItem extends L2GameClientPacket
{
	private static Logger _log = Logger.getLogger(RequestPetUseItem.class.getName());
	private static final String _C__8A_REQUESTPETUSEITEM = "[C] 8a RequestPetUseItem";

	private int _objectId;

	@Override
	protected void readImpl()
	{
		_objectId = readD();
	}

	@Override
	protected void runImpl()
	{
		L2PcInstance activeChar = getClient().getActiveChar();
		if (activeChar == null)
		    return;

		L2PetInstance pet = (L2PetInstance)activeChar.getPet();
		if (pet == null)
			return;

		L2ItemInstance item = pet.getInventory().getItemByObjectId(_objectId);
        if (item == null)
            return;

        if (item.isWear())
            return;

		int itemId = item.getItemId();

		if (activeChar.isAlikeDead() || pet.isDead())
        {
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_CANNOT_BE_USED);
			sm.addItemName(item);
			activeChar.sendPacket(sm);
			sm = null;
			return;
		}

		if (Config.DEBUG)
			_log.finest(activeChar.getObjectId()+": pet use item " + _objectId);

		if (!item.isEquipped())
		{
			if (!item.getItem().checkCondition(pet, pet, true))
				return;
		}

		//check if the item matches the pet
		if (item.isEquipable())
		{
			if (item.getItem().getBodyPart() == L2Item.SLOT_NECK)
			{
				if (item.getItem().getItemType() == L2ArmorType.PET)
				{
					useItem(pet, item, activeChar);
					return;
				}
			}
			if (PetDataTable.isWolf(pet.getNpcId()) && // wolf
                    item.getItem().isForWolf())
			{
				useItem(pet, item, activeChar);
				return;
			}
            else if (PetDataTable.isEvolvedWolf(pet.getNpcId()) && // evolved wolf
                    (item.getItem().isForEvolvedWolf()||item.getItem().isForWolf()))
            {
                useItem(pet, item, activeChar);
                return;
            }
			else if (PetDataTable.isHatchling(pet.getNpcId()) && // hatchlings
                        item.getItem().isForHatchling())
			{
				useItem(pet, item, activeChar);
				return;
			}
            else if (PetDataTable.isStrider(pet.getNpcId()) && // striders
                    item.getItem().isForStrider())
            {
                useItem(pet, item, activeChar);
                return;
            }
            else if (PetDataTable.isBaby(pet.getNpcId()) && // baby pets (buffalo, cougar, kookaboora)
                    item.getItem().isForBabyPet())
            {
                useItem(pet, item, activeChar);
                return;
            }
            else if (PetDataTable.isImprovedBaby(pet.getNpcId()) && // Improved baby pets (buffalo, cougar, kookaboora)
                    item.getItem().isForBabyPet())
            {
                useItem(pet, item, activeChar);
                return;
            }            
			else
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.PET_CANNOT_USE_ITEM));
                return;
			}
		}
		else if (PetDataTable.isPetFood(itemId))
		{
			if (PetDataTable.isWolf(pet.getNpcId()) && PetDataTable.isWolfFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isEvolvedWolf(pet.getNpcId()) && PetDataTable.isEvolvedWolfFood(itemId))
            {
            	useItem(pet, item, activeChar);
                return;
            }
			else if (PetDataTable.isSinEater(pet.getNpcId()) && PetDataTable.isSinEaterFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isHatchling(pet.getNpcId()) && PetDataTable.isHatchlingFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isStrider(pet.getNpcId()) && PetDataTable.isStriderFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isWyvern(pet.getNpcId()) && PetDataTable.isWyvernFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isBaby(pet.getNpcId()) && PetDataTable.isBabyFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isImprovedBaby(pet.getNpcId()) && PetDataTable.isImprovedBabyFood(itemId))
			{
				useItem(pet, item, activeChar);
				return;
			}
			else if (PetDataTable.isDino(pet.getNpcId()) && PetDataTable.isDinoFood(itemId))
            {
                useItem(pet, item, activeChar);
                return;
            }            	
            else if (PetDataTable.isGraciaPet(pet.getNpcId()) && PetDataTable.isGraciaPetFood(itemId))
            {
                useItem(pet, item, activeChar);
                return;
            }
            else if (PetDataTable.isBabyRudolph(pet.getNpcId()) && PetDataTable.isBabyRudolphFood(itemId))
            {
                useItem(pet, item, activeChar);
                return;
            }
			else
			{
				activeChar.sendPacket(new SystemMessage(SystemMessageId.PET_CANNOT_USE_ITEM));
				return;
			}
		}

	    IItemHandler handler = ItemHandler.getInstance().getItemHandler(item.getEtcItem());
	    if (handler != null)
			useItem(pet, item, activeChar);
		else
			activeChar.sendPacket(new SystemMessage(SystemMessageId.PET_CANNOT_USE_ITEM));

		return;
	}

	private synchronized void useItem(L2PetInstance pet, L2ItemInstance item, L2PcInstance activeChar)
	{
		if (item.isEquipable())
		{
			if (item.isEquipped())
			{
				pet.getInventory().unEquipItemInSlot(item.getLocationSlot());
				switch (item.getItem().getBodyPart())
				{
					case L2Item.SLOT_R_HAND:
						pet.setWeapon(0);
						break;
					case L2Item.SLOT_CHEST:
						pet.setArmor(0);
						break;
					case L2Item.SLOT_NECK:
						pet.setJewel(0);
						break;
				}
			}
			else
			{
				pet.getInventory().equipItem(item);
				switch (item.getItem().getBodyPart())
				{
					case L2Item.SLOT_R_HAND:
						pet.setWeapon(item.getItemId());
						break;
					case L2Item.SLOT_CHEST:
						pet.setArmor(item.getItemId());
						break;
					case L2Item.SLOT_NECK:
						pet.setJewel(item.getItemId());
						break;
				}
			}

			activeChar.sendPacket(new PetItemList(pet));
			pet.updateAndBroadcastStatus(1);
		}
		else
		{
			IItemHandler handler = ItemHandler.getInstance().getItemHandler(item.getEtcItem());
			if (handler != null)
			{
				handler.useItem(pet, item);
				pet.updateAndBroadcastStatus(1);
			}
			else
				_log.warning("no itemhandler registered for itemId:" + item.getItemId());
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__8A_REQUESTPETUSEITEM;
	}
}
