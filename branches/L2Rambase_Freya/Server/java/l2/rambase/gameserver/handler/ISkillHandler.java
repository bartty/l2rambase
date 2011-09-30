/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.handler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.model.L2ItemInstance;
import l2.rambase.gameserver.model.L2Object;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2World;
import l2.rambase.gameserver.model.actor.L2Attackable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.network.serverpackets.MagicSkillLaunched;
import l2.rambase.gameserver.network.serverpackets.PetItemList;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.gameserver.templates.skills.L2SkillType;


public interface ISkillHandler
{
	public static Logger _log = Logger.getLogger(ISkillHandler.class.getName());
	/**
	 * this is the worker method that is called when using an item.
	 * @param activeChar
	 * @param item
	 * @param target
	 * @return count reduction after usage
	 * @throws IOException
	 */
	public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets) throws IOException;
	
	/**
	 * this method is called at initialization to register all the item ids automatically
	 * @return all known itemIds
	 */
	public L2SkillType[] getSkillIds();
	
	public static class CrystalFinalizer implements Runnable
	{
		private L2PcInstance _activeChar;
		private L2Attackable _target;
		private int _crystalId;
		
		public CrystalFinalizer(L2PcInstance activeChar, L2Object target, int crystalId)
		{
			_activeChar = activeChar;
			_target = (L2Attackable) target;
			_crystalId = crystalId;
		}
		
		public void run()
		{
			if (_activeChar.isDead() || _target.isDead())
				return;
			
			_activeChar.enableAllSkills();
			
			try
			{
				_target.addAbsorber(_activeChar, _crystalId);
				_activeChar.setTarget(_target);
			}
			catch (Exception e)
			{
			}
		}
	}
	public static class PetSummonFinalizer implements Runnable
	{
		private final L2PcInstance _activeChar;
		private final L2ItemInstance _item;
		private final L2NpcTemplate _npcTemplate;

		public PetSummonFinalizer(L2PcInstance activeChar, L2NpcTemplate npcTemplate, L2ItemInstance item)
		{
			_activeChar = activeChar;
			_npcTemplate = npcTemplate;
			_item = item;
		}

		public void run()
		{
			try
			{
				_activeChar.sendPacket(new MagicSkillLaunched(_activeChar, 2046, 1));
				_activeChar.setIsCastingNow(false);

				// check for summon item validity
				if (_item == null
						|| _item.getOwnerId() != _activeChar.getObjectId()
						|| _item.getLocation() != L2ItemInstance.ItemLocation.INVENTORY)
					return;

				final L2PetInstance petSummon = L2PetInstance.spawnPet(_npcTemplate, _activeChar, _item);
				if (petSummon == null)
					return;

				petSummon.setShowSummonAnimation(true);
				petSummon.setTitle(_activeChar.getName());

				if (!petSummon.isRespawned())
				{
					petSummon.setCurrentHp(petSummon.getMaxHp());
					petSummon.setCurrentMp(petSummon.getMaxMp());
					petSummon.getStat().setExp(petSummon.getExpForThisLevel());
					petSummon.setCurrentFed(petSummon.getMaxFed());
				}

				petSummon.setRunning();

				if (!petSummon.isRespawned())
					petSummon.store();

				_activeChar.setPet(petSummon);

				L2World.getInstance().storeObject(petSummon);
				petSummon.spawnMe(_activeChar.getX() + 50, _activeChar.getY() + 100, _activeChar.getZ());
				petSummon.startFeed();
				_item.setEnchantLevel(petSummon.getLevel());

				if (petSummon.getCurrentFed() <= 0)
					ThreadPoolManager.getInstance().scheduleGeneral(new PetSummonFeedWait(_activeChar, petSummon), 60000);
				else
					petSummon.startFeed();

				petSummon.setFollowStatus(true);
				final int weaponId = petSummon.getWeapon();
				final int armorId = petSummon.getArmor();
				final int jewelId = petSummon.getJewel();
				if (weaponId > 0 && petSummon.getOwner().getInventory().getItemByItemId(weaponId) != null)
				{
					final L2ItemInstance item = petSummon.getOwner().getInventory().getItemByItemId(weaponId);
					final L2ItemInstance newItem = petSummon.getOwner().transferItem("Transfer", item.getObjectId(), 1, petSummon.getInventory(), petSummon); 
					if (newItem == null)
					{
						_log.warning("Invalid item transfer request: " + petSummon.getName() + "(pet) --> " + petSummon.getOwner().getName());
						petSummon.setWeapon(0);
					}
					else
						petSummon.getInventory().equipItem(newItem);
				}
				else
					petSummon.setWeapon(0);

				if (armorId > 0 && petSummon.getOwner().getInventory().getItemByItemId(armorId) != null)
				{
					final L2ItemInstance item = petSummon.getOwner().getInventory().getItemByItemId(armorId);
					final L2ItemInstance newItem = petSummon.getOwner().transferItem("Transfer", item.getObjectId(), 1, petSummon.getInventory(), petSummon); 
					if (newItem == null)
					{
						_log.warning("Invalid item transfer request: " + petSummon.getName() + "(pet) --> " + petSummon.getOwner().getName());
						petSummon.setArmor(0);
					}
					else
						petSummon.getInventory().equipItem(newItem);
				}
				else
					petSummon.setArmor(0);

				if (jewelId > 0 && petSummon.getOwner().getInventory().getItemByItemId(jewelId) != null)
				{
					final L2ItemInstance item = petSummon.getOwner().getInventory().getItemByItemId(jewelId);
					final L2ItemInstance newItem = petSummon.getOwner().transferItem("Transfer", item.getObjectId(), 1, petSummon.getInventory(), petSummon); 
					if (newItem == null)
					{
						_log.warning("Invalid item transfer request: " + petSummon.getName() + "(pet) --> " + petSummon.getOwner().getName());
						petSummon.setJewel(0);
					}
					else
						petSummon.getInventory().equipItem(newItem);
				}
				else
					petSummon.setJewel(0);

				petSummon.getOwner().sendPacket(new PetItemList(petSummon));
				petSummon.broadcastStatusUpdate();
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "", e);
			}
		}
	}
	public static class PetSummonFeedWait implements Runnable
	{
		private final L2PcInstance _activeChar;
		private final L2PetInstance _petSummon;

		PetSummonFeedWait(L2PcInstance activeChar, L2PetInstance petSummon)
		{
			_activeChar = activeChar;
			_petSummon = petSummon;
		}

		public void run()
		{
			try
			{
				if (_petSummon.getCurrentFed() <= 0)
					_petSummon.unSummon(_activeChar);
				else
					_petSummon.startFeed();
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "", e);
			}
		}
	}
}
