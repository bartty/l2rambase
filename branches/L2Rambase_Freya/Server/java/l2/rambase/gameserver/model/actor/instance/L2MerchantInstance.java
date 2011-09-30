/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model.actor.instance;

import l2.rambase.Config;
import l2.rambase.gameserver.TradeController;
import l2.rambase.gameserver.datatables.MerchantPriceConfigTable;
import l2.rambase.gameserver.datatables.MerchantPriceConfigTable.MerchantPriceConfig;
import l2.rambase.gameserver.model.L2TradeList;
import l2.rambase.gameserver.network.serverpackets.ActionFailed;
import l2.rambase.gameserver.network.serverpackets.ExBuySellListPacket;
import l2.rambase.gameserver.templates.chars.L2NpcTemplate;


public class L2MerchantInstance extends L2NpcInstance
{
	private MerchantPriceConfig _mpc;

	/**
	 * @param template
	 */
	public L2MerchantInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2MerchantInstance); 
		
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		_mpc = MerchantPriceConfigTable.getInstance().getMerchantPriceConfig(this);
	}

	@Override
	public String getHtmlPath(int npcId, int val)
	{
		String pom = "";

		if (val == 0) pom = "" + npcId;
		else pom = npcId + "-" + val;

		return "data/html/merchant/" + pom + ".htm";
	}

	/**
	 * @return Returns the mpc.
	 */
	public MerchantPriceConfig getMpc()
	{
		return _mpc;
	}

	public final void showBuyWindow(L2PcInstance player, int val)
	{
		double taxRate = 0;

		taxRate = getMpc().getTotalTaxRate();

		player.tempInventoryDisable();

		if (Config.DEBUG)
			_log.fine("Showing buylist");

		L2TradeList list = TradeController.getInstance().getBuyList(val);

		if (list != null && list.getNpcId().equals(String.valueOf(getNpcId())))
			player.sendPacket(new ExBuySellListPacket(player, list, taxRate, false));
		else
		{
			_log.warning("possible client hacker: "+player.getName()+" attempting to buy from GM shop! < Ban him!");
			_log.warning("buylist id:" + val);
		}

		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}
