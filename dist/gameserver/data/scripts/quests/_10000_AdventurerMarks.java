/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package quests;

import java.util.ArrayList;
import java.util.List;

import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;


import lineage2.gameserver.listener.actor.OnDeathListener;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.scripts.Functions;
import lineage2.commons.util.Rnd;

public class _10000_AdventurerMarks extends Quest implements ScriptFile
{
	private static final List<Integer> mobs1 = new ArrayList<>();
	static
	{
		for (int i=22931;i<22946;++i) 
		{
			mobs1.add(i);
		}
	}
	
	
	//gOG
	private static final List<Integer> mobs2 = new ArrayList<>();
	static
	{
		
		//GOg day and night time mobs
		for (int i=22947;i<22964;++i) 
		{
			mobs2.add(i);
		}
		

		
		//Fountain keeper
		mobs2.add(23039);
		//garden chief priest and minions
		mobs2.add(23160);
		mobs2.add(23161);
		
		
		
	}
	

	
	
	private static final List<Integer> mobs1and2 = new ArrayList<>();
	static
	{
		for (int i : new int[]
		{
				//SOA
				
				22746,
				22747,
				22748,
				22749,
				22750,
				22751,
				22752,
				22753,
				22754,
				22755,
				22756,
				22757,
				22758,
				22759,
				22760,
				22761,
				22762,
				22763,
				22764,
				22765,

		})
		{
			mobs1and2.add(i);
		}
	}
	
	
/* keep this for energy quest
 
	private final static int[] mobs1 =
		{
		
		
		//bloody swampland
			23162,
			23163,
			23164,
			23165,
			23166,
			23167,
			23168,
			23169,
			23170,
			23171,
			
		}
		
*/

	
	private static final List<Integer> mobs3 = new ArrayList<>();
	static
	{
		for (int i=22911;i<22930;++i) 
		{
			mobs3.add(i);
		}
		
	}
	

	

	
	/* mobs4 are the same as mobs 3

	*/	
	
	/**
	 * Adventurer�fs Mark - Loyalty
	 */
	private static int MARK_LOYALTY = 17739;
	/**
	 * Adventurer�fs Mark - Pledge
	 */
	private static int MARK_PLEDGE = 17740;
	
	
	/**
	 * Adventurer�fs Mark - Sincerity
	 */
	private static int MARK_SINCERITY = 17741;
	/**
	 * Adventurer�fs Mark - Spirit
	 */
	private static int MARK_SPIRIT = 17742;
	
	
	
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
	
	public _10000_AdventurerMarks()
	{
		super(true);
		addKillId(mobs1);
		addKillId(mobs2);
		addKillId(mobs1and2);
		addKillId(mobs3);

	}
	
	public void dropMarks(NpcInstance npc, QuestState st)
	{
		Player p=st.getPlayer();
		if(p==null)
			return;

		if(!p.canGetDailyMark())
		{
			
		}
		
		/* 30% chance, was 10% */
		if(Rnd.get(1,100)<70)
			return;
		
		int npcId = npc.getNpcId();
		
		/*1st 2 can be compressed  - i keep them as such for clarity */
		if(p.getLevel()>96 && mobs3.contains(npcId))
		{
			Functions.addItem(p, MARK_LOYALTY, 1);
			Functions.addItem(p, MARK_SPIRIT, 1);
			
		}
		else if (p.getLevel()>94 && mobs3.contains(npcId))
		{
			Functions.addItem(p, MARK_LOYALTY, 1);
			Functions.addItem(p, MARK_SINCERITY, 1);
		}
		else if (p.getLevel()>89 && (mobs2.contains(npcId) || mobs1and2.contains(npcId) ))
		{
			Functions.addItem(p, MARK_LOYALTY, 1);
			Functions.addItem(p, MARK_PLEDGE, 1);
		}
		else if (p.getLevel()>84 && ( mobs1and2.contains(npcId) || mobs1.contains(npcId) ) )
		{
			Functions.addItem(p, MARK_LOYALTY, 1);
		}
		else
		{
			return;
		}
		
		p.disableDailyMark();
		st.exitCurrentQuest(this);
	}

	
	
	
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(!st.isNowAvailableByTime())
		{
				return null;
		}
		if ((st.getState() == STARTED))
		{
			dropMarks(npc,st);
		}
		return null;
	}

}
