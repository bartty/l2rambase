/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package teleports.NewbieTeleport;
 
import l2.Rambase.Config;
import l2.Rambase.gameserver.model.actor.L2Npc;
import l2.Rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.Rambase.gameserver.model.quest.Quest;
import l2.Rambase.gameserver.model.quest.QuestState;
 
/**
 * @author some guy
 */
public class NewbieTeleport extends Quest
{
    //NPCs
    private static final int[] TELEPORTERS = {30598,30601,30602,30600,30599,32135};
    
    @Override
    public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
    {
        String htmltext = "";
        QuestState st = player.getQuestState(getName());
        
        if (st == null)
            return getNoQuestMsg(player);
        
        // Dark Elf
        if (event.equalsIgnoreCase("DE"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(12111,16686,-4584);
            else
                htmltext = "no-level.htm";
        }
        // Elf
        else if (event.equalsIgnoreCase("Elven"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(45478,48338,-3056);
            else
                htmltext = "no-level.htm";
        }
        // Dwarf
        else if (event.equalsIgnoreCase("Dwarf"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(115604,-178005,-896);
            else
                htmltext = "no-level.htm";
        }
        // Orc
        else if (event.equalsIgnoreCase("Orc"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(-45059,-113602,-192 );
            else
                htmltext = "no-level.htm";
        }
        // Kamael
        else if (event.equalsIgnoreCase("Kamael"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(-119712,44530,360);
            else
                htmltext = "no-level.htm";
        }
        // Human
        else if (event.equalsIgnoreCase("Human"))
        {
            if (player.getLevel() <= 20)
                player.teleToLocation(-84065,243247,-3728);
            else
                htmltext = "no-level.htm";
        }
        
        return htmltext;
    }
    
    @Override
    public String onTalk(L2Npc npc, L2PcInstance player)
    {
        String htmltext = "";
        QuestState st = player.getQuestState(getName());
        
        if (st == null)
            return null;
        
        return htmltext;
    }
    
    public NewbieTeleport(int questId, String name, String descr)
    {
        super(questId, name, descr);
        
        for (int teleporters : TELEPORTERS)
        {
            addStartNpc(teleporters);
            addTalkId(teleporters);
        }
    }
    
    public static void main(String[] args)
    {
        new NewbieTeleport(-1, NewbieTeleport.class.getSimpleName(), "teleports");
		if (Config.ENABLE_LOADING_INFO_FOR_SCRIPTS)
			_log.info("Loaded Teleports: Newbie Teleport");
    }
}