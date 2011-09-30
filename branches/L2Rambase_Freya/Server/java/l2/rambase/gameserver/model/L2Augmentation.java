/**
 * @autohr L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.datatables.AugmentationData;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.network.serverpackets.SkillCoolTime;
import l2.rambase.gameserver.skills.Stats;
import l2.rambase.gameserver.skills.funcs.FuncAdd;
import l2.rambase.gameserver.skills.funcs.LambdaConst;

import javolution.util.FastList;


public final class L2Augmentation
{
	private int _effectsId = 0;
	private AugmentationStatBoni _boni = null;
	private L2Skill _skill = null;

	public L2Augmentation(int effects, L2Skill skill)
	{
		_effectsId = effects;
		_boni = new AugmentationStatBoni(_effectsId);
		_skill = skill;
	}

	public L2Augmentation(int effects, int skill, int skillLevel)
	{
		this(effects, SkillTable.getInstance().getInfo(skill, skillLevel));
	}

	// =========================================================
	// Nested Class

	public class AugmentationStatBoni
	{
		private Stats _stats[];
		private float _values[];
		private boolean _active;

		public AugmentationStatBoni(int augmentationId)
		{
			_active = false;
			FastList <AugmentationData.AugStat> as = AugmentationData.getInstance().getAugStatsById(augmentationId);

			_stats = new Stats[as.size()];
			_values = new float[as.size()];

			int i=0;
			for (AugmentationData.AugStat aStat : as)
			{
				_stats[i] = aStat.getStat();
				_values[i] = aStat.getValue();
				i++;
			}
		}

		public void applyBonus(L2PcInstance player)
		{
			// make sure the bonuses are not applied twice..
			if (_active) return;

			for (int i=0; i < _stats.length; i++)
				((L2Character)player).addStatFunc(new FuncAdd(_stats[i], 0x40, this, new LambdaConst(_values[i])));

			_active = true;
		}

		public void removeBonus(L2PcInstance player)
		{
			// make sure the bonuses are not removed twice
			if (!_active) return;

			((L2Character)player).removeStatsOwner(this);

			_active = false;
		}
	}

	public int getAttributes()
	{
		return _effectsId;
	}

	/**
	 * Get the augmentation "id" used in serverpackets.
	 * @return augmentationId
	 */
	public int getAugmentationId()
	{
		return _effectsId;
	}

	public L2Skill getSkill()
	{
		return _skill;
	}

	/**
	 * Applies the bonuses to the player.
	 * @param player
	 */
	public void applyBonus(L2PcInstance player)
	{
        boolean updateTimeStamp = false;
        _boni.applyBonus(player);
        if(_skill != null)
        {
            player.addSkill(_skill);
            if(_skill.isActive())
            {
                if(player.getReuseTimeStamp().isEmpty() || !player.getReuseTimeStamp().containsKey(Integer.valueOf(_skill.getId())))
                {
                    int equipDelay = _skill.getEquipDelay();
                    if(equipDelay > 0)
                    {
                        player.addTimeStamp(_skill, equipDelay);
                        player.disableSkill(_skill, equipDelay);
                    }
                }
                updateTimeStamp = true;
            }
            player.sendSkillList();
            if(updateTimeStamp)
                player.sendPacket(new SkillCoolTime(player));
        }
	}
	
	/**
	 * Removes the augmentation bonuses from the player.
	 * @param player
	 */
	public void removeBonus(L2PcInstance player)
	{
	    _boni.removeBonus(player);
		
		// remove the skill if any
		if (_skill != null)
		{
			if (_skill.isPassive())
				player.removeSkill(_skill);
			else
				player.removeSkill(_skill, false, false);

			player.sendSkillList();
		}
	}
}
