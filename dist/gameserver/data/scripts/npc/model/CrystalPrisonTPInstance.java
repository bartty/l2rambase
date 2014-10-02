package npc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import instances.Vullock;
import instances.Baylor;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.templates.npc.NpcTemplate;
import lineage2.gameserver.utils.ReflectionUtils;

/**
 * @author Awakeninger
 */

public final class CrystalPrisonTPInstance extends NpcInstance {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int VullockInstance = 166;
    private static final int BaylorInstance = 167;

    private static final Logger _log = LoggerFactory.getLogger(CrystalPrisonTPInstance.class);
    
    public CrystalPrisonTPInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this))
        {
        	_log.info("can't bypass feed");
        	return;
        }

        if (command.startsWith("request_vallock")) {
            Reflection r = player.getActiveReflection();
            if (r != null) {
                if (player.canReenterInstance(VullockInstance))
                    player.teleToLocation(r.getTeleportLoc(), r);
            } else if (player.canEnterInstance(VullockInstance)) 
            {
            	_log.info("CrystalPrisonTPInstance - Starting new Vullock");
                ReflectionUtils.enterReflection(player, new Vullock(), VullockInstance);
                
            }
        } else if (command.equalsIgnoreCase("request_Baylor")) {
            Reflection r = player.getActiveReflection();
            if (r != null) {
                if (player.canReenterInstance(BaylorInstance))
                    player.teleToLocation(r.getTeleportLoc(), r);
            } else if (player.canEnterInstance(BaylorInstance)) 
            {
            	_log.info("CrystalPrisonTPInstance - Starting new Baylor");
                ReflectionUtils.enterReflection(player, new Baylor(), BaylorInstance);
            }
        }else
        {
        	_log.info("sent command to parent");
        	super.onBypassFeedback(player, command);
        }
    }
}