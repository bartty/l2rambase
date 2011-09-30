/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import java.util.Map;

import l2.rambase.Config;
import l2.rambase.gameserver.ai.L2CharacterAI;
import l2.rambase.gameserver.ai.L2NpcWalkerAI;
import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.network.clientpackets.Say2;
import l2.rambase.gameserver.network.serverpackets.CreatureSay;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.gameserver.util.Broadcast;


public class L2NpcWalkerInstance extends L2Npc
{
	/**
	 * Constructor of L2NpcWalkerInstance (use L2Character and L2NpcInstance constructor).<BR><BR>
	 * @param objectId given object id
	 * @param template L2NpcTemplateForThisAi
	 */
	public L2NpcWalkerInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2NpcWalkerInstance);
		setAI(new L2NpcWalkerAI(new L2NpcWalkerAIAccessor()));
	}

	/**
	 * AI can't be deattached, npc must move always with the same AI instance.
	 * @param newAI AI to set for this L2NpcWalkerInstance
	 */
	@Override
    public void setAI(L2CharacterAI newAI)
	{
		if(!(_ai instanceof L2NpcWalkerAI))
			_ai = newAI;
	}

	@Override
    public void onSpawn()
	{
		getAI().setHomeX(getX());
		getAI().setHomeY(getY());
		getAI().setHomeZ(getZ());
	}

	/**
	 * Sends a chat to all _knowObjects
	 * @param chat message to say
	 */
	public void broadcastChat(String chat)
	{
		Map<Integer, L2PcInstance> _knownPlayers = getKnownList().getKnownPlayers();

		if(_knownPlayers == null)
		{
			if(Config.DEVELOPER)
				_log.info("broadcastChat _players == null");
			return;
		}

		//we send message to known players only!
		if(!_knownPlayers.isEmpty())
		{
			CreatureSay cs = new CreatureSay(getObjectId(), Say2.ALL, getName(), chat);
			Broadcast.toKnownPlayers(this, cs);
		}
	}

	/**
	 * NPCs are immortal
	 * @param i ignore it
	 * @param attacker  ignore it
	 * @param awake  ignore it
	 */
	@Override
    public void reduceCurrentHp(double i, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
	{}

	/**
	 * NPCs are immortal
	 * @param killer ignore it
	 * @return false
	 */
	@Override
    public boolean doDie(L2Character killer)
	{
		return false;
	}

	@Override
    public L2NpcWalkerAI getAI()
	{
		return (L2NpcWalkerAI)_ai;
	}

	protected class L2NpcWalkerAIAccessor extends L2Character.AIAccessor
	{
		/**
		 * AI can't be deattached.
		 */
		@Override
        public void detachAI()
		{}
	}
}
