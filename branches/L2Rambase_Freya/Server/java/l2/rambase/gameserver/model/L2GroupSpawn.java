/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import l2.rambase.Config;
import l2.rambase.gameserver.Territory;
import l2.rambase.gameserver.idfactory.IdFactory;
import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;


public class L2GroupSpawn extends L2Spawn
{
	private Constructor<?> _constructor;
	private L2NpcTemplate _template;

	public L2GroupSpawn(L2NpcTemplate mobTemplate) throws SecurityException, ClassNotFoundException, NoSuchMethodException
    {
		super(mobTemplate);
		_constructor = Class.forName("l2.rambase.gameserver.model.actor.instance.L2ControllableMobInstance").getConstructors()[0];
		_template = mobTemplate;

		setAmount(1);
	}

	public L2Npc doGroupSpawn()
    {
		L2Npc mob = null;

		try
		{
            if (_template.type.equalsIgnoreCase("L2Pet") ||
                    _template.type.equalsIgnoreCase("L2Minion"))
                return null;

			Object[] parameters = {IdFactory.getInstance().getNextId(), _template};
			Object  tmp = _constructor.newInstance(parameters);

			if (!(tmp instanceof L2Npc))
				return null;

			mob = (L2Npc)tmp;

			int newlocx, newlocy, newlocz;

			if  (getLocx() == 0 && getLocy() == 0)
            {
			    if (getLocation() == 0)
                    return null;

                int p[] = Territory.getInstance().getRandomPoint(getLocation());
				newlocx = p[0];
				newlocy = p[1];
				newlocz = p[2];
			}
			else
            {
				newlocx = getLocx();
				newlocy = getLocy();
				newlocz = getLocz();
			}

			mob.setCurrentHpMp(mob.getMaxHp(), mob.getMaxMp());

			if (getHeading() == -1)
				mob.setHeading(Rnd.nextInt(61794));
			else
				mob.setHeading(getHeading());

			mob.setSpawn(this);
			mob.spawnMe(newlocx, newlocy, newlocz);
			mob.onSpawn();

			if (Config.DEBUG)
				_log.finest("spawned Mob ID: "+_template.npcId+" ,at: "
						+mob.getX()+" x, "+mob.getY()+" y, "+mob.getZ()+" z");

			return mob;

		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "NPC class not found: " + e.getMessage(),  e);
			return null;
		}
	}
}