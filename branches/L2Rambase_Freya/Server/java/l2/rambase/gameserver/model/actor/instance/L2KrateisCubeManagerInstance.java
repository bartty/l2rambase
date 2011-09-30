/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.gameserver.datatables.TeleportLocationTable;
import l2.rambase.gameserver.instancemanager.KrateisCubeManager;
import l2.rambase.gameserver.model.L2TeleportLocation;
import l2.rambase.gameserver.network.SystemMessageId;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;
import l2.rambase.util.Rnd;

import java.util.StringTokenizer;


public class L2KrateisCubeManagerInstance extends L2NpcInstance
{

    public L2KrateisCubeManagerInstance(int objectId, L2NpcTemplate template)
    {
        super(objectId, template);
    }

    public void onBypassFeedback(L2PcInstance player, String command)
    {
        StringTokenizer st = new StringTokenizer(command, " ");
        @SuppressWarnings("unused")
		String actualCommand = st.nextToken();
        if(command.startsWith("Register"))
        {
            if((double)player.getInventoryLimit() * 0.80000000000000004D <= (double)player.getInventory().getSize())
            {
                player.sendPacket(SystemMessageId.INVENTORY_LESS_THAN_80_PERCENT);
                showChatWindow(player, "data/html/krateisCube/32503-9.htm");
                return;
            }
            @SuppressWarnings("unused")
			boolean canParticipate = true;
            int cmdChoice = Integer.parseInt(command.substring(9, 10).trim());
            switch(cmdChoice)
            {
            case 1: // '\001'
                if(player.getLevel() < 70 || player.getLevel() > 75)
                {
                    showChatWindow(player, "data/html/krateisCube/32503-7.htm");
                    return;
                }
                break;

            case 2: // '\002'
                if(player.getLevel() < 76 || player.getLevel() > 79)
                {
                    showChatWindow(player, "data/html/krateisCube/32503-7.htm");
                    return;
                }
                break;

            case 3: // '\003'
                if(player.getLevel() < 80)
                {
                    showChatWindow(player, "data/html/krateisCube/32503-7.htm");
                    return;
                }
                break;
            }
            if(KrateisCubeManager.getInstance().isTimeToRegister())
            {
                if(KrateisCubeManager.getInstance().registerPlayer(player))
                {
                    showChatWindow(player, "data/html/krateisCube/32503-4.htm");
                    return;
                } else
                {
                    showChatWindow(player, "data/html/krateisCube/32503-5.htm");
                    return;
                }
            } else
            {
                showChatWindow(player, "data/html/krateisCube/32503-8.htm");
                return;
            }
        }
        if(command.startsWith("Rotate_Watcher_Mode"))
        {
            int _rndMode = Rnd.get(0, 1000) + 1;
            int _players = KrateisCubeManager.getInstance().getPlayersCount();
            if(_players < 0)
            {
                showChatWindow(player, "data/html/krateisCube/not_running.htm");
            }
            else if((++_players * 40 + _rndMode) % 30 == 0)
            {
            	showChatWindow(player, "data/html/krateisCube/watcher_idle.htm");
            }
            else if(_players * 40 > _rndMode)
            {
            	showChatWindow(player, "data/html/krateisCube/watcher_bonus.htm");
            }
            else if(_players * 40 <= _rndMode)
            {
            	showChatWindow(player, "data/html/krateisCube/watcher_bogus.htm");
            }
            return;
        }
        if(command.startsWith("Cancel"))
        {
            KrateisCubeManager.getInstance().removePlayer(player);
            showChatWindow(player, "data/html/krateisCube/32503-6.htm");
            return;
        }
        if(command.startsWith("TeleportIn"))
        {
            KrateisCubeManager.getInstance().teleportPlayerIn(player);
            return;
        }
        if(command.startsWith("goto"))
        {
            if(st.countTokens() <= 0)
                return;
            int whereTo = Integer.parseInt(st.nextToken());
            L2TeleportLocation list = TeleportLocationTable.getInstance().getTemplate(whereTo);
            if(list == null || player.isAlikeDead())
            {
                return;
            } else
            {
                player.teleToLocation(list.getLocX(), list.getLocY(), list.getLocZ(), true);
                return;
            }
        } else
        {
            super.onBypassFeedback(player, command);
            return;
        }
    }

    public String getHtmlPath(int npcId, int val)
    {
        String pom = "";
        if(val == 0)
            pom = (new StringBuilder()).append("").append(npcId).toString();
        else
            pom = (new StringBuilder()).append(npcId).append("-").append(val).toString();
        return (new StringBuilder()).append("data/html/krateisCube/").append(pom).append(".htm").toString();
    }
}
