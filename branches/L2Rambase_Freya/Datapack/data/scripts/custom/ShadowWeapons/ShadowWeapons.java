/**
 * @author L2Rambase Project
 */
package custom.ShadowWeapons;

import l2.rambase.gameserver.model.actor.L2Npc;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;
import l2.rambase.gameserver.model.quest.Quest;
import l2.rambase.gameserver.model.quest.QuestState;

public class ShadowWeapons extends Quest
{
	private static final String qn = "ShadowWeapons";
	
	//NPCs that would need to figure out what to show when asked about coupons exchange
	private static final int[] NPC = {
		30037,30066,30070,30109,30115,30120,30174,30175,30176,30187,30191,30195,
		30288,30289,30290,30297,30373,30462,30474,30498,30499,30500,30503,30504,
		30505,30511,30512,30513,30595,30676,30677,30681,30685,30687,30689,30694,
		30699,30704,30845,30847,30849,30854,30857,30862,30865,30894,30897,30900,
		30905,30910,30913,31269,31272,31288,31314,31317,31321,31324,31326,31328,
		31331,31334,31336,31965,31974,31276,31285,31958,31961,31996,31968,31977,
		32092,32093,32094,32095,32096,32097,32098,32193,32196,32199,32202,32205,
		32206,32213,32214,32221,32222,32229,32230,32233,32234
	};
	//itemId for shadow weapon coupons, it's not used more than once but increases readability
	private static final int D_COUPON = 8869;
	private static final int C_COUPON = 8870;
	

	public ShadowWeapons(int id, String name, String descr)
	{
		super(id, name, descr);
		for (int i : NPC)
		{
			addStartNpc(i);
			addTalkId(i);
		}
	}

	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(qn);
		if (st == null)
		{
			st = newQuestState(player);
		}
		String htmltext = "";
		long has_d = st.getQuestItemsCount(D_COUPON);
	    long has_c = st.getQuestItemsCount(C_COUPON);
	    if  (has_d > 0 || has_c > 0)
	    {
	      //let's assume character had both c & d-grade coupons, we'll confirm later
	      String multisell = "306893003";
	      if (has_d < 1)
	      {
	         //if s/he had c-grade only...
	         multisell = "306893002";
	      }
	      else if (has_c < 1)
	      {
	         //or d-grade only.
	         multisell = "306893001";
	      }
	      //finally, return htm with proper multisell value in it.
	      htmltext = st.showHtmlFile("multisell.htm").replace("%multisell%",String.valueOf(multisell));

	    }
	    else
	    {
	    	htmltext = "<html><body>";
	    	htmltext += "You don't have a Shadow weapon exchange coupon.<br>";
	    	htmltext += "A shadow Weapon exchange coupon is a<font color=\"LEVEL\"> gift received from a Grand Master, Magister or High Priest when you complete a class transfer.</font><br>";
	    	htmltext += "</body></html>";
	    }
		return htmltext;
	}

	public static void main(String[] args)
	{
		new ShadowWeapons(-1, qn, "custom");
	}
}