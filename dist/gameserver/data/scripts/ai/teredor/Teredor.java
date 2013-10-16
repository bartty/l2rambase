/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.teredor;

import java.util.List;
//import java.util.logging.Logger;


import lineage2.commons.threading.RunnableImpl;
import lineage2.commons.util.Rnd;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.ai.CtrlEvent;
import lineage2.gameserver.ai.CtrlIntention;
import lineage2.gameserver.ai.Fighter;
import lineage2.gameserver.data.xml.holder.NpcHolder;
import lineage2.gameserver.listener.actor.OnCurrentHpDamageListener;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.SimpleSpawner;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.utils.Location;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Party;
import lineage2.gameserver.model.Playable;
import lineage2.gameserver.tables.SkillTable;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class Teredor extends Fighter
{
	
	/**
	 * Field _log.
	 */
	private static final Logger _log = LoggerFactory.getLogger(Teredor.class);
	
	/**
	 * Field teredor, by voronius changed from the other one
	 */
	static int teredor = 25785;
	
	
	
	
	/**
	 * Field teredor.
	 */
	//static int teredor = 19160;
	
	
	/**
	 * Field eliteMillipede.
	 */
	private static final int eliteMillipede = 19015;
	/**
	 * Field teredorLairEgg.
	 */
	static int teredorLairEgg = 19023;
	/**
	 * Field coordsToSpawnEggs.
	 */
	final Location[] coordsToSpawnEggs =
	{
		new Location(176360, -185096, -3826),
		new Location(175896, -185576, -3826)
	};
	/**
	 * Field timeFromPassiveToActive.
	 */
	static int timeFromPassiveToActive = 6000;
	/**
	 * Field delayEggTask.
	 */
	private static final int delayEggTask = 90;
	/**
	 * Field _teredorActive.
	 */
	boolean _teredorActive = true;
	/**
	 * Field _eliteSpawned.
	 */
	boolean _eliteSpawned = false;
	/**
	 * Field _battleActive.
	 */
	boolean _battleActive = false;
	/**
	 * Field _jumpAttacked.
	 */
	boolean _jumpAttacked = false;
	/**
	 * Field _canUsePoison.
	 */
	boolean _canUsePoison = false;
	/**
	 * Field _poisonCasted.
	 */
	boolean _poisonCasted = false;
	/**
	 * Field teredorEggs.
	 */
	List<NpcInstance> teredorEggs;
	
	
	/**
	 * Field actor.
	 */
	private final NpcInstance actor = getActor();
	/**
	 * Field _currentHpListener.
	 */
	private final CurrentHpListener _currentHpListener = new CurrentHpListener();
	
	
	
	/**
	 * Field FLOOD.
	 */
	static final Skill Poison = SkillTable.getInstance().getInfo(14112, 1);
	
	
	
	
	
	/**
	 * Constructor for Teredor.
	 * @param actor NpcInstance
	 */
	public Teredor(NpcInstance actor)
	{
		super(actor);
		MAX_PURSUE_RANGE = 7000;
		actor.addListener(_currentHpListener);
	}
	
	/**
	 * Method onEvtAggression.
	 * @param attacker Creature
	 * @param aggro int
	 */
	@Override
	protected void onEvtAggression(Creature attacker, int aggro)
	{
		super.onEvtAggression(attacker, aggro);
	}
	
	/**
	 * Method thinkAttack.
	 */
	@Override
	protected void thinkAttack()
	{
		
		if (!_battleActive)
		{
			_battleActive = true;
			final Reflection r = actor.getReflection();
			teredorEggs = r.getAllByNpcId(teredorLairEgg, true);
			ThreadPoolManager.getInstance().scheduleAtFixedDelay(new EggSpawnTask(r), 1000, delayEggTask * 1000);
		}

		super.thinkAttack();
	}

	public void millipedeSpawn()
	{
		
		_log.info("In Teredor AI : spawning millipede");
		final Reflection r = actor.getReflection();
		final Location _coords = Location.findPointToStay(coordsToSpawnEggs[Rnd.get(1)], 50, 100);
		r.addSpawnWithoutRespawn(eliteMillipede, _coords, 0);
		r.addSpawnWithoutRespawn(eliteMillipede, _coords, 0);
		
	}
	
	
	/**
	 * Method onEvtDead.
	 * @param killer Creature
	 */
	@Override
	protected void onEvtDead(Creature killer)
	{
		_battleActive = false;
		actor.removeListener(_currentHpListener);
		super.onEvtDead(killer);
	}
	
	/**
	 * Method teleportHome.
	 */
	@Override
	protected void teleportHome()
	{
		return;
	}
	
	/**
	 * @author Mobius
	 */
	public class CurrentHpListener implements OnCurrentHpDamageListener
	{
		/**
		 * Method onCurrentHpDamage.
		 * @param actor Creature
		 * @param damage double
		 * @param attacker Creature
		 * @param skill Skill
		 * @see lineage2.gameserver.listener.actor.OnCurrentHpDamageListener#onCurrentHpDamage(Creature, double, Creature, Skill)
		 */
		@Override
		public void onCurrentHpDamage(Creature actor, double damage, Creature attacker, Skill skill)
		{
			
	
		

			if ((actor == null) || actor.isDead() || actor.getNpcId() != teredor )
			{
				return;
			}
			
			final double oldHp = actor.getCurrentHp();
			final double newHp = actor.getCurrentHp() - damage;
			final double maxHp = actor.getMaxHp();
			
			
			
			if (_teredorActive && ( (oldHp>=(0.8 * maxHp) &&  newHp <(0.8 * maxHp)) ||
					(oldHp>=(0.6 * maxHp) &&  newHp < (0.6 * maxHp)) || 
					(oldHp>=(0.4 * maxHp) &&  newHp < (0.4 * maxHp)) || 
					(oldHp>=(0.2 * maxHp) &&  newHp < (0.2 * maxHp))
					))
			{
			
				millipedeSpawn();
				_teredorActive = false;
				_eliteSpawned = false;
				ThreadPoolManager.getInstance().execute(new TeredorPassiveTask((NpcInstance) actor,attacker.getPlayer()));
				if ((newHp <= (0.8 * maxHp)) && !_canUsePoison)
				{
					_canUsePoison = true;
					
				}
			}
			else if(_teredorActive && newHp<=100)
			{
				millipedeSpawn();
			}
			else if(_teredorActive && _canUsePoison)
			{
				if (!((NpcInstance) actor).isCastingNow())
				{
				
						if(Poison!=null && canUseSkill(Poison,attacker))
						{
					
							((NpcInstance) actor).doCast(Poison,attacker, false);
						}
				}
			}
				
			
	}
	}
	
	/**
	 * @author Mobius
	 */
	public class TeredorPassiveTask extends RunnableImpl
	{
		/**
		 * Field _npc.
		 */
		NpcInstance _npc;
		Player _p;
		/**
		 * Constructor for TeredorPassiveTask.
		 * @param npc NpcInstance
		 */
		public TeredorPassiveTask(NpcInstance npc,Player p)
		{
			_npc = npc;
			_p=p;
		}
		
		/**
		 * Method runImpl.
		 */
		@Override
		public void runImpl()
		{
			if ((_npc != null) && (!_npc.isDead()) && _p!=null)
			{
				Party pty=_p.getParty();
				if(pty!=null)
				{
					List<Playable> pl=_p.getParty().getPartyMembersWithPets();
					Object[] aggro=new Object[pl.size()];
					pl.toArray(aggro);
					_npc.getAI().notifyEvent(CtrlEvent.EVT_FORGET_OBJECT,aggro);
				}
				else
				{
					_npc.getAI().notifyEvent(CtrlEvent.EVT_FORGET_OBJECT,_p);
				}
				
				
				
				_npc.getAggroList().clear();
				_npc.setTargetable(false);
				_npc.setIsInvul(true);
				setIntention(CtrlIntention.AI_INTENTION_IDLE);
				final Location _coords = Location.findAroundPosition(_npc,300,500);
				_npc.moveToLocation(_coords,10,true);
				
				ThreadPoolManager.getInstance().schedule(new TeredorActiveTask(_npc), timeFromPassiveToActive * 10);
			}
		}
	}
	
	/**
	 * @author Mobius
	 */
	public class TeredorActiveTask extends RunnableImpl
	{
		/**
		 * Field _npc.
		 */
		NpcInstance _npc;
		
		/**
		 * Constructor for TeredorActiveTask.
		 * @param npc NpcInstance
		 */
		public TeredorActiveTask(NpcInstance npc)
		{
			_npc = npc;
		}
		
		/**
		 * Method runImpl.
		 */
		@Override
		public void runImpl()
		{
			if ((_npc != null) && (!_npc.isDead()))
			{
				_npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				_npc.setTargetable(true);
				_npc.setUnAggred(false);
				_npc.setIsInvul(false);
				_eliteSpawned = false;
				_teredorActive = true;
				_jumpAttacked = false;
				_poisonCasted = false;
			}
		}
	}
	
	/**
	 * @author Mobius
	 */
	public class EggSpawnTask extends RunnableImpl
	{
		/**
		 * Field _r.
		 */
		Reflection _r;
		
		/**
		 * Constructor for EggSpawnTask.
		 * @param r Reflection
		 */
		public EggSpawnTask(Reflection r)
		{
			_r = r;
		}
		
		/**
		 * Method runImpl.
		 */
		@Override
		public void runImpl()
		{
			if (_battleActive)
			{
				final Location _coords = Location.findPointToStay(coordsToSpawnEggs[Rnd.get(1)], 50, 100);
				_r.addSpawnWithoutRespawn(teredorLairEgg, _coords, 0);
			}
		}
	}
}
