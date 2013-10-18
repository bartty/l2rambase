package quests;

import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;
import lineage2.gameserver.utils.ReflectionUtils;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.data.xml.holder.NpcHolder;
import lineage2.gameserver.model.SimpleSpawner;
import lineage2.gameserver.templates.npc.NpcTemplate;
import lineage2.gameserver.utils.Location;
import instances.TeredorCavern;

/**
 */
public class _50001_MarkOfLoyalty extends Quest implements ScriptFile
{
	private static final int AdventurerId=60000;

	/**
	 * Adventurer�fs Mark - Loyalty
	 */
	private static int MARK_LOYALTY = 17739;
	
	/**
	 * Seal of Loyalty
	 */
	private static final int SEAL_OF_LOYALTY = 17743;

	/**
	 * quest reset interval
	 */
	private static final int reset = 6*3600*1000;
	
	
	/**
	 * variable name to store last claiming time in db 
	 */
	
	public static final String varName="LoyaltyMarkExchange";
	
	
	/**
	 * 60kk on retail times a 3.0 custom multiplier
	 */
	private static final long xpAmount= (60000000 * 3);
			
	
	
	public _50001_MarkOfLoyalty()
	{
		super(false);
		addStartNpc(AdventurerId);
		addLevelCheck(85, 99);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{

		Player player = st.getPlayer();
		String htmltext = event;
		
		if(event.equalsIgnoreCase("exchange"))
		{
			long lastTime=player.getVarLong(varName,0);
			final long _curr_time = System.currentTimeMillis();
			
			if( (lastTime+reset)<=_curr_time)
			{
				player.setVar(varName, _curr_time, -1);
				st.takeItems(MARK_LOYALTY,1);
				st.giveItems(SEAL_OF_LOYALTY, 20);
				st.addExpAndSp(xpAmount,0L);
			}
			else
			{
				return "Try again later";
			}
		}
		return null;

	}

	@Override
	public void onLoad()
	{
	}

	@Override
	public void onReload()
	{
	}

	@Override
	public void onShutdown()
	{
	}
}
