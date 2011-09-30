/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.network.serverpackets;

import java.util.List;

import l2.rambase.gameserver.model.actor.L2Character;
import l2.rambase.gameserver.model.actor.instance.L2PetInstance;
import l2.rambase.gameserver.model.actor.instance.L2SummonInstance;

import javolution.util.FastList;


public class PartySpelled extends L2GameServerPacket
{
    private static final String _S__EE_PartySpelled = "[S] f4 PartySpelled";
    private List<Effect> _effects;
    private L2Character _activeChar;

    private class Effect
    {
    	protected int _skillId;
    	protected int _dat;
    	protected int _duration;

        public Effect(int pSkillId, int pDat, int pDuration)
        {
            _skillId = pSkillId;
            _dat = pDat;
            _duration = pDuration;
        }
    }

    public PartySpelled(L2Character cha)
    {
        _effects = new FastList<Effect>();
        _activeChar = cha;
    }

    @Override
	protected final void writeImpl()
    {
        writeC(0xf4);
        writeD(_activeChar instanceof L2SummonInstance ? 2 : _activeChar instanceof L2PetInstance ? 1 : 0);
        writeD(_activeChar.getObjectId());
        writeD(_effects.size());
        for (Effect temp : _effects)
        {
            writeD(temp._skillId);
            writeH(temp._dat);
            writeD(temp._duration / 1000);
        }

    }

    public void addPartySpelledEffect(int skillId, int dat, int duration)
    {
        _effects.add(new Effect(skillId, dat, duration));
    }

    /* (non-Javadoc)
     * @see l2.rambase.gameserver.serverpackets.ServerBasePacket#getType()
     */
    @Override
	public String getType()
    {
        return _S__EE_PartySpelled;
    }
}