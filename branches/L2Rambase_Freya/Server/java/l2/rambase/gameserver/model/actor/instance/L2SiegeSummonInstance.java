/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.model.L2Skill;
import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;

public class L2SiegeSummonInstance extends L2SummonInstance
{
	public static final int SIEGE_GOLEM_ID = 14737;
	public static final int HOG_CANNON_ID = 14768;
	public static final int SWOOP_CANNON_ID = 14839;

	public L2SiegeSummonInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, L2Skill skill)
    {
        super(objectId, template, owner, skill);
        setInstanceType(InstanceType.L2SiegeSummonInstance);
    }

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		if (!getOwner().isGM() && !this.isInsideZone(L2Character.ZONE_SIEGE))
		{
			unSummon(getOwner());
			getOwner().sendMessage("Summon was unsummoned because it exited siege zone");
		}
	}
}