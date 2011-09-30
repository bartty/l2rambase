package ai.fantasy_isle;

import l2.rambase.gameserver.instancemanager.QuestManager;

public class StartMCShow implements Runnable {
	@Override
	public void run() {
		try {
		QuestManager.getInstance().getQuest("MC_Show").notifyEvent("Start", null, null);
		} catch (Exception e){}
	}
}
