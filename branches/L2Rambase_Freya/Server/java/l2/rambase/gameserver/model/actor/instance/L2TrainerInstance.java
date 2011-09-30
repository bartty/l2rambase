/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public final class L2TrainerInstance extends L2NpcInstance
{
    /**
     * @param template
     */
    public L2TrainerInstance(int objectId, L2NpcTemplate template)
    {
        super(objectId, template);
        setInstanceType(InstanceType.L2TrainerInstance);
    }

    @Override
    public String getHtmlPath(int npcId, int val)
    {
        String pom = "";
        if (val == 0)
        {
            pom = "" + npcId;
        }
        else
        {
            pom = npcId + "-" + val;
        }

        return "data/html/trainer/" + pom + ".htm";
    }
}