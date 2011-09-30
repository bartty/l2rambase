/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.ai.CtrlEvent;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;

public class L2PenaltyMonsterInstance extends L2MonsterInstance
{
	private L2PcInstance _ptk;

	public L2PenaltyMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2PenaltyMonsterInstance);
	}

	@Override
	public L2Character getMostHated()
	{
		if (_ptk != null)
			return _ptk; //always attack only one person
		else
			return super.getMostHated();
	}

	public void setPlayerToKill(L2PcInstance ptk)
	{
		if (Rnd.get(100) <= 80)
		{
			CreatureSay cs = new CreatureSay(getObjectId(), Say2.ALL, getName(), "mmm your bait was delicious");
			this.broadcastPacket(cs);
		}
		_ptk = ptk;
		addDamageHate(ptk, 0, 10);
		getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, ptk);
		addAttackerToAttackByList(ptk);
	}

	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
			return false;

		if (Rnd.get(100) <= 75)
		{
			CreatureSay cs = new CreatureSay(getObjectId(), Say2.ALL, getName(), "I will tell fishes not to take your bait");
			this.broadcastPacket(cs);
		}
		return true;
	}
}
