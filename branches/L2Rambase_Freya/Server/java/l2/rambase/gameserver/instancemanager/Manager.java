/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.instancemanager;

public class Manager
{

	public static void reloadAll()
	{
		AuctionManager.getInstance().reload();
	}
}