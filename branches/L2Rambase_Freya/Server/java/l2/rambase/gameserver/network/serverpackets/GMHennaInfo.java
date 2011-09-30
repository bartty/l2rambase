/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import l2.rambase.gameserver.model.L2HennaInstance;
import l2.rambase.gameserver.model.actor.instance.L2PcInstance;


public class GMHennaInfo extends L2GameServerPacket
{
    private final L2PcInstance _activeChar;
    private final L2HennaInstance[] _hennas = new L2HennaInstance[3];
    private int _count;
    
    public GMHennaInfo(L2PcInstance activeChar)
    {
        _activeChar = activeChar;
        
        int j = 0;
        for (int i = 0; i < 3; i++)
        {
            L2HennaInstance h = _activeChar.getHenna(i+1);
            if (h != null)
            {
                _hennas[j++] = h;
            }
        }
        _count = j;
    }
    
    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#getType()
     */
    @Override
    public String getType()
    {
        return "[S] 0xf0 GMHennaInfo";
    }

    /**
     * @see l2.rambase.gameserver.network.serverpackets.L2GameServerPacket#writeImpl()
     */
    @Override
    protected void writeImpl()
    {
        writeC(0xf0);
        
        writeC(_activeChar.getHennaStatINT());
        writeC(_activeChar.getHennaStatSTR());
        writeC(_activeChar.getHennaStatCON());
        writeC(_activeChar.getHennaStatMEN());
        writeC(_activeChar.getHennaStatDEX());
        writeC(_activeChar.getHennaStatWIT());
        writeD(3); // slots
        writeD(_count); //size
        for (int i = 0; i < _count; i++)
        {
            writeD(_hennas[i].getSymbolId());
            writeD(_hennas[i].getSymbolId());
        }
    }    
}