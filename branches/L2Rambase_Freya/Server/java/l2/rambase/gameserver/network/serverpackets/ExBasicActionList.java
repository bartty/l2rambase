/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public final class ExBasicActionList extends L2GameServerPacket
{
	private static final String _S__FE_5E_EXBASICACTIONLIST = "[S] FE:5F ExBasicActionList";
	private static final int[] _actionsOnTransform = { 2, 3, 4, 5, 6, 7, 8, 9, 11, 40, 50, 55, 56, 63, 64, 65 };
	private static final int[] _defaultActionList;
	
	private int[] _actionIds;
	
	static
	{
		int count1 = 71; // 0 <-> (count1 - 1)
		int count2 = 89; // 1000 <-> (1000 + count2 - 1) 
		int[] actionIds = new int[count1 + count2];
		
		int index = 0;
		for (int i = 0; i < count1; i++)
		{
			actionIds[index++] = i;
		}
		for 	(int i = 0; i < count2; i++)
		{
			actionIds[index++] = 1000 + i;
		}
		_defaultActionList = actionIds;
	}
    
    public static int[] getDefaultActionList()
    {
    	return _defaultActionList;
    }
    
    public static int[] getTransformActionList()
    {
    	return _actionsOnTransform;
    }
    
    public ExBasicActionList(L2PcInstance player)
    {
    	if (player.isTransformed())
    	{
    		_actionIds = getTransformActionList();
    	}
    	else
    	{
    		_actionIds = getDefaultActionList();
    	}
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
    	return _S__FE_5E_EXBASICACTIONLIST;
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
    	writeC(0xfe);
    	writeH(0x5f);
    	writeD(_actionIds.length);
    	for (int i = 0; i < _actionIds.length; i++)
    	{
    		writeD(_actionIds[i]);
    	}
    }
}