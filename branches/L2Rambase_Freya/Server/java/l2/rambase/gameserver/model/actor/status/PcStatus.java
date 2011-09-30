/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.status;

import l2.rambase.Config;
import l2.rambase.gameserver.ai.CtrlIntention;
import l2.rambase.gameserver.instancemanager.DuelManager;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Playable;
import l2.rambase.gameserver.model.actor.L2Summon;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;
import l2.rambase.gameserver.model.actor.stat.PcStat;
import l2.rambase.gameserver.model.entity.Duel;
import l2.rambase.gameserver.model.quest.QuestState;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.SystemMessage;
import l2.rambase.gameserver.skills.Formulas;
import l2.rambase.gameserver.skills.Stats;
import l2.rambase.gameserver.util.Util;
import l2.rambase.util.Rnd;


public class PcStatus extends PlayableStatus
{
	private double _currentCp = 0; //Current CP of the L2PcInstance

	public PcStatus(L2PcInstance activeChar)
	{
		super(activeChar);
	}

	@Override
	public final void reduceCp(int value)
	{
		if (getCurrentCp() > value)
			setCurrentCp(getCurrentCp() - value);
		else
			setCurrentCp(0);
	}

	@Override
	public final void reduceHp(double value, L2Character attacker)
	{
		reduceHp(value, attacker, true, false, false);
	}

	@Override
	public final void reduceHp(double value, L2Character attacker, boolean awake, boolean isDOT, boolean isHPConsumption)
	{
		if (getActiveChar().isDead())
			return;

		if (getActiveChar().isInvul())
		{
			if (attacker == getActiveChar()) 
			{ 
				if (!isDOT && !isHPConsumption) 
					return; 
			} 
			else 
				return;
		}

		if (!isHPConsumption)
		{
			if (awake && getActiveChar().isSleeping())
				getActiveChar().stopSleeping(null);
			
			if (getActiveChar().isSitting())
				getActiveChar().standUp();

			if (getActiveChar().isFakeDeath())
				getActiveChar().stopFakeDeath(null);

			if (!isDOT)
			{
				if (getActiveChar().isStunned() && Rnd.get(10) == 0)
					getActiveChar().stopStunning(null);
				if (getActiveChar().isImmobileUntilAttacked())
					getActiveChar().stopImmobileUntilAttacked(null);
			}
		}

		int fullValue = (int) value;
		int tDmg = 0;

		if (attacker != null && attacker != getActiveChar())
		{
			final L2PcInstance attackerPlayer = attacker.getActingPlayer();

			if (attackerPlayer != null)
			{
				if (attackerPlayer.isGM() && !attackerPlayer.getAccessLevel().canGiveDamage())
					return;

				if (getActiveChar().isInDuel())
				{
					if (getActiveChar().getDuelState() == Duel.DUELSTATE_DEAD)
						return;
					else if (getActiveChar().getDuelState() == Duel.DUELSTATE_WINNER)
						return;

					// cancel duel if player got hit by another player, that is not part of the duel
					if (attackerPlayer.getDuelId() != getActiveChar().getDuelId())
						getActiveChar().setDuelState(Duel.DUELSTATE_INTERRUPTED);
				}
			}

			// Check and calculate transfered damage
			final L2Summon summon = getActiveChar().getPet();
			if (summon != null && summon instanceof L2SummonInstance && Util.checkIfInRange(900, getActiveChar(), summon, true))
			{
				tDmg = (int)value * (int)getActiveChar().getStat().calcStat(Stats.TRANSFER_DAMAGE_PERCENT, 0, null, null) /100;

				// Only transfer dmg up to current HP, it should not be killed
				tDmg = Math.min((int)summon.getCurrentHp() - 1, tDmg);
				if (tDmg > 0)
				{
					summon.reduceCurrentHp(tDmg, attacker, null);
					value -= tDmg;
					fullValue = (int) value; // reduce the announced value here as player will get a message about summon damage
				}
			}

			if (attacker instanceof L2Playable)
			{
				if (getCurrentCp() >= value)
				{
					setCurrentCp(getCurrentCp() - value);   // Set Cp to diff of Cp vs value
					value = 0;                              // No need to subtract anything from Hp
				}
				else
				{
					value -= getCurrentCp();                // Get diff from value vs Cp; will apply diff to Hp
					setCurrentCp(0, false);                        // Set Cp to 0
				}
			}

			if (fullValue > 0 && !isDOT)
			{
				SystemMessage smsg;
				// Send a System Message to the L2PcInstance
				smsg = new SystemMessage(SystemMessageId.C1_RECEIVED_DAMAGE_OF_S3_FROM_C2);
				smsg.addString(getActiveChar().getName());
				smsg.addCharName(attacker);
				smsg.addNumber(fullValue);
				getActiveChar().sendPacket(smsg);

				if (tDmg > 0)
				{
					smsg = new SystemMessage(SystemMessageId.C1_RECEIVED_DAMAGE_OF_S3_FROM_C2);
					smsg.addString(getActiveChar().getPet().getName());
					smsg.addCharName(attacker);
					smsg.addNumber(tDmg);
					getActiveChar().sendPacket(smsg);

					if (attackerPlayer != null)
					{
						smsg = new SystemMessage(SystemMessageId.GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_SERVITOR);
						smsg.addNumber(fullValue);
						smsg.addNumber(tDmg);
						attackerPlayer.sendPacket(smsg);
					}
				}
			}
		}

		if (value > 0)
		{
			value = getCurrentHp() - value;
			if (value <= 0)
			{
				if (getActiveChar().isInDuel())
				{
					getActiveChar().disableAllSkills();
					stopHpMpRegeneration();
					attacker.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
					attacker.sendPacket(ActionFailed.STATIC_PACKET);

					// let the DuelManager know of his defeat
					DuelManager.getInstance().onPlayerDefeat(getActiveChar());
					value = 1;
				}
				else
					value = 0;
			}
			setCurrentHp(value);
		}

		if (getActiveChar().getCurrentHp() < 0.5)
		{
			getActiveChar().abortAttack();
			getActiveChar().abortCast();

			if (getActiveChar().isInOlympiadMode())
			{
				stopHpMpRegeneration();
				getActiveChar().setIsDead(true);
				getActiveChar().setIsPendingRevive(true);
				if (getActiveChar().getPet() != null)
					getActiveChar().getPet().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null);
				return;
			}

			getActiveChar().doDie(attacker);
			if (!Config.DISABLE_TUTORIAL)
			{
				QuestState qs = getActiveChar().getQuestState("255_Tutorial");
				if (qs != null)
					qs.getQuest().notifyEvent("CE30", null, getActiveChar());
			}
		}
	}

	@Override
	public final void setCurrentHp(double newHp, boolean broadcastPacket)
	{
		super.setCurrentHp(newHp, broadcastPacket);

		if (!Config.DISABLE_TUTORIAL
				&& getCurrentHp() <= getActiveChar().getStat().getMaxHp() * .3)
		{
			QuestState qs = getActiveChar().getQuestState("255_Tutorial");
			if (qs != null)
				qs.getQuest().notifyEvent("CE45", null, getActiveChar());
        }
	}

	@Override
	public final double getCurrentCp()
	{
		return _currentCp;
	}

	@Override
	public final void setCurrentCp(double newCp)
	{
		setCurrentCp(newCp, true);
	}

	public final void setCurrentCp(double newCp, boolean broadcastPacket)
	{
		// Get the Max CP of the L2Character
		int maxCp = getActiveChar().getStat().getMaxCp();

		synchronized (this)
		{
			if (getActiveChar().isDead())
				return;

			if (newCp < 0)
				newCp = 0;

			if (newCp >= maxCp)
			{
				// Set the RegenActive flag to false
				_currentCp = maxCp;
				_flagsRegenActive &= ~REGEN_FLAG_CP;

				// Stop the HP/MP/CP Regeneration task
				if (_flagsRegenActive == 0)
					stopHpMpRegeneration();
			}
			else
			{
				// Set the RegenActive flag to true
				_currentCp = newCp;
				_flagsRegenActive |= REGEN_FLAG_CP;

				// Start the HP/MP/CP Regeneration task with Medium priority
				startHpMpRegeneration();
			}
		}

		// Send the Server->Client packet StatusUpdate with current HP and MP to all other L2PcInstance to inform
		if (broadcastPacket)
			getActiveChar().broadcastStatusUpdate();
	}

	@Override
	protected void doRegeneration()
	{
		final PcStat charstat = getActiveChar().getStat();

		// Modify the current CP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (getCurrentCp() < charstat.getMaxCp())
			setCurrentCp(getCurrentCp() + Formulas.calcCpRegen(getActiveChar()), false);

		// Modify the current HP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (getCurrentHp() < charstat.getMaxHp())
			setCurrentHp(getCurrentHp() + Formulas.calcHpRegen(getActiveChar()), false);

		// Modify the current MP of the L2Character and broadcast Server->Client packet StatusUpdate
		if (getCurrentMp() < charstat.getMaxMp())
			setCurrentMp(getCurrentMp() + Formulas.calcMpRegen(getActiveChar()), false);

		getActiveChar().broadcastStatusUpdate(); //send the StatusUpdate packet
	}

	@Override
	public L2PcInstance getActiveChar()
	{
		return (L2PcInstance)super.getActiveChar();
	}
}