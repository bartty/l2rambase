/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import l2.rambase.gameserver.model.actor.L2Npc;


public interface SpawnListener
{
    public void npcSpawned(L2Npc npc);
}
