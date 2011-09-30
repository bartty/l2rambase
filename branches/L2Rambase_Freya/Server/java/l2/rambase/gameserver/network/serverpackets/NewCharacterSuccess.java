/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.templates.chars.L2PcTemplate;

import javolution.util.FastList;


public class NewCharacterSuccess extends L2GameServerPacket
{
	private static final String _S__23_CHARTEMPLATES = "[S] 0d CharTemplates";
	private List<L2PcTemplate> _chars = new FastList<L2PcTemplate>();

    public NewCharacterSuccess()
    {        
    }
    
	public void addChar(L2PcTemplate template)
	{
		_chars.add(template);
	}

	@Override
	protected final void writeImpl()
	{
		writeC(0x0d);
		writeD(_chars.size());

		for (L2PcTemplate temp : _chars)
		{
			if (temp == null)
				continue;
			
			writeD(temp.race.ordinal());
			writeD(temp.classId.getId());
			writeD(0x46);
			writeD(temp.baseSTR);
			writeD(0x0a);
			writeD(0x46);
			writeD(temp.baseDEX);
			writeD(0x0a);
			writeD(0x46);
			writeD(temp.baseCON);
			writeD(0x0a);
			writeD(0x46);
			writeD(temp.baseINT);
			writeD(0x0a);
			writeD(0x46);
			writeD(temp.baseWIT);
			writeD(0x0a);
			writeD(0x46);
			writeD(temp.baseMEN);
			writeD(0x0a);
		}
	}

	/* (non-Javadoc)
	 * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__23_CHARTEMPLATES;
	}
}