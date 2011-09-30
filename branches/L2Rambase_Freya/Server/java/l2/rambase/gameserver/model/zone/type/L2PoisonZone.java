/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.zone.type;

import gnu.trove.TIntIntHashMap;

import java.util.Collection;
import java.util.concurrent.Future;

import l2.rambase.gameserver.ThreadPoolManager;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.datatables.SkillTable;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.L2Object.InstanceType;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.instance.L2MonsterInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.zone.L2ZoneType;
import l2.rambase.gameserver.network.serverpackets.EtcStatusUpdate;
import l2.rambase.util.Rnd;
import l2.rambase.util.StringUtil;


public class L2PoisonZone extends L2ZoneType
{
	private int _chance;
	private int _initialDelay;
	private int _reuse;
	private boolean _enabled;
	private boolean _bypassConditions;
	private InstanceType _target;
	private Future<?> _task;
	private TIntIntHashMap _skills;
	
	public L2PoisonZone(int id)
	{
		super(id);
		_chance = 100;
		_initialDelay = 0;
		_reuse = 30000;
		_enabled = true;
		_target = InstanceType.L2Character;
		_bypassConditions = false;
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("chance"))
		{
			_chance = Integer.parseInt(value);
		}
		else if (name.equals("initialDelay"))
		{
			_initialDelay = Integer.parseInt(value);
		}
		else if (name.equals("default_enabled"))
		{
			_enabled = Boolean.parseBoolean(value);
		}
		else if (name.equals("target"))
		{
			if (value == "pc")
			{
				_target = InstanceType.L2Playable;
			}
			else if (value == "pc_only")
			{
				_target = InstanceType.L2PcInstance;
			}
			else if (value == "npc")
			{
				_target = InstanceType.L2MonsterInstance;
			}
		}
		else if (name.equals("reuse"))
		{
			_reuse = Integer.parseInt(value);
		}
		else if (name.equals("bypassSkillConditions"))
		{
			_bypassConditions = Boolean.parseBoolean(value);
		}
		else if (name.equals("skillIdLvl"))
		{
			String[] propertySplit = value.split(";");
			_skills = new TIntIntHashMap(propertySplit.length);	
			for (String skill : propertySplit)
			{
				String[] skillSplit = skill.split("-");
				if (skillSplit.length != 2)
					_log.warning(StringUtil.concat("[L2PoisonZone]: invalid config property -> skillsIdLvl \"", skill, "\""));
				else
				{
					try
					{
						_skills.put(Integer.parseInt(skillSplit[0]), Integer.parseInt(skillSplit[1]));
					}
					catch (NumberFormatException nfe)
					{
						if (!skill.isEmpty())
						{
							_log.warning(StringUtil.concat("[L2PoisonZone]: invalid config property -> skillsIdLvl \"", skillSplit[0], "\"", skillSplit[1]));
						}
					}
				}
			}
		}
		else
			super.setParameter(name, value);
	}
	
	@Override
	protected void onEnter(L2Character character)
	{
		if (((character instanceof L2Playable) && _target.isType(InstanceType.L2Playable)) 
				|| ((character instanceof L2PcInstance) && _target.isType(InstanceType.L2PcInstance))
				|| ((character instanceof L2MonsterInstance) && _target.isType(InstanceType.L2MonsterInstance)))
		{
			if (_task == null)
			{
				synchronized(this)
				{
					if (_task == null)
						_task = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new ApplySkill(this), _initialDelay, _reuse);
				}
			}
		}
		if (character instanceof L2PcInstance)
		{
			character.setInsideZone(L2Character.ZONE_DANGERAREA, true);
			character.sendPacket(new EtcStatusUpdate((L2PcInstance) character));
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (_characterList.isEmpty() && _task != null)
		{
			_task.cancel(true);
			_task = null;
		}
		if (character instanceof L2PcInstance)
		{
			character.setInsideZone(L2Character.ZONE_DANGERAREA, false);
			if (!character.isInsideZone(L2Character.ZONE_DANGERAREA))
				character.sendPacket(new EtcStatusUpdate((L2PcInstance) character));
		}
	}
	
	public L2Skill getSkill(int skillId, int skillLvl)
	{
		return SkillTable.getInstance().getInfo(skillId, skillLvl);
	}
	
	public InstanceType getTargetType()
	{
		return _target;
	}
	
	public boolean isEnabled()
	{
		return _enabled;
	}
	
	public int getChance()
	{
		return _chance;
	}
	
	public void setZoneEnabled(boolean val)
	{
		_enabled = val;
	}
	
	protected Collection<L2Character> getCharacterList()
	{
		return _characterList.values();
	}
	
	class ApplySkill implements Runnable
	{
		private final L2PoisonZone _poisonZone;
		
		ApplySkill(L2PoisonZone zone)
		{
			_poisonZone = zone;
			if (_skills == null)
			{
				_skills = new TIntIntHashMap(1);
				_skills.put(4070, 1);
			}
		}
		
		public void run()
		{
			if (isEnabled())
			{
				for (L2Character temp : _poisonZone.getCharacterList())
				{
					if (temp != null && !temp.isDead())
					{
						if (((temp instanceof L2PcInstance && getTargetType().isType(InstanceType.L2PcInstance))) 
								|| (temp instanceof L2Playable && getTargetType().isType(InstanceType.L2Playable)) 
								|| (temp instanceof L2MonsterInstance 
										&& temp.hasAI() 
										&& temp.getAI().getIntention() != CtrlIntention.AI_INTENTION_IDLE 
										&& getTargetType().isType(InstanceType.L2MonsterInstance)) 
								&& Rnd.get(100) < getChance())
						{
							for (int skillId : _skills.keys())
							{
								if (_bypassConditions || getSkill(skillId, _skills.get(skillId)).checkCondition(temp, temp, false))
									if (temp.getFirstEffect(skillId) == null)
										getSkill(skillId, _skills.get(skillId)).getEffects(temp, temp);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
	}
	
	@Override
	public void onReviveInside(L2Character character)
	{
	}	
}